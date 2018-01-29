/**
 * Description: car-eye车辆管理平台
 * 文件名：AnswerCountAction.java
 * 版本信息：1.0
 * 日期：2015-4-3
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.statement.oncalltransaction.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.careye.base.action.BasePageAction;
import com.careye.statement.oncalltransaction.domain.AnswerCountInfo;
import com.careye.statement.oncalltransaction.service.AnswerCountService;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;
import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：AnswerCountAction
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-3 下午03:57:39
 * @修改人：Yuqk
 * @修改时间：2015-4-3 下午03:57:39
 * @修改备注：
 * @version 1.0
 */
public class AnswerCountAction extends BasePageAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1638467389562236615L;
	private Logger logger = Logger.getLogger(AnswerCountAction.class);
	private AnswerCountService answerCountService;
	private Map result;
	private AnswerCountInfo answerCountInfo;
	private String fileName;
	/*
	 * input
	 */
	private String blocid;
	private String carnumber;
	private String drivername;
	private String zbstatus;
	private String stime;
	private String etime;

	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 分页查询抢答统计列表
	 * 
	 * @return
	 */
	public String findPageAnswerCountList() {
		try {
			initMap();
			if (SessionUtils.getUser() == null) {
				return ERROR;
			}
			if (answerCountInfo == null) {
				answerCountInfo = new AnswerCountInfo();
			}
			if (StringUtils.isNotEmty(stime)) {
				answerCountInfo.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				answerCountInfo.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(blocid)) {
				answerCountInfo.setBlocid(Integer.parseInt(blocid));
			}
			if (StringUtils.isNotEmty(zbstatus)) {
				answerCountInfo.setZbstatus(Integer.parseInt(zbstatus));
			}
			if (StringUtils.isNotEmty(carnumber)) {
				answerCountInfo.setCarnumber(URLDecoder.decode(carnumber,
						"UTF-8").trim());
			}
			if (StringUtils.isNotEmty(drivername)) {
				answerCountInfo.setDrivername(URLDecoder.decode(drivername,
						"UTF-8").trim());
			}
			result = answerCountService.findPageAnswerCountList(
					answerCountInfo, this.getPage(), this.getLimit());
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AnswerCountAction的方法 findPageAnswerCountList执行出错，原因："
					+ e, e);
			return ERROR;
		}

	}

	/**
	 * 导出抢答数据列表
	 */
	public void excelExportAnswerCountList() {

		try {
			// 1.设置名字
			fileName = "抢答统计报表";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,20,20,15,15,15,25,25";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			if (SessionUtils.getUser() == null) {
				return ;
			}
			if (answerCountInfo == null) {
				answerCountInfo = new AnswerCountInfo();
			}
			if (StringUtils.isNotEmty(stime)) {
				answerCountInfo.setStime(new String(stime.getBytes("ISO-8859-1"),"UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				answerCountInfo.setEtime(new String(etime.getBytes("ISO-8859-1"),"UTF-8"));
			}
			if (StringUtils.isNotEmty(blocid)) {
				answerCountInfo.setBlocid(Integer.parseInt(blocid));
			}
			if (StringUtils.isNotEmty(zbstatus)) {
				answerCountInfo.setZbstatus(Integer.parseInt(zbstatus));
			}
			if (StringUtils.isNotEmty(carnumber)) {
				answerCountInfo.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"),"UTF-8").trim());
			}
			if (StringUtils.isNotEmty(drivername)) {
				answerCountInfo.setDrivername(new String(drivername.getBytes("ISO-8859-1"),"UTF-8").trim());
			}
			List<AnswerCountInfo> list= answerCountService.excelExportAnswerCountList(answerCountInfo);
			titleRow.createCell(0).setCellValue("序号");
			titleRow.createCell(1).setCellValue("集团名称");
			titleRow.createCell(2).setCellValue("车牌号");
			titleRow.createCell(3).setCellValue("当班司机姓名");
			titleRow.createCell(4).setCellValue("终端设备号");
			titleRow.createCell(5).setCellValue("中标状态");
			titleRow.createCell(6).setCellValue("订单ID");
			titleRow.createCell(7).setCellValue("创建时间");

			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.setHeightInPoints(15);
					AnswerCountInfo data = (AnswerCountInfo) list.get(i);
					contentRow.createCell(0).setCellValue(index);
					contentRow.createCell(1).setCellValue(data.getBlocname());
					contentRow.createCell(2).setCellValue(data.getCarnumber());
					contentRow.createCell(3).setCellValue(data.getDrivername());
					contentRow.createCell(4).setCellValue(data.getTerminal());
					if(data.getZbstatus()==1){
						contentRow.createCell(5).setCellValue("未中标");
					}else if(data.getZbstatus()==2){
						contentRow.createCell(5).setCellValue("中标");
					}else{
						contentRow.createCell(5).setCellValue("");
					}
					contentRow.createCell(6).setCellValue(data.getOrderid());
					contentRow.createCell(7).setCellValue(data.getCreatetime());
					for (int m = 0; m < contentRow.getLastCellNum(); m++) {
						contentRow.getCell(m).setCellStyle(
								exceldownway.setBookListStyle(book));
					}
				}
			}
			exceldownway.getCommonExcelListWay(book, fileName);
		} catch (Exception e) {
			try {
				getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * 分页查询派车成功列表
	 * 
	 * @return
	 */
	public String findPageSendSuccessList() {
		try {
			initMap();
			if (SessionUtils.getUser() == null) {
				return ERROR;
			}
			if (answerCountInfo == null) {
				answerCountInfo = new AnswerCountInfo();
			}
			if (StringUtils.isNotEmty(stime)) {
				answerCountInfo.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				answerCountInfo.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(blocid)) {
				answerCountInfo.setBlocid(Integer.parseInt(blocid));
			}
			if (StringUtils.isNotEmty(carnumber)) {
				answerCountInfo.setCarnumber(URLDecoder.decode(carnumber,
						"UTF-8").toUpperCase().trim());
			}
			if (StringUtils.isNotEmty(drivername)) {
				answerCountInfo.setDrivername(URLDecoder.decode(drivername,
						"UTF-8").trim());
			}
			result = answerCountService.findPageSendSuccessList(
					answerCountInfo, this.getPage(), this.getLimit());
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AnswerCountAction的方法 findPageSendSuccessList执行出错，原因："
					+ e, e);
			return ERROR;
		}
	}
	/**
	 * 导出派车成功数据列表
	 */
	public void excelExportSendSuccessList() {

		try {
			// 1.设置名字
			fileName = "派车成功统计报表";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,20,20,15,15,25,25";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			if (SessionUtils.getUser() == null) {
				return ;
			}
			if (answerCountInfo == null) {
				answerCountInfo = new AnswerCountInfo();
			}
			if (StringUtils.isNotEmty(stime)) {
				answerCountInfo.setStime(new String(stime.getBytes("ISO-8859-1"),"UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				answerCountInfo.setEtime(new String(etime.getBytes("ISO-8859-1"),"UTF-8"));
			}
			if (StringUtils.isNotEmty(blocid)) {
				answerCountInfo.setBlocid(Integer.parseInt(blocid));
			}
			if (StringUtils.isNotEmty(carnumber)) {
				answerCountInfo.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"),"UTF-8").toUpperCase().trim());
			}
			if (StringUtils.isNotEmty(drivername)) {
				answerCountInfo.setDrivername(new String(drivername.getBytes("ISO-8859-1"),"UTF-8").trim());
			}
			List<AnswerCountInfo> list= answerCountService.excelExportSendSuccessList(answerCountInfo);
			titleRow.createCell(0).setCellValue("序号");
			titleRow.createCell(1).setCellValue("集团名称");
			titleRow.createCell(2).setCellValue("车牌号");
			titleRow.createCell(3).setCellValue("当班司机姓名");
			titleRow.createCell(4).setCellValue("终端设备号");
			titleRow.createCell(5).setCellValue("订单ID");
			titleRow.createCell(6).setCellValue("创建时间");

			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.setHeightInPoints(15);
					AnswerCountInfo data = (AnswerCountInfo) list.get(i);
					contentRow.createCell(0).setCellValue(index);
					contentRow.createCell(1).setCellValue(data.getBlocname());
					contentRow.createCell(2).setCellValue(data.getCarnumber());
					contentRow.createCell(3).setCellValue(data.getDrivername());
					contentRow.createCell(4).setCellValue(data.getTerminal());
					contentRow.createCell(5).setCellValue(data.getOrderid());
					contentRow.createCell(6).setCellValue(data.getCreatetime());
					for (int m = 0; m < contentRow.getLastCellNum(); m++) {
						contentRow.getCell(m).setCellStyle(
								exceldownway.setBookListStyle(book));
					}
				}
			}
			exceldownway.getCommonExcelListWay(book, fileName);
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
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public AnswerCountService getAnswerCountService() {
		return answerCountService;
	}

	public void setAnswerCountService(AnswerCountService answerCountService) {
		this.answerCountService = answerCountService;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public AnswerCountInfo getAnswerCountInfo() {
		return answerCountInfo;
	}

	public void setAnswerCountInfo(AnswerCountInfo answerCountInfo) {
		this.answerCountInfo = answerCountInfo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBlocid() {
		return blocid;
	}

	public void setBlocid(String blocid) {
		this.blocid = blocid;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
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

	public String getZbstatus() {
		return zbstatus;
	}

	public void setZbstatus(String zbstatus) {
		this.zbstatus = zbstatus;
	}
}
