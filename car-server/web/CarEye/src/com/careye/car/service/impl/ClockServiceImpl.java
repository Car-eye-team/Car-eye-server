package com.careye.car.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.car.domain.ClockInfo;
import com.careye.car.service.ClockService;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-3 下午03:03:28
 * @修改人：ll
 * @修改时间：2015-11-3 下午03:03:28
 * @修改备注：
 * @version 1.0
 */
public class ClockServiceImpl extends GenericService implements ClockService {
	
	/**
	 * 条件查询考勤信息
	 * @param clockInfo
	 * @return
	 */
	@Override
	public Map getClockInfoList(int currentPage, int everyPage,
			ClockInfo clockInfo) {
		return this.baseDao.findPageList("oracle-clockQL.getClockInfoList",
				"oracle-clockQL.getClockInfoListCount", clockInfo,currentPage,everyPage);
	}
	
	/**
	 * 条件导出考勤信息
	 * @param clockInfo
	 * @return
	 */
	public List<ClockInfo> exportClockInfo(ClockInfo clockInfo){
		return this.baseDao.queryForList("oracle-clockQL.getClockInfoList", clockInfo);
	}

}
