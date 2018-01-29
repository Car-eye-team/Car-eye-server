/**    
 * Description: 晟鸿科技通讯平台    
 * 文件名：ParseUtil.java   
 * 版本信息：    
 * 日期：2015-5-13  
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015     
 * 版权所有    
 *    
 */
package com.careye.dscomm.utlis;

import java.io.UnsupportedEncodingException;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：ParseUtil    
 * 类描述：协议解析工具类    
 * 创建人：zr    
 * 创建时间：2015-5-13 上午09:51:05    
 * 修改人：zr    
 * 修改时间：2015-5-13 上午09:51:05    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ParseUtil {

	/**
	 * 字符串转换成指定长度的字符串
	 * @param value
	 * @param len
	 * @return
	 */
	public static String stringToString(String value,int len){
		if(value == null){
			value = "";
		}
		int length = value.length();
		if(length > len){
			value = value.substring(0, len);
		}else{
			for (int i = 0; i < (len-length); i++) {
				value = "0"+value;
			}
		}
		return value;

	}

	/**
	 * 字符串转换成指定长度的字符串
	 * @param value
	 * @param len
	 * @return
	 */
	public static String stringToStringSpace(String value,int len){
		if(value == null){
			value = "";
		}
		int length = value.length();
		if(length > len){
			value = value.substring(0, len);
		}else{
			for (int i = 0; i < (len-length); i++) {
				value = value+" ";
			}
		}
		return value;

	}

	/**
	 * 获取指定长度"\0"结尾的数组转字符串
	 * @param buf
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public static byte[] byteToSubStringToByte(byte data[],int startIndex, int length){

		String dataHex = parseByteToHexStr(byteTobyte(data, startIndex, length));
		//用“00”结尾截取十六进制字符串
		String[] strarr = replaceTwoToFour(dataHex,"00","##").split("##");
		//得到字符串二进制
		byte[] strbyte =  parseHexStrToByte(strarr[0]);
		return strbyte;
	}

	/**
	 * 二进制转换成10进制整数
	 * @param buf
	 * @return
	 */
	public static int byteToint(byte buf[]){
		return Integer.parseInt(parseByteToHexStr(buf), 16);
	}

	/**
	 * 二进制转换成10进制整数  低字节在前高字节在后
	 * @param buf
	 * @return
	 */
	public static int byteTodgint(byte buf[]){
		return Integer.parseInt(parseByteToHexStr(sortToByte(buf)), 16);
	}

	/**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	public static String parseByteToHexStr(byte buf[]) {  
		StringBuffer sb = new StringBuffer();  
		for (int i = 0; i < buf.length; i++) {  
			String hex = Integer.toHexString(buf[i] & 0xFF);  
			if (hex.length() == 1) {  
				hex = '0' + hex;  
			}  
			sb.append(hex.toUpperCase());  
		}  
		return sb.toString();  
	} 

	/**将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */  
	public static byte[] parseHexStrToByte(String hexStr) {  
		if (hexStr.length() < 1)  
			return null;  

		byte[] result = new byte[hexStr.length()/2];  
		for (int i = 0;i< hexStr.length()/2; i++) {  
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
			result[i] = (byte) (high * 16 + low);  
		}  
		return result;  
	}  

	/**
	 * 将整数转换成二进制字节（先低字节后高字节）
	 * @param num
	 * @return
	 */
	public static byte[] intToBytes(int iSource, int iArrayLen) {
		byte[] bLocalArr = new byte[iArrayLen];
		for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
			bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);
		}
		return bLocalArr;
	}

	public static byte[] longToByteOne(long num){
		byte[] b = new byte[1];
		b[0] = (byte)(int)(num >>> 0);
		return b;
	}

	/**
	 * long转成成byte[]
	 * @param number
	 * @param len
	 * @return
	 */
	public static byte[] longToByte(long number,int len) { 
		long temp = number; 
		byte[] b = new byte[len]; 
		for (int i = 0; i < b.length; i++) { 
			b[i] = new Long(temp & 0xff).byteValue();// 将最低位保存在最低位 
			temp = temp >> 8; // 向右移8位 
		} 
		return b; 
	} 

	/**
	 * 字节转long
	 * @param b
	 * @param num
	 * @return
	 */
	public static long byteToLong(byte[] b,int num) { 
		long s = 0; 
		try {

			if(num == 2){
				long s0 = b[0] & 0xff;// 最低位 
				long s1 = b[1] & 0xff; 

				// s0不变 
				s1 <<= 8; 

				s = s0 | s1 ;
			}

			if(num == 4){
				long s0 = b[0] & 0xff;// 最低位 
				long s1 = b[1] & 0xff; 
				long s2 = b[2] & 0xff; 
				long s3 = b[3] & 0xff; 

				// s0不变 
				s1 <<= 8; 
				s2 <<= 16; 
				s3 <<= 24; 

				s = s0 | s1 | s2 | s3;
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return s; 
	} 

	/**
	 * 高地位反相排序
	 * @param bytes
	 * @return
	 */
	public static byte[]sortToByte(byte[] bytes){
		byte[] des = new byte[bytes.length];
		int i = 0; 
		for (int j = bytes.length-1; j >=0; j--) {
			des[i] = bytes[j];
			i++;
		}
		return des;
	}


	/**
	 * 获取整数转换成十六进制
	 * @return
	 */
	public static String int2BytesStr(int iSource, int iArrayLen){
		String str = parseByteToHexStr(intToBytes(iSource,iArrayLen));
		return str;
	}


	/**
	 * 字符串转换成ascii的16进制
	 * @param value
	 * @return
	 */
	public static String stringToAsciiHexString(String value){
		StringBuffer sbu = new StringBuffer();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			sbu.append(Integer.toHexString((int)chars[i]));   
		}
		return sbu.toString();
	}

	/**
	 * 获取指定长度字节数
	 * @param src
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public static byte[] byteTobyte(byte[] src, int startIndex, int length){
		byte[] des = new byte[length];
		int i = 0; 
		for (int j = startIndex; i < length; ++j) {
			des[i] = src[j];
			++i;
		}
		return des;
	}


	/**
	 * 将字符串转换成二进制
	 * @param str
	 * @return
	 */
	public static byte[] stringToByte(String str){
		try {
			return (str+"\0").getBytes("GBK");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 字符串转换成指定长度的ascii的2进制
	 * @param value
	 * @param len
	 * @return
	 */
	public static byte[] stringToByte(String value,int len){
		int length = value.length();
		if(length > len){
			value = value.substring(0, len);
		}else{
			for (int i = 0; i < (len-length); i++) {
				value += " ";
			}
		}

		StringBuffer sbu = new StringBuffer();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			sbu.append(Integer.toHexString((int)chars[i]));   
		}
		return parseHexStrToByte(sbu.toString());

	}

	/**
	 * 位转成字节
	 * @param byteStr
	 * @return
	 */
	public static byte bitToByte(String byteStr) {  
		int re, len;  
		if (null == byteStr) {  
			return 0;  
		}  
		len = byteStr.length();  
		if (len != 4 && len != 8) {  
			return 0;  
		}  
		if (len == 8) {// 8 bit处理  
			if (byteStr.charAt(0) == '0') {// 正数  
				re = Integer.parseInt(byteStr, 2);  
			} else {// 负数  
				re = Integer.parseInt(byteStr, 2) - 256;  
			}  
		} else {//4 bit处理  
			re = Integer.parseInt(byteStr, 2);  
		}  
		return (byte) re;  
	} 

	/**
	 * 部标协议数据转义 
	 * 若校验码、消息头以及消息体中出现0x7e，则要进行转义处理，转义
		规则定义如下：
		0x7e <————> 0x7d 后紧跟一个0x02；
		0x7d <————> 0x7d 后紧跟一个0x01。
		转义处理过程如下：
		发送消息时：消息封装——>计算并填充校验码——>转义；
		接收消息时：转义还原——>验证校验码——>解析消息。
	 * @param bytes
	 * @return
	 */
	public static byte[] dataBbEscape(byte[] bytes,int type){
		String data = parseByteToHexStr(bytes).toUpperCase();

		if(type == 1){
			if(data.indexOf("7D02")>=0){
				data = replaceFourToTwo(data, "7D02", "7E");
			}

			if(data.indexOf("7D01")>=0){
				data = replaceFourToTwo(data, "7D01", "7D");
			}
			return parseHexStrToByte(data);
		}else{
			byte[] result = new byte[data.length()/2];
			data = replaceTwoToFour(data,"7D", "7D01");
			data = replaceTwoToFour(data,"7E", "7D02");
			result = parseHexStrToByte(data);
			return result;
		}
	}

	/**
	 * 8A转义
	 * @param bytes
	 * @param type
	 * @return
	 */
	public static byte[] data8AEscape(byte[] bytes,int type){
		String data = parseByteToHexStr(bytes).toUpperCase();

		if(type == 1){
			if(data.indexOf("8D02")>=0){
				data = replaceFourToTwo(data, "8D02", "8A");
			}

			if(data.indexOf("8D01")>=0){
				data = replaceFourToTwo(data, "8D01", "8D");
			}
			return parseHexStrToByte(data);
		}else{
			byte[] result = new byte[data.length()/2];
			data = replaceTwoToFour(data,"8D", "8D01");
			data = replaceTwoToFour(data,"8A", "8D02");
			result = parseHexStrToByte(data);
			return result;
		}
	}
	
	
	/**
	 * 9A转义
	 * @param bytes
	 * @param type
	 * @return
	 */
	public static byte[] data9AEscape(byte[] bytes,int type){
		String data = parseByteToHexStr(bytes).toUpperCase();

		if(type == 1){
			if(data.indexOf("9D02")>=0){
				data = replaceFourToTwo(data, "9D02", "9A");
			}

			if(data.indexOf("9D01")>=0){
				data = replaceFourToTwo(data, "9D01", "9D");
			}
			return parseHexStrToByte(data);
		}else{
			byte[] result = new byte[data.length()/2];
			data = replaceTwoToFour(data,"9D", "9D01");
			data = replaceTwoToFour(data,"9A", "9D02");
			result = parseHexStrToByte(data);
			return result;
		}
	}


	/**
	 * 部标协议数据转义 
	 * 若校验码、消息头以及消息体中出现0x7a，则要进行转义处理，转义
		规则定义如下：
		0x7a <————> 0x7d 后紧跟一个0x02；
		0x7d <————> 0x7d 后紧跟一个0x01。
		转义处理过程如下：
		发送消息时：消息封装——>计算并填充校验码——>转义；
		接收消息时：转义还原——>验证校验码——>解析消息。
	 * @param bytes
	 * @return
	 */
	public static byte[] data7AEscape(byte[] bytes,int type){
		String data = parseByteToHexStr(bytes).toUpperCase();
		if(type == 1){
			if(data.indexOf("7D02")>=0){
				data = replaceFourToTwo(data, "7D02", "7A");
			}

			if(data.indexOf("7D01")>=0){
				data = replaceFourToTwo(data, "7D01", "7D");
			}
			return parseHexStrToByte(data);
		}else{
			byte[] result = new byte[data.length()/2];
			data = replaceTwoToFour(data,"7D", "7D01");
			data = replaceTwoToFour(data,"7A", "7D02");
			result = parseHexStrToByte(data);
			return result;
		}

	}

	/**
	 * 恒天高科GPRS工业采集控制器HT-GPRS-V1.3协议 转义
	 *  0x5a转0x5b 0x01
	    0xa5转 0x5b 0x02
	    0x5b转0x5b 0x00
	 * @param bytes
	 * @param type
	 * @return
	 */
	public static byte[] data5AEscape(byte[] bytes,int type){
		String data = parseByteToHexStr(bytes).toUpperCase();
		if(type == 1){
			if(data.indexOf("5B00")>=0){
				data = replaceFourToTwo(data, "5B00", "5B");
			}

			if(data.indexOf("5B02")>=0){
				data = replaceFourToTwo(data, "5B02", "A5");
			}

			if(data.indexOf("5B01")>=0){
				data = replaceFourToTwo(data, "5B01", "5A");
			}
			return parseHexStrToByte(data);
		}else{
			byte[] result = new byte[data.length()/2];
			data = replaceTwoToFour(data,"5B", "5B00");
			data = replaceTwoToFour(data,"5A", "5B01");
			data = replaceTwoToFour(data,"A5", "5B02");
			result = parseHexStrToByte(data);
			return result;
		}

	}


	/**
	 * 恒天高科GPRS工业采集控制器HT-GPRS-V1.3协议 转义
	 *  0x5a转0x5b 0x01
	    0xa5转 0x5b 0x02
	    0x5b转0x5b 0x00
	 * @param bytes
	 * @param type
	 * @return
	 */
	public static byte[] data6AEscape(byte[] bytes,int type){
		String data = parseByteToHexStr(bytes).toUpperCase();

		if(type == 1){
			if(data.indexOf("6D02")>=0){
				data = replaceFourToTwo(data, "6D02", "6A");
			}

			if(data.indexOf("6D01")>=0){
				data = replaceFourToTwo(data, "6D01", "6D");
			}
			return parseHexStrToByte(data);
		}else{
			byte[] result = new byte[data.length()/2];
			data = replaceTwoToFour(data,"6D", "6D01");
			data = replaceTwoToFour(data,"6A", "6D02");
			result = parseHexStrToByte(data);
			return result;
		}
	}

	/**
	 * 雅迅协议数据转义
	 * 采用0x7e 作为帧头和帧尾，帧中间出现0x7e 时，需要进行转义。
	 * 转义方式为：0x7D － 0x7D ＋ 0x00；0x7E － 0x7D ＋ 0x01
	 * @param bytes
	 * @param type
	 * @return
	 */
	public static byte[] dataYxEscape(byte[] bytes,int type){
		String data = parseByteToHexStr(bytes).toUpperCase();
		try {
			if(type == 1){
				if(data.indexOf("7D01")>=0){
					data = replaceFourToTwo(data, "7D01", "7E");
				}

				if(data.indexOf("7D00")>=0){
					data = replaceFourToTwo(data, "7D00", "7D");
				}
				return parseHexStrToByte(data);
			}else{
				byte[] result = new byte[data.length()/2];
				data = replaceTwoToFour(data,"7D", "7D00");
				data = replaceTwoToFour(data,"7E", "7D01");
				result = parseHexStrToByte(data);
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return bytes;
		}
	}

	/**
	 * 深圳麦卡途科技有限公司MK6(8)000产品协议转义
	 *  0x5a转0x5b 0x01
	    0xa5转 0x5b 0x02
	    0x5b转0x5b 0x00
	 * @param bytes
	 * @param type
	 * @return
	 */
	public static byte[] dataMkEscape(byte[] bytes,int type){
		String data = parseByteToHexStr(bytes).toUpperCase();


		if(type == 1){
			if(data.indexOf("3D14")>=0){
				data = replaceFourToTwo(data, "3D14", "29");
			}
			if(data.indexOf("3D15")>=0){
				data = replaceFourToTwo(data, "3D15", "28");
			}
			if(data.indexOf("3D00")>=0){
				data = replaceFourToTwo(data, "3D00", "3D");
			}
			return parseHexStrToByte(data);
		}else{
			byte[] result = new byte[data.length()/2];
			data = replaceTwoToFour(data,"3D", "3D00");
			data = replaceTwoToFour(data,"28", "3D15");
			data = replaceTwoToFour(data,"29", "3D14");
			result = parseHexStrToByte(data);
			return result;
		}
	}
	
	
	/**
	 * 转义
	 * @param bytes
	 * @param type
	 * @return
	 */
	public static byte[] data5B5DEscape(byte[] bytes,int type){
		String data = parseByteToHexStr(bytes).toUpperCase();

		if(type == 1){
			
			if(data.indexOf("5B")>=0){
				data = replaceFourToTwo(data, "5B", "5C0B");
			}
			
			if(data.indexOf("5C")>=0){
				data = replaceFourToTwo(data, "5C", "5C0C");
			}
			
			if(data.indexOf("5D")>=0){
				data = replaceFourToTwo(data, "5D", "5C0D");
			}

			return parseHexStrToByte(data);
		}else{
			if(data.indexOf("5C0B")>=0){
				data = replaceFourToTwo(data, "5C0B", "5B");
			}
			
			if(data.indexOf("5C0C")>=0){
				data = replaceFourToTwo(data, "5C0C", "5C");
			}
			
			if(data.indexOf("5C0D")>=0){
				data = replaceFourToTwo(data, "5C0D", "5D");
			}

			return parseHexStrToByte(data);
		}
	}

	/**
	 * 转义操作（2个字符转4个字符）
	 * @param str
	 * @param thq
	 * @param thh
	 * @return
	 */
	public static String replaceTwoToFour(String str,String thq,String thh){

		StringBuffer des = new StringBuffer();				
		String s = "";
		str = String.format("%s%s", str," ");
		for(int i=0;i< str.length();i++){
			if(i%2==0){
				if(s.equals(thq)){
					s = thh;
				}
				des.append(s);
				s = "";
				s = String.format("%s%s", s,str.charAt(i));
			}else{
				s = String.format("%s%s", s,str.charAt(i));
			}
		}
		return des.toString();

	}

	/**
	 * 4个字符转2个字符
	 * @param data
	 * @param thq
	 * @param thh
	 * @return
	 */
	public static String replaceFourToTwo(String data,String thq,String thh){
		StringBuffer dataBuffer = new StringBuffer();
		for(int i=0;i< data.length();i+=2){
			String dStr = data.substring(i, i+2);
			dataBuffer.append(dStr);
			String desc = dataBuffer.toString();
			if(desc.endsWith(thq)){
				dataBuffer = new StringBuffer();
				desc = desc.substring(0, desc.length()-4);
				dataBuffer.append(desc);
				dataBuffer.append("##");
			}
		}
		return dataBuffer.toString().replaceAll("##", thh);
	}

	/**
	 * 异或运算和
	 * @param bytes
	 * @return
	 */
	public static byte[] byteOrbyte(byte[] bytes){
		byte[] orbyte = new byte[1];
		byte value = bytes[0];
		for (int i = 1; i < bytes.length; i++) {
			value = (byte) (value^bytes[i]);
		}
		orbyte[0] = value;
		return orbyte;
	}


	/**
	 * String的字符串转换成unicode的String
	 * @param strText
	 * @return
	 * @throws Exception
	 */
	public static String stringToUnicode(String strText) throws Exception {
		char c;
		String strRet = "";
		int intAsc;
		String strHex;
		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = (int) c;
			strHex = Integer.toHexString(intAsc);
			if (intAsc > 128) {
				strRet += "\\u" + strHex;
			} else {
				// 低位在前面补00
				strRet += "\\u00" + strHex;
			}
		}
		return strRet;
	}


	/**
	 * unicode的String转换成String的字符串
	 * @param hex
	 * @return
	 */
	public static String unicodeToString(String hex) {
		int t = hex.length() / 6;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < t; i++) {
			String s = hex.substring(i * 6, (i + 1) * 6);
			// 高位需要补上00再转
			String s1 = s.substring(2, 4) + "00";
			// 低位直接转
			String s2 = s.substring(4);
			// 将16进制的string转为int
			int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
			// 将int转换为字符
			char[] chars = Character.toChars(n);
			str.append(new String(chars));
		}
		return str.toString();
	}
	/**
	 * 将二进制转换成字符串
	 * @param src
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public static String byteToString(byte[] src, int startIndex, int length){
		byte[] des = new byte[length];
		int i = 0; 
		for (int j = startIndex; i < length; ++j) {
			des[i] = src[j];
			++i;
		}
		String str = null;
		try {
			str= new String(des,"gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return str.trim();
	}

	/**
	 * 数字字符串转ASCII码字符串
	 * 
	 * @param String
	 *            字符串
	 * @return ASCII字符串
	 */
	public static String StringToAsciiString(String content) {
		String result = "";
		int max = content.length();
		for (int i = 0; i < max; i++) {
			char c = content.charAt(i);
			String b = Integer.toHexString(c);
			result = result + b;
		}
		return result;
	}

	/**
	 * 字节转换成位
	 * @param bytes
	 * @return
	 */
	public static String byteTobit(byte[] bytes){
		String str = "";
		for (int j = 0; j < bytes.length; j++) {
			for(int i = 7; i >= 0; --i){
				str +=(bytes[j] & (1 << i)) == 0 ? '0' : '1';
			}
		}
		return str;
	}



	/**
	 * BCD码转为10进制串(阿拉伯数据)
	 * @param bytes
	 * @return
	 */
	public static String bcd2Str(byte[] bytes){
		StringBuffer temp=new StringBuffer(bytes.length*2);

		for(int i=0;i<bytes.length;i++){
			temp.append((byte)((bytes[i]& 0xf0)>>>4));
			temp.append((byte)(bytes[i]& 0x0f));
		}
		return temp.toString().substring(0,1).equalsIgnoreCase("0")?temp.toString().substring(1):temp.toString();
	}

	/**
	 * 10进制串转为BCD码
	 * @param asc
	 * @return
	 */
	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;

		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}

		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}

		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;

		for (int p = 0; p < asc.length()/2; p++) {
			if ( (abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ( (abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}

			if ( (abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ( (abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			}else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}

			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	/**
	 * 转换成指定长度的字符串 少的部分以空格填充
	 * @param s
	 * @param fieldLength
	 * @return
	 */
	public static String appendSpaceRight(String s, int fieldLength){
		if (s == null) {
			s = "";
		}
		String ret = s;
		int stringLength = s.length();
		if (stringLength < fieldLength) {
			for (int i = stringLength; i < fieldLength; ++i) {
				ret = ret + " ";
			}
		}
		else if (stringLength > fieldLength) {
			ret = ret.substring(0, fieldLength);
		}
		return ret;
	}

	/**
	 * 转换成指定长度的字符串 少的部分以0填充
	 * @param s
	 * @param fieldLength
	 * @return
	 */
	public static String appendRight(String s, int fieldLength){
		if (s == null) {
			s = "";
		}
		String ret = s;
		int stringLength = s.getBytes().length;
		if (stringLength < fieldLength) {
			for (int i = stringLength; i < fieldLength; ++i) {
				ret = ret + "0";
			}
		}
		else if (stringLength > fieldLength) {
			ret = ret.substring(0, fieldLength);
		}
		return ret;
	}

	/**
	 * 返回指定长度的字节数组
	 * @param str
	 * @param len
	 * @return
	 */
	public static byte[] getByteToByte(String str,int len){
		byte[] body = new byte[len];
		int dstPos = 0;
		//省域ID
		System.arraycopy(str.getBytes(), 0, body, dstPos, str.getBytes().length);
		return body;
	}


	/**
	 * 字符串转换成十六进制字符串
	 * @param str
	 * @return
	 */
	public static String str2HexStr(String str) {

		char[] chars = "0123456789ABCDEF".toCharArray();

		StringBuilder sb = new StringBuilder("");

		byte[] bs = str.getBytes();

		int bit;

		for (int i = 0; i < bs.length; i++) {

			bit = (bs[i] & 0x0f0) >> 4;

		sb.append(chars[bit]);

		bit = bs[i] & 0x0f;

		sb.append(chars[bit]);

		}

		return sb.toString();

	}

	/**
	 * 十六进制转换字符串
	 * @param hexStr
	 * @return
	 */
	public static String hexStr2Str(String hexStr) {

		String str = "0123456789ABCDEF";

		char[] hexs = hexStr.toCharArray();

		byte[] bytes = new byte[hexStr.length() / 2];

		int n;

		for (int i = 0; i < bytes.length; i++) {

			n = str.indexOf(hexs[2 * i]) * 16;

			n += str.indexOf(hexs[2 * i + 1]);

			bytes[i] = (byte) (n & 0xff);

		}
		return new String(bytes);

	} 

	/**
	 * 将 ascii转换成字符串
	 * @param ASCIIs
	 * @return
	 */
	public static String ascii2String(String ASCIIs) {  
		String[] ASCIIss = ASCIIs.split(" ");  
		StringBuffer sb = new StringBuffer();  
		for (int i = 0; i < ASCIIss.length; i++) {  
			sb.append((char) ascii2Char(Integer.parseInt(ASCIIss[i])));  
		}  
		return sb.toString();  
	}

	public static char ascii2Char(int ASCII) {  
		return (char) ASCII;  
	}  

	public static int char2ASCII(char c) {  
		return (int) c;  
	}  

	/** 
	 * 校验和 
	 * @param msg 需要计算校验和的byte数组 
	 * @param length 校验和位数 
	 * @return 计算出的校验和数组 
	 */  
	public static byte[] sumCheck(byte[] msg, int length) {  
		long mSum = 0;  
		byte[] mByte = new byte[length];  

		/** 逐Byte添加位数和 */  
		for (byte byteMsg : msg) {  
			long mNum = ((long)byteMsg >= 0) ? (long)byteMsg : ((long)byteMsg + 256);  
			mSum += mNum;  
		} /** end of for (byte byteMsg : msg) */  

		/** 位数和转化为Byte数组 */  
		for (int liv_Count = 0; liv_Count < length; liv_Count++) {  
			mByte[length - liv_Count - 1] = (byte)(mSum >> (liv_Count * 8) & 0xff);  
		}

		return mByte;  
	}

	/**
	 * CRC运算
	 * @param data
	 * @return
	 */
	public static String getCRC16( byte[] data){ 
		int CRCTABLE[]={ 
				0X0000, 0X1189, 0X2312, 0X329B, 0X4624, 0X57AD, 0X6536, 0X74BF,
				0X8C48, 0X9DC1, 0XAF5A, 0XBED3, 0XCA6C, 0XDBE5, 0XE97E, 0XF8F7,
				0X1081, 0X0108, 0X3393, 0X221A, 0X56A5, 0X472C, 0X75B7, 0X643E,
				0X9CC9, 0X8D40, 0XBFDB, 0XAE52, 0XDAED, 0XCB64, 0XF9FF, 0XE876,
				0X2102, 0X308B, 0X0210, 0X1399, 0X6726, 0X76AF, 0X4434, 0X55BD,
				0XAD4A, 0XBCC3, 0X8E58, 0X9FD1, 0XEB6E, 0XFAE7, 0XC87C, 0XD9F5,
				0X3183, 0X200A, 0X1291, 0X0318, 0X77A7, 0X662E, 0X54B5, 0X453C,
				0XBDCB, 0XAC42, 0X9ED9, 0X8F50, 0XFBEF, 0XEA66, 0XD8FD, 0XC974,
				0X4204, 0X538D, 0X6116, 0X709F, 0X0420, 0X15A9, 0X2732, 0X36BB,
				0XCE4C, 0XDFC5, 0XED5E, 0XFCD7, 0X8868, 0X99E1, 0XAB7A, 0XBAF3,
				0X5285, 0X430C, 0X7197, 0X601E, 0X14A1, 0X0528, 0X37B3, 0X263A,
				0XDECD, 0XCF44, 0XFDDF, 0XEC56, 0X98E9, 0X8960, 0XBBFB, 0XAA72,
				0X6306, 0X728F, 0X4014, 0X519D, 0X2522, 0X34AB, 0X0630, 0X17B9,
				0XEF4E, 0XFEC7, 0XCC5C, 0XDDD5, 0XA96A, 0XB8E3, 0X8A78, 0X9BF1,
				0X7387, 0X620E, 0X5095, 0X411C, 0X35A3, 0X242A, 0X16B1, 0X0738,
				0XFFCF, 0XEE46, 0XDCDD, 0XCD54, 0XB9EB, 0XA862, 0X9AF9, 0X8B70,
				0X8408, 0X9581, 0XA71A, 0XB693, 0XC22C, 0XD3A5, 0XE13E, 0XF0B7,
				0X0840, 0X19C9, 0X2B52, 0X3ADB, 0X4E64, 0X5FED, 0X6D76, 0X7CFF,
				0X9489, 0X8500, 0XB79B, 0XA612, 0XD2AD, 0XC324, 0XF1BF, 0XE036,
				0X18C1, 0X0948, 0X3BD3, 0X2A5A, 0X5EE5, 0X4F6C, 0X7DF7, 0X6C7E,
				0XA50A, 0XB483, 0X8618, 0X9791, 0XE32E, 0XF2A7, 0XC03C, 0XD1B5,
				0X2942, 0X38CB, 0X0A50, 0X1BD9, 0X6F66, 0X7EEF, 0X4C74, 0X5DFD,
				0XB58B, 0XA402, 0X9699, 0X8710, 0XF3AF, 0XE226, 0XD0BD, 0XC134,
				0X39C3, 0X284A, 0X1AD1, 0X0B58, 0X7FE7, 0X6E6E, 0X5CF5, 0X4D7C,
				0XC60C, 0XD785, 0XE51E, 0XF497, 0X8028, 0X91A1, 0XA33A, 0XB2B3,
				0X4A44, 0X5BCD, 0X6956, 0X78DF, 0X0C60, 0X1DE9, 0X2F72, 0X3EFB,
				0XD68D, 0XC704, 0XF59F, 0XE416, 0X90A9, 0X8120, 0XB3BB, 0XA232,
				0X5AC5, 0X4B4C, 0X79D7, 0X685E, 0X1CE1, 0X0D68, 0X3FF3, 0X2E7A,
				0XE70E, 0XF687, 0XC41C, 0XD595, 0XA12A, 0XB0A3, 0X8238, 0X93B1,
				0X6B46, 0X7ACF, 0X4854, 0X59DD, 0X2D62, 0X3CEB, 0X0E70, 0X1FF9,
				0XF78F, 0XE606, 0XD49D, 0XC514, 0XB1AB, 0XA022, 0X92B9, 0X8330,
				0X7BC7, 0X6A4E, 0X58D5, 0X495C, 0X3DE3, 0X2C6A, 0X1EF1, 0X0F78
		}; 

		int CRCVal= 0xffff;; 
		int i=0; 

		for(i=0; i <data.length; i++){ 
			CRCVal = CRCTABLE[(CRCVal ^ data[i]) & 0xFF] ^ (CRCVal>>8); 
		} 

		return parseByteToHexStr(sortToByte(intToBytes(~CRCVal, 2))); 
	}


	/**
	 * 恒天高科GPRS工业采集控制器HT-GPRS-V1.3协议CRC运算
	 * @param data
	 * @return
	 */
	public static byte[] getHtCRC16( byte[] data){ 

		int CRCTABLE[]={ 
				0x0000,0x1021,0x2042,0x3063,0x4084,0x50a5,0x60c6,0x70e7,
				0x8108,0x9129,0xa14a,0xb16b,0xc18c,0xd1ad,0xe1ce,0xf1ef,
				0x1231,0x0210,0x3273,0x2252,0x52b5,0x4294,0x72f7,0x62d6,
				0x9339,0x8318,0xb37b,0xa35a,0xd3bd,0xc39c,0xf3ff,0xe3de,
				0x2462,0x3443,0x0420,0x1401,0x64e6,0x74c7,0x44a4,0x5485,
				0xa56a,0xb54b,0x8528,0x9509,0xe5ee,0xf5cf,0xc5ac,0xd58d,
				0x3653,0x2672,0x1611,0x0630,0x76d7,0x66f6,0x5695,0x46b4,
				0xb75b,0xa77a,0x9719,0x8738,0xf7df,0xe7fe,0xd79d,0xc7bc,
				0x48c4,0x58e5,0x6886,0x78a7,0x0840,0x1861,0x2802,0x3823,
				0xc9cc,0xd9ed,0xe98e,0xf9af,0x8948,0x9969,0xa90a,0xb92b,
				0x5af5,0x4ad4,0x7ab7,0x6a96,0x1a71,0x0a50,0x3a33,0x2a12,
				0xdbfd,0xcbdc,0xfbbf,0xeb9e,0x9b79,0x8b58,0xbb3b,0xab1a,
				0x6ca6,0x7c87,0x4ce4,0x5cc5,0x2c22,0x3c03,0x0c60,0x1c41,
				0xedae,0xfd8f,0xcdec,0xddcd,0xad2a,0xbd0b,0x8d68,0x9d49,
				0x7e97,0x6eb6,0x5ed5,0x4ef4,0x3e13,0x2e32,0x1e51,0x0e70,
				0xff9f,0xefbe,0xdfdd,0xcffc,0xbf1b,0xaf3a,0x9f59,0x8f78,
				0x9188,0x81a9,0xb1ca,0xa1eb,0xd10c,0xc12d,0xf14e,0xe16f,
				0x1080,0x00a1,0x30c2,0x20e3,0x5004,0x4025,0x7046,0x6067,
				0x83b9,0x9398,0xa3fb,0xb3da,0xc33d,0xd31c,0xe37f,0xf35e,
				0x02b1,0x1290,0x22f3,0x32d2,0x4235,0x5214,0x6277,0x7256,
				0xb5ea,0xa5cb,0x95a8,0x8589,0xf56e,0xe54f,0xd52c,0xc50d,
				0x34e2,0x24c3,0x14a0,0x0481,0x7466,0x6447,0x5424,0x4405,
				0xa7db,0xb7fa,0x8799,0x97b8,0xe75f,0xf77e,0xc71d,0xd73c,
				0x26d3,0x36f2,0x0691,0x16b0,0x6657,0x7676,0x4615,0x5634,
				0xd94c,0xc96d,0xf90e,0xe92f,0x99c8,0x89e9,0xb98a,0xa9ab,
				0x5844,0x4865,0x7806,0x6827,0x18c0,0x08e1,0x3882,0x28a3,
				0xcb7d,0xdb5c,0xeb3f,0xfb1e,0x8bf9,0x9bd8,0xabbb,0xbb9a,
				0x4a75,0x5a54,0x6a37,0x7a16,0x0af1,0x1ad0,0x2ab3,0x3a92,
				0xfd2e,0xed0f,0xdd6c,0xcd4d,0xbdaa,0xad8b,0x9de8,0x8dc9,
				0x7c26,0x6c07,0x5c64,0x4c45,0x3ca2,0x2c83,0x1ce0,0x0cc1,
				0xef1f,0xff3e,0xcf5d,0xdf7c,0xaf9b,0xbfba,0x8fd9,0x9ff8,
				0x6e17,0x7e36,0x4e55,0x5e74,0x2e93,0x3eb2,0x0ed1,0x1ef0
		}; 

		int CRCVal= 0;
		int i=0; 

		for(i=0; i <data.length; i++){ 
			CRCVal = CRCTABLE[((CRCVal>>8) ^ data[i]) & 0x00FF] ^ (CRCVal<<8); 
		} 

		return sortToByte(intToBytes(CRCVal, 2)); 
	}

	/**
	 * 深圳市航天无线通信技术有限公司213GD协议CRC运算
	 * @param data
	 * @return
	 */
	public static byte[] getHttxCRC16(byte[] data){ 

		int crc = 0; 
		crc = 0xffff; 
		int ilen = data.length;
		for (int i = 0; i < ilen; i++) {
			crc = GetFcs(crc,data[i]); 
		}
		crc ^= 0xffff;

		return intToBytes(crc, 2); 
	}

	public static int GetFcs(int fcs, int src) { 

		int CRCTABLE[]={ 
				0x0000, 0x1189, 0x2312, 0x329b, 0x4624, 0x57ad, 0x6536, 0x74bf, 
				0x8c48, 0x9dc1, 0xaf5a, 0xbed3, 0xca6c, 0xdbe5, 0xe97e, 0xf8f7, 
				0x1081, 0x0108, 0x3393, 0x221a, 0x56a5, 0x472c, 0x75b7, 0x643e, 
				0x9cc9, 0x8d40, 0xbfdb, 0xae52, 0xdaed, 0xcb64, 0xf9ff, 0xe876, 
				0x2102, 0x308b, 0x0210, 0x1399, 0x6726, 0x76af, 0x4434, 0x55bd, 
				0xad4a, 0xbcc3, 0x8e58, 0x9fd1, 0xeb6e, 0xfae7, 0xc87c, 0xd9f5, 
				0x3183, 0x200a, 0x1291, 0x0318, 0x77a7, 0x662e, 0x54b5, 0x453c, 
				0xbdcb, 0xac42, 0x9ed9, 0x8f50, 0xfbef, 0xea66, 0xd8fd, 0xc974, 
				0x4204, 0x538d, 0x6116, 0x709f, 0x0420, 0x15a9, 0x2732, 0x36bb, 
				0xce4c, 0xdfc5, 0xed5e, 0xfcd7, 0x8868, 0x99e1, 0xab7a, 0xbaf3, 
				0x5285, 0x430c, 0x7197, 0x601e, 0x14a1, 0x0528, 0x37b3, 0x263a, 
				0xdecd, 0xcf44, 0xfddf, 0xec56, 0x98e9, 0x8960, 0xbbfb, 0xaa72, 
				0x6306, 0x728f, 0x4014, 0x519d, 0x2522, 0x34ab, 0x0630, 0x17b9, 
				0xef4e, 0xfec7, 0xcc5c, 0xddd5, 0xa96a, 0xb8e3, 0x8a78, 0x9bf1, 
				0x7387, 0x620e, 0x5095, 0x411c, 0x35a3, 0x242a, 0x16b1, 0x0738, 
				0xffcf, 0xee46, 0xdcdd, 0xcd54, 0xb9eb, 0xa862, 0x9af9, 0x8b70, 
				0x8408, 0x9581, 0xa71a, 0xb693, 0xc22c, 0xd3a5, 0xe13e, 0xf0b7, 
				0x0840, 0x19c9, 0x2b52, 0x3adb, 0x4e64, 0x5fed, 0x6d76, 0x7cff, 
				0x9489, 0x8500, 0xb79b, 0xa612, 0xd2ad, 0xc324, 0xf1bf, 0xe036, 
				0x18c1, 0x0948, 0x3bd3, 0x2a5a, 0x5ee5, 0x4f6c, 0x7df7, 0x6c7e, 
				0xa50a, 0xb483, 0x8618, 0x9791, 0xe32e, 0xf2a7, 0xc03c, 0xd1b5, 
				0x2942, 0x38cb, 0x0a50, 0x1bd9, 0x6f66, 0x7eef, 0x4c74, 0x5dfd, 
				0xb58b, 0xa402, 0x9699, 0x8710, 0xf3af, 0xe226, 0xd0bd, 0xc134, 
				0x39c3, 0x284a, 0x1ad1, 0x0b58, 0x7fe7, 0x6e6e, 0x5cf5, 0x4d7c, 
				0xc60c, 0xd785, 0xe51e, 0xf497, 0x8028, 0x91a1, 0xa33a, 0xb2b3, 
				0x4a44, 0x5bcd, 0x6956, 0x78df, 0x0c60, 0x1de9, 0x2f72, 0x3efb, 
				0xd68d, 0xc704, 0xf59f, 0xe416, 0x90a9, 0x8120, 0xb3bb, 0xa232, 
				0x5ac5, 0x4b4c, 0x79d7, 0x685e, 0x1ce1, 0x0d68, 0x3ff3, 0x2e7a, 
				0xe70e, 0xf687, 0xc41c, 0xd595, 0xa12a, 0xb0a3, 0x8238, 0x93b1, 
				0x6b46, 0x7acf, 0x4854, 0x59dd, 0x2d62, 0x3ceb, 0x0e70, 0x1ff9, 
				0xf78f, 0xe606, 0xd49d, 0xc514, 0xb1ab, 0xa022, 0x92b9, 0x8330, 
				0x7bc7, 0x6a4e, 0x58d5, 0x495c, 0x3de3, 0x2c6a, 0x1ef1, 0x0f78 
		}; 

		int xor = 0; 
		int iresult = 0; 
		xor = fcs; 
		xor ^= src; 
		iresult = (((fcs) >> 8) ^ CRCTABLE[xor & 0x00ff]); 
		return iresult; 
	}



	/**
	 * 深圳市城市漫步科技有限公司协议CRC运算
	 * @param data
	 * @return
	 */
	public static byte[] getCsmbCRC16( byte[] data){ 

		int crc = 0;
		int q = 0;
		int i=0;
		int c = 0;

		for(i=0; i <data.length; i++){ 
			c = data[i];
			q = (crc ^ c) & 0x0F;
			crc = (crc >> 4) ^(q * 0x1081);
			q = (crc ^(c >> 4)) & 0xF0;
			crc = (crc >> 4) ^(q * 0x1081);
		} 
		int crcval = (((crc << 8) & 0xFF00) | ((crc >> 8) & 0xFF));
		return sortToByte(intToBytes(crcval, 2)); 
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {

	//	System.out.println(ParseUtil.parseByteToHexStr(getHttxCRC16(parseHexStrToByte("a8070820a1b30200a5b3020069220000a9b30200abb30200adb3020000000000000000000000000000000000afb30200b1b3020000000000b3b30200e9290000b7b30200753103009d310300b7b30200b7b30200dd2c0000372c00005f2b0000b7b30200b7b30200b7b30200b7b302002d800200b7b30200b7b30200b7b30200b7b302007f2e0000b7b30200b7b30200f72d0000132e0000b7b30200b7b30200af8d0200b7b30200b7b30200b7b30200b7b30200b7b30200b7b30200b7b30200b7b30200b7b30200b7b3020000f002f800f0b2f80aa090e8000c82448344aaf10107da4501d100f0a7f8aff2090ebae80f0013f0010f18bffb1a43f001031847545b0300945b03000a444ff0000c10f8013b13f0070408bf10f8014b1d1108bf10f8015b641e05d010f8016b641e01f8016bf9d113f0080f1ebf10f8014bad1c0c1b09d16d1e58bf01f801cbfad505e014f8016b01f8016b6d1ef9d59142d6d3704700000023002400250026103a28bf78c1fbd8520728bf30c148bf0b6070476e2927f0e982702927f0e783662932f0c485652932f0c185672932f0be856129aff3008003681b0a28bf41f08001692927f09a82642927f09782752927f094826f2927f06883782927f0b483e92928f04181e42928f03e81f52928f03b81ef2927f06583f82927f0b3830368db0928bf41f08001632928f00a81732928f00f81e32928f01a81f32928f01f81002070471fb5aff3008028f01ffa04000020002131f008f8401c60600020002130f0ecffe0601fbd10b510bd28f074fb1146fff7e7ff22f0f5fc28f00ffefff7f3ff29f089f8000072b64ff00000704762b64ff00000704730bf00004ff0ff304ff0e021c1f82a0dc1f8290d7d4908604ff0e021c1f8280d704710b5fff7e4ff01207949086023207749a039c1f8a400aa2075490c31086055207349a039c1f8ac0000bf70480830006810f4806ff9d003206d490860aa206b49a039c1f8ac00552069490c310860fff7c2ff10bd10b5fff7baff45f23a006349a03908604ff480306249086000205f49a039c1f8ac01c1f8c8010846d0f888000121b1eb106f0cd10120584920390860aa205649a039c1f88c0055205449143908600020524920390860aa205049a039c1f88c0055204d491439086000bf4b481838006810f0407ff9d149480838006800f0040040f020004549a039c1f8a00100bf43480838006810f0400ff9d001203f496c3108600b203d49a039c1f88400aa203a4914390860552008600120374920390860aa203549143908605520086000bf32481838006810f0807ff9d002202f496431086000bf2d481838006810f0806ff9d000bf294818380068c0f30e000b2806d0264818380068c0f307400028f1d10320224920390860aa202049a039c1f88c0055201d491439086000bf1b481838006810f0007ff9d001201849086023201649a039c1f8a400aa2014490c31086055201249a039c1f8ac0000bf0f480830006810f4806ff9d003200c490860aa200a49a039c1f8ac00552008490c31086045f23a000549a039086006484ff0e021c861fff7f7fe10bd2ced00e0a0c00f40a8c10f4000a60e0098489949086099484860002088600804c860974808610020c8614ff070604862002088624ff4404008640020486488649048c864904808650020c86548668f488f4908608f4808628f480864401308666ff080708a4980310860002088490861086308650867c1f89000884888618848c861c016c863c865c867c1f89c000846806b40f4800088630846806b40f4002088630846c06b40f48070c8630846c06b40f40070c8630846c06b40f00070c8630846c06d40f08000c8650846c06b40f48040c8630846c06b40f48010c8630846806b40f40010886370476e486e4908607047614800686d4908405f4908600846c06820f44f20c8600846006968490840594908610846406a20f0706048620846006c20f47f2063490843534908640846c06c20f44720c8640846006d5c49c91e08405d4908434c4908650846406e20f0706040f0206048664d48006840f2c33188434b4908600846006a5449084040f00220474908620846006c41f223318843434908644248803000684d4908404049c1f880000846806b40f4801088630846806b40f4001088630846c06b40f48070c8630846c06b40f48070c8630846c06d40f08000c8650846c06b40f48040c86370472948006835490840274908600846406840f0a05048600846c06820f47040c8600846006940f60341884340f0857040f00800801c1c4908614ff0706048624ff4404008641b48c8640846006d40f60f41884340f4406013490865002048661648174908601748401c08626ff00f000864001108666ff4805011498031086040f203300e49c8611a48c8639020c8650020c867c1f89c000846806b40f40010886370475500a0c000c002403c0014140a010a0100800020f00c2003fffebfff00c00920feff7baff3efffff000c6000fff3fff9def3c807c4c00f40f00ff0fff3f3f0ff0a200a0002080a03ffff1fbfffefffcf020080002de9f047044689461546332c03d94ff0ff30bde8f087fff781fd102c13d3a4f110077e0907f01f08012000fa08f04ff0e02101eb8601c1f800014ff0e021394481f800542be0042c04d0052c0bd0062c1bd111e09548006840f480304ff0e021c1f8240d12e09148006840f400304ff0e021c1f8240d09e08c48006840f480204ff0e021c1f8240d00e000bf00bf042c05d3271f4ff0e021394481f8185dfff741fd0020b5e72de9f047044689461546332c03d94ff0ff30bde8f087fff72efd102c13d3a4f110077e0907f01f08012000fa08f04ff0e02101eb8601c1f800014ff0e021394481f800542be0042c04d0052c0bd0062c1bd111e06c48006840f480304ff0e021c1f8240d12e06748006840f400304ff0e021c1f8240d09e06348006840f480204ff0e021c1f8240d00e000bf00bf042c05d3271f4ff0e021394481f8185d0020b7e730b50146332902d94ff0ff3030bd102913d3a1f110035a0903f01f040120a0404ff0e02505eb8205c5f8800100204ff0e0251d4485f800042ce0042904d005290bd006291bd111e04648006820f480304ff0e025c5f8240d12e04248006820f400304ff0e025c5f8240d09e03d48006820f480204ff0e025c5f8240d00e000bf00bf042906d30b1f00204ff0e0251d4485f8180d0020b9e72de9f0410446332c03d94ff0ff30bde8f081102c02d24ff0ff30f8e7fff78efc102c0cd3a4f11006750906f01f070120b8404ff0e02101eb8501c1f88001fff781fc0020e3e72de9f0410446332c03d94ff0ff30bde8f081fff770fc102c0dd3a4f11006750906f01f070120b8404ff0e02101eb8501c1f8000123e0042c04d0052c0bd0062c1bd111e01048006840f480304ff0e021c1f8240d12e00b48006840f400304ff0e021c1f8240d09e00748006840f480204ff0e021c1f8240d00e000bf00bffff73efc0020c3e724ed00e010b5f848b0f8190048f6a041884206daf448b0f81900401cf249a1f81900f148b0f80d00b0f5fa7f06daee48b0f80d00401cec49a1f80d00ea48b0f80f00022806dae848b0f80f00401ce649a1f80f00e448b0f81100b0f5c87f06dae148b0f81100401cdf49a1f81100de48b0f81300642806dadb48b0f81300401cd949a1f81300d848b0f81500642806dad548b0f81500401cd349a1f81500d248b0f81700642806dacf48b0f81700401ccd49a1f81700cc48d0f83d00b0f5966f06d2c948d0f83900401cc749c1f83900c548b0f81b004ff6ff71884206dac248b0f81b00401cc049a1f81b00be48b0f81f004ff6ff71884206dabb48b0f81f00401cb949a1f81f00b748b0f823004ff6ff71884206dab448b0f82300401cb249a1f82300b048b0f82710b048b0f86400814206daac48b0f82700401caa49a1f82700a948d0f84500401ca749c1f845000846d0f84900401cc1f8490002f062f802f028f9a248406b10f0804f04d1a1480088401c9f49088010bd9f48806810f0010f65d19c48806800f00e00042802d00c285bd132e09848406900f0010001282bd191487b30b0f8940740f2dc51884204db00208c497b31a1f894078e480068c1b289487b30b0f8942700f296708154a0f29670b0f89407401c83497b31a1f894070846b0f8940740f2dc51884204db00207d497b31a1f8940728e07e48406900f00100012820d17b480068c1b276487b30b0f8942700f296708154a0f29670b0f8940740f2db51884205db00206e497b31a1f8940708e06b487b30b0f89407401c69497b31a1f8940700e000bf00bf70476a48806810f0010f4cd16748806800f00e00042802d00c2842d120e06348406900f00100012819d160480068c1b259487b304288001d8154001f408840f2ff31884204db002053497b31488006e051487b304088401c4f497b31488021e05248406900f00100012819d14f480068c1b249487b304288001d8154001f408840f2ff31884204db002043497b31488006e041487b304088401c3f497b31488000e000bf00bf704710b54148806810f0010f6fd13e48806800f00e00042802d00c287bd134e03a48406900f0010001282dd1384800f02cf935480068c1b22e487b30b0f80c2400f20e408154a0f20e40b0f80c2400f20e40815c2fa000f06bf925487b30b0f80c0440f28331884205db002021497b31a1f80c0408e01e487b30b0f80c04401c1c497b31a1f80c0446e01f48406900f0010001282ad11c480068c2b215487b30b0f80c1400f20e404254a0f20e40b0f80c2400f20e40815c16a000f039f90c487b30b0f80c0440f28331884205db002008497b31a1f80c0408e005487b30b0f80c04401c03497b31a1f80c0414e014e011e06e1b0010ad05001000c0092068c10720008009400000014000c00040cc0100107325000000bf00bf10bd10b53948016839a000f004f904203c49c1f8400101203b49087010bd10b50820324908603948d0f8800010f0050f2ad03648a430006810f0020f0cd002203249ac31086000203049c1f8b00024f061fd2fa000f0dff82c48a830006810f0020f0cd002202949ac31086000202749c1f8b40024f04efd29a000f0ccf84ff0ff302249ac31086010bd10b50320284908600020154934390860fe202449086124f0c1f9234880893b2805da21488089401c1f49086602e000201d490866fff712fa1d48c08840f480411b48c1804ff4407006498039086000200c49c1f8b40002200a49ac31086010bd000040c10f4045494e54322c455854494e543d25640d0a00000000c00f40000000100080024045494e54332c526973650d0a0000000045494e54332c46616c6c0d0a0000000000400240d71b00107507001001460020fe4ad2f849208a4200d301207047fb49d1f849100160704710b502460020f74bd3f8453014681b1b8b4200d3012010bdf249d1f845100160704700eb400303eb00139ab2042101e04b1c99b29142fbdb70472de9f04104460d46002631e0e848c06b40f01000e649c8631120fff7e5ff002716e0a0570121b940084206d0e048806b40f01000de49886305e0dc48c06b40f01000da49c8631120fff7ceff781cc7b2082fe6dbd648806b40f01000d44988631120fff7c1ff0420fff7beff701cc6b2ae42cbdbce48806b40f01000cc498863bde8f0810fb42de9f041adf5bc6d002400250027a04600260df2fc50009001a8009addf8f81526f0d0fa0446c1480078002827d0a5b222e0002600e0761cbd488642fbd33f2d04dd3f27a5f13f0085b201e02f460025b848008880b1b848007868b10020b549088001a800eb08013a46822026f043f808eb07001ffa80f8002ddad103e0e1b201a8fff77aff20460df5bc6dbde8f0015df814fb70b5b2b004460d460026c821684626f05bfe3c2d4adc00bf38e020780a21b1eb201f09dc2078c1eb20104130c0b206eb46010df8010007e02078302101eb201006eb46010df80100207800f00f000a280adb207800f00f003730c1b206eb4600401c0df8001008e0207800f00f01303106eb4600401c0df80010202106eb4600801c0df80010641c701c86b2ae42c4db002106eb4600c01c0df8001000208df8c700694680a0fff760ff02e082a0fff75cff32b070bd70b504460d4606e02178022023f093ff641c681e85b2002df6d170bd70b504460d4606e02178002023f085ff641c681e85b2002df6d170bdf0b5034600240025002609b90020f0bd002409e0185d175db84202d1681c85b200e003e0601c84b28c42f3db00bf8d4200d101263046eae710b502460023002000bf03e0d45c60405c1c23b28b42f9db10bd0146002048b2302805db392803dca0f1300250b217e0612808db662806dca0f1610250b200f10a0250b20ce0412808db462806dca0f1410250b200f10a0250b201e04ff0ff30704710b586b0044614224da101a826f066fd01a8005706b010bd30b585b0044643f23005142246a1684626f058fd684600eb24100078050204f00f001df800000543284605b030bd30b585b004460d4614223ba1684626f042fd1cb94ff0ff3005b030bd684600eb2510007804f8010b05f00f001df8000004f8010b0020efe72de9f04303461d468e46002423e02857302806db2857392803dc285d303847b217e02857412806db2857462803dc285d373847b20de02857612806db2857662803dc285d573847b203e04ff0ff30bde8f0832f55641c9442d9db4ff0000c00260de0701c285c15f8068040ea08104ffa80f960460cf1010c0ef80090b61ca642efdb0ffa8cf0e2e700006e1b001000c0092066c10720a086010022c2072027c207204f4244cafdbedd3a25730d0a000000004f4244cafdbeddb3acb3a40d0a00000030313233343536373839414243444546000000002de9f04385b004460d4616461422aff22401684626f0b8fc2f464ff00008c14617e014f80900c0f303101df80000c2b2404608f101083a5414f8090000f00f001df80000c2b2404608f101083a5409f10109b145e5db002007f808000ffa88f005b0bde8f08370b503460d46002400261878fff7f7fe44b2b4f1ff3f01d1701e70bd5878fff7eefe46b2b6f1ff3f02d14ff0ff30f4e7200144b234432c700020eee70146002210e0885c302802db885c392808dd885c2a2805d0885c232802d04ff0ff307047501cc2b2885c0028ebd10ab9401ef6e70020f4e7000030b502460023002013461a464b40dcb2664d35f8144084ea222030bdf0b504460d46002600274ff6ff7605e021783046fff7e6ff0646641c2800a5f101018db2f4d1a6f57040b83802d04ff0ff3700e000273846f0bd70b504460e4600254ff6ff7505e014f8011b2846fff7c9ff0546301ea6f101018eb2f4dc4ff6ff704540284670bdf0b504460d4616460027374605e014f8011b3846fff7b2ff0746281ea5f101018db2f4dc3846f0bd30b502460023002013461a464b40dcb23c4d35f8144084ea222030bdf0b504460d46002600274ff6ff7605e021783046fff7e6ff0646641c2800a5f101018db2f4d1a6f57040b83802d04ff0ff3700e000273846f0bd70b504460e4600254ff6ff7505e014f8011b2846fff7c9ff0546301ea6f101018eb2f4dc4ff6ff704540284670bd10b50246002300200b46190008d103e014780444e0b2521c1478002cf8d108e005e014780444e0b2521c5c1ea3b2002bf7d1c443e0b2441ce0b210bd10b50246002300240b46190008d103e010782044c4b2521c10780028f8d108e005e010782044c4b2521c581e83b2002bf7d10cb9002010bd4ff0ff30fbe70000724e03007250030010b540f2a521ff48fff7d7fdfd49a1f8a50210bd10b54ff4b971fb48fff7cdfdf949a1f8720110bd70b596b00024182110a826f093fb14210ba826f08ffb0025f24908a84cc94cc0f1a103c9cde906011422f14910a826f0dffa1422ee4973310ba826f0d9fa40f2a721e64826f022fb00bf04e04020e3490855601cc4b2042cf8db24f00afa0020de498880e3a1dd48801d26f07dfae5a1da48173026f078fa0120d84988773321d6481f3026f002fbe0a1d4481f3026f06bfa0121d14880f8521080f85310e1a1543026f061fae3a1cc48653026f05cfae2a1ca486c3026f057fac848d2a1097880f89f10d0a1097880f8d210042100f2051026f0dbfa0421c548443826f0d6fa4ff0ff30bd49c1f805010021c0484838416078210181692141818a2181810521c1810146c88930f005facde90401cda00cc8049830f03cfacde90201cba00cc8029830f035facde900012ff07ffdc949c1f8fa000621ae48483801820146088a30f0e8f9cde90401bea00cc8049830f01ffacde90201bda00cc8029830f018facde9000130f086f9c6b2304631f06ff8b849fa3148600f219d4848384182818241f29411c182002101834183042181830521c183f0210184002141840f2101850821c187aca0fff716fc142210a9904826f01efa8e4826f0fcf9c5b2062d0adba8a0fff708fcaa1f894811180622dc3026f00efa04e0a6a18548dc3026f0c4f914220ba98248733026f002fa1a218048153026f04bfa0121784880f87c1180f87d11332100f5bf7026f040fa062206a97748313026f0ecf940f6b8317448a0f8641001216d4880f8b311142180f8ba11102180f8bb1100216d48c0f86f100120664981f8d40140f6b83169488730a0f801100121614880f8d711002180f8d8114ff4fa7100f5ea70a0f8051000215b4880f8db110a2180f8dc11012180f8dd1141f2707100f5ea7041810121534880f8e011062180f8ff110c2208a900f2e11026f0a6f900214d4880f80012642100f5ea70a0f82d10494880f80312002180f80412032180f80512002180f80612012180f807123c2100f5ea70818600213f4880f80a123c2100f5ea70a0f8371000213a4880f80d124ff4347100f5ea7041870021364880f810124ff4347100f5ea70a0f83d1016213f3026f0b8f900212f4880f83012052180f8311200f2322026f0adf96e212e48ea3026f0a8f9fff74cfe002024f068fa012024f065fa01204a49887416b070bd10b58ab00024182104a826f0e8f91f490c3101a80ec90ec014221f4926f03df93f211d48873026f086f992211b48c63026f081f924f072f800bf04e0402011490855601cc4b2042cf8db00200e498880012081f8d40140f6b8310f488730a0f801100121084880f8d711002180f8d8114ff4fa7100f5ea70a0f805100021014880f8db114fe0600400102b430010745203004f00420044000000ad0500103131332e39382e3234312e363500000035313531000000006f62642e6c69766574656c656d61746963732e636f6d000035342e37322e3131392e3138340000003930383800000000434d4e45540000009a999999999923400ad7a3703d0ad73f07070010736574206b65790d0a000000736574206b6579310d0a000031323334353600003e1b00100a2180f8dc11012180f8dd1141f2707100f5ea7041810121a0f5ea7080f8e011062180f8ff110c2201a900f2e11026f0a1f80021f84880f80012642100f5ea70a0f82d10f44880f80312002180f80412032180f80512002180f80612012180f807123c2100f5ea7081860021ea4880f80a123c2100f5ea70a0f837100021e64880f80d124ff4347100f5ea7041870021e14880f810124ff4347100f5ea70a0f83d1016213f3026f0b3f80021da4880f83012052180f8311200f2322026f0a8f86e21d64826f0a4f8fff748fd002024f064f9012024f061f90120d14988740ab010bd10b543f62441ce4826f091f840f2a721c94826f08cf81621cb4826f088f81621c94826f084f8c84800210a460b460c461ec010bd38b5002400200090c048407c08b9002038bd23f065ff04224ff4c021684624f0baf8bda204216846fff7cefa012804d1bc480068b0f5e02f0fd34ff4c020b8490860062024f01ef90020b64988804ff4c020b3490839086020e0b1480068e8b9b0480838006800f50070ad49086008460068a0f5c020c0f34f20aa498880a8480068b0f5e02f09d34ff4c020a5490860062024f0f7f80020a349888023f0f5fba149c1f8ba009948406dc1f80e00974854300069c1f80600954854308068c1f80a00002404e0402097490855601c84b2042cf8db94488088401c92498880fff7bcfc00bf8f48016890a0fff7abf94ff4ba728b4801688b4824f0dff88948006800f500708749086008460068b0f5e02f02d38da0fff796f900207b494874012076e710b5002493a0fff78cf9092406e023f0d6fee0b224f0a5f8601c84b2202cf6db4ff410207749c1f81600c1f8120001206d494874fff753ff10bd10b58ca0fff770f9082024f08df84ff400206d49c1f8b200c1f8b6008da0fff763f9012062494874fff73cff10bd10b500244ff4ba71644825f0b1ff00bf04e0402061490855601cc4b2042cf8db00205e498880c1f80600c1f80a004ff41020c1f81600c1f812004ff40020c1f8b200c1f8b60000208876086281f82400a1f82500062081f8d10042f205114f48d130a0f80110891d401c4180491c8180491cc180891c0181491c4181fff7a9fffff78afffff724fc01203d49487410bd10b500243a48807c08b9002010bd002035498880002404e0402033490855601cc4b2042cf8db30488088401c2e498880fff7fcfb00bf23f046fe002024f015f840f2a7220021284824f02af8c82024f0e1f8012024f009f840f2a7224ff48031214824f01df80020214988740120cde738b5002400250020009002220021684623f07cff9df80000402809d09df80000ff2805d19df80100ff2801d1002038bd1148818844a0fff7c0f80f488088401e850240f2a72229460b4823f05fff10a204210948fff773f901280cd140f2a5210548fff788f9c4b20348b0f8a502a04277d10120dbe77ae060040010970600103e1b0010070700106c03001028cf0720404040400000000008c007202b430010577269746520466c61736820536f66745761726520416464723d25780d0a0000536f667477617265207061726120536563746f722077726974652066756c6c2e2e2e2e2e2e2e2e2e0d0a0000b2c1b3fd475053cafdbeddc7f82e2e2e2e2e2e2e2e2e0d0a00000000b2c1b3fd414c524d20cafdbeddc7f82e2e2e2e2e2e2e2e2e0d0a0000414c524d204164647220b7b5bbd8c6f0cabcb5d8d6b72e2e2e2e2e2e2e2e2e0d0a00000053797344617461302e4e756d6265723a3d25640d0a000000ffe7fd48c08820f02001fb48c180fb488088401e800200f5803540f2a7222946f64823f0c3feaff2f8020421f348fff7d6f8012813d140f2a521f048fff7ebf8c4b2ee48b0f8a502a04201d101203ee7e948c08820f02001e748c18003e0fff711fbfff7e5fee6a0fef7f7ff00202ee72de9f74390b00446154600260027b046b1464021684625f097fe13222146684625f0f0fd9df844008df813009df845008df81400222229460df1150025f0e2fddf48d0f81600b0f5001f05d3dda0fef7ccff13b0bde8f083d948d0f81600060cd748d0f8160080b2404287b2402f06d11f2e0ada701cc0b223f0d7fe05e0b7f5803f02d1f0b223f0d0fecd48d0f816803f216846fff751fa00f0ff098df83f9040224146684623f0dbfe4146caa0fef79cffc348d0f816004030c149c1f816000120cc494874fff76efdc2e72de9f0419ab004460d4616460027b84623f0d3fc40210aa825f030fec248c07a002857d1b348d0f81200b0f5001f06d3bea0fef774ff00201ab0bde8f081ad48d0f8128023f0b9fc402241460aa823f00ffe3f210aa8fff70afac7b29df86700b8422ed146b9b8a0fef759ff22220df13d01ba4825f060fd22220df13d0101a825f05afdb54913390198d1f81310884201d00020d0e713220aa9204625f04cfd9df83b0028709df83c006870ab48029941609048d0f8120040308e49c1f8120008e08c48d0f8120040308a49c1f812000020b1e70120afe72de9f04104460d46164600279e480560a04637463e0004d03a460168404623f04dfe0120bde8f0812de9f04104460d4616460027b5f5802f02d2b5f5e02f02d20020bde8f08132462946204623f0a4fd07463846f5e710b5002423f042fc00bf04e0204623f00ffe601cc4b2022cf8db062023f008fefff7fbf9fff787fd41f201006649a1f82700411c644827304180491c8180491cc180491c0181491c4181491c8181491cc181491c0182491c4182491c8182491cc182491c0183491c4183c91c818341f20111c183491c0184491c4184491c8184491cc184491c0185491c418541f201218185491cc185491c0186491c418641f201318186491cc186491c018741f201614187491c818741f20171c18741f60101a0f84010491ca0f8421041f60131a0f8441041f60141a0f8461041f60151a0f8481041f60161a0f84a1041f60171a0f84c10491ca0f84e10491ca0f85010491ca0f85210491ca0f85410491ca0f85610491ca0f8581042f20111a0f85a10491ca0f85c1042f20161a0f85e1042f20171a0f8601042f60101a0f8621032202149a1f8250001202c498874fff73cfd01202a494874fff72afc012010bd30b591b004460025402101a825f0f1fc3b22214601a825f04afc1348d0f8b200b0f5102f69d32ca0fef734fe11b030bd63e0750700106004001052656164205379736461746130206661696c2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e3a3d0d0a00002b43001047505320464c415348c7f8b4e6c2fa200d0a0000b5b1c7b0d0b447505320cafdbeddb5c4b5d8d6b725640d0a000000003e1b001047505320464c415348b6c1c8a1b5bdd7eebaf3b5d8d6b70d0a000000cce1c8a1cdb3bcc6b0fccafdbedd0d0a00000000b244001004c00720414c41524d20464c415348c7f8b4e6c2faa3acb7c5c6fab4e6b4a22e2e0d0a00fb48d0f8b200b0f5002f04d2f9a0fef7c4fdfff74efcf648d0f8b20080b2c0f58030b0f5803f02d1082023f0d6fc3f2101a8fff75af8c5b28df84350ec48d0f8b210402201a823f0e3fce948d0f8b210f0a0fef7a2fde648d0f8b2004030e449c1f8b2000120f1494874fff774fb11b030bdf0b591b0044600250026002723f0dafa402101a825f037fcd948d0f8b600b0f5002f0dd3d648d0f8b600d449d1f8b210884205d8d248d0f8b600b0f5102f07d3dfa0fef771fdfff7fbfb002011b0f0bdcb48d0f8b600c949d1f8b210884201d10020f3e7c648d0f8b67023f0abfa4022394601a823f001fc3f2101a8fef7fcffc6b2be48d0f8b610d4a0fef74dfd9df84300b04205d13b2201a9204625f051fb0125b648d0f8b6004030b449c1f8b6000846d0f8b600b0f5002f05d30846d0f8b600b0f5102f06d3caa0fef72dfdfff7b7fb0020bae70120b8494874fff702fb2846b3e77cb5002400250422002101a823f0c3fb04229103684623f0befb23f061faaff20872042101a8fef7cffd01286ad1aff2187204216846fef7c7fd012862d140f2a7220021b94823f0a6fb40f2a521b648fef7d6fd0446b448b0f8a502a04229d140f2a7224ff48031b04823f094fb40f2a521ad48fef7c4fd0546ab48b0f8a502a84203d1aaa0fef7ddfca1e0b248fef7d9fc40f2a7220021a44823f07cfb012023f0f0fb40f2a7224ff480319f4823f004fc8de040f2a7224ff480319b4823f06afb40f2a5219848fef79afd05469648b0f8a502a8420dd1002023f0d3fb40f2a7220021914823f0e8fb9a484830fef7a9fc6de099a0fef7a5fcfff763f9fff78cfb65e0aff2ec72042101a8fef75cfd012827d140f2a7220021834823f03bfb40f2a5218148fef76bfd04467f48b0f8a502a0420ed187489030fef783fc012023f0a0fb40f2a7224ff48031774823f0b4fb3de081a0fef775fcfff733f9fff75cfb35e0aff64c0204216846fef72cfd012826d140f2a72201046b4823f00bfb40f2a5216948fef73bfd04466748b0f8a502a8420dd16f48d830fef753fc002023f070fb40f2a72200215f4823f085fb0ee069a0fef746fcfff704f9fff72dfb06e072a0fef73efcfef751fffff725fb7cbd2de9f04704460d4617464ff000090026a04605f500393e4623f079f926b132464946404623f060fb0120bde8f0872de9f04704460d4616464ff00008c146274605f50038b14623f062f94a464146384623f0b8fa0120bde8f08738b5002400254ff4c0206149086004220146684623f0a9faaff62c1204216846fef7bcfc012820d100bfbce04ff4c02202eb44210422684623f097faaff6501204216846fef7aafc01280fd0214651a0fef7e2fb601e4ff4c02101eb40204c490860601e02498880a0e01ce199e02b430010414c41524d20464c415348d0b4b5d8d6b7d0a1d3dac6f0cabcb5d8d6b70d0a00d0b4414c41524d2020cafdbeddb5d8d6b725780d0a0000003e1b001052656164416c61726d446174614164647220d2ecb3a30d0a00000000b6c1416c61726d20cafdbeddb5d8d6b725783a0d0a000000414c41524d20464c415348b6c1c8a1b5bdd7eebaf3b5d8d6b70d0a006004001053797374656d50617461302d66696c65732061726520616c6c2072696768742e2e2e0d0a000000008c52030053797374656d2d66696c6573206973206572726f7221212153797374656d5061726130496e697469616c2e2e2e320d0a0000000053797374656d2d66696c6573206973204e554c4c21212153797374656d5061726130496e697469616c2e2e2e0d0a000000c00720536f667430693d25640d0a00601c84b2802cfff640af00bf802c09db601e4ff4c02101eb40204e490860601e4d4988804b4801684ca0fef724fb4ff4ba7248480168484823f0c6f946488b30d0f821104da0fef716fb4348b0f8721156a0fef710fb4ff4b9713f48fef7e9fb0546294651a0fef706fb3b48b0f87201a84211d13848818850a0fef7fcfa3648b0f82500332800db31e033488b30808ab0f5fa7f40db2ae02e480068a0f50070b0f5c02f1dd32b480068a0f5007029490860084601684ca0fef7ddfa4ff4ba7224480168244823f07ff92248006800f50070204908604ff4b9711f48fef7a9fb05461d48b0f87201a84215d000bf46a0fef7c1fafff762f9062023f0dcf9fff798f809e04ca0fef7b6fa062023f0d3f9fff754f9fff78df80f48d0f80610534801610d48d0f80a10504881600a48d0f80e004e495439486513220749be314b484e3825f0a5f813220349be31484825f09ff838bd00c007202b4300105265616420736f66747761726520706172612061646472312e2e2e25780d0a00536f6674576172655f506172612e75705f646174615f737066742e536f6674446174614c656e2e2e2e25640d0a000000436865636b4352432e2e2e25780d0a00536f6674506174612d66696c65732069732072696768742e2e2e536f6674506172614e756d3d25640d0a0000b5b1c7b0b5c4b4edcef3a3acbbd6b8b4c9cfd2bbb4ceb5c4d6b53d25640d0a00536f6674506172612d66696c6573206973206572726f72212121536f667450617261496e697469616c2e2e2e310d0a00536f6674506172612d66696c6573206973204e554c4c212121536f667450617261496e697469616c2e2e2e2e2e2e2e0d0a0000005b0700105ec30720ee48007800283ad0ed48007810b1012833d119e0eb48c06b40f48010e949c863e748001f0078401cc0b2e549091f0870e5490978884206db0020e149091f08700120091d087019e0de48806b40f48010dc498863da48001f0078401cc0b2d849091f0870d9490978884205db0020d449091f0870091d087000e000bf00bfd448007800283bd0ce48401e007810b1012833d119e0cb48c06b40f40020c949c863c748c01e0078401cc0b2c549c91e0870c8490978884206db0020c149c91e08700120891c087019e0be48806b40f40020bc498863ba48c01e0078401cc0b2b849c91e0870bc490978884205db0020b449c91e0870891c087000e000bf00bfb748007800283bd0ae48401c007810b1012833d119e0ab48c06b40f40010a949c863a748801e0078401cc0b2a549891e0870ab490978884206db0020a149891e08700120c91c087019e09e48806b40f400109c4988639a48801e0078401cc0b29849891e08709f490978884205db00209449891e0870c91c087000e000bf00bf704710b522f07cfe10bd10b522f06efe10bd10b595480078002854d08948c01c007810b1012844d11fe08548001d007828b901208349091d0870fff7e6ff8048801c0078401cc0b27e49891c087087490978884208db00207a49091d0870891e08700120491c08702ce07548001d007828b901207349091d0870fff7c2ff7048801c0078401cc0b26e49891c08707849097888420cdb74480078401e7349087000206749091d0870891e0870491c087008e000206349091d0870891e0870491c087000bf00bf10bd30b508b1042834d1694c24780cb118b930bd042800d1fbe7302904d0312910d0322924d11be00024534d2c70554d2c70554d2c70524ca46b44f48014504dac6316e000244c4d2c704e4d2c704e4d2c704b4ce46b44f48014494dec6308e0484c2270484c23700124434d2c7000e000bf39e0012801d0032835d14c4c247814b1012803d1c4e7032800d1c1e7302904d0312910d0322924d11be000243c4d2c703c4d2c70394d2c70354ca46b44f40024334dac6316e00024354d2c70354d2c70324d2c702e4ce46b44f400242c4dec6308e02e4c22702e4c237001242b4d2c7000e000bf00bf02282cd1302904d0312910d0322924d11be00024274d2c70274d2c70244d2c701d4ca46b44f400141b4dac6316e00024204d2c70204d2c701d4d2c70164ce46b44f40014144dec6308e01a4c22701a4c23700124164d2c7000e000bf00bf64e710b522f055fd10bd38b93222144b1a70144b1a70114a117009e0012807d10f220f4b1a7032220f4b1a700c4a11707047010000101100001000c0092002000010030000100400001005000010060000100700001008000010090000100a0000100b0000100c00001066c107204944445f323133474430315f532056312e302e385f6e6f647463000010b50ff0e2ff02f0b3fb10bd70477047704770474944445f323133474430315f482056312e302e385f6e6f647463000070b504460d46294620462ff0a5fa70bd2de9f04704460d4628462ff00cfc804628462ff008fc814628462ff004fc824621462ff035fc074649462ff031fc064641462ff02dfcbde8f0872de9f04104460d4628462ff0effb074628462ff0ebfb804621462ff01cfc064639462ff018fcbde8f0812de9f04f9db004460d4605fb05f704fb04f838462ef038fdcde91a01ffa00cc81a982ef06ffdcde9180128462ef02cfdcde91601fba00cc816982ef063fdcde914012146f948fff7a9ff83462ff096f9cde9120140462ef017fdcde91001f4a00cc810982ef04efdcde90e0120462ef00bfdcde90c01f0a00cc80c982ef042fdcde90a01eea00cc80a982ef0d3f8cde90801dde90e232ef0d7ffcde90601dde912232ef0c7f8cde90401dde914232ef0c1f8cde90201dde918232ef0c5ffcde900012ef06df8824628462ef0ddfccde91a01dda00cc81a982ef014fdcde918012146db48fff75aff83462ff047f9cde9160140462ef0c8fccde91401d5a00cc814982ef0fffccde9120120462ef0bcfccde91001d1a00cc810982ef0f3fccde90e01d0a00cc80e982ef084f8cde90c01dde912232ef088ffcde90a01dde916232ef078f8cde90801dde918232ef072f8cde906012ef024f8814649465046fff715ff064630461db0bde8f08ff0b591b0044604fb04f02ff00bfb06462146bb48fff70cff07462ff0f9f8cde90e0130462ff0f4f8cde90c01b5a00cc80c982ef0b1fccde90a0120462ef06efccde90801b1a00cc808982ef0a5fccde90601b0a00cc806982ef092fecde90401dde90a232ef030f8cde90201dde90e232ef034ffcde900012df0dcff0546284611b0f0bd7fb50446a448807a03280cd1a3480169808a0144a14801610020a1490860091f086004b070bd22f0b2fb05469c48001f006850b94ff47a7099490860081f0560083000681031086005e09548001f0068281a9349086092480068b0f57a7f48d38f48001f0560001d00682ef015fccde902018ca00cc802982ef038f9cde900012df096ff86490831086084485438b0f8030164280cdd0846016820462ff0cefa064682492ff026f97d490c31086003e000207b490c3108607d4979480c3000682ff0bcfa06467648001d016830462ff085f87349091d086001f10c0001680c3800682ff0edfb06462ff02efa05466b488582002095e72de9f04f9fb00446002500260027a84600201e901d901c901b901a9019901890a0782ff05bfa1c90e0782ff057fa1b9020792ff053fa1a9060792ff04ffa1990a0792ff04bfa18904ff087401c992ff07afa804640461b992ff045f88046574940462ff0ccf81d90564800682ff013f8cde9160154a00cc816982ef0bcf8cde9140152a00cc814982ef0b5f8cde912012df013ff1e90dde91d102ff054fa07464d480078102806d0302805d040283ed050283dd190e100bf6078012802d0032837d114e040462ef0e6ffcde9160142a00cc816982ef0a3fbcde914012df0edfe05463f4928462ff08af80546b6e03d491a982ff084f805463c493846fff7d3fd814629462ff01ffa0546384919982ff0eaf90fd218982ff09cf900f0ff0a19982ff097f900f0ff0b5146584661e097e02ae292e074e0b2ac806e2b1ef73eb94c2f2cdd2f873f9175dd3693dd5021ec4e563f483657cd7344ae3f05172b6a302d634078ec67b114c9773fd6eb7533981f2f188cbfe93e2207b452d2cc793f000000000000f03f61c40435e93d976608f9383f55d33fd2f162c23f315f5e807df644403e1b00105b07001024c007200000000000408f40000061450000c84200ff7f470cc007200000000000002440000000000000084010c007209a99999999f9584000d0e84600007f43333353400000d242fff792fd814629462ff0a4f9054628462ef048ffcde91601ffa00cc816982ef005fbcde914012df04ffe05461be018982ff016f900f0ff0a5046fff72bfe81462ef030ffcde91601f5a00cc816982ef0edfacde914012df037fe0646314628462ff078f9054600e000bf00bf93e16078072802d008287ed131e0eb491c982ff013fa01d2e8481c904ff087411c982ff061f981461b992ef02dff8046e34940462ff058f982464ff081412ff053f981462ef0f8fecde91601dda00cc816982df0a1ffcde91401dca00cc814982df09affcde912012df0f8fd07463d4681e04ff087411c982ff036f982461b992ef002ff81464ff081412ef089ff8046d0491a982ff0d2f901d2ce481a9019982ef0cafecde91601cba00cc816982df01ffecde914011a982ef0befecde91201c74800682ef0b8fecde9100140462ef0b3fecde90e01c3a00cc80e982ef070facde90c01dde910232ef06afacde90a01dde912232ef064facde90801dde914232df04affcde9060100e033e0b7a00cc806982ef055facde90401b6a00cc804982df03affcde902012df098fd0746b34938462ff0d9f881462ef07efecde91601afa00cc816982ef03bfacde914019da00cc814982df020ffcde91201aaa00cc812982df019ffcde910012df077fd07463d4600e000bf00bfd7e06078072802d008287ed145e08d491c982ff057f901d28a481c904ff087411c982ff0a5f881461b992ef071fe80468d48016840462ff09bf8834682492ff097f882464ff081412ff092f881462ef037fecde916017da00cc816982df0e0fecde914017ba00cc814982df0d9fecde9120182a00cc812982df0d2fecde91001aff2dc200cc810982ef0def9cde90e012df028fd07463d4689e04ff087411c982ff066f882461b992ef032fe81464ff081412ef0b9fe804668491a982ff002f901d266481a9019982ef0fafdcde9160163a00cc816982df04ffdcde914011a982ef0eefdcde912015f4800682ef0e8fdcde9100140462ef0e3fdcde90e015ba00cc80e982ef0a0f900e04fe0cde90c0161a00cc80c982ef097f9cde90a01dde910232ef091f9cde90801dde912232ef08bf9cde90601dde914232df071fecde904014ca00cc804982ef07ef9cde90201aff27c300cc802982df062fecde900012df0c0fc0746474938462ff001f881462ef0a6fdcde9160143a00cc816982ef063f9cde9140131a00cc814982df048fecde912013ea00cc812982df041fecde910012df09ffc07463d4600e000bf00bf00bf00bf28462ef083fdcde9160138a00cc816982ef0f0fa0ed228462ef078fdcde9160132a00cc816982ef0d7fbcde914012df07ffc05462846fff7a3fc28461fb0bde8f08f2de9fc41044620462ef03dff064630462ef07eff074621462ff08ff805462ef054fdcde9000122a00cc800982ef0c1fa43d820462ef027ff401cbde8fc818638d6c56de429409a99999999993d400000c242000010429a99999999992c40000000000068c04000007a43cdcccccccc246d400cc007201904560e2db29d3f000000000000224000000000000059400000b44300000000000012400000000000408a4000000000000000400000000000706b40000000000000e03f20462ef0e3febde8fc8130b593b0e148807a032810d1e0488168c0680144de4881600020dd490860091d0860091d0870db49087013b030bd21f0ebff0446d748001d006868b94ff47a70d4490860081d0460d44800682ef0b9fed049091f086005e0ce48001d0068201acc490860cb480068b0f57a7f69d3c848001d0460c8480078022800dad5e7c5490978c34a08321278881800ebd071c1f34701bf4a08321170104600782ef0d6fe05462ef0b0fccde91001aff290500cc810982ef06cf8cde90e01b8a00cc80e982df051fdcde90c01b24800682ef0befe05462ef098fccde90a01aff2c0500cc80a982df040fdcde90801dde90c232ef04ef8cde90601aba00cc806982ef047f8cde90401a54800682ef07dfccde90201dde904232df0d3fbcde900012df085fb9e490860084600682ef04bfe9949091f0968441a984800789649083108709348c46072e710b521f05fff64389849086010bd70b592b004466cb100208d4910310860924908608a49143108708749c1f8190012b070bd21f047ff05468c48006870b94ff47a7082491031086088480560884800682ef015fe7e490c31086005e083480068281a7a4910310860794810300068b0f57a7f6ad37d4805607e480078022800dad5e77b490978714a14321278881800ebd071c1f347016d4a14321170104600782ef032fe06462ef00cfccde91001aff2d8600cc810982df0c8ffcde90e0166a00cc80e982df0adfccde90c016048103000682ef019fe06462ef0f3fbcde90a01aff208700cc80a982df09bfccde90801dde90c232df0a9ffcde906015ea00cc806982df0a2ffcde90401584800682ef0d8fbcde90201dde904232df02efbcde900012df0e0fa51490860084600682ef0a6fd46490c310968451a4d4800784349143108704048c0f8195070e71cb54b480078402803d049480078502822d148480078072802d008281ad108e0464880788df802004448c0788df8030011e0414800798df802003f4840798df803003d48c0798df804003b4880798df8050000bf00bf01e000201cbd364800788df801006846fff73bfb04462046f4e71cb52f480078102806d0302805d0402833d0502835d131e000bf2c48007870b101208df80100284880788df802002648c0788df803006846fff71afb1ce024480078c8b103208df801001f4800798df802001d4840798df803001b48c0798df80400194840788df80500174880798df806006846fff7fcfa04e000bffff788ff00e000bf00bf1cbd00003e1b00105b0700103cc00720a801001014c00720000000000020ac40cdccccccccccf03f18c0072038000010360000105c8fc2f5285cef3f10c0072011c007208cc207201dc007201cc0072030b50246087808b9104630bd0fe01446002307e0c818407808b91046f5e7641c581cc3b22078cd5ca842f3d0521cff489042ecd10020e8e70020fd490870fd490870fd490870fd490870fd490870fd490870fd490871704710b50020fb490870fb490870f849ab31a1f89407a1f8920740f2dc51f74823f01dff10bd10b5f6480078012843d1f548806d40f08000f34988650846806b40f480408863052022f0a3f8ee48806b40f48060ec4988630846806b40f480608863012022f095f8e748806b40f48060e54988630846806b40f480608863642022f087f8fff7bdff142022f082f8dd48c06b40f48060db49c863012022f079f8d948c06b40f48060d749c863642022f070f80020d54908700020d4490870fff7a0ff0120fff72ffe0020cd490870c848407818b9cf4890f8370410b10120cd49087003f04ff9022807dd03f04bf90a2803da0120c849087002e00020c6490870bc487130fcf7fdffba489930d0f80e00c24908600420b5494d310870c0a0fdf743f800231a4631210320fef75fff10bd10b5b448806d40f08000b24988650846806b40f4804088630020ad490870052022f01ff8ac48806b40f48060aa4988630846806b40f480608863012022f011f8a548806b40f48060a34988630846806b40f48060886300209a49ab31a1f89407a1f11200d0f80e00a0490860642021f0f8ffa248007818b14ff4165020f079ff4ff4165020f075ff9348c06b40f480609149c863012021f0e4ff8e48c06b40f480608c49c86310bd10b5954890f87800c0f3401000b110bd8748806d40f08000854988650846806b40f480408863052021f0c7ff8048806b40f480607e4988630846806b40f480608863012021f0b9ff7948806b40f48060774988630846806b40f48060886300206e49ab31a1f89407642021f0a6fffff7dcfe4ff4165020f028ff6c48c06b40f480606a49c863012021f097ff6848c06b40f480606649c863fff7c7fe0020624908700120664908700020614908705a487130fcf73aff58489930d0f80e0060490860042054494d31087000231a4631210320fef79ffe9ae710b500215f48007e61f383005d49087600215b48007e61f307105949087600215848801d01824648407980b113225449891d544823f06dfd13225149891d524823f067fd01203e494874fdf733fd00203c4948713a49087010bd10b53e48806b40f480603c4988630846806b40f480608863012021f035ff3748806b40f48060354988630846806b40f4806088634ff4967021f026ff2f48c06b40f480602d49c863012021f01dff2b48c06b40f480602949c8634ff4967021f013ff10bd10b533a0fcf719fffff79bff00201d494871fff73ffefff72dfe1e48007808b9fff7bcff1c48c06d40f080001a49c8650846c06b40f48040c8630120154908700121204890f8780061f304101d4981f8780000231a4630210320fef710fe042007494d31087010bd5a29001019000010500000101f0000101e0000101d0000101c0000103e1b001021000010200000107f2300102200001000c009203c0000107fc107200717001023000010400000100d0a4f50454e204750530d0a0000000066c1072007070010e94300105ec30720436c6f7365204750530d0a0070b504460d46ff48806b40f48060fd498863142021f098fe06e02178002020f0dffe641c681e85b2002df6d1c82021f08bfef448c06b40f48060f249c86370bd07e003788b4201d1531edab2401c02b903e000f1be039842f3d100bf70472de9ff4fa1b0e84ee94f314621982ef0e8fa8146314623982ef0e3fa2090484620992ef020fc8046314624982ef0d9fa8246314622982ef0d4fa834651462ef012fc0446b10720462ef027f982462ef070f8cde91e012cf08efecde91c01d4a00cc81c982cf0e9f9cde91a0120982ef060f8cde918012bf006ffcde9160148462ef057f8cde914012bf0fdfecde91201dde916232df011fccde91001dde91a232df00bfccde90e01b10740462ef0f5f882462ef03ef8cde90c012cf05cfecde90a01bba00cc80a982cf0b7f9cde90801dde90e232cf089ffcde906012cf09bfecde904012bf0bbfbcde90201b1a00cc802982df0e2fbcde900012cf02cff0546394628462ef06dfa8246ab492ef069fa0546284625b0bde8f08f2de9f04fcdb0012600201290119010900f900e9000270d90c8211aa823f092fc1c2113a823f08efc9ea1a048fff7dcfc044624b99ea19d48fff7d6fc04469ea12046fff7d1fc0546281bc0b211901198c82801dbc820119021461aa8119a23f0cffb14b11198c82800db2ce307e01aa8805d4740701cc6b2c82e00dd03e01aa8805d2a28f3d100bf701cc6b21aa988190da9fcf794ff9df83400b84203d087a0fcf7a1fde1e700201290002609e01aa8805d2c2803d11298401cc0b21290701cc6b211988642f2db12980b2803d012980c2800d0c9e702222c211aa8fff7f4fe0446207841287ed17848007800287ad007222c211aa8fff7e7fe044620462bf08ffccde9060172a00cc806982df048fbcde904012cf092fe10906f4910982ef07dfa00d8a1e701222c211aa8fff7ccfe0446607830382178303901eb810100eb4100c0b28df823009df82300172800dd8be7a41c607830382178303901eb810100eb4100c1b208a8017100793b2800dd7be7a41c607830382178303901eb810100eb4100c1b208a8417140793b2800dd6be709222c211aa8fff796fe0446607830382178303901eb810100eb4100c0b28df820009df820001f2802dc9df8200000b952e7a41c607830382178303901eb810100eb4100c0b28df821009df821000c2804dc00e0fae39df8210000b93ce7a41c607830382178303901eb810100eb4100c0b28df822009df82200632803dc9df822000c2800dc27e703222c211aa8fff752fe04461a22214613a823f0e8fa607830382178303901eb810100eb410026494843cdf82600a41c607830382178303901eb810100eb410040f253714843ddf8261001eb4010cdf82600e41c607830382178303901eb810100eb4100c0eb001000eb8000ddf8261029e000c0092035fa8e3c1951c745000000000000004000007a44244750524d4300007f23001024474e524d4300000d0a0000524d4320d0a3d1e9b4edcef30d0a00001c0000109a9999999999fd3f0000964380ee360001ebc000cdf82600a41c607830382178303901eb810100eb410000eb4000ddf8261001eb4000cdf8260005222c211aa8fff7dafd044620783038642148436178303901eb810100eb4100a17830390844ff494843cdf82a00e41c607830382178303901eb810100eb410140f253704143ddf82a0000eb4111cdf82a10e41c607830382178303901eb810100eb4100c0eb001000eb8001ddf82a0000ebc101cdf82a10a41c607830382178303901eb810100eb410000eb4001ddf82a0000eb4101cdf82a1004222c211aa8fff78dfd044620784e2807d101219df8320061f341008df8320006e000209df8321060f341018df8321006222c211aa8fff775fd04462078452807d101219df8320061f300008df8320006e000219df8320061f300008df8320007222c211aa8fff75dfd044620462bf005fbcde90601c6a00cc806982df0bef9cde90401c4a00cc804982df0b7f9cde90201c3a00cc802982cf09cfecde900012cf0fafc109010982df08aff80b2adf82e0008222c211aa8fff734fd044620462bf0dcfacde90601b7a00cc806982df095f9cde904012cf0dffc109010982df06fff80b2adf83000b149d1f806002df0e4ff80462df0befdcde90601ada00cc806982cf067fecde904012cf0c5fcab490860a748801f007ec0f3400030b90846016800202ef040f9a4490860a049891d48682df0c2ff80462df09cfdcde906019ca00cc806982cf045fecde904012cf0a3fc9a4948609648801f007ec0f3000030b90846416800202ef01ef993494860ddf826002df0a1ff80462df07bfdcde906018ca00cc806982cf024fecde904012cf082fc8949083108609df83200c0f3400038b90846016800202ef0fdf8834908310860ddf82a002df07fff80462df059fdcde906017ba00cc806982cf002fecde904012cf060fc7849083148609df83200c0f3000038b90846416800202ef0dbf8724908314860704808300cc8103803c8fff79efc81462df012ff80462df054ff0e906a490e982ef02ef873d20e982df028fd0b460246cde9060165a0fcf708fb4ff00008c146c246c3466848c0891430642190fbf1f01ffa80f8bdf82e00143090fbf1f01ffa80f94a46414661a0fcf7effab8f10c0f04dd4fea48404fea104814e0b8f1020f04dd4fea88404fea10480ce0b8f1020f04d108eb88001ffa80f804e008eb880040044fea1048b9f1020f04dda9f102001ffa80f901e04ff000094a4641464ba0fcf7c3fa4d49d1f80e104d4a1268881a01d4014600e041428a4608eb090000ebd071491001fb0afb5a46514645a0fcf7acfa58462df0e5fe07900e992df0c0ff06d246490e982df0e1fe01d20fe00de02c48c01f0078401cc0b22a49c91f0870082802dd0020087001e023e300bf00202449c91f08703148d0f80e00304908601d48801f007ec0f381019df8320061f383008df832001848801f007e00099df8321060f307118df83210132208a9124823f078f81048801f007ec0f3810010b900210d48018201201c49993948712448007847e080ee36000000000000f09c400000000000005940000000000020ac4000000000000024400d0700100000000040774b41580000100000164364697374616e63656d696c653d252e32662c3e3135306d0d0a0000005ec3072056303d25642c56313d25640d0a000000d71b00104000001054696d653d25642c44697374616e63653d25640d0a0000000080bb4563c10720012803d0ff480078002860d0fe48807a082831d1fd49c8892df041fe8346fc492df0cefc8246fb492df0cafc8146fa492df06afe80462df0edfdc1b2f348801f80f80211811dc8892df029fe8346f0492df0b6fc8246ef492df0b2fc8146ee492df052fe80462df0d5fdec490870ec48007808b9fef7edfeea48007838b3e248807a022823d1e149c8892df008fe8346df492df095fc8246de492df091fc8146dd492df031fe80462df0b4fdc0b20f900f98052803dbd9490f98087002e00020d6490870d648007808b9fef7c2fe1321d54823f003f81322cc49d34822f0b0ff0120d2490870c949c8892df0d8fd8346c7492df065fc8246c6492df061fc8146c5492df001fe80462df084fdc84908700020fef747ffc248007800287ed04ff000080020be490870b849d1f806002df0b6fd81462df090fbcde90601aff2a8100cc806982cf038fccde904012cf096fab8490860ad48801f007ec0f3400030b90846016800202df011ffb2490860a749891d48682df093fd81462df06dfbcde90601aff2ec100cc806982cf015fccde904012cf073faa74948609c48801f007ec0f3000030b90846416800202df0eefea04948609f480cc8103803c8fff7b3fa81462df027fd804647f23050804579d88d48807a082806d09248007828b38a48807a022821d1b8f5967f1ed340f6b83190480c38fcf7b8f8b8b1fef7ccfe40462df04dfd81468c48016848462df04cfb894908607d484e30c06801e056e096e100eb080179484e30c16040462df037fd81468248016848462df036fb7f4908607148d0f8190040446f49c1f819007ca0fcf7e9f8774908682df0fefacde906017449083908682df0f7facde90401dde90601cde9000179a0dde90423fcf7d3f86c4948682df0e8facde906016949083948682df0e1facde90401dde90601cde9000177a0dde90423fcf7bdf841467da0fcf7b9f85448d0f8191080a0fcf7b3f803e0ffe785a0fcf7aef800bf13224f49884822f0b5fe00218648be3890f8d00061f383008349be3981f8d00000218148be3890f8d00061f307107e49be3981f8d00000217b4801827b480078002874d0884600207849087078480078002856d0002076490870764908703848801f007a00f5fa603449993188803448801fc07948803248801f817900f58050a0f8d014a0f58050407a2c49993108812b48801f807a48812948801fc07a8881084620f06efa804624489930d0f80e0061490968401aa8eb00002149891f48650846406d1d493031c1f851001c48801f90f87800c0f3401038b16931d1f80e00414609f07bf85549086014489930c0f80e800146d1f80e0020f012fb50a0fcf72cf80f48008a0d49a1f81d000c48008aa1f81f00002081f8250001f12100fbf7c9ff012005494874fcf7f5fd00bf0448008a02498ee0000079c107203e1b00100d07001000007a440000c84200006145a80100103500001078c107205ec307203c000010360000106000001014c0072038000010475053b6a8cebb2cbcc6cbe3c1bdb5e3bce4b5c4bee0c0eb0d0a00006f6c645f6c6174203d20252e3866202c206e65775f6c6174203d20252e38660d0a0000006f6c645f6c6e67203d20252e3866202c206e65775f6c6174203d20252e38660d0a000000bcc6cbe3bee0c0eb6d696c65616765203d2025640d0a0000b5b1c7b0c0dbbcc6c0efb3cc3d202564206d28b5a5cebb6d2f73290d0a000000bee0c0ebb3acb3f6b7b6cea7a3acb2bbc0dbbcd300000000e94300101800001061c1072061c0072058c10720a4c00720475053d0a3d7bc52544320cab1d6d30d0a000000a1f81f004ff47a71f748fbf71dff38b1f548fbf725ff0120f349213981f8250064231a4632210320fdf78ffe96e000bfee480078012808d1eb482138807a082803d10021ea4880f80211fef7e0ffe948007800287cd10120e6490870e648fbf7fffee348801dd0f80600002870d0e0480c30406800286bd0dd49891dd1f806002df07dfb80462df057f9cde90601aff218600cc806982cf0fff9cde904012cf05df8d549091d0860d148007ec0f3400038b90846016800202df0d8fcce49091d0860cb490c3148682df059fb80462df033f9cde90601aff260600cc806982cf0dbf9cde904012cf039f8c349091d4860bf48007ec0f3000038b90846416800202df0b4fcbc49091d4860bca0fbf7fbfeb949091d08682df00ff90b460246cde90601bea0fbf7effeb349091d48682df003f90b460246cde90601bda0fbf7e3fea9482138d0f81910aff2b010fbf7dbfe00231a4631210320fdf7f7fd0420a649153108704db0bde8f08f2de9f84f00260127b046b146b24600200090c821b14822f01cfdb0a1b248fef7befd0446a2a12046fef7b9fd0546281bc6b2c82e00dbc82632462146a74822f0bafc0cb1c82e00db04e108e0a348c05d80ea0a0a781cc7b2c82f00dd03e09e48c05d2a28f2d100bf781cc7b29b49c8196946fcf77ff89df80000504503d09aa0fbf78cfee6e04ff00008002709e09248c05d2c2803d108f1010000f0ff08781cc7b2b742f3dbb8f1110f00d0d2e002222c218948fef7e3ff04462078332861db012072493839087003216e48007e61f383006c49087600220846017e62f307110176a41c4ff00c08914622e020782c2806d14ff00009a8f1010000f0ff0815e0b9f1000f10d15f48007e0009401c5d49097e60f3071108465b4908760846007e00090c2800db09e04ff00109641c701ec6b2b8f1000f01d0002ed7d100bf5148007e00090c2805dd0c224e48017e62f3071101764c48007e0009012800dc79e047482138407900280bd100224648017e62f3830101760021007e61f307104149087676e02078322863d1012040493839087001213c48007e61f383003a49087600213848007e61f3071036490876a41c4ff00c084ff0000922e020782c2806d14ff00009a8f1010000f0ff0815e0b9f1000f10d12c48007e0009401c2a49097e60f307110846274908760846007e00090c2800db09e04ff00109641c701ec6b2b8f1000f01d0002ed7d100bf1e48007e00090c2806dd0c211b48007e61f30710194908761848007e0009012800dc11e0134821384079e0b900221248017e62f3830101760021007e61f307100e4908760fe000bf0b480078012808d108482138807a082803d10021074880f80211fef719fe0420064915310870bde8f88f5f1b001063c10720070700103500001054000010475053c3bbd3d0b6a8cebb2cbcc7c2bcd7eebaf3b5e3b5c4cebbd6c30d0a00006f6c642e6c6174203d20252e38660d0a000000006f6c642e6c6e67203d20252e38660d0a0000000096c2072024475047534100007f23001047534120d0a3d1e9b4edcef30d0a000010b542f6e061a748fbf7ecfc002837d0a448fbf7f3fca4480078012803d10021a24880f80211fef759fc01229f4890f8781062f3041180f8781090f87800c0f3401078b9fef7defd9948c06d40f080009749c8650846c06b40f48040c86395a0fbf71ffdfef7a1fdfef748fc01209b49087000231a4630210320fdf734fc16e04ff47a718748fbf7adfc80b1874890f87800c0f3401050b98548806d40f08000834988650846806b40f4804088638c480078002852d18ba18c48fef7f3fbd0b100227a4890f8781062f3041180f878107448fbf793fc83a18448fef7e3fb8449086001206f49713908718249087005208149087010bd81a17c48fef7d3fbd8b100216a4890f8780061f30410674981f878006448fbf772fc78a17448fef7c2fb7349086001205f497139087171490870052071490870dde772a16c48fef7b2fb60b170a16948fef7adfb7049086001206f490870052068490870cbe7cae710b56c480078002846d162480078012814d1aff2c0115e480068fef794fb40b3fef79dfe00205b49087001206349087007215748006822f0e0fa1ae05d480078012816d1aff2f01159480068fef77bfb78b15a48007860b9fff7a6fd00205449087001205649087007215048006822f0c4fa5248007801280ad14f480078012806d1fef78cfb00204d4908704b49087004204149087002e004203f4908704ff4fa712848fbf7effb60b12648fbf7f7fbfef775fb0020414908703f49087004203649087010bd10b51f487138007a18b93c480078012804d101202f49491e087010bd2d48401e0078b8b12b48401e017835a0fbf727fcfef752fbfef740fb002011497139c8730f48fbf7c9fb00202249491e08700420491c087020f061f91e480078042802d0052806d102e0fff7b9fe06e0fff765ff03e004201749087000bf00bfcde70000af1b001063c107200707001000c009205475726e206f66662047505320506f77657220696e20477073436865636b48616e640d0a000000002300001050000010244750524d432c007f23001028000010210000106900001024474e524d432c002447504753412c0024000010200000101f0000101d0000101e000010220000104770735f656e747279203d2025642e2e2e2e2e2e2e2e0d0a0000000038b5002000900024ff481ff082fafe480078d1283fd10120fc498871382000900122694640201ff059fa052000900122694641201ff052fa282000900122694642201ff04bfa012000900122694643201ff044fa11200090012269467e201ff03dfa00bf01e0601c84b241f288308442f9db15200090012269467e201ff02efa002401e0601c84b244f620608442f9dbdfa0fbf766fb38bd10b586b0002004900024da488079012854d100200490012204a950201ff012fa04200490012204a955201ff00bfa08200490012204a954201ff004fa0b200490012204a953201ff0fdf980200490012204a959201ff0f6f9cc48c08f03281adbca49c98f01eb810148002cf0b7f8cde90201c7a00cc802982bf0f0fdcde900012cf072f884b2ff2c02dae0b2049004e0ff20049001e015200490012204a960201ff0d0f907200490012204a950201ff0c9f9b648c18fb8a0fbf707fb06b010bd08b500200090ab488079012809d1002000900122694650201ff0b4f9b7a0fbf7f4fa08bd1cb504460123009301ab22460121302020f078fc9df804001cbd38b5044602230093b44b22460121302020f06bfcb1480078b049497800eb012005b215f4004f05d0e843401c05b2ad116d4200e0ad11284638bd13b582b004460123009303ab22460121302020f0effb1fbd10b500240020fff7c5ff0446002085498871f92c1ad105210f20fff7e5ff0b211020fff7e1ff00211120fff7ddff00211320fff7d9ff00213e20fff7d5ff93a0fbf79bfa01207749887102e000207549887110bd2de9f05f04460d46164600274ff48278c8f10009ba46012707e034f8170004eb470121f8020c781cc7b20a2ff5db65824ff0000a002712e0208850440ffa80fab4f90000484501ddb4f90090b4f90000404501dab4f90080a41c781cc7b20a2feadbaaeb08000ffa80faaaeb09000ffa80fa50462cf07afe83464ff082412cf01ffd30606b48801c00780a2809da6948801c0078401c6749891c08700020bde8f09f0120fbe72de9f04f95b004460d4616460027b846002011901090634890f80701c0f3000078b1614890f87800c0f3401048b95e48b0f87a0041f64c51884202dd5c48007858b10021594890f8770061f38610564981f8770015b0bde8f08f201e01db014600e0414289b21391281e01db014600e041421ffa81f9301e01db014600e0414289b2129112982bf09affcde90e0148a00cc80e982af091fdcde90c0148462bf08effcde90a0142a00cc80a982af085fdcde9080113982bf082ffcde906013ca00cc806982af079fdcde90401dde908232bf04bfbcde90201dde90c232bf045fbcde900012bf01bff119031a00cc832a003c82af062fdcde90e012bf010ff1090dde91010884270d9dde91021881a2bf056ffcde90e012bf03efacde90c0128a00cc80c9851e0480200103e1b0010496e697420626d69313630204f4b0d0a000000006505001048e17a14ae470f40626d693136305f616e796d5f494e54315f656e61626c652c2074683d25640d0a00000000626d693136305f616e796d5f494e54315f64697361626c650d0a00007c000010626d613235302074657374206f6b210d0a0000006004001007070010500000100000000000000040000000000040604000000000000010402bf022fccde90a01ffa00cc80a982cf0d1f9cde908012bf09dfe87b201e0ffe70027fb49088d2bf0e5fecde90e01f9a00cc80e982bf008fccde90c012bf08afe80b2b84268daf5480078002864d1f448d0f84500f149091f08603946f1a0fbf72af938462bf0c6fecde90e01f1a00cc80e982bf0fdfecde90c01f0a00cc80c982bf0f6fecde90a012bf040fa8246fdf7c6fd1ffa80f80a2098fbf0f100fb118a98fbf0fb52465946e648fbf704f90120da4908700121e44890f8770061f38610e14981f87700d248038d00220121112012f05ffa0021dc4890f8770061f38610d94981f87700d94890f8300218b141460f2011f081fbd54890f80b01c0f3000018b105210120fdf79ff8d1a0fbf7d3f8c7a0fbf7d0f813e0c049097801290fd1bf49d1f84510bd4a121f1268881a01d4014600e04142642902db0020b7490870a3e62de9ff5f04460d4616460027b846b9461ff0d3fab148401c0078012802d002281fd13ee3ad4a403221b2b648b830fff70efea94a443229b2b348cc30fff707fea64a483231b2af48e030fff700fea248801c0078401cc0b2a049891c08700a2800da28e30a209c49891c087000200c310870881fb0f900002cf09ffc039096484030016803982cf0c6fd83462cf031fcb0f1000a01db504601e0caf1000003280bdb8d48403000682cf023fc00b28a49083108800120891d087087480a30b0f900002cf07afc039084484430016803982cf0a1fd83462cf00cfcb0f1000a01db504601e0caf1000003280bdb7b48443000682cf0fefb00b278490a3108800120091d087075480c30b0f900002cf055fc039071484830016803982cf07cfd83462cf0e7fbb0f1000a01db504601e0caf1000003280bdb6848483000682cf0d9fb00b265490c3108800120891c087073496248403000682cf028fd04d270485e49403108600ae06e495c48403000682cf042fc03d26b4858494031086068495648443000682cf011fd04d264485349443108600ae063495048443000682cf02bfc03d25f484d49443108605c494b48483000682cf0fafc04d259484749483108600ae057494548483000682cf014fc03d2544841494831086040480e300078012836d100203d4914310870481c0078401c491c087008460078012860db08460078401e08704848007850b1c81d006838b133486930d0f80e00c91d0968884209d22f486930d0f80e002c491c3108603ea0faf7a0ff2a486930d0f80e0027491c310968401a3d49086001203649087036e00020214915310870481e0078401c491e087008460078462829db08460078401e08702c48007801280ad0081d006838b117486930d0f80e00091d0968884209d213486930d0f80e0010491831086027a0faf768ff0e486930d0f80e000b4918310968401a2149086000201a49087006480e30007841e00000000000002840650500108e23d6e25300b43f840000106e1b0010726573756c74616e743d25640d0a0000d882de1b4300803f0000000000002440ac5303000707001060040010b2fac9fac5f6d7b2b1a8beaf0d0a000000000443000004c37400001061637469766174696f6e2e2e0d0a00007800001073696c656e742e2e0d0a000001281dd10020ff490860a1f126000088401c80b226390880012810dbfa48807b10b9faa0faf701ff0120fa490870f649887301f19900d0f80e00f249086083e0f1489930d0f80e00ee490968401a0a287ad90020eb4926390880eb48807b012802d1eda0faf7e1fe0020e7498873084690f82e00002867d00846807b002863d181f82e00df490d39087021310870e449dc48303000682cf095f9834629f0bafd82462cf0dbf8cde90201dea00cc802982bf098fccde900012bf0b8fb40b2d1490c390870d649cf48343000682cf07af9834629f09ffd82462cf0c0f8cde90201d0a00cc802982bf07dfccde900012bf09dfb40b2c3490b390870c949c148383000682cf05ff9834629f084fd82462cf0a5f8cde90201c3a00cc802982bf062fccde900012bf082fb40b2b6490a390870084690f90030401e90f90020401e90f90010ba48faf771fe0020ae4914310870b349ac48303000682cf034f9834629f059fd82462cf07af8cde90201ada00cc802982bf037fccde900012bf057fb47b2ab48007868b94ff080419e48303000682cf018f982462cf007fa01b2a548a0f8f41098490c3991f90010c81b01d4014600e041420f2903db012092491431087097499048343000682cf0fcf8834629f021fd82462cf042f8cde9020191a00cc802982bf0fffbcde900012bf01ffb4ffa80f88f48007868b94ff080418148343000682cf0dff882462cf0cef901b28948a0f8f6107b490b3991f90010b1eb080001d4014600e041420f2903db01207549143108707a497348383000682cf0c2f8834629f0e7fc82462cf008f8cde9020174a00cc802982bf0c5fbcde900012bf0e5fa4ffa80f97248007868b94ff080416448383000682cf0a5f882462cf094f901b26c48a0f8f8105e490a3991f90010b1eb090001d4014600e041420f2903db0120584914310870564814300078002834d05548c079002830d1534890f82e0000282bd14f480d380078002843d14e489930d0f80e004b4910310968401a1e2839d948480c3890f90020394652a0faf79cfd44480b3890f90020414659a0faf794fd40480a3890f90020494660a0faf78cfd02203b490f3908701ce0454890f87600c0f3400010b164a0faf77efd36489930d0f80e0033491031086000203d4991f8761060f341013a4880f8761000202c490d39087009e000202a490e3908700120491c0870891e087000bf00bfbde8ff9f2de9fc4100272348807928b953a0faf753fd3846bde8fc811e4816300078401cc0b21b4916310870642805dd0020491e08700120491c08704e480078a8b968461ef04ffcbdf90000c11700eb91618c11bdf90200c11700eb91618d11bdf90400c11700eb91618e110be00220fff73efa04460420fff73afa05460620fff736fa064603491831b1f90010601a74d4014673e0940000103e1b0010d5f1b6af0d0a00006c000010beb2d6b90d0a000000000443144c77b6d7a54c40f453030050000010070700100d0a616e676c6574656d702058203a25642020436f7572736f72536576617220583a256420200d0a000000000d0a616e676c6574656d702059203a25642020436f7572736f72536576617220593a256420200d0a000000000d0a616e676c6574656d70205a203a25642020436f7572736f725365766172205a3a256420200d0a00000000cdcfb5f5b1a8beafc7e5b3fd0d0a00002e2e2e2e2e2e2e47656e736f72204661696c0d0a0000000070000010ffe74142ff4a1278914208ddfe480480c01e0078401cfc49c91e08700127fa49891cb1f90010681a01d4014600e04142f44a1278914209ddf348801c0580401f0078401cf049c91e08700127ee49091db1f90010701a01d4014600e04142e94a1278914209dde848001d0680c01f0078401ce549c91e08700127e348c01e0078082811dbdf480178e0a0faf75afce648c08840f48071e448c1800020da49c91e08700120e14908703846f8e62de9fc4700240025a14600bf64e0dd480078b0b968461ef053fbbdf90000c11700eb91618e11bdf90200c11700eb91618f11bdf90400c11700eb91614feaa1180be00220fff741f906460420fff73df907460620fff739f98046c049891db1f90010701a01d4014600e04142ba4a1278914204ddb948801d0680601cc4b2b7490831b1f90010781a01d4014600e04142b14a1278914204ddb04808300780601cc4b2ae490a31b1f90010b8eb010001d4014600e04142a84a1278914205dda7480a30a0f80080601cc4b21ff032f90a201ff0d6fb681cc5b2142d98db0a2c07db4ff00109a548c08840f48071a348c1804846bde8fc8770b5044600251ef0eefdbcb1a1a0faf7c7fba748d0f80e00934910310860a448d0f80e00091f0860002008310870964908709f4999398873284670bd9348007818b99b489938807b08b300208f49087085481430007810b997a0faf7a1fb01208149143108709248d0f80e00083908609048d0f80e00091d0968401a19281fd379483e38008846281adb012518e08848d0f80e0074490c310968401a0a280fd371481430007810b18aa0faf779fb00206d49143108707e48d0f80e00091f08602846bae710b50020704908700120fff797ff10bd2de9f04f9bb004460d4616461f464ff00008c14672489938807910b91bb0bde8f08f5b481730007830b1002059491731087078481ef01bf978480078012808d100207549087002207549087072481ef00ef92088adf864006088adf86600a088adf868002888adf85c006888adf85e00a888adf86000adf85860adf85a700020adf85400adf856005548d0f80ea0212261490ca821f027f924220ca902a8cdf82ca021f090f915ab019316980090dde91723dde919011ef047f980465948007810b95848007800b1a8e739486e3890f87800c0f3401038b936486e38b0f87a0041f66411884200dc98e7bdf8540000284ad0bdf85400092847d2dfe800f04605c7fdfcfbfaf9f80035489938807a012809d032489938807a022804d030489938807a082832d1404890f80501c0f3c00030b93d4890f80501c0f30010002825d001211b486e3890f8790061f3820018496e3981f87900bdf856002bf056f8cde9120132a00cc812982bf08df8cde91001aff620100cc810982bf085f8cde90e012af0cffb834656e0b5e2b2e27de060c10720ac0000102e2e2e2e2e2e2eb3d6d0f8d5f1b6af4c696d69743a25640d0a000000750700106c000010700000100d0a2e2e2e2e2e2e2eb3f5cabcbbafd5f1b6afbcecb2e2cab1bce40d0a000000d71b00100d0a2e2e2e2e2e2e2ebfaacabcd5f1b6afcab1bce4bcecb2e20d0a000d0a2e2e2e2e2e2e2ebbd6b8b4d5f1b6afcab1bce40d0a001ae00720720000107300001061c10720a2c007206004001093e34ee960fd7f3ffcf7fdfe1ffa80f9ff48c3894a4601211b2011f0aefb0021fc4890f8790061f38200fa4981f87900f94890f8300220b1bdf856101d2010f0cffcf54890f80c01c0f3800018b105210120fcf7edf90120f049087033e2f048807a012807d0ee48807a022803d0ec48807a08285fd1e84890f80501c0f3c000002858d00120e34991f8761060f30411e04880f87610bdf856002af09fffcde91201aff2a4000cc812982af0d5ffcde91001aff690200cc810982af0cdff05e0ffe1bfe15ee1fde09ce035e0cde90e012af010fb8346fcf796fe1ffa80f9ff2000eb09201ffa80f9c948c08983b24a460121042011f041fb0020c64991f8761060f30411c34880f87610c34890f8300220b1bdf85610042010f062fcbe4890f80901c0f3c00018b105210120fcf780f90120ba490870c6e1b948807a012807d0b748807a022803d0b548807a082858d1b14890f80501c0f30010002851d00121ac4890f8760061f34510aa4981f87600bdf856002af032ffcde91201aff280100cc812982af068ffcde91001aff66c300cc810982af060ffcde90e012af0aafa8346fcf730fe1ffa80f9ff2000eb09201ffa80f99648008a83b24a460121052011f0dbfa0021934890f8760061f34510904981f87600904890f8300220b1bdf85610052010f0fcfb8b4890f80901c0f3001018b105210120fcf71af901208749087060e18648807a012807d08448807a022803d08248807a082852d17e4890f80601c0f3800000284bd00121794890f8770061f38200774981f87700bdf856002af0ccfecde91201aff24c200cc812982af002ffcde91001aff638400cc810982af0fafecde90e012af044fa8346fcf7cafd1ffa80f96648838b4a4601210b2011f07bfa0021634890f8770061f38200604981f87700604890f8300220b1bdf856100b2010f09cfb5b4890f80a01c0f3800018b105210120fcf7baf801205749087000e15648807a012807d05448807a022803d05248807a082852d14e4890f80601c0f3c00000284bd00121494890f8770061f3c300474981f87700bdf856002af06cfecde91201aff20c300cc812982af0a2fecde91001aff6f8400cc810982af09afecde90e012af0e4f98346fcf76afd1ffa80f93648c38b4a4601210c2011f01bfa0021334890f8770061f3c300304981f87700304890f8300220b1bdf856100c2010f03cfb2b4890f80a01c0f3c00018b105210120fcf75af8012027490870a0e0244890f80701c0f30000002857d001211f4890f8770061f386101d4981f87700bdf856002af018fecde91201aff2b4300cc812982af04efecde91001aff6a0500cc810982af046fecde90e012af090f98346fcf716fd1ffa80f90c48038d4a460121112011f0c7f90021094890f8770061f38610064981f87700064890f8300280b1bdf856100f200ae00000650500100707001060040010710000103e1b001010f0dcfa794890f80b01c0f3000018b105210120fbf7faff01207549087040e07448807a032803d07248807a042835d16e4890f80501c0f3801078b301216e4890f8760061f341006b4981f876006b48838abdf856200121072011f07cf90020654991f8761060f34101634880f876105e4890f8300220b1bdf85610072010f09dfa5a4890f80901c0f3801018b105210120fbf7bbff01205549087001e000e000bf00bf91e41fb5d0205549487055484860554888601ef02efd5448007810b94c48807938bb5248406b10f4000f03d000205049087002e001204e4908704d48007810b9fef718fc01e0fef709fd0a214148b83020f01cfe0a213f48cc3020f017fe0a213c48e03020f012fe00203f490870491c08703748807900b91fbd3d480078f0b902a81df0bdfe68461df0edfebdf90800c11700eb91618911adf80810bdf90a00c11700eb91618911adf80a10bdf90c00c11700eb91618911adf80c1021e00220fef7a3fc044600ebd0704010adf808000420fef79afc044600ebd0704010adf80a000620fef791fc044600ebd0704010adf80c000020adf80000adf80200adf804001448b0f8033190f80221694602a8fff7fbfb1448401c0078401cc0b21249491c087014280edb1e200870bdf80c309ab2bdf80a3099b2bdf8083098b2fef790fe1ef096fc97e760040010710000103e1b0010070700106505001048020010e3820200d5830200c100001000c00920700000100020ff4908708870c870704710b5fd48d0f87600fc490840fa49c1f87600fba0f9f71fff10bd0020fd4948700021fd4880f856160020fc49087048707047fb48007870470121fa4a1170f84908707047f6480078052801d000207047f5484078002837d0f348c07b002833d1f148c07810b9e348807808b10020eee7ed48407a022805d1de48007910b9dd48407808b10020e2e7e748c07a10b9e648007b08b10020dae7e348007d08b10020d5e7dd4890f85606da494978884201d00020cce7dd48d0f8b600dc49d1f8b210884201d00020c2e70120c0e730b50246087808b9104630bd0fe01446002307e0c818407808b91046f5e7641c581cc3b22078cd5ca842f3d0521ccd488242ecd10020e8e710b50024ca48007830b1ca48007818b90020c449487202e00120c2494872fff767ffbe4800780c2803d10120bc49087010bdba480078032806db0120b84908700a20b649087005e00c20b44908700120b349087000240be004eb4422ac49891c01eb420040f2024120f0cefc601cc4b2052cf1dbb0a0f9f76cfe0021a54880f856160020a2494870a649c872087348730021ad4880f837140020a24948708870c6e710b59d48007803280adb9b480078092806da01209b49887001f17d00f9f7f5fd10bd10b502209649487200209849087098490870914800780c2803d08f4800780b2806d100208c49087001208c49087010e08948007805280cdb924890f8370410b18748407828b986483130f9f7cdfdfff7c6ff10bd002082493031c1f84900704730b50246087808b9104630bd0fe01446002307e0c818407808b91046f5e7641c581cc3b22078cd5ca842f3d0521c76488242ecd10020e8e730b50346187808b9184630bd002409e018780d78a84201d00846f6e75b1c491c601cc4b29442f3db0020eee710b54ff4e0716f4820f03afc00205649087110bd2de9fc4104460d46164698461ef021fb1cb94ff0ff30bde8fc81b6f50c7f02dd4ff0ff30f7e7204620f0b3fb152802d94ff0ff30efe74ff480615e4820f016fc5d480078042809d12b46224600215948cde9006813f090fe07461ae05648007805280dd12b46224600215248cde9006813f09cff0746394650a0f9f79cfd08e02b46224600214b48cde9006813f005fd07463846bfe770b504460e4615464b4880f8625a42484480418849a0f9f783fd41480078052809d1082e03d103203c49487005e006203a49487001e038494e7001202049087170bd0ae0037803b90be003788b4201d1531edab2401c02b903e000f1a0039842f0d100bf70472de9f04f89b00c46154600260027b046b246002007901ef09efa2f4620782a2803d1781e205c2a2804d011262ea0f9f744fda2e00020e055641c08f101001ffa80f800212c4880f82f1200f2292020f022fb8346024651e028cf072007070010cd11a000c7e5b3fdc8abb2bfb1a8beafd1b6cfa20d0a00005d2d0010072700106c030010d1000010340100103e1b00102b430010ec1f00107dc107207ec10720436c6f736520475052530d0a000000000717001069410010693d0010360100105064754c656e203d2025640d0a00000007370010534d535f446174616c656e203d2025640d0a0000b6ccd1b6cafdbeddb4edcef30d0a000060040010ff49204620f07dfa28b101200790fc49fca0f9f7d4fca41d08f106001ffa80f8002610e0fd4951f8260020f0b6fa8346fa4850f826105a46204620f062fa00b903e0701cc6b2112eecdb00bff34951f8260020f0a2fa0444f04951f8260020f09cfa40441ffa80f8ec4850f82610eca0f9f7a5fc112e02d1eea0f9f7a0fc0027042e7ed007dc6eb1012e7bd0022e7ad0032e79d124e1052e77d0062e76d0112ef7d1f7e3079810b109b0bde8f08fe6a0f9f785fc4ff4c871e74820f0dbfae649c8190a22e5a120f087fa07f10a0087b2e54820f062fa8346df49c8195a46e24920f07afae04820f058fa384487b22c21d948c155781c87b2db48333020f04dfa8346d549c8195a46d749333120f064fad548333020f041fa384487b22c21ce48c155781c87b2d048663020f036fa8346c949c8195a46cc49663120f04dfaca48663020f02afa384487b22c21c248c155781c87b2c448183820f01ffa8346be49c8195a46c049183920f036fabe48183820f013fa384487b22c21b748c155781c05e02fe221e0aae086e38ce24be387b2b548c01f20f001fa8346af49c8195a46b149c91f20f018faaf48c01f20f0f5f9384487b22a21a848c155781c87b2002208213846fff76bfe0be2079800b16be7a6a0f9f7f4fb4ff4c8719f4820f04afa9d49c8190c22a4a120f0f6f907f10c0087b29d4820f0d1f983469749c8195a46994920f0e9f9984820f0c7f9384487b22c219148c155781c87b29348333020f0bcf983468c49c8195a468f49333120f0d3f98d48333020f0b0f9384487b22c218548c155781c87b28748663020f0a5f983468149c8195a468349663120f0bcf98148663020f099f9384487b22c217a48c155781c87b27c484d3820f08ef983467549c8195a4678494d3920f0a5f976484d3820f082f9384487b22c216e48c155781c87b27048c01f20f077f983466a49c8195a466c49c91f20f08ef96a48c01f20f06bf9384487b22a216348c155781c87b2002208213846fff7e1fdb8e300bf079800b1e0e6022e03d167a0f9f767fb02e069a0f9f763fb4ff0000a002708e0e05d2c2803d10af1010000f0ff0a781c87b2782ff4ddbaf1040f6fd13221524820f0a8f914215048333020f0a3f914214d48663020f09ef9022e05d110214a48183820f097f904e0322147484d3820f091f907214448c01f20f08cf94ff0000a43e001222c212046fff7bafd8146a9eb0400401e87b2baf1000f04d13a462146394820f029f9baf1010f05d13a4621463548333020f020f9baf1020f05d13a4621463148663020f017f9baf1030f0ed1022e06d13a4621462b48183820f00cf905e03a46214628484d3820f005f9baf1040f05d13a4621462348c01f20f0fcf84c460af1010000f0ff0abaf1050fb8db022e60d11c4918392aa059e0bde0000089060010b6ccd1b6ceacbba4c3dcd4bfb4edcef33a25730d0a000000d4000010534d53436f6d6d616e64203d2025730d0a000000b2bbd6a7b3d6b5c4b6ccd1b6c3fcc1ee0d0a0000b2e9d1af495020b5d8d6b70d0a000000994100102a6765742067707273230000cc040010b2e9d1afd3f2c3fb0d0a00002a67657420646f6d61696e2300000000c9e8d6c3495020b5d8d6b70d0a000000c9e8d6c3d3f2c3fb0d0a00004950203a2025730d0a000000f9f785fa03e0f949f9a0f9f780faf7494631fba0f9f77bfaf4494d31fba0f9f776faf2498031fca0f9f771faef49b331fca0f9f76cfa4ff4c871fd4820f0c2f8022e16d1fb49486c20f04ff88346f9485a46416cf64820f067f8f649486c20f044f81ffa80fb002208215846fff7bffc15e0f049c86c20f038f88346ed485a46c16ceb4820f050f8ea49c86c20f02df81ffa80fb002208215846fff7a8fc022e04d10020d1491f39887703e00120cf491f398877fff7d3fb0020df49887701201f318874faf70ef933e04ff4c871d84820f078f8022e16d1d649886c20f005f88346d4485a46816cd14820f01df8d149886c1ff0faff1ffa80fb002208215846fff775fc15e0cb49086d1ff0eeff8346c8485a46016dc64820f006f8c549086d1ff0e3ff1ffa80fb002208215846fff75efc35e2079800b15ee54ff4c871bc4820f040f8bd49d1f806002af01cfe01902af0f6fbcde90401b9a00cc8049829f09ffccde9020129f0fdfa8346b348801f007ec0f3400020b9594600202af07aff8346ae49891d48682af0fdfd01902af0d7fbcde90401aaa00cc8049829f080fccde9020129f0defa0690a448801f007ec0f3000020b9002006992af05bff069006982af0bdfbcde9040158462af0b8fbcde90201dde90401cde900019aa19448dde902231ff08afc87b2002208213846fff7fdfbd4e1079800b1fde409229fa120461ff026ff60bb002178481f3880f806124ff4c87186481ff0d4ff8549486d1ff063ff834683485a46416d80481ff07bff8049486d1ff058ff1ffa80fb002208215846fff7d3fb01207b491f318874faf748f801208c4908708c48f9f704f988e009228aa120461ff0f3fe00282fd101215e481f3880f806124ff4c8716c481ff0a0ff6b49486d1ff02fff834669485a46416d66481ff047ff6649486d1ff024ff1ffa80fb002208215846fff79ffb012061491f318874faf714f801207249087076a0f9f723f97048f9f7cdf851e0072280a120461ff0bcfe00282fd1022142481f3880f806124ff4c87150481ff069ff5049486d1ff0f8fe83464d485a46416d4b481ff010ff4a49486d1ff0edfe1ffa80fb002208215846fff768fb012045491f318874f9f7ddff0120564908706aa0f9f7ecf85548f9f796f81ae04ff4c8713b481ff03eff3a49886d1ff0cdfe834638485a46816d35481ff0e5fe3549886d1ff0c2fe1ffa80fb002208215846fff73dfb14e1079800b13de464a0f9f7c6f84ff4c8712a481ff01cff2849c8190e2263a11ff0c8fe07f10e0087b213481f3890f8060210f00f0f09d12049c8190a225fa11ff0b8fe07f10a0087b2cce00a481f3890f8060200f00f0001280ad11749c8190a2259a11ff0a6fe07f10a0087b2bae0bfe0afe0d9e000007f040010646f6d61696e3a2025730d0a00000000504f5254203a2025730d0a0041504e3a2573200d0a000000555345523a2573200d0a00005057443a2573200d0a00000099410010d40000101f1b00100d0700100000000040774b412a706f736974696f6e23687474703a2f2f6d6170732e676f6f676c652e636f6d2f3f713d252e36662c252e366620202a0000000070617373656e676572000000440100104001001068656176796475747900000050726f5f536d73536574506172613a3520686561767964757479205265737453797344656c6179466c6167203d20545255450d0a00000000747261636b65720050726f5f536d73536574506172613a3520747261636b6572205265737453797344656c6179466c61"))));
		//System.out.println(ParseUtil.parseByteToHexStr(byteOrbyte(parseHexStrToByte("00320016013836373630323032393239303537390010150000008D01C3AFC3FBC0CFCFE700D2BBBCD7B1FBD2E63453B5EA00"))));
		
		System.out.println("=="+parseByteToHexStr(ParseUtil.intToBytes(2017, 1)));
		//7E03C12083397F1070103F3133343539323435383836202020203030303030303030303030202020202B3836313330313033383035303020202020202020203131313131314D7E

		// TODO Auto-generated method stub
		//System.out.println(ParseUtil.parseByteToHexStr(sumCheck(parseHexStrToByte("0300040131313131313101010F313835383930343331323620202020"),2)));
		//byte[] aa = YxEncoderUtil.getJymSmsByte(parseHexStrToByte("397F1070107F01"));
		//String nSndString="18589043126   ";	
		//byte jyhbyte[] = ParseUtil.sumCheck(parseHexStrToByte("407DC4AC3C1E43380018008000000B21012B20EA380AC03F6E011C1D462D880F0612"), 1);

		/*int i = jyhbyte[0] & 0xff;
		int i3 = jyhbyte[1] & 0xff;
		System.out.println("i=========="+i);
		System.out.println("i3=========="+i3);*/
		//jyhbyte[0] = (byte) ((int) jyhbyte[0] & 0xff);
		//jyhbyte[1] = (byte) ((int) jyhbyte[1] & 0xff);

		//System.out.println(ParseUtil.byteToint(jyhbyte));
		
		//System.out.println(parseByteToHexStr(byteOrbyte(parseHexStrToByte("0C50"))));


	}

}
