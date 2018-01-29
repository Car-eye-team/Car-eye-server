/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.constant;

import java.util.HashMap;
import java.util.Map;

import com.careye.redis.domain.AuthInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：Constant    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-29 上午11:30:46    
 * 修改人：zr    
 * 修改时间：2015-5-29 上午11:30:46    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class Constant {

	/**终端路由信息在redis缓存的名称*/
	public static final String CLIENT_ROUTE = "clientroute_";
	
	/**最后一次接收接入服务器上行消息时间,初始化为当前时间*/
	public static String LAST_RECEIVE_COMM_TIME = null;
	
	/**雅迅协议中用户识别码*/
	public static String USER_ID = "313131313131";
	
	/**鉴权信息MAP*/
	public static Map<String, AuthInfo> AUTHINFO_MAP = new HashMap<String, AuthInfo>();
	
	
}
