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
 * 类名称：SecretKey    
 * 类描述： 密钥实体 
 * 创建人：zr    
 * 创建时间：2015-10-15 下午03:55:29    
 * 修改人：zr    
 * 修改时间：2015-10-15 下午03:55:29    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class SecretKey extends BaseInfo{
	
	/**密钥类型*/
	private int keytype;
	
	/**装载密文*/
	private String keydata;
	
	/**密钥版本*/
	private String version;
	
	/**同步号*/
	private int sno;
	
	/**是否更新结束*/
	private int state;
	
	/**文件标识符*/
	private int uuid;
	
	/**随机数*/
	private int random;
	
	/**更新结果*/
	private int result;

	public int getKeytype() {
		return keytype;
	}

	public void setKeytype(int keytype) {
		this.keytype = keytype;
	}

	public String getKeydata() {
		return keydata;
	}

	public void setKeydata(String keydata) {
		this.keydata = keydata;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}

	public int getRandom() {
		return random;
	}

	public void setRandom(int random) {
		this.random = random;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
}
