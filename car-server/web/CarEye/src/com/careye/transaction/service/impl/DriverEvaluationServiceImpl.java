package com.careye.transaction.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.transaction.domain.DriverEvaluation;
import com.careye.transaction.service.DriverEvaluationService;
import com.careye.utils.SessionUtils;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-25 下午05:17:14
 * @修改人：ll
 * @修改时间：2015-11-25 下午05:17:14
 * @修改备注：
 * @version 1.0
 */
public class DriverEvaluationServiceImpl extends GenericService implements
		DriverEvaluationService {

	private SysOperateLogService logService;
	
	/**
	 * 获得司机评价客户信息列表
	 * 
	 * @param driverEvaluation
	 * @return
	 */
	public Map<Object, Object> getDriverEvaluationList(final int currentPage,
			final int everyPage, DriverEvaluation driverEvaluation){
		return this.baseDao.findPageList("oracle-driverEvalitionSQL.getDriverEvaluationList",
				"oracle-driverEvalitionSQL.getDriverEvaluationListCount", driverEvaluation, currentPage, everyPage);
	}

	/**
	 * Excel 导出
	 * 
	 * @param driverEvaluation
	 * @return
	 */
	public List<DriverEvaluation> exportExcel(DriverEvaluation driverEvaluation){
		logService.addLogInfo(SessionUtils.getUserId(), "Excel导出司机评价客户信息", 4);
		return this.baseDao.queryForList("oracle-driverEvalitionSQL.getDriverEvaluationList",driverEvaluation);
	}

	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}
}
