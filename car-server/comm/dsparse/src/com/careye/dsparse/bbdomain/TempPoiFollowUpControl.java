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
 * 类名称：TempPoiFollowUpControl    
 * 类描述：历史位置控制    
 * 创建人：zr    
 * 创建时间：2015-6-4 下午05:04:17    
 * 修改人：zr    
 * 修改时间：2015-6-4 下午05:04:17    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TempPoiFollowUpControl extends BaseInfo{

	/** 时间间隔 **/
	private  int  inv;
	
	/** 位置跟踪有效时间   **/
	private int expire;
	
	/**距离间隔*/
	private int distance;
	
	/**位置跟踪有效距离*/
	private int effdistance;
	
	/**跟踪方式*/
	private int way;
	
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getEffdistance() {
		return effdistance;
	}

	public void setEffdistance(int effdistance) {
		this.effdistance = effdistance;
	}

	public int getWay() {
		return way;
	}

	public void setWay(int way) {
		this.way = way;
	}

	public int getInv() {
		return inv;
	}

	public void setInv(int inv) {
		this.inv = inv;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

}
