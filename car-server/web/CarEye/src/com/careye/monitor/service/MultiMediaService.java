/**
 * Description: car-eye车辆管理平台
 * 文件名：MultiMediaService.Java
 * 版本信息：1.0
 * 日期：2015-3-26
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.monitor.service;

import java.util.List;
import java.util.Map;

import com.careye.message.domain.MultimediaSearchRecord;
import com.careye.message.domain.PhotoSendRecord;
import com.careye.message.domain.SoundRecord;
import com.careye.monitor.domain.MultiMedia;

/**
 * @项目名称：car-eye
 * @类名称：MultiMediaService
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-26 下午03:32:19
 * @修改人：Yuqk
 * @修改时间：2015-3-26 下午03:32:19
 * @修改备注：
 * @version 1.0
 */
public interface MultiMediaService {
	/**
	 * 根据MSId查找对应多媒体路径，用于显示图像
	 * @param multiMedia
	 * @return
	 */
	public List<MultiMedia> queryMultiMediaByMSId(MultiMedia multiMedia);

	/**
	 * 查询多媒体数据列表
	 * @param page
	 * @param limit
	 * @param multiMedia
	 * @return
	 */
	public Map queryMultiMediaList(Integer page, Integer limit,
			MultiMedia multiMedia);
	/**
	 * 导出多媒体数据
	 * @param multiMedia
	 * @return
	 */
	public List<MultiMedia> exportPhotoRecordList(MultiMedia multiMedia);
	
	/**
	 * 更新多媒体数据检索记录结果
	 * @param multimediaSearchRecord
	 * @return
	 */
	public int updateMultimediaSearchRecordResult(MultimediaSearchRecord multimediaSearchRecord);
	
	/**
	 * 添加多媒体检索记录
	 * @param photo
	 * @return 
	 */
	public int addMultimediaSearchRecord(MultimediaSearchRecord multimediaSearchRecord);
	
	/**
	 * 查询多媒体检索记录列表
	 * @param page
	 * @param limit
	 * @param multiMedia
	 * @return
	 */
	public Map queryMultimediaSearchRecordList(Integer page, Integer limit,
			MultimediaSearchRecord multimediaSearchRecord);
	
	/**
	 * 导出多媒体检索记录
	 * @param multiMedia
	 * @return
	 */
	public List<MultimediaSearchRecord> exportMultimediaSearchRecordList(MultimediaSearchRecord multimediaSearchRecord);

	/**
	 * 多媒体照片列表
	 * @param multiMedia
	 * @return
	 */
	public List queryCarPhotos(MultiMedia multiMedia);
	
	/**
	 * word导出   根据carid获取车牌和企业名称
	 * getCaridByBname
	 * getCaridByBcarnumber
	 */
	public String getCaridByBname(Integer carid);
	public String getCaridByBcarnumber(Integer carid);

}
