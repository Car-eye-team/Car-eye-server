package com.careye.common.domain;

import java.io.Serializable;

/**
 * @项目名称：TAXISERVER
 * @类名称：MediaEvent
 * @类描述：多媒体事件数据
 * @创建人：zr
 * @创建时间：2015-3-11 下午07:28:24
 * @修改人：zr
 * @修改时间：2015-3-11 下午07:28:24
 * @修改备注：
 * @version 1.0
 */
public class MediaEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer carid;

	/**多媒体ID*/
	private int dataid;
	
	/**多媒体类型*/
	private int mediatype;
	
	/**多媒体格式编码*/
	private int format;
	
	/**事件项编码*/
	private int eventcode;
	
	/**通道ID*/
	private int accessid;

	private String createtime;
		

	public int getFormat() {
		return format;
	}

	public void setFormat(int format) {
		this.format = format;
	}



	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public int getEventcode() {
		return eventcode;
	}

	public void setEventcode(int eventcode) {
		this.eventcode = eventcode;
	}

	public int getAccessid() {
		return accessid;
	}

	public void setAccessid(int accessid) {
		this.accessid = accessid;
	}



	public int getDataid() {
		return dataid;
	}



	public void setDataid(int dataid) {
		this.dataid = dataid;
	}



	public int getMediatype() {
		return mediatype;
	}



	public void setMediatype(int mediatype) {
		this.mediatype = mediatype;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	

}
