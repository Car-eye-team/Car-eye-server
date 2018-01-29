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
 * 类名称：PaymentNotice    
 * 类描述：二维码扫描支付通知    
 * 创建人：zr    
 * 创建时间：2015-5-15 上午11:07:05    
 * 修改人：zr    
 * 修改时间：2015-5-15 上午11:07:05    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class PaymentNotice extends BaseInfo{
	
	/**支付结果 1 成功 2 失败*/
	private int result;
	
	/**订单号*/
	private String orderid;
	
	/**支付类型*/
	private int type;
	
	/**金额*/
	private String money;
	

	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
}
