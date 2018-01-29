/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dssms.utlis;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**    
 *     
 * 项目名称：dssms    
 * 类名称：ToolsUtil    
 * 类描述： 工具类   
 * 创建人：zr    
 * 创建时间：2015-6-25 下午05:49:18    
 * 修改人：zr    
 * 修改时间：2015-6-25 下午05:49:18    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ToolsUtil {
	
	/**
	 * 获取等号后面的值
	 * @param line
	 * @return
	 */
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
	
}
