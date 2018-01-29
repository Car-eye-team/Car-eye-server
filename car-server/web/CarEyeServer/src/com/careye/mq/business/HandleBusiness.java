package com.careye.mq.business;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.careye.car.domain.CarInfo;
import com.careye.car.domain.PositionInfo;
import com.careye.car.domain.TerminalFunCount;
import com.careye.car.domain.TransactionInfo;
import com.careye.common.domain.HeartRecord;
import com.careye.common.domain.MediaEvent;
import com.careye.constant.ConfigProperties;
import com.careye.constant.Constant;
import com.careye.constant.ServiceConfig;
import com.careye.http.BaiDuHttp;
import com.careye.http.GaoDeHttp;
import com.careye.http.domain.BaiDuInfo;
import com.careye.mq.coder.ActivePush;
import com.careye.mq.domain.ClockInfo;
import com.careye.mq.domain.LedMessage;
import com.careye.mq.domain.MultimediaData;
import com.careye.mq.domain.OperateData;
import com.careye.mq.domain.Protocol;
import com.careye.mq.domain.TerminalParameter;
import com.careye.mq.utils.HandleUtil;
import com.careye.mq.utils.ToolsUtil;
import com.careye.utils.DateUtil;

/**
 * @项目名称：TAXISERVER
 * @类名称：HandleBusiness
 * @类描述：部标协议业务处理
 * @创建人：zr
 * @创建时间：2015-3-12 下午05:48:51
 * @修改人：zr
 * @修改时间：2015-3-12 下午05:48:51
 * @修改备注：
 * @version 1.0
 */
public class HandleBusiness {

	private static Logger logger = Logger.getLogger(HandleBusiness.class);
	public static Map<String,MultimediaData> multimediaDataMap = Collections.synchronizedMap(new HashMap<String,MultimediaData>());
	/**
	 * 终端注册
	 * @param protocol
	 * @param message
	 */
	public static void regeditTerminal(Protocol protocol,String message){
		try {
			//0：成功；1：车辆已被注册；2：数据库中无该车辆；3：终端已被注册；4：数据库中无该终端
			//根据车牌号查询车辆是否存在
			if(protocol.getPlateCode() != null && !"".equals(protocol.getPlateCode())){
				String terminal = ServiceConfig.carService.getCarInfoByCarNumber(protocol.getPlateCode());
				int result = 0;
				if(terminal == null){
					result = 2;
				}else if(terminal.equals(protocol.getTerminal())){
					result = 0;
				}else{
					result = 1;
				}
				HandleUtil.regeditTerminalResponse(protocol.getTerminal(), protocol.getSeq(),result, "123456",protocol.getDevicetype());
			}else{
				String carnumber = ServiceConfig.carService.getCarInfoByTerminal(protocol.getTerminal());
				int result = 0;
				if(carnumber == null){
					result = 2;
				}else if(carnumber.equals(protocol.getPlateCode())){
					result = 0;
				}else{
					result = 1;
				}
				HandleUtil.regeditTerminalResponse(protocol.getTerminal(), protocol.getSeq(),result, "123456",protocol.getDevicetype());
			}


			/*String carnumber = ServiceConfig.carService.getCarInfoByTerminal(protocol.getTerminal()); 
			if(count1 == 0){
				HandleUtil.regeditTerminalResponse(protocol.getTerminal(), protocol.getSeq(),4, "123456",protocol.getDevicetype());
				return;
			}
			HandleUtil.regeditTerminalResponse(protocol.getTerminal(), protocol.getSeq(),0, "123456",protocol.getDevicetype());*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理终端心跳包
	 * @param protocol
	 * @param message
	 */
	public static void heartbeat(Protocol protocol,String message,CarInfo carInfo){

		try {

			if(carInfo != null){
				//设置空重车状态
				if(protocol.getS9() == 0){
					carInfo.setKzstate(0); 
				}else{
					carInfo.setKzstate(1); 
				}

				//车辆状态判断
				try {
					boolean bz = true;
					//判断ACC状态 0: ACC关;1:ACC开
					int s0 = 0;
					try {
						s0 = protocol.getS0();
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(s0 == 0){
						protocol.setAcc(0);	
					}else{
						protocol.setAcc(1);
					}

					//判断车辆是否定位 0:未定位;1:定位 
					int s1 = 0;
					try {
						s1 = protocol.getS1();
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(s1 == 1){
						protocol.setGpsflg(1);
					}else{
						//10、将因ACC关的情况下导致的未定位状态显示停车
						if(protocol.getS0() == 0){
							carInfo = ActivePush.vehicleStatePush(carInfo,4);
						}else{
							carInfo = ActivePush.vehicleStatePush(carInfo,8);
						}
						protocol.setGpsflg(0);
						bz = false;
					}

					//根据速度判断是否行驶 1长时间离线2离线3 熄火4停车 5 行驶 6 报警 7在线 8 未定位
					if(bz){
						String speed = protocol.getSpeed();
						if(speed == null || "".equals(speed) || "0.000000".equals(speed) || "0.0".equals(speed)){
							speed = "0";
						}
						if(Float.parseFloat(speed)>0){
							carInfo = ActivePush.vehicleStatePush(carInfo,5);
						}else if(Float.parseFloat(speed)==0){
							carInfo = ActivePush.vehicleStatePush(carInfo,4);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				double dlng = new BigDecimal(protocol.getLng()).doubleValue() / 1000000;
				double dlat = new BigDecimal(protocol.getLat()).doubleValue() / 1000000;
				BaiDuInfo baiDuInfo = null;

				int maptype = Integer.parseInt(ConfigProperties.MAP_TYPE);
				if(maptype == 1){//百度解析经纬度
					//调用百度接口获取经纬度信息
					baiDuInfo = BaiDuHttp.getBaiDuInfo(dlat, dlng);
				}else{//高德地图解析经纬度(为了方便，仍用百度的实体类)
					baiDuInfo = GaoDeHttp.gpsLngLatForGaoDe(dlng, dlat);
				}



				//处理位置信息
				PositionInfoBusiness.operPositionInfor(baiDuInfo, protocol, carInfo, message, dlng, dlat);

				if(dlng >0){
					//如果车辆已调度发送司机位置信息 
					if(carInfo.getDispaterstatus() != null && carInfo.getDispaterstatus() ==3){
						//得到车辆已调派或者载客中订单列表
						List<TransactionInfo> tlist = ServiceConfig.transactionService.getTransactionInfoList(carInfo.getId());
						if(tlist.size() > 0){
							for(TransactionInfo tInfo : tlist){
								if(tInfo.getOrderid().startsWith(Constant.APP_ORDER+"")){

									TransactionInfo tr = ServiceConfig.transactionService.getTransactionInfo(tInfo.getOrderid());
									if(tr != null && tr.getPhone() != null){
										if(tr.getStatus() != null && (tr.getStatus() == 2 || tr.getStatus() == 5)){ //2已调派 5载客中
											HandleUtil.driverPositionNotice(tInfo.getOrderid(), tInfo.getStatus(), dlng, dlat,tr.getPhone());
										}
									}

								}
							}
						}
					}
				}

				//报警判断
				try {
					//部标协议中判断是否存在报警
					if(ToolsUtil.getAlarmStatus(protocol)){
						AlarmBusiness.alarm(protocol, baiDuInfo, carInfo, message, dlng, dlat);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				//区域报警处理
				if(dlng >0){
					HandleBusinessCommon.areaAlarm(carInfo, dlng, dlat, protocol.getSpeed(),protocol.getTime());
				}

			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询位置信息应答
	 * @param protocol
	 */
	public static void queryPositionAnswer(Protocol protocol,String message,CarInfo carInfo){

		try {

			if(carInfo != null){
				double dlng = new BigDecimal(protocol.getLng()).doubleValue() / 1000000;
				double dlat = new BigDecimal(protocol.getLat()).doubleValue() / 1000000;
				//推送车辆状态
				String speed = protocol.getSpeed();
				if(speed == null || "".equals(speed)){
					speed = "0";
				}
				if(Float.parseFloat(speed)>0){
					carInfo = ActivePush.vehicleStatePush(carInfo,5);
				}else if(Float.parseFloat(speed)==0){
					carInfo = ActivePush.vehicleStatePush(carInfo,4);
				}

				BaiDuInfo baiDuInfo = null;

				int maptype = Integer.parseInt(ConfigProperties.MAP_TYPE);
				if(maptype == 1){//百度解析经纬度
					//调用百度接口获取经纬度信息
					baiDuInfo = BaiDuHttp.getBaiDuInfo(dlat, dlng);
				}else{//高德地图解析经纬度(为了方便，仍用百度的实体类)
					baiDuInfo = GaoDeHttp.gpsLngLatForGaoDe(dlng, dlat);
				}

				PositionInfo positionInfo = new PositionInfo();
				positionInfo.setSpeed(protocol.getSpeed());
				positionInfo.setDirection(protocol.getDirection());
				positionInfo.setCreatetime(DateUtil.getSQLDate());
				if(baiDuInfo !=null){
					positionInfo.setBlng(baiDuInfo.getBlng());
					positionInfo.setBlat(baiDuInfo.getBlat());
					positionInfo.setGaddress(baiDuInfo.getAddress());
				}else{
					positionInfo.setBlng(dlng);
					positionInfo.setBlat(dlat);
					positionInfo.setGaddress("");
				}
				//位置信息推送
				ActivePush.positionAnswerPush(positionInfo,carInfo,protocol);

				//处理位置信息
				PositionInfoBusiness.operPositionInfor(baiDuInfo, protocol, carInfo, message, dlng, dlat);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询位置信息应答处理异常",e);
		}
	}

	/**
	 * 终端鉴权
	 * @param protocol
	 * @param message
	 */
	public static void authTerminal(Protocol protocol,String message,CarInfo carInfo){
		try {
			//收到终端鉴权消息插入一条上线记录
			HeartRecord heartRecord = new HeartRecord();
			heartRecord.setCarid(carInfo.getId());
			heartRecord.setInlinecount(1);
			heartRecord.setOfflinecount(0);
			ServiceConfig.commonService.saveHeartRecord(heartRecord);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 多媒体事件处理
	 * @param protocol
	 * @param message
	 */
	public static void multimediaEvent(Protocol protocol,String message,CarInfo carInfo){
		try {

			if(carInfo != null){
				//收到事件数据插入一条记录
				MediaEvent mediaEvent = new MediaEvent();
				mediaEvent.setCarid(carInfo.getId());
				mediaEvent.setDataid(protocol.getDataId());
				mediaEvent.setEventcode(protocol.getEventId());
				mediaEvent.setFormat(protocol.getFormat());
				mediaEvent.setMediatype(protocol.getMediaType());
				mediaEvent.setAccessid(protocol.getWayId());
				mediaEvent.setCreatetime(DateUtil.getSQLDate());

				ServiceConfig.commonService.addMediaEvent(mediaEvent);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 多媒体数据处理
	 * @param protocol
	 * @param message
	 */
	public static void multimediaData(Protocol protocol,String message){
		try {


			//多媒体ID
			int dataId = protocol.getDataId();

			StringBuffer keyBuffer = new StringBuffer();
			keyBuffer.append(protocol.getTerminal());
			keyBuffer.append("_");
			keyBuffer.append(dataId);

			//分包数
			int subpacket = protocol.getSubpacket();
			//多媒体数据
			String mediaData = protocol.getMediaData();

			MultimediaData multimediaData = multimediaDataMap.get(keyBuffer.toString());
			String[] mediaDataArray = null;

			if(multimediaData == null){
				//总包数
				int totalpacket = protocol.getTotalpacket();
				multimediaData = new MultimediaData();
				mediaDataArray = new String[totalpacket];
			}else{
				mediaDataArray = multimediaData.getMediaDataArray();
			}

			//收到第一包验证车辆信息是否存在
			if(subpacket == 1){

				multimediaData.setLat(protocol.getLat());
				multimediaData.setLng(protocol.getLng());
				multimediaData.setAltitude(protocol.getAltitude());
				multimediaData.setSpeed(protocol.getSpeed());
				multimediaData.setDirection(protocol.getDirection());
				multimediaData.setTime(protocol.getTime());
				multimediaData.setMediaType(protocol.getMediaType());
				multimediaData.setFormat(protocol.getFormat());
				multimediaData.setEventId(protocol.getEventId());
				multimediaData.setWayId(protocol.getWayId());

			}
			multimediaData.setDataId(protocol.getDataId());
			multimediaData.setTerminal(protocol.getTerminal());
			multimediaData.setDateTime(DateUtil.getSQLDate());
			mediaDataArray[subpacket-1] = mediaData;
			multimediaData.setMediaDataArray(mediaDataArray);
			multimediaDataMap.put(keyBuffer.toString(), multimediaData);
			logger.info("i=="+subpacket +",mediaData="+mediaData);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 终端参数设置应答
	 * @param protocol
	 * @param msg
	 * @return
	 */
	public static TerminalParameter terminalParameterAnswer(Protocol protocol,String msg,CarInfo carInfo) {

		JSONObject jsonObject = JSONObject.fromObject(msg);

		TerminalParameter terminalParameter = new TerminalParameter();

		terminalParameter.setSimCode(protocol.getTerminal());
		try {
			JSONArray jsons = jsonObject.getJSONArray("items");

			for (int i = 0; i < jsons.size(); i++) {
				JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
				terminalParameter = ToolsUtil.setTerminalInfo(tempJson.getInt("id"), tempJson.getString("value"), terminalParameter);
			}

			terminalParameter.setCreatetime(DateUtil.getSQLDate());
			terminalParameter.setCarid(carInfo.getId());

			//保存终端参数
			ServiceConfig.carService.saveTerminalParameter(terminalParameter);


		} catch (Exception e) {
			e.printStackTrace();
		}

		return terminalParameter;
	}

	/**
	 * 上班签到
	 * @param protocol
	 * @param message
	 */
	public static void clockUp(Protocol protocol,String message,CarInfo carInfo){
		try {
			JSONObject jsonObject = JSONObject.fromObject(message);


			String drivercode = ServiceConfig.carService.getDriverCodeByCarid(carInfo.getId());

			ClockInfo clockInfo = new ClockInfo();
			clockInfo.setCreatetime(DateUtil.getSQLDate());
			clockInfo.setCarid(carInfo.getId());
			clockInfo.setType(1);

			clockInfo.setCompanycode(protocol.getCompanycode());
			clockInfo.setDrivercode(drivercode);
			clockInfo.setVehicleid(carInfo.getCarnumber());
			clockInfo.setSignintime(DateUtil.numToDate(protocol.getSignintime()));
			clockInfo.setCount(protocol.getCount());
			clockInfo.setResult(protocol.getResult());
			clockInfo.setDriverid(protocol.getDriverid());
			clockInfo.setStime(DateUtil.numToDate(protocol.getTime()));

			double dlng = new BigDecimal(protocol.getLng()).doubleValue() / 1000000;
			double dlat = new BigDecimal(protocol.getLat()).doubleValue() / 1000000;

			//调用百度接口获取经纬度信息
			BaiDuInfo baiDuInfo = BaiDuHttp.getBaiDuInfo(dlat, dlng);

			clockInfo.setSblng(dlng);
			clockInfo.setSblat(dlat);
			if(baiDuInfo != null){
				clockInfo.setSbblng(baiDuInfo.getBlng());
				clockInfo.setSbblat(baiDuInfo.getBlat());
				clockInfo.setSbbaddress(baiDuInfo.getAddress());
			}
			clockInfo.setSbaltitude(protocol.getAltitude());
			clockInfo.setSbspeed(protocol.getSpeed());
			clockInfo.setSbdirection(protocol.getDirection());

			//保存考勤信息
			ServiceConfig.commonService.saveClockInfo(clockInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 下班签退
	 * @param protocol
	 * @param message
	 */
	public static void clockDown(Protocol protocol,String message,CarInfo carInfo){
		try {
			JSONObject jsonObject = JSONObject.fromObject(message);

			String drivercode = ServiceConfig.carService.getDriverCodeByCarid(carInfo.getId());

			ClockInfo clockInfo = new ClockInfo();
			clockInfo.setCreatetime(DateUtil.getSQLDate());
			clockInfo.setCarid(carInfo.getId());
			clockInfo.setType(2);

			clockInfo.setCompanycode(protocol.getCompanycode());
			clockInfo.setDrivercode(drivercode);
			clockInfo.setVehicleid(carInfo.getCarnumber());
			clockInfo.setEtime(DateUtil.numToDate(protocol.getEtime()));
			clockInfo.setMcs(protocol.getMcs());
			clockInfo.setStime(DateUtil.numToDate(protocol.getStime()));
			clockInfo.setDbmileage(protocol.getDbmileage());
			clockInfo.setDbyymileage(protocol.getDbyymileage());
			clockInfo.setVehicletrips(protocol.getVehicletrips());
			clockInfo.setJstmie(DateUtil.getSecord(protocol.getJstmie()));
			clockInfo.setTotalamount(protocol.getTotalamount());
			clockInfo.setCardamount(protocol.getCardamount());
			clockInfo.setCardnum(protocol.getCardnum());
			clockInfo.setBjmileage(protocol.getBjmileage());
			clockInfo.setTotalmileage(protocol.getTotalmileage());
			clockInfo.setTotalyymileage(protocol.getTotalyymileage());
			clockInfo.setPrice(protocol.getPrice());
			clockInfo.setTotalnumber(protocol.getTotalnumber());
			clockInfo.setTotalwaittime(DateUtil.getSecord(protocol.getTotalwaittime()));
			clockInfo.setDriverid(protocol.getDriverid());

			double dlng = new BigDecimal(protocol.getLng()).doubleValue() / 1000000;
			double dlat = new BigDecimal(protocol.getLat()).doubleValue() / 1000000;
			BaiDuInfo baiDuInfo = null;

			int maptype = Integer.parseInt(ConfigProperties.MAP_TYPE);
			if(maptype == 1){//百度解析经纬度
				//调用百度接口获取经纬度信息
				baiDuInfo = BaiDuHttp.getBaiDuInfo(dlat, dlng);
			}else{//高德地图解析经纬度(为了方便，仍用百度的实体类)
				baiDuInfo = GaoDeHttp.gpsLngLatForGaoDe(dlng, dlat);
			}

			clockInfo.setXblng(dlng);
			clockInfo.setXblat(dlat);
			if(baiDuInfo != null){
				clockInfo.setXbblng(baiDuInfo.getBlng());
				clockInfo.setXbblat(baiDuInfo.getBlat());
				clockInfo.setXbbaddress(baiDuInfo.getAddress());
			}
			clockInfo.setXbaltitude(protocol.getAltitude());
			clockInfo.setXbspeed(protocol.getSpeed());
			clockInfo.setXbdirection(protocol.getDirection());

			//保存考勤信息
			ServiceConfig.commonService.saveClockInfo(clockInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文本信息应答
	 * @param protocol
	 * @param message
	 */
	public static void resTextQuery(Protocol protocol,String message,CarInfo carInfo){
		try {
			JSONObject jsonObject = JSONObject.fromObject(message);

			if(carInfo != null){
				LedMessage ledMessage = new LedMessage();
				ledMessage.setCarid(carInfo.getId());
				ledMessage.setTextmessage(jsonObject.getString("textdata"));
				ledMessage.setSeq(jsonObject.getInt("textseq"));
				ledMessage.setCreatetime(DateUtil.getSQLDate());
				ledMessage.setType1(1);
				ledMessage.setType2(1);
				ledMessage.setType3(1);
				ledMessage.setType4(1);
				//保存文本信息
				ServiceConfig.commonService.saveTextInfo(ledMessage);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 运营数据上传
	 * @param protocol
	 * @param message
	 */
	public static void operateData(Protocol protocol,String message,CarInfo carInfo){
		try {
			JSONObject jsonObject = JSONObject.fromObject(message);

			if(carInfo != null){
				OperateData operateData = (OperateData) JSONObject.toBean(jsonObject, OperateData.class);

				String drivercode = ServiceConfig.carService.getDriverCodeByCarid(carInfo.getId());

				operateData.setCreatetime(DateUtil.getSQLDate());
				operateData.setCarid(carInfo.getId());
				operateData.setCarnumber(carInfo.getCarnumber());
				operateData.setDrivercode(drivercode);
				operateData.setWaitingtime(DateUtil.getMinute(operateData.getWaitingtime()));
				operateData.setStime(DateUtil.numToDate2(operateData.getStime())+":00");
				operateData.setPeoplenumber(jsonObject.getInt("number"));
				operateData.setKtime(DateUtil.numToDate(operateData.getZtime()));
				operateData.setZtime(DateUtil.numToDate(operateData.getZtime()));

				double klng = new BigDecimal(operateData.getKlng()).doubleValue() / 1000000;
				double klat = new BigDecimal(operateData.getKlat()).doubleValue() / 1000000;
				BaiDuInfo kbaiDuInfo = null;

				int maptype = Integer.parseInt(ConfigProperties.MAP_TYPE);
				if(maptype == 1){//百度解析经纬度
					//调用百度接口获取经纬度信息
					kbaiDuInfo = BaiDuHttp.getBaiDuInfo(klat, klng);
				}else{//高德地图解析经纬度(为了方便，仍用百度的实体类)
					kbaiDuInfo = GaoDeHttp.gpsLngLatForGaoDe(klng, klat);
				}


				if(kbaiDuInfo != null){
					operateData.setKlng(kbaiDuInfo.getBlng());
					operateData.setKlat(kbaiDuInfo.getBlat());
					operateData.setKaddress(kbaiDuInfo.getAddress());
				}

				double zlng = new BigDecimal(operateData.getZlng()).doubleValue() / 1000000;
				double zlat = new BigDecimal(operateData.getZlat()).doubleValue() / 1000000;
				BaiDuInfo zbaiDuInfo = null;

				if(maptype == 1){//百度解析经纬度
					//调用百度接口获取经纬度信息
					zbaiDuInfo = BaiDuHttp.getBaiDuInfo(zlat, zlng);
				}else{//高德地图解析经纬度(为了方便，仍用百度的实体类)
					zbaiDuInfo = GaoDeHttp.gpsLngLatForGaoDe(zlng, zlat);
				}

				if(zbaiDuInfo != null){
					operateData.setZlng(zbaiDuInfo.getBlng());
					operateData.setZlat(zbaiDuInfo.getBlat());
					operateData.setZaddress(zbaiDuInfo.getAddress());
				}

				//保存运营数据
				ServiceConfig.commonService.saveOperateData(operateData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 终端功能使用统计
	 * @param protocol
	 * @param message
	 */
	public static void terminalFunCount(Protocol protocol,String message,CarInfo carInfo){
		try {
			if(carInfo != null){
				//根据ID判断是否存在记录
				String number = protocol.getNumber();
				if(number != null){
					String num[] =number.split(",");
					TerminalFunCount tFunCount = new TerminalFunCount();
					tFunCount.setCarid(carInfo.getId());
					tFunCount.setCarnumber(carInfo.getCarnumber());
					tFunCount.setTerminal(carInfo.getTerminal());
					tFunCount.setCount12(Integer.parseInt(num[0]));
					tFunCount.setCount13(Integer.parseInt(num[1]));
					tFunCount.setCount14(Integer.parseInt(num[2]));
					tFunCount.setCount4(Integer.parseInt(num[3]));
					tFunCount.setCount15(Integer.parseInt(num[4]));
					tFunCount.setCount16(Integer.parseInt(num[5]));
					tFunCount.setCount8(Integer.parseInt(num[6]));
					tFunCount.setCount17(Integer.parseInt(num[7]));
					tFunCount.setCount11(Integer.parseInt(num[8]));
					tFunCount.setCount18(Integer.parseInt(num[9]));
					tFunCount.setCreatetime(DateUtil.getSQLDate());
					int count = ServiceConfig.carService.getTerminalFunCount(carInfo.getId());
					if(count > 0){
						ServiceConfig.carService.updateTerminalFunCount(tFunCount);
					}else{
						ServiceConfig.carService.insertTerminalFunCount(tFunCount);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 视频回放列表
	 * @param protocol
	 * @param message
	 */
	public static void playbackList(Protocol protocol,String message,CarInfo carInfo){
		try {
			JSONObject jsonObject = JSONObject.fromObject(message);

			//视频回放列表推送      推送前将字符串中的双引号替换为"|"
			ActivePush.playbackListPush(carInfo,protocol,jsonObject.getString("id"),jsonObject.getString("items").toString().replaceAll("\"", "|"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 视频回放结束
	 * @param protocol
	 * @param message
	 */
	public static void playbackFinish(Protocol protocol,String message,CarInfo carInfo){
		try {
			JSONObject jsonObject = JSONObject.fromObject(message);
			if(carInfo != null){
				//视频回放结束推送
				ActivePush.playbackFinishPush(carInfo,protocol,jsonObject.getString("id"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
