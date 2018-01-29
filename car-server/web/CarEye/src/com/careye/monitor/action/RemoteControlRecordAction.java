/**
 * Description: car-eye车辆管理平台
 * 文件名：RemoteControlRecordAction.java
 * 版本信息：1.0
 * 日期：2015-3-23
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.monitor.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarDriver;
import com.careye.monitor.domain.Photo;
import com.careye.monitor.domain.RemoteControl;
import com.careye.monitor.domain.TerminalSet;
import com.careye.monitor.service.RemoteControlRecordService;
import com.careye.system.action.UserAction;
import com.careye.system.domain.SysAuthLoginLog;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：RemoteControlRecordAction
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-23 下午08:35:37
 * @修改人：Yuqk
 * @修改时间：2015-3-23 下午08:35:37
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("serial")
public class RemoteControlRecordAction extends BasePageAction {
	private static final Logger logger = Logger.getLogger(UserAction.class);

	private RemoteControl remoteControl;
	private TerminalSet terminalSet;
	private RemoteControlRecordService remoteControlRecordService;

	private Map result;
	private String success;

	private String carnumber;//车牌号
	private String setstatus;//设置状态
	private String commandtype;//指令类型
	private String stime;//开始时间
	private String etime;//结束时间
	
	private String fileName;

	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 分页查询远程控制记录
	 * 
	 * @return
	 */
	public String findPageRemoteControlRecord() {

		try {
			initMap();
			if(remoteControl==null){
				remoteControl=new RemoteControl();
			}
			if (StringUtils.isNotEmty(carnumber)) {
				remoteControl.setCarnumber(URLDecoder.decode(carnumber, "UTF-8").toUpperCase().trim());
			}
			if (StringUtils.isNotEmty(setstatus)) {
				remoteControl.setStatus(setstatus);
			}
			if (StringUtils.isNotEmty(commandtype)) {
				remoteControl.setType(URLDecoder.decode(commandtype, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(stime)) {
				remoteControl.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				remoteControl.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			result = remoteControlRecordService.findPageRecordList(this.getPage(), this.getLimit(), remoteControl);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("RemoteControlRecordAction的方法 findPageRemoteControlRecord执行出错，原因："+ e, e);
			return ERROR;
		}
	}

	/**
	 * 导出远程控制记录 不分页查询远程控制记录
	 * 
	 * @return
	 */
	public void ExportRemoteControlRecord() {
		try {
		     //1.设置名字
			fileName = "远程控制记录报表";
	    	 HSSFWorkbook book = new HSSFWorkbook();
	         Sheet sheet= book.createSheet(fileName);
	         //sheet.setDefaultColumnWidth(15);
	         
	         ExcelDownWay exceldownway= new ExcelDownWay();
	         
	         //2.设置列宽（列数要对应上）
	         String str="7,15,20,15,120,15,20,20";
	         List<String> numberList=Arrays.asList(str.split(","));
	         sheet= exceldownway.setColumnWidth(sheet,numberList);
	     	
	         sheet.setDefaultRowHeight((short) 18);
	         Row titleRow= sheet.createRow(0);
	         titleRow.setHeightInPoints(20);
	         if(remoteControl==null){
					remoteControl=new RemoteControl();
				}
				if (StringUtils.isNotEmty(carnumber)) {
					remoteControl.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"), "UTF-8").toUpperCase().trim());
				}
				if (StringUtils.isNotEmty(setstatus)) {
					remoteControl.setStatus(setstatus);
				}
				if (StringUtils.isNotEmty(commandtype)) {
					remoteControl.setType(new String(commandtype.getBytes("ISO-8859-1"), "UTF-8").trim());
				}
				if (StringUtils.isNotEmty(stime)) {
					remoteControl.setStime(stime);
				}
				if (StringUtils.isNotEmty(etime)) {
					remoteControl.setEtime(etime);
				}
				List<RemoteControl> recordList = remoteControlRecordService
				.findRecordList(remoteControl);
				
				
				titleRow.createCell(0).setCellValue("序号");
		        titleRow.createCell(1).setCellValue("车牌号");
		        titleRow.createCell(2).setCellValue("指令类型");
		        titleRow.createCell(3).setCellValue("状态");
		        titleRow.createCell(4).setCellValue("协议指令");
		        titleRow.createCell(5).setCellValue("创建账号");
		        titleRow.createCell(6).setCellValue("创建时间");
	         
	         for(int i=0;i<titleRow.getLastCellNum();i++){
	        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
	         }
	         
	         
		        if(recordList.size()>0){
		            for(int i=0;i<recordList.size();i++){
		                int  index=i+1;
		                Row contentRow= sheet.createRow(index);
		                contentRow.setHeightInPoints(15);
		                RemoteControl data= (RemoteControl) recordList.get(i);
		                contentRow.createCell(0).setCellValue(index);
		                contentRow.createCell(1).setCellValue(data.getCarnumber());
		                contentRow.createCell(2).setCellValue(data.getType());
		                if (data.getStatus() == null) {
		                	contentRow.createCell(3).setCellValue("");
		                } else if (data.getStatus().equals("1")) {
	                		contentRow.createCell(3).setCellValue("成功");
						} else if(data.getStatus().equals("2")){
		                	contentRow.createCell(3).setCellValue("失败");
						}
		                contentRow.createCell(4).setCellValue(data.getRemark());
		                contentRow.createCell(5).setCellValue(data.getLoginname());
		                contentRow.createCell(6).setCellValue(data.getCreatetime());
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
	 * 分页查询终端参数设置记录
	 * 
	 * @return
	 */
	public String findPageTerminalSetRecord() {
		
		try {
			initMap();
			if(terminalSet==null){
				terminalSet=new TerminalSet();
			}
			if (StringUtils.isNotEmty(carnumber)) {
				terminalSet.setCarnumber(URLDecoder.decode(carnumber, "UTF-8").toUpperCase().trim());
			}
			if (StringUtils.isNotEmty(setstatus)) {
				terminalSet.setStatus(setstatus);
			}
			if (StringUtils.isNotEmty(commandtype)) {
				terminalSet.setType(URLDecoder.decode(commandtype, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(stime)) {
				terminalSet.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				terminalSet.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			result = remoteControlRecordService.findPageTerminalSetList(this.getPage(), this.getLimit(), terminalSet);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TerminalSetRecordAction的方法 findPageTerminalSetRecord执行出错，原因："+ e, e);
			return ERROR;
		}
	}
	
	/**
	 * 导出终端参数设置记录 不分页查询终端参数设置记录
	 * 
	 * @return
	 */
	public void ExportTerminalSetRecord() {
		
		
		try {
		     //1.设置名字
			fileName = "终端参数设置记录报表";
	    	 HSSFWorkbook book = new HSSFWorkbook();
	         Sheet sheet= book.createSheet(fileName);
	         //sheet.setDefaultColumnWidth(15);
	         
	         ExcelDownWay exceldownway= new ExcelDownWay();
	         
	         //2.设置列宽（列数要对应上）
	         String str="7,15,25,15,150,15,20";
	         List<String> numberList=Arrays.asList(str.split(","));
	         sheet= exceldownway.setColumnWidth(sheet,numberList);
	         
	         sheet.setDefaultRowHeight((short) 18);
	         Row titleRow= sheet.createRow(0);
	         titleRow.setHeightInPoints(20);
	         if(terminalSet==null){
					terminalSet=new TerminalSet();
				}
				if (StringUtils.isNotEmty(carnumber)) {
					terminalSet.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"), "UTF-8").toUpperCase().trim());
				}
				if (StringUtils.isNotEmty(setstatus)) {
					terminalSet.setStatus(setstatus);
				}
				if (StringUtils.isNotEmty(commandtype)) {
					terminalSet.setType(new String(commandtype.getBytes("ISO-8859-1"), "UTF-8").trim());
				}
				if (StringUtils.isNotEmty(stime)) {
					terminalSet.setStime(stime);
				}
				if (StringUtils.isNotEmty(etime)) {
					terminalSet.setEtime(etime);
				}
				List<TerminalSet> recordList = remoteControlRecordService.findTerminalSetList(terminalSet);
				
	         
	            titleRow.createCell(0).setCellValue("序号");
				titleRow.createCell(1).setCellValue("车牌号");
				titleRow.createCell(2).setCellValue("指令类型");
				titleRow.createCell(3).setCellValue("状态");
				titleRow.createCell(4).setCellValue("协议指令");
				titleRow.createCell(5).setCellValue("创建账号");
				titleRow.createCell(6).setCellValue("创建时间");
	         
	         for(int i=0;i<titleRow.getLastCellNum();i++){
	        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
	         }
	         
	         if(recordList.size()>0){
	             for(int i=0;i<recordList.size();i++){
	            	  int  index=i+1;
		                Row contentRow= sheet.createRow(index);
		                contentRow.setHeightInPoints(15);
		                TerminalSet data= (TerminalSet) recordList.get(i);
		                contentRow.createCell(0).setCellValue(index);
		                contentRow.createCell(1).setCellValue(data.getCarnumber());
		                contentRow.createCell(2).setCellValue(data.getType());
		                if (data.getStatus() == null) {
		                	contentRow.createCell(3).setCellValue("");
		                } else if (data.getStatus().equals("1")) {
	                		contentRow.createCell(3).setCellValue("成功");
						} else if(data.getStatus().equals("2")){
		                	contentRow.createCell(3).setCellValue("失败");
						}
		                contentRow.createCell(4).setCellValue(data.getRemark());
		                contentRow.createCell(5).setCellValue(data.getLoginname());
		                contentRow.createCell(6).setCellValue(data.getCreatetime());
	                 
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

	/*
	 * getter setter
	 */

	public RemoteControlRecordService getRemoteControlRecordService() {
		return remoteControlRecordService;
	}

	public RemoteControl getRemoteControl() {
		return remoteControl;
	}

	public void setRemoteControl(RemoteControl remoteControl) {
		this.remoteControl = remoteControl;
	}

	public void setRemoteControlRecordService(
			RemoteControlRecordService remoteControlRecordService) {
		this.remoteControlRecordService = remoteControlRecordService;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getSetstatus() {
		return setstatus;
	}

	public void setSetstatus(String setstatus) {
		this.setstatus = setstatus;
	}

	public String getCommandtype() {
		return commandtype;
	}

	public void setCommandtype(String commandtype) {
		this.commandtype = commandtype;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public TerminalSet getTerminalSet() {
		return terminalSet;
	}

	public void setTerminalSet(TerminalSet terminalSet) {
		this.terminalSet = terminalSet;
	}

}
