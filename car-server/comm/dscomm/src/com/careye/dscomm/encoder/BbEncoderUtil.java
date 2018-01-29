/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dscomm.encoder;

import com.careye.dscomm.utlis.ExceptionUtil;
import com.careye.dscomm.utlis.ParseUtil;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：BbEncoderUtil    
 * 类描述：808部标协议编码   
 * 创建人：zr    
 * 创建时间：2015-5-13 下午03:29:25    
 * 修改人：zr    
 * 修改时间：2015-5-13 下午03:29:25    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BbEncoderUtil {

	private static int seq = 1;

	//获得发送序列号
	public static int getSerialId(){
		if(seq > 65000){
			seq = 0;
		}
		seq++;
		return seq;
	}

	/**
	 * 组装协议消息头
	 * @param terminal 终端设备号
	 * @param msgid 消息ID
	 * @param bodyByte 消息体
	 * @return
	 */
	public static byte[] getMsghead808(String terminal,int msgid,byte[] bodyByte,int seq){

		int uDataLen = 0;
		if(bodyByte!=null){
			uDataLen = bodyByte.length;
		}

		//消息头
		byte[] msghead = new byte[12];
		int headnum = 0;
		//消息ID
		System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(msgid,2)), 0, msghead, headnum, 2);
		headnum +=2;

		//消息体属性
		System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(uDataLen, 2)), 0, msghead, headnum, 2);
		headnum +=2;

		//终端手机号
		System.arraycopy(ParseUtil.str2Bcd(terminal), 0, msghead, headnum,6);
		headnum +=6; 

		if(seq == 0){
			seq = getSerialId();
		}
		//消息流水号
		System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(seq,2)), 0, msghead, headnum, 2);
		return msghead;

	}

	/**
	 * 生成校验码
	 * @param msghead
	 * @param dataByte
	 * @return
	 */
	public static byte[] getCheckCode(byte[] msghead,byte[] bodyByte){

		int uDataLen = 0;
		if(bodyByte!=null){
			uDataLen = bodyByte.length;
		}

		//---校验码
		byte[] checkcode = new byte[msghead.length+uDataLen];
		int num = 0;
		System.arraycopy(msghead, 0, checkcode, num, msghead.length);
		num += msghead.length;
		if(bodyByte!=null){
			System.arraycopy(bodyByte, 0, checkcode, num, bodyByte.length);
		}
		return checkcode;
	}


	/**
	 * 判断7E开头的校验码
	 * @param data
	 * @return
	 */
	public static int checkCode(byte[] data){

		try {
			int datalen = data.length;
			//获取消息头、消息体、校验码进行转义
			byte[] bytes = ParseUtil.dataBbEscape(ParseUtil.byteTobyte(data, 1, datalen-2),1);
			//转义之后的长度
			datalen = bytes.length;
			//验证校验码是否一致
			byte[] jym = ParseUtil.byteTobyte(bytes,(bytes.length-1), 1);

			//消息头、消息体异或运算和得到校验码
			byte[] yzjym = ParseUtil.byteOrbyte(ParseUtil.byteTobyte(bytes,0, (bytes.length-1)));

			//部标协议
			if(ParseUtil.parseByteToHexStr(jym).equals(ParseUtil.parseByteToHexStr(yzjym))){
				return 22;
			}else{
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	} 


	/**
	 * 按照部标808协议编码
	 * @param terminal 终端设备ID
	 * @param msgid 消息ID
	 * @param bodyByte 消息体
	 * @return 
	 */
	public static byte[] getProtocol808(String terminal,int msgid,byte[] bodyByte,int seq){

		int uDataLen = 0;

		//消息头组装
		byte[] msghead = getMsghead808(terminal, msgid, bodyByte,seq);

		//校验码
		byte[] checkcode = ParseUtil.byteOrbyte(getCheckCode(msghead, bodyByte));

		//消息头转义
		msghead = ParseUtil.dataBbEscape(msghead,2);

		//校验码转义
		checkcode = ParseUtil.dataBbEscape(checkcode,2);

		if(bodyByte!=null){
			//消息体转义
			bodyByte = ParseUtil.dataBbEscape(bodyByte,2);
			uDataLen = bodyByte.length;
		}

		byte[] databyte =  new byte[msghead.length + uDataLen + checkcode.length + 2];

		try {
			int dstPos = 0;
			//--起始位
			System.arraycopy(ParseUtil.parseHexStrToByte("7E"), 0, databyte, dstPos, 1);																																																																																																																																							
			dstPos +=1;

			//--消息头
			System.arraycopy(msghead, 0, databyte, dstPos, msghead.length);
			dstPos += msghead.length;

			//---消息体
			if(bodyByte != null){
				System.arraycopy(bodyByte, 0, databyte, dstPos, bodyByte.length);
				dstPos += bodyByte.length;
			}

			//校验码
			System.arraycopy(checkcode, 0, databyte, dstPos, checkcode.length);
			dstPos += checkcode.length;

			//---结束位
			System.arraycopy(ParseUtil.parseHexStrToByte("7E"), 0, databyte, dstPos, 1);

			return databyte;

		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			e.printStackTrace();
			return null;
		}
	}

}
