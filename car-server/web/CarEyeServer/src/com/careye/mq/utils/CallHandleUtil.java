/**
 * Description: 出租车系统
 * 文件名：CallUtil.java
 * 版本信息：1.0
 * 日期：2015-3-23
 * Copyright car-eye车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.mq.utils;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.careye.constant.Constant;
import com.careye.constant.ServiceConfig;
import com.careye.mq.MqUtil;
import com.careye.utils.StringUtils;

/**
 * @项目名称：TAXISERVER
 * @类名称：CallUtil
 * @类描述：电召业务util
 * @创建人：zr
 * @创建时间：2015-3-23 上午11:23:56
 * @修改人：zr
 * @修改时间：2015-3-23 上午11:23:56
 * @修改备注：
 * @version 1.0
 */
public class CallHandleUtil {

	private final static Logger logger = Logger.getLogger(CallHandleUtil.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 发送招标
	 * @param terminal 设备号
	 * @param orderid 定单号
	 * @param answermode 抢答方式
	 * @param answerphone 抢答电话号码
	 * @param startlat 开始纬度
	 * @param startlng 开始经度
	 * @param endlat 结束纬度
	 * @param endlng 结束经度
	 * @param contents 调度简短信息
	 */
	public static void sendOrder(String terminal,String orderid,int ordertype,String ordertime,String startaddr,
			String endaddr,Double startlat,Double startlng,Double endlat,Double endlng,String contents){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			
			int seq = ToolsUtil.getSerialId();
			
			buffer.append(StringUtils.jsonChar("msgid",35584, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(orderid),0));
			buffer.append(StringUtils.jsonChar("ordertype",ordertype,0));
			buffer.append(StringUtils.jsonChar("ordertime",StringUtils.charStr(ordertime),0));
			buffer.append(StringUtils.jsonChar("startaddr",StringUtils.charStr(startaddr),0));
			buffer.append(StringUtils.jsonChar("endaddr",StringUtils.charStr(endaddr),0));
			buffer.append(StringUtils.jsonChar("remark",StringUtils.charStr(contents),0));
			buffer.append(StringUtils.jsonChar("startlat",new BigDecimal(startlat * Math.pow(10, 6)),0));
			buffer.append(StringUtils.jsonChar("startlng",new BigDecimal(startlng * Math.pow(10, 6)),0));
			buffer.append(StringUtils.jsonChar("endlat",new BigDecimal(endlat * Math.pow(10, 6)),0));
			buffer.append(StringUtils.jsonChar("endlng",new BigDecimal(endlng * Math.pow(10, 6)),1));
			buffer.append("}");

			logger.info("[发送招标]数据包："+buffer.toString());

			MqUtil.write(Constant.TAXI_DEVICE_TYPE, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.commonService.addCommondLog(carid, 1, 35584, "发送招标", seq, buffer.toString());
			
		} catch (Exception e) {
			logger.error("发送招标数据包编码异常",e);
			e.printStackTrace();
		}
	}


	/**
	 * 订单取消--电召中心处理结果
	 * @param terminal 设备号
	 * @param orderid 订单号
	 */
	public static void centralProcessResults(String terminal,String orderid){
		try {
			StringBuffer buffer = new StringBuffer();
			
			int seq = ToolsUtil.getSerialId();
			
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",35593, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(orderid),1));
			buffer.append("}");

			logger.info("[电召中心处理结果--订单取消]数据包："+buffer.toString());

			MqUtil.write(Constant.TAXI_DEVICE_TYPE, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.commonService.addCommondLog(carid, 1, 35593, "订单取消", seq, buffer.toString());
			
		} catch (Exception e) {
			logger.error("发送电召中心处理结果数据包编码异常",e);
			e.printStackTrace();
		}
	}


	/**
	 * 中标--订单确认
	 * @param terminal 设备号
	 * @param orderid 订单号
	 * @param passengerphone 乘客电话
	 * @param carnum 平台用于填充电召业务流水号
	 * @param contents 描述乘客所在的位置
	 */
	public static void orderSuccessful(String terminal,String orderid,String passengername,String passengerphone,
			String carnum,String contents,int ordertype,String ordertime,String startaddr,String endaddr,String remark,int result){
		try {
			StringBuffer buffer = new StringBuffer();
			
			int seq = ToolsUtil.getSerialId();
			
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",35585, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(orderid),0));
			buffer.append(StringUtils.jsonChar("ordertype",ordertype,0));
			buffer.append(StringUtils.jsonChar("ordertime",StringUtils.charStr(ordertime),0));
			buffer.append(StringUtils.jsonChar("startaddr",StringUtils.charStr(startaddr),0));
			buffer.append(StringUtils.jsonChar("endaddr",StringUtils.charStr(endaddr),0));
			buffer.append(StringUtils.jsonChar("passengerphone",StringUtils.charStr(passengerphone),0));
			buffer.append(StringUtils.jsonChar("remark",StringUtils.charStr(remark),0));
			buffer.append(StringUtils.jsonChar("result",result,1));
			
			buffer.append("}");

			logger.info("[中标]数据包："+buffer.toString());

			MqUtil.write(Constant.TAXI_DEVICE_TYPE, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.commonService.addCommondLog(carid, 1, 35585, "中标", seq, buffer.toString());
			
		} catch (Exception e) {
			logger.error("发送中标数据包编码异常",e);
			e.printStackTrace();
		}
	}

	/**
	 * 执行电召
	 * @param terminal 设备号
	 * @param orderid 定单号
	 * @param callfee 电召费
	 * @param carnum 车牌号
	 * @param result 电召结果
	 */
	public static void orderExecute(String terminal,String orderid,String callfee,String carnum,int result){
		try { 
			StringBuffer buffer = new StringBuffer();
			
			int seq = ToolsUtil.getSerialId();
			
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",20743, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(orderid),0));
			buffer.append(StringUtils.jsonChar("callfee",StringUtils.charStr(callfee),0));
			buffer.append(StringUtils.jsonChar("carnumber",StringUtils.charStr(carnum),0));
			buffer.append(StringUtils.jsonChar("result",result,1));
			buffer.append("}");

			logger.info("[执行电召]数据包："+buffer.toString());

			MqUtil.write(Constant.TAXI_DEVICE_TYPE, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.commonService.addCommondLog(carid, 1, 20743, "执行电召", seq, buffer.toString());
			
		} catch (Exception e) {
			logger.error("发送执行电召数据包编码异常",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行电召确认
	 * @param terminal 设备号
	 * @param result 处理结果 0 失败 1 成功
	 */
	public static void orderExecuteConfirmation(String terminal,int result){
		try { 
			StringBuffer buffer = new StringBuffer();
			
			int seq = ToolsUtil.getSerialId();
			
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",20999, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("result",result,1));
			buffer.append("}");

			logger.info("[执行电召确认]数据包："+buffer.toString());

			MqUtil.write(Constant.TAXI_DEVICE_TYPE, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.commonService.addCommondLog(carid, 1, 20999, "执行电召确认", seq, buffer.toString());
			
		} catch (Exception e) {
			logger.error("发送执行电召确认数据包编码异常",e);
			e.printStackTrace();
		}
	}



}
