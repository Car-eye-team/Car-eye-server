/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import com.careye.dsparse.constant.BaseInfo;

/**
 * 
 * @项目名称：dsparse
 * @类名称：IntegralIntegrity
 * @类描述：积分诚信度
 * @创建人：huangqin
 * @创建时间：2015-7-7 下午06:14:55
 * @修改人：huangqin
 * @修改时间：2015-7-7 下午06:14:55
 * @修改备注：
 * @version 1.0
 */
public class IntegralIntegrity extends BaseInfo{

	/**积分值 **/
	private  int integral;
	/**诚信度 **/
	private  int integrity;
	/**当前诚信度经验值 **/
	private  int currentexperience;
	/**下次升级诚信度经验值 **/
	private  int experiencenext;
	
	
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public int getIntegrity() {
		return integrity;
	}
	public void setIntegrity(int integrity) {
		this.integrity = integrity;
	}
	public int getCurrentexperience() {
		return currentexperience;
	}
	public void setCurrentexperience(int currentexperience) {
		this.currentexperience = currentexperience;
	}
	public int getExperiencenext() {
		return experiencenext;
	}
	public void setExperiencenext(int experiencenext) {
		this.experiencenext = experiencenext;
	}
	
}
