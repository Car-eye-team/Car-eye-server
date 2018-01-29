/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.processing;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.careye.dsparse.bbdomain.ArtificialConfirmedAlarm;
import com.careye.dsparse.bbdomain.CallInfo;
import com.careye.dsparse.bbdomain.CameraCommandInfo;
import com.careye.dsparse.bbdomain.CircleArea;
import com.careye.dsparse.bbdomain.DataTransmission;
import com.careye.dsparse.bbdomain.ElectronicStorage;
import com.careye.dsparse.bbdomain.EventSet;
import com.careye.dsparse.bbdomain.FileInfo;
import com.careye.dsparse.bbdomain.InfoDemandMenu;
import com.careye.dsparse.bbdomain.LineInfo;
import com.careye.dsparse.bbdomain.MulMediaDataLoadResponse;
import com.careye.dsparse.bbdomain.OperationNotice;
import com.careye.dsparse.bbdomain.PaymentNotice;
import com.careye.dsparse.bbdomain.PhoneBook;
import com.careye.dsparse.bbdomain.PoiInfo;
import com.careye.dsparse.bbdomain.PolygonArea;
import com.careye.dsparse.bbdomain.PublicKey;
import com.careye.dsparse.bbdomain.QuestionsSend;
import com.careye.dsparse.bbdomain.RecordStartCommand;
import com.careye.dsparse.bbdomain.RectangleArea;
import com.careye.dsparse.bbdomain.RegisterResponse;
import com.careye.dsparse.bbdomain.SecretKey;
import com.careye.dsparse.bbdomain.SecurityCode;
import com.careye.dsparse.bbdomain.StoraMulMediaRetrieval;
import com.careye.dsparse.bbdomain.TaxiInfo;
import com.careye.dsparse.bbdomain.TempPoiFollowUpControl;
import com.careye.dsparse.bbdomain.TerminalControl;
import com.careye.dsparse.bbdomain.TerminalGeneralRes;
import com.careye.dsparse.bbdomain.TerminalParameter;
import com.careye.dsparse.bbdomain.TerminalUpdate;
import com.careye.dsparse.bbdomain.TextInfo;
import com.careye.dsparse.bbdomain.TravelingRecorder;
import com.careye.dsparse.bbdomain.VehicleControl;
import com.careye.dsparse.bbdomain.WifiInfo;
import com.careye.dsparse.decoder.BbByteDecoderUtil;
import com.careye.dsparse.encoder.BbTaxiByteEncoder;
import com.careye.dsparse.utlis.ExceptionUtil;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：ProtocolAnalysis    
 * 类描述：出租车部标JSON协议解析处理 
 * 创建人：zr    
 * 创建时间：2015-5-14 下午04:28:38    
 * 修改人：zr    
 * 修改时间：2015-5-14 下午04:28:38    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BbTaxiJsonProtocolProcessing {

	private final static Logger logger = Logger.getLogger(BbTaxiJsonProtocolProcessing.class);

	/**
	 * 出租车部标协议处理业务
	 * @param msgid 消息ID
	 * @param terminal 设备号
	 * @param jsonStr json格式字符串
	 * @return
	 */
	public static String processing(int msgid,String terminal,JSONObject jsonObject){

		try {
			String bodymsg = null;
			CallInfo hzCallInfo = null;
			TaxiInfo taxiInfo = null;

			switch (msgid) {

			/***************************************************************************************
			 ************************************808部标协议****************************************
			 ***************************************************************************************/

			//终端注册应答(0x8100)
			case 33024:
				RegisterResponse registerResponse = (RegisterResponse) JSONObject.toBean(jsonObject, RegisterResponse.class);
				bodymsg = BbTaxiByteEncoder.encoderRegisterResponse(registerResponse);
				logger.info("终端注册应答: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//设置终端参数(0x8103)
			case 33027:
				TerminalParameter terminalParameter = BbByteDecoderUtil.getTerminalParameterInfo(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderSetTerminalParameter(terminalParameter);
				logger.info("设置终端参数: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//查询终端参数(0x8104)
			case 33028:
				bodymsg = null;
				logger.info("查询终端参数: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//终端控制(0x8105)
			case 33029:
				TerminalControl terminalControl  = (TerminalControl) JSONObject.toBean(jsonObject, TerminalControl.class);
				bodymsg = BbTaxiByteEncoder.encoderTerminalControl(terminalControl);
				logger.info("终端控制: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//查询终端属性(0x8107)
			case 33031:
				bodymsg = null;
				logger.info("查询终端属性: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;	

				//查询指定终端参数(0x8106)
			case 33030:
				TerminalParameter tTParameter = BbByteDecoderUtil.querySpecifyingTerminalParam(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderQuerySpecifyingTerminalParam(tTParameter);
				logger.info("查询指定终端参数: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//下发终端升级包(0x8108)
			case 33032:
				TerminalUpdate terminalUpdate = (TerminalUpdate) JSONObject.toBean(jsonObject, TerminalUpdate.class);
				bodymsg = BbTaxiByteEncoder.encoderSetTerminalUpdate(terminalUpdate);
				System.out.println("下发终端升级包: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//位置信息查询(0x8201)
			case 33281:
				bodymsg = null;
				logger.info("位置信息查询: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//临时位置跟踪控制(0x8202)
			case 33282:
				TempPoiFollowUpControl tempPoiFollowUpControl =(TempPoiFollowUpControl) JSONObject.toBean(jsonObject, TempPoiFollowUpControl.class);
				bodymsg = BbTaxiByteEncoder.encoderTempPoiFollowUpControlKey(tempPoiFollowUpControl);
				logger.info("临时位置跟踪控制: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//人工确认报警消息(0x8203)
			case 33283:
				ArtificialConfirmedAlarm acAlarm =(ArtificialConfirmedAlarm) JSONObject.toBean(jsonObject, ArtificialConfirmedAlarm.class);
				bodymsg = BbTaxiByteEncoder.encoderArtificialConfirmedAlarmKey(acAlarm);
				logger.info("人工确认报警消息: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//文本信息下发(0x8300)
			case 33536:
				TextInfo textInfo = (TextInfo) JSONObject.toBean(jsonObject, TextInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderTextInfo(textInfo);
				break;

				//事件设置(0x8301)
			case 33537:
				EventSet eventSet = BbByteDecoderUtil.getEventSet(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderEventSet(eventSet);
				logger.info("事件设置: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//提问下发(0x8302)
			case 33538:
				QuestionsSend questionsSend = BbByteDecoderUtil.getQuestionsSend(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderQuestionsSend(questionsSend);
				logger.info("提问下发: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//信息点播菜单设置(0x8303)
			case 33539:
				InfoDemandMenu infoDemandMenu = BbByteDecoderUtil.getInfoDemandMenu(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderInfoDemandMenu(infoDemandMenu);
				logger.info("信息点播菜单设置: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//信息服务(0x8304)
			case 33540:
				InfoDemandMenu idMenu = (InfoDemandMenu) JSONObject.toBean(jsonObject, InfoDemandMenu.class);
				bodymsg = BbTaxiByteEncoder.encoderInfoServer(idMenu);
				logger.info("信息服务: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//电话回拨(0x8400)
			case 33792:
				PhoneBook phoneBook = (PhoneBook) JSONObject.toBean(jsonObject, PhoneBook.class);
				bodymsg = BbTaxiByteEncoder.encoderCallBack(phoneBook);
				logger.info("电话回拨: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//设置电话本(0x8401)
			case 33793:
				PhoneBook pBook = BbByteDecoderUtil.getPhoneBook(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderPhoneBook(pBook);
				logger.info("设置电话本: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//车辆控制0x8500
			case 34048:
				VehicleControl vehicleControl = (VehicleControl) JSONObject.toBean(jsonObject, VehicleControl.class);
				bodymsg = BbTaxiByteEncoder.encoderVehicleControl(vehicleControl);
				logger.info("车辆控制: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//设置圆形区域0x8600
			case 34304:
				CircleArea circleArea = BbByteDecoderUtil.getCircleArea(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderCircleArea(circleArea);
				logger.info("设置圆形区域: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//删除圆形区域0x8601
			case 34305:
				CircleArea cArea = BbByteDecoderUtil.deleteCircleArea(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderDeleteCircleArea(cArea);
				logger.info("删除圆形区域: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//设置矩形区域0x8602
			case 34306:
				RectangleArea rectangleArea = BbByteDecoderUtil.getRectangleArea(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderRectangleArea(rectangleArea);
				logger.info("设置矩形区域: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//删除矩形区域0x8603
			case 34307:
				RectangleArea rArea = BbByteDecoderUtil.deleteRectangleArea(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderDeleteRectangleArea(rArea);
				logger.info("删除矩形区域: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//设置多边形区域0x8604
			case 34308:
				PolygonArea polygonArea = BbByteDecoderUtil.getPolygonArea(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderPolygonArea(polygonArea);
				logger.info("设置多边形区域: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//删除多边形区域0x8605
			case 34309:
				PolygonArea pArea = BbByteDecoderUtil.deletePolygonArea(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderDeletePolygonArea(pArea);
				logger.info("删除多边形区域: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//设置路线0x8606
			case 34310:
				LineInfo lineInfo = BbByteDecoderUtil.getLineInfo(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderLineInfo(lineInfo);
				logger.info("设置路线: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//删除线路0x8607
			case 34311:
				LineInfo lInfo = BbByteDecoderUtil.deleteLineInfo(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderDeleteLineInfo(lInfo);
				logger.info("删除线路: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//行驶记录数据采集命令(0x8700)
			case 34560:
				TravelingRecorder travelingRecorder = (TravelingRecorder) JSONObject.toBean(jsonObject, TravelingRecorder.class);
				bodymsg = BbTaxiByteEncoder.encoderTravelingRecorder(travelingRecorder, 1);
				logger.info("行驶记录数据采集命令: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;	

				//行驶记录参数下传命令(0x8701)
			case 34561:
				TravelingRecorder tRecorder = (TravelingRecorder) JSONObject.toBean(jsonObject, TravelingRecorder.class);
				bodymsg = BbTaxiByteEncoder.encoderTravelingRecorder(tRecorder, 2);
				logger.info("行驶记录参数下传命令: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;	

				//上报驾驶员身份信息请求(0x8702)
			case 34562:
				bodymsg = null;
				logger.info("上报驾驶员身份信息请求: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//多媒体数据上传应答(0x8800)
			case 34816:
				MulMediaDataLoadResponse mmdlResponse = BbByteDecoderUtil.getMmdlResponseInfo(jsonObject);
				bodymsg = BbTaxiByteEncoder.encoderMmdlResponse(mmdlResponse);
				logger.info("多媒体数据上传应答: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//摄像头立即拍摄命令(0x8801)
			case 34817:
				CameraCommandInfo cameraCommandInfo = (CameraCommandInfo) JSONObject.toBean(jsonObject, CameraCommandInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderCameraCommandInfo(cameraCommandInfo);
				logger.info("摄像头立即拍摄命令: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//存储多媒体数据检索(0x8802)
			case 34818:
				StoraMulMediaRetrieval storaMulMediaRetrieval = (StoraMulMediaRetrieval) JSONObject.toBean(jsonObject, StoraMulMediaRetrieval.class);
				bodymsg = BbTaxiByteEncoder.encoderStoraMulMediaRetrieval(storaMulMediaRetrieval);
				logger.info("存储多媒体数据检索: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//存储多媒体数据上传命令(0x8803)
			case 34819:
				StoraMulMediaRetrieval sMulMediaRetrieval = (StoraMulMediaRetrieval) JSONObject.toBean(jsonObject, StoraMulMediaRetrieval.class);
				bodymsg = BbTaxiByteEncoder.encoderStoraMulMediaUpload(sMulMediaRetrieval);
				logger.info("存储多媒体数据检索: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//录音开始命令(0x8804)
			case 34820:
				RecordStartCommand recordStartCommand = (RecordStartCommand) JSONObject.toBean(jsonObject, RecordStartCommand.class);
				bodymsg = BbTaxiByteEncoder.encoderRecordStartCommand(recordStartCommand);
				logger.info("录音开始命: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//单条存储多媒体数据检索上传命令(0x8805)
			case 34821:
				StoraMulMediaRetrieval dtMulMediaRetrieval = (StoraMulMediaRetrieval) JSONObject.toBean(jsonObject, StoraMulMediaRetrieval.class);
				bodymsg = BbTaxiByteEncoder.encoderOneStoraMulMediaUpload(dtMulMediaRetrieval);
				logger.info("单条存储多媒体数据检索上传命令: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//数据下行透传(0x8900)
			case 0x8900:
				DataTransmission dataTransmission = (DataTransmission) JSONObject.toBean(jsonObject, DataTransmission.class);
				bodymsg = BbTaxiByteEncoder.encoderDataTransmission(dataTransmission);
				logger.info("数据下行透传: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//平台 RSA 公钥(0x8A00)
			case 35328:
				PublicKey publicKey = (PublicKey) JSONObject.toBean(jsonObject, PublicKey.class);
				bodymsg = BbTaxiByteEncoder.encoderPlatformRsaPublicKey(publicKey);
				logger.info("平台 RSA 公钥: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				/***************************************************************************************
				 ************************************按照部标自定义协议**********************************
				 ***************************************************************************************/
				//一键通导航(0x8F00)
			case 36608:
				PoiInfo poiInfo = (PoiInfo) JSONObject.toBean(jsonObject, PoiInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderPoiNavi(poiInfo);
				logger.info("一键通导航: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//防伪码(0x5201)
			case 20993:
				SecurityCode securityCode = (SecurityCode) JSONObject.toBean(jsonObject, SecurityCode.class);
				bodymsg = BbTaxiByteEncoder.encoderSecurityCode(securityCode);
				logger.info("防伪码: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;


				//招标(0x5203)
			case 20995:
				CallInfo callInfo = (CallInfo) JSONObject.toBean(jsonObject, CallInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderInvitationInfo(callInfo);
				logger.info("招标信息: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//抢答确认(0x5204)
			case 20996:
				CallInfo callInfo1 = (CallInfo) JSONObject.toBean(jsonObject, CallInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderConfirmAnswer(callInfo1);
				logger.info("抢答确认: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//电召中心处理结果(0x5205)
			case 20997:
				CallInfo callInfo2 = (CallInfo) JSONObject.toBean(jsonObject, CallInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderCenterProcessingResults(callInfo2);
				logger.info("电召中心处理结果: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//中标(0x5206)
			case 20998:
				CallInfo callInfo3 = (CallInfo) JSONObject.toBean(jsonObject, CallInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderCallWinningBid(callInfo3);
				logger.info("中标: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//执行电召确认(0x5207)
			case 20999:
				CallInfo callInfo4 = (CallInfo) JSONObject.toBean(jsonObject, CallInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderPerformCallConfirmation(callInfo4);
				logger.info("执行电召确认: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//车辆信息操作通知(0x5208)
			case 21000:
				OperationNotice operationNotice = (OperationNotice) JSONObject.toBean(jsonObject, OperationNotice.class);
				bodymsg = BbTaxiByteEncoder.encoderOperationNotice(operationNotice);
				logger.info("车辆信息操作通知: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//语音叫车
			case 21001:
				CallInfo cInfo = (CallInfo) JSONObject.toBean(jsonObject, CallInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderVoiceInvitationInfo(cInfo);
				logger.info("语音叫车: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//调度规则通知(0x520B)
			case 21003:
				//由于调度规则只需要发送给调度服务器进行处理即可，不需要进行协议解析与转成二进制协议
				bodymsg = null;
				break;	

				//取消电召(0x520C)
			case 21004:
				//由于取消电召只需要发送给调度服务器进行处理即可，不需要进行协议解析与转成二进制协议
				bodymsg = null;
				break;

				//WIFI热点控制
			case 21008:
				WifiInfo wifiInfo = (WifiInfo) JSONObject.toBean(jsonObject, WifiInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderWifi(wifiInfo);
				logger.info("WIFI热点控制: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;
				
				//对讲申请加入组通知
			case 0x510F:
				taxiInfo = (TaxiInfo) JSONObject.toBean(jsonObject, TaxiInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderIntercomRequest(taxiInfo);
				logger.info("对讲申请加入组通知: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;
				
				//视频预览控制
			case 0x5110:
				taxiInfo = (TaxiInfo) JSONObject.toBean(jsonObject, TaxiInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderVideoPreviewControl(taxiInfo);
				logger.info("视频预览控制: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;
				
				//视频回放
			case 0x5111:
				taxiInfo = (TaxiInfo) JSONObject.toBean(jsonObject, TaxiInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderVideoPlayback(taxiInfo);
				logger.info("视频回放: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;
				
				//停止视频回放
			case 0x5112:
				taxiInfo = (TaxiInfo) JSONObject.toBean(jsonObject, TaxiInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderStopVideoPlayback(taxiInfo);
				logger.info("停止视频回放: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;
				
				//回放指定文件
			case 0x5113:
				taxiInfo = (TaxiInfo) JSONObject.toBean(jsonObject, TaxiInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderVideoPlaybackFile(taxiInfo);
				logger.info("回放指定文件: ["+terminal+"] ["+msgid+"] ["+bodymsg+"]");
				break;

				/***************************************************************************************
				 *************************出租车部标新增协议*********************************************
				 ***************************************************************************************/

				//订单任务下发
			case 0x8B00:
				hzCallInfo = (CallInfo) JSONObject.toBean(jsonObject, CallInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderZjInvitationInfo(hzCallInfo);
				logger.info("订单任务下发: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//订单确认
			case 0x8B01:
				hzCallInfo = (CallInfo) JSONObject.toBean(jsonObject, CallInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderZjCallWinningBid(hzCallInfo);
				logger.info("订单确认: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//中心取消订单
			case 0x8B09:
				hzCallInfo = (CallInfo) JSONObject.toBean(jsonObject, CallInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderZjCenterCancel(hzCallInfo);
				logger.info("中心取消订单: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

				//电子服务证下发
			case 0x8D00:
				ElectronicStorage electronicStorage = (ElectronicStorage) JSONObject.toBean(jsonObject, ElectronicStorage.class);
				bodymsg = BbTaxiByteEncoder.encoderElectronicStorage(electronicStorage);
				logger.info("电子服务证下发: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");	
				break;

				//校时应答
			case 0x8004:
				TerminalGeneralRes terGeneralRes = (TerminalGeneralRes) JSONObject.toBean(jsonObject, TerminalGeneralRes.class);
				bodymsg = BbTaxiByteEncoder.encoderTimeAnswer(terGeneralRes);
				logger.info("校时应答: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");	
				break;

				//文件下发
			case 0x8D01:
				FileInfo fileInfo = (FileInfo) JSONObject.toBean(jsonObject, FileInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderSendFile(fileInfo);
				logger.info("文件下发: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");	
				break;

				//密钥更新通知
			case 0x8D02:
				SecretKey secretKey = (SecretKey) JSONObject.toBean(jsonObject, SecretKey.class);
				bodymsg = BbTaxiByteEncoder.encoderSecretKeyUpateNotice(secretKey);
				logger.info("密钥更新通知: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");	
				break;

				//密钥内容下发
			case 0x8D03:
				SecretKey sKey = (SecretKey) JSONObject.toBean(jsonObject, SecretKey.class);
				bodymsg = BbTaxiByteEncoder.encoderSendSecretKey(sKey);
				logger.info("密钥内容下发: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");	
				break;

				//文本信息查询
			case 0x8305:
				TextInfo tInfo = (TextInfo) JSONObject.toBean(jsonObject, TextInfo.class);
				bodymsg = BbTaxiByteEncoder.encoderTextInfoQuery(tInfo);
				logger.info("文本信息查询: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");	
				break;

				//支付通知
			case 0x8B20:
				PaymentNotice payNotice = (PaymentNotice) JSONObject.toBean(jsonObject, PaymentNotice.class);
				bodymsg = BbTaxiByteEncoder.encoderPaymentNotice(payNotice);
				logger.info("支付通知: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;

			default:
				logger.info("通讯平台不支持该出租车部标协议解析: ["+terminal+"] ["+msgid+"] ["+bodymsg+"] ");
				break;
			}
			return bodymsg;
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
			return null;
		}
	}

}
