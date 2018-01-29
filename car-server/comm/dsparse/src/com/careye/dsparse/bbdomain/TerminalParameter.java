/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import java.util.ArrayList;
import java.util.List;

import com.careye.dsparse.constant.BaseInfo;


/**    
 *     
 * 项目名称：dsparse    
 * 类名称：TerminalParameter    
 * 类描述：终端参数    
 * 创建人：zr    
 * 创建时间：2015-5-16 下午02:18:37    
 * 修改人：zr    
 * 修改时间：2015-5-16 下午02:18:37    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TerminalParameter extends BaseInfo{
	
	/**参数总数*/
	private int count;
	
	/**应答流水号*/
	private int seqR;
	
	/**参数项列表*/
	private List<ParameterInfo> items = new ArrayList<ParameterInfo>();
	
	private List<Integer> inititems;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<ParameterInfo> getItems() {
		return items;
	}

	public void setItems(List<ParameterInfo> items) {
		this.items = items;
	}

	public List<Integer> getInititems() {
		return inititems;
	}

	public void setInititems(List<Integer> inititems) {
		this.inititems = inititems;
	}

	public int getSeqR() {
		return seqR;
	}

	public void setSeqR(int seqR) {
		this.seqR = seqR;
	}
	
	
	
	
}
