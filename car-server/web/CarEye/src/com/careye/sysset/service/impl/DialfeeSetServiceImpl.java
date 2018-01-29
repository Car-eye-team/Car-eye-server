/**
* Description: car-eye车辆管理平台
* 文件名：SetServiceImpl.java
* 版本信息：1.0
* 日期：2014-7-13
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.sysset.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.sysset.domain.AlarmtypeSet;
import com.careye.sysset.domain.DeviceType;
import com.careye.sysset.domain.DialfeeSet;
import com.careye.sysset.domain.PageSet;
import com.careye.sysset.service.DialfeeSetService;
import com.careye.sysset.service.SetService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：car-eye
 * @类名称：CallingUpSetServiceImpl
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-9 上午10:25:07
 * @修改人：huangqin
 * @修改时间：2015-3-9 上午10:25:07
 * @修改备注：
 * @version 1.0
 */
public class DialfeeSetServiceImpl extends GenericService implements DialfeeSetService{

	private SysOperateLogService logService;
	
	/**
	 * 分页电召费用设置列表
	 * @return
	 */
	@Override
	public Map findPageDialfeeSetList(int currentPage, int everyPage,
			DialfeeSet dialfeeSet) {
		return this.baseDao.findPageList("oracle-dialFeeSetSQL.findPageDialfeeSetList", 
				"oracle-dialFeeSetSQL.findPageDialfeeSetListCount", dialfeeSet, currentPage, everyPage);

	}
	
	/**
	 * 添加电召费用设置
	 * @return
	 */
	public int insertDialfeeSet(DialfeeSet DialfeeSet){
		
		return  this.baseDao.save("oracle-dialFeeSetSQL.insertDialfeeSet", DialfeeSet);
	}
	

	/**
	 * 得到电召费用最新的一条记录
	 * @return
	 */
	@Override
	public DialfeeSet getDialfeeSetMaxId() {
		return (DialfeeSet) this.baseDao.queryForObject("oracle-dialFeeSetSQL.getDialfeeSetMaxId");
	}
	
	
	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	
}






