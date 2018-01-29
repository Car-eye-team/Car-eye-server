/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dscomm.decoder;

import org.apache.log4j.Logger;

import com.careye.dscomm.domain.Protocol;
import com.careye.dscomm.utlis.ExceptionUtil;
import com.careye.dscomm.utlis.ParseUtil;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：BbDecoder    
 * 类描述：部标协议解码    
 * 创建人：zr    
 * 创建时间：2015-5-13 上午11:17:49    
 * 修改人：zr    
 * 修改时间：2015-5-13 上午11:17:49    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BbDecoder {

	private final static Logger logger = Logger.getLogger(BbDecoder.class);

	/**
	 * 解码808部标协议
	 * @param data
	 */
	public static Protocol decoder808Data(byte[] data){
		
		if(data.length < 10){
			return null;
		}

		String dataHex = ParseUtil.parseByteToHexStr(data);
		logger.info("[client->server] 接收到808部标消息 ["+dataHex+"]");

		int result = 0; //0：成功/确认；1：失败；2：消息有误；3：不支持  

		try {
			
			Protocol protocol = new Protocol();

			int datalen = data.length;

			//获取消息头、消息体、校验码进行转义
			byte[] bytes = ParseUtil.dataBbEscape(ParseUtil.byteTobyte(data, 1, datalen-2),1);
			//转义之后的长度
			datalen = bytes.length;
			//验证校验码是否一致
			byte[] jym = ParseUtil.byteTobyte(bytes,(bytes.length-1), 1);

			//消息头、消息体异或运算和得到校验码
			byte[] yzjym = ParseUtil.byteOrbyte(ParseUtil.byteTobyte(bytes,0, (bytes.length-1)));

			if(ParseUtil.parseByteToHexStr(jym).equals(ParseUtil.parseByteToHexStr(yzjym))){
				int nOff = 0;
				//消息ID
				byte[] msgidByte = ParseUtil.byteTobyte(bytes, nOff, 2);
				nOff+=2;
				int msgid = Integer.parseInt(ParseUtil.parseByteToHexStr(msgidByte), 16);

				//消息体属性
				byte[] bodyAttrByte = ParseUtil.byteTobyte(bytes, nOff, 2);
				nOff+=2;

				int bodylen = datalen-13;
				int fb = 0;
				String encryption = null;

				//当消息体属性中第 13 位为1 时表示消息体为长消息，进行分包发送处理
				int totalNum = 0; //消息总包数
				int serialNum = 0; //包序号

				try {
					//解析消息体属性
					String bodyAttr = ParseUtil.byteTobit(bodyAttrByte);
					//分包
					fb = Integer.valueOf(bodyAttr.substring(2,3),2);
					//数据加密方式
					encryption = bodyAttr.substring(3, 6);
					//消息体长度
					bodylen = Integer.valueOf(bodyAttr.substring(6, 16),2);

				} catch (Exception e) {
					e.printStackTrace();
					ExceptionUtil.getInfo(e);
					logger.error("[client->server] 解析消息体属性异常:"+e);
				}

				//终端手机号
				String terminal = ParseUtil.bcd2Str(ParseUtil.byteTobyte(bytes, nOff, 6));
				
				nOff+=6;

				// 消息体流水号
				int seq = Integer.parseInt(ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(bytes, nOff, 2)),16);

				//当消息体属性中第 13 位为1 时表示消息体为长消息，进行分包发送处理
				int num = 0;
				if(fb == 0){
					num = 12; 
				}else{
					totalNum = Integer.parseInt(ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(bytes, 12, 2)),16);
					serialNum = Integer.parseInt(ParseUtil.parseByteToHexStr(ParseUtil.byteTobyte(bytes, 14, 2)),16);
					num = 16;
				}

				//消息体
				byte[] bodybyte = ParseUtil.byteTobyte(bytes, num, bodylen);
				String msgbody = null;
				if(bodybyte != null){
					msgbody = ParseUtil.parseByteToHexStr(bodybyte);
				}
				
				logger.info("[client->server] 解码部标消息头信息 [设备号:"+terminal+"] [msgid:"+msgid+" seq:"+seq+" datalen:"+datalen+" 分包 (0 不分包 1 分包):"+fb+" 消息总包数:"+totalNum+" 包序号:"+serialNum+" 加密方式:"+encryption+" bodylen:"+bodybyte.length+" msgbody:"+msgbody+"]");

				protocol.setMsgid(msgid);
				protocol.setTerminal(terminal);
				protocol.setMsglen(datalen);
				protocol.setSeq(seq);
				protocol.setMsgbody(msgbody);
				protocol.setTotalnum(totalNum);
				protocol.setSerialnum(serialNum);
				protocol.setResult(result);

				return protocol;
			}else{
				result = 2;
				logger.info("[client->server] 校验码不正确,直接抛弃协议:"+ParseUtil.parseByteToHexStr(data));
				return null;
			}

		} catch (Exception e) {
			result = 1;
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
			logger.error("解析消息异常:"+dataHex);
			return null;
		}

	}
	
	
	public static void main(String[] args) {

		decoder808Data(ParseUtil.parseHexStrToByte("7E02000032013208068487026B00000000000C00030286AC2B074E22680060025400A21505131147120104000323FD03020000250400000000300116310114407E"));
		/*System.out.println(ParseUtil.bitToByte("0000110010"));
		String hex = Integer.toBinaryString(115);
		System.out.println(hex);
		System.out.println(Integer.valueOf("0000110010", 2));

		System.out.println(ParseUtil.parseHexStrToByte("02000032013208068487026B00000000000C00030286AC2B074E22680060025400A21505131147120104000323FD03020000250400000000300116310114").length);

		String aa = "0123456789876543";
		System.out.println(aa.substring(2, 3));
		System.out.println(aa.substring(3, 6));

		System.out.println(aa.substring(6, 16));*/

		//BbEncoder.platformResponse("13208068487", 306, 512, 0);
	}

}
