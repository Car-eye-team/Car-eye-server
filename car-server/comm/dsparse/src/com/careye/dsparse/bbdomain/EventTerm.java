/**    
 * Description: 晟鸿科技通讯平台    
 * 文件名：EventTerm.java   
 * 版本信息：    
 * 日期：2015-6-4  
 * Copyright car-eye 车辆管理平台 Copyright (c) 2015     
 * 版权所有    
 *    
 */
package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：EventTerm    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-4 下午05:56:35    
 * 修改人：zr    
 * 修改时间：2015-6-4 下午05:56:35    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class EventTerm {

	/**  事件 ID    **/
	private  int id;
	
	/**  事件内容长度     **/
	private  int  len;
	
	/**  事件内容    **/
	private  String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
