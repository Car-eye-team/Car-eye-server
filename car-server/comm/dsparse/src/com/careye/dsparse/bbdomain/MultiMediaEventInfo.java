/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：MultiMediaEventInfo    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-6 下午01:28:57    
 * 修改人：zr    
 * 修改时间：2015-6-6 下午01:28:57    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class MultiMediaEventInfo {
	
	/**多媒体数据ID*/
	private int dataId;
	
	/**多媒体类型*/
	private int mediaType;
	
	/**多媒体格式编码*/
	private int format;
	
	/**事件项编码*/
	private int eventId;
	
	/**通道ID*/
	private int wayId;

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	public int getMediaType() {
		return mediaType;
	}

	public void setMediaType(int mediaType) {
		this.mediaType = mediaType;
	}

	public int getFormat() {
		return format;
	}

	public void setFormat(int format) {
		this.format = format;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getWayId() {
		return wayId;
	}

	public void setWayId(int wayId) {
		this.wayId = wayId;
	}
	
	
}
