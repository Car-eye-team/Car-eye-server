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
import java.util.List;

import org.apache.log4j.Logger;

import com.careye.common.domain.TerminalParameter;
import com.careye.constant.Constant;
import com.careye.constant.ServiceConfig;
import com.careye.mq.MqUtil;
import com.careye.mq.domain.Cameras;
import com.careye.mq.domain.Event;
import com.careye.mq.domain.Line;
import com.careye.mq.domain.Menu;
import com.careye.mq.domain.Question;
import com.careye.mq.domain.TelephoneBook;
import com.careye.mq.domain.ZoneAlarm;
import com.careye.sysset.domain.DialRule;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

/**
 * @项目名称：car-eye
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
	 * 通用应答
	 * @param terminal 设备号
	 * @param respmsgid 应答ID
	 * @param respseq 应答流水号
	 * @param result 结果
	 * @return
	 */
	public static boolean universalResponse(Integer devicetype,String terminal,int respmsgid,int respseq,int result,String carnumber){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",32769, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",getSerialId(),0));
			buffer.append(StringUtils.jsonChar("respseq",respseq,0));
			buffer.append(StringUtils.jsonChar("respmsgid",respmsgid,0));
			buffer.append(StringUtils.jsonChar("result",result,1));
			buffer.append("}");

			logger.info("[通用应答]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			return true;
		} catch (Exception e) {
			logger.error("发送通用应答数据包编码异常",e);
			return false;
		}
	}

	/**
	 * 终端注册应答
	 * @param terminal 设备号
	 * @param seqR 应答流水
	 * @param result 0：成功；1：车辆已被注册；2：数据库中无该车辆；3：终端已被注册；4：数据库中无该终端
	 * @param passwd
	 * @return
	 */
	public static boolean regeditTerminalResponse(Integer devicetype,String terminal,int seqR,int result,String passwd,String carnumber){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",33024, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",getSerialId(),0));
			buffer.append(StringUtils.jsonChar("seqR",seqR,0));
			if(result == 0){
				buffer.append(StringUtils.jsonChar("result",result,0));
				buffer.append(StringUtils.jsonChar("passwd",StringUtils.charStr(passwd),1));
			}else{
				buffer.append(StringUtils.jsonChar("result",result,1));
			}

			buffer.append("}");

			logger.info("[终端注册应答]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			return true;
		} catch (Exception e) {
			logger.error("发送终端注册应答数据包编码异常",e);
			return false;
		}
	}


	/**
	 * 状态切换应答
	 * @param protocol
	 * @return
	 */
	public static boolean carTravelPositionAnswer(Integer devicetype,Protocol protocol,String carnumber){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",36610, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(protocol.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq",getSerialId(),0));
			buffer.append(StringUtils.jsonChar("seqR",protocol.getSeq(),0));
			buffer.append(StringUtils.jsonChar("status",protocol.getStatus(),0));
			buffer.append(StringUtils.jsonChar("result",0,1));
			buffer.append("}");

			logger.info("[状态切换应答]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			return true;
		} catch (Exception e) {
			logger.error("发送状态切换应答数据包编码异常",e);
			return false;
		}
	}

	/**
	 * 查询终端参数
	 * @param protocol
	 * @return
	 */
	public static boolean queryTerminalParm(Integer devicetype,String terminal,Integer carid){
		
		try {
			int seq = getSerialId();
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",33028, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,1));
			buffer.append("}");
			
			logger.info("[查询终端参数]数据包："+buffer.toString());
			
			MqUtil.write(devicetype, buffer.toString());
			
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33028, "查询终端参数", seq, buffer.toString());
			
			return true;
		} catch (Exception e) {
			logger.error("发送查询终端参数指令编码异常",e);
			return false;
		}
	}
	

	

	/**
	 * 终端参数设置
	 * @return
	 */
	public static boolean setTerminalParam(Integer devicetype,TerminalParameter terminalInfo,int seq,String carnumber){
		try {
			StringBuffer basicBuffer = new StringBuffer();
			StringBuffer itemBuffer = new StringBuffer();
			StringBuffer resultBuffer = new StringBuffer();
			basicBuffer.append("{");
			basicBuffer.append(StringUtils.jsonChar("msgid",33027, 0));
			basicBuffer.append(StringUtils.jsonChar("subpacket","0", 0));
			basicBuffer.append(StringUtils.jsonChar("encryption","0", 0));
			basicBuffer.append(StringUtils.jsonChar("bodysize","0", 0));
			basicBuffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminalInfo.getSimCode()),0));
			basicBuffer.append(StringUtils.jsonChar("seq",seq,0));

			int count = 0;
			int totalTerminalCount = terminalInfo.getTerminal().length;
			for (int i = 0; i < totalTerminalCount; i++) {

				count++;
				int val = terminalInfo.getTerminal()[i];
				String temp = ToolsUtil.getTerminalValue(val, terminalInfo);

				if (count == Constant.DATA_PACKET_NUM) {
					resultBuffer.append(basicBuffer);
					resultBuffer.append(StringUtils.jsonChar("count",8, 0));
					resultBuffer.append(StringUtils.jsonChar("items","[", 1));

					if (ToolsUtil.isNumeric(temp) && temp.indexOf("#") == -1) {
						itemBuffer.append("{");
						itemBuffer.append(StringUtils.jsonChar("id",val, 0));
						itemBuffer.append(StringUtils.jsonChar("size",temp.getBytes().length, 0));
						itemBuffer.append(StringUtils.jsonChar("value",temp, 1));
						itemBuffer.append("}");
					} else {
						if (temp.indexOf("#") > -1) {
							temp = temp.substring(0, temp.indexOf("#"));
						}

						itemBuffer.append("{");
						itemBuffer.append(StringUtils.jsonChar("id",val, 0));
						itemBuffer.append(StringUtils.jsonChar("size",temp.getBytes().length, 0));
						itemBuffer.append(StringUtils.jsonChar("value",StringUtils.charStr(temp), 1));
						itemBuffer.append("}");
					}

					itemBuffer.append("]}");
					resultBuffer.append(itemBuffer);

					logger.info("[终端参数设置]数据包："+resultBuffer.toString());

					MqUtil.write(devicetype, resultBuffer.toString());
					
					//添加指令下发记录
					Integer carid = ServiceConfig.carService.getCaridByTerminal(terminalInfo.getSimCode());
					ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33027, "终端参数设置", seq, resultBuffer.toString());

					itemBuffer.delete(0, itemBuffer.length());
					resultBuffer.delete(0, resultBuffer.length());

					count = 0;

				} else {
					if (i + 1 == totalTerminalCount) {

						resultBuffer.append(basicBuffer);
						resultBuffer.append(StringUtils.jsonChar("count",count, 0));
						resultBuffer.append(StringUtils.jsonChar("items","[", 1));
						if (ToolsUtil.isNumeric(temp) && temp.indexOf("#") == -1) {
							itemBuffer.append("{");
							itemBuffer.append(StringUtils.jsonChar("id",val, 0));
							itemBuffer.append(StringUtils.jsonChar("size",temp.getBytes().length, 0));
							itemBuffer.append(StringUtils.jsonChar("value",temp, 1));
							itemBuffer.append("}");
						} else {
							if (temp.indexOf("#") > -1) {
								temp = temp.substring(0, temp.indexOf("#"));
							}
							itemBuffer.append("{");
							itemBuffer.append(StringUtils.jsonChar("id",val, 0));
							itemBuffer.append(StringUtils.jsonChar("size",temp.getBytes().length, 0));
							itemBuffer.append(StringUtils.jsonChar("value",StringUtils.charStr(temp), 1));
							itemBuffer.append("}");
						}
						itemBuffer.append("]}");
						resultBuffer.append(itemBuffer);

						logger.info("[终端参数设置]数据包："+resultBuffer.toString());

						MqUtil.write(devicetype, resultBuffer.toString());
						
						//添加指令下发记录
						Integer carid = ServiceConfig.carService.getCaridByTerminal(terminalInfo.getSimCode());
						ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33027, "终端参数设置", seq, resultBuffer.toString());

						itemBuffer.delete(0, itemBuffer.length());

					} else {

						if (ToolsUtil.isNumeric(temp) && temp.indexOf("#") == -1) {
							itemBuffer.append("{");
							itemBuffer.append(StringUtils.jsonChar("id",val, 0));
							itemBuffer.append(StringUtils.jsonChar("size",temp.getBytes().length, 0));
							itemBuffer.append(StringUtils.jsonChar("value",temp, 1));
							itemBuffer.append("},");
						} else {
							if (temp.indexOf("#") > -1) {
								temp = temp.substring(0, temp.indexOf("#"));
							}
							itemBuffer.append("{");
							itemBuffer.append(StringUtils.jsonChar("id",val, 0));
							itemBuffer.append(StringUtils.jsonChar("size",temp.getBytes().length, 0));
							itemBuffer.append(StringUtils.jsonChar("value",StringUtils.charStr(temp), 1));
							itemBuffer.append("},");
						}

					}
				}
			}

			//加入至发送缓存中
			ToolsUtil.sendMap.put("33027_"+seq, seq);

			return true;
		} catch (Exception e) {
			logger.error("发送终端参数设置编码异常",e);
			return false;
		}
	}

	/**
	 * 远程拍照
	 * @param channel 通道ID
	 * @param cmd 拍摄命令
	 * @param inv 拍照间隔/录像时间
	 * @param save 保持标志
	 * @param screen 分辨率
	 * @param quality 图像/视频质量
	 * @param bright 亮度
	 * @param contrast 对比度
	 * @param saturation 饱和度
	 * @param chroma 色度
	 * @return
	 */
	public static String remoteCamera(Integer devicetype,String terminal,int channel,int cmd,int inv,int save,int screen,int quality,int bright,int contrast,int saturation,int chroma,int seq,String carnumber){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34817, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("channel",channel,0));
			buffer.append(StringUtils.jsonChar("cmd",cmd,0));
			buffer.append(StringUtils.jsonChar("inv",inv,0));
			buffer.append(StringUtils.jsonChar("save",save,0));
			buffer.append(StringUtils.jsonChar("screen",screen,0));
			buffer.append(StringUtils.jsonChar("quality",quality,0));
			buffer.append(StringUtils.jsonChar("bright",bright,0));
			buffer.append(StringUtils.jsonChar("contrast",contrast,0));
			buffer.append(StringUtils.jsonChar("saturation",saturation,0));
			buffer.append(StringUtils.jsonChar("chroma",chroma,1));
			buffer.append("}");
			logger.info("[远程拍照]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34817, "远程拍照", seq, buffer.toString());

			//加入至发送缓存中
			ToolsUtil.sendMap.put("34817_"+seq, seq);

			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送远程拍照数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 终端控制
	 * @param terminal 设备号
	 * @param id 命令字
	 * @param size 命令参数格式具体见后血描述，每个字段之间采用半角”;”分隔，每个STRING字段先按GBK编码处理后再组成消息
	 * @return
	 */
	public static String terminalControl(Integer devicetype,String terminal,int id,String size,int seq,String carnumber){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",33029, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("cmd",id,0));
			buffer.append(StringUtils.jsonChar("params",StringUtils.charStr(size),1));
			buffer.append("}");
			logger.info("[终端控制]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());

			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33029, "终端控制", seq, buffer.toString());

			//加入至发送缓存中
			ToolsUtil.sendMap.put("33029_"+seq, seq);
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送终端控制数据包编码异常",e);
			return "";
		}
	}

	/**
	 * 车辆控制
	 * @param terminal 设备号
	 * @param state 1 加锁 0 解锁
	 * @return
	 */
	public static String vehicleControl(Integer devicetype,String terminal,String state,int seq,String carnumber){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34048, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("ctrl",StringUtils.charStr(state),1));
			buffer.append("}");
			logger.info("[车辆控制]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());

			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34048, "车辆控制", seq, buffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("34048_"+seq, seq);

			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送车辆控制数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 设置圆形区域
	 * @param terminal 终端设备号
	 * @param list 设置区域列表
	 * @param update 0：更新区域；1：追加区域；2：修改区域；
	 * @return
	 */
	public static String circularArea(Integer devicetype,String terminal,List<ZoneAlarm> list,int update,int seq,String carnumber){
		try {
			String result = "";
			StringBuffer buffer = new StringBuffer();
			StringBuffer itemBuffer = new StringBuffer();
			StringBuffer resultBuffer = new StringBuffer();

			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34304, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("update",update, 0));
			int count = 0;
			int size = list.size();
			boolean flag = false;
			for (int i = 0; i < list.size(); i++) {
				ZoneAlarm zoneAlarm = list.get(i);
				count++;

				itemBuffer.append("{");
				itemBuffer.append(StringUtils.jsonChar("areaId",zoneAlarm.getAreaId(), 0));
				itemBuffer.append(StringUtils.jsonChar("attr",StringUtils.charStr(zoneAlarm.getAttr()), 0));
				itemBuffer.append(StringUtils.jsonChar("lat",new BigDecimal(zoneAlarm.getLat() * Math.pow(10, 6)), 0));
				itemBuffer.append(StringUtils.jsonChar("lng",new BigDecimal(zoneAlarm.getLng() * Math.pow(10, 6)), 0));

				int attr2 = Integer.parseInt(zoneAlarm.getAttr().substring(2, 3));
				int attr3 = Integer.parseInt(zoneAlarm.getAttr().substring(3, 4));
				int attr4 = Integer.parseInt(zoneAlarm.getAttr().substring(4, 5));
				int attr5 = Integer.parseInt(zoneAlarm.getAttr().substring(5, 6));
				int attr8 = Integer.parseInt(zoneAlarm.getAttr().substring(8, 9));
				int attr9 = Integer.parseInt(zoneAlarm.getAttr().substring(9, 10));
				if( attr2 == 1 || attr3 == 1 || attr4 == 1 || attr5 == 1 || attr8 == 1 || attr9 == 1){
					itemBuffer.append(StringUtils.jsonChar("ddalertinfo",StringUtils.charStr(zoneAlarm.getDdalertinfo()==null?"":zoneAlarm.getDdalertinfo()), 0));
					itemBuffer.append(StringUtils.jsonChar("driveralertinfo",StringUtils.charStr(zoneAlarm.getDriveralertinfo()==null?"":zoneAlarm.getDriveralertinfo()), 0));
				}
				
				int time = Integer.parseInt(zoneAlarm.getAttr().substring(0, 1));
				int speed = Integer.parseInt(zoneAlarm.getAttr().substring(1, 2));
				


				if( time == 1 && speed == 1){
					itemBuffer.append(StringUtils.jsonChar("radius",zoneAlarm.getRadius(), 0));
					itemBuffer.append(StringUtils.jsonChar("timeS",StringUtils.charStr(zoneAlarm.getTimeS()), 0));
					itemBuffer.append(StringUtils.jsonChar("timeE",StringUtils.charStr(zoneAlarm.getTimeE()), 0));
					itemBuffer.append(StringUtils.jsonChar("speedLimit",zoneAlarm.getSpeedLimit(), 0));
					itemBuffer.append(StringUtils.jsonChar("speedTime",zoneAlarm.getSpeedTime(), 1));
				}
				
				if( time == 1 && speed == 0){
					itemBuffer.append(StringUtils.jsonChar("radius",zoneAlarm.getRadius(), 0));
					itemBuffer.append(StringUtils.jsonChar("timeS",StringUtils.charStr(zoneAlarm.getTimeS()), 0));
					itemBuffer.append(StringUtils.jsonChar("timeE",StringUtils.charStr(zoneAlarm.getTimeE()), 1));
				}

				if( time == 0 && speed == 1){
					itemBuffer.append(StringUtils.jsonChar("radius",zoneAlarm.getRadius(), 0));
					itemBuffer.append(StringUtils.jsonChar("speedLimit",zoneAlarm.getSpeedLimit(), 0));
					itemBuffer.append(StringUtils.jsonChar("speedTime",zoneAlarm.getSpeedTime(), 1));
				}

				if( time == 0 && speed == 0){
					itemBuffer.append(StringUtils.jsonChar("radius",zoneAlarm.getRadius(), 1));
				}

				itemBuffer.append("}");

				if(count == Constant.DATA_PACKET_NUM){
					flag = true;
				}else{
					if(i+1 == size){						
						flag = true;
					}else{
						itemBuffer.append(",");
					}
				}

				if(flag){
					resultBuffer.append(buffer);
					resultBuffer.append(StringUtils.jsonChar("count",count, 0));
					resultBuffer.append(StringUtils.jsonChar("items","[", 1));
					itemBuffer.append("]}");
					resultBuffer.append(itemBuffer);

					logger.info("[设置圆形区域]数据包："+resultBuffer.toString());
					result = resultBuffer.toString();

					MqUtil.write(devicetype, resultBuffer.toString());
					
					//添加指令下发记录
					Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
					ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34304, "设置圆形区域", seq, resultBuffer.toString());

					itemBuffer = new StringBuffer();
					resultBuffer = new StringBuffer();
					flag = false;
					count = 0;
				}
			}
			//加入至发送缓存中
			ToolsUtil.sendMap.put("34304_"+seq, seq);

			return result;
		} catch (Exception e) {
			logger.error("发送设置圆形区域数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 删除圆形区域
	 * @param terminal
	 * @param list
	 * @param update
	 * @return
	 */
	public static String deleteCircularArea(Integer devicetype,String terminal,List<ZoneAlarm> list,int seq,String carnumber){
		try {
			String result = "";
			StringBuffer buffer = new StringBuffer();
			StringBuffer itemBuffer = new StringBuffer();
			StringBuffer resultBuffer = new StringBuffer();

			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34305, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			if(list!=null){
				int count = 0;
				int size = list.size();
				boolean flag = false;
				for (int i = 0; i < list.size(); i++) {
					ZoneAlarm zoneAlarm = list.get(i);
					count++;

					itemBuffer.append("{");
					itemBuffer.append(StringUtils.jsonChar("areaId",zoneAlarm.getAreaId(), 1));
					itemBuffer.append("}");

					if(count == 125){
						flag = true;
					}else{
						if(i+1 == size){						
							flag = true;
						}else{
							itemBuffer.append(",");
						}
					}

					if(flag){
						resultBuffer.append(buffer);
						resultBuffer.append(StringUtils.jsonChar("count",count, 0));
						resultBuffer.append(StringUtils.jsonChar("items","[", 1));
						itemBuffer.append("]}");
						resultBuffer.append(itemBuffer);

						logger.info("[删除圆形区域]数据包："+resultBuffer.toString());
						result = resultBuffer.toString();

						MqUtil.write(devicetype, resultBuffer.toString());
						
						//添加指令下发记录
						Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
						ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34305, "删除圆形区域", seq, resultBuffer.toString());

						itemBuffer = new StringBuffer();
						resultBuffer = new StringBuffer();
						flag = false;
						count = 0;
					}
				}
			}else{
				buffer.append(StringUtils.jsonChar("count",0, 1));
				buffer.append("}");
				logger.info("[删除圆形区域]数据包："+buffer.toString());
				result = buffer.toString();

				MqUtil.write(devicetype, buffer.toString());
				
				//添加指令下发记录
				Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
				ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34305, "删除圆形区域", seq, buffer.toString());
			}

			//加入至发送缓存中
			ToolsUtil.sendMap.put("34305_"+seq, seq);

			return result;
		} catch (Exception e) {
			logger.error("发送删除圆形区域数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 设置矩形区域
	 * @param terminal 终端设备号
	 * @param list 设置区域列表
	 * @param update 0：更新区域；1：追加区域；2：修改区域；
	 * @return
	 */
	public static String rectangleArea(Integer devicetype,String terminal,List<ZoneAlarm> list,int update,int seq,String carnumber){
		try {
			String result = "";
			StringBuffer buffer = new StringBuffer();
			StringBuffer itemBuffer = new StringBuffer();
			StringBuffer resultBuffer = new StringBuffer();

			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34306, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("update",update, 0));
			int count = 0;
			int size = list.size();
			boolean flag = false;
			for (int i = 0; i < list.size(); i++) {
				ZoneAlarm zoneAlarm = list.get(i);
				count++;

				itemBuffer.append("{");
				itemBuffer.append(StringUtils.jsonChar("areaId",zoneAlarm.getAreaId(), 0));
				itemBuffer.append(StringUtils.jsonChar("attr",StringUtils.charStr(zoneAlarm.getAttr()), 0));
				itemBuffer.append(StringUtils.jsonChar("latlt",new BigDecimal(zoneAlarm.getLatlt() * Math.pow(10, 6)), 0));
				itemBuffer.append(StringUtils.jsonChar("lnglt",new BigDecimal(zoneAlarm.getLnglt() * Math.pow(10, 6)), 0));
				itemBuffer.append(StringUtils.jsonChar("latrb",new BigDecimal(zoneAlarm.getLatrb() * Math.pow(10, 6)), 0));

				int attr2 = Integer.parseInt(zoneAlarm.getAttr().substring(2, 3));
				int attr3 = Integer.parseInt(zoneAlarm.getAttr().substring(3, 4));
				int attr4 = Integer.parseInt(zoneAlarm.getAttr().substring(4, 5));
				int attr5 = Integer.parseInt(zoneAlarm.getAttr().substring(5, 6));
				int attr8 = Integer.parseInt(zoneAlarm.getAttr().substring(8, 9));
				int attr9 = Integer.parseInt(zoneAlarm.getAttr().substring(9, 10));
				if( attr2 == 1 || attr3 == 1 || attr4 == 1 || attr5 == 1 || attr8 == 1 || attr9 == 1){
					itemBuffer.append(StringUtils.jsonChar("ddalertinfo",StringUtils.charStr(zoneAlarm.getDdalertinfo()==null?"":zoneAlarm.getDdalertinfo()), 0));
					itemBuffer.append(StringUtils.jsonChar("driveralertinfo",StringUtils.charStr(zoneAlarm.getDriveralertinfo()==null?"":zoneAlarm.getDriveralertinfo()), 0));
				}
				
				int time = Integer.parseInt(zoneAlarm.getAttr().substring(0, 1));
				int speed = Integer.parseInt(zoneAlarm.getAttr().substring(1, 2));


				if( time == 1 && speed == 1){
					itemBuffer.append(StringUtils.jsonChar("lngrb",new BigDecimal(zoneAlarm.getLngrb() * Math.pow(10, 6)), 0));
					itemBuffer.append(StringUtils.jsonChar("timeS",StringUtils.charStr(zoneAlarm.getTimeS()), 0));
					itemBuffer.append(StringUtils.jsonChar("timeE",StringUtils.charStr(zoneAlarm.getTimeE()), 0));
					itemBuffer.append(StringUtils.jsonChar("speedLimit",zoneAlarm.getSpeedLimit(), 0));
					itemBuffer.append(StringUtils.jsonChar("speedTime",zoneAlarm.getSpeedTime(), 1));
				}


				if( time == 1 && speed == 0){
					itemBuffer.append(StringUtils.jsonChar("lngrb",new BigDecimal(zoneAlarm.getLngrb() * Math.pow(10, 6)), 0));
					itemBuffer.append(StringUtils.jsonChar("timeS",StringUtils.charStr(zoneAlarm.getTimeS()), 0));
					itemBuffer.append(StringUtils.jsonChar("timeE",StringUtils.charStr(zoneAlarm.getTimeE()), 1));
				}

				if( time == 0 && speed == 1){
					itemBuffer.append(StringUtils.jsonChar("lngrb",new BigDecimal(zoneAlarm.getLngrb() * Math.pow(10, 6)), 0));
					itemBuffer.append(StringUtils.jsonChar("speedLimit",zoneAlarm.getSpeedLimit(), 0));
					itemBuffer.append(StringUtils.jsonChar("speedTime",zoneAlarm.getSpeedTime(), 1));
				}

				if( time == 0 && speed == 0){
					itemBuffer.append(StringUtils.jsonChar("lngrb",new BigDecimal(zoneAlarm.getLngrb() * Math.pow(10, 6)), 1));
				}

				itemBuffer.append("}");

				if(count == Constant.DATA_PACKET_NUM){
					flag = true;
				}else{
					if(i+1 == size){						
						flag = true;
					}else{
						itemBuffer.append(",");
					}
				}

				if(flag){
					resultBuffer.append(buffer);
					resultBuffer.append(StringUtils.jsonChar("count",count, 0));
					resultBuffer.append(StringUtils.jsonChar("items","[", 1));
					itemBuffer.append("]}");
					resultBuffer.append(itemBuffer);

					logger.info("[设置矩形区域]数据包："+resultBuffer.toString());
					result = resultBuffer.toString();

					MqUtil.write(devicetype, resultBuffer.toString());
					
					//添加指令下发记录
					Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
					ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34306, "设置矩形区域", seq, resultBuffer.toString());

					itemBuffer = new StringBuffer();
					resultBuffer = new StringBuffer();
					flag = false;
					count = 0;
				}
			}

			//加入至发送缓存中
			ToolsUtil.sendMap.put("34306_"+seq, seq);

			return result;
		} catch (Exception e) {
			logger.error("发送矩形区域数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 删除矩形区域
	 * @param terminal
	 * @param list
	 * @param update
	 * @return
	 */
	public static String deleteRectangleArea(Integer devicetype,String terminal,List<ZoneAlarm> list,int seq,String carnumber){
		try {
			String result = "";
			StringBuffer buffer = new StringBuffer();
			StringBuffer itemBuffer = new StringBuffer();
			StringBuffer resultBuffer = new StringBuffer();

			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34307, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			if(list !=null){
				int count = 0;
				int size = list.size();
				boolean flag = false;
				for (int i = 0; i < list.size(); i++) {
					ZoneAlarm zoneAlarm = list.get(i);
					count++;

					itemBuffer.append("{");
					itemBuffer.append(StringUtils.jsonChar("areaId",zoneAlarm.getAreaId(), 1));
					itemBuffer.append("}");

					if(count == 125){
						flag = true;
					}else{
						if(i+1 == size){						
							flag = true;
						}else{
							itemBuffer.append(",");
						}
					}

					if(flag){
						resultBuffer.append(buffer);
						resultBuffer.append(StringUtils.jsonChar("count",count, 0));
						resultBuffer.append(StringUtils.jsonChar("items","[", 1));
						itemBuffer.append("]}");
						resultBuffer.append(itemBuffer);

						logger.info("[删除矩形区域]数据包："+resultBuffer.toString());

						MqUtil.write(devicetype, resultBuffer.toString());
						
						//添加指令下发记录
						Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
						ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34307, "设置矩形区域", seq, resultBuffer.toString());

						itemBuffer = new StringBuffer();
						resultBuffer = new StringBuffer();
						flag = false;
						count = 0;
					}
				}
			}else{
				buffer.append(StringUtils.jsonChar("count",0, 1));
				buffer.append("}");
				logger.info("[删除矩形区域]数据包："+buffer.toString());
				result = buffer.toString();

				MqUtil.write(devicetype, buffer.toString());
				
				//添加指令下发记录
				Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
				ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34307, "设置矩形区域", seq, buffer.toString());
			}

			//加入至发送缓存中
			ToolsUtil.sendMap.put("34307_"+seq, seq);

			return result;
		} catch (Exception e) {
			logger.error("发送删除矩形区域数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 多边形区域
	 * @param zoneAlarm
	 * @return
	 */
	public static String polygonArea(Integer devicetype,String terminal,ZoneAlarm zoneAlarm,int seq,String carnumber){

		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34308, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("areaId",zoneAlarm.getAreaId(),0));
			buffer.append(StringUtils.jsonChar("attr",StringUtils.charStr(zoneAlarm.getAttr()), 0));

			int attr2 = Integer.parseInt(zoneAlarm.getAttr().substring(2, 3));
			int attr3 = Integer.parseInt(zoneAlarm.getAttr().substring(3, 4));
			int attr4 = Integer.parseInt(zoneAlarm.getAttr().substring(4, 5));
			int attr5 = Integer.parseInt(zoneAlarm.getAttr().substring(5, 6));
			int attr8 = Integer.parseInt(zoneAlarm.getAttr().substring(8, 9));
			int attr9 = Integer.parseInt(zoneAlarm.getAttr().substring(9, 10));
			if( attr2 == 1 || attr3 == 1 || attr4 == 1 || attr5 == 1 || attr8 == 1 || attr9 == 1){
				buffer.append(StringUtils.jsonChar("ddalertinfo",StringUtils.charStr(zoneAlarm.getDdalertinfo()==null?"":zoneAlarm.getDdalertinfo()), 0));
				buffer.append(StringUtils.jsonChar("driveralertinfo",StringUtils.charStr(zoneAlarm.getDriveralertinfo()==null?"":zoneAlarm.getDriveralertinfo()), 0));
			}
			
			int time = Integer.parseInt(zoneAlarm.getAttr().substring(0, 1));
			int speed = Integer.parseInt(zoneAlarm.getAttr().substring(1, 2));
			if(time == 1){
				buffer.append(StringUtils.jsonChar("timeS", StringUtils.charStr(zoneAlarm.getTimeS()), 0));
				buffer.append(StringUtils.jsonChar("timeE", StringUtils.charStr(zoneAlarm.getTimeE()), 0));
			}

			if(speed == 1){
				buffer.append(StringUtils.jsonChar("speedLimit", zoneAlarm.getSpeedLimit(), 0));
				buffer.append(StringUtils.jsonChar("speedTime", zoneAlarm.getSpeedTime(), 0));
			}

			buffer.append(StringUtils.jsonChar("count", zoneAlarm.getLatsrt().split(",").length, 0));


			StringBuffer itemsBuffer = new StringBuffer();

			itemsBuffer.append("[");
			String[] lat = zoneAlarm.getLatsrt().split(",");
			String[] lng = zoneAlarm.getLngsrt().split(",");

			for (int i = 0; i < lat.length; i++) {
				itemsBuffer.append("{");
				itemsBuffer.append(StringUtils.jsonChar("lat",new BigDecimal(Double.parseDouble(lat[i]) * Math.pow(10, 6)),0));
				itemsBuffer.append(StringUtils.jsonChar("lng",new BigDecimal(Double.parseDouble(lng[i]) * Math.pow(10, 6)),1));
				if(i == (lat.length-1)){
					itemsBuffer.append("}"); 
				}else{
					itemsBuffer.append("},"); 
				}
			}
			itemsBuffer.append("]");
			buffer.append(StringUtils.jsonChar("items",itemsBuffer.toString(),1));
			buffer.append("}");
			logger.info("[设置多边形区域]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());

			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34308, "设置多边形区域", seq, buffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("34308_"+seq, seq);

			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送多边形区域数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 删除多边形区域
	 * @param terminal
	 * @param list
	 * @param update
	 * @return
	 */
	public static String deletePolygonArea(Integer devicetype,String terminal,List<ZoneAlarm> list,int seq,String carnumber){
		try {
			String result = "";
			StringBuffer buffer = new StringBuffer();
			StringBuffer itemBuffer = new StringBuffer();
			StringBuffer resultBuffer = new StringBuffer();

			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34309, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			if(list !=null){
				buffer.append(StringUtils.jsonChar("seq",seq,0));
				int count = 0;
				int size = list.size();
				boolean flag = false;
				for (int i = 0; i < list.size(); i++) {
					ZoneAlarm zoneAlarm = list.get(i);
					count++;
					itemBuffer.append("{");
					itemBuffer.append(StringUtils.jsonChar("areaId",zoneAlarm.getAreaId(), 1));
					itemBuffer.append("}");

					if(count == 125){
						flag = true;
					}else{
						if(i+1 == size){						
							flag = true;
						}else{
							itemBuffer.append(",");
						}
					}
					if(flag){
						resultBuffer.append(buffer);
						resultBuffer.append(StringUtils.jsonChar("count",count, 0));
						resultBuffer.append(StringUtils.jsonChar("items","[", 1));
						itemBuffer.append("]}");
						resultBuffer.append(itemBuffer);

						logger.info("[删除多边形区域]数据包："+resultBuffer.toString());

						MqUtil.write(devicetype, buffer.toString());
						
						//添加指令下发记录
						Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
						ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34309, "删除多边形区域", seq, resultBuffer.toString());

						itemBuffer = new StringBuffer();
						resultBuffer = new StringBuffer();
						flag = false;
						count = 0;
					}
				}
			}else{
				buffer.append(StringUtils.jsonChar("seq",seq,0));
				buffer.append(StringUtils.jsonChar("count",0, 1));
				buffer.append("}");
				logger.info("[删除多边形区域]数据包："+buffer.toString());

				MqUtil.write(devicetype, buffer.toString());
				
				//添加指令下发记录
				Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
				ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34309, "删除多边形区域", seq, buffer.toString());
			}
			//加入至发送缓存中
			ToolsUtil.sendMap.put("34309_"+seq, seq);
			return result;
		} catch (Exception e) {
			logger.error("发送删除多边形区域数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 设置线路
	 * @param terminal 终端手机号
	 * @param line 线路属性
	 * @param list 线路拐点
	 * @return
	 */
	public static String setLine(Integer devicetype,String terminal,Line line,List<Line> list,int seq,String carnumber){
		try {
			String result = "";
			StringBuffer buffer = new StringBuffer();
			StringBuffer itemBuffer = new StringBuffer();
			StringBuffer resultBuffer = new StringBuffer();

			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34310, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("rid",line.getRid(), 0));
			buffer.append(StringUtils.jsonChar("rattr",StringUtils.charStr(line.getRattr()), 0));

			int date = Integer.parseInt(line.getRattr().substring(0, 1));
			if(date == 1){
				buffer.append(StringUtils.jsonChar("sdate",StringUtils.charStr(line.getSdate()), 0));
				buffer.append(StringUtils.jsonChar("edate",StringUtils.charStr(line.getEdate()), 0));
			}

			int size = list.size();
			boolean flag = false;
			for (int i = 0; i < list.size(); i++) {
				Line li = list.get(i);

				itemBuffer.append("{");
				itemBuffer.append(StringUtils.jsonChar("iid",li.getIid(), 0));
				itemBuffer.append(StringUtils.jsonChar("lid",li.getLid(), 0));
				itemBuffer.append(StringUtils.jsonChar("lat",new BigDecimal(li.getLat() * Math.pow(10, 6)), 0));
				itemBuffer.append(StringUtils.jsonChar("lng",new BigDecimal(li.getLng() * Math.pow(10, 6)), 0));
				itemBuffer.append(StringUtils.jsonChar("width",li.getWidth(), 0));


				int time = Integer.parseInt(li.getLattr().substring(0, 1));
				int speed = Integer.parseInt(li.getLattr().substring(1, 2));

				if( time == 1 && speed == 1){
					itemBuffer.append(StringUtils.jsonChar("lattr",StringUtils.charStr(li.getLattr()), 0));
					itemBuffer.append(StringUtils.jsonChar("rtime",li.getRtime(), 0));
					itemBuffer.append(StringUtils.jsonChar("stime",li.getStime(), 0));
					itemBuffer.append(StringUtils.jsonChar("tspeed",li.getTspeed(), 0));
					itemBuffer.append(StringUtils.jsonChar("pspeed",li.getPspeed(), 1));
				}


				if( time == 1 && speed == 0){
					itemBuffer.append(StringUtils.jsonChar("lattr",StringUtils.charStr(li.getLattr()), 0));
					itemBuffer.append(StringUtils.jsonChar("rtime",li.getRtime(), 0));
					itemBuffer.append(StringUtils.jsonChar("stime",li.getStime(), 1));
				}

				if( time == 0 && speed == 1){
					itemBuffer.append(StringUtils.jsonChar("lattr",StringUtils.charStr(li.getLattr()), 0));
					itemBuffer.append(StringUtils.jsonChar("tspeed",li.getTspeed(), 0));
					itemBuffer.append(StringUtils.jsonChar("pspeed",li.getPspeed(), 1));
				}

				if( time == 0 && speed == 0){
					itemBuffer.append(StringUtils.jsonChar("lattr",StringUtils.charStr(li.getLattr()), 1));
				}

				itemBuffer.append("}");

				if(i+1 == size){						
					flag = true;
				}else{
					itemBuffer.append(",");
				}

				if(flag){
					resultBuffer.append(buffer);
					resultBuffer.append(StringUtils.jsonChar("icount",size,0));
					resultBuffer.append(StringUtils.jsonChar("items","[", 1));
					itemBuffer.append("]}");
					resultBuffer.append(itemBuffer);

					logger.info("[设置线路]数据包："+resultBuffer.toString());

					MqUtil.write(devicetype, resultBuffer.toString());
					
					//添加指令下发记录
					Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
					ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34310, "设置线路", seq, resultBuffer.toString());
					
					result = resultBuffer.toString();
					itemBuffer = new StringBuffer();
					resultBuffer = new StringBuffer();
					flag = false;
				}
			}

			//加入至发送缓存中
			ToolsUtil.sendMap.put("34310_"+seq, seq);

			return result;
		} catch (Exception e) {
			logger.error("发送设置线路数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 删除线路
	 * @param terminal
	 * @param list
	 * @param update
	 * @return
	 */
	public static String deleteLine(Integer devicetype,String terminal,List<Line> list,int seq,String carnumber){
		try {
			String result = "";
			StringBuffer buffer = new StringBuffer();
			StringBuffer itemBuffer = new StringBuffer();
			StringBuffer resultBuffer = new StringBuffer();

			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34311, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			if(list!=null){
				buffer.append(StringUtils.jsonChar("seq",seq,0));
				int count = 0;
				int size = list.size();
				boolean flag = false;
				for (int i = 0; i < list.size(); i++) {
					Line line = list.get(i);
					count++;
					itemBuffer.append("{");
					itemBuffer.append(StringUtils.jsonChar("id",line.getId(), 1));
					itemBuffer.append("}");

					if(count == 125){
						flag = true;
					}else{
						if(i+1 == size){						
							flag = true;
						}else{
							itemBuffer.append(",");
						}
					}
					if(flag){
						resultBuffer.append(buffer);
						resultBuffer.append(StringUtils.jsonChar("count",count, 0));
						resultBuffer.append(StringUtils.jsonChar("items","[", 1));
						itemBuffer.append("]}");
						resultBuffer.append(itemBuffer);

						logger.info("[删除线路]数据包："+resultBuffer.toString());
						result = resultBuffer.toString();

						MqUtil.write(devicetype, resultBuffer.toString());
						
						//添加指令下发记录
						Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
						ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34311, "删除线路", seq, resultBuffer.toString());

						itemBuffer = new StringBuffer();
						resultBuffer = new StringBuffer();
						flag = false;
						count = 0;
					}
				}
			}else{
				buffer.append(StringUtils.jsonChar("seq",seq,0));
				buffer.append(StringUtils.jsonChar("count",0, 1));
				buffer.append("}");
				logger.info("[删除线路]数据包："+buffer.toString());
				result = buffer.toString();
				MqUtil.write(devicetype, buffer.toString());
				
				//添加指令下发记录
				Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
				ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34311, "删除线路", seq, buffer.toString());
			}
			//加入至发送缓存中
			ToolsUtil.sendMap.put("34311_"+seq, seq);
			return result;
		} catch (Exception e) {
			logger.error("发送删除线路数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 文本信息发送
	 * @param terminal 设备号
	 * @param emergency 紧急
	 * @param lcd 终端显示器显示
	 * @param tts 终端TTS播读
	 * @param adv 广告屏显示
	 * @param action 电召消息
	 * @param dist 带经纬度
	 * @param content 内容
	 * @return
	 */
	public static String textInfo(Integer devicetype,String terminal,int emergency,int lcd,int tts,int adv,int action,int dist,String content,int seq,int time,String carnumber){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",33536, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("emergency",emergency,0));
			buffer.append(StringUtils.jsonChar("lcd",lcd,0));
			buffer.append(StringUtils.jsonChar("tts",tts,0));
			buffer.append(StringUtils.jsonChar("adv",adv,0));
			buffer.append(StringUtils.jsonChar("action",action,0));
			buffer.append(StringUtils.jsonChar("dist",dist,0));
			buffer.append(StringUtils.jsonChar("content",StringUtils.charStr(content),0));
			buffer.append(StringUtils.jsonChar("textseq",seq%20,0));
			buffer.append(StringUtils.jsonChar("time",time,1));
			buffer.append("}");
			logger.info("[文本信息发送]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33536, "文本信息发送", seq, buffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("33536_"+seq, seq);
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送文本信息发送数据包编码异常",e);
			return null;
		}
	}
	/**
	 * 电召信息发送
	 * @param terminal 设备号
	 * @param emergency 紧急
	 * @param lcd 终端显示器显示
	 * @param tts 终端TTS播读
	 * @param adv 广告屏显示
	 * @param action 电召消息
	 * @param dist 带经纬度
	 * @param content 内容
	 * @return
	 */
	public static String sendDialInfo(Integer devicetype,String terminal,int emergency,int lcd,int tts,int adv,int action,int dist,
			String content,int seq,String carnumber,double lng,double lat,String ordId){
		
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",33536, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("emergency",emergency,0));
			buffer.append(StringUtils.jsonChar("lcd",lcd,0));
			buffer.append(StringUtils.jsonChar("tts",tts,0));
			buffer.append(StringUtils.jsonChar("adv",adv,0));
			buffer.append(StringUtils.jsonChar("action",action,0));
			buffer.append(StringUtils.jsonChar("dist",dist,0));
			buffer.append(StringUtils.jsonChar("content",StringUtils.charStr(content),0));
			buffer.append(StringUtils.jsonChar("lat",new BigDecimal(lat * Math.pow(10, 6)),0));
			buffer.append(StringUtils.jsonChar("lng",new BigDecimal(lng * Math.pow(10, 6)),0));
			buffer.append(StringUtils.jsonChar("ordId",StringUtils.charStr(ordId),1));
			buffer.append("}");
			logger.info("[电召信息发送]数据包："+buffer.toString());
			
			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33536, "电召信息发送", seq, buffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("33536_"+seq, seq);
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送电召信息发送数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 设置事件
	 * @param terminal 设备号
	 * @param list 事件列表
	 * @param type  0：删除终端现有所有事件，该命令后不带后继字节
					1：更新事件。
					2：追加事件。
					3:  修改事件。
					4：删除特定几项事件，之后事件项中无需带事件内容
	 * @return
	 */
	public static String setEvent(Integer devicetype,String terminal,List<Event> list,int type,int seq,String carnumber){

		try {
			StringBuffer buffer = new StringBuffer();
			StringBuffer itemBuffer = new StringBuffer();
			StringBuffer resultBuffer = new StringBuffer();

			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",33537, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));

			if(type!=0){
				buffer.append(StringUtils.jsonChar("type",type, 0));
				int count = 0;
				int size = list.size();
				boolean flag = false;
				for (int i = 0; i < list.size(); i++) {

					Event event = list.get(i);
					count++;

					itemBuffer.append("{");
					if(type!=4){
						itemBuffer.append(StringUtils.jsonChar("id",event.getId(), 0));
						itemBuffer.append(StringUtils.jsonChar("len",event.getContent().getBytes().length, 0));
						itemBuffer.append(StringUtils.jsonChar("content",StringUtils.charStr(event.getContent()), 1));
					}else{
						itemBuffer.append(StringUtils.jsonChar("id",event.getId(), 1));
					}
					itemBuffer.append("}");

					if(count == Constant.DATA_PACKET_NUM){
						flag = true;
					}else{
						if(i+1 == size){						
							flag = true;
						}else{
							itemBuffer.append(",");
						}
					}
					if(flag){
						resultBuffer.append(buffer);
						resultBuffer.append(StringUtils.jsonChar("count",count, 0));
						resultBuffer.append(StringUtils.jsonChar("items","[", 1));
						itemBuffer.append("]}");
						resultBuffer.append(itemBuffer);

						logger.info("[设置事件]数据包："+resultBuffer.toString());

						MqUtil.write(devicetype, resultBuffer.toString());
						
						//添加指令下发记录
						Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
						ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33537, "设置事件", seq, resultBuffer.toString());
						
						itemBuffer = new StringBuffer();
						resultBuffer = new StringBuffer();
						flag = false;
						count = 0;
					}
				}
			}else{
				buffer.append(StringUtils.jsonChar("type",type, 1));
				buffer.append("}");
				logger.info("[设置事件]数据包："+buffer.toString());
				MqUtil.write(devicetype, buffer.toString());

				//添加指令下发记录
				Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
				ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33537, "设置事件", seq, buffer.toString());
			}
			//加入至发送缓存中
			ToolsUtil.sendMap.put("33537_"+seq, seq);
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送设置事件数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 提问下发
	 * @param terminal 设备号
	 * @param list 问题答案列表
	 * @param content 问题内容
	 * @param type 提问下发标志
	 * @return
	 */
	public static String sendQuestion(Integer devicetype,String terminal,List<Question> list,String content,int tag0,int tag3,int tag4,int seq,String carnumber){

		try {
			StringBuffer buffer = new StringBuffer();
			StringBuffer itemBuffer = new StringBuffer();
			StringBuffer resultBuffer = new StringBuffer();
			String result="";
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",33538, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("tag0",tag0,0));
			buffer.append(StringUtils.jsonChar("tag3",tag3,0));
			buffer.append(StringUtils.jsonChar("tag4",tag4,0));
			buffer.append(StringUtils.jsonChar("len",content.getBytes().length,0));
			buffer.append(StringUtils.jsonChar("content",StringUtils.charStr(content),0));

			int size = list.size();
			boolean flag = false;
			for (int i = 0; i < list.size(); i++) {

				Question question = list.get(i);
				itemBuffer.append("{");
				itemBuffer.append(StringUtils.jsonChar("id",question.getId(), 0));
				itemBuffer.append(StringUtils.jsonChar("content",StringUtils.charStr(question.getContent()), 1));
				itemBuffer.append("}");


				if(i+1 == size){						
					flag = true;
				}else{
					itemBuffer.append(",");
				}

				if(flag){
					resultBuffer.append(buffer);
					resultBuffer.append(StringUtils.jsonChar("items","[", 1));
					itemBuffer.append("]}");
					resultBuffer.append(itemBuffer);

					logger.info("[提问下发]数据包："+resultBuffer.toString());

					MqUtil.write(devicetype, resultBuffer.toString());
					
					//添加指令下发记录
					Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
					ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33538, "提问下发", seq, resultBuffer.toString());
					
					result=resultBuffer.toString();
					itemBuffer = new StringBuffer();
					resultBuffer = new StringBuffer();
					flag = false;
				}
			}
			//加入至发送缓存中
			ToolsUtil.sendMap.put("33538_"+seq, seq);
			return result;
		} catch (Exception e) {
			logger.error("发送提问下发数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 设置点播菜单
	 * @param terminal 设备号
	 * @param list 菜单列表
	 * @param type 0：删除终端现有信息项；
					1：更新菜单；
					2：追加菜单；
					3：修改菜单
	 * @return
	 */
	public static String setInfoMenu(Integer devicetype,String terminal,List<Menu> list,int type,int seq,String carnumber){

		try {
			StringBuffer buffer = new StringBuffer();
			StringBuffer itemBuffer = new StringBuffer();
			StringBuffer resultBuffer = new StringBuffer();
			String result="";

			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",33539, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));


			buffer.append(StringUtils.jsonChar("type",type, 0));
			int count = 0;
			int size = list.size();
			boolean flag = false;
			for (int i = 0; i < list.size(); i++) {

				Menu menu = list.get(i);
				count++;

				itemBuffer.append("{");
				itemBuffer.append(StringUtils.jsonChar("type",menu.getId(), 0));
				itemBuffer.append(StringUtils.jsonChar("len",menu.getContent().getBytes().length, 0));
				itemBuffer.append(StringUtils.jsonChar("content",StringUtils.charStr(menu.getContent()), 1));
				itemBuffer.append("}");

				if(count == Constant.DATA_PACKET_NUM){
					flag = true;
				}else{
					if(i+1 == size){						
						flag = true;
					}else{
						itemBuffer.append(",");
					}
				}
				if(flag){
					resultBuffer.append(buffer);
					resultBuffer.append(StringUtils.jsonChar("count",count, 0));
					resultBuffer.append(StringUtils.jsonChar("items","[", 1));
					itemBuffer.append("]}");
					resultBuffer.append(itemBuffer);

					logger.info("[设置点播菜单]数据包："+resultBuffer.toString());

					MqUtil.write(devicetype, resultBuffer.toString());
					
					//添加指令下发记录
					Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
					ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33539, "设置点播菜单", seq, resultBuffer.toString());
					
					result=resultBuffer.toString();
					itemBuffer = new StringBuffer();
					resultBuffer = new StringBuffer();
					flag = false;
					count = 0;
				}
			}
			//加入至发送缓存中
			ToolsUtil.sendMap.put("33539_"+seq, seq);
			return result;
		} catch (Exception e) {
			logger.error("发送设置事件数据包编码异常",e);
			return "";
		}
	}

	/**
	 * 信息服务发送
	 * @param type
	 * @param content
	 * @return
	 */
	public static String sendInfoService(Integer devicetype,String terminal,int type,String content,int seq,String carnumber){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",33540, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("type",type,0));
			buffer.append(StringUtils.jsonChar("len",content.getBytes().length,0));
			buffer.append(StringUtils.jsonChar("content",StringUtils.charStr(content),1));
			buffer.append("}");

			logger.info("[信息服务发送]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33540, "信息服务发送", seq, buffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("33540_"+seq, seq);
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送信息服务发送数据包编码异常",e);
			return "";
		}
	}


	/**
	 * 电话回拨
	 * @param terminal 设备号
	 * @param mark 标志 0：普通通话；1：监听
	 * @param tel 电话号码 电话号码
	 * @return
	 */
	public static String telephoneCallBack(Integer devicetype,String terminal,int mark,String tel,int seq,String carnumber){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",33792, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("mark",mark,0));
			buffer.append(StringUtils.jsonChar("tel",StringUtils.charStr(tel),1));
			buffer.append("}");

			logger.info("[电话回拨]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33792, "电话回拨", seq, buffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("33792_"+seq, seq);
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送电话回拨数据包编码异常",e);
			return "";
		}
	}


	/**
	 * 设置电话本
	 * @param terminal 终端号码
	 * @param list 电话本列表
	 * @param type  0：删除终端上所有存储的联系人；
					1：表示更新电话本(删除终端中已有全部联系人并追加消息中的联系人)；
					2：表示追加电话本；
					3：表示修改电话本(以联系人为索引)
	 * @return
	 */
	public static String setTelephoneBook(Integer devicetype,String terminal,List<TelephoneBook> list,int type,int seq,String carnumber){

		try {
			StringBuffer buffer = new StringBuffer();
			StringBuffer itemBuffer = new StringBuffer();
			StringBuffer resultBuffer = new StringBuffer();
			String result="";

			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",33793, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));

			if(type!=0){
				buffer.append(StringUtils.jsonChar("type",type, 0));
				int count = 0;
				int size = list.size();
				boolean flag = false;
				for (int i = 0; i < list.size(); i++) {

					TelephoneBook telephoneBook = list.get(i);
					count++;

					itemBuffer.append("{");
					itemBuffer.append(StringUtils.jsonChar("tag",telephoneBook.getTag(), 0));
					itemBuffer.append(StringUtils.jsonChar("telLen",telephoneBook.getTel().getBytes().length, 0));
					itemBuffer.append(StringUtils.jsonChar("tel",StringUtils.charStr(telephoneBook.getTel()), 0));
					itemBuffer.append(StringUtils.jsonChar("ctcLen",telephoneBook.getContact().getBytes().length, 0));
					itemBuffer.append(StringUtils.jsonChar("contact",StringUtils.charStr(telephoneBook.getContact()), 1));
					itemBuffer.append("}");

					if(count == Constant.DATA_PACKET_NUM){
						flag = true;
					}else{
						if(i+1 == size){						
							flag = true;
						}else{
							itemBuffer.append(",");
						}
					}
					if(flag){
						resultBuffer.append(buffer);
						resultBuffer.append(StringUtils.jsonChar("count",count, 0));
						resultBuffer.append(StringUtils.jsonChar("items","[", 1));
						itemBuffer.append("]}");
						resultBuffer.append(itemBuffer);

						logger.info("[设置电话本]数据包："+resultBuffer.toString());

						MqUtil.write(devicetype, buffer.toString());
						
						//添加指令下发记录
						Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
						ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33793, "设置电话本", seq, buffer.toString());
						
						result=resultBuffer.toString();
						itemBuffer = new StringBuffer();
						resultBuffer = new StringBuffer();
						flag = false;
						count = 0;
					}
				}
			}else{
				buffer.append(StringUtils.jsonChar("type",type, 1));
				buffer.append("}");
				logger.info("[设置电话本]数据包："+buffer.toString());
				MqUtil.write(devicetype, buffer.toString());
				
				//添加指令下发记录
				Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
				ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33793, "设置电话本", seq, buffer.toString());
			}
			//加入至发送缓存中
			ToolsUtil.sendMap.put("33793_"+seq, seq);
			return result;
		} catch (Exception e) {
			logger.error("发送设置事件数据包编码异常",e);
			return "";
		}
	}


	/**
	 * 录音 
	 * @param terminal 设备号
	 * @param cmd 录音命令 0：停止录音；1:开始录音
	 * @param seconds 录音时间 单位为秒(s)，0表示一直录音
	 * @param tag 保存标志 0：实时长传；1：保存
	 * @param rate 音频采样率 0：8k；1:11k；2:23K；3：32K；其他保留
	 * @return
	 */
	public static String soundRecording(Integer devicetype,String terminal,int cmd,int seconds,int tag,int rate,int seq,String carnumber){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34820, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("cmd",cmd,0));
			buffer.append(StringUtils.jsonChar("seconds",seconds,0));
			buffer.append(StringUtils.jsonChar("tag",tag,0));
			buffer.append(StringUtils.jsonChar("rate",rate,1));

			buffer.append("}");

			logger.info("[录音]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34820, "录音", seq, buffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("34820_"+seq, seq);
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送电话回拨数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 摄像头立即拍摄
	 * @param terminal 设备号
	 * @param cameras 
	 * @return
	 */
	public static boolean cameras(Integer devicetype,String terminal,Cameras cameras,int seq,String carnumber){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34817, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("channel",cameras.getChannel(),0));
			buffer.append(StringUtils.jsonChar("cmd",cameras.getCmd(),0));
			buffer.append(StringUtils.jsonChar("inv",cameras.getInv(),0));
			buffer.append(StringUtils.jsonChar("save",cameras.getSave(),0));
			buffer.append(StringUtils.jsonChar("screen",cameras.getScreen(),0));
			buffer.append(StringUtils.jsonChar("quality",cameras.getQuality(),0));
			buffer.append(StringUtils.jsonChar("bright",cameras.getBright(),0));
			buffer.append(StringUtils.jsonChar("contrast",cameras.getContrast(),0));
			buffer.append(StringUtils.jsonChar("saturation",cameras.getSaturation(),0));
			buffer.append(StringUtils.jsonChar("chroma",cameras.getChroma(),1));

			buffer.append("}");

			logger.info("[录音]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34817, "录音", seq, buffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("34817_"+seq, seq);
			return true;
		} catch (Exception e) {
			logger.error("发送电话回拨数据包编码异常",e);
			return false;
		}
	}


	/**
	 * 存储多媒体数据检索
	 * @param terminal 设备号
	 * @param type 多媒体类型 0:图像;1:音频:2:视频;
	 * @param wayId 通道ID 0表示检索该媒体类型的所有通道; 
	 * @param eventId 事件项编码  0:平台下发指令:1:定时动作:2:抢劫报警触:3:碰撞侧翻报警触发;其他保留
	 * @param sdate 起始时间
	 * @param edate 结束时间
	 * @return
	 */
	public static String multimediaDataRetrieval (Integer devicetype,String terminal,int type,int wayId,int eventId,String sdate,String edate,int seq){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34818, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("type",type,0));
			buffer.append(StringUtils.jsonChar("id",wayId,0));
			buffer.append(StringUtils.jsonChar("fmt",eventId,0));
			buffer.append(StringUtils.jsonChar("starttime",StringUtils.charStr(sdate),0));
			buffer.append(StringUtils.jsonChar("endtime",StringUtils.charStr(edate),1));

			buffer.append("}");

			logger.info("[存储多媒体数据检索]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34818, "存储多媒体数据检索", seq, buffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("34818_"+seq, seq);
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送存储多媒体数据检索数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 存储多媒体数据上传
	 * @param terminal 设备号
	 * @param type 多媒体类型 0:图像;1:音频:2:视频;
	 * @param wayId 通道ID 0表示检索该媒体类型的所有通道; 
	 * @param eventId 事件项编码  0:平台下发指令:1:定时动作:2:抢劫报警触:3:碰撞侧翻报警触发;其他保留
	 * @param sdate 起始时间
	 * @param edate 结束时间
	 * @param del 删除标志 0:保留;1:删除;
	 * @return
	 */
	public static String multimediaDataUpload(Integer devicetype,String terminal,int type,int wayId,int eventId,String sdate,String edate,int del,int seq){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34819, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("type",type,0));
			buffer.append(StringUtils.jsonChar("id",wayId,0));
			buffer.append(StringUtils.jsonChar("fmt",eventId,0));
			buffer.append(StringUtils.jsonChar("starttime",StringUtils.charStr(sdate),0));
			buffer.append(StringUtils.jsonChar("endtime",StringUtils.charStr(edate),0));
			buffer.append(StringUtils.jsonChar("flg",del,1));

			buffer.append("}");

			logger.info("[存储多媒体数据上传]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34819, "存储多媒体数据上传", seq, buffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("34819_"+seq, seq);
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送存储多媒体数据检索数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 发送POI信息点
	 * @param terminal
	 * @param lng
	 * @param lat
	 * @param poiname
	 * @return
	 */
	public static String sendPoiInfo(Integer devicetype,String terminal,Double lng,Double lat,String poiname,int seq,String carnumber){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",36608, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("lat",new BigDecimal(lat * Math.pow(10, 6)),0));
			buffer.append(StringUtils.jsonChar("lng",new BigDecimal(lng * Math.pow(10, 6)),0));
			buffer.append(StringUtils.jsonChar("addr",StringUtils.charStr(poiname),1));
			buffer.append("}");

			logger.info("[发送POI信息点]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 36608, "发送POI信息点", seq, buffer.toString());
			
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送POI信息点数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 多媒体数据上传应答
	 * @param terminal 设备号
	 * @param seqR 应答流水号
	 * @param dataId 多媒体ID
	 * @param sum 重传包总数
	 * @param ids 重传包ID列表 id之间逗号隔开 
	 * @return
	 */
	public static String multimediaDataUploadAnswer(Integer devicetype,String terminal,int seqR,int dataId,int sum,String ids,String carnumber){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34816, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seqR",seqR,0));
			buffer.append(StringUtils.jsonChar("dataId",dataId,0));
			buffer.append(StringUtils.jsonChar("sum",sum,0));
			buffer.append(StringUtils.jsonChar("ids",StringUtils.charStr(ids),1));
			buffer.append("}");

			logger.info("[多媒体数据上传应答]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 34816, "多媒体数据上传应答", seqR, buffer.toString());
			
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送多媒体数据上传应答数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 费用下发
	 * @param terminal 设备号
	 * @param moneytype 费用类型
	 * @param auditstatus 审核状态 0 未审核 1已审核
	 * @param moneyreqid 费用应答id
	 * @param moneyid 费用id
	 * @param lat 纬度
	 * @param lng 经度
	 * @param money费用
	 * @param moneyuptime 费用上传时间
	 * @param billuptime 单据上传时间
	 * @param upaddr 费用上传地址
	 * @return
	 */
	public static String costInfo(Integer devicetype,String terminal,int moneytype,int auditstatus,String moneyreqid,String moneyid,Double lat,Double lng,
			String money,String moneyuptime,String billuptime,String upaddr,int seq,String carnumber){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",28702, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("moneytype",moneytype,0));
			buffer.append(StringUtils.jsonChar("auditstatus",auditstatus,0));
			buffer.append(StringUtils.jsonChar("moneyreqid",moneyreqid,0));
			buffer.append(StringUtils.jsonChar("moneyid",moneyid,0));
			buffer.append(StringUtils.jsonChar("lat",new BigDecimal(lat * Math.pow(10, 6)),0));
			buffer.append(StringUtils.jsonChar("lng",new BigDecimal(lng * Math.pow(10, 6)),0));
			buffer.append(StringUtils.jsonChar("money",StringUtils.charStr(money),0));
			buffer.append(StringUtils.jsonChar("moneyuptime",StringUtils.charStr(moneyuptime),0));
			buffer.append(StringUtils.jsonChar("billuptime",StringUtils.charStr(billuptime),0));
			buffer.append(StringUtils.jsonChar("upaddr",StringUtils.charStr(upaddr),1));
			buffer.append("}");
			logger.info("[费用下发]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 28702, "费用下发", seq, buffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("28702_"+seq, seq);
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送费用下发数据包编码异常",e);
			return null;
		}
	}
	
	
	/**
	 * 调度规则通知
	 * @param status 1 打开 2 关闭  opertype 1上次规则已生效  2上次规则未生效
	 * @return
	 */
	public static String notifyDialRule(String terminal,String carnumber,int seq,DialRule dialRule,int opertype){
		
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",21003, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("type",dialRule.getType(),0));
			buffer.append(StringUtils.jsonChar("radius",dialRule.getRadius(),0));
			buffer.append(StringUtils.jsonChar("cartype",StringUtils.charStr(dialRule.getCartype()),0));
			buffer.append(StringUtils.jsonChar("carnum",dialRule.getCarcount()==null?0:dialRule.getCarcount(),0));
			buffer.append(StringUtils.jsonChar("carstatus",StringUtils.charStr(dialRule.getCarstatus()),0));
			buffer.append(StringUtils.jsonChar("zkstate",StringUtils.charStr(dialRule.getZkstate()),0));
			buffer.append(StringUtils.jsonChar("disnumber",dialRule.getAssigncount()==null?0:dialRule.getAssigncount(),0));
			buffer.append(StringUtils.jsonChar("dispatchinterval",dialRule.getAssignmin(),0));
			buffer.append(StringUtils.jsonChar("effecttime",StringUtils.charStr(dialRule.getEffecttime()),0));
			buffer.append(StringUtils.jsonChar("countdisnum",dialRule.getTotalassigncount()==null?0:dialRule.getTotalassigncount(),0));
			buffer.append(StringUtils.jsonChar("advancedistime",dialRule.getAheadassignmin()==null?0:dialRule.getAheadassignmin(),0));
			buffer.append(StringUtils.jsonChar("instantdisnum",dialRule.getImmassigncount()==null?0:dialRule.getImmassigncount(),0));
			buffer.append(StringUtils.jsonChar("waittime",dialRule.getAssignwaitmin()==null?0:dialRule.getAssignwaitmin(),0));
			buffer.append(StringUtils.jsonChar("trainnum",dialRule.getTraincount()==null?0:dialRule.getTraincount(),0));
			buffer.append(StringUtils.jsonChar("stime",StringUtils.charStr(dialRule.getStime()),0));
			buffer.append(StringUtils.jsonChar("etime",StringUtils.charStr(dialRule.getEtime()),0));
			buffer.append(StringUtils.jsonChar("principle",StringUtils.charStr(dialRule.getPrinciple()),0));
			buffer.append(StringUtils.jsonChar("arrearage",dialRule.getArrearage()==null?0:dialRule.getArrearage(),0));
			buffer.append(StringUtils.jsonChar("breach",dialRule.getBreach()==null?0:dialRule.getBreach(),0));
			buffer.append(StringUtils.jsonChar("blacklist",dialRule.getBlacklist()==null?0:dialRule.getBlacklist(),0));
			buffer.append(StringUtils.jsonChar("opertype",opertype,1));
			buffer.append("}");
			logger.info("[调度规则通知]数据包："+buffer.toString());
			
			logger.info("发送[调度规则通知]数据包："+buffer.toString());
			MqUtil.write(29, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 21003, "调度规则通知", seq, buffer.toString());
		
			//加入至发送缓存中
			ToolsUtil.sendMap.put("21003_"+seq, seq);
			
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送调度规则通知数据包编码异常",e);
			return null;
		}
	}
	
	/**
	 * 车辆信息操作通知
	 * @param 操作类型	数字	1 增加车辆 2 修改车辆 3 删除车辆
	 * @param status 1 打开 2 关闭
	 * @return
	 */
	public static String operateCar(Integer devicetype,String terminal,int type,int seq,String carnumber){
		
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",21000, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("type",type,0));
			buffer.append(StringUtils.jsonChar("carnumber",StringUtils.charStr(carnumber),1));
			buffer.append("}");
			logger.info("[车辆信息操作通知]数据包："+buffer.toString());
			
			logger.info("发送[车辆信息操作通知]数据包："+buffer.toString());
			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 21000, "车辆信息操作通知", seq, buffer.toString());
			
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送车辆信息操作通知数据包编码异常",e);
			return null;
		}
	}
	
	/**
	 * 招标---订单任务下发
	 * @param terminal 设备号
	 * @param status 1 打开 2 关闭
	 * @return
	 */
	public static String sendDial(Integer devicetype,String terminal,String orderid,int answermode,int ordertype,String passengername,
			String passengerphone,String ordertime,String contents,int seq,String carnumber,String startaddr,String endaddr,
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
			
			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 35584, "招标", seq, buffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("35584_"+seq, seq);
			
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送招标数据包编码异常",e);
			return null;
		}
	}
	
	/**
	 * 中标---订单确认
	 * @param terminal 设备号
	 * @param status 1 打开 2 关闭
	 * @return
	 */
	public static String sendZb(Integer devicetype,String terminal,String orderid,int ordertype,String ordertime,String passengerphone,String remark,
			int seq,String startaddr,String endaddr){
		
		try {
			StringBuffer buffer = new StringBuffer();
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
			buffer.append(StringUtils.jsonChar("remark",StringUtils.charStr(remark),1));
			
			buffer.append("}");
			logger.info("[中标]数据包："+buffer.toString());
			
			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 35585, "中标", seq, buffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("35585_"+seq, seq);
			
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送中标数据包编码异常",e);
			return null;
		}
	}
	
	/**
	 * 终端参数设置
	 * @return
	 */
	public static String setTerminalParam(Integer devicetype,TerminalParameter terminalInfo,int seq,String carnumber,int val,String temp){
		try {
			StringBuffer basicBuffer = new StringBuffer();
			StringBuffer itemBuffer = new StringBuffer();
			StringBuffer resultBuffer = new StringBuffer();
			basicBuffer.append("{");
			basicBuffer.append(StringUtils.jsonChar("msgid",33027, 0));
			basicBuffer.append(StringUtils.jsonChar("subpacket","0", 0));
			basicBuffer.append(StringUtils.jsonChar("encryption","0", 0));
			basicBuffer.append(StringUtils.jsonChar("bodysize","0", 0));
			basicBuffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminalInfo.getSimCode()),0));
			basicBuffer.append(StringUtils.jsonChar("seq",seq,0));

			resultBuffer.append(basicBuffer);
			resultBuffer.append(StringUtils.jsonChar("count",1, 0));
			resultBuffer.append(StringUtils.jsonChar("items","[", 1));
			itemBuffer.append("{");
			itemBuffer.append(StringUtils.jsonChar("id",val, 0));
			itemBuffer.append(StringUtils.jsonChar("size",temp.getBytes().length, 0));
			itemBuffer.append(StringUtils.jsonChar("value",StringUtils.charStr(temp), 1));
			itemBuffer.append("}]");
			itemBuffer.append("}");
			
			resultBuffer.append(itemBuffer);
			
			
			logger.info("[终端参数设置]数据包："+resultBuffer.toString());
			MqUtil.write(devicetype, resultBuffer.toString());

			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminalInfo.getSimCode());
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33027, "终端参数设置", seq, resultBuffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("33027_"+seq, seq);

			return resultBuffer.toString();
		} catch (Exception e) {
			logger.error("发送终端参数设置编码异常",e);
			return null;
		}
	}
	
	/**
	 * 文本信息查询
	 * @return
	 */
	public static String queryLedMessage(Integer devicetype,String terminal,int seq){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",33541, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",seq,0));
			buffer.append(StringUtils.jsonChar("flag",StringUtils.charStr("11111111"),1));
			buffer.append("}");
			logger.info("[文本信息查询]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			//添加指令下发记录
			Integer carid = ServiceConfig.carService.getCaridByTerminal(terminal);
			ServiceConfig.logService.addCommondLog(carid, SessionUtils.getUserId(), 33541, "文本信息查询", seq, buffer.toString());
			
			//加入至发送缓存中
			ToolsUtil.sendMap.put("33541_"+seq, seq);
			return buffer.toString();
		} catch (Exception e) {
			logger.error("发送文本信息查询数据包编码异常",e);
			return null;
		}
	}
	
	/**
	 * 服务证补发
	 * @return
	 */
	public static boolean reissueServicePhoto(Integer devicetype,String terminal,String drivername,String servicecompany,String drivercode
			,String starlevel,String startext,String version,String picturedata,int index){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			
			buffer.append(StringUtils.jsonChar("msgid",36096, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",getSerialId(),0));
			if(index == 0){
				buffer.append(StringUtils.jsonChar("drivername",StringUtils.charStr(drivername),0));
				buffer.append(StringUtils.jsonChar("servicecompany",StringUtils.charStr(servicecompany),0));
				buffer.append(StringUtils.jsonChar("drivercode",StringUtils.charStr(drivercode),0));
				buffer.append(StringUtils.jsonChar("driverid",StringUtils.charStr("0"),0));
				buffer.append(StringUtils.jsonChar("starlevel",starlevel,0));
				buffer.append(StringUtils.jsonChar("startext",StringUtils.charStr(startext),0));
				buffer.append(StringUtils.jsonChar("format",1,0));
				buffer.append(StringUtils.jsonChar("windowsize",16,0));
				buffer.append(StringUtils.jsonChar("version",StringUtils.charStr(version),0));
			}
			buffer.append(StringUtils.jsonChar("picturedata",StringUtils.charStr(picturedata),1));
			buffer.append("}");

			logger.info("[服务证补发]数据包："+buffer.toString());

			MqUtil.write(devicetype, buffer.toString());
			
			return true;
		} catch (Exception e) {
			logger.error("发送服务证补发数据包编码异常",e);
			return false;
		}
	}
	
	/**
	 * 终端参数设置
	 * @return
	 */
	public static boolean TerParamSet(String terminal,int count,String items){
		
		try {
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",33027, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",getSerialId(),0));
			buffer.append(StringUtils.jsonChar("count",count,0));
			buffer.append(StringUtils.jsonChar("items",items,1));
			buffer.append("}");
			
			logger.info("发送[终端参数设置]数据包："+buffer.toString());
			MqUtil.write(29, buffer.toString());
			return true;
		} catch (Exception e) {
			logger.error("终端参数设置JSON格式数据包编码异常",e);
			return false;
		}
	}
	
	/**
	 * 播放和停止视频协议下发
	 * @return
	 */
	public static boolean playOrStopSend(String terminal,String id,String type){
		
		try {
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",20752, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",getSerialId(),0));
			buffer.append(StringUtils.jsonChar("id",id,0));
			buffer.append(StringUtils.jsonChar("type",type,1));
			buffer.append("}");
			
			logger.info("发送[播放和停止视频协议下发]数据包："+buffer.toString());
			MqUtil.write(29, buffer.toString());
			return true;
		} catch (Exception e) {
			logger.error("播放和停止视频协议下发JSON格式数据包编码异常",e);
			return false;
		}
	}
	
	/**
	 * 回放视频协议下发
	 * @return
	 */
	public static boolean playbackSend(String terminal,String id,String type,String stime, String etime){
		
		try {
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",20753, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",getSerialId(),0));
			buffer.append(StringUtils.jsonChar("id",id,0));
			buffer.append(StringUtils.jsonChar("type",type,0));
			buffer.append(StringUtils.jsonChar("stime",StringUtils.charStr(stime),0));
			buffer.append(StringUtils.jsonChar("etime",StringUtils.charStr(etime),1));
			buffer.append("}");
			
			logger.info("发送[回放视频协议下发]数据包："+buffer.toString());
			MqUtil.write(29, buffer.toString());
			return true;
		} catch (Exception e) {
			logger.error("回放视频协议下发JSON格式数据包编码异常",e);
			return false;
		}
	}
	
	/**
	 * 回放指定文件
	 * @param terminal
	 * @param id
	 * @param filename
	 * @param splaysec
	 * @param eplaysec
	 * @return
	 */
	public static boolean playbackAppoint(String terminal,String id,String filename,String splaysec, String eplaysec){
		
		try {
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",20755, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",getSerialId(),0));
			buffer.append(StringUtils.jsonChar("id",id,0));
			buffer.append(StringUtils.jsonChar("filename",StringUtils.charStr(filename),0));
			buffer.append(StringUtils.jsonChar("splaysec",splaysec,0));
			buffer.append(StringUtils.jsonChar("eplaysec",eplaysec,1));
			buffer.append("}");
			
			logger.info("发送[回放指定文件协议下发]数据包："+buffer.toString());
			MqUtil.write(29, buffer.toString());
			return true;
		} catch (Exception e) {
			logger.error("回放指定文件协议下发JSON格式数据包编码异常",e);
			return false;
		}
	}
	
	/**
	 * 停止视频回放协议下发
	 * @param terminal
	 * @param id
	 * @return
	 */
	public static boolean playbackStop(String terminal,String id){
		
		try {
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",20754, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",getSerialId(),0));
			buffer.append(StringUtils.jsonChar("id",id,1));
			buffer.append("}");
			
			logger.info("发送[停止视频回放协议下发]数据包："+buffer.toString());
			MqUtil.write(29, buffer.toString());
			return true;
		} catch (Exception e) {
			logger.error("停止视频回放协议下发JSON格式数据包编码异常",e);
			return false;
		}
	}
	
}




