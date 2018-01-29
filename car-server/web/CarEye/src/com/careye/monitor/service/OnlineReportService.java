package com.careye.monitor.service;

import java.util.List;
import java.util.Map;

import com.careye.monitor.domain.OnlineReport;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-6 上午11:07:48
 * @修改人：ll
 * @修改时间：2015-11-6 上午11:07:48
 * @修改备注：
 * @version 1.0
 */
public interface OnlineReportService {
	
	
	public OnlineReport getOR(OnlineReport onlineReport);
	/**
	 * word导出获取车辆信息
	 * 
	 */
	public List<OnlineReport> getCarInfoByCaridStr(String caridStr);
	
	/**
	 * 根据blocid获取企业名称getBname
	 */
	public String getBname(int blocid);
	/**
	 * 查询新车上线监控信息
	 * @param onlineReport
	 * @return
	 */
	public OnlineReport getOnlineReport(OnlineReport onlineReport);
	
	/**
	 * 查询新车上线监控信息列表
	 * @param currentPage
	 * @param everyPage
	 * @param onlineReport
	 * @return
	 */
	public Map getOnlineReportList(final int currentPage, final int everyPage, OnlineReport onlineReport);
	
	/**
	 * 查询新增车辆
	 * @param onlineReport
	 * @return
	 */
	public List<Integer> getNewCarid(OnlineReport onlineReport);
	
	/**
	 * 查询上线车辆
	 * @param onlineReport
	 * @return
	 */
	public List<Integer> getOnlineCarid(OnlineReport onlineReport);
	
	/**
	 * 查询未上线车辆
	 * @param onlineReport
	 * @return
	 */
	public List<Integer> getUnonlineCarid(OnlineReport onlineReport);
}
