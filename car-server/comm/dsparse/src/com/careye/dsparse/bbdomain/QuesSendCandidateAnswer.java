/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：QuesSendCandidateAnswer    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-4 下午07:07:18    
 * 修改人：zr    
 * 修改时间：2015-6-4 下午07:07:18    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class QuesSendCandidateAnswer {
	
	/** 答案 ID  **/
	private  int  id;
	
	/** 答案内容长度   **/
	private  int  len;
	
	/** 答案内容   **/
	private  String  content;

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
