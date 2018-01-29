package com.careye.monitor.service;

import java.util.List;
import java.util.Map;

import com.careye.monitor.domain.CommondLog;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-4 下午02:02:15
 * @修改人：ll
 * @修改时间：2015-11-4 下午02:02:15
 * @修改备注：
 * @version 1.0
 */
public interface CommondLogService {
	
	/**
	 * 分页查询下发日志
	 * @param currentPage
	 * @param everyPage
	 * @param commondLog
	 * @return
	 */
	public Map getCommondLogList(final int currentPage, final int everyPage, CommondLog commondLog);
	
	/**
	 * 条件导出下发日志
	 * @param commondLog
	 * @return
	 */
	public List<CommondLog> exportCommondLog(CommondLog commondLog);
	
	
	/**
	 * 删除
	 */
	public int deleteCommondLog(int id);
}
