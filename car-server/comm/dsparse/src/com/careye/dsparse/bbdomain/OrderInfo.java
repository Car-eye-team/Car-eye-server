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
 * 类名称：OrderInfo    
 * 类描述：    
 * 创建人：huangqin    
 * 创建时间：2015-7-7 下午16:10:41    
 * 修改人：huangqin    
 * 修改时间：2015-7-7 下午16:10:41  
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class OrderInfo extends BaseInfo{

	/**类型**/
	private int flag;
	/**评价状态*/
	private int evastatus;
	/**订单状态*/
	private int orderstatus;
	/**投诉状态*/
	private int comstatus;
	/**会员类型*/
	private int membertype;
	/**积分 **/
	private int integral;
	/**诚信度 **/
	private int integrity;
	/**会员ID **/
	private int memberid;
	/**货源ID **/
	private int sgid;
	/**订单ID **/
	private String orderid;
	/**下单者 **/
	private String orderswere;
	/**下订单时间**/
	private String ordertime;
	/**订单有效时间 **/
	private String effectivetime;
	/**订单确认时间 **/
	private String confirmtime;
	/**取货时间 **/
	private String pickuptime;
	/**已运到时间 **/
	private String transportedtime;
	/**完成时间 **/
	private String completiontime;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getEvastatus() {
		return evastatus;
	}
	public void setEvastatus(int evastatus) {
		this.evastatus = evastatus;
	}
	public int getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}
	public int getComstatus() {
		return comstatus;
	}
	public void setComstatus(int comstatus) {
		this.comstatus = comstatus;
	}
	public int getMembertype() {
		return membertype;
	}
	public void setMembertype(int membertype) {
		this.membertype = membertype;
	}
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
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public int getSgid() {
		return sgid;
	}
	public void setSgid(int sgid) {
		this.sgid = sgid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrderswere() {
		return orderswere;
	}
	public void setOrderswere(String orderswere) {
		this.orderswere = orderswere;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getEffectivetime() {
		return effectivetime;
	}
	public void setEffectivetime(String effectivetime) {
		this.effectivetime = effectivetime;
	}
	public String getConfirmtime() {
		return confirmtime;
	}
	public void setConfirmtime(String confirmtime) {
		this.confirmtime = confirmtime;
	}
	public String getPickuptime() {
		return pickuptime;
	}
	public void setPickuptime(String pickuptime) {
		this.pickuptime = pickuptime;
	}
	public String getTransportedtime() {
		return transportedtime;
	}
	public void setTransportedtime(String transportedtime) {
		this.transportedtime = transportedtime;
	}
	public String getCompletiontime() {
		return completiontime;
	}
	public void setCompletiontime(String completiontime) {
		this.completiontime = completiontime;
	}
	
}
