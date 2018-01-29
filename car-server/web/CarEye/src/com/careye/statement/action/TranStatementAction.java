/**
* Description: car-eye车辆管理平台
* 文件名：DrivingStateAction.java
* 版本信息：1.0
* 日期：2014-5-26
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.statement.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.careye.base.action.BasePageAction;
import com.careye.constant.WebConstants;
import com.careye.monitor.domain.OnlineRate;
import com.careye.statement.domain.CarDriverCancel;
import com.careye.statement.domain.CusBreach;
import com.careye.statement.domain.CusCancel;
import com.careye.statement.domain.DayuploadCount;
import com.careye.statement.domain.TFC;
import com.careye.statement.domain.TerminalFunCount;
import com.careye.statement.service.TranStatementService;
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.ExportToWord;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;
import common.Logger;

/**
 * @项目名称：FMS
 * @类名称：DrivingStateAction
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2014-5-26 下午01:47:32
 * @修改人：huangqin
 * @修改时间：2014-5-26 下午01:47:32
 * @修改备注：
 * @version 1.0
 */
public class TranStatementAction extends BasePageAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(TranStatementAction.class);
	private TranStatementService tranStatementService;
	private CusCancel cusCancel;
    private CusBreach cusBreach;
    private CarDriverCancel carDriverCancel;
    private TerminalFunCount terminalFunCount;
    
    private List<TerminalFunCount> tfclist = new ArrayList<TerminalFunCount>();
    private TFC tfc;
    private DayuploadCount dayuploadCount;
	
	private Map result;
	private String success;
	private List list;
	public TerminalFunCount getTerminalFunCount() {
		return terminalFunCount;
	}

	public void setTerminalFunCount(TerminalFunCount terminalFunCount) {
		this.terminalFunCount = terminalFunCount;
	}

	private int su;
	
	
	private String  phone;
	private String  carnumber;
	private String  blocid;
	private String  stime;
	private String  etime;
	private String  type;
	private String  wy;
	private String  terminal;
	private String  cusname;
	

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}

	private String  username;
	private String drivername;
	
	
	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	private String fileName;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 1.查询客户取消数统计报表
	 * @return
	 */
	public String selectCusCancelList() {
		try {
			initMap();
			if(cusCancel==null){
				cusCancel = new CusCancel();
			}
			if(StringUtils.isNotEmty(username)){
				cusCancel.setUsername(URLDecoder.decode(username, "UTF-8").trim());
			}
			if(StringUtils.isNotEmty(phone)){
				cusCancel.setPhone(URLDecoder.decode(phone, "UTF-8").trim());
			}
			
			if(StringUtils.isNotEmty(wy)){
				if(!"2".equals(wy)){
					cusCancel.setWy(wy);
				}
			}
			if(StringUtils.isNotEmty(stime)){
				cusCancel.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				cusCancel.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			result = tranStatementService.findCusCancelPageList(this.getPage(), this.getLimit(), cusCancel);	 
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TranStatementAction 的方法 selectCusCancelList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 2.Excel导出客户取消数统计报表
	 * @return
	 */
	public void  excelCusCancel() {
		try {
		     //1.设置名字
			 fileName="客户取消数统计报表"; 
	    	 HSSFWorkbook book = new HSSFWorkbook();
	         Sheet sheet= book.createSheet(fileName);
	         //sheet.setDefaultColumnWidth(15);
	         
	         ExcelDownWay exceldownway= new ExcelDownWay();
	         
	         //2.设置列宽（列数要对应上）
	         String str="7,15,25,20,15,25,20";
	         List<String> numberList=Arrays.asList(str.split(","));
	         sheet= exceldownway.setColumnWidth(sheet,numberList);
	         
	         sheet.setDefaultRowHeight((short) 18);
	         Row titleRow= sheet.createRow(0);
	         titleRow.setHeightInPoints(20);
	         
	         if(cusCancel==null){
					cusCancel = new CusCancel();
			 }
			 if(StringUtils.isNotEmty(username)){
					cusCancel.setUsername(new String(username.getBytes("iso8859-1"),"utf-8").trim());
			 }
			 if(StringUtils.isNotEmty(phone)){
					cusCancel.setPhone(new String(phone.getBytes("iso8859-1"),"utf-8").trim());
			 }
			 if(StringUtils.isNotEmty(wy)){
				 if(!"2".equals(wy)){
					 cusCancel.setWy(wy);
				 }
			 }
			 if(StringUtils.isNotEmty(stime)){
					cusCancel.setStime(new String(stime.getBytes("iso8859-1"),"utf-8"));
			 }
			 if(StringUtils.isNotEmty(etime)){
					cusCancel.setEtime(new String(etime.getBytes("iso8859-1"),"utf-8"));
			 }
	         
	         list= tranStatementService.getCusCancelList(cusCancel);   //Excel查询
	         
	         titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
	         titleRow.createCell(1).setCellValue("订单ID");
	         titleRow.createCell(2).setCellValue("乘客姓名");
	         titleRow.createCell(3).setCellValue("联系电话");
	         titleRow.createCell(4).setCellValue("是否违约");
	         titleRow.createCell(5).setCellValue("备注");
	         titleRow.createCell(6).setCellValue("取消时间");
	         
	         for(int i=0;i<titleRow.getLastCellNum();i++){
	        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
	         }
	         
	         if(list.size()>0){
	             for(int i=0;i<list.size();i++){
	                 int  index=i+1;
	                 Row contentRow= sheet.createRow(index);
	                 contentRow.createCell(0).setCellValue(index);
	                 CusCancel cusCancel= (CusCancel) list.get(i);
	                 contentRow.createCell(1).setCellValue(cusCancel.getOrderid());
	                 contentRow.createCell(2).setCellValue(cusCancel.getUsername());
	                 contentRow.createCell(3).setCellValue(cusCancel.getPhone());
	                 String wyString=null;
	                 if(cusCancel.getWy()!=null){
	                	 String wy=cusCancel.getWy();
	                	 if("1".equals(wy)){
	                		 wyString="违约";
		                 }else if("0".equals(wy)){
		                	 wyString="不违约";
		                 }
	                 }
	                 contentRow.createCell(4).setCellValue(wyString);
	                 contentRow.createCell(5).setCellValue(cusCancel.getRemark());
	                 contentRow.createCell(6).setCellValue(cusCancel.getCanceltime());
	                 
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
	 * 3.查询司机取消数统计报表
	 * @return
	 */
	public String selectCarDriverCancelList() {
		try {
			initMap();
			if(carDriverCancel==null){
				carDriverCancel = new CarDriverCancel();
			}
			if(StringUtils.isNotEmty(blocid)){
				carDriverCancel.setBlocid(Integer.parseInt(blocid));
			}
			if(StringUtils.isNotEmty(carnumber)){
				carDriverCancel.setCarnumber(URLDecoder.decode(carnumber, "UTF-8").toUpperCase().trim());
			}
			if(StringUtils.isNotEmty(drivername)){
				carDriverCancel.setDrivername(URLDecoder.decode(drivername, "UTF-8").trim());
			}
			if(StringUtils.isNotEmty(wy)){
				if(!"2".equals(wy)){
					carDriverCancel.setWy(wy);
				}
			}
			if(StringUtils.isNotEmty(stime)){
				carDriverCancel.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				carDriverCancel.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			result = tranStatementService.findCarDrviverCancelPageList(this.getPage(), this.getLimit(), carDriverCancel);	 
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TranStatementAction 的方法 selectCarDriverCancelList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 4.Excel导出司机取消数统计报表
	 * @return
	 */
	public void excelCarDriverCancel() {
		try {
		     //1.设置名字
			 fileName="司机取消数统计报表"; 
	    	 HSSFWorkbook book = new HSSFWorkbook();
	         Sheet sheet= book.createSheet(fileName);
	         //sheet.setDefaultColumnWidth(15);
	         
	         ExcelDownWay exceldownway= new ExcelDownWay();
	         
	         //2.设置列宽（列数要对应上）
	         String str="7,20,25,20,15,15,25,20";
	         List<String> numberList=Arrays.asList(str.split(","));
	         sheet= exceldownway.setColumnWidth(sheet,numberList);
	         
	         sheet.setDefaultRowHeight((short) 18);
	         Row titleRow= sheet.createRow(0);
	         titleRow.setHeightInPoints(20);
			 if(carDriverCancel==null){
					carDriverCancel = new CarDriverCancel();
			 }
			 if(StringUtils.isNotEmty(blocid)){
					carDriverCancel.setBlocid(Integer.parseInt(blocid));
			 }
			 if(StringUtils.isNotEmty(carnumber)){
					carDriverCancel.setCarnumber(new String(carnumber.getBytes("iso8859-1"),"utf-8").toUpperCase().trim());
			 }
			 if(StringUtils.isNotEmty(drivername)){
					carDriverCancel.setDrivername(new String(drivername.getBytes("iso8859-1"),"utf-8").trim());
			 }
			 if(StringUtils.isNotEmty(wy)){
					if(!"2".equals(wy)){
						carDriverCancel.setWy(wy);
					}
			 }
			 if(StringUtils.isNotEmty(stime)){
				 carDriverCancel.setStime(new String(stime.getBytes("iso8859-1"),"utf-8"));
			 }
			 if(StringUtils.isNotEmty(etime)){
				 carDriverCancel.setEtime(new String(etime.getBytes("iso8859-1"),"utf-8"));
			 }
	         
	         list= tranStatementService.getCarDriverCancelList(carDriverCancel);   //Excel查询
	         
	         titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
	         titleRow.createCell(1).setCellValue("订单ID");
	         titleRow.createCell(2).setCellValue("集团");
	         titleRow.createCell(3).setCellValue("司机");
	         titleRow.createCell(4).setCellValue("车牌号");
	         titleRow.createCell(5).setCellValue("是否违约");
	         titleRow.createCell(6).setCellValue("备注");
	         titleRow.createCell(7).setCellValue("取消时间");
	         
	         for(int i=0;i<titleRow.getLastCellNum();i++){
	        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
	         }
	         
	         if(list.size()>0){
	             for(int i=0;i<list.size();i++){
	                 int  index=i+1;
	                 Row contentRow= sheet.createRow(index);
	                 contentRow.createCell(0).setCellValue(index);
	                 CarDriverCancel carDriverCancel= (CarDriverCancel) list.get(i);
	                 contentRow.createCell(1).setCellValue(carDriverCancel.getOrderid());
	                 contentRow.createCell(2).setCellValue(carDriverCancel.getBlocname());
	                 contentRow.createCell(3).setCellValue(carDriverCancel.getDrivername());
	                 contentRow.createCell(4).setCellValue(carDriverCancel.getCarnumber());
	                 String wyString=null;
	                 if(carDriverCancel.getWy()!=null){
	                	 String wy=carDriverCancel.getWy();
	                	 if("1".equals(wy)){
	                		 wyString="违约";
		                 }else if("0".equals(wy)){
		                	 wyString="不违约";
		                 }
	                 }
	                 contentRow.createCell(5).setCellValue(wyString);
	                 contentRow.createCell(6).setCellValue(carDriverCancel.getRemark());
	                 contentRow.createCell(7).setCellValue(carDriverCancel.getCanceltime());
	                 
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
	 * 5.查询客户违约数统计报表
	 * @return
	 */
	public String selectCusBreachList() {
		try {
			initMap();
			if(cusBreach==null){
				cusBreach = new CusBreach();
			}
			if(StringUtils.isNotEmty(username)){
				cusBreach.setUsername(URLDecoder.decode(username, "UTF-8").trim());
			}
			if(StringUtils.isNotEmty(phone)){
				cusBreach.setPhone(URLDecoder.decode(phone, "UTF-8").trim());
			}
			if(StringUtils.isNotEmty(stime)){
				cusBreach.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				cusBreach.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			result = tranStatementService.findCusBreachPageList(this.getPage(), this.getLimit(), cusBreach);	 
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TranStatementAction 的方法 selectCusBreachList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 6.Excel导出司客户违约数统计报表
	 * @return
	 */
	public void excelCusBreachCancel() {
		try {
		     //1.设置名字
			 fileName="客户违约数统计报表"; 
	    	 HSSFWorkbook book = new HSSFWorkbook();
	         Sheet sheet= book.createSheet(fileName);
	         //sheet.setDefaultColumnWidth(15);
	         
	         ExcelDownWay exceldownway= new ExcelDownWay();
	         
	         //2.设置列宽（列数要对应上）
	         String str="7,20,25,20,25,20";
	         List<String> numberList=Arrays.asList(str.split(","));
	         sheet= exceldownway.setColumnWidth(sheet,numberList);
	         
	         sheet.setDefaultRowHeight((short) 18);
	         Row titleRow= sheet.createRow(0);
	         titleRow.setHeightInPoints(20);
	         
	         if(cusBreach==null){
					cusBreach = new CusBreach();
			 }
			 if(StringUtils.isNotEmty(username)){
				 cusBreach.setUsername(new String(username.getBytes("iso8859-1"),"utf-8").trim());
			 }
			 if(StringUtils.isNotEmty(phone)){
				 cusBreach.setPhone(new String(phone.getBytes("iso8859-1"),"utf-8").trim());
			 }
			 if(StringUtils.isNotEmty(stime)){
				 cusBreach.setStime(new String(stime.getBytes("iso8859-1"),"utf-8"));
			 }
			 if(StringUtils.isNotEmty(etime)){
				 cusBreach.setEtime(new String(etime.getBytes("iso8859-1"),"utf-8"));
			 }
	         
	         list= tranStatementService.getCusBreachList(cusBreach);   //Excel查询
	         
	         titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
	         titleRow.createCell(1).setCellValue("订单ID");
	         titleRow.createCell(2).setCellValue("乘客姓名");
	         titleRow.createCell(3).setCellValue("联系电话");
	         titleRow.createCell(4).setCellValue("备注");
	         titleRow.createCell(5).setCellValue("取消时间");
	         
	         for(int i=0;i<titleRow.getLastCellNum();i++){
	        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
	         }
	         
	         if(list.size()>0){
	             for(int i=0;i<list.size();i++){
	                 int  index=i+1;
	                 Row contentRow= sheet.createRow(index);
	                 contentRow.createCell(0).setCellValue(index);
	                 CusBreach cusBreach= (CusBreach) list.get(i);
	                 contentRow.createCell(1).setCellValue(cusBreach.getOrderid());
	                 contentRow.createCell(2).setCellValue(cusBreach.getUsername());
	                 contentRow.createCell(3).setCellValue(cusBreach.getPhone());
	                 contentRow.createCell(4).setCellValue(cusBreach.getRemark());
	                 contentRow.createCell(5).setCellValue(cusBreach.getCanceltime());
	                 
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
	 * 7.查询终端功能使用统计
	 * @return
	 */
	public String selectTerminalFunCountList() {
		try {
			initMap();
			if(terminalFunCount==null){
				terminalFunCount = new TerminalFunCount();
			}
			if(StringUtils.isNotEmty(blocid)){
				terminalFunCount.setBlocid(Integer.parseInt(blocid));
			}
			
			if(StringUtils.isNotEmty(terminal)){
				terminalFunCount.setTerminal(URLDecoder.decode(terminal, "UTF-8").trim());
			}
			if(StringUtils.isNotEmty(carnumber)){
				terminalFunCount.setCarnumber(URLDecoder.decode(carnumber, "UTF-8").toUpperCase().trim());
			}
			
			result = tranStatementService.findTerminalFunCountPageList(this.getPage(), this.getLimit(), terminalFunCount);	 
			
			list = (List) result.get("list");
			if(null == list && list.size() == 0){
				list = new LinkedList<TerminalFunCount>();
			}
			tfc = tranStatementService.selTFC(terminalFunCount);
			TerminalFunCount t = new TerminalFunCount();
			t.setId(0);
			t.setCarnumber("总数");
			t.setCount12(tfc.getSumcount12());
			t.setCount13(tfc.getSumcount13());
			t.setCount14(tfc.getSumcount14());
			t.setCount4(tfc.getSumcount4());
			t.setCount15(tfc.getSumcount15());
			t.setCount16(tfc.getSumcount16());
			t.setCount8(tfc.getSumcount8());
			t.setCount17(tfc.getSumcount17());
			t.setCount11(tfc.getSumcount11());
			t.setCount18(tfc.getSumcount18());
			list.add(t);
			
			this.success="true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TranStatementAction 的方法 selectTerminalFunCountList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 8.Excel导出终端功能使用统计
	 * @return
	 */
	public void  excelTerminalFunCount() {
		try {
		     //1.设置名字
			 fileName="终端功能使用统计"; 
	    	 HSSFWorkbook book = new HSSFWorkbook();
	         Sheet sheet= book.createSheet(fileName);
	         //sheet.setDefaultColumnWidth(15);
	         
	         ExcelDownWay exceldownway= new ExcelDownWay();
	         
	         //2.设置列宽（列数要对应上）
	         String str="7,15,15,15,15,15,15,15,15,15,15,20";
	         List<String> numberList=Arrays.asList(str.split(","));
	         sheet= exceldownway.setColumnWidth(sheet,numberList);
	         
	         sheet.setDefaultRowHeight((short) 18);
	         Row titleRow= sheet.createRow(0);
	         titleRow.setHeightInPoints(20);
	         
	         if(terminalFunCount==null){
	        	 terminalFunCount = new TerminalFunCount();
			 }
			 if(StringUtils.isNotEmty(blocid)){
				 terminalFunCount.setBlocid(Integer.parseInt(blocid));
			 }
			 if(StringUtils.isNotEmty(terminal)){
				 terminalFunCount.setTerminal(new String(terminal.getBytes("iso8859-1"),"utf-8").trim());
			 }
			 if(StringUtils.isNotEmty(carnumber)){
					terminalFunCount.setCarnumber(new String(carnumber.getBytes("iso8859-1"),"utf-8").toUpperCase().trim());
				}
	         
	         list= tranStatementService.getTerminalFunCountList(terminalFunCount);   //Excel查询
				if(null == list && list.size() == 0){
					list = new LinkedList<TerminalFunCount>();
				}
				tfc = tranStatementService.selTFC(terminalFunCount);
				TerminalFunCount t = new TerminalFunCount();
				t.setCarnumber("总数");
				t.setCount12(tfc.getSumcount12());
				t.setCount13(tfc.getSumcount13());
				t.setCount14(tfc.getSumcount14());
				t.setCount4(tfc.getSumcount4());
				t.setCount15(tfc.getSumcount15());
				t.setCount16(tfc.getSumcount16());
				t.setCount8(tfc.getSumcount8());
				t.setCount17(tfc.getSumcount17());
 				t.setCount11(tfc.getSumcount11());
				t.setCount18(tfc.getSumcount18());
				list.add(t);
	         
	         
	         titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
	         titleRow.createCell(1).setCellValue("车牌号");
	         titleRow.createCell(2).setCellValue("支付");
	         titleRow.createCell(3).setCellValue("电招");
	         titleRow.createCell(4).setCellValue("顺风车");
	         titleRow.createCell(5).setCellValue("导航");
	         titleRow.createCell(6).setCellValue("娱乐");
	         titleRow.createCell(7).setCellValue("对讲");
	         titleRow.createCell(8).setCellValue("设置");
	         titleRow.createCell(9).setCellValue("蓝牙");
	         titleRow.createCell(10).setCellValue("软件扩展");
	         titleRow.createCell(11).setCellValue("行车记录仪");
	         
	         
	         for(int i=0;i<titleRow.getLastCellNum();i++){
	        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
	         }
	         
	         if(list.size()>0){
	             for(int i=0;i<list.size();i++){
	                 int  index=i+1;
	                 Row contentRow= sheet.createRow(index);
	                 contentRow.createCell(0).setCellValue(index);
	                 TerminalFunCount terminalFunCount= (TerminalFunCount) list.get(i);
	                 contentRow.createCell(1).setCellValue(terminalFunCount.getCarnumber());
	                 contentRow.createCell(2).setCellValue(terminalFunCount.getCount12());
	                 contentRow.createCell(3).setCellValue(terminalFunCount.getCount13());
	                 contentRow.createCell(4).setCellValue(terminalFunCount.getCount14());
	                 contentRow.createCell(5).setCellValue(terminalFunCount.getCount4());
	                 contentRow.createCell(6).setCellValue(terminalFunCount.getCount15());
	                 contentRow.createCell(7).setCellValue(terminalFunCount.getCount16());
	                 contentRow.createCell(8).setCellValue(terminalFunCount.getCount8());
	                 contentRow.createCell(9).setCellValue(terminalFunCount.getCount17());
	                 contentRow.createCell(10).setCellValue(terminalFunCount.getCount11());
	                 contentRow.createCell(11).setCellValue(terminalFunCount.getCount18());
	                 
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
	 * word导出 终端功能使用统计
	 */
	public void exportTFCWord(){
		try{
			initMap();
			Map<String,Object> dataMap=new HashMap<String,Object>();
			
			dataMap.put("title", "终端功能使用统计");
			
			dataMap.put("condition", "终端功能使用统计数据如下：");
			
			String st = DateUtil.getSQLDate();
		    String y = st.substring(0, 4);
		    String m = st.substring(5, 7);
		    String today = st.substring(8, 10);
		    dataMap.put("year", y+"年");  
	        dataMap.put("month", m+"月");  
	        dataMap.put("date", today+"日");
			
			
			if(terminalFunCount==null){
				terminalFunCount = new TerminalFunCount();
			}
			if(SessionUtils.getUser() == null){
				return;
			}else{
				terminalFunCount.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				terminalFunCount.setBlocid(Integer.parseInt(blocid));
				String bname = tranStatementService.getBname(Integer.parseInt(blocid));
				dataMap.put("bname", "企业"+"【"+bname+"】"+",");
			}else{
//				terminalFunCount.setBlocid(SessionUtils.getUser().getBlocid());
//				String bname = tranStatementService.getBname(terminalFunCount.getBlocid());
				dataMap.put("bname", " ");
			}
			
			if(StringUtils.isNotEmty(terminal)){
				terminalFunCount.setTerminal(URLDecoder.decode(terminal, "UTF-8").trim());
				dataMap.put("terminal", "设备号"+"【"+terminal+"】"+",");
			}else{
				dataMap.put("terminal", "");
			}
			if(StringUtils.isNotEmty(carnumber)){
				terminalFunCount.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"),"utf-8"));
				dataMap.put("carnumber", "车牌号"+"【"+new String(carnumber.getBytes("ISO-8859-1"),"utf-8")+"】"+",");
			}else{
				dataMap.put("carnumber", "");
			}
			tfclist = tranStatementService.getTFCWord(terminalFunCount);
			
			tfc = tranStatementService.selTFC(terminalFunCount);
			
			dataMap.put("Sumcount12",tfc.getSumcount12());
			dataMap.put("Sumcount13",tfc.getSumcount13());
			dataMap.put("Sumcount14",tfc.getSumcount14());
			dataMap.put("Sumcount4",tfc.getSumcount4());
			dataMap.put("Sumcount15",tfc.getSumcount15());
			dataMap.put("Sumcount16",tfc.getSumcount16());
			dataMap.put("Sumcount8",tfc.getSumcount8());
			dataMap.put("Sumcount17",tfc.getSumcount17());
			dataMap.put("Sumcount11",tfc.getSumcount11());
			dataMap.put("Sumcount18",tfc.getSumcount18());
			
			
			dataMap.put("list",tfclist);
			
			ExportToWord.getInstance().exportWord(dataMap,WebConstants.FM_TFC_WORD,"终端功能使用统计");
			
		} catch (Exception e) {
			logger.error("TranStatementAction 的方法 exportTFCWord 执行出错，原因：" + e, e);
		}
	}
	
	
	/**
	 * 9.查询日上报统计
	 * @return
	 */
	public String findDayuploadList() {
		try {
			initMap();
			if(dayuploadCount==null){
				dayuploadCount = new DayuploadCount();
			}
			if(StringUtils.isNotEmty(blocid)){
				dayuploadCount.setBlocid(Integer.parseInt(blocid));
			}
			
			if(StringUtils.isNotEmty(terminal)){
				dayuploadCount.setTerminal(URLDecoder.decode(terminal, "UTF-8").trim());
			}
			if(StringUtils.isNotEmty(carnumber)){
				dayuploadCount.setCarnumber(URLDecoder.decode(carnumber, "UTF-8").toUpperCase().trim());
			}
			if(StringUtils.isNotEmty(type)){
				dayuploadCount.setType(Integer.parseInt(type));
			}
			if(StringUtils.isNotEmty(stime)){
				dayuploadCount.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				dayuploadCount.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			result = tranStatementService.findDayuploadList(this.getPage(), this.getLimit(), dayuploadCount);	 
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TranStatementAction 的方法 findDayuploadList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 8.Excel导出日上报统计
	 * @return
	 */
	public void  exportDayuploadList() {
		try {
		     //1.设置名字
			 fileName="终端功能使用统计"; 
	    	 HSSFWorkbook book = new HSSFWorkbook();
	         Sheet sheet= book.createSheet(fileName);
	         //sheet.setDefaultColumnWidth(15);
	         
	         ExcelDownWay exceldownway= new ExcelDownWay();
	         
	         //2.设置列宽（列数要对应上）
	         String str="7,20,15,20,15,15,15,15,25,25";
	         List<String> numberList=Arrays.asList(str.split(","));
	         sheet= exceldownway.setColumnWidth(sheet,numberList);
	         
	         sheet.setDefaultRowHeight((short) 18);
	         Row titleRow= sheet.createRow(0);
	         titleRow.setHeightInPoints(20);
	         
	         if(dayuploadCount==null){
	        	 dayuploadCount = new DayuploadCount();
			 }
			 if(StringUtils.isNotEmty(blocid)){
				 dayuploadCount.setBlocid(Integer.parseInt(blocid));
			 }
			 if(StringUtils.isNotEmty(terminal)){
				 dayuploadCount.setTerminal(new String(terminal.getBytes("iso8859-1"),"utf-8").trim());
			 }
			 if(StringUtils.isNotEmty(carnumber)){
					dayuploadCount.setCarnumber(new String(carnumber.getBytes("iso8859-1"),"utf-8").toUpperCase().trim());
				}
			 if(StringUtils.isNotEmty(type)){
					dayuploadCount.setType(Integer.parseInt(type));
				}
			 if(StringUtils.isNotEmty(stime)){
				 dayuploadCount.setStime(new String(stime.getBytes("iso8859-1"),"utf-8"));
			 }
			 if(StringUtils.isNotEmty(etime)){
				 dayuploadCount.setEtime(new String(etime.getBytes("iso8859-1"),"utf-8"));
			 }
	         
	         list= tranStatementService.exportDayupload(dayuploadCount);   //Excel查询
	         
	         titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
	         titleRow.createCell(1).setCellValue("终端设备号");
	         titleRow.createCell(2).setCellValue("车牌号");
	         titleRow.createCell(3).setCellValue("集团");
	         titleRow.createCell(4).setCellValue("功能类型");
	         titleRow.createCell(5).setCellValue("使用次数(次)");
	         titleRow.createCell(6).setCellValue("使用时长(秒)");
	         titleRow.createCell(7).setCellValue("使用流量(kb)");
	         titleRow.createCell(8).setCellValue("统计时间");
	         titleRow.createCell(9).setCellValue("创建时间");
	         
	         
	         for(int i=0;i<titleRow.getLastCellNum();i++){
	        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
	         }
	         
	         if(list.size()>0){
	             for(int i=0;i<list.size();i++){
	                 int  index=i+1;
	                 Row contentRow= sheet.createRow(index);
	                 contentRow.createCell(0).setCellValue(index);
	                 DayuploadCount dayuploadCount= (DayuploadCount) list.get(i);
	                 contentRow.createCell(1).setCellValue(dayuploadCount.getTerminal());
	                 contentRow.createCell(2).setCellValue(dayuploadCount.getCarnumber());
	                 contentRow.createCell(3).setCellValue(dayuploadCount.getBlocname());
	                 if(1==dayuploadCount.getType()){
	                	 contentRow.createCell(4).setCellValue("考拉FM"); 
	                 }else{
	                	 contentRow.createCell(4).setCellValue("");
	                 }
	                 contentRow.createCell(5).setCellValue(dayuploadCount.getNumbercount());
	                 contentRow.createCell(6).setCellValue(dayuploadCount.getUsinglen());
	                 contentRow.createCell(7).setCellValue(dayuploadCount.getFlow());
//	                 if(StringUtils.isNotEmty(dayuploadCount.getTime())){
//	                	 SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd-hh-mm-ss");
//	                	 Date date=sdf.parse(dayuploadCount.getTime());
//	                	 sdf=new SimpleDateFormat("yy-MM-dd HH:mm:ss");
//	                	 contentRow.createCell(8).setCellValue(sdf.format(date));
//	                 }
	                 contentRow.createCell(8).setCellValue(dayuploadCount.getTime());
	                 contentRow.createCell(9).setCellValue(dayuploadCount.getCreatetime());
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
	
	
	
	
	
	public TranStatementService getTranStatementService() {
		return tranStatementService;
	}

	public void setTranStatementService(TranStatementService tranStatementService) {
		this.tranStatementService = tranStatementService;
	}

	public CusCancel getCusCancel() {
		return cusCancel;
	}

	public void setCusCancel(CusCancel cusCancel) {
		this.cusCancel = cusCancel;
	}

	public CusBreach getCusBreach() {
		return cusBreach;
	}

	public void setCusBreach(CusBreach cusBreach) {
		this.cusBreach = cusBreach;
	}

	public CarDriverCancel getCarDriverCancel() {
		return carDriverCancel;
	}

	public void setCarDriverCancel(CarDriverCancel carDriverCancel) {
		this.carDriverCancel = carDriverCancel;
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

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getSu() {
		return su;
	}

	public void setSu(int su) {
		this.su = su;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getBlocid() {
		return blocid;
	}

	public void setBlocid(String blocid) {
		this.blocid = blocid;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public static Logger getLogger() {
		return logger;
	}
	public String getWy() {
		return wy;
	}

	public void setWy(String wy) {
		this.wy = wy;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public DayuploadCount getDayuploadCount() {
		return dayuploadCount;
	}

	public void setDayuploadCount(DayuploadCount dayuploadCount) {
		this.dayuploadCount = dayuploadCount;
	}

	public TFC getTfc() {
		return tfc;
	}

	public void setTfc(TFC tfc) {
		this.tfc = tfc;
	}
	
}
