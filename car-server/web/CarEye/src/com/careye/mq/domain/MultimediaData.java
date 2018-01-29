/**
* Description: 多森商用车平台
* 文件名：MultimediaData.java
* 版本信息：1.0
* 日期：2014-6-6
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.mq.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @项目名称：FMS
 * @类名称：MultimediaData
 * @类描述：多媒体数据实体
 * @创建人：zr
 * @创建时间：2014-6-6 上午10:47:16
 * @修改人：zr
 * @修改时间：2014-6-6 上午10:47:16
 * @修改备注：
 * @version 1.0
 */
public class MultimediaData {
	
	/**多媒体ID**/
	private Integer dataId;
	
	/**多媒体类型 0：图像；1：音频；2：视频**/
	private Integer mediaType;
	
	/**多媒体格式编码 0：JPEG；1：TIF；2：MP3；3：WAV；4：WMV；其他保留**/
	private Integer format;
	
	/**事件项编码0：平台下发指令；1：定时动作；2：抢劫报警触发；3：碰撞侧翻报警触发；其他保留**/
	private Integer eventId;
	
	/**通道ID**/
	private Integer wayId;
	
	/**数据总包数**/
	private Integer packetSum;
	
	/**数据包map**/
	private Map<Integer, String> map = new HashMap<Integer, String>();

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public Integer getMediaType() {
		return mediaType;
	}

	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}

	public Integer getFormat() {
		return format;
	}

	public void setFormat(Integer format) {
		this.format = format;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getWayId() {
		return wayId;
	}

	public void setWayId(Integer wayId) {
		this.wayId = wayId;
	}

	public Integer getPacketSum() {
		return packetSum;
	}

	public void setPacketSum(Integer packetSum) {
		this.packetSum = packetSum;
	}

	public Map<Integer, String> getMap() {
		return map;
	}

	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}
	
	
}
