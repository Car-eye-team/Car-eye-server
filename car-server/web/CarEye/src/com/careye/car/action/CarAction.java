/**
 * Description: car-eye车辆管理平台
 * 文件名：CarAction.java
 * 版本信息：1.0
 * 日期：2014-5-26
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.car.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarDriver;
import com.careye.car.domain.CarInfo;
import com.careye.car.domain.CarStatus;
import com.careye.car.domain.ControlRecord;
import com.careye.car.domain.Drivercode;
import com.careye.car.domain.OperateCertificate;
import com.careye.car.service.CarService;
import com.careye.common.domain.CarDetail;
import com.careye.common.service.CmssService;
import com.careye.common.service.MenuTreeService;
import com.careye.common.service.TerminalDeviceInfoService;
import com.careye.constant.ConfigProperties;
import com.careye.constant.Constant;
import com.careye.constant.ServiceConfig;
import com.careye.dssservice.service.DssService;
import com.careye.gtalk.domain.GtalkInfo;
import com.careye.gtalk.service.GtalkService;
import com.careye.mq.HandleUtil;
import com.careye.sysset.domain.CarType;
import com.careye.sysset.domain.CarUse;
import com.careye.sysset.domain.DeviceType;
import com.careye.system.domain.Bloc;
import com.careye.system.domain.BlocUser;
import com.careye.system.service.OrgazicationDeptService;
import com.careye.utils.Blowfish;
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;
import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：CarAction
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-03-09 下午01:47:32
 * @修改人：zhangrong
 * @修改时间：2015-03-09 下午01:47:32
 * @修改备注：
 * @version 1.0
 */
public class CarAction extends BasePageAction{
	
	private static final Logger logger = Logger.getLogger(CarAction.class);
	private CarService carService;
	private MenuTreeService menuTreeService;
	private TerminalDeviceInfoService terminalDeviceInfoService;
	private DssService dssService;
	private OrgazicationDeptService orgazicationDeptService;
	private CmssService cmssService;
	private GtalkService gtalkService;

	private CarInfo carInfo;
	private CarDriver carDriver;
	private OperateCertificate operateCertificate;
	
	private Map<String, String> queryParams;
	private Map<String,Object> jsondata;
	private List<CarStatus> carListWithTrack=Collections.emptyList();
	private List<CarStatus> clist = Collections.emptyList();

	private CarDetail data;
	private ControlRecord controlRecord;

	private Map result;
	private String success;
	private List list;
	private String ids;
	private String ters;
	private BlocUser user;
	private int su;

	private String flag;
	private String status;
	private String carnumber;
	private String cartype;
	private String devicetype;
	private String caruse;
	private String carstatus;
	private String deptid;
	private String stime;
	private String etime;
	private String carid;
	private String terminal;
	private String online;
	private String query;
	private String blocid;
	
	private String controltype;
	private String type;
	
	private  String  fileName;
	private  String controltext;
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 车辆下拉列表
	 * @return
	 */
	public String selectCarList(){
		initMap();
		try {
			
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(carInfo == null){
				carInfo = new CarInfo();
			}
			
			Integer userid = null;
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				userid = SessionUtils.getUserId();
				carInfo.setUserid(userid);
			}

			if(StringUtils.isNotEmty(blocid)){
				carInfo.setBlocid(Integer.parseInt(blocid));
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				carInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase().trim());
			}
			if(StringUtils.isNotEmty(query)){
				carInfo.setCarnumber(new String(query.getBytes("ISO-8859-1"), "UTF-8"));
			}
			List list = carService.selectCarList(carInfo);
			result.put("list", list);
		} catch (Exception e) {
			logger.error("CarAction 的方法 selectCarList 执行出错，原因：" + e, e);
		}
		return SUCCESS;
	}
	
	
	/**
	 * 获取设备类型列表
	 * @return
	 */
	public String getDeviceTypeList(){

		initMap();
		if(carInfo==null){
			carInfo=new CarInfo();
		}
		try {
			List<DeviceType> list = carService.getDeviceTypeList();
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction的方法 getDeviceTypeList执行出错，原因：" + e, e);
			return ERROR;
		}

	}

	/**
	 * 获取当班司机列表
	 * @return
	 */
	public String getDrivercodeList(){
		
		initMap();
		if(carInfo==null){
			carInfo=new CarInfo();
		}
		try {
			if(StringUtils.isNotEmty(carnumber)){
//				URLDecoder.decode(carnumber,"UTF-8").toUpperCase().trim()
//				new String(carnumber.getBytes("ISO-8859-1"),"utf-8")
				carInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase().trim());
			}
			List<Drivercode> list = carService.getDrivercodeList(carInfo);
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction的方法 getDrivercodeList执行出错，原因：" + e, e);
			return ERROR;
		}
		
	}
	

	/**
	 * 获取车辆类型列表
	 * @return
	 */
	public String getCarTypeList(){
		
		initMap();
		if(carInfo==null){
			carInfo=new CarInfo();
		}
		try {
			List<CarType> list = carService.getCarTypeList();
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction的方法 getCarTypeList执行出错，原因：" + e, e);
			return ERROR;
		}
		
	}
	
	/**
	 * 获取车辆用途列表
	 * @return
	 */
	public String getCarUseList(){
		
		initMap();
		if(carInfo==null){
			carInfo=new CarInfo();
		}
		try {
			List<CarUse> list = carService.getCarUseList();
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction的方法 getCarUseList执行出错，原因：" + e, e);
			return ERROR;
		}
		
	}

	/**
	 * 司机列表
	 * @return
	 */
	public String selectCarDriverList(){
		initMap();
		if(carDriver==null){
			carDriver=new CarDriver();
		}
		if(SessionUtils.getUser() == null){
			return SUCCESS;
		}
		if(!SessionUtils.getUser().getLoginname().equals("admin")){
			carDriver.setUserid(SessionUtils.getUserId());
		}	
		List<CarDriver> list=carService.selectCarDriver(carDriver);
		result.put("list", list);
		return SUCCESS;
	}

	/**
	 * 根据车辆id得到设备号
	 * @return
	 */
	public String getTerminalByCarid(){
		initMap();
		if(carid==null){
			return ERROR;
		}
		try {
			String terminal = carService.getCarInfoCarId(Integer.parseInt(carid)).getTerminal();
			result.put("terminal", terminal);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction的方法 getTerminalByCarid执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 根据车辆号得到车辆id
	 * @return
	 */
	public String getCaridByCarnumber(){
		initMap();
		if(carnumber==null){
			return ERROR;
		}
		try {
			String carid = carService.getCarInfoByCarNumber(carnumber).getId()+"";
			result.put("carid", carid);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction的方法 getTerminalByCarid执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	
	/**
	 * 根据车辆号得到车辆信息
	 * @return
	 */
	public String getCarInfoByCarnumber(){
			initMap();
			if(carnumber==null){
				return ERROR;
			}
		try {
			carInfo= carService.getCarInfoByCarNumber(carnumber);
			result.put("carInfo", carInfo);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction的方法 getCarInfoByCarnumber执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 车牌号列表
	 * @return
	 */
	public String selectCarNumberList(){
		initMap();
		if(carInfo==null){
			carInfo=new CarInfo();
		}
		if(SessionUtils.getUser() == null){
			return SUCCESS;
		}
		if(!SessionUtils.getUser().getLoginname().equals("admin")){
			carInfo.setUserid(SessionUtils.getUserId());
		}	
		List<CarInfo> list=carService.selectCarNumber(carInfo);
		result.put("list", list);
		return SUCCESS;
	}



	/**
	 * 分页查询车辆信息
	 * @return
	 */
	public String queryCarInfoList() {

		try {
			initMap();
			if(carInfo==null){
				carInfo=new CarInfo();
			}

			if(StringUtils.isNotEmty(carnumber)){
				carInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase().trim());
			}
			if(StringUtils.isNotEmty(devicetype)){
				carInfo.setDevicetype(Integer.parseInt(devicetype));
			}
			if(StringUtils.isNotEmty(caruse)){
				carInfo.setCaruse(Integer.parseInt(caruse));
			}
			if(StringUtils.isNotEmty(cartype)){
				carInfo.setCartype(Integer.parseInt(cartype));
			}
			if(StringUtils.isNotEmty(carstatus)){
				Integer status = Integer.parseInt(carstatus);
				if(status == 7){
					carInfo.setOnline(1);
				}else{
					carInfo.setCarstatus(status);
				}
			}
			if(StringUtils.isNotEmty(deptid)){
				carInfo.setBlocid(Integer.parseInt(deptid));
			}
			if(StringUtils.isNotEmty(stime)){
				carInfo.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				carInfo.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			
			if(StringUtils.isNotEmty(terminal)){
				carInfo.setTerminal(URLDecoder.decode(terminal,"UTF-8").trim());
			}
			
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				carInfo.setUserid(SessionUtils.getUserId());
			}
			
			result=carService.selectCheckCarInfo(this.getPage(),this.getLimit(), carInfo);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction的方法 queryCarInfoList执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 添加或修改车辆信息
	 * @param carInfo
	 * @return
	 */
	public String saveCarInfo(){
		try {
			initMap();
			Integer su = carService.saveCarInfo(carInfo);
			if(carInfo.getPreblocid() == null){	//添加
				result.put("deptid", carInfo.getBlocid());
			}else{	//修改
				int deptid = carInfo.getBlocid().intValue();
				int predeptid = carInfo.getPreblocid().intValue();
				if(deptid != predeptid){
					result.put("deptid", deptid);
					result.put("predeptid", predeptid);
				}
			}
			//保存终端数据进入mysql数据库
			if("1".equals(ConfigProperties.CMSS_STATUS)){
				Bloc org = orgazicationDeptService.quertDeptById(carInfo.getBlocid());
				carInfo.setBlocname(org.getBloc_name());
				
				cmssService.saveTer(carInfo);
			}
			
			/*if(ConfigProperties.GTALK_STATUS != null && "1".equals(ConfigProperties.GTALK_STATUS)){
				//判断gtalk用户是否存在，存在不操作
				int count = gtalkService.getOfuserCount(carInfo.getTerminal());
				if(count<=0){
					//保存用户信息至即时聊天的用户表中
					GtalkInfo gtalkInfo =  new GtalkInfo();
					gtalkInfo.setUsername(carInfo.getTerminal());
					gtalkInfo.setName(carInfo.getCarnumber());
					Blowfish blowfish = new Blowfish(Constant.OPENFIRE_PASSWORD_KEY);
					String password = "";
					if(carInfo.getTerminal().length() < 6){
						password = carInfo.getTerminal();
					}else{
						password = carInfo.getTerminal().substring(carInfo.getTerminal().length()-6, carInfo.getTerminal().length());
					}
					String encryptedPassword = blowfish.encryptString(password);
					gtalkInfo.setEncryptedPassword(encryptedPassword);
					gtalkInfo.setCreationDate("00"+System.currentTimeMillis());
					gtalkInfo.setModificationDate("00"+System.currentTimeMillis());
					gtalkService.addOfuser(gtalkInfo);
				}
			}*/
			
			result.put("su", su);
			this.success="true";
			return SUCCESS;

		} catch (Exception e) {
			this.success="false";
			logger.error("CarAction的方法 saveCarInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	

	/**
	 * 重新统计组织机构
	 * @param carInfo
	 * @return
	 */
	public String updateDeptidCarCount(){
		try {
			initMap();
			if(ids == null){
				return ERROR;
			}
			String deptid[] = ids.split(",");
			for (int i = 0; i < deptid.length; i++) {
//				carService.updateDeptTotal(Integer.parseInt(deptid[i]));
				
				//先统计上级所有的组织机构id
				List<Integer> deptidlist = carService.getUpDeptlist(Integer.parseInt(deptid[i]));
				
				//重新统计组织机构下面的车辆数
				for(int j=0;j<deptidlist.size();j++){
					
					//根据deptid获车辆总数
					int total = carService.getCarInfoSum(deptidlist.get(j));
					//根据deptid获取离线总数
					int caroffline = carService.getOffLineCarInfoSum(deptidlist.get(j));
					//根据deptid获取长离总数
					int longoffline = carService.getLongOffLineCarInfoSum(deptidlist.get(j));
					
					//更新
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("total", total);
					map.put("caroffline", caroffline);
					map.put("longoffline", longoffline);
					map.put("deptid", deptidlist.get(j));
					
					ServiceConfig.orgazicationDeptService.updateDeptCarNum(map);
					
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction的方法 updateDeptidCarCount执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	


	/**
	 * 删除车辆信息
	 * @param carInfo
	 * @return
	 */
	public String deleteCarInfo(){
		try {
			initMap();
			String id[] = ids.split(",");
			String ter[] = ters.split(",");
			for (int i = 0; i < id.length; i++) {
				CarInfo carInfo = carService.getCarInfoCarId(Integer.parseInt(id[i]));
				carService.deleteCarInfo(Integer.parseInt(id[i]));
				//删除车辆通知通讯平台
				HandleUtil.operateCar(carInfo.getUsertype(),carInfo.getTerminal(), 3, HandleUtil.getSerialId(), carInfo.getCarnumber());
				
				//删除终端数据mysql数据库
				if("1".equals(ConfigProperties.CMSS_STATUS)){
					cmssService.deleteTer(carInfo.getTerminal());
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction的方法 deleteCarInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**转移车辆**/
	@SuppressWarnings("unchecked")
	public String carMove() {
		try {
			initMap();
			if (deptid == null || ids == null)
				return ERROR;
			
			 String deptids = carService.updateCarDept(deptid,ids);
			 result.put("deptids", deptids);
		    
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction 的方法 carMove 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 读取车辆相信信息
	 * @return
	 */
	public String readCarDetailInfo(){
		try {
			initMap();
			if(carid == null){
				return ERROR;
			}
			data = carService.readCarDetailInfo(Integer.parseInt(carid.substring(1)));
			if(data == null){
				data = new CarDetail();
			}else{
				if(data.getDriversex() != null){
					data.setDriversex("1".equals(data.getDriversex())?"男":"女");
				}
				if(data.getNowstatus() != null){
					data.setNowstatusname(1 == (data.getNowstatus())?"正常":"注销");
				}
				if(data.getColor() != null){
					if(data.getColor().equals("1")){
						data.setCarnumbercolorname("蓝色");
					}else if(data.getColor().equals("2")){
						data.setCarnumbercolorname("黄色");
					}else if(data.getColor().equals("3")){
						data.setCarnumbercolorname("黑色");
					}else if(data.getColor().equals("4")){
						data.setCarnumbercolorname("白色");
					}else if(data.getColor().equals("5")){
						data.setCarnumbercolorname("红色");
					}else if(data.getColor().equals("6")){
						data.setCarnumbercolorname("紫色");
					}else if(data.getColor().equals("9")){
						data.setCarnumbercolorname("其他");
					}
				}else{
					data.setCarnumbercolorname("");
				}
				
				if(data.getCarcolor() != null){
					if(data.getCarcolor().equals("1")){
						data.setCarcolorname("蓝色");
					}else if(data.getCarcolor().equals("2")){
						data.setCarcolorname("黄色");
					}else if(data.getCarcolor().equals("3")){
						data.setCarcolorname("黑色");
					}else if(data.getCarcolor().equals("4")){
						data.setCarcolorname("白色");
					}else if(data.getCarcolor().equals("5")){
						data.setCarcolorname("红色");
					}else if(data.getCarcolor().equals("6")){
						data.setCarcolorname("紫色");
					}else if(data.getCarcolor().equals("7")){
						data.setCarcolorname("绿黄");
					}else if(data.getCarcolor().equals("8")){
						data.setCarcolorname("青绿加亮银");
					}else if(data.getCarcolor().equals("9")){
						data.setCarcolorname("蓝黄");
					}else if(data.getCarcolor().equals("10")){
						data.setCarcolorname("艳绿加银灰");
					}else if(data.getCarcolor().equals("11")){
						data.setCarcolorname("绿色");
					}else if(data.getCarcolor().equals("12")){
						data.setCarcolorname("晶蓝加亮银");
					}else if(data.getCarcolor().equals("13")){
						data.setCarcolorname("灰色");
					}else if(data.getCarcolor().equals("14")){
						data.setCarcolorname("墨绿色");
					}else if(data.getCarcolor().equals("15")){
						data.setCarcolorname("深蓝色");
					}else if(data.getCarcolor().equals("16")){
						data.setCarcolorname("棕色");
					}else if(data.getCarcolor().equals("99")){
						data.setCarcolorname("其他");
					}
				}else{
					data.setCarcolorname("");
				}
				
				if(data.getFueltype() != null){
					if(data.getFueltype() == 1){
						data.setFueltypename("单一汽油");
					}else if(data.getFueltype()== 2){
						data.setFueltypename("单一柴油");
					}else if(data.getFueltype()== 3){
						data.setFueltypename("燃气");
					}else if(data.getFueltype()== 4){
						data.setFueltypename("双燃料");
					}else if(data.getFueltype()== 5){
						data.setFueltypename("电动");
					}else if(data.getFueltype()== 9){
						data.setFueltypename("其他");
					}
				}else{
					data.setFueltypename("");
				}
				
			}
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction 的方法 readCarDetailInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 得到车辆相信信息
	 * @return
	 */
	public String getCarDetailInfo(){
		try {
			initMap();
			if(this.carnumber == null){
				return ERROR;
			}
			data = carService.getCarDetailInfo(carnumber);
			if(data == null){
				data = new CarDetail();
			}
			result.put("data", data);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction 的方法 getCarDetailInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
     * Excel导出
     * @throws IOException
     */
	public void exportExcel(){
		try {
		 fileName="车辆信息"; 
    	 HSSFWorkbook book = new HSSFWorkbook();
         Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
        ExcelDownWay exceldownway= new ExcelDownWay();
         
         //2.设置列宽（列数要对应上）
         String str="7,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,25,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20";
         List<String> numberList=Arrays.asList(str.split(","));
         sheet= exceldownway.setColumnWidth(sheet,numberList);
         
         sheet.setDefaultRowHeight((short) 18);
         Row titleRow= sheet.createRow(0);
         titleRow.setHeightInPoints(20);
         
        if(carInfo==null){
				carInfo=new CarInfo();
		}
		if(StringUtils.isNotEmty(carnumber)){
			carInfo.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"),"utf-8").toUpperCase().trim());
		}
		if(StringUtils.isNotEmty(devicetype)){
			carInfo.setDevicetype(Integer.parseInt(devicetype));
		}
		if(StringUtils.isNotEmty(caruse)){
			carInfo.setCaruse(Integer.parseInt(caruse));
		}
		if(StringUtils.isNotEmty(cartype)){
			carInfo.setCartype(Integer.parseInt(cartype));
		}
		if(StringUtils.isNotEmty(carstatus)){
			Integer status = Integer.parseInt(carstatus);
			if(status == 7){
				carInfo.setOnline(1);
			}else{
				carInfo.setCarstatus(status);
			}
		}
		if(StringUtils.isNotEmty(deptid)){
			carInfo.setBlocid(Integer.parseInt(deptid));
		}
		if(StringUtils.isNotEmty(stime)){
			carInfo.setStime(new String(stime.getBytes("ISO-8859-1"),"utf-8"));
		}
		if(StringUtils.isNotEmty(etime)){
			carInfo.setEtime(new String(etime.getBytes("ISO-8859-1"),"utf-8"));
		}
		
		if(StringUtils.isNotEmty(terminal)){
			carInfo.setTerminal(new String(terminal.getBytes("ISO-8859-1"),"utf-8").trim());
		}
		
		if(SessionUtils.getUser() == null){
			return;
		}
		if(!SessionUtils.getUser().getLoginname().equals("admin")){
			carInfo.setUserid(SessionUtils.getUserId());
		}
         list= carService.exportCarInfo(carInfo);   //得到Excel数据
         
         titleRow.createCell(0).setCellValue("序号");
         titleRow.createCell(1).setCellValue("集团");
         titleRow.createCell(2).setCellValue("车牌号");
         titleRow.createCell(3).setCellValue("终端设备号");
         titleRow.createCell(4).setCellValue("车辆状态");
         titleRow.createCell(5).setCellValue("设备类型");
         titleRow.createCell(6).setCellValue("设备型号");
         titleRow.createCell(7).setCellValue("车辆类型");
         titleRow.createCell(8).setCellValue("车主手机号");
         titleRow.createCell(9).setCellValue("密码");
         titleRow.createCell(10).setCellValue("车辆用途");
         titleRow.createCell(11).setCellValue("车牌颜色");
         titleRow.createCell(12).setCellValue("省");
         titleRow.createCell(13).setCellValue("市");
         titleRow.createCell(14).setCellValue("县/区");
         titleRow.createCell(15).setCellValue("车架号");
         titleRow.createCell(16).setCellValue("发动机号");
         titleRow.createCell(17).setCellValue("车身颜色");
         titleRow.createCell(18).setCellValue("生产厂家");
         titleRow.createCell(19).setCellValue("车辆型号");
         titleRow.createCell(20).setCellValue("经营权号");
         titleRow.createCell(21).setCellValue("车主姓名");
         titleRow.createCell(22).setCellValue("车主地址");
         titleRow.createCell(23).setCellValue("安装日期");
         titleRow.createCell(24).setCellValue("登记日期");
         titleRow.createCell(25).setCellValue("创建时间");
         titleRow.createCell(26).setCellValue("备注");
         titleRow.createCell(27).setCellValue("核定载客");
         titleRow.createCell(28).setCellValue("所有权性质");
         titleRow.createCell(29).setCellValue("入户日期");
         titleRow.createCell(30).setCellValue("出厂日期");
         titleRow.createCell(31).setCellValue("燃料类型");
         titleRow.createCell(32).setCellValue("发动机排量");
         titleRow.createCell(33).setCellValue("排放标准");
         titleRow.createCell(34).setCellValue("当前状态");
         titleRow.createCell(35).setCellValue("合同承包期(年)");
         titleRow.createCell(36).setCellValue("财产险");
         titleRow.createCell(37).setCellValue("交强险");
         titleRow.createCell(38).setCellValue("乘座险");
         titleRow.createCell(39).setCellValue("三责险");
         titleRow.createCell(40).setCellValue("车损险");
         titleRow.createCell(41).setCellValue("电子标签状态");
         titleRow.createCell(42).setCellValue("设备手机号");
         titleRow.createCell(43).setCellValue("视频编号");
         
         for(int i=0;i<titleRow.getLastCellNum();i++){
        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
         }
         
         
         if(list.size()>0){
             for(int i=0;i<list.size();i++){
                 int  index=i+1;
                 Row contentRow= sheet.createRow(index);
                 contentRow.createCell(0).setCellValue(index);
                 CarInfo cInfo= (CarInfo) list.get(i);
                 contentRow.createCell(1).setCellValue(cInfo.getBlocname());
                 contentRow.createCell(2).setCellValue(cInfo.getCarnumber());
                 contentRow.createCell(3).setCellValue(cInfo.getTerminal());
  //               contentRow.createCell(4).setCellValue(cInfo.getDevicenumber());
                 String statusInteger = cInfo.getCarstatus()+"";
                 String statusString = "";
                 if("7".equals(statusInteger)){
                	 statusString = "在线";
                 }else if("1".equals(statusInteger)){
                	 statusString = "长时间离线";
                 }else if("2".equals(statusInteger)){
                	 statusString = "离线";
                 }else if("3".equals(statusInteger)){
                	 statusString = "熄火";
                 }else if("5".equals(statusInteger)){
                	 statusString = "行驶";
                 }else if("4".equals(statusInteger)){
                	 statusString = "停车";
                 }else if("6".equals(statusInteger)){
                	 statusString = "报警";
                 }else if("8".equals(statusInteger)){
                	 statusString = "未定位";
                 }
                 contentRow.createCell(4).setCellValue(statusString);
                 contentRow.createCell(5).setCellValue(cInfo.getTypename());
                 contentRow.createCell(6).setCellValue(cInfo.getDevicemodel());
                 contentRow.createCell(7).setCellValue(cInfo.getCartypename());
                 contentRow.createCell(8).setCellValue(cInfo.getPhone());
                 contentRow.createCell(9).setCellValue(cInfo.getPassword());
                 contentRow.createCell(10).setCellValue(cInfo.getUsename());
                 String color = cInfo.getColor();
                 String colorString = "";
                 if("1".equals(color)){
                	 colorString = "蓝色";
                 }else if("2".equals(color)){
                	 colorString = "黄色";
                 }else if("3".equals(color)){
                	 colorString = "黑色";
                 }else if("4".equals(color)){
                	 colorString = "白色";
                 }else if("5".equals(color)){
                	 colorString = "红色";
                 }else if("6".equals(color) ){
                	 colorString = "紫色";
                 }else if("9".equals(color)){
                	 colorString = "其他";
                 }
                 contentRow.createCell(11).setCellValue(colorString);                
                 contentRow.createCell(12).setCellValue(cInfo.getProvincename());
                 contentRow.createCell(13).setCellValue(cInfo.getCityname());
                 contentRow.createCell(14).setCellValue(cInfo.getDistrictname());
                 contentRow.createCell(15).setCellValue(cInfo.getFramenumber());
                 contentRow.createCell(16).setCellValue(cInfo.getEnginenumber());
                 String carcolor = cInfo.getCarcolor();
                 String carcolorString = "";
                 if("1".equals(carcolor)){
                	 carcolorString = "蓝色";
                 }else if("2".equals(carcolor)){
                	 carcolorString = "黄色";
                 }else if("3".equals(carcolor)){
                	 carcolorString = "黑色";
                 }else if("4".equals(carcolor)){
                	 carcolorString = "白色";
                 }else if("5".equals(carcolor)){
                	 carcolorString = "红色";
                 }else if("6".equals(carcolor) ){
                	 carcolorString = "紫色";
                 }else if("7".equals(carcolor) ){
                	 carcolorString = "绿黄";
                 }else if("8".equals(carcolor) ){
                	 carcolorString = "青绿加亮银";
                 }else if("9".equals(carcolor) ){
                	 carcolorString = "蓝黄";
                 }else if("10".equals(carcolor) ){
                	 carcolorString = "艳绿加银灰";
                 }else if("11".equals(carcolor) ){
                	 carcolorString = "绿色";
                 }else if("12".equals(carcolor) ){
                	 carcolorString = "晶蓝加亮银";
                 }else if("13".equals(carcolor) ){
                	 carcolorString = "灰色";
                 }else if("14".equals(carcolor) ){
                	 carcolorString = "墨绿色";
                 }else if("15".equals(carcolor) ){
                	 carcolorString = "深蓝色";
                 }else if("16".equals(carcolor) ){
                	 carcolorString = "棕色";
                 }else if("99".equals(carcolor)){
                	 carcolorString = "其他";
                 }
                 contentRow.createCell(17).setCellValue(carcolorString);
                 contentRow.createCell(18).setCellValue(cInfo.getFactory());
                 contentRow.createCell(19).setCellValue(cInfo.getCarmodel());
                 contentRow.createCell(20).setCellValue(cInfo.getManagenumber());
                 contentRow.createCell(21).setCellValue(cInfo.getOwnername());
                 contentRow.createCell(22).setCellValue(cInfo.getOwneraddress());
                 contentRow.createCell(23).setCellValue(cInfo.getInstalltime());
                 contentRow.createCell(24).setCellValue(cInfo.getRegistertime());
                 contentRow.createCell(25).setCellValue(cInfo.getCreatetime());
                 contentRow.createCell(26).setCellValue(cInfo.getRemark());
                 contentRow.createCell(27).setCellValue(cInfo.getSeatnumber());
                 contentRow.createCell(28).setCellValue(cInfo.getOwnership());
                 contentRow.createCell(29).setCellValue(cInfo.getEntertime());
                 contentRow.createCell(30).setCellValue(cInfo.getFactorytime());
                 String fueltype = cInfo.getFueltype()+"";
                 String fueltypestr = "";
                 if("1".equals(fueltype)){
                	 fueltypestr = "单一汽油";
                 }else if("2".equals(fueltype)){
                	 fueltypestr = "单一柴油";
                 }else if("3".equals(fueltype)){
                	 fueltypestr = "燃气";
                 }else if("4".equals(fueltype)){
                	 fueltypestr = "双燃料";
                 }else if("5".equals(fueltype)){
                	 fueltypestr = "电动";
                 }else if("9".equals(fueltype)){
                	 fueltypestr = "其他";
                 }
                 contentRow.createCell(31).setCellValue(fueltypestr);
                 contentRow.createCell(32).setCellValue(cInfo.getEnginecapacity());
                 contentRow.createCell(33).setCellValue(cInfo.getCapacitystandard());
                 String nowstatus = cInfo.getNowstatus()+"";
                 String nowstatusstr = "";
                 if("1".equals(nowstatus)){
                	 nowstatusstr = "正常";
                 }else if("2".equals(nowstatus)){
                	 nowstatusstr = "注销";
                 }
                 contentRow.createCell(34).setCellValue(nowstatusstr);
                 contentRow.createCell(35).setCellValue(cInfo.getContracttime());
                 contentRow.createCell(36).setCellValue(cInfo.getProinsure());
                 contentRow.createCell(37).setCellValue(cInfo.getAccinsure());
                 contentRow.createCell(38).setCellValue(cInfo.getRidinsure());
                 contentRow.createCell(29).setCellValue(cInfo.getCominsure());
                 contentRow.createCell(40).setCellValue(cInfo.getDlwinsure());
                 contentRow.createCell(41).setCellValue(cInfo.getElectagstatus());
                 contentRow.createCell(42).setCellValue(cInfo.getTerphone());
                 contentRow.createCell(43).setCellValue(cInfo.getVediono());
             }
         }
         
         new ExcelDownWay().getCommonExcelListWay(book,fileName);
        
		} catch (Exception e) {
			logger.error("CarAction的方法 exportExcel执行出错，原因：" + e, e);
		}
	}
	
	/**
	 * 不分页得到车辆历史位置记录（去除停车重复记录，加上停留时间）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findNotStopCarTrackPointList() {
		initMap();
		if (queryParams == null){
			return SUCCESS;
		}
		if(jsondata == null) {
			jsondata = new HashMap<String,Object>();
		}
		try {
			carnumber = URLDecoder.decode(queryParams.get("carnumber"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String carid = carService.getCarInfoByCarNumber(carnumber).getId().toString();
		queryParams.put("carid", carid);
		queryParams.put("positiontable", "TO_VEHICLE_POSITION_"+carid);
		System.out.println("queryParams=="+queryParams);
		clist = carService.findCarTrackPointList(queryParams);
		if(clist.size() > 0){
			for(int i=0;i<clist.size();i++){
				if(clist.get(i).getMileage() != null && clist.get(i).getMileage().startsWith(".")){
					clist.get(i).setMileage("0"+clist.get(i).getMileage());
				}
				if(clist.get(i).getSummileage() != null && clist.get(i).getSummileage().startsWith(".")){
					clist.get(i).setSummileage("0"+clist.get(i).getSummileage());
				}
				if(clist.get(i).getCarstatus()!=null && clist.get(i).getCarstatus() == 4){
					if((i+1) <= (clist.size()-1)){
						if(clist.get(i+1).getCarstatus()!=null && clist.get(i+1).getCarstatus() == 4){
							clist.get(i).setDelete(1);
						}
					}
				}
			}
			Iterator ite = clist.iterator();
			while(ite.hasNext()){
				CarStatus carStatus = (CarStatus)ite.next();
				if(carStatus.getDelete() !=null && carStatus.getDelete() == 1){
					ite.remove();
				}else{
					if(carstatus !=null && !"".equals(carstatus)&& 
							!"null".equals(carstatus) && !"undefined".equals(carstatus)){
						if(carStatus.getCarstatus() ==null || (carStatus.getCarstatus().intValue() != Integer.parseInt(carstatus))){
							ite.remove();
						}
					}
				}
			}
			Collections.reverse(clist);
			for(int i=0;i<clist.size();i++){
				if(clist.get(i).getCarstatus()!=null && clist.get(i).getCarstatus() == 4){
					if((i+1) <= (clist.size()-1)){
						if(clist.get(i+1).getCarstatus()!=null && clist.get(i+1).getCarstatus() != 4){
							clist.get(i).setStoptime((DateUtil.dateDiffStr(clist.get(i).getCreatetime(), clist.get(i+1).getCreatetime())));
							clist.get(i).setStime(clist.get(i).getCreatetime());
							clist.get(i).setEtime(clist.get(i+1).getCreatetime());
						}
					}
				}
			}
		}
		
		result.put("list", clist);
		return SUCCESS;
	}
	
	/**
	 * 导出车辆历史位置记录（去除停车重复记录，加上停留时间）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void exportTrackExcel() {
         
		if (queryParams == null){
			return;
		}
		if(jsondata == null) {
			jsondata = new HashMap<String,Object>();
		}
		try {
//			carnumber = URLDecoder.decode(queryParams.get("carnumber"),"UTF-8");
			carnumber = new String(queryParams.get("carnumber").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		fileName="车辆("+carnumber+")历史位置信息"; 
    	HSSFWorkbook book = new HSSFWorkbook();
        Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
        Row titleRow= sheet.createRow(0);
		
		String carid = carService.getCarInfoByCarNumber(carnumber).getId().toString();
		queryParams.put("carid", carid);
		queryParams.put("positiontable", "TO_VEHICLE_POSITION_"+carid);
		System.out.println("queryParams=="+queryParams);
		clist = carService.findCarTrackPointList(queryParams);
		if(clist.size() > 0){
			for(int i=0;i<clist.size();i++){
				if(clist.get(i).getCarstatus()!=null && clist.get(i).getCarstatus() == 4){
					if((i+1) <= (clist.size()-1)){
						if(clist.get(i+1).getCarstatus()!=null && clist.get(i+1).getCarstatus() == 4){
							clist.get(i).setDelete(1);
						}
					}
				}
			}
			Iterator ite = clist.iterator();
			while(ite.hasNext()){
				CarStatus carStatus = (CarStatus)ite.next();
				if(carStatus.getDelete() !=null && carStatus.getDelete() == 1){
					ite.remove();
				}else{
					if(carstatus !=null && !"".equals(carstatus)&& 
							!"null".equals(carstatus) && !"undefined".equals(carstatus)){
						if(carStatus.getCarstatus() ==null || (carStatus.getCarstatus().intValue() != Integer.parseInt(carstatus))){
							ite.remove();
						}
					}
				}
			}
			Collections.reverse(clist);
			for(int i=0;i<clist.size();i++){
				if(clist.get(i).getCarstatus()!=null && clist.get(i).getCarstatus() == 4){
					if((i+1) <= (clist.size()-1)){
						if(clist.get(i+1).getCarstatus()!=null && clist.get(i+1).getCarstatus() != 4){
							clist.get(i).setStoptime((DateUtil.dateDiffStr(clist.get(i).getCreatetime(), clist.get(i+1).getCreatetime())));
							clist.get(i).setStime(clist.get(i).getCreatetime());
							clist.get(i).setEtime(clist.get(i+1).getCreatetime());
						}
					}
				}
			}
		}
		
		 titleRow.createCell(0).setCellValue("序号");
         titleRow.createCell(1).setCellValue("车牌号");
         titleRow.createCell(2).setCellValue("车辆状态");
         titleRow.createCell(3).setCellValue("终端设备号");
         titleRow.createCell(4).setCellValue("经度");
         titleRow.createCell(5).setCellValue("纬度");
         titleRow.createCell(6).setCellValue("速度(km/h)");
         titleRow.createCell(7).setCellValue("方向");
         titleRow.createCell(8).setCellValue("当前里程");
         titleRow.createCell(9).setCellValue("总里程");
         titleRow.createCell(10).setCellValue("当前位置");
         titleRow.createCell(11).setCellValue("停留时间");
         titleRow.createCell(12).setCellValue("创建时间");
		
		if(clist.size()>0){
            for(int i=0;i<clist.size();i++){
                int  index=i+1;
                Row contentRow= sheet.createRow(index);
                contentRow.createCell(0).setCellValue(index);
                CarStatus cInfo= (CarStatus) clist.get(i);
                contentRow.createCell(1).setCellValue(cInfo.getCarnumber());
                contentRow.createCell(2).setCellValue(StringUtils.getCarstatus(cInfo.getCarstatus()));
                contentRow.createCell(3).setCellValue(cInfo.getTerminal());
                contentRow.createCell(4).setCellValue(cInfo.getLng());
                contentRow.createCell(5).setCellValue(cInfo.getLat());
                contentRow.createCell(6).setCellValue(cInfo.getSpeed());
                contentRow.createCell(7).setCellValue(StringUtils.getDirection(cInfo.getDirection()));
                contentRow.createCell(8).setCellValue(cInfo.getMileage());
                contentRow.createCell(9).setCellValue(cInfo.getSummileage());
                contentRow.createCell(10).setCellValue(cInfo.getAddress());
                contentRow.createCell(11).setCellValue(cInfo.getStoptime());
                contentRow.createCell(12).setCellValue(cInfo.getCreatetime());
            }
        }
        new ExcelDownWay().getCommonExcelListWay(book,fileName);
	}
	
	
	
	/**
	 * 分页查询车辆控制记录
	 * @return
	 */
	public String queryCarControlRecordList() {

		try {
			initMap();
			if(controlRecord==null){
				controlRecord=new ControlRecord();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				controlRecord.setUserid(SessionUtils.getUserId());
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				controlRecord.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			if(stime!=null&&!stime.equals("")&&!stime.equals("null")){
				controlRecord.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(etime!=null&&!etime.equals("")&&!etime.equals("null")){
				controlRecord.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			result=carService.selectCheckCarControlRecord(this.getPage(),this.getLimit(), controlRecord);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction的方法 queryCarInfoList执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 车辆控制
	 * @param carInfo
	 * @return
	 */
	public String CheckCarInfo(){
		try {
			initMap();
			if(carInfo==null){
				carInfo=new CarInfo();
			}
			if(controlRecord==null){
				controlRecord=new ControlRecord();
			}
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				carInfo=carService.getCarInfoCarId(Integer.parseInt(id[i]));
				controlRecord.setBlocid(carInfo.getBlocid());
				controlRecord.setUserid(SessionUtils.getUserId());
				controlRecord.setCarnumber(carInfo.getCarnumber());
				if(type != null && !"".equals(type) && !"null".equals(type)){
					int seq=HandleUtil.getSerialId();
					String data=HandleUtil.vehicleControl(carInfo.getUsertype(),carInfo.getTerminal(),type,seq,carInfo.getCarnumber());
					controlRecord.setData(data);
					controlRecord.setSeq(seq);
					controlRecord.setControltext(controltext);
//					JSONObject obj = JSONObject.fromObject(data);
//					controlRecord.setControltype(Integer.parseInt(obj.getString("ctrl")));
					int res=carService.insertCarControlrecord(controlRecord);
					if(res<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					}
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CarAction的方法 CheckCarInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	
	
	
	
	
	/**
	 * oracle鉴权数据往86-mysql插一份
	 * @param carInfo
	 * @return
	 */
	public String addEcsTo86(){
		try {
			carService.addEcsTo86();
		} catch (Exception e) {
			logger.error("CarAction的方法 addEcsTo86执行出错，原因：" + e, e);
		}
		return null;
	}

	
	/**
	 * 86-mysql鉴权数据往118-oracle插一份
	 * @param carInfo
	 * @return
	 */
	public String addEcsTo118(){
		try {
			carService.addEcsTo118();
		} catch (Exception e) {
			logger.error("CarAction的方法 addEcsTo118执行出错，原因：" + e, e);
		}
		return null;
	}
	
	/**
	 * taxi数据往oracle插一份鉴权数据
	 * @param carInfo
	 * @return
	 */
	public String addEcsToOracle(){
		try {
			carService.addEcsToOracle();
		} catch (Exception e) {
			logger.error("CarAction的方法 addEcsToOracle执行出错，原因：" + e, e);
		}
		return null;
	}
	
	/**
	 * 根据carid获取营运证信息
	 * @return
	 */
	public String findOperateCertificateByCarid() {

		try {
			initMap();
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			
			OperateCertificate optCer = carService.findOperateCertificateByCarid(Integer.parseInt(carid));
			if(optCer == null){
				result.put("su", 1);
			}else{
				result.put("su", 2);
				result.put("data", optCer);
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarAction的方法 findOperateCertificateByCarid执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 添加或修改营运证信息
	 * @param operateCertificate
	 * @return
	 */
	public String saveOperateCertificate(){
		try {
			initMap();
			
			if(operateCertificate.getId() == null){	//添加
				carService.insertOperateCertificate(operateCertificate);
			}else{	//修改
				carService.updateOperateCertificate(operateCertificate);
			}
			result.put("su", su);
			this.success="true";
			return SUCCESS;

		} catch (Exception e) {
			this.success="false";
			logger.error("CarAction的方法 saveOperateCertificate执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	public CarService getCarService() {
		return carService;
	}


	public void setCarService(CarService carService) {
		this.carService = carService;
	}


	public CarInfo getCarInfo() {
		return carInfo;
	}


	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}


	public Map getResult() {
		return result;
	}


	public void setResult(Map result) {
		this.result = result;
	}


	public String getSuccess() {
		return success;
	}



	public void setSuccess(String success) {
		this.success = success;
	}


	public List getList() {
		return list;
	}


	public void setList(List list) {
		this.list = list;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public BlocUser getUser() {
		return user;
	}


	public void setUser(BlocUser user) {
		this.user = user;
	}


	public int getSu() {
		return su;
	}


	public void setSu(int su) {
		this.su = su;
	}


	public String getCarnumber() {
		return carnumber;
	}


	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}


	public String getCartype() {
		return cartype;
	}


	public void setCartype(String cartype) {
		this.cartype = cartype;
	}


	public String getDeptid() {
		return deptid;
	}


	public void setDeptid(String deptid) {
		this.deptid = deptid;
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






	public CarDetail getData() {
		return data;
	}


	public void setData(CarDetail data) {
		this.data = data;
	}




	public Map<String, String> getQueryParams() {
		return queryParams;
	}


	public void setQueryParams(Map<String, String> queryParams) {
		this.queryParams = queryParams;
	}


	public Map<String, Object> getJsondata() {
		return jsondata;
	}


	public void setJsondata(Map<String, Object> jsondata) {
		this.jsondata = jsondata;
	}


	public List<CarStatus> getCarListWithTrack() {
		return carListWithTrack;
	}


	public void setCarListWithTrack(List<CarStatus> carListWithTrack) {
		this.carListWithTrack = carListWithTrack;
	}


	public List<CarStatus> getClist() {
		return clist;
	}


	public void setClist(List<CarStatus> clist) {
		this.clist = clist;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDevicetype() {
		return devicetype;
	}


	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}


	public String getTers() {
		return ters;
	}


	public void setTers(String ters) {
		this.ters = ters;
	}


	

	public String getCarstatus() {
		return carstatus;
	}


	public void setCarstatus(String carstatus) {
		this.carstatus = carstatus;
	}

	public String getTerminal() {
		return terminal;
	}


	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}


	public MenuTreeService getMenuTreeService() {
		return menuTreeService;
	}

	public void setMenuTreeService(MenuTreeService menuTreeService) {
		this.menuTreeService = menuTreeService;
	}


	public String getOnline() {
		return online;
	}


	public void setOnline(String online) {
		this.online = online;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getCaruse() {
		return caruse;
	}

	public void setCaruse(String caruse) {
		this.caruse = caruse;
	}

	public CarDriver getCarDriver() {
		return carDriver;
	}

	public void setCarDriver(CarDriver carDriver) {
		this.carDriver = carDriver;
	}

	public TerminalDeviceInfoService getTerminalDeviceInfoService() {
		return terminalDeviceInfoService;
	}

	public void setTerminalDeviceInfoService(
			TerminalDeviceInfoService terminalDeviceInfoService) {
		this.terminalDeviceInfoService = terminalDeviceInfoService;
	}

	public DssService getDssService() {
		return dssService;
	}

	public void setDssService(DssService dssService) {
		this.dssService = dssService;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBlocid() {
		return blocid;
	}

	public void setBlocid(String blocid) {
		this.blocid = blocid;
	}

	public ControlRecord getControlRecord() {
		return controlRecord;
	}

	public void setControlRecord(ControlRecord controlRecord) {
		this.controlRecord = controlRecord;
	}

	public String getControltype() {
		return controltype;
	}

	public void setControltype(String controltype) {
		this.controltype = controltype;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public OperateCertificate getOperateCertificate() {
		return operateCertificate;
	}

	public void setOperateCertificate(OperateCertificate operateCertificate) {
		this.operateCertificate = operateCertificate;
	}

	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public String getControltext() {
		return controltext;
	}

	public void setControltext(String controltext) {
		this.controltext = controltext;
	}

	public OrgazicationDeptService getOrgazicationDeptService() {
		return orgazicationDeptService;
	}

	public void setOrgazicationDeptService(
			OrgazicationDeptService orgazicationDeptService) {
		this.orgazicationDeptService = orgazicationDeptService;
	}

	public CmssService getCmssService() {
		return cmssService;
	}

	public void setCmssService(CmssService cmssService) {
		this.cmssService = cmssService;
	}

	public GtalkService getGtalkService() {
		return gtalkService;
	}

	public void setGtalkService(GtalkService gtalkService) {
		this.gtalkService = gtalkService;
	}
	
	
}
