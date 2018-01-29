/**
 * Description: car-eye车辆管理平台
 * 文件名：CarServiceImpl.java
 * 版本信息：1.0
 * 日期：2014-5-22
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.statement.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.customer.domain.Customer;
import com.careye.statement.domain.CarDriverCancel;
import com.careye.statement.domain.CusBreach;
import com.careye.statement.domain.CusCancel;
import com.careye.statement.domain.DayuploadCount;
import com.careye.statement.domain.TFC;
import com.careye.statement.domain.TerminalFunCount;
import com.careye.statement.service.TranStatementService;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：car-eye
 * @类名称：TranStatementServiceImpl
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2014-5-22 上午10:05:25
 * @修改人：huangqin
 * @修改时间：2014-5-22 上午10:05:25
 * @修改备注：
 * @version 1.0
 */
public class TranStatementServiceImpl extends GenericService implements
		TranStatementService {

	private SysOperateLogService logService;

	/**
	 * 1
	 */
	@Override
	public Map<Object, Object> findCusCancelPageList(int currentPage,
			int everyPage, CusCancel cusCancel) {
		logService.addLogInfo(SessionUtils.getUserId(), "查询客户取消数统计记录", 5);
		return this.baseDao.findPageList(
				"oracle-transtatementSQL.findCusCancelPageList",
				"oracle-transtatementSQL.findCusCancelPageListCount",
				cusCancel, currentPage, everyPage);
	}

	/**
	 * 2
	 */
	@Override
	public Map<Object, Object> findCarDrviverCancelPageList(int currentPage,
			int everyPage, CarDriverCancel carDriverCancel) {
		logService.addLogInfo(SessionUtils.getUserId(), "查询司机取消数统计记录", 5);

		return this.baseDao.findPageList(
				"oracle-transtatementSQL.findCarDrviverCancelPageList",
				"oracle-transtatementSQL.findCarDrviverCancelPageListCount",
				carDriverCancel, currentPage, everyPage);
	}

	/**
	 * 3
	 */
	@Override
	public Map<Object, Object> findCusBreachPageList(int currentPage,
			int everyPage, CusBreach cusBreach) {
		logService.addLogInfo(SessionUtils.getUserId(), "查询客户违约数统计记录", 5);
		return this.baseDao.findPageList(
				"oracle-transtatementSQL.findCusBreachPageList",
				"oracle-transtatementSQL.findCusBreachPageListCount",
				cusBreach, currentPage, everyPage);
	}

	/**
	 * 4
	 */
	@Override
	public List<Customer> getCusCancelList(CusCancel cusCancel) {
		logService.addLogInfo(SessionUtils.getUserId(), "导出客户取消数统计报表记录", 4);
		return this.baseDao.queryForList(
				"oracle-transtatementSQL.findCusCancelPageList", cusCancel);
	}

	/**
	 * 5
	 */
	@Override
	public List<Customer> getCarDriverCancelList(CarDriverCancel carDriverCancel) {
		logService.addLogInfo(SessionUtils.getUserId(), "导出司机取消数统计记录", 4);
		return this.baseDao.queryForList(
				"oracle-transtatementSQL.findCarDrviverCancelPageList",
				carDriverCancel);
	}

	/**
	 * 6
	 */
	@Override
	public List<Customer> getCusBreachList(CusBreach cusBreach) {
		logService.addLogInfo(SessionUtils.getUserId(), "导出客户违约数统计记录", 4);
		return this.baseDao.queryForList(
				"oracle-transtatementSQL.findCusBreachPageList", cusBreach);
	}

	/**
	 * 7
	 */
	@Override
	public Map<Object, Object> findTerminalFunCountPageList(int currentPage,
			int everyPage, TerminalFunCount terminalFunCount) {
		logService.addLogInfo(SessionUtils.getUserId(), "查询终端功能使用数统计记录", 5);
		return this.baseDao.findPageList(
				"oracle-transtatementSQL.findTerminalFunCountPageList",
				"oracle-transtatementSQL.findTerminalFunCountPageListCount",
				terminalFunCount, currentPage, everyPage);
	}

	/**
	 * 8
	 */
	@Override
	public List<TerminalFunCount> getTerminalFunCountList(
			TerminalFunCount terminalFunCount) {
		logService.addLogInfo(SessionUtils.getUserId(), "导出终端功能使用统计记录", 4);
		return this.baseDao.queryForList(
				"oracle-transtatementSQL.findTerminalFunCountPageList",
				terminalFunCount);
	}

	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	/**
	 * exportDayupload TODO
	 * 
	 * @param dayuploadCount
	 * @return
	 * @see com.careye.statement.service.TranStatementService#exportDayupload(com.careye.statement.domain.DayuploadCount)
	 */
	
	public List<DayuploadCount> exportDayupload(DayuploadCount dayuploadCount) {
		logService.addLogInfo(SessionUtils.getUserId(), "导出终端日上报统计记录", 4);
		return this.baseDao.queryForList(
				"oracle-transtatementSQL.findDayuploadList",
				dayuploadCount);
	}

	/**
	 * findDayuploadList TODO
	 * 
	 * @param currentPage
	 * @param everyPage
	 * @param dayuploadCount
	 * @return
	 * @see com.careye.statement.service.TranStatementService#findDayuploadList(int,
	 *      int, com.careye.statement.domain.DayuploadCount)
	 */
	@Override
	public Map<Object, Object> findDayuploadList(int currentPage,
			int everyPage, DayuploadCount dayuploadCount) {
		logService.addLogInfo(SessionUtils.getUserId(), "查询终端日上报统计记录", 5);
		return this.baseDao.findPageList(
				"oracle-transtatementSQL.findDayuploadList",
				"oracle-transtatementSQL.findDayuploadListCount",
				dayuploadCount, currentPage, everyPage);
	}

	/**
	 * selTFC  终端功能使用次数统计总数
	 */
	public TFC selTFC(TerminalFunCount terminalFunCount){
		// TODO Auto-generated method stub
		return (TFC) this.baseDao.queryForObject("oracle-transtatementSQL.selTFC",terminalFunCount);
	}

	/**
	 * word 导出终端功能使用统计
	 * getBname  获取企业名称
	 * getTFCWord   导出列表word
	 */
	public String getBname(int blocid) {
		// TODO Auto-generated method stub
		return (String) this.baseDao.queryForObject("oracle-evaluateCountSQL.selBlocname", blocid);
	}
	public List<TerminalFunCount> getTFCWord(TerminalFunCount terminalFunCount) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-transtatementSQL.getTFCWord",terminalFunCount);
	}

}
