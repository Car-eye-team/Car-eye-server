package com.careye.constant;

import java.util.HashSet;
import java.util.Set;

public class Constant {

	/**发送短信*/
	public static final int SEND_SMS = 33546;
	
	/**
	 * 连接主机的超时时间（单位：毫秒）
	 */
	public static final int CONNECT_TIMEOUT = 60000;			
	
	/**
	 * 从主机读取数据的超时时间（单位：毫秒）
	 */
	public static final int READ_TIMEOUT = 60000;
	
	
	/**
	 * 数据包分包数
	 */
	public static final int DATA_PACKET_NUM = 8;
	
	/**
	 * 系统路径
	 */
	public static String BASE_PATH = "";
	
	/**
	 * 软电话新客户默认客户类型
	 */
	public static int SOFT_PHONE_CUSTOM_TYPE = 1;
	
	/**
	 * 软电话新客户默认客户类型名称
	 */
	public static String SOFT_PHONE_CUSTOM_TYPENAME = "默认客户";

	
	/**
	 * 报警过期截止日期与当前差，标记为已过期,单位天
	 */
	public static final int ALARM_STATUS = -1;

	/** 用户登陆日志报表 **/
	public static final int USER_LOGINFO_EXPORT = 1;
	
	/** 系统操作日志报表 **/
	public static final int SYS_OPTER_EXPORT = 2;
	
	/**
	 * 查询终端参数休眠时间（单位：毫秒）
	 */
	public static final int TERMINALPARAM_TIMEOUT = 8000;		
	
	/**订单MAP*/
	public static Set ORDERNO_MAP = new HashSet();
	
	
	/**  后枕屏广告  */
	public static final int LATERLIGHT = 22;
	
	/**  顶灯广告  */
	public static final int TOPLIGHT = 21;
	
	/**  主机广告  */
	public static final int HOSTADV = 23;
	
	/**openfire中的加密key*/
	public static final String OPENFIRE_PASSWORD_KEY = "7J4npxGBCTFj32q";
	
}
