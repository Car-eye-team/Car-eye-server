package com.careye.monitor.service;

import java.util.List;
import java.util.Map;

import com.careye.monitor.domain.OnlineRate;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-4-29 上午11:10:52
 * @修改人：ll
 * @修改时间：2016-4-29 上午11:10:52
 * @修改备注：
 * @version 1.0
 */
public interface OnlineRateService {
	/**
	 * word  导出获取企业名称
	 */
	public String getBname(int blocid);
	
	/**
	 * getOnlineRateWord word导出列表
	 */
	public List<OnlineRate> getOnlineRateWordOne(OnlineRate onlineRate);
	public List<OnlineRate> getOnlineRateWordTwo(OnlineRate onlineRate);
	public List<OnlineRate> getOnlineRateWordThree(OnlineRate onlineRate);
	
	
	/**
	 * 分页查询车辆信息
	 * @param currentPage
	 * @param everyPage
	 * @param onlineRate
	 * @return
	 */
	public Map findCarInfoList(final int currentPage, final int everyPage, OnlineRate onlineRate);
	
	/**
	 * 分页查询在线时长
	 * @param currentPage
	 * @param everyPage
	 * @param onlineRate
	 * @return
	 */
	public Map findTwoCarInfoList(final int currentPage, final int everyPage, OnlineRate onlineRate);
	
	/**
	 * 分页查询上下线次数
	 * @param currentPage
	 * @param everyPage
	 * @param onlineRate
	 * @return
	 */
	public Map findThreeCarInfoList(final int currentPage, final int everyPage, OnlineRate onlineRate);
}
