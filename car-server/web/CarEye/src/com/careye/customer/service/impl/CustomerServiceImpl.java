/**
* Description: car-eye车辆管理平台
* 文件名：CustomerInfoServiceImpl.java
* 版本信息：1.0
* 日期：2014-3-6
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.customer.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.careye.base.service.GenericService;
import com.careye.common.service.LogService;
import com.careye.common.service.SysOperateLogService;
import com.careye.customer.domain.Customer;
import com.careye.customer.domain.CustomerType;
import com.careye.customer.service.CustomerService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;
/**
 * @项目名称：car-eyeTms
 * @类名称：CustomerServiceImpl
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-3-6 下午12:39:55
 * @修改人：lxh
 * @修改时间：2014-3-6 下午12:39:55
 * @修改备注：
 * @version 1.0
 */
public class CustomerServiceImpl extends GenericService implements CustomerService{

	private  SysOperateLogService logService;
    
	
	public Map<Object, Object> getAllCustomer(int currentPage, int everyPage, Customer customer) {
		return this.baseDao.findPageList("oracle-customerSQL.getAllCustomer",
				"oracle-customerSQL.getAllCustomerCount", customer, currentPage,
				everyPage);
	}

	/**
	 *Excel导出
	 */
	@Override
	public List<Customer> findCustomerPageList(Customer customer) {
		logService.addLogInfo(SessionUtils.getUserId(), "Excel导出客户信息", 4);
		return this.baseDao.queryForList("oracle-customerSQL.getAllCustomer",customer);
	}
	
	
	
	
	/**
	 * 添加客户信息
	 * @param carDriver
	 * @return
	 */
	//@Log(operationType="add操作:",operationName="添加客户信息记录")
	public int addCustomer(Customer customer) {
		int areaName = queryCustomerNameIsExits(customer);
		
		int result=0;
		if(areaName>0){
			result=-3;
		}else{
			customer.setCreatetime(DateUtil.getSQLDate());
			result=this.baseDao.save("oracle-customerSQL.addCustomer", customer);
			if(result>0){
				logService.addLogInfo(customer.getUserid(), "添加客户信息记录:客户名称:"+customer.getCname()
						+";创建时间:"+
						customer.getCreatetime()+";影响数据ID:"+customer.getId(), 1);
			}
		}
			
		return result;
	}
	
	/**
	 * 删除客户信息记录
	 * @param id
	 * @return
	 */
	//@Log(operationType="delete操作:",operationName="删除客户信息记录")
	public int delCustomer(List <String> list,List <String> listTypes) {
		for(int i=0;i<list.size();i++){
			//根据id得到对象
			Customer customer=this.getCustomerById(Integer.parseInt(list.get(i)));
			
			logService.addLogInfo(customer.getUserid(), "删除客户信息记录:客户名称:"+customer.getCname()
					+";创建时间:"+
					customer.getCreatetime()+";影响数据ID:"+customer.getId(),3);
		}
		
		return  this.baseDao.delete("oracle-customerSQL.delCustomer", list);
	}

	/**
	 * 根据id得到客户信息
	 * @param id
	 * @return
	 */
	private Customer getCustomerById(int id){
		
	   return 	 (Customer) this.baseDao.queryForObject("oracle-customerSQL.getCustomerById",id);
	}
	
	
	/**
	 * 更新客户信息
	 * @param carDriver
	 * @return
	 */
	//@Log(operationType="update操作:",operationName="更新客户信息记录")
	public int updateCustomer(Customer customer) {
		
		int areaName = queryCustomerNameIsExits(customer);
		
		int result=0;
		
		if(areaName>0){
			result=-3;
		}else{
			    customer.setCreatetime(DateUtil.getSQLDate());
			
				if(!SessionUtils.getUser().equals("admin")){	//普通用户
					customer.setUserid(SessionUtils.getUser().getUserid());
				}
				result=this.baseDao.update("oracle-customerSQL.updateCustomer", customer);
				
				logService.addLogInfo(SessionUtils.getUserId(), "更新客户信息记录:客户名称:"+customer.getCname()
						+";创建时间:"+
						customer.getCreatetime()+";影响数据ID:"+customer.getId(),2);
			return result;
		}
		
		return result;
	}

	public Integer queryCustomerNameIsExits(Customer customer) {
		Object obj = this.baseDao.queryForObject(
				"oracle-customerSQL.queryCustomerNameIsExits", customer);
		int num = obj == null ? 0 : (Integer) obj;
		return num;
	}

	@Override
	public List<CustomerType> selCustomerTypeList() {
		return this.baseDao.queryForList("oracle-customerSQL.selCustomerTypeList");
	}
	
	/**
	 * 根据条件得到单个客户信息 
	 * 
	 * @return
	 */
	public Customer loadCustomerByPhone(Customer customer){
		return (Customer)this.baseDao.queryForObject("oracle-customerSQL.loadCustomerByPhone", customer);
	}
	
	
	/**
	 * 通过Id 获取客户
	 */
	public String selectCustomerByID(int id){
		return (String)this.baseDao.queryForObject("oracle-customerSQL.selectCustomerByID",id);
	}

	

	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	@Override
	public int addCustomerType(CustomerType customerType) {
        int areaName = queryCustomerTypeNameIsExits(customerType);
		
		int result=0;
		if(areaName>0){
			result=-3;
		}else{
			customerType.setCreatetime(DateUtil.getSQLDate());
			result=this.baseDao.save("oracle-customerSQL.addCustomerType", customerType);
			if(result>0){
				logService.addLogInfo(customerType.getUserid(), "添加客户类型信息记录:客户类型名称:"+customerType.getTypename()
						+";创建时间:"+
						customerType.getCreatetime()+";影响数据ID:"+customerType.getId(), 1);
			}
		}
		return result;
	}

	@Override
	public int delCustomerType(List<String> list) {
		int result=0;
		for(int i=0;i<list.size();i++){
			//查找是否包含子项
			if(this.getCustomerCountById(Integer.parseInt(list.get(i)))>0){
				result=-3;
				return result;
			}else{
			//根据id得到对象
			CustomerType customerType=this.getCustomerTypeById(Integer.parseInt(list.get(i)));
			logService.addLogInfo(customerType.getUserid(), "删除客户类型信息记录:客户类型名称:"+customerType.getTypename()
					+";创建时间:"+
					customerType.getCreatetime()+";影响数据ID:"+customerType.getId(),3);
			}
		}
		
		result= this.baseDao.delete("oracle-customerSQL.delCustomerType", list);
		return result;
	}
	
	/**
	 * 查找客户类型下的数量
	 * @param id
	 * @return
	 */
	private Integer  getCustomerCountById(Integer databanktypeid){
		
		return  (Integer) this.baseDao.queryForObject("oracle-customerSQL.getCustomerCountById",databanktypeid);
	}
	
	/**
	 * 根据id得到客户信息
	 * @param id
	 * @return
	 */
	private CustomerType getCustomerTypeById(int id){
		
	   return 	 (CustomerType) this.baseDao.queryForObject("oracle-customerSQL.getCustomerTypeById",id);
	}
	

	@Override
	public Map<Object, Object> getAllCustomerType(int currentPage,
			int everyPage, CustomerType customerType) {
		return this.baseDao.findPageList("oracle-customerSQL.getAllCustomerType",
				"oracle-customerSQL.getAllCustomerTypeCount", customerType, currentPage,
				everyPage);
	}

	@Override
	public Integer queryCustomerTypeNameIsExits(CustomerType customerType) {
		Object obj = this.baseDao.queryForObject(
				"oracle-customerSQL.queryCustomerTypeNameIsExits", customerType);
		int num = obj == null ? 0 : (Integer) obj;
		return num;
	}

	@Override
	public int updateCustomerType(CustomerType customerType) {
        int areaName = queryCustomerTypeNameIsExits(customerType);
		int result=0;
		if(areaName>0){
			result=-3;
		}else{
			customerType.setCreatetime(DateUtil.getSQLDate());
			
			if(!SessionUtils.getUser().equals("admin")){	//普通用户
				customerType.setUserid(SessionUtils.getUser().getId());
			}
				
			result=this.baseDao.update("oracle-customerSQL.updateCustomerType", customerType);
			if(result>0){
				logService.addLogInfo(customerType.getUserid(), "更新客户类型信息记录:客户类型名称:"+customerType.getTypename()
						+";创建时间:"+
						customerType.getCreatetime()+";影响数据ID:"+customerType.getId(),2);
			}
			return result;
		}
		
		return result;
	}
    /**
     * Excel 导出
     */
	@Override
	public List<CustomerType> findCustomerTypePageList(CustomerType customerType) {
		logService.addLogInfo(SessionUtils.getUserId(), "Excel导出客户类型信息", 4);
		return this.baseDao.queryForList("oracle-customerSQL.getAllCustomerType",customerType);
	}


}
