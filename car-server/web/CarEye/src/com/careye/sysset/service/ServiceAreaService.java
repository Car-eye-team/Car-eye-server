/**
 * Description: car-eye车辆管理平台
 * 文件名：ServiceAreaService.java
 * 版本信息：1.0
 * 日期：2015-3-31
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.sysset.service;

import java.util.List;

import com.careye.sysset.domain.ServiceArea;

/**
 * @项目名称：car-eye
 * @类名称：ServiceAreaService
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-31 下午02:07:45
 * @修改人：Yuqk
 * @修改时间：2015-3-31 下午02:07:45
 * @修改备注：
 * @version 1.0
 */
public interface ServiceAreaService {
	/**
	 * 依据传递的市编码，开通县或省市下级区域服务(增加记录)
	 * 
	 * @param serviceArea
	 * @return
	 */
	public int openServiceArea(ServiceArea serviceArea);

	/**
	 * 依据传递的省编码，关闭省级区域服务(级联删除记录)
	 * 
	 * @param pcode
	 * @return
	 */
	public int closeServiceProvince(Long pcode);
	/**
	 * 依据传递市编码，关闭市级区域服务(级联删除记录)
	 * 
	 * @param ccode
	 * @return
	 */
	public int closeServiceCity(Long ccode);
	/**
	 * 依据传递的县编码，关闭县级区域服务(删除记录)
	 * 
	 * @param cycode
	 * @return
	 */
	public int closeServiceCounty(Long cycode);

	/**
	 * 找到省级区域服务开通情况(查询记录)
	 * 
	 * @param serviceArea
	 * @return
	 */
	public List findServiceProvince();
	/**
	 * 依据传递的省编码，找到市级区域服务开通情况(查询记录)
	 * 
	 * @param serviceArea
	 * @return
	 */
	public List findServiceCity(ServiceArea serviceArea);
	/**
	 * 依据传递的市编码，找到县级区域服务开通情况(查询记录)
	 * 
	 * @param serviceArea
	 * @return
	 */
	public List findServiceCounty(ServiceArea serviceArea);
}
