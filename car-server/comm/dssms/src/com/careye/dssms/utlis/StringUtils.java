/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dssms.utlis;

import java.io.UnsupportedEncodingException;

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
	
	public final static String changeToGB2312(String str) {
		if (str == null)
			return null;
		if (str.equals(""))
			return "";
		try {
			return new String(str.getBytes(),"GB2312");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
}
