package com.careye.common.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.careye.base.service.GenericService;
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
import com.careye.common.service.CommonService;
import com.careye.utils.DateUtil;

/**
 * @项目名称：TAXISERVER
 * @类名称：CommonServiceImpl
 * @类描述：通用应答接口
 * @创建人：wuyongde
 * @创建时间：2015-4-17 下午06:06:06
 * @修改人：wuyongde
 * @修改时间：2015-4-17 下午06:06:06
 * @修改备注：
 * @version 1.0
 */
public class CommonServiceImpl extends GenericService implements CommonService{

	private static Logger logger = Logger.getLogger(CommonServiceImpl.class);
	
	/**
	 * 根据流水号更新处理结果
	 * @param seq
	 * @return
	 */
	@Override
	public int updateControlResult(ControlRecord controlRecord) {
		return this.baseDao.update("oracle-commonSQL.updateControlResult", controlRecord);
	}
	
	/**
	 * 根据流水号更新处理结果
	 * @param poiInfo
	 * @return
	 */
	public int updatePoiInfoResult(PoiInfo poiInfo){
		return this.baseDao.update("oracle-commonSQL.updatePoiInfoResult", poiInfo);
	}

	/**
	 * 根据流水号修改文本信息发送结果
	 * @param sendRecord
	 * @return
	 */
	@Override
	public int updateTextInfoResule(SendRecord sendRecord) {
		return this.baseDao.update("oracle-commonSQL.updateTextInfoResule", sendRecord);
	}
	
	/**
	 * 更新区域处理结果
	 * @param areaSet
	 * @return
	 */
	public int updateAreaCarResult(AreaSet areaSet){
		return this.baseDao.update("oracle-commonSQL.updateAreaCarResult", areaSet);
	}
	
	/**
	 * 更新摄像头立即拍摄结果
	 * @param photoSendRecord
	 * @return
	 */
	public int updatePhotoSendRecordResult(PhotoSendRecord photoSendRecord){
		return this.baseDao.update("oracle-commonSQL.updatePhotoSendRecordResult", photoSendRecord);
	}
	
	/**
	 * 更新多媒体数据检索记录结果
	 * @param multimediaSearchRecord
	 * @return
	 */
	public int updateMultimediaSearchRecordResult(MultimediaSearchRecord multimediaSearchRecord){
		return this.baseDao.update("oracle-commonSQL.updateMultimediaSearchRecordResult", multimediaSearchRecord);
	}
	
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
	public Integer addCommondLog(Integer carid,Integer userid,Integer msgid,String msgtype,Integer seq,String data){
		
		CommondLog commondLog=new CommondLog();
		commondLog.setCarid(carid);
		commondLog.setCreatetime(DateUtil.getSQLDate());
		commondLog.setData(data);
		commondLog.setMsgid(msgid);
		commondLog.setMsgtype(msgtype);
		commondLog.setSeq(seq);
		commondLog.setUserid(userid);
		
		return this.baseDao.save("oracle-commonSQL.addCommondLog", commondLog);
		
	}
	
	/**
	 * 修改指令下发日志记录
	 */
	public Integer updateCommondLog(CommondLog commondLog){
		return this.baseDao.update("oracle-commonSQL.updateCommondLog", commondLog);
	}
	
	/**
	 * 添加上、下线记录
	 */
	public Integer saveHeartRecord(HeartRecord heartRecord){
		heartRecord.setCreatetime(DateUtil.getSQLDate());
		heartRecord.setToday(DateUtil.getToDay());
		int count = (Integer) this.baseDao.queryForObject("oracle-commonSQL.queryHeartRecordCount", heartRecord);;
		if(count == 0){
			return this.baseDao.save("oracle-commonSQL.addHeartRecord", heartRecord);
		}else{
			return this.baseDao.update("oracle-commonSQL.updateHeartRecord", heartRecord);
		}
	}
	
	/**
	 * 添加多媒体数据
	 */
	public Integer addMultiMedia(MultiMedia multiMedia){
		return this.baseDao.save("oracle-commonSQL.addMultiMedia", multiMedia);
	}
	
	/**
	 * 添加多媒体事件数据
	 */
	public Integer addMediaEvent(MediaEvent mediaEvent){
		return this.baseDao.save("oracle-commonSQL.addMediaEvent", mediaEvent);
	}
	
	/**
	 * 添加考勤信息
	 * @param clockInfo
	 */
	public Integer saveClockInfo(ClockInfo clockInfo){
		//根据carid与上班时间判断是否有上班打卡记录
		Integer countid = (Integer) this.baseDao.queryForObject("oracle-commonSQL.queryClockInfoIsExist", clockInfo);
		if(countid != null){
			clockInfo.setId(countid);
			return this.baseDao.update("oracle-commonSQL.updateClockInfo", clockInfo);
		}
		return this.baseDao.save("oracle-commonSQL.addClockInfo", clockInfo);
	}
	
	/**
	 * 保存车辆文本信息
	 * @param carInfo
	 * @return
	 */
	public Integer saveTextInfo(LedMessage ledMessage){
		Integer count = (Integer)this.baseDao.queryForObject("oracle-commonSQL.queryTextInfoExist", ledMessage.getCarid());
		if(count == 0){
			return this.baseDao.save("oracle-commonSQL.addTextInfo", ledMessage);
		}else{
			return this.baseDao.update("oracle-commonSQL.updateTextInfo", ledMessage);
		}
	}
	
	/**
	 * 保存运营数据
	 * @param operateData
	 */
	public Integer saveOperateData(OperateData operateData){
		Integer count = (Integer)this.baseDao.queryForObject("oracle-commonSQL.queryOperateDataExist", operateData);
		if(count == 0){
			return this.baseDao.save("oracle-commonSQL.addOperateData", operateData);
		}else{
			return 0;
		}
	}
	
	/**
	 * 得到车辆系统区域
	 * @param carnumber
	 * @return
	 */
	public List<AreaSet> getCarAreaList(String carnumber){
		return this.baseDao.queryForList("oracle-commonSQL.getCarAreaList", carnumber);
	}
	
	/**
	 * 根据carid获取设备信息
	 * @param terminalnum
	 * @return
	 */
	@Override
	public TerminalPositionInfo getLngLatByCarid(int carid){
		return (TerminalPositionInfo) this.baseDao.queryForObject("oracle-commonSQL.getLngLatByCarid", carid);
	}
	
}
