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
 * @类名称：OptionsAvailableUpTimeToReturn
 * @类描述：车源更新发布时间返回
 * @创建人：huangqin
 * @创建时间：2015-7-7 下午06:25:17
 * @修改人：huangqin
 * @修改时间：2015-7-7 下午06:25:17
 * @修改备注：
 * @version 1.0
 */
public class OptionsAvailableUpTimeToReturn extends BaseInfo{

	/**车源ID **/
	private int carsourceid;
	
	/**发布时间 **/
	private String releasetime;

	public int getCarsourceid() {
		return carsourceid;
	}

	public void setCarsourceid(int carsourceid) {
		this.carsourceid = carsourceid;
	}

	public String getReleasetime() {
		return releasetime;
	}

	public void setReleasetime(String releasetime) {
		this.releasetime = releasetime;
	}
	
}
