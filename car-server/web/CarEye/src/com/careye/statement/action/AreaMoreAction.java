/**
 * Description: car-eye车辆管理平台
 * 文件名：AreaSetAction.java
 * 版本信息：1.0
 * 日期：2014-5-26
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.statement.action;

import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.AreaCar;
import com.careye.car.domain.AreaSet;
import com.careye.car.service.AreaSetService;
import com.careye.car.service.CarService;
import com.careye.common.domain.TerminalPositionInfo;
import com.careye.mq.domain.ZoneAlarm;
import com.careye.statement.domain.AreaMore;
import com.careye.statement.service.AreaMoreService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：AreaSetAction
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-19 下午01:47:32
 * @修改人：huangqin
 * @修改时间：2015-5-19 下午01:47:32
 * @修改备注：
 * @version 1.0
 */
public class AreaMoreAction extends BasePageAction{
	private static final Logger logger = Logger.getLogger(AreaMoreAction.class);
	private AreaMoreService areaMoreService;
	private CarService carService;

	private AreaMore areaMore;
	private AreaCar areaCar;
	private ZoneAlarm zoneAlarm;

	private Map result;
	private String success;
	private List list;
	private String ids;
	private String areatype;

	private String carids;
	private String areaids;

	private String carnumber;
	private String blocid;
	private String areaid;
	
	private String lng;
	private String lat;
	private String mileage;
	
	
	private TerminalPositionInfo terminalPositionInfo;
	
	public String getBlocid() {
		return blocid;
	}

	public void setBlocid(String blocid) {
		this.blocid = blocid;
	}

	private Integer opertype;
	private String terminal;
	private String areaname;
	private String ylng;
	private String ylat;
	private String radius;
	
	private String latlt;
	private String lnglt;
	private String latrb;
	private String lngrb;
	
	private String id;
	
	private List<ZoneAlarm> zlist = new ArrayList<ZoneAlarm>();	
	private List<ZoneAlarm> ylist = new ArrayList<ZoneAlarm>();	//圆形区域
	private List<ZoneAlarm> jlist = new ArrayList<ZoneAlarm>();	//矩形区域
	private List<ZoneAlarm> dlist = new ArrayList<ZoneAlarm>();	//多边形区域
	
	public String getAreatype() {
		return areatype;
	}

	public List<ZoneAlarm> getZlist() {
		return zlist;
	}

	public void setZlist(List<ZoneAlarm> zlist) {
		this.zlist = zlist;
	}

	public List<ZoneAlarm> getYlist() {
		return ylist;
	}

	public void setYlist(List<ZoneAlarm> ylist) {
		this.ylist = ylist;
	}

	public List<ZoneAlarm> getJlist() {
		return jlist;
	}

	public void setJlist(List<ZoneAlarm> jlist) {
		this.jlist = jlist;
	}

	public List<ZoneAlarm> getDlist() {
		return dlist;
	}

	public void setDlist(List<ZoneAlarm> dlist) {
		this.dlist = dlist;
	}

	public void setAreatype(String areatype) {
		this.areatype = areatype;
	}


	public String getYlng() {
		return ylng;
	}

	public void setYlng(String ylng) {
		this.ylng = ylng;
	}

	public String getYlat() {
		return ylat;
	}

	public void setYlat(String ylat) {
		this.ylat = ylat;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	String ydata = "";
	String jdata = "";
	String ddata = "";


	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}

	
	
	/**
	 * 分页查询多区域
	 * @return
	 */
	public String queryAreaMoreList() {

		try {
			initMap();
			if(areaMore==null){
				areaMore=new AreaMore();
			}

			if(areaname!=null&&!areaname.equals("")&&!areaname.equals("null")){
				areaMore.setAreaname(URLDecoder.decode(areaname,"UTF-8").trim());
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				areaMore.setUserid(SessionUtils.getUserId());
			}
			result=areaMoreService.queryPageAreaMoreList(this.getPage(),this.getLimit(), areaMore);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AreaMoreAction的方法 queryAreaMoreList执行出错，原因：" + e, e);
			return ERROR;
		}
	}


	/**
	 * 添加或修改系统区域
	 * @param areaMore
	 * @return
	 */
	public String saveAreaMore(){
		try {
			initMap();
			if(areaMore==null){
				areaMore=new AreaMore();
			}
			if(StringUtils.isNotEmty(areatype)){
				areaMore.setAreatype(Integer.parseInt(areatype));
				if(Integer.parseInt(areatype) == 1){
					if(StringUtils.isNotEmty(radius)){
						areaMore.setRadius(radius);
					}
				}
			}
			if(StringUtils.isNotEmty(areaname)){
				areaMore.setAreaname(URLDecoder.decode(areaname,"UTF-8"));
			}
			if(StringUtils.isNotEmty(ylng)){
				areaMore.setYlng(ylng);
			}
			if(StringUtils.isNotEmty(ylat)){
				areaMore.setYlat(ylat);
			}
			if(StringUtils.isNotEmty(latlt)){
				areaMore.setLatlt(latlt);
			}
			if(StringUtils.isNotEmty(lnglt)){
				areaMore.setLnglt(lnglt);
			}
			if(StringUtils.isNotEmty(latrb)){
				areaMore.setLatrb(latrb);
			}
			if(StringUtils.isNotEmty(lngrb)){
				areaMore.setLngrb(lngrb);
			}
			if(StringUtils.isNotEmty(id)){
				areaMore.setId(Integer.parseInt(id));
			}
			int su=-1;
			areaMore.setUserid(SessionUtils.getUserId());
			if(areaMore.getId()==null){
				areaMore.setBlocid(SessionUtils.getUser().getBlocid());
				su = areaMoreService.insertAreaMore(areaMore);
			}else{
				su=areaMoreService.updateAreaMore(areaMore);
			}
			result.put("su", su);
			this.success="true";
			return SUCCESS;
		} catch (Exception e) {
			this.success="false";
			logger.error("AreaMoreAction的方法 saveAreaMore执行出错，原因：" + e, e);
			return ERROR;
		}
	}



	/**
	 * 删除区域设置
	 * @param areaMore
	 * @return
	 */
	public String deleteAreaMore(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				//查看是否已经被车辆追加
				//int count = areaMoreService.queryAreaMoreNameIsExits(Integer.parseInt(id[i]));
				//if(count <= 0){
					result.put("su",areaMoreService.deleteAreaMore(Integer.parseInt(id[i])));
				//}else{
				//	result.put("su", -2);
				//}
			}
			return SUCCESS;
		} catch (Exception e) {
			result.put("su", -1);
			logger.error("AreaMoreAction的方法 deleteAreaMore执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 查询根据区域类型查询相关车辆位置信息  不分页
	 * @return
	 */
	public String queryCarPositionInfoList() {

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
			if(StringUtils.isNotEmty(blocid)){
				terminalPositionInfo.setBlocid(Integer.parseInt(blocid));
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
			if(StringUtils.isNotEmty(latlt)){
				terminalPositionInfo.setLatlt(latlt);
			}
			if(StringUtils.isNotEmty(lnglt)){
				terminalPositionInfo.setLnglt(lnglt);
			}
			if(StringUtils.isNotEmty(latrb)){
				terminalPositionInfo.setLatrb(latrb);
			}
			if(StringUtils.isNotEmty(lngrb)){
				terminalPositionInfo.setLngrb(lngrb);
			}
			if(StringUtils.isNotEmty(ids)){
				//根据id找到对象
				List<String> idList = Arrays.asList(ids.split(","));
				terminalPositionInfo.setIds(idList);
			}
			
			terminalPositionInfo.setUserid(SessionUtils.getUserId());
			
			list =  areaMoreService.queryCarPositionInfoList(terminalPositionInfo,terminalPositionInfo);
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("PoiInfoAction的方法 queryCarInfoList执行出错，原因：" + e, e);
			return ERROR;
		}
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

	public TerminalPositionInfo getTerminalPositionInfo() {
		return terminalPositionInfo;
	}

	public void setTerminalPositionInfo(TerminalPositionInfo terminalPositionInfo) {
		this.terminalPositionInfo = terminalPositionInfo;
	}

	public AreaMoreService getAreaMoreService() {
		return areaMoreService;
	}

	public void setAreaMoreService(AreaMoreService areaMoreService) {
		this.areaMoreService = areaMoreService;
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public AreaMore getAreaMore() {
		return areaMore;
	}

	public void setAreaMore(AreaMore areaMore) {
		this.areaMore = areaMore;
	}

	public AreaCar getAreaCar() {
		return areaCar;
	}

	public void setAreaCar(AreaCar areaCar) {
		this.areaCar = areaCar;
	}

	public ZoneAlarm getZoneAlarm() {
		return zoneAlarm;
	}

	public void setZoneAlarm(ZoneAlarm zoneAlarm) {
		this.zoneAlarm = zoneAlarm;
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

	public String getCarids() {
		return carids;
	}

	public void setCarids(String carids) {
		this.carids = carids;
	}

	public String getAreaids() {
		return areaids;
	}

	public void setAreaids(String areaids) {
		this.areaids = areaids;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getOpertype() {
		return opertype;
	}

	public void setOpertype(Integer opertype) {
		this.opertype = opertype;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getLatlt() {
		return latlt;
	}

	public void setLatlt(String latlt) {
		this.latlt = latlt;
	}

	public String getLnglt() {
		return lnglt;
	}

	public void setLnglt(String lnglt) {
		this.lnglt = lnglt;
	}

	public String getLatrb() {
		return latrb;
	}

	public void setLatrb(String latrb) {
		this.latrb = latrb;
	}

	public String getLngrb() {
		return lngrb;
	}

	public void setLngrb(String lngrb) {
		this.lngrb = lngrb;
	}

	public String getYdata() {
		return ydata;
	}

	public void setYdata(String ydata) {
		this.ydata = ydata;
	}

	public String getJdata() {
		return jdata;
	}

	public void setJdata(String jdata) {
		this.jdata = jdata;
	}

	public String getDdata() {
		return ddata;
	}

	public void setDdata(String ddata) {
		this.ddata = ddata;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}


}
