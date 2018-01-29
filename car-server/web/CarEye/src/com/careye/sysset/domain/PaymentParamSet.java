package com.careye.sysset.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-11-3 下午04:10:57
 * @修改人：ll
 * @修改时间：2016-11-3 下午04:10:57
 * @修改备注：
 * @version 1.0
 */
public class PaymentParamSet {
	
	private Integer id;
	/**编号**/
	private Integer type;
	/**收款公司**/
	private String company;
	/**微信APP密钥**/
	private String appsecret;
	/**微信公众号ID**/
	private String appid;
	/**微信商户ID**/
	private String mchid;
	/**微信API密钥**/
	private String key;
	/**微信生成签名字符串**/
	private String token;
	/**微信消息加密密钥**/
	private String encodingaeskey;
	/**微信API证书路径**/
	private String apiclientpath;
	/**手机微信公众号ID**/
	private String app_appid;
	/**手机微信商户ID**/
	private String app_mchid;
	/**手机微信API密钥**/
	private String app_key;
	/**卖家支付宝账号**/
	private String seller_email;
	/**支付宝合作者ID**/
	private String partner;
	/**卖家支付宝用户号**/
	private String seller_id;
	/**卖家支付宝密钥**/
	private String seller_key;
	/**创建时间**/
	private String createtime;
	/**订单标题**/
	private String subject;
	/**支付宝配置文件路径**/
	private String alipay_config_path;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMchid() {
		return mchid;
	}
	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEncodingaeskey() {
		return encodingaeskey;
	}
	public void setEncodingaeskey(String encodingaeskey) {
		this.encodingaeskey = encodingaeskey;
	}
	public String getApiclientpath() {
		return apiclientpath;
	}
	public void setApiclientpath(String apiclientpath) {
		this.apiclientpath = apiclientpath;
	}
	public String getApp_appid() {
		return app_appid;
	}
	public void setApp_appid(String appAppid) {
		app_appid = appAppid;
	}
	public String getApp_mchid() {
		return app_mchid;
	}
	public void setApp_mchid(String appMchid) {
		app_mchid = appMchid;
	}
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String appKey) {
		app_key = appKey;
	}
	public String getSeller_email() {
		return seller_email;
	}
	public void setSeller_email(String sellerEmail) {
		seller_email = sellerEmail;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String sellerId) {
		seller_id = sellerId;
	}
	public String getSeller_key() {
		return seller_key;
	}
	public void setSeller_key(String sellerKey) {
		seller_key = sellerKey;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAlipay_config_path() {
		return alipay_config_path;
	}
	public void setAlipay_config_path(String alipayConfigPath) {
		alipay_config_path = alipayConfigPath;
	}
	
}
