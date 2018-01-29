package com.careye.car.domain;

import java.util.List;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-10-19 下午05:58:25
 * @修改人：ll
 * @修改时间：2015-10-19 下午05:58:25
 * @修改备注：
 * @version 1.0
 */
public class CarDriverInfo {
	
	private Integer id;
	private Integer carid;
	
	private Integer blocid;
	/**集团名称**/
	private String blocname;
	/**终端号码*/
	private String terminal;
	/**车牌号*/
	private String carnumber;
	/**联系电话*/
	private String phone;
	private List<Integer> list;
	
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
	public Integer getBlocid() {
		return blocid;
	}
	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}
	public String getBlocname() {
		return blocname;
	}
	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Integer> getList() {
		return list;
	}
	public void setList(List<Integer> list) {
		this.list = list;
	}
	
	
}
