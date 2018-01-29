/**
 * Description: 出租车系统
 * 文件名：CallHandleBusiness.java
 * 版本信息：1.0
 * 日期：2015-3-23
 * Copyright car-eye车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.mq.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.careye.car.domain.CarDriver;
import com.careye.car.domain.CarInfo;
import com.careye.car.domain.CarState;
import com.careye.car.domain.DriverEvaluation;
import com.careye.car.domain.TranDriverCancel;
import com.careye.car.domain.TransactionInfo;
import com.careye.constant.Constant;
import com.careye.constant.ServiceConfig;
import com.careye.mq.domain.Protocol;
import com.careye.mq.utils.CallHandleUtil;
import com.careye.mq.utils.HandleUtil;
import com.careye.utils.DateUtil;
import com.careye.utils.StringUtils;

/**
 * @项目名称：TAXISERVER
 * @类名称：CallHandleBusiness
 * @类描述：电召业务处理
 * @创建人：zr
 * @创建时间：2015-3-23 下午03:07:21
 * @修改人：zr
 * @修改时间：2015-3-23 下午03:07:21
 * @修改备注：
 * @version 1.0
 */
public class CallHandleBusiness {

	private static Logger logger = Logger.getLogger(CallHandleBusiness.class);

	/**
	 * 召标发送车辆
	 * @param protocol
	 * @param message
	 */
	public static void zbSendCar(Protocol protocol,String message){

		try {
			List<TransactionInfo> list = new ArrayList<TransactionInfo>();  
			String terarr [] = protocol.getTers().split(",");
			for(String ter : terarr){  
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("terminal", ter);
				CarInfo carInfo = ServiceConfig.carService.getCarInfoByTer(paramsMap);
				
				if(carInfo != null){
					TransactionInfo transactionInfo = new TransactionInfo();
					transactionInfo.setOrderid(protocol.getOrderid());
					transactionInfo.setCarid(carInfo.getId());
					transactionInfo.setTerminal(ter);
					transactionInfo.setCarnumber(carInfo.getCarnumber());
					transactionInfo.setCreatetime(DateUtil.getSQLDate());
		    		list.add(transactionInfo); 
		    		
		    		//根据车辆id更改车辆状态表中调度状态为2调度中
		    		CarState carState = new CarState();
		    		carState.setCarid(carInfo.getId());
		    		carState.setDispaterstatus(2);
		    		ServiceConfig.transactionService.updateCarState(carState);
				}
		    }  
			//批量插入记录至约车业务抢答表
			ServiceConfig.transactionService.insertBitchTransactionAnswer(list);
			
			//更改约车业务表业务状态为调派中
			TransactionInfo tInfo = new TransactionInfo();
			tInfo.setStarttime(DateUtil.getSQLDate());
			tInfo.setOrderid(protocol.getOrderid());
			tInfo.setStatus(1);
			ServiceConfig.transactionService.updateTransactionInfo(tInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 处理订单抢答
	 * @param protocol
	 * @param message
	 */
	public static synchronized void orderAnswer(Protocol protocol,String message,CarInfo carInfo){
		try {
			if(carInfo != null){
				
				//判断是否为第一个抢答(根据订单ID获取抢答记录总数是否等于1)
				int count = ServiceConfig.transactionService.getTransactionInfoAnswerCount(protocol.getOrderid());
				
				//第一个抢答
				if(count == 0){
					//获取订单状态
					Integer status = ServiceConfig.transactionService.getOrderStatus(protocol.getOrderid());
					if(status != null){	
						if(status == 1){//有效，调派中
				    		
				    		//首先根据订单号把所有调派车辆调度状态改为未调度
				    		ServiceConfig.transactionService.updateCarStateByOrderid(protocol.getOrderid());
				    		
				    		//然后把抢答车辆，根据车辆id更改车辆状态表中调度状态为3已调度
				    		CarState carState = new CarState();
				    		carState.setCarid(carInfo.getId());
				    		carState.setDispaterstatus(3);
				    		ServiceConfig.transactionService.updateCarState(carState);
				    		
							//更改约车业务表
							TransactionInfo tInfo = new TransactionInfo();
							tInfo.setOrderid(protocol.getOrderid());
							tInfo.setStatus(2);
							tInfo.setCarid(carInfo.getId());
							tInfo.setResstatus(1);
							tInfo.setAnswertime(DateUtil.numToDate(protocol.getTime()));
							
							CarDriver carDriver = ServiceConfig.transactionService.getCurrentDriver(carInfo.getId());
							if(carDriver == null){
								carDriver = new CarDriver();
							}
							tInfo.setDrivercode(carDriver.getDrivercode());
							
							ServiceConfig.transactionService.updateTransactionInfo(tInfo);
							
							//更新抢答表中标状态
							tInfo.setZbstatus(2);
							int re = ServiceConfig.transactionService.updateTransactionAnswer(tInfo);
						
							//发送订单确认信息 0x00 失败；0x01 成功  0x02 电召已被抢答
							orderSuccessful(protocol,message,carInfo,1);
							
							String drivername = StringUtils.isEmty(carDriver.getDrivername())?"":carDriver.getDrivername();
							String driverphone = StringUtils.isEmty(carDriver.getPhone())?"":carDriver.getPhone();
							
							//发送电召抢答通知
							if(protocol.getOrderid().startsWith(Constant.APP_ORDER+"")){
								
								String phone = ServiceConfig.transactionService.getTransactionInfo(protocol.getOrderid()).getPhone();
								
								HandleUtil.orderAnswerNotice(protocol.getOrderid(), 2, carInfo.getCarnumber(),phone,carInfo.getTerminal(),  
										drivername, driverphone, protocol.getTime(),carDriver.getLng(),carDriver.getLat());
							}
							
							
						}else if(status == 3){ //取消订单
							CallHandleUtil.centralProcessResults(protocol.getTerminal(), protocol.getOrderid());
						}
					}
				}else{
					//发送订单确认信息 0x00 失败；0x01 成功  0x02 电召已被抢答
					orderSuccessful(protocol,message,carInfo,2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(carInfo != null){
				//发送订单确认信息 0x00 失败；0x01 成功  0x02 电召已被抢答
				orderSuccessful(protocol,message,carInfo,0);
			}
		}
	}
	/**
	 * 司机取消订单
	 * @param protocol
	 * @param message
	 */
	public static void driverCancelOrderAnswer(Protocol protocol,String message,CarInfo carInfo){
		
		try {
							
				//首先根据订单号把所有调派车辆调度状态改为未调度
				ServiceConfig.transactionService.updateCarStateByCarid(carInfo.getId());
				
				//更改约车业务表
				TransactionInfo tInfo = new TransactionInfo();
				tInfo.setOrderid(protocol.getOrderid());
				tInfo.setStatus(3);
				ServiceConfig.transactionService.updateTransactionInfo(tInfo);
				
				//插入司机取消订单记录表
				TranDriverCancel tranDriverCancel = new TranDriverCancel();
				tranDriverCancel.setCanceltime(DateUtil.getSQLDate());
				tranDriverCancel.setOrderid(protocol.getOrderid());
				tranDriverCancel.setCarid(carInfo.getId());
				tranDriverCancel.setCarnumber(carInfo.getCarnumber());
				//得到当班司机-司机代码
				CarDriver carDriver = ServiceConfig.transactionService.getCurrentDriver(carInfo.getId());
				if(carDriver != null){
					tranDriverCancel.setDriverid(carDriver.getId());
				}
				
				tranDriverCancel.setWy(0);
				String remark = "";
				if(protocol.getReason() ==1){
					remark = "道路拥堵";
				}else if(protocol.getReason() ==2){
					remark = "车辆故障";
				}else if(protocol.getReason() ==3){
					remark = "身体不舒服";
				}else if(protocol.getReason() ==4){
					remark = "其他";
				}
				tranDriverCancel.setRemark(remark);
				
				ServiceConfig.transactionService.addTranDriverCancel(tranDriverCancel);
							
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 订单状态更改
	 * @param protocol
	 * @param message
	 */
	public static void chageOrderStatusAnswer(Protocol protocol,String message,CarInfo carInfo){
		
		try {
				
				//更改约车业务表
				TransactionInfo tInfo = new TransactionInfo();
				tInfo.setOrderid(protocol.getOrderid());
				tInfo.setStatus(protocol.getOrderstatus());
				ServiceConfig.transactionService.updateTransactionInfo(tInfo);
				
				String phone = ServiceConfig.transactionService.getTransactionInfo(protocol.getOrderid()).getPhone();
				
				//订单状态通知
				if(protocol.getOrderid().startsWith(Constant.APP_ORDER+"")){
					String phone1 = ServiceConfig.transactionService.getTransactionInfo(protocol.getOrderid()).getPhone();
					HandleUtil.orderStatusNotice(protocol.getOrderid(), protocol.getOrderstatus(),phone1);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给指定车辆发送中标信息
	 * @param message
	 * @param protocol
	 */
	public static void orderSuccessful(Protocol protocol,String message,CarInfo carInfo,int result){

		try {
			//根据订单号获取约车信息
			TransactionInfo transactionInfo = ServiceConfig.transactionService.getTransactionInfo(protocol.getOrderid());
			if(transactionInfo != null){

				StringBuffer contents = new StringBuffer();
				contents.append(transactionInfo.getUsername());
				contents.append(" ");
				contents.append(transactionInfo.getSaddress());
				contents.append("至");
				contents.append(transactionInfo.getEaddress());

				//发送中标信息
				CallHandleUtil.orderSuccessful(protocol.getTerminal(), protocol.getOrderid(),transactionInfo.getUsername(),transactionInfo.getPhone(), carInfo.getCarnumber(),
				  contents.toString(), transactionInfo.getTratype(),transactionInfo.getTratype()==1?transactionInfo.getAppointmenttime():transactionInfo.getUsetime(),
				  transactionInfo.getSaddress(),transactionInfo.getEaddress(),"",result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 执行电召
	 * @param protocol
	 * @param message
	 */
	public static void orderExecute(Protocol protocol,String message,CarInfo carInfo){
		int result = 1;
		try {

			//根据订单号，更新约车业务状态
			TransactionInfo tInfo = new TransactionInfo();
			if(carInfo != null){
				tInfo.setProcessstatus(protocol.getResult());
				tInfo.setStatus(5);
				tInfo.setOrderid(protocol.getOrderid());
				tInfo.setCarpoolPersonNum(Integer.parseInt(protocol.getNumber()));
				tInfo.setEndtime(DateUtil.numToDate(protocol.getCompletiontime()));
				ServiceConfig.transactionService.updateTransactionInfo(tInfo);
				
				//抢答车辆，根据车辆id更改车辆状态表中调度状态为1未调度
	    		CarState carState = new CarState();
	    		carState.setCarid(carInfo.getId());
	    		carState.setDispaterstatus(1);
	    		ServiceConfig.transactionService.updateCarState(carState);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}

	}
	
	
	/**
	 * 司机评价乘客
	 * @param protocol
	 * @param message
	 */
	public static void driverEvaluation(Protocol protocol,String message){

		try {
			DriverEvaluation driverEvaluation = new DriverEvaluation();
			driverEvaluation.setOrderid(protocol.getOrderid());
			driverEvaluation.setSlevel(protocol.getLevel());
			driverEvaluation.setContent(protocol.getContent());
			driverEvaluation.setCreatetime(DateUtil.getSQLDate());
			//插入司机评价乘客
			ServiceConfig.transactionService.insertDriverEvaluation(driverEvaluation);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
