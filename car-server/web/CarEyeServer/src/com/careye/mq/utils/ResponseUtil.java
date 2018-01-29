package com.careye.mq.utils;

import com.careye.car.domain.CarInfo;
import com.careye.car.domain.PhotoSendRecord;
import com.careye.common.domain.AreaSet;
import com.careye.common.domain.CommondLog;
import com.careye.common.domain.ControlRecord;
import com.careye.common.domain.MultimediaSearchRecord;
import com.careye.common.domain.PoiInfo;
import com.careye.common.domain.SendRecord;
import com.careye.constant.ServiceConfig;
import com.careye.mq.domain.Protocol;
import com.careye.utils.DateUtil;

/**
 * @项目名称：TAXISERVER
 * @类名称：ResponseUtil
 * @类描述：处理部标应答
 * @创建人：zr
 * @创建时间：2015-3-12 下午05:50:08
 * @修改人：zr
 * @修改时间：2015-3-12 下午05:50:08
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
				
				//根据设备号查询车辆是否存在
				CarInfo cInfo = new CarInfo();
				cInfo.setTerminal(protocol.getTerminal());
				cInfo.setDevicetype(protocol.getDevicetype());
				CarInfo carInfo = ServiceConfig.carService.getCarInfoDetail(cInfo);
				
				switch (respmsgid) {
				
				//设置圆形区域
				case 34304:
					updateCommandStatus(carInfo,respmsgid,respseq,result);
					operAreaCarResult(1,respseq,result,carInfo);
					break;

					//删除圆形区域
				case 34305:
					updateCommandStatus(carInfo,respmsgid,respseq,result);
					operAreaCarResult(1,respseq,result,carInfo);
					break;

					//设置矩形区域
				case 34306:
					updateCommandStatus(carInfo,respmsgid,respseq,result);
					operAreaCarResult(2,respseq,result,carInfo);
					break;

					//删除矩形区域
				case 34307:
					updateCommandStatus(carInfo,respmsgid,respseq,result);
					operAreaCarResult(2,respseq,result,carInfo);
					break;

					//设置多边形区域
				case 34308:
					updateCommandStatus(carInfo,respmsgid,respseq,result);
					operAreaCarResult(3,respseq,result,carInfo);
					break;

					//删除多边形区域
				case 34309:
					updateCommandStatus(carInfo,respmsgid,respseq,result);
					operAreaCarResult(3,respseq,result,carInfo);
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
							ServiceConfig.commonService.updatePhotoSendRecordResult(photoSendRecord);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					updateCommandStatus(carInfo,respmsgid,respseq,result);
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
							ServiceConfig.commonService.updateMultimediaSearchRecordResult(multimediaSearchRecord);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					updateCommandStatus(carInfo,respmsgid,respseq,result);
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
	 * 更新指令下发状态
	 * @param carInfo
	 */
	private static void updateCommandStatus(CarInfo carInfo,int respmsgid,int respseq,int result) {
		try {
			if(carInfo != null){
				CommondLog commondLog = new CommondLog();
				commondLog.setSeq(respseq);
				commondLog.setCarid(carInfo.getId());
				commondLog.setStatus(result);
				commondLog.setMsgid(respmsgid);
				commondLog.setRestime(DateUtil.getSQLDate());
				ServiceConfig.commonService.updateCommondLog(commondLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
				ServiceConfig.commonService.updateAreaCarResult(areaSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
