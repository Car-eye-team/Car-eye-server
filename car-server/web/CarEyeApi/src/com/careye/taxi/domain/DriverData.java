package com.careye.taxi.domain;

import net.spy.memcached.transcoders.IntegerTranscoder;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：司机评价数据
 * @创建人：ll
 * @创建时间：2015-12-7 下午05:41:08
 * @修改人：ll
 * @修改时间：2015-12-7 下午05:41:08
 * @修改备注：
 * @version 1.0
 */
public class DriverData {
	
	/**评价星级**/
	private Integer slevel;
	
	/**评价级别**/
	private Integer evalevel;
	
	/**服务次数**/
	private Integer servicecount;
	
	/**好评次数**/
	private Integer goodcount;

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

	public Integer getServicecount() {
		return servicecount;
	}

	public void setServicecount(Integer servicecount) {
		this.servicecount = servicecount;
	}

	public Integer getGoodcount() {
		return goodcount;
	}

	public void setGoodcount(Integer goodcount) {
		this.goodcount = goodcount;
	}
	
}
