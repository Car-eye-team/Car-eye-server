/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.test;

import com.careye.dscomm.utlis.ParseUtil;


/**    
 *     
 * 项目名称：dscomm    
 * 类名称：test    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-15 下午01:42:09    
 * 修改人：zr    
 * 修改时间：2015-6-15 下午01:42:09    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class test {
	private static StringBuffer tcp9ABuffer  = new StringBuffer();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//System.out.println(ParseUtil.parseByteToHexStr(ParseUtil.sumCheck(ParseUtil.parseHexStrToByte("FF2ED108000B331C4502A50D"), 2)));
		/*byte[] ss = new byte[1];
		ss[0] = ParseUtil.bitToByte("00011111");
		System.out.println("==="+ParseUtil.parseByteToHexStr(ss));
		
		System.out.println(ParseUtil.parseByteToHexStr("@".getBytes()));
		
		String hex = "2A4851323031314441CA698312270F270F23";
		byte[] aa = ParseUtil.parseHexStrToByte(hex);
		System.out.println(ParseUtil.byteToString(aa, 0, aa.length));*/
		
		// TODO Auto-generated method stub
		/*System.out.println(String.valueOf(ParseUtil.byteToLong(ParseUtil.parseHexStrToByte("55D2B64A"))));
		
		System.out.println(Integer.valueOf("11010010", 2));

		System.out.println("==="+ParseUtil.byteTobit(ParseUtil.intToBytes(82, 1)));
		StringBuffer terBuffer = new StringBuffer();
		StringBuffer iTemp = new StringBuffer();;

		byte[] data = ParseUtil.parseHexStrToByte("55D2B64A");
		int nOff = 0;
		//伪IP 设备号
		byte[] ip1byte = ParseUtil.byteTobyte(data, nOff, 1);
		nOff+=1;
		int ip1 = ParseUtil.byteToint(ip1byte);
		if(ip1>=128){
			String ip1str = ParseUtil.byteTobit(ip1byte);
			ip1 = Integer.valueOf(ip1str.substring(1, ip1str.length()), 2);
			iTemp.append("1");
		}else{
			iTemp.append("0");
		}
		terBuffer.append(ip1);

		byte[] ip2byte = ParseUtil.byteTobyte(data, nOff, 1);
		nOff+=1;
		int ip2 = ParseUtil.byteToint(ip2byte);
		if(ip2>=128){
			String ip2str = ParseUtil.byteTobit(ip2byte);
			ip2 = Integer.valueOf(ip2str.substring(1, ip2str.length()), 2);
			iTemp.append("1");
		}else{
			iTemp.append("0");
		}
		terBuffer.append(ip2);

		byte[] ip3byte = ParseUtil.byteTobyte(data, nOff, 1);
		nOff+=1;
		int ip3 = ParseUtil.byteToint(ip3byte);
		if(ip3>=128){
			String ip3str = ParseUtil.byteTobit(ip3byte);
			ip3 = Integer.valueOf(ip3str.substring(1, ip3str.length()), 2);
			iTemp.append("1");
		}else{
			iTemp.append("0");
		}
		terBuffer.append(ip3);

		byte[] ip4byte = ParseUtil.byteTobyte(data, nOff, 1);
		nOff+=1;
		int ip4 = ParseUtil.byteToint(ip4byte);
		if(ip4>=128){
			String ip4str = ParseUtil.byteTobit(ip4byte);
			ip4 = Integer.valueOf(ip4str.substring(1, ip4str.length()), 2);
			iTemp.append("1");
		}else{
			iTemp.append("0");
		}
		terBuffer.append(ip4);

		String terString = "1"+(Integer.valueOf(iTemp.toString(), 2)+30)+terBuffer.toString();
		System.out.println(terString);*/
		
		
		/*String terminal = "13685825474";
		byte[] terbyte = new byte[4];
		int ter13 = Integer.parseInt(terminal.substring(1, 3))-30;
		//转换成二进制
		String ter13bit = Integer.toBinaryString(ter13);
		int len = 4-ter13bit.length();
		for (int i = 0; i < len; i++) {
			ter13bit = "0"+ter13bit;
		}
		
		System.out.println(ter13bit);
		
		String ter35bit = ParseUtil.byteTobit(ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(terminal.substring(3, 5)), 1)));
		if("1".equals(ter13bit.substring(0, 1))){
			ter35bit = "1"+	ter35bit.substring(1, ter35bit.length());
		}
		terbyte[0] = ParseUtil.bitToByte(ter35bit);
		
		String ter57bit = ParseUtil.byteTobit(ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(terminal.substring(5, 7)), 1)));
		if("1".equals(ter13bit.substring(1, 2))){
			ter57bit = "1"+	ter57bit.substring(1,ter57bit.length());
		}
		terbyte[1] = ParseUtil.bitToByte(ter57bit);
		
		String ter79bit = ParseUtil.byteTobit(ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(terminal.substring(7, 9)), 1)));
		if("1".equals(ter13bit.substring(2, 3))){
			ter79bit = "1"+	ter79bit.substring(1, ter79bit.length());
		}
		terbyte[2] = ParseUtil.bitToByte(ter79bit);
		
		String ter911bit = ParseUtil.byteTobit(ParseUtil.sortToByte(ParseUtil.intToBytes(Integer.parseInt(terminal.substring(7, 9)), 1)));
		if("1".equals(ter13bit.substring(3, 4))){
			ter911bit = "1"+	ter911bit.substring(1, ter911bit.length());
		}
		terbyte[3] = ParseUtil.bitToByte(ter911bit);
		
		System.out.println(ParseUtil.parseByteToHexStr(terbyte));*/
		
		//String aa = "0B331C45";
		//byte[] body = ParseUtil.parseHexStrToByte("2A48512C383835363032363231312C56312C3038313632322C562C323233332E393837372C4E2C31313335322E343730362C452C3030302E30302C3030302C3239303631362C46464446464246462C46442C333030302C3436302C30312C393533372C343335322C43342C2656354323");
		System.out.println(Integer.parseInt("1000",2));
		//System.out.println("=="+ParseUtil.parseByteToHexStr(ParseUtil.byteOrbyte(ParseUtil.parseHexStrToByte("24885602621108185529061622339870C6113524700C000000FFFFFBFFFD413000520000000063040255040001CC012541110026565C007e"))));
		/*String fver = "BD$V13.2;R01243;S014;XM026.843;M000061;F000.039;T0000045;D00;GX4;GY5;GZ253;@1H\n#ATINFO$VIN:WDDHF5EB7AA208483;CALID:0034467740010708;";
		if(fver.indexOf("CALID") >=0 && fver.indexOf("VIN") >=0 ){
			String aa = fver.split("VIN")[1].split("CALID")[0];
			System.out.println(aa.substring(1, aa.length()-1));
		}*/
		stick9APackage("9A998877665544");
		stick9APackage("9988776655449A");
	}
	
	/**
	 * 数据包粘包处理
	 * @param bytes 数据包
	 * @param context 上下文context对象
	 */
	public static void stick9APackage(String data){
		try {

			String [] dataAry = data.split("9A9A");
			int num = dataAry.length;
			if(num>1){				
				for(int j = 0;j<num; j++){
					if(j==0){
						tcp9ABuffer.append(dataAry[j]+"9A");
						System.out.println("====="+tcp9ABuffer.toString());
						tcp9ABuffer  = new StringBuffer();
					}else if(j == (num-1)){
						if(data.endsWith("9A9A")){
							tcp9ABuffer.append("9A"+dataAry[j]+"9A");
							System.out.println("====="+tcp9ABuffer.toString());
							tcp9ABuffer  = new StringBuffer();
							tcp9ABuffer.append("9A");
						}else{
							if(dataAry[j].endsWith("9A")){
								tcp9ABuffer.append("9A"+dataAry[j]);
								System.out.println("====="+tcp9ABuffer.toString());
								tcp9ABuffer  = new StringBuffer();
							}else{
								tcp9ABuffer.append("9A"+dataAry[j]);
							}
						}

					}else{
						tcp9ABuffer.append("9A"+dataAry[j]+"9A");
						System.out.println("====="+tcp9ABuffer.toString());
						tcp9ABuffer  = new StringBuffer();
					}
				}

			}else{
				if(data.endsWith("9A9A")){
					tcp9ABuffer.append(dataAry[0]+"9A");
					System.out.println("====="+tcp9ABuffer.toString());
					tcp9ABuffer  = new StringBuffer();
					tcp9ABuffer.append("9A");
				}else{
					if(data.endsWith("9A")){
						tcp9ABuffer.append(dataAry[0]);
						if(data.startsWith("9A")){
							System.out.println("====="+tcp9ABuffer.toString());
							tcp9ABuffer  = new StringBuffer();
						}
					}else{
						tcp9ABuffer.append(dataAry[0]);
					}
				}

			}	
			
			
			if(tcp9ABuffer.toString().startsWith("9A") && tcp9ABuffer.toString().endsWith("9A")){
				System.out.println("====="+tcp9ABuffer.toString());
				tcp9ABuffer  = new StringBuffer();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
