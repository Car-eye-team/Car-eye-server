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
import com.careye.dsparse.bbdomain.CarSource;
import com.careye.dsparse.bbdomain.DataCompressReport;
import com.careye.dsparse.bbdomain.DataTransmission;
import com.careye.dsparse.bbdomain.DriverInfo;
import com.careye.dsparse.bbdomain.ElectronicWaybill;
import com.careye.dsparse.bbdomain.GateOrder;
import com.careye.dsparse.bbdomain.InfoDemandMenu;
import com.careye.dsparse.bbdomain.MultiMediaData;
import com.careye.dsparse.bbdomain.MultiMediaEventInfo;
import com.careye.dsparse.bbdomain.PositionDataUpload;
import com.careye.dsparse.bbdomain.PositionInfo;
import com.careye.dsparse.bbdomain.PositionInfoItems;
import com.careye.dsparse.bbdomain.PublicKey;
import com.careye.dsparse.bbdomain.QuestioningAnswer;
import com.careye.dsparse.bbdomain.RegisterInfo;
import com.careye.dsparse.bbdomain.ReturnRecord;
import com.careye.dsparse.bbdomain.SearchTerResponse;
import com.careye.dsparse.bbdomain.ServiceEvaluation;
import com.careye.dsparse.bbdomain.StorageMultiMediaResponse;
import com.careye.dsparse.bbdomain.TaximeterInfo;
import com.careye.dsparse.bbdomain.TerminalGeneralRes;
import com.careye.dsparse.bbdomain.TerminalParameter;
import com.careye.dsparse.bbdomain.TerminalUpdateResponse;
import com.careye.dsparse.bbdomain.TerminalUseInfo;
import com.careye.dsparse.bbdomain.TravelingRecorder;
import com.careye.dsparse.bbdomain.WarnInfo;
import com.careye.dsparse.constant.BaseInfo;
import com.careye.dsparse.decoder.BbTaxiByteDecoderUtil;
import com.careye.dsparse.utlis.ExceptionUtil;
import com.careye.dsparse.utlis.StringUtils;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：JsonEncoder    
 * 类描述：部标组装json协议    
 * 创建人：zr    
 * 创建时间：2015-5-14 上午09:37:18    
 * 修改人：zr    
 * 修改时间：2015-5-14 上午09:37:18    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BbJsonEncoder {

	private final static Logger logger = Logger.getLogger(BbJsonEncoder.class);


	/**
	 * 组装终端通用应答json协议
	 * @return
	 */
	public static String encoderTerminalGeneralResJson(BaseInfo baseInfo,TerminalGeneralRes terminalGeneralRes){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
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
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
			buffer.append(StringUtils.jsonChar("passwd",StringUtils.charStr(authInfo.getPasswd()),1));
			buffer.append("}");
			logger.info("[鉴权消息]json数据包："+buffer.toString());

			return buffer.toString();
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
		StringBuffer buffer = new StringBuffer();
		try {
			if(dataTransmission != null){

				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("type",dataTransmission.getType(), 0));
				buffer.append(StringUtils.jsonChar("data",StringUtils.charStr(dataTransmission.getData()),1));
				buffer.append("}");
				logger.info("[ 数据上行透传 ]json数据包："+buffer.toString());
			}
			return buffer.toString();
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
		StringBuffer buffer = new StringBuffer();
		try {
			if(dataCompressReport != null){
				
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("len",dataCompressReport.getLen(), 0));
				buffer.append(StringUtils.jsonChar("data",StringUtils.charStr(dataCompressReport.getData()),1));
				buffer.append("}");
				logger.info("[ 数据压缩上报 ]json数据包："+buffer.toString());
			}
			return buffer.toString();
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
		StringBuffer buffer = new StringBuffer();
		try {
			if(publicKey != null){

				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("e",publicKey.getE(), 0));
				buffer.append(StringUtils.jsonChar("n",StringUtils.charStr(publicKey.getN()),1));
				buffer.append("}");
				logger.info("[ 终端RSA公钥 ]json数据包："+buffer.toString());
			}
			return buffer.toString();
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
	 * 组装电召抢答
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
	 * 驾驶员取消电召
	 * @param baseInfo
	 * @param callInfo
	 * @return
	 */
	public static String encoderZjCancelCallJson(BaseInfo baseInfo,CallInfo callInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			if(callInfo != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));
				buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(callInfo.getOrderid()), 0));
				buffer.append(StringUtils.jsonChar("reason", callInfo.getReason(), 1));
				buffer.append("}");
			}
			logger.info("[驾驶员取消电召]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装驾驶员取消电召数据包编码异常",e);
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
	 * 组装查询货源
	 * @param baseInfo
	 * @param orderInfo
	 * @return
	 */
	public static String encoderQueryGoods(BaseInfo baseInfo,GateOrder orderInfo){

		try {
			StringBuffer buffer = new StringBuffer();
			if(orderInfo != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));

				buffer.append(StringUtils.jsonChar("sgid",orderInfo.getSgid(),0));
				buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(orderInfo.getOrderid()),1));
				buffer.append("}");
			}
			logger.info("[查询货源]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装查询货源协议数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装确认订单
	 * @param baseInfo
	 * @param orderInfo
	 * @return
	 */
	public static String encoderConfirmOrder(BaseInfo baseInfo,GateOrder orderInfo){

		try {
			StringBuffer buffer = new StringBuffer();
			if(orderInfo != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));

				buffer.append(StringUtils.jsonChar("result",orderInfo.getResult(),0));
				buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(orderInfo.getOrderid()),1));
				buffer.append("}");
			}
			logger.info("[确认订单]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装确认订单协议数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装已运到
	 * @param baseInfo
	 * @param orderInfo
	 * @return
	 */
	public static String encoderArriveOrder(BaseInfo baseInfo,GateOrder orderInfo){

		try {
			StringBuffer buffer = new StringBuffer();
			if(orderInfo != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));

				buffer.append(StringUtils.jsonChar("lat", orderInfo.getLat(), 0));
				buffer.append(StringUtils.jsonChar("lng", orderInfo.getLng(), 0));
				buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(orderInfo.getOrderid()),1));
				buffer.append("}");
			}
			logger.info("[已运到]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装已运到协议数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装查询订单
	 * @param baseInfo
	 * @param orderInfo
	 * @return
	 */
	public static String encoderQueryOrder(BaseInfo baseInfo,GateOrder orderInfo){

		try {
			StringBuffer buffer = new StringBuffer();
			if(orderInfo != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));

				buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(orderInfo.getOrderid()),1));
				buffer.append("}");
			}
			logger.info("[查询订单]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装查询订单协议数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装投诉订单
	 * @param baseInfo
	 * @param ComplainOrder
	 * @return
	 */
	public static String encoderComplainOrder(BaseInfo baseInfo,GateOrder complainOrder){

		try {
			StringBuffer buffer = new StringBuffer();
			if(complainOrder != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));

				buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(complainOrder.getOrderid()),0));
				buffer.append(StringUtils.jsonChar("type", complainOrder.getType(), 0));
				buffer.append(StringUtils.jsonChar("desc", StringUtils.charStr(complainOrder.getDesc()), 1));
				buffer.append("}");
			}
			logger.info("[投诉订单]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装投诉订单协议数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装查询积分诚信度协议
	 * @return
	 */
	public static String encoderQueryIntegralOrder(BaseInfo baseInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(),1));
			buffer.append("}");
			logger.info("[查询积分诚信度]json数据包："+buffer.toString());

			return buffer.toString();
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("查询积分诚信度数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装查询收货联系方式
	 * @param baseInfo
	 * @param orderInfo
	 * @return
	 */
	public static String encoderQueryOrderReceipt(BaseInfo baseInfo,GateOrder orderInfo){

		try {
			StringBuffer buffer = new StringBuffer();
			if(orderInfo != null){
				buffer.append("{");
				buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
				buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
				buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
				buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
				buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
				buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(), 0));

				buffer.append(StringUtils.jsonChar("orderid",StringUtils.charStr(orderInfo.getOrderid()),1));
				buffer.append("}");
			}
			logger.info("[查询收货联系方式]json数据包："+buffer.toString());
			return buffer.toString();

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("组装查询收货联系方式协议数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装查询我的资料
	 * @return
	 */
	public static String encoderQueryInformation(BaseInfo baseInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(),1));
			buffer.append("}");
			logger.info("[查询我的资料]json数据包："+buffer.toString());

			return buffer.toString();
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("查询我的资料数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装查询我的车源
	 * @return
	 */
	public static String encoderQueryCarSource(BaseInfo baseInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(),1));
			buffer.append("}");
			logger.info("[查询我的车源]json数据包："+buffer.toString());

			return buffer.toString();
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("查询我的车源数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装车源更新状态
	 * @return
	 */
	public static String encoderUpdateCarsourceStatus(BaseInfo baseInfo,GateOrder orderInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(),0));

			buffer.append(StringUtils.jsonChar("carsourceid", orderInfo.getCarsourceid(),0));
			buffer.append(StringUtils.jsonChar("effect",orderInfo.getEffect(),1));

			buffer.append("}");
			logger.info("[车源更新状态]json数据包："+buffer.toString());

			return buffer.toString();
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("车源更新状态数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装车源更新发布时间
	 * @return
	 */
	public static String encoderUpdateCarsourceTime(BaseInfo baseInfo,GateOrder orderInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(),0));

			buffer.append(StringUtils.jsonChar("carsourceid", orderInfo.getCarsourceid(),1));

			buffer.append("}");
			logger.info("[车源更新发布时间]json数据包："+buffer.toString());

			return buffer.toString();
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("车源更新发布时间数据包编码异常",e);
			return null;
		}
	}


	/**
	 * 组装发布车源
	 * @return
	 */
	public static String encoderSendCarsource(BaseInfo baseInfo,CarSource carSource){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(),0));

			buffer.append(StringUtils.jsonChar("type", carSource.getType(),0));
			buffer.append(StringUtils.jsonChar("use", carSource.getUse(),0));
			buffer.append(StringUtils.jsonChar("isexpedited", carSource.getIsexpedited(),0));
			buffer.append(StringUtils.jsonChar("transportline", carSource.getTransportline(),0));
			buffer.append(StringUtils.jsonChar("sprovince", carSource.getSprovince(),0));
			buffer.append(StringUtils.jsonChar("scity", carSource.getScity(),0));
			buffer.append(StringUtils.jsonChar("sdistrict", carSource.getSdistrict(),0));
			buffer.append(StringUtils.jsonChar("eprovince", carSource.getEprovince(),0));
			buffer.append(StringUtils.jsonChar("ecity", carSource.getEcity(),0));
			buffer.append(StringUtils.jsonChar("edistrict", carSource.getEdistrict(),1));

			buffer.append("}");
			logger.info("[发布车源]json数据包："+buffer.toString());

			return buffer.toString();
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("发布车源数据包编码异常",e);
			return null;
		}
	}

	/**
	 * 组装回程状态记录
	 * @return
	 */
	public static String encoderReturnRecord(BaseInfo baseInfo,ReturnRecord returnRecord){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(),0));

			buffer.append(StringUtils.jsonChar("status", returnRecord.getStatus(),0));
			buffer.append(StringUtils.jsonChar("weight", returnRecord.getWeight(),0));
			buffer.append(StringUtils.jsonChar("lat", returnRecord.getLat(),0));
			buffer.append(StringUtils.jsonChar("lng", returnRecord.getLng(),1));

			buffer.append("}");
			logger.info("[回程状态记录]json数据包："+buffer.toString());

			return buffer.toString();
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("回程状态记录数据包编码异常",e);
			return null;
		}
	}
	/**
	 * 组装冷链阀值和温度
	 * @return
	 */
	public static String encoderWarnInfo(BaseInfo baseInfo,WarnInfo warnInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append(StringUtils.jsonChar("msgid", baseInfo.getMsgid(), 0));
			buffer.append(StringUtils.jsonChar("subpacket", baseInfo.getSubpacket(), 0));
			buffer.append(StringUtils.jsonChar("encryption", baseInfo.getEncryption(), 0));
			buffer.append(StringUtils.jsonChar("bodysize", baseInfo.getBodysize(), 0));
			buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr(baseInfo.getTerminal()),0));
			buffer.append(StringUtils.jsonChar("seq", baseInfo.getSeq(),0));

			buffer.append(StringUtils.jsonChar("normal", StringUtils.charStr(warnInfo.getNormal()),0));
			buffer.append(StringUtils.jsonChar("warn", StringUtils.charStr(warnInfo.getWarn()),0));
			buffer.append(StringUtils.jsonChar("alarm", StringUtils.charStr(warnInfo.getAlarm()),0));
			buffer.append(StringUtils.jsonChar("temperature", StringUtils.charStr(warnInfo.getTemperature()),1));

			buffer.append("}");
			logger.info("[冷链阀值和温度]json数据包："+buffer.toString());

			return buffer.toString();
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			logger.error("冷链阀值和温度数据包编码异常",e);
			return null;
		}
	}

}
