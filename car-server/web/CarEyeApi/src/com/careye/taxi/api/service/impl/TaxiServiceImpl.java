/**
* Description: car-eye车辆管理平台
* 文件名：ServiceWinServiceImpl.java
* 版本信息：1.0
* 日期：2015-1-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.taxi.api.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.api.domain.CustomerEvaluation;
import com.careye.base.service.GenericService;
import com.careye.taxi.api.service.TaxiService;
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
import com.careye.utils.DateUtil;



/**
 * @项目名称：OCS
 * @类名称：TaxiServiceImpl
 * @类描述：飞嘀打车操作taxi数据库
 * @创建人：zhangrong
 * @创建时间：2015-1-20 上午11:39:33
 * @修改人：zhangrong
 * @修改时间：2015-1-20 上午11:39:33
 * @修改备注：
 * @version 1.0
 */
public class TaxiServiceImpl extends GenericService implements TaxiService{

	
	/**
	 * 保存订单信息
	 * @param transaction
	 * @return
	 */
	@Override
	public Integer addTransaction(Transaction transaction) {
		transaction.setCreatetime(DateUtil.getSQLDate());
		transaction.setStarttime(DateUtil.getSQLDate());
		transaction.setStatus(0);
		if(transaction.getCalltype() == null){
			transaction.setCalltype(1);
		}
		return this.baseDao.save("oracle-TaxiSQL.addTransaction", transaction);
	}

	/**
	 * 更新订单信息
	 * @param transaction
	 * @return
	 */
	@Override
	public Integer updateTransaction(Transaction transaction) {
		return this.baseDao.update("oracle-TaxiSQL.updateTransaction", transaction);
	}

	/**
	 * 根据订单ID获取司机信息
	 * @param orderid
	 * @return
	 */
	@Override
	public DriverInfo getDriverInfo(String orderid) {
		return (DriverInfo) this.baseDao.queryForObject("oracle-TaxiSQL.getDriverInfo", orderid);
	}

	/**
	 * 更新订单信息
	 * @param transaction
	 * @return
	 */
	@Override
	public Integer updateTransactionUpTaix(Transaction transaction) {
		return this.baseDao.update("oracle-TaxiSQL.updateTransactionUpTaix", transaction);
	}

	/**
	 * 更新订单支付信息
	 * @param transaction
	 * @return
	 */
	@Override
	public Integer updateOrderPaymentInfo(Transaction transaction) {
		return this.baseDao.update("oracle-TaxiSQL.updateOrderPaymentInfo", transaction);
	}

	/**
	 * 根据订单ID获取订单详情
	 * @param orderid
	 * @return
	 */
	@Override
	public Transaction getOrderdetail(String orderid) {
		return (Transaction) this.baseDao.queryForObject("oracle-TaxiSQL.getOrderdetail", orderid);
	}
	public Transaction getSimbleOrderdetail(String orderid) {
		return (Transaction) this.baseDao.queryForObject("oracle-TaxiSQL.getSimbleOrderdetail", orderid);
	}

	/**
	 * 根据订单号更新支付状态
	 * @param orderid
	 * @return
	 */
	@Override
	public Integer updateOrderPayStatus(String orderid) {
		return this.baseDao.update("oracle-TaxiSQL.updateOrderPayStatus", orderid);
	}
	
	/**
	 * 根据订单号更新交易状态
	 * @param orderid
	 * @return
	 */
	public Integer updateOrderTradeStatus(Transaction transaction){
		return this.baseDao.update("oracle-TaxiSQL.updateOrderTradeStatus", transaction);
	}

	/**
	 * 获取未处理订单列表
	 * @param transaction
	 * @return
	 */
	@Override
	public List<Transaction> getUntreatedOrderList(Transaction transaction) {
		return this.baseDao.queryForList("oracle-TaxiSQL.getUntreatedOrderList", transaction);
	}
	
	
	/**
	 * 根据订单号更新订单状态
	 * @param orderid
	 * @return
	 */
	@Override
	public Integer updateTransactionStatus(Transaction transaction) {
		return this.baseDao.update("oracle-TaxiSQL.updateTransactionStatus", transaction);
	}
	
	/**
	 * 根据商户唯一订单号更新交易状态
	 * @param orderid
	 * @return
	 */
	public Integer updateTransactionStatusByOuttradeno(Transaction transaction){
		return this.baseDao.update("oracle-TaxiSQL.updateTransactionStatusByOuttradeno", transaction);
	}
	
	/**
	 * 查询直径范围内车辆
	 * @param map
	 * @return
	 */
	public List<VehicleInfo> queryVehicleInfo(Map map){
		return this.baseDao.queryForList("oracle-TaxiSQL.queryVehicleInfo", map);
	}
	
	/**
	 * 根据车牌号获取驾驶员信息
	 * @param trim
	 * @return
	 */
	public DriverInfo queryDriverInfo(String carnumber){
		return (DriverInfo)this.baseDao.queryForObject("oracle-TaxiSQL.queryDriverInfo", carnumber);
	}
	
	/**
	 * 根据车牌号、司机手机号获取驾驶员信息
	 * @param trim
	 * @return
	 */
	public DriverInfo queryDriverInfoByMap(Map<String,String> map){
		return (DriverInfo)this.baseDao.queryForObject("oracle-TaxiSQL.queryDriverInfoByMap", map);
	}
	
	/**
	 * 根据车牌号获取当班司机信息
	 * @param trim
	 * @return
	 */
	public DriverInfo getCurrentWorkDriverInfo(String carnumber){
		return (DriverInfo)this.baseDao.queryForObject("oracle-TaxiSQL.getCurrentWorkDriverInfo", carnumber);
	}
	
	/**
	 * 根据车牌号获取车载手机号码
	 * @param vname
	 * @return
	 */
	public String getPhoneByCarnumber(String carnumber){
		return (String)this.baseDao.queryForObject("oracle-TaxiSQL.getPhoneByCarnumber", carnumber);
	}

	/** 
	 * 判断是否存在订单信息
	 * @param orderid
	 * @return
	 */
	@Override
	public Integer isTcCancelExitsOrderid(String orderid) {
		return (Integer) this.baseDao.queryForObject("oracle-TaxiSQL.isTcCancelExitsOrderid", orderid);
	}
	/** 
	 * 添加到约车业务乘客取消表
	 * @return
	 */
	@Override
	public Integer addTranCustomerCancel(TranCustomerCancel tranCustomerCancel) {
		
		return this.baseDao.save("oracle-TaxiSQL.addTranCustomerCancel", tranCustomerCancel);
	}

	/**
	 * 根据车牌号得到当班司机服务监督卡号
	 * @param vname
	 * @return
	 */
	public String getDrivercodeByCarnumber(String carnumber){
		return (String)this.baseDao.queryForObject("oracle-TaxiSQL.getDrivercodeByCarnumber", carnumber);
	}
	

	/**
	 * 保存司机信息
	 * @param driverInfo
	 */
	public Integer saveDriverInfo(DriverInfo driverInfo) {
		Integer driverid = queryPhoneIsExist(driverInfo.getPhone());
		if(driverid == null){
			driverInfo.setCreatetime(DateUtil.getSQLDate());
			return this.baseDao.save("oracle-TaxiSQL.addDriverInfo", driverInfo);
		}else{
			this.baseDao.update("oracle-TaxiSQL.updateDriverInfo", driverInfo);
		}
		return driverid;
	}

	/**
	 * 司机手机号是否存在,存在返回id
	 * @param phone
	 * @return
	 */
	public Integer queryPhoneIsExist(String phone) {
		return (Integer)this.baseDao.queryForObject("oracle-TaxiSQL.queryPhoneIsExist", phone);
	}

	/**
	 * 判断设备号(手机号)是否存在
	 * @param phone
	 * @return
	 */
	public boolean queryTerminalIsExist(String phone) {
		return (Integer)this.baseDao.queryForObject("oracle-TaxiSQL.queryTerminalIsExist", phone) > 0?true:false;
	}

	
	/**
	 * 创建车辆位置信息表
	 * @param tableName
	 */
	public void createCarPosTable(Map<String,String> map){
		//创建表
		this.baseDao.update("oracle-TaxiSQL.createCarPosTable", map.get("tableName"));
		
		//创建索引
		this.baseDao.update("oracle-TaxiSQL.createACCIndex", map);
		this.baseDao.update("oracle-TaxiSQL.createCARNUMBEIndex", map);
		this.baseDao.update("oracle-TaxiSQL.createCARSTATUSIndex", map);
		this.baseDao.update("oracle-TaxiSQL.createCREATETIMEIndex", map);
		this.baseDao.update("oracle-TaxiSQL.createGPSFLAGIndex", map);
		this.baseDao.update("oracle-TaxiSQL.createTERMINALIndex", map);
		this.baseDao.update("oracle-TaxiSQL.createZKSTATEIndex", map);
	}
	
	/**
	 * 分页获取订单列表
	 */
	@Override
	public Map getTransactionList(int currentPage,int everyPage, Map map){
		return this.baseDao.findPageList(
				"oracle-TaxiSQL.getTransactionList",
				"oracle-TaxiSQL.getTransactionListCount", map,
				currentPage, everyPage);
	}
	
	/**
	 * 添加客户评价
	 */
	public int addCustomerEvaluation(CustomerEvaluation customerEvaluation){
		customerEvaluation.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-TaxiSQL.addCustomerEvaluation", customerEvaluation);
	}
	
	/**
	 * 根据条件得到服务次数
	 */
	public int getServiceCount(Map<String,String> map){
		return (Integer) this.baseDao.queryForObject("oracle-TaxiSQL.getServiceCount", map);
	}
	
	/**
	 * 根据条件得到服务次数
	 */
	public int getGoodCount(Map<String,String> map){
		return (Integer) this.baseDao.queryForObject("oracle-TaxiSQL.getGoodCount", map);
	}
	
	/**
	 * 根据条件得到服务次数
	 */
	public DriverData getLevel(Map<String,String> map){
		return (DriverData) this.baseDao.queryForObject("oracle-TaxiSQL.getLevel", map);
	}
	
	/**
	 * 获得司机被评价列表
	 */
	public Map getDriverDataMap(int currentPage,int everyPage, Map<String,String> map){
		return this.baseDao.findPageList("oracle-TaxiSQL.getDriverDataList",
				"oracle-TaxiSQL.getDriverDataListCount", map, currentPage, everyPage);
	}
	
	/**
	 * 获得客户评价列表
	 */
	public Map getCustomerDataMap(int currentPage,int everyPage, Map<String,String> map){
		return this.baseDao.findPageList("oracle-TaxiSQL.getCustomerDataList",
				"oracle-TaxiSQL.getCustomerDataListCount", map, currentPage, everyPage);
	}
	
	/**
	 * 根据设备号查询车辆id
	 */
	public Integer getCaridByTerminal(String terminal){
		return (Integer) this.baseDao.queryForObject("oracle-TaxiSQL.getCaridByTerminal", terminal);
	}
	
	/**
	 * 根据车牌号查询车辆id
	 */
	public Integer getCaridByCarnumber(String carnumber){
		return (Integer) this.baseDao.queryForObject("oracle-TaxiSQL.getCaridByCarnumber", carnumber);
	}
	
	/**
	 * 添加顺风车订单
	 */
	public int addShunfengOrder(ShunfengOrder shunfengOrder){
		return this.baseDao.save("oracle-TaxiSQL.addShunfengOrder", shunfengOrder);
	}
	
	/**
	 * 添加拼车乘客
	 */
	public int addShunfengPassengers(ShunfengPassengers shunfengPassengers){
		int count = (Integer) this.baseDao.queryForObject("oracle-TaxiSQL.shunfengPassengersIsExist", shunfengPassengers);
		if(count > 0){
			return this.baseDao.update("oracle-TaxiSQL.updateShunfengPassengers", shunfengPassengers);
		}else{
			return this.baseDao.save("oracle-TaxiSQL.addShunfengPassengers", shunfengPassengers);
		}
	}
	
	/**
	 * 历史订单列表
	 */
	public List getHistoryOrderList(Map map){
		return this.baseDao.queryForList("oracle-TaxiSQL.getHistoryOrderList", map);
	}
	/**
	 * 历史订单列表
	 */
	public Map getHistoryOrderList(int currentPage,int everyPage, Map map){
		return this.baseDao.findPageList("oracle-TaxiSQL.getHistoryOrderList", 
				"oracle-TaxiSQL.getHistoryOrderListCount", map, currentPage, everyPage);
	}
	
	/**
	 * 订单乘客列表
	 */
	public List getOrderPassengersList(Map map){
		return this.baseDao.queryForList("oracle-TaxiSQL.getOrderPassengersList", map);
	}
	
	/**
	 * 得到车辆运行轨迹经纬度点集合
	 * @param paramsMap 可以传入参数stime,etime，起止时间,车辆id
	 * @return
	 */
	public List<CarStatus> findCarTrackPointList(Map map){
		return this.baseDao.queryForList("oracle-TaxiSQL.findCarTrackPointList", map);
	}
	
	/**
	 * 根据订单号查询车辆id
	 */
	public Integer getCaridByOrderid(String orderid){
		return (Integer) this.baseDao.queryForObject("oracle-TaxiSQL.getCaridByOrderid", orderid);
	}
	
	/**
	 * 根据驾驶员id查询服务证信息
	 */
	public ServiceLicense findServiceLicenseByDriverid(String driverid){
		return (ServiceLicense) this.baseDao.queryForObject("oracle-TaxiSQL.findServiceLicenseByDriverid", driverid);
	}
	
	/**
	 * 获取车辆信息
	 * @param paramsMap
	 * @return
	 */
	public CarInfo getCarInfo(Map paramsMap) {
		return (CarInfo) this.baseDao.queryForObject("oracle-TaxiSQL.getCarInfo", paramsMap);

	}
	
	/**
	 * 添加多媒体数据
	 */
	public Integer addMultiMedia(MultiMedia multiMedia){
		return this.baseDao.save("oracle-TaxiSQL.addMultiMedia", multiMedia);
	}
	
	/**
	 * 根据车牌号获取司机信息列表
	 */
	public List findDriverInfoList(String carnumber){
		return this.baseDao.queryForList("oracle-TaxiSQL.findDriverInfoList", carnumber);
	}
	
	/**
	 * 根据车牌号查询司机是否存在
	 */
	 public Integer isExistDriver(DriverListInfo driverListInfo){
		 return (Integer) this.baseDao.queryForObject("oracle-TaxiSQL.isExistDriver", driverListInfo);
	 }
	
	/**
	 * 修改当班司机
	 */
	public Integer updateDutyDriver(DriverListInfo driverListInfo){
		return this.baseDao.update("oracle-TaxiSQL.updateDutyDriver", driverListInfo);
	}
	
}










