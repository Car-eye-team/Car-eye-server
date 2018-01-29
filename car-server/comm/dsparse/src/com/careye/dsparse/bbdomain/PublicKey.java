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
 * 类名称：PublicKey    
 * 类描述：公钥    
 * 创建人：zr    
 * 创建时间：2015-6-6 下午03:22:32    
 * 修改人：zr    
 * 修改时间：2015-6-6 下午03:22:32    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class PublicKey extends BaseInfo{

	/**平台RSA 公钥{e,n}中的e*/
	private int e;
	
	/**RSA 公钥{e,n}中的n*/
	private String n;

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}
	
	
	
}
