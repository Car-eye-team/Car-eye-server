package com.careye.api.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-25 下午01:42:07
 * @修改人：ll
 * @修改时间：2016-5-25 下午01:42:07
 * @修改备注：
 * @version 1.0
 */
public class AdvertContentHzp {
	
	private Integer id;
	
	/**广告类型名称**/
	private String typename;
	
	/**广告名称**/
	private String adname;
	
	/**链接地址**/
	private String linkpath;
	
	/**图片**/
	private String path;
	
	/**到期日期**/
	private String dtime;
	
	/**广告描述**/
	private String remark;
	
	/**发布时间**/
	private String reltime;
	
	/**广告类型 1 图片广告 2 文字广告 3 视频广告**/
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getAdname() {
		return adname;
	}

	public void setAdname(String adname) {
		this.adname = adname;
	}

	public String getLinkpath() {
		return linkpath;
	}

	public void setLinkpath(String linkpath) {
		this.linkpath = linkpath;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDtime() {
		return dtime;
	}

	public void setDtime(String dtime) {
		this.dtime = dtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReltime() {
		return reltime;
	}

	public void setReltime(String reltime) {
		this.reltime = reltime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
