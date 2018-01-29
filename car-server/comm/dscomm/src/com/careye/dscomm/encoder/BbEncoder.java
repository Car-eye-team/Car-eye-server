/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dscomm.encoder;

import org.apache.log4j.Logger;

import com.careye.dscomm.utlis.ExceptionUtil;
import com.careye.dscomm.utlis.ParseUtil;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：BbEncoder    
 * 类描述： 808部标协议编码    
 * 创建人：zr    
 * 创建时间：2015-5-13 下午04:23:16    
 * 修改人：zr    
 * 修改时间：2015-5-13 下午04:23:16    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BbEncoder {
	
	private final static Logger logger = Logger.getLogger(BbEncoder.class);

	/**
	 * 平台通用应答
	 * @param reseq 应答流水号
	 * @param msgid  对应的平台消息的ID
	 * @param result 0：成功/确认；1：失败；2：消息有误；3：不支持 
	 * @return
	 */
	public static byte[] platformResponse(String terminal,int reseq,int msgid,int result){

		try {
			byte[] bodyByte = new byte[5];
			int dstPos = 0;

			//对应的平台消息的流水号
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(reseq,2)), 0, bodyByte, dstPos, 2);
			dstPos+=2;

			//对应的平台消息的ID
			System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(msgid,2)), 0, bodyByte, dstPos, 2);
			dstPos+=2;

			//0：成功/确认；1：失败；2：消息有误；3：不支持
			System.arraycopy(ParseUtil.longToByteOne(result), 0, bodyByte, dstPos, 1);

			byte[] msgbytes = BbEncoderUtil.getProtocol808(terminal,0x8001,bodyByte,BbEncoderUtil.getSerialId());
			
			logger.info("平台通用应答 ["+terminal+"] ["+ParseUtil.parseByteToHexStr(msgbytes)+"]");

			return msgbytes;

		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
			return null;
		}

	}
	
	

}
