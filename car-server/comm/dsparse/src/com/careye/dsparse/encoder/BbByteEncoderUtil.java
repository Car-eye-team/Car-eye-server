/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.encoder;

import java.util.List;

import org.apache.log4j.Logger;

import com.careye.dsparse.bbdomain.CircleAreaItems;
import com.careye.dsparse.bbdomain.EventTerm;
import com.careye.dsparse.bbdomain.InfoDemandMenuItems;
import com.careye.dsparse.bbdomain.LineInfoItems;
import com.careye.dsparse.bbdomain.ParameterInfo;
import com.careye.dsparse.bbdomain.PhoneBookItems;
import com.careye.dsparse.bbdomain.QuesSendCandidateAnswer;
import com.careye.dsparse.bbdomain.RectangleAreaItems;
import com.careye.dsparse.bbdomain.TerminalParameter;
import com.careye.dsparse.utlis.ExceptionUtil;
import com.careye.dsparse.utlis.ParseUtil;
import com.careye.dsparse.utlis.StringUtils;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：ByteEncoderUtil    
 * 类描述：组装部标二进制协议 工具类    
 * 创建人：zr    
 * 创建时间：2015-5-16 下午02:40:35    
 * 修改人：zr    
 * 修改时间：2015-5-16 下午02:40:35    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BbByteEncoderUtil {

	private final static Logger logger = Logger.getLogger(BbByteEncoderUtil.class);

	/**
	 * 获取候选答案列表 长度
	 * @return
	 */
	public static int getQuesSendCandidateAnswerLen(List<QuesSendCandidateAnswer> list){

		int len = 0;
		try {
			for (QuesSendCandidateAnswer answeritem: list) {
				len += 3;
				byte[] contentbyte = ParseUtil.stringToByte(answeritem.getContent());
				len +=  contentbyte.length;
			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}
		return len;
	}

	/**
	 * 获取候选答案列表 二进制
	 * @param list
	 * @param len
	 * @return
	 */
	public static byte[] getQuesSendCandidateAnswerByte(List<QuesSendCandidateAnswer> list,int length){

		byte [] bytes = new byte[length];
		try {
			int dstPos = 0;
			for (QuesSendCandidateAnswer answeritem : list) {

				byte[] contentbyte = ParseUtil.stringToByte(answeritem.getContent());
				int contentlen = contentbyte.length;
				//答案ID
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(answeritem.getId(),1)), 0, bytes, dstPos, 1);
				dstPos += 1;
				//答案内容长度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(contentlen,2)), 0, bytes, dstPos, 2);
				dstPos += 2;
				//答案内容
				System.arraycopy(contentbyte, 0, bytes, dstPos, contentlen);
				dstPos += contentlen;
			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return bytes;

	}


	/**
	 * 获取设置圆形区域二进制
	 * @param list
	 * @param len
	 * @return
	 */
	public static byte[] getCircleAreaByte(List<CircleAreaItems> list,int length){

		byte [] bytes = new byte[length];
		try {
			int dstPos = 0;
			for (CircleAreaItems cItems : list) {

				//区域ID
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cItems.getAreaId(),4)), 0, bytes, dstPos, 4);
				dstPos += 4;
				byte[] attrbyte = new byte[2];
				//区域属性
				StringBuffer attrBuffer = new StringBuffer();
				attrBuffer.append(cItems.getAttr7());
				attrBuffer.append(cItems.getAttr6());
				attrBuffer.append(cItems.getAttr5());
				attrBuffer.append(cItems.getAttr4());
				attrBuffer.append(cItems.getAttr3());
				attrBuffer.append(cItems.getAttr2());
				attrBuffer.append(cItems.getAttr1());
				attrBuffer.append(cItems.getAttr0());
				attrbyte[0] = ParseUtil.bitToByte("00000000");
				attrbyte[1] = ParseUtil.bitToByte(attrBuffer.toString());

				//区域属性
				System.arraycopy(attrbyte, 0, bytes, dstPos, 2);
				dstPos += 2;

				//中心点纬度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cItems.getLat(),4)), 0, bytes, dstPos, 4);
				dstPos += 4;

				//中心点经度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cItems.getLng(),4)), 0, bytes, dstPos, 4);
				dstPos += 4;

				//半径
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cItems.getRadius(),4)), 0, bytes, dstPos, 4);
				dstPos += 4;

				if(cItems.getAttr0() != 0){
					//起始时间
					System.arraycopy(ParseUtil.str2Bcd(cItems.getTimeS()), 0, bytes, dstPos, 6);
					dstPos += 6;

					//起始时间
					System.arraycopy(ParseUtil.str2Bcd(cItems.getTimeE()), 0, bytes, dstPos, 6);
					dstPos += 6;
				}


				if(cItems.getAttr1()!=0){
					//最高速度
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cItems.getSpeedLimit(),2)), 0, bytes, dstPos, 2);
					dstPos += 2;
					//超速持续时间
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(cItems.getSpeedTime(),1)), 0, bytes, dstPos, 1);
					dstPos += 1;
				}


			}

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return bytes;

	}


	/**
	 * 获取设置矩形区域二进制
	 * @param list
	 * @param len
	 * @return
	 */
	public static byte[] getRectangleAreaByte(List<RectangleAreaItems> list,int length){

		byte [] bytes = new byte[length];
		try {
			int dstPos = 0;
			for (RectangleAreaItems rItems : list) {

				//区域ID
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(rItems.getAreaId(),4)), 0, bytes, dstPos, 4);
				dstPos += 4;
				byte[] attrbyte = new byte[2];
				//区域属性
				StringBuffer attrBuffer = new StringBuffer();
				attrBuffer.append(rItems.getAttr7());
				attrBuffer.append(rItems.getAttr6());
				attrBuffer.append(rItems.getAttr5());
				attrBuffer.append(rItems.getAttr4());
				attrBuffer.append(rItems.getAttr3());
				attrBuffer.append(rItems.getAttr2());
				attrBuffer.append(rItems.getAttr1());
				attrBuffer.append(rItems.getAttr0());
				attrbyte[0] = ParseUtil.bitToByte("00000000");
				attrbyte[1] = ParseUtil.bitToByte(attrBuffer.toString());

				//区域属性
				System.arraycopy(attrbyte, 0, bytes, dstPos, 2);
				dstPos += 2;

				//左上点纬度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(rItems.getLatlt(),4)), 0, bytes, dstPos, 4);
				dstPos += 4;

				//左上点经度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(rItems.getLnglt(),4)), 0, bytes, dstPos, 4);
				dstPos += 4;

				//右下点纬度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(rItems.getLatrb(),4)), 0, bytes, dstPos, 4);
				dstPos += 4;

				//右下点经度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(rItems.getLngrb(),4)), 0, bytes, dstPos, 4);
				dstPos += 4;

				if(rItems.getAttr0() != 0){
					//起始时间
					System.arraycopy(ParseUtil.str2Bcd(rItems.getTimeS()), 0, bytes, dstPos, 6);
					dstPos += 6;

					//起始时间
					System.arraycopy(ParseUtil.str2Bcd(rItems.getTimeE()), 0, bytes, dstPos, 6);
					dstPos += 6;
				}

				if(rItems.getAttr1() != 0){
					//最高速度
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(rItems.getSpeedLimit(),2)), 0, bytes, dstPos, 2);
					dstPos += 2;

					//超速持续时间
					System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(rItems.getSpeedTime(),1)), 0, bytes, dstPos, 1);
					dstPos += 1;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return bytes;

	}


	/**
	 * 获取设置电话本二进制
	 * @param list
	 * @param len
	 * @return
	 */
	public static byte[] getPhoneBookByte(List<PhoneBookItems> list,int length){

		byte [] bytes = new byte[length];
		try {
			int dstPos = 0;
			for (PhoneBookItems pItems : list) {
				//电话号码
				byte[] telbyte = ParseUtil.stringToByte(pItems.getTel());
				//联系人
				byte[] contactbyte = ParseUtil.stringToByte(pItems.getContact());

				//标志
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(pItems.getTag(),1)), 0, bytes, dstPos, 1);
				dstPos += 1;
				//号码长度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(telbyte.length,1)), 0, bytes, dstPos, 1);
				dstPos += 1;
				//电话号码
				System.arraycopy(telbyte, 0, bytes, dstPos, telbyte.length);
				dstPos += telbyte.length;

				//联系人长度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(contactbyte.length,1)), 0, bytes, dstPos, 1);
				dstPos += 1;
				//联系人
				System.arraycopy(contactbyte, 0, bytes, dstPos, contactbyte.length);
				dstPos += contactbyte.length;
			}

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return bytes;

	}

	/**
	 * 获取设置电话本
	 * @param list
	 * @return
	 */
	public static int getPhoneBookLen(List<PhoneBookItems> list){
		int len = 0;
		try {
			for (PhoneBookItems pItems : list) {
				//电话号码
				byte[] telbyte = ParseUtil.stringToByte(pItems.getTel());
				len +=  telbyte.length;

				//联系人
				byte[] contactbyte = ParseUtil.stringToByte(pItems.getContact());
				len +=  contactbyte.length;
			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}
		return len;
	}

	/**
	 * 圆形区域长度
	 * @param list
	 * @return
	 */
	public static int getCircleAreaLen(List<CircleAreaItems> list){

		int len = 18;
		try {
			for (CircleAreaItems pItems : list) {
				if(pItems.getAttr0() != 0){
					len += 12;
				}

				if(pItems.getAttr1() != 0){
					len += 3;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}
		return len;
	}

	/**
	 * 矩形区域长度
	 * @param list
	 * @return
	 */
	public static int getRectangleAreaLen(List<RectangleAreaItems> list){

		int len = 22;
		try {
			for (RectangleAreaItems pItems : list) {
				if(pItems.getAttr0() != 0){
					len += 12;
				}

				if(pItems.getAttr1() != 0){
					len += 3;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}
		return len;
	}
	
	
	/**
	 * 路线长度
	 * @param list
	 * @return
	 */
	public static int getLineInfoLen(List<LineInfoItems> list){

		int len = 16;
		try {
			for (LineInfoItems pItems : list) {
				if(pItems.getLattr0() != 0){
					len += 4;
				}

				if(pItems.getLattr1() != 0){
					len += 3;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}
		return len;
	}



	/**
	 * 获取信息点播菜单长度
	 * @param list
	 * @return
	 */
	public static int getInfoDemandMenuLen(List<InfoDemandMenuItems> list){
		int len = 0;
		try {
			for (InfoDemandMenuItems idmItems : list) {
				byte[] contentbyte = ParseUtil.stringToByte(idmItems.getContent());
				len +=  contentbyte.length;
			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}
		return len;
	}


	/**
	 * 获取信息点播菜单二进制
	 * @param list
	 * @param len
	 * @return
	 */
	public static byte[] getInfoDemandMenuByte(List<InfoDemandMenuItems> list,int length){

		byte [] bytes = new byte[length];
		try {
			int dstPos = 0;
			for (InfoDemandMenuItems idmItems : list) {
				byte[] contentbyte = ParseUtil.stringToByte(idmItems.getContent());
				//信息类型
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(idmItems.getType(),1)), 0, bytes, dstPos, 1);
				dstPos += 1;
				//信息名称长度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(contentbyte.length,2)), 0, bytes, dstPos, 2);
				dstPos += 2;
				//信息名称
				System.arraycopy(contentbyte, 0, bytes, dstPos, contentbyte.length);
				dstPos += contentbyte.length;
			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return bytes;

	}

	/**
	 * 获取事件项列表 长度
	 * @return
	 */
	public static int getEventItemLen(List<EventTerm> list){
		int len = 0;
		try {
			if(list != null){
				for (EventTerm eventTerm : list) {
					byte[] contentbyte = ParseUtil.stringToByte(eventTerm.getContent());
					len +=  contentbyte.length;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}
		return len;
	}

	/**
	 * 获取事件参数二进制
	 * @param list
	 * @param len
	 * @return
	 */
	public static byte[] getEventSetByte(List<EventTerm> list,int length){

		byte [] bytes = new byte[length];
		try {
			int dstPos = 0;
			for (EventTerm eventTerm : list) {
				byte[] contentbyte = ParseUtil.stringToByte(eventTerm.getContent());
				//事件ID
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(eventTerm.getId(),1)), 0, bytes, dstPos, 1);
				dstPos += 1;
				//事件内容长度
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(contentbyte.length,1)), 0, bytes, dstPos, 1);
				dstPos += 1;
				//事件内容
				System.arraycopy(contentbyte, 0, bytes, dstPos, contentbyte.length);
				dstPos += contentbyte.length;
			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return bytes;

	}

	/**
	 * 获取终端控制
	 * @param data
	 * @param cmd
	 * @return
	 */
	public static String getTerminalControl(String  data,int cmd){

		String [] dataList=data.split(";");
		StringBuffer valueBuffer = new StringBuffer();
		try {

			if(cmd == 1){
				//URL 地址
				valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.stringToByte(dataList[0])));
				valueBuffer.append("3B");
				//拨号点名称
				valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.stringToByte(dataList[1])));
				valueBuffer.append("3B");
				//拨号用户名
				valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.stringToByte(dataList[2])));
				valueBuffer.append("3B");
				//拨号密码
				valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.stringToByte(dataList[3])));
				valueBuffer.append("3B");
				//地址
				valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.stringToByte(dataList[4])));
				valueBuffer.append("3B");
				//TCP 端口
				valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.intToBytes(Integer.parseInt(dataList[5]), 2)));
				valueBuffer.append("3B");
				//UDP 端口
				valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.intToBytes(Integer.parseInt(dataList[6]), 2)));
				valueBuffer.append("3B");
				//制造商ID
				valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.intToBytes(Integer.parseInt(dataList[7]), 5)));
				valueBuffer.append("3B");
				//硬件版本
				valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.stringToByte(dataList[8])));
				valueBuffer.append("3B");
				//固件版本
				valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.stringToByte(dataList[9])));
				valueBuffer.append("3B");
				//连接到指定服务器时限
				valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.intToBytes(Integer.parseInt(dataList[10]), 2)));

			}else if(cmd == 2){

				if(Integer.parseInt(dataList[0]) == 1){
					valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.intToBytes(Integer.parseInt(dataList[0]), 1)));
				}else{
					//连接控制
					valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.intToBytes(Integer.parseInt(dataList[0]), 1)));
					valueBuffer.append("3B");
					//监管平台鉴权码
					valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.stringToByte(dataList[1])));
					valueBuffer.append("3B");
					//拨号点名称;
					valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.stringToByte(dataList[2])));
					valueBuffer.append("3B");
					//拨号用户名
					valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.stringToByte(dataList[3])));
					valueBuffer.append("3B");
					//拨号密码
					valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.stringToByte(dataList[4])));
					valueBuffer.append("3B");
					//地址
					valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.stringToByte(dataList[5])));
					valueBuffer.append("3B");
					//TCP 端口
					valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.intToBytes(Integer.parseInt(dataList[6]), 2)));
					valueBuffer.append("3B");
					//UDP 端口
					valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.intToBytes(Integer.parseInt(dataList[7]), 2)));
					valueBuffer.append("3B");
					//连接到指定服务器时限
					valueBuffer.append(ParseUtil.parseByteToHexStr(ParseUtil.intToBytes(Integer.parseInt(dataList[8]), 2)));
				}

			}else{
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}

		return valueBuffer.toString();
	}

	/**
	 * 获取终端参数应答json
	 * @param terminalParameter
	 * @return
	 */
	public static StringBuffer getTerminalParJson(TerminalParameter terminalParameter){
		StringBuffer buffer = new StringBuffer();

		try {
			List<ParameterInfo> list = terminalParameter.getItems();
			int i = 1;
			for (ParameterInfo parameterInfo : list) {

				try {
					int id = parameterInfo.getId();

					int size = parameterInfo.getSize();
					byte[] valuebyte = ParseUtil.parseHexStrToByte(parameterInfo.getValue()); 
					buffer.append("{");
					buffer.append(StringUtils.jsonChar("id",id, 0));
					buffer.append(StringUtils.jsonChar("size",size, 0));
					int flag = 3;
					int intvalue = 0;
					String srtvalue = null;

					switch (id) {
					//终端心跳发送间隔，单位为秒（s）
					case 0x0001:
						flag = 1;
						break;
						//TCP 消息应答超时时间，单位为秒（s）
					case 0x0002:
						flag = 1;
						break;
						//TCP 消息重传次数
					case 0x0003:
						flag = 1;
						break;
						//UDP 消息应答超时时间，单位为秒（s）
					case 0x0004:
						flag = 1;
						break;
						//UDP 消息重传次数
					case 0x0005:
						flag = 1;
						break;
						//SMS 消息应答超时时间，单位为秒（s）
					case 0x0006:
						flag = 1;
						break;
						//SMS 消息重传次数
					case 0x0007:  //0x0008-0x000F 保留
						flag = 1;
						break;
						//主服务器APN，无线通信拨号访问点。若网络制式为CDMA，则该处为PPP 拨号号码
					case 0x0010:
						flag = 2;
						break;
						//主服务器无线通信拨号用户名
					case 0x0011:
						flag = 2;
						break;
						//主服务器无线通信拨号密码				
					case 0x0012:
						flag = 2;
						break;
						//主服务器地址,IP 或域名
					case 0x0013:
						flag = 2;
						break;
						//备份服务器APN，无线通信拨号访问点
					case 0x0014:
						flag = 2;
						break;
						//备份服务器无线通信拨号用户名
					case 0x0015:
						flag = 2;
						break;
						//备份服务器无线通信拨号密码
					case 0x0016:
						flag = 2;
						break;
						//备份服务器地址,IP 或域名
					case 0x0017:
						flag = 2;
						break;
						//服务器TCP 端口
					case 0x0018:
						flag = 1;
						break;
						//服务器UDP 端口
					case 0x0019:
						flag = 1;
						break;
						//道路运输证IC 卡认证主服务器IP 地址或域名
					case 0x001A:
						flag = 2;
						break;
						//道路运输证IC 卡认证主服务器TCP 端口
					case 0x001B:
						flag = 1;
						break;
						//道路运输证IC 卡认证主服务器UDP 端口
					case 0x001C:
						flag = 1;
						break;
						//道路运输证IC 卡认证备份服务器IP 地址或域名，端口同主服务器
					case 0x001D: //0x001E-0x001F 保留
						flag = 2;
						break;
						//位置汇报策略，0：定时汇报；1：定距汇报；2：定时和定距汇报
					case 0x0020:
						flag = 1;
						break;
						//位置汇报方案，0：根据ACC 状态； 1：根据登录状态和ACC 状态，先判断登录状态，若登录再根据ACC 状态
					case 0x0021:
						flag = 1;
						break;
						//驾驶员未登录汇报时间间隔，单位为秒（s），>0
					case 0x0022: //0x0023-0x0026 DWORD 保留
						flag = 1;
						break;
						//休眠时汇报时间间隔，单位为秒（s），>0
					case 0x0027:
						flag = 1;
						break;
						//紧急报警时汇报时间间隔，单位为秒（s），>0
					case 0x0028:
						flag = 1;
						break;
						//缺省时间汇报间隔，单位为秒（s），>0
					case 0x0029:  //0x002A-0x002B DWORD 保留
						flag = 1;
						break;
						//缺省距离汇报间隔，单位为米（m），>0
					case 0x002C:
						flag = 1;
						break;
						//驾驶员未登录汇报距离间隔，单位为米（m），>0
					case 0x002D:
						flag = 1;
						break;
						//休眠时汇报距离间隔，单位为米（m），>0
					case 0x002E:
						flag = 1;
						break;
						//紧急报警时汇报距离间隔，单位为米（m），>0
					case 0x002F:
						flag = 1;
						break;
						//拐点补传角度，<180
					case 0x0030:
						flag = 1;
						break;
						//电子围栏半径（非法位移阈值），单位为米
					case 0x0031:  //0x00032-0x003F 保留
						flag = 1;
						break;
						//监控平台电话号码
					case 0x0040:
						flag = 2;
						break;
						//复位电话号码，可采用此电话号码拨打终端电话让终端复位
					case 0x0041:
						flag = 2;
						break;
						//恢复出厂设置电话号码，可采用此电话号码拨打终端电话让终端恢复出厂设置
					case 0x0042:
						flag = 2;
						break;
						//监控平台SMS 电话号码
					case 0x0043:
						flag = 2;
						break;
						//接收终端SMS 文本报警号码
					case 0x0044:
						flag = 2;
						break;
						//终端电话接听策略，0：自动接听；1：ACC ON 时自动接听，OFF 时手动接听
					case 0x0045:
						flag = 1;
						break;
						//每次最长通话时间，单位为秒（s），0 为不允许通话，0xFFFFFFFF 为不限制
					case 0x0046:
						flag = 1;
						break;
						//当月最长通话时间，单位为秒（s），0 为不允许通话，0xFFFFFFFF 为不限制
					case 0x0047:
						flag = 1;
						break;
						//监听电话号码
					case 0x0048:
						flag = 2;
						break;
						//监管平台特权短信号码
					case 0x0049:  //0x004A-0x004F 保留
						flag = 2;
						break;
						//报警屏蔽字
					case 0x0050:
						flag = 1;
						break;
						//报警发送文本SMS 开关
					case 0x0051:
						flag = 1;
						break;
						//报警拍摄开关
					case 0x0052:
						flag = 1;
						break;
						//报警拍摄存储标志
					case 0x0053:
						flag = 1;
						break;
						//关键标志，与位置信息汇报消息中的报警标志相对应，相应位为1 则对相应报警为关键报警
					case 0x0054:
						flag = 1;
						break;
						//最高速度，单位为公里每小时（km/h）
					case 0x0055:
						flag = 1;
						break;
						//超速持续时间，单位为秒（s）
					case 0x0056:
						flag = 1;
						break;
						//连续驾驶时间门限，单位为秒（s）
					case 0x0057:
						flag = 1;
						break;
						//当天累计驾驶时间门限，单位为秒（s）
					case 0x0058:
						flag = 1;
						break;
						//最小休息时间，单位为秒（s）
					case 0x0059:
						flag = 1;
						break;
						//最长停车时间，单位为秒（s）
					case 0x005A:
						flag = 1;
						break;
						//超速报警预警差值，单位为1/10Km/h
					case 0x005B:
						flag = 1;
						break;
						//疲劳驾驶预警差值，单位为秒（s），>0
					case 0x005C:
						flag = 1;
						break;
						//碰撞报警参数设置：b7-b0： 碰撞时间，单位4ms；b15-b8：碰撞加速度，单位0.1g，设置范围在：0-79 之间，默认为10。
					case 0x005D:
						flag = 1;
						break;
						//侧翻报警参数设置：侧翻角度，单位1 度，默认为30 度。
					case 0x005E: //0x005F-0x0063 保留
						flag = 1;
						break;
						//定时拍照控制
					case 0x0064:
						flag = 1;
						break;
						//定距拍照控制
					case 0x0065: //0x0066-0x006F 保留
						flag = 1;
						break;
						//图像/视频质量，1-10，1 最好
					case 0x0070:
						flag = 1;
						break;
						//亮度，0-255
					case 0x0071:
						flag = 1;
						break;
						//对比度，0-127
					case 0x0072:
						flag = 1;
						break;
						//饱和度，0-127
					case 0x0073:
						flag = 1;
						break;
						//色度，0-255
					case 0x0074: //0x0075-0x007F
						flag = 1;
						break;
						//车辆里程表读数，1/10km
					case 0x0080:
						flag = 1;
						break;
						//车辆所在的省域ID
					case 0x0081:
						flag = 1;
						break;
						//车辆所在的市域ID
					case 0x0082:
						flag = 1;
						break;
						//公安交通管理部门颁发的机动车号牌
					case 0x0083:
						flag = 2;
						break;
						//车牌颜色，按照JT/T415-2006 的5.4.12
					case 0x0084:
						flag = 1;
						break;
						//GNSS 定位模式
					case 0x0090:
						flag = 1;
						break;
						//GNSS 波特率
					case 0x0091:
						flag = 1;
						break;
						//GNSS 模块详细定位数据输出频
					case 0x0092:
						flag = 1;
						break;
						//GNSS 模块详细定位数据采集频率，单位为秒，默认为1。
					case 0x0093:
						flag = 1;
						break;
						//GNSS 模块详细定位数据上传方
					case 0x0094:
						flag = 1;
						break;
						//GNSS 模块详细定位数据上传设置
					case 0x0095:
						flag = 1;
						break;
						//CAN 总线通道1 采集时间间隔(ms)，0 表示不采集
					case 0x0100:
						flag = 1;
						break;
						//CAN 总线通道1 上传时间间隔(s)，0 表示不上传
					case 0x0101:
						flag = 1;
						break;
						//CAN 总线通道2 采集时间间隔(ms)，0 表示不采集
					case 0x0102:
						flag = 1;
						break;
						//CAN 总线通道2 上传时间间隔(s)，0 表示不上传
					case 0x0103:
						flag = 1;
						break;
						//CAN 总线ID 单独采集设置
					case 0x0110: //0x0111-0x01FF BYTE[8] 用于其他CAN 总线ID 单独采集设置 0xF000-0xFFFF 用户自定义
						flag = 1;
						break;

					default:
						break;
					}


					if(flag == 1){
						String valueHex = ParseUtil.parseByteToHexStr(valuebyte);
						if("FFFFFFFF".equals(valueHex)){
							intvalue = 0;
						}else{
							intvalue = Integer.parseInt(ParseUtil.parseByteToHexStr(valuebyte), 16);
						}
						buffer.append(StringUtils.jsonChar("value",intvalue, 1));
					}else if(flag == 2){
						if(valuebyte == null){
							srtvalue = "";
						}else{
							srtvalue = ParseUtil.byteToString(valuebyte, 0, valuebyte.length);
						}

						buffer.append(StringUtils.jsonChar("value",StringUtils.charStr(srtvalue), 1));

					}else{
						buffer.append(StringUtils.jsonChar("value",StringUtils.charStr(""), 1));
						logger.info("无效消息ID："+id);
					}
					if(i == list.size()){
						buffer.append("}");
					}else{
						buffer.append("},");
					}

					i++;
				} catch (Exception e) {
					e.printStackTrace();ExceptionUtil.getInfo(e);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}
		return buffer;
	}

	/**
	 * 获取终端参数二进制
	 * @param list
	 * @param len
	 * @return
	 */
	public static byte[] getTerminalParByte(List<ParameterInfo> list,int length){

		byte [] bytes = new byte[length];
		try {
			int dstPos = 0;
			for (ParameterInfo parameterInfo : list) {
				byte [] valuebyte = (parameterInfo.getValue()+"\0").getBytes("GBK");

				int id = parameterInfo.getId();

				byte [] vbyte = null;
				int len = 0;
				switch (id) {
				//终端心跳发送间隔，单位为秒（s）
				case 0x0001:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//TCP 消息应答超时时间，单位为秒（s）
				case 0x0002:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//TCP 消息重传次数
				case 0x0003:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//UDP 消息应答超时时间，单位为秒（s）
				case 0x0004:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//UDP 消息重传次数
				case 0x0005:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//SMS 消息应答超时时间，单位为秒（s）
				case 0x0006:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//SMS 消息重传次数
				case 0x0007:  //0x0008-0x000F 保留
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//主服务器APN，无线通信拨号访问点。若网络制式为CDMA，则该处为PPP 拨号号码
				case 0x0010:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//主服务器无线通信拨号用户名
				case 0x0011:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//主服务器无线通信拨号密码				
				case 0x0012:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//主服务器地址,IP 或域名
				case 0x0013:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//备份服务器APN，无线通信拨号访问点
				case 0x0014:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//备份服务器无线通信拨号用户名
				case 0x0015:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//备份服务器无线通信拨号密码
				case 0x0016:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//备份服务器地址,IP 或域名
				case 0x0017:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//服务器TCP 端口
				case 0x0018:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//服务器UDP 端口
				case 0x0019:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//道路运输证IC 卡认证主服务器IP 地址或域名
				case 0x001A:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//道路运输证IC 卡认证主服务器TCP 端口
				case 0x001B:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//道路运输证IC 卡认证主服务器UDP 端口
				case 0x001C:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//道路运输证IC 卡认证备份服务器IP 地址或域名，端口同主服务器
				case 0x001D: //0x001E-0x001F 保留
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//位置汇报策略，0：定时汇报；1：定距汇报；2：定时和定距汇报
				case 0x0020:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//位置汇报方案，0：根据ACC 状态； 1：根据登录状态和ACC 状态，先判断登录状态，若登录再根据ACC 状态
				case 0x0021:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//驾驶员未登录汇报时间间隔，单位为秒（s），>0
				case 0x0022: //0x0023-0x0026 DWORD 保留
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//休眠时汇报时间间隔，单位为秒（s），>0
				case 0x0027:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//紧急报警时汇报时间间隔，单位为秒（s），>0
				case 0x0028:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//缺省时间汇报间隔，单位为秒（s），>0
				case 0x0029:  //0x002A-0x002B DWORD 保留
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//缺省距离汇报间隔，单位为米（m），>0
				case 0x002C:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//驾驶员未登录汇报距离间隔，单位为米（m），>0
				case 0x002D:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//休眠时汇报距离间隔，单位为米（m），>0
				case 0x002E:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//紧急报警时汇报距离间隔，单位为米（m），>0
				case 0x002F:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//拐点补传角度，<180
				case 0x0030:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//电子围栏半径（非法位移阈值），单位为米
				case 0x0031:  //0x00032-0x003F 保留
					len = 2;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					
				case 0x0032:  //升级服务器地址，IP或域名
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					
				case 0x0033:  //升级服务器TCP端口
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					
				case 0x0034:  //总部电话号码
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					
				case 0x0035:  //呼叫中心电话
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					
				case 0x0036:  //1 系统恢复出厂设置 2 不恢复
					len = 1;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					
				case 0x0037:  //删除SD卡中指令位置文件指令。路径
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					
					//监控平台电话号码
				case 0x0040:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//复位电话号码，可采用此电话号码拨打终端电话让终端复位
				case 0x0041:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//恢复出厂设置电话号码，可采用此电话号码拨打终端电话让终端恢复出厂设置
				case 0x0042:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//监控平台SMS 电话号码
				case 0x0043:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//接收终端SMS 文本报警号码
				case 0x0044:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//终端电话接听策略，0：自动接听；1：ACC ON 时自动接听，OFF 时手动接听
				case 0x0045:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//每次最长通话时间，单位为秒（s），0 为不允许通话，0xFFFFFFFF 为不限制
				case 0x0046:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//当月最长通话时间，单位为秒（s），0 为不允许通话，0xFFFFFFFF 为不限制
				case 0x0047:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//监听电话号码
				case 0x0048:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//监管平台特权短信号码
				case 0x0049:  //0x004A-0x004F 保留
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//报警屏蔽字
				case 0x0050:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//报警发送文本SMS 开关
				case 0x0051:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//报警拍摄开关
				case 0x0052:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//报警拍摄存储标志
				case 0x0053:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//关键标志，与位置信息汇报消息中的报警标志相对应，相应位为1 则对相应报警为关键报警
				case 0x0054:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//最高速度，单位为公里每小时（km/h）
				case 0x0055:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//超速持续时间，单位为秒（s）
				case 0x0056:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//连续驾驶时间门限，单位为秒（s）
				case 0x0057:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//当天累计驾驶时间门限，单位为秒（s）
				case 0x0058:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//最小休息时间，单位为秒（s）
				case 0x0059:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//最长停车时间，单位为秒（s）
				case 0x005A:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//超速报警预警差值，单位为1/10Km/h
				case 0x005B:
					len = 2;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//疲劳驾驶预警差值，单位为秒（s），>0
				case 0x005C:
					len = 2;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//碰撞报警参数设置：b7-b0： 碰撞时间，单位4ms；b15-b8：碰撞加速度，单位0.1g，设置范围在：0-79 之间，默认为10。
				case 0x005D:
					len = 2;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//侧翻报警参数设置：侧翻角度，单位1 度，默认为30 度。
				case 0x005E: //0x005F-0x0063 保留
					len = 2;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//定时拍照控制
				case 0x0064:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//定距拍照控制
				case 0x0065: //0x0066-0x006F 保留
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//图像/视频质量，1-10，1 最好
				case 0x0070:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//亮度，0-255
				case 0x0071:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//对比度，0-127
				case 0x0072:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//饱和度，0-127
				case 0x0073:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//色度，0-255
				case 0x0074: //0x0075-0x007F
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//车辆里程表读数，1/10km
				case 0x0080:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//车辆所在的省域ID
				case 0x0081:
					len = 2;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//车辆所在的市域ID
				case 0x0082:
					len = 2;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//公安交通管理部门颁发的机动车号牌
				case 0x0083:
					len = valuebyte.length;
					vbyte = valuebyte;
					break;
					//车牌颜色，按照JT/T415-2006 的5.4.12
				case 0x0084:
					len = 1;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//GNSS 定位模式
				case 0x0090:
					len = 1;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//GNSS 波特率
				case 0x0091:
					len = 1;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//GNSS 模块详细定位数据输出频
				case 0x0092:
					len = 1;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//GNSS 模块详细定位数据采集频率，单位为秒，默认为1。
				case 0x0093:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//GNSS 模块详细定位数据上传方
				case 0x0094:
					len = 1;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//GNSS 模块详细定位数据上传设置
				case 0x0095:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//CAN 总线通道1 采集时间间隔(ms)，0 表示不采集
				case 0x0100:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//CAN 总线通道1 上传时间间隔(s)，0 表示不上传
				case 0x0101:
					len = 2;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//CAN 总线通道2 采集时间间隔(ms)，0 表示不采集
				case 0x0102:
					len = 4;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//CAN 总线通道2 上传时间间隔(s)，0 表示不上传
				case 0x0103:
					len = 2;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;
					//CAN 总线ID 单独采集设置
				case 0x0110: //0x0111-0x01FF BYTE[8] 用于其他CAN 总线ID 单独采集设置 0xF000-0xFFFF 用户自定义
					len = 8;
					vbyte = ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(parameterInfo.getValue()),len));
					break;

				default:
					break;
				}

				//组装数据包
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(id,4)), 0, bytes, dstPos, 4);
				dstPos += 4;
				System.arraycopy(ParseUtil.sortToByte(ParseUtil.intToBytes(len,1)), 0, bytes, dstPos, 1);
				dstPos +=1;
				System.arraycopy(vbyte, 0, bytes, dstPos, len);
				dstPos += len;


			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return bytes;

	}

	/**
	 * 获取终端参数长度
	 * @return
	 */
	public static int getTerminalParLen(List<ParameterInfo> list){

		int len = 0;
		try {
			for (ParameterInfo parameterInfo : list) {
				int id = parameterInfo.getId();
				byte [] valuebyte = (parameterInfo.getValue()+"\0").getBytes("GBK"); 
				switch (id) {
				case 0x0001:
					len += 4;
					break;

				case 0x0002:
					len += 4;
					break;
				case 0x0003:
					len += 4;
					break;
				case 0x0004:
					len += 4;
					break;
				case 0x0005:
					len += 4;
					break;
				case 0x0006:
					len += 4;
					break;
				case 0x0007:  //0x0008-0x000F 保留
					len += 4;
					break;
				case 0x0010:
					len +=valuebyte.length;
					break;
				case 0x0011:
					len +=valuebyte.length;
					break;
				case 0x0012:
					len +=valuebyte.length;
					break;
				case 0x0013:
					len +=valuebyte.length;
					break;
				case 0x0014:
					len +=valuebyte.length;
					break;
				case 0x0015:
					len +=valuebyte.length;
					break;
				case 0x0016:
					len +=valuebyte.length;
					break;
				case 0x0017:
					len +=valuebyte.length;
					break;
				case 0x0018:
					len += 4;
					break;
				case 0x0019:
					len += 4;
					break;
				case 0x001A:
					len +=valuebyte.length;
					break;
				case 0x001B:
					len += 4;
					break;
				case 0x001C:
					len += 4;
					break;
				case 0x001D: //0x001E-0x001F 保留
					len +=valuebyte.length;
					break;
				case 0x0020:
					len += 4;
					break;
				case 0x0021:
					len += 4;
					break;
				case 0x0022: //0x0023-0x0026 DWORD 保留
					len += 4;
					break;
				case 0x0027:
					len += 4;
					break;
				case 0x0028:
					len += 4;
					break;
				case 0x0029:  //0x002A-0x002B DWORD 保留
					len += 4;
					break;
				case 0x002C:
					len += 4;
					break;
				case 0x002D:
					len += 4;
					break;
				case 0x002E:
					len += 4;
					break;
				case 0x002F:
					len += 4;
					break;
				case 0x0030:
					len += 4;
					break;
				case 0x0031:  //0x00032-0x003F 保留
					len += 2;
					break;
					
				//厦门项目自定义
				case 0x0032:  //升级服务器地址，IP或域名
					len +=valuebyte.length;
					break;	
				case 0x0033:  //升级服务器TCP端口
					len +=4;
					break;
				case 0x0034:  //总部电话号码
					len +=valuebyte.length;
					break;
				case 0x0035:  //呼叫中心电话
					len +=valuebyte.length;
					break;
				case 0x0036:  //1 系统恢复出厂设置 2 不恢复
					len +=1;
					break;
				case 0x0037:  //删除SD卡中指令位置文件指令。路径
					len +=valuebyte.length;
					break;
					
				case 0x0040:
					len +=valuebyte.length;
					break;
				case 0x0041:
					len +=valuebyte.length;
					break;
				case 0x0042:
					len +=valuebyte.length;
					break;

				case 0x0043:
					len +=valuebyte.length;
					break;
				case 0x0044:
					len +=valuebyte.length;
					break;
				case 0x0045:
					len += 4;
					break;
				case 0x0046:
					len += 4;
					break;
				case 0x0047:
					len += 4;
					break;
				case 0x0048:
					len +=valuebyte.length;
					break;
				case 0x0049:  //0x004A-0x004F 保留
					len +=valuebyte.length;
					break;
				case 0x0050:
					len += 4;
					break;
				case 0x0051:
					len += 4;
					break;
				case 0x0052:
					len += 4;
					break;
				case 0x0053:
					len += 4;
					break;
				case 0x0054:
					len += 4;
					break;
				case 0x0055:
					len += 4;
					break;
				case 0x0056:
					len += 4;
					break;
				case 0x0057:
					len += 4;
					break;
				case 0x0058:
					len += 4;
					break;
				case 0x0059:
					len += 4;
					break;
				case 0x005A:
					len += 4;
					break;
				case 0x005B:
					len += 2;
					break;
				case 0x005C:
					len += 2;
					break;
				case 0x005D:
					len += 2;
					break;
				case 0x005E: //0x005F-0x0063 保留
					len += 2;
					break;
				case 0x0064:
					len += 4;
					break;
				case 0x0065: //0x0066-0x006F 保留
					len += 4;
					break;
				case 0x0070:
					len += 4;
					break;
				case 0x0071:
					len += 4;
					break;
				case 0x0072:
					len += 4;
					break;
				case 0x0073:
					len += 4;
					break;
				case 0x0074: //0x0075-0x007F
					len += 4;
					break;
				case 0x0080:
					len += 4;
					break;
				case 0x0081:
					len += 2;
					break;
				case 0x0082:
					len += 2;
					break;
				case 0x0083:
					len +=valuebyte.length;
					break;
				case 0x0084:
					len += 1;
					break;

				case 0x0090:
					len += 1;
					break;
				case 0x0091:
					len += 1;
					break;
				case 0x0092:
					len += 1;
					break;
				case 0x0093:
					len += 4;
					break;
				case 0x0094:
					len += 1;
					break;
				case 0x0095:
					len += 4;
					break;
				case 0x0100:
					len += 4;
					break;
				case 0x0101:
					len += 2;
					break;
				case 0x0102:
					len += 4;
					break;

				case 0x0103:
					len += 2;
					break;
				case 0x0110: //0x0111-0x01FF BYTE[8] 用于其他CAN 总线ID 单独采集设置 0xF000-0xFFFF 用户自定义
					len += 8;
					break;

				default:
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
		}

		return len;
	}

}
