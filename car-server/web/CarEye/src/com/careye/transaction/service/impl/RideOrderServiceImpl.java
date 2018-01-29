/**
 * 
 */
package com.careye.transaction.service.impl;

import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.transaction.domain.RideOrder;
import com.careye.transaction.domain.RidePassenger;
import com.careye.transaction.service.RideOrderService;

/**
 * @author Administrator
 *
 */
public class RideOrderServiceImpl extends GenericService implements RideOrderService{
	private SysOperateLogService logService;
	
	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	/**
	 * 顺风车订单查询分页显示列表
	 */
	public Map selRideOrderList(Integer currentPage, Integer everyPage,
			RideOrder rideOrder) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-rideOrderSQL.selRideOrderList",
				"oracle-rideOrderSQL.selRideOrderListCount", rideOrder, currentPage, everyPage);
	}

	/**
	 * 删除顺风车订单
	 * 删除该订单号下的乘客信息
	 */
	public int deleteRideOrder(String id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-rideOrderSQL.deleteRideOrder", id);
	}

	public int deleteRO(String id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-rideOrderSQL.deleteRO", id);
	}

	/**
	 * 乘客列表
	 */
	public Map selRidePassengerList(Integer currentPage, Integer everyPage,
			RidePassenger ridePassenger) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-rideOrderSQL.selRidePassengerList",
				"oracle-rideOrderSQL.selRidePassengerListCount", ridePassenger, currentPage, everyPage);
	}

	/**
	 * 删除乘客
	 */
	public int deleteRidePassenger(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-rideOrderSQL.deleteRidePassenger", id);
	}

	@Override
	public RidePassenger findPassengerTrip(RidePassenger ridePassenger) {
		// TODO Auto-generated method stub
		return (RidePassenger) this.baseDao.queryForObject("oracle-rideOrderSQL.findPassengerTrip", ridePassenger);
	}


}
