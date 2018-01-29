/**
 * 
 */
package com.careye.transaction.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.careye.advert.domain.AdvertVer;
import com.careye.base.action.BasePageAction;
import com.careye.constant.WebConstants;
import com.careye.customer.domain.ComplaintCount;
import com.careye.transaction.domain.EvaluateCount;
import com.careye.transaction.service.EvaluateCountService;
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.ExportToWord;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @author Administrator
 *
 */
public class EvaluateCountAction extends BasePageAction{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(EvaluateCountAction.class);
	
	private EvaluateCount evaluateCount;
	private EvaluateCountService evaluateCountService;
	
	private String blocid;
	private String carnumber;
	private String year;
	private String month;
	private String searchtype;
	private String success;
	private Map result;
	private String ids;
	private List list;
	private String datetime;
	private String evalevel;
	
	private String fileName;
	
	private List <EvaluateCount> eclist=new ArrayList<EvaluateCount>();
	private List <EvaluateCount> eclist1=new ArrayList<EvaluateCount>();
	private List <EvaluateCount> infolist=new ArrayList<EvaluateCount>();
	private List <EvaluateCount> dtimelist=new ArrayList<EvaluateCount>();
	
	
	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
		
	}
	
	/**
	 * 得到当前时间的年月
	 * @return
	 */
	private String getDateTime(){
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Date dt = new Date();
		return df.format(dt);
	}
	
	public String getEvaluateCountList(){
		try {
			initMap();
			if(evaluateCount==null){
				evaluateCount = new EvaluateCount();
			}
			if(StringUtils.isNotEmty(year)){
				evaluateCount.setYear(URLDecoder.decode(year, "UTF-8"));
			}
			if(StringUtils.isNotEmty(month)){
				evaluateCount.setMonth(URLDecoder.decode(month, "UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				evaluateCount.setBlocid(Integer.parseInt(blocid));
			}
			if(StringUtils.isNotEmty(carnumber)){
				evaluateCount.setCarnumber(URLDecoder.decode(carnumber, "UTF-8").trim());
			}
			
			if(StringUtils.isNotEmty(year)){
				eclist =evaluateCountService.getEvaluateCountListByYear(evaluateCount);
			}else{
				if(StringUtils.isEmty(month)){
					month=getDateTime();
				}
				evaluateCount.setMonth(month);
				eclist =evaluateCountService.getEvaluateCountListByMonth(evaluateCount);
			}
			
			
			for(int i=0;i<eclist.size();i++){
				DecimalFormat  df = new  DecimalFormat("0.00");
				EvaluateCount ec= (EvaluateCount)eclist.get(i);
				Integer c4 = Integer.parseInt(ec.getCount1())+
							 Integer.parseInt(ec.getCount2())+
							 Integer.parseInt(ec.getCount3());
				
				
				if(c4 == 0){
					ec.setCount4("0");
				}else{
					Integer c1 = Integer.parseInt(ec.getCount1());
					Double d4 = ((Double.valueOf(c1)/c4)*100);
					ec.setCount4(df.format(d4)+"%");
				}
			}
			
			
			
			result.put("list", eclist);
			
			return SUCCESS; 
		} catch (Exception e) {
			logger.error("EvaluateCountAction 的方法 getAllAlipayStatement 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	

	public void exportExcel(){
		try {
			// 1.设置名字
			fileName = "评价统计列表--按月统计";
			if(StringUtils.isNotEmty(searchtype)){
				 if(Integer.parseInt(searchtype)==2){
					 fileName="评价统计列表 ---- 按年统计"; 
				 }
			 }
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,25,25,25,25,25,25,25,25,25,25,25,25";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);
			

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			if(evaluateCount==null){
				evaluateCount = new EvaluateCount();
			}
			if(StringUtils.isNotEmty(year)){
				evaluateCount.setYear(URLDecoder.decode(year, "UTF-8"));
			}
			if(StringUtils.isNotEmty(month)){
				evaluateCount.setMonth(URLDecoder.decode(month, "UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				evaluateCount.setBlocid(Integer.parseInt(blocid));
			}
			if(StringUtils.isNotEmty(carnumber)){
				evaluateCount.setCarnumber(URLDecoder.decode(carnumber, "UTF-8").trim());
			}
			
			if(StringUtils.isNotEmty(year)){
				eclist =evaluateCountService.getEvaluateCountListByYear(evaluateCount);
			}else{
				if(StringUtils.isEmty(month)){
					month=getDateTime();
				}
				evaluateCount.setMonth(month);
				eclist =evaluateCountService.getEvaluateCountListByMonth(evaluateCount);
			}
			
			
			for(int i=0;i<eclist.size();i++){
				DecimalFormat  df = new  DecimalFormat("0.00");
				EvaluateCount ec= (EvaluateCount)eclist.get(i);
				Integer c4 = Integer.parseInt(ec.getCount1())+
							 Integer.parseInt(ec.getCount2())+
							 Integer.parseInt(ec.getCount3());
				if(c4 == 0){
					ec.setCount4("0");
				}else{
					Integer c1 = Integer.parseInt(ec.getCount1());
					Double d4 = ((Double.valueOf(c1)/c4)*100);
					ec.setCount4(df.format(d4)+"%");
				}
			}
		        
		        titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
		        titleRow.createCell(1).setCellValue("日期");
		        titleRow.createCell(2).setCellValue("好评数");
		        titleRow.createCell(3).setCellValue("中评数");
		        titleRow.createCell(4).setCellValue("差评数");
		        titleRow.createCell(5).setCellValue("好评百分比");
		        sheet.setDefaultColumnWidth(20);  
		        sheet.setDefaultRowHeightInPoints(20); 
		        
		        for (int i = 0; i < titleRow.getLastCellNum(); i++) {
					titleRow.getCell(i).setCellStyle(
							exceldownway.setBookHeadStyle(book));
				}
		        
		        if(eclist.size()>0){
		            for(int i=0;i<eclist.size();i++){
		                int  index=i+1;
		                Row contentRow= sheet.createRow(index);
		                contentRow.createCell(0).setCellValue(index);
		                EvaluateCount data= (EvaluateCount) eclist.get(i);
		                contentRow.createCell(1).setCellValue(data.getDatetime());
		                contentRow.createCell(2).setCellValue(data.getCount1());
		                contentRow.createCell(3).setCellValue(data.getCount2());
		                contentRow.createCell(4).setCellValue(data.getCount3());
		                contentRow.createCell(5).setCellValue(data.getCount4());
		                
//		              
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
	 * word 导出
	 * @return
	 */
	public void exportWord(){
		try {
			initMap();
		    Map<String,Object> dataMap=new HashMap<String,Object>();
		    String st = DateUtil.getSQLDate();
		    String y = st.substring(0, 4);
		    String m = st.substring(5, 7);
		    String today = st.substring(8, 10);
			
			if(StringUtils.isNotEmty(searchtype)){
				 if(Integer.parseInt(searchtype)==2){
					 dataMap.put("Title", "评价统计");
					 dataMap.put("title1", "评价统计--按年统计");
					 dataMap.put("title2", "评价明细--按年统计");
				 }else  if(Integer.parseInt(searchtype)==1){
					 dataMap.put("Title", "评价统计");
					 dataMap.put("title1", "评价统计--按月统计");
					 dataMap.put("title2", "评价明细--按月统计");
				 }
			 }else{
				 dataMap.put("Title", "评价统计");
				 dataMap.put("title1", "评价统计");
				 dataMap.put("title2", "评价明细");
			 }
			
			if(StringUtils.isNotEmty(blocid)){
				String blocname = evaluateCountService.selBlocname(Integer.parseInt(blocid));
				dataMap.put("blocname", "企业"+"【"+blocname+"】"+",");
			}else{
				dataMap.put("blocname", "");
			}
			if(StringUtils.isNotEmty(carnumber)){
				dataMap.put("carnumber", "车牌号"+"【"+new String(carnumber.getBytes("ISO-8859-1"),"utf-8")+"】"+",");
			}else{
				dataMap.put("carnumber", "");
			}
			
			
	        dataMap.put("Year", y);  
	        dataMap.put("Month", m);  
	        dataMap.put("Date", today);  
	        
	        
	        if(evaluateCount==null){
				evaluateCount = new EvaluateCount();
			}
			if(StringUtils.isNotEmty(year)){
				evaluateCount.setYear(URLDecoder.decode(year, "UTF-8"));
			}
			if(StringUtils.isNotEmty(month)){
				evaluateCount.setMonth(URLDecoder.decode(month, "UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				evaluateCount.setBlocid(Integer.parseInt(blocid));
			}
			if(StringUtils.isNotEmty(carnumber)){
				evaluateCount.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"),"utf-8"));
			}
			
			if(StringUtils.isNotEmty(year)){
				eclist =evaluateCountService.getEvaluateCountListByYear(evaluateCount);
				eclist1 =evaluateCountService.getECListByYear(evaluateCount);
				if(StringUtils.isNotEmty(blocid)){
					String blocname = evaluateCountService.selBlocname(Integer.parseInt(blocid));
					dataMap.put("time", year+"年"+",");
				}else{
					dataMap.put("blocname", "");
					dataMap.put("time", year+"年"+",");
				}
				
			}else{
				if(StringUtils.isEmty(month)){
					month=getDateTime();
				}
				evaluateCount.setMonth(month);
				eclist =evaluateCountService.getEvaluateCountListByMonth(evaluateCount);
				eclist1 =evaluateCountService.getECListByMonth(evaluateCount);
				if(StringUtils.isNotEmty(blocid)){
					String blocname = evaluateCountService.selBlocname(Integer.parseInt(blocid));
					dataMap.put("time", month.substring(0,4)+"年"+month.substring(5,7)+"月"+",");
				}else{
					dataMap.put("blocname", "");
					dataMap.put("time", month.substring(0,4)+"年"+month.substring(5,7)+"月"+",");
				}
			}
			
			for(int i=0;i<eclist.size();i++){
				DecimalFormat  df = new  DecimalFormat("0.00");
				EvaluateCount ec= (EvaluateCount)eclist.get(i);
				Integer c4 = Integer.parseInt(ec.getCount1())+
							 Integer.parseInt(ec.getCount2())+
							 Integer.parseInt(ec.getCount3());
				if(c4 == 0){
					ec.setCount4("0");
				}else{
					Integer c1 = Integer.parseInt(ec.getCount1());
					Double d4 = ((Double.valueOf(c1)/c4)*100);
					ec.setCount4(df.format(d4)+"%");
					
				}
			}
			
			for(int i=0;i<eclist1.size();i++){
				EvaluateCount ec1= (EvaluateCount)eclist1.get(i);
				if(ec1.getEvalevel() == 1){
					ec1.setEvalevelname("好评");
				}else if(ec1.getEvalevel() == 3){
					ec1.setEvalevelname("中评");
				}else if(ec1.getEvalevel() == 2){
					ec1.setEvalevelname("差评");
				}else{
					ec1.setEvalevelname("");
				}
			}
			
			dataMap.put("condition", "评价统计数据如下：");
			dataMap.put("list", eclist);
			dataMap.put("list1", eclist1);
	        
			ExportToWord.getInstance().exportWord(dataMap,WebConstants.FM_EC_WORD,"评价统计");
			
		} catch (Exception e) {
			logger.error("EvaluateCountAction 的方法 exportWord 执行出错，原因：" + e, e);
		}
		
	}
	
	/**
	 * 评价统计详情
	 * 
	 * @return
	 */
	public String getEvaluateCountDetails() {
		try {
			initMap();
			if(evaluateCount==null){
				evaluateCount = new EvaluateCount();
			}
			if (StringUtils.isNotEmty(blocid)) {
				evaluateCount.setBlocid(Integer.parseInt(blocid));
			}
			if(StringUtils.isNotEmty(carnumber)){
				evaluateCount.setCarnumber(URLDecoder.decode(carnumber, "UTF-8"));
			}
			if(StringUtils.isNotEmty(datetime)){
				if(datetime.length() == 7){
					evaluateCount.setMonth(URLDecoder.decode(datetime, "UTF-8"));
				}else if(datetime.length() == 10){
					evaluateCount.setDay(URLDecoder.decode(datetime, "UTF-8"));
				}
			}
			if (StringUtils.isNotEmty(evalevel)) {
				evaluateCount.setEvalevel(Integer.parseInt(evalevel));
			}
			
			
			result = evaluateCountService.getEvaluateCountDetails(this.getPage(), this.getLimit(), evaluateCount);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("EvaluateCountAction 的方法 getEvaluateCountDetails 执行出错，原因：" + e,e);
			return ERROR;
		}
	}

	public EvaluateCount getEvaluateCount() {
		return evaluateCount;
	}

	public void setEvaluateCount(EvaluateCount evaluateCount) {
		this.evaluateCount = evaluateCount;
	}

	public EvaluateCountService getEvaluateCountService() {
		return evaluateCountService;
	}

	public void setEvaluateCountService(EvaluateCountService evaluateCountService) {
		this.evaluateCountService = evaluateCountService;
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

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

//	public List<EvaluateCount> getEclist() {
//		return eclist;
//	}
//
//	public void setEclist(List<EvaluateCount> eclist) {
//		this.eclist = eclist;
//	}

	public String getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getEvalevel() {
		return evalevel;
	}

	public void setEvalevel(String evalevel) {
		this.evalevel = evalevel;
	}
	
	
	
}
