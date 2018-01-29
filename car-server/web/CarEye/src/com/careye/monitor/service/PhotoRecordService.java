/**
 * Description: car-eye车辆管理平台
 * 文件名：PhotoRecordService.java
 * 版本信息：1.0
 * 日期：2015-3-26 
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.monitor.service;

import java.util.List;
import java.util.Map;

import com.careye.monitor.domain.Photo;
import com.careye.monitor.domain.RemoteControl;


/**
 * @项目名称：car-eye
 * @类名称：PhotoRecordService
 * @类描述：摄像头立即拍摄记录service接口
 * @创建人：Yuqk
 * @创建时间：2015-3-26 上午10:50:07
 * @修改人：Yuqk
 * @修改时间：2015-3-26 上午10:50:07
 * @修改备注：
 * @version 1.0
 */
public interface PhotoRecordService {

	/**
	 * 分页查询摄像头立即拍摄记录
	 * @param page
	 * @param limit
	 * @param photo
	 * @return
	 */
	public Map findPageRecordList(Integer page, Integer limit,
			Photo photo);
	/**
	 * 添加摄像头立即拍摄记录
	 * @param photo
	 * @return 
	 */
	public int addPhotoRecord(Photo photo);
	
	/**
	 * 导出拍摄记录
	 * @param photo
	 * @return
	 */
	public List<Photo> exportPhotoRecordList(Photo photo);
}
