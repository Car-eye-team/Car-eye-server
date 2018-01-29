/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;


/**    
 *     
 * 项目名称：dsparse    
 * 类名称：GzInfoItems    
 * 类描述：广州C-OBD 终端通信协议实体    
 * 创建人：Administrator    
 * 创建时间：2016-6-30 下午02:08:05    
 * 修改人：Administrator    
 * 修改时间：2016-6-30 下午02:08:05    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class GzInfoItems {

	/**类型*/
	private int type;

	/**故障码状态*/
	private int state;

	/**故障码*/
	private String code;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}
