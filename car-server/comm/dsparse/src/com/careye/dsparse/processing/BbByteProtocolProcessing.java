/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.processing;

import com.careye.dsparse.bbdomain.BatteryInfo;
import com.careye.dsparse.bbdomain.CallInfo;
import com.careye.dsparse.bbdomain.CameraCommandResponse;
import com.careye.dsparse.bbdomain.CanTotalBusDataUpload;
import com.careye.dsparse.bbdomain.CarSource;
import com.careye.dsparse.bbdomain.DataCompressReport;
import com.careye.dsparse.bbdomain.DataTransmission;
import com.careye.dsparse.bbdomain.DriverInfo;
import com.careye.dsparse.bbdomain.ElectronicWaybill;
import com.careye.dsparse.bbdomain.GateOrder;
import com.careye.dsparse.bbdomain.InfoDemandMenu;
import com.careye.dsparse.bbdomain.MultiMediaData;
import com.careye.dsparse.bbdomain.MultiMediaEventInfo;
import com.careye.dsparse.bbdomain.PositionDataUpload;
import com.careye.dsparse.bbdomain.PositionInfo;
import com.careye.dsparse.bbdomain.PublicKey;
import com.careye.dsparse.bbdomain.QuestioningAnswer;
import com.careye.dsparse.bbdomain.RegisterInfo;
import com.careye.dsparse.bbdomain.ReturnRecord;
import com.careye.dsparse.bbdomain.SearchTerResponse;
import com.careye.dsparse.bbdomain.ServiceEvaluation;
import com.careye.dsparse.bbdomain.StorageMultiMediaResponse;
import com.careye.dsparse.bbdomain.TaxiInfo;
import com.careye.dsparse.bbdomain.TaximeterInfo;
import com.careye.dsparse.bbdomain.TerminalGeneralRes;
import com.careye.dsparse.bbdomain.TerminalParameter;
import com.careye.dsparse.bbdomain.TerminalUpdateResponse;
import com.careye.dsparse.bbdomain.TerminalUseInfo;
import com.careye.dsparse.bbdomain.TravelingRecorder;
import com.careye.dsparse.bbdomain.WarnInfo;
import com.careye.dsparse.constant.BaseInfo;
import com.careye.dsparse.decoder.BbByteDecoder;
import com.careye.dsparse.decoder.BbTaxiByteDecoder;
import com.careye.dsparse.encoder.BbJsonEncoder;
import com.careye.dsparse.encoder.BbTaxiJsonEncoder;
import com.careye.dsparse.utlis.ExceptionUtil;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：ByteProtocolProcessing    
 * 类描述：部标二进制协议解析处理   
 * 创建人：zr    
 * 创建时间：2015-5-14 下午05:21:02    
 * 修改人：zr    
 * 修改时间：2015-5-14 下午05:21:02    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BbByteProtocolProcessing {

	/**
	 * 部标协议业务处理
	 * @param msgid
	 * @param terminal
	 * @param msgbody
	 * @return
	 */
	public static String processing(int msgid,String terminal,String msgbody,BaseInfo baseInfo){

		try {
			String jsonMsg = null;
			CallInfo zjCallInfo = null;

			switch (msgid) {

			/***************************************************************************************
			 ************************************808部标协议****************************************
			 ***************************************************************************************/

			//终端通用应答(0x0001)
			case 1:
				TerminalGeneralRes terminalGeneralRes = BbByteDecoder.decoderTerminalGeneralResMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderTerminalGeneralResJson(baseInfo, terminalGeneralRes);
				break;

				//终端心跳(0x0002)
			case 2:
				jsonMsg = BbJsonEncoder.encoderHeartbeatJson(baseInfo);
				break;

				//终端注销(0x0003)
			case 3:
				jsonMsg = BbJsonEncoder.encoderLogoutJson(baseInfo);
				break;

				//终端注册(0x0100)
			case 256:
				RegisterInfo registerInfo = BbByteDecoder.decoderRegisterMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderRegisterJson(baseInfo, registerInfo);
				break;

				//查询终端参数应答(0x0104)
			case 260:
				TerminalParameter terminalParameter = BbByteDecoder.decoderQueryParamAnswer(msgbody);
				jsonMsg = BbJsonEncoder.queryParamAnswer(baseInfo, terminalParameter);
				break;

				//查询终端属性应答(0x0107)
			case 263:
				SearchTerResponse searchTerResponse = BbByteDecoder.decoderSearchTerResponseMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderSearchTerResponseJson(baseInfo, searchTerResponse);
				break;

				//终端升级结果通知(0x0108)
			case 264:
				TerminalUpdateResponse terminalUpdateResponse = BbByteDecoder.decoderTerUpdateResMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderTerUpdateResJson(baseInfo, terminalUpdateResponse);
				break;	

				//位置信息汇报(0x0200)
			case 512:
				PositionInfo positionInfo = BbByteDecoder.decoderPositionMsg(msgbody,1);
				jsonMsg = BbJsonEncoder.encoderPositionInfoJson(baseInfo, positionInfo,1);
				break;

				//位置信息查询应答(0x0201)
			case 513:
				PositionInfo positionInfo1 = BbByteDecoder.decoderPositionMsg(msgbody,2);
				jsonMsg = BbJsonEncoder.encoderPositionInfoJson(baseInfo, positionInfo1,2);
				break;

				//事件报告(0x0301)
			case 769:
				int id =  BbByteDecoder.decoderEventReport(msgbody);
				jsonMsg = BbJsonEncoder.encoderEventReportJson(baseInfo, id);
				break;

				//提问应答(0x0302)
			case 770:
				QuestioningAnswer questioningAnswer = BbByteDecoder.decoderDataQuestioningAnswerMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderDataQuestioningAnswerJson(baseInfo, questioningAnswer);
				break;

				//信息点播/取消(0x0303)
			case 771:
				InfoDemandMenu infoDemandMenu = BbByteDecoder.decoderInfoDemandMenuCancel(msgbody);
				jsonMsg = BbJsonEncoder.encoderInfoDemandMenuCancelJson(baseInfo, infoDemandMenu);
				break;

				//车辆控制应答(0x0500)
			case 1280:
				PositionInfo positionInfo2 = BbByteDecoder.decoderPositionMsg(msgbody,2);
				jsonMsg = BbJsonEncoder.encoderPositionInfoJson(baseInfo, positionInfo2,2);
				break;

				//行驶记录数据上传(0x0700)
			case 1792:
				TravelingRecorder travelingRecorder = BbByteDecoder.decoderTravelingRecorder(msgbody);
				jsonMsg = BbJsonEncoder.encoderTravelingRecorderJson(baseInfo, travelingRecorder);
				break;

				//电子运单上报(0x0701)
			case 1793:
				ElectronicWaybill electronicWaybill = BbByteDecoder.decoderElectronicWaybill(msgbody);
				jsonMsg = BbJsonEncoder.encoderElectronicWaybillJson(baseInfo, electronicWaybill);
				break;

				//驾驶员身份信息采集上报(0x0702)
			case 1794:
				DriverInfo driverInfo = BbByteDecoder.decoderDriverInfo(msgbody);
				jsonMsg = BbJsonEncoder.encoderDriverInfoJson(baseInfo, driverInfo);
				break;

				//定位数据批量上传(0x0704)	
			case 1796:
				PositionDataUpload pdUpload = BbByteDecoder.decoderPdUploadMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderPdUploadJson(baseInfo, pdUpload);
				break;

				//CAN 总线数据上传 (0x0705)
			case 1797:
				CanTotalBusDataUpload ctbdUpload = BbByteDecoder.decoderCtbdUploadMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderCtbdUploadJson(baseInfo, ctbdUpload);
				break;

				//多媒体事件信息上传(0x0800)
			case 2048:
				MultiMediaEventInfo mmeInfo = BbByteDecoder.decoderMmeInfoMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderMmeInfoJson(baseInfo, mmeInfo);
				break;

				//多媒体数据上传 (0x0801)
			case 2049:
				MultiMediaData multiMediaData = BbByteDecoder.decoderMultiMediaDataMsg(baseInfo,msgbody);
				jsonMsg = BbJsonEncoder.encoderMultiMediaDataJson(baseInfo, multiMediaData);
				/*picBuffer.append(multiMediaData.getMediaData());
				if(baseInfo.getSubpacket() == baseInfo.getTotalpacket()){
					FileUtil.saveToImgByStr(ParseUtil.parseHexStrToByte(picBuffer.toString()), "D://", "pic.jpg");
					picBuffer = new StringBuffer();
				}*/
				break;

				//存储多媒体数据检索应答(0x0802)
			case 2050:
				StorageMultiMediaResponse smmResponse = BbByteDecoder.decoderSmmResponseMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderSmmResponseJson(baseInfo, smmResponse);
				break;

				//摄像头立即拍摄命令应答(0x0805)
			case 2053:
				CameraCommandResponse ccResponse = BbByteDecoder.decoderCcResponseMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderCcResponseJson(baseInfo, ccResponse);
				break;

				//数据上行透传  (0x0900)
			case 2304:
				DataTransmission dataTransmission = BbByteDecoder.decoderDataTransmission(msgbody);
				jsonMsg = BbJsonEncoder.encoderDataTransmissionJson(baseInfo, dataTransmission);
				break;	

				//数据压缩上报 (0x0901)
			case 2305:
				DataCompressReport dataCompressReport = BbByteDecoder.decoderDataCompressReportMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderDataCompressReportJson(baseInfo, dataCompressReport);
				break;

				//终端 RSA 公钥(0x0A00)
			case 2560:
				PublicKey publicKey = BbByteDecoder.decoderTerminalRsaPublicKey(msgbody);
				jsonMsg = BbJsonEncoder.encoderPublicKeyJson(baseInfo,publicKey);
				break;

				/***************************************************************************************
				 ************************************按照部标自定义协议**********************************
				 ***************************************************************************************/

				//抢答
			case 20740:
				CallInfo callInfo = BbByteDecoder.decoderAnswerCall(msgbody);
				jsonMsg = BbJsonEncoder.encoderAnswerCallJson(baseInfo, callInfo);
				break;

				//电召中心处理结果应答
			case 20741:
				CallInfo callInfo1 = BbByteDecoder.decoderCentralProcessingResponse(msgbody);
				jsonMsg = BbJsonEncoder.encoderCentralProcessingResponseJson(baseInfo, callInfo1);
				break;

				//执行电召
			case 20743:
				CallInfo callInfo2 = BbByteDecoder.decoderPerformCall(msgbody);
				jsonMsg = BbJsonEncoder.encoderPerformCallJson(baseInfo, callInfo2);
				break;

				//计价器开钥匙门信息(0x5108)
			case 20744:
				//解析二进制协议
				TaximeterInfo taximeterInfo = BbByteDecoder.decoderTaximeterMsg(msgbody);
				//封装json协议
				jsonMsg = BbJsonEncoder.encoderTaximeterJson(baseInfo, taximeterInfo);
				break;

				//服务评价(0x5108)
			case 20745:
				//解析二进制协议
				ServiceEvaluation serviceEvaluation = BbByteDecoder.decoderServiceEvaluationMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderServiceEvaluation(baseInfo, serviceEvaluation);
				break;

				//上报终端功能使用次数(0x510A)
			case 20746:
				TerminalUseInfo terminalUseInfo = BbByteDecoder.decoderTerminalUseMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderTerminalUse(baseInfo, terminalUseInfo);
				break;

				//日使用上报(0x510C)
			case 20748:
				TerminalUseInfo tUseInfo = BbByteDecoder.decoderTerminalDayUseMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderTerminalDayUse(baseInfo, tUseInfo);
				break;

				/***************************************************************************************
				 *************************城市联盟(厦门)新能源产业发展有限公司协议*************************
				 ***************************************************************************************/
				//日使用上报(0x510D)
			case 20749:
				BatteryInfo batteryInfo = BbByteDecoder.decoderBatteryInfo(msgbody);
				jsonMsg = BbJsonEncoder.encoderBatteryInfo(baseInfo, batteryInfo);
				break;
				
				//乘客人数上报
			case 0x510E:
				zjCallInfo = BbByteDecoder.decoderNumberReported(msgbody);
				jsonMsg = BbJsonEncoder.encoderNumberReportedJson(baseInfo, zjCallInfo);
				break;
				
				/***************************************************************************************
				 *************************浙江智慧车联网有限公司协议**************************************
				 ***************************************************************************************/

				//驾驶员抢答命令
			case 0x0B01:
				zjCallInfo = BbByteDecoder.decoderZjAnswerCall(msgbody);
				jsonMsg = BbJsonEncoder.encoderZjAnswerCallJson(baseInfo, zjCallInfo);
				break;

				//抢答结果确认
			case 0x0B0F:
				zjCallInfo = BbByteDecoder.decoderZjAnswerCall(msgbody);
				jsonMsg = BbJsonEncoder.encoderZjAnswerCallJson(baseInfo, zjCallInfo);
				break;

				//电召任务完成
			case 0x0B07:
				zjCallInfo = BbByteDecoder.decoderZjAnswerCall(msgbody);
				jsonMsg = BbJsonEncoder.encoderZjAnswerCallJson(baseInfo, zjCallInfo);
				break;

				//驾驶员取消电召
			case 0x0B08:
				zjCallInfo = BbByteDecoder.decoderZjCancelCall(msgbody);
				jsonMsg = BbJsonEncoder.encoderZjCancelCallJson(baseInfo, zjCallInfo);
				break;


				//服务评价
			case 0x8B09:
				zjCallInfo = BbByteDecoder.decoderZjEvaluation(msgbody);
				jsonMsg = BbJsonEncoder.encoderZjEvaluationJson(baseInfo, zjCallInfo);
				break;
				
				/***************************************************************************************
				 ************************************8.94.56gate设备到web**********************************
				 ***************************************************************************************/	

				//查询货源(0x7101)
			case 28929:
				GateOrder orderInfo = BbByteDecoder.decoderQueryGoodsMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderQueryGoods(baseInfo, orderInfo);
				break;

				//确认订单(0x7102)
			case 28930:
				GateOrder oInfo = BbByteDecoder.decoderConfirmOrderMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderConfirmOrder(baseInfo, oInfo);
				break;

				//已运到(0x7103)
			case 28931:
				GateOrder oInfo1 = BbByteDecoder.decoderArriveOrderMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderArriveOrder(baseInfo, oInfo1);
				break;

				//查询订单(0x7104)
			case 28932:
				GateOrder oInfo2 = BbByteDecoder.decoderQueryOrderMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderQueryOrder(baseInfo, oInfo2);
				break;

				//投诉(0x7105)
			case 28933:
				GateOrder complainOrder = BbByteDecoder.decoderComplainOrderMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderComplainOrder(baseInfo, complainOrder);
				break;

				//查询积分诚信度(0x7106)
			case 28934:
				jsonMsg = BbJsonEncoder.encoderQueryIntegralOrder(baseInfo);
				break;

				//查询收货联系方式(0x7107)
			case 28935:
				GateOrder oInfo3 = BbByteDecoder.decoderQueryOrderReceiptMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderQueryOrderReceipt(baseInfo, oInfo3);
				break;

				//查询我的资料(0x7108)
			case 28936:
				jsonMsg = BbJsonEncoder.encoderQueryInformation(baseInfo);
				break;

				//查询我的车源(0x7109)
			case 28937:
				jsonMsg = BbJsonEncoder.encoderQueryCarSource(baseInfo);
				break;

				//车源更新状态(0x710A)
			case 28938:
				GateOrder oInfo4 = BbByteDecoder.decoderUpdateCarsourceStatusMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderUpdateCarsourceStatus(baseInfo,oInfo4);
				break;

				//车源更新发布时间(0x710B)
			case 28939:
				GateOrder oInfo5 = BbByteDecoder.decoderUpdateCarsourceTimeMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderUpdateCarsourceTime(baseInfo,oInfo5);
				break;

				//发布车源(0x710C)
			case 28940:
				CarSource carSource = BbByteDecoder.decoderSendCarsourceMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderSendCarsource(baseInfo,carSource);
				break;

				//回程状态记录(0x710D)
			case 28941:
				ReturnRecord returnRecord = BbByteDecoder.decoderReturnRecordMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderReturnRecord(baseInfo,returnRecord);
				break;

				//冷链阀值和温度(0x710E)
			case 28942:
				WarnInfo warnInfo = BbByteDecoder.decoderWarnInfoMsg(msgbody);
				jsonMsg = BbJsonEncoder.encoderWarnInfo(baseInfo,warnInfo);
				break;
				
				//视频回放列表
			case 0x5114:
				TaxiInfo taxiInfo = BbTaxiByteDecoder.decoderVideoPlaybackList(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderVideoPlaybackListJson(baseInfo, taxiInfo);
				break;
				
				//回放结束
			case 0x5115:
				taxiInfo = BbTaxiByteDecoder.decoderVideoPlaybackEnd(msgbody);
				jsonMsg = BbTaxiJsonEncoder.encoderVideoPlaybackEndJson(baseInfo, taxiInfo);
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
