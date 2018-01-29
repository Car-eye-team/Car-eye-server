package com.careye.monitor.domain;

import com.careye.base.action.BaseDomain;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-4-29 上午10:07:32
 * @修改人：ll
 * @修改时间：2016-4-29 上午10:07:32
 * @修改备注：
 * @version 1.0
 */
public class OnlineRate extends BaseDomain {
	
	private Integer id;
	
	private Integer carid;
	
	private String carnumber;
	
	/**上线次数**/
	private Integer inlinecount;
	
	/**下线次数**/
	private Integer offlinecount;
	
	/**在线时长**/
	private String onlinetimes;
	
	/**在线时间比**/
	private String onlinepercent;
	
	/**日期**/
	private String createtime;

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getInlinecount() {
		return inlinecount;
	}

	public void setInlinecount(Integer inlinecount) {
		this.inlinecount = inlinecount;
	}

	public Integer getOfflinecount() {
		return offlinecount;
	}

	public void setOfflinecount(Integer offlinecount) {
		this.offlinecount = offlinecount;
	}

	public String getOnlinetimes() {
		return onlinetimes;
	}

	public void setOnlinetimes(String onlinetimes) {
		this.onlinetimes = onlinetimes;
	}

	public String getOnlinepercent() {
		return onlinepercent;
	}

	public void setOnlinepercent(String onlinepercent) {
		this.onlinepercent = onlinepercent;
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
