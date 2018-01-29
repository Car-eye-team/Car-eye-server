/**
 * Description: car-eye车辆管理平台
 * 文件名：MultiMediaServiceImpl.java
 * 版本信息：1.0
 * 日期：2015-3-26
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.monitor.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.message.domain.MultimediaSearchRecord;
import com.careye.message.domain.PhotoSendRecord;
import com.careye.message.domain.SoundRecord;
import com.careye.monitor.domain.MultiMedia;
import com.careye.monitor.service.MultiMediaService;

/**
 * @项目名称：car-eye
 * @类名称：MultiMediaServiceImpl
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-26 下午03:34:33
 * @修改人：Yuqk
 * @修改时间：2015-3-26 下午03:34:33
 * @修改备注：
 * @version 1.0
 */
public class MultiMediaServiceImpl extends GenericService implements
		MultiMediaService {
	private SysOperateLogService logService;

	/**
	 * 获取媒体数据列表，用于根据多媒体路径显示图像
	 * 
	 * @param currentPage
	 * @param everyPage
	 * @param multiMediaInfo
	 * @return
	 */
	public List<MultiMedia> queryMultiMediaByMSId(MultiMedia multiMedia) {
		return this.baseDao.queryForList(
				"oracle-multiMediaSQL.findMultiMediaByMSId", multiMedia);
	}

	/**
	 * 查询多媒体数据列表
	 */
	public Map queryMultiMediaList(Integer page, Integer limit,
			MultiMedia multiMedia) {
		return this.baseDao.findPageList(
				"oracle-multiMediaSQL.findMultiMediaList",
				"oracle-multiMediaSQL.findMultiMediaListCount", multiMedia,
				page, limit);
	}

	
	@Override
	public List<MultiMedia> exportPhotoRecordList(MultiMedia multiMedia) {
		return this.baseDao.queryForList(
				"oracle-multiMediaSQL.findMultiMediaList", multiMedia);
	}


	/**
	 * 更新多媒体数据检索记录结果
	 * @param multimediaSearchRecord
	 * @return
	 */
	@Override
	public int updateMultimediaSearchRecordResult(
			MultimediaSearchRecord multimediaSearchRecord) {
		return this.baseDao.update("oracle-multiMediaSQL.updateMultimediaSearchRecordResult", multimediaSearchRecord);
	}
	
	/**
	 * 添加多媒体检索记录
	 * @param photo
	 * @return 
	 */
	public int addMultimediaSearchRecord(MultimediaSearchRecord multimediaSearchRecord){
		return this.baseDao.save("oracle-multiMediaSQL.addMultimediaSearchRecord", multimediaSearchRecord);
	}
	
	/**
	 * 查询多媒体检索记录列表
	 */
	public Map queryMultimediaSearchRecordList(Integer page, Integer limit,
			MultimediaSearchRecord multimediaSearchRecord) {
		
		return this.baseDao.findPageList(
				"oracle-multiMediaSQL.findMultimediaSearchRecordList",
				"oracle-multiMediaSQL.findMultimediaSearchRecordListCount", multimediaSearchRecord,page, limit);
	}
	
	/**
	 * 导出多媒体检索记录
	 * @param multiMedia
	 * @return
	 */
	public List<MultimediaSearchRecord> exportMultimediaSearchRecordList(MultimediaSearchRecord multimediaSearchRecord){
		return this.baseDao.queryForList("oracle-multiMediaSQL.exportMultimediaSearchRecordList", multimediaSearchRecord);
	}
	
	/**
	 * 多媒体照片列表
	 * @param multiMedia
	 * @return
	 */
	public List queryCarPhotos(MultiMedia multiMedia){
		return this.baseDao.queryForList("oracle-multiMediaSQL.queryCarPhotos", multiMedia);
	}
	
	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	/**
	 * word导出   根据carid获取车牌和企业名称
	 * getCaridByBname
	 * getCaridByBcarnumber
	 */
	public String getCaridByBcarnumber(Integer carid) {
		// TODO Auto-generated method stub
		return (String) this.baseDao.queryForObject("oracle-multiMediaSQL.getCaridByBcarnumber", carid);
	}
	public String getCaridByBname(Integer carid) {
		// TODO Auto-generated method stub
		return (String) this.baseDao.queryForObject("oracle-multiMediaSQL.getCaridByBname", carid);
	}

}
