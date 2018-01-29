package com.careye.car.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.careye.constant.Constant;
import com.careye.mq.domain.TerminalParameter;
import com.careye.base.service.GenericService;
import com.careye.car.domain.CarInfo;
import com.careye.car.domain.SerEva;
import com.careye.car.domain.TerminalDayUpload;
import com.careye.car.domain.TerminalFunCount;
import com.careye.car.service.CarService;
import com.careye.redis.CarInfoCache;

/**
 * @项目名称：TAXISERVER
 * @类名称：CarServiceImpl
 * @类描述：车辆服务接口
 * @创建人：zr
 * @创建时间：2015-3-12 下午06:07:25
 * @修改人：zr
 * @修改时间：2015-3-12 下午06:07:25
 * @修改备注：
 * @version 1.0
 */
public class CarServiceImpl extends GenericService implements CarService{

	private static Logger logger = Logger.getLogger(CarServiceImpl.class);
	/**
	 * 根据车牌号查询车辆是否存在
	 * @param carnumber
	 * @return
	 */
	@Override
	public String getCarInfoByCarNumber(String carnumber) {
		return (String) this.baseDao.queryForObject("oracle-carInfoSQL.getCarInfoByCarNumber", carnumber);
	}

	/**
	 * 根据设备号查询车辆是否存在
	 * @param carnumber
	 * @return
	 */
	@Override
	public String getCarInfoByTerminal(String terminal) {
		return (String) this.baseDao.queryForObject("oracle-carInfoSQL.getCarInfoByTerminal", terminal);
	}

	/**
	 * 获取车辆信息
	 * @param paramsMap
	 * @return
	 */
	@Override
	public CarInfo getCarInfo(Map<String, Object> paramsMap) {

		CarInfo carInfo2 = null;
		
		//本地缓存加载
		StringBuffer mapBuffer = new StringBuffer();
		mapBuffer.append(paramsMap.get("terminal"));
		mapBuffer.append("_");
		mapBuffer.append(paramsMap.get("devicetype"));
		carInfo2 = Constant.CAR_MAP.get(mapBuffer.toString());
		
		return carInfo2;
	
//		return (CarInfo) this.baseDao.queryForObject("oracle-carInfoSQL.getCarInfo", paramsMap);

	}
	
	/**
	 * 通过设备号获取车辆信息
	 * @param paramsMap
	 * @return
	 */
	public CarInfo getCarInfoByTer(Map<String, Object> paramsMap){
		return (CarInfo) this.baseDao.queryForObject("oracle-carInfoSQL.getCarInfoByTer", paramsMap);
	}

	/**
	 * 获取车辆信息
	 * @param paramsMap
	 * @return
	 */
	public CarInfo getCarInfoDetail(CarInfo cInfo){
		return (CarInfo) this.baseDao.queryForObject("oracle-carInfoSQL.getCarInfoDetail", cInfo);
	}
	
	/**
	 * 根据报警类型获取报警名称
	 * @param alarmkey
	 * @return
	 */
	@Override
	public String getAlarmName(String alarmkey) {
		String alarmName = CarInfoCache.alarmNameMap.get(alarmkey);
		if(alarmName == null){
			alarmName = (String) this.baseDao.queryForObject("oracle-alarmSQL.getAlarmName", alarmkey);
			CarInfoCache.alarmNameMap.put(alarmkey, alarmName);
		}
		return alarmName;
	}

	
	/**
	 * 根据车辆ID获取车辆状态
	 * @param carid
	 * @return
	 */
	@Override
	public Integer getCarStatus(int carid) {
		Object reObject = this.baseDao.queryForObject("oracle-carInfoSQL.getCarStatus", carid);
		if(reObject == null){
			return -1;
		}else {
			return (Integer) reObject;
		}
	}

	/**
	 * 更新车辆状态
	 * @param paramsMap
	 * @return
	 */
	@Override
	public Integer updateCarState(Map<String, Object> paramsMap) {
		this.baseDao.update("oracle-carInfoSQL.updateCarAcc", paramsMap);
		return this.baseDao.update("oracle-carInfoSQL.updateCarState", paramsMap);
	}
	

	/**
	 * 根据集团ID更新集团在离线数量
	 * @param blocid
	 * @return
	 */
	@Override
	public Integer updateOneDeptTotal(int blocid) {
		return this.baseDao.update("oracle-carInfoSQL.updateOneDeptTotal", blocid);
	}

	/**
	 * 根据报警key获取报警声音路径
	 * @param alarmkey
	 * @return
	 */
	@Override
	public String getMusicaddrByAlarmtype(String alarmkey) {
		return (String) this.baseDao.queryForObject("oracle-carInfoSQL.getMusicaddrByAlarmtype", alarmkey);
	}

	/**
	 * 根据服务监督卡号获取司机ID
	 * @param driverno
	 * @return
	 */
	@Override
	public Integer getDriverId(String driverno) {
		return (Integer) this.baseDao.queryForObject("oracle-carInfoSQL.getDriverId", driverno);
	}


	/**
	 * 插入服务评价
	 * @param serEva
	 * @return
	 */
	@Override
	public Integer insertSerEva(SerEva serEva) {
		return this.baseDao.save("oracle-carInfoSQL.insertSerEva", serEva);
	}

	/**
	 * 获取上级机构ID
	 * @param blocid
	 * @return
	 */
	@Override
	public List<Integer> getUpDeptlist(int blocid) {
		return this.baseDao.queryForList("oracle-carInfoSQL.getUpDeptlist", blocid);
	}

	/**
	 * 根据车辆ID获取是否存在记录
	 * @param carid
	 * @return
	 */
	@Override
	public Integer getTerminalFunCount(int carid) {
		return (Integer) this.baseDao.queryForObject("oracle-carInfoSQL.getTerminalFunCount", carid);
	}

	/**
	 * 插入日上报次数
	 * @param terminalFunCount
	 * @return
	 */
	public Integer insertTerminalDayUploadCount(TerminalDayUpload terminalDayUpload){
		return this.baseDao.save("oracle-carInfoSQL.insertTerminalDayUploadCount", terminalDayUpload);
	}
	
	/**
	 * 根据设备号得到设备类型
	 * @param simCode
	 * @return
	 */
	@Override
	public Integer getTypeidByTer(String terminal) {
		return (Integer)this.baseDao.queryForObject("oracle-carInfoSQL.getTypeidByTer", terminal);
	}
	
	/**
	 * 获取车辆总数
	 * @param deptid
	 * @return
	 */
	@Override
	public int getCarInfoSum(int deptid) {
		return (Integer) this.baseDao.queryForObject("oracle-carInfoSQL.getCarInfoSum", deptid);
	}

	/**
	 * 获取长离线数量
	 * @param deptid
	 * @return
	 */
	@Override
	public int getLongOffLineCarInfoSum(int deptid) {
		return (Integer) this.baseDao.queryForObject("oracle-carInfoSQL.getLongOffLineCarInfoSum", deptid);
	}

	/**
	 * 获取离线数量
	 * @param deptid
	 * @return
	 */
	@Override
	public int getOffLineCarInfoSum(int deptid) {
		return (Integer) this.baseDao.queryForObject("oracle-carInfoSQL.getOffLineCarInfoSum", deptid);
	}
	
	/**
	 * 所有组织机构列表不分页
	 * @return
	 */
	public List<Integer> selectAllDeptidList(){
		return this.baseDao.queryForList("oracle-carInfoSQL.selectAllDeptidList");
	}
	
	/**
     * 更新机构表中的车辆总数、离线数、长离数
     */
	@Override
	public int updateDeptCarNum(Map map) {
		return this.baseDao.update("oracle-carInfoSQL.updateDeptCarNum", map);
	}
	
	/**
	 * 获取所有车辆信息
	 * @return
	 */
	public List<CarInfo> findCarInfo(){
		return this.baseDao.queryForList("oracle-carInfoSQL.findCarInfo");
	}

	/**
	 * 根据终端号码获取车辆信息id
	 * @param terminal
	 * @return
	 */
	public Integer getCaridByTerminal(String terminal){
		return (Integer) this.baseDao.queryForObject("oracle-carInfoSQL.getCaridByTerminal", terminal);
	}
	
	/**
	 * 保存终端参数
	 * @param terminalParameter
	 * @return
	 */
	public Integer saveTerminalParameter(TerminalParameter terminalParameter){
		int count = (Integer)this.baseDao.queryForObject("oracle-carInfoSQL.getTerminalParameterCount", terminalParameter);
		if(count == 0){
			return this.baseDao.save("oracle-carInfoSQL.addTerminalParameter", terminalParameter);
		}
		return this.baseDao.update("oracle-carInfoSQL.updateTerminalParameter", terminalParameter);
	}
	
	/**
	 * 得到全部车辆信息
	 * @return
	 */
	public List<CarInfo> getCarInfoAll(){
		return  this.baseDao.queryForList("oracle-carInfoSQL.getCarInfoAll");
	}
	
	/**
	 * 更新车辆的司机代码
	 * @param carInfo
	 * @return
	 */
	public Integer updateDrivercode(CarInfo carInfo){
		return this.baseDao.update("oracle-carInfoSQL.updateDrivercode", carInfo);
	}
	
	/**
	 * 插入功能使用次数
	 * @param terminalFunCount
	 * @return
	 */
	@Override
	public Integer insertTerminalFunCount(TerminalFunCount terminalFunCount) {
		return this.baseDao.save("oracle-carInfoSQL.insertTerminalFunCount", terminalFunCount);
	}

	/**
	 * 更新次数
	 * @param terminalFunCount
	 * @return
	 */
	@Override
	public Integer updateTerminalFunCount(TerminalFunCount terminalFunCount) {
		return this.baseDao.update("oracle-carInfoSQL.updateTerminalFunCount", terminalFunCount);
	}
	
	/**
	 * 获取司机代码
	 * @param alarmkey
	 * @return
	 */
	public String getDriverCodeByCarid(int id){
		return (String) this.baseDao.queryForObject("oracle-carInfoSQL.getDriverCodeByCarid", id);
	}
	
}



