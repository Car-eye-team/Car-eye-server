package com.careye.constant;

import java.util.HashSet;
import java.util.Set;

public class Constant {

	/**
	 * 连接主机的超时时间（单位：毫秒）
	 */
	public static final int CONNECT_TIMEOUT = 60000;			
	
	/**
	 * 从主机读取数据的超时时间（单位：毫秒）
	 */
	public static final int READ_TIMEOUT = 60000;
	
	/**乐嘀版本类型**/
	public static final Integer LD_APP_TYPE = 181;
	
	/**Launcher版本类型**/
	public static final Integer LAUNCHER_APP_TYPE = 201;
	
	/**car-eye车辆管理平台设备类型**/
	public static final Integer DEVICE_TYPE = 29;
	
	/**DSS-2826-顺风车版本类型**/
	public static final Integer SF_APP_TYPE = 203;
	
	/**DSS-2826-对讲**/
	public static final Integer DJ_APP_TYPE = 210;
	/**
	 * 短信内容后缀
	 */
//	public static String SMS_FRONT_CONTENT = "【乐途无忧】";
	public static String SMS_FRONT_CONTENT = "【网约车】";
	
	/**
	 * 发送验证码短信内容
	 */
//	public static String SEND_AUTHCODE_CONTENT = "您的手机验证码:{0}，请在10分钟内使用。欢迎使用，如有问题请咨询4000755938。";
	public static String SEND_AUTHCODE_CONTENT = "您的手机验证码:{0}，请再10分钟内使用。欢迎使用，如有问题请咨询4000755938。";
	
//	/**
//	 * 华宝短信内容
//	 */
//	public static String SMS_FRONT_CONTENT = "【华宝科技】";
//	public static String SEND_AUTHCODE_CONTENT = "您的手机验证码:{0}，请在10分钟内使用。欢迎使用，如有问题请咨询4000755938。";
	
	/**验证码失效时间（毫秒）*/
	public static Integer LOSE_EFFICACY_TIME = 10*60*1000;
	
	/**订单MAP*/
	public static Set ORDERNO_MAP = new HashSet();
	
	/**微信订单开头数字*/
	public static Integer WECHAT_ORDER = 5;
	
	/**乐嘀订单开头数字*/
	public static Integer APP_ORDER = 3;
	
	/**
	 * 语音存储路径
	 */
	public static final String VOICE_PATH = "/upload/voice/";
	
	/**
	 * 本项目域名
	 */
	public static final String DOMAIN_NAME = "http://39.108.246.45:801";
	
	/**管理平台项目名**/
	public static final String PLATFORM_NAME = "http://39.108.246.45:800";
	
	/**服务证二维码地址**/
	public static final String FWZ_CODE_PATH = "/qrcode/fwz/";
	
	/** FTP图片域名*/
	public static final String  FTP_IP_PIC = "http://img.51tsp.com";
	
}
