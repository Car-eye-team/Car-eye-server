package com.careye.car.action;

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
import com.careye.car.domain.CarCondition;
import com.careye.car.domain.ClockInfo;
import com.careye.car.service.CarConditionService;
import com.careye.common.domain.TerminalPositionInfo;
import com.careye.constant.WebConstants;
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
 * @创建时间：2015-10-20 下午05:46:30
 * @修改人：ll
 * @修改时间：2015-10-20 下午05:46:30
 * @修改备注：
 * @version 1.0
 */
public class CarConditionAction extends BasePageAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CarConditionAction.class);
	
	private CarConditionService carConditionService;
	private CarCondition carCondition;
	
	private List<CarCondition> cclist1 = new ArrayList<CarCondition>();
	
	private String blocid;
	private String carnumber;
	private String stime;
	private String etime;
	private String month;
	
	private String fileName;
	
	private Map result;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 车辆统计信息列表
	 * @return
	 */
	public String getCarConditionList(){
		initMap();
		try {
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(carCondition==null){
				carCondition=new CarCondition();
			}
			
			Integer userid = null;
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				userid = SessionUtils.getUserId();
				carCondition.setUserid(userid);
			}
			if(StringUtils.isNotEmty(blocid)){
				carCondition.setBlocid(Integer.parseInt(blocid));
			}
			if(StringUtils.isNotEmty(carnumber)){
				carCondition.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase().trim());
			}
			if(StringUtils.isNotEmty(month)){
				int dayInt = DateUtil.getMaxDayOfMonth(URLDecoder.decode(month,"UTF-8").trim());
				carCondition.setStime(URLDecoder.decode(month,"UTF-8").trim()+"-01 00:00:00");
				carCondition.setEtime(URLDecoder.decode(month,"UTF-8").trim()+"-"+dayInt+" 23:59:59");
			}
			result = carConditionService.getCarConditionList(carCondition, this.getPage(), this.getLimit());
			List<CarCondition> list = (List<CarCondition>) result.get("list");
			for(CarCondition info : list){
				info.setCreatetime(info.getCreatetime().substring(0,10));
				
				if(StringUtils.isNotEmty(info.getOnlinetimes())){
					int idlingtime = (int) (Double.parseDouble(info.getOnlinetimes()) * 3600);
					int hour = idlingtime/(60*60);
					int min = (idlingtime%(60*60))/60;
					int secord = (idlingtime%(60*60))%60;
					String runtimestr = ""; 
					if(hour != 0){
						runtimestr += hour+"时";
					}
					if(min != 0){
						runtimestr += min+"分";
					}else if(hour != 0){
						runtimestr += min+"分";
					}
					if(secord != 0){
						runtimestr += secord+"秒";
					}
					info.setOnlinetimes(runtimestr);
				}
			}
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarConditionAction的方法 getCarConditionList执行出错，原因：" + e, e);
			return ERROR;
		}

	}
	
	/**
	 * word导出
	 * exportWord
	 */
	public void exportWord(){
		try {
			initMap();
		    Map<String,Object> dataMap=new HashMap<String,Object>();
		    dataMap.put("Title", "车辆在线时长统计");
		    
		    String st = DateUtil.getSQLDate();
		    String y = st.substring(0, 4);
		    String m = st.substring(5, 7);
		    String today = st.substring(8, 10);
			
		    dataMap.put("year", y+"年");  
	        dataMap.put("month", m+"月");  
	        dataMap.put("date", today+"日");
			
	        
	        if(carCondition==null){
				carCondition=new CarCondition();
			}
	        if(StringUtils.isNotEmty(blocid)){
	        	carCondition.setBlocid(Integer.parseInt(blocid));
				String bname = carConditionService.getBname(Integer.parseInt(blocid));
				dataMap.put("bname", "企业"+"【"+bname+"】"+",");
			}else{
				carCondition.setBlocid(SessionUtils.getUser().getBlocid());
				String bname = carConditionService.getBname(carCondition.getBlocid());
				dataMap.put("bname", "企业"+"【"+bname+"】"+",");
			}
			if(StringUtils.isNotEmty(carnumber)){
				carCondition.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"),"utf-8"));
				dataMap.put("carnumber", "车牌号"+"【"+new String(carnumber.getBytes("ISO-8859-1"),"utf-8")+"】"+",");
			}else{
				dataMap.put("carnumber", "");
			}
			if(StringUtils.isNotEmty(month)){
				carCondition.setStime(URLDecoder.decode(month,"UTF-8"));
				dataMap.put("time", month.substring(0,4)+"年"+month.substring(5,7)+"月，");
			}else{
				dataMap.put("time", "");
			}
			dataMap.put("condition", "车辆在线时长统计数据如下：");
	        
			cclist1 = carConditionService.getWordList(carCondition);
			for(int i=0;i<cclist1.size();i++){
				CarCondition cc1= (CarCondition)cclist1.get(i);
				cc1.setCreatetime(cc1.getCreatetime().substring(0,10));
				
				if(StringUtils.isNotEmty(cc1.getOnlinetimes())){
					int idlingtime = (int) (Double.parseDouble(cc1.getOnlinetimes()) * 3600);
					int hour = idlingtime/(60*60);
					int min = (idlingtime%(60*60))/60;
					int secord = (idlingtime%(60*60))%60;
					String runtimestr = ""; 
					if(hour != 0){
						runtimestr += hour+"时";
					}
					if(min != 0){
						runtimestr += min+"分";
					}else if(hour != 0){
						runtimestr += min+"分";
					}
					if(secord != 0){
						runtimestr += secord+"秒";
					}
					cc1.setOnlinetimes(runtimestr);
				}
			}
			
			dataMap.put("list",cclist1);
	       
	        
			ExportToWord.getInstance().exportWord(dataMap,WebConstants.FM_CARCONDITION_WORD,"车辆在线时长统计");
			
		} catch (Exception e) {
			logger.error("CarConditionAction 的方法 exportWord 执行出错，原因：" + e, e);
		}
	}
	
	/**
     * Excel导出
     * @throws IOException
     */
	public String exportExcel(){
		try {
		 fileName="车辆在线时长统计"; 
    	 HSSFWorkbook book = new HSSFWorkbook();
         Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
        ExcelDownWay exceldownway= new ExcelDownWay();
        
        if(SessionUtils.getUser() == null){
			return ERROR;
		}
		if(carCondition==null){
			carCondition=new CarCondition();
		}
		
		Integer userid = null;
		if(!SessionUtils.getUser().getLoginname().equals("admin")){
			userid = SessionUtils.getUserId();
			carCondition.setUserid(userid);
		}
		if(StringUtils.isNotEmty(blocid)){
			carCondition.setBlocid(Integer.parseInt(blocid));
		}
		if(StringUtils.isNotEmty(carnumber)){
			carCondition.setCarnumber((new String(carnumber.getBytes("iso-8859-1"),"utf-8")).toUpperCase().trim());
		}
		if(StringUtils.isNotEmty(month)){
			int dayInt = DateUtil.getMaxDayOfMonth(URLDecoder.decode(month,"UTF-8").trim());
			carCondition.setStime(URLDecoder.decode(month,"UTF-8").trim()+"-01 00:00:00");
			carCondition.setEtime(URLDecoder.decode(month,"UTF-8").trim()+"-"+dayInt+" 23:59:59");
		}
		result = carConditionService.getCarConditionList(carCondition, 1, Integer.MAX_VALUE);
		List<CarCondition> list = (List<CarCondition>) result.get("list");
		for(CarCondition info : list){
			info.setCreatetime(info.getCreatetime().substring(0,10));
			
			if(StringUtils.isNotEmty(info.getOnlinetimes())){
				int idlingtime = (int) (Double.parseDouble(info.getOnlinetimes()) * 3600);
				int hour = idlingtime/(60*60);
				int min = (idlingtime%(60*60))/60;
				int secord = (idlingtime%(60*60))%60;
				String runtimestr = ""; 
				if(hour != 0){
					runtimestr += hour+"时";
				}
				if(min != 0){
					runtimestr += min+"分";
				}else if(hour != 0){
					runtimestr += min+"分";
				}
				if(secord != 0){
					runtimestr += secord+"秒";
				}
				info.setOnlinetimes(runtimestr);
			}
		}
         
         //2.设置列宽（列数要对应上）
         String str="7,20,20,20,20,20";
         List<String> numberList=Arrays.asList(str.split(","));
         sheet= exceldownway.setColumnWidth(sheet,numberList);
         
         sheet.setDefaultRowHeight((short) 18);
         Row titleRow= sheet.createRow(0);
         titleRow.setHeightInPoints(20);
         
         titleRow.createCell(0).setCellValue("序号");
         titleRow.createCell(1).setCellValue("日期");
         titleRow.createCell(2).setCellValue("企业名称");
         titleRow.createCell(3).setCellValue("终端号码");
         titleRow.createCell(4).setCellValue("车牌号");
         titleRow.createCell(5).setCellValue("在线时长");
         
         for(int i=0;i<titleRow.getLastCellNum();i++){
        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
         }
         
         if(list.size()>0){
             for(int i=0;i<list.size();i++){
                 int  index=i+1;
                 Row contentRow= sheet.createRow(index);
                 contentRow.createCell(0).setCellValue(index);
                 CarCondition cInfo= (CarCondition) list.get(i);
                 contentRow.createCell(1).setCellValue(cInfo.getCreatetime());
                 contentRow.createCell(2).setCellValue(cInfo.getBlocname());
                 contentRow.createCell(3).setCellValue(cInfo.getTerminal());
                 contentRow.createCell(4).setCellValue(cInfo.getCarnumber());
                 contentRow.createCell(5).setCellValue(cInfo.getOnlinetimes());
             }
         }
         
         new ExcelDownWay().getCommonExcelListWay(book,fileName);
        
		} catch (Exception e) {
			logger.error("CarConditionAction的方法 exportExcel执行出错，原因：" + e, e);
		}
		return null;
	}

	public CarConditionService getCarConditionService() {
		return carConditionService;
	}

	public void setCarConditionService(CarConditionService carConditionService) {
		this.carConditionService = carConditionService;
	}

	public CarCondition getCarCondition() {
		return carCondition;
	}

	public void setCarCondition(CarCondition carCondition) {
		this.carCondition = carCondition;
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
	

}
