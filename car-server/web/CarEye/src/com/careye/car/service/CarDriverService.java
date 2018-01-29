/**
* Description: car-eye车辆管理平台
* 文件名：CarService.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.service;

import java.util.List;
import java.util.Map;

import com.careye.car.domain.CarDriver;
import com.careye.car.domain.PreJobExam;
import com.careye.car.domain.ServiceLicense;
import com.careye.car.domain.TaxiMeter;

/**
 * @项目名称：car-eye
 * @类名称：CarDriverService
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-10 上午10:00:03
 * @修改人：huangqin
 * @修改时间：2015-3-10 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public interface CarDriverService {
	
	/**
	 * 获取最新插入的id
	 * @return
	 */
	public int getNewId();
	
	/**
	 * 更新司机代码
	 * @param carDriver
	 * @return
	 */
	public int updateDrivercode(CarDriver carDriver);

	/**
	 * 司机是否已存在
	 * @param carnumber
	 * @param carstatus
	 * @return
	 */
	public int queryDriverNameIsExist(CarDriver carDriver);
	
	/**
	 * 查询服务监督卡号是否存在
	 * @param 
	 * @return
	 */
	public int getDrivercodeIsExist(CarDriver carDriver);
	
	/**
	 * 更新驾驶员信息
	 * @param carDriver
	 * @return
	 */
	public int updateCarDriver(CarDriver carDriver);
	
	
	/**
	 * 删除驾驶员信息
	 * @param id
	 * @return
	 */
	public int deleteCarDriver(List<String> list);
	
	/**
	 * 添加驾驶员信息
	 * @param carDriver
	 * @return
	 */
	public int insertCarDriver(CarDriver carDriver);
	
	
	/**
	 * 条件查询驾驶员信息
	 * @param carDriver
	 * @return
	 */
	public Map selectCheckCarDriver(final int currentPage, final int everyPage,CarDriver carDriver);
	
	/**
	 * 分页查询司机服务证列表信息
	 * @param carDriver
	 * @return
	 */
	public Map queryServicePhotoList(final int currentPage, final int everyPage,ServiceLicense serviceLicense);
	
	/**
	 * Excel导出
	 * @param carDriver
	 * @return
	 */
	public List<CarDriver> getAllCarDriver(CarDriver carDriver);
	
	/**
	 * 根据驾驶员姓名获取驾驶员ID
	 * @param drivername
	 * @return
	 */
	public int getCarDriverId(String drivername);

	/**
	 * 条件查询当班驾驶员信息
	 * @param carDriver
	 * @return
	 */
	public Map selectDutyCarDriver(final int currentPage, final int everyPage,CarDriver carDriver);


	/**
	 * 条件查询计价器信息
	 * @param carDriver
	 * @return
	 */
	public Map selectTaxiMeter(final int currentPage, final int everyPage,TaxiMeter taxiMeter);
	
	/**
	 * 添加驾驶证信息
	 * @param carDriver
	 * @return
	 */
	public int insertDriverLicense(CarDriver carDriver);
	
	/**
	 * 修改驾驶证信息
	 * @param carDriver
	 * @return
	 */
	public int updateDriverLicense(CarDriver carDriver);

	/**
	 * 删除驾驶员信息
	 * @param id
	 * @return
	 */
	public int deleteDriverLicense(List<String> list);
	
	/**
	 * 根据driver获取岗前考试信息
	 * @param seq
	 * @return
	 */
	public PreJobExam findPreJobExamByDriverid(String driverid);
	
	/**
	 * 新增岗前考试信息
	 * @param seq
	 * @return
	 */
	public int insertPreJobExam(PreJobExam preJobExam);
	
	/**
	 * 根据id修改岗前考试信息
	 * @param seq
	 * @return
	 */
	public int updatePreJobExam(PreJobExam preJobExam);
	
	/**
	 * 根据driverid删除岗前考试信息
	 * @param seq
	 * @return
	 */
	public int deletePreJobExam(List<String> list);
	
	/**
	 * 根据driver获取服务证信息
	 * @param seq
	 * @return
	 */
	public ServiceLicense findServiceLicenseByDriverid(String driverid);
	
	/**
	 * 新增服务证信息
	 * @param seq
	 * @return
	 */
	public int insertServiceLicense(ServiceLicense serviceLicense);
	
	/**
	 * 根据id修改服务证信息
	 * @param seq
	 * @return
	 */
	public int updateServiceLicense(ServiceLicense serviceLicense);
	
	/**
	 * 根据driverid删除服务证信息
	 * @param seq
	 * @return
	 */
	public int deleteServiceLicense(List<String> list);
}
