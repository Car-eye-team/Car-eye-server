/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import com.careye.dsparse.constant.BaseInfo;

/**
 * 
 * @项目名称：dsparse
 * @类名称：GoodSourceInfo
 * @类描述：货源信息
 * @创建人：huangqin
 * @创建时间：2015-7-7 下午05:12:11
 * @修改人：huangqin
 * @修改时间：2015-7-7 下午05:12:11
 * @修改备注：
 * @version 1.0
 */
public class GoodSourceInfo  extends BaseInfo{
	 
	 /**标志 **/
	 private int flag;
	 /**货源ID **/
	 private int sgid;
	 /**货物情况 **/
	 private int goodssituation;
	 /**重量 **/
	 private int weight;
	 /**数量 **/
	 private int quantity;
	 /**是否长期货源 **/
	 private int longtermsupply;
	 /**纬度 **/
	 private int lat;
	 /**经度 **/
	 private int lng;
	 /**运费 **/
	 private String shipping;
	 /**订单ID **/
	 private String orderid;
	 /**货源名称 **/
	 private String goodsname;
	 /**形状 **/
	 private String shape;
	 /**包装方式 **/
	 private String packingname;
	 /**运输类型 **/
	 private String typetransport;
	 /**运送方式 **/
	 private String shippingmethod;
	 /**注意事项 **/
	 private String precautions;
	 /**联系人**/
	 private String fullname;
	 /**联系电话 **/
	 private String tel;
	 /**联系手机 **/
	 private String phone;
	 /**取货地址 **/
	 private String address;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getSgid() {
		return sgid;
	}
	public void setSgid(int sgid) {
		this.sgid = sgid;
	}
	public int getGoodssituation() {
		return goodssituation;
	}
	public void setGoodssituation(int goodssituation) {
		this.goodssituation = goodssituation;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getLongtermsupply() {
		return longtermsupply;
	}
	public void setLongtermsupply(int longtermsupply) {
		this.longtermsupply = longtermsupply;
	}
	public int getLat() {
		return lat;
	}
	public void setLat(int lat) {
		this.lat = lat;
	}
	public int getLng() {
		return lng;
	}
	public void setLng(int lng) {
		this.lng = lng;
	}
	public String getShipping() {
		return shipping;
	}
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getPackingname() {
		return packingname;
	}
	public void setPackingname(String packingname) {
		this.packingname = packingname;
	}
	public String getTypetransport() {
		return typetransport;
	}
	public void setTypetransport(String typetransport) {
		this.typetransport = typetransport;
	}
	public String getShippingmethod() {
		return shippingmethod;
	}
	public void setShippingmethod(String shippingmethod) {
		this.shippingmethod = shippingmethod;
	}
	public String getPrecautions() {
		return precautions;
	}
	public void setPrecautions(String precautions) {
		this.precautions = precautions;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
