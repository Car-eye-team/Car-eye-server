package com.careye.transaction.domain;

import com.careye.base.action.BaseDomain;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-25 上午11:53:04
 * @修改人：ll
 * @修改时间：2015-11-25 上午11:53:04
 * @修改备注：
 * @version 1.0
 */
public class DriverEvaluation extends BaseDomain {
	
	private Integer id;
	/**订单id**/
	private String orderid;
	/**星级**/
	private Integer slevel;
	/**评价内容**/
	private String content;
	/**创建时间**/
	private String createtime;
	/**司机id**/
	private Integer did;
	/**司机姓名**/
	private String dname;
	/**司机手机号**/
	private String dphone;
	/**客户id**/
	private Integer cid;
	/**客户姓名**/
	private String cname;
	/**客户手机号**/
	private String cphone;
	/**上车地址**/
	private String saddress;
	/**下车地址**/
	private String eaddress;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	public String getEaddress() {
		return eaddress;
	}
	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
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
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDphone() {
		return dphone;
	}
	public void setDphone(String dphone) {
		this.dphone = dphone;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	
}
