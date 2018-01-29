/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.decoder;

import org.apache.mina.core.session.IoSession;

import com.careye.dscomm.utlis.ExceptionUtil;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：DecoderUtil    
 * 类描述：解码协议工具类  
 * 创建人：zr    
 * 创建时间：2015-5-18 上午10:03:04    
 * 修改人：zr    
 * 修改时间：2015-5-18 上午10:03:04    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DecoderUtil {

	/**
	 * 
	 * @param data
	 * @return devicetype 0 异常协议 22 部标协议 23 雅迅协议  17 博实结协议
	 */
	public static int protocolJudgment(byte[] data,String dataHex,int type,IoSession session){

		int devicetype = 22;

		try {
			//7E开头7E结尾协议
			if(dataHex.startsWith("7E") && dataHex.endsWith("7E")){
				if(type > 0){
					return type;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
		}

		return devicetype;

	}

}
