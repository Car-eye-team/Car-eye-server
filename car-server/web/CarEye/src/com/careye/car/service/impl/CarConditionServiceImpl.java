package com.careye.car.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.car.domain.CarCondition;
import com.careye.car.service.CarConditionService;
import com.careye.common.service.SysOperateLogService;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-10-20 下午05:40:30
 * @修改人：ll
 * @修改时间：2015-10-20 下午05:40:30
 * @修改备注：
 * @version 1.0
 */
public class CarConditionServiceImpl extends GenericService implements
		CarConditionService {
	
	private SysOperateLogService logService;
	/**
	 * 车辆统计信息
	 * @param carCondition
	 * @return
	 */
	public Map getCarConditionList(CarCondition carCondition, int currentPage, int everyPage){
		return this.baseDao.findPageList("oracle-carConditionSQL.getCarConditionList", 
				"oracle-carConditionSQL.getCarConditionListCount", carCondition, currentPage, everyPage);
	}
	
	public SysOperateLogService getLogService() {
		return logService;
	}
	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	/**
	 * 获取企业名称
	 */
	public String getBname(int blocid) {
		// TODO Auto-generated method stub
		return (String) this.baseDao.queryForObject("oracle-OnlineReportSQL.getBname", blocid);
	}

	/**
	 * word导出列表
	 */
	public List<CarCondition> getWordList(CarCondition carCondition) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-carConditionSQL.getWordList",carCondition);
	}

}
