/**
 * Description: car-eye车辆管理平台
 * 文件名：Base64Tool.java
 * 版本信息：1.0
 * 日期：2013-8-10
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.utils;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @项目名称：car-eye
 * @类名称：Base64Tool
 * @类描述：
 * @创建人：zr
 * @创建时间：2013-8-10 下午02:10:59
 * @修改人：zr
 * @修改时间：2013-8-10 下午02:10:59
 * @修改备注：
 * @version 1.0
 */
public class Base64Tool {
	private static BASE64Encoder encoder = new BASE64Encoder();

	private static char[] codec_table = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',   
		'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',   
		'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',   
		'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',   
		'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',   
		'7', '8', '9', '+', '/' };   

	// 将 s 进行 BASE64 编码
	public static String getBASE64(String s){
		if (s == null || s.equals("")){
			return null;
		}else{
			String newStr = "";
			s = s.replaceAll("\r\n", "");
			s = s.replaceAll("\r", "");
			s = s.replaceAll("\n", "");

			try {
				newStr = encoder.encode(s.getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			newStr = newStr.replaceAll("\r\n", "");
			newStr = newStr.replaceAll("\r", "");
			newStr = newStr.replaceAll("\n", "");
			return newStr;
		}
	}

	// 将 BASE64 编码的字符串 s 进行解码
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b,"utf-8");
		} catch (Exception e) {
			return null;
		}
	}

	public static String encode(byte[] a) {   
		int totalBits = a.length * 8;   
		int nn = totalBits % 6;   
		int curPos = 0;// process bits   
		StringBuffer toReturn = new StringBuffer();   
		while (curPos < totalBits) {   
			int bytePos = curPos / 8;   
			switch (curPos % 8) {   
			case 0:   
				toReturn.append(codec_table[(a[bytePos] & 0xfc) >> 2]);   
				break;   
			case 2:   

				toReturn.append(codec_table[(a[bytePos] & 0x3f)]);   
				break;   
			case 4:   
				if (bytePos == a.length - 1) {   
					toReturn   
					.append(codec_table[((a[bytePos] & 0x0f) << 2) & 0x3f]);   
				} else {   
					int pos = (((a[bytePos] & 0x0f) << 2) | ((a[bytePos + 1] & 0xc0) >> 6)) & 0x3f;   
					toReturn.append(codec_table[pos]);   
				}   
				break;   
			case 6:   
				if (bytePos == a.length - 1) {   
					toReturn   
					.append(codec_table[((a[bytePos] & 0x03) << 4) & 0x3f]);   
				} else {   
					int pos = (((a[bytePos] & 0x03) << 4) | ((a[bytePos + 1] & 0xf0) >> 4)) & 0x3f;   
					toReturn.append(codec_table[pos]);   
				}   
				break;   
			default:   
				break;   
			}   
			curPos+=6;   
		}   
		if(nn==2)   
		{   
			toReturn.append("==");   
		}   
		else if(nn==4)   
		{   
			toReturn.append("=");   
		}   
		return toReturn.toString();   

	}   

	public static void main(String args[]) throws UnsupportedEncodingException{
		Base64Tool encoder = new Base64Tool();
		//		System.out.println();
		System.out.println(encoder.getBASE64("北京市"));
		System.out.println(encoder.getFromBASE64(encoder.getBASE64("北京市")));
	}

}
