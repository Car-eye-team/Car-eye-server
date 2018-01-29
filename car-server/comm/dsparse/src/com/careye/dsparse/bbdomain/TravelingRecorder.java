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
 * 类名称：TravelingRecorder    
 * 类描述：行驶记录仪    
 * 创建人：zr    
 * 创建时间：2015-6-6 下午03:41:37    
 * 修改人：zr    
 * 修改时间：2015-6-6 下午03:41:37    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TravelingRecorder extends BaseInfo{
	
	/**命令字*/
	private int cmd;
	
	/**应答流水号*/
	private int seqR;
	
	/**数据块*/
	private String data;

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public int getSeqR() {
		return seqR;
	}

	public void setSeqR(int seqR) {
		this.seqR = seqR;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	
}
