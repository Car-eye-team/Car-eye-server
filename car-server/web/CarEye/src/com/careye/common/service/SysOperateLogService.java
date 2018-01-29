/**
* Description: car-eye车辆管理平台
* 文件名：Log.java
* 版本信息：1.0
* 日期：2014-7-28
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.service;

import java.util.List;
import java.util.Map;

import com.careye.common.domain.SysOperateLog;
import com.careye.system.domain.SysAuthLoginLog;

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
public  interface SysOperateLogService {
	/**
	 * 分页查询日志信息列表
	 * @return
	 */
	public Map findLogInfoPageList(int currentPage, int everyPage,SysOperateLog log);
	

	/**
	 * 添加日志信息
	 * @param operattype 1添加 2修改 3删除 4导出
	 * @return
	 */
	public Integer addLogInfo(Integer userid,String content,Integer operattype);
	
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
	 * 删除操作日志deleteLogInfo
	 */
	public int deleteLogInfo(int id);
	
	/**
	 * 导出操作日志exportLogInfo
	 */
	public List<SysOperateLog> exportLogInfo(SysOperateLog logInfo);
}
