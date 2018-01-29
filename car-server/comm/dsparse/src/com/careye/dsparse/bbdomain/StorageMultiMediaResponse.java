/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import java.util.ArrayList;
import java.util.List;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：StorageMultiMediaResponse    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-6 下午02:39:44    
 * 修改人：zr    
 * 修改时间：2015-6-6 下午02:39:44    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class StorageMultiMediaResponse {

	/** 应答流水号  **/
	private  int  seqR;
	
	/** 多媒体数据总项数 **/
	private  int count;
	
	/**  检索项 **/
	private  List<MultiMediaRetrieval> items = new ArrayList<MultiMediaRetrieval>();

	public int getSeqR() {
		return seqR;
	}

	public void setSeqR(int seqR) {
		this.seqR = seqR;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<MultiMediaRetrieval> getItems() {
		return items;
	}

	public void setItems(List<MultiMediaRetrieval> items) {
		this.items = items;
	}
	
	
}
