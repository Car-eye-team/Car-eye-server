/**
 * Description: 多森商用车平台
 * 文件名：JSONEncoder.java
 * 版本信息：1.0
 * 日期：2013-7-22
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.mq;


import java.math.BigDecimal;
import java.text.MessageFormat;

import org.apache.log4j.Logger;

import com.careye.constant.Constant;
import com.careye.utils.DateUtil;
import com.careye.utils.StringUtils;


/**
 * @项目名称：byd
 * @类名称：JSONEncoder
 * @类描述：处理下发
 * @创建人：zr
 * @创建时间：2013-7-22 下午03:00:26
 * @修改人：zr
 * @修改时间：2013-7-22 下午03:00:26
 * @修改备注：
 * @version 1.0
 */
public class HandleUtil {

	private final static Logger logger = Logger.getLogger(HandleUtil.class);

	public static int seqn = 0;

	//获得发送序列号
	public static int getSerialId(){

		if(seqn > 65000){
			seqn = 0;
		}
		seqn++;
		return seqn;
	}

	
	/**
	 * 发送短信
	 * @param terminal 设备号
	 * @param result 处理结果 0 失败 1 成功
	 */
	public static boolean sendSms(String terminal,int seq,String phone,String content,int source){
		try { 
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",0, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("phone",StringUtils.charStr(phone),0));
			buffer.append(StringUtils.jsonChar("content",StringUtils.charStr(content),0));
			buffer.append(StringUtils.jsonChar("source",source,1));
			buffer.append("}");
			
			logger.info("[发送短信]数据包："+buffer.toString());
			MqUtil.writeSms(27, buffer.toString());
			
			return true;
			
		} catch (Exception e) {	
			logger.error("发送发送短信数据包编码异常",e);
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 招标---订单任务下发
	 * @param terminal 设备号
	 * @param status 1 打开 2 关闭
	 * @return
	 */
	public static String sendDial(String terminal,String orderid,int ordertype,String ordertime,String contents,int seq,String startaddr,String endaddr,
			Double startlat,Double startlng,Double endlat,Double endlng){
		
		try {
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("{");
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
			
			logger.info("[招标]数据包："+buffer.toString());
			
			MqUtil.write(Constant.DEVICE_TYPE, buffer.toString());
			
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送招标数据包编码异常",e);
			return null;
		}
	}
	
	/**
	 * 招标---语音订单任务下发
	 * @param terminal 设备号
	 * @param status 1 打开 2 关闭
	 * @return
	 */
	public static String sendVoiceDial(String terminal,String orderid,int ordertype,String ordertime,String contents,int seq,String startaddr,String endaddr,
			Double startlat,Double startlng,Double endlat,Double endlng){
		
		try {
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("{");
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
			
			logger.info("[招标]数据包："+buffer.toString());
			
			MqUtil.write(Constant.DEVICE_TYPE, buffer.toString());
			
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送招标数据包编码异常",e);
			return null;
		}
	}
	
	/**
	 * 取消电召
	 * @param terminal 设备号
	 * @param result 处理结果 0 失败 1 成功
	 */
	public static void cancelCarOrder(String terminal,int seq,String orderid){
		try { 
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",35593, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(orderid),1));
			buffer.append("}");
			
			logger.info("[取消电召]数据包："+buffer.toString());
			MqUtil.write(Constant.DEVICE_TYPE, buffer.toString());
		} catch (Exception e) {
			logger.error("发送取消电召数据包编码异常",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 对讲申请通知
	 * @param terminal 设备号
	 * @param result 处理结果 0 失败 1 成功
	 */
	public static void invateNotice(String terminal,int seq,int groupid,int carid,String groupname,String carnumber){
		try { 
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",0x510F, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("groupid",groupid,0));
			buffer.append(StringUtils.jsonChar("carid",carid,0));
			buffer.append(StringUtils.jsonChar("groupname",StringUtils.charStr(groupname),0));
			buffer.append(StringUtils.jsonChar("carnumber",StringUtils.charStr(carnumber),1));
			buffer.append("}");
			
			logger.info("[对讲申请通知]数据包："+buffer.toString());
			MqUtil.write(Constant.DEVICE_TYPE, buffer.toString());
		} catch (Exception e) {
			logger.error("发送对讲申请通知数据包编码异常",e);
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		String random = DateUtil.getFourRandom();
		String content = MessageFormat.format(Constant.SEND_AUTHCODE_CONTENT, random);
		HandleUtil.sendSms("15814403512", HandleUtil.getSerialId(), "15814403512", content, 1);
	}

}
