package com.careye.taxi.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-5 下午02:18:19
 * @修改人：ll
 * @修改时间：2016-5-5 下午02:18:19
 * @修改备注：
 * @version 1.0
 */
public class GroupMember {
	
	/**对讲组id**/
	private Integer gid;
	
	/**车辆id**/
	private Integer carid;
	
	/**车牌号**/
	private String carnumber;
	
	/**设备号**/
	private String terminal;
	
	/**司机姓名**/
	private String drivername;
	
	/**司机电话**/
	private String driverphone;
	
	/**加入时间**/
	private String createtime;
	
	

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

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

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getDriverphone() {
		return driverphone;
	}

	public void setDriverphone(String driverphone) {
		this.driverphone = driverphone;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	
	
}
