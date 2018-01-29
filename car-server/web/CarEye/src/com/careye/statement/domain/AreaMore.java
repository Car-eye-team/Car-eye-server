package com.careye.statement.domain;

import com.careye.base.action.BaseDomain;

/**
 * 
 * @项目名称：QHFMS
 * @类名称：
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-28 下午07:16:08
 * @修改人：huangqin
 * @修改时间：2015-3-28 下午07:16:08
 * @修改备注：
 * @version 1.0
 */
public class AreaMore extends BaseDomain{
    private Integer id;
	
	/**区域类型 1 圆形区域 2 矩形区域  **/
	private Integer areatype;
	
	/** 区域名称 **/
	private String areaname;
	
	
	/** 中心点纬度 **/
	private String ylat;
	/** 中心点经度 **/
	private String ylng;
	/** 半径 **/
	private String radius;
	/** 左上点纬度 **/
	private String latlt;
	/** 左上点经度 **/
	private String lnglt;
	/** 右下点纬度 **/
	private String latrb;
	/** 右下点经度 **/
	private String lngrb;
	
	/** 车牌号 **/
	private String carnumber;
	/** 终端号 **/
	private String terminal;
	/** 数据包 **/
	private String data;
	/** 操作类型	1追加2更新 3 删除 **/
	private Integer opertype;
	/** 流水号**/
	private Integer seq;
	/** 处理结果	1成功 2失败 **/
	private Integer result;
	/** 区域ID **/
	private Integer areaid;

	/** 创建时间 **/
	private String createtime;
	
	private  Integer  blocid;
	private String  blocname;
	private Integer  cartype;
	private String  color;
	private Integer  caruse;
	private String lng;
	private String lat;
	private String mileage;
	
	public Integer getCartype() {
		return cartype;
	}
	public void setCartype(Integer cartype) {
		this.cartype = cartype;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getCaruse() {
		return caruse;
	}
	public void setCaruse(Integer caruse) {
		this.caruse = caruse;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAreatype() {
		return areatype;
	}
	public void setAreatype(Integer areatype) {
		this.areatype = areatype;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getYlat() {
		return ylat;
	}
	public void setYlat(String ylat) {
		this.ylat = ylat;
	}
	public String getYlng() {
		return ylng;
	}
	public void setYlng(String ylng) {
		this.ylng = ylng;
	}
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getOpertype() {
		return opertype;
	}
	public void setOpertype(Integer opertype) {
		this.opertype = opertype;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Integer getBlocid() {
		return blocid;
	}
	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}
	public String getBlocname() {
		return blocname;
	}
	public void setBlocname(String blocname) {
		this.blocname = blocname;
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
	
}
