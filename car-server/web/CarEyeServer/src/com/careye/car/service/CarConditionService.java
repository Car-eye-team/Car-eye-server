package com.careye.car.service;

import java.util.Map;

import com.careye.car.domain.CarCondition;

/**
 * 
 * @项目名称：CVP
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-10-20 下午02:29:27
 * @修改人：ll
 * @修改时间：2015-10-20 下午02:29:27
 * @修改备注：
 * @version 1.0
 */
public interface CarConditionService {
	
	/**
	 * 添加车辆信息统计
	 * @param carCondition
	 * @return
	 */
	public int saveCarCondition(CarCondition carCondition);
	
	/**
	 * 修改车辆信息统计
	 * @param carCondition
	 * @return
	 */
	public int updateCarCondition(CarCondition carCondition);
	
	/**
	 * 根据carid查询当天是否存在车辆统计信息
	 * @param carCondition
	 * @return
	 */
	public CarCondition findCarCondition(CarCondition carCondition);

	/**
	 * 统计营运数据
	 * @param map
	 * @return
	 */
	public CarCondition queryCarConditionData(Map map);
}
