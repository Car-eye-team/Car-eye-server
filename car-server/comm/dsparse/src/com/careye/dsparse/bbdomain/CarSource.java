/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**
 * @项目名称：dsparse
 * @类名称：CarSource
 * @类描述：车源
 * @创建人：zhangrong
 * @创建时间：2015-7-8 上午09:33:52
 * @修改人：zhangrong
 * @修改时间：2015-7-8 上午09:33:52
 * @修改备注：
 * @version 1.0
 */
public class CarSource {
	
	/**类型 1  本地车 2 回程车*/
	private int type;
	/**用途 1 普通运输 2 大件运输3  其他*/
	private int use;
	/**是否加急 1 普通车源2 加急车源*/
	private int isexpedited;
	/**运输路线 1非固定线路2 固定线路3 专线*/
	private int transportline;
	/**出发省份*/
	private String sprovince;
	/**出发城市*/
	private String scity;
	/**出发区*/
	private String sdistrict;
	/**目的地省份*/
	private String eprovince;
	/**目的地城市*/
	private String ecity;
	/**目的地区*/
	private String edistrict;
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getUse() {
		return use;
	}
	public void setUse(int use) {
		this.use = use;
	}
	public int getIsexpedited() {
		return isexpedited;
	}
	public void setIsexpedited(int isexpedited) {
		this.isexpedited = isexpedited;
	}
	public int getTransportline() {
		return transportline;
	}
	public void setTransportline(int transportline) {
		this.transportline = transportline;
	}
	public String getSprovince() {
		return sprovince;
	}
	public void setSprovince(String sprovince) {
		this.sprovince = sprovince;
	}
	public String getScity() {
		return scity;
	}
	public void setScity(String scity) {
		this.scity = scity;
	}
	public String getSdistrict() {
		return sdistrict;
	}
	public void setSdistrict(String sdistrict) {
		this.sdistrict = sdistrict;
	}
	public String getEprovince() {
		return eprovince;
	}
	public void setEprovince(String eprovince) {
		this.eprovince = eprovince;
	}
	public String getEcity() {
		return ecity;
	}
	public void setEcity(String ecity) {
		this.ecity = ecity;
	}
	public String getEdistrict() {
		return edistrict;
	}
	public void setEdistrict(String edistrict) {
		this.edistrict = edistrict;
	}
	
	

}
