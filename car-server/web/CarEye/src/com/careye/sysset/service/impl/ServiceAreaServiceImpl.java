/**
 * Description: car-eye车辆管理平台
 * 文件名：ServiceAreaServiceImpl.java
 * 版本信息：1.0
 * 日期：2015-3-31
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.sysset.service.impl;

import java.util.List;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.sysset.domain.ServiceArea;
import com.careye.sysset.service.ServiceAreaService;

/**
 * @项目名称：car-eye
 * @类名称：ServiceAreaServiceImpl
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-31 下午02:09:00
 * @修改人：Yuqk
 * @修改时间：2015-3-31 下午02:09:00
 * @修改备注：
 * @version 1.0
 */
public class ServiceAreaServiceImpl extends GenericService implements
		ServiceAreaService {
	private SysOperateLogService logService;
	/**
	 * 删除省级记录  级联
	 */
	@Override
	public int closeServiceProvince(Long pcode) {
		return this.baseDao.delete(
				"oracle-serviceAreaSQL.closeServiceProvince", pcode);
	}

	/**
	 * 删除市级记录  级联
	 */
	@Override
	public int closeServiceCity(Long ccode) {
		return this.baseDao.delete(
				"oracle-serviceAreaSQL.closeServiceCity", ccode);
	}

	/**
	 * 删除县级记录
	 */
	@Override
	public int closeServiceCounty(Long cycode) {
		return this.baseDao.delete(
				"oracle-serviceAreaSQL.closeServiceCounty", cycode);
	}

	/**
	 *查询省级记录
	 */
	@Override
	public List findServiceProvince() {
		return this.baseDao.queryForList(
				"oracle-serviceAreaSQL.findServiceProvince");
	}

	/**
	 *查询市级记录
	 */
	@Override
	public List findServiceCity(ServiceArea serviceArea) {
		return this.baseDao.queryForList(
				"oracle-serviceAreaSQL.findServiceCity", serviceArea);
	}

	/**
	 *查询县记录
	 */
	@Override
	public List findServiceCounty(ServiceArea serviceArea) {
		return this.baseDao.queryForList(
				"oracle-serviceAreaSQL.findServiceCounty", serviceArea);
	}

	/**
	 * 增加县记录
	 */
	@Override
	public int openServiceArea(ServiceArea serviceArea) {
		return this.baseDao.save("oracle-serviceAreaSQL.openServiceArea",
				serviceArea);
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
