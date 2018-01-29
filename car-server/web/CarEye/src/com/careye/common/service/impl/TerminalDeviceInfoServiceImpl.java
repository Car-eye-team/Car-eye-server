/**
* Description: 多森56taxi物流平台
* 文件名：TerminalDeviceInfoServiceImpl.java
* 版本信息：1.0
* 日期：2013-8-7
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.common.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.action.TreeDomain;
import com.careye.base.service.GenericService;
import com.careye.car.domain.CarInfo;
import com.careye.common.domain.CarDetail;
import com.careye.common.domain.Carrealtime;
import com.careye.common.domain.MapInfo;
import com.careye.common.domain.OperaTimeAnalysis;
import com.careye.common.domain.PositionInfo;
import com.careye.common.domain.TerminalHisPosition;
import com.careye.common.domain.TerminalParameter;
import com.careye.common.domain.TerminalPositionInfo;
import com.careye.common.service.SysOperateLogService;
import com.careye.common.service.TerminalDeviceInfoService;
import com.careye.monitor.domain.OnlineReport;
import com.careye.transaction.domain.Transaction;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：56taxi
 * @类名称：TerminalDeviceInfoServiceImpl
 * @类描述：终端信息接口实现
 * @创建人：zr
 * @创建时间：2013-8-7 下午01:07:44
 * @修改人：zr
 * @修改时间：2013-8-7 下午01:07:44
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class TerminalDeviceInfoServiceImpl extends GenericService implements TerminalDeviceInfoService {
	
	private SysOperateLogService  logService;
	
	
	
	/**根据车牌号查询最新订单信息**/
	public Transaction queryLastOrderInfo(String carnumber) {
		return (Transaction) this.baseDao.queryForObject("oracle-transactionSQL.queryLastOrderInfo",carnumber);
	}
	
	/**
	 * word 企业车辆在线向情况导出 获取企业名称
	 * getBname
	 */
	public String getBname(int blocid) {
		// TODO Auto-generated method stub
		return (String) this.baseDao.queryForObject("oracle-OnlineReportSQL.getBname", blocid);
	}
	/**
	 * word 企业车辆在线向情况导出 获取离线在线车辆数量
	 * getTpi
	 */
	public TerminalPositionInfo getTpi(TerminalPositionInfo terminalPositionInfo) {
		return (TerminalPositionInfo) this.baseDao.queryForObject("oracle-terminalDeviceInfoSQL.getTpi", terminalPositionInfo);
	}
	
	/**
	 *  word 企业车辆在线向情况导出 获取离线车辆明细
	 * getTpiList1
	 */
	public List<TerminalPositionInfo> getTpiList1(TerminalPositionInfo terminalPositionInfo) {
		return this.baseDao.queryForList("oracle-terminalDeviceInfoSQL.getTpiList1",terminalPositionInfo);
	}
	
	/**
	 *  word 企业车辆在线向情况导出 获取在线车辆明细
	 * getTpiList2
	 */
	public List<TerminalPositionInfo> getTpiList2(TerminalPositionInfo terminalPositionInfo) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-terminalDeviceInfoSQL.getTpiList2",terminalPositionInfo);
	}

	/**
	 * 根据终端号码查询位置信息表中是否存在记录
	 * @param terminalnum
	 * @return
	 */
	@Override
	public Integer getPositionCount(String terminal) {
		return (Integer) this.baseDao.queryForObject("oracle-terminalDeviceInfoSQL.getPositionCount", terminal);
	}

	/**
	 * 添加终端设备位置信息
	 * @param terminalPositionInfo
	 * @return
	 */
	@Override
	public Integer addTerminalPositionInfo(
			TerminalPositionInfo terminalPositionInfo) {
		terminalPositionInfo.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-terminalDeviceInfoSQL.addTerminalPositionInfo", terminalPositionInfo);
	}
	
	/**
	 * 更新终端位置信息
	 * @param terminalnum
	 * @return
	 */
	@Override
	public Integer updateTerminalPositionInfo(TerminalPositionInfo terminalPositionInfo) {
		return this.baseDao.update("oracle-terminalDeviceInfoSQL.updateTerminalPositionInfo", terminalPositionInfo);
	}

	/**
	 * 插入位置信息至终端历史位置表中
	 * @param terminalHisPosition
	 * @return
	 */
	@Override
	public Integer insertTerminalHisPosition(TerminalHisPosition terminalHisPosition) {
		return this.baseDao.save("oracle-terminalDeviceInfoSQL.insertTerminalHisPosition", terminalHisPosition);
	}
	

	/**
	 * 终端位置信息列表
	 * @param 
	 * @return
	 */
	public List<TerminalPositionInfo> terminalPositionList(TerminalPositionInfo terminalPositionInfo){
		return this.baseDao.queryForList("oracle-terminalDeviceInfoSQL.terminalPositionList",terminalPositionInfo);
	}

	/**
	 * 车辆分布列表
	 * @param currentPage
	 * @param everyPage
	 * @param terminalPositionInfo
	 * @return
	 */
	@Override
	public Map getCarLocationList(int currentPage, int everyPage,
			TerminalPositionInfo terminalPositionInfo) {
		return this.baseDao.findPageList("oracle-terminalDeviceInfoSQL.getCarLocationList",
				"oracle-terminalDeviceInfoSQL.getCarLocationListCount", terminalPositionInfo,currentPage,everyPage);
	}
	/**
	 * Excel车辆分布列表
	 * @param terminalPositionInfo
	 * @return
	 */
	public List<TerminalPositionInfo> getCarLocationList(TerminalPositionInfo terminalPositionInfo){
		return  this.baseDao.queryForList("oracle-terminalDeviceInfoSQL.getCarLocationList", terminalPositionInfo);
	}
	
	
	/**
	 * 根据车牌号获取车辆最后位置信息
	 * @param carnumber
	 * @return
	 */
	@Override
	public TerminalPositionInfo getTerminalPositionInfo(String carnumber) {
		return (TerminalPositionInfo) this.baseDao.queryForObject("oracle-terminalDeviceInfoSQL.getTerminalPositionInfo", carnumber);
	}
	
	/**
	 * 车辆下拉列表
	 * @param 
	 * @return
	 */
	public List<CarInfo> selectCarList(CarInfo carInfo){
		return this.baseDao.queryForList("oracle-terminalDeviceInfoSQL.selectCarList",carInfo);
	}
	
	/**查询组织机构下面车辆状态位置信息列表**/
	public List<PositionInfo> loadCarPosByDept(TreeDomain baseDomain){
		return this.baseDao.queryForList("oracle-terminalDeviceInfoSQL.loadCarPosByDept", baseDomain);
	}
	
	/**根据车牌号查询车辆状态位置信息**/
	public MapInfo queryCarDetail(String carnumber){
		logService.addLogInfo(SessionUtils.getUserId(), "查询车辆位置:车牌号:"+carnumber,5);
		return (MapInfo)this.baseDao.queryForObject("oracle-terminalDeviceInfoSQL.queryCarDetail", carnumber);
	}
	
	/**根据carid查询车辆状态位置信息**/
	public MapInfo queryCarDetail(Integer carid){
		return (MapInfo)this.baseDao.queryForObject("oracle-terminalDeviceInfoSQL.queryCarDetailByCarid", carid);
	}
	
	/**根据车牌号查询车辆位置以及资料信息**/
	public PositionInfo queryCarTerminalDetail(String carnumber){
		logService.addLogInfo(SessionUtils.getUserId(), "查询车辆详细信息:车牌号:"+carnumber,5);
		return (PositionInfo)this.baseDao.queryForObject("oracle-terminalDeviceInfoSQL.queryCarTerminalDetail", carnumber);
	}
	
	/**根据车牌号查询车辆档案信息**/
	public CarInfo queryCarArchiveDetail(String carnumber){
		return (CarInfo)this.baseDao.queryForObject("oracle-terminalDeviceInfoSQL.queryCarArchiveDetail", carnumber);
	}
	
	/**读取车辆参数信息**/
	public CarDetail readCarParameter(String carnumber){
		return (CarDetail)this.baseDao.queryForObject("oracle-terminalDeviceInfoSQL.readCarParameter", carnumber);
	}
	/**
	 * 车辆自动化列表
	 * @param currentPage
	 * @param everyPage
	 * @param terminalPositionInfo
	 * @return
	 */
	public Map getCarAutoList(final int currentPage, final int everyPage,TerminalPositionInfo terminalPositionInfo){
		return this.baseDao.findPageList("oracle-terminalDeviceInfoSQL.getCarAutoList",
				"oracle-terminalDeviceInfoSQL.getCarAutoListCount", terminalPositionInfo,currentPage,everyPage);
	}
	
	/**
	 * 导出车辆分布列表
	 * @return
	 */
	public List<TerminalPositionInfo> exportTerminalPosition (TerminalPositionInfo terminalPositionInfo){
		return this.baseDao.queryForList("oracle-terminalDeviceInfoSQL.exportTerminalPosition", terminalPositionInfo);
	}
	
	/**
	 * 导出车辆自动化列表
	 * @return
	 */
	public List<TerminalPositionInfo> exportCarAuto (Map<String, String> paramsMap){
		return this.baseDao.queryForList("oracle-terminalDeviceInfoSQL.exportCarAuto", paramsMap);
	}

	/**
	 * 根据设备号删除最后位置标中的数据
	 * @param terminal
	 * @return
	 */
	@Override
	public Integer deleteTerminalPosition(String terminal) {
		return this.baseDao.delete("oracle-terminalDeviceInfoSQL.deleteTerminalPosition", terminal);
	}

	/**
	 * 根据车牌号删除最后位置标中的数据
	 * @param carnumber
	 * @return
	 */
	@Override
	public Integer deleteTerminalPositionCarNumber(String carnumber) {
		return this.baseDao.delete("oracle-terminalDeviceInfoSQL.deleteTerminalPositionCarNumber", carnumber);
	}

	/**
	 * 根据车辆ID更新车辆最后位置信息
	 * @param carid
	 * @return
	 */
	@Override
	public Integer updateTerminalPosition(CarInfo carInfo) {
		return this.baseDao.update("oracle-terminalDeviceInfoSQL.updateTerminalPosition", carInfo);
	}

	/**
	 * 根据车辆ID删除最后位置信息
	 * @param carid
	 * @return
	 */
	@Override
	public Integer deleteTerminalPositionCarId(int carid) {
		return this.baseDao.delete("oracle-terminalDeviceInfoSQL.deleteTerminalPositionCarId", carid);
	}

	/**
	 * 批量查询车辆位置信息
	 * @param ids
	 * @return
	 */
	@Override
	public List<PositionInfo> queryCarAllDetail(List<Integer> idlist) {
		return this.baseDao.queryForList("oracle-terminalDeviceInfoSQL.queryCarAllDetail", idlist);
	}
	
	
	/**
	 * 创建车辆位置信息表
	 * @param tableName
	 */
	@Override
	public void createCarPosTable(Map<String,String> map){
		//创建表
		this.baseDao.update("oracle-terminalDeviceInfoSQL.createCarPosTable", map.get("tableName"));
		
		//创建索引
		this.baseDao.update("oracle-terminalDeviceInfoSQL.createACCIndex", map);
		this.baseDao.update("oracle-terminalDeviceInfoSQL.createCARNUMBEIndex", map);
		this.baseDao.update("oracle-terminalDeviceInfoSQL.createCARSTATUSIndex", map);
		this.baseDao.update("oracle-terminalDeviceInfoSQL.createCREATETIMEIndex", map);
		this.baseDao.update("oracle-terminalDeviceInfoSQL.createGPSFLAGIndex", map);
		this.baseDao.update("oracle-terminalDeviceInfoSQL.createTERMINALIndex", map);
		this.baseDao.update("oracle-terminalDeviceInfoSQL.createZKSTATEIndex", map);
	}
	
	/**
	 * 查询终端参数记录
	 * @param id
	 * @return
	 */
	public TerminalParameter getTerminalParam(Integer id){
		return (TerminalParameter)this.baseDao.queryForObject("oracle-terminalDeviceInfoSQL.getTerminalParam", id);
	}

	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	/**
	 * 车辆实时信息
	 */
	public Map getCarrealtimeList(int currentPage, int everyPage,
			Carrealtime carrealtime) {
		return this.baseDao.findPageList("oracle-terminalDeviceInfoSQL.getCarrealtimeList",
				"oracle-terminalDeviceInfoSQL.getCarrealtimeListCount", carrealtime,currentPage,everyPage);
	}

	/**
	 * Excel车辆实时信息
	 */
	public List<Carrealtime> exportCarrealtime(Carrealtime carrealtime) {
		return this.baseDao.queryForList("oracle-terminalDeviceInfoSQL.getCarrealtimeList", carrealtime);
	}

	/**
	 * 营运时长分析图表数据获取
	 */
	public List<OperaTimeAnalysis> queryOta(Map map) {
		return this.baseDao.queryForList("oracle-terminalDeviceInfoSQL.queryOta", map);
	}

	/**
	 * 导出 营运时长分析 数据以及图表
	 */
//	public Map getOtaList(int currentPage, int everyPage, OperaTimeAnalysis ota) {
//		return this.baseDao.findPageList("oracle-terminalDeviceInfoSQL.getOtaList",
//				"oracle-terminalDeviceInfoSQL.getOtaListCount", ota,currentPage,everyPage);
//	}
	public List<OperaTimeAnalysis> exportOta(OperaTimeAnalysis ota) {
		return this.baseDao.queryForList("oracle-terminalDeviceInfoSQL.exportOta", ota);
	}

	/**
	 * 营运里程统计列表
	 */
	public Map getOperaMileStatiList(final int currentPage, final int everyPage,OperaTimeAnalysis ota){
		return this.baseDao.findPageList("oracle-terminalDeviceInfoSQL.getOperaMileStatiList",
				"oracle-terminalDeviceInfoSQL.getOperaMileStatiListCount", ota,currentPage,everyPage);
	}
	
	/**
	 * 营运时长统计列表
	 */
	public Map getOperaTimeStatiList(final int currentPage, final int everyPage,OperaTimeAnalysis ota){
		return this.baseDao.findPageList("oracle-terminalDeviceInfoSQL.getOperaTimeStatiList",
				"oracle-terminalDeviceInfoSQL.getOperaTimeStatiListCount", ota,currentPage,everyPage);
	}

	/**
	 * 日均营运统计列表
	 */
	public Map getOperaDayStatiList(int currentPage, int everyPage,
			OperaTimeAnalysis ota) {
		return this.baseDao.findPageList("oracle-terminalDeviceInfoSQL.getOperaDayStatiList",
				"oracle-terminalDeviceInfoSQL.getOperaDayStatiListCount", ota,currentPage,everyPage);
	}
	
	/**
	 * 营运里程分析图表数据获取
	 */
	public List<OperaTimeAnalysis> queryOma(Map map) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-terminalDeviceInfoSQL.queryOma", map);
	}
	
	/**
	 * 车辆下拉分页列表
	 */
	public Map selectCarPageList(final int currentPage, final int everyPage,CarInfo carInfo){
		return this.baseDao.findPageList("oracle-terminalDeviceInfoSQL.selectCarList",
				"oracle-terminalDeviceInfoSQL.selectCarListCount", carInfo,currentPage,everyPage);
	}
	
	
}









