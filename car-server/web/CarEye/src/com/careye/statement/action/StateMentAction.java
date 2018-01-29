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

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarDriver;
import com.careye.car.domain.CarInfo;
import com.careye.car.service.CarService;
import com.careye.statement.domain.AreaMore;
import com.careye.statement.domain.CarPosition;
import com.careye.statement.domain.DrivingState;
import com.careye.statement.service.StateMentService;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;


import common.Logger;

/**
 * @项目名称：FMS
 * @类名称：DrivingStateAction
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-26 下午01:47:32
 * @修改人：lxh
 * @修改时间：2014-5-26 下午01:47:32
 * @修改备注：
 * @version 1.0
 */
public class StateMentAction extends BasePageAction{
	private static final Logger logger = Logger.getLogger(StateMentAction.class);
	private StateMentService stateMentService;
	private DrivingState drivingState;
	private CarService carService;
	private CarInfo carInfo;
	private AreaMore areaMore;
	
	public AreaMore getAreaMore() {
		return areaMore;
	}


	public void setAreaMore(AreaMore areaMore) {
		this.areaMore = areaMore;
	}


	private Map result;
	private String success;
	private List list;
	private int su;
	
	private String carnumber;	//车辆id并不是车牌号
	private String carid;	//车辆id并不是车牌号
	private String stime;
	private String etime;
	private String speedtype;
	private String speedvalue;
	private String userid;
	private String blocid;
	private String blocname;
	private CarPosition carPosition ;
	
	private String carids;
	private String flag;
	private String flagTwo;
	private String fileName;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}

	
	/**
	 * 查询车辆里程统计月报表
	 * @return
	 */
	public String selectCheckCarKilo() {
		
		try {
			initMap();
			if(drivingState==null){
				drivingState=new DrivingState();
			}
			if(carInfo==null){
				carInfo=new CarInfo();
			}
			if(StringUtils.isNotEmty(stime)){
				drivingState.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				drivingState.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			
			list = new ArrayList<DrivingState>();
			if(StringUtils.isNotEmty(blocid)&&StringUtils.isEmty(carnumber)){
				carInfo.setUserid(SessionUtils.getUserId());
				carInfo.setBlocid(Integer.parseInt(blocid));
				List<CarInfo> listcar = carService.selectCarListByCheck(carInfo);
				List<DrivingState> list2 = new ArrayList<DrivingState>();
				for (CarInfo carInfo : listcar) {
					DrivingState dState=new DrivingState();
					dState.setPositiontable("to_vehicle_position_" + carInfo.getId());
					dState.setCarid(carInfo.getId());
					list2.add(dState);
				}
				list = stateMentService.selectCarKiloList(drivingState,list2);
			}else {
				String car[] = carnumber.split(",");
				List<DrivingState> list2 = new ArrayList<DrivingState>();
				for (int i = 0; i < car.length; i++) {
					DrivingState dState=new DrivingState();
					dState.setPositiontable("to_vehicle_position_" + car[i]);
					dState.setCarid(Integer.parseInt(car[i]));
					list2.add(dState);
				}
				list = stateMentService.selectCarKiloList(drivingState,list2);
			}
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("StateMentAction的方法 selectCheckCarKilo执行出错，原因：" + e, e);
			return ERROR;
		}
	}


	/**
	 * Excel车辆里程统计月报表
	 * @return
	 */
	public void exportCheckCarKilo() {
		
		try {
			initMap();
			if(drivingState==null){
				drivingState=new DrivingState();
			}
			if(carInfo==null){
				carInfo=new CarInfo();
			}
			if(stime!=null&&!stime.equals("")&&!stime.equals("null")){
				drivingState.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(etime!=null&&!etime.equals("")&&!etime.equals("null")){
				drivingState.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			
			list = new ArrayList<DrivingState>();
			if(StringUtils.isNotEmty(blocid)&&StringUtils.isEmty(carnumber)){
				carInfo.setUserid(Integer.parseInt(userid));
				carInfo.setBlocid(Integer.parseInt(blocid));
				List<CarInfo> listcar = carService.selectCarListByCheck(carInfo);
				List<DrivingState> list2 = new ArrayList<DrivingState>();
				for (CarInfo carInfo : listcar) {
					DrivingState dState=new DrivingState();
					dState.setPositiontable("to_vehicle_position_" + carInfo.getId());
					dState.setCarid(carInfo.getId());
					list2.add(dState);
				}
				list = stateMentService.selectCarKiloList(drivingState,list2);
			}else {
				String car[] = carnumber.split(",");
				List<DrivingState> list2 = new ArrayList<DrivingState>();
				for (int i = 0; i < car.length; i++) {
					DrivingState dState=new DrivingState();
					dState.setPositiontable("to_vehicle_position_" + car[i]);
					dState.setCarid(Integer.parseInt(car[i]));
					list2.add(dState);
				}
				list = stateMentService.selectCarKiloList(drivingState,list2);
			}
			
			String fileName = "里程统计报表";
		   	 HSSFWorkbook book = new HSSFWorkbook();
		     Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
		     Row titleRow= sheet.createRow(0);
			
			titleRow.createCell(0).setCellValue("序号");
	        titleRow.createCell(1).setCellValue("集团");
	        titleRow.createCell(2).setCellValue("终端号码");
	        titleRow.createCell(3).setCellValue("车牌号");
	        titleRow.createCell(4).setCellValue("里程（KM）");
	        if(list.size()>0){
	            for(int i=0;i<list.size();i++){
	                int  index=i+1;
	                Row contentRow= sheet.createRow(index);
	                contentRow.createCell(0).setCellValue(index);
	                DrivingState data= (DrivingState) list.get(i);
	                contentRow.createCell(1).setCellValue(data.getBlocname());
	                contentRow.createCell(2).setCellValue(data.getTerminal());
	                contentRow.createCell(3).setCellValue(data.getCarnumber());
	                contentRow.createCell(4).setCellValue(data.getMileage());
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
	 * 查询车辆位置统计报表
	 * @return
	 */
	public String selectCarPosition() {
		try {
			initMap();
			if(carPosition==null){
				carPosition=new CarPosition();
			}
			if(StringUtils.isNotEmty(blocname)){
				carPosition.setBlocname(URLDecoder.decode(blocname,"UTF-8"));
			}
			if(StringUtils.isNotEmty(flag)){
				carPosition.setFlag(Integer.parseInt(flag));
			}
			if(StringUtils.isNotEmty(flagTwo)){
				carPosition.setFlagTwo(Integer.parseInt(flagTwo));
			}
			if(StringUtils.isNotEmty(stime)){
				carPosition.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				carPosition.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			result = stateMentService.selectCarPosition(this.getPage(), this.getLimit(), carPosition);	 
			return SUCCESS;
		} catch (Exception e) {
			logger.error("StateMentAction的方法 selectCarPosition执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	
	public StateMentService getStateMentService() {
		return stateMentService;
	}


	public void setStateMentService(StateMentService stateMentService) {
		this.stateMentService = stateMentService;
	}


	public DrivingState getDrivingState() {
		return drivingState;
	}


	public void setDrivingState(DrivingState drivingState) {
		this.drivingState = drivingState;
	}


	public CarService getCarService() {
		return carService;
	}


	public void setCarService(CarService carService) {
		this.carService = carService;
	}


	public CarInfo getCarInfo() {
		return carInfo;
	}


	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}


	public Map getResult() {
		return result;
	}


	public CarPosition getCarPosition() {
		return carPosition;
	}


	public void setCarPosition(CarPosition carPosition) {
		this.carPosition = carPosition;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public String getFlagTwo() {
		return flagTwo;
	}


	public void setFlagTwo(String flagTwo) {
		this.flagTwo = flagTwo;
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


	public String getCarnumber() {
		return carnumber;
	}


	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}


	public String getCarid() {
		return carid;
	}


	public void setCarid(String carid) {
		this.carid = carid;
	}


	public String getCarids() {
		return carids;
	}


	public void setCarids(String carids) {
		this.carids = carids;
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


	public String getBlocname() {
		return blocname;
	}


	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}


	public void setEtime(String etime) {
		this.etime = etime;
	}


	public String getSpeedtype() {
		return speedtype;
	}


	public void setSpeedtype(String speedtype) {
		this.speedtype = speedtype;
	}


	public String getSpeedvalue() {
		return speedvalue;
	}


	public void setSpeedvalue(String speedvalue) {
		this.speedvalue = speedvalue;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getBlocid() {
		return blocid;
	}


	public void setBlocid(String blocid) {
		this.blocid = blocid;
	}




	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



}
