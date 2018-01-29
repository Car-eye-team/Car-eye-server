package com.careye.customer.service;

import java.util.List;
import java.util.Map;

import com.careye.customer.domain.ComplaintCount;
import com.careye.customer.domain.ComplaintInfo;
import com.careye.customer.domain.ComplaintType;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-12 下午04:26:56
 * @修改人：ll
 * @修改时间：2016-5-12 下午04:26:56
 * @修改备注：
 * @version 1.0
 */
public interface ComplaintService {
	
	/**
	 * word导出投诉统计getTSWordByYear 按年统计
	 */
	public List<ComplaintInfo> getTSWordByYear(ComplaintCount complaintCount);
	/**
	 * word导出投诉统计getTSWordByMonth 按月统计
	 */
	public List<ComplaintInfo> getTSWordByMonth(ComplaintCount complaintCount);
	
	/**
	 * 得到所有投诉类型列表
	 */
	public List getAllComplaintTypeList();
	
	/**
	 * 分页查询投诉类型列表
	 */
	public Map getComplaintTypeList(Integer currentPage, Integer everyPage, ComplaintType complaintType);
	
	/**
	 * 新增投诉类型
	 */
	public int saveComplaintType(ComplaintType complaintType);
	
	/**
	 * 删除投诉类型
	 */
	public int delComplaintType(List list);
	
	/**
	 * 分页查询投诉列表
	 */
	public Map getComplaintInfoList(Integer currentPage, Integer everyPage, ComplaintInfo complaintInfo);
	
	/**
	 * 删除投诉
	 */
	public int delComplaintInfo(List list);
	
	/**
	 * 投诉处理
	 */
	public int dealComplaintInfo(ComplaintInfo complaintInfo);
	
	/**
	 * 导出投诉信息
	 */
	public List exportExcel(ComplaintInfo complaintInfo);
	
	/**
	 * 查询投诉按年列表
	 */
	public List<ComplaintCount> getAllComplaintCountByYear(ComplaintCount complaintCount);
	
	/**
	 * 查询投诉按月列表
	 */
	public List<ComplaintCount> getAllComplaintCountByMonth(ComplaintCount complaintCount);
	
	/**
	 * 分页查询投诉统计详情
	 */
	public Map getComplaintCountDetails(Integer currentPage, Integer everyPage, ComplaintCount complaintCount);
	
}
