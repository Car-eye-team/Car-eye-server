/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.utlis;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.careye.dscomm.encoder.BbEncoderUtil;
import com.careye.dscomm.threadpool.ThreadPoolManager;
import com.careye.dscomm.threadpool.ThreadPoolTask;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：StickPackageUtil    
 * 类描述：粘包处理工具类    
 * 创建人：zr    
 * 创建时间：2015-5-27 下午03:58:31    
 * 修改人：zr    
 * 修改时间：2015-5-27 下午03:58:31    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class StickPackageUtil20160513 {/*

	private final static Logger logger = Logger.getLogger(StickPackageUtil20160513.class);

	public static Map<IoSession, StringBuffer> tcpmap = new HashMap<IoSession, StringBuffer>();

	public static Map<IoSession, String> cachemap = new HashMap<IoSession, String>();

	//public static Object anObject = new Object();

	*//**
	 * 粘包处理
	 * @param session
	 * @param msg
	 *//*
	public static void stickPackage(IoSession session,String msg,int protocoltype){

		try {

			//转换成大写
			String data = msg.toUpperCase();
			switch (protocoltype) {
			//7E开头7E结尾 格式协议
			case 1:
				handle7E(session,data);
				break;

				//2929开头0D结尾格式协议
			case 2:
				handle29290D(session,data);
				break;

				//FF开头0D结尾格式协议
			case 3:
				handleFF0D(session, data);
				break;

				//2A48513230开头23结尾格式协议(赢时通)
			case 4:
				handleHQ20(session, data);
				break;

				//40开头0D结尾格式协议(中导)
			case 5:
				handle400D(session, data);
				break;

				//7878开头0D0A结尾格式协议(深圳市格林维信息科技有限公司GPS定位器)
			case 6:
				handle78780D0A(session, data);
				break;

			case 7:  //AA55开头的格式协议(蓝度商用车协议)
				handle55AA(session, data);
				break;

			case 8:  //7A开头的格式协议
				handle7A(session, data);
				break;

			case 9:  //5A开头的格式协议
				handle5A(session, data);
				break;

			case 10:  //6A开头的格式协议
				handle6A(session, data);
				break;

			case 11:  //28开头、29结尾的格式协议
				handle2829(session, data);
				break;

			case 12:  //8A开头的格式多森+海联+速锐得OBD协议
				handle8A(session, data);
				break;

			case 13:  //2454开头、0D0A结尾的格式协议
				handle240D0A(session, data);
				break;

				//4040开头0D0A结尾格式深圳市航天无线通信技术有限公司213GD协议
			case 14:
				handle400A(session, data);
				break;

				//深圳市城市漫步科技有限公司
			case 15:
				handle540D0A(session, data);
				break;

			case 16:  //9A开头的格式多森私家车协议
				handle9A(session, data);
				break;

			case 17:  //5948开头的格式东风日产协议
				handle59480D0A(session, data);
				break;

			case 18:  //5B开头5D结尾赛格车圣空中传输通信协议
				handle5B5D(session, data);
				break;


			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("粘包异常"+e);
		}

	}

	*//**
	 * 处理7E开头7E结尾 格式协议 粘包
	 * @param session
	 * @param data
	 *//*
	public static void handle7E(IoSession session,String data){

		try {
			StringBuffer tcp7EBuffer = tcpmap.get(session);

			if(tcp7EBuffer == null){
				tcp7EBuffer = new StringBuffer();
			}

			String cacheStr = cachemap.get(session);
			if(cacheStr != null){
				String outmsg = String.format("%s%s%s",tcp7EBuffer.toString(),data,cacheStr);
				boolean reflag = BbEncoderUtil.checkCode(ParseUtil.parseHexStrToByte(outmsg));
				if(reflag){
					sendBusinessProcessing(session,outmsg);
					tcp7EBuffer = new StringBuffer();
					tcpmap.put(session, tcp7EBuffer);
					cachemap.put(session, null);
					return;
				}
			}


			if(data.startsWith("7E")&&data.endsWith("7E")&&data.indexOf("7E7E")<=0){
				if(data.equals("7E")){
					tcp7EBuffer.append("7E");
				}else{
					sendBusinessProcessing(session,data);
					return;
				}
			}else{
				String [] dataAry = data.split("7E7E");
				int len = dataAry.length;
				if(len>1){				
					for(int j = 0;j<len; j++){
						String dataStr = dataAry[j];
						if(j==0){
							tcp7EBuffer.append(dataStr+"7E");
							if(tcp7EBuffer.toString().startsWith("7E") && tcp7EBuffer.toString().endsWith("7E")){
								int tcplen  = tcp7EBuffer.toString().length();
								if(tcplen%2 == 0){
									sendBusinessProcessing(session,tcp7EBuffer.toString());
									tcp7EBuffer = new StringBuffer();
									tcpmap.put(session, tcp7EBuffer);
								}
							}
						}else if(j == (len-1)){
							if(data.endsWith("7E7E")){
								tcp7EBuffer.append("7E"+dataStr+"7E");
								if(tcp7EBuffer.toString().startsWith("7E") && tcp7EBuffer.toString().endsWith("7E")){
									sendBusinessProcessing(session,tcp7EBuffer.toString());
									tcp7EBuffer  = new StringBuffer();
									tcpmap.put(session, tcp7EBuffer);
								}
								tcp7EBuffer.append("7E");
							}else{
								if(dataStr.endsWith("7E")){
									tcp7EBuffer.append("7E"+dataStr);
									if(tcp7EBuffer.toString().startsWith("7E") && tcp7EBuffer.toString().endsWith("7E")){
										sendBusinessProcessing(session,tcp7EBuffer.toString());
										tcp7EBuffer  = new StringBuffer();
										tcpmap.put(session, tcp7EBuffer);
									}
								}else{
									tcp7EBuffer.append("7E"+dataStr);
								}
							}
						}else{
							tcp7EBuffer.append("7E"+dataStr+"7E");
							if(tcp7EBuffer.toString().startsWith("7E") && tcp7EBuffer.toString().endsWith("7E")){
								sendBusinessProcessing(session,tcp7EBuffer.toString());
								tcp7EBuffer  = new StringBuffer();
								tcpmap.put(session, tcp7EBuffer);
							}
						}
					}
				}else{
					if(data.endsWith("7E7E")){
						tcp7EBuffer.append(dataAry[0]+"7E");
						if(tcp7EBuffer.toString().startsWith("7E") && tcp7EBuffer.toString().endsWith("7E")){
							sendBusinessProcessing(session,tcp7EBuffer.toString());
							tcp7EBuffer = new StringBuffer();
							tcpmap.put(session, tcp7EBuffer);
						}
						tcp7EBuffer.append("7E");
					}else{
						if(data.endsWith("7E")){
							tcp7EBuffer.append(dataAry[0]);
							if(data.startsWith("7E")){
								if(tcp7EBuffer.toString().startsWith("7E") && tcp7EBuffer.toString().endsWith("7E")){
									sendBusinessProcessing(session,tcp7EBuffer.toString());
									tcp7EBuffer  = new StringBuffer();
									tcpmap.put(session, tcp7EBuffer);
								}
							}else{
								if(tcp7EBuffer.toString().startsWith("7E") && tcp7EBuffer.toString().endsWith("7E")){

									boolean reflag = BbEncoderUtil.checkCode(ParseUtil.parseHexStrToByte(tcp7EBuffer.toString()));
									if(reflag){
										sendBusinessProcessing(session,tcp7EBuffer.toString());
										tcp7EBuffer  = new StringBuffer();
										tcpmap.put(session, tcp7EBuffer);
									}else{
										cachemap.put(session, data);
										String tcpstr = tcp7EBuffer.toString().replace(data, "");
										tcp7EBuffer  = new StringBuffer();
										tcp7EBuffer.append(tcpstr);
										tcpmap.put(session, tcp7EBuffer);
									}

								}
							}
						}else{
							//处理分包 00007E先到 而7E0000后到的情况
							if(tcp7EBuffer.toString().endsWith("7E") && dataAry[0].startsWith("7E")){
								String outmsg = String.format("%s%s", dataAry[0],tcp7EBuffer.toString());
								if(outmsg.startsWith("7E") && outmsg.endsWith("7E")){
									sendBusinessProcessing(session,outmsg);
									tcp7EBuffer  = new StringBuffer();
									tcpmap.put(session, tcp7EBuffer);
								}
							}else{
								tcp7EBuffer.append(dataAry[0]);
							}
						}
					}
				}
			}

			tcpmap.put(session, tcp7EBuffer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	*//**
	 * 处理2929开头0D结尾格式协议 粘包
	 * @param session
	 * @param data
	 *//*
	public static void handle29290D(IoSession session,String data){

		try {

			StringBuffer dataBuffer = tcpmap.get(session);
			if(dataBuffer == null){
				dataBuffer = new StringBuffer();
			}

			int len = data.indexOf("2929");
			int len1 = data.indexOf("0D");
			if(len>=0 && len1>=0){
				String [] dataAry = data.split("0D2929");
				int num = dataAry.length;
				if(num>1){
					for(int j = 0;j<num; j++){
						if(j==0){
							dataBuffer.append(dataAry[j]+"0D");
							sendBusinessProcessing(session,dataBuffer.toString());
							dataBuffer  = new StringBuffer();
						}else if(j == (num-1)){
							if(dataAry[j].endsWith("0D")){
								dataBuffer.append("2929"+dataAry[j]);
								sendBusinessProcessing(session,dataBuffer.toString());
								dataBuffer  = new StringBuffer();
							}else{
								dataBuffer.append("2929"+dataAry[j]);
							}
						}else{
							dataBuffer.append("2929"+dataAry[j]+"0D");
							sendBusinessProcessing(session,dataBuffer.toString());
							dataBuffer  = new StringBuffer();
						}
					}
				}else{
					dataBuffer.append(data.substring(0, len1+"0D".length()));
					sendBusinessProcessing(session,dataBuffer.toString());
					dataBuffer  = new StringBuffer();
					if(data.length()>"0D".length()){
						dataBuffer.append(data.substring(len, data.length()));
					}
				}
			}else{
				if(len>=0){
					dataBuffer  = new StringBuffer();
					dataBuffer.append(data.substring(len, data.length()));
				}else{

					if(len1>=0){

						if(data.endsWith("0D")){
							dataBuffer.append(data);
							sendBusinessProcessing(session,dataBuffer.toString());
							dataBuffer  = new StringBuffer();
						}else{
							dataBuffer.append(data);
						}
					}else{
						dataBuffer.append(data);
					}
				}
			}

			tcpmap.put(session, dataBuffer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	*//**
	 * 处理FF开头0D结尾格式协议 粘包
	 * @param session
	 * @param data
	 *//*
	public static void handleFF0D(IoSession session,String data){

		try {

			StringBuffer dataBuffer = tcpmap.get(session);
			if(dataBuffer == null){
				dataBuffer = new StringBuffer();
			}

			String [] dataAry = data.split("0DFF");
			int num = dataAry.length;
			if(num>1){				
				for(int j = 0;j<num; j++){
					if(j==0){
						dataBuffer.append(dataAry[j]+"0D");
						sendBusinessProcessing(session,dataBuffer.toString());
						dataBuffer  = new StringBuffer();

					}else if(j == (num-1)){
						if(data.endsWith("0DFF")){
							dataBuffer.append("FF"+dataAry[j]+"0D");
							sendBusinessProcessing(session,dataBuffer.toString());
							dataBuffer  = new StringBuffer();

							dataBuffer.append("0D");
						}else{
							if(dataAry[j].endsWith("0D")){
								dataBuffer.append("FF"+dataAry[j]);
								sendBusinessProcessing(session,dataBuffer.toString());
								dataBuffer  = new StringBuffer();
							}else{
								dataBuffer.append("FF"+dataAry[j]);
							}
						}
					}else{
						dataBuffer.append("FF"+dataAry[j]+"0D");
						sendBusinessProcessing(session,dataBuffer.toString());
						dataBuffer  = new StringBuffer();
					}
				}
			}else{
				if(data.endsWith("0DFF")){
					dataBuffer.append(dataAry[0]+"0D");
					sendBusinessProcessing(session,dataBuffer.toString());
					dataBuffer  = new StringBuffer();

					dataBuffer.append("0D");
				}else{
					if(data.endsWith("0D")){
						dataBuffer.append(dataAry[0]);
						if(data.startsWith("FF")){
							sendBusinessProcessing(session,dataBuffer.toString());
							dataBuffer  = new StringBuffer();
						}else{
							if(dataBuffer.toString().startsWith("FF") && dataBuffer.toString().endsWith("0D")){
								sendBusinessProcessing(session,dataBuffer.toString());
								dataBuffer  = new StringBuffer();
							}
						}
					}else{
						dataBuffer.append(dataAry[0]);
					}
				}

			}

			tcpmap.put(session, dataBuffer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	*//**
	 * *HQ20开头＃结尾格式协议(赢时通)
	 * @param session
	 * @param data
	 *//*
	public static void handleHQ20(IoSession session,String data){

		try {

			sendBusinessProcessing(session,data);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	*//**
	 * 0开头0D结尾格式协议(中导)
	 * @param session
	 * @param data
	 *//*
	public static void handle400D(IoSession session,String data){

		try {
			if(data.startsWith("40") && data.endsWith("0D")){
				sendBusinessProcessing(session,data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	*//**
	 * 7878开头0D0A结尾格式协议(深圳市格林维信息科技有限公司K100)
	 * @param session
	 * @param data
	 *//*
	public static void handle78780D0A(IoSession session,String data){

		try {
			sendBusinessProcessing(session,data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	*//**
	 * 6868开头0D0A结尾格式协议(深圳市格林维信息科技有限公司G11)
	 * @param session
	 * @param data
	 *//*
	public static void handle68680D0A(IoSession session,String data){

		try {
			sendBusinessProcessing(session,data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	*//**
	 * 55AA开头格式协议(蓝度商用车协议)
	 * @param session
	 * @param data
	 *//*
	public static void handle55AA(IoSession session,String data){
		try {
			sendBusinessProcessing(session,data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	*//**
	 * 处理7A开头7A结尾 格式协议 粘包
	 * @param session
	 * @param data
	 *//*
	public static void handle7A(IoSession session,String data){

		try {
			StringBuffer tcp7ABuffer = tcpmap.get(session);
			if(tcp7ABuffer == null){
				tcp7ABuffer = new StringBuffer();
			}

			String [] dataAry = data.split("7A7A");
			int num = dataAry.length;
			if(num>1){				
				for(int j = 0;j<num; j++){
					if(j==0){
						tcp7ABuffer.append(dataAry[j]+"7A");
						if(tcp7ABuffer.toString().startsWith("7A") && tcp7ABuffer.toString().endsWith("7A")){
							sendBusinessProcessing(session,tcp7ABuffer.toString());
						}
						tcp7ABuffer  = new StringBuffer();

					}else if(j == (num-1)){
						if(data.endsWith("7A7A")){
							tcp7ABuffer.append("7A"+dataAry[j]+"7A");
							if(tcp7ABuffer.toString().startsWith("7A") && tcp7ABuffer.toString().endsWith("7A")){
								sendBusinessProcessing(session,tcp7ABuffer.toString());
							}
							tcp7ABuffer  = new StringBuffer();

							tcp7ABuffer.append("7A");
						}else{
							if(dataAry[j].endsWith("7A")){
								tcp7ABuffer.append("7A"+dataAry[j]);
								if(tcp7ABuffer.toString().startsWith("7A") && tcp7ABuffer.toString().endsWith("7A")){
									sendBusinessProcessing(session,tcp7ABuffer.toString());
								}
								tcp7ABuffer  = new StringBuffer();
							}else{
								tcp7ABuffer.append("7A"+dataAry[j]);
							}
						}
					}else{
						tcp7ABuffer.append("7A"+dataAry[j]+"7A");
						if(tcp7ABuffer.toString().startsWith("7A") && tcp7ABuffer.toString().endsWith("7A")){
							sendBusinessProcessing(session,tcp7ABuffer.toString());
						}
						tcp7ABuffer  = new StringBuffer();
					}
				}
			}else{
				if(data.endsWith("7A7A")){
					tcp7ABuffer.append(dataAry[0]+"7A");
					if(tcp7ABuffer.toString().startsWith("7A") && tcp7ABuffer.toString().endsWith("7A")){
						sendBusinessProcessing(session,tcp7ABuffer.toString());
					}
					tcp7ABuffer  = new StringBuffer();

					tcp7ABuffer.append("7A");
				}else{
					if(data.endsWith("7A")){
						tcp7ABuffer.append(dataAry[0]);
						if(data.startsWith("7A")){
							if(tcp7ABuffer.toString().startsWith("7A") && tcp7ABuffer.toString().endsWith("7A")){
								sendBusinessProcessing(session,tcp7ABuffer.toString());
							}
							tcp7ABuffer  = new StringBuffer();
						}else{
							if(tcp7ABuffer.toString().startsWith("7A") && tcp7ABuffer.toString().endsWith("7A")){
								if(tcp7ABuffer.toString().startsWith("7A") && tcp7ABuffer.toString().endsWith("7A")){
									sendBusinessProcessing(session,tcp7ABuffer.toString());
								}
								tcp7ABuffer  = new StringBuffer();
							}
						}
					}else{
						//处理分包 00007A先到 而7A0000后到的情况
						if(tcp7ABuffer.toString().endsWith("7A") && dataAry[0].startsWith("7A")){
							String outmsg = String.format("%s%s", dataAry[0],tcp7ABuffer.toString());
							if(outmsg.startsWith("7A") && outmsg.endsWith("7A")){
								sendBusinessProcessing(session,outmsg);
							}
							tcp7ABuffer  = new StringBuffer();
						}else{
							tcp7ABuffer.append(dataAry[0]);
						}
					}
				}

			}

			tcpmap.put(session, tcp7ABuffer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	*//**
	 * 处理8A开头8A结尾 格式协议 粘包
	 * @param session
	 * @param data
	 *//*
	public static void handle8A(IoSession session,String data){

		try {
			StringBuffer tcp8ABuffer = tcpmap.get(session);
			if(tcp8ABuffer == null){
				tcp8ABuffer = new StringBuffer();
			}

			String [] dataAry = data.split("8A8A");
			int num = dataAry.length;
			if(num>1){				
				for(int j = 0;j<num; j++){
					if(j==0){
						tcp8ABuffer.append(dataAry[j]+"8A");
						if(tcp8ABuffer.toString().startsWith("8A") && tcp8ABuffer.toString().endsWith("8A")){
							sendBusinessProcessing(session,tcp8ABuffer.toString());
						}
						tcp8ABuffer  = new StringBuffer();

					}else if(j == (num-1)){
						if(data.endsWith("8A8A")){
							tcp8ABuffer.append("8A"+dataAry[j]+"8A");
							if(tcp8ABuffer.toString().startsWith("8A") && tcp8ABuffer.toString().endsWith("8A")){
								sendBusinessProcessing(session,tcp8ABuffer.toString());
							}
							tcp8ABuffer  = new StringBuffer();

							tcp8ABuffer.append("8A");
						}else{
							if(dataAry[j].endsWith("8A")){
								tcp8ABuffer.append("8A"+dataAry[j]);
								if(tcp8ABuffer.toString().startsWith("8A") && tcp8ABuffer.toString().endsWith("8A")){
									sendBusinessProcessing(session,tcp8ABuffer.toString());
								}
								tcp8ABuffer  = new StringBuffer();
							}else{
								tcp8ABuffer.append("8A"+dataAry[j]);
							}
						}
					}else{
						tcp8ABuffer.append("8A"+dataAry[j]+"8A");
						if(tcp8ABuffer.toString().startsWith("8A") && tcp8ABuffer.toString().endsWith("8A")){
							sendBusinessProcessing(session,tcp8ABuffer.toString());
						}
						tcp8ABuffer  = new StringBuffer();
					}
				}
			}else{
				if(data.endsWith("8A8A")){
					tcp8ABuffer.append(dataAry[0]+"8A");
					if(tcp8ABuffer.toString().startsWith("8A") && tcp8ABuffer.toString().endsWith("8A")){
						sendBusinessProcessing(session,tcp8ABuffer.toString());
					}
					tcp8ABuffer  = new StringBuffer();

					tcp8ABuffer.append("8A");
				}else{
					if(data.endsWith("8A")){
						tcp8ABuffer.append(dataAry[0]);
						if(data.startsWith("8A")){
							if(tcp8ABuffer.toString().startsWith("8A") && tcp8ABuffer.toString().endsWith("8A")){
								sendBusinessProcessing(session,tcp8ABuffer.toString());
							}
							tcp8ABuffer  = new StringBuffer();
						}else{
							if(tcp8ABuffer.toString().startsWith("8A") && tcp8ABuffer.toString().endsWith("8A")){
								if(tcp8ABuffer.toString().startsWith("8A") && tcp8ABuffer.toString().endsWith("8A")){
									sendBusinessProcessing(session,tcp8ABuffer.toString());
								}
								tcp8ABuffer  = new StringBuffer();
							}
						}
					}else{
						//处理分包 00008A先到 而8A0000后到的情况
						if(tcp8ABuffer.toString().endsWith("8A") && dataAry[0].startsWith("8A")){
							String outmsg = String.format("%s%s", dataAry[0],tcp8ABuffer.toString());
							if(outmsg.startsWith("8A") && outmsg.endsWith("8A")){
								sendBusinessProcessing(session,outmsg);
							}
							tcp8ABuffer  = new StringBuffer();
						}else{
							tcp8ABuffer.append(dataAry[0]);
						}
					}
				}

			}

			tcpmap.put(session, tcp8ABuffer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	*//**
	 * 处理8A开头8A结尾 格式协议 粘包
	 * @param session
	 * @param data
	 *//*
	public static void handle9A(IoSession session,String data){

		try {
			StringBuffer tcp9ABuffer = tcpmap.get(session);
			if(tcp9ABuffer == null){
				tcp9ABuffer = new StringBuffer();
			}

			String [] dataAry = data.split("9A9A");
			int num = dataAry.length;
			if(num>1){				
				for(int j = 0;j<num; j++){
					if(j==0){
						tcp9ABuffer.append(dataAry[j]+"9A");
						if(tcp9ABuffer.toString().startsWith("9A") && tcp9ABuffer.toString().endsWith("9A")){
							sendBusinessProcessing(session,tcp9ABuffer.toString());
						}
						tcp9ABuffer  = new StringBuffer();

					}else if(j == (num-1)){
						if(data.endsWith("9A9A")){
							tcp9ABuffer.append("9A"+dataAry[j]+"9A");
							if(tcp9ABuffer.toString().startsWith("9A") && tcp9ABuffer.toString().endsWith("9A")){
								sendBusinessProcessing(session,tcp9ABuffer.toString());
							}
							tcp9ABuffer  = new StringBuffer();

							tcp9ABuffer.append("9A");
						}else{
							if(dataAry[j].endsWith("9A")){
								tcp9ABuffer.append("9A"+dataAry[j]);
								if(tcp9ABuffer.toString().startsWith("9A") && tcp9ABuffer.toString().endsWith("9A")){
									sendBusinessProcessing(session,tcp9ABuffer.toString());
								}
								tcp9ABuffer  = new StringBuffer();
							}else{
								tcp9ABuffer.append("9A"+dataAry[j]);
							}
						}
					}else{
						tcp9ABuffer.append("9A"+dataAry[j]+"9A");
						if(tcp9ABuffer.toString().startsWith("9A") && tcp9ABuffer.toString().endsWith("9A")){
							sendBusinessProcessing(session,tcp9ABuffer.toString());
						}
						tcp9ABuffer  = new StringBuffer();
					}
				}
			}else{
				if(data.endsWith("9A9A")){
					tcp9ABuffer.append(dataAry[0]+"9A");
					if(tcp9ABuffer.toString().startsWith("9A") && tcp9ABuffer.toString().endsWith("9A")){
						sendBusinessProcessing(session,tcp9ABuffer.toString());
					}
					tcp9ABuffer  = new StringBuffer();

					tcp9ABuffer.append("9A");
				}else{
					if(data.endsWith("9A")){
						tcp9ABuffer.append(dataAry[0]);
						if(data.startsWith("9A")){
							if(tcp9ABuffer.toString().startsWith("9A") && tcp9ABuffer.toString().endsWith("9A")){
								sendBusinessProcessing(session,tcp9ABuffer.toString());
							}
							tcp9ABuffer  = new StringBuffer();
						}else{
							if(tcp9ABuffer.toString().startsWith("9A") && tcp9ABuffer.toString().endsWith("9A")){
								if(tcp9ABuffer.toString().startsWith("9A") && tcp9ABuffer.toString().endsWith("9A")){
									sendBusinessProcessing(session,tcp9ABuffer.toString());
								}
								tcp9ABuffer  = new StringBuffer();
							}
						}
					}else{
						//处理分包 00008A先到 而8A0000后到的情况
						if(tcp9ABuffer.toString().endsWith("9A") && dataAry[0].startsWith("9A")){
							String outmsg = String.format("%s%s", dataAry[0],tcp9ABuffer.toString());
							if(outmsg.startsWith("9A") && outmsg.endsWith("9A")){
								sendBusinessProcessing(session,outmsg);
							}
							tcp9ABuffer  = new StringBuffer();
						}else{
							tcp9ABuffer.append(dataAry[0]);
						}
					}
				}

			}

			tcpmap.put(session, tcp9ABuffer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	*//**
	 * 5A开头A5结束格式协议(恒天高科GPRS工业采集控制器协议)
	 * @param session
	 * @param data
	 *//*
	public static void handle5A(IoSession session,String data){
		try {
			try {

				StringBuffer dataBuffer = tcpmap.get(session);
				if(dataBuffer == null){
					dataBuffer = new StringBuffer();
				}

				int len = data.indexOf("5A");
				int len1 = data.indexOf("A5");
				if(len>=0 && len1>=0){
					String [] dataAry = data.split("A55A");
					int num = dataAry.length;
					if(num>1){
						for(int j = 0;j<num; j++){
							if(j==0){
								dataBuffer.append(dataAry[j]+"A5");
								sendBusinessProcessing(session,dataBuffer.toString());
								dataBuffer  = new StringBuffer();
							}else if(j == (num-1)){
								if(dataAry[j].endsWith("A5")){
									dataBuffer.append("5A"+dataAry[j]);
									sendBusinessProcessing(session,dataBuffer.toString());
									dataBuffer  = new StringBuffer();
								}else{
									dataBuffer.append("5A"+dataAry[j]);
								}
							}else{
								dataBuffer.append("5A"+dataAry[j]+"A5");
								sendBusinessProcessing(session,dataBuffer.toString());
								dataBuffer  = new StringBuffer();
							}
						}
					}else{
						dataBuffer.append(data.substring(0, len1+"A5".length()));
						sendBusinessProcessing(session,dataBuffer.toString());
						dataBuffer  = new StringBuffer();
						if(data.length()>"A5".length()){
							dataBuffer.append(data.substring(len, data.length()));
						}
					}
				}else{
					if(len>=0){
						dataBuffer  = new StringBuffer();
						dataBuffer.append(data.substring(len, data.length()));
					}else{

						if(len1>=0){

							if(data.endsWith("A5")){
								dataBuffer.append(data);
								sendBusinessProcessing(session,dataBuffer.toString());
								dataBuffer  = new StringBuffer();
							}else{
								dataBuffer.append(data);
							}
						}else{
							dataBuffer.append(data);
						}
					}
				}

				tcpmap.put(session, dataBuffer);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	*//**
	 * 6A开头格式协议(多森软件GPRS控制器协议)
	 * @param session
	 * @param data
	 *//*
	public static void handle6A(IoSession session,String data){
		try {
			sendBusinessProcessing(session,data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	*//**
	 * 28开头、29结束格式协议(深圳麦卡途科技有限公司MK6000协议)
	 * @param session
	 * @param data
	 *//*
	public static void handle2829(IoSession session,String data){

		try {
			StringBuffer tcpBuffer = tcpmap.get(session);
			if(tcpBuffer == null){
				tcpBuffer = new StringBuffer();
			}

			String [] dataAry = data.split("2928");
			int num = dataAry.length;
			if(num>1){				
				for(int j = 0;j<num; j++){
					if(j==0){
						tcpBuffer.append(dataAry[j]+"29");
						if(tcpBuffer.toString().startsWith("28") && tcpBuffer.toString().endsWith("29")){
							sendBusinessProcessing(session,tcpBuffer.toString());
						}
						tcpBuffer  = new StringBuffer();

					}else if(j == (num-1)){
						if(data.endsWith("2928")){
							tcpBuffer.append("28"+dataAry[j]+"29");
							if(tcpBuffer.toString().startsWith("28") && tcpBuffer.toString().endsWith("29")){
								sendBusinessProcessing(session,tcpBuffer.toString());
							}
							tcpBuffer  = new StringBuffer();

							tcpBuffer.append("28");
						}else{
							if(dataAry[j].endsWith("29")){
								tcpBuffer.append("28"+dataAry[j]);
								if(tcpBuffer.toString().startsWith("28") && tcpBuffer.toString().endsWith("29")){
									sendBusinessProcessing(session,tcpBuffer.toString());
								}
								tcpBuffer  = new StringBuffer();
							}else{
								tcpBuffer.append("28"+dataAry[j]);
							}
						}
					}else{
						tcpBuffer.append("28"+dataAry[j]+"29");
						if(tcpBuffer.toString().startsWith("28") && tcpBuffer.toString().endsWith("29")){
							sendBusinessProcessing(session,tcpBuffer.toString());
						}
						tcpBuffer  = new StringBuffer();
					}
				}
			}else{
				if(data.endsWith("2928")){
					tcpBuffer.append(dataAry[0]+"29");
					if(tcpBuffer.toString().startsWith("28") && tcpBuffer.toString().endsWith("29")){
						sendBusinessProcessing(session,tcpBuffer.toString());
						tcpBuffer  = new StringBuffer();
					}
					tcpBuffer.append("28");
				}else{
					if(data.endsWith("29")){
						tcpBuffer.append(dataAry[0]);
						if(data.startsWith("28")){
							if(tcpBuffer.toString().startsWith("28") && tcpBuffer.toString().endsWith("29")){
								sendBusinessProcessing(session,tcpBuffer.toString());
							}
							tcpBuffer  = new StringBuffer();
						}else{
							if(tcpBuffer.toString().startsWith("28") && tcpBuffer.toString().endsWith("29")){
								sendBusinessProcessing(session,tcpBuffer.toString());
								tcpBuffer  = new StringBuffer();
							}
						}
					}else{
						//处理分包 00008A先到 而8A0000后到的情况
						if(tcpBuffer.toString().endsWith("29") && dataAry[0].startsWith("28")){
							String outmsg = String.format("%s%s", dataAry[0],tcpBuffer.toString());
							if(outmsg.startsWith("28") && outmsg.endsWith("29")){
								sendBusinessProcessing(session,outmsg);
							}
							tcpBuffer  = new StringBuffer();
						}else{
							tcpBuffer.append(dataAry[0]);
						}
					}
				}

			}

			tcpmap.put(session, tcpBuffer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	*//**
	 * 24开头、0A结束格式协议(深圳速锐得科技有限公司T6产品协议)
	 * @param session
	 * @param data
	 *//*
	public static void handle240D0A(IoSession session,String data){

		try {
			StringBuffer tcpBuffer = tcpmap.get(session);
			if(tcpBuffer == null){
				tcpBuffer = new StringBuffer();
			}

			String [] dataAry = data.split("0A2454");
			int num = dataAry.length;
			if(num>1){				
				for(int j = 0;j<num; j++){
					if(j==0){
						tcpBuffer.append(dataAry[j]+"0A");
						if(tcpBuffer.toString().startsWith("2454") && tcpBuffer.toString().endsWith("0A")){
							sendBusinessProcessing(session,tcpBuffer.toString());
						}
						tcpBuffer  = new StringBuffer();

					}else if(j == (num-1)){
						if(data.endsWith("0A2454")){
							tcpBuffer.append("2454"+dataAry[j]+"0A");
							if(tcpBuffer.toString().startsWith("2454") && tcpBuffer.toString().endsWith("0A")){
								sendBusinessProcessing(session,tcpBuffer.toString());
							}
							tcpBuffer  = new StringBuffer();

							tcpBuffer.append("2454");
						}else{
							if(dataAry[j].endsWith("0A")){
								tcpBuffer.append("2454"+dataAry[j]);
								if(tcpBuffer.toString().startsWith("2454") && tcpBuffer.toString().endsWith("0A")){
									sendBusinessProcessing(session,tcpBuffer.toString());
								}
								tcpBuffer  = new StringBuffer();
							}else{
								tcpBuffer.append("2454"+dataAry[j]);
							}
						}
					}else{
						tcpBuffer.append("2454"+dataAry[j]+"0A");
						if(tcpBuffer.toString().startsWith("2454") && tcpBuffer.toString().endsWith("0A")){
							sendBusinessProcessing(session,tcpBuffer.toString());
						}
						tcpBuffer  = new StringBuffer();
					}
				}
			}else{
				if(data.endsWith("0A2454")){
					tcpBuffer.append(dataAry[0]+"0A");
					if(tcpBuffer.toString().startsWith("2454") && tcpBuffer.toString().endsWith("0A")){
						sendBusinessProcessing(session,tcpBuffer.toString());
						tcpBuffer  = new StringBuffer();
					}
					tcpBuffer.append("2454");
				}else{
					if(data.endsWith("0A")){
						tcpBuffer.append(dataAry[0]);
						if(data.startsWith("2454")){
							if(tcpBuffer.toString().startsWith("2454") && tcpBuffer.toString().endsWith("0A")){
								sendBusinessProcessing(session,tcpBuffer.toString());
							}
							tcpBuffer  = new StringBuffer();
						}else{
							if(tcpBuffer.toString().startsWith("2454") && tcpBuffer.toString().endsWith("0A")){
								sendBusinessProcessing(session,tcpBuffer.toString());
								tcpBuffer  = new StringBuffer();
							}
						}
					}else{
						//处理分包 00008A先到 而8A0000后到的情况
						if(tcpBuffer.toString().endsWith("0A") && dataAry[0].startsWith("2454")){
							String outmsg = String.format("%s%s", dataAry[0],tcpBuffer.toString());
							if(outmsg.startsWith("2454") && outmsg.endsWith("0A")){
								sendBusinessProcessing(session,outmsg);
							}
							tcpBuffer  = new StringBuffer();
						}else{
							tcpBuffer.append(dataAry[0]);
						}
					}
				}

			}

			tcpmap.put(session, tcpBuffer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	*//**
	 * 以4040开头 0D0A结束深圳市航天无线通信技术有限公司213GD协议
	 * @param session
	 * @param data
	 *//*
	public static void handle400A(IoSession session,String data){

		try {
			StringBuffer tcpBuffer = tcpmap.get(session);
			if(tcpBuffer == null){
				tcpBuffer = new StringBuffer();
			}
			if(data.startsWith("4040")&&data.endsWith("0D0A")&&data.indexOf("0D0A4040")<=0){
				if(data.equals("4040")){
					tcpBuffer.append("4040");
				}else if(data.equals("0D0A")){
					tcpBuffer.append("0D0A");
				}else{
					sendBusinessProcessing(session,data);
					return;
				}
			}else{
				String [] dataAry = data.split("0A40");
				int num = dataAry.length;
				if(num>1){				
					for(int j = 0;j<num; j++){
						if(j==0){
							tcpBuffer.append(dataAry[j]+"0A");
							if(tcpBuffer.toString().startsWith("40") && tcpBuffer.toString().endsWith("0A")){
								sendBusinessProcessing(session,tcpBuffer.toString());
							}
							tcpBuffer  = new StringBuffer();

						}else if(j == (num-1)){
							if(data.endsWith("0A40")){
								tcpBuffer.append("40"+dataAry[j]+"0A");
								if(tcpBuffer.toString().startsWith("40") && tcpBuffer.toString().endsWith("0A")){
									sendBusinessProcessing(session,tcpBuffer.toString());
								}
								tcpBuffer  = new StringBuffer();

								tcpBuffer.append("40");
							}else{
								if(dataAry[j].endsWith("0A")){
									tcpBuffer.append("40"+dataAry[j]);
									if(tcpBuffer.toString().startsWith("40") && tcpBuffer.toString().endsWith("0A")){
										sendBusinessProcessing(session,tcpBuffer.toString());
									}
									tcpBuffer  = new StringBuffer();
								}else{
									tcpBuffer.append("40"+dataAry[j]);
								}
							}
						}else{
							tcpBuffer.append("40"+dataAry[j]+"0A");
							if(tcpBuffer.toString().startsWith("40") && tcpBuffer.toString().endsWith("0A")){
								sendBusinessProcessing(session,tcpBuffer.toString());
							}
							tcpBuffer  = new StringBuffer();
						}
					}
				}else{
					if(data.endsWith("0A40")){
						tcpBuffer.append(dataAry[0]+"0A");
						if(tcpBuffer.toString().startsWith("40") && tcpBuffer.toString().endsWith("0A")){
							sendBusinessProcessing(session,tcpBuffer.toString());
							tcpBuffer  = new StringBuffer();
						}
						tcpBuffer.append("40");
					}else{
						if(data.endsWith("0A")){
							tcpBuffer.append(dataAry[0]);
							if(data.startsWith("40")){
								if(tcpBuffer.toString().startsWith("40") && tcpBuffer.toString().endsWith("0A")){
									sendBusinessProcessing(session,tcpBuffer.toString());
								}
								tcpBuffer  = new StringBuffer();
							}else{
								if(tcpBuffer.toString().startsWith("40") && tcpBuffer.toString().endsWith("0A")){
									sendBusinessProcessing(session,tcpBuffer.toString());
									tcpBuffer  = new StringBuffer();
								}
							}
						}else{
							//处理分包 00008A先到 而8A0000后到的情况
							if(tcpBuffer.toString().endsWith("0A") && dataAry[0].startsWith("40")){
								String outmsg = String.format("%s%s", dataAry[0],tcpBuffer.toString());
								if(outmsg.startsWith("40") && outmsg.endsWith("0A")){
									sendBusinessProcessing(session,outmsg);
								}
								tcpBuffer  = new StringBuffer();
							}else{
								tcpBuffer.append(dataAry[0]);
							}
						}
					}

				}
			}
			tcpmap.put(session, tcpBuffer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	*//**
	 * 以54开头 0D0A结束深圳市城市漫步科技有限公司协议
	 * @param session
	 * @param data
	 *//*
	public static void handle540D0A(IoSession session,String data){

		try {
			StringBuffer tcpBuffer = tcpmap.get(session);
			if(tcpBuffer == null){
				tcpBuffer = new StringBuffer();
			}
			if(data.startsWith("5454")&&data.endsWith("0D0A")&&data.indexOf("0D0A5454")<=0){
				if(data.equals("5454")){
					tcpBuffer.append("5454");
				}else if(data.equals("0D0A")){
					tcpBuffer.append("0D0A");
				}else{
					sendBusinessProcessing(session,data);
					return;
				}
			}else{
				String [] dataAry = data.split("0A54");
				int num = dataAry.length;
				if(num>1){				
					for(int j = 0;j<num; j++){
						if(j==0){
							tcpBuffer.append(dataAry[j]+"0A");
							if(tcpBuffer.toString().startsWith("54") && tcpBuffer.toString().endsWith("0A")){
								sendBusinessProcessing(session,tcpBuffer.toString());
							}
							tcpBuffer  = new StringBuffer();

						}else if(j == (num-1)){
							if(data.endsWith("0A54")){
								tcpBuffer.append("54"+dataAry[j]+"0A");
								if(tcpBuffer.toString().startsWith("54") && tcpBuffer.toString().endsWith("0A")){
									sendBusinessProcessing(session,tcpBuffer.toString());
								}
								tcpBuffer  = new StringBuffer();

								tcpBuffer.append("54");
							}else{
								if(dataAry[j].endsWith("0A")){
									tcpBuffer.append("54"+dataAry[j]);
									if(tcpBuffer.toString().startsWith("54") && tcpBuffer.toString().endsWith("0A")){
										sendBusinessProcessing(session,tcpBuffer.toString());
									}
									tcpBuffer  = new StringBuffer();
								}else{
									tcpBuffer.append("54"+dataAry[j]);
								}
							}
						}else{
							tcpBuffer.append("54"+dataAry[j]+"0A");
							if(tcpBuffer.toString().startsWith("54") && tcpBuffer.toString().endsWith("0A")){
								sendBusinessProcessing(session,tcpBuffer.toString());
							}
							tcpBuffer  = new StringBuffer();
						}
					}
				}else{
					if(data.endsWith("0A54")){
						tcpBuffer.append(dataAry[0]+"0A");
						if(tcpBuffer.toString().startsWith("54") && tcpBuffer.toString().endsWith("0A")){
							sendBusinessProcessing(session,tcpBuffer.toString());
							tcpBuffer  = new StringBuffer();
						}
						tcpBuffer.append("54");
					}else{
						if(data.endsWith("0A")){
							tcpBuffer.append(dataAry[0]);
							if(data.startsWith("54")){
								if(tcpBuffer.toString().startsWith("54") && tcpBuffer.toString().endsWith("0A")){
									sendBusinessProcessing(session,tcpBuffer.toString());
								}
								tcpBuffer  = new StringBuffer();
							}else{
								if(tcpBuffer.toString().startsWith("54") && tcpBuffer.toString().endsWith("0A")){
									sendBusinessProcessing(session,tcpBuffer.toString());
									tcpBuffer  = new StringBuffer();
								}
							}
						}else{
							//处理分包 00008A先到 而8A0000后到的情况
							if(tcpBuffer.toString().endsWith("0A") && dataAry[0].startsWith("54")){
								String outmsg = String.format("%s%s", dataAry[0],tcpBuffer.toString());
								if(outmsg.startsWith("54") && outmsg.endsWith("0A")){
									sendBusinessProcessing(session,outmsg);
								}
								tcpBuffer  = new StringBuffer();
							}else{
								tcpBuffer.append(dataAry[0]);
							}
						}
					}

				}
			}
			tcpmap.put(session, tcpBuffer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	*//**
	 * 以5948开头 0D0A结束东风日产协议
	 * @param session
	 * @param data
	 *//*
	public static void handle59480D0A(IoSession session,String data){

		try {
			StringBuffer tcpBuffer = tcpmap.get(session);
			if(tcpBuffer == null){
				tcpBuffer = new StringBuffer();
			}
			if(data.startsWith("5948")&&data.endsWith("0D0A")&&data.indexOf("0D5D5B48")<=0){
				if(data.equals("5948")){
					tcpBuffer.append("5948");
				}else if(data.equals("0D0A")){
					tcpBuffer.append("0D0A");
				}else{
					sendBusinessProcessing(session,data);
					return;
				}
			}else{
				String [] dataAry = data.split("0A59");
				int num = dataAry.length;
				if(num>1){				
					for(int j = 0;j<num; j++){
						if(j==0){
							tcpBuffer.append(dataAry[j]+"0A");
							if(tcpBuffer.toString().startsWith("59") && tcpBuffer.toString().endsWith("0A")){
								sendBusinessProcessing(session,tcpBuffer.toString());
							}
							tcpBuffer  = new StringBuffer();

						}else if(j == (num-1)){
							if(data.endsWith("0A59")){
								tcpBuffer.append("59"+dataAry[j]+"0A");
								if(tcpBuffer.toString().startsWith("59") && tcpBuffer.toString().endsWith("0A")){
									sendBusinessProcessing(session,tcpBuffer.toString());
								}
								tcpBuffer  = new StringBuffer();

								tcpBuffer.append("59");
							}else{
								if(dataAry[j].endsWith("0A")){
									tcpBuffer.append("59"+dataAry[j]);
									if(tcpBuffer.toString().startsWith("59") && tcpBuffer.toString().endsWith("0A")){
										sendBusinessProcessing(session,tcpBuffer.toString());
									}
									tcpBuffer  = new StringBuffer();
								}else{
									tcpBuffer.append("59"+dataAry[j]);
								}
							}
						}else{
							tcpBuffer.append("59"+dataAry[j]+"0A");
							if(tcpBuffer.toString().startsWith("59") && tcpBuffer.toString().endsWith("0A")){
								sendBusinessProcessing(session,tcpBuffer.toString());
							}
							tcpBuffer  = new StringBuffer();
						}
					}
				}else{
					if(data.endsWith("0A59")){
						tcpBuffer.append(dataAry[0]+"0A");
						if(tcpBuffer.toString().startsWith("59") && tcpBuffer.toString().endsWith("0A")){
							sendBusinessProcessing(session,tcpBuffer.toString());
							tcpBuffer  = new StringBuffer();
						}
						tcpBuffer.append("59");
					}else{
						if(data.endsWith("0A")){
							tcpBuffer.append(dataAry[0]);
							if(data.startsWith("59")){
								if(tcpBuffer.toString().startsWith("59") && tcpBuffer.toString().endsWith("0A")){
									sendBusinessProcessing(session,tcpBuffer.toString());
								}
								tcpBuffer  = new StringBuffer();
							}else{
								if(tcpBuffer.toString().startsWith("59") && tcpBuffer.toString().endsWith("0A")){
									sendBusinessProcessing(session,tcpBuffer.toString());
									tcpBuffer  = new StringBuffer();
								}
							}
						}else{
							//处理分包 00008A先到 而8A0000后到的情况
							if(tcpBuffer.toString().endsWith("0A") && dataAry[0].startsWith("59")){
								String outmsg = String.format("%s%s", dataAry[0],tcpBuffer.toString());
								if(outmsg.startsWith("59") && outmsg.endsWith("0A")){
									sendBusinessProcessing(session,outmsg);
								}
								tcpBuffer  = new StringBuffer();
							}else{
								tcpBuffer.append(dataAry[0]);
							}
						}
					}

				}
			}
			tcpmap.put(session, tcpBuffer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	*//**
	 * 以5B开头 5D结束赛格车圣空中传输通信协议
	 * @param session
	 * @param data
	 *//*
	public static void handle5B5D(IoSession session,String data){

		try {
			StringBuffer tcpBuffer = tcpmap.get(session);
			if(tcpBuffer == null){
				tcpBuffer = new StringBuffer();
			}
			if(data.startsWith("5B")&&data.endsWith("5D")&&data.indexOf("5D5B")<=0){
				if(data.equals("5B")){
					tcpBuffer.append("5B");
				}else if(data.equals("5D")){
					tcpBuffer.append("5D");
				}else{
					sendBusinessProcessing(session,data);
					return;
				}
			}else{
				String [] dataAry = data.split("5D5B");
				int num = dataAry.length;
				if(num>1){				
					for(int j = 0;j<num; j++){
						if(j==0){
							tcpBuffer.append(dataAry[j]+"5D");
							if(tcpBuffer.toString().startsWith("5B") && tcpBuffer.toString().endsWith("5D")){
								sendBusinessProcessing(session,tcpBuffer.toString());
							}
							tcpBuffer  = new StringBuffer();

						}else if(j == (num-1)){
							if(data.endsWith("5D5B")){
								tcpBuffer.append("5B"+dataAry[j]+"5D");
								if(tcpBuffer.toString().startsWith("5B") && tcpBuffer.toString().endsWith("5D")){
									sendBusinessProcessing(session,tcpBuffer.toString());
								}
								tcpBuffer  = new StringBuffer();

								tcpBuffer.append("5B");
							}else{
								if(dataAry[j].endsWith("5D")){
									tcpBuffer.append("5B"+dataAry[j]);
									if(tcpBuffer.toString().startsWith("5B") && tcpBuffer.toString().endsWith("5D")){
										sendBusinessProcessing(session,tcpBuffer.toString());
									}
									tcpBuffer  = new StringBuffer();
								}else{
									tcpBuffer.append("5B"+dataAry[j]);
								}
							}
						}else{
							tcpBuffer.append("5B"+dataAry[j]+"5D");
							if(tcpBuffer.toString().startsWith("5B") && tcpBuffer.toString().endsWith("5D")){
								sendBusinessProcessing(session,tcpBuffer.toString());
							}
							tcpBuffer  = new StringBuffer();
						}
					}
				}else{
					if(data.endsWith("5D5B")){
						tcpBuffer.append(dataAry[0]+"5D");
						if(tcpBuffer.toString().startsWith("5B") && tcpBuffer.toString().endsWith("5D")){
							sendBusinessProcessing(session,tcpBuffer.toString());
							tcpBuffer  = new StringBuffer();
						}
						tcpBuffer.append("5B");
					}else{
						if(data.endsWith("5D")){
							tcpBuffer.append(dataAry[0]);
							if(data.startsWith("5B")){
								if(tcpBuffer.toString().startsWith("5B") && tcpBuffer.toString().endsWith("5D")){
									sendBusinessProcessing(session,tcpBuffer.toString());
								}
								tcpBuffer  = new StringBuffer();
							}else{
								if(tcpBuffer.toString().startsWith("5B") && tcpBuffer.toString().endsWith("5D")){
									sendBusinessProcessing(session,tcpBuffer.toString());
									tcpBuffer  = new StringBuffer();
								}
							}
						}else{
							//处理分包 00008A先到 而8A0000后到的情况
							if(tcpBuffer.toString().endsWith("5D") && dataAry[0].startsWith("5B")){
								String outmsg = String.format("%s%s", dataAry[0],tcpBuffer.toString());
								if(outmsg.startsWith("5B") && outmsg.endsWith("5D")){
									sendBusinessProcessing(session,outmsg);
								}
								tcpBuffer  = new StringBuffer();
							}else{
								tcpBuffer.append(dataAry[0]);
							}
						}
					}

				}
			}
			tcpmap.put(session, tcpBuffer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	*//**
	 * 发给业务进行处理
	 * @param session
	 * @param message
	 *//*
	public static void sendBusinessProcessing(IoSession session,String message){

		if(ThreadPoolManager.executor!=null){
			logger.info("[client->server] [threadpool] ===="+ThreadPoolManager.executor.getPoolSize()+"==="+ThreadPoolManager.executor.getLargestPoolSize()+"==="+ThreadPoolManager.queue.size());
			ThreadPoolManager.executor.execute(new ThreadPoolTask(session,message));
		}

	}

*/}
