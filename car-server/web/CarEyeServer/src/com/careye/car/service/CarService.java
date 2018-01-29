package com.careye.car.service;

import java.util.List;
import java.util.Map;

import com.careye.car.domain.Alarm;
import com.careye.car.domain.CarDriver;
import com.careye.car.domain.CarInfo;
import com.careye.car.domain.SerEva;
import com.careye.car.domain.TerminalDayUpload;
import com.careye.car.domain.TerminalFunCount;
import com.careye.mq.domain.TerminalParameter;


/**
 * @项目名称：TAXISERVER
 * @类名称：CarService
 * @类描述：车辆服务接口
 * @创建人：zr
 * @创建时间：2015-3-12 下午06:06:06
 * @修改人：zr
 * @修改时间：2015-3-12 下午06:06:06
 * @修改备注：
 * @version 1.0
 */
public interface CarService {
	
	/**
	 * 根据车牌号查询车辆是否存在
	 * @param carnumber
	 * @return
	 */
	public String getCarInfoByCarNumber(String carnumber);
	
	/**
	 * 根据设备号查询车辆是否存在
	 * @param carnumber
	 * @return
	 */
	public String getCarInfoByTerminal(String terminal);
	
	/**
	 * 获取车辆信息
	 * @param paramsMap
	 * @return
	 */
	public CarInfo getCarInfo(Map<String, Object> paramsMap);
	
	/**
	 * 通过设备号获取车辆信息
	 * @param paramsMap
	 * @return
	 */
	public CarInfo getCarInfoByTer(Map<String, Object> paramsMap);
	
	/**
	 * 获取车辆信息
	 * @param paramsMap
	 * @return
	 */
	public CarInfo getCarInfoDetail(CarInfo cInfo);
	
	/**
	 * 根据报警类型获取报警名称
	 * @param alarmkey
	 * @return
	 */
	public String getAlarmName(String alarmkey);
	
	
	/**
	 * 根据车辆ID获取车辆状态
	 * @param carid
	 * @return
	 */
	public Integer getCarStatus(int carid);
	
	/**
	 * 更新车辆状态
	 * @param paramsMap
	 * @return
	 */
	public Integer updateCarState(Map<String, Object> paramsMap);
	
	/**
	 * 根据集团ID更新集团在离线数量
	 * @param blocid
	 * @return
	 */
	public Integer updateOneDeptTotal(int blocid);
	
	/**
	 * 根据报警key获取报警声音路径
	 * @param alarmkey
	 * @return
	 */
	public String getMusicaddrByAlarmtype(String alarmkey);
	
	/**
	 * 根据服务监督卡号获取司机ID
	 * @param driverno
	 * @return
	 */
	public Integer getDriverId(String driverno);
	
	/**
	 * 插入服务评价
	 * @param serEva
	 * @return
	 */
	public Integer insertSerEva(SerEva serEva);
	
	
	/**
	 * 根据车辆ID获取是否存在记录
	 * @param carid
	 * @return
	 */
	public Integer getTerminalFunCount(int carid);
	
	
	/**
	 * 根据设备号得到设备类型
	 * @param simCode
	 * @return
	 */
	public Integer getTypeidByTer(String terminal);
	
	/**
	 * 统计上级所有的组织机构id
	 * @param deptid
	 * @return
	 */
	public List<Integer> getUpDeptlist(int deptid);
	
	/**
	 * 获取车辆总数
	 * @param deptid
	 * @return
	 */
	public int getCarInfoSum(int deptid);
	
	/**
	 * 获取离线数量
	 * @param deptid
	 * @return
	 */
	public int getOffLineCarInfoSum(int deptid);
	
	
	/**
	 * 获取长离线数量
	 * @param deptid
	 * @return
	 */
	public int getLongOffLineCarInfoSum(int deptid);
	
	/**
	 * 所有组织机构列表不分页
	 * @return
	 */
	public List<Integer> selectAllDeptidList();
	
	/**
	 * 更新机构表中的车辆总数、离线数、长离数
	 * @param map
	 * @return
	 */
	public int updateDeptCarNum(Map map);
	
	/**
	 * 获取所有车辆信息
	 * @return
	 */
	public List<CarInfo> findCarInfo();
	
	/**
	 * 根据终端号码获取车辆信息id
	 * @param terminal
	 * @return
	 */
	public Integer getCaridByTerminal(String terminal);

	/**
	 * 保存终端参数
	 * @param terminalParameter
	 * @return
	 */
	public Integer saveTerminalParameter(TerminalParameter terminalParameter);

	/**
	 * 得到全部车辆信息
	 * @return
	 */
	public List<CarInfo> getCarInfoAll();
	
	/**
	 * 更新车辆的司机代码
	 * @param carInfo
	 * @return
	 */
	public Integer updateDrivercode(CarInfo carInfo);
	
	/**
	 * 插入功能使用次数
	 * @param terminalFunCount
	 * @return
	 */
	public Integer insertTerminalFunCount(TerminalFunCount terminalFunCount);
	
	/**
	 * 更新次数
	 * @param terminalFunCount
	 * @return
	 */
	public Integer updateTerminalFunCount(TerminalFunCount terminalFunCount);
	
	/**
	 * 获取司机代码
	 * @param alarmkey
	 * @return
	 */
	public String getDriverCodeByCarid(int id);
	
}
