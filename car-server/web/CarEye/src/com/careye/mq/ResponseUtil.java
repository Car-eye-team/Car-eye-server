/**
 * Description: 多森商用车平台
 * 文件名：ResponseHutil.java
 * 版本信息：1.0
 * 日期：2014-5-30
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.mq;

import com.careye.car.domain.AreaSet;
import com.careye.car.domain.CarInfo;
import com.careye.car.domain.ControlRecord;
import com.careye.car.domain.PoiInfo;
import com.careye.constant.ServiceConfig;
import com.careye.message.domain.EventCar;
import com.careye.message.domain.LineSet;
import com.careye.message.domain.MenuCar;
import com.careye.message.domain.MultimediaSearchRecord;
import com.careye.message.domain.PhotoSendRecord;
import com.careye.message.domain.QuestionSendRecord;
import com.careye.message.domain.SendRecord;
import com.careye.message.domain.ServiceSend;
import com.careye.message.domain.SoundRecord;
import com.careye.tel.domain.TelBookSend;
import com.careye.tel.domain.TelCallSend;

/**
 * @项目名称：FMS
 * @类名称：ResponseHutil
 * @类描述：应答处理
 * @创建人：zr
 * @创建时间：2014-5-30 下午04:10:24
 * @修改人：zr
 * @修改时间：2014-5-30 下午04:10:24
 * @修改备注：
 * @version 1.0
 */
public class ResponseUtil {

	/**
	 * 处理应答
	 * @param protocol
	 */
	public static void getTreatmentResponse(Protocol protocol){

		if(protocol != null){
			try {
				//应答流水号,对应下发流水号
				int respseq = protocol.getRespseq();
				//应答消息ID，对应下发消息ID
				int respmsgid = protocol.getRespmsgid();
				//result结果 0：成功/确认；1：失败；2：消息有误；3：不支持
				int result = protocol.getResult();

				ToolsUtil.sendMap.remove(respmsgid+"_"+respseq);

				//根据设备号查询车辆是否存在
				CarInfo carInfo = ServiceConfig.carService.getCarInfo(protocol.getTerminal());

				switch (respmsgid) {

				//车辆控制通用应答
				case 34048:
					try {
						if(carInfo != null){
							ControlRecord controlRecord = new ControlRecord();
							controlRecord.setSeq(respseq);
							if(result == 0){
								controlRecord.setResult(1);
							}else{
								controlRecord.setResult(2);
							}
							controlRecord.setCarnumber(carInfo.getCarnumber());
							ServiceConfig.carService.updateControlResult(controlRecord);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

					//文本信息发送通用应答
				case 33536:
					try {
						if(carInfo != null){
							SendRecord sendRecord = new SendRecord();
							sendRecord.setSeq(respseq);
							if(result == 0){
								sendRecord.setResult(1);
							}else{
								sendRecord.setResult(2);
							}
							sendRecord.setCarnumber(carInfo.getCarnumber());
							ServiceConfig.textInfoService.updateTextInfoResule(sendRecord);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

					//事件操作通用应答
				case 33537:
					try {
						if(carInfo != null){
							EventCar eventCar = new EventCar();
							eventCar.setSeq(respseq);
							if(result == 0){
								eventCar.setResult(1);
							}else{
								eventCar.setResult(2);
							}
							eventCar.setCarnumber(carInfo.getCarnumber());
							ServiceConfig.eventService.updateEventResult(eventCar);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

					//提问通用应答
				case 33538:
					try {
						if(carInfo != null){
							QuestionSendRecord questionSendRecord = new QuestionSendRecord();
							questionSendRecord.setSeq(respseq);
							if(result == 0){
								questionSendRecord.setResult(1);
							}else{
								questionSendRecord.setResult(2);
							}
							questionSendRecord.setCarnumber(carInfo.getCarnumber());
							ServiceConfig.questionService.updateQuestionResult(questionSendRecord);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

					//POI信息发送通用应答
				case 36608:
					try {
						if(carInfo != null){
							PoiInfo poiInfo = new PoiInfo();
							poiInfo.setSeq(respseq);
							if(result == 0){
								poiInfo.setResult(1);
							}else{
								poiInfo.setResult(2);
							}
							poiInfo.setCarnumber(carInfo.getCarnumber());
							ServiceConfig.poiInfoService.updatePoiInfoResult(poiInfo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

					//设置圆形区域
				case 34304:
					operAreaCarResult(1,respseq,result,carInfo);
					break;

					//删除圆形区域
				case 34305:
					operAreaCarResult(1,respseq,result,carInfo);
					break;

					//设置矩形区域
				case 34306:
					operAreaCarResult(2,respseq,result,carInfo);
					break;

					//删除矩形区域
				case 34307:
					operAreaCarResult(2,respseq,result,carInfo);
					break;

					//设置多边形区域
				case 34308:
					operAreaCarResult(3,respseq,result,carInfo);
					break;

					//删除多边形区域
				case 34309:
					operAreaCarResult(3,respseq,result,carInfo);
					break;

					//设置线路	
				case 34310:
					try {
						if(carInfo != null){
							LineSet lineSet = new LineSet();
							lineSet.setSeq(respseq);
							if(result == 0){
								lineSet.setResult(1);
							}else{
								lineSet.setResult(2);
							}
							lineSet.setCarnumber(carInfo.getCarnumber());
//							ServiceConfig.lineSetService.updateLineSetResult(lineSet);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

					//删除线路
				case 34311:
					try {
						if(carInfo != null){
							LineSet lineSet = new LineSet();
							lineSet.setSeq(respseq);
							if(result == 0){
								lineSet.setResult(1);
							}else{
								lineSet.setResult(2);
							}
							lineSet.setCarnumber(carInfo.getCarnumber());
//							ServiceConfig.lineSetService.updateLineSetResult(lineSet);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

					//信息点播菜单设置应答
				case 33539:
					try {
						if(carInfo != null){
							MenuCar menuCar = new MenuCar();
							menuCar.setSeq(respseq);
							if(result == 0){
								menuCar.setResult(1);
							}else{
								menuCar.setResult(2);
							}
							menuCar.setCarnumber(carInfo.getCarnumber());
//							ServiceConfig.menuserviceService.updateMenuCar(menuCar);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

					//信息服务下发应答
				case 33540:
					try {
						if(carInfo != null){
							ServiceSend serviceSend = new ServiceSend();
							serviceSend.setSeq(respseq);
							if(result == 0){
								serviceSend.setResult(1);
							}else{
								serviceSend.setResult(2);
							}
							serviceSend.setCarnumber(carInfo.getCarnumber());
//							ServiceConfig.menuserviceService.updateServiceSendResult(serviceSend);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

					//电话回拨应答
				case 33792:
					try {
						if(carInfo != null){
							TelCallSend telCallSend = new TelCallSend();
							telCallSend.setSeq(respseq);
							if(result == 0){
								telCallSend.setResult(1);
							}else{
								telCallSend.setResult(2);
							}
							telCallSend.setCarnumber(carInfo.getCarnumber());
							ServiceConfig.telCallService.updateTelCallResule(telCallSend);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

					//设置电话本应答
				case 33793:
					try {
						if(carInfo != null){
							TelBookSend telBookSend = new TelBookSend();
							telBookSend.setSeq(respseq);
							if(result == 0){
								telBookSend.setResult(1);
							}else{
								telBookSend.setResult(2);
							}
							telBookSend.setCarnumber(carInfo.getCarnumber());
							ServiceConfig.telBookService.updateTelBookSendResult(telBookSend);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

					//录音开始下发应答
				case 34820:
					try {
						if(carInfo != null){
							SoundRecord soundRecord = new SoundRecord();
							soundRecord.setSeq(respseq);
							if(result == 0){
								soundRecord.setResult(1);
							}else{
								soundRecord.setResult(2);
							}
							soundRecord.setCarnumber(carInfo.getCarnumber());
//							ServiceConfig.multiMediaService.updateSoundRecordResult(soundRecord);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

					//摄像头立即拍摄应答
				case 34817:
					try {
						if(carInfo != null){
							PhotoSendRecord photoSendRecord = new PhotoSendRecord();
							photoSendRecord.setSeq(respseq);
							if(result == 0){
								photoSendRecord.setResult(1);
							}else{
								photoSendRecord.setResult(2);
							}
							photoSendRecord.setCarid(carInfo.getId());
//							ServiceConfig.multiMediaService.updatePhotoSendRecordResult(photoSendRecord);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

					//存储多媒体数据检索应答
				case 34818:
					try {
						if(carInfo != null){
							MultimediaSearchRecord multimediaSearchRecord = new MultimediaSearchRecord();
							multimediaSearchRecord.setSeq(respseq);
							if(result == 0){
								multimediaSearchRecord.setResult(1);
							}else{
								multimediaSearchRecord.setResult(2);
							}
							multimediaSearchRecord.setCarid(carInfo.getId());
//							ServiceConfig.multiMediaService.updateMultimediaSearchRecordResult(multimediaSearchRecord);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				default:
					break;
				}


			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 操作区域
	 * @param type 1 圆形区域 2 矩形区域 3 多边形区域
	 * @param respseq 
	 * @param result
	 * @param carInfo
	 */
	public static void operAreaCarResult(int type,int respseq,int result,CarInfo carInfo){
		try {
			if(carInfo != null){
				AreaSet areaSet = new AreaSet();
				areaSet.setSeq(respseq);
				if(result == 0){
					areaSet.setResult(1);
				}else{
					areaSet.setResult(2);
				}
				areaSet.setAreatype(type);
				areaSet.setCarnumber(carInfo.getCarnumber());
				ServiceConfig.areaSetService.updateAreaCarResult(areaSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
