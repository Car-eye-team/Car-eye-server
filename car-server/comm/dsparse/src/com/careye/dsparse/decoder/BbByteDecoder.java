/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.decoder;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.careye.dsparse.bbdomain.Auth;
import com.careye.dsparse.bbdomain.BatteryInfo;
import com.careye.dsparse.bbdomain.CallInfo;
import com.careye.dsparse.bbdomain.CameraCommandResponse;
import com.careye.dsparse.bbdomain.CanTotalBusDataUpload;
import com.careye.dsparse.bbdomain.CanTotalBusDataUploadItems;
import com.careye.dsparse.bbdomain.CarSource;
import com.careye.dsparse.bbdomain.DataCompressReport;
import com.careye.dsparse.bbdomain.DataTransmission;
import com.careye.dsparse.bbdomain.DriverInfo;
import com.careye.dsparse.bbdomain.ElectronicWaybill;
import com.careye.dsparse.bbdomain.GateOrder;
import com.careye.dsparse.bbdomain.InfoDemandMenu;
import com.careye.dsparse.bbdomain.MultiMediaData;
import com.careye.dsparse.bbdomain.MultiMediaEventInfo;
import com.careye.dsparse.bbdomain.MultiMediaRetrieval;
import com.careye.dsparse.bbdomain.ParameterInfo;
import com.careye.dsparse.bbdomain.PositionDataUpload;
import com.careye.dsparse.bbdomain.PositionDataUploadItems;
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
import com.careye.dsparse.utlis.ExceptionUtil;
import com.careye.dsparse.utlis.ParseUtil;
import com.careye.dsparse.utlis.StringUtils;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：ByteDecoder    
 * 类描述： 部标二进制协议解析   
 * 创建人：zr    
 * 创建时间：2015-5-14 上午09:47:27    
 * 修改人：zr    
 * 修改时间：2015-5-14 上午09:47:27    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BbByteDecoder {

	private final static Logger logger = Logger.getLogger(BbByteDecoder.class);

	/**
	 * 解析终端通用应答
	 * @param msgbody
	 * @return
	 */
	public static TerminalGeneralRes decoderTerminalGeneralResMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		TerminalGeneralRes terminalGeneralRes = new TerminalGeneralRes();
		try {
			int dstPos = 0;
			//应答流水号
			int respseq = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 2));
			dstPos += 2;
			terminalGeneralRes.setRespseq(respseq);
			//应答ID
			int respmsgid = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 2));
			dstPos += 2;
			terminalGeneralRes.setRespmsgid(respmsgid);
			//结果
			int result = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			dstPos += 1;
			terminalGeneralRes.setResult(result);

			return terminalGeneralRes;
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析鉴权协议异常"+e);
			return null;
		}
	}

	/**
	 * 解析鉴权协议
	 * @param msgbody
	 * @return
	 */
	public static Auth decoderAuthMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		Auth authInfo = new Auth();
		try {
			String passwd = ParseUtil.byteToString(data, 0, data.length);
			authInfo.setPasswd(passwd);
			return authInfo;
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析鉴权协议异常"+e);
			return null;
		}
	}

	/**
	 * 解析终端注册协议(0x0100)
	 * @param msgbody
	 * @return
	 */
	public static RegisterInfo decoderRegisterMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		int bodylen = 37;
		int len = 8;
		if(data.length > 37){
			bodylen = 37;
			len = 20;
		}else{
			bodylen = 25;
		}

		RegisterInfo registerInfo = new RegisterInfo();
		try {

			int dstPos = 0;
			//省域ID
			int province = Integer.parseInt(ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(data, dstPos, 2)),16);
			registerInfo.setProvince(province);
			dstPos += 2;
			//市县域ID
			int city = Integer.parseInt(ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(data, dstPos, 2)),16);
			registerInfo.setCity(city);
			dstPos += 2;
			//制造商ID
			byte [] oembyte = ParseUtil.byteTobyte(data, dstPos, 5);
			String oem = ParseUtil.byteToString(oembyte, 0, oembyte.length);
			registerInfo.setOem(oem);
			dstPos += 5;

			//终端型号
			byte [] typebyte = ParseUtil.byteTobyte(data, dstPos, len);
			String type = ParseUtil.byteToString(typebyte, 0, typebyte.length);
			registerInfo.setType(type);
			dstPos += len;

			//终端ID
			byte [] midbyte = ParseUtil.byteTobyte(data, dstPos, 7);
			String mid = ParseUtil.byteToString(midbyte, 0, midbyte.length);
			registerInfo.setMid(mid);
			dstPos += 7;

			//车牌颜色
			int platecolor = Integer.parseInt(ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(data, dstPos, 1)),16);
			registerInfo.setPlatecolor(platecolor);
			dstPos += 1;

			//车辆标识
			byte [] platecodebyte = ParseUtil.byteTobyte(data, dstPos, data.length-bodylen);
			String platecode = ParseUtil.byteToString(platecodebyte, 0, platecodebyte.length);
			registerInfo.setPlatecode(platecode);

			return registerInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析终端注册协议异常"+e);
			return null;
		}
	}

	/**
	 * 查询参数应答
	 * @param msgbody
	 * @return
	 */
	public static TerminalParameter decoderQueryParamAnswer(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {
			TerminalParameter terminalParameter = new TerminalParameter();
			int dstPos = 0;
			//应答流水号
			int seqR = Integer.parseInt(ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(data, dstPos, 2)),16);
			terminalParameter.setSeqR(seqR);
			dstPos += 2;
			//应答参数个数
			int count = Integer.parseInt(ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(data, dstPos, 1)),16);
			terminalParameter.setCount(count);
			dstPos += 1;

			for (int i = 0; i < count; i++) {

				ParameterInfo parameterInfo= new ParameterInfo();
				//参数ID
				int id = Integer.parseInt(ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(data, dstPos, 4)),16);
				dstPos += 4;
				parameterInfo.setId(id);

				//参数长度
				int size = Integer.parseInt(ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(data, dstPos, 1)),16);
				dstPos += 1;
				parameterInfo.setSize(size);

				//参数值
				String value = ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(data, dstPos, size));
				dstPos += size;
				parameterInfo.setValue(value);

				terminalParameter.getItems().add(parameterInfo);
			}


			return terminalParameter;

		} catch (Exception e) {
			logger.info("解析查询参数应答协议异常"+e);
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

	}


	/**
	 * 解析查询终端属性应答(0x0107)
	 * @param msgbody
	 * @return
	 */
	public static SearchTerResponse decoderSearchTerResponseMsg(String msgbody){
		byte [] data = ParseUtil.parseHexStrToByte(msgbody);

		SearchTerResponse searchTerResponse = new SearchTerResponse();
		try {
			int dstPos = 0;

			//终端类型      (WORD)  
			String type = ParseUtil.byteTobit(ParseUtil.byteTobyte(data, dstPos,2));
			searchTerResponse.setType(type);
			dstPos += 2;

			// 制造商 ID   ( BYTE[5])
			byte [] markeridbyte = ParseUtil.byteTobyte(data, dstPos, 5);
			String markerid = ParseUtil.byteToString(markeridbyte, 0, markeridbyte.length);
			searchTerResponse.setMarkerid(markerid);
			dstPos += 5;

			// 终端型号    ( BYTE[20])
			byte [] termainaltypebyte = ParseUtil.byteTobyte(data, dstPos, 20);
			String termainaltype = ParseUtil.byteToString(termainaltypebyte, 0, termainaltypebyte.length);
			searchTerResponse.setTermainaltype(termainaltype);
			dstPos += 20;

			// 终端 ID   (  BYTE[7] )
			byte [] termainalidbyte = ParseUtil.byteTobyte(data, dstPos, 7);
			String termainalid = ParseUtil.byteToString(termainalidbyte, 0, termainalidbyte.length);
			searchTerResponse.setTermainalid(termainalid);
			dstPos += 7;

			//终端 SIM 卡 ICCID [ BCD[10] ]
			byte [] iccidbyte = ParseUtil.byteTobyte(data, dstPos, 10);
			String iccid = ParseUtil.bcd2Str(iccidbyte);
			searchTerResponse.setIccid(iccid);
			dstPos += 10;

			// 终端硬件版本号长度 (BYTE)
			int hardwarelen = Integer.parseInt(ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(data, dstPos, 1)),16);
			searchTerResponse.setHardwarelen(hardwarelen);
			dstPos += 1;

			// 终端硬件版本号(STRING)
			byte [] hardwarenobyte = ParseUtil.byteTobyte(data, dstPos, hardwarelen);
			String hardwareno = ParseUtil.byteToString(hardwarenobyte, 0, hardwarenobyte.length);
			searchTerResponse.setHardwareno(hardwareno);
			dstPos += hardwarelen;

			// 终端固件版本号长度 (BYTE)
			int firmwarelen = Integer.parseInt(ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(data, dstPos, 1)),16);
			searchTerResponse.setFirmwarelen(firmwarelen);
			dstPos += 1;

			// 终端固件版本号 (STRING)
			byte [] firmwarenobyte = ParseUtil.byteTobyte(data, dstPos,firmwarelen);
			String firmwareno = ParseUtil.byteToString(firmwarenobyte, 0, firmwarenobyte.length);
			searchTerResponse.setFirmwareno(firmwareno);
			dstPos += firmwarelen;

			// GNSS 模块属性  ( BYTE )
			String cnssproperty = ParseUtil.byteTobit(ParseUtil.byteTobyte(data, dstPos, 1));
			searchTerResponse.setCnssproperty(cnssproperty);
			dstPos += 1;

			// 通信模块属性  ( BYTE )
			String comproperty = ParseUtil.byteTobit(ParseUtil.byteTobyte(data, dstPos, 1));;
			searchTerResponse.setComproperty(comproperty);
			dstPos += 1;

			return searchTerResponse;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析查询终端属性应答协议异常:"+e);
			return null;
		}

	}

	/**
	 * 终端升级结果通知(0x0108)
	 * @param msgbody
	 * @return
	 */
	public static TerminalUpdateResponse decoderTerUpdateResMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		TerminalUpdateResponse terUpdateRes = new TerminalUpdateResponse();
		try {

			int dstPos = 0;
			//升级类型
			int type = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			terUpdateRes.setType(type);
			dstPos += 1;
			//升级结果
			int result = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			terUpdateRes.setResult(result);

			return terUpdateRes;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析终端升级结果协议异常"+e);
			return null;
		}
	}

	/**
	 * 解析终端位置信息汇报协议\车辆控制应答(0x0200 0x0201 0x0500)
	 * @param msgbody
	 * @param type 1:0x0200 2:0x0201 0x0500
	 * @return
	 */
	public static PositionInfo decoderPositionMsg(String msgbody,int type){
		byte [] data = ParseUtil.parseHexStrToByte(msgbody);

		PositionInfo positionInfo = new PositionInfo();
		try {

			int dstPos = 0;

			//应答流水号(0x0201)
			if(type == 2){
				int seqR = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 2));
				positionInfo.setSeqR(seqR);
				dstPos += 2;
			}

			//报警标志
			byte [] anbyte = ParseUtil.byteTobyte(data, dstPos, 4);
			String an = ParseUtil.byteTobit(anbyte);
			positionInfo = BbByteDecoderUtil.setAlarmState(an, positionInfo);
			dstPos += 4;

			//状态
			byte [] snbyte = ParseUtil.byteTobyte(data, dstPos, 4);
			String sn = ParseUtil.byteTobit(snbyte);
			positionInfo = BbByteDecoderUtil.setState(sn, positionInfo);
			dstPos += 4;

			//纬度
			byte [] latbyte = ParseUtil.byteTobyte(data, dstPos, 4);
			int lat = Integer.parseInt(ParseUtil.parseByteToHexStr(latbyte),16);
			positionInfo.setLat(lat);
			dstPos += 4;

			//经度
			byte [] lngbyte = ParseUtil.byteTobyte(data, dstPos, 4);
			int lng = Integer.parseInt(ParseUtil.parseByteToHexStr(lngbyte),16);
			positionInfo.setLng(lng);
			dstPos += 4;

			//高程
			byte [] altitudebyte = ParseUtil.byteTobyte(data, dstPos, 2);
			int altitude = Integer.parseInt(ParseUtil.parseByteToHexStr(altitudebyte),16);
			positionInfo.setAltitude(altitude);
			dstPos += 2;

			//速度
			byte [] speedbyte = ParseUtil.byteTobyte(data, dstPos, 2);
			int speed = Integer.parseInt(ParseUtil.parseByteToHexStr(speedbyte),16)/10;
			positionInfo.setSpeed(String.valueOf(speed));
			dstPos += 2;

			//方向
			byte [] directionbyte = ParseUtil.byteTobyte(data, dstPos, 2);
			int direction = Integer.parseInt(ParseUtil.parseByteToHexStr(directionbyte),16);
			positionInfo.setDirection(direction);
			dstPos += 2;

			//时间
			byte [] timebyte = ParseUtil.byteTobyte(data, dstPos, 6);
			String time = ParseUtil.bcd2Str(timebyte);
			positionInfo.setTime(time);
			dstPos += 6;

			//解析位置附加信息项列表
			int datalen = data.length;
			if(dstPos < datalen){
				List<PositionInfoItems> list = new ArrayList<PositionInfoItems>();
				try {
					int size = data.length-dstPos; 
					for(int i = 0; i< size;i++){

						PositionInfoItems positionInfoItems = new PositionInfoItems();
						int aid = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
						dstPos += 1;
						positionInfoItems.setAid(aid);
						int alen = 0;
						if(aid > 50){
							alen = data.length-dstPos;
						}else{
							alen = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
							dstPos += 1;
						}

						positionInfoItems.setAlen(alen);

						//进出区域/路线报警附加信息 路段行驶时间不足/过长报警附加信息 两个直接返回16进制字符串由前台自行解析
						if(aid == 0x12 || aid == 0x13 || aid > 50){
							String avalue = ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(data, dstPos, alen));
							dstPos += alen;			
							positionInfoItems.setAvalue(avalue);
						}else{
							int avalue = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, alen));
							dstPos += alen;

							String value = String.valueOf(avalue);

							//附加信息ID1(0x01 里程，DWORD，1/10km，对应车上里程表读数)
							if(aid == 1){
								//转换为km
								value = StringUtils.intChangeString(avalue,10);  
								positionInfo.setMileage(value);
							}else if(aid == 2){  //油量，WORD，1/10L，对应车上油量表读数
								//转换为L
								value = StringUtils.intChangeString(avalue,10);
							}else if(aid == 3){   //行驶记录功能获取的速度，WORD，1/10km/h
								if(avalue != 0){
									//转换为km/h
									value = StringUtils.intChangeString(avalue,10);
									positionInfo.setSpeed(value);
								}
							}
							positionInfoItems.setAvalue(value);
						}
						list.add(positionInfoItems);

						if(dstPos >= data.length){
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				positionInfo.setItems(list);

			}

			return positionInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析终端位置信息汇报协议异常:"+e);
			return null;
		}
	}


	/**
	 * 解析事件报告
	 * @param msgbody
	 * @return
	 */
	public static int decoderEventReport(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		int id = 0;
		try {
			int dstPos = 0;
			//事件ID
			id = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析 解析事件报告协议异常:"+e);
		}

		return id;
	}


	/**
	 * 解析提问应答(0x0302)
	 * @param msgbody
	 * @return
	 */
	public static QuestioningAnswer decoderDataQuestioningAnswerMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		QuestioningAnswer questioningAnswer = new QuestioningAnswer();
		try {
			int dstPos = 0;

			//   应答流水号    WORD 
			byte [] seqRbyte = ParseUtil.byteTobyte(data, dstPos, 2);
			int  seqR = ParseUtil.byteToint(seqRbyte);
			questioningAnswer.setSeqR(seqR);
			dstPos += 2;

			// 答案 ID 
			byte [] answerbyte = ParseUtil.byteTobyte(data, dstPos, 1);
			int  answer = ParseUtil.byteToint(answerbyte);
			questioningAnswer.setAnswer(answer);
			dstPos += 1;

			return questioningAnswer;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析 提问应答 协议异常:"+e);
			return null;
		}

	}

	/**
	 * 信息点播/取消
	 * @param msgbody
	 * @return
	 */
	public static InfoDemandMenu decoderInfoDemandMenuCancel(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		InfoDemandMenu infoDemandMenu = new InfoDemandMenu();
		try {
			int dstPos = 0;

			//信息类型
			byte [] typebyte = ParseUtil.byteTobyte(data, dstPos, 1);
			int  type = ParseUtil.byteToint(typebyte);
			infoDemandMenu.setType(type);
			dstPos += 1;

			//点播/取消标志 
			byte [] demandbyte = ParseUtil.byteTobyte(data, dstPos, 1);
			int  demand = ParseUtil.byteToint(demandbyte);
			infoDemandMenu.setDemand(demand);
			dstPos += 1;

			return infoDemandMenu;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析信息点播/取消 协议异常:"+e);
			return null;
		}

	}

	/**
	 * 解析电子运单
	 * @param msgbody
	 * @return
	 */
	public static ElectronicWaybill decoderElectronicWaybill(String msgbody){

		ElectronicWaybill electronicWaybill = new ElectronicWaybill();

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {
			int dstPos = 0;
			//电子运单长度
			int len = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 4));
			dstPos += 4;
			electronicWaybill.setLen(len);
			//电子运单内容
			String content = ParseUtil.byteToString(ParseUtil.byteTobyte(data, dstPos, len), 0, len);
			electronicWaybill.setContent(content);

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析事件报告协议异常:"+e);
		}

		return electronicWaybill;
	}


	/**
	 * 解析行驶记录数据上传
	 * @param msgbody
	 * @return
	 */
	public static TravelingRecorder decoderTravelingRecorder(String msgbody){

		TravelingRecorder travelingRecorder = new TravelingRecorder();

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {
			int dstPos = 0;
			//命令字
			int cmd = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			dstPos += 1;
			travelingRecorder.setCmd(cmd);

			//数据块
			String dataContent = ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(data, dstPos, data.length-1));
			travelingRecorder.setData(dataContent);

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析 行驶记录数据上传协议异常:"+e);
		}

		return travelingRecorder;
	}



	/**
	 * 解析驾驶员身份信息采集上报
	 * @param msgbody
	 * @return
	 */
	public static DriverInfo decoderDriverInfo(String msgbody){

		DriverInfo driverInfo = new DriverInfo();

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {
			int dstPos = 0;
			//状态
			int state = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			dstPos += 1;
			driverInfo.setState(state);

			//时间
			byte [] timebyte = ParseUtil.byteTobyte(data, dstPos, 6);
			String time = ParseUtil.bcd2Str(timebyte);
			driverInfo.setTime(time);
			dstPos += 6;

			//IC卡读取结果 0x00：IC 卡读卡成功； 0x01：读卡失败，原因为卡片密钥认证未通过；0x02：读卡失败，原因为卡片已被锁定；0x03：读卡失败，原因为卡片被拔出；0x04：读卡失败，原因为数据校验错误。
			int result = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			dstPos += 1;
			driverInfo.setResult(result);
			
			//以下字段在 IC 卡读取结果等于 0x00 时才有效
			if(result == 0){
				//驾驶员姓名长度
				int namelen = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
				dstPos += 1;
				driverInfo.setNamelen(namelen);

				//驾驶员姓名
				byte[] namebyte = ParseUtil.byteTobyte(data, dstPos, namelen);
				driverInfo.setName(ParseUtil.byteToString(namebyte, 0, namebyte.length));
				dstPos += namelen;

				//从业资格证编码
				String qc = ParseUtil.byteToString(ParseUtil.byteTobyte(data, dstPos, 20), 0, 20);
				driverInfo.setQc(qc);
				dstPos += 20;

				//发证机构名称长度
				int orglen = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
				dstPos += 1;
				driverInfo.setOrglen(orglen);

				//发证机构名称
				byte[] orgbyte = ParseUtil.byteTobyte(data, dstPos, orglen);
				driverInfo.setOrg(ParseUtil.byteToString(orgbyte, 0, orgbyte.length));
				dstPos += orglen;

				//证件有效期
				byte [] effectivetimebyte = ParseUtil.byteTobyte(data, dstPos, 4);
				String effectivetime = ParseUtil.bcd2Str(effectivetimebyte);
				driverInfo.setEffectivetime(effectivetime);
			}

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析驾驶员身份信息采集上报协议异常:"+e);
		}

		return driverInfo;
	}

	/**
	 * 解析定位数据批量上传(0x0704)
	 * @param msgbody
	 * @return
	 */
	public static PositionDataUpload decoderPdUploadMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);

		PositionDataUpload pdUpload = new PositionDataUpload();
		try {
			int dstPos = 0;

			//数据项个数     (WORD)  
			int count = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 2));
			pdUpload.setCount(count);
			dstPos += 2;

			// 位置数据类型  (BYTE)
			int type = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			pdUpload.setType(type);
			dstPos += 1;

			//  位置汇报数据项 
			List<PositionDataUploadItems> items = new ArrayList<PositionDataUploadItems>();

			for(int i=0;i<count;i++){

				PositionDataUploadItems prData = new PositionDataUploadItems();

				//位置汇报数据体长度      (WORD)  
				int len = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 2));
				prData.setLen(len);
				dstPos += 2;

				//位置信息汇报 (0x0200)消息体   BYTE[length] 
				byte [] positionInfobyte = ParseUtil.byteTobyte(data,dstPos,len);
				PositionInfo positionInfo = BbByteDecoder.decoderPositionMsg(ParseUtil.parseByteToHexStr(positionInfobyte),1);
				prData.setPosition(positionInfo);
				dstPos += len;

				items.add(prData);
			}

			pdUpload.setItems(items);

			return pdUpload;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析定位数据批量上传协议异常:"+e);
			return null;
		}
	}

	/**
	 * 解析CAN 总线数据上传 (0x0705)
	 * @param msgbody
	 * @return
	 */
	public static CanTotalBusDataUpload decoderCtbdUploadMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);

		CanTotalBusDataUpload ctbdUpload = new CanTotalBusDataUpload();
		try {
			int dstPos = 0;

			//数据项个数
			int count = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 2));
			ctbdUpload.setCount(count);
			dstPos += 2;

			//CAN 总线数据接收时间
			byte [] receivedatebyte = ParseUtil.byteTobyte(data, dstPos, 5);
			String receivedate = ParseUtil.bcd2Str(receivedatebyte);
			ctbdUpload.setReceivedate(receivedate);
			dstPos += 5;

			List<CanTotalBusDataUploadItems> items = new ArrayList<CanTotalBusDataUploadItems>();
			//  CAN 总线数据项 
			if(count>0){

				for(int i=0;i<count;i++){

					CanTotalBusDataUploadItems cItems = new CanTotalBusDataUploadItems();
					//CAN ID
					byte[] canIdbyte =  ParseUtil.byteTobyte(data, dstPos, 4);

					//转换成位
					String canIdBit = ParseUtil.byteTobit(canIdbyte);
					//CAN 通道号ID(bit31 表示CAN 通道号，0：CAN1，1：CAN2)
					int channel = Integer.parseInt(canIdBit.substring(0, 1));
					cItems.setChannel(channel);
					//示帧类型(bit30 表示帧类型，0：标准帧，1：扩展帧；)
					int type = Integer.parseInt(canIdBit.substring(1, 2));
					cItems.setType(type);
					//据采集方式(bit29 表示数据采集方式，0：原始数据，1：采集区间的平均值；)
					int way =  Integer.parseInt(canIdBit.substring(2, 3));
					cItems.setWay(way);

					//bit28-bit0 表示CAN 总线ID。
					int canid = Integer.valueOf(canIdBit.substring(3, canIdBit.length()),2);
					cItems.setCanid(canid);
					dstPos += 4;

					//CAN DATA
					byte[] canDatabyte =  ParseUtil.byteTobyte(data, dstPos, 8);
					//保存can的十六进制数据
					cItems.setCandata(ParseUtil.parseByteToHexStr(canDatabyte));
					dstPos += 8;

					items.add(cItems);
				}
			}
			ctbdUpload.setItems(items);

			return ctbdUpload;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析定位数据批量上传协议异常:"+e);
			return null;
		}
	}

	/**
	 * 解析多媒体事件信息上传(0x0800)
	 * @param msgbody
	 * @return
	 */
	public static MultiMediaEventInfo decoderMmeInfoMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		MultiMediaEventInfo mmEventInfo = new MultiMediaEventInfo();

		try {
			int dstPos = 0;

			// 多媒体数据 ID      DWORD 
			byte [] dataIdbyte = ParseUtil.byteTobyte(data, dstPos, 4);
			int  dataId = ParseUtil.byteToint(dataIdbyte);
			mmEventInfo.setDataId(dataId);
			dstPos += 4;

			//多媒体类型      TYPE
			int mediaType = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			mmEventInfo.setMediaType(mediaType);
			dstPos += 1;

			//多媒体格式编码      TYPE
			int format = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			mmEventInfo.setFormat(format);
			dstPos += 1;

			//事件项编码       TYPE
			int eventId = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			mmEventInfo.setEventId(eventId);
			dstPos += 1;

			//通道 ID     TYPE
			byte [] wayIdbyte = ParseUtil.byteTobyte(data, dstPos, 1);
			int  wayId = ParseUtil.byteToint(wayIdbyte);
			mmEventInfo.setWayId(wayId);
			dstPos += 1;

			return mmEventInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析多媒体事件信息上传协议异常:"+e);
			return null;
		}
	}


	/**
	 * 解析多媒体数据上传 (0x0801)
	 * @param msgbody
	 * @return
	 */
	public static MultiMediaData decoderMultiMediaDataMsg(BaseInfo baseInfo,String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		MultiMediaData multiMediaData = new MultiMediaData();

		try {
			int dstPos = 0;
			// 多媒体 ID    DWORD 
			byte [] idbyte = ParseUtil.byteTobyte(data, dstPos, 4);
			int  dataId = ParseUtil.byteToint(idbyte);
			multiMediaData.setDataId(dataId);
			dstPos += 4;

			if(baseInfo.getSubpacket() == 1){

				// 多媒体类型      BYTE 
				int mediaType = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
				multiMediaData.setMediaType(mediaType);
				dstPos += 1;

				//  多媒体格式编码      BYTE 
				int format = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
				multiMediaData.setFormat(format);
				dstPos += 1;

				//  事件项编码      BYTE 
				int eventId = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
				multiMediaData.setEventId(eventId);
				dstPos += 1;

				//  通道 ID    BYTE 
				int wayId = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
				multiMediaData.setWayId(wayId);
				dstPos += 1;

				//位置信息汇报 (0x0200)消息体   BYTE[length] 
				byte [] positionInfobyte = ParseUtil.byteTobyte(data,dstPos,28);
				PositionInfo positionInfo = BbTaxiByteDecoder.decoderProtocolPositionMsg(ParseUtil.parseByteToHexStr(positionInfobyte));
				multiMediaData.setPositionInfo(positionInfo);
				dstPos += 28;
			}
			//多媒体数据包(后续考虑分包传) 
			byte [] multidatabyte = ParseUtil.byteTobyte(data, dstPos,data.length-dstPos);
			String  multidata = ParseUtil.parseByteToHexStr(multidatabyte);
			multiMediaData.setMediaData(multidata);

			//包总数
			multiMediaData.setPacketSum(baseInfo.getTotalpacket());
			//包ID
			multiMediaData.setPacketId(baseInfo.getSubpacket());

			return multiMediaData;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析多媒体数据上传协议异常:"+e);
			return null;
		}
	}


	/**
	 * 解析存储多媒体数据检索应答(0x0802)
	 * @param msgbody
	 * @return
	 */
	public static StorageMultiMediaResponse decoderSmmResponseMsg(String msgbody){
		byte [] data = ParseUtil.parseHexStrToByte(msgbody);

		StorageMultiMediaResponse smmResponse = new StorageMultiMediaResponse();
		try {
			int dstPos = 0;

			// 应答流水号    WORD 
			byte [] seqRbyte = ParseUtil.byteTobyte(data, dstPos, 2);
			int  seqR = ParseUtil.byteToint(seqRbyte);
			smmResponse.setSeqR(seqR);
			dstPos += 2;

			// 多媒体数据总项数    WORD 
			byte [] countbyte = ParseUtil.byteTobyte(data, dstPos, 2);
			int  count = ParseUtil.byteToint(countbyte);
			smmResponse.setCount(count);
			dstPos += 2;

			List<MultiMediaRetrieval> items = new ArrayList<MultiMediaRetrieval>();
			if(count>0){   //如果多媒体数据总项数 >0 则进行集合的解析
				//继续处理   多媒体检索项数据
				for(int i=0;i<count;i++){

					MultiMediaRetrieval mmRetrieval = new MultiMediaRetrieval();
					//多媒体 ID  DWORD
					byte [] mediaidbyte = ParseUtil.byteTobyte(data, dstPos, 4);
					int  mediaid = ParseUtil.byteToint(mediaidbyte);
					mmRetrieval.setMediaid(mediaid);
					dstPos += 4;

					//多媒体类型  TYPE
					int type = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
					mmRetrieval.setType(type);
					dstPos += 1;

					//通道 ID   TYPE
					byte [] channlebyte = ParseUtil.byteTobyte(data, dstPos, 1);
					int  channle = ParseUtil.byteToint(channlebyte);
					mmRetrieval.setId(channle);
					dstPos += 1;

					//事件项编码    TYPE
					int fmt = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
					mmRetrieval.setFmt(fmt);
					dstPos += 1;

					//位置信息汇报 (0x0200)消息体   BYTE[length] 
					byte [] positionInfobyte = ParseUtil.byteTobyte(data,dstPos,28);
					PositionInfo positionInfo = BbByteDecoder.decoderPositionMsg(ParseUtil.parseByteToHexStr(positionInfobyte),1);
					mmRetrieval.setPositionInfo(positionInfo);

					items.add(mmRetrieval);
				}
			}
			smmResponse.setItems(items);


			return smmResponse;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析存储多媒体数据检索应答协议异常:"+e);
			return null;
		}
	}


	/**
	 * 解析摄像头立即拍摄命令应答(0x0805)
	 * @param msgbody
	 * @return
	 */
	public static CameraCommandResponse decoderCcResponseMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		CameraCommandResponse ccResponse = new CameraCommandResponse();
		try {
			int dstPos = 0;

			// 应答流水号    WORD 
			byte [] seqRbyte = ParseUtil.byteTobyte(data, dstPos, 2);
			int  seqR = ParseUtil.byteToint(seqRbyte);
			ccResponse.setSeqR(seqR);
			dstPos += 2;

			// 结果     TYPE
			int result = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			ccResponse.setResult(result);
			dstPos += 1;

			// 多媒体 ID 个数     WORD
			int count = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			ccResponse.setCount(count);
			dstPos += 1;

			if(count>0){
				List<Integer> list = new ArrayList<Integer>();
				for(int i=0;i<count;i++){
					//解析多媒体 ID 列表
					int cameraid = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 4));
					list.add(cameraid);
					dstPos += 4;
				}
				ccResponse.setList(list);
			}
			return ccResponse;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析摄像头立即拍摄命令应答协议异常:"+e);
			return null;
		}
	}

	/**
	 * 解析数据上行透传  (0x0900)
	 * @param msgbody
	 * @return
	 */
	public static DataTransmission decoderDataTransmission(String msgbody){
		
		if(msgbody != null){
			byte [] data = ParseUtil.parseHexStrToByte(msgbody);
			if(data != null){
				DataTransmission dataTransmission = new DataTransmission();
				try {
					int dstPos = 0;

					//透传消息类型    BYTE  [0x00:GNSS 模块详细定位数据;0x0B:道路运输证 IC 卡信息, 0x41:串口 1 透传, 0x42:串口 2 透传;0xF0-0xFF:用户自定义透传 ]
					byte [] typebyte = ParseUtil.byteTobyte(data, dstPos, 1);
					int  type = ParseUtil.byteToint(typebyte);
					dataTransmission.setType(type);
					dstPos += 1;

					//透传消息内容
					byte [] databyte = ParseUtil.byteTobyte(data, dstPos,data.length-1);
					String dataContent =  ParseUtil.parseByteToHexStr(databyte);
					dataTransmission.setData(dataContent);
					return dataTransmission;
				} catch (Exception e) {
					e.printStackTrace();ExceptionUtil.getInfo(e);
					logger.info("解析数据上行透传协议异常:"+e);
					return null;
				}
			}
		}
		return null;
	}

	/**
	 * 解析 数据压缩上报 (0x0901)
	 * @param msgbody
	 * @return
	 */
	public static DataCompressReport decoderDataCompressReportMsg(String msgbody){
		byte [] data = ParseUtil.parseHexStrToByte(msgbody);

		DataCompressReport dataCompressReport = new DataCompressReport();
		try {
			int dstPos = 0;

			//  压缩消息长度   DWORD 
			byte [] lenbyte = ParseUtil.byteTobyte(data, dstPos, 4);
			int  len = ParseUtil.byteToint(lenbyte);
			dataCompressReport.setLen(len);
			dstPos += 4;

			// 压缩消息体
			byte [] msgbyte = ParseUtil.byteTobyte(data, dstPos,data.length-4);
			String msg = ParseUtil.parseByteToHexStr(msgbyte);
			dataCompressReport.setData(msg);

			return dataCompressReport;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析 数据压缩上报协议异常:"+e);
			return null;
		}

	}


	/**
	 * 解析 终端RSA公钥 (0x0A00)
	 * @param msgbody
	 * @return
	 */
	public static PublicKey decoderTerminalRsaPublicKey(String msgbody){
		byte [] data = ParseUtil.parseHexStrToByte(msgbody);

		PublicKey publicKey = new PublicKey();
		try {
			int dstPos = 0;

			// 平台RSA 公钥{e,n}中的e
			byte [] ebyte = ParseUtil.byteTobyte(data, dstPos, 4);
			int  e = ParseUtil.byteToint(ebyte);
			publicKey.setE(e);
			dstPos += 4;

			//RSA 公钥{e,n}中的n
			byte [] nbyte = ParseUtil.byteTobyte(data, dstPos,data.length-4);
			String n = ParseUtil.byteToString(nbyte, 0, nbyte.length);
			publicKey.setN(n);

			return publicKey;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析终端RSA公钥 协议异常:"+e);
			return null;
		}

	}


	/**
	 * 解析电召抢答(0x5104)
	 * @param msgbody
	 * @return
	 */
	public static CallInfo decoderAnswerCall(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			CallInfo callInfo = new CallInfo();
			int dstPos = 0;
			//订单号
			String orderid = ParseUtil.byteToString(data, dstPos, data.length);
			callInfo.setOrderid(orderid);
			return callInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析电召抢答协议异常:"+e);
			return null;
		}
	}

	/**
	 * 解析电召中心处理结果应答(0x5105)
	 * @param msgbody
	 * @return
	 */
	public static CallInfo decoderCentralProcessingResponse(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			CallInfo callInfo = new CallInfo();
			int dstPos = 0;
			//订单号
			byte[] orderidbyte = ParseUtil.byteTobyte(data, dstPos, data.length-1);
			String orderid = ParseUtil.byteToString(orderidbyte, 0, orderidbyte.length);
			callInfo.setOrderid(orderid);
			dstPos += orderidbyte.length;

			//处理结果
			int result = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			callInfo.setResult(result);
			dstPos += 1;
			return callInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析电召中心处理结果应答协议异常:"+e);
			return null;
		}
	}

	/**
	 * 执行电召 (0x5105)
	 * @param msgbody
	 * @return
	 */
	public static CallInfo decoderPerformCall(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {
			CallInfo callInfo = new CallInfo();
			int dstPos = 0;

			//订单号
			byte[] orderidbyte = ParseUtil.byteToSubStringToByte(data, dstPos, data.length);
			String orderid = ParseUtil.byteToString(orderidbyte, 0, orderidbyte.length);
			callInfo.setOrderid(orderid);
			dstPos += orderidbyte.length+1;

			//电召费
			byte[] callfeebyte = ParseUtil.byteToSubStringToByte(data, dstPos, data.length-dstPos);
			String callfee = ParseUtil.byteToString(callfeebyte, 0, callfeebyte.length);
			callInfo.setCallfee(callfee);
			dstPos += callfeebyte.length+1;

			//车牌号
			byte[] carnumbyte = ParseUtil.byteToSubStringToByte(data, dstPos, data.length-dstPos);
			String carnumber = ParseUtil.byteToString(carnumbyte, 0, carnumbyte.length);
			callInfo.setCarnumber(carnumber);
			dstPos += carnumbyte.length+1;


			//处理结果
			int result = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			callInfo.setResult(result);
			dstPos += 1;
			return callInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析执行电召协议异常:"+e);
			return null;
		}
	}


	/**
	 * 解析计价器开钥匙门信息(0x5108)
	 * @param msgbody
	 * @return
	 */
	public static TaximeterInfo decoderTaximeterMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);

		TaximeterInfo taximeterInfo = new TaximeterInfo();

		try {
			int dstPos = 0;
			//车牌号
			byte [] carnumberbyte = ParseUtil.byteTobyte(data, dstPos, (data.length-21));
			String carnumber = ParseUtil.byteToString(carnumberbyte, 0, carnumberbyte.length);
			taximeterInfo.setCarnum(carnumber);
			dstPos += carnumberbyte.length;
			//PSAM卡号
			String psam = ParseUtil.bcd2Str(ParseUtil.byteTobyte(data, dstPos, 6));
			taximeterInfo.setPsam(psam);
			dstPos += 6;
			//计价器设备号
			int taximeterno = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 4));
			taximeterInfo.setTaximeterno(taximeterno);
			dstPos +=4;
			//司机标识号
			int driverno = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 4));
			taximeterInfo.setDriverno(driverno);
			dstPos +=4;
			//卡类型生成日期
			String time = ParseUtil.bcd2Str(ParseUtil.byteTobyte(data, dstPos, 3));
			taximeterInfo.setTime(time);
			dstPos +=3;
			//黑名单版本号
			int blacklistver =  ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 2));
			taximeterInfo.setBlacklistver(blacklistver);
			dstPos +=2;
			//黑名单版本号
			int softever =  ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 2));
			taximeterInfo.setSoftever(softever);
			dstPos +=2;

			return taximeterInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析计价器开钥匙门信息协议异常:"+e);
			return null;
		}
	}

	/**
	 * 解析服务评价协议
	 * @param msgbody
	 * @return
	 */
	public static ServiceEvaluation decoderServiceEvaluationMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			ServiceEvaluation serviceEvaluation = new ServiceEvaluation();
			int dstPos = 0;
			//评价结果
			int result = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			serviceEvaluation.setResult(result);
			dstPos +=1;
			//不满意原因
			int reason = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			serviceEvaluation.setReason(reason);
			dstPos +=1;
			return serviceEvaluation;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析服务评价协议异常:"+e);
			return null;
		}

	}

	/**
	 * 解析上报终端功能使用次数协议
	 * @param msgbody
	 * @return
	 */
	public static TerminalUseInfo decoderTerminalUseMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			TerminalUseInfo terminalUseInfo = new TerminalUseInfo();
			int dstPos = 0;
			//功能使用次数
			byte [] numbertype = ParseUtil.byteTobyte(data, dstPos,data.length); 
			String number = ParseUtil.byteToString(numbertype, 0, numbertype.length);
			terminalUseInfo.setNumber(number);

			return terminalUseInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析上报终端功能使用次数协议异常:"+e);
			return null;
		}

	}

	/**
	 * 解析终端日使用上报
	 * @param msgbody
	 * @return
	 */
	public static TerminalUseInfo decoderTerminalDayUseMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			TerminalUseInfo terminalUseInfo = new TerminalUseInfo();
			int dstPos = 0;
			//功能使用次数
			String time = ParseUtil.bcd2Str(ParseUtil.byteTobyte(data, dstPos,6));
			terminalUseInfo.setTime(time);
			dstPos +=6;

			//功能类型
			int type = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos,1));
			terminalUseInfo.setType(type);
			dstPos +=1;

			//使用次数
			int num = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos,4));
			terminalUseInfo.setNum(num);
			dstPos +=4;

			//使用时长
			int usinglen = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos,4));
			terminalUseInfo.setUsinglen(usinglen);
			dstPos +=4;

			//使用流量
			int flow = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos,4));
			terminalUseInfo.setFlow(flow);

			return terminalUseInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析终端日使用上报协议异常:"+e);
			return null;
		}

	}

	/**
	 * 解析电池信息上报
	 * @param msgbody
	 * @return
	 */
	public static BatteryInfo decoderBatteryInfo(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			BatteryInfo batteryInfo = new BatteryInfo();
			int dstPos = 0;
			//订单号
			int voltage = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos,2));
			batteryInfo.setVoltage(voltage);
			return batteryInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析电池信息上报协议异常:"+e);
			return null;
		}
	}

	/**
	 * 解析电召抢答(0x5104)
	 * @param msgbody
	 * @return
	 */
	public static CallInfo decoderZjAnswerCall(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			CallInfo callInfo = new CallInfo();
			int dstPos = 0;
			//订单号
			int orderid = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos,4));
			callInfo.setOrderid(String.valueOf(orderid));
			return callInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析电召抢答协议异常:"+e);
			return null;
		}
	}

	/**
	 * 驾驶员取消电召
	 * @param msgbody
	 * @return
	 */
	public static CallInfo decoderZjCancelCall(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			CallInfo callInfo = new CallInfo();
			int dstPos = 0;
			//订单号
			int orderid = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos,4));
			callInfo.setOrderid(String.valueOf(orderid));
			dstPos+=4;
			//取消原因
			int reason =  ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos,1));
			callInfo.setReason(reason);
			return callInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析电召抢答协议异常:"+e);
			return null;
		}
	}

	/**
	 * 服务评价
	 * @param msgbody
	 * @return
	 */
	public static CallInfo decoderZjEvaluation(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			CallInfo callInfo = new CallInfo();
			int dstPos = 0;
			//评价时间
			String evaluationtime = ParseUtil.bcd2Str(ParseUtil.byteTobyte(data, dstPos,6));
			callInfo.setEvaluationtime(evaluationtime);
			dstPos+=6;

			//服务资格证号
			String sqcn = ParseUtil.byteToString(ParseUtil.byteTobyte(data, dstPos,19), 0, 19);
			dstPos+=19;
			callInfo.setSqcn(sqcn);

			//司机代码
			String drivercode = ParseUtil.bcd2Str(ParseUtil.byteTobyte(data, dstPos,6));
			callInfo.setDrivercode(drivercode);
			dstPos+=6;

			//评价内容
			int econtent =  ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos,1));
			callInfo.setEcontent(econtent);
			return callInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析服务评价协议异常:"+e);
			return null;
		}
	}


	/**
	 * 乘客人数上报
	 * @param msgbody
	 * @return
	 */
	public static CallInfo decoderNumberReported(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		int datalen = data.length;
		try {

			CallInfo callInfo = new CallInfo();
			int dstPos = 0;
			//评价时间
			String time = ParseUtil.bcd2Str(ParseUtil.byteTobyte(data, dstPos,6));
			callInfo.setTime(time);
			dstPos+=6;

			//人数
			int number =  ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos,1));
			callInfo.setNumber(number);
			dstPos+=1;

			//经度
			byte [] lngbyte = ParseUtil.byteTobyte(data, dstPos, 4);
			int lng = Integer.parseInt(ParseUtil.parseByteToHexStr(lngbyte),16);
			callInfo.setLng(lng);
			dstPos += 4;

			//纬度
			byte [] latbyte = ParseUtil.byteTobyte(data, dstPos, 4);
			int lat = Integer.parseInt(ParseUtil.parseByteToHexStr(latbyte),16);
			callInfo.setLat(lat);
			dstPos += 4;
			int len = datalen- dstPos;
			//业务流水号
			String serialnumber = ParseUtil.byteToString(ParseUtil.byteTobyte(data, dstPos,len), 0, len);
			callInfo.setSerialnumber(serialnumber);

			return callInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析服务评价协议异常:"+e);
			return null;
		}
	}


	/**
	 * 解析查询货源(0x7101)协议
	 * @param msgbody
	 * @return
	 */
	public static GateOrder decoderQueryGoodsMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			GateOrder orderInfo = new GateOrder();
			int dstPos = 0;
			//货源ID
			int sgid = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 2));
			orderInfo.setSgid(sgid);
			dstPos +=2;

			//订单ID
			byte[] orderidbyte = ParseUtil.byteToSubStringToByte(data, dstPos, data.length-dstPos);
			String orderid = ParseUtil.byteToString(orderidbyte, 0, orderidbyte.length);
			orderInfo.setOrderid(orderid);
			dstPos += orderidbyte.length+1;

			return orderInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析查询货源协议异常:"+e);
			return null;
		}

	}

	/**
	 * 解析确认订单(0x7102)协议
	 * @param msgbody
	 * @return
	 */
	public static GateOrder decoderConfirmOrderMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			GateOrder orderInfo = new GateOrder();
			int dstPos = 0;
			//订单结果
			int result = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			orderInfo.setResult(result);
			dstPos +=1;
			//订单ID
			String orderid = ParseUtil.byteToString(data, dstPos, data.length-dstPos);
			orderInfo.setOrderid(orderid);
			return orderInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析确认订单协议异常:"+e);
			return null;
		}

	}

	/**
	 * 已运到(0x7103)协议
	 * @param msgbody
	 * @return
	 */
	public static GateOrder decoderArriveOrderMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			GateOrder orderInfo = new GateOrder();
			int dstPos = 0;
			//纬度
			byte [] latbyte = ParseUtil.byteTobyte(data, dstPos, 4);
			int lat = Integer.parseInt(ParseUtil.parseByteToHexStr(latbyte),16);
			orderInfo.setLat(lat);
			dstPos += 4;

			//经度
			byte [] lngbyte = ParseUtil.byteTobyte(data, dstPos, 4);
			int lng = Integer.parseInt(ParseUtil.parseByteToHexStr(lngbyte),16);
			orderInfo.setLng(lng);
			dstPos += 4;

			//订单ID
			String orderid = ParseUtil.byteToString(data, dstPos, data.length-dstPos);
			orderInfo.setOrderid(orderid);
			return orderInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析已运到协议异常:"+e);
			return null;
		}

	}

	/**
	 * 解析查询订单(0x7104)协议
	 * @param msgbody
	 * @return
	 */
	public static GateOrder decoderQueryOrderMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			GateOrder orderInfo = new GateOrder();
			int dstPos = 0;
			//订单ID
			String orderid = ParseUtil.byteToString(data, dstPos, data.length);
			orderInfo.setOrderid(orderid);
			return orderInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析查询订单协议异常:"+e);
			return null;
		}

	}

	/**
	 * 解析投诉订单(0x7105)协议
	 * @param msgbody
	 * @return
	 */
	public static GateOrder decoderComplainOrderMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			GateOrder complainOrder = new GateOrder();
			int dstPos = 0;

			//投诉类型
			int type = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			complainOrder.setType(type);
			dstPos +=1;

			//订单ID
			byte[] orderidbyte = ParseUtil.byteToSubStringToByte(data, dstPos, data.length-dstPos);
			String orderid = ParseUtil.byteToString(orderidbyte, 0, orderidbyte.length);
			complainOrder.setOrderid(orderid);
			dstPos += orderidbyte.length+1;

			//投诉信息
			byte[] descbyte = ParseUtil.byteToSubStringToByte(data, dstPos, data.length-dstPos);
			String desc = ParseUtil.byteToString(descbyte, 0, descbyte.length);
			complainOrder.setDesc(desc);

			return complainOrder;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析投诉订单协议异常:"+e);
			return null;
		}

	}

	/**
	 * 解析查询收货联系方式(0x7107)
	 * @param msgbody
	 * @return
	 */
	public static GateOrder decoderQueryOrderReceiptMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			GateOrder orderInfo = new GateOrder();
			int dstPos = 0;

			//订单ID
			byte[] orderidbyte = ParseUtil.byteToSubStringToByte(data, dstPos, data.length-dstPos);
			String orderid = ParseUtil.byteToString(orderidbyte, 0, orderidbyte.length);
			orderInfo.setOrderid(orderid);

			return orderInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析查询收货联系方式协议异常:"+e);
			return null;
		}

	}

	/**
	 * 解析车源更新状态(0x710A)
	 * @param msgbody
	 * @return
	 */
	public static GateOrder decoderUpdateCarsourceStatusMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			GateOrder orderInfo = new GateOrder();
			int dstPos = 0;

			//车源ID
			int carsourceid = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 2));
			orderInfo.setCarsourceid(carsourceid);
			dstPos +=2;

			//生效状态
			int effect = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			orderInfo.setEffect(effect);
			return orderInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析车源更新状态协议异常:"+e);
			return null;
		}

	}

	/**
	 * 解析车源更新发布时间(0x710B)
	 * @param msgbody
	 * @return
	 */
	public static GateOrder decoderUpdateCarsourceTimeMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			GateOrder orderInfo = new GateOrder();
			int dstPos = 0;

			//车源ID
			int carsourceid = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 2));
			orderInfo.setCarsourceid(carsourceid);
			dstPos +=2;

			return orderInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析车源更新发布时间协议异常:"+e);
			return null;
		}

	}

	/**
	 * 解析发布车源(0x710C)
	 * @param msgbody
	 * @return
	 */
	public static CarSource decoderSendCarsourceMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			CarSource carSource = new CarSource();
			int dstPos = 0;

			//类型
			int type = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			carSource.setType(type);
			dstPos +=1;

			//用途
			int use = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			carSource.setUse(use);
			dstPos +=1;

			//是否加急
			int isexpedited = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			carSource.setIsexpedited(isexpedited);
			dstPos +=1;

			//运输路线
			int transportline = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			carSource.setTransportline(transportline);
			dstPos +=1;

			//出发省份
			String sprovince = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 4))+"";
			carSource.setSprovince(sprovince);
			dstPos +=4;

			//出发城市
			String scity = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 4))+"";
			carSource.setScity(scity);
			dstPos +=4;

			//出发区
			String sdistrict = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 4))+"";
			carSource.setSdistrict(sdistrict);
			dstPos +=4;

			//目的地省份
			String eprovince = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 4))+"";
			carSource.setEprovince(eprovince);
			dstPos +=4;

			//目的地城市
			String ecity = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 4))+"";
			carSource.setEcity(ecity);
			dstPos +=4;

			//目的区
			String edistrict = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 4))+"";
			carSource.setEdistrict(edistrict);

			return carSource;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析发布车源协议异常:"+e);
			return null;
		}

	}

	/**
	 * 解析回程状态记录(0x710D)
	 * @param msgbody
	 * @return
	 */
	public static ReturnRecord decoderReturnRecordMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			ReturnRecord returnRecord = new ReturnRecord();
			int dstPos = 0;

			//状态
			int status = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 1));
			returnRecord.setStatus(status);
			dstPos +=1;

			//装货、卸货重量
			float weight = ParseUtil.byteToint(ParseUtil.byteTobyte(data, dstPos, 2));
			returnRecord.setWeight(weight);
			dstPos +=2;

			//纬度
			int lat = Integer.parseInt(ParseUtil.parseByteToHexStr((ParseUtil.byteTobyte(data, dstPos, 4))), 16);
			returnRecord.setLat(lat);
			dstPos +=4;

			//经度
			int lng = Integer.parseInt(ParseUtil.parseByteToHexStr((ParseUtil.byteTobyte(data, dstPos, 4))), 16);
			returnRecord.setLng(lng);

			return returnRecord;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析回程状态记录协议异常:"+e);
			return null;
		}

	}

	/**
	 * 冷链阀值和温度(0x710E)
	 * @param msgbody
	 * @return
	 */
	public static WarnInfo decoderWarnInfoMsg(String msgbody){

		byte [] data = ParseUtil.parseHexStrToByte(msgbody);
		try {

			WarnInfo warnInfo = new WarnInfo();
			int dstPos = 0;

			//正常阀值
			byte[] normalbyte = ParseUtil.byteToSubStringToByte(data, dstPos, data.length-dstPos);
			String normal = ParseUtil.byteToString(normalbyte, 0, normalbyte.length);
			warnInfo.setNormal(normal);
			dstPos += normalbyte.length+1;

			//警告阀值
			byte[] warnbyte = ParseUtil.byteToSubStringToByte(data, dstPos, data.length-dstPos);
			String warn = ParseUtil.byteToString(warnbyte, 0, warnbyte.length);
			warnInfo.setWarn(warn);
			dstPos += warnbyte.length+1;

			//报警阀值
			byte[] alarmbyte = ParseUtil.byteToSubStringToByte(data, dstPos, data.length-dstPos);
			String alarm = ParseUtil.byteToString(alarmbyte, 0, alarmbyte.length);
			warnInfo.setAlarm(alarm);
			dstPos += alarmbyte.length+1;

			//当前温度
			byte[] temperaturebyte = ParseUtil.byteToSubStringToByte(data, dstPos, data.length-dstPos);
			String temperature = ParseUtil.byteToString(temperaturebyte, 0, temperaturebyte.length);
			warnInfo.setTemperature(temperature);

			return warnInfo;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			logger.info("解析冷链阀值和温度协议异常:"+e);
			return null;
		}

	}

}
