/**
 * Description: car-eye车辆管理平台
 * 文件名：OnCallCountAction.java
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
import com.careye.statement.oncalltransaction.domain.InBoundInfo;
import com.careye.statement.oncalltransaction.domain.InSystemInfo;
import com.careye.statement.oncalltransaction.domain.OnCallCountInfo;
import com.careye.statement.oncalltransaction.service.OnCallCountService;
import com.careye.transaction.domain.Transaction;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;
import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：OnCallCountAction
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-3 上午10:28:01
 * @修改人：Yuqk
 * @修改时间：2015-4-3 上午10:28:01
 * @修改备注：
 * @version 1.0
 */
public class OnCallCountAction extends BasePageAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5742771332123685101L;
	private static final Logger logger = Logger
			.getLogger(OnCallCountAction.class);
	private OnCallCountService onCallCountService;

	private String stime;
	private String etime;
	private String blocid;
	private String carnumber;
	private String drivername;
	private String cusname;

	private OnCallCountInfo onCallCountInfo;
	private String fileName;
	private Map result;

	private void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 查询所有电召统计数据
	 * 
	 * @return
	 */
	public String findOnCallCountData() {

		try {
			initMap();
			if (onCallCountInfo == null) {
				onCallCountInfo = new OnCallCountInfo();
			}
			if (StringUtils.isNotEmty(stime)) {
				onCallCountInfo.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				onCallCountInfo.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(blocid)) {
				onCallCountInfo.setBlocid(Integer.parseInt(blocid));
			}
			if (StringUtils.isNotEmty(carnumber)) {
				onCallCountInfo.setCarnumber(URLDecoder.decode(carnumber,
						"UTF-8").toUpperCase());
			}
			if (StringUtils.isNotEmty(drivername)) {
				onCallCountInfo.setDrivername(URLDecoder.decode(drivername,
						"UTF-8"));
			}
			if (StringUtils.isNotEmty(cusname)) {
				onCallCountInfo.setCusname(URLDecoder.decode(cusname, "UTF-8"));
			}
			if (SessionUtils.getUser() == null) {
				return SUCCESS;
			}
			result.put("list", onCallCountService
					.findOnCallCountData(onCallCountInfo));
			return SUCCESS;
		} catch (Exception e) {
			logger.error(
					"OnCallCountAction的方法 findOnCallCountData执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 导出电召统计数据
	 */
	public void exportOnCallCountData() {
		try {
			initMap();
			if (onCallCountInfo == null) {
				onCallCountInfo = new OnCallCountInfo();
			}
			if (StringUtils.isNotEmty(stime)) {
				onCallCountInfo.setStime(new String(stime
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				onCallCountInfo.setEtime(new String(etime
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
			if (StringUtils.isNotEmty(blocid)) {
				onCallCountInfo.setBlocid(Integer.parseInt(blocid));
			}
			if (StringUtils.isNotEmty(carnumber)) {
				onCallCountInfo.setCarnumber(new String(carnumber
						.getBytes("ISO-8859-1"), "UTF-8").toUpperCase());
			}
			if (StringUtils.isNotEmty(drivername)) {
				onCallCountInfo.setDrivername(new String(drivername
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
			if (StringUtils.isNotEmty(cusname)) {
				onCallCountInfo.setCusname(new String(cusname
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
			if (SessionUtils.getUser() == null) {
				return;
			}
			List<OnCallCountInfo> list = onCallCountService
					.findOnCallCountData(onCallCountInfo);

			// 1.设置名字
			fileName = "电召统计报表";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,15,25";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			titleRow.createCell(0).setCellValue("序号");
			titleRow.createCell(1).setCellValue("统计类型");
			titleRow.createCell(2).setCellValue("统计数量");

			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.setHeightInPoints(15);
					OnCallCountInfo data = (OnCallCountInfo) list.get(i);
					contentRow.createCell(0).setCellValue(index);
					String cv = "";
					if (data.getCounttype() != null) {
						switch (data.getCounttype()) {
						case 1:
							cv = "拨入电话数量";
							break;
						case 2:
							cv = "接入系统数量";
							break;
						case 3:
							cv = "下发业务数量";
							break;
						case 4:
							cv = "抢答业务数量";
							break;
						case 5:
							cv = "派车成功数量";
							break;
						case 6:
							cv = "客户取消数量";
							break;
						case 7:
							cv = "司机取消数量";
							break;
						case 8:
							cv = "客户违约数量";
							break;
						case 9:
							cv = "司机违约数量";
							break;
						}
					}
					contentRow.createCell(1).setCellValue(cv);
					contentRow.createCell(2)
							.setCellValue(data.getCountnumber());
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
				logger.error(
						"OnCallCountAction的方法 exportOnCallCountData执行出错，原因："
								+ e, e);
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 查询所有电召拨入电话记录
	 * 
	 * @return
	 */
	public String findInBoundList() {

		try {
			initMap();
			if (onCallCountInfo == null) {
				onCallCountInfo = new OnCallCountInfo();
			}
			if (StringUtils.isNotEmty(stime)) {
				onCallCountInfo.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				onCallCountInfo.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(cusname)) {
				onCallCountInfo.setCusname(URLDecoder.decode(cusname, "UTF-8"));
			}
			if (SessionUtils.getUser() == null) {
				return SUCCESS;
			}
			result = onCallCountService.findInBoundPageList(this.getPage(),
					this.getLimit(), onCallCountInfo);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("OnCallCountAction的方法 findInBoundList执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 导出电召拨入电话记录
	 */
	public void exportInBoundList() {
		try {
			initMap();
			if (onCallCountInfo == null) {
				onCallCountInfo = new OnCallCountInfo();
			}
			if (StringUtils.isNotEmty(stime)) {
				onCallCountInfo.setStime(new String(stime
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				onCallCountInfo.setEtime(new String(etime
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
			if (StringUtils.isNotEmty(cusname)) {
				onCallCountInfo.setCusname(new String(cusname
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
			if (SessionUtils.getUser() == null) {
				return;
			}
			List<InBoundInfo> list = onCallCountService
					.exportInBoundList(onCallCountInfo);

			// 1.设置名字
			fileName = "拨入电话记录报表";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,15,25,25,25";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			titleRow.createCell(0).setCellValue("序号");
			titleRow.createCell(1).setCellValue("来电号码");
			titleRow.createCell(2).setCellValue("客户名称");
			titleRow.createCell(3).setCellValue("来电时间");
			titleRow.createCell(4).setCellValue("挂机时间");

			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.setHeightInPoints(15);
					InBoundInfo data = (InBoundInfo) list.get(i);
					contentRow.createCell(0).setCellValue(index);
					contentRow.createCell(1).setCellValue(data.getCallnumber());
					contentRow.createCell(2).setCellValue(data.getCusname());
					contentRow.createCell(3).setCellValue(
							data.getInboundcalltime());
					contentRow.createCell(4).setCellValue(
							data.getHangupcalltime());
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
				logger.error("OnCallCountAction的方法 exportInBoundList执行出错，原因："
						+ e, e);
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 查询所有电召接入系统记录
	 * 
	 * @return
	 */
	public String findInSystemList() {

		try {
			initMap();
			if (onCallCountInfo == null) {
				onCallCountInfo = new OnCallCountInfo();
			}
			if (StringUtils.isNotEmty(stime)) {
				onCallCountInfo.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				onCallCountInfo.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(cusname)) {
				onCallCountInfo.setCusname(URLDecoder.decode(cusname, "UTF-8"));
			}
			if (SessionUtils.getUser() == null) {
				return SUCCESS;
			}
			result = onCallCountService.findInSystemPageList(this.getPage(),
					this.getLimit(), onCallCountInfo);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("OnCallCountAction的方法 findInBoundList执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 导出电召接入系统记录
	 */
	public void exportInSystemList() {
		try {
			initMap();
			if (onCallCountInfo == null) {
				onCallCountInfo = new OnCallCountInfo();
			}
			if (StringUtils.isNotEmty(stime)) {
				onCallCountInfo.setStime(new String(stime
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				onCallCountInfo.setEtime(new String(etime
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
			if (StringUtils.isNotEmty(cusname)) {
				onCallCountInfo.setCusname(new String(cusname
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
			if (SessionUtils.getUser() == null) {
				return;
			}
			List<InSystemInfo> list = onCallCountService
					.exportInSystemList(onCallCountInfo);

			// 1.设置名字
			fileName = "拨入电话记录报表";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,15,25,25,25";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			titleRow.createCell(0).setCellValue("序号");
			titleRow.createCell(1).setCellValue("来电号码");
			titleRow.createCell(2).setCellValue("客户名称");
			titleRow.createCell(3).setCellValue("来电时间");
			titleRow.createCell(4).setCellValue("挂机时间");

			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.setHeightInPoints(15);
					InSystemInfo data = (InSystemInfo) list.get(i);
					contentRow.createCell(0).setCellValue(index);
					contentRow.createCell(1).setCellValue(data.getCallnumber());
					contentRow.createCell(2).setCellValue(data.getCusname());
					contentRow.createCell(3).setCellValue(
							data.getInboundcalltime());
					contentRow.createCell(4).setCellValue(
							data.getHangupcalltime());
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
				logger.error("OnCallCountAction的方法 exportInBoundList执行出错，原因："
						+ e, e);
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 查询所有业务记录
	 * 
	 * @return
	 */
	public String findTransactionList() {

		try {
			initMap();
			if (onCallCountInfo == null) {
				onCallCountInfo = new OnCallCountInfo();
			}
			if (StringUtils.isNotEmty(stime)) {
				onCallCountInfo.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				onCallCountInfo.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(blocid)) {
				onCallCountInfo.setBlocid(Integer.parseInt(blocid));
			}
			if (StringUtils.isNotEmty(carnumber)) {
				onCallCountInfo.setCarnumber(URLDecoder.decode(carnumber,
						"UTF-8").toUpperCase());
			}
			if (StringUtils.isNotEmty(drivername)) {
				onCallCountInfo.setDrivername(URLDecoder.decode(drivername,
						"UTF-8"));
			}
			if (StringUtils.isNotEmty(cusname)) {
				onCallCountInfo.setCusname(URLDecoder.decode(cusname, "UTF-8"));
			}
			if (SessionUtils.getUser() == null) {
				return SUCCESS;
			}
			result = onCallCountService.findTransactionList(onCallCountInfo,this.getPage(),this.getLimit());
			return SUCCESS;
		} catch (Exception e) {
			logger.error(
					"OnCallCountAction的方法 findOnCallCountData执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 导出业务记录
	 */
	public void exportTransactionList() {
		try {
			initMap();
			if (onCallCountInfo == null) {
				onCallCountInfo = new OnCallCountInfo();
			}
			if (StringUtils.isNotEmty(stime)) {
				onCallCountInfo.setStime(new String(stime
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				onCallCountInfo.setEtime(new String(etime
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
			if (StringUtils.isNotEmty(blocid)) {
				onCallCountInfo.setBlocid(Integer.parseInt(blocid));
			}
			if (StringUtils.isNotEmty(carnumber)) {
				onCallCountInfo.setCarnumber(new String(carnumber
						.getBytes("ISO-8859-1"), "UTF-8").toUpperCase());
			}
			if (StringUtils.isNotEmty(drivername)) {
				onCallCountInfo.setDrivername(new String(drivername
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
			if (StringUtils.isNotEmty(cusname)) {
				onCallCountInfo.setCusname(new String(cusname
						.getBytes("ISO-8859-1"), "UTF-8"));
			}
			if (SessionUtils.getUser() == null) {
				return;
			}
			List<Transaction> list = onCallCountService
					.exportTransactionList(onCallCountInfo);

			// 1.设置名字
			fileName = "电召统计报表";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,25,12,12,15,15,15,15,15,30,25,20,20,20,15,15,15,20,15,25,15,20";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			titleRow.createCell(0).setCellValue("序号");
			titleRow.createCell(1).setCellValue("订单号");
			titleRow.createCell(2).setCellValue("是否抢答");
			titleRow.createCell(3).setCellValue("业务来源");
			titleRow.createCell(4).setCellValue("约车类型");
			titleRow.createCell(5).setCellValue("业务类型");
			titleRow.createCell(6).setCellValue("业务状态");
			titleRow.createCell(7).setCellValue("用户名称");
			titleRow.createCell(8).setCellValue("联系电话");
			titleRow.createCell(9).setCellValue("出发地");
//			if (2 == Integer.parseInt(calltype)) {
//				titleRow.createCell(10).setCellValue("语音地址");
//			} else {
//				titleRow.createCell(10).setCellValue("目的地");
//			}
			titleRow.createCell(10).setCellValue("抢答成功车牌号");
			titleRow.createCell(11).setCellValue("抢答时间");
			titleRow.createCell(12).setCellValue("业务开始时间");
			titleRow.createCell(13).setCellValue("业务结束时间");
			titleRow.createCell(14).setCellValue("回拨乘客电话");
			titleRow.createCell(15).setCellValue("是否合乘");
			titleRow.createCell(16).setCellValue("合乘人数");
			titleRow.createCell(17).setCellValue("预约时间");
			titleRow.createCell(18).setCellValue("电召费(元)");
			titleRow.createCell(19).setCellValue("备注");
			titleRow.createCell(20).setCellValue("创建人");
			titleRow.createCell(21).setCellValue("创建时间");
			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.createCell(0).setCellValue(index);
					Transaction transaction = (Transaction) list.get(i);
					contentRow.createCell(1).setCellValue(
							transaction.getOrderid());
					String resstatusStr = "";
					int resstatus = transaction.getResstatus();
					if (resstatus == 0) {
						resstatusStr = "未抢答";
					} else if (resstatus == 1) {
						resstatusStr = "抢答";
					}
					contentRow.createCell(2).setCellValue(resstatusStr);
					int source = transaction.getSource();
					String sourceStr = "";
					if (source == 1) {
						sourceStr = "电召";
					} else if (source == 2) {
						sourceStr = "96106网站";
					} else if (source == 3) {
						sourceStr = "飞嘀";
					} else if (source == 4) {
						sourceStr = "服务窗";
					}
					contentRow.createCell(3).setCellValue(sourceStr);
					contentRow.createCell(4).setCellValue(
							transaction.getTypename());
					int tratype = transaction.getTratype();
					String tratypeStr = "";
					if (tratype == 0) {
						tratypeStr = "即派订单";
					} else if (tratype == 1) {
						tratypeStr = "预约订单";
					}
					contentRow.createCell(5).setCellValue(tratypeStr);
					int status = transaction.getStatus();
					String statusStr = "";
					if (status == 0) {
						statusStr = "无应答";
					} else if (status == 1) {
						statusStr = "调派中";
					} else if (status == 2) {
						statusStr = "已调派";
					} else if (status == 3) {
						statusStr = "取消";
					} else if (status == 4) {
						statusStr = "超时 ";
					} else if (status == 5) {
						statusStr = "完成 ";
					}
					contentRow.createCell(6).setCellValue(statusStr);
					contentRow.createCell(7).setCellValue(
							transaction.getUsernametwo());
					contentRow.createCell(8).setCellValue(
							transaction.getPhone());
					contentRow.createCell(9).setCellValue(
							transaction.getSaddress());
//					if (2 == Integer.parseInt(calltype)) {
//						contentRow.createCell(10).setCellValue(
//								transaction.getFilepath());
//					} else {
//						contentRow.createCell(10).setCellValue(
//								transaction.getEaddress());
//					}
					contentRow.createCell(10).setCellValue(
							transaction.getCarnumber());
					contentRow.createCell(11).setCellValue(
							transaction.getAnswertime());
					contentRow.createCell(12).setCellValue(
							transaction.getStarttime());
					contentRow.createCell(13).setCellValue(
							transaction.getEndtime());

					String callbackphoneStr = "";
					if (transaction.getCallbackphone() != null) {
						int callbackphone = transaction.getCallbackphone();
						if (callbackphone == 0) {
							callbackphoneStr = "失败";
						} else if (callbackphone == 1) {
							callbackphoneStr = "成功";
						}
					}
					contentRow.createCell(14).setCellValue(callbackphoneStr);
					String carpoolStr = "";
					int carpool = transaction.getCarpool();
					if (carpool == 0) {
						carpoolStr = "不合乘";
					} else if (carpool == 1) {
						carpoolStr = "合乘";
					}
					contentRow.createCell(15).setCellValue(carpoolStr);
					contentRow.createCell(16).setCellValue(
							transaction.getCarpoolpersonnum());
					contentRow.createCell(17).setCellValue(
							transaction.getAppointmenttime());
					contentRow.createCell(18).setCellValue(
							transaction.getCallfee());
					contentRow.createCell(19).setCellValue(
							transaction.getRemark());
					contentRow.createCell(20).setCellValue(
							transaction.getUsername());
					contentRow.createCell(21).setCellValue(
							transaction.getCreatetime());

					for (int m = 0; m < contentRow.getLastCellNum(); m++) {
						contentRow.getCell(m).setCellStyle(
								exceldownway.setBookListStyle(book));
					}
				}
			}
			exceldownway.getCommonExcelListWay(book, fileName);
		} catch (Exception e) {
			try {
				e.printStackTrace();
				getResponse()
						.getWriter()
						.print(
								"<script language=javascript>alert('Error!');history.back();</script>");
			} catch (IOException e1) {
				logger.error(
						"OnCallCountAction的方法 exportOnCallCountData执行出错，原因："
								+ e, e);
				e1.printStackTrace();
			}
		}
	}

	/*
	 * getter setter
	 */
	public OnCallCountService getOnCallCountService() {
		return onCallCountService;
	}

	public void setOnCallCountService(OnCallCountService onCallCountService) {
		this.onCallCountService = onCallCountService;
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

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
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

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}

	public OnCallCountInfo getOnCallCountInfo() {
		return onCallCountInfo;
	}

	public void setOnCallCountInfo(OnCallCountInfo onCallCountInfo) {
		this.onCallCountInfo = onCallCountInfo;
	}
}
