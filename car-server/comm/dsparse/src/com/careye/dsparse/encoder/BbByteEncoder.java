/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.encoder;

import java.util.List;

import org.apache.log4j.Logger;

import com.careye.dsparse.bbdomain.ArtificialConfirmedAlarm;
import com.careye.dsparse.bbdomain.CallInfo;
import com.careye.dsparse.bbdomain.CameraCommandInfo;
import com.careye.dsparse.bbdomain.CircleArea;
import com.careye.dsparse.bbdomain.CircleAreaItems;
import com.careye.dsparse.bbdomain.DataTransmission;
import com.careye.dsparse.bbdomain.EventSet;
import com.careye.dsparse.bbdomain.FileInfo;
import com.careye.dsparse.bbdomain.GoodSourceInfo;
import com.careye.dsparse.bbdomain.InfoDemandMenu;
import com.careye.dsparse.bbdomain.IntegralIntegrity;
import com.careye.dsparse.bbdomain.LineInfo;
import com.careye.dsparse.bbdomain.LineInfoItems;
import com.careye.dsparse.bbdomain.MulMediaDataLoadResponse;
import com.careye.dsparse.bbdomain.MyOptions;
import com.careye.dsparse.bbdomain.MyProfile;
import com.careye.dsparse.bbdomain.NoticeAcceptGoods;
import com.careye.dsparse.bbdomain.NotifyingMessage;
import com.careye.dsparse.bbdomain.OperationNotice;
import com.careye.dsparse.bbdomain.OptionsAvailableStaUpToReturn;
import com.careye.dsparse.bbdomain.OptionsAvailableUpTimeToReturn;
import com.careye.dsparse.bbdomain.OrderInfo;
import com.careye.dsparse.bbdomain.PaymentNotice;
import com.careye.dsparse.bbdomain.PhoneBook;
import com.careye.dsparse.bbdomain.PoiInfo;
import com.careye.dsparse.bbdomain.PolygonArea;
import com.careye.dsparse.bbdomain.PolygonAreaItems;
import com.careye.dsparse.bbdomain.PublicKey;
import com.careye.dsparse.bbdomain.QuestionsSend;
import com.careye.dsparse.bbdomain.ReceivingContact;
import com.careye.dsparse.bbdomain.RecordStartCommand;
import com.careye.dsparse.bbdomain.RectangleArea;
import com.careye.dsparse.bbdomain.RectangleAreaItems;
import com.careye.dsparse.bbdomain.RegisterResponse;
import com.careye.dsparse.bbdomain.SecurityCode;
import com.careye.dsparse.bbdomain.StoraMulMediaRetrieval;
import com.careye.dsparse.bbdomain.TempPoiFollowUpControl;
import com.careye.dsparse.bbdomain.TerminalControl;
import com.careye.dsparse.bbdomain.TerminalParameter;
import com.careye.dsparse.bbdomain.TerminalUpdate;
import com.careye.dsparse.bbdomain.TextInfo;
import com.careye.dsparse.bbdomain.TravelingRecorder;
import com.careye.dsparse.bbdomain.VehicleControl;
import com.careye.dsparse.bbdomain.WifiInfo;
import com.careye.dsparse.utlis.DateUtil;
import com.careye.dsparse.utlis.ExceptionUtil;
import com.careye.dsparse.utlis.ParseUtil;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：ByteEncoder    
 * 类描述：组装部标二进制协议    
 * 创建人：zr    
 * 创建时间：2015-5-14 下午04:10:30    
 * 修改人：zr    
 * 修改时间：2015-5-14 下午04:10:30    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BbByteEncoder {
	private final static Logger logger = Logger.getLogger(BbByteEncoder.class);

	/**
	 * 编码终端注册应答
	 * @return
	 */
	public static String encoderRegisterResponse(RegisterResponse registerResponse){

		try {
			if(registerResponse != null){
				byte [] passwdbyte = ParseUtil.stringToByte(registerResponse.getPasswd());
				int len = passwdbyte.length;

				byte[] bytes = new byte[3+len];	
				int dstPos = 0;
				//应答流水号
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(registerResponse.getSeqR(),2)), 0, bytes, dstPos, 2);
				dstPos+=2;
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(registerResponse.getResult(),1)), 0, bytes, dstPos, 1);
				dstPos+=1;
				System.arraycopy(passwdbyte, 0, bytes, dstPos, len);
				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("终端注册应答: ["+bodymsg+"]");
				return bodymsg;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 编码设置终端参数
	 * @return
	 */
	public static String encoderSetTerminalParameter(TerminalParameter terminalParameter){

		try {
			if(terminalParameter != null){
				int count = terminalParameter.getCount();
				int len = BbByteEncoderUtil.getTerminalParLen(terminalParameter.getItems()) + count*5;

				byte[] bytes = new byte[1+len];	
				int dstPos = 0;
				//参数总数
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(count,1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				//参数设置数据包
				byte [] bodyparm = BbByteEncoderUtil.getTerminalParByte(terminalParameter.getItems(), len);
				if(bodyparm != null){
					System.arraycopy(bodyparm, 0, bytes, dstPos, len);
				}

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("设置终端参数: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 编码终端控制
	 * @return
	 */
	public static String encoderTerminalControl(TerminalControl terminalControl){
		try {
			if(terminalControl != null){
				int len = 0;

				int cmd = terminalControl.getId();
				String data = terminalControl.getSize();

				String valuehex = BbByteEncoderUtil.getTerminalControl(data, cmd);

				if(valuehex != null){
					len = ParseUtil.parseHexStrToByte(valuehex).length;
				}

				byte[] bytes = new byte[1+len];	
				int dstPos = 0;
				//命令字 (type)
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(terminalControl.getId(),1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				//当命令字为1或者2的时候，进行处理
				if(valuehex != null){
					System.arraycopy(ParseUtil.parseHexStrToByte(valuehex), 0, bytes, dstPos, len);
				}

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("设置终端控制 : ["+bodymsg+"]");
				return bodymsg;
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 编码查询指定终端参数
	 * @return
	 */
	public static String encoderQuerySpecifyingTerminalParam(TerminalParameter terminalParameter){

		try {

			if(terminalParameter != null){
				int count = terminalParameter.getCount();
				int len = count*4;

				byte[] bytes = new byte[1+len];	
				int dstPos = 0;
				//参数总数
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(count,1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				List<Integer> list = terminalParameter.getInititems();
				for (Integer integer : list) {
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(integer,4)), 0, bytes, dstPos, 4);
					dstPos+=4;
				}

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("查询指定终端参数: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 编码终端升级包
	 * @return
	 */
	public static String encoderSetTerminalUpdate(TerminalUpdate terminalUpdate){

		try {
			if(terminalUpdate != null){

				//版本号
				byte [] versionbyte = ParseUtil.stringToByte(terminalUpdate.getVersion());
				//版本号长度
				int versionlen = versionbyte.length;

				//升级数据包
				byte [] databyte = ParseUtil.stringToByte(terminalUpdate.getData());
				//升级数据包长度
				int datalen = databyte.length;

				byte[] bytes = new byte[11+versionlen+datalen];	
				int dstPos = 0;
				//升级类型
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(terminalUpdate.getType(),1)), 0, bytes, dstPos, 1);
				dstPos += 1;

				//制造商 ID
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(terminalUpdate.getOem(),5)), 0, bytes, dstPos, 5);
				dstPos += 5;

				//版本号长度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(versionlen,1)), 0, bytes, dstPos, 1);
				dstPos += 1;

				//版本号
				System.arraycopy(versionbyte, 0, bytes, dstPos,versionlen);
				dstPos += versionlen;

				//升级数据包长度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(datalen,4)), 0, bytes, dstPos,4);
				dstPos += 4;

				//升级数据包
				System.arraycopy(databyte, 0, bytes, dstPos, datalen);

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("下发终端升级包: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 编码临时位置跟踪控制
	 * @return
	 */
	public static String encoderTempPoiFollowUpControlKey(TempPoiFollowUpControl tempPoiFollowUpControl){

		try {
			byte[] bytes = new byte[6];	
			int dstPos = 0;
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(tempPoiFollowUpControl.getInv(),2)), 0, bytes, dstPos,2);
			dstPos+=2;
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(tempPoiFollowUpControl.getExpire(),4)), 0, bytes, dstPos,4);
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("临时位置跟踪控制  : ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码人工确认报警消息
	 * @return
	 */
	public static String encoderArtificialConfirmedAlarmKey(ArtificialConfirmedAlarm artificialConfirmedAlarm){

		try {
			if(artificialConfirmedAlarm != null){
				byte[] bytes = new byte[6];	
				int dstPos = 0;

				// 报警消息流水号 WORD
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(artificialConfirmedAlarm.getSeqR(),2)), 0, bytes, dstPos,2);
				dstPos += 2;

				byte[] typebyte = new byte[4];
				StringBuffer type1 = new StringBuffer();
				type1.append("000");
				type1.append(artificialConfirmedAlarm.getDisplacement());
				type1.append(artificialConfirmedAlarm.getIgnite());
				type1.append("000");
				typebyte[0] = ParseUtil.bitToByte(type1.toString());

				StringBuffer type2 = new StringBuffer();
				type2.append("0");
				type2.append(artificialConfirmedAlarm.getDriving());
				type2.append(artificialConfirmedAlarm.getLine());
				type2.append(artificialConfirmedAlarm.getArea());
				type2.append("0000");
				typebyte[1] = ParseUtil.bitToByte(type2.toString());

				StringBuffer type3 = new StringBuffer();
				type3.append("00000000");
				typebyte[2] = ParseUtil.bitToByte(type3.toString());

				StringBuffer type4 = new StringBuffer();
				type4.append("0000");
				type4.append(artificialConfirmedAlarm.getWarn());
				type4.append("00");
				type4.append(artificialConfirmedAlarm.getUrgency());
				typebyte[3] = ParseUtil.bitToByte(type4.toString());

				// 人工确认报警类型   DWORD 
				System.arraycopy(typebyte, 0, bytes, dstPos,4);
				dstPos+=4;

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("人工确认报警消息  : ["+bodymsg+"]");
				return bodymsg;
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 文本信息下发
	 * @param textInfo
	 * @return
	 */
	public static String encoderTextInfo(TextInfo textInfo){

		try {
			//文本信息
			byte [] contentbyte = ParseUtil.stringToByte(textInfo.getContent());
			int contentslen = contentbyte.length;
			byte[] bytes = new byte[1+contentslen];	

			int dstPos = 0;
			//标志
			StringBuffer strBuffer = new StringBuffer();
			strBuffer.append("0");
			strBuffer.append("0");
			strBuffer.append(textInfo.getAction());
			strBuffer.append(textInfo.getAdv());
			strBuffer.append(textInfo.getTts());
			strBuffer.append(textInfo.getLcd());
			strBuffer.append("0");
			strBuffer.append(textInfo.getEmergency());
			byte[] state = new byte[1];
			state[0] = ParseUtil.bitToByte(strBuffer.toString());
			System.arraycopy(state, 0, bytes, dstPos, 1);
			dstPos += 1;
			//文本信息
			System.arraycopy(contentbyte, 0, bytes, dstPos, contentslen);
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码文本信息下发: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 普通文本信息
	 * @param textInfo
	 * @return
	 */
	public static String encoderCommonTextInfo(TextInfo textInfo){

		try {
			//文本信息
			byte [] contentbyte = ParseUtil.stringToByte(textInfo.getContent());
			int contentslen = contentbyte.length;
			byte[] bytes = new byte[2+contentslen];	

			int dstPos = 0;

			//消息类型
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(textInfo.getType(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			//消息长度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(contentslen,1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			//文本信息
			System.arraycopy(contentbyte, 0, bytes, dstPos, contentslen);
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码普通文本信息下发: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 信息推送
	 * @param textInfo
	 * @return
	 */
	public static String encoderInfoPush(TextInfo textInfo){

		try {
			if(textInfo.getTel() == null){
				textInfo.setTel("");
			}
			//电话号码
			byte [] telbyte = ParseUtil.stringToByte(textInfo.getTel());
			int tellen = telbyte.length;

			if(textInfo.getPhone() == null){
				textInfo.setPhone("");
			}

			//电话号码
			byte [] phonebyte = ParseUtil.stringToByte(textInfo.getPhone());
			int phonelen = phonebyte.length;

			//文本信息
			byte [] contentbyte = ParseUtil.stringToByte(textInfo.getContent());
			int contentslen = contentbyte.length;
			byte[] bytes = new byte[2+contentslen+tellen+phonelen];	

			int dstPos = 0;

			//电话号码
			System.arraycopy(telbyte, 0, bytes, dstPos, tellen);
			dstPos+=tellen;


			//手机号码
			System.arraycopy(phonebyte, 0, bytes, dstPos, phonelen);
			dstPos+=phonelen;

			//消息长度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(contentslen,1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			//文本信息
			System.arraycopy(contentbyte, 0, bytes, dstPos, contentslen);
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码普通文本信息下发: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码事件设置
	 * @return
	 */
	public static String encoderEventSet(EventSet eventSet){

		try {
			if(eventSet != null){
				int count = eventSet.getCount();
				int len = BbByteEncoderUtil.getEventItemLen(eventSet.getItems()) + 2*count;

				byte[] bytes = new byte[2+len];	
				int dstPos = 0;
				//设置类型 
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(eventSet.getType(),1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				//设置总数 
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(count,1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				if(eventSet.getItems().size() >0){
					// 事件项列表 
					byte [] bodyparm = BbByteEncoderUtil.getEventSetByte(eventSet.getItems(), len);

					if(bodyparm != null){
						System.arraycopy(bodyparm, 0, bytes, dstPos, len);
					}
				}

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("设置终端参数: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码信息点播菜单设置
	 * @param infoDemandMenu
	 * @return
	 */
	public static String encoderInfoDemandMenu(InfoDemandMenu infoDemandMenu){

		try {
			if(infoDemandMenu != null){

				int count = infoDemandMenu.getCount();
				int len = BbByteEncoderUtil.getInfoDemandMenuLen(infoDemandMenu.getItems()) + 3*count;

				byte[] bytes = new byte[2+len];	
				int dstPos = 0;

				//设置类型
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(infoDemandMenu.getType(),1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				//设置总数 
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(count,1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				// 事件项列表 
				byte [] bodyparm = BbByteEncoderUtil.getInfoDemandMenuByte(infoDemandMenu.getItems(), len);

				if(bodyparm != null){
					System.arraycopy(bodyparm, 0, bytes, dstPos, len);
				}

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("信息点播菜单设置: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 编码设置电话本
	 * @param phoneBook
	 * @return
	 */
	public static String encoderPhoneBook(PhoneBook phoneBook){

		try {
			if(phoneBook != null){

				int count = phoneBook.getCount();
				int len = BbByteEncoderUtil.getPhoneBookLen(phoneBook.getItems()) + 3*count;

				byte[] bytes = new byte[2+len];	
				int dstPos = 0;

				//设置类型
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(phoneBook.getType(),1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				//设置总数 
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(count,1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				if(phoneBook.getItems().size() > 0){
					// 事件项列表 
					byte [] bodyparm = BbByteEncoderUtil.getPhoneBookByte(phoneBook.getItems(), len);

					if(bodyparm != null){
						System.arraycopy(bodyparm, 0, bytes, dstPos, len);
					}
				}

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("设置电话本0x8401: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 编码设置圆形区域
	 * @param circleArea
	 * @return
	 */
	public static String encoderCircleArea(CircleArea circleArea){

		try {
			if(circleArea != null){

				int count = circleArea.getCount();
				int len = BbByteEncoderUtil.getCircleAreaLen(circleArea.getItems())*count;


				byte[] bytes = new byte[2+len];	
				int dstPos = 0;

				//设置属性
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(circleArea.getUpdate(),1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				//设置总数 
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(count,1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				if(circleArea.getItems().size() >0){
					// 区域项 
					byte [] bodyparm = BbByteEncoderUtil.getCircleAreaByte(circleArea.getItems(), len);

					if(bodyparm != null){
						System.arraycopy(bodyparm, 0, bytes, dstPos, len);
					}
				}


				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("设置圆形区域0x8600: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 编码删除圆形区域
	 * @param circleArea
	 * @return
	 */
	public static String encoderDeleteCircleArea(CircleArea circleArea){

		try {
			if(circleArea != null){

				int count = circleArea.getCount();
				int len = 4*count;

				byte[] bytes = new byte[1+len];	
				int dstPos = 0;

				//区域总数 
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(count,1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				List<CircleAreaItems> list = circleArea.getItems();
				for (CircleAreaItems cItems : list) {
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cItems.getAreaId(),4)), 0, bytes, dstPos, 4);
					dstPos+=4;
				}

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("删除圆形区域0x8601: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}



	/**
	 * 编码设置矩形区域
	 * @param rectangleArea
	 * @return
	 */
	public static String encoderRectangleArea(RectangleArea rectangleArea){

		try {
			if(rectangleArea != null){

				int count = rectangleArea.getCount();
				int len = BbByteEncoderUtil.getRectangleAreaLen(rectangleArea.getItems())*count;

				byte[] bytes = new byte[2+len];	
				int dstPos = 0;

				//设置属性
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(rectangleArea.getUpdate(),1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				//设置总数 
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(count,1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				if(rectangleArea.getItems().size() > 0){
					// 区域项 
					byte [] bodyparm = BbByteEncoderUtil.getRectangleAreaByte(rectangleArea.getItems(), len);

					if(bodyparm != null){
						System.arraycopy(bodyparm, 0, bytes, dstPos, len);
					}
				}

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("设置矩形区域0x8602: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码删除矩形区域
	 * @param rectangleArea
	 * @return
	 */
	public static String encoderDeleteRectangleArea(RectangleArea rectangleArea){

		try {
			if(rectangleArea != null){

				int count = rectangleArea.getCount();
				int len = 4*count;

				byte[] bytes = new byte[1+len];	
				int dstPos = 0;

				//区域总数 
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(count,1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				List<RectangleAreaItems> list = rectangleArea.getItems();
				for (RectangleAreaItems rItems : list) {
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(rItems.getAreaId(),4)), 0, bytes, dstPos, 4);
					dstPos+=4;
				}

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("删除矩形区域0x8603: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 编码设置多边形区域
	 * @param polygonArea
	 * @return
	 */
	public static String encoderPolygonArea(PolygonArea polygonArea){

		try {
			if(polygonArea != null){

				int count = polygonArea.getCount();
				int len = 8*count;
				int length = 8+len;
				if(polygonArea.getAttr0() != 0){
					length += 12;
				}

				if(polygonArea.getAttr0() != 0){
					length += 3;
				}

				byte[] bytes = new byte[length];	
				int dstPos = 0;

				//区域ID
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(polygonArea.getAreaId(),4)), 0, bytes, dstPos, 4);
				dstPos+=4;

				//区域属性
				byte[] attrbyte = new byte[2];
				StringBuffer attrBuffer = new StringBuffer();
				attrBuffer.append(polygonArea.getAttr7());
				attrBuffer.append(polygonArea.getAttr6());
				attrBuffer.append(polygonArea.getAttr5());
				attrBuffer.append(polygonArea.getAttr4());
				attrBuffer.append(polygonArea.getAttr3());
				attrBuffer.append(polygonArea.getAttr2());
				attrBuffer.append(polygonArea.getAttr1());
				attrBuffer.append(polygonArea.getAttr0());
				attrbyte[0] = ParseUtil.bitToByte("00000000");
				attrbyte[1] = ParseUtil.bitToByte(attrBuffer.toString());

				//区域属性
				System.arraycopy(attrbyte, 0, bytes, dstPos, 2);
				dstPos += 2;

				if(polygonArea.getAttr0() != 0){
					//起始时间
					System.arraycopy(ParseUtil.str2Bcd(polygonArea.getTimeS()), 0, bytes, dstPos, 6);
					dstPos += 6;

					//起始时间
					System.arraycopy(ParseUtil.str2Bcd(polygonArea.getTimeE()), 0, bytes, dstPos, 6);
					dstPos += 6;
				}

				if(polygonArea.getAttr1() != 0){
					//最高速度
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(polygonArea.getSpeedLimit(),2)), 0, bytes, dstPos, 2);
					dstPos += 2;

					//超速持续时间
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(polygonArea.getSpeedTime(),1)), 0, bytes, dstPos, 1);
					dstPos += 1;
				}
				//区域总顶点数
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(count,1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				//顶点项
				List<PolygonAreaItems> list = polygonArea.getItems();
				for (PolygonAreaItems pItems : list) {
					//顶点纬度
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(pItems.getLat(),4)), 0, bytes, dstPos, 4);
					dstPos+=4;
					//顶点经度
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(pItems.getLng(),4)), 0, bytes, dstPos, 4);
					dstPos+=4;
				}

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("设置多边形区域0x8604: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 编码删除多边形区域
	 * @param polygonArea
	 * @return
	 */
	public static String encoderDeletePolygonArea(PolygonArea polygonArea){

		try {
			if(polygonArea != null){

				int count = polygonArea.getCount();
				int len = 4*count;

				byte[] bytes = new byte[1+len];	
				int dstPos = 0;

				//区域总数 
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(count,1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				List<PolygonAreaItems> list = polygonArea.getItems();
				for (PolygonAreaItems pItems : list) {
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(pItems.getAreaId(),4)), 0, bytes, dstPos, 4);
					dstPos+=4;
				}
				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("删除多边形区域0x8605: ["+bodymsg+"]");
				return bodymsg;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码设置线路
	 * @param lineInfo
	 * @return
	 */
	public static String encoderLineInfo(LineInfo lineInfo){

		try {
			if(lineInfo != null){

				int count = lineInfo.getCount();
				int len = BbByteEncoderUtil.getLineInfoLen(lineInfo.getItems())*count;
				int length = 8+len;

				if(lineInfo.getRattr0() != 0){
					length += 12;
				}
				byte[] bytes = new byte[length];	
				int dstPos = 0;

				//路线ID
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(lineInfo.getRid(),4)), 0, bytes, dstPos, 4);
				dstPos+=4;

				//区域属性
				byte[] attrbyte = new byte[2];
				StringBuffer attrBuffer = new StringBuffer();
				attrBuffer.append("00");
				attrBuffer.append(lineInfo.getRattr5());
				attrBuffer.append(lineInfo.getRattr4());
				attrBuffer.append(lineInfo.getRattr3());
				attrBuffer.append(lineInfo.getRattr4());
				attrBuffer.append(lineInfo.getRattr1());
				attrBuffer.append(lineInfo.getRattr0());
				attrbyte[0] = ParseUtil.bitToByte("00000000");
				attrbyte[1] = ParseUtil.bitToByte(attrBuffer.toString());

				//路线属性
				System.arraycopy(attrbyte, 0, bytes, dstPos, 2);
				dstPos += 2;

				if(lineInfo.getRattr0() != 0){
					//起始时间
					System.arraycopy(ParseUtil.str2Bcd(lineInfo.getSdate()), 0, bytes, dstPos, 6);
					dstPos += 6;

					//起始时间
					System.arraycopy(ParseUtil.str2Bcd(lineInfo.getEdate()), 0, bytes, dstPos, 6);
					dstPos += 6;
				}

				//路线总拐点数
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(count,2)), 0, bytes, dstPos, 2);
				dstPos+=2;

				//顶点项
				List<LineInfoItems> list = lineInfo.getItems();
				for (LineInfoItems lItems : list) {
					//拐点ID
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(lItems.getIid(),4)), 0, bytes, dstPos, 4);
					dstPos+=4;
					//路段ID
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(lItems.getLid(),4)), 0, bytes, dstPos, 4);
					dstPos+=4;

					//拐点纬度
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(lItems.getLat(),4)), 0, bytes, dstPos, 4);
					dstPos+=4;

					//拐点经度
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(lItems.getLng(),4)), 0, bytes, dstPos, 4);
					dstPos+=4;

					//路段宽度
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(lItems.getWidth(),1)), 0, bytes, dstPos, 1);
					dstPos+=1;

					//区域属性
					byte[] lattrbyte = new byte[1];
					StringBuffer lattrBuffer = new StringBuffer();
					lattrBuffer.append("0000");
					lattrBuffer.append(lineInfo.getRattr3());
					lattrBuffer.append(lineInfo.getRattr4());
					lattrBuffer.append(lineInfo.getRattr1());
					lattrBuffer.append(lineInfo.getRattr0());
					attrbyte[0] = ParseUtil.bitToByte(lattrBuffer.toString());
					//路段属性
					System.arraycopy(lattrbyte, 0, bytes, dstPos, 1);
					dstPos += 1;
					if(lItems.getLattr0() != 0){
						//路段行驶时长阀值
						System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(lItems.getRtime(),2)), 0, bytes, dstPos, 2);
						dstPos+=2;

						//路段行驶不足阀值
						System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(lItems.getStime(),2)), 0, bytes, dstPos, 2);
						dstPos+=2;
					}

					if(lItems.getLattr1() != 0){
						//路段最高速度
						System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(lItems.getTspeed(),2)), 0, bytes, dstPos, 2);
						dstPos+=2;

						//路段超速持续时问
						System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(lItems.getPspeed(),1)), 0, bytes, dstPos, 1);
						dstPos+=1;
					}
				}

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("设置线路0x8606: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码删除线路
	 * @param lineInfo
	 * @return
	 */
	public static String encoderDeleteLineInfo(LineInfo lineInfo){

		try {
			if(lineInfo != null){

				int count = lineInfo.getCount();
				int len = 4*count;

				byte[] bytes = new byte[1+len];	
				int dstPos = 0;

				//路线数
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(count,1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				//路线ID1
				List<LineInfoItems> list = lineInfo.getItems();
				for (LineInfoItems lItems : list) {
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(lItems.getId(),4)), 0, bytes, dstPos, 4);
					dstPos+=4;
				}
				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("删除线路0x8607: ["+bodymsg+"]");
				return bodymsg;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码多媒体数据上传应答
	 * @return
	 */
	public static String encoderMmdlResponse(MulMediaDataLoadResponse mmdlResponse){

		try {
			if(mmdlResponse != null){

				int count = mmdlResponse.getPacketSum();

				byte[] bytes = new byte[5+4*count];	
				int dstPos = 0;
				//多媒体ID 
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(mmdlResponse.getDataId(),4)), 0, bytes, dstPos,4);
				dstPos += 4;
				//重传包总数 
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(count,1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				//重传包 ID 列表 
				List<Integer> list = mmdlResponse.getIds();
				if(list != null){
					for (Integer id : list) {
						//重传包ID 列表
						System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(id,4)), 0, bytes, dstPos, 4);
						dstPos+=4;
					}
				}
				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("多媒体数据上传应答: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码摄像头立即拍摄命令
	 * @return
	 */
	public static String encoderCameraCommandInfo(CameraCommandInfo cameraCommandInfo){

		try {

			byte[] bytes = new byte[12];	
			int dstPos = 0;
			//通道 ID 
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cameraCommandInfo.getChannel(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//拍摄命令
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cameraCommandInfo.getCmd(),2)), 0, bytes, dstPos,2);
			dstPos+=2;
			//拍照间隔/录像时间
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cameraCommandInfo.getInv(),2)), 0, bytes, dstPos,2);
			dstPos+=2;
			//保存标志
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cameraCommandInfo.getSave(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//分辨率
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cameraCommandInfo.getScreen(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//图像/视频质量
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cameraCommandInfo.getQuality(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//亮度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cameraCommandInfo.getBright(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//对比度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cameraCommandInfo.getContrast(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//饱和度 
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cameraCommandInfo.getSaturation(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//色度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cameraCommandInfo.getChroma(),1)), 0, bytes, dstPos,1);
			dstPos+=1;


			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("存储多媒体数据检索 : ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 行驶记录数据采集命令、行驶记录参数下传命令
	 * @param travelingRecorder
	 * @param type 1  行驶记录数据采集命令 2 行驶记录参数下传命令
	 * @return
	 */
	public static String encoderTravelingRecorder(TravelingRecorder travelingRecorder,int type){

		try {
			byte[] dataByte = null;
			int len = 0;
			if(type == 2){
				dataByte = ParseUtil.parseHexStrToByte(travelingRecorder.getData());
				len = dataByte.length;
			}

			byte[] bytes = new byte[1+len];	
			int dstPos = 0;

			//命令字
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(travelingRecorder.getCmd(),1)), 0, bytes, dstPos,1);
			dstPos+=1;

			if(type == 2){
				System.arraycopy(dataByte,0, bytes, dstPos, len);
				dstPos+=len;
			}

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("行驶记录数据采集命令、行驶记录参数下传命令 : ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}



	/**
	 * 编码存储多媒体数据检索
	 * @return
	 */
	public static String encoderStoraMulMediaRetrieval(StoraMulMediaRetrieval storaMulMediaRetrieval){

		try {

			byte[] bytes = new byte[15];	
			int dstPos = 0;
			//多媒体类型
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(storaMulMediaRetrieval.getType(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//通道ID
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(storaMulMediaRetrieval.getId(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//事件项编码
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(storaMulMediaRetrieval.getFmt(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//起始时间
			System.arraycopy(ParseUtil.str2Bcd(storaMulMediaRetrieval.getStarttime()), 0, bytes, dstPos, 6);
			dstPos+=6;
			//结束时间
			System.arraycopy(ParseUtil.str2Bcd(storaMulMediaRetrieval.getEndtime()), 0, bytes, dstPos, 6);
			dstPos+=6;

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("存储多媒体数据检索 : ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码存储多媒体数据上传命令
	 * @return
	 */
	public static String encoderStoraMulMediaUpload(StoraMulMediaRetrieval storaMulMediaRetrieval){

		try {

			byte[] bytes = new byte[16];	
			int dstPos = 0;
			//多媒体类型
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(storaMulMediaRetrieval.getType(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//通道ID
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(storaMulMediaRetrieval.getId(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//事件项编码
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(storaMulMediaRetrieval.getFmt(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//起始时间
			System.arraycopy(ParseUtil.str2Bcd(storaMulMediaRetrieval.getStarttime()), 0, bytes, dstPos, 6);
			dstPos+=6;
			//结束时间
			System.arraycopy(ParseUtil.str2Bcd(storaMulMediaRetrieval.getEndtime()), 0, bytes, dstPos, 6);
			dstPos+=6;
			//删除标志
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(storaMulMediaRetrieval.getFlg(),1)), 0, bytes, dstPos,1);
			dstPos+=1;

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("存储多媒体数据上传命令 : ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码单条存储多媒体数据检索上传命令
	 * @return
	 */
	public static String encoderOneStoraMulMediaUpload(StoraMulMediaRetrieval storaMulMediaRetrieval){

		try {

			byte[] bytes = new byte[16];	
			int dstPos = 0;
			//多媒体ID
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(storaMulMediaRetrieval.getMediaid(),4)), 0, bytes, dstPos,4);
			dstPos+=4;
			//删除标志
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(storaMulMediaRetrieval.getFlg(),1)), 0, bytes, dstPos,1);
			dstPos+=1;

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("单条存储多媒体数据检索上传命令 : ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码数据下行透传
	 * @return
	 */
	public static String encoderDataTransmission(DataTransmission dataTransmission){


		try {
			byte [] databyte = ParseUtil.parseHexStrToByte(dataTransmission.getData());
			int len = databyte.length;

			byte[] bytes = new byte[1+len];	
			int dstPos = 0;

			//透传消息类型
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(dataTransmission.getType(),1)), 0, bytes, dstPos,1);
			dstPos+=1;

			//透传消息内容
			System.arraycopy(databyte, 0, bytes, dstPos, len);
			dstPos+=len;

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码数据下行透传 : ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码平台 RSA 公钥
	 * @return
	 */
	public static String encoderPlatformRsaPublicKey(PublicKey publicKey){

		try {
			byte []  nbyte = (publicKey.getN()).getBytes("GBK");
			int len = nbyte.length;

			byte[] bytes = new byte[4+len];	
			int dstPos = 0;
			//e  DWORD
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(publicKey.getE(),4)), 0, bytes, dstPos,4);
			dstPos+=4;

			//n  BYTE[128]
			System.arraycopy(nbyte, 0, bytes, dstPos, len);

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码平台 RSA 公钥  : ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码录音开始命令
	 * @return
	 */
	public static String encoderRecordStartCommand(RecordStartCommand recordStartCommand){

		try {
			byte[] bytes = new byte[5];	
			int dstPos = 0;
			//录音命令
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(recordStartCommand.getCmd(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//录音时间
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(recordStartCommand.getSeconds(),2)), 0, bytes, dstPos,2);
			dstPos+=2;
			//保存标志
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(recordStartCommand.getTag(),1)), 0, bytes, dstPos,1);
			dstPos+=1;
			//音频采样率
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(recordStartCommand.getRate(),1)), 0, bytes, dstPos,1);
			dstPos+=1;

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("录音开始命令 : ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码车辆控制
	 * @param vehicleControl
	 * @return
	 */
	public static String encoderVehicleControl(VehicleControl vehicleControl){

		try {
			if(vehicleControl != null){

				byte[] bytes = new byte[1];	
				int ctrl = vehicleControl.getCtrl();
				if(ctrl == 2){
					ctrl = 0;
				}

				//控制标志
				StringBuffer vcBuffer = new StringBuffer();
				vcBuffer.append("0000000");
				vcBuffer.append(ctrl);
				bytes[0] = ParseUtil.bitToByte(vcBuffer.toString());

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("车辆控制0x8500: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码信息服务
	 * @param infoDemandMenu
	 * @return
	 */
	public static String encoderInfoServer(InfoDemandMenu infoDemandMenu){

		try {
			if(infoDemandMenu != null){

				byte[] contentbyte = ParseUtil.stringToByte(infoDemandMenu.getContent());
				int contentlen = contentbyte.length;

				byte[] bytes = new byte[3+contentlen];	
				int dstPos = 0;

				//信息类型
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(infoDemandMenu.getType(),1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				//信息总长度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(contentlen,2)), 0, bytes, dstPos, 2);
				dstPos+=2;

				//信息内容 
				System.arraycopy(contentbyte, 0, bytes, dstPos, contentlen);

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("信息服务: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 电话回拨
	 * @param phoneBook
	 * @return
	 */
	public static String encoderCallBack(PhoneBook phoneBook){

		try {
			if(phoneBook != null){

				byte[] telbyte = ParseUtil.stringToByte(phoneBook.getTel());
				int tellen = telbyte.length;

				byte[] bytes = new byte[1+tellen];	
				int dstPos = 0;

				//标志
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(phoneBook.getMark(),1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				//电话号码
				System.arraycopy(telbyte, 0, bytes, dstPos, tellen);

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("电话回拨0x8400: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码提问下发
	 * @return
	 */
	public static String encoderQuestionsSend(QuestionsSend questionsSend){
		try {
			if(questionsSend != null){

				int len = BbByteEncoderUtil.getQuesSendCandidateAnswerLen(questionsSend.getItems());

				//问题
				byte[] content = ParseUtil.stringToByte(questionsSend.getContent());
				int contentlen = content.length;

				byte[] bytes = new byte[2 + len + contentlen];	

				StringBuffer flagBuffer = new StringBuffer();
				flagBuffer.append("000");
				flagBuffer.append(questionsSend.getTag4());
				flagBuffer.append(questionsSend.getTag3());
				flagBuffer.append("00");
				flagBuffer.append(questionsSend.getTag0());

				byte[] flagbyte = new byte[1];
				flagbyte[0] = ParseUtil.bitToByte(flagBuffer.toString());

				int dstPos = 0;
				// 标志 
				System.arraycopy(flagbyte, 0, bytes, dstPos, 1);
				dstPos+=1;

				//问题内容长度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(contentlen,1)), 0, bytes, dstPos, 1);
				dstPos+=1;

				//问题
				System.arraycopy(content, 0, bytes, dstPos, contentlen);
				dstPos+=contentlen;

				if(questionsSend.getItems().size() > 0){
					//问题列表 
					byte [] eventitems = BbByteEncoderUtil.getQuesSendCandidateAnswerByte(questionsSend.getItems(), len);
					if(eventitems != null){
						System.arraycopy(eventitems, 0, bytes, dstPos, len);
					}
				}

				String bodymsg = ParseUtil.parseByteToHexStr(bytes);
				logger.info("提问下发: ["+bodymsg+"]");
				return bodymsg;
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

	}




	/**
	 * 编码扫描支付通知
	 * @param paymentNotice
	 * @return
	 */
	public static String encoderPaymentNotice(PaymentNotice paymentNotice){

		try {
			byte [] orderidbyte = ParseUtil.stringToByte(paymentNotice.getOrderid());
			int len = orderidbyte.length;

			byte[] bytes = new byte[1+len];	
			int dstPos = 0;
			//支付结果
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(paymentNotice.getResult(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			//订单号
			System.arraycopy(orderidbyte, 0, bytes, dstPos, len);
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码扫描支付通知: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 编码招标信息
	 * @param callInfo
	 * @return
	 */
	public static String encoderInvitationInfo(CallInfo callInfo){

		try {

			//订单号
			byte [] orderidbyte = ParseUtil.stringToByte(callInfo.getOrderid());
			int orderidlen = orderidbyte.length;

			//乘客姓名
			byte [] pnamebyte = ParseUtil.stringToByte(callInfo.getPassengername());
			int pnamelen = pnamebyte.length;

			//抢答电话号码
			byte [] aphonebyte = ParseUtil.stringToByte(callInfo.getPassengerphone());
			int aphonelen = aphonebyte.length;

			//调度简短信息
			byte [] contentsbyte = ParseUtil.stringToByte(callInfo.getContents());
			int contentslen = contentsbyte.length;

			//出发地
			byte [] startaddrbyte = ParseUtil.stringToByte(callInfo.getStartaddr());
			int startaddrlen = startaddrbyte.length;

			//目的地
			byte [] endaddrbyte = ParseUtil.stringToByte(callInfo.getEndaddr());
			int endaddrlen = endaddrbyte.length;

			byte[] bytes = new byte[24+orderidlen+aphonelen+contentslen+startaddrlen+endaddrlen+pnamelen];	
			int dstPos = 0;
			//定单号
			System.arraycopy(orderidbyte, 0, bytes, dstPos, orderidlen);
			dstPos += orderidlen;
			//订单时间
			System.arraycopy(ParseUtil.str2Bcd(DateUtil.dateToNumber(callInfo.getOrdertime())), 0, bytes, dstPos, 6);
			dstPos += 6;
			//抢答方式
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getAnswermode(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			//订单类型
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getOrdertype(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			//乘客姓名
			System.arraycopy(pnamebyte, 0, bytes, dstPos, pnamelen);
			dstPos += pnamelen;
			//乘客电话
			System.arraycopy(aphonebyte, 0, bytes, dstPos, aphonelen);
			dstPos += aphonelen;
			//开始经度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getStartlng(),4)), 0, bytes, dstPos, 4);
			dstPos+=4;
			//开始纬度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getStartlat(),4)), 0, bytes, dstPos, 4);
			dstPos+=4;
			//出发地
			System.arraycopy(startaddrbyte, 0, bytes, dstPos, startaddrlen);
			dstPos += startaddrlen;
			//结束经度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getEndlng(),4)), 0, bytes, dstPos, 4);
			dstPos+=4;
			//结束纬度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getEndlat(),4)), 0, bytes, dstPos, 4);
			dstPos+=4;
			//目的地
			System.arraycopy(endaddrbyte, 0, bytes, dstPos, endaddrlen);
			dstPos += endaddrlen;
			//调度简短信息
			System.arraycopy(contentsbyte, 0, bytes, dstPos, contentslen);

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码招标信息: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码抢答确认
	 * @param callInfo
	 * @return
	 */
	public static String encoderConfirmAnswer(CallInfo callInfo){

		try {
			byte[] bytes = new byte[1];	
			int dstPos = 0;

			//抢答确认结果
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getResult(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码抢答确认: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码电召中心处理结果
	 * @param callInfo
	 * @return
	 */
	public static String encoderCenterProcessingResults(CallInfo callInfo){

		try {
			//订单号
			byte [] orderidbyte = ParseUtil.stringToByte(callInfo.getOrderid());
			int orderidlen = orderidbyte.length;

			byte[] bytes = new byte[1+orderidlen];	
			int dstPos = 0;
			//定单号
			System.arraycopy(orderidbyte, 0, bytes, dstPos, orderidlen);
			dstPos += orderidlen;
			//处理结果
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getResult(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码电召中心处理结果: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码中标
	 * @param callInfo
	 * @return
	 */
	public static String encoderCallWinningBid(CallInfo callInfo){

		try {

			//订单号
			byte [] orderidbyte = ParseUtil.stringToByte(callInfo.getOrderid());
			int orderidlen = orderidbyte.length;

			//乘客姓名
			byte [] pnamebyte = ParseUtil.stringToByte(callInfo.getPassengername());
			int pnamelen = pnamebyte.length;

			//乘客电话
			byte [] pphonebyte = ParseUtil.stringToByte(callInfo.getPassengerphone());
			int pphonelen = pphonebyte.length;

			//车牌号
			byte [] carnumberbyte = ParseUtil.stringToByte(callInfo.getCarnumber());
			int carnumberlen = carnumberbyte.length;

			//描述信息
			byte [] contentsbyte = ParseUtil.stringToByte(callInfo.getContents());
			int contentslen = contentsbyte.length;

			//本机手机号
			byte [] localphonebyte = ParseUtil.stringToByte(callInfo.getLocalphone());
			int localphonelen = localphonebyte.length;

			//电召费
			byte [] callfeebyte = ParseUtil.stringToByte(callInfo.getCallfee());
			int callfeelen = callfeebyte.length;

			int len = orderidlen + pnamelen + pphonelen + carnumberlen + contentslen + localphonelen + callfeelen;

			byte[] bytes = new byte[len];	
			int dstPos = 0;

			//订单号
			System.arraycopy(orderidbyte, 0, bytes, dstPos, orderidlen);
			dstPos += orderidlen;

			//乘客姓名
			System.arraycopy(pnamebyte, 0, bytes, dstPos, pnamelen);
			dstPos += pnamelen;

			//乘客电话
			System.arraycopy(pphonebyte, 0, bytes, dstPos, pphonelen);
			dstPos += pphonelen;

			//车牌号
			System.arraycopy(carnumberbyte, 0, bytes, dstPos, carnumberlen);
			dstPos += carnumberlen;

			//描述信息
			System.arraycopy(contentsbyte, 0, bytes, dstPos, contentslen);
			dstPos += contentslen;

			//本机手机号
			System.arraycopy(localphonebyte, 0, bytes, dstPos, localphonelen);
			dstPos += localphonelen;

			//电召费
			System.arraycopy(callfeebyte, 0, bytes, dstPos, callfeelen);
			dstPos += callfeelen;

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码中标: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 编码执行电召确认
	 * @param callInfo
	 * @return
	 */
	public static String encoderPerformCallConfirmation(CallInfo callInfo){

		try {
			byte[] bytes = new byte[1];	
			int dstPos = 0;

			//处理结果
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getResult(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码执行电召确认: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 编码语音招标信息
	 * @param callInfo
	 * @return
	 */
	public static String encoderVoiceInvitationInfo(CallInfo callInfo){

		try {

			//订单号
			byte [] orderidbyte = ParseUtil.stringToByte(callInfo.getOrderid());
			int orderidlen = orderidbyte.length;

			//乘客姓名
			byte [] pnamebyte = ParseUtil.stringToByte(callInfo.getPassengername());
			int pnamelen = pnamebyte.length;

			//乘客电话号码
			byte [] aphonebyte = ParseUtil.stringToByte(callInfo.getPassengerphone());
			int aphonelen = aphonebyte.length;

			//调度简短信息
			byte [] contentsbyte = ParseUtil.stringToByte(callInfo.getContents());
			int contentslen = contentsbyte.length;

			//出发地
			byte [] startaddrbyte = ParseUtil.stringToByte(callInfo.getStartaddr());
			int startaddrlen = startaddrbyte.length;


			//录音文件地址
			byte [] urlbyte = ParseUtil.stringToByte(callInfo.getUrl());
			int urllen = urlbyte.length;

			byte[] bytes = new byte[15+orderidlen+aphonelen+contentslen+urllen+startaddrlen+pnamelen];	
			int dstPos = 0;
			//定单号
			System.arraycopy(orderidbyte, 0, bytes, dstPos, orderidlen);
			dstPos += orderidlen;
			//订单时间
			System.arraycopy(ParseUtil.str2Bcd(DateUtil.dateToNumber(callInfo.getOrdertime())), 0, bytes, dstPos, 6);
			dstPos += 6;
			//抢答方式
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getAnswermode(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			//乘客姓名
			System.arraycopy(pnamebyte, 0, bytes, dstPos, pnamelen);
			dstPos += pnamelen;
			//乘客电话
			System.arraycopy(aphonebyte, 0, bytes, dstPos, aphonelen);
			dstPos += aphonelen;
			//开始经度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getStartlng(),4)), 0, bytes, dstPos, 4);
			dstPos+=4;
			//开始纬度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getStartlat(),4)), 0, bytes, dstPos, 4);
			dstPos+=4;
			//出发地
			System.arraycopy(startaddrbyte, 0, bytes, dstPos, startaddrlen);
			dstPos += startaddrlen;
			//录音文件URL
			System.arraycopy(urlbyte, 0, bytes, dstPos, urllen);
			dstPos +=urllen;
			//调度简短信息
			System.arraycopy(contentsbyte, 0, bytes, dstPos, contentslen);

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码语音叫车信息: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 编码车辆信息操作通知
	 * @param operationNotice
	 * @return
	 */
	public static String encoderOperationNotice(OperationNotice operationNotice){

		try {
			byte [] carnumberbyte = ParseUtil.stringToByte(operationNotice.getCarnumber());
			int len = carnumberbyte.length;

			byte[] bytes = new byte[1+len];	
			int dstPos = 0;
			//操作类型 1 增加车辆 2 修改车辆 3 删除车辆
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(operationNotice.getType(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			//车牌号
			System.arraycopy(carnumberbyte, 0, bytes, dstPos, len);
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码车辆信息操作通知: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 一键通导航
	 * @param poiInfo
	 * @return
	 */
	public static String encoderPoiNavi(PoiInfo poiInfo){

		try {
			byte [] addrbyte = ParseUtil.stringToByte(poiInfo.getAddr());
			int len = addrbyte.length;

			byte[] bytes = new byte[8+len];	
			int dstPos = 0;
			//纬度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(poiInfo.getLat(),4)), 0, bytes, dstPos, 4);
			dstPos+=4;
			//经度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(poiInfo.getLng(),4)), 0, bytes, dstPos, 4);
			dstPos+=4;
			//目的地
			System.arraycopy(addrbyte, 0, bytes, dstPos, len);
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码一键通导航: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

	}

	/**
	 * 编码防伪码
	 * @param securityCode
	 * @return
	 */
	public static String encoderSecurityCode(SecurityCode securityCode){

		try {

			byte[] bytes = new byte[8];	
			int dstPos = 0;
			//显示方式
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(securityCode.getStyle(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			//显示字符的颜色
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(securityCode.getColor(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			//显示内容
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(securityCode.getContent(),2)), 0, bytes, dstPos, 2);
			dstPos+=2;
			//延迟
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(securityCode.getDelayed(),2)), 0, bytes, dstPos, 2);
			dstPos+=2;
			//显示时间
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(securityCode.getTime(),2)), 0, bytes, dstPos, 2);

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码防伪码: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

	}

	/**
	 * 编码WIFI热点控制
	 * @param wifi 
	 * @return
	 */
	public static String encoderWifi(WifiInfo wifi){

		try {

			byte[] bytes = new byte[1];	
			int dstPos = 0;
			//关闭开启	数字	1 打开 2 关闭
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(wifi.getStatus(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码WIFI热点控制: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 文本消息推送
	 * @param textInfo
	 * @return
	 */
	public static String encoderTextInfoPush(TextInfo textInfo){

		try {
			//发送人
			byte [] senderbyte = ParseUtil.stringToByte(textInfo.getSender());
			int senderlen = senderbyte.length;

			//文本信息
			byte [] contentbyte = ParseUtil.stringToByte(textInfo.getContent());
			int contentslen = contentbyte.length;

			//设备存储地址
			byte [] pathbyte = ParseUtil.stringToByte(textInfo.getPath());
			int pathlen = pathbyte.length;

			byte[] bytes = new byte[8+contentslen+senderlen+pathlen];	

			int dstPos = 0;

			//发送人
			System.arraycopy(senderbyte, 0, bytes, dstPos, senderlen);
			dstPos+=senderlen;

			//发送时间
			System.arraycopy(ParseUtil.str2Bcd(DateUtil.dateToNumber(textInfo.getSender())), 0, bytes, dstPos, 6);
			dstPos += 6;

			//类型
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(textInfo.getType(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;

			//tts阅读
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(textInfo.getTts(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;

			//发送内容
			System.arraycopy(contentbyte, 0, bytes, dstPos, contentslen);
			dstPos+=contentslen;


			//设备存储地址
			System.arraycopy(pathbyte, 0, bytes, dstPos, pathlen);
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码文本消息推送下发: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 文件传输
	 * @param textInfo
	 * @return
	 */
	public static String encoderFileInfoPush(FileInfo fileInfo){

		try {
			//SD卡存储位置
			byte [] sdpathbyte = ParseUtil.stringToByte(fileInfo.getSdpath());
			int sdpathlen = sdpathbyte.length;
			byte[] bytes = new byte[sdpathlen];	
			int dstPos = 0;
			//设备存储地址
			System.arraycopy(sdpathbyte, 0, bytes, dstPos, sdpathlen);
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码文件传输: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 编码浙江智慧车联网有限公司招标信息
	 * @param callInfo
	 * @return
	 */
	public static String encoderZjInvitationInfo(CallInfo callInfo){

		try {

			//调度简短信息
			byte [] contentsbyte = ParseUtil.stringToByte(callInfo.getContents());
			int contentslen = contentsbyte.length;

			//录音文件
			byte [] urlbyte = ParseUtil.stringToByte(callInfo.getUrl());
			int urllen = urlbyte.length;

			byte[] bytes = new byte[11+contentslen+urllen];	
			int dstPos = 0;
			//业务ID
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(callInfo.getOrderid()),4)), 0, bytes, dstPos, 4);
			dstPos += 4;
			//业务类型
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getOrdertype(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			//要车时间
			System.arraycopy(ParseUtil.str2Bcd(DateUtil.dateToNumber(callInfo.getOrdertime())), 0, bytes, dstPos, 6);
			dstPos += 6;
			//业务描述
			System.arraycopy(contentsbyte, 0, bytes, dstPos, contentslen);
			dstPos += contentslen;
			//录音文件
			System.arraycopy(urlbyte, 0, bytes, dstPos, urllen);
			dstPos += urllen;
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码浙江智慧车联网有限公司招标信息: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 编码浙江智慧车联网有限公司中标信息
	 * @param callInfo
	 * @return
	 */
	public static String encoderZjCallWinningBid(CallInfo callInfo){

		try {
			String bodymsg = null;
			if(callInfo.getContents() == null){
				byte[] bytes = new byte[4];
				int dstPos = 0;
				//业务ID
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(callInfo.getOrderid()),4)), 0, bytes, dstPos, 4);
				dstPos += 4;
				bodymsg = ParseUtil.parseByteToHexStr(bytes);
			}else{
				//乘客电话
				byte [] aphonebyte = ParseUtil.stringToByte(callInfo.getPassengerphone());
				int aphonelen = aphonebyte.length;

				//业务描述
				byte [] contentsbyte = ParseUtil.stringToByte(callInfo.getContents());
				int contentslen = contentsbyte.length;

				byte[] bytes = new byte[21+aphonelen+contentslen];	

				int dstPos = 0;
				//业务ID
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(callInfo.getOrderid()),4)), 0, bytes, dstPos, 4);
				dstPos += 4;
				//业务类型
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getOrdertype(),1)), 0, bytes, dstPos, 1);
				dstPos+=1;
				//乘客电话
				System.arraycopy(aphonebyte, 0, bytes, dstPos, aphonelen);
				dstPos += aphonelen;
				//调度简短信息
				System.arraycopy(contentsbyte, 0, bytes, dstPos, contentslen);
				dstPos += contentslen;
				//开始经度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getStartlng(),4)), 0, bytes, dstPos, 4);
				dstPos+=4;
				//开始纬度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getStartlat(),4)), 0, bytes, dstPos, 4);
				dstPos+=4;
				//结束经度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getEndlng(),4)), 0, bytes, dstPos, 4);
				dstPos+=4;
				//结束纬度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(callInfo.getEndlat(),4)), 0, bytes, dstPos, 4);
				dstPos+=4;
				bodymsg = ParseUtil.parseByteToHexStr(bytes);
			}

			logger.info("编码浙江智慧车联网有限公司中标信息: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 浙江智慧车联网有限公司取消电召
	 * @param callInfo
	 * @return
	 */
	public static String encoderZjCenterCancel(CallInfo callInfo){

		try {
			byte[] bytes = new byte[4];
			int dstPos = 0;
			//业务ID
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(callInfo.getOrderid()),4)), 0, bytes, dstPos, 4);
			dstPos += 4;
			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("编码浙江智慧车联网有限公司取消电召: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 订单信息
	 * @param orderInfo
	 * @return
	 */
	public static String encoderOrderInfo(OrderInfo orderInfo){

		try {
			//订单ID
			byte [] orderidbyte = ParseUtil.stringToByte(orderInfo.getOrderid());
			int orderidlen = orderidbyte.length;

			//下单者
			byte [] orderswerebyte = ParseUtil.stringToByte(orderInfo.getOrderswere());
			int orderswerelen = orderswerebyte.length;

			//下订单时间
			byte [] ordertimebyte = ParseUtil.stringToByte(orderInfo.getOrdertime());
			int ordertimelen = ordertimebyte.length;

			//订单有效时间
			byte [] effectivebyte = ParseUtil.stringToByte(orderInfo.getEffectivetime());
			int effectivelen = effectivebyte.length;

			//订单确认时间
			byte [] confirmbyte = ParseUtil.stringToByte(orderInfo.getConfirmtime());
			int confirmlen = confirmbyte.length;

			//取货时间
			byte [] pickupbyte = ParseUtil.stringToByte(orderInfo.getPickuptime());
			int pickuplen = pickupbyte.length; 

			//已运到时间
			byte [] transportedbyte = ParseUtil.stringToByte(orderInfo.getTransportedtime());
			int transportedlen = transportedbyte.length;

			//完成时间
			byte [] completionbyte = ParseUtil.stringToByte(orderInfo.getCompletiontime());
			int completionlen = completionbyte.length;

			int len = orderidlen + orderswerelen + ordertimelen + effectivelen + confirmlen + pickuplen + transportedlen+completionlen;

			byte[] bytes = new byte[len+13];	
			int dstPos = 0;

			//标志
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(orderInfo.getFlag(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;

			//评价状态
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(orderInfo.getEvastatus(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;

			//订单状态
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(orderInfo.getOrderstatus(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;

			//投诉状态
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(orderInfo.getComstatus(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;

			//会员类型
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(orderInfo.getMembertype(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;

			//积分
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(orderInfo.getIntegral(),2)), 0, bytes, dstPos, 2);
			dstPos+=2;

			//诚信度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(orderInfo.getIntegrity(),2)), 0, bytes, dstPos, 2);
			dstPos+=2;

			//会员ID
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(orderInfo.getMemberid(),2)), 0, bytes, dstPos, 2);
			dstPos+=2;

			//货源ID
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(orderInfo.getSgid(),2)), 0, bytes, dstPos, 2);
			dstPos+=2;

			//订单ID
			System.arraycopy(orderidbyte, 0, bytes, dstPos, orderidlen);
			dstPos += orderidlen;

			//下单者
			System.arraycopy(orderswerebyte, 0, bytes, dstPos, orderswerelen);
			dstPos += orderswerelen;

			//下订单时间
			System.arraycopy(ordertimebyte, 0, bytes, dstPos, ordertimelen);
			dstPos += ordertimelen;

			//订单有效时间
			System.arraycopy(effectivebyte, 0, bytes, dstPos, effectivelen);
			dstPos += effectivelen;

			//订单确认时间
			System.arraycopy(confirmbyte, 0, bytes, dstPos, confirmlen);
			dstPos += confirmlen;

			//取货时间
			System.arraycopy(pickupbyte, 0, bytes, dstPos, pickuplen);
			dstPos += pickuplen;

			//已运到时间
			System.arraycopy(transportedbyte, 0, bytes, dstPos, transportedlen);
			dstPos += transportedlen;

			//完成时间
			System.arraycopy(completionbyte, 0, bytes, dstPos, completionlen);
			dstPos += completionlen;

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("订单信息下发: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}




	/**
	 * 货源信息
	 * @param gsInfo
	 * @return
	 */
	public static String encoderGoodSourceInfo(GoodSourceInfo gsInfo){

		try {
			//运费
			byte [] shipbyte = ParseUtil.stringToByte(gsInfo.getShipping());
			int shiplen = shipbyte.length;

			//订单ID
			byte [] orderidbyte = ParseUtil.stringToByte(gsInfo.getOrderid());
			int orderidlen = orderidbyte.length;

			//货源名称
			byte [] gsnamebyte = ParseUtil.stringToByte(gsInfo.getGoodsname());
			int gsnamelen = gsnamebyte.length;

			//形状
			byte [] shapebyte = ParseUtil.stringToByte(gsInfo.getShape());
			int shapelen = shapebyte.length;

			//包装方式
			byte [] packingnamebyte = ParseUtil.stringToByte(gsInfo.getPackingname());
			int packingnamelen = packingnamebyte.length;

			//运输类型
			byte [] typetransportbyte = ParseUtil.stringToByte(gsInfo.getTypetransport());
			int typetransportlen = typetransportbyte.length; 

			//运送方式
			byte [] shippingmethodbyte = ParseUtil.stringToByte(gsInfo.getShippingmethod());
			int shippingmethodlen = shippingmethodbyte.length;

			//注意事项
			byte [] precautionsbyte = ParseUtil.stringToByte(gsInfo.getPrecautions());
			int precautionslen = precautionsbyte.length;

			//联系人
			byte [] fullnamebyte = ParseUtil.stringToByte(gsInfo.getFullname());
			int fullnamelen = fullnamebyte.length;

			//联系电话
			byte [] telbyte = ParseUtil.stringToByte(gsInfo.getTel());
			int tellen = telbyte.length;

			//联系手机
			byte [] phonebyte = ParseUtil.stringToByte(gsInfo.getPhone());
			int phonelen = phonebyte.length;

			//取货地址
			byte [] addressbyte = ParseUtil.stringToByte(gsInfo.getAddress());
			int addresslen = addressbyte.length;

			int len = shiplen + orderidlen +gsnamelen +
			shapelen + packingnamelen + typetransportlen + shippingmethodlen+
			precautionslen+fullnamelen+tellen+phonelen+addresslen;

			byte[] bytes = new byte[len+17];	
			int dstPos = 0;

			//标志
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(gsInfo.getFlag(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;

			//货源ID
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(gsInfo.getSgid(),2)), 0, bytes, dstPos, 2);
			dstPos+=2;

			//货物情况
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(gsInfo.getGoodssituation(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;

			//重量
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(gsInfo.getWeight(),2)), 0, bytes, dstPos,2);
			dstPos+=2;

			//数量
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(gsInfo.getQuantity(),2)), 0, bytes, dstPos,2);
			dstPos+=2;

			//是否长期货源
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(gsInfo.getLongtermsupply(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;

			//出发纬度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(gsInfo.getLat(),4)), 0, bytes, dstPos, 4);
			dstPos+=4;

			//出发经度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(gsInfo.getLng(),4)), 0, bytes, dstPos, 4);
			dstPos+=4;

			//运费
			System.arraycopy(shipbyte, 0, bytes, dstPos, shiplen);
			dstPos += shiplen;

			//订单ID
			System.arraycopy(orderidbyte, 0, bytes, dstPos, orderidlen);
			dstPos += orderidlen;

			//货源名称
			System.arraycopy(gsnamebyte, 0, bytes, dstPos, gsnamelen);
			dstPos += gsnamelen;

			//形状
			System.arraycopy(shapebyte, 0, bytes, dstPos, shapelen);
			dstPos += shapelen;

			//包装方式
			System.arraycopy(packingnamebyte, 0, bytes, dstPos, packingnamelen);
			dstPos += packingnamelen;

			//运输类型
			System.arraycopy(typetransportbyte, 0, bytes, dstPos,typetransportlen);
			dstPos += typetransportlen;

			//运送方式
			System.arraycopy(shippingmethodbyte, 0, bytes, dstPos, shippingmethodlen);
			dstPos += shippingmethodlen;

			//注意事项
			System.arraycopy(precautionsbyte, 0, bytes, dstPos, precautionslen);
			dstPos += precautionslen;

			//联系人
			System.arraycopy(fullnamebyte, 0, bytes, dstPos, fullnamelen);
			dstPos += fullnamelen;

			//联系电话
			System.arraycopy(telbyte, 0, bytes, dstPos, tellen);
			dstPos += tellen;

			//联系手机
			System.arraycopy(phonebyte, 0, bytes, dstPos, phonelen);
			dstPos += phonelen;

			//取货地址
			System.arraycopy(addressbyte, 0, bytes, dstPos,addresslen);
			dstPos += addresslen;


			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("货源信息下发: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}



	/**
	 * 通知接受货物
	 * @param nAcceptGoods
	 * @return
	 */
	public static String encoderNoticeAcceptGoods(NoticeAcceptGoods naGoods){

		try {
			//订单ID
			byte [] orderidbyte = ParseUtil.stringToByte(naGoods.getOrderid());
			int orderidlen = orderidbyte.length;

			//接受货物最晚时间
			byte [] latesttimebyte = ParseUtil.stringToByte(naGoods.getLatesttime());
			int latesttimelen = latesttimebyte.length;

			//备注
			byte [] remarkbyte = ParseUtil.stringToByte(naGoods.getRemark());
			int remarklen = remarkbyte.length;

			int len = orderidlen +latesttimelen +remarklen ;

			byte[] bytes = new byte[len+1];	
			int dstPos = 0;

			//通知次数
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(naGoods.getTimes(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;

			//订单ID
			System.arraycopy(orderidbyte, 0, bytes, dstPos, orderidlen);
			dstPos += orderidlen;

			//接受货物最晚时间
			System.arraycopy(latesttimebyte, 0, bytes, dstPos, latesttimelen);
			dstPos += latesttimelen;

			//备注
			System.arraycopy(remarkbyte, 0, bytes, dstPos,remarklen);
			dstPos += remarklen;


			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("通知接受货物下发: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 告知信息
	 * @param notifyingMessage
	 * @return
	 */
	public static String encoderNotifyingMessage(NotifyingMessage notifyingMessage){

		try {
			//订单ID
			byte [] orderidbyte = ParseUtil.stringToByte(notifyingMessage.getOrderid());
			int orderidlen = orderidbyte.length;

			//告知时间
			byte [] informtimebyte = ParseUtil.stringToByte(notifyingMessage.getInformtime());
			int informtimelen = informtimebyte.length;

			//告知内容
			byte [] informcontentbyte = ParseUtil.stringToByte(notifyingMessage.getInformcontent());
			int informcontentlen = informcontentbyte.length;

			//告知人
			byte [] informnamebyte = ParseUtil.stringToByte(notifyingMessage.getInformname());
			int informnamelen = informnamebyte.length;

			int len = orderidlen +informtimelen +informcontentlen+informnamelen ;

			byte[] bytes = new byte[len];	
			int dstPos = 0;

			//订单ID
			System.arraycopy(orderidbyte, 0, bytes, dstPos, orderidlen);
			dstPos += orderidlen;

			//告知时间
			System.arraycopy(informtimebyte, 0, bytes, dstPos, informtimelen);
			dstPos += informtimelen;

			//告知内容
			System.arraycopy(informcontentbyte, 0, bytes, dstPos,informcontentlen);
			dstPos += informcontentlen;

			//告知人
			System.arraycopy(informnamebyte, 0, bytes, dstPos,informnamelen);
			dstPos += informnamelen;

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("告知信息下发: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 积分诚信度
	 * @param integralIntegrity
	 * @return
	 */
	public static String encoderIntegralIntegrity(IntegralIntegrity integralIntegrity){

		try {
			byte[] bytes = new byte[8];	
			int dstPos = 0;

			//积分值
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(integralIntegrity.getIntegral(),2)), 0, bytes, dstPos, 2);
			dstPos+=2;
			//诚信度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(integralIntegrity.getIntegrity(),2)), 0, bytes, dstPos, 2);
			dstPos+=2;
			//当前诚信度经验值
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(integralIntegrity.getCurrentexperience(),2)), 0, bytes, dstPos, 2);
			dstPos+=2;
			//下次升级诚信度经验值
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(integralIntegrity.getExperiencenext(),2)), 0, bytes, dstPos, 2);
			dstPos+=2;

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("积分诚信度下发: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 收货联系方式
	 * @param notifyingMessage
	 * @return
	 */
	public static String encoderReceivingContact(ReceivingContact reContact){

		try {
			//订单ID
			byte [] orderidbyte = ParseUtil.stringToByte(reContact.getOrderid());
			int orderidlen = orderidbyte.length;

			//联系人
			byte [] fullnamebyte = ParseUtil.stringToByte(reContact.getFullname());
			int fullnamelen = fullnamebyte.length;

			//联系电话
			byte [] telbyte = ParseUtil.stringToByte(reContact.getTel());
			int tellen = telbyte.length;

			//联系手机
			byte [] phonebyte = ParseUtil.stringToByte(reContact.getPhone());
			int phonelen = phonebyte.length;

			//取货地址
			byte [] addressbyte = ParseUtil.stringToByte(reContact.getAddress());
			int addresslen = addressbyte.length;

			int len = orderidlen +fullnamelen +tellen+ phonelen+addresslen;

			byte[] bytes = new byte[len+9];	
			int dstPos = 0;

			//标志位
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(reContact.getFlag(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;

			//纬度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(reContact.getLat(),4)), 0, bytes, dstPos, 4);
			dstPos+=4;

			//经度
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(reContact.getLng(),4)), 0, bytes, dstPos,4);
			dstPos+=4;

			//订单ID
			System.arraycopy(orderidbyte, 0, bytes, dstPos, orderidlen);
			dstPos += orderidlen;

			//联系人
			System.arraycopy(fullnamebyte, 0, bytes, dstPos, fullnamelen);
			dstPos += fullnamelen;

			//联系电话
			System.arraycopy(telbyte, 0, bytes, dstPos, tellen);
			dstPos += tellen;

			//联系手机
			System.arraycopy(phonebyte, 0, bytes, dstPos, phonelen);
			dstPos += phonelen;

			//取货地址
			System.arraycopy(addressbyte, 0, bytes, dstPos,addresslen);
			dstPos += addresslen;

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("收货联系方式: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 我的资料
	 * @param myProfile
	 * @return
	 */
	public static String encoderMyProfile(MyProfile myProfile){

		try {
			//车牌号
			byte [] carnumberbyte = ParseUtil.stringToByte(myProfile.getCarnumber());
			int carnumberlen = carnumberbyte.length;

			//车辆归属地
			byte [] attributionbyte = ParseUtil.stringToByte(myProfile.getAttribution());
			int attributionlen = attributionbyte.length;

			//车体状况
			byte [] carstatusbyte = ParseUtil.stringToByte(myProfile.getCarstatus());
			int carstatuslen = carstatusbyte.length;

			//外形
			byte [] shapebyte = ParseUtil.stringToByte(myProfile.getShape());
			int shapelen = shapebyte.length;
			//载重
			byte [] loadbyte = ParseUtil.stringToByte(myProfile.getLoad());
			int loadlen = loadbyte.length;

			//行驶证号
			byte [] drivlicnumbyte = ParseUtil.stringToByte(myProfile.getDrivlicnum());
			int drivlicnumlen = drivlicnumbyte.length;

			//行驶公里数
			byte [] kilometersbyte = ParseUtil.stringToByte(myProfile.getKilometers());
			int kilometerslen = kilometersbyte.length;

			//GPS
			byte [] gpsbyte = ParseUtil.stringToByte(myProfile.getGps());
			int gpslen = gpsbyte.length;

			//上次上报险时间
			byte [] insurancetimebyte = ParseUtil.stringToByte(myProfile.getInsurancetime());
			int insurancetimelen = insurancetimebyte.length;

			//是否带车载终端
			byte [] vehicleterminalbyte = ParseUtil.stringToByte(myProfile.getVehicleterminal());
			int vehicleterminallen = vehicleterminalbyte.length;

			//联系人
			byte [] namebyte = ParseUtil.stringToByte(myProfile.getName());
			int namelen = namebyte.length;

			//固话
			byte [] telbyte = ParseUtil.stringToByte(myProfile.getTel());
			int tellen = telbyte.length;

			// 手机
			byte [] phonebyte = ParseUtil.stringToByte(myProfile.getPhone());
			int phonelen = phonebyte.length;

			int len = carnumberlen +attributionlen +carstatuslen+shapelen +
			loadlen +drivlicnumlen +kilometerslen+gpslen +
			insurancetimelen +vehicleterminallen +namelen+tellen +phonelen;

			byte[] bytes = new byte[len];	
			int dstPos = 0;

			//车牌号
			System.arraycopy(carnumberbyte, 0, bytes, dstPos, carnumberlen);
			dstPos += carnumberlen;

			//告知时间
			System.arraycopy(attributionbyte, 0, bytes, dstPos, attributionlen);
			dstPos += attributionlen;

			//车辆归属地
			System.arraycopy(carstatusbyte, 0, bytes, dstPos,carstatuslen);
			dstPos += carstatuslen;

			//外形
			System.arraycopy(shapebyte, 0, bytes, dstPos,shapelen);
			dstPos += shapelen;

			//载重
			System.arraycopy(loadbyte, 0, bytes, dstPos,loadlen);
			dstPos += loadlen;

			//行驶证号
			System.arraycopy(drivlicnumbyte, 0, bytes, dstPos,drivlicnumlen);
			dstPos += drivlicnumlen;

			//行驶公里数
			System.arraycopy(kilometersbyte, 0, bytes, dstPos,kilometerslen);
			dstPos += kilometerslen;

			//GPS
			System.arraycopy(gpsbyte, 0, bytes, dstPos,gpslen);
			dstPos += gpslen;

			//上次上报险时间
			System.arraycopy(insurancetimebyte, 0, bytes, dstPos,insurancetimelen);
			dstPos += insurancetimelen;

			//是否带车载终端
			System.arraycopy(vehicleterminalbyte, 0, bytes, dstPos,vehicleterminallen);
			dstPos += vehicleterminallen;

			//联系人
			System.arraycopy(namebyte, 0, bytes, dstPos,namelen);
			dstPos +=namelen;

			//固话
			System.arraycopy(telbyte, 0, bytes, dstPos,tellen);
			dstPos += tellen;

			//手机
			System.arraycopy(phonebyte, 0, bytes, dstPos,phonelen);
			dstPos += phonelen;

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("我的资料下发: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}



	/**
	 * 我的车源
	 * @param myOptions
	 * @return
	 */
	public static String encoderMyOptions(MyOptions myOptions){

		try {
			//类型
			byte [] typebyte = ParseUtil.stringToByte(myOptions.getType());
			int typelen = typebyte.length;

			//用途
			byte [] usebyte = ParseUtil.stringToByte(myOptions.getUse());
			int uselen = usebyte.length;

			//出发地
			byte [] inchoatbyte = ParseUtil.stringToByte(myOptions.getInchoat());
			int inchoatlen = inchoatbyte.length;

			//目的地
			byte [] destinationbyte = ParseUtil.stringToByte(myOptions.getDestination());
			int destinationlen = destinationbyte.length;

			//是否加急
			byte [] isexpeditedbyte = ParseUtil.stringToByte(myOptions.getIsexpedited());
			int isexpeditedlen = isexpeditedbyte.length;

			//运输路线
			byte [] transportlinebyte = ParseUtil.stringToByte(myOptions.getTransportline());
			int transportlinelen = transportlinebyte.length;

			//发布时间
			byte [] releasetimebyte = ParseUtil.stringToByte(myOptions.getReleasetime());
			int releasetimelen = releasetimebyte.length;

			int len = typelen +uselen +inchoatlen +destinationlen+isexpeditedlen+transportlinelen+releasetimelen;

			byte[] bytes = new byte[len+5];	
			int dstPos = 0;

			//车源ID
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(myOptions.getCarsourceid(),2)), 0, bytes, dstPos, 2);
			dstPos+=2;
			//生效状态
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(myOptions.getEffect(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;
			//会员ID
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(myOptions.getMemberid(),2)), 0, bytes, dstPos, 2);
			dstPos+=2;

			//类型
			System.arraycopy(typebyte, 0, bytes, dstPos, typelen);
			dstPos += typelen;

			//用途
			System.arraycopy(usebyte, 0, bytes, dstPos, uselen);
			dstPos += uselen;

			//出发地
			System.arraycopy(inchoatbyte, 0, bytes, dstPos,inchoatlen);
			dstPos += inchoatlen;

			//目的地
			System.arraycopy(destinationbyte, 0, bytes, dstPos,destinationlen);
			dstPos += destinationlen;

			//是否加急
			System.arraycopy(isexpeditedbyte, 0, bytes, dstPos,isexpeditedlen);
			dstPos += isexpeditedlen;

			//运输路线
			System.arraycopy(transportlinebyte, 0, bytes, dstPos,transportlinelen);
			dstPos += transportlinelen;

			//发布时间
			System.arraycopy(releasetimebyte, 0, bytes, dstPos,releasetimelen);
			dstPos += releasetimelen;

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("我的车源下发: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}

	/**
	 * 车源状态更新返回
	 * @param oasUpToReturn
	 * @return
	 */
	public static String encoderOptionsAvailableStaUpToReturn(OptionsAvailableStaUpToReturn oasUpToReturn){

		try {
			byte[] bytes = new byte[3];	
			int dstPos = 0;

			//车源ID
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(oasUpToReturn.getCarsourceid(),2)), 0, bytes, dstPos, 2);
			dstPos+=2;
			//生效状态
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(oasUpToReturn.getEffect(),1)), 0, bytes, dstPos, 1);
			dstPos+=1;

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("车源状态更新返回: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}


	/**
	 * 车源更新发布时间返回
	 * @param nAcceptGoods
	 * @return
	 */
	public static String encoderOptionsAvailableUpTimeToReturn(OptionsAvailableUpTimeToReturn oaUpTimeToReturn){

		try {
			//发布时间
			byte [] releasetimebyte = ParseUtil.stringToByte(oaUpTimeToReturn.getReleasetime());
			int releasetimelen = releasetimebyte.length;

			int len = releasetimelen ;

			byte[] bytes = new byte[len+2];	
			int dstPos = 0;

			//车源ID 
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(oaUpTimeToReturn.getCarsourceid(),2)), 0, bytes, dstPos,2);
			dstPos+=2;

			//订单ID
			System.arraycopy(releasetimebyte, 0, bytes, dstPos, releasetimelen);
			dstPos += releasetimelen;

			String bodymsg = ParseUtil.parseByteToHexStr(bytes);
			logger.info("车源更新发布时间返回下发: ["+bodymsg+"]");
			return bodymsg;

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
	}
}
