/**
 * Description: car-eye车辆管理平台
 * 文件名：CityInfoAction.java
 * 版本信息：1.0
 * 日期：2013-8-5
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.customer.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;
import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：CustomerAction
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2014-3-16 下午05:24:20
 * @修改人：huangqin
 * @修改时间：2013-3-16 下午05:24:20
 * @修改备注：
 * @version 1.0
 */
public class CustomerAction extends BasePageAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(BasePageAction.class);
	private CustomerService customerService;
	private CustomerInboundService customerInboundService;
	private Customer customer;
	private Customer data;
	private Map<Object, Object> result;
	private String cname;
	private String stime;
	private String etime;
	private String ids;
	private String success;
	private BlocUser blocUser;
	private List<CustomerType> listType = new ArrayList<CustomerType>();
	private List<Customer> list = new ArrayList<Customer>();

	private CustomerType customerType;
	private String typename;
	private String customertypeids;

	private String fileName;

	private String phone;

	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 查询客户信息列表
	 * 
	 * @return
	 */
	public String queryCustomerList() {
		try {
			initMap();
			if (customer == null) {
				customer = new Customer();
			}
			if (StringUtils.isNotEmty(cname)) {
				customer.setCname(URLDecoder.decode(cname, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(typename)) {
				customer.setTypename(URLDecoder.decode(typename, "UTF-8"));
			}
			if (ids != null && !"".equals(ids) && !"null".equals(ids)) {
				String[] datas = ids.split(",");
				for (int j = 0; j < datas.length; j++) {
					customer.getIds().add(Integer.parseInt(datas[j]));
				}
			}
			if (stime != null && !"".equals(stime) && !"null".equals(stime)) {
				customer.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (etime != null && !"".equals(etime) && !"null".equals(etime)) {
				customer.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			result = customerService.getAllCustomer(this.getPage(), this
					.getLimit(), customer);
			return SUCCESS;
		} catch (Exception e) {
			logger
					.error("CustomerAction 的方法 queryCustomerList 执行出错，原因：" + e,
							e);
			return ERROR;
		}
	}

	/**
	 * 添加客户信息
	 * 
	 * @return
	 */
	public String saveCustomer() {
		try {
			initMap();
			if (customer == null) {
				customer = new Customer();
			}
			int reFlag = 0;
			this.blocUser = (BlocUser) ServletActionContext.getRequest()
					.getSession().getAttribute(WebConstants.LOGIN_USER);
			customer.setUserid(this.blocUser.getId());
			if (customer.getId() != null) {
				reFlag = customerService.updateCustomer(customer);
			} else {
				customer.setSource(1); // 默认设置 1 96106
				customer.setFlag(1); // 客户状态 1、正常 0 删除 默认1
				reFlag = customerService.addCustomer(customer);
			}
			result.put("su", reFlag);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("CustomerAction 的方法 saveCustomer 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 添加客户信息
	 * 
	 * @return
	 */
	private String id;
	private String typeid;

	public String saveInBoundCus() {
		try {
			initMap();
			if (customer == null) {
				customer = new Customer();
			}
			if (StringUtils.isNotEmty(id)) {
				customer.setId(Integer.parseInt(id));
			}
			if (StringUtils.isNotEmty(typeid)) {
				customer.setTypeid(Integer.parseInt(typeid));
			}
			int reFlag = 0;
			this.blocUser = (BlocUser) ServletActionContext.getRequest()
					.getSession().getAttribute(WebConstants.LOGIN_USER);
			customer.setUserid(this.blocUser.getId());
			if (customer.getId() != null) {
				reFlag = customerService.updateCustomer(customer);
			} else {
				customer.setSource(1); // 默认设置 1 96106
				customer.setFlag(1); // 客户状态 1、正常 0 删除 默认1
				reFlag = customerService.addCustomer(customer);
			}
			result.put("su", reFlag);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("CustomerAction 的方法 saveCustomer 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 删除客户信息 （实际不是删除 是修改）
	 * 
	 * @return
	 */
	public String deleteCustomer() {
		try {
			initMap();
			if (ids == null)
				return ERROR;
			List<String> list = Arrays.asList(ids.split(","));
			List<String> listTypes = Arrays.asList(customertypeids.split(","));
			result.put("su", customerService.delCustomer(list, listTypes));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CustomerAction 的方法 deleteCustomer 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 根据手机号得到客户信息
	 * 
	 * @return
	 */
	public String loadCustomer() {
		try {
			initMap();
			if (phone == null)
				return ERROR;
			customer = new Customer();
			customer.setPhone(phone);
			data = customerService.loadCustomerByPhone(customer);
			if (data == null) {
				data = new Customer();
				// 新客户插入记录
				data.setCname(phone);
				data.setTypeid(Constant.SOFT_PHONE_CUSTOM_TYPE);
				data.setSex("1");
				data.setPhone(phone);
				data.setFlag(1);
				data.setUserid(SessionUtils.getUserId());
				int re = customerService.addCustomer(data);
				data.setTypename(Constant.SOFT_PHONE_CUSTOM_TYPENAME);
				data.setId(re);
			}

			// 添加客户来电记录
			CustomerInbound customerInbound = new CustomerInbound();
			customerInbound.setCid(data.getId());
			customerInbound.setCallnumber(data.getPhone());
			customerInbound.setInboundcalltime(DateUtil.getSQLDate());
			customerInbound.setUserid(SessionUtils.getUserId());
			customerInbound.setCreatetime(DateUtil.getSQLDate());
			customerInboundService.addCustomerInbound(customerInbound);

			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CustomerAction 的方法 loadCustomer 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/************************************* 客户类型 *****************************************/

	/**
	 * 获取客户类型下拉列表
	 * 
	 * @return
	 */
	public String selCustomerTypeList() {
		try {
			initMap();
			listType = customerService.selCustomerTypeList();
			result.put("list", listType);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CustomerAction的方法 selAreaList执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 查询客户类型信息列表
	 * 
	 * @return
	 */
	public String queryCustomerTypeList() {
		try {
			initMap();
			if (customerType == null) {
				customerType = new CustomerType();
			}
			if (StringUtils.isNotEmty(typename)) {
				customerType.setTypename(URLDecoder.decode(typename, "UTF-8")
						.trim());
			}
			if (StringUtils.isNotEmty(stime)) {
				customerType.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				customerType.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			result = customerService.getAllCustomerType(this.getPage(), this
					.getLimit(), customerType);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CustomerAction 的方法 queryCustomerTypeList 执行出错，原因："
					+ e, e);
			return ERROR;
		}
	}

	/**
	 * 添加客户类型信息
	 * 
	 * @return
	 */
	public String saveCustomerType() {
		try {
			initMap();
			if (customerType == null) {
				customerType = new CustomerType();
			}
			int reFlag = 0;
			this.blocUser = (BlocUser) ServletActionContext.getRequest()
					.getSession().getAttribute(WebConstants.LOGIN_USER);
			customerType.setUserid(this.blocUser.getId());
			if (customerType.getId() != null) {
				reFlag = customerService.updateCustomerType(customerType);
			} else {
				reFlag = customerService.addCustomerType(customerType);
			}
			result.put("su", reFlag);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("CustomerAction 的方法 saveCustomerType 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 删除客户类型信息
	 * 
	 * @return
	 */
	public String deleteCustomerType() {
		try {
			initMap();
			if (ids == null)
				return ERROR;
			List<String> list = Arrays.asList(ids.split(","));
			result.put("su", customerService.delCustomerType(list));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CustomerAction 的方法 deleteCustomerType 执行出错，原因：" + e,
					e);
			return ERROR;
		}
	}

	/**
	 * Excel导出
	 * 
	 * @throws IOException
	 */
	public void exportExcel() {
		try {
			// 1.设置名字
			fileName = "客户类型信息";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,20,15,15,20";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			if (customerType == null) {
				customerType = new CustomerType();
			}

			customerType.setUserid(SessionUtils.getUserId());
			if (StringUtils.isNotEmty(typename)) {
				customerType.setTypename(new String(typename
						.getBytes("iso8859-1"), "utf-8").trim());
			}

			listType = customerService.findCustomerTypePageList(customerType); // Excel查询

			titleRow.createCell(0).setCellValue("序号");// titleRow.setHeight((short)(20
														// * 15));
			titleRow.createCell(1).setCellValue("客户类型");
			titleRow.createCell(2).setCellValue("备注");
			titleRow.createCell(3).setCellValue("创建人");
			titleRow.createCell(4).setCellValue("创建时间");

			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (listType.size() > 0) {
				for (int i = 0; i < listType.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.createCell(0).setCellValue(index);
					CustomerType customerType = (CustomerType) listType.get(i);
					contentRow.createCell(1).setCellValue(
							customerType.getTypename());
					contentRow.createCell(2).setCellValue(
							customerType.getRemark());
					contentRow.createCell(3).setCellValue(
							customerType.getUsername());
					contentRow.createCell(4).setCellValue(
							customerType.getCreatetime());

					for (int m = 0; m < contentRow.getLastCellNum(); m++) {
						contentRow.getCell(m).setCellStyle(
								exceldownway.setBookListStyle(book));
					}
				}
			}

			exceldownway.getCommonExcelListWay(book, fileName);

		} catch (Exception e) {
			try {
				getResponse()
						.getWriter()
						.print(
								"<script language=javascript>alert('Error!');history.back();</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Excel导出
	 * 
	 * @throws IOException
	 */
	public void exportExcelTwo() {
		try {
			// 1.设置名字
			fileName = "客户信息";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,15,15,7,15,15,30,15,25,15,20";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			if (customer == null) {
				customer = new Customer();
			}

			customer.setUserid(SessionUtils.getUserId());
			if (StringUtils.isNotEmty(typename)) {
				customer.setTypename(new String(typename.getBytes("iso8859-1"),
						"utf-8"));
			}
			if (StringUtils.isNotEmty(cname)) {
				customer.setCname(new String(cname.getBytes("iso8859-1"),
						"utf-8").trim());
			}
			if (StringUtils.isNotEmty(stime)) {
				customer.setStime(new String(stime.getBytes("iso8859-1"),
						"utf-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				customer.setEtime(new String(etime.getBytes("iso8859-1"),
						"utf-8"));
			}

			list = customerService.findCustomerPageList(customer); // Excel查询

			titleRow.createCell(0).setCellValue("序号");// titleRow.setHeight((short)(20
														// * 15));
			titleRow.createCell(1).setCellValue("客户名称");
			titleRow.createCell(2).setCellValue("类型");
			titleRow.createCell(3).setCellValue("性别");
			titleRow.createCell(4).setCellValue("联系电话");
			titleRow.createCell(5).setCellValue("手机");
			titleRow.createCell(6).setCellValue("住址");
			titleRow.createCell(7).setCellValue("邮政编码");
			titleRow.createCell(8).setCellValue("信息描述");
			titleRow.createCell(9).setCellValue("创建人");
			titleRow.createCell(10).setCellValue("创建时间");

			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.createCell(0).setCellValue(index);
					Customer customer = (Customer) list.get(i);
					contentRow.createCell(1).setCellValue(customer.getCname());
					contentRow.createCell(2).setCellValue(
							customer.getTypename());
					String sex = customer.getSex().equals("1") ? "男" : "女";
					contentRow.createCell(3).setCellValue(sex);
					contentRow.createCell(4).setCellValue(customer.getTel());
					contentRow.createCell(5).setCellValue(customer.getPhone());
					contentRow.createCell(6).setCellValue(
							customer.getPostaddr());
					contentRow.createCell(7).setCellValue(
							customer.getPostalcode());
					contentRow.createCell(8).setCellValue(customer.getRemark());
					contentRow.createCell(9).setCellValue(
							customer.getUsername());
					contentRow.createCell(10).setCellValue(
							customer.getCreatetime());

					for (int m = 0; m < contentRow.getLastCellNum(); m++) {
						contentRow.getCell(m).setCellStyle(
								exceldownway.setBookListStyle(book));
					}
				}
			}

			exceldownway.getCommonExcelListWay(book, fileName);

		} catch (Exception e) {
			try {
				getResponse()
						.getWriter()
						.print(
								"<script language=javascript>alert('Error!');history.back();</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Map<Object, Object> getResult() {
		return result;
	}

	public void setResult(Map<Object, Object> result) {
		this.result = result;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public BlocUser getBlocUser() {
		return blocUser;
	}

	public void setBlocUser(BlocUser blocUser) {
		this.blocUser = blocUser;
	}

	public List<CustomerType> getListType() {
		return listType;
	}

	public void setListType(List<CustomerType> listType) {
		this.listType = listType;
	}

	public List<Customer> getList() {
		return list;
	}

	public void setList(List<Customer> list) {
		this.list = list;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getCustomertypeids() {
		return customertypeids;
	}

	public void setCustomertypeids(String customertypeids) {
		this.customertypeids = customertypeids;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Customer getData() {
		return data;
	}

	public void setData(Customer data) {
		this.data = data;
	}

	public CustomerInboundService getCustomerInboundService() {
		return customerInboundService;
	}

	public void setCustomerInboundService(
			CustomerInboundService customerInboundService) {
		this.customerInboundService = customerInboundService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
}
