/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.encoder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.careye.dsparse.bbdomain.Auth;
import com.careye.dsparse.bbdomain.BatteryInfo;
import com.careye.dsparse.bbdomain.CallInfo;
import com.careye.dsparse.bbdomain.CameraCommandResponse;
import com.careye.dsparse.bbdomain.CanTotalBusDataUpload;
import com.careye.dsparse.bbdomain.DataCompressReport;
import com.careye.dsparse.bbdomain.DataTransmission;
import com.careye.dsparse.bbdomain.DriverInfo;
import com.careye.dsparse.bbdomain.ElectronicStorage;
import com.careye.dsparse.bbdomain.ElectronicWaybill;
import com.careye.dsparse.bbdomain.EventSet;
import com.careye.dsparse.bbdomain.InfoDemandMenu;
import com.careye.dsparse.bbdomain.MultiMediaData;
import com.careye.dsparse.bbdomain.MultiMediaEventInfo;
import com.careye.dsparse.bbdomain.PositionDataUpload;
import com.careye.dsparse.bbdomain.PositionInfo;
import com.careye.dsparse.bbdomain.PositionInfoItems;
import com.careye.dsparse.bbdomain.PublicKey;
import com.careye.dsparse.bbdomain.QuestioningAnswer;
import com.careye.dsparse.bbdomain.RegisterInfo;
import com.careye.dsparse.bbdomain.RunDataInfo;
import com.careye.dsparse.bbdomain.SearchTerResponse;
import com.careye.dsparse.bbdomain.SecretKey;
import com.careye.dsparse.bbdomain.ServiceEvaluation;
import com.careye.dsparse.bbdomain.SigninInfo;
import com.careye.dsparse.bbdomain.StorageMultiMediaResponse;
import com.careye.dsparse.bbdomain.TaxiInfo;
import com.careye.dsparse.bbdomain.TaximeterInfo;
import com.careye.dsparse.bbdomain.TerminalDetect;
import com.careye.dsparse.bbdomain.TerminalGeneralRes;
import com.careye.dsparse.bbdomain.TerminalParameter;
import com.careye.dsparse.bbdomain.TerminalUpdate;
import com.careye.dsparse.bbdomain.TerminalUpdateResponse;
import com.careye.dsparse.bbdomain.TerminalUseInfo;
import com.careye.dsparse.bbdomain.TextInfo;
import com.careye.dsparse.bbdomain.TravelingRecorder;
import com.careye.dsparse.constant.BaseInfo;
import com.careye.dsparse.decoder.BbTaxiByteDecoderUtil;
import com.careye.dsparse.utlis.ExceptionUtil;
import com.careye.dsparse.utlis.StringUtils;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：JsonEncoder    
 * 类描述：出租车部标组装json协议    
 * 创建人：zr    
 * 创建时间：2015-5-14 上午09:37:18    
 * 修改人：zr    
 * 修改时间：2015-5-14 上午09:37:18    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BbTaxiJsonEncoder {

	private final static Logger logger = Logger.getLogger(BbTaxiJsonEncoder.class);


	/**
	 * 组装终端通用应答json协议
	 * @return
	 */
	public static String encoderTerminalGeneralResJson(BaseInfo baseInfo,TerminalGeneralRes terminalGeneralRes){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(),0));
			buffer.append(StringUtils.jsonChar("respseq",terminalGeneralRes.getRespseq(),0));
			buffer.append(StringUtils.jsonChar("respmsgid",terminalGeneralRes.getRespmsgid(),0));
			buffer.append(StringUtils.jsonChar("result", terminalGeneralRes.getResult(),1));

			buffer.append("}");
			logger.info("[终端通用应答]json数据包："+buffer.toString());

			return buffer.toString();
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("终端通用应答数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装终端心跳json协议
	 * @return
	 */
	public static String encoderHeartbeatJson(BaseInfo baseInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(),1));
			buffer.append("}");
			logger.info("[终端心跳]json数据包："+buffer.toString());

			return buffer.toString();
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("终端心跳数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装终端注销json协议
	 * @return
	 */
	public static String encoderLogoutJson(BaseInfo baseInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(),1));
			buffer.append("}");
			logger.info("[终端注销]json数据包："+buffer.toString());

			return buffer.toString();
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("终端注销数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装鉴权消息json协议
	 * @return
	 */
	public static String encoderAuthJson(BaseInfo baseInfo,Auth authInfo){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());
			map.put("passwd",authInfo.getPasswd());
			map.put("version",authInfo.getVersion());
			map.put("timestamp",authInfo.getTimestamp());
			map.put("sim",authInfo.getSim());
			map.put("deviceid",authInfo.getDeviceid());
			map.put("reason",authInfo.getReason());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[鉴权消息]json数据包："+jsonStr);

			return jsonStr;


		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("鉴权消息数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装终端注册消息json协议
	 * @return
	 */
	public static String encoderRegisterJson(BaseInfo baseInfo,RegisterInfo registerInfo){

		try {
			StringBuffer buffer = new StringBuffer();
			if(registerInfo!=null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("province", registerInfo.getProvince(), 0));
				buffer.append(StringUtils.jsonChar("city", registerInfo.getCity(), 0));
				buffer.append(StringUtils.jsonChar("oem",StringUtils.charStr(registerInfo.getOem()),0));
				buffer.append(StringUtils.jsonChar("type",StringUtils.charStr(registerInfo.getType()),0));
				buffer.append(StringUtils.jsonChar("mid",StringUtils.charStr(registerInfo.getMid()),0));
				buffer.append(StringUtils.jsonChar("plateColor",registerInfo.getPlatecolor(),0));
				buffer.append(StringUtils.jsonChar("plateCode",StringUtils.charStr(registerInfo.getPlatecode()),1));
				buffer.append("}");
			}
			logger.info("[终端注册]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装终端注册消息json协议数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装终端参数应答json数据
	 * @param baseInfo
	 * @param terminalParameter
	 * @return
	 */
	public static String queryParamAnswer(BaseInfo baseInfo,TerminalParameter terminalParameter){
		try {
			StringBuffer buffer = new StringBuffer();
			if(terminalParameter!=null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("seqR", terminalParameter.getSeqR(), 0));
				buffer.append(StringUtils.jsonChar("count",terminalParameter.getCount(), 0));
				buffer.append("items:[");
				buffer.append(BbByteEncoderUtil.getTerminalParJson(terminalParameter).toString());
				buffer.append("]}");
			}
			logger.info("[终端参数应答]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装终端参数应答json协议数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装查询终端属性应答 json协议
	 * @return
	 */
	public static  String 	encoderSearchTerResponseJson(BaseInfo baseInfo,SearchTerResponse searchTerResponse){
		String jsonStr=null;
		try {
			if(searchTerResponse != null){

				Map <String,Object>  map = new LinkedHashMap<String,Object>();
				map.put("msgid", baseInfo.getMsgid());
				map.put("totalpacket", baseInfo.getTotalpacket());
				map.put("subpacket", baseInfo.getSubpacket());
				map.put("encryption", baseInfo.getEncryption());
				map.put("bodysize", baseInfo.getBodysize());
				map.put("terminal", baseInfo.getTerminal());
				map.put("seq", baseInfo.getSeq());
				map.put("type", searchTerResponse.getType());
				map.put("markerid", searchTerResponse.getMarkerid());
				map.put("termainaltype", searchTerResponse.getTermainaltype());
				map.put("termainalid", searchTerResponse.getTermainalid());
				map.put("iccid", searchTerResponse.getIccid());
				map.put("hardwarelen", searchTerResponse.getHardwarelen());
				map.put("hardwareno", searchTerResponse.getHardwareno());
				map.put("firmwarelen", searchTerResponse.getFirmwarelen());
				map.put("firmwareno", searchTerResponse.getFirmwareno());
				map.put("cnssproperty", searchTerResponse.getCnssproperty());
				map.put("comproperty", searchTerResponse.getComproperty());

				jsonStr=JSON.toJSONString(map,false);
				logger.info("[查询终端属性应答 ]json数据包："+jsonStr);
			}
			return jsonStr;
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("查询终端属性应答  数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装终端升级结果通知消息json协议
	 * @return
	 */
	public static String encoderTerUpdateResJson(BaseInfo baseInfo,TerminalUpdateResponse terminalUpdateResponse){

		try {
			StringBuffer buffer = new StringBuffer();
			if(terminalUpdateResponse!=null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("type", terminalUpdateResponse.getType(), 0));
				buffer.append(StringUtils.jsonChar("result",terminalUpdateResponse.getResult(),1));
				buffer.append("}");
			}
			logger.info("[终端升级结果通知]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装终端升级结果通知消息json协议数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装终端位置信息汇报json协议
	 * @param baseInfo
	 * @param positionInfo
	 * @param type 1 位置信息汇报 2 位置信息查询应答
	 * @return
	 */
	public static String encoderPositionInfoJson(BaseInfo baseInfo,PositionInfo positionInfo,int type){

		try {

			StringBuffer buffer = new StringBuffer();
			if(positionInfo != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));

				if(type == 2){
					buffer.append(StringUtils.jsonChar("seqR", positionInfo.getSeqR(), 0));
				}

				String alarminfo = positionInfo.getAlarminfo();
				if(alarminfo != null || !"".equals(alarminfo)){
					buffer.append(alarminfo);
				}

				String stateinfo = positionInfo.getStateinfo();
				if(stateinfo != null || !"".equals(stateinfo)){
					buffer.append(stateinfo);
				}

				buffer.append(StringUtils.jsonChar("lat", positionInfo.getLat(), 0));
				buffer.append(StringUtils.jsonChar("lng", positionInfo.getLng(), 0));
				buffer.append(StringUtils.jsonChar("altitude",positionInfo.getAltitude(),0));
				buffer.append(StringUtils.jsonChar("speed",positionInfo.getSpeed(),0));
				buffer.append(StringUtils.jsonChar("direction",positionInfo.getDirection(),0));

				if(positionInfo.getMileage() != null){
					buffer.append(StringUtils.jsonChar("mileage",positionInfo.getMileage(),0));
				}

				List<PositionInfoItems> list = positionInfo.getItems();
				if(list.size() > 0){
					buffer.append(StringUtils.jsonChar("time",StringUtils.charStr(positionInfo.getTime()),0));
				}else{
					buffer.append(StringUtils.jsonChar("time",StringUtils.charStr(positionInfo.getTime()),1));
				}
				//附加信息
				if(list.size() > 0){
					buffer.append(StringUtils.jsonChar("acount",positionInfo.getItems().size(), 0));
					buffer.append("items:[");
					int size = list.size();
					for (int i = 0; i < size; i++) {
						PositionInfoItems positionInfoItems = list.get(i);
						buffer.append("{");
						buffer.append(StringUtils.jsonChar("aid",positionInfoItems.getAid(),0));
						buffer.append(StringUtils.jsonChar("alen",positionInfoItems.getAlen(),0));
						buffer.append(StringUtils.jsonChar("avalue",StringUtils.charStr(positionInfoItems.getAvalue()),1));

						if(i == (size-1)){
							buffer.append("}");
						}else{
							buffer.append("},");
						}
					}
					buffer.append("]");
				}
				buffer.append("}");

			}

			logger.info("[终端位置信息汇报]json数据包："+buffer.toString());

			return buffer.toString();
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装终端位置信息汇报数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 组装行驶记录数据上传JSON
	 * @param baseInfo
	 * @param callInfo
	 * @return
	 */
	public static String encoderTravelingRecorderJson(BaseInfo baseInfo,TravelingRecorder travelingRecorder){
		try {
			StringBuffer buffer = new StringBuffer();
			if(travelingRecorder != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("cmd", travelingRecorder.getCmd(), 0));
				buffer.append(StringUtils.jsonChar("data",StringUtils.charStr(travelingRecorder.getData()), 1));
				buffer.append("}");
			}
			logger.info("[行驶记录数据上传]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装行驶记录数据上传数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装电召抢答
	 * @param baseInfo
	 * @param callInfo
	 * @return
	 */
	public static String encoderAnswerCallJson(BaseInfo baseInfo,CallInfo callInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			if(callInfo != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(callInfo.getOrderid()), 1));
				buffer.append("}");
			}
			logger.info("[电召抢答]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装电召抢答协议数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装事件报告
	 * @param baseInfo
	 * @param id
	 * @return
	 */
	public static String encoderEventReportJson(BaseInfo baseInfo,int id){
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
			buffer.append(StringUtils.jsonChar("id",id, 1));
			buffer.append("}");
			logger.info("[事件报告]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装事件报告数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装提问应答 json协议
	 * @return
	 */
	public static String encoderDataQuestioningAnswerJson(BaseInfo baseInfo,QuestioningAnswer questioningAnswer){
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
			buffer.append(StringUtils.jsonChar("seqR",questioningAnswer.getSeqR(), 0));
			buffer.append(StringUtils.jsonChar("answer",questioningAnswer.getAnswer(), 1));
			buffer.append("}");

			logger.info("[提问应答]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装提问应答数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 组装电子运单 json协议
	 * @return
	 */
	public static String encoderElectronicWaybillJson(BaseInfo baseInfo,ElectronicWaybill electronicWaybill){
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
			buffer.append(StringUtils.jsonChar("len",electronicWaybill.getLen(), 0));
			buffer.append(StringUtils.jsonChar("content",StringUtils.charStr(electronicWaybill.getContent()), 1));
			buffer.append("}");

			logger.info("[电子运单]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装电子运单数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 组装驾驶员身份信息采集上报json协议
	 * @return
	 */
	public static String encoderDriverInfoJson(BaseInfo baseInfo,DriverInfo driverInfo){
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
			buffer.append(StringUtils.jsonChar("state",driverInfo.getState(), 0));
			buffer.append(StringUtils.jsonChar("time",StringUtils.charStr(driverInfo.getTime()), 0));
			buffer.append(StringUtils.jsonChar("result",driverInfo.getResult(), 0));
			buffer.append(StringUtils.jsonChar("namelen",driverInfo.getNamelen(), 0));
			buffer.append(StringUtils.jsonChar("name",StringUtils.charStr(driverInfo.getName()), 0));
			buffer.append(StringUtils.jsonChar("qc",StringUtils.charStr(driverInfo.getQc()), 0));
			buffer.append(StringUtils.jsonChar("orglen",driverInfo.getOrglen(), 0));
			buffer.append(StringUtils.jsonChar("org",StringUtils.charStr(driverInfo.getOrg()), 0));
			buffer.append(StringUtils.jsonChar("effectivetime",StringUtils.charStr(driverInfo.getEffectivetime()), 1));
			buffer.append("}");

			logger.info("[驾驶员身份信息采集上报]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装驾驶员身份信息采集上报数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装定位数据批量上传json协议
	 * @return
	 */
	public static String encoderPdUploadJson(BaseInfo baseInfo,PositionDataUpload pdUpload){

		String jsonStr=null;
		try {
			if(pdUpload != null){

				Map <Object,Object>  map=new LinkedHashMap<Object,Object> ();
				map.put("msgid", baseInfo.getMsgid());
				map.put("totalpacket", baseInfo.getTotalpacket());
				map.put("subpacket", baseInfo.getSubpacket());
				map.put("encryption", baseInfo.getEncryption());
				map.put("bodysize", baseInfo.getBodysize());
				map.put("terminal", baseInfo.getTerminal());
				map.put("seq", baseInfo.getSeq());
				map.put("count",pdUpload.getCount());
				map.put("type",pdUpload.getType());
				map.put("items",pdUpload.getItems());

				jsonStr = JSON.toJSONString(map,false);
				logger.info("[定位数据批量上传]json数据包："+jsonStr);
			}
			return jsonStr;
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("定位数据批量上传数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 组装CAN 总线数据上传json协议
	 * @return
	 */
	public static String encoderCtbdUploadJson(BaseInfo baseInfo,CanTotalBusDataUpload ctbdUpload){
		String jsonStr=null;
		try {
			if(ctbdUpload != null){

				Map <Object,Object>  map=new LinkedHashMap<Object,Object> ();
				map.put("msgid", baseInfo.getMsgid());
				map.put("totalpacket", baseInfo.getTotalpacket());
				map.put("subpacket", baseInfo.getSubpacket());
				map.put("encryption", baseInfo.getEncryption());
				map.put("bodysize", baseInfo.getBodysize());
				map.put("terminal", baseInfo.getTerminal());
				map.put("seq", baseInfo.getSeq());
				map.put("count", ctbdUpload.getCount());
				map.put("receivedate",ctbdUpload.getReceivedate());
				map.put("items", ctbdUpload.getItems());

				jsonStr=JSON.toJSONString(map,false);
				logger.info("[CAN总线数据上传]json数据包："+jsonStr);

			}
			return jsonStr;
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("CAN总线数据上传数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装 多媒体事件信息上传json协议
	 * @return
	 */
	public static String encoderMmeInfoJson(BaseInfo baseInfo,MultiMediaEventInfo mmEventInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			if(mmEventInfo != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("dataId",mmEventInfo.getDataId(), 0));
				buffer.append(StringUtils.jsonChar("mediaType",mmEventInfo.getMediaType(), 0));
				buffer.append(StringUtils.jsonChar("format",mmEventInfo.getFormat(), 0));
				buffer.append(StringUtils.jsonChar("eventId",mmEventInfo.getEventId(), 0));
				buffer.append(StringUtils.jsonChar("wayId",mmEventInfo.getWayId(), 1));
				buffer.append("}");
				logger.info("[多媒体事件信息上传]json数据包："+buffer.toString());
			}
			return buffer.toString();
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("多媒体事件信息上传数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装 多媒体数据上传json协议
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String encoderMultiMediaDataJson(BaseInfo baseInfo,MultiMediaData multiMediaData){
		String jsonStr=null;
		try {
			if(multiMediaData != null){

				Map <String,Object>  map=new LinkedHashMap<String,Object> ();
				map.put("msgid", baseInfo.getMsgid());
				map.put("totalpacket", baseInfo.getTotalpacket());
				map.put("subpacket", baseInfo.getSubpacket());
				map.put("encryption", baseInfo.getEncryption());
				map.put("bodysize", baseInfo.getBodysize());
				map.put("terminal", baseInfo.getTerminal());
				map.put("seq", baseInfo.getSeq());
				map.put("dataId", multiMediaData.getDataId());

				if(baseInfo.getSubpacket() == 1){
					map = BbTaxiByteDecoderUtil.setAlarmStateMap(multiMediaData.getPositionInfo().getAlarminfo(), map);
					map = BbTaxiByteDecoderUtil.setStateMap(multiMediaData.getPositionInfo().getStateinfo(), map);
					map.put("lat", multiMediaData.getPositionInfo().getLat());
					map.put("lng", multiMediaData.getPositionInfo().getLng());
					map.put("altitude", multiMediaData.getPositionInfo().getAltitude());
					map.put("speed", multiMediaData.getPositionInfo().getSpeed());
					map.put("direction", multiMediaData.getPositionInfo().getDirection());
					map.put("time", multiMediaData.getPositionInfo().getTime());
					map.put("mediaType",multiMediaData.getMediaType());
					map.put("format", multiMediaData.getFormat());
					map.put("eventId", multiMediaData.getEventId());
					map.put("wayId", multiMediaData.getWayId());
				}
				map.put("packetSum", multiMediaData.getPacketSum());
				map.put("packetId", multiMediaData.getPacketId());
				map.put("mediaData", multiMediaData.getMediaData());

				jsonStr=JSON.toJSONString(map,false);
				logger.info("[多媒体数据上传]json数据包："+jsonStr);
			}
			return jsonStr;
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("多媒体数据上传数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装 存储多媒体数据检索应答 json协议
	 * @return
	 */
	public static String encoderSmmResponseJson(BaseInfo baseInfo,StorageMultiMediaResponse smmResponse){
		String jsonStr=null;
		try {
			if(smmResponse != null){

				Map <String,Object>  map=new LinkedHashMap<String,Object> ();
				map.put("msgid", baseInfo.getMsgid());
				map.put("totalpacket", baseInfo.getTotalpacket());
				map.put("subpacket", baseInfo.getSubpacket());
				map.put("encryption", baseInfo.getEncryption());
				map.put("bodysize", baseInfo.getBodysize());
				map.put("terminal", baseInfo.getTerminal());
				map.put("seq", baseInfo.getSeq());
				map.put("seqR", smmResponse.getSeqR());
				map.put("count", smmResponse.getCount());
				map.put("items", smmResponse.getItems());
				jsonStr = JSON.toJSONString(map,false);

				logger.info("[存储多媒体数据检索应答 ]json数据包："+jsonStr);
			}
			return jsonStr;
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("存储多媒体数据检索应答 数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装 摄像头立即拍摄命令应答 json协议
	 * @return
	 */
	public static String encoderCcResponseJson(BaseInfo baseInfo,CameraCommandResponse ccResponse){
		String jsonStr=null;
		try {
			if(ccResponse != null){

				Map <Object,Object>  map=new LinkedHashMap<Object,Object> ();
				map.put("msgid", baseInfo.getMsgid());
				map.put("totalpacket", baseInfo.getTotalpacket());
				map.put("subpacket", baseInfo.getSubpacket());
				map.put("encryption", baseInfo.getEncryption());
				map.put("bodysize", baseInfo.getBodysize());
				map.put("terminal", baseInfo.getTerminal());
				map.put("seq", baseInfo.getSeq());

				map.put("seqR", ccResponse.getSeqR());
				map.put("result", ccResponse.getResult());
				map.put("count", ccResponse.getCount());
				map.put("items", ccResponse.getList());
				jsonStr=JSON.toJSONString(map,false);
				logger.info("[摄像头立即拍摄命令应答 ]json数据包："+jsonStr);
			}
			return jsonStr;
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("摄像头立即拍摄命令应答 数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装 数据上行透传 json协议
	 * @return
	 */
	public static String encoderDataTransmissionJson(BaseInfo baseInfo,DataTransmission dataTransmission){
		String jsonStr=null;
		try {
			if(dataTransmission != null){

				StringBuffer buffer = new StringBuffer();
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("type",dataTransmission.getType(), 0));
				buffer.append(StringUtils.jsonChar("mid",dataTransmission.getMid(), 0));
				buffer.append(StringUtils.jsonChar("data",StringUtils.charStr(dataTransmission.getData()),1));
				buffer.append("}");
				jsonStr = buffer.toString();
				logger.info("[ 数据上行透传 ]json数据包："+jsonStr);
			}
			return jsonStr;
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error(" 数据上行透传   数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装数据压缩上报 json协议
	 * @return
	 */
	public static String encoderDataCompressReportJson(BaseInfo baseInfo,DataCompressReport dataCompressReport){
		String jsonStr=null;
		try {
			if(dataCompressReport != null){

				StringBuffer buffer = new StringBuffer();
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("len",dataCompressReport.getLen(), 0));
				buffer.append(StringUtils.jsonChar("data",StringUtils.charStr(dataCompressReport.getData()),1));
				buffer.append("}");
				jsonStr = buffer.toString();
				logger.info("[ 数据压缩上报 ]json数据包："+jsonStr);
			}
			return jsonStr;
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error(" 数据压缩上报   数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装终端RSA公钥json协议
	 * @return
	 */
	public static String encoderPublicKeyJson(BaseInfo baseInfo,PublicKey publicKey){
		String jsonStr=null;
		try {
			if(publicKey != null){

				StringBuffer buffer = new StringBuffer();
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("e",publicKey.getE(), 0));
				buffer.append(StringUtils.jsonChar("n",StringUtils.charStr(publicKey.getN()),1));
				buffer.append("}");
				jsonStr = buffer.toString();
				logger.info("[ 终端RSA公钥 ]json数据包："+jsonStr);
			}
			return jsonStr;
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("终端RSA公钥数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 组装信息点播/取消 json协议
	 * @param baseInfo
	 * @param infoDemandMenu
	 * @return
	 */
	public static String encoderInfoDemandMenuCancelJson(BaseInfo baseInfo,InfoDemandMenu infoDemandMenu){

		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
			buffer.append(StringUtils.jsonChar("type",infoDemandMenu.getType(), 0));
			buffer.append(StringUtils.jsonChar("demand",infoDemandMenu.getDemand(), 1));
			buffer.append("}");

			logger.info("[信息点播/取消]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装信息点播/取消数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 组装电召中心处理结果应答
	 * @param baseInfo
	 * @param callInfo
	 * @return
	 */
	public static String encoderCentralProcessingResponseJson(BaseInfo baseInfo,CallInfo callInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			if(callInfo != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(callInfo.getOrderid()), 0));
				buffer.append(StringUtils.jsonChar("result", callInfo.getResult(),1));
				buffer.append("}");
			}
			logger.info("[电召中心处理结果应答]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装电召中心处理结果应答数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装执行电召数据包
	 * @param baseInfo
	 * @param callInfo
	 * @return
	 */
	public static String encoderPerformCallJson(BaseInfo baseInfo,CallInfo callInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			if(callInfo != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(callInfo.getOrderid()), 0));
				buffer.append(StringUtils.jsonChar("callfee",StringUtils.charStr(callInfo.getCallfee()), 0));
				buffer.append(StringUtils.jsonChar("carnumber",StringUtils.charStr(callInfo.getCarnumber()), 0));
				buffer.append(StringUtils.jsonChar("result", callInfo.getResult(),1));
				buffer.append("}");
			}
			logger.info("[执行电召]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装执行电召数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 组装计价器开钥匙门信息
	 * @param baseInfo
	 * @param taximeterInfo
	 * @return
	 */
	public static String encoderTaximeterJson(BaseInfo baseInfo,TaximeterInfo taximeterInfo){

		try {
			StringBuffer buffer = new StringBuffer();
			if(taximeterInfo!=null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("carnumber",StringUtils.charStr(taximeterInfo.getCarnum()), 0));
				buffer.append(StringUtils.jsonChar("psam",StringUtils.charStr(taximeterInfo.getPsam()), 0));
				buffer.append(StringUtils.jsonChar("taximeterno",taximeterInfo.getTaximeterno(),0));
				buffer.append(StringUtils.jsonChar("driverno",taximeterInfo.getDriverno(),0));
				buffer.append(StringUtils.jsonChar("time",StringUtils.charStr(taximeterInfo.getTime()), 0));
				buffer.append(StringUtils.jsonChar("blacklistver",taximeterInfo.getBlacklistver(),0));
				buffer.append(StringUtils.jsonChar("softever",taximeterInfo.getSoftever(),1));
				buffer.append("}");
			}
			logger.info("[计价器开钥匙门信息]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装计价器开钥匙门信息协议数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装服务评价上报
	 * @param baseInfo
	 * @param serviceEvaluation
	 * @return
	 */
	public static String encoderServiceEvaluation(BaseInfo baseInfo,ServiceEvaluation serviceEvaluation){

		try {
			StringBuffer buffer = new StringBuffer();
			if(serviceEvaluation!=null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("result",serviceEvaluation.getResult(),0));
				buffer.append(StringUtils.jsonChar("reason",serviceEvaluation.getReason(),1));
				buffer.append("}");
			}
			logger.info("[服务评价]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装服务评价协议数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 组装上报终端功能使用次数
	 * @param baseInfo
	 * @param terminalUseInfo
	 * @return
	 */
	public static String encoderTerminalUse(BaseInfo baseInfo,TerminalUseInfo terminalUseInfo){

		try {
			StringBuffer buffer = new StringBuffer();
			if(terminalUseInfo!=null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("number",StringUtils.charStr(terminalUseInfo.getNumber()),1));
				buffer.append("}");
			}
			logger.info("[上报终端功能使用次数]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装上报终端功能使用次数协议数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 组装日使用上报
	 * @param baseInfo
	 * @param terminalUseInfo
	 * @return
	 */
	public static String encoderTerminalDayUse(BaseInfo baseInfo,TerminalUseInfo terminalUseInfo){

		try {
			StringBuffer buffer = new StringBuffer();
			if(terminalUseInfo!=null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));

				buffer.append(StringUtils.jsonChar("time",StringUtils.charStr(terminalUseInfo.getTime()),0));
				buffer.append(StringUtils.jsonChar("type", terminalUseInfo.getType(), 0));
				buffer.append(StringUtils.jsonChar("number",terminalUseInfo.getNum(), 0));
				buffer.append(StringUtils.jsonChar("usinglen", terminalUseInfo.getUsinglen(), 0));
				buffer.append(StringUtils.jsonChar("flow",terminalUseInfo.getFlow(), 1));
				buffer.append("}");
			}
			logger.info("[日使用上报]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装日使用上报协议数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 组装电池信息
	 * @param baseInfo
	 * @param batteryInfo
	 * @return
	 */
	public static String encoderBatteryInfo(BaseInfo baseInfo,BatteryInfo batteryInfo){

		try {
			StringBuffer buffer = new StringBuffer();
			if(batteryInfo!=null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("voltage",batteryInfo.getVoltage(), 1));
				buffer.append("}");
			}
			logger.info("[电池信息上报]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装电池信息上报协议数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装订单抢答
	 * @param baseInfo
	 * @param callInfo
	 * @return
	 */
	public static String encoderZjAnswerCallJson(BaseInfo baseInfo,CallInfo callInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			if(callInfo != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(callInfo.getOrderid()),0));
				buffer.append(StringUtils.jsonChar("time",StringUtils.charStr(callInfo.getTime()), 1));
				buffer.append("}");
			}
			logger.info("[订单抢答]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装订单抢答协议数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 组装订单接受
	 * @param baseInfo
	 * @param callInfo
	 * @return
	 */
	public static String encoderOrderAcceptanceJson(BaseInfo baseInfo,CallInfo callInfo){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());
			map.put("orderid",callInfo.getOrderid());
			map.put("result",callInfo.getResult());
			map.put("time",callInfo.getTime());
			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[订单接受]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装订单接受数据包编码异常",e);
			return null;
		}
	}
	
	/**
	 * 回放文件列表
	 * @param baseInfo
	 * @param taxiInfo
	 * @return
	 */
	public static String encoderVideoPlaybackListJson(BaseInfo baseInfo,TaxiInfo taxiInfo){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", taxiInfo.getTotalpacket());
			map.put("subpacket", taxiInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());
			map.put("id",taxiInfo.getId());
			map.put("count",taxiInfo.getItems().size());
			map.put("items",taxiInfo.getItems());
			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[回放文件列表]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装回放文件列表数据包编码异常",e);
			return null;
		}
	}
	
	/**
	 * 回放结束
	 * @param baseInfo
	 * @param taxiInfo
	 * @return
	 */
	public static String encoderVideoPlaybackEndJson(BaseInfo baseInfo,TaxiInfo taxiInfo){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());
			map.put("id",taxiInfo.getId());
			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[回放结束]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装回放结束数据包编码异常",e);
			return null;
		}
	}
	
	/**
	 * 组装订单完成
	 * @param baseInfo
	 * @param callInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String encoderOrderFulfillmentJson(BaseInfo baseInfo,CallInfo callInfo){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			map.put("orderid",callInfo.getOrderid());
			map.put("completiontime",callInfo.getCompletiontime());
			map.put("number",callInfo.getNumber());
			map.put("result",callInfo.getResult());

			PositionInfo positionInfo = callInfo.getPositionInfo();
			if(positionInfo != null){
				//位置信息
				map = BbTaxiByteDecoderUtil.setAlarmStateMap(positionInfo.getAlarminfo(), map);
				map = BbTaxiByteDecoderUtil.setStateMap(positionInfo.getStateinfo(), map);
				map.put("lat",positionInfo.getLat());
				map.put("lng",positionInfo.getLng());
				map.put("altitude",positionInfo.getAltitude());
				map.put("speed",positionInfo.getSpeed());
				map.put("direction",positionInfo.getDirection());
				map.put("time",positionInfo.getTime());
			}
			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[订单完成]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装订单完成数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装订单状态
	 * @param baseInfo
	 * @param callInfo
	 * @return
	 */
	public static String encoderOrderStatusJson(BaseInfo baseInfo,CallInfo callInfo){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			map.put("orderid",callInfo.getOrderid());
			map.put("orderstatus",callInfo.getOrderstatus());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[订单状态]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装订单状态数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装司机取消订单
	 * @param baseInfo
	 * @param callInfo
	 * @return
	 */
	public static String encoderDriverCancelOrderJson(BaseInfo baseInfo,CallInfo callInfo){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			map.put("orderid",callInfo.getOrderid());
			map.put("reason",callInfo.getReason());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[司机取消订单]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装司机取消订单数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装司机评价乘客
	 * @param baseInfo
	 * @param callInfo
	 * @return
	 */
	public static String encoderDriverEvaluationJson(BaseInfo baseInfo,CallInfo callInfo){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			map.put("orderid",callInfo.getOrderid());
			map.put("level",callInfo.getLevel());
			map.put("content",callInfo.getContent());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[司机评价乘客]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装司机评价乘客数据包编码异常",e);
			return null;
		}
	}
	/**
	 * 上班签到
	 * @param baseInfo
	 * @param signinInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String encoderSignIntoWorkJson(BaseInfo baseInfo,SigninInfo signinInfo){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			//位置信息
			map = BbTaxiByteDecoderUtil.setAlarmStateMap(signinInfo.getPositionInfo().getAlarminfo(), map);
			map = BbTaxiByteDecoderUtil.setStateMap(signinInfo.getPositionInfo().getStateinfo(), map);
			map.put("lat", signinInfo.getPositionInfo().getLat());
			map.put("lng", signinInfo.getPositionInfo().getLng());
			map.put("altitude", signinInfo.getPositionInfo().getAltitude());
			map.put("speed", signinInfo.getPositionInfo().getSpeed());
			map.put("direction", signinInfo.getPositionInfo().getDirection());
			map.put("time", signinInfo.getPositionInfo().getTime());

			//签到信息
			map.put("blnumber", signinInfo.getBlnumber());
			map.put("driverid", signinInfo.getDriverid());
			map.put("carnumber", signinInfo.getCarnumber());
			map.put("signintime", signinInfo.getSignintime());
			map.put("count", signinInfo.getCount());
			map.put("result", signinInfo.getResult());
			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[上班签到]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装上班签到数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 下班签退
	 * @param baseInfo
	 * @param signinInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String encoderSignBackFromWorkJson(BaseInfo baseInfo,SigninInfo signinInfo){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			//位置信息
			map = BbTaxiByteDecoderUtil.setAlarmStateMap(signinInfo.getPositionInfo().getAlarminfo(), map);
			map = BbTaxiByteDecoderUtil.setStateMap(signinInfo.getPositionInfo().getStateinfo(), map);
			map.put("lat", signinInfo.getPositionInfo().getLat());
			map.put("lng", signinInfo.getPositionInfo().getLng());
			map.put("altitude", signinInfo.getPositionInfo().getAltitude());
			map.put("speed", signinInfo.getPositionInfo().getSpeed());
			map.put("direction", signinInfo.getPositionInfo().getDirection());
			map.put("time", signinInfo.getPositionInfo().getTime());

			//签退信息
			map.put("blnumber", signinInfo.getBlnumber());
			map.put("driverid", signinInfo.getDriverid());
			map.put("carnumber", signinInfo.getCarnumber());
			map.put("mcs", signinInfo.getMcs());
			map.put("stime", signinInfo.getStime());
			map.put("etime", signinInfo.getEtime());
			map.put("dbmileage", signinInfo.getDbmileage());
			map.put("dbyymileage", signinInfo.getDbyymileage());
			map.put("vehicletrips", signinInfo.getVehicletrips());
			map.put("jstmie", signinInfo.getJstmie());
			map.put("totalamount", signinInfo.getTotalamount());
			map.put("cardamount", signinInfo.getCardamount());
			map.put("cardnum", signinInfo.getCardnum());
			map.put("bjmileage", signinInfo.getBjmileage());
			map.put("totalmileage", signinInfo.getTotalmileage());
			map.put("totalyymileage", signinInfo.getTotalyymileage());
			map.put("price", signinInfo.getPrice());
			map.put("totalnumber", signinInfo.getTotalnumber());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[下班签退]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装下班签退数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 营运数据上传
	 * @param baseInfo
	 * @param runDataInfo
	 * @return
	 */
	public static String encoderRunDataUploadJson(BaseInfo baseInfo,RunDataInfo runDataInfo){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			//空转重时位置基本信息
			map.put("kalarminfo", runDataInfo.getKpositionInfo().getAlarminfo());
			map.put("kstateinfo", runDataInfo.getKpositionInfo().getStateinfo());
			map.put("klat", runDataInfo.getKpositionInfo().getLat());
			map.put("klng", runDataInfo.getKpositionInfo().getLng());
			map.put("kaltitude", runDataInfo.getKpositionInfo().getAltitude());
			map.put("kspeed", runDataInfo.getKpositionInfo().getSpeed());
			map.put("kdirection", runDataInfo.getKpositionInfo().getDirection());
			map.put("ktime", runDataInfo.getKpositionInfo().getTime());

			//重转空时位置基本信息
			map.put("zalarminfo", runDataInfo.getZpositionInfo().getAlarminfo());
			map.put("zstateinfo", runDataInfo.getZpositionInfo().getStateinfo());
			map.put("zlat", runDataInfo.getZpositionInfo().getLat());
			map.put("zlng", runDataInfo.getZpositionInfo().getLng());
			map.put("zaltitude", runDataInfo.getZpositionInfo().getAltitude());
			map.put("zspeed", runDataInfo.getZpositionInfo().getSpeed());
			map.put("zdirection", runDataInfo.getZpositionInfo().getDirection());
			map.put("ztime", runDataInfo.getZpositionInfo().getTime());

			map.put("runid", runDataInfo.getRunid());
			map.put("number", runDataInfo.getNumber());
			map.put("evaluateid", runDataInfo.getEvaluateid());
			map.put("options", runDataInfo.getOptions());
			map.put("extend", runDataInfo.getExtend());

			//计价器营运数据
			map.put("carnumber", runDataInfo.getCarnumber());
			map.put("blnumber", runDataInfo.getBlnumber());
			map.put("driverid", runDataInfo.getDriverid());
			map.put("stime", runDataInfo.getStime());
			map.put("etime", runDataInfo.getEtime());
			map.put("mileage", runDataInfo.getMileage());
			map.put("airmileage", runDataInfo.getAirmileage());
			map.put("fuelsurcharge", runDataInfo.getFuelsurcharge());
			map.put("waitingtime", runDataInfo.getWaitingtime());
			map.put("tradeamount", runDataInfo.getTradeamount());
			map.put("vehicletrips", runDataInfo.getVehicletrips());
			map.put("tradetype", runDataInfo.getTradetype());
			map.put("data", runDataInfo.getDatahex());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[营运数据上传]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装营运数据上传数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 电子服务证书请求
	 * @param baseInfo
	 * @param electronicStorage
	 * @return
	 */
	public static String encoderElectronicStorageJson(BaseInfo baseInfo,ElectronicStorage electronicStorage){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			map.put("drivercode",electronicStorage.getDrivercode());
			map.put("driverid", electronicStorage.getDriverid());
			map.put("version", electronicStorage.getVersion());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[电子服务证书请求]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装电子服务证书请求数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 校时请求
	 * @param baseInfo
	 * @param terGeneralRes
	 * @return
	 */
	public static String encoderTimeRequestJson(BaseInfo baseInfo,TerminalGeneralRes terGeneralRes){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			map.put("time",terGeneralRes.getTime());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[校时请求]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装校时请求数据包编码异常",e);
			return null;
		}
	}

	/**
	 *  密钥更新请求
	 * @param baseInfo
	 * @param secretKey
	 * @return
	 */
	public static String encoderSecretKeyUpateRequestJson(BaseInfo baseInfo,SecretKey secretKey){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			map.put("keytype",secretKey.getKeytype());
			map.put("uuid",secretKey.getUuid());
			map.put("random",secretKey.getRandom());
			map.put("sno",secretKey.getSno());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[ 密钥更新请求]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装 密钥更新请求数据包编码异常",e);
			return null;
		}
	}


	/**
	 *  密钥更新结果
	 * @param baseInfo
	 * @param secretKey
	 * @return
	 */
	public static String encoderSecretKeyUpdateResultJson(BaseInfo baseInfo,SecretKey secretKey){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			map.put("result",secretKey.getResult());
			map.put("keytype",secretKey.getKeytype());
			map.put("version",secretKey.getVersion());
			map.put("sno",secretKey.getSno());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[密钥更新结果]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装密钥更新结果数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 终端事件通知
	 * @param baseInfo
	 * @param event
	 * @return
	 */
	public static String encoderTerEventNoticeJson(BaseInfo baseInfo,EventSet event){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			map.put("eventtype",event.getEventtype());
			map.put("reason",event.getReason());
			map.put("time",event.getTime());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[终端事件通知]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装终端事件通知数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 文本信息应答
	 * @param baseInfo
	 * @param textInfo
	 * @return
	 */
	public static String encoderTextResponseJson(BaseInfo baseInfo,TextInfo textInfo){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			map.put("flag",textInfo.getFlag());
			map.put("textseq",textInfo.getTextseq());
			map.put("textdata",textInfo.getTextdata());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[文本信息应答]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装文本信息应答数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 升级结果报告
	 * @param baseInfo
	 * @param terminalUpdate
	 * @return
	 */
	public static String encoderUpdateResultJson(BaseInfo baseInfo,TerminalUpdate terminalUpdate){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			map.put("devicetype",terminalUpdate.getDevicetype());
			map.put("oem",terminalUpdate.getOem());
			map.put("dversion",terminalUpdate.getDversion());
			map.put("sversion",terminalUpdate.getSversion());
			map.put("result",terminalUpdate.getResult());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[升级结果报告]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装升级结果报告数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 终端自检报告
	 * @param baseInfo
	 * @param terminalDetect
	 * @return
	 */
	public static String encoderTerminalDetectJson(BaseInfo baseInfo,TerminalDetect terminalDetect){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			map.put("time",terminalDetect.getTime());
			map.put("count",terminalDetect.getCount());
			map.put("items",terminalDetect.getItems());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[终端自检报告]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装终端自检报告数据包编码异常",e);
			return null;
		}
	}
	/**
	 * 编码服务评价
	 * @param baseInfo
	 * @param callInfo
	 * @return
	 */
	public static String encoderZjEvaluationJson(BaseInfo baseInfo,CallInfo callInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			if(callInfo != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("evaluationtime",StringUtils.charStr(callInfo.getEvaluationtime()), 0));
				buffer.append(StringUtils.jsonChar("sqcn",StringUtils.charStr(callInfo.getSqcn()), 0));
				buffer.append(StringUtils.jsonChar("drivercode",StringUtils.charStr(callInfo.getDrivercode()), 0));
				buffer.append(StringUtils.jsonChar("econtent", callInfo.getEcontent(), 1));
				buffer.append("}");
			}
			logger.info("[编码服务评价]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装编码服务评价数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 编码乘客人数上报
	 * @param baseInfo
	 * @param callInfo
	 * @return
	 */
	public static String encoderNumberReportedJson(BaseInfo baseInfo,CallInfo callInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			if(callInfo != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("totalpacket", baseInfo.getTotalpacket(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));

				buffer.append(StringUtils.jsonChar("time",StringUtils.charStr(callInfo.getTime()), 0));
				buffer.append(StringUtils.jsonChar("number",callInfo.getNumber(), 0));
				buffer.append(StringUtils.jsonChar("lng",callInfo.getLng(), 0));
				buffer.append(StringUtils.jsonChar("lat",callInfo.getLat(), 0));
				buffer.append(StringUtils.jsonChar("serialnumber", StringUtils.charStr(callInfo.getSerialnumber()), 1));
				buffer.append("}");
			}
			logger.info("[编码乘客人数上报]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装编码乘客人数上报异常",e);
			return null;
		}
	}


	/**
	 * 对讲申请操作
	 * @param baseInfo
	 * @param taxiInfo
	 * @return
	 */
	public static String encoderIntercomOperationJson(BaseInfo baseInfo,TaxiInfo taxiInfo){
		try {

			Map <String,Object>  map = new LinkedHashMap<String,Object>();
			map.put("msgid", baseInfo.getMsgid());
			map.put("totalpacket", baseInfo.getTotalpacket());
			map.put("subpacket", baseInfo.getSubpacket());
			map.put("encryption", baseInfo.getEncryption());
			map.put("bodysize", baseInfo.getBodysize());
			map.put("terminal", baseInfo.getTerminal());
			map.put("seq", baseInfo.getSeq());

			map.put("groupid",taxiInfo.getGroupid());
			map.put("carid",taxiInfo.getCarid());
			map.put("result",taxiInfo.getResult());

			String jsonStr = JSON.toJSONString(map,false);

			logger.info("[对讲申请操作]json数据包："+jsonStr);

			return jsonStr;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装对讲申请操作数据包编码异常",e);
			return null;
		}
	}


}
