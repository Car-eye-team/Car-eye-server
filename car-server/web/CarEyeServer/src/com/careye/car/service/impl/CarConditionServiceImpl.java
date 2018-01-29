package com.careye.car.service.impl;

import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.car.domain.CarCondition;
import com.careye.car.service.CarConditionService;

/**
 * 
 * @项目名称：CVP
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-10-20 下午02:52:15
 * @修改人：ll
 * @修改时间：2015-10-20 下午02:52:15
 * @修改备注：
 * @version 1.0
 */
public class CarConditionServiceImpl extends GenericService implements
		CarConditionService {

	/**
	 * 添加车辆信息统计
	 * @param carCondition
	 * @return
	 */
	public int saveCarCondition(CarCondition carCondition){
		return this.baseDao.save("oracle-carConditionSQL.insertCarCondition", carCondition);
	}
	
	/**
	 * 修改车辆信息统计
	 * @param carCondition
	 * @return
	 */
	public int updateCarCondition(CarCondition carCondition){
		return this.baseDao.update("oracle-carConditionSQL.updateCarCondition", carCondition);
	}
	
	/**
	 * 根据carid查询当天是否存在车辆统计信息
	 * @param carCondition
	 * @return
	 */
	public CarCondition findCarCondition(CarCondition carCondition){
		return (CarCondition) this.baseDao.queryForObject("oracle-carConditionSQL.findCarCondition", carCondition);
	}
	
	/**
	 * 统计营运数据
	 * @param map
	 * @return
	 */
	public CarCondition queryCarConditionData(Map map){
		return (CarCondition)this.baseDao.queryForObject("oracle-carConditionSQL.queryCarConditionData", map);
	}

}
