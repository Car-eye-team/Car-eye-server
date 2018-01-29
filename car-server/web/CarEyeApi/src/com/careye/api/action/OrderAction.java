/**
* Description: car-eye车辆管理平台
* 文件名：OrderAction.java
* 版本信息：1.0
* 日期：2015-11-13
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.api.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.careye.api.domain.ApiKey;
import com.careye.api.domain.CustomerEvaluation;
import com.careye.api.service.ApiService;
import com.careye.base.action.BasePageAction;
import com.careye.constant.Constant;
import com.careye.constant.ServiceConfig;
import com.careye.mq.HandleUtil;
import com.careye.taxi.api.service.TaxiService;
import com.careye.taxi.domain.DriverData;
import com.careye.taxi.domain.DriverInfo;
import com.careye.taxi.domain.DriverListInfo;
import com.careye.taxi.domain.ServiceLicense;
import com.careye.taxi.domain.ShunfengOrder;
import com.careye.taxi.domain.ShunfengPassengers;
import com.careye.taxi.domain.TranCustomerCancel;
import com.careye.taxi.domain.Transaction;
import com.careye.taxi.domain.TransactionDetails;
import com.careye.taxi.domain.VehicleInfo;
import com.careye.utils.DateUtil;
import com.careye.utils.FileSizeUtil;
import com.careye.utils.QRCodeEncoderHandler;
import com.careye.utils.StringUtils;
import com.careye.wx.domain.OrderInfo;

import common.Logger;

/**
 * @项目名称：DSTAXIAPI
 * @类名称：OrderAction
 * @类描述：电召订单action
 * @创建人：zhangrong
 * @创建时间：2015-11-13 下午02:22:32
 * @修改人：zhangrong
 * @修改时间：2015-11-13 下午02:22:32
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class OrderAction extends BasePageAction {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(OrderAction.class);
	
	private String success;
	private Map jsondata;
	
	private ApiKey apiKey = new ApiKey();
	private OrderInfo orderInfo;
	private Transaction transaction;
	private TaxiService taxiService;
	
	private ApiService apiService;
	
	private String ak;
	private String type;
	
	//1 微信公众号 2 app
	private String source;
	
	private String passengerName; //乘客姓名
	private String telPhone; //乘客电话
	private String sex; //乘客性别
	private String useTime; //用车时间
	private String carpool; //是否合乘
	private String carpoolPersonNum; //合乘人数
	private String useAddr; //用车地点
	private String destinationAddr; //目的地
	private String slng; //用车地点经度
	private String slat; //用车地点纬度
	private String elng; //目的地纬度
	private String elat; //目的地纬度
	private String orderType; //orderType
	private String userid; 
	
	private String orderNo; 
	
	private String latitude; //召车纬度
	private String longitude ; //召车经度
	private String range; //召车直径，单位米
	private String maxList; //查询的车辆最大数
	
	private String vname; //车牌号
	
	private String phone; 
	private String startUtc; //起始时间
	private String endUtc; //结束时间
	private String sorttype; //排序 1 订单倒序 2 订单顺序
	
	private String isup; //1 未上车 2 上车
	
	private String orderid; //订单号
	private String outtradeno; //商户唯一订单
	private String cost; //订单价格
	private String tradestatus; //交易状态
	
	private String drivercode;
	private String terminal;
	private String stime;
	private String etime;
	private String everypage;
	private String currentpage;
	private String status;//业务状态
	private String filearr;//语音
	
	private String slevel;//星级
	private String content;//评价内容
	private String evalevel;//评价级别
	
	private String cellphone;//司机手机号
	
	private String remark;
	private String saddress;
	private String eaddress;
	private String summile;//总行车里程
	private String totalfee;//总费用
	private String ordersatus;//订单状态
	
	private String seq;//拼车序号
	private String passagename;//乘客姓名
	
	private String carnumber;//车牌号
	
	
	
	//初始化返回JSON的Map对象
	private void initMap() {
		if(jsondata == null) {
			jsondata = new HashMap();
		}
	}
	
	
	/**
	 * 下订单
	 * @return
	 */
	public String generateOrder(){
		try {
			initMap();
			logger.info("开始调用下订单接口:参数如下：");
			logger.info("ak="+ak+",passengerName="+passengerName+",telPhone="+telPhone+",sex="+sex+",useTime="+useTime
					+",carpool="+carpool+",carpoolPersonNum="+carpoolPersonNum+",useAddr="+useAddr+",destinationAddr="
					+destinationAddr+",slng="+slng+",slat="+slat+",elng="+elng+",selat="+elat+",orderType="+orderType
					+",source="+source+",userid="+userid);
			
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
			if(StringUtils.isEmty(passengerName)){
				jsondata.put("status", 4);
				jsondata.put("message", "乘客姓名不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(telPhone)){
				jsondata.put("status", 4);
				jsondata.put("message", "乘客电话不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(carpool)){
				jsondata.put("status", 4);
				jsondata.put("message", "是否合乘不能为空");
				return SUCCESS;
			}
			
			if(StringUtils.isEmty(destinationAddr)){
				jsondata.put("status", 4);
				jsondata.put("message", "目的地不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(slng)){
				jsondata.put("status", 4);
				jsondata.put("message", "用车地点经度不能为空");
				return SUCCESS;
			}else{
				slng = StringUtils.getSixFloat(Double.parseDouble(slng))+"";
			}
			if(StringUtils.isEmty(slat)){
				jsondata.put("status", 4);
				jsondata.put("message", "用车地点纬度不能为空");
				return SUCCESS;
			}else{
				slat = StringUtils.getSixFloat(Double.parseDouble(slat))+"";
			}
			if(StringUtils.isEmty(elng)){
				jsondata.put("status", 4);
				jsondata.put("message", "用车地点经度不能为空");
				return SUCCESS;
			}else{
				elng = StringUtils.getSixFloat(Double.parseDouble(elng))+"";
			}
			if(StringUtils.isEmty(elat)){
				jsondata.put("status", 4);
				jsondata.put("message", "目的地纬度不能为空");
				return SUCCESS;
			}else{
				elat = StringUtils.getSixFloat(Double.parseDouble(elat))+"";
			}
			if(StringUtils.isEmty(orderType)){
				jsondata.put("status", 4);
				jsondata.put("message", "订单类型不能为空");
				return SUCCESS;
			}
			//如果为语音订单
			if(orderType == "3"){
				elng = "0";
				elat = "0";
			}
			
			if(transaction == null){
				transaction = new Transaction();
			}
			
			if(orderInfo == null){
				orderInfo = new OrderInfo();
			}
			orderInfo.setSourcetype(1);
			
			int sourceorder = Constant.WECHAT_ORDER; //微信
			if(StringUtils.isNotEmty(source)){
				if("1".equals(source)){
					if(StringUtils.isEmty(userid)){
						jsondata.put("status", 4);
						jsondata.put("message", "微信用户id不能为空");
						return SUCCESS;
					}
				}else if("2".equals(source)){
					sourceorder = Constant.APP_ORDER; //乐嘀
					orderInfo.setSourcetype(2);
				}
			}
			transaction.setSource(sourceorder);	
			
			if(StringUtils.isEmty(sex)){
				sex = "MALE";
			}

			if(StringUtils.isEmty(useTime)){
				orderInfo.setUseTime(DateUtil.getSQLDate());
			}else{
				try {
					orderInfo.setUseTime(DateUtil.numberToDate12(useTime.trim()));
				} catch (Exception e) {
					jsondata.put("status", 4);
					jsondata.put("message", "用车时间格式不正确");
					return SUCCESS;
				}
			}
			orderInfo.setPassengerName(passengerName.trim());
			orderInfo.setTelPhone(telPhone.trim());
			orderInfo.setReservationsTel(telPhone.trim());
			orderInfo.setSex(sex);
			orderInfo.setUserid(userid);
			orderInfo.setCarpool(Integer.parseInt(carpool));
			if(StringUtils.isNotEmty(carpoolPersonNum)){
				orderInfo.setCarpoolPersonNum(Integer.parseInt(carpoolPersonNum));
			}else{
				orderInfo.setCarpoolPersonNum(0);
			}
			if(StringUtils.isEmty(useAddr)){
				orderInfo.setUseaddr("");
			}else{
				orderInfo.setUseaddr(useAddr.trim());
			}
			orderInfo.setDestinationaddr(destinationAddr.trim());
			orderInfo.setSlng(Double.parseDouble(slng));
			orderInfo.setSlat(Double.parseDouble(slat));
			orderInfo.setElng(Double.parseDouble(elng));
			orderInfo.setElat(Double.parseDouble(elat));
			orderInfo.setOrderTyp(Integer.parseInt(orderType));
			
			orderInfo.setCreatetime(DateUtil.getSQLDate());
			orderInfo.setPay(1);
			orderInfo.setOrderstatus(0);
			
			String orderid = "";
				
			transaction.setUsernametwo(passengerName.trim());
			transaction.setPhone(telPhone.trim());
			transaction.setSex(sex);
			transaction.setCarpool(Integer.parseInt(carpool));
			if(StringUtils.isNotEmty(carpoolPersonNum)){
				transaction.setCarpoolpersonnum(Integer.parseInt(carpoolPersonNum));
			}else{
				transaction.setCarpoolpersonnum(0);
			}
			if(StringUtils.isEmty(useAddr)){
				transaction.setSaddress("");
			}else{
				transaction.setSaddress(useAddr.trim());
			}
			transaction.setEaddress(destinationAddr.trim());
			transaction.setSlng(slng);
			transaction.setSlat(slat);
			transaction.setElat(elat==null?"0":elat);
			transaction.setElng(elng==null?"0":elng);
			transaction.setTratype(Integer.parseInt(orderType));
			transaction.setUserid(1);
			
			transaction.setCreatetime(DateUtil.getSQLDate());
			transaction.setResstatus(0);
			transaction.setUsetime(orderInfo.getUseTime());
			if("1".equals(orderType)){
				transaction.setAppointmenttime(orderInfo.getUseTime());
			}
			transaction.setOrderid(sourceorder + DateUtil.getOrderid());
			int re = taxiService.addTransaction(transaction);
			if(re > 0)
				orderid = transaction.getOrderid();
			
			//下发招标协议
			HandleUtil.sendDial("0", orderid,Integer.parseInt(orderType),transaction.getUsetime(), 
					"",HandleUtil.getSerialId(),useAddr.trim(),destinationAddr.trim(),Double.parseDouble(slat), 
					Double.parseDouble(slng),Double.parseDouble(transaction.getElat()),
					 Double.parseDouble(transaction.getElng()));
			
			if(orderid == null){
				jsondata.put("status", 8);
			}else{
				orderInfo.setOrderid(orderid);
				orderInfo.setOrderTime(DateUtil.getSQLDate());
				orderInfo.setOrderstatus(1);
				int id = ServiceConfig.wxService.addOrderInfo(orderInfo);
				jsondata.put("status", 0);
				jsondata.put("orderid", orderid);
				logger.info("调用下订单接口成功，订单号："+orderid);
			}
			logger.info(jsondata.toString());
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			logger.info(jsondata.toString());
			e.printStackTrace();
			logger.info("请求下订单失败!");
			return ERROR;
		}finally{
			logger.info("下订单返回结果："+jsondata.toString());
		}
	}
	
	/**
	 * 语音下订单
	 * @return
	 */
	public String generateVoiceOrder(){
		try {
			initMap();
			logger.info("开始调用语音下订单接口:参数如下：");
			logger.info("ak="+ak+",passengerName="+passengerName+",telPhone="+telPhone+",sex="+sex+",useTime="+useTime
					+",carpool="+carpool+",carpoolPersonNum="+carpoolPersonNum+",useAddr="+useAddr+",destinationAddr="
					+destinationAddr+",slng="+slng+",slat="+slat+",elng="+elng+",selat="+elat+",orderType="+orderType
					+",source="+source+",userid="+userid);
			
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
			if(StringUtils.isEmty(passengerName)){
				jsondata.put("status", 4);
				jsondata.put("message", "乘客姓名不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(telPhone)){
				jsondata.put("status", 4);
				jsondata.put("message", "乘客电话不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(carpool)){
				jsondata.put("status", 4);
				jsondata.put("message", "是否合乘不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(slng)){
				jsondata.put("status", 4);
				jsondata.put("message", "用车地点经度不能为空");
				return SUCCESS;
			}else{
				slng = StringUtils.getSixFloat(Double.parseDouble(slng))+"";
			}
			if(StringUtils.isEmty(slat)){
				jsondata.put("status", 4);
				jsondata.put("message", "用车地点纬度不能为空");
				return SUCCESS;
			}else{
				slat = StringUtils.getSixFloat(Double.parseDouble(slat))+"";
			}
			if(StringUtils.isEmty(orderType)){
				jsondata.put("status", 4);
				jsondata.put("message", "订单类型不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(filearr)){
				jsondata.put("status", 4);
				jsondata.put("message", "语音流不能为空");
				return SUCCESS;
			}
			//如果为语音订单
//			if(orderType == "3"){
				elng = "0";
				elat = "0";
//			}
				
//			HttpServletRequest request = ServletActionContext.getRequest();
//			String path = request.getContextPath();
//			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
			String fileName = FileSizeUtil.getRandomFileName()+".amr";
//			String upaddr = basePath + Constant.VOICE_PATH + fileName;
			String upaddr = Constant.DOMAIN_NAME + Constant.VOICE_PATH + fileName;

			//上传文件保存路径
			String savePath = ServletActionContext.getServletContext().getRealPath("");
			savePath = savePath + Constant.VOICE_PATH;
			//如果文件夹不存在则重新创建
			File file = new File(savePath);
			if (!file.exists()){
				file.mkdirs();
			} 
			FileSizeUtil.saveToFileByStr(filearr, savePath, fileName);
			
			if(transaction == null){
				transaction = new Transaction();
			}
			
			if(orderInfo == null){
				orderInfo = new OrderInfo();
			}
			orderInfo.setSourcetype(1);
			
			int sourceorder = Constant.WECHAT_ORDER; //微信
			if(StringUtils.isNotEmty(source)){
				if("1".equals(source)){
					if(StringUtils.isEmty(userid)){
						jsondata.put("status", 4);
						jsondata.put("message", "微信用户id不能为空");
						return SUCCESS;
					}
				}else if("2".equals(source)){
					sourceorder = Constant.APP_ORDER; //乐嘀
					orderInfo.setSourcetype(2);
				}
			}
			transaction.setSource(sourceorder);	
			
			if(StringUtils.isEmty(sex)){
				sex = "MALE";
			}

			if(StringUtils.isEmty(useTime)){
				orderInfo.setUseTime(DateUtil.getSQLDate());
			}else{
				try {
					orderInfo.setUseTime(DateUtil.numberToDate12(useTime.trim()));
				} catch (Exception e) {
					jsondata.put("status", 4);
					jsondata.put("message", "用车时间格式不正确");
					return SUCCESS;
				}
			}
			orderInfo.setPassengerName(passengerName.trim());
			orderInfo.setTelPhone(telPhone.trim());
			orderInfo.setReservationsTel(telPhone.trim());
			orderInfo.setSex(sex);
			orderInfo.setUserid(userid);
			orderInfo.setCarpool(Integer.parseInt(carpool));
			if(StringUtils.isNotEmty(carpoolPersonNum)){
				orderInfo.setCarpoolPersonNum(Integer.parseInt(carpoolPersonNum));
			}else{
				orderInfo.setCarpoolPersonNum(0);
			}
			if(StringUtils.isEmty(useAddr)){
				orderInfo.setUseaddr("");
			}else{
				orderInfo.setUseaddr(useAddr.trim());
			}
			orderInfo.setDestinationaddr(destinationAddr);
			orderInfo.setSlng(Double.parseDouble(slng));
			orderInfo.setSlat(Double.parseDouble(slat));
			orderInfo.setElng(Double.parseDouble(elng));
			orderInfo.setElat(Double.parseDouble(elat));
			orderInfo.setOrderTyp(Integer.parseInt(orderType));
			
			orderInfo.setCreatetime(DateUtil.getSQLDate());
			orderInfo.setPay(1);
			orderInfo.setOrderstatus(0);
			orderInfo.setUrl(upaddr);
			
			String orderid = "";
				
			transaction.setUsernametwo(passengerName.trim());
			transaction.setPhone(telPhone.trim());
			transaction.setSex(sex);
			transaction.setCarpool(Integer.parseInt(carpool));
			if(StringUtils.isNotEmty(carpoolPersonNum)){
				transaction.setCarpoolpersonnum(Integer.parseInt(carpoolPersonNum));
			}else{
				transaction.setCarpoolpersonnum(0);
			}
			if(StringUtils.isEmty(useAddr)){
				transaction.setSaddress("");
			}else{
				transaction.setSaddress(useAddr.trim());
			}
			transaction.setEaddress(destinationAddr);
			transaction.setSlng(slng);
			transaction.setSlat(slat);
			transaction.setElat(elat==null?"0":elat);
			transaction.setElng(elng==null?"0":elng);
			transaction.setTratype(Integer.parseInt(orderType));
			transaction.setUserid(1);
			transaction.setCalltype(2);
			
			transaction.setCreatetime(DateUtil.getSQLDate());
			transaction.setResstatus(0);
			transaction.setUsetime(orderInfo.getUseTime());
			if("1".equals(orderType)){
				transaction.setAppointmenttime(DateUtil.getSQLDate());
			}
			transaction.setOrderid(sourceorder + DateUtil.getOrderid());
			transaction.setFilepath(upaddr);
			int re = taxiService.addTransaction(transaction);
			if(re > 0)
				orderid = transaction.getOrderid();
			
			//下发招标协议
			HandleUtil.sendVoiceDial("0", orderid,Integer.parseInt(orderType),transaction.getUsetime(), 
					"",HandleUtil.getSerialId(),useAddr.trim(),upaddr.trim(),Double.parseDouble(slat), 
					Double.parseDouble(slng),Double.parseDouble(transaction.getElat()),
					 Double.parseDouble(transaction.getElng()));
			
			if(orderid == null){
				jsondata.put("status", 8);
			}else{
				orderInfo.setOrderid(orderid);
				orderInfo.setOrderTime(DateUtil.getSQLDate());
				orderInfo.setOrderstatus(1);
				int id = ServiceConfig.wxService.addOrderInfo(orderInfo);
				jsondata.put("status", 0);
				jsondata.put("orderid", orderid);
				logger.info("调用语音下订单接口成功，订单号："+orderid);
			}
			logger.info(jsondata.toString());
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			logger.info(jsondata.toString());
			e.printStackTrace();
			logger.info("请求语音下订单失败!");
			return ERROR;
		}finally{
			logger.info("语音下订单返回结果："+jsondata.toString());
		}
	}

	/**
	 * 取消订单
	 * @return
	 */
	public String cancelTaxi(){
		try {
			initMap();
			logger.info("开始调用取消订单接口,订单号:"+orderNo);
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
			if(StringUtils.isEmty(orderNo)){
				jsondata.put("status", 4);
				jsondata.put("message", "订单号不能为空");
				return SUCCESS;
			}
			if(orderInfo == null){
				orderInfo = new OrderInfo();
			}
			orderInfo.setOrderid(orderNo.trim());
			if(orderNo.startsWith(Constant.APP_ORDER+"")){
				orderInfo.setSourcetype(2);
			}else if(orderNo.startsWith(Constant.WECHAT_ORDER+"")){
				orderInfo.setSourcetype(1);
			}
			
			boolean flag = false;
			if(transaction == null){
				transaction = new Transaction();
			}
			transaction.setOrderid(orderNo.trim());
			transaction.setStatus(3);
			flag = taxiService.updateTransactionStatus(transaction)>0?true:false;
			
			//判断当前是否存在订单id
			int count=taxiService.isTcCancelExitsOrderid(transaction.getOrderid());
			transaction=taxiService.getOrderdetail(transaction.getOrderid());
			if(count<=0){     //如果当前订单在乘客取消表中不存在则添加
				//1.根据订单得到详细
				TranCustomerCancel tcCancel=new TranCustomerCancel();
				tcCancel.setWy("0");   //违约
				tcCancel.setOrderid(transaction.getOrderid());
				tcCancel.setUsername(transaction.getUsername());
				tcCancel.setPhone(transaction.getPhone());
				tcCancel.setRemark(transaction.getRemark());
				tcCancel.setCanceltime(DateUtil.getSQLDate());
				//插入记录到乘客取消表
				taxiService.addTranCustomerCancel(tcCancel);
			}
			
			//发送取消电召协议
			HandleUtil.cancelCarOrder(transaction.getTerminal()==null?"0":transaction.getTerminal(), HandleUtil.getSerialId(), orderNo.trim());
			
			if(flag){
				jsondata.put("status", 0);
				orderInfo.setOrderstatus(3);
				ServiceConfig.wxService.updateOrderStatus(orderInfo);
			}else{
				jsondata.put("status", 8);
			}
			logger.info(jsondata.toString());
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			logger.info(jsondata.toString());
			e.printStackTrace();
			logger.info("请求取消订单失败");
			return ERROR;
		}
	}
	
	/**
	 * 通过订单号查询订单信息
	 * @return
	 */
	public String queryOrderInfoByOrderNo(){
		try {
			initMap();
			logger.info("开始调用通过订单号查询订单信息接口,订单号:"+orderNo);
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
			if(StringUtils.isEmty(orderNo)){
				jsondata.put("status", 4);
				jsondata.put("message", "订单号不能为空");
				return SUCCESS;
			}
			if(orderInfo == null){
				orderInfo = new OrderInfo();
			}
			orderInfo.setOrderid(orderNo.trim());
			if(orderNo.startsWith(Constant.APP_ORDER+"")){
				orderInfo.setSourcetype(2);
			}else if(orderNo.startsWith(Constant.WECHAT_ORDER+"")){
				orderInfo.setSourcetype(1);
			}else{
				orderInfo.setSourcetype(3);
			}
			
			OrderInfo oInfo = null;
			if(transaction == null){
				transaction = new Transaction();
			}
			transaction.setOrderid(orderNo.trim());
			transaction = taxiService.getOrderdetail(orderNo.trim());
			
			if(transaction != null){
				oInfo = new OrderInfo();
				oInfo.setOrderid(transaction.getOrderid());
				oInfo.setPassengerName(transaction.getUsername());
				oInfo.setTelPhone(transaction.getPhone());
				oInfo.setDsex(transaction.getSex());
				oInfo.setOrderTime(transaction.getCreatetime());
				oInfo.setCarpool(transaction.getCarpool());
				oInfo.setCarpoolPersonNum(transaction.getCarpoolpersonnum());
				oInfo.setUseaddr(transaction.getSaddress());
				oInfo.setDestinationaddr(transaction.getEaddress());
				oInfo.setSlng(transaction.getSlng() == null ?0.0:Double.parseDouble(transaction.getSlng()));
				oInfo.setSlat(transaction.getSlat() == null ?0.0:Double.parseDouble(transaction.getSlat()));
				oInfo.setOrderTyp(transaction.getTratype());
				oInfo.setVname(transaction.getDrivername());
				oInfo.setCellphone(transaction.getDriverphone());
				oInfo.setOrderstatus(transaction.getStatus());
				oInfo.setCarnumber(transaction.getCarnumber());
				oInfo.setCost(transaction.getCallfee());
				oInfo.setElng(transaction.getElng() == null ?0.0:Double.parseDouble(transaction.getElng()));
				oInfo.setElat(transaction.getElat() == null ?0.0:Double.parseDouble(transaction.getElat()));
				oInfo.setLng(transaction.getLng() == null ?0.0:Double.parseDouble(transaction.getLng()));
				oInfo.setLat(transaction.getLat() == null ?0.0:Double.parseDouble(transaction.getLat()));
				oInfo.setIsup(transaction.getIsup());
				oInfo.setUseTime(transaction.getUsetime());
				oInfo.setAnswertime(transaction.getAnswertime());
				oInfo.setUrl(transaction.getFilepath());
				oInfo.setPraise(transaction.getPraise());
				oInfo.setDrivercode(transaction.getDrivercode());
				oInfo.setProcessstatus(transaction.getProcessstatus());
				oInfo.setPay(transaction.getPay());
				oInfo.setIsappraise(transaction.getIsappraise());
				oInfo.setIscomplaint(transaction.getIscomplaint());
				
				orderInfo.setOrderstatus(transaction.getStatus());
				orderInfo.setIsup(transaction.getIsup());
				orderInfo.setCarnumber(transaction.getCarnumber());
				orderInfo.setVname(transaction.getDrivername());
				orderInfo.setDsex(transaction.getSex());
				orderInfo.setIsappraise(transaction.getIsappraise());
				orderInfo.setIscomplaint(transaction.getIscomplaint());
			}
				
			if(oInfo == null){
				jsondata.put("status", 8);
			}else{
				//更新订单信息
				if(orderInfo.getSourcetype() != 3){
					ServiceConfig.wxService.updateOrderInfo(orderInfo);
				}
				
				jsondata.put("status", 0);
				jsondata.put("orderInfo", oInfo);
			}
			logger.info(jsondata.toString());
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			logger.info(jsondata.toString());
			e.printStackTrace();
			logger.info("请求通过订单号查询订单失败!");
			return ERROR;
		}
	}

	/**
	 * 查询直径范围内车辆
	 * @return
	 */
	public String queryVehicleForEmpty(){
		try {
			initMap();
			logger.info("开始调用查询直径范围内车辆接口,ak="+ak+",latitude="+latitude+",longitude="+longitude+
					",range="+range+",maxList  ="+maxList);
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
			if(StringUtils.isEmty(latitude)){
				jsondata.put("status", 4);
				jsondata.put("message", "召车纬度不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(longitude)){
				jsondata.put("status", 4);
				jsondata.put("message", "召车经度不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(range)){
				jsondata.put("status", 4);
				jsondata.put("message", "召车直径不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(maxList)){
				jsondata.put("status", 4);
				jsondata.put("message", "车辆最大数不能为空");
				return SUCCESS;
			}
			
			List<VehicleInfo> list = new ArrayList<VehicleInfo>();
			Map map = new HashMap<String, String>();
			map.put("lat", latitude.trim());
			map.put("lng", longitude.trim());
			map.put("mileage", range.trim());
			map.put("maxList", maxList.trim());
			list = taxiService.queryVehicleInfo(map);
			
			jsondata.put("list", list);
			jsondata.put("status",0);
			logger.info(jsondata.toString());
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			logger.info(jsondata.toString());
			e.printStackTrace();
			logger.info("请求查询直径范围内车辆失败!");
			return ERROR;
		}
	}
	
	/**
	 * 获取驾驶员信息
	 * @return
	 */
	public String queryDriverInfo(){
		try {
			initMap();
			logger.info("开始调用获取驾驶员信息接口：参数：ak="+ak+",vname="+vname);
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
//			vname = "粤B09544";
			if(StringUtils.isEmty(vname)){
				jsondata.put("status", 4);
				jsondata.put("message", "车牌号不能为空");
				return SUCCESS;
			}
			
			//根据车牌号获取司机信息
			DriverInfo driverInfo =  taxiService.queryDriverInfo(vname.trim());
			if(driverInfo != null){
				if(StringUtils.isNotEmty(driverInfo.getPicturepath())){
					driverInfo.setPicturepath(Constant.PLATFORM_NAME+driverInfo.getPicturepath());
				}
				
				ServiceLicense slInfo = taxiService.findServiceLicenseByDriverid(driverInfo.getId()+"");
				
				if(slInfo != null){
					String starlevel = slInfo.getStarlevel();
					String slevelstr = "";
					if("1".equals(starlevel)){
						slevelstr = "一星";
					}else if("2".equals(starlevel)){
						slevelstr = "二星";
					}else if("3".equals(starlevel)){
						slevelstr = "三星";
					}else if("4".equals(starlevel)){
						slevelstr = "四星";
					}else if("5".equals(starlevel)){
						slevelstr = "五星";
					}
					
//					Integer zjstatus = slInfo.getZjstatus();
//					String zjstatusstr = "";
//					if(zjstatus == null){
//					}else if(zjstatus == 1){
//						zjstatusstr = "正常";
//					}else if(zjstatus == 2){
//						zjstatusstr = "注销";
//					}
					
					String savePath = ServletActionContext.getServletContext().getRealPath("") + Constant.FWZ_CODE_PATH;
					String filename = driverInfo.getId()+".png";
					String code = "姓名:"+slInfo.getName()+"\n服务证号:"+slInfo.getServicenumber()
								+"\n星级:"+slevelstr+"\n发证日期:"+slInfo.getFztime()
								+"\n有效期:"+slInfo.getValidity()+"年";
//					System.out.println(code);
					boolean  bo  = QRCodeEncoderHandler.encoderQRCode(code, savePath,filename);
					if(bo){
						driverInfo.setQrcodepath(Constant.DOMAIN_NAME+Constant.FWZ_CODE_PATH+filename);
					}
				}
				

			}
			
			jsondata.put("driverInfo", driverInfo);
			jsondata.put("status",0);
			logger.info(jsondata.toString());
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			logger.info(jsondata.toString());
			e.printStackTrace();
			logger.info("请求获取驾驶员信息失败");
			return ERROR;
		}
	}
	
	/**
	 * 通过手机号和时间查询订单信息
	 * @return
	 */
	public String queryOrderInfoByphone(){
		try {
			initMap();
			logger.info("开始调用手机号和时间查询订单信息接口,时间:"+startUtc+"-"+endUtc+",手机号:"+phone+",sorttype:"+sorttype);
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
				jsondata.put("status", 4);
				jsondata.put("message", "订车电话不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(startUtc)){
				jsondata.put("status", 4);
				jsondata.put("message", "起始时间不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(endUtc)){
				jsondata.put("status", 4);
				jsondata.put("message", "结束时间不能为空");
				return SUCCESS;
			}
			long sTime = DateUtil.NumberTimeToTimeStamp(startUtc.trim());
			long eTime = DateUtil.NumberTimeToTimeStamp(endUtc.trim());
			
			List<OrderInfo> orderList = new ArrayList<OrderInfo>();
			Map map = new HashMap<String, String>();
			map.put("telphone", phone.trim());
			map.put("stime", DateUtil.TimeStampToDateTime(sTime));
			map.put("etime", DateUtil.TimeStampToDateTime(eTime));
			if(StringUtils.isEmty(sorttype)){
				map.put("sorttype", "1");	//订单倒序
			}else{
				map.put("sorttype", sorttype);
			}
			orderList = ServiceConfig.wxService.queryOrderInfoByphone(map);
		
			if(orderList.size() > 0){
				for(OrderInfo oInfo : orderList){
					//根据车牌号得到当班司机服务监督卡号
					if(oInfo.getCarnumber() != null){
						String drivercode = taxiService.getDrivercodeByCarnumber(oInfo.getCarnumber());
						oInfo.setDrivercode(drivercode);
					}
				}
			}
			jsondata.put("status", 0);
			jsondata.put("orderList", orderList);
			logger.info(jsondata.toString());
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			logger.info(jsondata.toString());
			logger.info("请求通过手机号和时间查询订单信息失败!");
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 支付订单费用
	 * @return
	 */
	public String prePay(){
		try {
			initMap();
			logger.info("开始调用支付订单费用接口:"+ak+"-"+ak+",订单号:"+orderid+",商户唯一订单号:"+outtradeno+",订单价格:"+cost);
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
			if(StringUtils.isEmty(orderid)){
				jsondata.put("status", 4);
				jsondata.put("message", "订单号不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(cost)){
				jsondata.put("status", 4);
				jsondata.put("message", "订单价格不能为空");
				return SUCCESS;
			}
			if(orderInfo == null){
				orderInfo = new OrderInfo();
			}
			if(orderid.startsWith(Constant.APP_ORDER+"")){
				orderInfo.setSourcetype(2);
			}else if(orderid.startsWith(Constant.WECHAT_ORDER+"")){
				orderInfo.setSourcetype(1);
			}
			orderInfo.setOrderid(orderid.trim());
			orderInfo.setOuttradeno(outtradeno.trim());
			orderInfo.setCost(cost.trim());
			//更新订单费用与商户订单号
			int count = ServiceConfig.wxService.updateOrderStatus(orderInfo);
			if(count > 0){
				jsondata.put("status", 0);
				logger.info("支付订单接口处理成功");
			}else{
				jsondata.put("status", 8);
				logger.info("支付订单接口请求结果不存在");
			}
			transaction = new Transaction();
			transaction.setOrderid(orderid.trim());
			transaction.setCallfee(cost.trim());
			//更新订单费用
			taxiService.updateTransaction(transaction);
			logger.info(jsondata.toString());
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			logger.info(jsondata.toString());
			logger.info("支付订单接口处理失败");
			e.printStackTrace();
			return ERROR;
		}
	}
	
	
	/**
	 * 支付回调接口
	 * @return
	 */
	public String notifyOrderStatus(){
		try {
			initMap();
			logger.info("开始调用支付回调接口");
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
			if(StringUtils.isEmty(outtradeno)){
				jsondata.put("status", 4);
				jsondata.put("message", "商户唯一订单号不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(tradestatus)){
				jsondata.put("status", 4);
				jsondata.put("message", "支付状态不能为空");
				return SUCCESS;
			}
			if(orderInfo == null){
				orderInfo = new OrderInfo();
			}
			orderInfo.setOuttradeno(outtradeno.trim());
			
			if(tradestatus.equals("1")){ //支付成功
				orderInfo.setPay(2);
			}else if(tradestatus.equals("2")){ //支付确认中
				orderInfo.setPay(3);
			}else if(tradestatus.equals("3")){ //支付失败
				orderInfo.setPay(1);
			}
			
			//更新订单交易状态
			int count = ServiceConfig.wxService.updateOrderStatusByOuttradeno(orderInfo);
			if(count > 0){
				jsondata.put("status", 0);
				logger.info("支付回调接口处理成功");
			}else{
				jsondata.put("status", 8);
				logger.info("支付回调接口请求结果不存在");
			}
			transaction = new Transaction();
			transaction.setOrderid(outtradeno.trim());
			if(tradestatus.equals("1")){ //支付成功
				transaction.setPay(2);
			}else if(tradestatus.equals("2")){ //支付确认中
				transaction.setPay(3);
			}else if(tradestatus.equals("3")){ //支付失败
				transaction.setPay(1);
			}
			taxiService.updateTransaction(transaction);
			logger.info(jsondata.toString());
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			logger.info(jsondata.toString());
			logger.info("支付回调接口处理失败");
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 是否上车
	 * @return
	 */
	public String upTaxi(){
		try {
			initMap();
			logger.info("开始调用是否上车接口");
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
			if(StringUtils.isEmty(orderNo)){
				jsondata.put("status", 4);
				jsondata.put("message", "订单号不能为空");
				return SUCCESS;
			}
			if(StringUtils.isEmty(isup)){
				jsondata.put("status", 4);
				jsondata.put("message", "上车状态参数不能为空");
				return SUCCESS;
			}
			if(orderInfo == null){
				orderInfo = new OrderInfo();
			}
			if(orderNo.startsWith(Constant.APP_ORDER+"")){
				orderInfo.setSourcetype(2);
			}else if(orderNo.startsWith(Constant.WECHAT_ORDER+"")){
				orderInfo.setSourcetype(1);
			}
			orderInfo.setOrderid(orderNo);
			orderInfo.setIsup(Integer.parseInt(isup));
			if("1".equals(isup)){
				orderInfo.setOrderstatus(3); //取消订单
			}
			
			ServiceConfig.wxService.updateOrderInfoUpTaix(orderInfo);
			transaction = new Transaction();
			transaction.setOrderid(orderNo);
			transaction.setIsup(Integer.parseInt(isup));
			if("1".equals(isup)){
				transaction.setStatus(3); //取消订单
			}
			taxiService.updateTransaction(transaction);
			jsondata.put("su", 0);
			logger.info(jsondata.toString());
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("su", 5);
			logger.info(jsondata.toString());
			e.printStackTrace();
			logger.info("请求是否上车失败");
			return ERROR;
		}
	}
	
	/**
	 * 分页获取订单列表接口
	 * @return
	 */
	public String getTransactionList(){
		try {
			initMap();
			logger.info("开始调用分页获取订单列表接口:ak="+ak+",terminal="+terminal+",drivercode="+drivercode+",status="+status
					+",stime="+stime+",etime="+etime+",everypage="+everypage+",currentpage="+currentpage);
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
			
//			if(StringUtils.isEmty(drivercode)){
//				jsondata.put("status", 4);
//				jsondata.put("message", "drivercode不能为空");
//				return SUCCESS;
//			}
			if(StringUtils.isEmty(terminal)){
				jsondata.put("status", 4);
				jsondata.put("message", "terminal不能为空");
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
			
			Map map = new HashMap();
			map.put("terminal", terminal);
			map.put("drivercode", drivercode);
			map.put("stime", stime);
			map.put("etime", etime);
			map.put("status", status);
			Map tranMap = new HashMap();
			tranMap =  taxiService.getTransactionList(Integer.parseInt(currentpage), Integer.parseInt(everypage), map);
			List<TransactionDetails> list = (List<TransactionDetails>) tranMap.get("list");
			int totalCount = (Integer) tranMap.get("totalCount");
			int totalPages = (int) Math.floor((double)totalCount/Integer.parseInt(everypage))+1; 
			if((totalPages-1) * Integer.parseInt(everypage) == totalCount){
				totalPages -= 1;
			}
			jsondata.put("list", list);
			jsondata.put("totalCount", totalCount);
			jsondata.put("totalPages", totalPages);
			jsondata.put("status",0);
			logger.info(jsondata.toString());
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			logger.info(jsondata.toString());
			e.printStackTrace();
			logger.info("请求分页获取订单列表失败");
			return ERROR;
		}
	}
	
	/**
	 * 客户评价api接口
	 * @return
	 */
	 public String addCustomerEvaluation(){
			logger.info("客户评价api接口:ak="+ak+",orderid="+orderid+",slevel="+slevel+",content="+content+",evalevel="+evalevel);
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

				if(orderid == null || "".equals(orderid)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数orderid为空不存在");
					return SUCCESS;
				}
				if(slevel == null || "".equals(slevel)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数slevel为空不存在");
					return SUCCESS;
				}
				if(content == null || "".equals(content)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数content为空不存在");
					return SUCCESS;
				}
				if(evalevel == null || "".equals(evalevel)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数evalevel为空不存在");
					return SUCCESS;
				}
				
				CustomerEvaluation customerEvaluation = new CustomerEvaluation();
				customerEvaluation.setOrderid(orderid);
				customerEvaluation.setSlevel(Integer.parseInt(slevel));
				customerEvaluation.setContent(content);
				customerEvaluation.setEvalevel(Integer.parseInt(evalevel));
				
				int result = taxiService.addCustomerEvaluation(customerEvaluation);
				
				Transaction transaction = taxiService.getOrderdetail(orderid);
				if(transaction != null && transaction.getStatus() != null && transaction.getStatus()>4){
					transaction.setStatus(8);
					transaction.setIsappraise(1);
					taxiService.updateTransactionStatus(transaction);
					orderInfo = new OrderInfo();
					orderInfo.setSourcetype(2);
					orderInfo.setOrderid(orderid);
					orderInfo.setOrderstatus(8);
					orderInfo.setIsappraise(1);
					ServiceConfig.wxService.updateOrderStatus(orderInfo);
				}
					
				jsondata.put("status", 0);
				jsondata.put("message", "成功");
					
				return SUCCESS;
			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info("客户评价api接口请求异常");
				e.printStackTrace();
				return ERROR;
			}finally{
				logger.info("返回结果："+jsondata.toString());
			}
		}
	 
	 /**
		 * 获得司机被评价数据api接口
		 * @return
		 */
		 public String getDriverData(){
				logger.info("获得司机评价数据接口:ak="+ak+",orderid="+orderid+",cellphone="+cellphone);
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

					if(orderid == null || "".equals(orderid)){
						jsondata.put("status", 4);
						jsondata.put("message", "必填参数orderid为空不存在");
						return SUCCESS;
					}
					if(cellphone == null || "".equals(cellphone)){
						jsondata.put("status", 4);
						jsondata.put("message", "必填参数cellphone为空不存在");
						return SUCCESS;
					}
					
					Map map = new HashMap<String, String>();
					map.put("orderid", orderid);
					map.put("cellphone", cellphone);
					
					int servicecount = taxiService.getServiceCount(map);
					int goodcount = taxiService.getGoodCount(map);
					DriverData driverData = taxiService.getLevel(map);
					if(driverData != null){
						driverData.setServicecount(servicecount);
						driverData.setGoodcount(goodcount);
					}else{
						driverData = new DriverData();
						driverData.setServicecount(servicecount);
						driverData.setGoodcount(goodcount);
					}
					jsondata.put("data", driverData);	
					jsondata.put("status", 0);
					jsondata.put("message", "成功");
						
					return SUCCESS;
				} catch (Exception e) {
					jsondata.put("status", 5);
					jsondata.put("message", "服务器内部错误");
					logger.info("客户评价api接口请求异常");
					e.printStackTrace();
					return ERROR;
				}finally{
					logger.info("返回结果："+jsondata.toString());
				}
			}
		 
		 /**
			 * 获得司机被评价列表api接口
			 * @return
			 */
			 public String getDriverDataList(){
					logger.info("获得司机评价列表接口:ak="+ak+",cellphone="+cellphone+",everypage="+everypage+",currentpage="+currentpage);
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

						if(cellphone == null || "".equals(cellphone)){
							jsondata.put("status", 4);
							jsondata.put("message", "必填参数cellphone为空不存在");
							return SUCCESS;
						}
						if(everypage == null || "".equals(everypage)){
							jsondata.put("status", 4);
							jsondata.put("message", "必填参数everypage为空不存在");
							return SUCCESS;
						}
						if(currentpage == null || "".equals(currentpage)){
							jsondata.put("status", 4);
							jsondata.put("message", "必填参数currentpage为空不存在");
							return SUCCESS;
						}
						
						Map map = new HashMap<String, String>();
						map.put("cellphone", cellphone);
						
						Map driverDataMap = new HashMap();
						driverDataMap = taxiService.getDriverDataMap(Integer.parseInt(currentpage),Integer.parseInt(everypage),map);
						
						Integer totalCount = (Integer) driverDataMap.get("totalCount");
						if(totalCount == null){
							totalCount = 0;
						}
						int totalPages = (int) Math.floor((double)totalCount/Integer.parseInt(everypage))+1;
						if((totalPages-1) * Integer.parseInt(everypage) == totalCount){
							totalPages -= 1;
						}
						
						jsondata.put("totalCount", totalCount);
						jsondata.put("totalPages", totalPages);
						jsondata.put("data", driverDataMap.get("list"));	
						jsondata.put("status", 0);
						jsondata.put("message", "成功");
							
						return SUCCESS;
					} catch (Exception e) {
						jsondata.put("status", 5);
						jsondata.put("message", "服务器内部错误");
						logger.info("客户评价api接口请求异常");
						e.printStackTrace();
						return ERROR;
					}finally{
						logger.info("返回结果："+jsondata.toString());
					}
				}
			 
			 /**
				 * 获得司机被评价列表api接口
				 * @return
				 */
				 public String getCustomerDataList(){
						logger.info("获得客户评价列表接口:ak="+ak+",phone="+phone+",everypage="+everypage+",currentpage="+currentpage);
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

							if(phone == null || "".equals(phone)){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数phone为空不存在");
								return SUCCESS;
							}
							if(everypage == null || "".equals(everypage)){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数everypage为空不存在");
								return SUCCESS;
							}
							if(currentpage == null || "".equals(currentpage)){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数currentpage为空不存在");
								return SUCCESS;
							}
							
							Map map = new HashMap<String, String>();
							map.put("phone", phone);
							
							Map customerDataMap = new HashMap();
							customerDataMap = taxiService.getCustomerDataMap(Integer.parseInt(currentpage),Integer.parseInt(everypage),map);
							
							Integer totalCount = (Integer) customerDataMap.get("totalCount");
							if(totalCount == null){
								totalCount = 0;
							}
							int totalPages = (int) Math.floor((double)totalCount/Integer.parseInt(everypage))+1;
							if((totalPages-1) * Integer.parseInt(everypage) == totalCount){
								totalPages -= 1;
							}
							
							jsondata.put("totalCount", totalCount);
							jsondata.put("totalPages", totalPages);
							jsondata.put("data", customerDataMap.get("list"));	
							jsondata.put("status", 0);
							jsondata.put("message", "成功");
								
							return SUCCESS;
						} catch (Exception e) {
							jsondata.put("status", 5);
							jsondata.put("message", "服务器内部错误");
							logger.info("客户评价api接口请求异常");
							e.printStackTrace();
							return ERROR;
						}finally{
							logger.info("返回结果："+jsondata.toString());
						}
					}
			 
			 /**
				 * 添加顺风车订单api接口
				 * @return
				 */
				 public String addShunfengOrder(){
						logger.info("添加顺风车订单api接口:ak="+ak+",orderid="+orderid+",terminal="+terminal+",stime="+stime+",etime="+etime
								+",summile="+summile+",totalfee="+totalfee+",ordersatus="+ordersatus+",remark="+remark+",saddress="+saddress
								+",slat="+slat+",slng="+slng+",eaddress="+eaddress+",elat="+elat+",elng="+elng);
						initMap();
						ApiKey apiKey = new ApiKey();
						try {
							if(ak == null){
								jsondata.put("status", 4);
								jsondata.put("message", "ak不存在或者非法");
								return SUCCESS;
							}else{
								apiKey.setKey(DateUtil.StringChange(ak));
								apiKey.setTypeid(Constant.SF_APP_TYPE);
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

							if(orderid == null || "".equals(orderid)){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数orderid为空不存在");
								return SUCCESS;
							}
							if(terminal == null || "".equals(terminal)){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数terminal为空不存在");
								return SUCCESS;
							}
							if(stime == null || "".equals(stime)){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数stime为空不存在");
								return SUCCESS;
							}
							if(etime == null || "".equals(etime)){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数etime为空不存在");
								return SUCCESS;
							}
							if(summile == null || "".equals(summile)){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数summile为空不存在");
								return SUCCESS;
							}
							if(totalfee == null || "".equals(totalfee)){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数totalfee为空不存在");
								return SUCCESS;
							}
							if(ordersatus == null || "".equals(ordersatus)){
								jsondata.put("status", 4);
								jsondata.put("message", "必填参数ordersatus为空不存在");
								return SUCCESS;
							}
							
							Integer carid = taxiService.getCaridByTerminal(terminal);
							if(carid == null){
								jsondata.put("status", 8);
								jsondata.put("message", "设备号不存在");
								return SUCCESS;
							}
							
							ShunfengOrder shunfengOrder = new ShunfengOrder();
							shunfengOrder.setCarid(carid);
							shunfengOrder.setRemark(remark);
							shunfengOrder.setOrderid(orderid);
							shunfengOrder.setSaddress(saddress);
							shunfengOrder.setSlat(slat);
							shunfengOrder.setSlng(slng);
							shunfengOrder.setEaddress(eaddress);
							shunfengOrder.setElat(elat);
							shunfengOrder.setElng(elng);
							shunfengOrder.setStime(stime);
							shunfengOrder.setEtime(etime);
							shunfengOrder.setSummile(summile);
							shunfengOrder.setTotalfee(totalfee);
							shunfengOrder.setOrdersatus(Integer.parseInt(ordersatus));
							shunfengOrder.setCreatetime(DateUtil.getSQLDate());
							
							taxiService.addShunfengOrder(shunfengOrder);
								
							jsondata.put("status", 0);
							jsondata.put("message", "成功");
								
							return SUCCESS;
						} catch (Exception e) {
							jsondata.put("status", 5);
							jsondata.put("message", "服务器内部错误");
							logger.info("添加顺风车订单api接口请求异常");
							e.printStackTrace();
							return ERROR;
						}finally{
							logger.info("返回结果："+jsondata.toString());
						}
					}
				 
				 /**
					 * 添加拼车乘客api接口
					 * @return
					 */
					 public String addShunfengPassengers(){
							logger.info("添加拼车乘客api接口:ak="+ak+",orderid="+orderid+",seq="+seq+",stime="+stime+",etime="+etime
									+",summile="+summile+",totalfee="+totalfee+",ordersatus="+ordersatus+",remark="+remark+",saddress="+saddress
									+",slat="+slat+",slng="+slng+",eaddress="+eaddress+",elat="+elat+",elng="+elng
									+",passagename="+passagename+",phone="+phone);
							initMap();
							ApiKey apiKey = new ApiKey();
							try {
								if(ak == null){
									jsondata.put("status", 4);
									jsondata.put("message", "ak不存在或者非法");
									return SUCCESS;
								}else{
									apiKey.setKey(DateUtil.StringChange(ak));
									apiKey.setTypeid(Constant.SF_APP_TYPE);
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

								if(orderid == null || "".equals(orderid)){
									jsondata.put("status", 4);
									jsondata.put("message", "必填参数orderid为空不存在");
									return SUCCESS;
								}
								if(seq == null || "".equals(seq)){
									jsondata.put("status", 4);
									jsondata.put("message", "必填参数seq为空不存在");
									return SUCCESS;
								}
								if(stime == null || "".equals(stime)){
									jsondata.put("status", 4);
									jsondata.put("message", "必填参数stime为空不存在");
									return SUCCESS;
								}
								if(etime == null || "".equals(etime)){
									jsondata.put("status", 4);
									jsondata.put("message", "必填参数etime为空不存在");
									return SUCCESS;
								}
								if(summile == null || "".equals(summile)){
									jsondata.put("status", 4);
									jsondata.put("message", "必填参数summile为空不存在");
									return SUCCESS;
								}
								if(totalfee == null || "".equals(totalfee)){
									jsondata.put("status", 4);
									jsondata.put("message", "必填参数totalfee为空不存在");
									return SUCCESS;
								}
								if(ordersatus == null || "".equals(ordersatus)){
									jsondata.put("status", 4);
									jsondata.put("message", "必填参数ordersatus为空不存在");
									return SUCCESS;
								}
								if(passagename == null || "".equals(passagename)){
									jsondata.put("status", 4);
									jsondata.put("message", "必填参数passagename为空不存在");
									return SUCCESS;
								}
								
								ShunfengPassengers shunfengPassengers = new ShunfengPassengers();
								shunfengPassengers.setSeq(Integer.parseInt(seq));
								shunfengPassengers.setRemark(remark);
								shunfengPassengers.setOrderid(orderid);
								shunfengPassengers.setSaddress(saddress);
								shunfengPassengers.setSlat(slat);
								shunfengPassengers.setSlng(slng);
								shunfengPassengers.setEaddress(eaddress);
								shunfengPassengers.setElat(elat);
								shunfengPassengers.setElng(elng);
								shunfengPassengers.setStime(stime);
								shunfengPassengers.setEtime(etime);
								shunfengPassengers.setSummile(summile);
								shunfengPassengers.setTotalfee(totalfee);
								shunfengPassengers.setOrdersatus(Integer.parseInt(ordersatus));
								shunfengPassengers.setCreatetime(DateUtil.getSQLDate());
								shunfengPassengers.setPassagename(passagename);
								shunfengPassengers.setPhone(phone);
								
								taxiService.addShunfengPassengers(shunfengPassengers);
									
								jsondata.put("status", 0);
								jsondata.put("message", "成功");
									
								return SUCCESS;
							} catch (Exception e) {
								jsondata.put("status", 5);
								jsondata.put("message", "服务器内部错误");
								logger.info("添加拼车乘客api接口请求异常");
								e.printStackTrace();
								return ERROR;
							}finally{
								logger.info("返回结果："+jsondata.toString());
							}
						}
					 
					 /**
						 * 历史订单列表api接口
						 * @return
						 */
						 public String getHistoryOrderList(){
								logger.info("历史订单列表api接口:ak="+ak+",terminal="+terminal+",stime="+stime+",etime="+etime);
								initMap();
								ApiKey apiKey = new ApiKey();
								try {
									if(ak == null){
										jsondata.put("status", 4);
										jsondata.put("message", "ak不存在或者非法");
										return SUCCESS;
									}else{
										apiKey.setKey(DateUtil.StringChange(ak));
										apiKey.setTypeid(Constant.SF_APP_TYPE);
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

									if(terminal == null || "".equals(terminal)){
										jsondata.put("status", 4);
										jsondata.put("message", "必填参数terminal为空不存在");
										return SUCCESS;
									}
									if(everypage == null || "".equals(everypage)){
										jsondata.put("status", 4);
										jsondata.put("message", "必填参数everypage为空不存在");
										return SUCCESS;
									}
									if(currentpage == null || "".equals(currentpage)){
										jsondata.put("status", 4);
										jsondata.put("message", "必填参数currentpage为空不存在");
										return SUCCESS;
									}
									
									Integer carid = taxiService.getCaridByTerminal(terminal);
									if(carid == null){
										jsondata.put("status", 8);
										jsondata.put("message", "设备号不存在");
										return SUCCESS;
									}
									
									Map map = new HashMap<String, String>();
									map.put("carid", carid+"");
									map.put("stime", stime);
									map.put("stime", stime);
									
									Map dataMap = new HashMap();
									dataMap = taxiService.getHistoryOrderList(Integer.parseInt(currentpage),Integer.parseInt(everypage),map);
									
									Integer totalCount = (Integer) dataMap.get("totalCount");
									if(totalCount == null){
										totalCount = 0;
									}
									int totalPages = (int) Math.floor((double)totalCount/Integer.parseInt(everypage))+1;
									if((totalPages-1) * Integer.parseInt(everypage) == totalCount){
										totalPages -= 1;
									}
									
									jsondata.put("totalCount", totalCount);
									jsondata.put("totalPages", totalPages);
									jsondata.put("list", dataMap.get("list"));	
									jsondata.put("status", 0);
									jsondata.put("message", "成功");
										
									return SUCCESS;
								} catch (Exception e) {
									jsondata.put("status", 5);
									jsondata.put("message", "服务器内部错误");
									logger.info("历史订单列表api接口请求异常");
									e.printStackTrace();
									return ERROR;
								}finally{
									logger.info("返回结果："+jsondata.toString());
								}
							}
						 
						 /**
							 * 订单乘客列表api接口
							 * @return
							 */
							 public String getOrderPassengersList(){
									logger.info("订单乘客列表api接口:ak="+ak+",orderid="+orderid);
									initMap();
									ApiKey apiKey = new ApiKey();
									try {
										if(ak == null){
											jsondata.put("status", 4);
											jsondata.put("message", "ak不存在或者非法");
											return SUCCESS;
										}else{
											apiKey.setKey(DateUtil.StringChange(ak));
											apiKey.setTypeid(Constant.SF_APP_TYPE);
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

										if(orderid == null || "".equals(orderid)){
											jsondata.put("status", 4);
											jsondata.put("message", "必填参数orderid为空不存在");
											return SUCCESS;
										}
										
										Map map = new HashMap<String, String>();
										map.put("orderid", orderid);
										
										jsondata.put("list", taxiService.getOrderPassengersList(map));	
										jsondata.put("status", 0);
										jsondata.put("message", "成功");
											
										return SUCCESS;
									} catch (Exception e) {
										jsondata.put("status", 5);
										jsondata.put("message", "服务器内部错误");
										logger.info("订单乘客列表api接口请求异常");
										e.printStackTrace();
										return ERROR;
									}finally{
										logger.info("返回结果："+jsondata.toString());
									}
								}
							 
		 /**
			* 拼车乘客轨迹api接口
			* @return
		  */
		public String findNotStopCarTrackPointList(){
									try {
										logger.info("拼车乘客轨迹api接口:ak="+ak+",orderid="+orderid+",stime="+stime+",etime="+etime);
										initMap();
										if (ak == null) {
											jsondata.put("status", 4);
											jsondata.put("message", "ak不存在或者非法");
											return SUCCESS;
										} else {
											apiKey.setKey(DateUtil.StringChange(ak));
											apiKey.setTypeid(Constant.SF_APP_TYPE);
											apiKey.setRequestcount(1);// 只查询密钥是启用的
											int count = apiService.apikeyIsExist(apiKey);
											if (count <= 0) {
												jsondata.put("status", 1);
												jsondata.put("message", "ak不存在或者非法");
												return SUCCESS;
											}
											// 增加API密钥请求次数
											apiService.updateApiKeyByIdKey(apiKey);
										}
										if(orderid == null || "".equals(orderid)){
											jsondata.put("status", 4);
											jsondata.put("message", "必填参数orderid为空不存在");
											return SUCCESS;
										}
										if(stime == null || "".equals(stime)){
											jsondata.put("status", 4);
											jsondata.put("message", "必填参数stime为空不存在");
											return SUCCESS;
										}
										if(etime == null || "".equals(etime)){
											jsondata.put("status", 4);
											jsondata.put("message", "必填参数etime为空不存在");
											return SUCCESS;
										}
										
										Integer carid = taxiService.getCaridByOrderid(orderid);
										if(carid == null){
											jsondata.put("status", 8);
											jsondata.put("message", "设备号不存在");
											return SUCCESS;
										}
										
										Map map = new HashMap();
										map.put("carid", carid);
										map.put("stime", stime);
										map.put("etime", etime);
										
										map.put("positiontable", "TO_VEHICLE_POSITION_"+carid);
										System.out.println("map=="+map);
										
										jsondata.put("list", taxiService.findCarTrackPointList(map));
										jsondata.put("status", 0);
										jsondata.put("message", "成功");
										
									} catch (Exception e) {
										jsondata.put("status", 5);
										jsondata.put("message", "服务器内部错误");
										logger.info("拼车乘客轨迹api接口异常");
										e.printStackTrace();
										return ERROR;
									}finally{
										logger.info("返回结果："+jsondata.toString());
									}
									return SUCCESS;
	}
	
		/**
		 * 根据车牌号获取司机信息列表
		 * @return
		 */
		public String findDriverInfoList(){
			try {
				initMap();
				logger.info("开始调用根据车牌号获取司机信息列表接口：参数：ak="+ak+",carnumber="+carnumber);
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
//				carnumber = "粤B00008";
				if(StringUtils.isEmty(carnumber)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数carnumber不能为空");
					return SUCCESS;
				}
				
				//根据车牌号获取司机信息列表
				List<DriverListInfo> dlist =  taxiService.findDriverInfoList(carnumber);
				if(dlist != null && dlist.size() > 0){
					for(DriverListInfo info : dlist){
						if(StringUtils.isNotEmty(info.getPicturepath())){
							info.setPicturepath(Constant.PLATFORM_NAME+info.getPicturepath());
						}
						
						ServiceLicense slInfo = taxiService.findServiceLicenseByDriverid(info.getId()+"");
						
						if(slInfo != null){
							String starlevel = slInfo.getStarlevel();
							String slevelstr = "";
							if("1".equals(starlevel)){
								slevelstr = "一星";
							}else if("2".equals(starlevel)){
								slevelstr = "二星";
							}else if("3".equals(starlevel)){
								slevelstr = "三星";
							}else if("4".equals(starlevel)){
								slevelstr = "四星";
							}else if("5".equals(starlevel)){
								slevelstr = "五星";
							}
							
							
							String savePath = ServletActionContext.getServletContext().getRealPath("") + Constant.FWZ_CODE_PATH;
							String filename = info.getId()+".png";
							String code = "姓名:"+slInfo.getName()+"\n服务证号:"+slInfo.getServicenumber()
										+"\n星级:"+slevelstr+"\n发证日期:"+slInfo.getFztime()
										+"\n有效期:"+slInfo.getValidity()+"年";
//							System.out.println(code);
							boolean  bo  = QRCodeEncoderHandler.encoderQRCode(code, savePath,filename);
							if(bo){
								info.setQrcodepath(Constant.DOMAIN_NAME+Constant.FWZ_CODE_PATH+filename);
							}
						}
					}
				}
				
				jsondata.put("list", dlist);
				jsondata.put("status",0);
				jsondata.put("message", "成功");
				logger.info(jsondata.toString());
				return SUCCESS;
			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info(jsondata.toString());
				e.printStackTrace();
				logger.info("请求根据车牌号获取司机信息列表失败");
				return ERROR;
			}
		}
		
		/**
		 * 修改当班司机
		 * @return
		 */
		public String updateDutyDriver(){
			try {
				initMap();
				logger.info("开始调用修改当班司机接口：参数：ak="+ak+",carnumber="+carnumber+",drivercode="+drivercode);
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
//				carnumber = "粤B00008";
				if(StringUtils.isEmty(carnumber)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数carnumber不能为空");
					return SUCCESS;
				}
				if(StringUtils.isEmty(drivercode)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数drivercode不能为空");
					return SUCCESS;
				}
				
				DriverListInfo driverListInfo = new DriverListInfo();
				driverListInfo.setCarnumber(carnumber);
				driverListInfo.setDrivercode(drivercode);
				//查询司机是否存在
				Integer dCount = taxiService.isExistDriver(driverListInfo);
				if(dCount == 0){
					jsondata.put("status", 8);
					jsondata.put("message", "驾驶员不存在或者不是该车辆的驾驶员");
					return SUCCESS;
				}
				
				//修改当班司机
				taxiService.updateDutyDriver(driverListInfo);
				
				jsondata.put("status",0);
				jsondata.put("message", "成功");
				logger.info(jsondata.toString());
				return SUCCESS;
			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info(jsondata.toString());
				e.printStackTrace();
				logger.info("请求根据车牌号获取司机信息列表失败");
				return ERROR;
			}
		}
		
								
	public String getSuccess() {
		return success;
	}


	public void setSuccess(String success) {
		this.success = success;
	}


	public Map getResult() {
		return jsondata;
	}


	public void setResult(Map jsondata) {
		this.jsondata = jsondata;
	}


	public ApiKey getApiKey() {
		return apiKey;
	}


	public void setApiKey(ApiKey apiKey) {
		this.apiKey = apiKey;
	}


	public OrderInfo getOrderInfo() {
		return orderInfo;
	}


	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}


	public Transaction getTransaction() {
		return transaction;
	}


	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}


	public TaxiService getTaxiService() {
		return taxiService;
	}


	public void setTaxiService(TaxiService taxiService) {
		this.taxiService = taxiService;
	}


	public ApiService getApiService() {
		return apiService;
	}


	public void setApiService(ApiService apiService) {
		this.apiService = apiService;
	}


	public String getAk() {
		return ak;
	}


	public void setAk(String ak) {
		this.ak = ak;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getPassengerName() {
		return passengerName;
	}


	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}


	public String getTelPhone() {
		return telPhone;
	}


	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getUseTime() {
		return useTime;
	}


	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}


	public String getCarpool() {
		return carpool;
	}


	public void setCarpool(String carpool) {
		this.carpool = carpool;
	}


	public String getCarpoolPersonNum() {
		return carpoolPersonNum;
	}


	public void setCarpoolPersonNum(String carpoolPersonNum) {
		this.carpoolPersonNum = carpoolPersonNum;
	}


	public String getUseAddr() {
		return useAddr;
	}


	public void setUseAddr(String useAddr) {
		this.useAddr = useAddr;
	}


	public String getDestinationAddr() {
		return destinationAddr;
	}


	public void setDestinationAddr(String destinationAddr) {
		this.destinationAddr = destinationAddr;
	}





	public String getElng() {
		return elng;
	}


	public void setElng(String elng) {
		this.elng = elng;
	}


	public String getElat() {
		return elat;
	}


	public void setElat(String elat) {
		this.elat = elat;
	}


	public String getOrderType() {
		return orderType;
	}


	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public Map getJsondata() {
		return jsondata;
	}


	public void setJsondata(Map jsondata) {
		this.jsondata = jsondata;
	}


	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getRange() {
		return range;
	}


	public void setRange(String range) {
		this.range = range;
	}


	public String getMaxList() {
		return maxList;
	}


	public void setMaxList(String maxList) {
		this.maxList = maxList;
	}


	public String getVname() {
		return vname;
	}


	public void setVname(String vname) {
		this.vname = vname;
	}


	public String getStartUtc() {
		return startUtc;
	}


	public void setStartUtc(String startUtc) {
		this.startUtc = startUtc;
	}


	public String getEndUtc() {
		return endUtc;
	}


	public void setEndUtc(String endUtc) {
		this.endUtc = endUtc;
	}


	public String getSorttype() {
		return sorttype;
	}


	public void setSorttype(String sorttype) {
		this.sorttype = sorttype;
	}


	public String getIsup() {
		return isup;
	}


	public void setIsup(String isup) {
		this.isup = isup;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getOrderid() {
		return orderid;
	}


	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}


	public String getOuttradeno() {
		return outtradeno;
	}


	public void setOuttradeno(String outtradeno) {
		this.outtradeno = outtradeno;
	}


	public String getCost() {
		return cost;
	}


	public void setCost(String cost) {
		this.cost = cost;
	}


	public String getTradestatus() {
		return tradestatus;
	}


	public void setTradestatus(String tradestatus) {
		this.tradestatus = tradestatus;
	}


	public String getTerminal() {
		return terminal;
	}


	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}


	public String getStime() {
		return stime;
	}


	public void setStime(String stime) {
		this.stime = stime;
	}


	public String getEtime() {
		return etime;
	}


	public void setEtime(String etime) {
		this.etime = etime;
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

	public String getSlevel() {
		return slevel;
	}


	public void setSlevel(String slevel) {
		this.slevel = slevel;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	public String getSlng() {
		return slng;
	}


	public void setSlng(String slng) {
		this.slng = slng;
	}


	public String getSlat() {
		return slat;
	}


	public void setSlat(String slat) {
		this.slat = slat;
	}


	public String getDrivercode() {
		return drivercode;
	}


	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getFilearr() {
		return filearr;
	}


	public void setFilearr(String filearr) {
		this.filearr = filearr;
	}


	public String getEvalevel() {
		return evalevel;
	}


	public void setEvalevel(String evalevel) {
		this.evalevel = evalevel;
	}


	public String getCellphone() {
		return cellphone;
	}


	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getSaddress() {
		return saddress;
	}


	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}


	public String getEaddress() {
		return eaddress;
	}


	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
	}


	public String getSummile() {
		return summile;
	}


	public void setSummile(String summile) {
		this.summile = summile;
	}


	public String getTotalfee() {
		return totalfee;
	}


	public void setTotalfee(String totalfee) {
		this.totalfee = totalfee;
	}


	public String getOrdersatus() {
		return ordersatus;
	}


	public void setOrdersatus(String ordersatus) {
		this.ordersatus = ordersatus;
	}


	public String getSeq() {
		return seq;
	}


	public void setSeq(String seq) {
		this.seq = seq;
	}


	public String getPassagename() {
		return passagename;
	}


	public void setPassagename(String passagename) {
		this.passagename = passagename;
	}


	public String getCarnumber() {
		return carnumber;
	}


	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}	
	
}
