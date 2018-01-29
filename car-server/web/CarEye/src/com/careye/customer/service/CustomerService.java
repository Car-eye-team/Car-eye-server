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
import com.careye.customer.domain.CustomerType;

/**
 * @项目名称：car-eyeTms
 * @类名称：CustomerService
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-3-6 上午11:57:21
 * @修改人：lxh
 * @修改时间：2014-3-6 上午11:57:21
 * @修改备注：
 * @version 1.0
 */
public interface CustomerService {

	/**
	 * 得到所有的客户信息
	 * 
	 * @param petFence
	 * @return
	 */
	public Map<Object, Object> getAllCustomer(final int currentPage,
			final int everyPage, Customer customer);

	/**
	 * Excel 导出
	 * 
	 * @param customer
	 * @return
	 */
	public List<Customer> findCustomerPageList(Customer customer);

	/**
	 * 添加客户信息
	 * 
	 * @param petFence
	 * @return
	 */
	public int addCustomer(Customer customer);

	/**
	 * 删除客户信息
	 * 
	 * @param id
	 * @return
	 */
	public int delCustomer(List<String> list, List<String> listTypes);

	/**
	 * 修改客户信息
	 * 
	 * @param petFence
	 * @return
	 */
	public int updateCustomer(Customer customer);

	/**
	 * 查询是否相同的名字存在
	 * 
	 * @param name
	 * @return
	 */
	public Integer queryCustomerNameIsExits(Customer customer);

	/**
	 * 得到所有的客户类型下拉列表
	 * 
	 * @return
	 */
	public List<CustomerType> selCustomerTypeList();

	/**
	 * 根据条件得到单个客户信息 
	 * 
	 * @return
	 */
	public Customer loadCustomerByPhone(Customer customer);
	
	
	
	

	/******************************************************************************************/

	/**
	 * 得到所有的客户类型信息
	 * 
	 * @param petFence
	 * @return
	 */
	public Map<Object, Object> getAllCustomerType(final int currentPage,
			final int everyPage, CustomerType customerType);

	/**
	 * Excel 导出
	 * 
	 * @param customerType
	 * @return
	 */
	public List<CustomerType> findCustomerTypePageList(CustomerType customerType);
	
	/**
	 * 添加客户类型信息
	 * 
	 * @param petFence
	 * @return
	 */
	public int addCustomerType(CustomerType customerType);

	/**
	 * 删除客户类型信息
	 * 
	 * @param id
	 * @return
	 */
	public int delCustomerType(List<String> list);

	/**
	 * 修改客户类型信息
	 * 
	 * @param petFence
	 * @return
	 */
	public int updateCustomerType(CustomerType customerType);

	/**
	 * 查询是否相同的名字存在
	 * 
	 * @param name
	 * @return
	 */
	public Integer queryCustomerTypeNameIsExits(CustomerType customerType);

}
