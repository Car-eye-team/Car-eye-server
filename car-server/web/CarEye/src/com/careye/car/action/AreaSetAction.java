/**
 * Description: car-eye车辆管理平台
 * 文件名：AreaSetAction.java
 * 版本信息：1.0
 * 日期：2014-5-26
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.car.action;

import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.AreaCar;
import com.careye.car.domain.AreaSet;
import com.careye.car.domain.CarInfo;
import com.careye.car.service.AreaSetService;
import com.careye.car.service.CarService;
import com.careye.mq.HandleUtil;
import com.careye.mq.domain.ZoneAlarm;
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
public class AreaSetAction extends BasePageAction{
	private static final Logger logger = Logger.getLogger(AreaSetAction.class);
	private AreaSetService areaSetService;
	private CarService carService;

	private AreaSet areaSet;
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
	private Integer opertype;
	private String terminal;
	private String areaname;
	private String stime;
	private String etime;
	private String maxspeed ;
	private String termvalidity ;
	private String speedtime ;
	private String attr0 ;
	private String attr1;
	private String attr2 ;
	private String attr3 ;
	private String attr4 ;
	private String attr5 ;
	private String ylng;
	private String ylat;
	private String radius;
	
	private String latsrt;
	private String lngsrt;
	
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

	public String getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(String maxspeed) {
		this.maxspeed = maxspeed;
	}

	public String getTermvalidity() {
		return termvalidity;
	}

	public void setTermvalidity(String termvalidity) {
		this.termvalidity = termvalidity;
	}

	public String getSpeedtime() {
		return speedtime;
	}

	public void setSpeedtime(String speedtime) {
		this.speedtime = speedtime;
	}

	public String getAttr0() {
		return attr0;
	}

	public void setAttr0(String attr0) {
		this.attr0 = attr0;
	}

	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getAttr2() {
		return attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public String getAttr3() {
		return attr3;
	}

	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}

	public String getAttr4() {
		return attr4;
	}

	public void setAttr4(String attr4) {
		this.attr4 = attr4;
	}

	public String getAttr5() {
		return attr5;
	}

	public void setAttr5(String attr5) {
		this.attr5 = attr5;
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
	 * 分页查询区域设置
	 * @return
	 */
	public String queryAreaSetList() {

		try {
			initMap();
			if(areaSet==null){
				areaSet=new AreaSet();
			}

			if(areaname!=null&&!areaname.equals("")&&!areaname.equals("null")){
				areaSet.setAreaname(URLDecoder.decode(areaname,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				areaSet.setUserid(SessionUtils.getUserId());
			}
			result=areaSetService.queryPageAreaSetList(this.getPage(),this.getLimit(), areaSet);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AreaSetAction的方法 queryAreaSetList执行出错，原因：" + e, e);
			return ERROR;
		}
	}


	/**
	 * 添加或修改系统区域
	 * @param areaSet
	 * @return
	 */
	public String saveAreaSet(){
		try {
			initMap();
			areaSet.setStime(URLDecoder.decode(areaSet.getStime(), "UTF-8"));
			areaSet.setEtime(URLDecoder.decode(areaSet.getEtime(), "UTF-8"));
			areaSet.setUserid(SessionUtils.getUserId());
			if(areaSet.getId()==null){
				{
					areaSet.setBlocid(SessionUtils.getUser().getBlocid());
					int count = areaSetService.insertAreaSet(areaSet);

					if(count<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					}
				}
			}else{
				int re=areaSetService.updateAreaSet(areaSet);
				if(re<=0){
					result.put("su", -1);
				}else{
					result.put("su", 1);
				}
			}
			this.success="true";
			return SUCCESS;
		} catch (Exception e) {
			this.success="false";
			logger.error("AreaSetAction的方法 saveAreaSet执行出错，原因：" + e, e);
			return ERROR;
		}
	}



	/**
	 * 删除区域设置
	 * @param areaSet
	 * @return
	 */
	public String deleteAreaSet(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				//查看是否已经被车辆追加
				int count = areaSetService.queryAreaSetCount(Integer.parseInt(id[i]));
				if(count <= 0){
					areaSetService.deleteAreaSet(Integer.parseInt(id[i]));
					result.put("su", 1);
				}else{
					result.put("su", -2);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			result.put("su", -1);
			logger.error("AreaSetAction的方法 deleteAreaSet执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	
	/***************************************************************/
	
	/**
	 * 追加车辆区域
	 * @param areaSet
	 * @return
	 */
	public String addCarAreaSet(){
		try {
			initMap();
			String aids[] = areaids.split(",");
			String cids[] = carids.split(",");
			for (int i = 0; i < aids.length; i++) {	
				zoneAlarm = areaSetService.queryAreaById(Integer.parseInt(aids[i]));
				zoneAlarm.setAreaId(Integer.parseInt(aids[i]));
				zoneAlarm.setAreaid(Integer.parseInt(aids[i]));
				zoneAlarm.setStime(zoneAlarm.getTimeS());
				zoneAlarm.setEtime(zoneAlarm.getTimeE());
				if(zoneAlarm.getTermvalidity() == null){
					zoneAlarm.setTermvalidity(10);
				}
				zoneAlarm.setTimeE(DateUtil.termvalidityFormat(zoneAlarm.getTermvalidity(), DateUtil.dateToNumber(zoneAlarm.getTimeE())));
				zoneAlarm.setTimeS(DateUtil.termvalidityFormat(zoneAlarm.getTermvalidity(), DateUtil.dateToNumber(zoneAlarm.getTimeS())));
				zoneAlarm.setMaxspeed(Double.parseDouble(zoneAlarm.getSpeedLimit()));
//				zoneAlarm.setSpeedtime(Double.parseDouble(zoneAlarm.getSpeedTime()+""));
				zoneAlarm.setYlat(zoneAlarm.getLat());
				zoneAlarm.setYlng(zoneAlarm.getLng());
				zoneAlarm.setAttr(zoneAlarm.getAttr()+"000000");
				if(zoneAlarm.getAreatype() == 1){
					ylist.add(zoneAlarm);
				}else if(zoneAlarm.getAreatype() == 2){
					jlist.add(zoneAlarm);
				}else if(zoneAlarm.getAreatype() == 3){
					dlist.add(zoneAlarm);
				}
			}
			for (int j = 0; j < cids.length; j++) {	//车辆id

				CarInfo carInfo= carService.getCarInfoCarId(Integer.parseInt(cids[j]));
				String terminal = carInfo.getTerminal();
				Integer devicetype = carInfo.getUsertype();
				carnumber = carService.getCarInfoCarId(Integer.parseInt(cids[j])).getCarnumber();
				
				areaCar = new AreaCar();
				areaCar.setBlocid(SessionUtils.getUser().getBlocid());
				areaCar.setUserid(SessionUtils.getUserId());
				areaCar.setCarnumber(carnumber);
				areaCar.setResult(1);
				
				int seq = 0;
				if(ylist.size() > 0){
					seq = HandleUtil.getSerialId();
					ydata=HandleUtil.circularArea(devicetype,terminal, ylist, 1, seq,carnumber);
				}
				for (int k = 0; k < ylist.size(); k++) {		//圆形区域
					areaCar.setSeq(seq);
					areaCar.setAreaid(ylist.get(k).getAreaid());
					//添加车辆区域表
					int count = areaSetService.queryAreaCarIsExist(areaCar);
					if(count <= 0){
						areaSetService.insertAreaCar(areaCar);
					}
					//添加区域操作记录表
					zoneAlarm = ylist.get(k);
					zoneAlarm.setCarnumber(carnumber);
					zoneAlarm.setOpertype(1);
					zoneAlarm.setSeq(seq);
					zoneAlarm.setData(ydata);
					int re = areaSetService.insertAreaCarRecord(zoneAlarm);
				}
				
				if(jlist.size() > 0){
					seq = HandleUtil.getSerialId();
					jdata=HandleUtil.rectangleArea(devicetype,terminal, jlist, 1, seq,carnumber);
				}
				for (int m = 0; m < jlist.size(); m++) {		//矩形区域
					areaCar.setAreaid(jlist.get(m).getAreaid());
					areaCar.setSeq(seq);
					//添加车辆区域表
					int count = areaSetService.queryAreaCarIsExist(areaCar);
					if(count <= 0){
						areaSetService.insertAreaCar(areaCar);
					}
					//添加区域操作记录表
					zoneAlarm = jlist.get(m);
					zoneAlarm.setCarnumber(carnumber);
					zoneAlarm.setOpertype(1);
					zoneAlarm.setSeq(seq);
					zoneAlarm.setData(jdata);
					int re = areaSetService.insertAreaCarRecord(zoneAlarm);
				}
				for (int n = 0; n < dlist.size(); n++) {		//多边形区域

					seq = HandleUtil.getSerialId();
					areaCar.setSeq(seq);
					areaCar.setAreaid(dlist.get(n).getAreaid());
					//添加车辆区域表
					int count = areaSetService.queryAreaCarIsExist(areaCar);
					if(count <= 0){
						areaSetService.insertAreaCar(areaCar);
					}
					ddata=HandleUtil.polygonArea(devicetype,terminal, dlist.get(n), seq,carnumber);
					//添加区域操作记录表
					zoneAlarm = dlist.get(n);
					zoneAlarm.setCarnumber(carnumber);
					zoneAlarm.setOpertype(1);
					zoneAlarm.setSeq(seq);
					zoneAlarm.setData(ddata);
					int re = areaSetService.insertAreaCarRecord(zoneAlarm);
				}

			}
			result.put("su", 1);
			return SUCCESS;
		} catch (Exception e) {
			result.put("su", -1);
			logger.error("AreaSetAction的方法 saveCarAreaSet执行出错，原因：" + e, e);
			return ERROR;
		}
	}


	/**
	 * 删除车辆区域设置
	 * @param areaSet
	 * @return
	 */
	public String deleteAreaCar(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				areaSet = areaSetService.getAreaCarDetail(Integer.parseInt(id[i]));
				areaSetService.deleteAreaCar(Integer.parseInt(id[i]));

				zoneAlarm = new ZoneAlarm();
				zoneAlarm.setAreaId(areaSet.getAreaid());
				zoneAlarm.setAreatype(areaSet.getAreatype());
				zoneAlarm.setCarnumber(areaSet.getCarnumber());

				if(zoneAlarm.getAreatype() == 1){
					ylist.add(zoneAlarm);
				}else if(zoneAlarm.getAreatype() == 2){
					jlist.add(zoneAlarm);
				}else if(zoneAlarm.getAreatype() == 3){
					dlist.add(zoneAlarm);
				}
				zlist.add(zoneAlarm);
			}
			//1 圆形区域 2 矩形区域 3 多边形区域
			for(ZoneAlarm zoneAlarm : zlist){
				String data = "";

				int seq = HandleUtil.getSerialId();

				Integer devicetype = carService.getUserTypeByTerminal(areaSet.getTerminal());
				if(zoneAlarm.getAreatype() == 1){ 
					data = HandleUtil.deleteCircularArea(devicetype,areaSet.getTerminal(), ylist, seq,areaSet.getCarnumber());
				}else if(zoneAlarm.getAreatype() == 2){
					data = HandleUtil.deleteRectangleArea(devicetype,areaSet.getTerminal(), jlist, seq,areaSet.getCarnumber());
				}else if(zoneAlarm.getAreatype() == 3){
					data = HandleUtil.deletePolygonArea(devicetype,areaSet.getTerminal(), dlist, seq,areaSet.getCarnumber());
				}
				//添加区域操作记录表
				zoneAlarm = areaSetService.queryAreaById(zoneAlarm.getAreaId());
				zoneAlarm.setCarnumber(areaSet.getCarnumber());
				zoneAlarm.setOpertype(3);
				zoneAlarm.setStime(zoneAlarm.getTimeS());
				zoneAlarm.setEtime(zoneAlarm.getTimeE());
				zoneAlarm.setMaxspeed(Double.parseDouble(zoneAlarm.getSpeedLimit()));
				zoneAlarm.setSpeedtime(Double.parseDouble(zoneAlarm.getSpeedTime()+""));
				zoneAlarm.setYlat(zoneAlarm.getLat());
				zoneAlarm.setYlng(zoneAlarm.getLng());
				zoneAlarm.setSeq(seq);
				zoneAlarm.setData(data);
				int re = areaSetService.insertAreaCarRecord(zoneAlarm);
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AreaSetAction的方法 deleteAreaCar执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 更新车辆区域设置
	 * @param areaSet
	 * @return
	 */
	public String updateAreaCar(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {	
				zoneAlarm = areaSetService.queryAreaById(Integer.parseInt(id[i]));
				zoneAlarm.setAreaId(Integer.parseInt(id[i]));
				zoneAlarm.setAreaid(Integer.parseInt(id[i]));
				zoneAlarm.setStime(zoneAlarm.getTimeS());
				zoneAlarm.setEtime(zoneAlarm.getTimeE());
				zoneAlarm.setTimeE(DateUtil.dateToNumber(zoneAlarm.getTimeE()));
				zoneAlarm.setTimeS(DateUtil.dateToNumber(zoneAlarm.getTimeS()));
				zoneAlarm.setMaxspeed(Double.parseDouble(zoneAlarm.getSpeedLimit()));
				zoneAlarm.setSpeedtime(Double.parseDouble(zoneAlarm.getSpeedTime()+""));
				zoneAlarm.setYlat(zoneAlarm.getLat());
				zoneAlarm.setYlng(zoneAlarm.getLng());
				zoneAlarm.setAttr(zoneAlarm.getAttr()+"000000");
				if(zoneAlarm.getAreatype() == 1){
					ylist.add(zoneAlarm);
				}else if(zoneAlarm.getAreatype() == 2){
				}else if(zoneAlarm.getAreatype() == 3){
					dlist.add(zoneAlarm);
				}
			}


			areaCar = new AreaCar();
			areaCar.setBlocid(SessionUtils.getUser().getBlocid());
			areaCar.setUserid(SessionUtils.getUserId());

			carnumber = areaSetService.getCarnumberByTer(terminal);
			areaCar.setCarnumber(carnumber);
			areaCar.setResult(1);


			int seq = 0;
			Integer devicetype = carService.getUserTypeByTerminal(areaSet.getTerminal());
			
			if(ylist.size() > 0){
				seq = HandleUtil.getSerialId();
				ydata=HandleUtil.circularArea(devicetype,terminal, ylist, 2, seq,carnumber);
			}
			for (int k = 0; k < ylist.size(); k++) {		//圆形区域
				zoneAlarm = ylist.get(k);
				zoneAlarm.setCarnumber(carnumber);
				zoneAlarm.setOpertype(2);
				zoneAlarm.setSeq(seq);
				zoneAlarm.setData(ydata);
				int re = areaSetService.insertAreaCarRecord(zoneAlarm);
			}
			
			if(jlist.size() > 0){
				seq = HandleUtil.getSerialId();
				jdata=HandleUtil.rectangleArea(devicetype,terminal, jlist, 2, seq,carnumber);
			}
			
			for (int m = 0; m < jlist.size(); m++) {		//矩形区域
				zoneAlarm = jlist.get(m);
				zoneAlarm.setCarnumber(carnumber);
				zoneAlarm.setOpertype(2);
				zoneAlarm.setSeq(seq);
				zoneAlarm.setData(jdata);
				int re = areaSetService.insertAreaCarRecord(zoneAlarm);
			}
			for (int n = 0; n < dlist.size(); n++) {		//多边形区域
				seq = HandleUtil.getSerialId();
				ddata=HandleUtil.polygonArea(devicetype,terminal, dlist.get(n), seq,carnumber);
				zoneAlarm = dlist.get(n);
				zoneAlarm.setCarnumber(carnumber);
				zoneAlarm.setOpertype(2);
				zoneAlarm.setSeq(seq);
				zoneAlarm.setData(ddata);
				int re = areaSetService.insertAreaCarRecord(zoneAlarm);
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AreaSetAction的方法 deleteAreaCar执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 分页查询车辆区域下发记录
	 * @return
	 */
	public String queryAreaSendRecordList() {

		try {
			initMap();
			if(areaSet==null){
				areaSet=new AreaSet();
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				areaSet.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase().trim());
			}
			if(areaname!=null&&!areaname.equals("")&&!areaname.equals("null")){
				areaSet.setAreaname(URLDecoder.decode(areaname,"UTF-8").trim());
			}
			if(opertype!=null){
				areaSet.setOpertype(opertype);
			}
			if(stime!=null){
				areaSet.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(etime!=null){
				areaSet.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				areaSet.setUserid(SessionUtils.getUserId());
			}
			result=areaSetService.queryAreaSendRecordList(this.getPage(),this.getLimit(), areaSet);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AreaSetAction的方法 queryAreaSendRecordList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 分页查询车辆区域设置
	 * @return
	 */
	public String queryAreaCarList() {

		try {
			initMap();
			if(areaCar==null){
				areaCar=new AreaCar();
			}

			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				areaCar.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				areaCar.setUserid(SessionUtils.getUserId());
			}
			result=areaSetService.queryPageAreaCarList(this.getPage(),this.getLimit(), areaCar);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AreaSetAction的方法 queryAreaCarList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 查看区域详情
	 * @return
	 */
	public String getAreaSetDetail(){
		try {
			initMap();
			if(id == null){
				return ERROR;
			}
			areaSet = areaSetService.getAreaSetDetail(Integer.parseInt(id));
			result.put("areaSet", areaSet);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AreaSetAction的方法 getAreaSetDetail执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	
	
	
	public AreaSetService getAreaSetService() {
		return areaSetService;
	}

	public void setAreaSetService(AreaSetService areaSetService) {
		this.areaSetService = areaSetService;
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public AreaSet getAreaSet() {
		return areaSet;
	}

	public void setAreaSet(AreaSet areaSet) {
		this.areaSet = areaSet;
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

	public String getLatsrt() {
		return latsrt;
	}

	public void setLatsrt(String latsrt) {
		this.latsrt = latsrt;
	}

	public String getLngsrt() {
		return lngsrt;
	}

	public void setLngsrt(String lngsrt) {
		this.lngsrt = lngsrt;
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


}
