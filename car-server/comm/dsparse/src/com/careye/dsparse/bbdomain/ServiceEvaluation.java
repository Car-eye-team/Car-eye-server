/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：ServiceEvaluation    
 * 类描述：服务评价    
 * 创建人：zr    
 * 创建时间：2015-5-15 下午02:24:15    
 * 修改人：zr    
 * 修改时间：2015-5-15 下午02:24:15    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ServiceEvaluation {
	
	/**评价结果 1 非常满意 2 满意 3一般 4不满意*/
	private int result;
	
	/**不满意原因	1．绕路  2.有异味  3.服务态度不好如果选择非常满意、满意、一般时 该值填0*/
	private int reason;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}
	
}
