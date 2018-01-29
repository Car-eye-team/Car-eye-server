/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.utlis;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

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
	 * 经纬度转换  
	 * 度分转换：【度 . 分 . 分 格式】
		将度分单位数据转换为度单位数据 
		度=度+分/60 &
	 * @param lnglat
	 * @return
	 */
	public static double getLnglat(String lnglat){
		double lnglatd = 0;
		try {
			int len = lnglat.length();
			if(len < 9){
				for (int i = 0; i < (9-len); i++) {
					lnglat = "0"+lnglat;
				}
			}
			double ds=Double.parseDouble(lnglat.substring(0, 3));
			int fs1 = Integer.parseInt(lnglat.substring(3, 8));
			String fs2 = StringUtils.intChangeString(fs1,10000);
			lnglatd  = ds+(Double.parseDouble(fs2)/6);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lnglatd;
	}

	/**
	 * 获取指定长度"\0"结尾的数组转字符串
	 * @param buf
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public static byte[] byteToSubStringToByte(byte data[],int startIndex, int length){

		try {
			String dataHex = ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(data, startIndex, length));
			//用“00”结尾截取十六进制字符串
			String[] strarr = replaceStr(dataHex,"00","##").split("##");
			byte[] strbyte = null;
			if(strarr.length == 0){
				strbyte = new byte[0];
			}else{
				if("".equals(strarr[0])){
					strbyte = new byte[0];
				}else{
					strbyte =  parseHexStrToByte(strarr[0]);
				}

			}
			return strbyte;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

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
	 * 二进制转换成10进制整数
	 * @param buf
	 * @return
	 */
	public static int byteToint(byte buf[]){
		try {
			return Integer.parseInt(parseByteToHexStr(buf), 16);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int byteTogdint(byte buf[]){
		try {
			return Integer.parseInt(parseByteToHexStr(sortToByte(buf)), 16);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
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

		if (hexStr == null) 
			return null;

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
	 * 字符串转换成ascii的2进制
	 * @param value
	 * @return
	 */
	public static byte[] stringToAsciiByte(String value){
		StringBuffer sbu = new StringBuffer();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			sbu.append(Integer.toHexString((int)chars[i]));   
		}
		return parseHexStrToByte(sbu.toString());
	}

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
	 * 字符串转字符串
	 * @param value
	 * @param len
	 * @return
	 */
	public static String strToStr(String value,int len){
		if(value == null){
			value = "";
		}
		int length = value.length();
		if(length > len){
			value = value.substring(0, len);
		}else{
			for (int i = 0; i < (len-length); i++) {
				value = value + "0";
			}
		}
		return value;

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
	 * 数据转义 
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
	public static byte[] dataEscape(byte[] bytes){
		try {

			String data = parseByteToHexStr(bytes);
			byte[] result = new byte[data.length()/2];
			data = replaceStr(data,"7D", "7D01");
			data = replaceStr(data,"7E", "7D02");
			result = parseHexStrToByte(data);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return bytes;
		}
	}

	/**
	 * 转义操作
	 * @param str
	 * @param thq
	 * @param thh
	 * @return
	 */
	public static String replaceStr(String str,String thq,String thh){

		StringBuffer des = new StringBuffer();				
		String s = "";
		str = str+" ";
		for(int i=0;i< str.length();i++){

			if(i%2==0){
				if(s.equals(thq)){
					s = thh;
				}

				des.append(s);
				s = "";
				s = s+str.charAt(i);
			}else{
				s = s+str.charAt(i);
			}
		}
		return des.toString();

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
		if(src!=null){
			byte[] des = new byte[length];
			int i = 0; 
			for (int j = startIndex; i < length; ++j) {
				des[i] = src[j];
				++i;
			}
			String str = null;
			try {
				str= new String(des,"GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
			return str.trim();
		}else{
			return null;
		}
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
	 * 二进制字符串转十进制
	 * 
	 * @param binary
	 *            二进制字符串
	 * @return 十进制数值
	 */
	public static int binaryToAlgorism(String binary) {
		int max = binary.length();
		int result = 0;
		for (int i = max; i > 0; i--) {
			char c = binary.charAt(i - 1);
			int algorism = c - '0';
			result += Math.pow(2, max - i) * algorism;
		}
		return result;
	}

	/**
	 * double 保留两位小数
	 * @param value
	 * @return
	 */
	public static String twoDecimalPlaces(double value){
		DecimalFormat df = new DecimalFormat("######0.00"); 
		return df.format(value); 
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*double bb =40582974*0.0001/180;
		double ss = bb/180;
		double aa = 1352%60;
		System.out.println("41601003=="+bb);*/

		//System.out.println(StringUtils.parse("DWXX=Lat:N23d5.1708m,Lon: E114d23.6212m,Course:120,Speed:53.02;DateTime:08-09-12 14:52:36").get("DWXX").toString());

		//Lat:N23d5.1708m,Lon: E114d23.6212m,Course:120,Speed:53.02;DateTime:08-09-12 14:52:36
		/*String info ="Lat:N23d5.1708m,Lon: E114d23.6212m,Course:120,Speed:53.02;DateTime:08-09-12 14:52:36";
		String[] infoarr = info.split(",");
		String lat = infoarr[0].split(":")[1].replace("N", "").replace("m", "");
		String lng = infoarr[1].split(":")[1].trim().replace("E", "").replace("m", "");
		String direction = infoarr[2].split(":")[1];
		String speed = infoarr[3].split(";")[0].split(":")[1];
		String gpstime = DateUtil.dateToNumber1(infoarr[3].split(";")[1].replace("DateTime:", ""));
 
		System.out.println("123456="+lat);
		System.out.println("123456="+lng);*/
		
		System.out.println(ParseUtil.parseByteToHexStr(ParseUtil.byteOrbyte(parseHexStrToByte("000B0202000020130101081547"))));
	}

}
