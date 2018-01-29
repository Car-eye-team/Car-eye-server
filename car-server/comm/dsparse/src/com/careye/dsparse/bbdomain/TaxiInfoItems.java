/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：TaxiInfoItems    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2016-10-13 下午03:02:48    
 * 修改人：Administrator    
 * 修改时间：2016-10-13 下午03:02:48    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TaxiInfoItems {
	
	/**文件名*/
	private String filename;
	
	/**播放开始时间*/
	private Integer splaysec;
	
	/**播放结束时间*/
	private Integer eplaysec;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getSplaysec() {
		return splaysec;
	}

	public void setSplaysec(Integer splaysec) {
		this.splaysec = splaysec;
	}

	public Integer getEplaysec() {
		return eplaysec;
	}

	public void setEplaysec(Integer eplaysec) {
		this.eplaysec = eplaysec;
	}
	
	
}
