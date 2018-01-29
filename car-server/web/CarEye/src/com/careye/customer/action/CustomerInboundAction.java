/**
 * Description: car-eye车辆管理平台
 * 文件名：CityInfoAction.java
 * 版本信息：1.0
 * 日期：2013-8-5
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.customer.action;

import java.io.File;
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
import com.careye.constant.WebConstants;
import com.careye.customer.domain.Customer;
import com.careye.customer.domain.CustomerInbound;
import com.careye.customer.domain.CustomerType;
import com.careye.customer.service.CustomerInboundService;
import com.careye.customer.service.CustomerService;
import com.careye.system.domain.BlocUser;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：CustomerAction.java
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2014-3-16 下午05:24:20
 * @修改人：huangqin
 * @修改时间：2013-3-16 下午05:24:20
 * @修改备注：
 * @version 1.0
 */
public class CustomerInboundAction extends BasePageAction {
	  private static final long serialVersionUID = 1L;
		private static final Logger logger = Logger.getLogger(BasePageAction.class);
		private CustomerInboundService customerInboundService;
		private CustomerInbound customerInbound;
		private Map<Object, Object> result;
		private String callnumber;
		private String stime;
		private String etime;
		private String success;
		private BlocUser blocUser;
		private String ids;
		private List<CustomerInbound> list=new ArrayList<CustomerInbound>();
		private String cname;
		private String agentid; //分机号码
		
		private String fileName;
		
		public void initMap() {
			if(result == null) {
				result = new HashMap();
			}
		}
		/**
		 * 查询客户信息列表
		 * @return
		 */
		public String queryCustomerInboundList(){
			try {
				initMap();
				if(customerInbound==null){
					customerInbound = new CustomerInbound();
				}
				if(StringUtils.isNotEmty(cname)){
					customerInbound.setCname(URLDecoder.decode(cname, "UTF-8").trim());
				}
				if(StringUtils.isNotEmty(callnumber)){
					customerInbound.setCallnumber(URLDecoder.decode(callnumber, "UTF-8").trim());
				}
				if(stime!=null&&!"".equals(stime)&&!"null".equals(stime)){
					customerInbound.setStime(URLDecoder.decode(stime, "UTF-8"));
				}
				if(etime!=null&&!"".equals(etime)&&!"null".equals(etime)){
					customerInbound.setEtime(URLDecoder.decode(etime, "UTF-8"));
				}
				result = customerInboundService.getAllCustomerInbound(this.getPage(), this.getLimit(), customerInbound);	 
				return SUCCESS;
			} catch (Exception e) {
				logger.error("CustomerInboundAction 的方法 queryCustomerInboundList 执行出错，原因：" + e, e);
				return ERROR;
			}
		}
//		/**
//		 * 添加客户信息
//		 * @return
//		 */
//		public String saveCustomerInbound(){
//			try {
//				initMap();
//				if(customerInbound==null){
//					customerInbound = new CustomerInbound();
//				}
//				int reFlag=0;
//				this.blocUser = (BlocUser) ServletActionContext.getRequest().getSession().getAttribute(WebConstants.LOGIN_USER);	
//				customerInbound.setUserid(this.blocUser.getId());
//				if(customerInbound.getId()!=null){
//					reFlag= customerInboundService.updateCustomerInbound(customerInbound);
//				}else{
//					reFlag = customerInboundService.addCustomer(customerInbound);
//				}
//				result.put("su", reFlag);
//				this.success = "true";
//				return SUCCESS;
//			} catch (Exception e) {
//				this.success = "false";
//				logger.error("CustomerInboundAction 的方法 saveCustomerInbound 执行出错，原因：" + e, e);
//				return ERROR;
//			}
//		}
		/**
		 * 删除客户信息  
		 * @return
		 */
		public String deleteCustomerInbound(){
			try {
				initMap();
				if (ids == null)
					return ERROR;
				List<String> list = Arrays.asList(ids.split(","));
				result.put("su", customerInboundService.delCustomerInbound(list));
				return SUCCESS;
			} catch (Exception e) {
				logger.error("CustomerInboundAction 的方法 deleteCustomerInbound 执行出错，原因：" + e, e);
				return ERROR;
			}
		}
		
       /**
	     * Excel导出
	     * @throws IOException
	     */
		public  void   exportExcel(){
			try {
			     //1.设置名字
				 fileName="客户来电信息"; 
		    	 HSSFWorkbook book = new HSSFWorkbook();
		         Sheet sheet= book.createSheet(fileName);
		         //sheet.setDefaultColumnWidth(15);
		         
		         ExcelDownWay exceldownway= new ExcelDownWay();
		         
		         //2.设置列宽（列数要对应上）
		         String str="7,15,15,15,20,20,15,20";
		         List<String> numberList=Arrays.asList(str.split(","));
		         sheet= exceldownway.setColumnWidth(sheet,numberList);
		         
		         sheet.setDefaultRowHeight((short) 18);
		         Row titleRow= sheet.createRow(0);
		         titleRow.setHeightInPoints(20);
		         if(customerInbound==null){
		        	 customerInbound=new CustomerInbound();
			     }
		         
		         customerInbound.setUserid(SessionUtils.getUserId());
		         if(StringUtils.isNotEmty(cname)){
		        	 customerInbound.setCname(new String(cname.getBytes("iso8859-1"),"utf-8").trim());
				 }
		         if(StringUtils.isNotEmty(callnumber)){
		        	 customerInbound.setCallnumber(new String(callnumber.getBytes("iso8859-1"),"utf-8").trim());
				 }
		         if(StringUtils.isNotEmty(stime)){
		        	 customerInbound.setStime(new String(stime.getBytes("iso8859-1"),"utf-8"));
				 }
		         if(StringUtils.isNotEmty(etime)){
		        	 customerInbound.setEtime(new String(etime.getBytes("iso8859-1"),"utf-8"));
				 }
		         
		         list=customerInboundService.findCustomerInboundPageList(customerInbound);   //Excel查询
		         
		         titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
		         titleRow.createCell(1).setCellValue("客户姓名");
		        // titleRow.createCell(2).setCellValue("客户类型");
		         titleRow.createCell(2).setCellValue("分机号码");
		         titleRow.createCell(3).setCellValue("来电号码");
		         titleRow.createCell(4).setCellValue("来电时间");
		         titleRow.createCell(5).setCellValue("挂机时间");
		         titleRow.createCell(6).setCellValue("创建人");
		         titleRow.createCell(7).setCellValue("创建时间");
		         
		         for(int i=0;i<titleRow.getLastCellNum();i++){
		        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
		         }
		         
		         if(list.size()>0){
		             for(int i=0;i<list.size();i++){
		                 int  index=i+1;
		                 Row contentRow= sheet.createRow(index);
		                 contentRow.createCell(0).setCellValue(index);
		                 CustomerInbound customerInbound= (CustomerInbound) list.get(i);
		                 String cname=customerInbound.getCname();
		                 if(StringUtils.isEmty(cname)){
		                	 cname="未知";
		                 }
		                 contentRow.createCell(1).setCellValue(cname);
	//	                 String typename=customerInbound.getCname();
	//	                 if(StringUtils.isEmty(typename)){
	//	                	 typename="未知";
	//	                 }
		                 contentRow.createCell(2).setCellValue(customerInbound.getAgentid());
		                 contentRow.createCell(3).setCellValue(customerInbound.getCallnumber());
		                 contentRow.createCell(4).setCellValue(customerInbound.getInboundcalltime());
		                 contentRow.createCell(5).setCellValue(customerInbound.getHangupcalltime());
		                 contentRow.createCell(6).setCellValue(customerInbound.getUsername());
		                 contentRow.createCell(7).setCellValue(customerInbound.getCreatetime());
		                 
		                 for(int m=0;m<contentRow.getLastCellNum();m++){
		                	 contentRow.getCell(m).setCellStyle(exceldownway.setBookListStyle(book));
		                 }
		             }
		         }
		         
		         exceldownway.getCommonExcelListWay(book,fileName);
		         
			} catch (Exception e) {
				try {
					getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	    }
		
		/**
		 * 更新坐席最后一条记录挂机时间
		 * @return
		 */
		public String updateHangupcalltime(){
			try {
				initMap();
				if(agentid != null){
					customerInboundService.updateHangupcalltime(agentid);
				}
				return SUCCESS;
			} catch (Exception e) {
				logger.error("CustomerInboundAction 的方法 updateHangupcalltime 执行出错，原因：" + e, e);
				return ERROR;
			}
		}
		
		
		
		
		public CustomerInboundService getCustomerInboundService() {
			return customerInboundService;
		}
		public void setCustomerInboundService(
				CustomerInboundService customerInboundService) {
			this.customerInboundService = customerInboundService;
		}
		public CustomerInbound getCustomerInbound() {
			return customerInbound;
		}
		public void setCustomerInbound(CustomerInbound customerInbound) {
			this.customerInbound = customerInbound;
		}
		public Map<Object, Object> getResult() {
			return result;
		}
		public void setResult(Map<Object, Object> result) {
			this.result = result;
		}
		public String getCallnumber() {
			return callnumber;
		}
		public void setCallnumber(String callnumber) {
			this.callnumber = callnumber;
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
		public String getIds() {
			return ids;
		}
		public void setIds(String ids) {
			this.ids = ids;
		}
		public List<CustomerInbound> getList() {
			return list;
		}
		public void setList(List<CustomerInbound> list) {
			this.list = list;
		}
		public String getCname() {
			return cname;
		}
		public void setCname(String cname) {
			this.cname = cname;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getAgentid() {
			return agentid;
		}
		public void setAgentid(String agentid) {
			this.agentid = agentid;
		}
	
}
