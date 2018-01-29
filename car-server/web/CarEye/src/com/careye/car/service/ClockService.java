package com.careye.car.service;

import java.util.List;
import java.util.Map;

import com.careye.car.domain.AreaSet;
import com.careye.car.domain.ClockInfo;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-3 下午03:01:09
 * @修改人：ll
 * @修改时间：2015-11-3 下午03:01:09
 * @修改备注：
 * @version 1.0
 */
public interface ClockService {
	
	/**
	 * 条件查询考勤信息
	 * @param clockInfo
	 * @return
	 */
	public Map getClockInfoList(final int currentPage, final int everyPage,ClockInfo clockInfo);
	
	/**
	 * 条件导出考勤信息
	 * @param clockInfo
	 * @return
	 */
	public List<ClockInfo> exportClockInfo(ClockInfo clockInfo);
	
}
