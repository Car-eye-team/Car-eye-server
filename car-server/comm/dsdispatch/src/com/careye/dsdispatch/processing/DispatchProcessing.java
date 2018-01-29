/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */
package com.careye.dsdispatch.processing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.jms.MapMessage;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.careye.dsdispatch.constant.ConfigProperties;
import com.careye.dsdispatch.constant.Constant;
import com.careye.dsdispatch.encoder.JsonEncoder;
import com.careye.dsdispatch.mq.JmsBusinessSender;
import com.careye.dsdispatch.mq.JmsCommSender;
import com.careye.dsdispatch.redis.RedisManager;
import com.careye.dsdispatch.timer.AssignmentDispatchTimer;
import com.careye.dsdispatch.timer.ImmediateDispatchTimer;
import com.careye.dsdispatch.utlis.DateUtil;
import com.careye.dsdispatch.utlis.DispatchUtil;
import com.careye.dsdispatch.utlis.Gps;
import com.careye.dsdispatch.utlis.PositionUtil;
import com.careye.redis.domain.CallInfo;
import com.careye.redis.domain.CarInfo;
import com.careye.redis.domain.DispatchRule;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：DispatchProcessing    
 * 类描述：调派业务处理    
 * 创建人：zr    
 * 创建时间：2015-5-20 下午03:02:20    
 * 修改人：zr    
 * 修改时间：2015-5-20 下午03:02:20    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DispatchProcessing {

	private final static Logger logger = Logger.getLogger(DispatchProcessing.class);

	/**
	 * 电召业务调度
	 * @param msgid
	 * @param jsonObject
	 */
	public static void dispatch(int msgid,JSONObject jsonObject,String msgbody,int devicetype){

		//根据订单时间查找调度规则
		if(jsonObject != null){

			try {

				CallInfo callInfo =  (CallInfo) JSONObject.toBean(jsonObject, CallInfo.class);

				//type 订单类型  1 即时调度 2 策略预约调度策略 3 指派模式 4 再调度
				int type = 1;
				String orderid = callInfo.getOrderid();
				String terminal = callInfo.getTerminal();
				if (orderid != null || !"0".equals(orderid)) {

					//判断用户经纬度是否存在
					int lng = callInfo.getStartlng();
					int lat = callInfo.getStartlat();

					if(lng == 0 || lat == 0){
						logger.info("订单号["+orderid+"] 乘客所在地经纬度信息不正常,系统将不进行调度 ["+lng+"] ["+lat+"]");
					}else{

						logger.info("订单号["+orderid+"] 招标JSON信息中终端设备号[ 0 即时、预约订单 否则 指派模式(直接发送不需要进行调度)] ["+terminal+"]");

						//终端设备号传入数据为0 则为即时、预约订单，否则为指派模式订单
						if("0".equals(terminal)){
							//获取订单时间
							String ordertime = jsonObject.getString("ordertime");
							if(ordertime == null || "".equals(ordertime)){
								ordertime = DateUtil.getSQLDate();
							}

							//根据订单时间查找对应的调度规则
							//语音叫车作为即时订单处理
							if(msgid == 21001){
								type = 1;
							}else{
								//0x00：即时订单。0x01：预约订单。
								int ordertype = jsonObject.getInt("ordertype");
								if(ordertype == 0){
									type = 1;
								}else{
									type = 2;
								}
							}

							DispatchRule dispatchRule = DispatchRuleProcessing.getDispatchRule(ordertime, type);
							if(dispatchRule != null){

								//处理即时订单
								if(type == 1 ){
									logger.info("订单号["+orderid+"] 即时订单");
								}else{
									//预约订单处理，先按照即时订单处理,如果调派指定轮次之后，将停止调派，等待预约订单前多少分钟进行调派。
									logger.info("订单号["+orderid+"] 预约订单");

									//将预约订单号加入redis缓存中
									RedisManager.getInstance().addSet(Constant.BOOKING_ORDERID, orderid);

								}

								logger.info("订单号["+orderid+"] 即时订单处理：半径大小["+dispatchRule.getRadius()+"米],车辆类型[1.出租车 默认1,可多选逗号隔开] ["+dispatchRule.getCartype()+"],空重车状态["+dispatchRule.getZkstate()+"],车辆调度状态["+dispatchRule.getCarstatus()+"],选择车辆数量["+dispatchRule.getCarnum()+"],调派轮次["+dispatchRule.getDisnumber()+"],调派间隔["+dispatchRule.getDispatchinterval()+"]");

								callInfo.setDevicetype(devicetype);
								//将调度消息加入redis缓存中
								RedisManager.getInstance().setObject(Constant.DISPATCH_ORDERID+orderid, callInfo);

								int interval = dispatchRule.getDispatchinterval();
								//启动定时器进行调派
								Timer timer = new Timer();
								ImmediateDispatchTimer dispatchTimer = new ImmediateDispatchTimer(dispatchRule,callInfo,msgbody,type,devicetype);
								timer.schedule(dispatchTimer,0, interval*1000);	

							}else{
								logger.info("订单号["+orderid+"] 调度策略不存在,系统将不进行调度");
							}

						}else{

							//指派模式,直接发送至对应车辆
							logger.info("订单号["+orderid+"] 指派订单");

							//获取订单时间
							String ordertime = jsonObject.getString("ordertime");
							if(ordertime == null || "".equals(ordertime)){
								ordertime = DateUtil.getSQLDate();
							}

							//获取指派订单调度规则
							DispatchRule dispatchRule = DispatchRuleProcessing.getDispatchRule(ordertime, 3);
							if(dispatchRule != null){

								logger.info("订单号["+orderid+"] 指派订单处理：半径大小["+dispatchRule.getRadius()+"米],车辆类型[1.出租车 默认1,可多选逗号隔开] ["+dispatchRule.getCartype()+"],空重车状态["+dispatchRule.getZkstate()+"],车辆调度状态["+dispatchRule.getCarstatus()+"],选择车辆数量["+dispatchRule.getCarnum()+"],调派轮次["+dispatchRule.getDisnumber()+"],调派间隔["+dispatchRule.getDispatchinterval()+"]");

								//将调度消息加入redis缓存中
								callInfo.setDevicetype(devicetype);
								RedisManager.getInstance().setObject(Constant.DISPATCH_ORDERID+orderid, callInfo);

								int interval = dispatchRule.getDispatchinterval();
								//启动定时器进行调派
								Timer timer = new Timer();
								AssignmentDispatchTimer assignmentDispatchTimer = new AssignmentDispatchTimer(dispatchRule,callInfo,msgbody,devicetype);
								timer.schedule(assignmentDispatchTimer,0, interval*1000);	

							}else{
								logger.info("订单号["+orderid+"] 调度策略不存在,指定车辆发送一次");

								sendCommServer(msgid, terminal, 22, msgbody, orderid,0);
								//将发送的终端设备号（招标发送车辆）写入业务分发服务器上行队列中
								String jsonMsg = JsonEncoder.encoderBidSendCar(orderid,terminal);
								sendBusinessServer(20753,jsonMsg,devicetype);
							}

						}
					}
				}else{
					logger.info("订单号["+orderid+"] 招标信息订单号为空,系统将不进行调度");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * 查找乘客周边车辆
	 * @param lng 乘客经度
	 * @param lat 乘客纬度
	 * @param distance 距离（单位米）
	 * @param msgid 协议消息ID
	 * @param msgbody 十六进制数据包
	 * @param carnum 调派车辆数
	 */
	public static void findPeripheralVehicle(String orderid,int lng,int lat,int distance,int msgid,String msgbody,int carnum,int devicetype){

		try {

			//将乘客经纬度转换成真实经纬度信息
			double dlng = new BigDecimal(lng).doubleValue() / 1000000;
			double dlat = new BigDecimal(lat).doubleValue() / 1000000;
			//将百度坐标转换成真实坐标
			Gps gps = PositionUtil.bd09_To_Gps84(dlat, dlng);
			lng  = new BigDecimal(gps.getWgLon() * Math.pow(10, 6)).intValue();
			lat  = new BigDecimal(gps.getWgLat() * Math.pow(10, 6)).intValue();

			StringBuffer terBuffer = new StringBuffer();

			//如果设定1千米范围内 则车1千米范围的车辆 如果1至2千米 则要查2千米返回的车辆
			int diff = distance/1000+1;

			//先获取乘客周边1千米范围内的车辆列表
			List<CarInfo> list = DispatchUtil.searchTerMap(lng, lat, diff);

			if(list != null){

				//获取符合条件的车辆列表（距离在调派范围之内，调派车辆数在调度规则范围内）
				List<CarInfo> caList = DispatchUtil.getEligibleCarInfo(list, distance, lng, lat);

				if(caList != null){

					logger.info("订单号["+orderid+"] 找到["+caList.size()+"]辆符合调派规则的车辆");
					int i = 0;
					for (CarInfo carInfo : caList) {

						//可调派车辆数大于调派车辆数时，后面的车辆将不再调派
						if(i>carnum){
							break;
						}

						if(!"".equals(terBuffer.toString())){
							terBuffer.append(",");
						}
						terBuffer.append(carInfo.getTerminal());

						//发送至接入服务器
						sendCommServer(msgid, carInfo.getTerminal(), 22, msgbody, orderid,carInfo.getDistance());

						i+=1;

					}
				}else{
					logger.info("订单号["+orderid+"] 未找到合适的车辆进行调派");
				}
			}

			if(!"".equals(terBuffer.toString())){
				//将发送的终端设备号（招标发送车辆）写入业务分发服务器上行队列中
				String jsonMsg = JsonEncoder.encoderBidSendCar(orderid, terBuffer.toString());
				sendBusinessServer(20753,jsonMsg,devicetype);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}  

	/**
	 * 处理电召抢答
	 * @param msgid
	 * @param jsonObject
	 * @param msgbody
	 */
	public static void callAnswer(int msgid,String terminal,JSONObject jsonObject,String msgbody){

		try {

			String orderid = jsonObject.getString("orderid");
			logger.info("订单号["+orderid+"] 已被车辆["+terminal+"] 抢答,删除对应订单redis缓存中信息");
			RedisManager.getInstance().delSet(Constant.BOOKING_ORDERID, orderid);
			RedisManager.getInstance().delObject(Constant.DISPATCH_ORDERID+orderid);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 取消电召
	 * @param msgid
	 * @param jsonObject
	 */
	public static void cancelCall(JSONObject jsonObject){

		try {

			String orderid = jsonObject.getString("orderid");
			logger.info("订单号["+orderid+"] 已被用户取消,删除对应订单redis缓存中信息");
			RedisManager.getInstance().delSet(Constant.BOOKING_ORDERID, orderid);
			RedisManager.getInstance().delObject(Constant.DISPATCH_ORDERID+orderid);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * 发送至接入服务器下行消息队列
	 * @param msgid
	 * @param terminal
	 * @param devicetype
	 * @param msgbody
	 * @param orderid
	 */
	public static void sendCommServer(int msgid,String terminal,int devicetype,String msgbody,String orderid,int distance){

		try {

			//从redis缓存中获取车辆车牌号、车辆ID、设备号信息
			CarInfo cInfo = (CarInfo) RedisManager.getInstance().getObject(Constant.CARINFO_REDIS_NAME+terminal);
			if(cInfo != null){
				devicetype = cInfo.getDevicetype();

				logger.info("订单号["+orderid+"] 找到符合条件车辆 [设备类型:"+cInfo.getDevicetype()+"] [车辆ID:"+cInfo.getCarid()+"] [设备号:"+terminal+"] [车牌号:"+cInfo.getCarnumber()+"] 相距["+distance+"]米");

				//根据设备号获取当前设备的路由信息
				/*Map<String, String> map = (Map<String, String>) RedisManager.getInstance().getObject("clientroute");
				String ip = map.get(terminal);*/
				StringBuffer keyBuffer = new StringBuffer();
				keyBuffer.append(Constant.CLIENT_ROUTE);
				keyBuffer.append(terminal);
				String clientroute = RedisManager.getInstance().get(keyBuffer.toString());

				String[] routearray = clientroute.split(",");
				//路由IP
				String ip = routearray[0]; 
				//服务器类型 1 TCP/IP 服务器 2 UDP 服务器
				int servertype = Integer.parseInt(routearray[1]);

				if(ip != null){
					//写入至接入服务器下行队列
					StringBuffer mqBuffer = new StringBuffer();
					String msg = null;
					if(servertype == 1){
						msg = "TCP接入服务器";
						mqBuffer.append(ConfigProperties.DS_COMM_DOWN_QUEUE_NAME);
					}else{
						msg = "UDP接入服务器";
						mqBuffer.append(ConfigProperties.DS_UDP_DOWN_QUEUE_NAME);
					}
					mqBuffer.append("_");
					mqBuffer.append(ip);

					logger.info("订单号["+orderid+"] 消息写入至"+msg+"下行队列 ["+mqBuffer.toString()+"]");

					//将对应消息插入接入服务器下行队列中
					Map<String, Object> map = new HashMap<String, Object>();       
					map.put("msgid",msgid);
					map.put("seq", 0);
					map.put("terminal",terminal);
					map.put("msgbody", msgbody);
					map.put("devicetype", devicetype);
					JmsCommSender.getInstance().send(mqBuffer.toString(), map);

				}else{
					logger.info("订单号["+orderid+"] 车辆 ["+cInfo.getDevicetype()+"] ["+cInfo.getCarid()+"] ["+terminal+"] ["+cInfo.getCarnumber()+"] 终端链路路由信息不存在");
				}
			}else{
				logger.info("订单号["+orderid+"] 车辆信息不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * 发送至业务分发服务器上行队列
	 * @param msgid
	 * @param jsonMsg
	 */
	public static void sendBusinessServer(int msgid,String jsonMsg,int devicetype){

		try {
			logger.info("消息写入业务分发服务器上行队列 ["+ConfigProperties.DS_BUSINESS_UP_QUEUE_NAME+"]");
			MapMessage message = JmsBusinessSender.getInstance().getSession().createMapMessage();      
			message.setInt("msgid",msgid);
			message.setInt("seq", 0);
			message.setString("terminal","0");
			message.setString("jsonMsg", jsonMsg);
			message.setInt("devicetype", devicetype);
			message.setInt("apptype", 1);
			JmsBusinessSender.getInstance().sendMapMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
