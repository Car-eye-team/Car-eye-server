/**
* Description: car-eye车辆管理平台
* 文件名：SupplierService.java
* 版本信息：1.0
* 日期：2014-3-6
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.sysset.service;

import java.util.List;
import java.util.Map;

import com.careye.sysset.domain.CarType;


/**
 * @项目名称：FMS
 * @类名称：SupplierService
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-7-26 上午11:57:21
 * @修改人：zhangrong
 * @修改时间：2014-7-26 上午11:57:21
 * @修改备注：
 * @version 1.0
 */
public interface CarTypeService {
	/**
	 * 查看车辆类型名称是否存在
	 * @param carTypeName
	 * @return
	 */
	public int carTypeNameIsExist(CarType carType);
	/**
	 * 删除车辆类型
	 * @param id
	 * @return
	 */
	public int deleteCarType(List<String> ids);
	
	/**
	 * 添加车辆类型
	 * @param carType
	 * @return
	 */
	public int insertCarType(CarType carType);
	
	/**
	 * 修改车辆类型
	 * @param id
	 * @return
	 */
	public int updateCarType(CarType carType);
	/**
	 * 查看车辆类型
	 * @param id
	 * @return
	 */
	public CarType getCarTypeById(int id);
	
	/**
	 * 车辆类型分页
	 * @return
	 */
	public Map selectPageCarType(final int currentPage, final int everyPage,CarType carType);
	
	
	/** Excel导出 **/
	public List getAllCarType(CarType carType);
}
