/**
* Description: car-eye车辆管理平台
* 文件名：OrgazicationDeptAction.java
* 版本信息：1.0
* 日期：2014-5-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.api.action;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.api.domain.AdvertContentHzp;
import com.careye.api.domain.AdviceFeedBack;
import com.careye.api.domain.ApiKey;
import com.careye.api.domain.AppUserFriends;
import com.careye.api.domain.AppUserInvite;
import com.careye.api.domain.AuthCompanyCode;
import com.careye.api.domain.ComplaintInfo;
import com.careye.api.domain.FriendsList;
import com.careye.api.domain.InviteList;
import com.careye.api.domain.PhoneAuthCode;
import com.careye.api.domain.VersionControl;
import com.careye.api.domain.WeatherInfo;
import com.careye.api.service.ApiService;
import com.careye.api.service.AuthService;
import com.careye.api.service.CommonService;
import com.careye.api.service.TspService;
import com.careye.base.action.BasePageAction;
import com.careye.constant.ConfigProperties;
import com.careye.constant.Constant;
import com.careye.constant.ServiceConfig;
import com.careye.http.BaiDuHttp;
import com.careye.http.GaoDeHttp;
import com.careye.http.domain.BaiDuInfo;
import com.careye.mq.HandleUtil;
import com.careye.taxi.api.service.TaxiService;
import com.careye.taxi.domain.CarInfo;
import com.careye.taxi.domain.MultiMedia;
import com.careye.taxi.domain.Transaction;
import com.careye.utils.DateUtil;
import com.careye.utils.StringUtils;
import com.careye.wx.domain.OrderInfo;

import common.Logger;

/**
 * @项目名称：car-eyeAPI
 * @类名称：AppAction
 * @类描述：桩联网app-api接口提供
 * @创建人：zhangrong
 * @创建时间：2015-9-10 下午02:55:49
 * @修改人：zhangrong
 * @修改时间：2015-9-10 下午02:55:49
 * @修改备注：
 * @version 1.0
 */

@SuppressWarnings("unchecked")
public class AppAction extends BasePageAction {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(AppAction.class);
	
	private TspService tspService;
	private ApiService apiService;
	private CommonService commonService;
	private AuthService authService;
	
	private OrderInfo orderInfo;
	private TaxiService taxiService;
	
	private PhoneAuthCode phoneAuthCode;
	private AdviceFeedBack adviceFeedBack;
	
	private String success;
	private Map jsondata;
	private String ak;  //用户密钥
	private ApiKey apiKey = new ApiKey();
	
	private String id;
	private String phone;
	private String authcode; //短信验证码
	
	private String cityname;//城市名称
	private String citycode;//城市编码
	private String days;//未来天数
	
	private String type;
	
	private String version;  //版本号
	private String content;  //意见反馈内容
	private String typeid;
	
	private String passengerphone;//乘客手机号
	private String passengername;//乘客姓名
	private String driverphone;//司机手机号
	private String carnumber;//车牌号
	private String remark;//投诉描述
	private String orderid;//订单号
	private String terminal;//设备号
	private String lng;
	private String lat;
	
	private String ucar;
	private String vcar;
	private String isagree;
	private String searchtext;
	
	
	private String appid;
	private String secretKey;
	private String imei;
	private String iccid;
	
	private String position;//显示位置 1顶灯广告 2后枕屏广告
	
	private String mdataid;//多媒体数据ID
	private String mtype;//多媒体类型
	private String formatcode;//多媒体格式编码
	private String eventcode;//事件项编码
	private String channelid;//通道ID
	private String speed;//速度
	private String direction;//方向
	private String altitude;//高度
	private String time;//时间
	private String path;//图片路径
	
	private String everypage;
	private String currentpage;
	
	public void initMap() {
		if(jsondata == null) {
			jsondata = new HashMap();
		}
	}
	
	/**
	 * 获取验证码接口
	 * @return
	 */
	public String sendAuthcode(){
		try {
			logger.info("获取验证码接口:ak="+ak+",phone="+phone);
			initMap();
			
			if (ak == null) {
				jsondata.put("status", 4);
				jsondata.put("message", "ak不存在或者非法");
				return SUCCESS;
			} else {
				apiKey.setKey(DateUtil.StringChange(ak));
				apiKey.setTypeid(Constant.LD_APP_TYPE);
				apiKey.setRequestcount(1);// 只查询密钥是启用的
				int count = apiService.apikeyIsExist(apiKey);
				if (count <= 0) {
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}
				// 增加API密钥请求次数
				apiService.updateApiKeyByIdKey(apiKey);
			}
			
			logger.info("开始调用发送短信接口");
			if(StringUtils.isEmty(phone)){
				jsondata.put("status", 4);
				jsondata.put("message", "手机号不存在或者非法");
				return SUCCESS;
			}
			if(phone.length() != 11){
				jsondata.put("status", 4);
				jsondata.put("message", "手机号格式不对");
				return SUCCESS;
			}
			//调用消息队列发送短信
			String random = DateUtil.getFourRandom();
			String content = random;
			//MessageFormat.format(Constant.SEND_AUTHCODE_CONTENT, random);
			
			boolean flag = HandleUtil.sendSms(phone, HandleUtil.getSerialId(), phone, content, 1);
			
			if(flag){
				jsondata.put("status", 0);
				jsondata.put("su", 0);
				jsondata.put("message", "ok");
				
				//插入记录
				phoneAuthCode = new PhoneAuthCode(content,phone,random,DateUtil.getSQLDate());
				commonService.saveAppCode(phoneAuthCode);
				
			}else{
				jsondata.put("status", 0);
				jsondata.put("su", -1);
				jsondata.put("message", "fail");
			}
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			e.printStackTrace();
			return ERROR;
		}finally{
			logger.info(jsondata.toString());
		}
	}
	
	/**
	 * 验证码验证是否在有效期内正确
	 * @return
	 */
	public String verifySmsAuthCode(){
		try {
			initMap();
			logger.info("开始调用验证码验证接口");
			
			if(ak == null){
				jsondata.put("status", 1);
				jsondata.put("message", "ak不存在或者非法");
				return SUCCESS;
			}else{
				apiKey.setKey(DateUtil.StringChange(ak));
				apiKey.setTypeid(Constant.LD_APP_TYPE);
				apiKey.setRequestcount(1);// 只查询密钥是启用的
				int count = apiService.apikeyIsExist(apiKey);
				if (count <= 0) {
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}
				// 增加API密钥请求次数
				apiService.updateApiKeyByIdKey(apiKey);
			}
			
			if(StringUtils.isEmty(phone)){
				jsondata.put("su", -1);
				return SUCCESS;
			}
			if(StringUtils.isEmty(authcode)){
				jsondata.put("su", -2);
				return SUCCESS;
			}
			phoneAuthCode = commonService.queryAuthCodeByPhone(phone);
			if(phoneAuthCode == null || StringUtils.isEmty(phoneAuthCode.getAuthcode()) || !authcode.equals(phoneAuthCode.getAuthcode())){
				jsondata.put("su", 1);
			}else if(DateUtil.dateDiff(DateUtil.getSQLDate(), phoneAuthCode.getCreatetime()) > Constant.LOSE_EFFICACY_TIME){
				jsondata.put("su", 2);
				logger.info("调用验证码验证接口成功，返回结果：不通过");
			}else{
				jsondata.put("su", 0);
				logger.info("调用验证码验证接口成功，返回结果：通过");
			}
			logger.info(jsondata.toString());
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("su", 1);
			logger.info(jsondata.toString());
			e.printStackTrace();
			logger.info("请求验证码验证是否在有效期内正确异常");
			return ERROR;
		}
	}

	/**
	 * 获取手机APP版本信息详情
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String loadNewVersion(){

		logger.info("获取手机APP最新版本信息接口:type="+type+",ak="+ak);
		initMap();
		ApiKey apiKey = new ApiKey();
		try {

			if(ak == null){
				jsondata.put("status", 4);
				jsondata.put("message", "ak不存在或者非法");
				return SUCCESS;
			}else{
				apiKey.setKey(DateUtil.StringChange(ak));
				apiKey.setTypeid(Integer.parseInt(type));
				apiKey.setRequestcount(1);//只查询密钥是启用的
				//查询密钥是否存在
				int count = apiService.apikeyIsExist(apiKey);
				if(count<=0){
					jsondata.put("status", 1);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}
				// 增加API密钥请求次数
				apiService.updateApiKeyByIdKey(apiKey);
			}

			if(type == null || "".equals(type)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数type为空不存在");
				return SUCCESS;
			}

			VersionControl versionControl = apiService.getApiKeyControlInfo(Integer.parseInt(type));
			
			if(versionControl != null){
				versionControl.setDownloadaddr(Constant.PLATFORM_NAME+"/"+""+versionControl.getDownloadaddr());
				
				jsondata.put("status", 0);
				jsondata.put("message", "成功");
				jsondata.put("versionIndex", versionControl.getVersion());
				jsondata.put("versionId", versionControl.getVersionname());
				jsondata.put("uploadFile", versionControl.getDownloadaddr());
				jsondata.put("upgradecontent", StringUtils.isNotEmty(versionControl.getUpgradecontent())?versionControl.getUpgradecontent():"");
				jsondata.put("createTime", versionControl.getCreatetime());
				jsondata.put("type",type);
				
				return SUCCESS;
			}else{
				jsondata.put("status", 8);
				jsondata.put("message", "请求结果不存在");
				return SUCCESS;
			}

		} catch (Exception e) {
			jsondata.put("status", 5);
			jsondata.put("message", "服务器内部错误");
			logger.info("手机APP版本获取信息请求异常");
			e.printStackTrace();
			return ERROR;
		}finally{
			logger.info("返回结果："+jsondata.toString());
		}
	}

	
	/**
	 * 获取意见反馈api接口
	 * @return
	 */
	 public String addAdviceFeedBack(){
			logger.info("获取意见反馈api接口:type="+type+",ak="+ak+",version="+version+",content="+content);
			initMap();
			ApiKey apiKey = new ApiKey();
			try {
				if(ak == null){
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}else{
					apiKey.setKey(DateUtil.StringChange(ak));
					apiKey.setTypeid(Integer.parseInt(type));
					apiKey.setRequestcount(1);//只查询密钥是启用的
					//查询密钥是否存在
					int count = apiService.apikeyIsExist(apiKey);
					if(count<=0){
						jsondata.put("status", 1);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}
					// 增加API密钥请求次数
					apiService.updateApiKeyByIdKey(apiKey);
				}

				if(type == null || "".equals(type)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数type为空不存在");
					return SUCCESS;
				}
				if(version == null || "".equals(version)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数version为空不存在");
					return SUCCESS;
				}
				if(content == null || "".equals(content)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数content为空不存在");
					return SUCCESS;
				}
				
				adviceFeedBack = new AdviceFeedBack();
				adviceFeedBack.setTypeid(Integer.parseInt(type));
				adviceFeedBack.setVersion(version);
				adviceFeedBack.setFeedbackcontent(content);
				adviceFeedBack.setStatus(1);
				
				int result = apiService.addAdviceFeedBack(adviceFeedBack);
				
				if(result > 0){
					
					jsondata.put("status", 0);
					jsondata.put("message", "成功");
					jsondata.put("remark",1);
					
					return SUCCESS;
				}else{
					jsondata.put("status", 8);
					jsondata.put("message", "请求结果不存在");
					return SUCCESS;
				}

			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info("获取意见反馈api接口请求异常");
				e.printStackTrace();
				return ERROR;
			}finally{
				logger.info("返回结果："+jsondata.toString());
			}
		}
	
	
	 /**
	 * 根据typeid获得公司介绍内容api接口
	 * @return
	 */
	 public String getContentByType(){
			logger.info("根据typeid获得公司介绍内容api接口:typeid="+typeid+",ak="+ak);
			initMap();
			ApiKey apiKey = new ApiKey();
			try {
				if(ak == null){
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}else{
					apiKey.setKey(DateUtil.StringChange(ak));
					apiKey.setTypeid(Constant.LD_APP_TYPE);
					apiKey.setRequestcount(1);//只查询密钥是启用的
					//查询密钥是否存在
					int count = apiService.apikeyIsExist(apiKey);
					if(count<=0){
						jsondata.put("status", 1);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}
					// 增加API密钥请求次数
					apiService.updateApiKeyByIdKey(apiKey);
				}

				if(typeid == null || "".equals(typeid)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数typeid为空不存在");
					return SUCCESS;
				}
				
				String content = commonService.getContentByType(Integer.parseInt(typeid));
				
				if(content != null){
					
					jsondata.put("status", 0);
					jsondata.put("message", "成功");
					jsondata.put("content",content);
					
					return SUCCESS;
				}else{
					jsondata.put("status", 8);
					jsondata.put("message", "请求结果不存在");
					return SUCCESS;
				}

			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info("根据typeid获得公司介绍内容api接口请求异常");
				e.printStackTrace();
				return ERROR;
			}finally{
				logger.info("返回结果："+jsondata.toString());
			}
		}
	 
	 
	 /**
		 * 获取天气预报详情API接口
		 * @return
		 */
		public String getWeatherDetails(){
			try {
				logger.info("获取天气预报详情API接口:ak="+ak+",cityname="+cityname);
				initMap();

				if(ak == null){
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}else{
					apiKey.setKey(DateUtil.StringChange(ak));
					apiKey.setTypeid(Constant.LAUNCHER_APP_TYPE);
					apiKey.setRequestcount(1);//只查询密钥是启用的
					//查询密钥是否存在
					int count = apiService.apikeyIsExist(apiKey);
					if(count<=0){
						jsondata.put("status", 1);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}
					// 增加API密钥请求次数
					apiService.updateApiKeyByIdKey(apiKey);
				}
//				cityname = "深圳";
				if(StringUtils.isEmty(cityname)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数cityname不能为空");
					return SUCCESS;
				}
				
				if(StringUtils.isNotEmty(cityname)){
					cityname = cityname.trim();
					if(cityname.endsWith("市") || cityname.endsWith("省")){
						cityname = cityname.trim().substring(0, cityname.trim().length()-1);
					}
				}
				
				Map map = new HashMap();
				map.put("cityname", cityname);
				map.put("datetime", DateUtil.getNowDate());
				
				WeatherInfo today = tspService.getWeatherToday(map);
				if(today != null){
					String type = today.getType();
					
					if("晴".equals(type)){
						today.setTypeimg(0);
					}else if("多云".equals(type)){
						today.setTypeimg(1);
					}else if("阴".equals(type)){
						today.setTypeimg(2);
					}else if("阵雨".equals(type)){
						today.setTypeimg(3);
					}else if("雷阵雨".equals(type)){
						today.setTypeimg(4);
					}else if("雷阵雨伴有冰雹".equals(type)){
						today.setTypeimg(5);
					}else if("雨夹雪".equals(type)){
						today.setTypeimg(6);
					}else if("小雨".equals(type)){
						today.setTypeimg(7);
					}else if("中雨".equals(type)){
						today.setTypeimg(8);
					}else if("大雨".equals(type)){
						today.setTypeimg(9);
					}else if("暴雨".equals(type)){
						today.setTypeimg(10);
					}else if("大暴雨".equals(type)){
						today.setTypeimg(11);
					}else if("特大暴雨".equals(type)){
						today.setTypeimg(12);
					}else if("阵雪".equals(type)){
						today.setTypeimg(13);
					}else if("小雪".equals(type)){
						today.setTypeimg(14);
					}else if("中雪".equals(type)){
						today.setTypeimg(15);
					}else if("大雪".equals(type)){
						today.setTypeimg(16);
					}else if("暴雪".equals(type)){
						today.setTypeimg(17);
					}else if("雾".equals(type)){
						today.setTypeimg(18);
					}else if("冻雨".equals(type)){
						today.setTypeimg(19);
					}else if("沙尘暴".equals(type)){
						today.setTypeimg(20);
					}else if("小到中雨".equals(type)){
						today.setTypeimg(21);
					}else if("中到大雨".equals(type)){
						today.setTypeimg(22);
					}else if("大到暴雨".equals(type)){
						today.setTypeimg(23);
					}else if("暴雨到大暴雨".equals(type)){
						today.setTypeimg(24);
					}else if("大暴雨到特大暴雨".equals(type)){
						today.setTypeimg(25);
					}else if("小到中雪".equals(type)){
						today.setTypeimg(26);
					}else if("中到大雪".equals(type)){
						today.setTypeimg(27);
					}else if("大到暴雪".equals(type)){
						today.setTypeimg(28);
					}else if("浮沉".equals(type)){
						today.setTypeimg(29);
					}else if("扬沙".equals(type)){
						today.setTypeimg(30);
					}else if("强沙尘暴".equals(type)){
						today.setTypeimg(31);
					}else if("霾".equals(type)){
						today.setTypeimg(53);
					}
					
					if(StringUtils.isEmty(today.getCurTemp()) && StringUtils.isNotEmty(today.getLowtemp())){
						Double avg = (Double.parseDouble(today.getHightemp().split("℃")[0])+Double.parseDouble(today.getLowtemp().split("℃")[0]))/2;
						today.setCurTemp((Integer)avg.intValue()+"℃");
					}
				}
				
				jsondata.put("status", 0);
				jsondata.put("message", "成功");
				jsondata.put("data", today);
				if(today != null){
					logger.info("当前℃："+today.getCurTemp()+",最高："+today.getHightemp()+",最低："+today.getLowtemp());
				}else{
					logger.info("未获取到天气");
				}
				return SUCCESS;
			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info("获取天气预报详情API接口异常");
				e.printStackTrace();
				return ERROR;
			}finally{
				logger.info(jsondata.toString());
			}
		}
		
		 /**
		 * 根据经纬度获取天气预报详情API接口
		 * @return
		 */
		public String getWeatherDetailsByLngLat(){
			try {
				logger.info("根据经纬度获取天气预报详情API接口:ak="+ak+",lng="+lng+",lat="+lat);
				initMap();

				if(ak == null){
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}else{
					apiKey.setKey(DateUtil.StringChange(ak));
					apiKey.setTypeid(Constant.LAUNCHER_APP_TYPE);
					apiKey.setRequestcount(1);//只查询密钥是启用的
					//查询密钥是否存在
					int count = apiService.apikeyIsExist(apiKey);
					if(count<=0){
						jsondata.put("status", 1);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}
					// 增加API密钥请求次数
					apiService.updateApiKeyByIdKey(apiKey);
				}
				if(StringUtils.isEmty(lng)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数lng不能为空");
					return SUCCESS;
				}
				if(StringUtils.isEmty(lat)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数lat不能为空");
					return SUCCESS;
				}
				
				WeatherInfo today = null;
				if(Double.parseDouble(lat) >0){
					BaiDuInfo baiDuInfo = BaiDuHttp.getBaiDuInfo(Double.parseDouble(lat), Double.parseDouble(lng));
					if(baiDuInfo != null){
						cityname = baiDuInfo.getCity();
					}
					
					if(StringUtils.isNotEmty(cityname)){
						cityname = cityname.trim();
						if(cityname.endsWith("市") || cityname.endsWith("省")){
							cityname = cityname.trim().substring(0, cityname.trim().length()-1);
						}
					}
					
					Map map = new HashMap();
					map.put("cityname", cityname);
					map.put("datetime", DateUtil.getNowDate());
					today = tspService.getWeatherToday(map);
					if(today != null){
						String type = today.getType();
						
						if("晴".equals(type)){
							today.setTypeimg(0);
						}else if("多云".equals(type)){
							today.setTypeimg(1);
						}else if("阴".equals(type)){
							today.setTypeimg(2);
						}else if("阵雨".equals(type)){
							today.setTypeimg(3);
						}else if("雷阵雨".equals(type)){
							today.setTypeimg(4);
						}else if("雷阵雨伴有冰雹".equals(type)){
							today.setTypeimg(5);
						}else if("雨夹雪".equals(type)){
							today.setTypeimg(6);
						}else if("小雨".equals(type)){
							today.setTypeimg(7);
						}else if("中雨".equals(type)){
							today.setTypeimg(8);
						}else if("大雨".equals(type)){
							today.setTypeimg(9);
						}else if("暴雨".equals(type)){
							today.setTypeimg(10);
						}else if("大暴雨".equals(type)){
							today.setTypeimg(11);
						}else if("特大暴雨".equals(type)){
							today.setTypeimg(12);
						}else if("阵雪".equals(type)){
							today.setTypeimg(13);
						}else if("小雪".equals(type)){
							today.setTypeimg(14);
						}else if("中雪".equals(type)){
							today.setTypeimg(15);
						}else if("大雪".equals(type)){
							today.setTypeimg(16);
						}else if("暴雪".equals(type)){
							today.setTypeimg(17);
						}else if("雾".equals(type)){
							today.setTypeimg(18);
						}else if("冻雨".equals(type)){
							today.setTypeimg(19);
						}else if("沙尘暴".equals(type)){
							today.setTypeimg(20);
						}else if("小到中雨".equals(type)){
							today.setTypeimg(21);
						}else if("中到大雨".equals(type)){
							today.setTypeimg(22);
						}else if("大到暴雨".equals(type)){
							today.setTypeimg(23);
						}else if("暴雨到大暴雨".equals(type)){
							today.setTypeimg(24);
						}else if("大暴雨到特大暴雨".equals(type)){
							today.setTypeimg(25);
						}else if("小到中雪".equals(type)){
							today.setTypeimg(26);
						}else if("中到大雪".equals(type)){
							today.setTypeimg(27);
						}else if("大到暴雪".equals(type)){
							today.setTypeimg(28);
						}else if("浮沉".equals(type)){
							today.setTypeimg(29);
						}else if("扬沙".equals(type)){
							today.setTypeimg(30);
						}else if("强沙尘暴".equals(type)){
							today.setTypeimg(31);
						}else if("霾".equals(type)){
							today.setTypeimg(53);
						}
						
						if(StringUtils.isEmty(today.getCurTemp()) && StringUtils.isNotEmty(today.getLowtemp())){
							Double avg = (Double.parseDouble(today.getHightemp().split("℃")[0])+Double.parseDouble(today.getLowtemp().split("℃")[0]))/2;
							today.setCurTemp((Integer)avg.intValue()+"℃");
						}
					}
				}
				
				jsondata.put("status", 0);
				jsondata.put("message", "成功");
				jsondata.put("data", today);
				if(today != null){
					logger.info("当前℃："+today.getCurTemp()+",最高："+today.getHightemp()+",最低："+today.getLowtemp());
				}else{
					logger.info("未获取到天气");
				}
				return SUCCESS;
			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info("根据经纬度获取天气预报详情API接口异常");
				e.printStackTrace();
				return ERROR;
			}finally{
				logger.info(jsondata.toString());
			}
		}
		
		
		/**
		 * 获取投诉类型列表API接口
		 * @return
		 */
		public String getComplaintTypeList(){
			try {
				logger.info("获取投诉类型列表API接口:ak="+ak);
				initMap();

				if(ak == null){
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}else{
					apiKey.setKey(DateUtil.StringChange(ak));
					apiKey.setTypeid(Constant.LD_APP_TYPE);
					apiKey.setRequestcount(1);//只查询密钥是启用的
					//查询密钥是否存在
					int count = apiService.apikeyIsExist(apiKey);
					if(count<=0){
						jsondata.put("status", 1);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}
					// 增加API密钥请求次数
					apiService.updateApiKeyByIdKey(apiKey);
				}
				
				jsondata.put("status", 0);
				jsondata.put("message", "成功");
				jsondata.put("list", apiService.getComplaintTypeList());
				return SUCCESS;
			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info("获取投诉类型列表API接口异常");
				e.printStackTrace();
				return ERROR;
			}finally{
				logger.info(jsondata.toString());
			}
		}
		
		/**
		 * 投诉API接口
		 * @return
		 */
		public String saveComplaintInfo(){
			try {
				logger.info("投诉API接口:ak="+ak+",phone="+phone+",passengerphone="+passengerphone+",passengername="+passengername
						+",driverphone="+driverphone+",carnumber="+carnumber+",type="+type+",remark="+remark+",orderid="+orderid);
				initMap();

				if(ak == null){
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}else{
					apiKey.setKey(DateUtil.StringChange(ak));
					apiKey.setTypeid(Constant.LD_APP_TYPE);
					apiKey.setRequestcount(1);//只查询密钥是启用的
					//查询密钥是否存在
					int count = apiService.apikeyIsExist(apiKey);
					if(count<=0){
						jsondata.put("status", 1);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}
					// 增加API密钥请求次数
					apiService.updateApiKeyByIdKey(apiKey);
				}
				
				if(StringUtils.isEmty(phone)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数phone不能为空");
					return SUCCESS;
				}
				if(StringUtils.isEmty(passengerphone)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数passengerphone不能为空");
					return SUCCESS;
				}
				if(StringUtils.isEmty(passengername)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数passengername不能为空");
					return SUCCESS;
				}
				if(StringUtils.isEmty(driverphone)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数driverphone不能为空");
					return SUCCESS;
				}
				if(StringUtils.isEmty(carnumber)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数carnumber不能为空");
					return SUCCESS;
				}
				if(StringUtils.isEmty(type)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数type不能为空");
					return SUCCESS;
				}
				
				ComplaintInfo complaintInfo = new ComplaintInfo();
				complaintInfo.setPhone(phone);
				complaintInfo.setPassengerphone(passengerphone);
				complaintInfo.setPassengername(passengername);
				complaintInfo.setDriverphone(driverphone);
				complaintInfo.setCarnumber(carnumber);
				complaintInfo.setType(type);
				complaintInfo.setRemark(remark);
				complaintInfo.setOrderid(orderid);
				complaintInfo.setDealstatus(1);
				complaintInfo.setComplainttime(DateUtil.getSQLDate());
				
				apiService.addComplaintInfo(complaintInfo);
				
				Transaction transaction = taxiService.getOrderdetail(orderid);
				if(transaction != null && transaction.getStatus() != null && transaction.getStatus()>4){
					transaction.setStatus(8);
					transaction.setIscomplaint(1);
					taxiService.updateTransactionStatus(transaction);
					orderInfo = new OrderInfo();
					orderInfo.setSourcetype(2);
					orderInfo.setOrderid(orderid);
					orderInfo.setOrderstatus(8);
					orderInfo.setIscomplaint(1);
					ServiceConfig.wxService.updateOrderStatus(orderInfo);
				}
				
				jsondata.put("status", 0);
				jsondata.put("message", "成功");
				return SUCCESS;
			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info("获取投诉类型列表API接口异常");
				e.printStackTrace();
				return ERROR;
			}finally{
				logger.info(jsondata.toString());
			}
		}
		
		/**
		 * 获取投诉列表API接口
		 * @return
		 */
		public String getComplaintInfoList(){
			try {
				logger.info("获取投诉列表API接口:ak="+ak+",phone="+phone);
				initMap();

				if(ak == null){
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}else{
					apiKey.setKey(DateUtil.StringChange(ak));
					apiKey.setTypeid(Constant.LD_APP_TYPE);
					apiKey.setRequestcount(1);//只查询密钥是启用的
					//查询密钥是否存在
					int count = apiService.apikeyIsExist(apiKey);
					if(count<=0){
						jsondata.put("status", 1);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}
					// 增加API密钥请求次数
					apiService.updateApiKeyByIdKey(apiKey);
				}
				
				if(StringUtils.isEmty(phone)){
					jsondata.put("status", 4);
					jsondata.put("message", "phone不能为空");
					return SUCCESS;
				}
				if(StringUtils.isEmty(everypage)){
					jsondata.put("status", 4);
					jsondata.put("message", "everypage不能为空");
					return SUCCESS;
				}
				if(StringUtils.isEmty(currentpage)){
					jsondata.put("status", 4);
					jsondata.put("message", "currentpage不能为空");
					return SUCCESS;
				}
				
				Map comMap = new HashMap();
				comMap =  apiService.getComplaintInfoList(Integer.parseInt(currentpage), Integer.parseInt(everypage), phone);
				int totalCount = (Integer) comMap.get("totalCount");
				int totalPages = (int) Math.floor((double)totalCount/Integer.parseInt(everypage))+1; 
				if((totalPages-1) * Integer.parseInt(everypage) == totalCount){
					totalPages -= 1;
				}
				jsondata.put("list", comMap.get("list"));
				jsondata.put("totalCount", totalCount);
				jsondata.put("totalPages", totalPages);
				
				jsondata.put("status", 0);
				jsondata.put("message", "成功");
				return SUCCESS;
			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info("获取投诉列表API接口异常");
				e.printStackTrace();
				return ERROR;
			}finally{
				logger.info(jsondata.toString());
			}
		}
		
		/**
		 * 获取投诉详情API接口
		 * @return
		 */
		public String getComplaintInfo(){
			try {
				logger.info("获取投诉详情API接口:ak="+ak+",id="+id);
				initMap();

				if(ak == null){
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}else{
					apiKey.setKey(DateUtil.StringChange(ak));
					apiKey.setTypeid(Constant.LD_APP_TYPE);
					apiKey.setRequestcount(1);//只查询密钥是启用的
					//查询密钥是否存在
					int count = apiService.apikeyIsExist(apiKey);
					if(count<=0){
						jsondata.put("status", 1);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}
					// 增加API密钥请求次数
					apiService.updateApiKeyByIdKey(apiKey);
				}
				
				if(StringUtils.isEmty(id)){
					jsondata.put("status", 4);
					jsondata.put("message", "id不能为空");
					return SUCCESS;
				}
				
				jsondata.put("status", 0);
				jsondata.put("message", "成功");
				jsondata.put("data", apiService.getComplaintInfo(id));
				return SUCCESS;
			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info("获取投诉详情API接口异常");
				e.printStackTrace();
				return ERROR;
			}finally{
				logger.info(jsondata.toString());
			}
		}
	
		/**
		 * 获取广告API接口
		 * @return
		 */
		public String getAdvertContentList(){
			try {
				logger.info("获取广告API接口:ak="+ak+",position="+position);
				initMap();

				if(ak == null){
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}else{
					apiKey.setKey(DateUtil.StringChange(ak));
					apiKey.setRequestcount(1);//只查询密钥是启用的
					//查询密钥是否存在
					int count = apiService.apikeyIsExist(apiKey);
					if(count<=0){
						jsondata.put("status", 1);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}
					// 增加API密钥请求次数
					apiService.updateApiKeyByIdKey(apiKey);
				}
				
				if(StringUtils.isEmty(position)){
					jsondata.put("status", 4);
					jsondata.put("message", "position不能为空");
					return SUCCESS;
				}
				String positionid = null;
				if("1".equals(position)){
					positionid = "21";//顶灯广告
					
					List ddList = apiService.getAdvertContentDdList(positionid);
					jsondata.put("list", ddList);
					if(ddList != null){
						jsondata.put("count", ddList.size());
					}else{
						jsondata.put("count", 0);
					}
					jsondata.put("verid", apiService.getVeridByPositionid(positionid));
				}else if("2".equals(position)){
					positionid = "22";//后枕屏广告
					
					List<AdvertContentHzp> hzpList = apiService.getAdvertContentHzpList(positionid);
					if(hzpList !=null){
						for(AdvertContentHzp info : hzpList){
							if(StringUtils.isNotEmty(info.getPath())){
								info.setPath(Constant.PLATFORM_NAME+info.getPath());
							}
						}
						jsondata.put("count", hzpList.size());
					}else{
						jsondata.put("count", 0);
					}
					jsondata.put("list", hzpList);
					jsondata.put("verid", apiService.getVeridByPositionid(positionid));
				}else if("3".equals(position)){
					positionid = "23";//主机广告
					//主机广告的结果参数与后枕屏一样
					List<AdvertContentHzp> hzpList = apiService.getAdvertContentHzpList(positionid);
					if(hzpList !=null){
						for(AdvertContentHzp info : hzpList){
							if(StringUtils.isNotEmty(info.getPath())){
								info.setPath(Constant.PLATFORM_NAME+info.getPath());
							}
						}
						jsondata.put("count", hzpList.size());
					}else{
						jsondata.put("count", 0);
					}
					jsondata.put("list", hzpList);
					jsondata.put("verid", apiService.getVeridByPositionid(positionid));
				}
				
				jsondata.put("status", 0);
				jsondata.put("message", "成功");
				return SUCCESS;
			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info("获取广告API接口异常");
				e.printStackTrace();
				return ERROR;
			}finally{
				logger.info(jsondata.toString());
			}
		}
	
		/**
		 * 根据设备号获取当前里程API接口
		 * @return
		 */
		public String getNowMileByTerminal(){
			try {
				logger.info("根据设备号获取当前里程API接口:ak="+ak+",terminal="+terminal);
				initMap();

				if(ak == null){
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}else{
					apiKey.setKey(DateUtil.StringChange(ak));
					apiKey.setTypeid(Constant.LD_APP_TYPE);
					apiKey.setRequestcount(1);//只查询密钥是启用的
					//查询密钥是否存在
					int count = apiService.apikeyIsExist(apiKey);
					if(count<=0){
						jsondata.put("status", 1);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}
					// 增加API密钥请求次数
					apiService.updateApiKeyByIdKey(apiKey);
				}
				
				if(StringUtils.isEmty(terminal)){
					jsondata.put("status", 4);
					jsondata.put("message", "terminal不能为空");
					return SUCCESS;
				}
				
				jsondata.put("status", 0);
				jsondata.put("message", "成功");
				jsondata.put("mile", apiService.getNowMileByTerminal(terminal));
				return SUCCESS;
			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info("根据设备号获取当前里程API接口异常");
				e.printStackTrace();
				return ERROR;
			}finally{
				logger.info(jsondata.toString());
			}
		}
		
		/**
		 * 添加邀请信息api接口
		 * @return
		 */
		 public String saveAppUserInvite(){
				logger.info("添加邀请信息api接口:ak="+ak+",ucar="+ucar+",vcar="+vcar+",remark="+remark);
				initMap();
				ApiKey apiKey = new ApiKey();
				try {
					if(ak == null){
						jsondata.put("status", 4);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}else{
						apiKey.setKey(DateUtil.StringChange(ak));
						apiKey.setRequestcount(1);//只查询密钥是启用的
						//查询密钥是否存在
						int count = apiService.apikeyIsExist(apiKey);
						if(count<=0){
							jsondata.put("status", 1);
							jsondata.put("message", "ak不存在或者非法");
							return SUCCESS;
						}
						// 增加API密钥请求次数
						apiService.updateApiKeyByIdKey(apiKey);
					}

					if(ucar == null || "".equals(ucar)){
						jsondata.put("status", 4);
						jsondata.put("message", "必填参数ucar为空不存在");
						return SUCCESS;
					}
					if(vcar == null || "".equals(vcar)){
						jsondata.put("status", 4);
						jsondata.put("message", "必填参数vcar为空不存在");
						return SUCCESS;
					}
					String userid = apiService.getCaridByCarnumber(ucar);
					if(userid == null){
						jsondata.put("status", 4);
						jsondata.put("message", "必填参数ucar不存在于数据库");
						return SUCCESS;
					}
					String vid = apiService.getCaridByCarnumber(vcar);
					if(vid == null){
						jsondata.put("status", 4);
						jsondata.put("message", "必填参数vcar不存在于数据库");
						return SUCCESS;
					}
					
					AppUserInvite appUserInvite = new AppUserInvite();
					appUserInvite.setUserid(Integer.parseInt(userid));
					appUserInvite.setVid(Integer.parseInt(vid));
					appUserInvite.setRemark(remark);
					appUserInvite.setInvitetime(DateUtil.getSQLDate());
					//添加
					apiService.addAppUserInvite(appUserInvite);
					
					jsondata.put("status", 0);
					jsondata.put("message", "成功");
						
					return SUCCESS;
				} catch (Exception e) {
					jsondata.put("status", 5);
					jsondata.put("message", "服务器内部错误");
					logger.info("添加邀请信息api接口请求异常");
					e.printStackTrace();
					return ERROR;
				}finally{
					logger.info("返回结果："+jsondata.toString());
				}
			}

		 /**
			 * 同意/拒绝邀请api接口
			 * @return
			 */
			 public String updateAppUserInvite(){
					logger.info("同意/拒绝邀请信息api接口:ak="+ak+",ucar="+ucar
							+",vcar="+vcar+",isagree="+isagree);
					initMap();
					ApiKey apiKey = new ApiKey();
					try {
						if(ak == null){
							jsondata.put("status", 4);
							jsondata.put("message", "ak不存在或者非法");
							return SUCCESS;
						}else{
							apiKey.setKey(DateUtil.StringChange(ak));
							apiKey.setRequestcount(1);//只查询密钥是启用的
							//查询密钥是否存在
							int count = apiService.apikeyIsExist(apiKey);
							if(count<=0){
								jsondata.put("status", 1);
								jsondata.put("message", "ak不存在或者非法");
								return SUCCESS;
							}
							// 增加API密钥请求次数
							apiService.updateApiKeyByIdKey(apiKey);
						}

						if(ucar == null || "".equals(ucar)){
							jsondata.put("status", 4);
							jsondata.put("message", "必填参数ucar为空不存在");
							return SUCCESS;
						}
						if(vcar == null || "".equals(vcar)){
							jsondata.put("status", 4);
							jsondata.put("message", "必填参数vcar为空不存在");
							return SUCCESS;
						}
						if(isagree == null || "".equals(isagree)){
							jsondata.put("status", 4);
							jsondata.put("message", "必填参数isagree为空不存在");
							return SUCCESS;
						}
						
						String userid = apiService.getCaridByCarnumber(ucar);
						if(userid == null){
							jsondata.put("status", 4);
							jsondata.put("message", "必填参数ucar不存在于数据库");
							return SUCCESS;
						}
						String vid = apiService.getCaridByCarnumber(vcar);
						if(vid == null){
							jsondata.put("status", 4);
							jsondata.put("message", "必填参数vcar不存在于数据库");
							return SUCCESS;
						}
						
							AppUserInvite appUserInvite = new AppUserInvite();
							appUserInvite.setUserid(Integer.parseInt(userid));
							appUserInvite.setVid(Integer.parseInt(vid));
							appUserInvite.setAgreedtime(DateUtil.getSQLDate());
							appUserInvite.setIsagree(Integer.parseInt(isagree));
							
							//修改
							apiService.updateAppUserInvite(appUserInvite);
							
							if("2".equals(isagree)){
								jsondata.put("status", 0);
								jsondata.put("message", "成功");
							}else if("1".equals(isagree)){
									AppUserFriends friendsInfo = new AppUserFriends();
									friendsInfo.setUserid(Integer.parseInt(userid));
									friendsInfo.setVid(Integer.parseInt(vid));
									friendsInfo.setCreatetime(DateUtil.getSQLDate());
									
									AppUserFriends friendsInfo2 = new AppUserFriends();
									friendsInfo2.setUserid(Integer.parseInt(vid));
									friendsInfo2.setVid(Integer.parseInt(userid));
									friendsInfo2.setCreatetime(DateUtil.getSQLDate());
									
									int friendCount = apiService.isExistFriends(friendsInfo);
									int friendCount2 = apiService.isExistFriends(friendsInfo2);
									if(friendCount > 0 && friendCount2 > 0){
										jsondata.put("status", 9);
										jsondata.put("message", "好友已存在");
									}else{
										if(friendCount <= 0){
											apiService.addAppUserFriends(friendsInfo);
										}
										if(friendCount2 <= 0){
											apiService.addAppUserFriends(friendsInfo2);
										}
										
										jsondata.put("status", 0);
										jsondata.put("message", "成功");
									}
							}
						
						return SUCCESS;
					} catch (Exception e) {
						jsondata.put("status", 5);
						jsondata.put("message", "服务器内部错误");
						logger.info("同意/拒绝邀请信息api接口请求异常");
						e.printStackTrace();
						return ERROR;
					}finally{
						logger.info("返回结果："+jsondata.toString());
					}
				}
			 
			 /**
				 * 删除车友api接口
				 * @return
				 */
				 public String deleteAppUserFriends(){
						logger.info("删除车友api接口:ak="+ak+",ucar="+ucar+",vcar="+vcar);
						initMap();
						ApiKey apiKey = new ApiKey();
						try {
							if(ak == null){
								jsondata.put("status", 4);
								jsondata.put("message", "ak不存在或者非法");
								return SUCCESS;
							}else{
								apiKey.setKey(DateUtil.StringChange(ak));
								apiKey.setRequestcount(1);//只查询密钥是启用的
								//查询密钥是否存在
								int count = apiService.apikeyIsExist(apiKey);
								if(count<=0){
									jsondata.put("status", 1);
									jsondata.put("message", "ak不存在或者非法");
									return SUCCESS;
								}
								// 增加API密钥请求次数
								apiService.updateApiKeyByIdKey(apiKey);
							}
							
							if(ucar == null || "".equals(ucar)){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数ucar为空不存在");
								return SUCCESS;
							}
							if(vcar == null || "".equals(vcar)){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数vcar为空不存在");
								return SUCCESS;
							}

							String userid = apiService.getCaridByCarnumber(ucar);
							if(userid == null){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数ucar不存在于数据库");
								return SUCCESS;
							}
							String vid = apiService.getCaridByCarnumber(vcar);
							if(vid == null){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数vcar不存在于数据库");
								return SUCCESS;
							}
							
							AppUserFriends appUserFriends = new AppUserFriends();
							appUserFriends.setUserid(Integer.parseInt(userid));
							appUserFriends.setVid(Integer.parseInt(vid));
							
							apiService.deleteAppUserFriends(appUserFriends);
							
							jsondata.put("status", 0);
							jsondata.put("message", "成功");
								
							return SUCCESS;
						} catch (Exception e) {
							jsondata.put("status", 5);
							jsondata.put("message", "服务器内部错误");
							logger.info("删除车友api接口请求异常");
							e.printStackTrace();
							return ERROR;
						}finally{
							logger.info("返回结果："+jsondata.toString());
						}
					}
			 
			 /**
				 * 邀请信息列表api接口
				 * @return
				 */
				 public String getAppUserInviteList(){
						logger.info("邀请信息列表api接口:ak="+ak+",ucar="+ucar);
						initMap();
						ApiKey apiKey = new ApiKey();
						try {
							if(ak == null){
								jsondata.put("status", 4);
								jsondata.put("message", "ak不存在或者非法");
								return SUCCESS;
							}else{
								apiKey.setKey(DateUtil.StringChange(ak));
								apiKey.setRequestcount(1);//只查询密钥是启用的
								//查询密钥是否存在
								int count = apiService.apikeyIsExist(apiKey);
								if(count<=0){
									jsondata.put("status", 1);
									jsondata.put("message", "ak不存在或者非法");
									return SUCCESS;
								}
								// 增加API密钥请求次数
								apiService.updateApiKeyByIdKey(apiKey);
							}

							if(ucar == null || "".equals(ucar)){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数ucar为空不存在");
								return SUCCESS;
							}
							
							String userid = apiService.getCaridByCarnumber(ucar);
							if(userid == null){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数ucar不存在于数据库");
								return SUCCESS;
							}
							
							Map map = new HashMap();
							map.put("userid", userid);
							//添加
							List<InviteList> list = apiService.getAppUserInviteList(map);
							
							jsondata.put("data", list);
							jsondata.put("status", 0);
							jsondata.put("message", "成功");
								
							return SUCCESS;
						} catch (Exception e) {
							jsondata.put("status", 5);
							jsondata.put("message", "服务器内部错误");
							logger.info("邀请信息列表api接口请求异常");
							e.printStackTrace();
							return ERROR;
						}finally{
							logger.info("返回结果："+jsondata.toString());
						}
					}

	/**
	 * 获取车友列表api接口
	 * @return
	 */
	public String getAppUserFriendsList(){
							logger.info("获取车友列表api接口:ak="+ak+",ucar="+ucar+",searchtext="+searchtext
									+",currentpage="+currentpage+",everypage="+everypage);
							initMap();
							ApiKey apiKey = new ApiKey();
							try {
								if(ak == null){
									jsondata.put("status", 4);
									jsondata.put("message", "ak不存在或者非法");
									return SUCCESS;
								}else{
									apiKey.setKey(DateUtil.StringChange(ak));
									apiKey.setRequestcount(1);//只查询密钥是启用的
									//查询密钥是否存在
									int count = apiService.apikeyIsExist(apiKey);
									if(count<=0){
										jsondata.put("status", 1);
										jsondata.put("message", "ak不存在或者非法");
										return SUCCESS;
									}
									// 增加API密钥请求次数
									apiService.updateApiKeyByIdKey(apiKey);
								}
								
								if(ucar == null || "".equals(ucar)){
									jsondata.put("status", 4);
									jsondata.put("message", "必填参数ucar为空不存在");
									return SUCCESS;
								}
								if(currentpage == null || "".equals(currentpage)){
									jsondata.put("status", 4);
									jsondata.put("message", "必填参数currentpage为空不存在");
									return SUCCESS;
								}
								if(everypage == null || "".equals(everypage)){
									jsondata.put("status", 4);
									jsondata.put("message", "必填参数everypage为空不存在");
									return SUCCESS;
								}
								
								String userid = apiService.getCaridByCarnumber(ucar);
								if(userid == null){
									jsondata.put("status", 4);
									jsondata.put("message", "必填参数ucar不存在于数据库");
									return SUCCESS;
								}
								
								Map map = new HashMap();
								map.put("userid", userid);
								map.put("searchtext", searchtext);
								
								Map resultMap = new HashMap();
								resultMap = apiService.getAppUserFriendsList(map,Integer.parseInt(currentpage), Integer.parseInt(everypage));
								
								Integer totalCount = (Integer) resultMap.get("totalCount");
								if(totalCount == null){
									totalCount = 0;
								}
								int totalPages = (int) Math.floor((double)totalCount/Integer.parseInt(everypage))+1;
								if((totalPages-1) * Integer.parseInt(everypage) == totalCount){
									totalPages -= 1;
								}
								
								List<FriendsList> list =  (List<FriendsList>) resultMap.get("list");
								
								jsondata.put("totalCount", totalCount);
								jsondata.put("totalPages", totalPages);
								jsondata.put("data", list);
								
								jsondata.put("status", 0);
								jsondata.put("message", "成功");
									
								return SUCCESS;
							} catch (Exception e) {
								jsondata.put("status", 5);
								jsondata.put("message", "服务器内部错误");
								logger.info("获取车友列表api接口请求异常");
								e.printStackTrace();
								return ERROR;
							}finally{
								logger.info("返回结果："+jsondata.toString());
							}
	}
	
	/**
	 * 设备图片上传API接口
	 * @return
	 */
	public String imgUploadTer(){
		try {
			logger.info("设备图片上传API接口:ak="+ak+",terminal="+terminal+",mdataid="+mdataid+",mtype="+mtype
					+",formatcode="+formatcode+",eventcode="+eventcode+",channelid="+channelid+",speed="+speed
					+",direction="+direction+",altitude="+altitude+",time="+time+",path="+path
					+",lng="+lng+",lat="+lat);
			initMap();

			if(ak == null){
				jsondata.put("status", 4);
				jsondata.put("message", "ak不存在或者非法");
				return SUCCESS;
			}else{
				apiKey.setKey(DateUtil.StringChange(ak));
				apiKey.setRequestcount(1);//只查询密钥是启用的
				//查询密钥是否存在
				int count = apiService.apikeyIsExist(apiKey);
				if(count<=0){
					jsondata.put("status", 1);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}
				// 增加API密钥请求次数
				apiService.updateApiKeyByIdKey(apiKey);
			}
			
			if(StringUtils.isEmty(terminal)){
				jsondata.put("status", 4);
				jsondata.put("message", "terminal不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(mdataid)){
				jsondata.put("status", 4);
				jsondata.put("message", "mdataid不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(mtype)){
				jsondata.put("status", 4);
				jsondata.put("message", "mtype不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(formatcode)){
				jsondata.put("status", 4);
				jsondata.put("message", "formatcode不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(eventcode)){
				jsondata.put("status", 4);
				jsondata.put("message", "eventcode不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(channelid)){
				jsondata.put("status", 4);
				jsondata.put("message", "channelid不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(speed)){
				jsondata.put("status", 4);
				jsondata.put("message", "speed不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(direction)){
				jsondata.put("status", 4);
				jsondata.put("message", "direction不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(altitude)){
				jsondata.put("status", 4);
				jsondata.put("message", "altitude不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(time)){
				jsondata.put("status", 4);
				jsondata.put("message", "time不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(path)){
				jsondata.put("status", 4);
				jsondata.put("message", "path不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(lng)){
				jsondata.put("status", 4);
				jsondata.put("message", "lng不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(lat)){
				jsondata.put("status", 4);
				jsondata.put("message", "lat不能为空");
				return SUCCESS;
			}
			
			Map paramsMap = new HashMap();
			paramsMap.put("terminal", terminal);
			paramsMap.put("devicetype", Constant.DEVICE_TYPE);
			CarInfo carInfo = taxiService.getCarInfo(paramsMap);
			if(carInfo == null){
				jsondata.put("status", 8);
				jsondata.put("message", "该设备在数据库中不存在");
				return SUCCESS;
			}
			
			//保存多媒体数据
			MultiMedia multiMedia = new MultiMedia();
			multiMedia.setAccessid(Integer.parseInt(channelid));
			multiMedia.setBlocid(carInfo.getBlocid());
			multiMedia.setTerminal(terminal);
			multiMedia.setCarid(carInfo.getId());
			multiMedia.setDataid(Integer.parseInt(mdataid));
			multiMedia.setMediatype(Integer.parseInt(mtype));
			multiMedia.setFormat(Integer.parseInt(formatcode));
			multiMedia.setEventcode(Integer.parseInt(eventcode));
			multiMedia.setMultimediapath(Constant.FTP_IP_PIC +'/'+ path);
			multiMedia.setCreatetime(DateUtil.getSQLDate());
			multiMedia.setAltitude(altitude);
			multiMedia.setSpeed(speed);
			multiMedia.setDirection(direction);
			multiMedia.setGpstime(DateUtil.numToDate(time));
			
			double dlng = new BigDecimal(lng).doubleValue() / 1000000;
			double dlat = new BigDecimal(lat).doubleValue() / 1000000;
			BaiDuInfo baiDuInfo = null;
			
			int maptype = Integer.parseInt(ConfigProperties.MAP_TYPE);
			if(maptype == 1){//百度解析经纬度
				//调用百度接口获取经纬度信息
				baiDuInfo = BaiDuHttp.getBaiDuInfo(dlat, dlng);
			}else{//高德地图解析经纬度(为了方便，仍用百度的实体类)
				baiDuInfo = GaoDeHttp.gpsLngLatForGaoDe(dlng, dlat);
			}
			if(baiDuInfo != null){
				multiMedia.setBlat(baiDuInfo.getBlat());
				multiMedia.setBlng(baiDuInfo.getBlng());
				multiMedia.setAddress(baiDuInfo.getAddress());
			}
			
			
			taxiService.addMultiMedia(multiMedia);
			
			jsondata.put("status", 0);
			jsondata.put("message", "成功");
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			jsondata.put("message", "服务器内部错误");
			logger.info("设备图片上传API接口异常");
			e.printStackTrace();
			return ERROR;
		}finally{
			logger.info(jsondata.toString());
		}
	}	
	
	
					 
	 /**
	 * 鉴权码验证api接口
	 * @return
	 */
	
	 
	 public String queryAuthCode(){
		logger.info("验证鉴权码api接口:ak="+ak+",appid="+appid+",secretKey="+secretKey+",authcode="+authcode+",imei="+imei+",iccid="+iccid);
		initMap();
		ApiKey apiKey = new ApiKey();
		try {
			if(ak == null){
				jsondata.put("status", 4);
				jsondata.put("message", "ak不存在或者非法");
				return SUCCESS;
			}else{
				apiKey.setKey(DateUtil.StringChange(ak));
				apiKey.setRequestcount(1);//只查询密钥是启用的
				//查询密钥是否存在
				int count = apiService.apikeyIsExist(apiKey);
				if(count<=0){
					jsondata.put("status", 1);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}
				// 增加API密钥请求次数
				apiService.updateApiKeyByIdKey(apiKey);
			}
			
			if(appid == null || "".equals(appid)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数appid不能为空！");
				return SUCCESS;
			}
			if(secretKey == null || "".equals(secretKey)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数secretKey不能为空！");
				return SUCCESS;
			}
			if(authcode == null || "".equals(authcode)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数authcode不能为空！");
				return SUCCESS;
			}
			if(imei == null || "".equals(imei)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数imei不能为空！");
				return SUCCESS;
			}
//			if(iccid == null || "".equals(iccid)){
//				jsondata.put("status", 4);
//				jsondata.put("message", "必填参数iccid为空不存在");
//				return SUCCESS;
//			}
			
			Map map = new HashMap();
			map.put("appid", appid);
			map.put("secretKey", secretKey);
			map.put("authcode", authcode);
			
			AuthCompanyCode authCompanyCode = new AuthCompanyCode();
			// 鉴权码是否存在
			authCompanyCode = authService.authcodeIsExistByAuthCode(map);
			if(authCompanyCode == null){
				jsondata.put("status", 8);
				jsondata.put("message", "鉴权码不存在！");
				return SUCCESS;
			}else{
				if(authCompanyCode.getStatus().equals("2")){
					Map map0 = new HashMap();
					map0.put("imei", imei);
					map0.put("authcode", authcode);
					map0.put("companyid", authCompanyCode.getCompanyid());
					
					Integer count0 = authService.imeiAndIccidIsExist(map0);
					if(count0 > 0){
						
						Map map1 = new HashMap();
						map1.put("id", authCompanyCode.getId());
						map1.put("authcode", authCompanyCode.getAuthcode());
						map1.put("status", "2");
						map1.put("imei", imei);
						map1.put("iccid", iccid);
						map1.put("usetime", DateUtil.getSQLDate());
						
						Integer updateInt = authService.updateAuthCodeStatus(map1);
						if(updateInt > 0 ){
							jsondata.put("status", 0);
							jsondata.put("message", "鉴权成功！");
							return SUCCESS;
						}else{
							jsondata.put("status", 6);
							jsondata.put("message", "鉴权失败！");
							return SUCCESS;
						}
						
					}else{
						jsondata.put("status", 9);
						jsondata.put("message", "鉴权码已被使用！");
						return SUCCESS;
					}
				}else{
					Map map1 = new HashMap();
					map1.put("id", authCompanyCode.getId());
					map1.put("authcode", authCompanyCode.getAuthcode());
					map1.put("status", "2");
					map1.put("imei", imei);
					map1.put("iccid", iccid);
					map1.put("usetime", DateUtil.getSQLDate());
					
					Integer updateInt = authService.updateAuthCodeStatus(map1);
					if(updateInt > 0 ){
						jsondata.put("status", 0);
						jsondata.put("message", "鉴权成功！");
						return SUCCESS;
					}else{
						jsondata.put("status", 6);
						jsondata.put("message", "鉴权失败！");
						return SUCCESS;
					}
				}
				
			}
			
		} catch (Exception e) {
			jsondata.put("status", 5);
			jsondata.put("message", "服务器内部错误");
			logger.info("验证鉴权码api接口请求异常");
			e.printStackTrace();
			return ERROR;
		}finally{
			logger.info("返回结果："+jsondata.toString());
		}
	}					 
		
	 
	public String getSuccess() {
		return success;
	}


	public void setSuccess(String success) {
		this.success = success;
	}


	public Map getJsondata() {
		return jsondata;
	}


	public void setJsondata(Map jsondata) {
		this.jsondata = jsondata;
	}


	public String getAk() {
		return ak;
	}


	public void setAk(String ak) {
		this.ak = ak;
	}


	public ApiKey getApiKey() {
		return apiKey;
	}

	public void setApiKey(ApiKey apiKey) {
		this.apiKey = apiKey;
	}

	public PhoneAuthCode getPhoneAuthCode() {
		return phoneAuthCode;
	}

	public void setPhoneAuthCode(PhoneAuthCode phoneAuthCode) {
		this.phoneAuthCode = phoneAuthCode;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ApiService getApiService() {
		return apiService;
	}

	public void setApiService(ApiService apiService) {
		this.apiService = apiService;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public AdviceFeedBack getAdviceFeedBack() {
		return adviceFeedBack;
	}

	public void setAdviceFeedBack(AdviceFeedBack adviceFeedBack) {
		this.adviceFeedBack = adviceFeedBack;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public TspService getTspService() {
		return tspService;
	}

	public void setTspService(TspService tspService) {
		this.tspService = tspService;
	}

	public String getPassengerphone() {
		return passengerphone;
	}

	public void setPassengerphone(String passengerphone) {
		this.passengerphone = passengerphone;
	}

	public String getPassengername() {
		return passengername;
	}

	public void setPassengername(String passengername) {
		this.passengername = passengername;
	}

	public String getDriverphone() {
		return driverphone;
	}

	public void setDriverphone(String driverphone) {
		this.driverphone = driverphone;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getEverypage() {
		return everypage;
	}

	public void setEverypage(String everypage) {
		this.everypage = everypage;
	}

	public String getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(String currentpage) {
		this.currentpage = currentpage;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public TaxiService getTaxiService() {
		return taxiService;
	}

	public void setTaxiService(TaxiService taxiService) {
		this.taxiService = taxiService;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUcar() {
		return ucar;
	}

	public void setUcar(String ucar) {
		this.ucar = ucar;
	}

	public String getVcar() {
		return vcar;
	}

	public void setVcar(String vcar) {
		this.vcar = vcar;
	}

	public String getIsagree() {
		return isagree;
	}

	public void setIsagree(String isagree) {
		this.isagree = isagree;
	}

	public String getSearchtext() {
		return searchtext;
	}

	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public AuthService getAuthService() {
		return authService;
	}

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	public String getMdataid() {
		return mdataid;
	}

	public void setMdataid(String mdataid) {
		this.mdataid = mdataid;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public String getFormatcode() {
		return formatcode;
	}

	public void setFormatcode(String formatcode) {
		this.formatcode = formatcode;
	}

	public String getEventcode() {
		return eventcode;
	}

	public void setEventcode(String eventcode) {
		this.eventcode = eventcode;
	}

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}





