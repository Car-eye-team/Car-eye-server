package com.careye.sysset.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-10-10 下午05:54:40
 * @修改人：ll
 * @修改时间：2016-10-10 下午05:54:40
 * @修改备注：
 * @version 1.0
 */
public class VideoParamSet {
	
	/****/
	private Integer id;
	
	/**协议类型**/
	private String protocol;
	
	/**IP**/
	private String ip;
	
	/**端口**/
	private String port;
	
	/**提供类型**/
	private String rendertype;
	
	/**用户名**/
	private String name;
	
	/**密码**/
	private String password;
	
	/**缓存**/
	private String inspectionday;
	
	/**播放声音**/
	private String playsound;
	
	/**按比例显示**/
	private String showtoscale;
	
	/**显示码率信息**/
	private String showsatic;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRendertype() {
		return rendertype;
	}

	public void setRendertype(String rendertype) {
		this.rendertype = rendertype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInspectionday() {
		return inspectionday;
	}

	public void setInspectionday(String inspectionday) {
		this.inspectionday = inspectionday;
	}

	public String getPlaysound() {
		return playsound;
	}

	public void setPlaysound(String playsound) {
		this.playsound = playsound;
	}

	public String getShowtoscale() {
		return showtoscale;
	}

	public void setShowtoscale(String showtoscale) {
		this.showtoscale = showtoscale;
	}

	public String getShowsatic() {
		return showsatic;
	}

	public void setShowsatic(String showsatic) {
		this.showsatic = showsatic;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
}
