/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：LineInfoItems    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-5 下午02:41:37    
 * 修改人：zr    
 * 修改时间：2015-6-5 下午02:41:37    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class LineInfoItems {
	
	private int id;
	
	/**拐点ID*/
	private int iid;
	
	/**路段ID*/
	private int lid;
	
	/**拐点纬度*/
	private int lat;
	
	/**拐点经度*/
	private int lng;
	
	/**路段宽度*/
	private int width;
	
	/**路段行驶时长阀值*/
	private int rtime;
	
	/**路段行驶不足阀值*/
	private int stime;
	
	/**路段最高速度*/
	private int tspeed;
	
	/**路段超速持续时问*/
	private int pspeed;
	
	/**1：行驶时间*/
	private int lattr0;
	
	/**1：限速*/
	private int lattr1;
	
	/**0：北纬；1：南纬*/
	private int lattr2;
	
	/**0：东经；1：西经*/
	private int lattr3;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLng() {
		return lng;
	}

	public void setLng(int lng) {
		this.lng = lng;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getRtime() {
		return rtime;
	}

	public void setRtime(int rtime) {
		this.rtime = rtime;
	}

	public int getStime() {
		return stime;
	}

	public void setStime(int stime) {
		this.stime = stime;
	}

	public int getTspeed() {
		return tspeed;
	}

	public void setTspeed(int tspeed) {
		this.tspeed = tspeed;
	}

	public int getPspeed() {
		return pspeed;
	}

	public void setPspeed(int pspeed) {
		this.pspeed = pspeed;
	}

	public int getLattr0() {
		return lattr0;
	}

	public void setLattr0(int lattr0) {
		this.lattr0 = lattr0;
	}

	public int getLattr1() {
		return lattr1;
	}

	public void setLattr1(int lattr1) {
		this.lattr1 = lattr1;
	}

	public int getLattr2() {
		return lattr2;
	}

	public void setLattr2(int lattr2) {
		this.lattr2 = lattr2;
	}

	public int getLattr3() {
		return lattr3;
	}

	public void setLattr3(int lattr3) {
		this.lattr3 = lattr3;
	}
	
	
}
