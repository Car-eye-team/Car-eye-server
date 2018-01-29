/**
 * 
 */
package com.careye.transaction.service;

import java.util.Map;

import com.careye.transaction.domain.RideOrder;
import com.careye.transaction.domain.RidePassenger;


/**
 * @author Administrator
 *
 */
public interface RideOrderService {
	
	/**
	 * 顺风车订单查询分页显示列表
	 */
	public Map selRideOrderList(Integer currentPage, Integer everyPage, RideOrder rideOrder);
	
	/**
	 * 删除顺风车订单
	 * 随便删除该订单号下的乘客信息
	 */
	public int deleteRideOrder(String id);
	public int deleteRO(String id);
	
	/**
	 * 乘车列表
	 */
	
	public Map selRidePassengerList(Integer currentPage, Integer everyPage, RidePassenger ridePassenger);
	
	
	/**
	 * 删除乘客
	 * deleteRidePassenger
	 */
	public int deleteRidePassenger(int id);
	
	
	/**
	 * 乘客详情
	 */
	public RidePassenger findPassengerTrip(RidePassenger ridePassenger);
	
}
