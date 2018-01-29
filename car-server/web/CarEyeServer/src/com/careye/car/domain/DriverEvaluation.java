package com.careye.car.domain;
/**
 * 
 * @项目名称：CVP
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-25 上午11:53:04
 * @修改人：ll
 * @修改时间：2015-11-25 上午11:53:04
 * @修改备注：
 * @version 1.0
 */
public class DriverEvaluation {
	
	private Integer id;
	/**订单id**/
	private String orderid;
	/**星级**/
	private Integer slevel;
	/**评价内容**/
	private String content;
	/**创建时间**/
	private String createtime;
	
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
	
}
