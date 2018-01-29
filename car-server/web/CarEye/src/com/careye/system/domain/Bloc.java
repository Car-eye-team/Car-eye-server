/**
* Description: car-eye车辆管理平台
* 文件名：OrganicationDept.java
* 版本信息：1.0
* 日期：2014-5-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.system.domain;

import java.io.Serializable;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：Bloc
 * @类描述：集团表
 * @创建人：huangqin
 * @创建时间：2015-3-5 上午10:25:37
 * @修改人：huangqin
 * @修改时间：2015-3-5 上午10:25:37
 * @修改备注：
 * @version 1.0
 */
public class Bloc extends BaseDomain  implements  Serializable {

	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/**id**/
	private Integer id;
	/**集团名称**/
	private String bloc_name;
	/**联系人**/
	private String contract;
	/**联系电话**/
	private String tel;
	/**联系地址**/
	private String address;
	/**上级父ID 为0代表根节点**/
	private Integer parentid;
	/**备注**/
	private String remark;
	/**用户ID**/
	private Integer userid;
	/**创建时间**/
	private String createtime;
	
	private String leg_per;
	private String leg_per_card;
	private Integer ec_nature;
	private String management;
	private String reg_number;
	private String reg_capital;
	private Integer reg_person;;
	private String reg_date;
	private String fax;
	private String reg_address;
	private String res_per;
	private String est_date;
	private String ent_reg_date;
	private String email;
	private String com_homepage;
	private String bu_li_number;
	private String tax_reg_number;
	private String issuing_date;
	private String permit_person;
	private String business_scope;
	
	private String companyid;

	
	private String text;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBloc_name() {
		return bloc_name;
	}
	public void setBloc_name(String bloc_name) {
		this.bloc_name = bloc_name;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLeg_per() {
		return leg_per;
	}
	public void setLeg_per(String legPer) {
		leg_per = legPer;
	}
	public String getLeg_per_card() {
		return leg_per_card;
	}
	public void setLeg_per_card(String legPerCard) {
		leg_per_card = legPerCard;
	}
	public Integer getEc_nature() {
		return ec_nature;
	}
	public void setEc_nature(Integer ecNature) {
		ec_nature = ecNature;
	}
	public String getManagement() {
		return management;
	}
	public void setManagement(String management) {
		this.management = management;
	}
	public String getReg_number() {
		return reg_number;
	}
	public void setReg_number(String regNumber) {
		reg_number = regNumber;
	}
	public String getReg_capital() {
		return reg_capital;
	}
	public void setReg_capital(String regCapital) {
		reg_capital = regCapital;
	}
	public Integer getReg_person() {
		return reg_person;
	}
	public void setReg_person(Integer regPerson) {
		reg_person = regPerson;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String regDate) {
		reg_date = regDate;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getReg_address() {
		return reg_address;
	}
	public void setReg_address(String regAddress) {
		reg_address = regAddress;
	}
	public String getRes_per() {
		return res_per;
	}
	public void setRes_per(String resPer) {
		res_per = resPer;
	}
	public String getEst_date() {
		return est_date;
	}
	public void setEst_date(String estDate) {
		est_date = estDate;
	}
	public String getEnt_reg_date() {
		return ent_reg_date;
	}
	public void setEnt_reg_date(String entRegDate) {
		ent_reg_date = entRegDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCom_homepage() {
		return com_homepage;
	}
	public void setCom_homepage(String comHomepage) {
		com_homepage = comHomepage;
	}
	public String getBu_li_number() {
		return bu_li_number;
	}
	public void setBu_li_number(String buLiNumber) {
		bu_li_number = buLiNumber;
	}
	public String getTax_reg_number() {
		return tax_reg_number;
	}
	public void setTax_reg_number(String taxRegNumber) {
		tax_reg_number = taxRegNumber;
	}
	public String getIssuing_date() {
		return issuing_date;
	}
	public void setIssuing_date(String issuingDate) {
		issuing_date = issuingDate;
	}
	public String getPermit_person() {
		return permit_person;
	}
	public void setPermit_person(String permitPerson) {
		permit_person = permitPerson;
	}
	public String getBusiness_scope() {
		return business_scope;
	}
	public void setBusiness_scope(String businessScope) {
		business_scope = businessScope;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	
}











