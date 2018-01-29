/**
 * Description: car-eye车辆管理平台
 * 文件名：RemoteControlRecordAction.java
 * 版本信息：1.0
 * 日期：2015-3-23
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.monitor.action;

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

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarInfo;
import com.careye.message.domain.SendRecord;
import com.careye.monitor.domain.DispatcherInfo;
import com.careye.monitor.domain.DispatcherRecord;
import com.careye.monitor.service.DispatcherRecordService;
import com.careye.system.action.UserAction;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：RemoteControlRecordAction
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-3-23 下午08:35:37
 * @修改人：Yuqk
 * @修改时间：2015-3-23 下午08:35:37
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("serial")
public class DispatcherRecordAction extends BasePageAction {
	private static final Logger logger = Logger.getLogger(UserAction.class);

	private DispatcherRecord dispatcherRecord;
	private DispatcherInfo dispatcherInfo;
	private DispatcherRecordService dispatcherRecordService;

	private Map result;
	private String success;

	private String blocid;
	private String carnumber;
	private String status;
	private String type;
	private String stime;
	private String etime;
	private String flag;
	private String id;
	private String remark;
	private String emergency;
	private String lcd;
	private String dist;
	
	
	public String getCarids() {
		return carids;
	}

	public void setCarids(String carids) {
		this.carids = carids;
	}

	private String tts;
	private String adv;
	private String action;
    private String carids;
    
	private String fileName;

	private List<DispatcherRecord> list=new ArrayList<DispatcherRecord>();
	
	
	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 分页查询调度记录
	 * 
	 * @return
	 */
	public String  queryDispatcherRecordList() {

		try {
			initMap();
			if(dispatcherRecord==null){
				dispatcherRecord=new DispatcherRecord();
			}
			if (StringUtils.isNotEmty(blocid)) {
				dispatcherRecord.setBlocid(Integer.parseInt(blocid));
			}
			if (StringUtils.isNotEmty(carnumber)) {
				dispatcherRecord.setCarnumber(URLDecoder.decode(carnumber, "UTF-8").toUpperCase().trim());
			}
			if (StringUtils.isNotEmty(status)) {
				dispatcherRecord.setStatus(Integer.parseInt(status));
			}
			if (StringUtils.isNotEmty(stime)) {
				dispatcherRecord.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				dispatcherRecord.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			result = dispatcherRecordService.queryDispatcherRecord(this
					.getPage(), this.getLimit(), dispatcherRecord);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("DispatcherRecordAction的方法 queryDispatcherRecord执行出错，原因："
							+ e, e);
			return ERROR;
		}
	}
	
	/**
	 * 分页查询调度信息
	 * 
	 * @return
	 */
	public String  queryDispatcherInfoList() {

		try {
			initMap();
			if(dispatcherRecord==null){
				dispatcherRecord=new DispatcherRecord();
			}
			result = dispatcherRecordService.queryDispatcherInfo(this
					.getPage(), this.getLimit(), dispatcherInfo);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("DispatcherRecordAction的方法 queryDispatcherInfoList执行出错，原因："
							+ e, e);
			return ERROR;
		}
	}
	/**
	 * 添加或修改调度记录信息
	 * @param carDriver
	 * @return
	 */
	public String saveDispatcherRecord(){
		try {
			initMap();
			if(dispatcherInfo==null){
				dispatcherInfo=new DispatcherInfo();
			}
			if(StringUtils.isNotEmty(id)){
				dispatcherInfo.setId(Integer.parseInt(id));
			}
			if(StringUtils.isNotEmty(remark)){
				dispatcherInfo.setRemark(URLDecoder.decode(remark, "UTF-8"));
			}
			if(StringUtils.isNotEmty(emergency)){
				dispatcherInfo.setEmergency(Integer.parseInt(emergency));
			}
			if(StringUtils.isNotEmty(lcd)){
				dispatcherInfo.setLcd(Integer.parseInt(lcd));
			}
			if(StringUtils.isNotEmty(tts)){
				dispatcherInfo.setTts(Integer.parseInt(tts));
			}
			if(StringUtils.isNotEmty(adv)){
				dispatcherInfo.setAdv(Integer.parseInt(adv));
			}
			if(StringUtils.isNotEmty(action)){
				dispatcherInfo.setAction(Integer.parseInt(action));
			}
			if(StringUtils.isNotEmty(dist)){
				dispatcherInfo.setDist(Integer.parseInt(dist));
			}
			if(StringUtils.isNotEmty(flag)){
				dispatcherInfo.setFlag(Integer.parseInt(flag));
			}
			if(carids!=null&&!"".equals(carids)&&!"null".equals(carids)){
				String  []  datas= carids.split(",");
				for(int j=0;j<datas.length;j++){
					dispatcherInfo.getCarids().add(Integer.parseInt(datas[j]));
				}
				dispatcherInfo.setCaridsStr(carids);
			}
			dispatcherInfo.setUserid(SessionUtils.getUserId());
		    int count =-1;
		    
		    count = dispatcherRecordService.insertDispatcherInfo(dispatcherInfo);
//		    if(dispatcherRecord.getId()==null){
//		    }else{
//					 count=dispatcherRecordService.updateDispatcherInfo(dispatcherInfo);
//			}
			if(count<=0){
					result.put("su", -1);
			}else{
					result.put("su", 1);
			}
			this.success="true";
			return SUCCESS;
			
		} catch (Exception e) {
			result.put("su", -1);
			this.success="false";
			logger.error("DispatcherRecordAction的方法 saveDispatcherRecord执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 导出调度记录
	 * 
	 * @return
	 */
	public void exportDispatcherRecord() {
		     try {
			     //1.设置名字
		    	 fileName = "调度记录";
		    	 HSSFWorkbook book = new HSSFWorkbook();
		         Sheet sheet= book.createSheet(fileName);
		         //sheet.setDefaultColumnWidth(15);
		         
		         ExcelDownWay exceldownway= new ExcelDownWay();
		         
		         //2.设置列宽（列数要对应上）
		         String str="7,15,20,20,12,12,18,18,15,12,25,20,20";
		         List<String> numberList=Arrays.asList(str.split(","));
		         sheet= exceldownway.setColumnWidth(sheet,numberList);
		         
		         sheet.setDefaultRowHeight((short) 18);
		         Row titleRow= sheet.createRow(0);
		         titleRow.setHeightInPoints(20);
		         
		         if(dispatcherRecord==null){
						dispatcherRecord=new DispatcherRecord();
					}
		         if (StringUtils.isNotEmty(blocid)) {
						dispatcherRecord.setBlocid(Integer.parseInt(blocid));
					}
					if (StringUtils.isNotEmty(carnumber)) {
						dispatcherRecord.setCarnumber(new String(carnumber.getBytes(),"utf-8").toUpperCase().trim());
					}
					if (StringUtils.isNotEmty(status)) {
						dispatcherRecord.setStatus(Integer.parseInt(status));
					}
					if (StringUtils.isNotEmty(stime)) {
						dispatcherRecord.setStime(stime);
					}
					if (StringUtils.isNotEmty(etime)) {
						dispatcherRecord.setEtime(etime);
					}
		         
		         list= dispatcherRecordService.selectRecordList(dispatcherRecord);   //Excel查询
		         
		         titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
		         titleRow.createCell(1).setCellValue("车牌号");
		         titleRow.createCell(2).setCellValue("终端设备号");
		         titleRow.createCell(3).setCellValue("集团");
		         titleRow.createCell(4).setCellValue("状态");
		         titleRow.createCell(5).setCellValue("紧急");
		         titleRow.createCell(6).setCellValue("终端显示器显示");
		         titleRow.createCell(7).setCellValue("终端TTS播读");
		         titleRow.createCell(8).setCellValue("广告屏显示");
		         titleRow.createCell(9).setCellValue("电召消息");
		         titleRow.createCell(10).setCellValue("备注");
		         titleRow.createCell(11).setCellValue("创建人");
		         titleRow.createCell(12).setCellValue("创建时间");
		         
		         for(int i=0;i<titleRow.getLastCellNum();i++){
		        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
		         }
		         
		         if(list.size()>0){
		             for(int i=0;i<list.size();i++){
		                 int  index=i+1;
		                 Row contentRow= sheet.createRow(index);
		                 contentRow.createCell(0).setCellValue(index);
		                 DispatcherRecord dispatcherRecord= (DispatcherRecord) list.get(i);
		                 
		                 contentRow.createCell(1).setCellValue(dispatcherRecord.getCarnumber());
		                 contentRow.createCell(2).setCellValue(dispatcherRecord.getTerminal());
		                 contentRow.createCell(3).setCellValue(dispatcherRecord.getBlocname());
		                 String statusString=null;
		                 if(dispatcherRecord.getStatus()!=null){
		                	 int status=dispatcherRecord.getStatus();
		                	 if(status==1){
			                	 statusString="成功";
			                 }else if(status==2){
			                	 statusString="失败";
			                 }
		                 }
		                 
		                 contentRow.createCell(4).setCellValue(statusString);
		                 
		                 String emergencyString=null;
		                 if(dispatcherRecord.getEmergency()!=null){
		                	 int status=dispatcherRecord.getEmergency();
		                	 if(status==1){
		                		 emergencyString="是";
			                 }else if(status==0){
			                	 emergencyString="否";
			                 }
		                 }
		                 
		                 contentRow.createCell(5).setCellValue(emergencyString);
		                 
		                 String lcdString=null;
		                 if(dispatcherRecord.getLcd()!=null){
		                	 int status=dispatcherRecord.getLcd();
		                	 if(status==1){
		                		 lcdString="是";
			                 }else if(status==0){
			                	 lcdString="否";
			                 }
		                 }
		                 contentRow.createCell(6).setCellValue(lcdString);
		                 
		                 String ttsString=null;
		                 if(dispatcherRecord.getTts()!=null){
		                	 int status=dispatcherRecord.getTts();
		                	 if(status==1){
		                		 ttsString="是";
			                 }else if(status==0){
			                	 ttsString="否";
			                 }
		                 }
		                 contentRow.createCell(7).setCellValue(ttsString);
		                 
		                 String advString=null;
		                 if(dispatcherRecord.getAdv()!=null){
		                	 int status=dispatcherRecord.getAdv();
		                	 if(status==1){
		                		 advString="是";
			                 }else if(status==0){
			                	 advString="否";
			                 }
		                 }
		                 contentRow.createCell(8).setCellValue(advString);
		                 
		                 String actionString=null;
		                 if(dispatcherRecord.getAction()!=null){
		                	 int status=dispatcherRecord.getAction();
		                	 if(status==1){
		                		 actionString="是";
			                 }else if(status==0){
			                	 actionString="否";
			                 }
		                 }
		                 contentRow.createCell(9).setCellValue(actionString);
		                 contentRow.createCell(10).setCellValue(dispatcherRecord.getRemark());
		                 contentRow.createCell(11).setCellValue(dispatcherRecord.getUsername());
		                 contentRow.createCell(12).setCellValue(dispatcherRecord.getCreatetime());
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

	public DispatcherRecord getDispatcherRecord() {
		return dispatcherRecord;
	}

	public void setDispatcherRecord(DispatcherRecord dispatcherRecord) {
		this.dispatcherRecord = dispatcherRecord;
	}

	public DispatcherRecordService getDispatcherRecordService() {
		return dispatcherRecordService;
	}

	public void setDispatcherRecordService(
			DispatcherRecordService dispatcherRecordService) {
		this.dispatcherRecordService = dispatcherRecordService;
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

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<DispatcherRecord> getList() {
		return list;
	}
	public void setList(List<DispatcherRecord> list) {
		this.list = list;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public DispatcherInfo getDispatcherInfo() {
		return dispatcherInfo;
	}

	public void setDispatcherInfo(DispatcherInfo dispatcherInfo) {
		this.dispatcherInfo = dispatcherInfo;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public String getLcd() {
		return lcd;
	}

	public void setLcd(String lcd) {
		this.lcd = lcd;
	}

	public String getTts() {
		return tts;
	}

	public void setTts(String tts) {
		this.tts = tts;
	}

	public String getAdv() {
		return adv;
	}

	public void setAdv(String adv) {
		this.adv = adv;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public static Logger getLogger() {
		return logger;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}
	
	
}
