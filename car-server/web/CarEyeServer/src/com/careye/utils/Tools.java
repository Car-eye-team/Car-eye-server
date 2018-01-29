package com.careye.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * @author zr
 * 工具类
 */
public class Tools {

	/**
	 * 将整数转换成二进制字节（先低字节后高字节）
	 * @param num
	 * @return
	 */
	public static byte[] int2Bytes(int iSource, int iArrayLen) {
		byte[] bLocalArr = new byte[iArrayLen];
		for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
			bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);
		}
		return bLocalArr;
	}

	/**
	 * 获取整数转换成二进制字节
	 * @return
	 */
	public static String int2BytesStr(int iSource, int iArrayLen){
		String str = parseByte2HexStr(int2Bytes(iSource,iArrayLen));
		return str;
	}

	/**
	 * 获取指定长度字节数
	 * @param src
	 * @param startIndex
	 * @param length
	 * @return
	 */
	public static byte[] byteTobyte(byte[] src, int startIndex, int length)
	{
		byte[] des = new byte[length];
		int i = 0; 
		for (int j = startIndex; i < length; ++j) {
			des[i] = src[j];
			++i;
		}
		return des;
	}


	public static byte[] longToByteOne(long num){
		byte[] b = new byte[1];
		b[0] = (byte)(int)(num >>> 0);
		return b;
	}

	/**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	public static String parseByte2HexStr(byte buf[]) {  
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
	public static byte[] parseHexStr2Byte(String hexStr) {  

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
	 * 1)	报文的发送端需要将待发送报文的数据内容中（从版本号至校验位，包括版本号和校验位）出现的1002H转义为0x10100202，将出现的1003H转义为0x10100303。
	 * @param str
	 * @return
	 */
	public static byte[] tropeByte(String str,int type){
		byte[] result = new byte[str.length()/2];
		if(type == 1){
			if(str.indexOf("1002")>=0){
				str = str.replaceAll("1002", "10100202");
			}

			if(str.indexOf("1003")>=0){
				str = str.replaceAll("1003", "10100203");
			}
		}
		//		if(str.indexOf("10")>=0){
		//			str = str.replaceAll("10", "1010");
		//		}
		str = replaceStr(str,"10", "1010");
		result = Tools.parseHexStr2Byte(str);

		return result;
	}

	/**
	 * 为保证数据传输的透明，需对信息字段中出现的标志位进行转义处理，定义如下
	 *  7EH 《————》 7DH+02H；
	 *  7DH 《————》 7DH+01H；
	 * @param str
	 * @param type
	 * @return
	 */
	public static byte[] tropeYXByte(String str,int type){

		byte[] result = new byte[str.length()/2];

		if(type == 1){
			str = replaceStr(str,"7D", "7D01");
			str = replaceStr(str,"7E", "7D02");
			//			if(str.indexOf("7D")>=0){
			//				str = str.replaceAll("7D", "7D01");
			//			}
			//
			//			if(str.indexOf("7E")>=0){
			//				str = str.replaceAll("7E", "7D02");
			//			}
		}
		str = replaceStr(str,"10", "1010");
		result = Tools.parseHexStr2Byte(str);

		return result;
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
	 * 对DTU协议进行逆转义(1010转10)
	 * @param bytes
	 * @return
	 */
	public static byte[] dtuTransferredMeaning(byte[] bytes){

		StringBuffer des = new StringBuffer();
		String str = "";
		for (int j = 0; j < bytes.length; j++) {

			String hex = Integer.toHexString(bytes[j] & 0xFF).toUpperCase();  
			if (hex.length() == 1) {  
				hex = '0' + hex;  
			}

			if(hex.equals("10")){
				str = str+hex;
				if(str.indexOf("1010")>=0){
					str = str.replaceAll("1010", "10");
					des.append(str);
					str = "";
				}

			}else{
				if(str.indexOf("1010")>=0){
					str = str.replaceAll("1010", "10");
				}
				des.append(str);
				str = "";
				des.append(hex);
			}
		}
		return Tools.parseHexStr2Byte(des.toString());
	}

	/**
	 * 计价器与智能顶灯通信协议逆转 
	 * 2)	报文的接收端在找到报文头（1002H）后，接收数据内容（从版本号至校验位，包括版本号和校验位），
	 *    在接收过程中，需要将遇到的0x10100202逆转义为0x1002，将将遇到的0x10100303逆转义为0x1003
	 * @param bytes
	 * @return
	 */
	public static byte[] taximeterToplightReversal(byte[] bytes){
		String str = parseByte2HexStr(bytes);
		str = str.replaceAll("10100202", "1002").replaceAll("10100303", "1003");
		return parseHexStr2Byte(str);

	}

	/**
	 * GK-120D与GK-110G接口协议
	 * 其中帧以7EH作为起始/结束标志，为保证数据传输的透明，需对信息字段中出现的标志位进行转义处理，定义如下
	 *  7DH+02H 《————》 7EH；
	 *  7DH+01H 《————》7DH 
	 * @param bytes
	 * @return
	 */
	public static byte[] yxReversal(byte[] bytes){
		String str = parseByte2HexStr(bytes).toUpperCase();
		str = str.replaceAll("7D02", "7E").replaceAll("7D01", "7D");
		return parseHexStr2Byte(str);
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
	 *String的字符串转换成unicode的String
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
	 *unicode的String转换成String的字符串
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
	 * 得到经纬度的度、分（分+小数点后四位）
	 * @param latlng 
	 * @param type 1 返回度 2 返回分 3 返回小数点后（1至2）两位 4 返回小数点（3至4）两位 
	 * @return 十六进制字符串
	 */
	public static String getLatLng(double latlng,int type){

		try {
			int reLatlng = 0;
			double min = (latlng-(int)latlng)*60;
			String minStr = String.valueOf(min).substring(String.valueOf(min).indexOf(".")+1, String.valueOf(min).length());
			int size = minStr.length();
			if(minStr.length()<4){
				for (int i = 0; i < (4-size); i++) {
					minStr +="0";
				}
			}
			switch (type) {
			case 1:
				reLatlng = (int)latlng;
				break;
			case 2:
				reLatlng = (int) ((latlng-(int)latlng)*60);
				break;
			case 3:
				reLatlng = Integer.parseInt(minStr.substring(0, 2));
				break;
			case 4:
				reLatlng = Integer.parseInt(minStr.substring(2, 4));
				break;
			default:
				break;
			}
			return Tools.parseByte2HexStr(Tools.longToByteOne((reLatlng)));
		} catch (Exception e) {
			e.printStackTrace();
			return "00";
		}
	}

	/**
	 * 解码经纬度
	 * @param latlngbyte
	 * @return
	 */
	public static Double decoderLatlng(byte[] latlngbyte){
		try {
			int no=0;
			byte[] ad = Tools.byteTobyte(latlngbyte, no, 1);
			int adi = Integer.parseInt(Tools.parseByte2HexStr(ad),16);
			no+=1;
			byte[] ac = Tools.byteTobyte(latlngbyte, no, 1);
			no+=1;
			byte[] ac1 = Tools.byteTobyte(latlngbyte, no, 1);
			no+=1;
			byte[] ac2 = Tools.byteTobyte(latlngbyte, no, 1);
			no+=1;
			StringBuffer acBuffer = new StringBuffer();
			acBuffer.append(Integer.parseInt(Tools.parseByte2HexStr(ac),16));
			acBuffer.append(Integer.parseInt(Tools.parseByte2HexStr(ac1),16));
			acBuffer.append(Integer.parseInt(Tools.parseByte2HexStr(ac2),16));
			Double acDouble = Double.parseDouble(acBuffer.toString())/10000;
			Double latlng = adi + acDouble/60;
			DecimalFormat df = new DecimalFormat("#.000000");
			return Double.parseDouble(df.format(latlng));
		} catch (Exception e) {
			e.printStackTrace();
			return 0.000000;
		}
	}

	/**
	 * 得到16进制 年 月 日 时 分 秒
	 * @param formart
	 * @return 十六进制字符串
	 */
	public static String getDateTime(String formart){
		String str = "00";
		try {
			String systemdate = new SimpleDateFormat(formart).format(Calendar.getInstance().getTime());
			str = parseByte2HexStr(longToByteOne(Long.parseLong(systemdate)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return str.trim();
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
	 * @函数功能: BCD码转为10进制串(阿拉伯数据)
	 * @输入参数: BCD码
	 * @输出结果: 10进制串
	 */
	public static String bcd2Str(byte[] bytes){
		StringBuffer temp=new StringBuffer(bytes.length*2);

		for(int i=0;i<bytes.length;i++){
			temp.append((byte)((bytes[i]& 0xf0)>>>4));
			temp.append((byte)(bytes[i]& 0x0f));
		}
		return temp.toString().substring(0,1).equalsIgnoreCase("0")?temp.toString().substring(1):temp.toString();
	}

	/** *//**
	 * @函数功能: 10进制串转为BCD码
	 * @输入参数: 10进制串
	 * @输出结果: BCD码
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
	 * 转换成指定长度的字符串
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

	// 将byte数组转换成InputStream  
	public static InputStream byteTOInputStream(byte[] in) throws Exception {  

		ByteArrayInputStream is = new ByteArrayInputStream(in);  

		return is;  

	}  

	/**
	 * 字符串转换成十六进制字符串
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


	public static void main(String[] args){
		//		System.out.println("bytes2HexString(longToByte(36609))=="+parseByte2HexStr("停运".getBytes()));
		//		//System.out.println(0x1002);
		//		System.out.println("333333333");
		//		Scanner sc = new Scanner(System.in);
		//		System.out.println("请出入一个十进制数");
		//		int x = sc.nextInt();
		//		System.out.println(x+"的十六进制结果是："+Integer.toHexString(x));
		//System.out.println(parseByte2HexStr(int2Bytes(17,2)).substring(2, 4));
		//System.out.println("2222=="+CRCverify.getCRC16(Tools.parseHexStr2Byte("00010205000025030027010000001C36363636361C00000000000000000000000000000000000000000000000000003200")));
		//		String data = "001245616325478";
		//		int size = data.indexOf("16");
		//		if(size>=0){
		//			System.out.println("str1="+data.substring(0, size));
		//			System.out.println("str2="+data.substring(size, data.length()));
		//		}
		//	System.out.println("transferredMeaning==="+Tools.parseByte2HexStr(dtuTransferredMeaning(Tools.parseHexStr2Byte("1010001010222222"))));

		//		String str = "100";
		//		StringBuffer des = new StringBuffer();				
		//		String s = "";
		//		for(int i=0;i< str.length();i++){
		//			
		//			if(i%2==0){
		//				if(s.equals("10")){
		//					s = "1010";
		//				}
		//				
		//				des.append(s);
		//				s = "";
		//				s = s+str.charAt(i);
		//			}else{
		//				s = s+str.charAt(i);
		//			}
		//		}
		//		
		//		System.out.println("des.toString()--"+replaceStr("222222102525","10","1010"));

	} 


}
