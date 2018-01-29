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
 * 类名称：TerminalControl    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-4 下午02:44:39    
 * 修改人：zr    
 * 修改时间：2015-6-4 下午02:44:39    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TerminalControl extends BaseInfo{

	/** 命令字   **/
	private  int  id;
	
	/** 命令参数   **/
	private  String  size;
	
	/**终端控制包长度*/
	private int len;
	
	private String valuehex;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public String getValuehex() {
		return valuehex;
	}
	public void setValuehex(String valuehex) {
		this.valuehex = valuehex;
	}
	
	

}
