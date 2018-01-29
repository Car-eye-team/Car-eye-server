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
 * 类名称：RegisterInfo    
 * 类描述：注册信息    
 * 创建人：zr    
 * 创建时间：2015-5-14 下午02:49:47    
 * 修改人：zr    
 * 修改时间：2015-5-14 下午02:49:47    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class RegisterInfo extends BaseInfo{
	
	/**省域ID*/
	private int province;
	
	/**市县域ID*/
	private int city;
	
	/**制造商ID*/
	private String oem;
	
	/**终端型号*/
	private String type;
	
	/**终端ID*/
	private String mid;
	
	/**车牌颜色*/
	private int platecolor;
	
	/**车辆标识*/
	private String platecode;

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public String getOem() {
		return oem;
	}

	public void setOem(String oem) {
		this.oem = oem;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getPlatecolor() {
		return platecolor;
	}

	public void setPlatecolor(int platecolor) {
		this.platecolor = platecolor;
	}

	public String getPlatecode() {
		return platecode;
	}

	public void setPlatecode(String platecode) {
		this.platecode = platecode;
	}
	
	
}
