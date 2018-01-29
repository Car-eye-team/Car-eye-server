package com.careye.api.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：顶灯广告
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-25 上午11:57:59
 * @修改人：ll
 * @修改时间：2016-5-25 上午11:57:59
 * @修改备注：
 * @version 1.0
 */
public class AdvertContentDd {
	
	private Integer id;
	
	/**拼接的内容**/
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
