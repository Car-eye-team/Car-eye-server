package com.careye.car.domain;

import com.careye.base.action.BaseDomain;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-10-20 下午01:52:33
 * @修改人：ll
 * @修改时间：2015-10-20 下午01:52:33
 * @修改备注：
 * @version 1.0
 */
public class CarCondition extends BaseDomain{
	
	private Integer id;
	/**车辆id**/
	private Integer carid;
	/**创建时间**/
	private String createtime;
	/**在线时间**/
	private String onlinetimes;
	/**车牌号**/
	private String carnumber;
	/**终端号码**/
	private String terminal;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCarid() {
		return carid;
	}
	public void setCarid(Integer carid) {
		this.carid = carid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getOnlinetimes() {
		return onlinetimes;
	}
	public void setOnlinetimes(String onlinetimes) {
		this.onlinetimes = onlinetimes;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
}
