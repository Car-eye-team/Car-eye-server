/**
* Description: car-eye车辆管理平台
* 文件名：PoiInfoAction.java
* 版本信息：1.0
* 日期：2014-6-4
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarDriver;
import com.careye.car.domain.CarDriverInfo;
import com.careye.car.domain.CarInfo;
import com.careye.car.domain.PoiInfo;
import com.careye.car.service.CarService;
import com.careye.car.service.PoiInfoService;
import com.careye.common.domain.TerminalPositionInfo;
import com.careye.http.BaiDuHttp;
import com.careye.mongodb.MongoDB;
import com.careye.mq.HandleUtil;
import com.careye.system.domain.BlocUser;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：FMS
 * @类名称：PoiInfoAction
 * @类描述：POI信息ACTION
 * @创建人：zr
 * @创建时间：2014-6-4 上午11:55:39
 * @修改人：zr
 * @修改时间：2014-6-4 上午11:55:39
 * @修改备注：
 * @version 1.0
 */
public class PoiInfoAction extends BasePageAction{
	
	private static final Logger logger = Logger.getLogger(PoiInfoAction.class);
	private PoiInfoService poiInfoService;
	private CarService carService;
	private PoiInfo poiInfo;
	private TerminalPositionInfo terminalPositionInfo;
	private Map result;
	private String carids;
	
	private List list;
	private String carnumber;
	private String stime;
	private String etime;
	private String query;
	private String city;
	
	private String lng;
	private String lat;
	private String mileage;
	private String kzstate;
	
	private String uplng1;
	private String uplat1;
	private String uplng2;
	private String uplat2;
	private String downlng1;
	private String downlat1;
	private String downlng2;
	private String downlat2;
	private String stime1;
	private String etime1;
	private String stime2;
	private String etime2;
	
	private String terminal;
	private String deptid;
	private String usertype;
	private String ids;
	
	private String fileName;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 发送poi信息
	 * @return
	 */
	public String sendPoiInfo(){
		try {
			initMap();
			if(poiInfo==null){
				poiInfo=new PoiInfo();
			}
			
			String [] caridsAr = carids.split(",");
			for (int i = 0; i < caridsAr.length; i++) {
				
				//根据车辆ID获取车辆信息
				CarInfo carinfo = carService.getCarInfoCarId(Integer.parseInt(caridsAr[i]));
				if(carinfo != null){
					BlocUser user = SessionUtils.getUser();
					String systemdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); // 获取系统当前时间
					int seq=HandleUtil.getSerialId();
					String packdata = HandleUtil.sendPoiInfo(carinfo.getUsertype(),carinfo.getTerminal(), poiInfo.getLng(), poiInfo.getLat(), poiInfo.getPoiname(), seq,carinfo.getCarnumber());
					poiInfo.setSeq(seq);
					poiInfo.setCarnumber(carinfo.getCarnumber());
					poiInfo.setDatapacket(packdata);
					poiInfo.setCreatetime(systemdate);
					poiInfo.setBlocid(user.getBlocid());
					poiInfo.setUserid(user.getId());
					poiInfoService.insertPoiInfo(poiInfo);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("PoiInfoAction的方法 sendPoiInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 获取发送记录列表
	 * @return
	 */
	public String queryPoiInfoRecordList(){

		try {
			initMap();
			if(poiInfo==null){
				poiInfo=new PoiInfo();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(StringUtils.isNotEmty(carnumber)){
				poiInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase().trim());
			}
			
			if(StringUtils.isNotEmty(stime)){
				poiInfo.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			
			if(StringUtils.isNotEmty(etime)){
				poiInfo.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			
			result = poiInfoService.queryPoiInfoRecordList(this.getPage(),this.getLimit(), poiInfo);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("PoiInfoAction的方法 queryPoiInfoRecordList执行出错，原因：" + e, e);
			return ERROR;
		}
	
	}
	
	/**
	 * 删除记录deleteRecord
	 */
	public String deleteRecord(){
		try {
			initMap();
			String id[] = ids.split(",");
			
			for (int i = 0; i < id.length; i++) {
				int count = poiInfoService.deleteRecord(Integer.parseInt(id[i]));
				if(count>0){
					result.put("su", 0);
				}else {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("PoiInfoAction 的方法 deleteRecord执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 获取城市内检索请求地址列表
	 * @return
	 */
	public String getResultByCity() {
		try {
			initMap();
			if(StringUtils.isEmty(query)){
				list = new ArrayList();
				return SUCCESS;
			}else{
//				String q = URLDecoder.decode(query, "UTF-8");
//				city = URLDecoder.decode(city, "UTF-8");
				list = BaiDuHttp.getResultByCity(query,city);
			}
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 根据距离经纬度条件查询车辆信息取数据库
	 * @return
	 */
	public String queryCarInfoList(){

		try {
			initMap();
			if(terminalPositionInfo==null){
				terminalPositionInfo=new TerminalPositionInfo();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(StringUtils.isNotEmty(carnumber)){
				terminalPositionInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase().trim());
			}
			if(StringUtils.isNotEmty(lat)){
				terminalPositionInfo.setLat(Double.parseDouble(lat));
			}
			if(StringUtils.isNotEmty(lng)){
				terminalPositionInfo.setLng(Double.parseDouble(lng));
			}
			if(StringUtils.isNotEmty(mileage)){
				terminalPositionInfo.setMileage(URLDecoder.decode(mileage,"UTF-8"));
			}
			if(StringUtils.isNotEmty(kzstate)){
				terminalPositionInfo.setKzstate(URLDecoder.decode(kzstate,"UTF-8"));
			}
			terminalPositionInfo.setUserid(SessionUtils.getUserId());
			
//			result = poiInfoService.queryCarInfoList(this.getPage(),this.getLimit(), terminalPositionInfo);
			list =  poiInfoService.queryCarInfoList(terminalPositionInfo);
			result.put("list",list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("PoiInfoAction的方法 queryCarInfoList执行出错，原因：" + e, e);
			return ERROR;
		}
	
	}
	
	/**
	 * 根据距离经纬度条件查询车辆信息取mongo
	 * @return
	 */
	public String queryCarInfoListByMongo(){
		
		try {
			initMap();
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			
			List<Integer> idList =  MongoDB.getInstance().queryPositionGroup(uplng1,uplat1,uplng2,uplat2
							,downlng1,downlat1,downlng2,downlat2,stime1,etime1,stime2,etime2);
			if(idList != null && idList.size() > 0){
				CarDriverInfo carDriverInfo = new CarDriverInfo();
				carDriverInfo.setList(idList);
				list = carService.getCarDriverInfoList(carDriverInfo);
			}
			
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("PoiInfoAction的方法 queryCarInfoListByMongo执行出错，原因：" + e, e);
			return ERROR;
		}
		
	}
	
	
	
	/**
     * Excel导出
     * @throws IOException
     */
	public  void   exportPoiInfoRecordExcel(){
		try {
	     //1.设置名字
		 fileName="POI发送记录信息"; 
    	 HSSFWorkbook book = new HSSFWorkbook();
         Sheet sheet= book.createSheet(fileName);
         //sheet.setDefaultColumnWidth(15);
         
         ExcelDownWay exceldownway= new ExcelDownWay();
         
         //2.设置列宽（列数要对应上）
         String str="7,20,15,15,15,30,10,20";
         List<String> numberList=Arrays.asList(str.split(","));
         sheet= exceldownway.setColumnWidth(sheet,numberList);
         
         sheet.setDefaultRowHeight((short) 18);
         Row titleRow= sheet.createRow(0);
         titleRow.setHeightInPoints(20);
         
         if(poiInfo==null){
				poiInfo=new PoiInfo();
			}
			if(StringUtils.isNotEmty(carnumber)){
				poiInfo.setCarnumber(new String(carnumber.getBytes("iso8859-1"),"utf-8").toUpperCase().trim());
			}
			
			if(StringUtils.isNotEmty(stime)){
				poiInfo.setStime(new String(stime.getBytes("iso8859-1"),"utf-8"));
			}
			
			if(StringUtils.isNotEmty(etime)){
				poiInfo.setEtime(new String(etime.getBytes("iso8859-1"),"utf-8"));
			}
         
         list= poiInfoService.selectPoiInfoRecordList(poiInfo);   //Excel查询
         
         titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
         titleRow.createCell(1).setCellValue("集团");
         titleRow.createCell(2).setCellValue("车牌号");
         titleRow.createCell(3).setCellValue("经度");
         titleRow.createCell(4).setCellValue("纬度");
         titleRow.createCell(5).setCellValue("POI名称");
         titleRow.createCell(6).setCellValue("处理结果");
         titleRow.createCell(7).setCellValue("发送时间");
         
         for(int i=0;i<titleRow.getLastCellNum();i++){
        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
         }
         
         if(list.size()>0){
             for(int i=0;i<list.size();i++){
                 int  index=i+1;
                 Row contentRow= sheet.createRow(index);
                 contentRow.createCell(0).setCellValue(index);
                 PoiInfo poiInfo= (PoiInfo) list.get(i);
                 contentRow.createCell(1).setCellValue(poiInfo.getBlocname());
                 contentRow.createCell(2).setCellValue(poiInfo.getCarnumber());
                 contentRow.createCell(3).setCellValue(poiInfo.getLng());
                 contentRow.createCell(4).setCellValue(poiInfo.getLat());
                 contentRow.createCell(5).setCellValue(poiInfo.getPoiname());
                 
                 String resultString=null;
                 if(poiInfo.getResult()!=null){
                	 int result=poiInfo.getResult();
                	 if(result==1){
                    	 resultString="成功";
                     }else if(result==2){
                    	 resultString="失败";
                     }
                 }
                
                 contentRow.createCell(6).setCellValue(resultString);
                 contentRow.createCell(7).setCellValue(poiInfo.getCreatetime());
                 
                 for(int m=0;m<contentRow.getLastCellNum();m++){
                	 contentRow.getCell(m).setCellStyle(exceldownway.setBookListStyle(book));
                 }
             }
         }
         
         exceldownway.getCommonExcelListWay(book,fileName);
         
	} catch (Exception e) {
		try {
			getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
    }
	
	/**
	 * 参数设置车辆列表
	 * @return
	 */
	public String queryDsobdParamSetList() {
		
		try {
			initMap();
			if(poiInfo==null){
				poiInfo=new PoiInfo();
			}
			if(terminal!=null&&!terminal.equals("")&&!terminal.equals("null")){
				poiInfo.setTerminal(URLDecoder.decode(terminal,"UTF-8"));
			}
			if(deptid!=null&&!deptid.equals("")&&!deptid.equals("null")){
				poiInfo.setBlocid(Integer.parseInt(deptid));
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				poiInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			if(usertype!=null&&!usertype.equals("")&&!usertype.equals("null")){
				poiInfo.setUsertype(Integer.parseInt(usertype));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				poiInfo.setUserid(SessionUtils.getUserId());
			}
			
			result=poiInfoService.queryDsobdParamSetList(this.getPage(),this.getLimit(), poiInfo);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("PoiInfoAction的方法 queryDsobdParamSetList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	

	public PoiInfoService getPoiInfoService() {
		return poiInfoService;
	}

	public void setPoiInfoService(PoiInfoService poiInfoService) {
		this.poiInfoService = poiInfoService;
	}

	public PoiInfo getPoiInfo() {
		return poiInfo;
	}

	public void setPoiInfo(PoiInfo poiInfo) {
		this.poiInfo = poiInfo;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public String getCarids() {
		return carids;
	}

	public void setCarids(String carids) {
		this.carids = carids;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public String getQuery() {
		return query;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public TerminalPositionInfo getTerminalPositionInfo() {
		return terminalPositionInfo;
	}

	public void setTerminalPositionInfo(TerminalPositionInfo terminalPositionInfo) {
		this.terminalPositionInfo = terminalPositionInfo;
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

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getUplng1() {
		return uplng1;
	}

	public void setUplng1(String uplng1) {
		this.uplng1 = uplng1;
	}

	public String getUplat1() {
		return uplat1;
	}

	public void setUplat1(String uplat1) {
		this.uplat1 = uplat1;
	}

	public String getUplng2() {
		return uplng2;
	}

	public void setUplng2(String uplng2) {
		this.uplng2 = uplng2;
	}

	public String getUplat2() {
		return uplat2;
	}

	public void setUplat2(String uplat2) {
		this.uplat2 = uplat2;
	}

	public String getDownlng1() {
		return downlng1;
	}

	public void setDownlng1(String downlng1) {
		this.downlng1 = downlng1;
	}

	public String getDownlat1() {
		return downlat1;
	}

	public void setDownlat1(String downlat1) {
		this.downlat1 = downlat1;
	}

	public String getDownlng2() {
		return downlng2;
	}

	public void setDownlng2(String downlng2) {
		this.downlng2 = downlng2;
	}

	public String getDownlat2() {
		return downlat2;
	}

	public void setDownlat2(String downlat2) {
		this.downlat2 = downlat2;
	}

	public String getStime1() {
		return stime1;
	}

	public void setStime1(String stime1) {
		this.stime1 = stime1;
	}

	public String getEtime1() {
		return etime1;
	}

	public void setEtime1(String etime1) {
		this.etime1 = etime1;
	}

	public String getStime2() {
		return stime2;
	}

	public void setStime2(String stime2) {
		this.stime2 = stime2;
	}

	public String getEtime2() {
		return etime2;
	}

	public void setEtime2(String etime2) {
		this.etime2 = etime2;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getKzstate() {
		return kzstate;
	}

	public void setKzstate(String kzstate) {
		this.kzstate = kzstate;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
}
