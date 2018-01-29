/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：LdSycInfoItems    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-7-24 上午09:26:09    
 * 修改人：zr    
 * 修改时间：2015-7-24 上午09:26:09    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class LdSycInfoItems {
	
	/**数据项*/
	private int id;
	
	/**数据项值*/
	private String value;
	
	/**数据流名称*/
	private String name;
	
	/**数据流单位*/
	private String unit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
