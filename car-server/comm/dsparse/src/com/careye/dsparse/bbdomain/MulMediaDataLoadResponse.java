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
 * 类名称：MulMediaDataLoadResponse    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-6 下午02:03:03    
 * 修改人：zr    
 * 修改时间：2015-6-6 下午02:03:03    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class MulMediaDataLoadResponse extends BaseInfo{

	/** 应答流水号**/
	private int seqR;
	
	/**多媒体ID  DWORD  **/
	private int dataId;
	
	/** 重传包总数 **/
	private int packetSum;
	
	/** 重传包 ID 列表**/
	private List<Integer> ids = new ArrayList<Integer>();

	public int getSeqR() {
		return seqR;
	}

	public void setSeqR(int seqR) {
		this.seqR = seqR;
	}

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	public int getPacketSum() {
		return packetSum;
	}

	public void setPacketSum(int packetSum) {
		this.packetSum = packetSum;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
	
}
