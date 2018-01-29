/**
 * Description: car-eye车辆管理平台
 * 文件名：CustomerService.java
 * 版本信息：1.0
 * 日期：2014-3-6
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.customer.service;

import java.util.List;
import java.util.Map;

import com.careye.customer.domain.Customer;
import com.careye.customer.domain.CustomerInbound;
import com.careye.customer.domain.CustomerType;

/**
 * @项目名称：car-eyeTms
 * @类名称：CustomerInboundService
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-3-6 上午11:57:21
 * @修改人：lxh
 * @修改时间：2014-3-6 上午11:57:21
 * @修改备注：
 * @version 1.0
 */
public interface CustomerInboundService {

	/**
	 * 得到所有的客户来电信息
	 * 
	 * @param petFence
	 * @return
	 */
	public Map<Object, Object> getAllCustomerInbound(final int currentPage,
			final int everyPage, CustomerInbound customerInbound);

	/**
	 * Excel 导出
	 * 
	 * @param customerInbound
	 * @return
	 */
	public List<CustomerInbound> findCustomerInboundPageList(CustomerInbound customerInbound);

	/**
	 * 删除客户来电信息
	 * 
	 * @param id
	 * @return
	 */
	public int delCustomerInbound(List<String> list);

	/**
	 * 添加客户来电记录信息
	 * 
	 * @param petFence
	 * @return
	 */
	public int addCustomerInbound(CustomerInbound customerInbound);

	/**
	 * 更新坐席最后一条记录挂机时间
	 * @param agentid
	 */
	public int updateHangupcalltime(String agentid);



}




