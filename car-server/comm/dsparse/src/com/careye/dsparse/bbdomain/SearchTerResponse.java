/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：SearchTerResponse    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-4 下午03:59:44    
 * 修改人：zr    
 * 修改时间：2015-6-4 下午03:59:44    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class SearchTerResponse {
	
	/**  终端类型     **/
	private  String   type;
	
	/**  制造商 ID     **/
	private  String  markerid;
	
	/**  终端型号   **/
	private  String  termainaltype;
	
	/**  终端 ID     **/
	private  String  termainalid;
	
	/**  终端 SIM 卡 ICCID    **/
	private  String  iccid;
	
	/**  终端硬件版本号长度      **/
	private  int  hardwarelen;
	
	/**  终端硬件版本号      **/
	private  String  hardwareno;
	
	/**  终端固件版本号长度      **/
    private  int  firmwarelen;
    
    /**  终端固件版本号     **/
    private  String  firmwareno;
    
    /**  GNSS 模块属性     **/
    private  String  cnssproperty;
    
    /**  通信模块属性     **/
    private  String  comproperty;


	public String getMarkerid() {
		return markerid;
	}

	public void setMarkerid(String markerid) {
		this.markerid = markerid;
	}

	public String getTermainaltype() {
		return termainaltype;
	}

	public void setTermainaltype(String termainaltype) {
		this.termainaltype = termainaltype;
	}

	public String getTermainalid() {
		return termainalid;
	}

	public void setTermainalid(String termainalid) {
		this.termainalid = termainalid;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public int getHardwarelen() {
		return hardwarelen;
	}

	public void setHardwarelen(int hardwarelen) {
		this.hardwarelen = hardwarelen;
	}

	public String getHardwareno() {
		return hardwareno;
	}

	public void setHardwareno(String hardwareno) {
		this.hardwareno = hardwareno;
	}

	public int getFirmwarelen() {
		return firmwarelen;
	}

	public void setFirmwarelen(int firmwarelen) {
		this.firmwarelen = firmwarelen;
	}

	public String getFirmwareno() {
		return firmwareno;
	}

	public void setFirmwareno(String firmwareno) {
		this.firmwareno = firmwareno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCnssproperty() {
		return cnssproperty;
	}

	public void setCnssproperty(String cnssproperty) {
		this.cnssproperty = cnssproperty;
	}

	public String getComproperty() {
		return comproperty;
	}

	public void setComproperty(String comproperty) {
		this.comproperty = comproperty;
	}

	
}
