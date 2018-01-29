/**
 * Description: taxi物流平台
 * 文件名：TerminalDeviceInfoAction.java
 * 版本信息：1.0
 * 日期：2013-9-6
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.common.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.mina.filter.reqres.Request;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.misc.BASE64Decoder;

import net.sf.json.JSONObject;

import com.careye.base.action.BasePageAction;
import com.careye.base.action.TreeDomain;
import com.careye.car.domain.CarInfo;
import com.careye.car.domain.CarnumberInfo;
import com.careye.car.service.CarService;
import com.careye.common.domain.Carrealtime;
import com.careye.common.domain.HistoryPositionInfo;
import com.careye.common.domain.MapInfo;
import com.careye.common.domain.OperaTimeAnalysis;
import com.careye.common.domain.PositionInfo;
import com.careye.common.domain.TerminalPositionInfo;
import com.careye.common.service.TerminalDeviceInfoService;
import com.careye.constant.WebConstants;
import com.careye.mongodb.MongoDB;
import com.careye.monitor.domain.OnlineReport;
import com.careye.monitor.service.RemoteControlRecordService;
import com.careye.system.domain.UserCar;
import com.careye.transaction.domain.EvaluateCount;
import com.careye.transaction.domain.Transaction;
import com.careye.transaction.service.TransactionService;
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.ExportToWord;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;


/**
 * @项目名称：56taxi
 * @类名称：TerminalDeviceInfoAction
 * @类描述：终端
 * @创建人：zhangrong
 * @创建时间：2014-5-26 下午02:25:12
 * @修改人：zhangrong
 * @修改时间：2014-5-26 下午02:25:12
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TerminalDeviceInfoAction extends BasePageAction {

	private static final Logger logger = Logger.getLogger(TerminalDeviceInfoAction.class);
	private TerminalDeviceInfoService terminalDeviceInfoService;
	private CarService carService;
	private RemoteControlRecordService remoteControlRecordService;
	private TransactionService transactionService;
	private Carrealtime carrealtime;
	private HistoryPositionInfo historyPos;
	private TerminalPositionInfo terminalPositionInfo;
	private Transaction transaction;
	private OperaTimeAnalysis ota;
	
	
	private TerminalPositionInfo tpi;
	private List<TerminalPositionInfo> tpilist1 = new ArrayList<TerminalPositionInfo>();
	private List<TerminalPositionInfo> tpilist2 = new ArrayList<TerminalPositionInfo>();;
	
	
	private CarInfo carInfo;
	private MapInfo data;
	
	private Transaction lastOrderInfo;
	
	private String province;
	private String city;
	private String district;
	
	private String lng;
	private String lat;
	private String lng1;
	private String lat1;
	private String maptype;
	
	private String carid;
	private String carnumber;
	private String terminal;
	private String query;
	private String devicetype;
	private String deptid;
	private String blocid;
	private Map result;
	private String success;
	
	private String cartype;
	private String usename;
	private String carstatus;
	private String userid;
	
	private String stime;
	private String etime;
	
	private String sortcolumn;
	private String sortway;
	private String sort;
	
	private String ids;
	private String id;
	private String type;
	private String carids; 
	private String typename; 	//类型名称
	
	private String style;
	private String color;
	private String content;
	private String delayed;
	private String time;
	private String areaid;
	
	private String gpsflag;
	private String cars;
	private String times;
	private String fileName;
	
	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 根据车牌获取最新订单信息
	 * @return
	 */
	public String queryLastOrderInfo(){
		try {
			initMap();
			if(this.carnumber != null){
				lastOrderInfo = terminalDeviceInfoService.queryLastOrderInfo(carnumber);
			}
//			if(lastOrderInfo == null){
//				lastOrderInfo = new Transaction();
//			}
			result.put("lastOrderInfo", lastOrderInfo);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
//			logger.error("TerminalDeviceInfoAction 的方法 queryLastOrderInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 终端位置信息
	 * @return
	 */
	public String queryCarDetail(){
		try {
			initMap();
			if(this.carnumber != null){
				data = terminalDeviceInfoService.queryCarDetail(carnumber);
			}
			if(this.carid != null){
				data = terminalDeviceInfoService.queryCarDetail(Integer.parseInt(carid));
			}
			if(data == null){
				data = new MapInfo();
			}
			result.put("data", data);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction 的方法 queryCarDetail执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 批量获取车辆位置信息
	 * @return
	 */
	public String queryCarAllDetail(){
		try {
			initMap();
			if(this.ids == null){
				return ERROR;
			}
			
			List<Integer> idlist = new ArrayList<Integer>();
			String idsarr [] = ids.split(",");
			for (int i = 0; i < idsarr.length; i++) {
				idlist.add(Integer.parseInt(idsarr[i]));
			}
			
			List<PositionInfo> list = terminalDeviceInfoService.queryCarAllDetail(idlist);
			result.put("list",list);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarPositionInfoAction 的方法 queryCarAllDetail执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 查看组织机构下面终端位置信息列表
	 * @return
	 */
	public String loadCarPosByDept(){
		try {
			initMap();
			TreeDomain baseDomain = new TreeDomain();
			baseDomain.setUserid(SessionUtils.getUserId());
			if(deptid != null && !"".equals(deptid) && !"null".equals(deptid)){
				baseDomain.setBlocid(Integer.parseInt(deptid));
			}
			List<PositionInfo> list = terminalDeviceInfoService.loadCarPosByDept(baseDomain);
			result.put("list",list);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarPositionInfoAction 的方法 loadCarPosByDept执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 终端位置信息列表
	 * @return
	 */
	public String terminalPositionList(){
		initMap();
		try {
			
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			
			if(terminalPositionInfo==null){
    			terminalPositionInfo=new TerminalPositionInfo();
    		}
    		
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				terminalPositionInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}
			if(stime!=null&&!stime.equals("")&&!stime.equals("null")){
    			terminalPositionInfo.setStime(URLDecoder.decode(stime,"UTF-8"));
    		}
    		
    		if(etime!=null&&!etime.equals("")&&!etime.equals("null")){
    			terminalPositionInfo.setEtime(URLDecoder.decode(etime,"UTF-8"));
    		}
    		if(deptid!=null&&!deptid.equals("")&&!deptid.equals("null")){
    			terminalPositionInfo.setBlocid(Integer.parseInt(deptid));
			}
    		if(carstatus!=null&&!carstatus.equals("")&&!carstatus.equals("null")){
    			terminalPositionInfo.setCarstatus(URLDecoder.decode(carstatus,"UTF-8"));
    		}
    		
    		if(sort!=null){
				try {
					JSONObject jsonObject = JSONObject.fromObject(sort.replace("[", "").replace("]", ""));
					sortcolumn = jsonObject.get("property").toString();
					sortway = jsonObject.get("direction").toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
    		
    		if(sortcolumn!=null&&!sortcolumn.equals("")&&!sortcolumn.equals("null")){
    			terminalPositionInfo.setSortcolumn(sortcolumn);
    		}else{
    			terminalPositionInfo.setSortcolumn("createtime");	//默认按时间排序
    		}
    		
    		if(sortway!=null&&!sortway.equals("")&&!sortway.equals("null")){
    			terminalPositionInfo.setSortway(sortway);
    		}else{
    			terminalPositionInfo.setSortway("desc");	//默认按降序
    		}
    		
			List list = Collections.emptyList();
			
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				terminalPositionInfo.setUserid(SessionUtils.getUserId());
			}
			list = terminalDeviceInfoService.terminalPositionList(terminalPositionInfo);

			result.put("list", list);
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction 的方法 terminalPositionList 执行出错，原因：" + e, e);
		}
		return SUCCESS;
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
			
			Integer userid = null;
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				userid = SessionUtils.getUserId();
			}
			CarInfo carInfo = new CarInfo();
			carInfo.setUserid(userid);
			
			if(StringUtils.isNotEmty(query)){
				carInfo.setCarnumber(URLDecoder.decode(query,"UTF-8").toUpperCase());
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				carInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}
			if(StringUtils.isNotEmty(deptid)){
				carInfo.setBlocid(Integer.parseInt(deptid));
			}
			List list = terminalDeviceInfoService.selectCarList(carInfo);
			result.put("list", list);
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction 的方法 selectCarList 执行出错，原因：" + e, e);
		}
		return SUCCESS;
	}
	
	/**
	 * 车辆分页下拉列表
	 * @return
	 */
	public String selectCarPageList(){
		initMap();
		try {
			
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			
			Integer userid = null;
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				userid = SessionUtils.getUserId();
			}
			CarInfo carInfo = new CarInfo();
			carInfo.setUserid(userid);

			if(StringUtils.isNotEmty(query)){
				carInfo.setCarnumber(new String(query.getBytes("ISO-8859-1"),"utf-8"));
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				carInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}
			if(StringUtils.isNotEmty(deptid)){
				carInfo.setBlocid(Integer.parseInt(deptid));
			}
			result = terminalDeviceInfoService.selectCarPageList(this.getPage(),this.getLimit(),carInfo);
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction 的方法 selectCarList 执行出错，原因：" + e, e);
		}
		return SUCCESS;
	}
	
	
	/**
	 * 车牌号列表,不分页取出集团
	 * @return
	 */
	public String queryCarList(){
		try {
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
			if(StringUtils.isNotEmty(carnumber)){
				carInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").trim().toUpperCase());
			}
			if(StringUtils.isNotEmty(blocid)){
				carInfo.setBlocid(Integer.parseInt(blocid));
			}
			if(StringUtils.isNotEmty(areaid)){
				carInfo.setAreaid(Integer.parseInt(areaid));
			}
			List<CarnumberInfo> list=carService.queryCarList(carInfo);
			result.put("list", list);
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction的方法 queryCarList执行出错，原因：" + e, e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 雅讯车牌号列表
	 * @return
	 */
	public String queryYxCarList(){
		try {
			initMap();
			if(carInfo==null){
				carInfo=new CarInfo();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(StringUtils.isNotEmty(carnumber)){
				carInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").trim().toUpperCase());
			}
			if(StringUtils.isNotEmty(blocid)){
				carInfo.setBlocid(Integer.parseInt(blocid));
			}
			List<CarnumberInfo> list=carService.queryYxCarList(carInfo);
			result.put("list", list);
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction的方法 queryYxCarList执行出错，原因：" + e, e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	
    /**
     * 车辆分布列表
     * @return
     */
    public String getCarLocationList(){
		try {
			initMap();
			if(terminalPositionInfo==null){
				terminalPositionInfo=new TerminalPositionInfo();
			}

			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				terminalPositionInfo.setUserid(SessionUtils.getUserId());
			}

			if(lng!=null&&!lng.equals("")&&!lng.equals("null")){
					terminalPositionInfo.setLng(Double.parseDouble(lng));
			}
			if(lng1!=null&&!lng1.equals("")&&!lng1.equals("null")){
					terminalPositionInfo.setLng1(Double.parseDouble(lng1));
			}
			if(lat!=null&&!lat.equals("")&&!lat.equals("null")){
					terminalPositionInfo.setLat(Double.parseDouble(lat));
			}
			if(lat1!=null&&!lat1.equals("")&&!lat1.equals("null")){
					terminalPositionInfo.setLat1(Double.parseDouble(lat1));
			}
			
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				terminalPositionInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}

			if(stime!=null&&!stime.equals("")&&!stime.equals("null")){
				terminalPositionInfo.setStime(URLDecoder.decode(stime,"UTF-8"));
			}

			if(etime!=null&&!etime.equals("")&&!etime.equals("null")){
				terminalPositionInfo.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(devicetype!=null&&!devicetype.equals("")&&!devicetype.equals("null")){
				terminalPositionInfo.setDevicetype(Integer.parseInt(devicetype));
			}
			if(usename!=null&&!usename.equals("")&&!usename.equals("null")){
				terminalPositionInfo.setUsename(usename);
			}
			if(carstatus!=null&&!carstatus.equals("")&&!carstatus.equals("null")){
				terminalPositionInfo.setCarstatus(carstatus);
			}
			if(deptid!=null&&!deptid.equals("")&&!deptid.equals("null")){
				terminalPositionInfo.setBlocid(Integer.parseInt(deptid));
			}
			if(blocid!=null&&!blocid.equals("")&&!blocid.equals("null")){
				terminalPositionInfo.setBlocid(Integer.parseInt(blocid));
			}
			if(terminal!=null&&!terminal.equals("")&&!terminal.equals("null")){
				terminalPositionInfo.setTerminal(URLDecoder.decode(terminal,"UTF-8"));
			}
			if(cartype!=null&&!cartype.equals("")&&!cartype.equals("null")){
				terminalPositionInfo.setCartype(Integer.parseInt(cartype));
			}
			if(province!=null&&!province.equals("")&&!province.equals("null")){
				terminalPositionInfo.setProvince(URLDecoder.decode(province,"UTF-8"));
			}
			if(city!=null&&!city.equals("")&&!city.equals("null")){
				terminalPositionInfo.setCity(URLDecoder.decode(city,"UTF-8"));
			}
			if(district!=null&&!district.equals("")&&!district.equals("null")){
				terminalPositionInfo.setDistrict(URLDecoder.decode(district,"UTF-8"));
			}
			result = terminalDeviceInfoService.getCarLocationList(this.getPage(),this.getLimit(), terminalPositionInfo);
			return SUCCESS;

		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction的方法 getCarLocationList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
    
    

    /**
     * Excel车辆分布列表
     * @return
     */
    public void exportCarLocationList(){
		try {
			if(terminalPositionInfo==null){
				terminalPositionInfo=new TerminalPositionInfo();
			}
				terminalPositionInfo.setUserid(Integer.parseInt(userid));
			if(StringUtils.isNotEmty(lng)){
					terminalPositionInfo.setGlng(Double.parseDouble(lng));
			}
			if(StringUtils.isNotEmty(lat)){
					terminalPositionInfo.setGlat(Double.parseDouble(lat));
			}
			if(StringUtils.isNotEmty(carnumber)){
				terminalPositionInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}

			if(StringUtils.isNotEmty(stime)){
				terminalPositionInfo.setStime(URLDecoder.decode(stime,"UTF-8"));
			}

			if(StringUtils.isNotEmty(etime)){
				terminalPositionInfo.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(devicetype)){
				terminalPositionInfo.setDevicetype(Integer.parseInt(devicetype));
			}
			if(StringUtils.isNotEmty(usename)){
				terminalPositionInfo.setUsename(usename);
			}
			if(StringUtils.isNotEmty(carstatus)){
				terminalPositionInfo.setCarstatus(carstatus);
			}
			if(StringUtils.isNotEmty(blocid)){
				terminalPositionInfo.setBlocid(Integer.parseInt(blocid));
			}
			if(StringUtils.isNotEmty(terminal)){
				terminalPositionInfo.setTerminal(URLDecoder.decode(terminal,"UTF-8"));
			}
			if(StringUtils.isNotEmty(cartype)){
				terminalPositionInfo.setCartype(Integer.parseInt(cartype));
			}
			if(StringUtils.isNotEmty(province)){
				terminalPositionInfo.setProvince(URLDecoder.decode(province,"UTF-8"));
			}
			if(StringUtils.isNotEmty(city)){
				terminalPositionInfo.setCity(URLDecoder.decode(city,"UTF-8"));
			}
			if(StringUtils.isNotEmty(district)){
				terminalPositionInfo.setDistrict(URLDecoder.decode(district,"UTF-8"));
			}
			List<TerminalPositionInfo> list= terminalDeviceInfoService.getCarLocationList(terminalPositionInfo);

			 String fileName="车辆分布报表"; 
		   	 HSSFWorkbook book = new HSSFWorkbook();
		     Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
		     Row titleRow= sheet.createRow(0);
		        
		        titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
		        titleRow.createCell(1).setCellValue("集团");
		        titleRow.createCell(2).setCellValue("车牌号");
		        titleRow.createCell(3).setCellValue("终端号码");
		        titleRow.createCell(4).setCellValue("设备类型");
		        titleRow.createCell(5).setCellValue("车辆状态");
		        titleRow.createCell(6).setCellValue("车牌颜色");
		        titleRow.createCell(7).setCellValue("车辆类别");
		        titleRow.createCell(8).setCellValue("车辆用途");
		        titleRow.createCell(9).setCellValue("经度");
		        titleRow.createCell(10).setCellValue("纬度");
		        titleRow.createCell(11).setCellValue("所在省");
		        titleRow.createCell(12).setCellValue("所在市");
		        titleRow.createCell(13).setCellValue("所在县/区");
		        titleRow.createCell(14).setCellValue("地址");
		        titleRow.createCell(15).setCellValue("更新时间");
		        
		        sheet.setDefaultColumnWidth(20);  
		        sheet.setDefaultRowHeightInPoints(20); 
		        
		        if(list.size()>0){
		            for(int i=0;i<list.size();i++){
		                int  index=i+1;
		                Row contentRow= sheet.createRow(index);
		                contentRow.createCell(0).setCellValue(index);
		                TerminalPositionInfo data= (TerminalPositionInfo) list.get(i);
		                contentRow.createCell(1).setCellValue(data.getBlocname());
		                contentRow.createCell(2).setCellValue(data.getCarnumber());
		                contentRow.createCell(3).setCellValue(data.getTerminal());
		                contentRow.createCell(4).setCellValue(data.getTypename());
		                if (data.getCarstatus()!=null) {
		                	Integer statu = Integer.parseInt(data.getCarstatus());
			                String statuString ="";
			                if (statu==8) {
			                	statuString = "未定位";
							} else if (statu==2) {
								statuString = "离线";
							} else if (statu==3) {
								statuString = "熄火";
							} else if (statu==4) {
								statuString = "停车";
							} else if (statu==5) {
								statuString = "行驶";
							} else if (statu==6) {
								statuString = "报警";
							} else if (statu==7) {
								statuString = "在线";
							}else {
								statuString = "长时间离线";
							}
			                contentRow.createCell(5).setCellValue(statuString);
						}else {
			                contentRow.createCell(5).setCellValue("");
						}
		                
		                if (data.getColor()!=null) {
		                	Integer colorInt = Integer.parseInt(data.getColor());
			                String colorString ="";
			                if (colorInt==1) {
			                	colorString = "蓝色";
							} else if (colorInt==2) {
								colorString = "黄色";
							} else if (colorInt==3) {
								colorString = "黑色";
							} else if (colorInt==5) {
								colorString = "白色";
							} else if (colorInt==4) {
								colorString = "红色";
							} else if (colorInt==6) {
								colorString = "紫色";
							}else {
								colorString = "其他";
							}
			                contentRow.createCell(6).setCellValue(colorString);
						}else {
			                contentRow.createCell(6).setCellValue("");
						}
		                
		                contentRow.createCell(7).setCellValue(data.getCartypename());
		                contentRow.createCell(8).setCellValue(data.getUsename());
		                contentRow.createCell(9).setCellValue(data.getLng());
		                contentRow.createCell(10).setCellValue(data.getLat());
		                contentRow.createCell(11).setCellValue(data.getProvince());
		                contentRow.createCell(12).setCellValue(data.getCity());
		                contentRow.createCell(13).setCellValue(data.getDistrict());
		                contentRow.createCell(14).setCellValue(data.getAddress());
		                contentRow.createCell(15).setCellValue(data.getCreatetime());
		            }
		        }
		        
		        new ExcelDownWay().getCommonExcelListWay(book,fileName);
		        
		        
			} catch (Exception e) {
				try {
					getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	}
    /**
     * word 导出企业车辆在线情况
     */
    public void exportCarLocationWord(){
    	try {
			initMap();
		    Map<String,Object> dataMap=new HashMap<String,Object>();
		    
		    dataMap.put("Title", "企业车辆在线情况报告");
		    dataMap.put("Title1", "企业离线车辆明细");
		    dataMap.put("Title2", "企业在线车辆明细");
		    
		    String st = DateUtil.getSQLDate();
		    String y = st.substring(0, 4);
		    String m = st.substring(5, 7);
		    String today = st.substring(8, 10);
			
		    dataMap.put("year", y);  
	        dataMap.put("month", m);  
	        dataMap.put("date", today);
	        dataMap.put("condition", "企业车辆在线情况数据如下：");
	        
	        if(terminalPositionInfo==null){
				terminalPositionInfo=new TerminalPositionInfo();
			}
	        
			if(StringUtils.isNotEmty(blocid)){
				terminalPositionInfo.setBlocid(Integer.parseInt(blocid));
				String bname = terminalDeviceInfoService.getBname(Integer.parseInt(blocid));
				dataMap.put("bname", "企业"+"【"+bname+"】"+",");
			}else{
				terminalPositionInfo.setBlocid(SessionUtils.getUser().getBlocid());
				String bname = terminalDeviceInfoService.getBname(terminalPositionInfo.getBlocid());
				dataMap.put("bname", "企业"+"【"+bname+"】"+",");
			}
			if(StringUtils.isNotEmty(stime)){
				terminalPositionInfo.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				terminalPositionInfo.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			
			if(StringUtils.isNotEmty(stime) && StringUtils.isNotEmty(etime)){
				dataMap.put("time", stime+"到"+etime+",");
			}else if(StringUtils.isEmty(stime) && StringUtils.isNotEmty(etime)){
				dataMap.put("time", "时间截止到"+etime+",");
			}else if(StringUtils.isNotEmty(stime) && StringUtils.isEmty(etime)){
				dataMap.put("time", "从"+stime+"开始,到现在");
			}else{
				dataMap.put("time", "");
			}
			
			tpi = terminalDeviceInfoService.getTpi(terminalPositionInfo);
			
			dataMap.put("count1", tpi.getCount1()+"辆"); 
			dataMap.put("count2", tpi.getCount2()+"辆");
	        
			tpilist1 = terminalDeviceInfoService.getTpiList1(terminalPositionInfo);
			for(int i=0;i<tpilist1.size();i++){
				TerminalPositionInfo tp1= (TerminalPositionInfo)tpilist1.get(i);
				if(tp1.getCarstatus().equals("1")){
					tp1.setCarstatus("长时间离线");
				}else if(tp1.getCarstatus().equals("2")){
					tp1.setCarstatus("离线");
				}
			}
			tpilist2 = terminalDeviceInfoService.getTpiList2(terminalPositionInfo);
			for(int i=0;i<tpilist2.size();i++){
				TerminalPositionInfo tp2= (TerminalPositionInfo)tpilist2.get(i);
				if(tp2.getCarstatus().equals("3")){
					tp2.setCarstatus("熄火");
				}else if(tp2.getCarstatus().equals("4")){
					tp2.setCarstatus("停车");
				}else if(tp2.getCarstatus().equals("5")){
					tp2.setCarstatus("行驶");
				}else if(tp2.getCarstatus().equals("6")){
					tp2.setCarstatus("报警");
				}else if(tp2.getCarstatus().equals("7")){
					tp2.setCarstatus("在线");
				}else if(tp2.getCarstatus().equals("8")){
					tp2.setCarstatus("未定位");
				}
			}
			
			dataMap.put("list1", tpilist1);
			dataMap.put("list2", tpilist2);
	        
			ExportToWord.getInstance().exportWord(dataMap,WebConstants.FM_CARLOCATION_WORD,"企业车辆在线情况报告");
			
		} catch (Exception e) {
			logger.error("EvaluateCountAction 的方法 exportWord 执行出错，原因：" + e, e);
		}
    }
	

    /**
	 * 车辆历史位置记录
	 * @return
	 */
	public String getCarhistoryList(){
		initMap();
		try {
			
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			
			if(historyPos==null){
				historyPos=new HistoryPositionInfo();
			}
			
			if(StringUtils.isNotEmty(carnumber)){
				historyPos.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}
			if(StringUtils.isNotEmty(terminal)){
				historyPos.setTerminal(URLDecoder.decode(terminal,"UTF-8"));
			}
			if(StringUtils.isNotEmty(stime)){
				historyPos.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			
			if(StringUtils.isNotEmty(etime)){
				historyPos.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				historyPos.setBlocid(Integer.parseInt(blocid));
			}else{
				historyPos.setBlocid(SessionUtils.getBlocId());
			}
			if(StringUtils.isNotEmty(carstatus)){
				historyPos.setCarstatus(Integer.parseInt(carstatus));
			}
			if(StringUtils.isNotEmty(gpsflag)){
				historyPos.setGpsflag(Integer.parseInt(gpsflag));
			}
			
			result = MongoDB.getInstance().getCarhistoryList(this.getPage(),this.getLimit(),historyPos);
			
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction 的方法 terminalPositionList 执行出错，原因：" + e, e);
		}
		return SUCCESS;
	}
	/**
	 * 删除车辆历史位置 deleteCarhistory
	 */
	
	public String deleteCarhistory(){
		try {
			initMap();
			
			String carnumber[] = cars.split(",");
			String createtime[] = times.split(",");
			HistoryPositionInfo hp=new HistoryPositionInfo();
			for (int i = 0; i < carnumber.length; i++) {
				String car = carnumber[i];
				hp.setCarnumber(car);
				for (int j = 0; j < createtime.length; j++) {
					String tm =  createtime[j];
					hp.setCreatetime(tm);
				}
			}
			
			MongoDB.getInstance().deleteCarhistory(hp.getCarnumber(),hp.getCreatetime());
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction 的方法 deleteCarhistory执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
     * Excel车辆历史记录列表
     * @return
     */
    public void exportCarhistory(){
		try {
			if(SessionUtils.getUser() == null){
				return ;
			}
			initMap();
			fileName="车辆历史记录"; 
	   	    HSSFWorkbook book = new HSSFWorkbook();
	        Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
	        Row titleRow= sheet.createRow(0);
			if(historyPos==null){
				historyPos=new HistoryPositionInfo();
			}
			
			if(StringUtils.isNotEmty(carnumber)){
//				historyPos.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
				historyPos.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"),"utf-8"));
				
			}
			if(StringUtils.isNotEmty(terminal)){
				historyPos.setTerminal(URLDecoder.decode(terminal,"UTF-8"));
			}
			if(StringUtils.isNotEmty(stime)){
				historyPos.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			
			if(StringUtils.isNotEmty(etime)){
				historyPos.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				historyPos.setBlocid(Integer.parseInt(blocid));
			}else{
				historyPos.setBlocid(SessionUtils.getBlocId());
			}
			if(StringUtils.isNotEmty(carstatus)){
				historyPos.setCarstatus(Integer.parseInt(carstatus));
			}
			if(StringUtils.isNotEmty(gpsflag)){
				historyPos.setGpsflag(Integer.parseInt(gpsflag));
			}
			
			result = MongoDB.getInstance().getCarhistoryList(1,Integer.MAX_VALUE,historyPos);
			
			List<HistoryPositionInfo> list= (List<HistoryPositionInfo>) result.get("list");

			 ExcelDownWay exceldownway = new ExcelDownWay();
				// 2.设置列宽（列数要对应上）
				String str = "7,25,25,25,25,25,25,25,25,25,25,50,25";
				List<String> numberList = Arrays.asList(str.split(","));
				sheet = exceldownway.setColumnWidth(sheet, numberList);
		        
		        titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
		        titleRow.createCell(1).setCellValue("集团名称");
		        titleRow.createCell(2).setCellValue("终端号码");
		        titleRow.createCell(3).setCellValue("车牌号");
		        titleRow.createCell(4).setCellValue("车辆状态");
		        titleRow.createCell(5).setCellValue("GPS是否定位");
		        titleRow.createCell(6).setCellValue("速度");
		        titleRow.createCell(7).setCellValue("方向");
		        titleRow.createCell(8).setCellValue("上传时间");
		        titleRow.createCell(9).setCellValue("经度");
		        titleRow.createCell(10).setCellValue("纬度");
		        titleRow.createCell(11).setCellValue("地址");
		        titleRow.createCell(12).setCellValue("创建时间");
		        HSSFCellStyle style = book.createCellStyle();  
		        
		        sheet.setDefaultColumnWidth(20);  
		        sheet.setDefaultRowHeightInPoints(20); 
		        
		        for (int i = 0; i < titleRow.getLastCellNum(); i++) {
					titleRow.getCell(i).setCellStyle(
							exceldownway.setBookHeadStyle(book));
				}
		        
		        if(list.size()>0){
		            for(int i=0;i<list.size();i++){
		                int  index=i+1;
		                Row contentRow= sheet.createRow(index);
		                contentRow.createCell(0).setCellValue(index);
		                HistoryPositionInfo hpt= (HistoryPositionInfo) list.get(i);
		                contentRow.createCell(1).setCellValue(hpt.getBlocname());
		                contentRow.createCell(2).setCellValue(hpt.getTerminal());
		                contentRow.createCell(3).setCellValue(hpt.getCarnumber());
		                contentRow.createCell(4).setCellValue(StringUtils.getCarstatus(hpt.getCarstatus()));
		                contentRow.createCell(5).setCellValue(StringUtils.getGpsflag(hpt.getGpsflag()));
		                contentRow.createCell(6).setCellValue(hpt.getSpeed());
		                contentRow.createCell(7).setCellValue(StringUtils.getDirection(hpt.getDirection()));
		                contentRow.createCell(8).setCellValue(hpt.getGpstime());
		                contentRow.createCell(9).setCellValue(hpt.getBlng());
		                contentRow.createCell(10).setCellValue(hpt.getBlat());
		                contentRow.createCell(11).setCellValue(hpt.getAddress());
		                contentRow.createCell(12).setCellValue(hpt.getCreatetime());
		            }
		        }
		        
		        exceldownway.getCommonExcelListWay(book,fileName);
		        
		        
			} catch (Exception e) {
				try {
					e.printStackTrace();
					getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		   }
    
    /**
	 * 车辆实时信息
	 * @return
	 */
	public String getCarrealtimeList(){
		initMap();
		try {
			
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			
			if(carrealtime==null){
				carrealtime=new Carrealtime();
			}
			
			if(StringUtils.isNotEmty(carnumber)){
				carrealtime.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}
			if(StringUtils.isNotEmty(terminal)){
				carrealtime.setTerminal(URLDecoder.decode(terminal,"UTF-8"));
			}
			if(StringUtils.isNotEmty(stime)){
				carrealtime.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			
			if(StringUtils.isNotEmty(etime)){
				carrealtime.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				carrealtime.setBlocid(Integer.parseInt(blocid));
			}else{
				carrealtime.setBlocid(SessionUtils.getBlocId());
			}
			if(StringUtils.isNotEmty(carstatus)){
				carrealtime.setCarstatus(Integer.parseInt(carstatus));
			}
			if(StringUtils.isNotEmty(gpsflag)){
				carrealtime.setGpsflag(Integer.parseInt(gpsflag));
			}
			
//			result = MongoDB.getInstance().getCarhistoryList(this.getPage(),this.getLimit(),historyPos);
			result = terminalDeviceInfoService.getCarrealtimeList(this.getPage(),this.getLimit(), carrealtime);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction 的方法 getCarrealtimeList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
     * Excel车辆实时信息
     * @return
     */
    public void exportCarrealtime(){
		try {
			if(SessionUtils.getUser() == null){
				return ;
			}
			initMap();
			fileName="车辆实时信息"; 
	   	    HSSFWorkbook book = new HSSFWorkbook();
	        Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
	        Row titleRow= sheet.createRow(0);
			
			if(carrealtime==null){
				carrealtime=new Carrealtime();
			}
			
			if(StringUtils.isNotEmty(carnumber)){
				carrealtime.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"),"utf-8"));
			}
			if(StringUtils.isNotEmty(terminal)){
				carrealtime.setTerminal(URLDecoder.decode(terminal,"UTF-8"));
			}
			if(StringUtils.isNotEmty(stime)){
				carrealtime.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			
			if(StringUtils.isNotEmty(etime)){
				carrealtime.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				carrealtime.setBlocid(Integer.parseInt(blocid));
			}else{
				carrealtime.setBlocid(SessionUtils.getBlocId());
			}
			if(StringUtils.isNotEmty(carstatus)){
				carrealtime.setCarstatus(Integer.parseInt(carstatus));
			}
			if(StringUtils.isNotEmty(gpsflag)){
				carrealtime.setGpsflag(Integer.parseInt(gpsflag));
			}
			
			List<Carrealtime> list= terminalDeviceInfoService.exportCarrealtime(carrealtime);
			 ExcelDownWay exceldownway = new ExcelDownWay();
				// 2.设置列宽（列数要对应上）
				String str = "7,25,25,25,25,25,25,25,25,25,25,25,25,50,25";
				List<String> numberList = Arrays.asList(str.split(","));
				sheet = exceldownway.setColumnWidth(sheet, numberList);
		        
		        titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
		        titleRow.createCell(1).setCellValue("集团名称");
		        titleRow.createCell(2).setCellValue("终端号码");
		        titleRow.createCell(3).setCellValue("车牌号");
		        titleRow.createCell(4).setCellValue("高度");
		        titleRow.createCell(5).setCellValue("速度");
		        titleRow.createCell(6).setCellValue("方向");
		        titleRow.createCell(7).setCellValue("在线状态");
		        titleRow.createCell(8).setCellValue("GPS是否定位");
		        titleRow.createCell(9).setCellValue("定位时间");
		        titleRow.createCell(10).setCellValue("经度");
		        titleRow.createCell(11).setCellValue("纬度");
		        titleRow.createCell(12).setCellValue("地址");
		        titleRow.createCell(13).setCellValue("创建时间");
		        HSSFCellStyle style = book.createCellStyle();  
		        
		        sheet.setDefaultColumnWidth(20);  
		        sheet.setDefaultRowHeightInPoints(20); 
		        
		        for (int i = 0; i < titleRow.getLastCellNum(); i++) {
					titleRow.getCell(i).setCellStyle(
							exceldownway.setBookHeadStyle(book));
				}
		        
		        if(list.size()>0){
		            for(int i=0;i<list.size();i++){
		                int  index=i+1;
		                Row contentRow= sheet.createRow(index);
		                contentRow.createCell(0).setCellValue(index);
		                Carrealtime hpt= (Carrealtime) list.get(i);
		                contentRow.createCell(1).setCellValue(hpt.getBlocname());
		                contentRow.createCell(2).setCellValue(hpt.getTerminal());
		                contentRow.createCell(3).setCellValue(hpt.getCarnumber());
		                contentRow.createCell(4).setCellValue(hpt.getAltitude());
		                contentRow.createCell(5).setCellValue(hpt.getSpeed());
		                
		                if (hpt.getDirection()!=null) {
		                	Integer d = Integer.parseInt(hpt.getDirection());
			                String st ="";
							if(d ==0){
								st= "正北";
							}else if(d >0 && d <90){
								st= "东北";
							}else if(d == 90){
								st= "正东";
							}else if(d >90 && d <180){
								st= "东南";
							}else if(d == 180){
								st= "正南";
							}else if(d >180 && d <270){
								st= "西南";
							}else if(d == 270){
								st= "正西";
							}else if(d >270 && d <360){
								st= "西北";
							}else if(d == 360){
								st= "正北";
							}else{
								st= "";
							}
			                contentRow.createCell(6).setCellValue(st);
						}else {
			                contentRow.createCell(6).setCellValue("未知方向");
						}
		                if (hpt.getCarstatus()!=null) {
		                	Integer statu = hpt.getCarstatus();
			                String statuString ="";
			                if(statu == 1){
								statuString = "长时间离线";
							}else if(statu == 2){
								statuString = "离线";
							}else if(statu == 3){
								statuString = "熄火";
							}else if(statu == 4){
								statuString = "停车";
							}else if(statu == 5){
								statuString = "行驶";
							}else if(statu == 6){
								statuString = "报警";
							}else if(statu == 7){
								statuString = "在线";
							}else if(statu == 8){
								statuString = "未定位";
							}else{
								statuString = "";				
							}
			                contentRow.createCell(7).setCellValue(statuString);
						}else {
			                contentRow.createCell(7).setCellValue("");
						}
		                
		                if (hpt.getGpsflag()!=null) {
		                	Integer statu = hpt.getGpsflag();
			                String statuString ="";
			                if(statu == 1 || statu == 2){
								statuString = "已定位";
							}else{
								statuString = "未定位";				
							}
			                contentRow.createCell(8).setCellValue(statuString);
						}else {
			                contentRow.createCell(8).setCellValue("");
						}
		                
		                contentRow.createCell(9).setCellValue(hpt.getGpstime());
		                contentRow.createCell(10).setCellValue(hpt.getBlng());
		                contentRow.createCell(11).setCellValue(hpt.getBlat());
		                contentRow.createCell(12).setCellValue(hpt.getAddress());
		                contentRow.createCell(13).setCellValue(hpt.getCreatetime());
		            }
		        }
		        
		        exceldownway.getCommonExcelListWay(book,fileName);
		        
		        
			} catch (Exception e) {
				try {
					e.printStackTrace();
					getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
    
    
    /**
	 * 营运时长分析
	 * queryOta
	 * @return
	 */
    public String queryOta(){
		try {
			initMap();
			if(ota == null){
				ota = new OperaTimeAnalysis();
			}
			if (StringUtils.isNotEmty(blocid)) {
				ota.setBlocid(Integer.parseInt(blocid));
			}
			if (StringUtils.isNotEmty(carnumber)) {
				ota.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").trim());
//				dbaScore.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"),"utf-8"));
			}
			if (StringUtils.isNotEmty(stime)) {
//				String st = stime + " 00:00:00";
				ota.setStime(URLDecoder.decode(stime, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(etime)) {
//				String et = etime + " 23:59:59";
				ota.setEtime(URLDecoder.decode(etime, "UTF-8").trim());
			}
			Map map = new HashMap();
//			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date df1 = df.parse(ota.getEtime());
			Date df2 = df.parse(ota.getStime());
			long diff = df1.getTime() - df2.getTime();//这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			List<OperaTimeAnalysis> opt = new ArrayList<OperaTimeAnalysis>();
			List<OperaTimeAnalysis> optList = new ArrayList<OperaTimeAnalysis>();

			for(int i=0;i<=days;i++){
				map.put("i",i);
				map.put("time",stime);
				map.put("blocid",ota.getBlocid());
				map.put("carnumber",ota.getCarnumber());
				opt = terminalDeviceInfoService.queryOta(map);
				
				optList.addAll(opt);
				
			}
			List<Double>  data1 = new ArrayList<Double>();		//在线时长
			List<Double>  data2 = new ArrayList<Double>();
			List<Double>  data3 = new ArrayList<Double>();
			
			for(OperaTimeAnalysis optaa : optList){
				data1.add(optaa.getDrivertime());
				data2.add(optaa.getOfflinetime());
				data3.add(optaa.getPassengertime());
			}
			
			
			
			result.put("data1", data1);
			result.put("data2", data2);
			result.put("data3", data3);
			
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction 的方法 queryOta执行出错，原因：" + e, e);
			return ERROR;
		}
	}
    
    /**
	 * 营运里程统计列表
	 * @return
	 */
	public String getOperaMileStatiList(){
		initMap();
		try {
			if(ota==null){
				ota = new OperaTimeAnalysis();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				ota.setUserid(SessionUtils.getUserId());
			}
			
			if(StringUtils.isNotEmty(carnumber)){
				ota.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}
			if(StringUtils.isNotEmty(stime)){
				ota.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				ota.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				ota.setBlocid(Integer.parseInt(blocid));
			}else{
				ota.setBlocid(SessionUtils.getBlocId());
			}
			
			result = terminalDeviceInfoService.getOperaMileStatiList(this.getPage(),this.getLimit(), ota);
			List<OperaTimeAnalysis> list = (List<OperaTimeAnalysis>) result.get("list");
			if(list != null){
				DecimalFormat df = new DecimalFormat("#0.00");
				for(OperaTimeAnalysis info : list){
					if(StringUtils.isNotEmty(info.getDrivermile()) && StringUtils.isNotEmty(info.getDrivermile1()) && Double.parseDouble(info.getDrivermile()) != 0){
						info.setDrivermilepercent(df.format((Double.parseDouble(info.getDrivermile1())/Double.parseDouble(info.getDrivermile()))*100)+"%");
					}
					if(StringUtils.isNotEmty(info.getPassengermile()) && StringUtils.isNotEmty(info.getPassengermile1()) && Double.parseDouble(info.getPassengermile()) != 0){
						info.setPassengermilepercent(df.format((Double.parseDouble(info.getPassengermile1())/Double.parseDouble(info.getPassengermile()))*100)+"%");
					}
				}
			}
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction 的方法 getOperaMileStatiList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
     * Excel营运里程统计
     * @return
     */
    public String exportOms(){
		try {
			initMap();
			fileName="营运里程统计"; 
	   	    HSSFWorkbook book = new HSSFWorkbook();
	        Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
	        Row titleRow= sheet.createRow(0);
			
	        if(ota==null){
				ota = new OperaTimeAnalysis();
			}
			if(SessionUtils.getUser() == null){
				return ERROR;
			}else{
				ota.setUserid(SessionUtils.getUserId());
			}
			
			if(StringUtils.isNotEmty(carnumber)){
				ota.setCarnumber((new String(carnumber.getBytes("iso-8859-1"),"utf-8")).toUpperCase());
			}
			if(StringUtils.isNotEmty(stime)){
				ota.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				ota.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				ota.setBlocid(Integer.parseInt(blocid));
			}else{
				ota.setBlocid(SessionUtils.getBlocId());
			}
			
			result = terminalDeviceInfoService.getOperaMileStatiList(1,Integer.MAX_VALUE, ota);
			List<OperaTimeAnalysis> list = (List<OperaTimeAnalysis>) result.get("list");
			if(list != null){
				DecimalFormat df = new DecimalFormat("#0.00");
				for(OperaTimeAnalysis info : list){
					if(StringUtils.isNotEmty(info.getDrivermile()) && StringUtils.isNotEmty(info.getDrivermile1())){
						info.setDrivermilepercent(df.format((Double.parseDouble(info.getDrivermile1())/Double.parseDouble(info.getDrivermile()))*100)+"%");
					}
					if(StringUtils.isNotEmty(info.getPassengermile()) && StringUtils.isNotEmty(info.getPassengermile1())){
						info.setPassengermilepercent(df.format((Double.parseDouble(info.getPassengermile1())/Double.parseDouble(info.getPassengermile()))*100)+"%");
					}
				}
			}
			 ExcelDownWay exceldownway = new ExcelDownWay();
				// 2.设置列宽（列数要对应上）
				String str = "7,25,25,25,30,30,30,30";
				List<String> numberList = Arrays.asList(str.split(","));
				sheet = exceldownway.setColumnWidth(sheet, numberList);
		        
		        titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
		        titleRow.createCell(1).setCellValue("日期");
		        titleRow.createCell(2).setCellValue("日均行驶总里程(km)");
		        titleRow.createCell(3).setCellValue("日均载客里程(km)");
		        titleRow.createCell(4).setCellValue("日均7:30-21:30行驶总里程(km)");
		        titleRow.createCell(5).setCellValue("日均7:30-21:30行驶总比例");
		        titleRow.createCell(6).setCellValue("日均7:30-21:30载客里程(km)");
		        titleRow.createCell(7).setCellValue("日均7:30-21:30载客里程比例");
		        HSSFCellStyle style = book.createCellStyle();  
		        
		        sheet.setDefaultColumnWidth(20);  
		        sheet.setDefaultRowHeightInPoints(20); 
		        
		        for (int i = 0; i < titleRow.getLastCellNum(); i++) {
					titleRow.getCell(i).setCellStyle(
							exceldownway.setBookHeadStyle(book));
				}
		        
		        if(list.size()>0){
		            for(int i=0;i<list.size();i++){
		                int  index=i+1;
		                Row contentRow= sheet.createRow(index);
		                contentRow.createCell(0).setCellValue(index);
		                OperaTimeAnalysis data = (OperaTimeAnalysis) list.get(i);
		                contentRow.createCell(1).setCellValue(data.getCreatetime());
		                contentRow.createCell(2).setCellValue(data.getDrivermile());
		                contentRow.createCell(3).setCellValue(data.getPassengermile());
		                contentRow.createCell(4).setCellValue(data.getDrivermile1());
		                contentRow.createCell(5).setCellValue(data.getDrivermilepercent());		                
		                contentRow.createCell(6).setCellValue(data.getPassengermile1());
		                contentRow.createCell(7).setCellValue(data.getPassengermilepercent());
		            }
		        }
		        
		        exceldownway.getCommonExcelListWay(book,fileName);
		        
		        
			} catch (Exception e) {
				try {
					e.printStackTrace();
					getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		}
    
    /**
	 * 营运时长统计列表
	 * @return
	 */
	public String getOperaTimeStatiList(){
		initMap();
		try {
			if(ota==null){
				ota = new OperaTimeAnalysis();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				ota.setUserid(SessionUtils.getUserId());
			}
			
			if(StringUtils.isNotEmty(carnumber)){
				ota.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}
			if(StringUtils.isNotEmty(stime)){
				ota.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				ota.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				ota.setBlocid(Integer.parseInt(blocid));
			}else{
				ota.setBlocid(SessionUtils.getBlocId());
			}
			
			result = terminalDeviceInfoService.getOperaTimeStatiList(this.getPage(),this.getLimit(), ota);
			List<OperaTimeAnalysis> list = (List<OperaTimeAnalysis>) result.get("list");
			if(list != null){
				DecimalFormat df = new DecimalFormat("#0.00");
				for(OperaTimeAnalysis info : list){
					if(info.getInlinetime() > 0 && info.getDrivertime1() > 0){
						info.setInlinetimepercent(df.format((info.getDrivertime1()/info.getInlinetime())*100)+"%");
					}
					if(info.getPassengertime() > 0 && info.getPassengertime1() > 0){
						info.setPassengertimepercent(df.format((info.getPassengertime1()/info.getPassengertime())*100)+"%");
					}
				}
			}
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction 的方法 getOperaTimeStatiList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
     * Excel营运时长统计
     * @return
     */
    public String exportOts(){
		try {
			initMap();
			fileName="营运时长统计"; 
	   	    HSSFWorkbook book = new HSSFWorkbook();
	        Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
	        Row titleRow= sheet.createRow(0);
			
	        if(ota==null){
				ota = new OperaTimeAnalysis();
			}
			if(SessionUtils.getUser() == null){
				return ERROR;
			}else{
				ota.setUserid(SessionUtils.getUserId());
			}
			
			if(StringUtils.isNotEmty(carnumber)){
				ota.setCarnumber((new String(carnumber.getBytes("iso-8859-1"),"utf-8")).toUpperCase());
			}
			if(StringUtils.isNotEmty(stime)){
				ota.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				ota.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				ota.setBlocid(Integer.parseInt(blocid));
			}else{
				ota.setBlocid(SessionUtils.getBlocId());
			}
			
			result = terminalDeviceInfoService.getOperaTimeStatiList(1,Integer.MAX_VALUE, ota);
			List<OperaTimeAnalysis> list = (List<OperaTimeAnalysis>) result.get("list");
			if(list != null){
				DecimalFormat df = new DecimalFormat("#0.00");
				for(OperaTimeAnalysis info : list){
					if(info.getInlinetime() > 0 && info.getDrivertime1() > 0){
						info.setInlinetimepercent(df.format((info.getDrivertime1()/info.getInlinetime())*100)+"%");
					}
					if(info.getPassengertime() > 0 && info.getPassengertime1() > 0){
						info.setPassengertimepercent(df.format((info.getPassengertime1()/info.getPassengertime())*100)+"%");
					}
				}
			}
			 ExcelDownWay exceldownway = new ExcelDownWay();
				// 2.设置列宽（列数要对应上）
				String str = "7,25,25,25,30,30,30,30";
				List<String> numberList = Arrays.asList(str.split(","));
				sheet = exceldownway.setColumnWidth(sheet, numberList);
		        
		        titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
		        titleRow.createCell(1).setCellValue("日期");
		        titleRow.createCell(2).setCellValue("日均在线时长(h)");
		        titleRow.createCell(3).setCellValue("日均载客时长(h)");
		        titleRow.createCell(4).setCellValue("日均7:30-21:30在线时长(h)");
		        titleRow.createCell(5).setCellValue("日均7:30-21:30在线时长比例");
		        titleRow.createCell(6).setCellValue("日均7:30-21:30载客时长(h)");
		        titleRow.createCell(7).setCellValue("日均7:30-21:30载客时长比例");
		        HSSFCellStyle style = book.createCellStyle();  
		        
		        sheet.setDefaultColumnWidth(20);  
		        sheet.setDefaultRowHeightInPoints(20); 
		        
		        for (int i = 0; i < titleRow.getLastCellNum(); i++) {
					titleRow.getCell(i).setCellStyle(
							exceldownway.setBookHeadStyle(book));
				}
		        
		        if(list.size()>0){
		            for(int i=0;i<list.size();i++){
		                int  index=i+1;
		                Row contentRow= sheet.createRow(index);
		                contentRow.createCell(0).setCellValue(index);
		                OperaTimeAnalysis data = (OperaTimeAnalysis) list.get(i);
		                contentRow.createCell(1).setCellValue(data.getCreatetime());
		                contentRow.createCell(2).setCellValue(data.getInlinetime());
		                contentRow.createCell(3).setCellValue(data.getPassengertime());
		                contentRow.createCell(4).setCellValue(data.getDrivertime1());
		                contentRow.createCell(5).setCellValue(data.getInlinetimepercent());		                
		                contentRow.createCell(6).setCellValue(data.getPassengertime1());
		                contentRow.createCell(7).setCellValue(data.getPassengertimepercent());
		            }
		        }
		        
		        exceldownway.getCommonExcelListWay(book,fileName);
		        
		        
			} catch (Exception e) {
				try {
					e.printStackTrace();
					getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		}
    /**
     * 日均营运统计列表
     * getOperaDayStatiList
     */
    public String getOperaDayStatiList(){
		initMap();
		try {
			if(ota==null){
				ota = new OperaTimeAnalysis();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				ota.setUserid(SessionUtils.getUserId());
			}
			
			if(StringUtils.isNotEmty(carnumber)){
				ota.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}
			if(StringUtils.isNotEmty(stime)){
				ota.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				ota.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				ota.setBlocid(Integer.parseInt(blocid));
			}else{
				ota.setBlocid(SessionUtils.getBlocId());
			}
			result = terminalDeviceInfoService.getOperaDayStatiList(this.getPage(),this.getLimit(), ota);
			
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction 的方法 getOperaDayStatiList 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
    /**
     * 日均营运统计  导出excel
     * exportOperaDayStati
     */
    public String exportOperaDayStati(){
		try {
			initMap();
			fileName="日均营运统计"; 
	   	    HSSFWorkbook book = new HSSFWorkbook();
	        Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
	        Row titleRow= sheet.createRow(0);
			
	        if(ota==null){
				ota = new OperaTimeAnalysis();
			}
			if(SessionUtils.getUser() == null){
				return ERROR;
			}else{
				ota.setUserid(SessionUtils.getUserId());
			}
			
			if(StringUtils.isNotEmty(carnumber)){
				ota.setCarnumber((new String(carnumber.getBytes("iso-8859-1"),"utf-8")).toUpperCase());
			}
			if(StringUtils.isNotEmty(stime)){
				ota.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				ota.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				ota.setBlocid(Integer.parseInt(blocid));
			}else{
				ota.setBlocid(SessionUtils.getBlocId());
			}
			
			result = terminalDeviceInfoService.getOperaDayStatiList(1,Integer.MAX_VALUE, ota);
			
			List<OperaTimeAnalysis> list = (List<OperaTimeAnalysis>) result.get("list");
			
			
			 ExcelDownWay exceldownway = new ExcelDownWay();
				// 2.设置列宽（列数要对应上）
				String str = "7,30,30,30,30,30,30,30,30,30";
				List<String> numberList = Arrays.asList(str.split(","));
				sheet = exceldownway.setColumnWidth(sheet, numberList);
		        
		        titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
		        titleRow.createCell(1).setCellValue("日期");
		        titleRow.createCell(2).setCellValue("日均营运收入(元)");
		        titleRow.createCell(3).setCellValue("日均行驶里程（公里）");
		        titleRow.createCell(4).setCellValue("日均载客里程（公里）");
		        titleRow.createCell(5).setCellValue("日均在线时长（小时）");
		        titleRow.createCell(6).setCellValue("日均载客时长（小时）");
		        titleRow.createCell(7).setCellValue("日均等待时间（分钟）");
		        titleRow.createCell(8).setCellValue("日均计费时间（分钟）");
		        titleRow.createCell(9).setCellValue("日均载客次数");
		        HSSFCellStyle style = book.createCellStyle();  
		        
		        sheet.setDefaultColumnWidth(20);  
		        sheet.setDefaultRowHeightInPoints(20); 
		        
		        for (int i = 0; i < titleRow.getLastCellNum(); i++) {
					titleRow.getCell(i).setCellStyle(
							exceldownway.setBookHeadStyle(book));
				}
		        
		        if(list.size()>0){
		            for(int i=0;i<list.size();i++){
		                int  index=i+1;
		                Row contentRow= sheet.createRow(index);
		                contentRow.createCell(0).setCellValue(index);
		                OperaTimeAnalysis data = (OperaTimeAnalysis) list.get(i);
		                contentRow.createCell(1).setCellValue(data.getCreatetime());
		                contentRow.createCell(2).setCellValue(data.getIncome());
		                contentRow.createCell(3).setCellValue(data.getDrivermile());
		                contentRow.createCell(4).setCellValue(data.getPassengermile());
		                contentRow.createCell(5).setCellValue(data.getInlinetime());		                
		                contentRow.createCell(6).setCellValue(data.getPassengertime());
		                contentRow.createCell(7).setCellValue(data.getWaittime());
		                contentRow.createCell(8).setCellValue(data.getFeetime());
		                contentRow.createCell(9).setCellValue(data.getPassengercount());
		            }
		        }
		        
		        exceldownway.getCommonExcelListWay(book,fileName);
		        
		        
			} catch (Exception e) {
				try {
					e.printStackTrace();
					getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		}
    
    /**
	 * 营运里程分析
	 * queryOta
	 * @return
	 */
    public String queryOma(){
		try {
			initMap();
			if(ota == null){
				ota = new OperaTimeAnalysis();
			}
			if (StringUtils.isNotEmty(blocid)) {
				ota.setBlocid(Integer.parseInt(blocid));
			}
			if (StringUtils.isNotEmty(carnumber)) {
				ota.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").trim());
//				dbaScore.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"),"utf-8"));
			}
			if (StringUtils.isNotEmty(stime)) {
//				String st = stime + " 00:00:00";
				ota.setStime(URLDecoder.decode(stime, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(etime)) {
//				String et = etime + " 23:59:59";
				ota.setEtime(URLDecoder.decode(etime, "UTF-8").trim());
			}
			Map map = new HashMap();
//			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date df1 = df.parse(ota.getEtime());
			Date df2 = df.parse(ota.getStime());
			long diff = df1.getTime() - df2.getTime();//这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			List<OperaTimeAnalysis> opt = new ArrayList<OperaTimeAnalysis>();
			List<OperaTimeAnalysis> optList = new ArrayList<OperaTimeAnalysis>();

			for(int i=0;i<=days;i++){
				map.put("i",i);
				map.put("time",stime);
				map.put("blocid",ota.getBlocid());
				map.put("carnumber",ota.getCarnumber());
				opt = terminalDeviceInfoService.queryOma(map);
				
				optList.addAll(opt);
				
			}
			List<Double>  data1 = new ArrayList<Double>();		//在线里程
			List<Double>  data2 = new ArrayList<Double>();
			List<Double>  data3 = new ArrayList<Double>();
			
			for(OperaTimeAnalysis optaa : optList){
				data1.add(Double.parseDouble(optaa.getDrivermile()));
				data2.add(Double.parseDouble(optaa.getEmptymile()));
				data3.add(Double.parseDouble(optaa.getPassengermile()));
			}
			
			
			
			result.put("data1", data1);
			result.put("data2", data2);
			result.put("data3", data3);
			
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction 的方法 queryOma执行出错，原因：" + e, e);
			return ERROR;
		}
	}
    /**
     * 数据营运时长分析
     * @return
     */
    public String getOtaList(){
    	initMap();
		try {
			if(ota==null){
				ota = new OperaTimeAnalysis();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				ota.setUserid(SessionUtils.getUserId());
			}
			
			if(StringUtils.isNotEmty(carnumber)){
				ota.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(stime)){
				ota.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				ota.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				ota.setBlocid(Integer.parseInt(blocid));
			}else{
				ota.setBlocid(SessionUtils.getBlocId());
			}
			List<OperaTimeAnalysis> list= terminalDeviceInfoService.exportOta(ota);
			result.put("list",list);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TerminalDeviceInfoAction 的方法 getOtaList 执行出错，原因：" + e, e);
			return ERROR;
		}
    }
    
    /**
     * 导出营运时长分析
     * @return
     */
    
    public void exportOta(){
		try {
			initMap();
			fileName="车辆实时信息"; 
	   	    HSSFWorkbook book = new HSSFWorkbook();
	        Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
	        Row titleRow= sheet.createRow(0);
			
	        if(ota == null){
				ota = new OperaTimeAnalysis();
			}
			if (StringUtils.isNotEmty(blocid)) {
				ota.setBlocid(Integer.parseInt(blocid));
			}
			if (StringUtils.isNotEmty(carnumber)) {
				ota.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").trim());
//				dbaScore.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"),"utf-8"));
			}
			if (StringUtils.isNotEmty(stime)) {
				String st = stime + " 00:00:00";
				ota.setStime(URLDecoder.decode(st, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(etime)) {
				String et = etime + " 23:59:59";
				ota.setEtime(URLDecoder.decode(et, "UTF-8").trim());
			}
			
			List<OperaTimeAnalysis> list= terminalDeviceInfoService.exportOta(ota);
			 ExcelDownWay exceldownway = new ExcelDownWay();
				// 2.设置列宽（列数要对应上）
				String str = "7,25,25,25,25,25";
				List<String> numberList = Arrays.asList(str.split(","));
				sheet = exceldownway.setColumnWidth(sheet, numberList);
		        
		        titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
		        titleRow.createCell(1).setCellValue("企业名称");
		        titleRow.createCell(2).setCellValue("车牌号");
		        titleRow.createCell(3).setCellValue("行驶时长");
		        titleRow.createCell(4).setCellValue("空车时长");
		        titleRow.createCell(5).setCellValue("载客时长");
		        HSSFCellStyle style = book.createCellStyle();  
		        
		        sheet.setDefaultColumnWidth(20);  
		        sheet.setDefaultRowHeightInPoints(20); 
		        
		        for (int i = 0; i < titleRow.getLastCellNum(); i++) {
					titleRow.getCell(i).setCellStyle(
							exceldownway.setBookHeadStyle(book));
				}
		        
		        if(list.size()>0){
		            for(int i=0;i<list.size();i++){
		                int  index=i+1;
		                Row contentRow= sheet.createRow(index);
		                contentRow.createCell(0).setCellValue(index);
		                OperaTimeAnalysis op= (OperaTimeAnalysis) list.get(i);
		                contentRow.createCell(1).setCellValue(op.getBlocname());
		                contentRow.createCell(2).setCellValue(op.getCarnumber());
		                contentRow.createCell(3).setCellValue(op.getDrivertime());
		                contentRow.createCell(4).setCellValue(op.getOfflinetime());
		                contentRow.createCell(5).setCellValue(op.getPassengertime());
		            }
		        }
		        
		        exceldownway.getCommonExcelListWay(book,fileName);
		        
		        
			} catch (Exception e) {
				try {
					e.printStackTrace();
					getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
    
	public TerminalDeviceInfoService getTerminalDeviceInfoService() {
		return terminalDeviceInfoService;
	}
	public void setTerminalDeviceInfoService(
			TerminalDeviceInfoService terminalDeviceInfoService) {
		this.terminalDeviceInfoService = terminalDeviceInfoService;
	}


	public Map getResult() {
		return result;
	}
	public void setResult(Map result) {
		this.result = result;
	}

	public TerminalPositionInfo getTerminalPositionInfo() {
		return terminalPositionInfo;
	}

	public void setTerminalPositionInfo(TerminalPositionInfo terminalPositionInfo) {
		this.terminalPositionInfo = terminalPositionInfo;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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

	public String getLng1() {
		return lng1;
	}

	public void setLng1(String lng1) {
		this.lng1 = lng1;
	}

	public String getLat1() {
		return lat1;
	}

	public void setLat1(String lat1) {
		this.lat1 = lat1;
	}

	public String getMaptype() {
		return maptype;
	}

	public void setMaptype(String maptype) {
		this.maptype = maptype;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}


	public String getUsename() {
		return usename;
	}

	public void setUsename(String usename) {
		this.usename = usename;
	}

	public String getCarstatus() {
		return carstatus;
	}

	public void setCarstatus(String carstatus) {
		this.carstatus = carstatus;
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

	public String getSortcolumn() {
		return sortcolumn;
	}


	public void setSortcolumn(String sortcolumn) {
		this.sortcolumn = sortcolumn;
	}

	public String getSortway() {
		return sortway;
	}

	public void setSortway(String sortway) {
		this.sortway = sortway;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public MapInfo getData() {
		return data;
	}

	public void setData(MapInfo data) {
		this.data = data;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getBlocid() {
		return blocid;
	}

	public void setBlocid(String blocid) {
		this.blocid = blocid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public String getCarids() {
		return carids;
	}

	public void setCarids(String carids) {
		this.carids = carids;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RemoteControlRecordService getRemoteControlRecordService() {
		return remoteControlRecordService;
	}

	public void setRemoteControlRecordService(
			RemoteControlRecordService remoteControlRecordService) {
		this.remoteControlRecordService = remoteControlRecordService;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDelayed() {
		return delayed;
	}

	public void setDelayed(String delayed) {
		this.delayed = delayed;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}


	public HistoryPositionInfo getHistoryPos() {
		return historyPos;
	}

	public void setHistoryPos(HistoryPositionInfo historyPos) {
		this.historyPos = historyPos;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Carrealtime getCarrealtime() {
		return carrealtime;
	}

	public void setCarrealtime(Carrealtime carrealtime) {
		this.carrealtime = carrealtime;
	}

	public OperaTimeAnalysis getOta() {
		return ota;
	}

	public void setOta(OperaTimeAnalysis ota) {
		this.ota = ota;
	}

	public String getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(String gpsflag) {
		this.gpsflag = gpsflag;
	}

	public String getCars() {
		return cars;
	}

	public void setCars(String cars) {
		this.cars = cars;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public TerminalPositionInfo getTpi() {
		return tpi;
	}

	public void setTpi(TerminalPositionInfo tpi) {
		this.tpi = tpi;
	}

	public Transaction getLastOrderInfo() {
		return lastOrderInfo;
	}

	public void setLastOrderInfo(Transaction lastOrderInfo) {
		this.lastOrderInfo = lastOrderInfo;
	}
	
	

	
}





