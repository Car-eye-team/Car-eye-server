package com.careye.customer.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.careye.base.action.BasePageAction;
import com.careye.constant.WebConstants;
import com.careye.customer.domain.ComplaintCount;
import com.careye.customer.domain.ComplaintInfo;
import com.careye.customer.domain.ComplaintType;
import com.careye.customer.service.ComplaintService;
import com.careye.system.domain.Bloc;
import com.careye.system.service.OrgazicationDeptService;
import com.careye.transaction.domain.EvaluateCount;
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.ExportToWord;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;
import common.Logger;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-12 下午05:29:48
 * @修改人：ll
 * @修改时间：2016-5-12 下午05:29:48
 * @修改备注：
 * @version 1.0
 */
public class ComplaintAction extends BasePageAction{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ComplaintAction.class);
	
	private ComplaintService complaintService;
	private OrgazicationDeptService orgazicationDeptService;
	private ComplaintType complaintType;
	private ComplaintInfo complaintInfo;
	private ComplaintCount complaintCount;
	
	private String typename;
	
	private String carnumber;
	private String type;
	private String dealstatus;
	private String stime;
	private String etime;
	
	private String blocid;
	private String year;
	private String month;
	private String day;
	private String datetime;
	
	private String ids;
	private String success;
	private Map<Object, Object> result;
	private String fileName;
	
	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 查询所有投诉类型列表
	 * 
	 * @return
	 */
	public String getAllComplaintTypeList() {
		try {
			initMap();
			
			List list = complaintService.getAllComplaintTypeList();

			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("ComplaintAction 的方法 getAllComplaintTypeList 执行出错，原因：" + e,e);
			return ERROR;
		}
	}
	
	/**
	 * 查询投诉类型列表
	 * 
	 * @return
	 */
	public String getComplaintTypeList() {
		try {
			initMap();
			if (complaintType == null) {
				complaintType = new ComplaintType();
			}
			if (StringUtils.isNotEmty(typename)) {
				complaintType.setTypename(URLDecoder.decode(typename, "UTF-8"));
			}

			result = complaintService.getComplaintTypeList(this.getPage(), this
					.getLimit(), complaintType);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("ComplaintAction 的方法 getComplaintTypeList 执行出错，原因：" + e,e);
			return ERROR;
		}
	}
	
	/**
	 * 保存投诉类型
	 * 
	 * @return
	 */
	public String saveComplaintType() {
		try {
			initMap();
			if (complaintType == null) {
				return ERROR;
			}
			complaintType.setCreatetime(DateUtil.getSQLDate());
			result.put("su", complaintService.saveComplaintType(complaintType));
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("ComplaintAction 的方法 saveComplaintType 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 删除投诉类型
	 * 
	 * @return
	 */
	public String delComplaintType() {
		try {
			initMap();
			if (ids == null){
				return ERROR;
			}
			List<String> list = Arrays.asList(ids.split(","));
			result.put("su", complaintService.delComplaintType(list));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("ComplaintAction 的方法 delComplaintType 执行出错，原因：" + e,e);
			return ERROR;
		}
	}
	
	
	/**
	 * 查询投诉列表
	 * 
	 * @return
	 */
	public String getComplaintInfoList() {
		try {
			initMap();
			if (complaintInfo == null) {
				complaintInfo = new ComplaintInfo();
			}
			if (StringUtils.isNotEmty(carnumber)) {
				complaintInfo.setCarnumber(URLDecoder.decode(carnumber, "UTF-8"));
			}
			if (StringUtils.isNotEmty(type)) {
				complaintInfo.setType(URLDecoder.decode(type, "UTF-8"));
			}
			if (StringUtils.isNotEmty(dealstatus)) {
				complaintInfo.setDealstatus(Integer.parseInt(dealstatus));
			}
			if (StringUtils.isNotEmty(stime)) {
				complaintInfo.setStime(stime);
			}
			if (StringUtils.isNotEmty(etime)) {
				complaintInfo.setEtime(etime);
			}

			result = complaintService.getComplaintInfoList(this.getPage(), this
					.getLimit(), complaintInfo);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("ComplaintAction 的方法 getComplaintInfoList 执行出错，原因：" + e,e);
			return ERROR;
		}
	}
	
	/**
	 * 删除投诉
	 * 
	 * @return
	 */
	public String delComplaintInfo() {
		try {
			initMap();
			if (ids == null){
				return ERROR;
			}
			List<String> list = Arrays.asList(ids.split(","));
			result.put("su", complaintService.delComplaintInfo(list));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("ComplaintAction 的方法 delComplaintInfo 执行出错，原因：" + e,e);
			return ERROR;
		}
	}
	
	
	/**
	 * 投诉处理
	 * 
	 * @return
	 */
	public String dealComplaintInfo() {
		try {
			initMap();
			if (complaintInfo == null) {
				return ERROR;
			}
			if(SessionUtils.getUser() == null){
				return ERROR;
			}else{
				complaintInfo.setDealman(SessionUtils.getUserId()+"");
			}
			complaintInfo.setDealstatus(2);
			complaintInfo.setDealtime(DateUtil.getSQLDate());
			
			complaintService.dealComplaintInfo(complaintInfo);
			
			result.put("su", 0);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("ComplaintAction 的方法 dealComplaintInfo 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 投诉Excel导出
	 * 
	 * @throws IOException
	 */
	public void exportExcel() {
		try {
			// 1.设置名字
			fileName = "投诉信息";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			
			if (complaintInfo == null) {
				complaintInfo = new ComplaintInfo();
			}
			if (StringUtils.isNotEmty(carnumber)) {
				complaintInfo.setCarnumber(new String(carnumber.getBytes("iso8859-1"), "utf-8").trim());
			}
			if (StringUtils.isNotEmty(type)) {
				complaintInfo.setType(URLDecoder.decode(type, "UTF-8"));
			}
			if (StringUtils.isNotEmty(dealstatus)) {
				complaintInfo.setDealstatus(Integer.parseInt(dealstatus));
			}
			if (StringUtils.isNotEmty(stime)) {
				complaintInfo.setStime(stime);
			}
			if (StringUtils.isNotEmty(etime)) {
				complaintInfo.setEtime(etime);
			}

			List<ComplaintInfo> list = complaintService.exportExcel(complaintInfo); // Excel查询
			if(list == null){
				list = new ArrayList<ComplaintInfo>();
			}

			titleRow.createCell(0).setCellValue("序号");// titleRow.setHeight((short)(20
														// * 15));
			titleRow.createCell(1).setCellValue("用户手机号");
			titleRow.createCell(2).setCellValue("企业名称");
			titleRow.createCell(3).setCellValue("车牌号");
			titleRow.createCell(4).setCellValue("司机名称");
			titleRow.createCell(5).setCellValue("司机手机号");
			titleRow.createCell(6).setCellValue("投诉类型");
			titleRow.createCell(7).setCellValue("投诉描述");
			titleRow.createCell(8).setCellValue("乘客姓名");
			titleRow.createCell(9).setCellValue("乘客手机号");
			titleRow.createCell(10).setCellValue("订单号");
			titleRow.createCell(11).setCellValue("投诉时间");
			titleRow.createCell(12).setCellValue("处理状态");
			titleRow.createCell(13).setCellValue("处理时间");
			titleRow.createCell(14).setCellValue("处理人");
			titleRow.createCell(15).setCellValue("处理内容");

			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.createCell(0).setCellValue(index);
					ComplaintInfo data = (ComplaintInfo) list.get(i);
					contentRow.createCell(1).setCellValue(data.getPhone());
					contentRow.createCell(2).setCellValue(data.getBlocname());
					contentRow.createCell(3).setCellValue(data.getCarnumber());
					contentRow.createCell(4).setCellValue(data.getDrivername());
					contentRow.createCell(5).setCellValue(data.getDriverphone());
					contentRow.createCell(6).setCellValue(data.getTypename());
					contentRow.createCell(7).setCellValue(data.getRemark());
					contentRow.createCell(8).setCellValue(data.getPassengername());
					contentRow.createCell(9).setCellValue(data.getPassengerphone());
					contentRow.createCell(10).setCellValue(data.getOrderid());
					contentRow.createCell(11).setCellValue(data.getComplainttime());
					String dealstatusStr = "";
					Integer dealstatusInt = data.getDealstatus();
					if(dealstatusInt == null){
					}else if(dealstatusInt == 1){
						dealstatusStr = "未处理";
					}else if(dealstatusInt == 2){
						dealstatusStr = "已处理";
					}
					contentRow.createCell(12).setCellValue(dealstatusStr);
					contentRow.createCell(13).setCellValue(data.getDealtime());
					contentRow.createCell(14).setCellValue(data.getDealman());
					contentRow.createCell(15).setCellValue(data.getDealcontent());

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
	 * 投诉统计列表
	 * 
	 * @return
	 */
	public String getComplaintCountList() {
		try {
			initMap();
			if (complaintCount == null) {
				complaintCount = new ComplaintCount();
			}
			if (StringUtils.isNotEmty(blocid)) {
				complaintCount.setBlocid(Integer.parseInt(blocid));
				
				Bloc bloc = orgazicationDeptService.quertDeptById(Integer.parseInt(blocid));
				complaintCount.setBlocname(bloc.getBloc_name());
			}
			if(StringUtils.isNotEmty(carnumber)){
				complaintCount.setCarnumber(URLDecoder.decode(carnumber, "UTF-8"));
			}
			if(StringUtils.isNotEmty(year)){
				complaintCount.setYear(URLDecoder.decode(year, "UTF-8"));
			}
			if(StringUtils.isNotEmty(month)){
				complaintCount.setMonth(URLDecoder.decode(month, "UTF-8"));
			}
			
			List<ComplaintCount> comList = null;
			if(StringUtils.isNotEmty(year)){
				comList =complaintService.getAllComplaintCountByYear(complaintCount);
			}else{
				if(StringUtils.isEmty(month)){
					month=DateUtil.getCurMonth();
				}
				complaintCount.setMonth(month);
				comList =complaintService.getAllComplaintCountByMonth(complaintCount);
				if(comList != null && comList.size() > 0){
					for(ComplaintCount info : comList){
						info.setBlocname(complaintCount.getBlocname());
					}
				}
			}
			result.put("list", comList);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("ComplaintAction 的方法 getComplaintCountList 执行出错，原因：" + e,e);
			return ERROR;
		}
	}
	
	/**
	 * 投诉统计Excel导出
	 * 
	 * @throws IOException
	 */
	public void exportExcelCount() {
		try {
			// 1.设置名字
			fileName = "投诉统计";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,20,20,20";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			
			if (complaintCount == null) {
				complaintCount = new ComplaintCount();
			}
			if (StringUtils.isNotEmty(blocid)) {
				complaintCount.setBlocid(Integer.parseInt(blocid));
				
				Bloc bloc = orgazicationDeptService.quertDeptById(Integer.parseInt(blocid));
				complaintCount.setBlocname(bloc.getBloc_name());
			}
			if (StringUtils.isNotEmty(carnumber)) {
				complaintCount.setCarnumber(new String(carnumber.getBytes("iso8859-1"), "utf-8").trim());
			}
			if(StringUtils.isNotEmty(year)){
				complaintCount.setYear(URLDecoder.decode(year, "UTF-8"));
			}
			if(StringUtils.isNotEmty(month)){
				complaintCount.setMonth(URLDecoder.decode(month, "UTF-8"));
			}
			
			List<ComplaintCount> comList = null;
			if(StringUtils.isNotEmty(year)){
				comList =complaintService.getAllComplaintCountByYear(complaintCount);
			}else{
				if(StringUtils.isEmty(month)){
					month=DateUtil.getCurMonth();
				}
				complaintCount.setMonth(month);
				comList =complaintService.getAllComplaintCountByMonth(complaintCount);
				if(comList != null && comList.size() > 0){
					for(ComplaintCount info : comList){
						info.setBlocname(complaintCount.getBlocname());
					}
				}
			}
			if(comList == null){
				comList = new ArrayList<ComplaintCount>();
			}

			titleRow.createCell(0).setCellValue("序号");// titleRow.setHeight((short)(20
														// * 15));
			titleRow.createCell(1).setCellValue("日期");
			titleRow.createCell(2).setCellValue("企业名称");
			titleRow.createCell(3).setCellValue("投诉数");

			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (comList.size() > 0) {
				for (int i = 0; i < comList.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.createCell(0).setCellValue(index);
					ComplaintCount data = (ComplaintCount) comList.get(i);
					contentRow.createCell(1).setCellValue(data.getDatetime());
					contentRow.createCell(2).setCellValue(data.getBlocname());
					contentRow.createCell(3).setCellValue(data.getCount());

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
	 * 投诉统计详情
	 * 
	 * @return
	 */
	public String getComplaintCountDetails() {
		try {
			initMap();
			if (complaintCount == null) {
				complaintCount = new ComplaintCount();
			}
			if (StringUtils.isNotEmty(blocid)) {
				complaintCount.setBlocid(Integer.parseInt(blocid));
			}
			if(StringUtils.isNotEmty(carnumber)){
				complaintCount.setCarnumber(URLDecoder.decode(carnumber, "UTF-8"));
			}
			if(StringUtils.isNotEmty(datetime)){
				if(datetime.length() == 7){
					complaintCount.setMonth(URLDecoder.decode(datetime, "UTF-8"));
				}else if(datetime.length() == 10){
					complaintCount.setDay(URLDecoder.decode(datetime, "UTF-8"));
				}
			}
			
			result = complaintService.getComplaintCountDetails(this.getPage(), this.getLimit(), complaintCount);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("ComplaintAction 的方法 getComplaintCountDetails 执行出错，原因：" + e,e);
			return ERROR;
		}
	}
	
	
	/**
	 * 投诉统计word导出
	 * @return
	 */
	public void exportWord(){
		try {
			initMap();
		    Map<String,Object> dataMap=new HashMap<String,Object>();
		    dataMap.put("title", "投诉统计");
		    dataMap.put("condition", "投诉数据统计如下：");
		    dataMap.put("title1", "投诉明细：");
		    if (complaintCount == null) {
				complaintCount = new ComplaintCount();
			}
			
		    
			if(StringUtils.isNotEmty(blocid)){
				complaintCount.setBlocid(Integer.parseInt(blocid));
				Bloc bloc = orgazicationDeptService.quertDeptById(Integer.parseInt(blocid));
				complaintCount.setBlocname(bloc.getBloc_name());
				
				dataMap.put("bname", "企业"+"【"+bloc.getBloc_name()+"】"+",");
			}else{
				dataMap.put("bname", "");
			}
			if(StringUtils.isNotEmty(carnumber)){
				complaintCount.setCarnumber(new String(carnumber.getBytes("iso8859-1"), "utf-8").trim());
				dataMap.put("carnum", "车牌号"+"【"+new String(carnumber.getBytes("ISO-8859-1"),"utf-8")+"】"+",");
			}else{
				dataMap.put("carnum", "");
			}
			if(StringUtils.isNotEmty(year)){
				complaintCount.setYear(URLDecoder.decode(year, "UTF-8"));
				dataMap.put("time", year.substring(0,4)+"年"+",");
			}
			if(StringUtils.isNotEmty(month)){
				complaintCount.setMonth(URLDecoder.decode(month, "UTF-8"));
				dataMap.put("time", month.substring(0,4)+"年"+month.substring(5,7)+"月"+",");
			}
			
			if(StringUtils.isEmty(year) && StringUtils.isEmty(month)){
				dataMap.put("time", " ");
			}
			
			
			List<ComplaintCount> comList = null;
			if(StringUtils.isNotEmty(year)){
				comList =complaintService.getAllComplaintCountByYear(complaintCount);
			}else{
				if(StringUtils.isEmty(month)){
					month=DateUtil.getCurMonth();
				}
				complaintCount.setMonth(month);
				comList =complaintService.getAllComplaintCountByMonth(complaintCount);
				if(comList != null && comList.size() > 0){
					for(ComplaintCount info : comList){
						info.setBlocname(complaintCount.getBlocname());
					}
				}
			}
			
			List<ComplaintInfo> comList1 = null;
			if(StringUtils.isNotEmty(year)){
				comList1 =complaintService.getTSWordByYear(complaintCount);
			}else{
				if(StringUtils.isEmty(month)){
					month=DateUtil.getCurMonth();
				}
				complaintCount.setMonth(month);
				comList1 =complaintService.getTSWordByMonth(complaintCount);
			}
			
			dataMap.put("list", comList);
			dataMap.put("list1", comList1);
	        
			ExportToWord.getInstance().exportWord(dataMap,WebConstants.FM_TS_WORD,"投诉统计");
			
		} catch (Exception e) {
			logger.error("ComplaintAction 的方法 exportWord 执行出错，原因：" + e, e);
		}
	}

	public ComplaintService getComplaintService() {
		return complaintService;
	}

	public void setComplaintService(ComplaintService complaintService) {
		this.complaintService = complaintService;
	}

	public ComplaintType getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(ComplaintType complaintType) {
		this.complaintType = complaintType;
	}

	public ComplaintInfo getComplaintInfo() {
		return complaintInfo;
	}

	public void setComplaintInfo(ComplaintInfo complaintInfo) {
		this.complaintInfo = complaintInfo;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
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

	public Map<Object, Object> getResult() {
		return result;
	}

	public void setResult(Map<Object, Object> result) {
		this.result = result;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getDealstatus() {
		return dealstatus;
	}

	public void setDealstatus(String dealstatus) {
		this.dealstatus = dealstatus;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public OrgazicationDeptService getOrgazicationDeptService() {
		return orgazicationDeptService;
	}

	public void setOrgazicationDeptService(
			OrgazicationDeptService orgazicationDeptService) {
		this.orgazicationDeptService = orgazicationDeptService;
	}

	public ComplaintCount getComplaintCount() {
		return complaintCount;
	}

	public void setComplaintCount(ComplaintCount complaintCount) {
		this.complaintCount = complaintCount;
	}

	public String getBlocid() {
		return blocid;
	}

	public void setBlocid(String blocid) {
		this.blocid = blocid;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

}
