/**
 * Description: car-eye车辆管理平台
 * 文件名：CityInfoAction.java
 * 版本信息：1.0
 * 日期：2013-8-5
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.transaction.action;

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
import com.careye.car.domain.CarInfo;
import com.careye.car.service.CarService;
import com.careye.constant.WebConstants;
import com.careye.mq.HandleUtil;
import com.careye.system.domain.BlocUser;
import com.careye.transaction.domain.Transaction;
import com.careye.transaction.domain.TransactionAnswer;
import com.careye.transaction.domain.TransactionType;
import com.careye.transaction.service.TransactionService;
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.FileSizeUtil;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;
import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：TransactionAction
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2014-3-16 下午05:24:20
 * @修改人：huangqin
 * @修改时间：2013-3-16 下午05:24:20
 * @修改备注：
 * @version 1.0
 */
public class TransactionAction extends BasePageAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(BasePageAction.class);
	private TransactionService transactionService;
	private CarService carService;
	private Transaction transaction;
	private Transaction data;
	private Map<Object, Object> result;
	private String source;
	private String typeid;
	private String tratype;
	private String status;
	private String resstatus;
	private String carpool;

	private String stime;
	private String etime;
	private String stime1;
	private String etime1;
	private String stime2;
	private String etime2;
	private String ids;
	private String success;
	private BlocUser blocUser;
	private List<TransactionType> listType = new ArrayList<TransactionType>();
	private List<Transaction> list = new ArrayList<Transaction>();

	private TransactionType transactionType;
	private String typename;
	private String transactiontypeids;

	private String orderid;
	private String carnumber;
	private TransactionAnswer transactionAnswer;

	private String phone;
	private String fileName;

	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 获取最后一次约车信息
	 * 
	 * @return
	 */
	public String loadLastTransaction() {
		try {
			initMap();
			if (phone == null)
				return ERROR;
			data = transactionService.loadLastTransaction(phone);
			if (data == null) {
				data = new Transaction();
			} else {
				if (data.getTratype() == 2) {
					data.setUsetime(data.getAppointmenttime());
				}
			}
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CustomerAction 的方法 loadCustomer 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	private String calltype;

	/**
	 * 查询约车业务信息列表
	 * 
	 * @return
	 */
	public String queryTransactionList() {
		try {
			initMap();
			if (transaction == null) {
				transaction = new Transaction();
			}
			
			if (StringUtils.isNotEmty(source)) {
				transaction.setSource(Integer.parseInt(source));
			}
			if (StringUtils.isNotEmty(typeid)) {
				transaction.setTypeid(Integer.parseInt(typeid));
			}
			if (StringUtils.isNotEmty(tratype)) {
				transaction.setTratype(Integer.parseInt(tratype));
			}
			if (StringUtils.isNotEmty(status)) {
				transaction.setStatus(Integer.parseInt(status));
			}
			if (StringUtils.isNotEmty(orderid)) {
				transaction.setOrderid(orderid);
			}
			if (StringUtils.isNotEmty(phone)) {
				transaction.setPhone(phone);
			}
			if (StringUtils.isNotEmty(carpool)) {
				transaction.setCarpool(Integer.parseInt(carpool));
			}
			if (StringUtils.isNotEmty(resstatus)) {
				transaction.setResstatus(Integer.parseInt(resstatus));
			}
			if (StringUtils.isNotEmty(ids)) {
				String[] datas = ids.split(",");
				for (int j = 0; j < datas.length; j++) {
					transaction.getIds().add(Integer.parseInt(datas[j]));
				}
			}
			if (StringUtils.isNotEmty(stime)) {
				transaction.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				transaction.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(stime1)) {
				transaction.setStime1(URLDecoder.decode(stime1, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime1)) {
				transaction.setEtime1(URLDecoder.decode(etime1, "UTF-8"));
			}
			if (StringUtils.isNotEmty(stime2)) {
				transaction.setStime2(URLDecoder.decode(stime2, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime2)) {
				transaction.setEtime2(URLDecoder.decode(etime2, "UTF-8"));
			}
			if (StringUtils.isEmty(calltype)) {
				return ERROR;
			}
			transaction.setCalltype(Integer.parseInt(calltype));
			
			transaction.setUserid(SessionUtils.getUserId());
			
			result = transactionService.getAllTransaction(this.getPage(), this
					.getLimit(), transaction);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TransactionAction 的方法 queryTransactionList 执行出错，原因："
					+ e, e);
			return ERROR;
		}
	}

	/**
	 * 保存约车业务信息
	 * 
	 * @return
	 */
	public String saveTransaction() {
		try {
			initMap();
			if (transaction == null) {
				transaction = new Transaction();
			}
			int reFlag = 0;
			transaction.setUserid(SessionUtils.getUserId());
			if (transaction.getId() != null) {
				reFlag = transactionService.updateTransaction(transaction);
			} else {
				if (transaction.getTratype() == 1) { // 预约
					transaction.setAppointmenttime(transaction.getUsetime());
				}
				transaction.setOrderid("2" + DateUtil.getOrderid());
				transaction.setSource(2);
				reFlag = transactionService.addTransaction(transaction);
				if(StringUtils.isNotEmty(transaction.getCarNumbers())){	//指定召车
					String [] carnumberArr = transaction.getCarNumbers().split(",");
					for(String carNumber:carnumberArr){
						CarInfo car = carService.getCarInfoByCarNumber(carNumber);
						String terminal = car == null ? "0" : car.getTerminal();
						Integer devicetype = carService.getUserTypeByTerminal(terminal);
						if(!"0".equals(terminal)){
							
							HandleUtil.sendDial(devicetype,terminal, transaction.getOrderid(), 0,
									transaction.getTratype(), transaction.getUsernametwo(), transaction.getPhone(),
									transaction.getUsetime(),transaction.getRemark(), HandleUtil.getSerialId(),
									carNumber, transaction.getSaddress(), transaction.getEaddress(),
									Double.parseDouble(transaction.getSlat()), Double.parseDouble(transaction.getSlng())
									, Double.parseDouble(transaction.getElat()), Double.parseDouble(transaction.getElng()));
						}
					}
				}else{
					String terminal = "0";
					
					HandleUtil.sendDial(29,terminal, transaction.getOrderid(), 0,
							transaction.getTratype(), transaction.getUsernametwo(), transaction.getPhone(),
							transaction.getUsetime(),transaction.getRemark(), HandleUtil.getSerialId(),
							carnumber, transaction.getSaddress(), transaction.getEaddress(),
							Double.parseDouble(transaction.getSlat()), Double.parseDouble(transaction.getSlng())
							, Double.parseDouble(transaction.getElat()), Double.parseDouble(transaction.getElng()));
				}
			}
			result.put("su", reFlag);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("TransactionAction 的方法 saveTransaction 执行出错，原因：" + e,
					e);
			return ERROR;
		}
	}

	/**
	 * 删除约车业务信息
	 * 
	 * @return
	 */
	public String deleteTransaction() {
		try {
			initMap();
			if (ids == null)
				return ERROR;
			List<String> list = Arrays.asList(ids.split(","));
			result.put("su", transactionService.delTransaction(list));
			return SUCCESS;
		} catch (Exception e) {
			logger.error(
					"TransactionAction 的方法 deleteTransaction 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/************************************* 约车业务类型 *****************************************/
	/**
	 * 获取约车业务类型下拉列表
	 * 
	 * @return
	 */
	public String selTransactionTypeList() {
		try {
			initMap();
			listType = transactionService.selTransactionTypeList();
			result.put("list", listType);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TransactionAction的方法 selAreaList执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 查询约车业务类型信息列表
	 * 
	 * @return
	 */
	public String queryTransactionTypeList() {
		try {
			initMap();
			if (transactionType == null) {
				transactionType = new TransactionType();
			}
			if (StringUtils.isNotEmty(typename)) {
				transactionType.setTypename(URLDecoder
						.decode(typename, "UTF-8").trim());
			}
			// if(StringUtils.isNotEmty(stime)){
			// transactionType.setStime(URLDecoder.decode(stime, "UTF-8"));
			// }
			// if(StringUtils.isNotEmty(etime)){
			// transactionType.setEtime(URLDecoder.decode(etime, "UTF-8"));
			// }
			result = transactionService.getAllTransactionType(this.getPage(),
					this.getLimit(), transactionType);
			return SUCCESS;
		} catch (Exception e) {
			logger.error(
					"TransactionAction 的方法 queryTransactionTypeList 执行出错，原因："
							+ e, e);
			return ERROR;
		}
	}

	/**
	 * 添加约车业务类型信息
	 * 
	 * @return
	 */
	public String saveTransactionType() {
		try {
			initMap();
			if (transactionType == null) {
				transactionType = new TransactionType();
			}
			int reFlag = 0;
			this.blocUser = (BlocUser) ServletActionContext.getRequest()
					.getSession().getAttribute(WebConstants.LOGIN_USER);
			transactionType.setUserid(this.blocUser.getId());
			if (transactionType.getId() != null) {
				reFlag = transactionService
						.updateTransactionType(transactionType);
			} else {
				reFlag = transactionService.addTransactionType(transactionType);
			}
			result.put("su", reFlag);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("TransactionAction 的方法 saveTransactionType 执行出错，原因："
					+ e, e);
			return ERROR;
		}
	}

	/**
	 * 删除约车业务类型信息
	 * 
	 * @return
	 */
	public String deleteTransactionType() {
		try {
			initMap();
			if (ids == null)
				return ERROR;
			List<String> list = Arrays.asList(ids.split(","));
			result.put("su", transactionService.delTransactionType(list));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TransactionAction 的方法 deleteTransactionType 执行出错，原因："
					+ e, e);
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
			if (StringUtils.isEmty(calltype)) {
				return;
			} else if (2 == Integer.parseInt(calltype)) {
				fileName = "语音订单业务信息";
			} else {
				fileName = "约车业务信息";
			}
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,25,12,12,15,15,15,15,15,15,30,30,25,20,20,20,15,15,15,20,15,25,15,20";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			if (transaction == null) {
				transaction = new Transaction();
			}

			transaction.setUserid(SessionUtils.getUserId());
			if (StringUtils.isNotEmty(source)) {
				transaction.setSource(Integer.parseInt(source));
			}
			if (StringUtils.isNotEmty(typeid)) {
				transaction.setTypeid(Integer.parseInt(typeid));
			}
			if (StringUtils.isNotEmty(tratype)) {
				transaction.setTratype(Integer.parseInt(tratype));
			}
			if (StringUtils.isNotEmty(orderid)) {
				transaction.setOrderid(orderid);
			}
			if (StringUtils.isNotEmty(phone)) {
				transaction.setPhone(phone);
			}
			if (StringUtils.isNotEmty(status)) {
				transaction.setStatus(Integer.parseInt(status));
			}
			if (StringUtils.isNotEmty(carpool)) {
				transaction.setCarpool(Integer.parseInt(carpool));
			}
			if (StringUtils.isNotEmty(resstatus)) {
				transaction.setResstatus(Integer.parseInt(resstatus));
			}
			if (StringUtils.isNotEmty(stime)) {
				transaction.setStime(stime);
			}
			if (StringUtils.isNotEmty(etime)) {
				transaction.setEtime(etime);
			}
			if (StringUtils.isNotEmty(stime1)) {
				transaction.setStime1(stime1);
			}
			if (StringUtils.isNotEmty(etime1)) {
				transaction.setEtime1(etime1);
			}
			if (StringUtils.isNotEmty(stime2)) {
				transaction.setStime2(stime2);
			}
			if (StringUtils.isNotEmty(etime2)) {
				transaction.setEtime2(etime2);
			}
			transaction.setCalltype(Integer.parseInt(calltype));
			
			transaction.setUserid(SessionUtils.getUserId());

			list = transactionService.findTransactionPageList(transaction); // Excel查询

			titleRow.createCell(0).setCellValue("序号");// titleRow.setHeight((short)(20
														// * 15));
			titleRow.createCell(1).setCellValue("订单号");
			titleRow.createCell(2).setCellValue("是否抢答");
			titleRow.createCell(3).setCellValue("业务来源");
			titleRow.createCell(4).setCellValue("约车类型");
			titleRow.createCell(5).setCellValue("业务类型");
			titleRow.createCell(6).setCellValue("业务状态");
			titleRow.createCell(7).setCellValue("用户名称");
			
			titleRow.createCell(8).setCellValue("当班司机");
			
			titleRow.createCell(9).setCellValue("联系电话");
			titleRow.createCell(10).setCellValue("出发地");
			if (2 == Integer.parseInt(calltype)) {
				titleRow.createCell(11).setCellValue("语音地址");
			} else {
				titleRow.createCell(11).setCellValue("目的地");
			}
			titleRow.createCell(12).setCellValue("抢答成功车牌号");
			titleRow.createCell(13).setCellValue("抢答时间");
			titleRow.createCell(14).setCellValue("业务开始时间");
			titleRow.createCell(15).setCellValue("业务结束时间");
			titleRow.createCell(16).setCellValue("回拨乘客电话");
			titleRow.createCell(17).setCellValue("是否合乘");
			titleRow.createCell(18).setCellValue("合乘人数");
			titleRow.createCell(19).setCellValue("预约时间");
			titleRow.createCell(20).setCellValue("电召费(元)");
			titleRow.createCell(21).setCellValue("备注");
			titleRow.createCell(22).setCellValue("创建人");
			titleRow.createCell(23).setCellValue("创建时间");

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
					if(transaction.getResstatus()!=null){
						int resstatus = transaction.getResstatus();
						if (resstatus == 0) {
							resstatusStr = "未抢答";
						} else if (resstatus == 1) {
							resstatusStr = "抢答";
						}
					}
					
					contentRow.createCell(2).setCellValue(resstatusStr);
					String sourceStr = "";
					if(transaction.getSource()!=null){
					int source = transaction.getSource();
					if (source == 1) {
						sourceStr = "电召";
					} else if (source == 2) {
						sourceStr = "96106网站";
					} else if (source == 3) {
						sourceStr = "飞嘀";
					} else if (source == 4) {
						sourceStr = "服务窗";
					} else if (source == 5) {
						sourceStr = "微信";
					}
					}
					contentRow.createCell(3).setCellValue(sourceStr);
					contentRow.createCell(4).setCellValue(
							transaction.getTypename());
					String tratypeStr = null;
					if(transaction.getTratype()!=null){
					    int tratype = transaction.getTratype();
						if (tratype == 0) {
							tratypeStr = "即时召车";
						} else if (tratype == 1) {
							tratypeStr = "预约召车";
						} else if (tratype == 2) {
							tratypeStr = "车辆指派";
						} else {
							tratypeStr = "";
						}
					}
					contentRow.createCell(5).setCellValue(tratypeStr);
					String statusStr = "";
					if(transaction.getStatus()!=null){
					int status = transaction.getStatus();
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
						statusStr = "载客中";
					} else if (status == 6) {
						statusStr = "待支付";
					} else if (status == 7) {
						statusStr = "待评价";
					} else if (status == 8) {
						statusStr = "完成 ";
					}
					}
					contentRow.createCell(6).setCellValue(statusStr);
					contentRow.createCell(7).setCellValue(
							transaction.getUsernametwo());
					String phoneStr=transaction.getPhone();
					if(StringUtils.isEmty(phoneStr)){
						phoneStr="";
					}
					
					contentRow.createCell(8).setCellValue(transaction.getDrivername());
					
					contentRow.createCell(9).setCellValue(phoneStr);
					contentRow.createCell(10).setCellValue(
							transaction.getSaddress());
					
					if (2 == Integer.parseInt(calltype)) {
						contentRow.createCell(11).setCellValue(
								transaction.getFilepath());
					} else {
						contentRow.createCell(11).setCellValue(
								transaction.getEaddress());
					}
					contentRow.createCell(12).setCellValue(
							transaction.getCarnumber());
					contentRow.createCell(13).setCellValue(
							transaction.getAnswertime());
					contentRow.createCell(14).setCellValue(
							transaction.getStarttime());
					contentRow.createCell(15).setCellValue(
							transaction.getEndtime());

					String callbackphoneStr = null;
					if (transaction.getCallbackphone() != null) {
						int callbackphone = transaction.getCallbackphone();
						if (callbackphone == 0) {
							callbackphoneStr = "失败";
						} else if (callbackphone == 1) {
							callbackphoneStr = "成功";
						}
					}
					contentRow.createCell(16).setCellValue(callbackphoneStr);
					String carpoolStr = "";
					if(transaction.getCarpool()!=null){
					int carpool = transaction.getCarpool();
					if (carpool == 0) {
						carpoolStr = "不合乘";
					} else if (carpool == 1) {
						carpoolStr = "合乘";
					}
					}
					contentRow.createCell(17).setCellValue(carpoolStr);
					contentRow.createCell(18).setCellValue(
							transaction.getCarpoolpersonnum());
					contentRow.createCell(19).setCellValue(
							transaction.getAppointmenttime());
					contentRow.createCell(20).setCellValue(
							transaction.getCallfee());
					contentRow.createCell(21).setCellValue(
							transaction.getRemark());
					contentRow.createCell(22).setCellValue(
							transaction.getUsername());
					contentRow.createCell(23).setCellValue(
							transaction.getCreatetime());

//					for (int m = 0; m < contentRow.getLastCellNum(); m++) {
//						contentRow.getCell(m).setCellStyle(
//								exceldownway.setBookListStyle(book));
//					}
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

	/****** 抢单信息 ******/
	/**
	 * 查询约车业务信息列表
	 * 
	 * @return
	 */
	public String getTransactionAnswerList() {
		try {
			initMap();
			if (transactionAnswer == null) {
				transactionAnswer = new TransactionAnswer();
			}
			if (StringUtils.isNotEmty(orderid)) {
				transactionAnswer.setOrderid(URLDecoder
						.decode(orderid, "UTF-8"));
			}
			if (StringUtils.isNotEmty(carnumber)) {
				transactionAnswer.setCarnumber(URLDecoder.decode(carnumber,
						"UTF-8"));
			}
			result = transactionService.getAllTransactionAnswer(this.getPage(),
					this.getLimit(), transactionAnswer);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TransactionAction 的方法 queryTransactionList执行出错，原因："
					+ e, e);
			return ERROR;
		}
	}

	private String voiceurl;

	/**
	 * 播放语音
	 */
	public String downloadURLVoice() {
		try {
			initMap();
			if (StringUtils.isEmty(voiceurl)) {
				return ERROR;
			} else {
				voiceurl = URLDecoder.decode(voiceurl, "UTF-8");
			}
			String fn = voiceurl.substring(voiceurl.lastIndexOf("/") + 1);
			String voiceDir = SessionUtils.getSession().getServletContext().getRealPath("")+ "/download/voice/";
			FileSizeUtil.downLoadFromUrl(voiceurl, fn, voiceDir);
			result.put("fileName", fn.replace(".amr", ".mp3"));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TransactionAction 的方法 downloadURLVoice执行出错，原因：" + e,
					e);
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * Excel导出
	 * 
	 * @throws IOException
	 */
	public void exportAnswerList() {
		try {
			// 1.设置名字
			if (StringUtils.isEmty(calltype)) {
				return;
			} else if (2 == Integer.parseInt(calltype)) {
				fileName = "语音订单抢单明细表";
			} else {
				fileName = "业务订单抢单明细表";
			}
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,25,20,20,20,20,20,20,20,20,20,20,20,20,20,15,15,15,20,15,25,15,20,20,20,20,20";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			if (transactionAnswer == null) {
				transactionAnswer = new TransactionAnswer();
			}
			if (StringUtils.isNotEmty(orderid)) {
				transactionAnswer.setOrderid(URLDecoder
						.decode(orderid, "UTF-8"));
			}
			if (StringUtils.isNotEmty(carnumber)) {
				transactionAnswer.setCarnumber(URLDecoder.decode(carnumber,
						"UTF-8"));
			}

			List<TransactionAnswer> dataList = transactionService
					.exportTransactionAnswerList(transactionAnswer); // Excel查询

			titleRow.createCell(0).setCellValue("序号");
			titleRow.createCell(1).setCellValue("订单号");
			titleRow.createCell(2).setCellValue("集团");
			titleRow.createCell(3).setCellValue("车牌号");
			titleRow.createCell(4).setCellValue("终端设备号");
			titleRow.createCell(5).setCellValue("发送时间");
			titleRow.createCell(6).setCellValue("是否中标");
			titleRow.createCell(7).setCellValue("车辆当前状态");
			titleRow.createCell(8).setCellValue("设备类型");
			titleRow.createCell(9).setCellValue("车辆类型");
			titleRow.createCell(10).setCellValue("终端手机号");
			titleRow.createCell(11).setCellValue("车牌颜色");
			titleRow.createCell(12).setCellValue("省");
			titleRow.createCell(13).setCellValue("市");
			titleRow.createCell(14).setCellValue("县/区");
			titleRow.createCell(15).setCellValue("查询密码");
			titleRow.createCell(16).setCellValue("车辆用途");
			titleRow.createCell(17).setCellValue("驾驶员");
			titleRow.createCell(18).setCellValue("燃油种类");
			titleRow.createCell(19).setCellValue("车架号");
			titleRow.createCell(20).setCellValue("行驶证号");
			titleRow.createCell(21).setCellValue("变速箱号");
			titleRow.createCell(22).setCellValue("排气量");
			titleRow.createCell(23).setCellValue("发动机号");
			titleRow.createCell(24).setCellValue("购车日期");
//			titleRow.createCell(27).setCellValue("创建时间");

			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (dataList.size() > 0) {
				for (int i = 0; i < dataList.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.createCell(0).setCellValue(index);
					TransactionAnswer ta = (TransactionAnswer) dataList.get(i);
					contentRow.createCell(1).setCellValue(ta.getOrderid());
					contentRow.createCell(2).setCellValue(ta.getBlocname());
					contentRow.createCell(3).setCellValue(ta.getCarnumber());
					contentRow.createCell(4).setCellValue(ta.getTerminal());
					contentRow.createCell(5).setCellValue(ta.getSendtime());
					if(ta.getIsbidwin()==null){
						contentRow.createCell(6).setCellValue("");
					}else if(ta.getIsbidwin()==1){
						contentRow.createCell(6).setCellValue("未中标");
					} else if(ta.getIsbidwin()==2){
						contentRow.createCell(6).setCellValue("中标");
					}
					if (ta.getCarstatus() == null) {
						contentRow.createCell(7).setCellValue("");
					} else if (ta.getCarstatus() == 7) {
						contentRow.createCell(7).setCellValue("在线");
					} else if (ta.getCarstatus() == 1) {
						contentRow.createCell(7).setCellValue("长时间离线");
					} else if (ta.getCarstatus() == 2) {
						contentRow.createCell(7).setCellValue("离线");
					} else if (ta.getCarstatus() == 3) {
						contentRow.createCell(7).setCellValue("熄火");
					} else if (ta.getCarstatus() == 4) {
						contentRow.createCell(7).setCellValue("停车");
					} else if (ta.getCarstatus() == 5) {
						contentRow.createCell(7).setCellValue("行驶");
					} else if (ta.getCarstatus() == 6) {
						contentRow.createCell(7).setCellValue("报警");
					} else if (ta.getCarstatus() == 8) {
						contentRow.createCell(7).setCellValue("未定位");
					}
					contentRow.createCell(8).setCellValue(ta.getTypename());
					contentRow.createCell(9).setCellValue(ta.getCartypename());
					contentRow.createCell(10).setCellValue(ta.getPhone());
					contentRow.createCell(11).setCellValue(ta.getColor());
					contentRow.createCell(12).setCellValue(ta.getProvincename());
					contentRow.createCell(13).setCellValue(ta.getCityname());
					contentRow.createCell(14).setCellValue(ta.getDistrictname());
					contentRow.createCell(15).setCellValue(ta.getPassword());
					contentRow.createCell(16).setCellValue(ta.getUsename());
					contentRow.createCell(17).setCellValue(ta.getDrivername1());
					contentRow.createCell(18).setCellValue(ta.getOiltype());
					contentRow.createCell(19).setCellValue(ta.getFramenumber());
					contentRow.createCell(20).setCellValue(ta.getDrivlicnum());
					contentRow.createCell(21).setCellValue(ta.getTransnumber());
					contentRow.createCell(22).setCellValue(
							ta.getVentingmeasure());
					contentRow.createCell(23).setCellValue(ta.getEnginenumber());
					contentRow.createCell(24).setCellValue(ta.getBuytime());
//					contentRow.createCell(27).setCellValue(ta.getCreatetime());

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

	/*
	 * getter setter
	 */

	public String getVoiceurl() {
		return voiceurl;
	}

	public void setVoiceurl(String voiceurl) {
		this.voiceurl = voiceurl;
	}

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Map<Object, Object> getResult() {
		return result;
	}

	public void setResult(Map<Object, Object> result) {
		this.result = result;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTratype() {
		return tratype;
	}

	public void setTratype(String tratype) {
		this.tratype = tratype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResstatus() {
		return resstatus;
	}

	public void setResstatus(String resstatus) {
		this.resstatus = resstatus;
	}

	public String getStime1() {
		return stime1;
	}

	public void setStime1(String stime1) {
		this.stime1 = stime1;
	}

	public String getEtime1() {
		return etime1;
	}

	public void setEtime1(String etime1) {
		this.etime1 = etime1;
	}

	public String getStime2() {
		return stime2;
	}

	public void setStime2(String stime2) {
		this.stime2 = stime2;
	}

	public String getEtime2() {
		return etime2;
	}

	public void setEtime2(String etime2) {
		this.etime2 = etime2;
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

	public List<TransactionType> getListType() {
		return listType;
	}

	public void setListType(List<TransactionType> listType) {
		this.listType = listType;
	}

	public List<Transaction> getList() {
		return list;
	}

	public void setList(List<Transaction> list) {
		this.list = list;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTransactiontypeids() {
		return transactiontypeids;
	}

	public void setTransactiontypeids(String transactiontypeids) {
		this.transactiontypeids = transactiontypeids;
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

	public Transaction getData() {
		return data;
	}

	public void setData(Transaction data) {
		this.data = data;
	}

	public String getCarpool() {
		return carpool;
	}

	public void setCarpool(String carpool) {
		this.carpool = carpool;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public TransactionAnswer getTransactionAnswer() {
		return transactionAnswer;
	}

	public void setTransactionAnswer(TransactionAnswer transactionAnswer) {
		this.transactionAnswer = transactionAnswer;
	}

	public String getCalltype() {
		return calltype;
	}

	public void setCalltype(String calltype) {
		this.calltype = calltype;
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

}
