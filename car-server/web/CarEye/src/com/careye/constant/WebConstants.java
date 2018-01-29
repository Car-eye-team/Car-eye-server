package com.careye.constant;



public class WebConstants {
	
	/**
	 * 发送验证码
	 */
	public static final String SEND_SMS_CODE = "/api/sendAuthcode.action";
	/**
	 * 接口密钥
	 */
	public static final String KEY = "zhvvc2vuz2f0zte0ndczmtqxodkymdy=";
	
	public static final String URL = "http://39.108.246.45:801";
	
	/**
	 * 验证校验码
	 */
	public static final String CHECK_SMS_CODE = "/api/verifySmsAuthCode.action";
	
	/**导出WORD模板目录**/
	public static final String FM_WORDFTL_DIC = "/report_ftl";
	/**freemarket 模板**/
	public static final String FM_FILE_DIC = "upload/freemarket";
	/**freemarket 评价统计模板保存目录**/
	public static final String FM_EC_WORD = "ecps17.ftl";
	
	/**freemarket 企业新车上线模板保存目录**/
	public static final String FM_OR_WORD = "newcar.ftl";
	
	/**freemarket 投诉统计模板保存目录**/
	public static final String FM_TS_WORD = "tsword01.ftl";
	
	/**freemarket 企业车辆在线情况模板保存目录**/
	public static final String FM_CARLOCATION_WORD = "carlocation.ftl";
	
	/**freemarket 车辆在线时长统计模板保存目录**/
	public static final String FM_CARCONDITION_WORD = "carcondition.ftl";
	
	/**freemarket 车辆在线率分析模板保存目录**/
	public static final String FM_ONLINERATE_WORD = "onlinerate.ftl";
	
	/**freemarket 终端功能使用统计模板保存目录**/
	public static final String FM_TFC_WORD = "terminalfuncount.ftl";
	
	/**freemarket 车辆上传照片查询信息模板保存目录**/
	public static final String FM_CARPHOTO_WORD = "carphoto3.ftl";
	/**
	 * word图片
	 */
	public static String WORDIMAGE="wordimage";
	
	/**
	 * 文件保存路径
	 */
	public static final String appDir = "upload";
	
	/**司机照片上传文件目录**/
	public static final String DRIVER_PHOTO_DIR = "driverphoto";
	
	
	/**报警声音上传文件目录**/
	public static final String TAXI_ALARMMUSIC_DIR = "alarmmusic";
	
	/**报警提醒默认声音**/
	public static final String ALARM_MUSIC_DEFAULT = "upload/alarmmusic/default.wav";
	
	
	/**飞嘀图片文件文件目录**/
	public static final String TAXI_FEIDI_IMG_DIR = "feidi";
	
	/**
	 * session信息
	 */
	public static String LOGIN_USER = "taxiuser";
	
	/**
	 * 文件保存路径
	 */
	public static final String SERVICELICENSE_DIR = "upload";
	
	/**
	 * 版本型号管理
	 */
	public static String APP="version";
	
	/**
	 * 服务证
	 */
	public static String SERVCIELISENCE="servicelicense";

	
	/**
	 * 返回根节点组织机构图标
	 * @return
	 */
	public static String getRootDeptPic(String contextpath){
		return "<img src='"+ contextpath + "/ext4/resources/images/dept.png'></img>";
	}
	
	/**
	 * 返回非根节点组织机构根节点图标
	 * @return
	 */
	public static String getDeptPic(String contextpath){
		return "<img src='"+ contextpath + "/ext4/resources/images/dept1.png'></img>";
	}
	/**
	 * 广告图片文件保存路径
	 */
	public static final String scrollAdvertDir = "upload";
	/**
	 * 广告图片版本型号管理
	 */
	public static String SCROLLADVERT="advertpic";
	
	/**
	 * 返回车辆状态文本
	 * @param carstatus 车辆状态
	 * @param travelposition 车辆运输状态
	 * @return
	 */
	public static String getCarText(String contextpath,String carnumber,Integer carstatus,Integer travelposition,Integer type){
		String carText = "";
		String img = "";
		
		if(carstatus==7){	//在线
			img = "<img src='"+ contextpath + "/resource/images/inline_2.png'></img>";
			carText = "<font color='green'>"+carnumber+"(在线)</font>";
		}else if(carstatus==1){	//长时间离线
			img = "<img src='"+ contextpath + "/resource/images/offline_2.png'></img>";
			carText = "<font color='000000'>"+carnumber+"(长时间离线)</font>";
		}else if(carstatus==2){	//离线
			img = "<img src='"+ contextpath + "/resource/images/offline_2.png'></img>";
			carText = "<font color='000000'>"+carnumber+"(离线)</font>";
		}else if(carstatus==3){	//熄火
			img = "<img src='"+ contextpath + "/resource/images/shutdown_2.png'></img>";
			carText = "<font color='1200ff'>"+carnumber+"(熄火)</font>";
		}else if(carstatus==5){	//行驶
			img = "<img src='"+ contextpath + "/resource/images/drivering_2.png'></img>";
			carText = "<font color='green'>"+carnumber+"(行驶)</font>";
		}else if(carstatus==4){	//停车
			img = "<img src='"+ contextpath + "/resource/images/stop_2.png'></img>";
			carText = "<font color='9c9c9c'>"+carnumber+"(停车)</font>";
		}else if(carstatus==6){	//报警
			img = "<img src='"+ contextpath + "/resource/images/warn_2.png'></img>";
			carText = "<font color='red'>"+carnumber+"(报警)</font>";
		}else if(carstatus==8){	//未定位
			img = "<img src='"+ contextpath + "/resource/images/stop_2.png'></img>";
			carText = "<font color='9c9c9c'>"+carnumber+"(未定位)</font>";
		}
		if(type == 0){	//返回不带图片
			return carText;
		}else{	//返回带图片
			return img + carText;
		}

	}
	
}











