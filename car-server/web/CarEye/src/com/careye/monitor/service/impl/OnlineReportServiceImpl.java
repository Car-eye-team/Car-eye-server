package com.careye.monitor.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.monitor.domain.CommondLog;
import com.careye.monitor.domain.OnlineReport;
import com.careye.monitor.service.OnlineReportService;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-6 上午11:15:56
 * @修改人：ll
 * @修改时间：2015-11-6 上午11:15:56
 * @修改备注：
 * @version 1.0
 */
public class OnlineReportServiceImpl extends GenericService implements
		OnlineReportService {
	
	/**
	 * 查询新车上线监控信息
	 * @param onlineReport
	 * @return
	 */
	@Override
	public OnlineReport getOnlineReport(OnlineReport onlineReport) {
		return (OnlineReport) this.baseDao.queryForObject("oracle-OnlineReportSQL.getOnlineReport", onlineReport);
	}
	
	/**
	 * 查询新车上线监控信息列表
	 * @param currentPage
	 * @param everyPage
	 * @param onlineReport
	 * @return
	 */
	public Map getOnlineReportList(final int currentPage, final int everyPage, OnlineReport onlineReport){
		return this.baseDao.findPageList("oracle-OnlineReportSQL.getOnlineReport", "oracle-OnlineReportSQL.getOnlineReportCount", onlineReport, currentPage, everyPage);
	}
	
	/**
	 * 查询新增车辆
	 * @param onlineReport
	 * @return
	 */
	public List<Integer> getNewCarid(OnlineReport onlineReport){
		return this.baseDao.queryForList("oracle-OnlineReportSQL.getNewCarid", onlineReport);
	}
	
	/**
	 * 查询上线车辆
	 * @param onlineReport
	 * @return
	 */
	public List<Integer> getOnlineCarid(OnlineReport onlineReport){
		return this.baseDao.queryForList("oracle-OnlineReportSQL.getOnlineCarid", onlineReport);
	}
	
	/**
	 * 查询未上线车辆
	 * @param onlineReport
	 * @return
	 */
	public List<Integer> getUnonlineCarid(OnlineReport onlineReport){
		return this.baseDao.queryForList("oracle-OnlineReportSQL.getUnonlineCarid", onlineReport);
	}

	/**
	 * 根据blocid获取企业名称getBname
	 */
	public String getBname(int blocid) {
		// TODO Auto-generated method stub
		return (String) this.baseDao.queryForObject("oracle-OnlineReportSQL.getBname", blocid);
	}

	/**
	 * word导出获取车辆信息
	 * 
	 */
	public List<OnlineReport> getCarInfoByCaridStr(String caridStr) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-OnlineReportSQL.getCarInfoByCaridStr", caridStr);
	}

	@Override
	public OnlineReport getOR(OnlineReport onlineReport) {
		// TODO Auto-generated method stub
		return (OnlineReport) this.baseDao.queryForObject("oracle-OnlineReportSQL.getOnlineReport", onlineReport);
	}
}
