/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */




package com.careye.dsparse.utlis;

import java.io.UnsupportedEncodingException;

public class Base64Tool {

	private static char[] digits ={'0','1','2','3','4','5','6','7','8','9',':',';','A','B','C','D','E','F','G','H','I','J','K'
		,'L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l',
		'm','n','o','p','q','r','s','t','u','v','w','x','y','z'};


	/** 
	 * @param args 
	 */  
	/** 
	 * 把10进制的数字转换成64进制 
	 * @param number 
	 * @param shift 
	 * @return 
	 */  
	public static String compressNumber(int number, int shift) {  
		char[] buf = new char[64];  
		int charPos = 64;  
		int radix = 1 << shift;  
		long mask = radix - 1;  
		do {  
			buf[--charPos] = digits[(int)(number & mask)];  
			number >>>= shift;  
		} while (number != 0);  
		return new String(buf, charPos, (64 - charPos));  
	}  

	public static void main(String args[]) throws UnsupportedEncodingException{
		System.out.println(compressNumber(13,6));   
	}

}
