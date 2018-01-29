/**
 * Description: car-eye车辆管理平台
 * 文件名：AlarmAction.java
 * 版本信息：1.0
 * 日期：2014-6-10
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.common.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarDriver;
import com.careye.car.domain.CarInfo;
import com.careye.car.service.CarService;
import com.careye.common.domain.Alarm;
import com.careye.common.domain.AlarmType;
import com.careye.common.domain.SpeedAlarm;
import com.careye.common.service.AlarmService;
import com.careye.common.service.MenuTreeService;
import com.careye.mongodb.MongoDB;
import com.careye.mq.HandleUtil;
import com.careye.system.domain.UserCar;
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：AlarmAction
 * @类描述：报警action
 * @创建人：huangqin
 * @创建时间：2015-3-10 下午03:00:55
 * @修改人：huangqin
 * @修改时间：2014-3-10 下午03:00:55
 * @修改备注：
 * @version 1.0
 */
public class AlarmAction extends BasePageAction{

	private static final Logger logger = Logger.getLogger(AlarmAction.class);

	private AlarmService alarmService;
	private MenuTreeService menuTreeService;
	private CarService carService;
	private Alarm alarm;
	private SpeedAlarm speedAlarm;
	private String stime1;
	private String etime1;
	private String loginname;
	private String stime;
	private String etime;
	private String maxspeed;
	private String speedtime;
	private String carnumber;
	private String alarmkey;
	private String carid;
	private String blocid;
	private String terminal;
	private String flag;	//报警处理标记 1 未处理 2 已处理
	private String ids;
	private String cars;
	private String times;
	private String success;

	private Map result;

	private String userid;
	private String fileName;
	
	private String an;
	private String status;
	
	private List<Alarm> list=new ArrayList<Alarm>();
	
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 获取报警记录报表
	 * @return
	 */
	public String getAlarmList(){
		try {
			initMap();
			if(alarm==null){
				alarm=new Alarm();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				alarm.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				alarm.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				alarm.setBlocid(SessionUtils.getBlocId());
			}
			if(StringUtils.isNotEmty(alarmkey)){
				alarm.setAlarmkey(alarmkey);
			}
			if(StringUtils.isNotEmty(stime)){
				alarm.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				alarm.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(carnumber)){
				alarm.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").trim());
			}
			result = MongoDB.getInstance().getAlarmList(this.getPage(),this.getLimit(), alarm);
			return SUCCESS;

		} catch (Exception e) {
			logger.error("AlarmAction的方法 getAlarmList执行出错，原因：" + e, e);
			return ERROR;
		}
		
	}
	

	/**
	 * 获取报警类型列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String selectAlarmTypeList(){
		initMap();
		try {
			List<AlarmType>list = alarmService.selectAlarmTypeList();
			result.put("list", list);
		} catch (Exception e) {
			logger.error("AlarmAction 的方法 selectAlarmTypeList 执行出错，原因：" + e, e);
		}
		return SUCCESS;
	}

	

	 /**
    * Excel导出
    * @throws IOException
    */
	public  void   exportExcel(){
		try {
		 fileName="报警记录"; 
   	    HSSFWorkbook book = new HSSFWorkbook();
        Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
        Row titleRow= sheet.createRow(0);
        if(alarm==null){
			alarm=new Alarm();
		}
		if(!SessionUtils.getUser().getLoginname().equals("admin")){
			alarm.setUserid(SessionUtils.getUserId());
		}

		if(StringUtils.isNotEmty(blocid)){
			alarm.setBlocid(Integer.parseInt(blocid));
		}
		if(StringUtils.isNotEmty(alarmkey)){
			alarm.setAlarmkey(alarmkey);
		}
		if(StringUtils.isNotEmty(stime)){
			alarm.setStime(new String(stime.getBytes("iso8859-1"),"utf-8"));
		}
		
		if(StringUtils.isNotEmty(etime)){
			alarm.setEtime(new String(etime.getBytes("iso8859-1"),"utf-8"));
		}
		if(StringUtils.isNotEmty(carnumber)){
			alarm.setCarnumber(new String(carnumber.getBytes("iso8859-1"),"utf-8"));
		}
        result = MongoDB.getInstance().getAlarmList(1,Integer.MAX_VALUE, alarm);
        list = (List<Alarm>) result.get("list");
        

        ExcelDownWay exceldownway = new ExcelDownWay();
		// 2.设置列宽（列数要对应上）
		String str = "7,25,25,25,25,25,25,25,25,25,50";
		List<String> numberList = Arrays.asList(str.split(","));
		sheet = exceldownway.setColumnWidth(sheet, numberList);
        
        titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
        titleRow.createCell(1).setCellValue("组织机构");
        titleRow.createCell(2).setCellValue("终端号码");
        titleRow.createCell(3).setCellValue("车牌号");
        titleRow.createCell(4).setCellValue("报警类型");
        titleRow.createCell(5).setCellValue("速度");
        titleRow.createCell(6).setCellValue("方向");
        titleRow.createCell(7).setCellValue("报警时间");
        titleRow.createCell(8).setCellValue("经度");
        titleRow.createCell(9).setCellValue("纬度");
        titleRow.createCell(10).setCellValue("地址");
        HSSFCellStyle style = book.createCellStyle();  
        
        sheet.setDefaultColumnWidth(20);  
        sheet.setDefaultRowHeightInPoints(20); 
        
        for (int i = 0; i < titleRow.getLastCellNum(); i++) {
			titleRow.getCell(i).setCellStyle(
					exceldownway.setBookHeadStyle(book));
		}
        
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                int  index=i+1;
                Row contentRow= sheet.createRow(index);
                contentRow.createCell(0).setCellValue(index);
                Alarm alarm= (Alarm) list.get(i);
                contentRow.createCell(1).setCellValue(alarm.getBlocname());
                contentRow.createCell(2).setCellValue(alarm.getTerminal());
                contentRow.createCell(3).setCellValue(alarm.getCarnumber());
                contentRow.createCell(4).setCellValue(alarm.getAlarmkey());
                contentRow.createCell(5).setCellValue(alarm.getSpeed());
                int value = alarm.getDirection()==null?0:Integer.parseInt(alarm.getDirection());
                String dir = "";
                if(value ==0){
                	dir = "正北";
				}else if(value >0 && value <90){
					dir = "东北";
				}else if(value == 90){
					dir = "正东";
				}else if(value >90 && value <180){
					dir = "东南";
				}else if(value == 180){
					dir = "正南";
				}else if(value >180 && value <270){
					dir = "西南";
				}else if(value == 270){
					dir = "正西";
				}else if(value >270 && value <360){
					dir = "西北";
				}else if(value == 360){
					dir = "正北";
				}
                contentRow.createCell(6).setCellValue(dir);
                contentRow.createCell(7).setCellValue(alarm.getAlarmtime());
                contentRow.createCell(8).setCellValue(alarm.getBlng());
                contentRow.createCell(9).setCellValue(alarm.getBlat());
                contentRow.createCell(10).setCellValue(alarm.getAddress());
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
	 * 删除
	 */
	public String deleteInfo(){
		try {
			initMap();
			
			String carnumber[] = cars.split(",");
			String createtime[] = times.split(",");
			CarInfo co = new CarInfo();
			for (int i = 0; i < carnumber.length; i++) {
				String car = carnumber[i];
				co.setCarnumber(car);
				for (int j = 0; j < createtime.length; j++) {
					String tm =  createtime[j];
					co.setCreatetime(tm);
				}
			}
			
			MongoDB.getInstance().deleteAlarmInfo(co.getCarnumber(),co.getCreatetime());
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CommondLogAction 的方法 deleteCommondLog执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	
	public AlarmService getAlarmService() {
		return alarmService;
	}

	public void setAlarmService(AlarmService alarmService) {
		this.alarmService = alarmService;
	}

	public MenuTreeService getMenuTreeService() {
		return menuTreeService;
	}

	public void setMenuTreeService(MenuTreeService menuTreeService) {
		this.menuTreeService = menuTreeService;
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}

	public SpeedAlarm getSpeedAlarm() {
		return speedAlarm;
	}

	public void setSpeedAlarm(SpeedAlarm speedAlarm) {
		this.speedAlarm = speedAlarm;
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

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
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

	public String getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(String maxspeed) {
		this.maxspeed = maxspeed;
	}

	public String getSpeedtime() {
		return speedtime;
	}

	public void setSpeedtime(String speedtime) {
		this.speedtime = speedtime;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getAlarmkey() {
		return alarmkey;
	}

	public void setAlarmkey(String alarmkey) {
		this.alarmkey = alarmkey;
	}

	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public String getBlocid() {
		return blocid;
	}

	public void setBlocid(String blocid) {
		this.blocid = blocid;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Alarm> getList() {
		return list;
	}

	public void setList(List<Alarm> list) {
		this.list = list;
	}

	public String getAn() {
		return an;
	}

	public void setAn(String an) {
		this.an = an;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCars() {
		return cars;
	}

	public void setCars(String cars) {
		this.cars = cars;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}
	

}
