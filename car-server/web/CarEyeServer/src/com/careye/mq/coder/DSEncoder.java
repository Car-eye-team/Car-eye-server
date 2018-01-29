/**
 * Description: 出租车系统
 * 文件名：DSEncoder.java
 * 版本信息：1.0
 * 日期：2015-3-18
 * Copyright car-eye车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.mq.coder;

import org.apache.log4j.Logger;

import com.careye.car.domain.Alarm;
import com.careye.car.domain.CarInfo;
import com.careye.car.domain.PositionInfo;
import com.careye.mq.domain.Protocol;
import com.careye.utils.StringUtils;

/**
 * @项目名称：TAXISERVER
 * @类名称：DSEncoder
 * @类描述：json组装
 * @创建人：zr
 * @创建时间：2015-3-18 下午03:53:07
 * @修改人：zr
 * @修改时间：2015-3-18 下午03:53:07
 * @修改备注：
 * @version 1.0
 */
public class DSEncoder {
	private final static Logger logger = Logger.getLogger(DSEncoder.class);
	// 数据包中使用的流水号
	public static int seqNumber = 0;

	/*
	 * 获取顺序号
	 */
	public static int getSeqNumber() {
		++ seqNumber;
		if(seqNumber >= 65000)
			seqNumber = 0;

		return seqNumber;
	}

	/**
	 * 编码车辆状态推送
	 * @param carnumber 车牌号
	 * @param carid 车辆ID
	 * @param carstatus 车辆状态
	 * @param carstatus 车辆运输状态
	 * @return
	 */
	public static String EncoderVehicleStatePush(CarInfo carInfo){

		String out = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",28, 0));
			buffer.append(StringUtils.jsonChar("seq",getSeqNumber(),0));
			buffer.append(StringUtils.jsonChar("carnumber",StringUtils.charStr(carInfo.getCarnumber()),0));
			buffer.append(StringUtils.jsonChar("carid",carInfo.getId(),0));
			buffer.append(StringUtils.jsonChar("blocid",carInfo.getBlocid(),0));
			buffer.append(StringUtils.jsonChar("userid",0,0));
			buffer.append(StringUtils.jsonChar("username",StringUtils.charStr(""),0));
			buffer.append(StringUtils.jsonChar("carstatus",carInfo.getCarstatus(),0));
			buffer.append(StringUtils.jsonChar("travelposition",0,0));
			buffer.append(StringUtils.jsonChar("text",StringUtils.charStr(""),1));
			buffer.append("}");

			logger.info("[编码车辆状态推送]数据包："+buffer.toString());

			out = buffer.toString();

			return out;
		} catch (Exception e) {
			logger.error("格式化编码车辆状态推送编码异常",e);
			return out;
		}
	}

	/**
	 * 编码实时位置信息
	 * @param carnumber 车牌号
	 * @param carid 车辆ID
	 * @param carstatus 车辆状态
	 * @param speed 速度
	 * @param direction 方向
	 * @param lng 经纬度
	 * @param lat
	 * @param address 地址
	 * @return
	 */
	public static String EncoderRealTimePosition(PositionInfo positionInfo,CarInfo carInfo){

		String out = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",29, 0));
			buffer.append(StringUtils.jsonChar("seq",getSeqNumber(),0));
			buffer.append(StringUtils.jsonChar("carnumber",StringUtils.charStr(carInfo.getCarnumber()),0));
			buffer.append(StringUtils.jsonChar("carid",carInfo.getId(),0));
			buffer.append(StringUtils.jsonChar("blocid",carInfo.getBlocid(),0));
			buffer.append(StringUtils.jsonChar("carstatus",carInfo.getCarstatus(),0));
			buffer.append(StringUtils.jsonChar("kzstate",carInfo.getKzstate(),0));
			buffer.append(StringUtils.jsonChar("acc",positionInfo.getAcc(),0));   //acc状态 0 关 1 开
			buffer.append(StringUtils.jsonChar("speed",StringUtils.charStr(positionInfo.getSpeed()),0));
			buffer.append(StringUtils.jsonChar("direction",StringUtils.charStr(positionInfo.getDirection()),0));
			buffer.append(StringUtils.jsonChar("lng",StringUtils.charStr(String.valueOf(positionInfo.getBlng())),0));
			buffer.append(StringUtils.jsonChar("lat",StringUtils.charStr(String.valueOf(positionInfo.getBlat())),0));
			buffer.append(StringUtils.jsonChar("glng",StringUtils.charStr(String.valueOf(positionInfo.getBlng())),0));
			buffer.append(StringUtils.jsonChar("glat",StringUtils.charStr(String.valueOf(positionInfo.getBlat())),0));
			buffer.append(StringUtils.jsonChar("mileage",StringUtils.charStr(String.valueOf(positionInfo.getMileage())),0));
			buffer.append(StringUtils.jsonChar("address",StringUtils.charStr(positionInfo.getAddress()),0));
			buffer.append(StringUtils.jsonChar("datetime",StringUtils.charStr(positionInfo.getGpstime()),1));
			buffer.append("}");

			logger.info("[编码实时位置信息]数据包："+buffer.toString());

			out = buffer.toString();

			return out;
		} catch (Exception e) {
			logger.error("格式化编码实时位置信息异常",e);
			return out;
		}
	}

	/**
	 * 编码报警信息
	 * @param carInfo
	 * @param alarm
	 * @return
	 */
	public static String EncoderAlarm(CarInfo carInfo,Alarm alarm){

		String out = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",30, 0));
			buffer.append(StringUtils.jsonChar("seq",getSeqNumber(),0));
			buffer.append(StringUtils.jsonChar("carnumber",StringUtils.charStr(carInfo.getCarnumber()),0));
			buffer.append(StringUtils.jsonChar("carid",carInfo.getId(),0));
			buffer.append(StringUtils.jsonChar("blocid",carInfo.getBlocid(),0));
			buffer.append(StringUtils.jsonChar("carstatus",carInfo.getCarstatus(),0));
			buffer.append(StringUtils.jsonChar("alarmname",StringUtils.charStr(alarm.getAlarmname()),0));
			buffer.append(StringUtils.jsonChar("speed",StringUtils.charStr(alarm.getSpeed()),0));
			buffer.append(StringUtils.jsonChar("direction",StringUtils.charStr(alarm.getDirection()),0));
			buffer.append(StringUtils.jsonChar("lng",StringUtils.charStr(String.valueOf(alarm.getBlng())),0));
			buffer.append(StringUtils.jsonChar("lat",StringUtils.charStr(String.valueOf(alarm.getLat())),0));
			buffer.append(StringUtils.jsonChar("alarmtime",StringUtils.charStr(alarm.getAlarmtime()),0));
			buffer.append(StringUtils.jsonChar("alarmkey",StringUtils.charStr(alarm.getAlarmkey()),0));
			buffer.append(StringUtils.jsonChar("musicaddr",StringUtils.charStr(alarm.getMusicaddr()),0));
			buffer.append(StringUtils.jsonChar("address",StringUtils.charStr(alarm.getAddress()),0));
			buffer.append(StringUtils.jsonChar("deptname",StringUtils.charStr(carInfo.getBlocname()),0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(carInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("drivername",StringUtils.charStr(""),0));
			buffer.append(StringUtils.jsonChar("phone",StringUtils.charStr(carInfo.getPhone()),1));
			buffer.append("}");

			logger.info("[编码报警信息]数据包："+buffer.toString());

			out = buffer.toString();

			return out;
		} catch (Exception e) {
			logger.error("格式化编码报警信息信息异常",e);
			return out;
		}
	}

	/**
	 * 位置应答信息
	 * @param positionInfo
	 * @param carInfo
	 * @param protocol
	 * @return
	 */
	public static String EncoderPositionAnswerPush(PositionInfo positionInfo,CarInfo carInfo,Protocol protocol){

		String out = null;
		try {
			int type = 0;
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",31, 0));
			buffer.append(StringUtils.jsonChar("seq",getSeqNumber(),0));
			buffer.append(StringUtils.jsonChar("seqR",protocol.getSeqR(),0));
			buffer.append(StringUtils.jsonChar("carnumber",StringUtils.charStr(carInfo.getCarnumber()),0));
			buffer.append(StringUtils.jsonChar("type",type,0));
			buffer.append(StringUtils.jsonChar("carid",carInfo.getId(),0));
			buffer.append(StringUtils.jsonChar("blocid",carInfo.getBlocid(),0));
			buffer.append(StringUtils.jsonChar("carstatus",carInfo.getCarstatus(),0));
			buffer.append(StringUtils.jsonChar("speed",StringUtils.charStr(positionInfo.getSpeed()),0));
			buffer.append(StringUtils.jsonChar("direction",StringUtils.charStr(positionInfo.getDirection()),0));
			buffer.append(StringUtils.jsonChar("lng",StringUtils.charStr(String.valueOf(positionInfo.getBlng())),0));
			buffer.append(StringUtils.jsonChar("lat",StringUtils.charStr(String.valueOf(positionInfo.getBlat())),0));
			buffer.append(StringUtils.jsonChar("address",StringUtils.charStr(positionInfo.getGaddress()),0));
			buffer.append(StringUtils.jsonChar("deptname",StringUtils.charStr(carInfo.getBlocname()),0));
			buffer.append(StringUtils.jsonChar("datetime",StringUtils.charStr(positionInfo.getCreatetime()),1));
			buffer.append("}");
			logger.info("[位置应答信息]数据包："+buffer.toString());

			out = buffer.toString();

			return out;
		} catch (Exception e) {
			logger.error("格式化位置应答信息异常",e);
			return out;
		}
	}
	
	/**
	 * 视频回放列表信息
	 * @param carInfo
	 * @param protocol
	 * @return
	 */
	public static String EncoderPlaybackListPush(CarInfo carInfo,Protocol protocol,String id,String items){

		String out = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",36, 0));
			buffer.append(StringUtils.jsonChar("seq",getSeqNumber(),0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(carInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("carid",carInfo.getId(),0));
			buffer.append(StringUtils.jsonChar("id",id,0));
			buffer.append(StringUtils.jsonChar("count",protocol.getCount(),0));
			buffer.append(StringUtils.jsonChar("items",StringUtils.charStr(items),1));
			buffer.append("}");
			logger.info("[视频回放列表]数据包："+buffer.toString());

			out = buffer.toString();

			return out;
		} catch (Exception e) {
			logger.error("格式化视频回放列表异常",e);
			return out;
		}
	}
	
	/**
	 * 视频回放结束信息
	 * @param carInfo
	 * @param protocol
	 * @return
	 */
	public static String EncoderPlaybackFinishPush(CarInfo carInfo,Protocol protocol,String id){

		String out = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",37, 0));
			buffer.append(StringUtils.jsonChar("seq",getSeqNumber(),0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(carInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("carid",carInfo.getId(),0));
			buffer.append(StringUtils.jsonChar("id",id,1));
			buffer.append("}");
			logger.info("[视频回放结束]数据包："+buffer.toString());

			out = buffer.toString();

			return out;
		} catch (Exception e) {
			logger.error("格式化视频回放结束异常",e);
			return out;
		}
	}
	
}
