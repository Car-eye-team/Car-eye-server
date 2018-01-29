/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**
 * @项目名称：dsparse
 * @类名称：TerminalUpdateResponse
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-6-1 上午10:35:32
 * @修改人：zhangrong
 * @修改时间：2015-6-1 上午10:35:32
 * @修改备注：
 * @version 1.0
 */
public class TerminalUpdateResponse {
	
	/**升级类型0：终端，12：道路运输证 IC 卡读卡器，52：北斗卫星定位模块*/
	private int type;

	/**升级结果	0：成功，1：失败，2：取消 */
	private int result;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
}
