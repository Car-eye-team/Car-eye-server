
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
 * @类名称：NoticeAcceptGoods
 * @类描述：通知接受货物
 * @创建人：huangqin
 * @创建时间：2015-7-7 下午05:59:16
 * @修改人：huangqin
 * @修改时间：2015-7-7 下午05:59:16
 * @修改备注：
 * @version 1.0
 */
public class NoticeAcceptGoods extends BaseInfo{
	
	/**通知次数 **/
	private int times;
	/**订单ID**/
	private String orderid;
	/**接受货物最晚时间**/
	private String latesttime;
	/**备注 **/
	private String remark;
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getLatesttime() {
		return latesttime;
	}
	public void setLatesttime(String latesttime) {
		this.latesttime = latesttime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
