package com.careye.car.service;

import java.util.List;
import java.util.Map;

import com.careye.car.domain.CarCondition;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-10-20 下午05:38:23
 * @修改人：ll
 * @修改时间：2015-10-20 下午05:38:23
 * @修改备注：
 * @version 1.0
 */
public interface CarConditionService {
	
	/**
	 * 获取企业名称
	 */
	public String getBname(int blocid);
	
	/**
	 * 车辆统计信息
	 * @param areaSet
	 * @return
	 */
	public Map getCarConditionList(CarCondition carCondition, int currentPage, int everyPage);
	
	/**
	 * word导出列表
	 */
	public List<CarCondition> getWordList(CarCondition carCondition);
}
