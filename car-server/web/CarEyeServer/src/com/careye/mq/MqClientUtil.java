package com.careye.mq;

import java.util.HashMap;
import java.util.Map;

import javax.jms.MapMessage;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.careye.car.domain.CarInfo;
import com.careye.constant.ServiceConfig;
import com.careye.mq.domain.Protocol;
import com.careye.mq.utils.ResponseUtil;
import com.careye.mq.business.CallHandleBusiness;
import com.careye.mq.business.HandleBusiness;

/**
 * @car-eye车辆管理平台业务处理
 * @类名称：MqClientUtil
 * @类描述：消息处理
 * @创建人：zr
 * @创建时间：2013-7-22 下午04:46:02
 * @修改人：zr
 * @修改时间：2013-7-22 下午04:46:02
 * @修改备注：
 * @version 1.0
 */
public class MqClientUtil {

	private static Logger logger = Logger.getLogger(MqClientUtil.class);

	private static MqClientUtil mcu = new MqClientUtil();

	public static MqClientUtil getInstance() {
		if (mcu == null) {
			mcu = new MqClientUtil();
		}
		return mcu;
	}

	/**
	 * 处理通讯平台发送过来的数据包
	 * @param param
	 * @param message JSON数据包
	 * @throws Exception
	 */
	public void messageMqReceived(int param[],Object msg,int type) throws Exception {
		try {

			String message = null;
			int devicetype = 29;
			if(type == 1){
				message = msg.toString();
			}else{
				MapMessage msgMap = (MapMessage) msg;
				message = msgMap.getString("jsonMsg");
				devicetype = msgMap.getInt("devicetype");
			}

			if(devicetype == 22){
				devicetype = 29;
			}
			
			logger.info("【接收到通讯平台发送消息】"+message);
			JSONObject jsonObject = JSONObject.fromObject(message);
			jsonObject.remove("id");
			jsonObject.remove("len");
			jsonObject.remove("value");
			Protocol protocol = (Protocol) JSONObject.toBean(jsonObject, Protocol.class);
			protocol.setDevicetype(devicetype);
			//根据设备号查询车辆是否存在
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("terminal", protocol.getTerminal());
			paramsMap.put("devicetype", protocol.getDevicetype());
			CarInfo carInfo = ServiceConfig.carService.getCarInfo(paramsMap);

			if(carInfo != null){
				logger.info("解析消息ID为："+protocol.getMsgid());
				switch (protocol.getMsgid()) {

				//终端应答指令
				case 1:
					protocol.setDevicetype(devicetype);
					ResponseUtil.getTreatmentResponse(protocol);
					break;

				case 256:
					logger.info("【部标设备】处理---终端注册");
					protocol.setDevicetype(devicetype);
					HandleBusiness.regeditTerminal(protocol,message);
					logger.info("【部标设备】处理---终端注册数据包完成");
					break;

				case 260:
					logger.info("【部标设备】处理---终端参数设置应答");
					protocol.setDevicetype(devicetype);
					HandleBusiness.terminalParameterAnswer(protocol, message,carInfo);
					logger.info("【部标设备】处理---终端参数设置应答数据包完成");
					break;

				case 258:
					logger.info("【部标设备】处理---终端鉴权");
					protocol.setDevicetype(devicetype);
					HandleBusiness.authTerminal(protocol,message,carInfo);
					logger.info("【部标设备】处理---终端鉴权数据包完成");
					break;

				case 512:
					logger.info("【部标设备】处理---位置跟踪信息汇报心跳包数据包");
					protocol.setDevicetype(devicetype);
					HandleBusiness.heartbeat(protocol,message,carInfo);
					logger.info("【部标设备】处理---位置跟踪信息汇报心跳包数据包完成");
					break;

					//位置信息查询应答
				case 513:
					logger.info("【部标设备】处理---位置信息查询应答");
					protocol.setDevicetype(devicetype);
					HandleBusiness.queryPositionAnswer(protocol,message,carInfo);
					logger.info("【部标设备】处理---位置信息查询应答数据包完成");
					break;

					//多媒体事件上传
				case 0x0800:
					logger.info("【部标设备】处理---多媒体事件上传");
					protocol.setDevicetype(devicetype);
					HandleBusiness.multimediaEvent(protocol, message,carInfo);
					logger.info("【部标设备】处理---多媒体事件上传数据包完成");
					break;

					//多媒体数据上传
				case 0x0801:
					logger.info("【部标设备】处理---多媒体数据上传");
					protocol.setDevicetype(devicetype);
					HandleBusiness.multimediaData(protocol, message);
					logger.info("【部标设备】处理---多媒体数据上传数据包完成");
					break;

					//上班签到
				case 2560:
					logger.info("【部标设备】处理---上班签到");
					protocol.setDevicetype(devicetype);
					HandleBusiness.clockUp(protocol, message,carInfo);
					logger.info("【部标设备】处理---上班签到数据包完成");
					break;

					//下班签退
				case 2561:
					logger.info("【部标设备】处理---下班签退");
					protocol.setDevicetype(devicetype);
					HandleBusiness.clockDown(protocol, message,carInfo);
					logger.info("【部标设备】处理---下班签退数据包完成");
					break;

					//文本信息应答
				case 773:
					logger.info("【部标设备】处理---文本信息应答");
					protocol.setDevicetype(devicetype);
					HandleBusiness.resTextQuery(protocol, message,carInfo);
					logger.info("【部标设备】处理---文本信息应答数据包完成");
					break;

					//运营数据上传
				case 2563:
					logger.info("【部标设备】处理---运营数据上传");
					protocol.setDevicetype(devicetype);
					HandleBusiness.operateData(protocol, message,carInfo);
					logger.info("【部标设备】处理---运营数据上传数据包完成");
					break;

				case 20746:
					logger.info("【部标设备】处理---上报终端功能使用次数");
					protocol.setDevicetype(devicetype);
					HandleBusiness.terminalFunCount(protocol, message,carInfo);
					logger.info("【部标设备】处理---上报终端功能使用次数处理完成");
					break;	

				case 20756:
					logger.info("【部标设备】处理---视频回放列表");
					protocol.setDevicetype(devicetype);
					HandleBusiness.playbackList(protocol, message,carInfo);
					logger.info("【部标设备】处理---视频回放列表处理完成");
					break;	
				case 20757:
					logger.info("【部标设备】处理---视频回放结束");
					protocol.setDevicetype(devicetype);
					HandleBusiness.playbackFinish(protocol, message,carInfo);
					logger.info("【部标设备】处理---视频回放结束处理完成");
					break;	


					/****************************************************************
					 *************************电召业务开始****************************
					 ****************************************************************/
				case 20753:
					logger.info("【部标设备】处理---召标发送车辆");
					protocol.setDevicetype(devicetype);
					CallHandleBusiness.zbSendCar(protocol, message);
					logger.info("【部标设备】处理---召标发送车辆完成");
					break;

				case 2817:
					logger.info("【部标设备】处理---电召抢答");
					protocol.setDevicetype(devicetype);
					CallHandleBusiness.orderAnswer(protocol, message,carInfo);
					logger.info("【部标设备】处理---电召抢答完成");
					break;

				case 35618:
					logger.info("【部标设备】处理---司机取消订单");
					protocol.setDevicetype(devicetype);
					CallHandleBusiness.driverCancelOrderAnswer(protocol, message,carInfo);
					logger.info("【部标设备】处理---司机取消订单完成");
					break;

				case 35617:
					logger.info("【部标设备】处理---订单状态更改");
					protocol.setDevicetype(devicetype);
					CallHandleBusiness.chageOrderStatusAnswer(protocol, message,carInfo);
					logger.info("【部标设备】处理---订单状态更改完成");
					break;

				case 2823:
					logger.info("【部标设备】处理---执行电召");
					protocol.setDevicetype(devicetype);
					CallHandleBusiness.orderExecute(protocol, message,carInfo);
					//				HandleBusiness.heartbeat(protocol,message);
					logger.info("【部标设备】处理---执行电召处理完成");
					break;

					//司机评价乘客
				case 35619:
					logger.info("【部标设备】处理---司机评价乘客");
					protocol.setDevicetype(devicetype);
					CallHandleBusiness.driverEvaluation(protocol, message);
					logger.info("【部标设备】处理---司机评价乘客数据包完成");
					break;


					/****************************************************************
					 *************************电召业务结束****************************
					 ****************************************************************/

				default:
					logger.info("收到与业务无关数据包,直接抛弃：" + message);
					break;
				}
			}else{
				logger.info("设备["+protocol.getTerminal()+"]系统中不存在不处理业务");
			}

			protocol = null;
			jsonObject = null;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}

	}

	public static void main(String[] args) {
		MqClientUtil mqClientUtil = new MqClientUtil();
		int param[] = {0};;
		//		String message = "{\"msgid\":2048,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":8,\"terminal\":\"112321321\",\"seq\":15,\"dataId\":1027135333,\"mediaType\":0,\"format\":0,\"eventId\":2,\"wayId\":1}";
		//		String message = "{\"msgid\":260,\"subpacket\":0,\"encryption\":0,\"bodysize\":177,\"terminal\":\"15814403512\",\"seq\":104,\"seqR\":3,\"count\":20,\"items\":[{\"id\":39,\"size\":4,\"value\":4},{\"id\":40,\"size\":4,\"value\":10},{\"id\":41,\"size\":4,\"value\":25},{\"id\":44,\"size\":4,\"value\":4},{\"id\":45,\"size\":4,\"value\":4},{\"id\":46,\"size\":4,\"value\":4},{\"id\":47,\"size\":4,\"value\":4},{\"id\":48,\"size\":4,\"value\":4},{\"id\":64,\"size\":11,\"value\":\"03198736047\"},{\"id\":65,\"size\":0,\"value\":\"4\"},{\"id\":66,\"size\":0,\"value\":\"4\"},{\"id\":67,\"size\":0,\"value\":\"4\"},{\"id\":68,\"size\":0,\"value\":\"4\"},{\"id\":69,\"size\":4,\"value\":4},{\"id\":70,\"size\":4,\"value\":4},{\"id\":71,\"size\":4,\"value\":4},{\"id\":72,\"size\":11,\"value\":\"03198736047\"},{\"id\":73,\"size\":0,\"value\":\"4\"},{\"id\":80,\"size\":4,\"value\":4},{\"id\":81,\"size\":4,\"value\":4}]}";
		//		String message = "{\"msgid\":1,\"subpacket\":0,\"encryption\":0,\"bodysize\":28,\"terminal\":\"112321321\",\"respseq\":\"14\",\"respmsgid\":34817,\"result\":1}";
		//		String message = "{\"msgid\":258,\"subpacket\":0,\"encryption\":0,\"bodysize\":28,\"terminal\":\"15814403512\",\"seq\":1}";
		//		String message = "{\"msgid\":512,\"subpacket\":0,\"encryption\":0,\"bodysize\":28,\"terminal\":\"15814403512\",\"seq\":1,\"s18\":0,\"s9\":0,\"s4\":0,\"s1\":1,\"s0\":0,\"a1\":1,\"lat\":22584001,\"lng\":113902356,\"altitude\":15,\"speed\":47,\"direction\":20,\"time\":\"160429152738\"}";
		//		String message = "{\"msgid\":512,\"subpacket\":0,\"encryption\":0,\"bodysize\":28,\"terminal\":\"15814403512\",\"seq\":1,\"s18\":0,\"s9\":0,\"s4\":0,\"s1\":1,\"s0\":0,\"a1\":1,\"lat\":22502248,\"lng\":113489768,\"altitude\":15,\"speed\":47,\"direction\":20,\"time\":\"151019152738\"}";
		//		String message = "{\"msgid\":20753,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":12,\"terminal\":\"0\",\"seq\":11,\"orderid\":\"2144540593164083621\",\"ters\":\"15814403512,13788812345\"}";
		//		String message = "{\"msgid\":2817,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":12,\"terminal\":\"15814403512\",\"seq\":11,\"orderid\":\"2144540593164083621\",\"time\":\"151021134400\"}";
		//		String message = "{\"msgid\":2823,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":42,\"terminal\":\"15814403512\",\"seq\":26,\"orderid\":\"2144540593164083621\",\"completiontime\":\"151021134700\",\"number\":4,\"result\":0,\"s18\":0,\"s9\":0,\"s4\":0,\"s1\":1,\"lat\":22583897,\"lng\":113886362,\"altitude\":0,\"speed\":\"0\",\"direction\":44,\"time\":\"151020104135\"}";
		//		String message = "{\"msgid\":2560,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":106,\"terminal\":\"100100000005\",\"seq\":7,\"s18\":0,\"s9\":0,\"s4\":0,\"s1\":1,\"lat\":22566275,\"lng\":113874360,\"altitude\":64,\"speed\":\"0\",\"direction\":40,\"time\":\"160525090717\",\"companycode\":\"100000111\",\"drivercode\":\"00000000011\",\"vehicleid\":\"000001\",\"signintime\":\"160525090717\",\"count\":21,\"result\":144,\"driverid\":\"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\"}";
		//		String message = "{\"msgid\":2563,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":134,\"terminal\":\"100100000001\",\"seq\":83,\"kalarminfo\":\"00000000000000000000000000000000\",\"kstateinfo\":\"00000000000000000000001000010001\",\"klat\":0,\"klng\":0,\"kaltitude\":0,\"kspeed\":\"0\",\"kdirection\":0,\"ktime\":\"160525110607\",\"zalarminfo\":\"00000000000000000000000000000000\",\"zstateinfo\":\"00000000000000000000000000010001\",\"zlat\":0,\"zlng\":0,\"zaltitude\":0,\"zspeed\":\"0\",\"zdirection\":0,\"ztime\":\"160525110659\",\"runid\":1098035577,\"number\":1,\"evaluateid\":1098035643,\"options\":1,\"extend\":0,\"carnumber\":\"B88888\",\"blnumber\":\"0000000000000000000000000000000\",\"driverid\":\"00000000000000000000000000000000000000\",\"stime\":\"1605251105\",\"etime\":\"1106\",\"mileage\":\"0.00\",\"airmileage\":\"0.00\",\"fuelsurcharge\":\"0.00\",\"waitingtime\":\"000\",\"tradeamount\":\"6.00\",\"vehicletrips\":46,\"tradetype\":0,\"data\":\"\"}";
		//		String message = "{\"msgid\":773,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":145,\"terminal\":\"15814403512\",\"seq\":7,\"flag\":\"11111111\",\"textseq\":5,\"textdata\":\"天气转凉，注意加衣服。\"}";
		//		String message = "{\"msgid\":2563,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":202,\"terminal\":\"15814403512\",\"seq\":7,\"kalarminfo\":\"00000000000000000000000000000000\",\"kstateinfo\":\"00000000000000000000000000000011\",\"klat\":22566275,\"klng\":113874360,\"kaltitude\":64,\"kspeed\":\"0\",\"kdirection\":40,\"ktime\":\"151019150546\",\"zalarminfo\":\"00000000000000000000000000000000\",\"zstateinfo\":\"00000000000000000000000000000011\",\"zlat\":22566275,\"zlng\":113874360,\"zaltitude\":64,\"zspeed\":\"0\",\"zdirection\":40,\"ztime\":\"151019150534\",\"runid\":0,\"number\":1,\"evaluateid\":0,\"options\":0,\"extend\":0,\"carnumber\":\"000001\",\"companycode\":\"000280050\",\"drivercode\":\"00000000011\",\"driverid\":\"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\",\"stime\":\"1510191348\",\"etime\":\"1349\",\"mileage\":\"0.00\",\"airmileage\":\"0.00\",\"fuelsurcharge\":\"0.00\",\"waitingtime\":\"000\",\"tradeamount\":\"8.00\",\"vehicletrips\":20,\"tradetime\":\"151019134900\",\"tradetype\":1,\"cardtype\":0,\"cardoem\":0,\"csn\":0,\"tradecardno\":\"0000000000000000000\",\"tradeseq\":0,\"samno\":\"00000000000\",\"samseq\":0,\"cardtradeamount\":\"0.00\",\"tradebalance\":\"0.00\"}";
		//		String message = "{\"msgid\":35618,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":202,\"terminal\":\"15814403512\",\"seq\":7,\"orderid\":\"514482646619\",\"reason\":2}";
		//		String message = "{\"msgid\":35619,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":202,\"orderid\":\"2144540593164083621\",\"level\":4,\"content\":\"测试\"}";

		//		String message = "{\"msgid\":20753,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":12,\"terminal\":\"0\",\"seq\":11,\"orderid\":\"514617339181\",\"ters\":\"15814403512,100100000001\"}";
		//		String message = "{\"msgid\":2817,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":12,\"terminal\":\"15814403512\",\"seq\":11,\"orderid\":\"514617339181\",\"time\":\"160427131300\"}";
		//		String message = "{\"msgid\":35617,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":12,\"terminal\":\"15814403512\",\"seq\":11,\"orderid\":\"514617339181\",\"orderstatus\":6}";
		//		String message = "{\"msgid\":20746,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":12,\"terminal\":\"15814403512\",\"seq\":11,\"number\":\"2,2,4,4,5,6,7,8,9,10\"}";
		//		String message = "{\"msgid\":2817,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":12,\"terminal\":\"100100000002\",\"seq\":39,\"orderid\":\"514626903600\",\"time\":\"160508145301\"}";

		//		String message = "{\"msgid\":20756,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":12,\"terminal\":\"15814403512\",\"seq\":11,\"id\":1,\"count\":2,\"items\":[{\"filename\":\"1476329336981.mp4\",\"splaysec\":0,\"eplaysec\":32},{\"filename\":\"1476329336981.mp4\",\"splaysec\":50,\"eplaysec\":100}]}";
		String message = "{\"msgid\":20757,\"totalpacket\":0,\"subpacket\":0,\"encryption\":0,\"bodysize\":12,\"terminal\":\"15814403512\",\"seq\":11,\"id\":1}";

		try { 
			mqClientUtil.messageMqReceived(param, message,1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}
