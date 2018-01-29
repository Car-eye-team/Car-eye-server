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

import com.careye.car.domain.CarDriver;
import com.careye.customer.domain.Customer;
import com.careye.statement.domain.AreaMore;
import com.careye.statement.domain.CarDriverCancel;
import com.careye.statement.domain.CarPosition;
import com.careye.statement.domain.CusBreach;
import com.careye.statement.domain.CusCancel;
import com.careye.statement.domain.DayuploadCount;
import com.careye.statement.domain.DrivingState;
import com.careye.statement.domain.TFC;
import com.careye.statement.domain.TerminalFunCount;
import com.careye.utils.SessionUtils;



/**
 * @项目名称：car-eye
 * @类名称：TranStatementService
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2014-5-22 上午10:00:03
 * @修改人：huangqin
 * @修改时间：2014-5-22 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public interface TranStatementService {
	
	/**
	 * 根据客户取消数统计
	 * @param carPosition
	 * @return
	 */
	public Map<Object, Object> findCusCancelPageList(final int currentPage,
			final int everyPage, CusCancel cusCancel);
	
	/**
	 * Excel 导出客户取消数统计
	 * 
	 * @param customer
	 * @return
	 */
	public List<Customer> getCusCancelList(CusCancel cusCancel);
	
  
	/**
	 * 根据司机取消数统计
	 * @param carPosition
	 * @return
	 */
	public Map<Object, Object> findCarDrviverCancelPageList(final int currentPage,
			final int everyPage, CarDriverCancel carDriverCancel);
	
	/**
	 * Excel 导出司机取消数统计
	 * 
	 * @param customer
	 * @return
	 */
	public List<Customer> getCarDriverCancelList(CarDriverCancel carDriverCancel);
	
	
	/**
	 * 根据客户违约数统计
	 * @param carPosition
	 * @return
	 */
	public Map<Object, Object> findCusBreachPageList(final int currentPage,
			final int everyPage, CusBreach cusBreach);
	
	/**
	 * Excel 导出客户违约数统计
	 * 
	 * @param customer
	 * @return
	 */
	public List<Customer> getCusBreachList(CusBreach cusBreach);
	
	
	/**
	 * 根据终端功能使用统计
	 * @param carPosition
	 * @return
	 */
	public Map<Object, Object> findTerminalFunCountPageList(int currentPage,
			int everyPage, TerminalFunCount terminalFunCount) ;
	
	/**
	 * Excel 导出终端功能使用统计
	 * 
	 * @param customer
	 * @return
	 */
	public List<TerminalFunCount> getTerminalFunCountList(TerminalFunCount terminalFunCount) ;
	/**
	 * word 导出终端功能使用统计
	 * getBname  获取企业名称
	 * 
	 * getTFCWord   导出列表word
	 */
	public String getBname(int blocid);
	public List<TerminalFunCount> getTFCWord(TerminalFunCount terminalFunCount);
	/**
	 * 日上报统计
	 * @param carPosition
	 * @return
	 */
	public Map<Object, Object> findDayuploadList(int currentPage,
			int everyPage, DayuploadCount dayuploadCount) ;
	
	/**
	 * Excel 导出日上报统计
	 * 
	 * @param customer
	 * @return
	 */
	public List<DayuploadCount> exportDayupload(DayuploadCount dayuploadCount) ;
	
	/**
	 * selTFC  终端功能使用次数统计总数
	 */
	public TFC selTFC(TerminalFunCount terminalFunCount) ;
}
