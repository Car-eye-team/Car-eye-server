/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：RecordStartCommand    
 * 类描述：录音开始命令    
 * 创建人：zr    
 * 创建时间：2015-6-6 下午02:55:36    
 * 修改人：zr    
 * 修改时间：2015-6-6 下午02:55:36    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class RecordStartCommand extends BaseInfo{

	/** 录音命令   **/
	private int  cmd;
	
	/** 录音时间  **/
	private int  seconds;
	
	/** 保存标志**/
	private  int tag;
	
	/**  音频采样率**/
	private  int rate;

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
	
}
