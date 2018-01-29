package com.careye.mq.utils;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.careye.constant.Constant;
import com.careye.mq.MqUtil;
import com.careye.utils.StringUtils;

/**
 * @项目名称：TAXISERVER
 * @类名称：HandleUtil
 * @类描述：
 * @创建人：zr
 * @创建时间：2015-3-12 下午07:15:09
 * @修改人：zr
 * @修改时间：2015-3-12 下午07:15:09
 * @修改备注：
 * @version 1.0
 */
public class HandleUtil {
	
	private final static Logger logger = Logger.getLogger(HandleUtil.class);

	
	/**
	 * 终端注册应答
	 * @param terminal 设备号
	 * @param seqR 应答流水
	 * @param result 0：成功；1：车辆已被注册；2：数据库中无该车辆；3：终端已被注册；4：数据库中无该终端
	 * @param passwd
	 * @return
	 */
	public static boolean regeditTerminalResponse(String terminal,int seqR,int result,String passwd,int type){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",33024, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",ToolsUtil.getSerialId(),0));
			buffer.append(StringUtils.jsonChar("seqR",seqR,0));
			if(result == 0){
				buffer.append(StringUtils.jsonChar("result",result,0));
				buffer.append(StringUtils.jsonChar("passwd",StringUtils.charStr(passwd),1));
			}else{
				buffer.append(StringUtils.jsonChar("result",result,1));
			}

			buffer.append("}");

			logger.info("[终端注册应答]数据包："+buffer.toString());
			
			MqUtil.write(Constant.TAXI_DEVICE_TYPE, buffer.toString());
			

			return true;
		} catch (Exception e) {
			logger.error("发送终端注册应答数据包编码异常",e);
			return false;
		}
	}
	
	/**
	 * 报警确认
	 * @param terminal 设备号
	 * @return
	 */
	public static boolean alarmConfirm(String terminal){
		
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",35588, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",ToolsUtil.getSerialId(),1));
			
			buffer.append("}");
			
			logger.info("[报警确认应答]数据包："+buffer.toString());
			
			MqUtil.write(Constant.TAXI_DEVICE_TYPE, buffer.toString());
			
			
			return true;
		} catch (Exception e) {
			logger.error("发送终端注册应答数据包编码异常",e);
			return false;
		}
	}
	
	/**
	 * 多媒体数据上传应答
	 * @param terminal
	 * @param dataId
	 * @param packetSum
	 * @param ids
	 * @return
	 */
	public static boolean multimediaDataResponse(String terminal,int dataId,int packetSum,String ids){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",34816, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",ToolsUtil.getSerialId(),0));
			buffer.append(StringUtils.jsonChar("dataId",dataId,0));
			buffer.append(StringUtils.jsonChar("packetSum",packetSum,0));
			buffer.append(StringUtils.jsonChar("ids",StringUtils.charStr(ids),1));
			buffer.append("}");

			logger.info("[多媒体数据上传应答]数据包："+buffer.toString());

			MqUtil.write(Constant.TAXI_DEVICE_TYPE, buffer.toString());
			
			return true;
		} catch (Exception e) {
			logger.error("发送多媒体数据上传应答数据包编码异常",e);
			return false;
		}
	}
	
	
	/**
	 * 通用应答
	 * @param terminal 设备号
	 * @param respmsgid 应答ID
	 * @param respseq 应答流水号
	 * @param result 结果
	 * @return
	 */
	public static boolean universalResponse(String terminal,int respmsgid,int respseq,int result,int type){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",32769, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",ToolsUtil.getSerialId(),0));
			buffer.append(StringUtils.jsonChar("respseq",respseq,0));
			buffer.append(StringUtils.jsonChar("respmsgid",respmsgid,0));
			buffer.append(StringUtils.jsonChar("result",result,1));
			buffer.append("}");

			logger.info("[通用应答]数据包："+buffer.toString());

			MqUtil.write(Constant.TAXI_DEVICE_TYPE, buffer.toString());
			
			return true;
		} catch (Exception e) {
			logger.error("发送通用应答数据包编码异常",e);
			return false;
		}
	}
	
	/**
	 * 电召抢答通知
	 * @return
	 */
	public static boolean orderAnswerNotice(String orderid,int orderstatus,String carnumber,String terminal,String phone,
			String drivername,String driverphone,String time,Double lng,Double lat){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",20768, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",ToolsUtil.getSerialId(),0));
			buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(orderid),0));
			buffer.append(StringUtils.jsonChar("orderstatus",orderstatus,0));
			buffer.append(StringUtils.jsonChar("carnumber",StringUtils.charStr(carnumber),0));
			buffer.append(StringUtils.jsonChar("phone",StringUtils.charStr(phone),0));
			buffer.append(StringUtils.jsonChar("drivername",StringUtils.charStr(drivername),0));
			buffer.append(StringUtils.jsonChar("driverphone",StringUtils.charStr(driverphone),0));
			buffer.append(StringUtils.jsonChar("time",StringUtils.charStr(time),0));
			buffer.append(StringUtils.jsonChar("lng",new BigDecimal(lng * Math.pow(10, 6)),0));
			buffer.append(StringUtils.jsonChar("lat",new BigDecimal(lat * Math.pow(10, 6)),1));
			buffer.append("}");

			logger.info("[电召抢答通知]数据包："+buffer.toString());

			MqUtil.write(Constant.APP_DEVICE_TYPE, buffer.toString());
			
			return true;
		} catch (Exception e) {
			logger.error("发送电召抢答通知数据包编码异常",e);
			return false;
		}
	}
	
	/**
	 * 订单状态通知
	 * @return
	 */
	public static boolean orderStatusNotice(String orderid,int orderstatus,String terminal){

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",20769, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",ToolsUtil.getSerialId(),0));
			buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(orderid),0));
			buffer.append(StringUtils.jsonChar("orderstatus",orderstatus,1));
			buffer.append("}");

			logger.info("[订单状态通知]数据包："+buffer.toString());

			MqUtil.write(Constant.APP_DEVICE_TYPE, buffer.toString());
			
			return true;
		} catch (Exception e) {
			logger.error("发送订单状态通知数据包编码异常",e);
			return false;
		}
	}
	
	/**
	 * 司机位置通知
	 * @return
	 */
	public static boolean driverPositionNotice(String orderid,int orderstatus,Double lng,Double lat,String terminal){
		
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid",20771, 0));
			buffer.append(StringUtils.jsonChar("subpacket","0", 0));
			buffer.append(StringUtils.jsonChar("encryption","0", 0));
			buffer.append(StringUtils.jsonChar("bodysize","0", 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(terminal),0));
			buffer.append(StringUtils.jsonChar("seq",ToolsUtil.getSerialId(),0));
			buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(orderid),0));
			buffer.append(StringUtils.jsonChar("orderstatus",orderstatus,0));
			buffer.append(StringUtils.jsonChar("lng",new BigDecimal(lng * Math.pow(10, 6)),0));
			buffer.append(StringUtils.jsonChar("lat",new BigDecimal(lat * Math.pow(10, 6)),1));
			buffer.append("}");
			
			logger.info("[司机位置通知]数据包："+buffer.toString());
			
			MqUtil.write(Constant.APP_DEVICE_TYPE, buffer.toString());
			
			return true;
		} catch (Exception e) {
			logger.error("发送司机位置通知数据包编码异常",e);
			return false;
		}
	}
	
	public static void main(String[] args) {
		//{"msgid":34816,"subpacket":0,"encryption":0,"bodysize":0,"terminal":"100100000001","seq":10648,"dataId":527113238,"packetSum":2,"ids":"1,2"}
		multimediaDataResponse("100100000001", 527150813, 3, "1,2,3");
	}
	
	
}




