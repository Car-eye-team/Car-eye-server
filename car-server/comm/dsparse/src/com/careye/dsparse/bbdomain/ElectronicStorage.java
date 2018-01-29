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
 * 类名称：ElectronicStorage    
 * 类描述：电子服务证实体    
 * 创建人：zr    
 * 创建时间：2015-10-15 下午01:51:56    
 * 修改人：zr    
 * 修改时间：2015-10-15 下午01:51:56    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ElectronicStorage extends BaseInfo{
	
	/**司机代码*/
	private String drivercode;
	
	/**驾驶员唯一编号*/
	private String driverid;
	
	/**电子服务证版本*/
	private String version;
	
	/**司机姓名*/
	private String drivername;
	
	/**服务公司*/
	private String servicecompany;
	
	/**星级*/
	private int starlevel;
	
	/**星级显示文本*/
	private String startext;
	
	/**照片格式编码*/
	private int format;
	
	/**窗口大小*/
	private int windowsize;
	
	/**照片数据*/
	private String picturedata;

	public String getDrivercode() {
		return drivercode;
	}

	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getServicecompany() {
		return servicecompany;
	}

	public void setServicecompany(String servicecompany) {
		this.servicecompany = servicecompany;
	}

	public int getStarlevel() {
		return starlevel;
	}

	public void setStarlevel(int starlevel) {
		this.starlevel = starlevel;
	}

	public String getStartext() {
		return startext;
	}

	public void setStartext(String startext) {
		this.startext = startext;
	}

	public int getFormat() {
		return format;
	}

	public void setFormat(int format) {
		this.format = format;
	}

	public int getWindowsize() {
		return windowsize;
	}

	public void setWindowsize(int windowsize) {
		this.windowsize = windowsize;
	}

	public String getPicturedata() {
		return picturedata;
	}

	public void setPicturedata(String picturedata) {
		this.picturedata = picturedata;
	}
	
}
