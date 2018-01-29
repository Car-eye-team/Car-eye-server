package com.careye.transaction.service;

import java.util.List;
import java.util.Map;

import com.careye.transaction.domain.DriverEvaluation;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-25 下午05:14:22
 * @修改人：ll
 * @修改时间：2015-11-25 下午05:14:22
 * @修改备注：
 * @version 1.0
 */
public interface DriverEvaluationService {
	
	/**
	 * 获得司机评价客户信息列表
	 * 
	 * @param driverEvaluation
	 * @return
	 */
	public Map<Object, Object> getDriverEvaluationList(final int currentPage,
			final int everyPage, DriverEvaluation driverEvaluation);

	/**
	 * Excel 导出
	 * 
	 * @param driverEvaluation
	 * @return
	 */
	public List<DriverEvaluation> exportExcel(DriverEvaluation driverEvaluation);
}
