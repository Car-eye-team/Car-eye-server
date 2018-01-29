/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.bbdomain;

import java.util.List;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：CameraCommandResponse    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-6 下午02:19:27    
 * 修改人：zr    
 * 修改时间：2015-6-6 下午02:19:27    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class CameraCommandResponse {

	/**应答流水号*/
	private int  seqR;
	
	/**结果*/
	private int result;
	
	/**多媒体个数*/
	private int count;
	
	/**多媒体id列表*/
	private  List<Integer> list;

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}
	
}
