/**
 * 
 */
package com.careye.transaction.service;

import java.util.List;
import java.util.Map;

import com.careye.transaction.domain.EvaluateCount;


/**
 * @author Administrator
 *
 */
public interface EvaluateCountService {
	
	
	
	public List<EvaluateCount> getEvaluateCountListByYear(EvaluateCount evaluateCount);
	
	
	public List<EvaluateCount> getEvaluateCountListByMonth(EvaluateCount evaluateCount);
	
	/**
	 * 根据blocid获取部门名称
	 */
	public String selBlocname(int blocid);
	
	
	
	/**
	 * word导出的详情列表
	 */
	//年
	public List<EvaluateCount> getECListByYear(EvaluateCount evaluateCount);
	//月
	public List<EvaluateCount> getECListByMonth(EvaluateCount evaluateCount);
	
	/**
	 * 分页查询评价统计详情
	 */
	public Map getEvaluateCountDetails(Integer currentPage, Integer everyPage, EvaluateCount evaluateCount);
}
