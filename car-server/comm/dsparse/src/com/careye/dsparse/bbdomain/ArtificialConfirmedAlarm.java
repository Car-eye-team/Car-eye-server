/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */



package com.careye.dsparse.bbdomain;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：ArtificialConfirmedAlarm    
 * 类描述：人工确认报警消息    
 * 创建人：zr    
 * 创建时间：2015-6-4 下午05:12:40    
 * 修改人：zr    
 * 修改时间：2015-6-4 下午05:12:40    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ArtificialConfirmedAlarm extends BaseInfo{
	
	/** 报警消息流水号    **/
	private  int  seqR;
	
	/** 人工确认报警类型  **/
	private  int  type;
	
	/** 1：确认紧急报警； **/
	private int  urgency;
	
	/** 1：确认危险预警； **/
	private int  warn;
	
	/**  1：确认进出区域报警； **/
	private int  area;
	
	/** 1：确认进出路线报警；**/
	private int  line;
	
	/**  1：确认路段行驶时间不足/过长报警； **/
	private int  driving;
	
	/**  1：确认车辆非法点火报警； **/
	private int  ignite;
	
	/**  1：确认车辆非法位移报警； **/
	private int  displacement;

	public int getSeqR() {
		return seqR;
	}

	public void setSeqR(int seqR) {
		this.seqR = seqR;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUrgency() {
		return urgency;
	}

	public void setUrgency(int urgency) {
		this.urgency = urgency;
	}

	public int getWarn() {
		return warn;
	}

	public void setWarn(int warn) {
		this.warn = warn;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getDriving() {
		return driving;
	}

	public void setDriving(int driving) {
		this.driving = driving;
	}

	public int getIgnite() {
		return ignite;
	}

	public void setIgnite(int ignite) {
		this.ignite = ignite;
	}

	public int getDisplacement() {
		return displacement;
	}

	public void setDisplacement(int displacement) {
		this.displacement = displacement;
	}

	
}
