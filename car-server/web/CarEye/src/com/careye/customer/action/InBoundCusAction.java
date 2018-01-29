/**
 * Description: car-eye车辆管理平台
 * 文件名：InBoundCusAction.java
 * 版本信息：1.0
 * 日期：2015-6-10
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.customer.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.careye.base.action.BasePageAction;
import com.careye.constant.Constant;
import com.careye.constant.WebConstants;
import com.careye.customer.domain.Customer;
import com.careye.customer.domain.CustomerInbound;
import com.careye.customer.domain.CustomerType;
import com.careye.customer.service.CustomerInboundService;
import com.careye.customer.service.CustomerService;
import com.careye.system.domain.BlocUser;
import com.careye.transaction.domain.Transaction;
import com.careye.transaction.domain.TransactionType;
import com.careye.transaction.service.TransactionService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：InBoundCusAction
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-6-10 上午09:52:56
 * @修改人：Yuqk
 * @修改时间：2015-6-10 上午09:52:56
 * @修改备注：
 * @version 1.0
 */
public class InBoundCusAction extends BasePageAction {
	
	private static final long serialVersionUID = -7341716758589182991L;
	private Logger logger = Logger.getLogger(InBoundCusAction.class);
	private CustomerService customerService;
	private TransactionService transactionService;
	private CustomerInboundService customerInboundService;
	private String phone;
	private String agentid;
	private String inboundcalltime;
	private String recordfile;
	private String uniqueid;

	private Transaction transaction;
	private Customer customer;
	private List<CustomerType> cusTypeList;
	private List<TransactionType> traTypeList;
	private BlocUser blocUser;
	/**
	 * 加载来电客户信息
	 * @return
	 */
	public String findInBoundCus() {
		if (phone == null)
			return ERROR;
		customer = new Customer();
		customer.setPhone(phone);
		customer = customerService.loadCustomerByPhone(customer);
		if (customer == null) {
			customer = new Customer();
			// 新客户插入记录
			customer.setCname(customer.getCname()==null?phone:customer.getCname());
			customer.setTypeid(Constant.SOFT_PHONE_CUSTOM_TYPE);
			customer.setSex("1");
			customer.setPhone(phone);
			customer.setFlag(1);
			customer.setUserid(SessionUtils.getUserId());
			int re = customerService.addCustomer(customer);
			customer.setTypename(Constant.SOFT_PHONE_CUSTOM_TYPENAME);
			customer.setId(re);
		}

		if(StringUtils.isNotEmty(inboundcalltime) || StringUtils.isNotEmty(uniqueid)){
			// 添加客户来电记录
			CustomerInbound customerInbound = new CustomerInbound();
			customerInbound.setCid(customer.getId());
			customerInbound.setCallnumber(customer.getPhone());
			customerInbound.setInboundcalltime(inboundcalltime);
			customerInbound.setUserid(SessionUtils.getUserId());
			customerInbound.setCreatetime(DateUtil.getSQLDate());
			customerInbound.setAgentid(agentid);
			customerInbound.setRecordfile(recordfile);
			customerInbound.setUniqueid(uniqueid);
			customerInboundService.addCustomerInbound(customerInbound);
		}
		// 获取最后一次约车记录
		transaction = transactionService.loadLastTransaction(phone);
		if (transaction == null) {
			transaction = new Transaction();
		} else {
			if (transaction.getTratype() == 2) {
				transaction.setUsetime(transaction.getAppointmenttime());
			}
		}
		transaction.setCreatetime(DateUtil.getSQLDate());
		//订单类型list
		traTypeList=transactionService.selTransactionTypeList();
		//客户类型list
		cusTypeList=customerService.selCustomerTypeList();
		return SUCCESS;
		
	}
	/**
	 *保存客户信息
	 * @return
	 */
	public String saveCustomer(){
		if(customer==null){
			customer = new Customer();
		}
		int reFlag=0;
		this.blocUser = (BlocUser) ServletActionContext.getRequest().getSession().getAttribute(WebConstants.LOGIN_USER);	
		customer.setUserid(this.blocUser.getId());
		if(customer.getId()!=null){
			reFlag= customerService.updateCustomer(customer);
		}else{
			customer.setSource(1);    //默认设置  1 96106 
			customer.setFlag(1);      //客户状态	1、正常 0 删除 默认1
			reFlag = customerService.addCustomer(customer);
		}
		if(reFlag<0){
			return ERROR;
		}
		return SUCCESS;
		
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public CustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public TransactionService getTransactionService() {
		return transactionService;
	}
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	public CustomerInboundService getCustomerInboundService() {
		return customerInboundService;
	}
	public void setCustomerInboundService(
			CustomerInboundService customerInboundService) {
		this.customerInboundService = customerInboundService;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<CustomerType> getCusTypeList() {
		return cusTypeList;
	}
	public void setCusTypeList(List<CustomerType> cusTypeList) {
		this.cusTypeList = cusTypeList;
	}
	public List<TransactionType> getTraTypeList() {
		return traTypeList;
	}
	public void setTraTypeList(List<TransactionType> traTypeList) {
		this.traTypeList = traTypeList;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public String getInboundcalltime() {
		return inboundcalltime;
	}
	public void setInboundcalltime(String inboundcalltime) {
		this.inboundcalltime = inboundcalltime;
	}
	public String getRecordfile() {
		return recordfile;
	}
	public void setRecordfile(String recordfile) {
		this.recordfile = recordfile;
	}
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	
}
