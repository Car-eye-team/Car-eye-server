/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：DriverInfo    
 * 类描述：驾驶员信息    
 * 创建人：zr    
 * 创建时间：2015-6-5 下午03:41:56    
 * 修改人：zr    
 * 修改时间：2015-6-5 下午03:41:56    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DriverInfo {
	
	/**状态*/
	private int state;
	
	/**时间*/
	private String time;
	
	/**IC卡读取结果*/
	private int result;
	
	/**驾驶员姓名长度*/
	private int namelen;
	
	/**驾驶员姓名*/
	private String name;
	
	/**从业资格证编码*/
	private String qc;
	
	/**发证机构名称长度*/
	private int orglen;
	
	/**发证机构名称*/
	private String org;
	
	/**证件有效期*/
	private String effectivetime;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getNamelen() {
		return namelen;
	}

	public void setNamelen(int namelen) {
		this.namelen = namelen;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQc() {
		return qc;
	}

	public void setQc(String qc) {
		this.qc = qc;
	}

	public int getOrglen() {
		return orglen;
	}

	public void setOrglen(int orglen) {
		this.orglen = orglen;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getEffectivetime() {
		return effectivetime;
	}

	public void setEffectivetime(String effectivetime) {
		this.effectivetime = effectivetime;
	}
	
	
	
}
