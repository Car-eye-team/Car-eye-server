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
 * 类名称：RegisterResponse    
 * 类描述：终端注册应答    
 * 创建人：zr    
 * 创建时间：2015-5-14 下午04:38:16    
 * 修改人：zr    
 * 修改时间：2015-5-14 下午04:38:16    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class RegisterResponse extends BaseInfo{
	
	/**应答流水号*/
	private int seqR;
	
	/**应答结果0：成功；1：车辆已被注册；2：数据库中无该车辆；3：终端已被注册；4：数据库中无该终端*/
	private int result;
	
	/**鉴权码*/
	private String passwd;

	public int getSeqR() {
		return seqR;
	}

	public void setSeqR(int seqR) {
		this.seqR = seqR;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
