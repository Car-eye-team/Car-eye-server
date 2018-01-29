
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
 * @类名称：OptionsAvailableStaUpToReturn
 * @类描述：车源状态更新返回
 * @创建人：huangqin
 * @创建时间：2015-7-7 下午06:24:12
 * @修改人：huangqin
 * @修改时间：2015-7-7 下午06:24:12
 * @修改备注：
 * @version 1.0
 */
public class OptionsAvailableStaUpToReturn extends BaseInfo{
   
	/** 车源ID**/
	private int carsourceid;
	/** 生效状态**/
	private int effect;
	
	public int getCarsourceid() {
		return carsourceid;
	}
	public void setCarsourceid(int carsourceid) {
		this.carsourceid = carsourceid;
	}
	public int getEffect() {
		return effect;
	}
	public void setEffect(int effect) {
		this.effect = effect;
	}
	
}
