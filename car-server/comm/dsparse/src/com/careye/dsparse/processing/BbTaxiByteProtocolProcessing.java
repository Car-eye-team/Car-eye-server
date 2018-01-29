/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.processing;

import com.careye.dsparse.bbdomain.CallInfo;
import com.careye.dsparse.bbdomain.CameraCommandResponse;
import com.careye.dsparse.bbdomain.CanTotalBusDataUpload;
import com.careye.dsparse.bbdomain.DataCompressReport;
import com.careye.dsparse.bbdomain.DataTransmission;
import com.careye.dsparse.bbdomain.DriverInfo;
import com.careye.dsparse.bbdomain.ElectronicStorage;
import com.careye.dsparse.bbdomain.ElectronicWaybill;
import com.careye.dsparse.bbdomain.EventSet;
import com.careye.dsparse.bbdomain.InfoDemandMenu;
import com.careye.dsparse.bbdomain.MultiMediaData;
import com.careye.dsparse.bbdomain.MultiMediaEventInfo;
import com.careye.dsparse.bbdomain.PositionDataUpload;
import com.careye.dsparse.bbdomain.PositionInfo;
import com.careye.dsparse.bbdomain.QuestioningAnswer;
import com.careye.dsparse.bbdomain.RegisterInfo;
import com.careye.dsparse.bbdomain.RunDataInfo;
import com.careye.dsparse.bbdomain.SearchTerResponse;
import com.careye.dsparse.bbdomain.SecretKey;
import com.careye.dsparse.bbdomain.ServiceEvaluation;
import com.careye.dsparse.bbdomain.SigninInfo;
import com.careye.dsparse.bbdomain.StorageMultiMediaResponse;
import com.careye.dsparse.bbdomain.TaxiInfo;
import com.careye.dsparse.bbdomain.TaximeterInfo;
import com.careye.dsparse.bbdomain.TerminalDetect;
import com.careye.dsparse.bbdomain.TerminalGeneralRes;
import com.careye.dsparse.bbdomain.TerminalParameter;
import com.careye.dsparse.bbdomain.TerminalUpdate;
import com.careye.dsparse.bbdomain.TerminalUpdateResponse;
import com.careye.dsparse.bbdomain.TerminalUseInfo;
import com.careye.dsparse.bbdomain.TextInfo;
import com.careye.dsparse.bbdomain.TravelingRecorder;
import com.careye.dsparse.constant.BaseInfo;
import com.careye.dsparse.decoder.BbTaxiByteDecoder;
import com.careye.dsparse.encoder.BbTaxiJsonEncoder;
import com.careye.dsparse.utlis.ExceptionUtil;

/**
 * 项目名称：dsparse    
 * 类名称：BbTaxiByteProtocolProcessing    
 * 类描述：出租车部标协议处理    
 * 创建人：zr    
 * 创建时间：2015-10-14 上午10:22:22    
 * 修改人：zr    
 * 修改时间：2015-10-14 上午10:22:22    
 * 修改备注：    
 * @version 1.0  
 *
 */
public class BbTaxiByteProtocolProcessing {

	public static StringBuffer picBuffer = new StringBuffer();

	/**
	 * 出租车部标协议业务处理
	 * @param msgid
	 * @param terminal
	 * @param msgbody
	 * @return
	 */
	public static String processing(int msgid,String terminal,String msgbody,BaseInfo baseInfo){

		try {
			String jsonMsg = null;
			CallInfo zjCallInfo = null;
			SigninInfo signinInfo = null;
			TaxiInfo taxiInfo = null;

			switch (msgid) {

			/***************************************************************************************
			 ************************************出租车部标协议****************************************
			 ***************************************************************************************/

			//终端通用应答(0x0001)
			case 1:
				TerminalGeneralRes terminalGeneralRes = BbTaxiByteDecoder.decoderTerminalGeneralResMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderTerminalGeneralResJson(baseInfo, terminalGeneralRes);
				break;

				//终端心跳(0x0002)
			case 2:
				jsonMsg = BbTaxiJsonEncoder.encoderHeartbeatJson(baseInfo);
				break;

				//终端注销(0x0003)
			case 3:
				jsonMsg = BbTaxiJsonEncoder.encoderLogoutJson(baseInfo);
				break;

				//终端注册(0x0100)
			case 256:
				RegisterInfo registerInfo = BbTaxiByteDecoder.decoderRegisterMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderRegisterJson(baseInfo, registerInfo);
				break;

				//查询终端参数应答(0x0104)
			case 260:
				TerminalParameter terminalParameter = BbTaxiByteDecoder.decoderQueryParamAnswer(msgbody);
				jsonMsg = BbTaxiJsonEncoder.queryParamAnswer(baseInfo, terminalParameter);
				break;

				//查询终端属性应答(0x0107)
			case 263:
				SearchTerResponse searchTerResponse = BbTaxiByteDecoder.decoderSearchTerResponseMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderSearchTerResponseJson(baseInfo, searchTerResponse);
				break;

				//终端升级结果通知(0x0108)
			case 264:
				TerminalUpdateResponse terminalUpdateResponse = BbTaxiByteDecoder.decoderTerUpdateResMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderTerUpdateResJson(baseInfo, terminalUpdateResponse);
				break;	

				//位置信息汇报(0x0200)
			case 512:
				PositionInfo positionInfo = BbTaxiByteDecoder.decoderPositionMsg(msgbody,1);
				jsonMsg = BbTaxiJsonEncoder.encoderPositionInfoJson(baseInfo, positionInfo,1);
				break;

				//位置信息查询应答(0x0201)
			case 513:
				PositionInfo positionInfo1 = BbTaxiByteDecoder.decoderPositionMsg(msgbody,2);
				jsonMsg = BbTaxiJsonEncoder.encoderPositionInfoJson(baseInfo, positionInfo1,2);
				break;

				//事件报告(0x0301)
			case 769:
				int id =  BbTaxiByteDecoder.decoderEventReport(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderEventReportJson(baseInfo, id);
				break;

				//提问应答(0x0302)
			case 770:
				QuestioningAnswer questioningAnswer = BbTaxiByteDecoder.decoderDataQuestioningAnswerMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderDataQuestioningAnswerJson(baseInfo, questioningAnswer);
				break;

				//信息点播/取消(0x0303)
			case 771:
				InfoDemandMenu infoDemandMenu = BbTaxiByteDecoder.decoderInfoDemandMenuCancel(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderInfoDemandMenuCancelJson(baseInfo, infoDemandMenu);
				break;

				//车辆控制应答(0x0500)
			case 1280:
				PositionInfo positionInfo2 = BbTaxiByteDecoder.decoderPositionMsg(msgbody,2);
				jsonMsg = BbTaxiJsonEncoder.encoderPositionInfoJson(baseInfo, positionInfo2,2);
				break;

				//行驶记录数据上传(0x0700)
			case 1792:
				TravelingRecorder travelingRecorder = BbTaxiByteDecoder.decoderTravelingRecorder(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderTravelingRecorderJson(baseInfo, travelingRecorder);
				break;

				//电子运单上报(0x0701)
			case 1793:
				ElectronicWaybill electronicWaybill = BbTaxiByteDecoder.decoderElectronicWaybill(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderElectronicWaybillJson(baseInfo, electronicWaybill);
				break;

				//驾驶员身份信息采集上报(0x0702)
			case 1794:
				DriverInfo driverInfo = BbTaxiByteDecoder.decoderDriverInfo(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderDriverInfoJson(baseInfo, driverInfo);
				break;

				//定位数据批量上传(0x0704)	
			case 1796:
				PositionDataUpload pdUpload = BbTaxiByteDecoder.decoderPdUploadMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderPdUploadJson(baseInfo, pdUpload);
				break;

				//CAN 总线数据上传 (0x0705)
			case 1797:
				CanTotalBusDataUpload ctbdUpload = BbTaxiByteDecoder.decoderCtbdUploadMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderCtbdUploadJson(baseInfo, ctbdUpload);
				break;

				//多媒体事件信息上传(0x0800)
			case 2048:
				MultiMediaEventInfo mmeInfo = BbTaxiByteDecoder.decoderMmeInfoMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderMmeInfoJson(baseInfo, mmeInfo);
				break;

				//多媒体数据上传 (0x0801)
			case 2049:
				MultiMediaData multiMediaData = BbTaxiByteDecoder.decoderMultiMediaDataMsg(baseInfo,msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderMultiMediaDataJson(baseInfo, multiMediaData);
				/*picBuffer.append(multiMediaData.getMediaData());
				if(baseInfo.getSubpacket() == baseInfo.getTotalpacket()){
					FileUtil.saveToImgByStr(ParseUtil.parseHexStrToByte(picBuffer.toString()), "D://", "pic.jpg");
					picBuffer = new StringBuffer();
				}*/
				break;

				//存储多媒体数据检索应答(0x0802)
			case 2050:
				StorageMultiMediaResponse smmResponse = BbTaxiByteDecoder.decoderSmmResponseMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderSmmResponseJson(baseInfo, smmResponse);
				break;

				//摄像头立即拍摄命令应答(0x0805)
			case 2053:
				CameraCommandResponse ccResponse = BbTaxiByteDecoder.decoderCcResponseMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderCcResponseJson(baseInfo, ccResponse);
				break;

				//数据上行透传  (0x0900)
			case 0x0900:
				DataTransmission dataTransmission = BbTaxiByteDecoder.decoderDataTransmission(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderDataTransmissionJson(baseInfo, dataTransmission);
				break;	

				//数据压缩上报 (0x0901)
			case 2305:
				DataCompressReport dataCompressReport = BbTaxiByteDecoder.decoderDataCompressReportMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderDataCompressReportJson(baseInfo, dataCompressReport);
				break;

				/*		//终端 RSA 公钥(0x0A00)
			case 2560:
				PublicKey publicKey = BbTaxiByteDecoder.decoderTerminalRsaPublicKey(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderPublicKeyJson(baseInfo,publicKey);
				break;*/


				//上班签到				
			case 0x0A00:
				signinInfo = BbTaxiByteDecoder.decoderSignIntoWorkMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderSignIntoWorkJson(baseInfo,signinInfo);
				break;

				//下班签退		
			case 0x0A01:
				signinInfo = BbTaxiByteDecoder.decoderSignBackFromWorkMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderSignBackFromWorkJson(baseInfo,signinInfo);
				break;

				//运营数据上传
			case 0x0A03:
				RunDataInfo runDataInfo = BbTaxiByteDecoder.decoderRunDataUploadMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderRunDataUploadJson(baseInfo,runDataInfo);
				break;

				//电子服务证请求
			case 0x0D00:
				ElectronicStorage electronicStorage = BbTaxiByteDecoder.decoderElectronicStorageMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderElectronicStorageJson(baseInfo,electronicStorage);
				break;

				//校时请求
			case 0x0004:
				TerminalGeneralRes terGeneralRes = BbTaxiByteDecoder.decoderTimeRequestMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderTimeRequestJson(baseInfo,terGeneralRes);
				break;

				//密钥更新请求
			case 0x0D01:
				SecretKey secretKey = BbTaxiByteDecoder.decoderSecretKeyUpdateRequestMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderSecretKeyUpateRequestJson(baseInfo,secretKey);
				break;

				//密钥更新结果
			case 0x0D02:
				SecretKey sKey = BbTaxiByteDecoder.decoderSecretKeyUpdateResultMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderSecretKeyUpdateResultJson(baseInfo,sKey);
				break;

				//终端时间通知
			case 0xFFFF:
				EventSet event = BbTaxiByteDecoder.decoderTerEventNotice(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderTerEventNoticeJson(baseInfo,event);
				break;

				//文本信息应答
			case 0x0305:
				TextInfo textInfo = BbTaxiByteDecoder.decoderTextResponse(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderTextResponseJson(baseInfo, textInfo);
				break;

				//升级结果报告
			case 0x0105:
				TerminalUpdate terminalUpdate = BbTaxiByteDecoder.decoderUpdateResult(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderUpdateResultJson(baseInfo, terminalUpdate);
				break;

				//终端自检报告
			case 0x0106:
				TerminalDetect terminalDetect = BbTaxiByteDecoder.decoderTerminalDetect(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderTerminalDetectJson(baseInfo, terminalDetect);
				break;

				/***************************************************************************************
				 ************************************按照部标自定义协议**********************************
				 ***************************************************************************************/

				//抢答
			case 20740:
				CallInfo callInfo = BbTaxiByteDecoder.decoderAnswerCall(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderAnswerCallJson(baseInfo, callInfo);
				break;

				//电召中心处理结果应答
			case 20741:
				CallInfo callInfo1 = BbTaxiByteDecoder.decoderCentralProcessingResponse(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderCentralProcessingResponseJson(baseInfo, callInfo1);
				break;

				//执行电召
			case 20743:
				CallInfo callInfo2 = BbTaxiByteDecoder.decoderPerformCall(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderPerformCallJson(baseInfo, callInfo2);
				break;

				//计价器开钥匙门信息(0x5108)
			case 20744:
				//解析二进制协议
				TaximeterInfo taximeterInfo = BbTaxiByteDecoder.decoderTaximeterMsg(msgbody);
				//封装json协议
				jsonMsg = BbTaxiJsonEncoder.encoderTaximeterJson(baseInfo, taximeterInfo);
				break;

				//服务评价(0x5108)
			case 20745:
				//解析二进制协议
				ServiceEvaluation serviceEvaluation = BbTaxiByteDecoder.decoderServiceEvaluationMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderServiceEvaluation(baseInfo, serviceEvaluation);
				break;

				//上报终端功能使用次数(0x510A)
			case 20746:
				TerminalUseInfo terminalUseInfo = BbTaxiByteDecoder.decoderTerminalUseMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderTerminalUse(baseInfo, terminalUseInfo);
				break;

				//日使用上报(0x510C)
			case 20748:
				TerminalUseInfo tUseInfo = BbTaxiByteDecoder.decoderTerminalDayUseMsg(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderTerminalDayUse(baseInfo, tUseInfo);
				break;
				
				//视频回放列表
			case 0x5114:
				taxiInfo = BbTaxiByteDecoder.decoderVideoPlaybackList(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderVideoPlaybackListJson(baseInfo, taxiInfo);
				break;
				
				//回放结束
			case 0x5115:
				taxiInfo = BbTaxiByteDecoder.decoderVideoPlaybackEnd(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderVideoPlaybackEndJson(baseInfo, taxiInfo);
				break;

				//订单抢答
			case 0x0B01:
				zjCallInfo = BbTaxiByteDecoder.decoderZjAnswerCall(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderZjAnswerCallJson(baseInfo, zjCallInfo);
				break;

				//订单接受
			case 0x0B02:
				zjCallInfo = BbTaxiByteDecoder.decoderOrderAcceptance(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderOrderAcceptanceJson(baseInfo, zjCallInfo);
				break;

				//订单完成
			case 0x0B07:
				zjCallInfo = BbTaxiByteDecoder.decoderOrderFulfillment(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderOrderFulfillmentJson(baseInfo, zjCallInfo);
				break;

				//订单状态更改
			case 0x8B21:
				zjCallInfo = BbTaxiByteDecoder.decoderOrderStatus(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderOrderStatusJson(baseInfo, zjCallInfo);
				break;

				//司机取消订单
			case 0x8B22:
				zjCallInfo = BbTaxiByteDecoder.decoderDriverCancelOrder(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderDriverCancelOrderJson(baseInfo, zjCallInfo);
				break;

				//司机评价乘客
			case 0x8B23:
				zjCallInfo = BbTaxiByteDecoder.decoderDriverEvaluation(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderDriverEvaluationJson(baseInfo, zjCallInfo);
				break;

			default:
				break;
			}

			return jsonMsg;
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
			return null;
		}
	}

}
