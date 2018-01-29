package com.careye.monitor.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.monitor.domain.CommondLog;
import com.careye.monitor.service.CommondLogService;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-4 下午02:05:11
 * @修改人：ll
 * @修改时间：2015-11-4 下午02:05:11
 * @修改备注：
 * @version 1.0
 */
public class CommondLogServiceImpl extends GenericService implements
		CommondLogService {
	
	/**
	 * 分页查询下发日志
	 * @param currentPage
	 * @param everyPage
	 * @param commondLog
	 * @return
	 */
	@Override
	public Map getCommondLogList(int currentPage, int everyPage,
			CommondLog commondLog) {
		return this.baseDao.findPageList("oracle-commondLogSQL.getCommondLogList",
				"oracle-commondLogSQL.getCommondLogListCount", commondLog,currentPage,everyPage);
	}
	
	/**
	 * 条件导出下发日志
	 * @param commondLog
	 * @return
	 */
	public List<CommondLog> exportCommondLog(CommondLog commondLog){
		return this.baseDao.queryForList("oracle-commondLogSQL.getCommondLogList", commondLog);
	}

	/**
	 * 删除
	 */
	public int deleteCommondLog(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-commondLogSQL.deleteCommondLog", id);
	}

}
