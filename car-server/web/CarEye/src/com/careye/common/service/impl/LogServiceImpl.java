/**
* Description: car-eye车辆管理平台
* 文件名：CityInfoServiceImpl.java
* 版本信息：1.0
* 日期：2013-8-5
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.common.service.impl;

import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.domain.CommondLog;
import com.careye.common.domain.LogInfo;
import com.careye.common.service.LogService;
import com.careye.utils.DateUtil;

/**
 * @项目名称：car-eye
 * @类名称：CityInfoServiceImpl
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-3-9 上午10:26:07
 * @修改人：zhangrong
 * @修改时间：2015-3-9 上午10:26:07
 * @修改备注：
 * @version 1.0
 */
public class LogServiceImpl extends GenericService implements LogService{

	@Override
	public Integer addLogInfo(Integer userid,String content,Integer operattype) {
		LogInfo log=new LogInfo();
		log.setUserid(userid);
		log.setContent(content);
		log.setOperattype(operattype);
		log.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-logSQL.addLogInfo", log);
	}

	@Override
	public Map findLogInfoPageList(int currentPage, int everyPage, LogInfo log){
		return this.baseDao.findPageList("oracle-logSQL.findLogInfoPageList",
				"oracle-logSQL.findLogInfoPageListCount",log,currentPage,everyPage);
	}
	
	

}
