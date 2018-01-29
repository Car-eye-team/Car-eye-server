/**
* Description: car-eye车辆管理平台
* 文件名：ServiceWinService.java
* 版本信息：1.0
* 日期：2015-1-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.taxi.api.service;

import java.util.List;
import java.util.Map;

import com.careye.api.domain.CustomerEvaluation;
import com.careye.taxi.domain.CarInfo;
import com.careye.taxi.domain.CarStatus;
import com.careye.taxi.domain.DriverData;
import com.careye.taxi.domain.DriverInfo;
import com.careye.taxi.domain.DriverListInfo;
import com.careye.taxi.domain.MultiMedia;
import com.careye.taxi.domain.ServiceLicense;
import com.careye.taxi.domain.ShunfengOrder;
import com.careye.taxi.domain.ShunfengPassengers;
import com.careye.taxi.domain.TranCustomerCancel;
import com.careye.taxi.domain.Transaction;
import com.careye.taxi.domain.VehicleInfo;




/**
 * @项目名称：OCS
 * @类名称：TaxiService
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-1-20 上午11:39:08
 * @修改人：zhangrong
 * @修改时间：2015-1-20 上午11:39:08
 * @修改备注：
 * @version 1.0
 */
public interface TaxiService {
	
	/**
	 * 保存订单信息
	 * @param transaction
	 * @return
	 */
	public Integer addTransaction(Transaction transaction);
	
	/**
	 * 更新订单信息
	 * @param transaction
	 * @return
	 */
	public Integer updateTransaction(Transaction transaction);
	
	/**
	 * 根据订单ID获取司机信息
	 * @param orderid
	 * @return
	 */
	public DriverInfo getDriverInfo(String orderid);
	
	/**
	 * 更新订单信息
	 * @param transaction
	 * @return
	 */
	public Integer updateTransactionUpTaix(Transaction transaction);
	
	/**
	 * 更新订单支付信息
	 * @param transaction
	 * @return
	 */
	public Integer updateOrderPaymentInfo(Transaction transaction);
	
	
	/**
	 * 根据订单ID获取订单详情
	 * @param orderid
	 * @return
	 */
	public Transaction getOrderdetail(String orderid);
	public Transaction getSimbleOrderdetail(String orderid);
	
	/**
	 * 根据订单号更新支付状态
	 * @param orderid
	 * @return
	 */
	public Integer updateOrderPayStatus(String orderid);
	
	
	/**
	 * 根据订单号更新交易状态
	 * @param orderid
	 * @return
	 */
	public Integer updateOrderTradeStatus(Transaction transaction);
	
	/**
	 * 获取未处理订单列表
	 * @param transaction
	 * @return
	 */
	public List<Transaction> getUntreatedOrderList(Transaction transaction);
	
	
	/**
	 * 根据订单号更新订单状态
	 * @param orderid
	 * @return
	 */
	public Integer updateTransactionStatus(Transaction transaction);
	
	/**
	 * 根据商户唯一订单号更新交易状态
	 * @param orderid
	 * @return
	 */
	public Integer updateTransactionStatusByOuttradeno(Transaction transaction);

	/**
	 * 查询直径范围内车辆
	 * @param map
	 * @return
	 */
	public List<VehicleInfo> queryVehicleInfo(Map map);

	/**
	 * 根据车牌号获取驾驶员信息
	 * @param trim
	 * @return
	 */
	public DriverInfo queryDriverInfo(String carnumber);
	
	/**
	 * 根据车牌号、司机手机号获取驾驶员信息
	 * @param trim
	 * @return
	 */
	public DriverInfo queryDriverInfoByMap(Map<String,String> map);
	
	/**
	 * 根据车牌号获取当班司机信息
	 * @param trim
	 * @return
	 */
	public DriverInfo getCurrentWorkDriverInfo(String carnumber);

	/**
	 * 根据车牌号获取车载手机号码
	 * @param vname
	 * @return
	 */
	public String getPhoneByCarnumber(String carnumber);
	
	
	/** 
	 * 判断是否存在订单信息
	 * @param orderid
	 * @return
	 */
	public Integer isTcCancelExitsOrderid(String orderid);
	
	/** 
	 * 添加到约车业务乘客取消表
	 * @return
	 */
	public Integer addTranCustomerCancel(TranCustomerCancel tranCustomerCancel);

	/**
	 * 根据车牌号得到当班司机服务监督卡号
	 * @param vname
	 * @return
	 */
	public String getDrivercodeByCarnumber(String carnumber);
	
	/**
	 * 分页获取订单列表
	 */
	public Map getTransactionList(int currentPage,int everyPage, Map map);
	
	/**
	 * 添加客户评价
	 */
	public int addCustomerEvaluation(CustomerEvaluation customerEvaluation);
	
	/**
	 * 根据条件得到服务次数
	 */
	public int getServiceCount(Map<String,String> map);
	
	/**
	 * 根据条件得到服务次数
	 */
	public int getGoodCount(Map<String,String> map);
	
	/**
	 * 根据条件得到服务次数
	 */
	public DriverData getLevel(Map<String,String> map);
	
	/**
	 * 获得司机被评价列表
	 */
	public Map getDriverDataMap(int currentPage,int everyPage, Map<String,String> map);
	
	/**
	 * 获得客户评价列表
	 */
	public Map getCustomerDataMap(int currentPage,int everyPage, Map<String,String> map);
	
	/**
	 * 根据设备号查询车辆id
	 */
	public Integer getCaridByTerminal(String terminal);
	
	/**
	 * 根据车牌号查询车辆id
	 */
	public Integer getCaridByCarnumber(String carnumber);
	
	/**
	 * 添加顺风车订单
	 */
	public int addShunfengOrder(ShunfengOrder shunfengOrder);
	
	/**
	 * 添加拼车乘客
	 */
	public int addShunfengPassengers(ShunfengPassengers shunfengPassengers);
	
	/**
	 * 历史订单列表
	 */
	public Map getHistoryOrderList(int currentPage,int everyPage, Map map);
	
	/**
	 * 订单乘客列表
	 */
	public List getOrderPassengersList(Map map);
	
	/**
	 * 得到车辆运行轨迹经纬度点集合
	 * @param paramsMap 可以传入参数stime,etime，起止时间,车辆id
	 * @return
	 */
	public List<CarStatus> findCarTrackPointList(Map map);
	
	/**
	 * 根据订单号查询车辆id
	 */
	public Integer getCaridByOrderid(String orderid);
	
	/**
	 * 根据驾驶员id查询服务证信息
	 */
	public ServiceLicense findServiceLicenseByDriverid(String driverid);
	
	/**
	 * 获取车辆信息
	 * @param paramsMap
	 * @return
	 */
	public CarInfo getCarInfo(Map paramsMap);
	
	/**
	 * 添加多媒体数据
	 */
	public Integer addMultiMedia(MultiMedia multiMedia);
	
	
	/**
	 * 根据车牌号获取司机信息列表
	 */
	public List findDriverInfoList(String carnumber);
	
	/**
	 * 根据车牌号查询司机是否存在
	 */
	 public Integer isExistDriver(DriverListInfo driverListInfo);
	
	/**
	 * 修改当班司机
	 */
	public Integer updateDutyDriver(DriverListInfo driverListInfo);
	
}



