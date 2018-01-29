package com.careye.monitor.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-5 下午05:27:29
 * @修改人：ll
 * @修改时间：2015-11-5 下午05:27:29
 * @修改备注：
 * @version 1.0
 */
public class OnlineReport {
	
	/**新增车辆**/
	private Integer addnumber;
	
	/**上线车辆**/
	private Integer onlinenumber;
	
	/**未上线车辆**/
	private Integer unonlinenumber;
	
	/**上线比例**/
	private String onlinepercent;
	
	private Integer userid;
	private Integer blocid;
	private String stime;
	private String etime;
	
	private String blocname;
	private String carnumber;
	private String terminal;
	private String createtime;
	
	
	
	public String getBlocname() {
		return blocname;
	}

	public void setBlocname(String blocname) {
		this.blocname = blocname;
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

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getAddnumber() {
		return addnumber;
	}

	public void setAddnumber(Integer addnumber) {
		this.addnumber = addnumber;
	}

	public Integer getOnlinenumber() {
		return onlinenumber;
	}

	public void setOnlinenumber(Integer onlinenumber) {
		this.onlinenumber = onlinenumber;
	}

	public Integer getUnonlinenumber() {
		return unonlinenumber;
	}

	public void setUnonlinenumber(Integer unonlinenumber) {
		this.unonlinenumber = unonlinenumber;
	}

	public String getOnlinepercent() {
		return onlinepercent;
	}

	public void setOnlinepercent(String onlinepercent) {
		this.onlinepercent = onlinepercent;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getBlocid() {
		return blocid;
	}

	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}
	
}
