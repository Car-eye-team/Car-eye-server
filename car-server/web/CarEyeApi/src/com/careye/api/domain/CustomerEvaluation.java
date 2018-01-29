package com.careye.api.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-26 下午04:04:38
 * @修改人：ll
 * @修改时间：2015-11-26 下午04:04:38
 * @修改备注：
 * @version 1.0
 */
public class CustomerEvaluation {
	
	private Integer id;
	/**订单id**/
	private String orderid;
	/**星级  1至5数字 数字越大评价越好**/
	private Integer slevel;
	/**评价级别  1好评 2差评**/
	private Integer evalevel;
	/**评价内容**/
	private String content;
	/**评价时间**/
	private String createtime;
	
	private String carnumber;
	private String drivername;
	private String drivercode;//司机代码
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public Integer getSlevel() {
		return slevel;
	}
	public void setSlevel(Integer slevel) {
		this.slevel = slevel;
	}
	public Integer getEvalevel() {
		return evalevel;
	}
	public void setEvalevel(Integer evalevel) {
		this.evalevel = evalevel;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getDrivercode() {
		return drivercode;
	}
	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}
	
}
