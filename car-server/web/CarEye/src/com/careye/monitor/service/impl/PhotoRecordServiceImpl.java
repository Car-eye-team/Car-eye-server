/**
 * Description: car-eye车辆管理平台
 * 文件名：PhotoRecordServiceImpl.java
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
import com.careye.monitor.domain.Photo;
import com.careye.monitor.service.PhotoRecordService;

/**
 * @项目名称：car-eye
 * @类名称：PhotoRecordServiceImpl
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-26 上午10:57:48
 * @修改人：Yuqk
 * @修改时间：2015-3-26 上午10:57:48
 * @修改备注：
 * @version 1.0
 */
public class PhotoRecordServiceImpl extends GenericService implements
		PhotoRecordService {
	private SysOperateLogService logService;

	@Override
	public int addPhotoRecord(Photo photo) {
		return this.baseDao.save("oracle-photoSQL.addPhotoRecord", photo);
	}
	

	@Override
	public Map findPageRecordList(Integer page, Integer limit, Photo photo) {
		return this.baseDao.findPageList("oracle-photoSQL.queryPhotoRecordList",
				"oracle-photoSQL.queryPhotoRecordListCount", photo,page, limit);
	}
	
	@Override
	public List<Photo> exportPhotoRecordList(Photo photo) {
		return this.baseDao.queryForList("oracle-photoSQL.queryPhotoRecordList", photo);
	}
	/*
	 * getter setter
	 */
	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	
}
