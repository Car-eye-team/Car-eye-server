/**
* Description: 出租车系统
* 文件名：TransactionServer.java
* 版本信息：1.0
* 日期：2015-3-23
* Copyright car-eye车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.car.service;

import java.util.List;

import com.careye.car.domain.CarDriver;
import com.careye.car.domain.CarState;
import com.careye.car.domain.DialfeeSet;
import com.careye.car.domain.DriverEvaluation;
import com.careye.car.domain.PassageStatistic;
import com.careye.car.domain.Taximeter;
import com.careye.car.domain.TranDriverCancel;
import com.careye.car.domain.TransactionInfo;

/**
 * @项目名称：TAXISERVER
 * @类名称：TransactionServer
 * @类描述：业务处理接口
 * @创建人：zr
 * @创建时间：2015-3-23 下午03:19:15
 * @修改人：zr
 * @修改时间：2015-3-23 下午03:19:15
 * @修改备注：
 * @version 1.0
 */
public interface TransactionService {
	
	/**
	 * 插入约车抢答
	 * @param transactionInfo
	 * @return
	 */
	public Integer insertTransactionAnswer(TransactionInfo transactionInfo);
	/**
	 * 批量插入约车抢答记录
	 * @param transactionInfo
	 * @return
	 */
	public Integer insertBitchTransactionAnswer(List<TransactionInfo> list);
	
	/**
	 * 根据订单号获取约车业务信息
	 * @param orderid
	 * @return
	 */
	public TransactionInfo getTransactionInfo(String orderid);
	
	/**
	 * 更新约车业务状态
	 * @param transactionInfo
	 * @return
	 */
	public Integer updateTransactionInfo(TransactionInfo transactionInfo);
	
	/**
	 * 获取预约业务列表
	 * @param datetime
	 * @return
	 */
	public List<TransactionInfo> getYyTransactionInfoList(String datetime);
	
	/**
	 * 根据订单号查询抢答总数
	 * @param orderid
	 * @return
	 */
	public Integer getTransactionInfoAnswerCount(String orderid);
	
	/**
	 * 插入司机取消记录
	 * @param tranDriverCancel
	 * @return
	 */
	public Integer insertTranDriverCancel(TranDriverCancel tranDriverCancel);
	
	/**
	 * 根据车辆id更改车辆状态表记录
	 * @param carState
	 */
	public Integer updateCarState(CarState carState);
	
	/**
	 * 根据订单号把所有调派车辆调度状态改为未调度
	 * @param carState
	 */
	public Integer updateCarStateByOrderid(String orderid);
	
	/**
	 * 更新车辆调度状态
	 */
	public Integer updateCarStateByCarid(Integer id);
	
	/**
	 * 更新抢答表状态
	 * @param transactionInfo
	 * @return
	 */
	public Integer updateTransactionAnswer(TransactionInfo transactionInfo);
	
	
	/**
	 * 根据订单号得到订单状态
	 * @param carState
	 */
	public Integer getOrderStatus(String orderid);
	
	/**
	 * 获取未处理订单列表
	 * @param orderInfo
	 * @return
	 */
	public List<TransactionInfo> getUntreatedOrderList(TransactionInfo orderInfo);
	
	/**
	 * 得到电召费用最新的一条记录
	 * @return
	 */
	public DialfeeSet getDialfeeSetMaxId();
	
	/**
	 * 插入计价器数据
	 * @param transactionInfo
	 * @return
	 */
	public Integer insertMeterData(Taximeter taximeter);
	
	/**
	 * 插入乘客人数上报统计表
	 * @param PassageStatistic
	 * @return
	 */
	public Integer insertTerminalPassageStatistic(PassageStatistic passageStatistic);
	
	/**
	 * 得到当班司机信息
	 * @param id
	 * @return
	 */
	public CarDriver getCurrentDriver(Integer id);
	
	/**
	 * 添加司机取消记录表
	 * @param tranDriverCancel
	 */
	public Integer addTranDriverCancel(TranDriverCancel tranDriverCancel);
	
	/**
	 * 根据车辆id得到订单状态为已调派或者载客中的订单
	 * @param id
	 * @return
	 */
	public List<TransactionInfo> getTransactionInfoList(Integer carid);
	
	/**
	 * 插入司机评价乘客
	 * @param driverEvaluation
	 * @return
	 */
	public Integer insertDriverEvaluation(DriverEvaluation driverEvaluation);
	
}
