/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：CanTotalBusDataUploadItems    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-5 下午04:33:19    
 * 修改人：zr    
 * 修改时间：2015-6-5 下午04:33:19    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class CanTotalBusDataUploadItems {
	
	/**CAN 总线I**/
	private int  canid;
	
	/**CAN 通道号*/
	private int channel;
	
	/**帧类型*/
	private int type;
	
	/**数据采集方式*/
	private int way;
	
	/** CAN DATA   **/
	private String  candata;

	public int getCanid() {
		return canid;
	}

	public void setCanid(int canid) {
		this.canid = canid;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getWay() {
		return way;
	}

	public void setWay(int way) {
		this.way = way;
	}

	public String getCandata() {
		return candata;
	}

	public void setCandata(String candata) {
		this.candata = candata;
	}

	
	
}
