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
import com.careye.sysset.domain.CarUse;


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
public interface CarUseService {
	/**
	 * 查看车辆用途名称是否存在
	 * @param carTypeName
	 * @return
	 */
	public int carUseNameIsExist(CarUse carUse);
	/**
	 * 删除车辆用途
	 * @param id
	 * @return
	 */
	public int deleteCarUse(List<String> ids);
	
	/**
	 * 添加车辆用途
	 * @param carType
	 * @return
	 */
	public int insertCarUse(CarUse carUse);
	
	/**
	 * 修改车辆用途
	 * @param id
	 * @return
	 */
	public int updateCarUse(CarUse carUse);
	/**
	 * 查看车辆用途
	 * @param id
	 * @return
	 */
	public CarUse getCarUseById(int id);
	
	/**
	 * 车辆用途分页
	 * @return
	 */
	public Map selectPageCarUse(final int currentPage, final int everyPage,CarUse carUse);
}
