package com.careye.monitor.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.monitor.domain.OnlineRate;
import com.careye.monitor.service.OnlineRateService;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-4-29 上午11:13:22
 * @修改人：ll
 * @修改时间：2016-4-29 上午11:13:22
 * @修改备注：
 * @version 1.0
 */
public class OnlineRateServiceImpl extends GenericService implements
		OnlineRateService {

	
	/**
	 * word  导出获取企业名称
	 */
	public String getBname(int blocid) {
		// TODO Auto-generated method stub
		return (String) this.baseDao.queryForObject("oracle-OnlineReportSQL.getBname", blocid);
	}
	/**
	 * 分页查询车辆信息
	 * @param currentPage
	 * @param everyPage
	 * @param onlineRate
	 * @return
	 */
	public Map findCarInfoList(final int currentPage, final int everyPage, OnlineRate onlineRate){
		return this.baseDao.findPageList("oracle-OnlineRateSQL.findCarInfoList",
				"oracle-OnlineRateSQL.findCarInfoListCount", onlineRate,currentPage,everyPage);
	}
	
	/**
	 * 分页查询在线时长
	 * @param currentPage
	 * @param everyPage
	 * @param onlineRate
	 * @return
	 */
	public Map findTwoCarInfoList(final int currentPage, final int everyPage, OnlineRate onlineRate){
		return this.baseDao.findPageList("oracle-OnlineRateSQL.findTwoCarInfoList",
				"oracle-OnlineRateSQL.findTwoCarInfoListCount", onlineRate,currentPage,everyPage);
	}
	
	/**
	 * 分页查询上下线次数
	 * @param currentPage
	 * @param everyPage
	 * @param onlineRate
	 * @return
	 */
	public Map findThreeCarInfoList(final int currentPage, final int everyPage, OnlineRate onlineRate){
		return this.baseDao.findPageList("oracle-OnlineRateSQL.findThreeCarInfoList",
				"oracle-OnlineRateSQL.findThreeCarInfoListCount", onlineRate,currentPage,everyPage);
	}
	/**
	 * getOnlineRateWord word导出列表
	 */
	public List<OnlineRate> getOnlineRateWordOne(OnlineRate onlineRate) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-OnlineRateSQL.findCarInfoList",onlineRate);
	}
	public List<OnlineRate> getOnlineRateWordTwo(OnlineRate onlineRate) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-OnlineRateSQL.findTwoCarInfoList",onlineRate);
	}
	public List<OnlineRate> getOnlineRateWordThree(OnlineRate onlineRate) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-OnlineRateSQL.findThreeCarInfoList",onlineRate);
	}

	

}
