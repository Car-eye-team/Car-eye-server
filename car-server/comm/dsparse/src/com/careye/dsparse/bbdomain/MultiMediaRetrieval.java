/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：MultiMediaRetrieval    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-6 下午02:40:02    
 * 修改人：zr    
 * 修改时间：2015-6-6 下午02:40:02    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class MultiMediaRetrieval {

	/** 多媒体 ID **/
	private  int mediaid;
	
	/** 多媒体类型 **/
	private  int type;
	
	/**  通道 ID **/
	private  int id;
	
	/**  事件项编码 **/
	private  int fmt;
	
	/** 位置信息汇报(0x0200)消息体  **/
	private PositionInfo  positionInfo;

	public int getMediaid() {
		return mediaid;
	}

	public void setMediaid(int mediaid) {
		this.mediaid = mediaid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFmt() {
		return fmt;
	}

	public void setFmt(int fmt) {
		this.fmt = fmt;
	}

	public PositionInfo getPositionInfo() {
		return positionInfo;
	}

	public void setPositionInfo(PositionInfo positionInfo) {
		this.positionInfo = positionInfo;
	}
	
	
}
