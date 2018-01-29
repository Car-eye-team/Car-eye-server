/**
* Description: car-eye车辆管理平台
* 文件名：CarService.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.statement.service;

import java.util.List;
import java.util.Map;

import com.careye.customer.domain.Customer;
import com.careye.statement.domain.AreaMore;
import com.careye.statement.domain.CarPosition;
import com.careye.statement.domain.DrivingState;



/**
 * @项目名称：car-eye
 * @类名称：TelCallService
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:00:03
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public interface StateMentService {
	
	/**
	 * 条件查询车辆里程统计报表
	 * @param TelCallSend
	 * @return
	 */
	public List<DrivingState> selectCarKiloList(DrivingState drivingState,List<DrivingState> list);
	
	
	
	/**
	 * 根据Acc状态统计车辆位置总数
	 * @param carPosition
	 * @return
	 */
	public Map<Object, Object> selectCarPosition(final int currentPage,
			final int everyPage, CarPosition carPosition);
	
	
	

	
}
