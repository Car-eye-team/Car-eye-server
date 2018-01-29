package com.careye.common.service;


import java.util.List;

import com.careye.car.domain.CarInfo;
import com.careye.car.domain.PhotoSendRecord;
import com.careye.common.domain.AreaSet;
import com.careye.common.domain.CommondLog;
import com.careye.common.domain.ControlRecord;
import com.careye.common.domain.HeartRecord;
import com.careye.common.domain.MediaEvent;
import com.careye.common.domain.MultiMedia;
import com.careye.common.domain.MultimediaSearchRecord;
import com.careye.common.domain.PoiInfo;
import com.careye.common.domain.SendRecord;
import com.careye.common.domain.TerminalPositionInfo;
import com.careye.mq.domain.ClockInfo;
import com.careye.mq.domain.LedMessage;
import com.careye.mq.domain.OperateData;


/**
 * @项目名称：TAXISERVER
 * @类名称：CommonService
 * @类描述：通用应答接口
 * @创建人：wuyongde
 * @创建时间：2015-4-17 下午06:06:06
 * @修改人：wuyongde
 * @修改时间：2015-4-17 下午06:06:06
 * @修改备注：
 * @version 1.0
 */
public interface CommonService {
	
	/**
	 * 根据流水号更新处理结果
	 * @param seq
	 * @return
	 */
	public int updateControlResult(ControlRecord controlRecord);
	
	/**
	 * 根据流水号更新处理结果
	 * @param poiInfo
	 * @return
	 */
	public int updatePoiInfoResult(PoiInfo poiInfo);
	
	/**
	 * 根据流水号修改文本信息发送结果
	 * @param sendRecord
	 * @return
	 */
	public int updateTextInfoResule(SendRecord sendRecord);
	
	/**
	 * 更新区域处理结果
	 * @param areaSet
	 * @return
	 */
	public int updateAreaCarResult(AreaSet areaSet);
	
	/**
	 * 更新摄像头立即拍摄结果
	 * @param photoSendRecord
	 * @return
	 */
	public int updatePhotoSendRecordResult(PhotoSendRecord photoSendRecord);
	
	/**
	 * 更新多媒体数据检索记录结果
	 * @param multimediaSearchRecord
	 * @return
	 */
	public int updateMultimediaSearchRecordResult(MultimediaSearchRecord multimediaSearchRecord);
	
	/**
	 * 添加指令下发日志记录
	 * @param carid
	 * @param userid
	 * @param mgsid
	 * @param msgtype
	 * @param seq
	 * @param data
	 * @return
	 */
	public Integer addCommondLog(Integer carid,Integer userid,Integer msgid,String msgtype,Integer seq,String data);
	
	/**
	 * 修改指令下发日志记录
	 */
	public Integer updateCommondLog(CommondLog commondLog);
	
	/**
	 * 保存上、下线记录
	 */
	public Integer saveHeartRecord(HeartRecord heartRecord);
	
	/**
	 * 添加多媒体数据
	 */
	public Integer addMultiMedia(MultiMedia multiMedia);

	/**
	 * 添加多媒体事件数据
	 */
	public Integer addMediaEvent(MediaEvent mediaEvent);

	/**
	 * 添加考勤信息
	 * @param clockInfo
	 */
	public Integer saveClockInfo(ClockInfo clockInfo);

	/**
	 * 保存车辆文本信息
	 * @param carInfo
	 * @return
	 */
	public Integer saveTextInfo(LedMessage ledMessage);
	
	/**
	 * 保存运营数据
	 * @param operateData
	 */
	public Integer saveOperateData(OperateData operateData);
	
	/**
	 * 得到车辆系统区域
	 * @param carnumber
	 * @return
	 */
	public List<AreaSet> getCarAreaList(String carnumber);
	
	/**
	 * 根据carid获取设备信息
	 * @param terminalnum
	 * @return
	 */
	public TerminalPositionInfo getLngLatByCarid(int carid);

	
}
