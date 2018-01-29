/**
 * Description: car-eye车辆管理平台
 * 文件名：CityInfoAction.java
 * 版本信息：1.0
 * 日期：2013-8-5
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.common.action;

import java.io.IOException;
import java.net.URLDecoder;
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
import com.careye.common.domain.SysOperateLog;
import com.careye.common.service.SysOperateLogService;
import com.careye.system.domain.SysAuthLoginLog;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：car-eyeTms
 * @类名称：CityInfoAction
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-3-14 下午03:24:20
 * @修改人：zhangrong
 * @修改时间：2014-3-14 下午03:24:20
 * @修改备注：
 * @version 1.0
 */
public class SysOperateLogAction extends BasePageAction {

	private static final Logger logger = Logger.getLogger(SysOperateLogAction.class);
	private SysOperateLog logInfo;
	private SysOperateLogService logService;
	
	private Map result;
	
	private String stime;
	private String etime;
	private String loginname;
	private String operattype;
	private String ids;
	
	//初始化返回JSON的Map对象
	private void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}	
	private String fileName;
	
	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 日志记录分页列表
	 * @return
	 */
	public String queryLogInfoList(){
		try{
			initMap();
			if(logInfo==null){
				logInfo = new SysOperateLog();
			}
			if(loginname !=null && !loginname.equals("")){
				logInfo.setLoginname(URLDecoder.decode(loginname, "UTF-8").trim());
			}
			if(operattype !=null && !operattype.equals("")&&!operattype.equals("null")){
				logInfo.setOperattype(Integer.parseInt(operattype));
			}
			if(stime!=null&& !stime.equals("")&&!stime.equals("null")){
				logInfo.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if(etime!=null&& !etime.equals("")&&!etime.equals("null")){
				logInfo.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			result = logService.findLogInfoPageList(this.getPage(), getLimit(), logInfo);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("SysOperateLogAction 的方法 queryLogInfoList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 导出exportLogInfo
	 */
	public void exportLogInfo(){
		try {
			initMap();
			if(logInfo == null){
				logInfo = new SysOperateLog();
			}
			if(loginname !=null && !loginname.equals("")){
				logInfo.setLoginname(URLDecoder.decode(loginname, "UTF-8").trim());
			}
			if(operattype !=null && !operattype.equals("")&&!operattype.equals("null")){
				logInfo.setOperattype(Integer.parseInt(operattype));
			}
			if(stime!=null&& !stime.equals("")&&!stime.equals("null")){
				logInfo.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if(etime!=null&& !etime.equals("")&&!etime.equals("null")){
				logInfo.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}

			List<SysOperateLog> list = logService.exportLogInfo(logInfo);

		    fileName = "用户登陆日志报表";
		   	 HSSFWorkbook book = new HSSFWorkbook();
		     Sheet sheet= book.createSheet(fileName);
		     

		        ExcelDownWay exceldownway= new ExcelDownWay();
		         
//		         //2.设置列宽（列数要对应上）
//		         String str="7,15,15,15,20,20,15,15,15,25";
//		         List<String> numberList=Arrays.asList(str.split(","));
//		         sheet= exceldownway.setColumnWidth(sheet,numberList);
//		         sheet.setDefaultRowHeight((short) 20);
			     Row titleRow= sheet.createRow(0);
		         titleRow.setHeightInPoints(20);
		         
		      // 设置列宽    
		         sheet.setColumnWidth(0, 2000);    
		         sheet.setColumnWidth(1, 3500);    
		         sheet.setColumnWidth(2, 3500);    
		         sheet.setColumnWidth(3, 7500);    
		         sheet.setColumnWidth(4, 5500);    
		        /* // Sheet样式    
		         HSSFCellStyle sheetStyle = book.createCellStyle();    
		         // 背景色的设定    
		         sheetStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);    
		         // 前景色的设定    
		         sheetStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);    
		         // 填充模式    
		         sheetStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);    
		         // 设置列的样式    
		         for (int i = 0; i <= 14; i++) {    
		           sheet.setDefaultColumnStyle((short) i, sheetStyle);    
		         }   */
		         
		         // 字体样式    
		         HSSFFont columnHeadFont = book.createFont();    
		         columnHeadFont.setFontName("宋体");    
		         columnHeadFont.setFontHeightInPoints((short) 10);    
		         columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
		         // 列头的样式    
		         HSSFCellStyle columnHeadStyle = book.createCellStyle();    
		         columnHeadStyle.setFont(columnHeadFont);    
		         columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中    
		         columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中    
		         columnHeadStyle.setLocked(true);    
		         columnHeadStyle.setWrapText(true);    
		         columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色    
		         columnHeadStyle.setBorderLeft((short) 1);// 边框的大小    
		         columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色    
		         columnHeadStyle.setBorderRight((short) 1);// 边框的大小    
		         columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体    
		         columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色    
		         // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）    
		         columnHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		         columnHeadStyle.setFillForegroundColor(HSSFColor.GREEN.index);    
		         
		         HSSFFont font = book.createFont();    
		         font.setFontName("宋体");    
		         font.setFontHeightInPoints((short) 10);    
		         // 普通单元格样式    
		         HSSFCellStyle style = book.createCellStyle();    
		         style.setFont(font);    
		         style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中    
		         style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 上下居中    
		         style.setWrapText(true);    
		         style.setLeftBorderColor(HSSFColor.BLACK.index);    
		         style.setBorderLeft((short) 1);    
		         style.setRightBorderColor(HSSFColor.BLACK.index);    
		         style.setBorderRight((short) 1);    
		         style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体    
		         style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．    
		         style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．    
		         
			titleRow.createCell(0).setCellValue("序号");
	        titleRow.createCell(1).setCellValue("操作用户");
	        titleRow.createCell(2).setCellValue("操作类型");
	        titleRow.createCell(3).setCellValue("日志内容");
	        titleRow.createCell(4).setCellValue("操作时间");

	        
	         for(int i=0;i<titleRow.getLastCellNum();i++){
//	        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
	        	 titleRow.getCell(i).setCellStyle(columnHeadStyle);
	         }
	        
	        if(list.size()>0){
	            for(int i=0;i<list.size();i++){
	                int  index=i+1;
	                Row contentRow= sheet.createRow(index);
	                contentRow.setHeightInPoints(15);
	                contentRow.createCell(0).setCellValue(index);
	                SysOperateLog data= (SysOperateLog) list.get(i);
	                contentRow.createCell(1).setCellValue(data.getLoginname());
	                
	                if (data.getOperattype() == 1 ) {
	                	contentRow.createCell(2).setCellValue("添加");
					}else if(data.getOperattype() == 2 ){
						contentRow.createCell(2).setCellValue("修改");
					}else if(data.getOperattype() == 3 ){
						contentRow.createCell(2).setCellValue("删除");
					}else if(data.getOperattype() == 4 ){
						contentRow.createCell(2).setCellValue("导出");
					}else if(data.getOperattype() == 5 ){
						contentRow.createCell(2).setCellValue("导入");
					}else{
	                	contentRow.createCell(2).setCellValue("");
					}
	                
//	                contentRow.createCell(2).setCellValue(data.getOperattype());
	                contentRow.createCell(3).setCellValue(data.getContent());
	                contentRow.createCell(4).setCellValue(data.getCreatetime());

	                 for(int m=0;m<contentRow.getLastCellNum();m++){
//	                	 contentRow.getCell(m).setCellStyle(exceldownway.setBookListStyle(book));
	                	 contentRow.getCell(m).setCellStyle(style);
	                 }
	                
	            }
	        }
	        
	       new ExcelDownWay().getCommonExcelListWay(book,fileName);
	       
	       
			} catch (Exception e) {
				try {
					getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	}
	
	/**
	 * 删除操作日志deleteLogInfo
	 * @return
	 */
	public String deleteLogInfo(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				logService.deleteLogInfo(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("SysOperateLogAction 的方法 deleteLogInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}


	public SysOperateLog getLogInfo() {
		return logInfo;
	}


	public void setLogInfo(SysOperateLog logInfo) {
		this.logInfo = logInfo;
	}


	public SysOperateLogService getLogService() {
		return logService;
	}


	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}


	public Map getResult() {
		return result;
	}


	public void setResult(Map result) {
		this.result = result;
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



	public String getOperattype() {
		return operattype;
	}


	public void setOperattype(String operattype) {
		this.operattype = operattype;
	}


	public String getLoginname() {
		return loginname;
	}


	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}
	
	

}
