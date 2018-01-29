/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：TerminalGeneralRes    
 * 类描述：终端通用应答    
 * 创建人：zr    
 * 创建时间：2015-6-8 上午11:50:29    
 * 修改人：zr    
 * 修改时间：2015-6-8 上午11:50:29    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TerminalGeneralRes {
	
	/**应答流水号*/
	private int respseq;
	
	/**应答ID*/
	private int respmsgid;
	
	/**结果*/
	private int result;
	
	/**时间*/
	private String time;

	public int getRespseq() {
		return respseq;
	}

	public void setRespseq(int respseq) {
		this.respseq = respseq;
	}

	public int getRespmsgid() {
		return respmsgid;
	}

	public void setRespmsgid(int respmsgid) {
		this.respmsgid = respmsgid;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	
}
