/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.utlis;

import java.text.DecimalFormat;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：StringUtils    
 * 类描述： 工具类   
 * 创建人：zr    
 * 创建时间：2015-5-14 上午09:41:04    
 * 修改人：zr    
 * 修改时间：2015-5-14 上午09:41:04    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class StringUtils {


	/**检测是否为空*/
	public static boolean isNull(String obj){
		if(obj == null || obj.equals("") || obj.equals("null") || obj.equals("NULL")){
			return true;
		}
		return false;
	}

	public static String jsonChar(String parm,Object value,int type){
		String str = null;

		if(type == 0){
			str = "\""+parm+"\":"+value+",";  
		}else {
			str = "\""+parm+"\":"+value+"";
		}
		return str;
	}

	public static String charStr(String parm){
		if(parm == null ){
			parm = "";
		}
		String str = null;
		str = "\""+parm+"\""; 
		return str;
	}

	// 解析一行记录
	public static Properties parse(String line) {
		String parsePattern="([^=\t]+)=([^\t]*)";
		Matcher m = Pattern.compile(parsePattern).matcher(line);
		Properties result = new Properties();
		while (m.find()) {
			String key = m.group(1).trim();
			String value = m.group(2).trim();
			result.put(key.toUpperCase(), value);
		}
		return result;
	}


	/**
	 * 整数转换成小数/1000
	 * @param parm
	 * @return
	 */
	public static String intChangeString(int parm,int number){

		String  s = "0";
		try {
			String xs = "0.0";
			if(number == 10){
				xs = "0.0";
			}else if(number == 100){
				xs = "0.00";
			}else if(number == 1000){
				xs = "0.000";
			}else if(number == 10000){
				xs = "0.0000";
			}else if(number == 100000){
				xs = "0.00000";
			}
			float num = (float)parm/number;  
			DecimalFormat df = new DecimalFormat(xs);//格式化小数  
			s = df.format(num);
			s = String.valueOf(Double.parseDouble(s));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return  s;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int eLng = 68324185;
		double aa = (double)(eLng%10000)/3600;
		System.out.println(eLng/10000/60+aa);
	}


}
