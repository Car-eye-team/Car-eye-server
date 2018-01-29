/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：PhoneBookItems    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-5 上午10:41:56    
 * 修改人：zr    
 * 修改时间：2015-6-5 上午10:41:56    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class PhoneBookItems {

	/**标志 1：呼入；2：呼出；3：呼入/呼出*/
	private int tag;
	
	/**号码长度*/
	private int telLen;
	
	/**电话号码*/
	private String tel;
	
	/**联系人长度*/
	private int ctcLen;

	/**联系人*/
	private String contact;

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getTelLen() {
		return telLen;
	}

	public void setTelLen(int telLen) {
		this.telLen = telLen;
	}


	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getCtcLen() {
		return ctcLen;
	}

	public void setCtcLen(int ctcLen) {
		this.ctcLen = ctcLen;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
	
	
}
