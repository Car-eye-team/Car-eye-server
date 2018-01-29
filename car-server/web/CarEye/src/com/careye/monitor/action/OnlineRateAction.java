package com.careye.monitor.action;

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
import com.careye.monitor.domain.OnlineRate;
import com.careye.monitor.domain.OperateData;
import com.careye.monitor.service.OnlineRateService;
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
 * @创建时间：2016-4-29 上午11:14:48
 * @修改人：ll
 * @修改时间：2016-4-29 上午11:14:48
 * @修改备注：
 * @version 1.0
 */
public class OnlineRateAction extends BasePageAction {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(OnlineRateAction.class);
	
	private OnlineRateService onlineRateService;
	
	private OnlineRate onlineRate;
	private List<OnlineRate> orlist = new ArrayList<OnlineRate>();
	private List<OnlineRate> orlist1 = new ArrayList<OnlineRate>();
	private List<OnlineRate> orlist2 = new ArrayList<OnlineRate>();
	
	private Integer carid;
	private String blocid;
	private String stime;
	private String etime;
	
	private Map result;
	private String success;
	
	private String fileName;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * word  导出
	 */
	public void exportWordOnlineRate(){
		try {	
			initMap();
			
			Map<String,Object> dataMap=new HashMap<String,Object>();
			
			dataMap.put("Title", "车辆在线率分析");
			dataMap.put("title1", "在线时长明细：");
			dataMap.put("title2", "上下线次数明细：");
			
			String st = DateUtil.getSQLDate();
		    String y = st.substring(0, 4);
		    String m = st.substring(5, 7);
		    String today = st.substring(8, 10);
		    dataMap.put("year", y+"年");  
	        dataMap.put("month", m+"月");  
	        dataMap.put("date", today+"日");
			
			if(onlineRate==null){
				onlineRate=new OnlineRate();
			}
			if(SessionUtils.getUser() == null){
				return;
			}else{
				onlineRate.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				onlineRate.setBlocid(Integer.parseInt(blocid.trim()));
				String bname = onlineRateService.getBname(Integer.parseInt(blocid));
				dataMap.put("bname", "企业"+"【"+bname+"】"+",");
			}else{
				onlineRate.setBlocid(SessionUtils.getUser().getBlocid());
				String bname = onlineRateService.getBname(onlineRate.getBlocid());
				dataMap.put("bname", "企业"+"【"+bname+"】"+",");
			}
			if(StringUtils.isNotEmty(stime)){
				onlineRate.setStime(stime+" 00:00:00");
			}
			if(StringUtils.isNotEmty(etime)){
				onlineRate.setEtime(etime+" 23:59:59");
			}
			if(StringUtils.isEmty(stime) && StringUtils.isEmty(etime)){
				onlineRate.setStime(DateUtil.getCurMonth()+"-01 00:00:00");
				onlineRate.setEtime(DateUtil.getToday()+" 23:59:59");
			}
			if(StringUtils.isNotEmty(onlineRate.getStime()) && StringUtils.isNotEmty(onlineRate.getEtime())){
				dataMap.put("time", onlineRate.getStime()+"到"+onlineRate.getEtime()+",");
			}else if(StringUtils.isEmty(onlineRate.getStime()) && StringUtils.isNotEmty(onlineRate.getEtime())){
				dataMap.put("time", "时间截止到"+onlineRate.getEtime()+",");
			}else if(StringUtils.isNotEmty(onlineRate.getStime()) && StringUtils.isEmty(onlineRate.getEtime())){
				dataMap.put("time", "从"+onlineRate.getStime()+"开始,");
			}else{
				dataMap.put("time", "");
			}
			dataMap.put("condition", "车辆在线率分析数据如下：");
			
			
			orlist = onlineRateService.getOnlineRateWordOne(onlineRate);
			if(orlist != null){
				int i = 0;
				for(OnlineRate info : orlist ){
					i++;
					info.setId(i);
					DecimalFormat df = new DecimalFormat("#0.00");
					if(StringUtils.isNotEmty(info.getOnlinetimes())){
						String starttime = onlineRate.getStime();
						String endtime = null;
						if(StringUtils.isNotEmty(etime) && !DateUtil.getToday().equals(etime)){
							endtime = onlineRate.getEtime();
						}else{
							endtime = DateUtil.getSQLDate();
						}
						int alltimes = DateUtil.secBetween(endtime, starttime);
						String onlinepercent = df.format((Double.parseDouble(info.getOnlinetimes())*3600/alltimes)*100)+"%";
						info.setOnlinepercent(onlinepercent);
						
						info.setOnlinetimes(info.getOnlinetimes()+"小时");
					}
				}
			}
			
			orlist1 = onlineRateService.getOnlineRateWordTwo(onlineRate);
			if(orlist1 != null){
				int i = 0;
				for(OnlineRate info : orlist1 ){
					i++;
					info.setId(i);
					DecimalFormat df = new DecimalFormat("#0.00");
					if(StringUtils.isNotEmty(info.getOnlinetimes())){
						
						info.setOnlinepercent(StringUtils.getTwoFloat(Double.parseDouble(info.getOnlinetimes())/24*100)+"%");
						
						info.setOnlinetimes(info.getOnlinetimes()+"小时");
					}
				}
			}
			
			orlist2 = onlineRateService.getOnlineRateWordThree(onlineRate);
			
			
			dataMap.put("list",orlist);
			dataMap.put("list1",orlist1);
			dataMap.put("list2",orlist2);
			
			ExportToWord.getInstance().exportWord(dataMap,WebConstants.FM_ONLINERATE_WORD,"车辆在线率分析");
			
		} catch (Exception e) {
				logger.error("OnlineRateAction的方法 exportWordOnlineRate执行出错，原因：" + e, e);
		}
	}
	
	/**
	 * 分页查询车辆信息
	 * @return
	 */
	public String findCarInfoList() {

		try {
			initMap();
			if(onlineRate==null){
				onlineRate=new OnlineRate();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				onlineRate.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				onlineRate.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				onlineRate.setBlocid(SessionUtils.getUser().getBlocid());
			}
			if(StringUtils.isNotEmty(stime)){
				onlineRate.setStime(stime+" 00:00:00");
			}
			if(StringUtils.isNotEmty(etime)){
				onlineRate.setEtime(etime+" 23:59:59");
			}
			if(StringUtils.isEmty(stime) && StringUtils.isEmty(etime)){
				onlineRate.setStime(DateUtil.getCurMonth()+"-01 00:00:00");
				onlineRate.setEtime(DateUtil.getToday()+" 23:59:59");
			}
			
			result = onlineRateService.findCarInfoList(this.getPage(), this.getLimit(), onlineRate);
			List<OnlineRate> list = (List<OnlineRate>) result.get("list");
			if(list != null){
				int i = 0;
				for(OnlineRate info : list ){
					i++;
					info.setId(i);
					DecimalFormat df = new DecimalFormat("#0.00");
					if(StringUtils.isNotEmty(info.getOnlinetimes())){
						String starttime = onlineRate.getStime();
						String endtime = null;
						if(StringUtils.isNotEmty(etime) && !DateUtil.getToday().equals(etime)){
							endtime = onlineRate.getEtime();
						}else{
							endtime = DateUtil.getSQLDate();
						}
						int alltimes = DateUtil.secBetween(endtime, starttime);
						String onlinepercent = df.format((Double.parseDouble(info.getOnlinetimes())*3600/alltimes)*100)+"%";
						info.setOnlinepercent(onlinepercent);
						
						info.setOnlinetimes(info.getOnlinetimes()+"h");
					}
				}
			}
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("OnlineRateAction的方法 findCarInfoList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
     * Excel导出
     * @throws IOException
     */
	public String exportExcel(){
		try {
		 fileName="车辆在线率分析"; 
    	 HSSFWorkbook book = new HSSFWorkbook();
         Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
        ExcelDownWay exceldownway= new ExcelDownWay();
         
         //2.设置列宽（列数要对应上）
         String str="7,20,20,20,20,20,20";
         List<String> numberList=Arrays.asList(str.split(","));
         sheet= exceldownway.setColumnWidth(sheet,numberList);
         
         sheet.setDefaultRowHeight((short) 18);
         Row titleRow= sheet.createRow(0);
         titleRow.setHeightInPoints(20);
         
         initMap();
			if(onlineRate==null){
				onlineRate=new OnlineRate();
			}
			if(SessionUtils.getUser() == null){
				return ERROR;
			}else{
				onlineRate.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				onlineRate.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				onlineRate.setBlocid(SessionUtils.getUser().getBlocid());
			}
			if(StringUtils.isNotEmty(stime)){
				onlineRate.setStime(stime+" 00:00:00");
			}
			if(StringUtils.isNotEmty(etime)){
				onlineRate.setEtime(etime+" 23:59:59");
			}
			if(StringUtils.isEmty(stime) && StringUtils.isEmty(etime)){
				onlineRate.setStime(DateUtil.getCurMonth()+"-01 00:00:00");
				onlineRate.setEtime(DateUtil.getToday()+" 23:59:59");
			}
			
			result = onlineRateService.findCarInfoList(1, Integer.MAX_VALUE, onlineRate);
			List<OnlineRate> list = (List<OnlineRate>) result.get("list");
			if(list != null){
				int i = 0;
				for(OnlineRate info : list ){
					i++;
					info.setId(i);
					DecimalFormat df = new DecimalFormat("#0.00");
					if(StringUtils.isNotEmty(info.getOnlinetimes())){
						String starttime = onlineRate.getStime();
						String endtime = null;
						if(StringUtils.isNotEmty(etime) && !DateUtil.getToday().equals(etime)){
							endtime = onlineRate.getEtime();
						}else{
							endtime = DateUtil.getSQLDate();
						}
						int alltimes = DateUtil.secBetween(endtime, starttime);
						String onlinepercent = df.format((Double.parseDouble(info.getOnlinetimes())*3600/alltimes)*100)+"%";
						info.setOnlinepercent(onlinepercent);
						
						info.setOnlinetimes(info.getOnlinetimes()+"h");
					}
				}
			}else{
				list = new ArrayList<OnlineRate>();
			}
         
         titleRow.createCell(0).setCellValue("序号");
         titleRow.createCell(1).setCellValue("企业");
         titleRow.createCell(2).setCellValue("车牌号");
         titleRow.createCell(3).setCellValue("上线次数");
         titleRow.createCell(4).setCellValue("下线次数");
         titleRow.createCell(5).setCellValue("在线时长");
         titleRow.createCell(6).setCellValue("在线时间比");
         
         for(int i=0;i<titleRow.getLastCellNum();i++){
        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
         }
         
         
         if(list.size()>0){
             for(int i=0;i<list.size();i++){
                 int  index=i+1;
                 Row contentRow= sheet.createRow(index);
                 contentRow.createCell(0).setCellValue(index);
                 OnlineRate cInfo= (OnlineRate) list.get(i);
                 contentRow.createCell(1).setCellValue(cInfo.getBlocname());
                 contentRow.createCell(2).setCellValue(cInfo.getCarnumber());
                 contentRow.createCell(3).setCellValue(cInfo.getInlinecount());
                 contentRow.createCell(4).setCellValue(cInfo.getOfflinecount());
                 contentRow.createCell(5).setCellValue(cInfo.getOnlinetimes());
                 contentRow.createCell(6).setCellValue(cInfo.getOnlinepercent());
             }
         }
         
         new ExcelDownWay().getCommonExcelListWay(book,fileName);
        
		} catch (Exception e) {
			logger.error("OperateDataAction的方法 exportExcel执行出错，原因：" + e, e);
		}
		return null;
	}
	
	/**
	 * 分页查询在线时长
	 * @return
	 */
	public String findTwoCarInfoList() {

		try {
			initMap();
			if(onlineRate==null){
				onlineRate=new OnlineRate();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				onlineRate.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				onlineRate.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				onlineRate.setBlocid(SessionUtils.getUser().getBlocid());
			}
			if(StringUtils.isNotEmty(stime)){
				onlineRate.setStime(stime+" 00:00:00");
			}
			if(StringUtils.isNotEmty(etime)){
				onlineRate.setEtime(etime+" 23:59:59");
			}
			if(StringUtils.isEmty(stime) && StringUtils.isEmty(etime)){
				onlineRate.setStime(DateUtil.getCurMonth()+"-01 00:00:00");
				onlineRate.setEtime(DateUtil.getToday()+" 23:59:59");
			}
			
			result = onlineRateService.findTwoCarInfoList(this.getPage(), this.getLimit(), onlineRate);
			List<OnlineRate> list = (List<OnlineRate>) result.get("list");
			if(list != null){
				int i = 0;
				for(OnlineRate info : list ){
					i++;
					info.setId(i);
					DecimalFormat df = new DecimalFormat("#0.00");
					if(StringUtils.isNotEmty(info.getOnlinetimes())){
						
						info.setOnlinepercent(StringUtils.getTwoFloat(Double.parseDouble(info.getOnlinetimes())/24*100)+"%");
						
						info.setOnlinetimes(info.getOnlinetimes()+"h");
					}
				}
			}
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("OnlineRateAction的方法 findTwoCarInfoList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 图形显示在线时长
	 * @return
	 */
	public String twoPriterList() {

		try {
			initMap();
			if(onlineRate==null){
				onlineRate=new OnlineRate();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				onlineRate.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				onlineRate.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				onlineRate.setBlocid(SessionUtils.getUser().getBlocid());
			}
			onlineRate.setCarid(carid);
			if(StringUtils.isNotEmty(stime)){
				onlineRate.setStime(stime+" 00:00:00");
			}
			if(StringUtils.isNotEmty(etime)){
				onlineRate.setEtime(etime+" 23:59:59");
			}
			if(StringUtils.isEmty(stime) && StringUtils.isEmty(etime)){
				onlineRate.setStime(DateUtil.getCurMonth()+"-01 00:00:00");
				onlineRate.setEtime(DateUtil.getToday()+" 23:59:59");
			}
			
			result = onlineRateService.findTwoCarInfoList(1, Integer.MAX_VALUE, onlineRate);
			List<OnlineRate> list = (List<OnlineRate>) result.get("list");
			if(list != null){
				int i = 0;
				for(OnlineRate info : list ){
					i++;
					info.setId(i);
					DecimalFormat df = new DecimalFormat("#0.00");
					if(StringUtils.isNotEmty(info.getOnlinetimes())){
						String onlinetimes = df.format(Double.parseDouble(info.getOnlinetimes()));
						info.setOnlinetimes(onlinetimes);
					}
				}
			}
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("OnlineRateAction的方法 twoPriterList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 分页查询上下线次数
	 * @return
	 */
	public String findThreeCarInfoList() {

		try {
			initMap();
			if(onlineRate==null){
				onlineRate=new OnlineRate();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				onlineRate.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				onlineRate.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				onlineRate.setBlocid(SessionUtils.getUser().getBlocid());
			}
			if(StringUtils.isNotEmty(stime)){
				onlineRate.setStime(stime+" 00:00:00");
			}
			if(StringUtils.isNotEmty(etime)){
				onlineRate.setEtime(etime+" 23:59:59");
			}
			if(StringUtils.isEmty(stime) && StringUtils.isEmty(etime)){
				onlineRate.setStime(DateUtil.getCurMonth()+"-01 00:00:00");
				onlineRate.setEtime(DateUtil.getToday()+" 23:59:59");
			}
			
			result = onlineRateService.findThreeCarInfoList(this.getPage(), this.getLimit(), onlineRate);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("OnlineRateAction的方法 findThreeCarInfoList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 图形上下线次数
	 * @return
	 */
	public String threePriterList() {

		try {
			initMap();
			if(onlineRate==null){
				onlineRate=new OnlineRate();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				onlineRate.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				onlineRate.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				onlineRate.setBlocid(SessionUtils.getUser().getBlocid());
			}
			onlineRate.setCarid(carid);
			if(StringUtils.isNotEmty(stime)){
				onlineRate.setStime(stime+" 00:00:00");
			}
			if(StringUtils.isNotEmty(etime)){
				onlineRate.setEtime(etime+" 23:59:59");
			}
			if(StringUtils.isEmty(stime) && StringUtils.isEmty(etime)){
				onlineRate.setStime(DateUtil.getCurMonth()+"-01 00:00:00");
				onlineRate.setEtime(DateUtil.getToday()+" 23:59:59");
			}
			
			result = onlineRateService.findThreeCarInfoList(1, Integer.MAX_VALUE, onlineRate);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("OnlineRateAction的方法 threePriterList执行出错，原因：" + e, e);
			return ERROR;
		}
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public OnlineRateService getOnlineRateService() {
		return onlineRateService;
	}

	public void setOnlineRateService(OnlineRateService onlineRateService) {
		this.onlineRateService = onlineRateService;
	}

	public OnlineRate getOnlineRate() {
		return onlineRate;
	}

	public void setOnlineRate(OnlineRate onlineRate) {
		this.onlineRate = onlineRate;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

}
