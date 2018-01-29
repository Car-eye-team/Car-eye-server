/**
 * Description: 多森商用车平台
 * 文件名：HandleBusiness.java
 * 版本信息：1.0
 * 日期：2013-8-7
 * Copyright car-eye车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.mq.business;

import java.util.List;
import java.util.Map;


import sun.misc.GC.LatencyRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.careye.car.domain.Alarm;
import com.careye.car.domain.CarInfo;
import com.careye.common.domain.AreaSet;
import com.careye.common.domain.TerminalPositionInfo;
import com.careye.common.service.CommonService;
import com.careye.constant.ServiceConfig;
import com.careye.http.BaiDuHttp;
import com.careye.http.domain.BaiDuInfo;
import com.careye.mongodb.MongoDB;
import com.careye.mq.coder.ActivePush;
import com.careye.utils.DateUtil;

import common.Logger;


/**
 * @项目名称：cvpserver
 * @类名称：HandleBusinessCommon
 * @类描述：区域报警业务公共处理
 * @创建人：wuyongde
 * @创建时间：2016-2-22 上午11:55:49
 * @修改人：wuyongde
 * @修改时间：2016-2-22 上午11:55:49
 * @修改备注：
 * @version 1.0
 */
public class HandleBusinessCommon {
	private final static Logger logger = Logger.getLogger(HandleBusinessCommon.class);


	/**
	 * 区域报警业务处理
	 * @param carInfo
	 * @param dlng 经度
	 * @param dlat 纬度
	 */
	public static void areaAlarm(CarInfo carInfo,Double dlng,Double dlat,String speed,String alarmtime){
		try {
			//得到当前时间在开始时间结束时间之内的车辆区域列表
			CommonService commonService = ServiceConfig.commonService;
			List<AreaSet> aslist = commonService.getCarAreaList(carInfo.getCarnumber());
			boolean speedflag = false; //限速报警标志
			boolean enterflag = false; //进区域报警标志
			boolean exitflag = false;  //出区域报警标志
			logger.info("设备号："+carInfo.getTerminal()+"区域报警记录----"+aslist.size());
			if(aslist.size() >0){
				for(AreaSet areaSet:aslist){
					if(areaSet.getAreatype() != null && areaSet.getAreatype() ==1){//圆形区域
						//两点得到距离
						Double distance = BaiDuHttp.Distance(dlng, dlat, areaSet.getYlng(), areaSet.getYlat());
						if(areaSet.getAttr3() !=null && areaSet.getAttr3() == 1){ //进区域报警给平台报警
							if(!enterflag && distance < areaSet.getRadius()){ //两点距离<半径
								//操作进区域报警
								terminalAlarm(carInfo,alarmtime,2021,"进区域报警",areaSet);
								enterflag = true; //更改状态,防止重复操作进区域报警
							}
						}
						if(areaSet.getAttr5() !=null && areaSet.getAttr5() == 1){ //出区域报警给平台报警
							if(!exitflag && distance > areaSet.getRadius()){ //两点距离>半径
								//操作出区域报警
								terminalAlarm(carInfo,alarmtime,2022,"出区域报警",areaSet);
								exitflag = true; //更改状态,防止重复操作进区域报警
							}
						}
					}else if(areaSet.getAreatype() != null && areaSet.getAreatype() ==2){ //矩形区域
						//判断是否在矩形区域之内
						boolean flag = BaiDuHttp.inArea(areaSet.getLnglt(), areaSet.getLatlt(), 
								areaSet.getLngrb(), areaSet.getLatrb(), dlng, dlat);
						
						if(areaSet.getAttr3() !=null && areaSet.getAttr3() == 1){ //进区域报警给平台报警
							if(!enterflag && flag){ 
								//操作进区域报警
								terminalAlarm(carInfo,alarmtime,2021,"进区域报警",areaSet);
								enterflag = true; //更改状态,防止重复操作进区域报警
							}
						}
						if(areaSet.getAttr5() !=null && areaSet.getAttr5() == 1){ //出区域报警给平台报警
							if(!exitflag && !flag){ 
								//操作进区域报警
								terminalAlarm(carInfo,alarmtime,2022,"出区域报警",areaSet);
								exitflag = true; //更改状态,防止重复操作进区域报警
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	/**
	 * 设备报警
	 * @param protocol
	 * @param message
	 */
	public static void terminalAlarm(CarInfo carInfo,String alarmtime,int alarmtype,String alarmtypestr,AreaSet areaSet){
		try {
			//0 未定位 1 已定位
			BaiDuInfo baiDuInfo = new BaiDuInfo();;
			TerminalPositionInfo tpo = ServiceConfig.commonService.getLngLatByCarid(carInfo.getId());
			if(tpo != null){
				baiDuInfo.setBlat(tpo.getBlat());
				baiDuInfo.setBlng(tpo.getBlng());
				baiDuInfo.setAddress(tpo.getAddress());
			}
			Alarm alarm = new Alarm();
			alarm.setBlocid(1);
			alarm.setBlocname(carInfo.getBlocname());
			alarm.setTerminal(carInfo.getTerminal());
			alarm.setCarnumber(carInfo.getCarnumber());
			alarm.setAlarmname(alarmtypestr);
			alarm.setAlarmtype(alarmtype);
			alarm.setAlarmkey(alarmtype+"");
			alarm.setBlat(tpo.getBlat());
			alarm.setBlng(tpo.getBlng());
			alarm.setLat(tpo.getLat());
			alarm.setLng(tpo.getLng());
			alarm.setSpeed(tpo.getSpeed());
			alarm.setDirection(tpo.getDirection());
			alarm.setAddress(tpo.getAddress());
			alarm.setAlarmtime(DateUtil.numToDate(alarmtime));
			alarm.setAreaid(areaSet.getAreaid());
			
			//添加报警信息往mongodb
			MongoDB.getInstance().addAlarm(alarm);
			
			ActivePush.alarmPush(carInfo,null,alarm);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	}
	
}
