/**
* Description: car-eye车辆管理平台
* 文件名：Log.java
* 版本信息：1.0
* 日期：2014-7-28
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.service;

import java.util.Map;

import com.careye.common.domain.LogInfo;

/**
 * @项目名称：car-eyeTms
 * @类名称：Log
 * @类描述：类的方法描述注解
 * @创建人：lxh
 * @创建时间：2014-7-28 下午05:49:40
 * @修改人：lxh
 * @修改时间：2014-7-28 下午05:49:40
 * @修改备注：
 * @version 1.0
 */
public  interface LogService {
	/**
	 * 分页查询日志信息列表
	 * @return
	 */
	public Map findLogInfoPageList(int currentPage, int everyPage,LogInfo log);
	

	/**
	 * 添加日志信息
	 * @param carInfo
	 * @return
	 */
	public Integer addLogInfo(Integer userid,String content,Integer operattype);
	

}
