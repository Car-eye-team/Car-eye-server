package com.careye.monitor.action;

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
import com.careye.car.domain.CarInfo;
import com.careye.car.service.CarService;
import com.careye.constant.WebConstants;
import com.careye.monitor.domain.CommondLog;
import com.careye.monitor.domain.OnlineReport;
import com.careye.monitor.service.OnlineReportService;
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
 * @创建时间：2015-11-6 上午11:17:38
 * @修改人：ll
 * @修改时间：2015-11-6 上午11:17:38
 * @修改备注：
 * @version 1.0
 */
public class OnlineReportAction extends BasePageAction{
	
	private static final Logger logger = Logger.getLogger(OnlineReportAction.class);
	
	private OnlineReportService onlineReportService;
	private CarService carService;
	private OnlineReport onlineReport;
	private OnlineReport or;
	
	private List <OnlineReport> orlist1=new ArrayList<OnlineReport>();
	private List <OnlineReport> orlist2=new ArrayList<OnlineReport>();
	private List <OnlineReport> orlist3=new ArrayList<OnlineReport>();
	
	
	private String blocid;
	private String stime;
	private String etime;
	private String type;
	
	private Map result;
	private String success;
	
	private String fileName;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * word导出
	 */
	public void exportCo(){
		try {
			initMap();
		    Map<String,Object> dataMap=new HashMap<String,Object>();
		    
		    dataMap.put("title", "企业新车上线监管报告");
		    dataMap.put("title1", "新增车辆明细");
		    dataMap.put("title2", "上线车辆");
		    dataMap.put("title3", "未上线车辆");
		    
		    if(onlineReport==null){
				onlineReport=new OnlineReport();
			}
		    
		    if(StringUtils.isNotEmty(blocid)){
				onlineReport.setBlocid(Integer.parseInt(blocid.trim()));
				String bname = onlineReportService.getBname(Integer.parseInt(blocid));
				dataMap.put("bname", "企业"+"【"+bname+"】"+",");
			}else{
				onlineReport.setBlocid(SessionUtils.getUser().getBlocid());
				String bname = onlineReportService.getBname(onlineReport.getBlocid());
				dataMap.put("bname", "企业"+"【"+bname+"】"+",");
			}
			if(StringUtils.isNotEmty(stime)){
				onlineReport.setStime(URLDecoder.decode(stime,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(etime)){
				onlineReport.setEtime(URLDecoder.decode(etime,"UTF-8").trim());
			}
			
			if(StringUtils.isNotEmty(stime) && StringUtils.isNotEmty(etime)){
				dataMap.put("time", stime+"到"+etime+",");
			}else if(StringUtils.isEmty(stime) && StringUtils.isNotEmty(etime)){
				dataMap.put("time", "时间截止到"+etime+",");
			}else if(StringUtils.isNotEmty(stime) && StringUtils.isEmty(etime)){
				dataMap.put("time", "从"+stime+"开始,");
			}else{
				dataMap.put("time", "");
			}
			dataMap.put("condition", "新增上线车辆数据如下：");
			
			or = onlineReportService.getOR(onlineReport);
			or.setUnonlinenumber(or.getAddnumber() - or.getOnlinenumber());
			dataMap.put("addnumber", or.getAddnumber());
			dataMap.put("onlinenumber", or.getOnlinenumber());
			dataMap.put("unonlinenumber", or.getUnonlinenumber());
			
//			result = onlineReportService.getOnlineReportList(this.getPage(), this.getLimit(), onlineReport);
//			List<OnlineReport> list = (List<OnlineReport>) result.get("list");
//			if(list != null && list.size() > 0){
//				for(OnlineReport info : list){
//					info.setUnonlinenumber(info.getAddnumber() - info.getOnlinenumber());
//					dataMap.put("addnumber", info.getAddnumber());
//					dataMap.put("onlinenumber", info.getOnlinenumber());
//					dataMap.put("unonlinenumber", info.getUnonlinenumber());
//					
//				}
//			}
			
			List<Integer>  caridList1 = new ArrayList<Integer>();
			caridList1 = onlineReportService.getNewCarid(onlineReport);
			String caridStr1 = "";
			for(Integer info : caridList1){
				if(info != null){
					caridStr1 += info+",";
				}
			}
			if(caridStr1.length() >= 2){
				caridStr1 = caridStr1.substring(0,caridStr1.length()-1);
			}else{
				caridStr1 = "0";
			}
			orlist1 = onlineReportService.getCarInfoByCaridStr(caridStr1);
				
			List<Integer>  caridList2 = new ArrayList<Integer>();
			caridList2 = onlineReportService.getOnlineCarid(onlineReport);
			String caridStr2 = "";
			for(Integer info : caridList2){
				if(info != null){
					caridStr2 += info+",";
				}
			}
			if(caridStr2.length() >= 2){
				caridStr2 = caridStr2.substring(0,caridStr2.length()-1);
			}else{
				caridStr2 = "0";
			}
			orlist2 = onlineReportService.getCarInfoByCaridStr(caridStr2);
				
			List<Integer>  caridList3 = new ArrayList<Integer>();
			caridList3 = onlineReportService.getUnonlineCarid(onlineReport);
			String caridStr3 = "";
			for(Integer info : caridList3){
				if(info != null){
					caridStr3 += info+",";
				}
			}
			if(caridStr3.length() >= 2){
				caridStr3 = caridStr3.substring(0,caridStr3.length()-1);
			}else{
				caridStr3 = "0";
			}
			orlist3 = onlineReportService.getCarInfoByCaridStr(caridStr3);
				
			dataMap.put("list", orlist1);
			dataMap.put("list1", orlist2);
			dataMap.put("list2", orlist3);
	        
			ExportToWord.getInstance().exportWord(dataMap,WebConstants.FM_OR_WORD,"企业新车上线");
			
		} catch (Exception e) {
			logger.error("OnlineReportAction 的方法 exportCo 执行出错，原因：" + e, e);
		}
	}
	
	
	/**
	 * 新车上线监管
	 * @return
	 */
	public String getOnlineReportList() {

		try {
			initMap();
			if(onlineReport==null){
				onlineReport=new OnlineReport();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				onlineReport.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				onlineReport.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				onlineReport.setBlocid(SessionUtils.getUser().getBlocid());
			}
			if(StringUtils.isNotEmty(stime)){
				onlineReport.setStime(URLDecoder.decode(stime,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(etime)){
				onlineReport.setEtime(URLDecoder.decode(etime,"UTF-8").trim());
			}
			
			result = onlineReportService.getOnlineReportList(this.getPage(), this.getLimit(), onlineReport);
			
			List<OnlineReport> list = (List<OnlineReport>) result.get("list");
			if(list != null && list.size() > 0){
				for(OnlineReport info : list){
					info.setUnonlinenumber(info.getAddnumber() - info.getOnlinenumber());
					
					DecimalFormat df = new DecimalFormat("#.##");//保留小数点后两位有效数字
					double percent = (double)info.getOnlinenumber()/info.getAddnumber();
					info.setOnlinepercent(df.format(StringUtils.getTwoFloat(percent))+"%");
				}
			}
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("OnlineReportAction的方法 getOnlineReportList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 分页查询车辆详细
	 * @return
	 */
	public String findCarInfoList() {

		try {
			initMap();
			if(onlineReport==null){
				onlineReport=new OnlineReport();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				onlineReport.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				onlineReport.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				onlineReport.setBlocid(SessionUtils.getUser().getBlocid());
			}
			if(StringUtils.isNotEmty(stime)){
				onlineReport.setStime(URLDecoder.decode(stime,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(etime)){
				onlineReport.setEtime(URLDecoder.decode(etime,"UTF-8").trim());
			}
			
			List<Integer>  caridList = new ArrayList<Integer>();
			if(type == null || "0".equals(type)){
				return SUCCESS;
			}else if("1".equals(type)){
				caridList = onlineReportService.getNewCarid(onlineReport);
			}else if("2".equals(type)){
				caridList = onlineReportService.getOnlineCarid(onlineReport);
			}else if("3".equals(type)){
				caridList = onlineReportService.getUnonlineCarid(onlineReport);
			}
			
			String caridStr = "";
			for(Integer info : caridList){
				if(info != null){
					caridStr += info+",";
				}
			}
			if(caridStr.length() >= 2){
				caridStr = caridStr.substring(0,caridStr.length()-1);
			}
			
			result = carService.getCarInfoListByCaridStr(this.getPage(), this.getLimit(), caridStr);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("OnlineReportAction的方法 findCarInfoList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 导出车辆详细
	 * @return
	 */
	public void exportExcel() {

		try {
			fileName="车辆信息"; 
	    	 HSSFWorkbook book = new HSSFWorkbook();
	         Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
	        ExcelDownWay exceldownway= new ExcelDownWay();
	         
	         //2.设置列宽（列数要对应上）
	         String str="7,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,25,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20";
	         List<String> numberList=Arrays.asList(str.split(","));
	         sheet= exceldownway.setColumnWidth(sheet,numberList);
	         
	         sheet.setDefaultRowHeight((short) 18);
	         Row titleRow= sheet.createRow(0);
	         titleRow.setHeightInPoints(20);
			
			initMap();
			if(onlineReport==null){
				onlineReport=new OnlineReport();
			}
			if(SessionUtils.getUser() == null){
				return ;
			}else{
				onlineReport.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				onlineReport.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				onlineReport.setBlocid(SessionUtils.getUser().getBlocid());
			}
			if(StringUtils.isNotEmty(stime)){
				onlineReport.setStime(URLDecoder.decode(stime,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(etime)){
				onlineReport.setEtime(URLDecoder.decode(etime,"UTF-8").trim());
			}
			
			List<Integer>  caridList = new ArrayList<Integer>();
			if(type == null || "0".equals(type)){
				return ;
			}else if("1".equals(type)){
				caridList = onlineReportService.getNewCarid(onlineReport);
			}else if("2".equals(type)){
				caridList = onlineReportService.getOnlineCarid(onlineReport);
			}else if("3".equals(type)){
				caridList = onlineReportService.getUnonlineCarid(onlineReport);
			}
			
			String caridStr = "";
			for(Integer info : caridList){
				if(info != null){
					caridStr += info+",";
				}
			}
			if(caridStr.length() >= 2){
				caridStr = caridStr.substring(0,caridStr.length()-1);
			}
			
			result = carService.getCarInfoListByCaridStr(1, Integer.MAX_VALUE, caridStr);
			List<CarInfo> list = (List<CarInfo>) result.get("list");
			if(list == null){
				list = new ArrayList<CarInfo>();
			}
			
			titleRow.createCell(0).setCellValue("序号");
	         titleRow.createCell(1).setCellValue("集团");
	         titleRow.createCell(2).setCellValue("车牌号");
	         titleRow.createCell(3).setCellValue("终端设备号");
	 //        titleRow.createCell(3).setCellValue("设备号");
	         titleRow.createCell(4).setCellValue("车辆状态");
	         titleRow.createCell(5).setCellValue("设备类型");
	         titleRow.createCell(6).setCellValue("设备型号");
	         titleRow.createCell(7).setCellValue("车辆类型");
	         titleRow.createCell(8).setCellValue("车主手机号");
	         titleRow.createCell(9).setCellValue("密码");
	         titleRow.createCell(10).setCellValue("车辆用途");
	         titleRow.createCell(11).setCellValue("车牌颜色");
	         titleRow.createCell(12).setCellValue("省");
	         titleRow.createCell(13).setCellValue("市");
	         titleRow.createCell(14).setCellValue("县/区");
	         titleRow.createCell(15).setCellValue("车架号");
	         titleRow.createCell(16).setCellValue("发动机号");
	         titleRow.createCell(17).setCellValue("车身颜色");
	         titleRow.createCell(18).setCellValue("生产厂家");
	         titleRow.createCell(19).setCellValue("车辆型号");
	         titleRow.createCell(20).setCellValue("经营权号");
	         titleRow.createCell(21).setCellValue("车主姓名");
	         titleRow.createCell(22).setCellValue("车主地址");
	         titleRow.createCell(23).setCellValue("安装日期");
	         titleRow.createCell(24).setCellValue("登记日期");
	         titleRow.createCell(25).setCellValue("创建时间");
	         titleRow.createCell(26).setCellValue("备注");
	         titleRow.createCell(27).setCellValue("核定载客");
	         titleRow.createCell(28).setCellValue("所有权性质");
	         titleRow.createCell(29).setCellValue("入户日期");
	         titleRow.createCell(30).setCellValue("出厂日期");
	         titleRow.createCell(31).setCellValue("燃料类型");
	         titleRow.createCell(32).setCellValue("发动机排量");
	         titleRow.createCell(33).setCellValue("排放标准");
	         titleRow.createCell(34).setCellValue("当前状态");
	         titleRow.createCell(35).setCellValue("合同承包期(年)");
	         titleRow.createCell(36).setCellValue("财产险");
	         titleRow.createCell(37).setCellValue("交强险");
	         titleRow.createCell(38).setCellValue("乘座险");
	         titleRow.createCell(39).setCellValue("三责险");
	         titleRow.createCell(40).setCellValue("车损险");
	         titleRow.createCell(41).setCellValue("电子标签状态");
	         
	         for(int i=0;i<titleRow.getLastCellNum();i++){
	        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
	         }
	         
	         
	         if(list.size()>0){
	             for(int i=0;i<list.size();i++){
	                 int  index=i+1;
	                 Row contentRow= sheet.createRow(index);
	                 contentRow.createCell(0).setCellValue(index);
	                 CarInfo cInfo= (CarInfo) list.get(i);
	                 contentRow.createCell(1).setCellValue(cInfo.getBlocname());
	                 contentRow.createCell(2).setCellValue(cInfo.getCarnumber());
	                 contentRow.createCell(3).setCellValue(cInfo.getTerminal());
	  //               contentRow.createCell(4).setCellValue(cInfo.getDevicenumber());
	                 String statusInteger = cInfo.getCarstatus()+"";
	                 String statusString = "";
	                 if("7".equals(statusInteger)){
	                	 statusString = "在线";
	                 }else if("1".equals(statusInteger)){
	                	 statusString = "长时间离线";
	                 }else if("2".equals(statusInteger)){
	                	 statusString = "离线";
	                 }else if("3".equals(statusInteger)){
	                	 statusString = "熄火";
	                 }else if("5".equals(statusInteger)){
	                	 statusString = "行驶";
	                 }else if("4".equals(statusInteger)){
	                	 statusString = "停车";
	                 }else if("6".equals(statusInteger)){
	                	 statusString = "报警";
	                 }else if("8".equals(statusInteger)){
	                	 statusString = "未定位";
	                 }
	                 contentRow.createCell(4).setCellValue(statusString);
	                 contentRow.createCell(5).setCellValue(cInfo.getTypename());
	                 contentRow.createCell(6).setCellValue(cInfo.getDevicemodel());
	                 contentRow.createCell(7).setCellValue(cInfo.getCartypename());
	                 contentRow.createCell(8).setCellValue(cInfo.getPhone());
	                 contentRow.createCell(9).setCellValue(cInfo.getPassword());
	                 contentRow.createCell(10).setCellValue(cInfo.getUsename());
	                 String color = cInfo.getColor();
	                 String colorString = "";
	                 if("1".equals(color)){
	                	 colorString = "蓝色";
	                 }else if("2".equals(color)){
	                	 colorString = "黄色";
	                 }else if("3".equals(color)){
	                	 colorString = "黑色";
	                 }else if("4".equals(color)){
	                	 colorString = "白色";
	                 }else if("5".equals(color)){
	                	 colorString = "红色";
	                 }else if("6".equals(color) ){
	                	 colorString = "紫色";
	                 }else if("9".equals(color)){
	                	 colorString = "其他";
	                 }
	                 contentRow.createCell(11).setCellValue(colorString);                
	                 contentRow.createCell(12).setCellValue(cInfo.getProvincename());
	                 contentRow.createCell(13).setCellValue(cInfo.getCityname());
	                 contentRow.createCell(14).setCellValue(cInfo.getDistrictname());
	                 contentRow.createCell(15).setCellValue(cInfo.getFramenumber());
	                 contentRow.createCell(16).setCellValue(cInfo.getEnginenumber());
	                 String carcolor = cInfo.getCarcolor();
	                 String carcolorString = "";
	                 if("1".equals(carcolor)){
	                	 carcolorString = "蓝色";
	                 }else if("2".equals(carcolor)){
	                	 carcolorString = "黄色";
	                 }else if("3".equals(carcolor)){
	                	 carcolorString = "黑色";
	                 }else if("4".equals(carcolor)){
	                	 carcolorString = "白色";
	                 }else if("5".equals(carcolor)){
	                	 carcolorString = "红色";
	                 }else if("6".equals(carcolor) ){
	                	 carcolorString = "紫色";
	                 }else if("7".equals(carcolor) ){
	                	 carcolorString = "绿黄";
	                 }else if("8".equals(carcolor) ){
	                	 carcolorString = "青绿加亮银";
	                 }else if("9".equals(carcolor) ){
	                	 carcolorString = "蓝黄";
	                 }else if("10".equals(carcolor) ){
	                	 carcolorString = "艳绿加银灰";
	                 }else if("11".equals(carcolor) ){
	                	 carcolorString = "绿色";
	                 }else if("12".equals(carcolor) ){
	                	 carcolorString = "晶蓝加亮银";
	                 }else if("13".equals(carcolor) ){
	                	 carcolorString = "灰色";
	                 }else if("14".equals(carcolor) ){
	                	 carcolorString = "墨绿色";
	                 }else if("15".equals(carcolor) ){
	                	 carcolorString = "深蓝色";
	                 }else if("16".equals(carcolor) ){
	                	 carcolorString = "棕色";
	                 }else if("99".equals(carcolor)){
	                	 carcolorString = "其他";
	                 }
	                 contentRow.createCell(17).setCellValue(carcolorString);
	                 contentRow.createCell(18).setCellValue(cInfo.getFactory());
	                 contentRow.createCell(19).setCellValue(cInfo.getCarmodel());
	                 contentRow.createCell(20).setCellValue(cInfo.getManagenumber());
	                 contentRow.createCell(21).setCellValue(cInfo.getOwnername());
	                 contentRow.createCell(22).setCellValue(cInfo.getOwneraddress());
	                 contentRow.createCell(23).setCellValue(cInfo.getInstalltime());
	                 contentRow.createCell(24).setCellValue(cInfo.getRegistertime());
	                 contentRow.createCell(25).setCellValue(cInfo.getCreatetime());
	                 contentRow.createCell(26).setCellValue(cInfo.getRemark());
	                 contentRow.createCell(27).setCellValue(cInfo.getSeatnumber());
	                 contentRow.createCell(28).setCellValue(cInfo.getOwnership());
	                 contentRow.createCell(29).setCellValue(cInfo.getEntertime());
	                 contentRow.createCell(30).setCellValue(cInfo.getFactorytime());
	                 String fueltype = cInfo.getFueltype()+"";
	                 String fueltypestr = "";
	                 if("1".equals(fueltype)){
	                	 fueltypestr = "单一汽油";
	                 }else if("2".equals(fueltype)){
	                	 fueltypestr = "单一柴油";
	                 }else if("3".equals(fueltype)){
	                	 fueltypestr = "燃气";
	                 }else if("4".equals(fueltype)){
	                	 fueltypestr = "双燃料";
	                 }else if("5".equals(fueltype)){
	                	 fueltypestr = "电动";
	                 }else if("9".equals(fueltype)){
	                	 fueltypestr = "其他";
	                 }
	                 contentRow.createCell(31).setCellValue(fueltypestr);
	                 contentRow.createCell(32).setCellValue(cInfo.getEnginecapacity());
	                 contentRow.createCell(33).setCellValue(cInfo.getCapacitystandard());
	                 String nowstatus = cInfo.getNowstatus()+"";
	                 String nowstatusstr = "";
	                 if("1".equals(nowstatus)){
	                	 nowstatusstr = "正常";
	                 }else if("2".equals(nowstatus)){
	                	 nowstatusstr = "注销";
	                 }
	                 contentRow.createCell(34).setCellValue(nowstatusstr);
	                 contentRow.createCell(35).setCellValue(cInfo.getContracttime());
	                 contentRow.createCell(36).setCellValue(cInfo.getProinsure());
	                 contentRow.createCell(37).setCellValue(cInfo.getAccinsure());
	                 contentRow.createCell(38).setCellValue(cInfo.getRidinsure());
	                 contentRow.createCell(29).setCellValue(cInfo.getCominsure());
	                 contentRow.createCell(40).setCellValue(cInfo.getDlwinsure());
	                 contentRow.createCell(41).setCellValue(cInfo.getElectagstatus());
	             }
	         }
	         
	         new ExcelDownWay().getCommonExcelListWay(book,fileName);
		} catch (Exception e) {
			logger.error("OnlineReportAction的方法 exportExcel执行出错，原因：" + e, e);
		}
	}

	public OnlineReportService getOnlineReportService() {
		return onlineReportService;
	}

	public void setOnlineReportService(OnlineReportService onlineReportService) {
		this.onlineReportService = onlineReportService;
	}

	public OnlineReport getOnlineReport() {
		return onlineReport;
	}

	public void setOnlineReport(OnlineReport onlineReport) {
		this.onlineReport = onlineReport;
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

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public OnlineReport getOr() {
		return or;
	}

	public void setOr(OnlineReport or) {
		this.or = or;
	}
	
}
