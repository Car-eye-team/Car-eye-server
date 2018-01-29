/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：RectangleAreaItems    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-5 下午01:46:01    
 * 修改人：zr    
 * 修改时间：2015-6-5 下午01:46:01    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class RectangleAreaItems {
	
	/**区域ID*/
	private int areaId;
	
	/**左上点纬度*/
	private int latlt;
	
	/**左上点经度*/
	private int lnglt;
	
	/**右下点纬度*/
	private int latrb;
	
	/**右下点经度*/
	private int lngrb;
	
	/**起始时间*/
	private String timeS;
	
	/**结束时间*/
	private String timeE;
	
	/**最高速度*/
	private int speedLimit;
	
	/**超速持续时间*/
	private int speedTime;
	
	/**根据时间*/
	private int attr0;
	
	/**限速*/
	private int attr1;
	
	/**进区域报警给驾驶员*/
	private int attr2;
	
	/**进区域报警给平台*/
	private int attr3;
	
	/**出区域报警给驾驶员*/
	private int attr4;
	
	/**出区域报警给平台*/
	private int attr5;
	
	/**0：北纬；1：南纬*/
	private int attr6;
	
	/**0：东经；1：西经*/
	private int attr7;

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getLatlt() {
		return latlt;
	}

	public void setLatlt(int latlt) {
		this.latlt = latlt;
	}

	public int getLnglt() {
		return lnglt;
	}

	public void setLnglt(int lnglt) {
		this.lnglt = lnglt;
	}

	public int getLatrb() {
		return latrb;
	}

	public void setLatrb(int latrb) {
		this.latrb = latrb;
	}

	public int getLngrb() {
		return lngrb;
	}

	public void setLngrb(int lngrb) {
		this.lngrb = lngrb;
	}

	public String getTimeS() {
		return timeS;
	}

	public void setTimeS(String timeS) {
		this.timeS = timeS;
	}

	public String getTimeE() {
		return timeE;
	}

	public void setTimeE(String timeE) {
		this.timeE = timeE;
	}

	public int getSpeedLimit() {
		return speedLimit;
	}

	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
	}

	public int getSpeedTime() {
		return speedTime;
	}

	public void setSpeedTime(int speedTime) {
		this.speedTime = speedTime;
	}

	public int getAttr0() {
		return attr0;
	}

	public void setAttr0(int attr0) {
		this.attr0 = attr0;
	}

	public int getAttr1() {
		return attr1;
	}

	public void setAttr1(int attr1) {
		this.attr1 = attr1;
	}

	public int getAttr2() {
		return attr2;
	}

	public void setAttr2(int attr2) {
		this.attr2 = attr2;
	}

	public int getAttr3() {
		return attr3;
	}

	public void setAttr3(int attr3) {
		this.attr3 = attr3;
	}

	public int getAttr4() {
		return attr4;
	}

	public void setAttr4(int attr4) {
		this.attr4 = attr4;
	}

	public int getAttr5() {
		return attr5;
	}

	public void setAttr5(int attr5) {
		this.attr5 = attr5;
	}

	public int getAttr6() {
		return attr6;
	}

	public void setAttr6(int attr6) {
		this.attr6 = attr6;
	}

	public int getAttr7() {
		return attr7;
	}

	public void setAttr7(int attr7) {
		this.attr7 = attr7;
	}
	
}
