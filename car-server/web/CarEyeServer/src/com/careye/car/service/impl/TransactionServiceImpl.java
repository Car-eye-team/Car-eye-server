/**
* Description: 出租车系统
* 文件名：TransactionServiceImpl.java
* 版本信息：1.0
* 日期：2015-3-23
* Copyright car-eye车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.car.service.impl;

import java.util.List;

import com.careye.base.service.GenericService;
import com.careye.car.domain.CarDriver;
import com.careye.car.domain.CarState;
import com.careye.car.domain.DialfeeSet;
import com.careye.car.domain.DriverEvaluation;
import com.careye.car.domain.PassageStatistic;
import com.careye.car.domain.Taximeter;
import com.careye.car.domain.TranDriverCancel;
import com.careye.car.domain.TransactionInfo;
import com.careye.car.service.TransactionService;
import com.careye.constant.ServiceConfig;
import com.careye.ocs.domain.OrderInfo;
import com.careye.utils.DateUtil;

/**
 * @项目名称：TAXISERVER
 * @类名称：TransactionServiceImpl
 * @类描述：业务处理接口
 * @创建人：zr
 * @创建时间：2015-3-23 下午03:19:57
 * @修改人：zr
 * @修改时间：2015-3-23 下午03:19:57
 * @修改备注：
 * @version 1.0
 */
public class TransactionServiceImpl extends GenericService implements TransactionService{

	/**
	 * 插入约车抢答
	 * @param transactionInfo
	 * @return
	 */
	@Override
	public Integer insertTransactionAnswer(TransactionInfo transactionInfo) {
		return this.baseDao.save("oracle-transactionSQL.insertTransactionAnswer", transactionInfo);
	}

	/**
	 * 批量插入约车抢答记录
	 * @param transactionInfo
	 * @return
	 */
	public Integer insertBitchTransactionAnswer(List<TransactionInfo> list){
		return this.baseDao.save("oracle-transactionSQL.insertBitchTransactionAnswer", list);
	}
	
	/**
	 * 根据订单号获取约车业务信息
	 * @param orderid
	 * @return
	 */
	@Override
	public TransactionInfo getTransactionInfo(String orderid) {
		return (TransactionInfo) this.baseDao.queryForObject("oracle-transactionSQL.getTransactionInfo", orderid);
	}

	/**
	 * 更新约车业务状态
	 * @param transactionInfo
	 * @return
	 */
	@Override
	public Integer updateTransactionInfo(TransactionInfo transactionInfo) {
		Integer re = this.baseDao.update("oracle-transactionSQL.updateTransactionInfo", transactionInfo);
		try{
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setOrderid(transactionInfo.getOrderid());
			orderInfo.setOrderstatus(transactionInfo.getStatus());
			ServiceConfig.ocsService.updateOrderStatus(orderInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 获取预约业务列表
	 * @param datetime
	 * @return
	 */
	@Override
	public List<TransactionInfo> getYyTransactionInfoList(String datetime) {
		return this.baseDao.queryForList("oracle-transactionSQL.getYyTransactionInfoList", datetime);
	}

	/**
	 * 根据订单号查询抢答总数
	 * @param orderid
	 * @return
	 */
	@Override
	public Integer getTransactionInfoAnswerCount(String orderid) {
		return (Integer) this.baseDao.queryForObject("oracle-transactionSQL.getTransactionInfoAnswerCount", orderid);
	}

	/**
	 * 插入司机取消记录
	 * @param tranDriverCancel
	 * @return
	 */
	@Override
	public Integer insertTranDriverCancel(TranDriverCancel tranDriverCancel) {
		return this.baseDao.save("oracle-transactionSQL.insertTranDriverCancel", tranDriverCancel);
	}
	
	/**
	 * 根据车辆id更改车辆状态表记录
	 * @param carState
	 */
	public Integer updateCarState(CarState carState){
		return this.baseDao.update("oracle-transactionSQL.updateCarState", carState);
	}
	
	/**
	 * 根据订单号把所有调派车辆调度状态改为未调度
	 * @param carState
	 */
	public Integer updateCarStateByOrderid(String orderid){
		return this.baseDao.update("oracle-transactionSQL.updateCarStateByOrderid", orderid);
	}
	
	/**
	 * 更新车辆调度状态
	 */
	public Integer updateCarStateByCarid(Integer id){
		return this.baseDao.update("oracle-transactionSQL.updateCarStateByCarid", id);
	}
	
	/**
	 * 更新抢答表状态
	 * @param transactionInfo
	 * @return
	 */
	public Integer updateTransactionAnswer(TransactionInfo transactionInfo){
		return this.baseDao.update("oracle-transactionSQL.updateTransactionAnswer", transactionInfo);
	}
	
	
	/**
	 * 根据订单号得到订单状态
	 * @param carState
	 */
	public Integer getOrderStatus(String orderid){
		return (Integer) this.baseDao.queryForObject("oracle-transactionSQL.getOrderStatus", orderid);
	}
	
	/**
	 * 获取未处理订单列表
	 * @param orderInfo
	 * @return
	 */
	public List<TransactionInfo> getUntreatedOrderList(TransactionInfo transactionInfo){
		return this.baseDao.queryForList("oracle-transactionSQL.getUntreatedOrderList", transactionInfo);
	}
	
	/**
	 * 得到电召费用最新的一条记录
	 * @return
	 */
	@Override
	public DialfeeSet getDialfeeSetMaxId() {
		return (DialfeeSet) this.baseDao.queryForObject("oracle-transactionSQL.getDialfeeSetMaxId");
	}
	
	/**
	 * 插入计价器数据
	 * @param transactionInfo
	 * @return
	 */
	public Integer insertMeterData(Taximeter taximeter){
		taximeter.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-transactionSQL.insertMeterData", taximeter);
	}
	
	/**
	 * 插入乘客人数上报统计表
	 * @param PassageStatistic
	 * @return
	 */
	public Integer insertTerminalPassageStatistic(PassageStatistic passageStatistic){
		return this.baseDao.save("oracle-transactionSQL.insertTerminalPassageStatistic", passageStatistic);
	}
	
	/**
	 * 得到当班司机信息
	 * @param id
	 * @return
	 */
	public CarDriver getCurrentDriver(Integer id){
		return (CarDriver)this.baseDao.queryForObject("oracle-transactionSQL.getCurrentDriver", id);
	}
	
	/**
	 * 添加司机取消记录表
	 * @param tranDriverCancel
	 */
	public Integer addTranDriverCancel(TranDriverCancel tranDriverCancel){
		return this.baseDao.save("oracle-transactionSQL.addTranDriverCancel", tranDriverCancel);
	}
	
	/**
	 * 根据车辆id得到订单状态为已调派或者载客中的订单
	 * @param id
	 * @return
	 */
	public List<TransactionInfo> getTransactionInfoList(Integer carid){
		return this.baseDao.queryForList("oracle-transactionSQL.getTransactionInfoList", carid);
	}
	
	/**
	 * 插入司机评价乘客
	 * @param driverEvaluation
	 * @return
	 */
	public Integer insertDriverEvaluation(DriverEvaluation driverEvaluation){
		return this.baseDao.save("oracle-transactionSQL.insertDriverEvaluation", driverEvaluation);
	}
	
	
}








