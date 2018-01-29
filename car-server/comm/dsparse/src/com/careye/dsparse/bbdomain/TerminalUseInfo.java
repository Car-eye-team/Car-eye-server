/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：TerminalInfo    
 * 类描述：终端属性信息    
 * 创建人：zr    
 * 创建时间：2015-5-15 下午02:55:39    
 * 修改人：zr    
 * 修改时间：2015-5-15 下午02:55:39    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TerminalUseInfo {

	/**功能使用次数*/
	private String number;
	
	/**统计时间*/
	private String time;
	
	/**功能类型*/
	private int type;
	
	/**使用时长*/
	private int usinglen;
	
	/**使用流量*/
	private int flow;
	
	private int num;
	

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUsinglen() {
		return usinglen;
	}

	public void setUsinglen(int usinglen) {
		this.usinglen = usinglen;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}
	
	
}
