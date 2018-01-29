/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import java.io.Serializable;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：MultiMediaData    
 * 类描述：多媒体数据上传    
 * 创建人：zr    
 * 创建时间：2015-6-6 下午01:45:13    
 * 修改人：zr    
 * 修改时间：2015-6-6 下午01:45:13    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class MultiMediaData implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**多媒体ID*/
	private int dataId;
	
	/**多媒体类型*/
	private int mediaType;
	
	/**多媒体格式编码*/
	private int format;
	
	/**事件项编码*/ 
	private int eventId;
	
	/**通道 ID*/
	private int wayId;
	
	/**位置信息汇报(0x0200)消息体 */
	private PositionInfo positionInfo;
	
	
	/**数据总包数*/
	private int packetSum;
	
	/**包ID*/
	private int packetId;
	
	/**多媒体数据包*/
	private String  mediaData;

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

	public PositionInfo getPositionInfo() {
		return positionInfo;
	}

	public void setPositionInfo(PositionInfo positionInfo) {
		this.positionInfo = positionInfo;
	}

	public String getMediaData() {
		return mediaData;
	}

	public void setMediaData(String mediaData) {
		this.mediaData = mediaData;
	}

	public int getPacketSum() {
		return packetSum;
	}

	public void setPacketSum(int packetSum) {
		this.packetSum = packetSum;
	}

	public int getPacketId() {
		return packetId;
	}

	public void setPacketId(int packetId) {
		this.packetId = packetId;
	}
	
	
}
