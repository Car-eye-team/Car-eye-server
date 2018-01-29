/**
* Description: 多森56taxi物流平台
* 文件名：CarService.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.service;

import java.util.List;
import java.util.Map;

import com.careye.car.domain.CarDeviceDetail;
import com.careye.car.domain.CarDriver;
import com.careye.car.domain.CarDriverInfo;
import com.careye.car.domain.CarInfo;
import com.careye.car.domain.CarPosSuffix;
import com.careye.car.domain.CarStatus;
import com.careye.car.domain.CarnumberInfo;
import com.careye.car.domain.ControlRecord;
import com.careye.car.domain.Drivercode;
import com.careye.car.domain.OperateCertificate;
import com.careye.common.domain.CarDetail;
import com.careye.common.domain.CarState;
import com.careye.sysset.domain.CarType;
import com.careye.sysset.domain.CarUse;
import com.careye.sysset.domain.DeviceType;

/**
 * @项目名称：FMS
 * @类名称：CarService
 * @类描述：
 * @创建人：zr
 * @创建时间：2014-5-22 上午10:00:03
 * @修改人：zr
 * @修改时间：2014-5-22 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public interface CarService {
	/**
	 * 当班司机列表
	 * @param 
	 * @return
	 */
	public List<Drivercode> getDrivercodeList(CarInfo carInfo);
	
	/**
	 * 车辆下拉列表
	 * @param 
	 * @return
	 */
	public List<CarInfo> selectCarList(CarInfo carInfo);
	
	/**
	 * 车辆列表,取出集团
	 * @param 
	 * @return
	 */
	public List<CarnumberInfo> queryCarList(CarInfo carInfo);
	/**
	 * 雅讯车辆列表
	 * @param 
	 * @return
	 */
	public List<CarnumberInfo> queryYxCarList(CarInfo carInfo);

	/**
	 * 车辆下拉列表
	 * @param 
	 * @return
	 */
	public List<CarInfo> selectCarListByCheck(CarInfo carInfo);
	
	
	/**
	 * 根据终端号码获取车辆信息
	 * @param terminal
	 * @return
	 */
	public CarInfo getCarInfo(String terminal);
	/**
	 * 根据终端号码获取车辆信息id
	 * @param terminal
	 * @return
	 */
	public Integer getCaridByTerminal(String terminal);
	
	
	/**
	 * 根据车牌号获取车辆信息
	 * @param terminal
	 * @return
	 */
	public CarInfo getCarInfoCarnumber(String carnumber);
	
	/**
	 * 根据车牌号查询车辆信息
	 * @param terminal
	 * @return
	 */
	public CarInfo getCarInfoByCarNumber(String carnumber);
	
	/**
	 * 根据车辆ID获取车辆信息
	 * @param carid
	 * @return
	 */
	public CarInfo getCarInfoCarId(int carid);
	
	/**
	 * 根据carid获取设备类型
	 * @param terminal
	 * @return
	 */
	public Integer getUserTypeByCarid(int carid);
	
	/**
	 * 查询车牌号信息
	 * @param terminal
	 * @return
	 */
	public List<CarInfo> selectCarNumber(CarInfo carInfo);
	
	/**
	 * 根据车牌号更新车辆当前状态
	 * @param carnumber
	 * @param carstatus
	 * @return
	 */
	public int updateCarState(CarInfo carInfo);
	
	/**
	 * 终端号码是否已存在
	 * @param carnumber
	 * @param carstatus
	 * @return
	 */
	public int queryTerminalIsExist(CarInfo carInfo);
	
	/**
	 * 验证手机卡号是否存在
	 * @param carInfo
	 * @return
	 */
	public int queryPhoneIsExist(CarInfo carInfo);
	
	/**
	 * 车牌号是否在已存在
	 * @param carnumber
	 * @param carstatus
	 * @return
	 */
	public int queryCarNumberIsExist(CarInfo carInfo);
	
	/**
	 * 设备号是否已存在
	 * @param carnumber
	 * @param carstatus
	 * @return
	 */
	public int queryDevicenumberIsExist(CarInfo carInfo);
	

	/**
	 * 更新车辆信息
	 * @param carInfo
	 * @return
	 */
	public int updateCarInfo(CarInfo carInfo);
	
	/**
	 * 删除车辆信息
	 * @param id
	 * @return
	 */
	public int deleteCarInfo(int id);
	
	/**
	 * 添加车辆信息
	 * @param carInfo
	 * @return
	 */
	public int insertCarInfo(CarInfo carInfo);
	
	/**
	 * 添加车辆状态
	 * @param carInfo
	 * @return
	 */
	public int insertCarState(CarState carState);
	
	/**
	 * 条件查询车辆信息
	 * @param carInfo
	 * @return
	 */
	public Map selectCheckCarInfo(final int currentPage, final int everyPage,CarInfo carInfo);
	
	/**
	 * 条件查询车辆指定信息
	 * @param carInfo
	 * @return
	 */
	public Map queySomeCarInfo(final int currentPage, final int everyPage,CarInfo carInfo);
	public Map queryLedCarList(final int currentPage, final int everyPage,CarInfo carInfo);
	
	
	/**
	 * 插入车辆，位置信息后缀表记录
	 * @param carPosSuffix
	 * @return
	 */
	public Integer insertCarPosSuffix(CarPosSuffix carPosSuffix);
	
	/**
	 * 根据车辆ID修改 车辆，位置信息后缀表记录状态
	 * @param carid
	 * @return
	 */
	public Integer updateCarPosSuffixState(int carid);
	
	/**
	 * 根据车辆ID得到车辆位置表后缀名
	 * @param carid
	 * @return
	 */
	public String getSuffix(int carid);
	
	/**
	 * 导出车辆信息记录
	 * @return
	 */
	public List<CarInfo> exportCarInfo (CarInfo carInfo);
	
	
	/**
	 * 保存车辆信息（此方法为一个业务逻辑）
	 * @param carInfo
	 * @return
	 */
	public Integer saveCarInfo(CarInfo carInfo);
	
	/**
	 * 批量转移车辆
	 * @param list
	 * @return
	 */
	public String updateCarDept(String deptid,String ids);
	
	/**
	 * 根据设备类型获取用户类型
	 * @param devicetype
	 * @return
	 */
	public int getUserType(int devicetype);
	
	/**
	 * 根据设备号获取用户类型
	 * @param devicetype
	 * @return
	 */
	public int getUserTypeByTerminal(String terminal);
	/**
	 * 根据组织机构更新对应车辆总数
	 * @param devicetype
	 * @return
	 */
	public int updateDeptTotal(int deptid);
	
	/**
	 * 获取设备类型列表
	 * @return
	 */
	public List<DeviceType> getDeviceTypeList();
	
	/**
	 * 获取设备类型列表
	 * @return
	 */
	public List<CarType> getCarTypeList();
	
	/**
	 * 获取设备类型列表
	 * @return
	 */
	public List<CarUse> getCarUseList();
	
	/**
	 * 司机信息列表
	 * @param terminal
	 * @return
	 */
	public List<CarDriver> selectCarDriver(CarDriver carDriver);
	
	
	/**
	 * 根据设备号获取车辆设备类型
	 * @param terminal
	 * @return
	 */
	public Integer getCarDeviceType(String carnumber);
	/**
	 * 根据车牌号号获取车辆设备类型与用户类型
	 * @param terminal
	 * @return
	 */
	public CarDeviceDetail getCarDeviceDetail(String carnumber);
	
	/**读取车辆详细信息**/
	public CarDetail readCarDetailInfo(int carid);
	
	/**车辆详细信息**/
	public CarDetail getCarDetailInfo(String carnumber);
	
	/**
	 * 得到车辆运行轨迹经纬度点集合
	 * @param paramsMap 可以传入参数queryTime1,queryTime2，起止时间,车辆id
	 * @return
	 */
	public List<CarStatus> findCarTrackPointList(Map paramsMap);
	
	/**
	 * oracle鉴权数据往86-mysql插一份
	 */
	public void addEcsTo86();
	
	/**
	 * 86-mysql鉴权数据往118-oracle插一份
	 */
	public void addEcsTo118();
	/**
	 * taxi数据往oracle插一份鉴权数据
	 */
	public void addEcsToOracle();
	
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
	 * 根据流水号更新处理结果
	 * @param seq
	 * @return
	 */
	public int updateControlResult(ControlRecord controlRecord);
	
	/**
	 * 获取车辆司机信息 
	 * @param seq
	 * @return
	 */
	public List<CarDriverInfo> getCarDriverInfoList(CarDriverInfo carDriverInfo);

	/**
	 * 条件查询车辆控制记录信息
	 * @param carInfo
	 * @return
	 */
	public Map selectCheckCarControlRecord(final int currentPage, final int everyPage,ControlRecord controlRecord);
	
	/**
	 * 添加车辆控制记录
	 * @param carInfo
	 * @return
	 */
	public int insertCarControlrecord(ControlRecord controlRecord);
	/**
	 * 获取最新插入的车辆id 
	 * @param seq
	 * @return
	 */
	public Integer getNewCarid();
	
	/**
	 * 根据carid新增车辆属性信息
	 * @param seq
	 * @return
	 */
	public int insertCarProperty(CarInfo carInfo);
	
	/**
	 * 根据carid修改车辆属性信息
	 * @param seq
	 * @return
	 */
	public int updateCarProperty(CarInfo carInfo);
	
	/**
	 * 根据carid删除车辆属性信息
	 * @param seq
	 * @return
	 */
	public int deleteCarProperty(String carid);
	
	/**
	 * 根据carid获取营运证信息
	 * @param seq
	 * @return
	 */
	public OperateCertificate findOperateCertificateByCarid(Integer carid);
	
	/**
	 * 根据carid新增营运证信息
	 * @param seq
	 * @return
	 */
	public int insertOperateCertificate(OperateCertificate operateCertificate);
	
	/**
	 * 根据id修改营运证信息
	 * @param seq
	 * @return
	 */
	public int updateOperateCertificate(OperateCertificate operateCertificate);
	
	/**
	 * 根据carid删除营运证信息
	 * @param seq
	 * @return
	 */
	public int deleteOperateCertificate(String carid);
	
	/**
	 * 根据caridStr获得车辆信息
	 * @param caridStr
	 * @return
	 */
	public Map getCarInfoListByCaridStr(final int currentPage, final int everyPage,String caridStr);
	
	/**
	 * 更新司机代码
	 * @param seq
	 * @return
	 */
	public int updateDriverCode(CarInfo carInfo);
	
}
