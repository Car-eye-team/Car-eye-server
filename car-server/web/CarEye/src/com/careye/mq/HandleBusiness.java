/**
 * Description: 多森商用车平台
 * 文件名：HandleBusiness.java
 * 版本信息：1.0
 * 日期：2013-8-7
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.mq;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.careye.car.domain.CarInfo;
import com.careye.common.domain.BaiDuInfo;
import com.careye.common.domain.TerminalParameter;
import com.careye.mq.domain.MultimediaData;


/**
 * @项目名称：car-eye
 * @类名称：HandleBusiness
 * @类描述：终端业务处理
 * @创建人：zr
 * @创建时间：2013-8-7 上午11:55:49
 * @修改人：zr
 * @修改时间：2013-8-7 上午11:55:49
 * @修改备注：
 * @version 1.0
 */
public class HandleBusiness {
	private final static Logger logger = Logger.getLogger(HandleBusiness.class);

	/** 终端设置缓存 */
	public static Map<Integer, TerminalParameter> terminalMap = new HashMap<Integer, TerminalParameter>();

	/**多媒体数据缓存**/
	public static Map<String, MultimediaData> mediadataMap = new HashMap<String, MultimediaData>();

	public static void main(String[] args) {}

	/**
	 * 操作位置信息表
	 * @param baiDuInfo
	 * @param protocol
	 */
	public static void operPositionInfor(BaiDuInfo baiDuInfo,Protocol protocol,CarInfo carInfo,String message){} 

	/**
	 * 终端注册
	 * @param protocol
	 * @param message
	 */
	public static void regeditTerminal(Protocol protocol,String message){}

	/**
	 * 终端心跳包处理
	 * @param protocol
	 */
	public static void heartbeat(Protocol protocol,String message){}

	/**
	 * 报警
	 * @param protocol
	 */
	public static void alarm(Protocol protocol,BaiDuInfo baiDuInfo,CarInfo carInfo,String message){}

	/**
	 * 车辆运输状态切换
	 * @param protocol
	 */
	public static void carTravelPosition(Protocol protocol){}

	/**
	 * 查询位置信息应答
	 * @param protocol
	 */
	public static void queryPositionAnswer(Protocol protocol,String message){}


	/**
	 * 处理天气预报请求
	 * @param protocol
	 * @param message
	 */
	public static void weatherRequest(Protocol protocol,String message){}

	/**
	 * 采集驾驶员身份信息数据
	 * @param protocol
	 * @param message
	 */
	public static void driverInfo(Protocol protocol,String message){}

	/**
	 * 采集电子运单数据
	 * @param protocol
	 * @param message
	 */
	public static void electronicBillLading(Protocol protocol,String message){}

	/**
	 * 事件报告
	 * @param protocol
	 */
	public static void eventReport(Protocol protocol,String message){}

	/**
	 * 提问应答
	 * @param protocol
	 */
	public static void questionAnswer(Protocol protocol){}

	/**
	 * 处理信息点播/取消
	 * @param protocol
	 */
	public static void infoOnDemand(Protocol protocol){}

	/**
	 * 多媒体事件信息上传处理
	 * @param protocol
	 * @param message
	 */
	public static void multimediaEventInfo(Protocol protocol,String message){} 

	/**
	 * 多媒体数据上传
	 * @param protocol
	 * @param message
	 */
	public static void multimediaDataUpload(Protocol protocol,String message){}

	/**
	 * 检查数据包
	 * @param protocol
	 * @param key
	 */
	public static void checkMultimediaData(CarInfo carInfo,Protocol protocol,String key,boolean flag){}
	
	/**
	 * 终端控制应答
	 * @param protocol
	 * @param message
	 */
	public static void vehicleControlAnswer(Protocol protocol,String message){}
	

}
