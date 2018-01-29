package com.careye.utils;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.felix.main.Main;


/**
 * String工具类，包括对String对象的一些常用操作方法。
 * 
 * @author libo
 * 
 */
public final class StringUtils {
	
	public static String listToString(List<String> stringList){
        if (stringList==null) {
            return null;
        }
        removeDuplicate(stringList);
        StringBuffer result=new StringBuffer();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }
	
	
	public static void removeDuplicate(List list) {
	   for ( int i = 0 ; i < list.size() - 1 ; i ++ ) {
		     for ( int j = list.size() - 1 ; j > i; j -- ) {
			       if (list.get(j).equals(list.get(i))) {
			         list.remove(j);
			       }
		      }
	    }
	}
	
	/**
	 * 将编码格式为“ISO-8859-1”的字符串转换为“GB2312”格式的字符串。
	 * 
	 * @param str
	 *            编码格式为“ISO-8859-1”的字符串
	 * @return 编码格式为“GB2312”的字符串
	 */
	public final static String changeToGB2312(String str) {
		if (str == null)
			return null;
		if (str.equals(""))
			return "";
		try {
			return new String(str.getBytes("iso-8859-1"), "gb2312");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public final static String changeToGBK(String str) {
		if (str == null)
			return null;
		if (str.equals(""))
			return "";
		try {
			return new String(str.getBytes("iso-8859-1"), "GBK");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 是否为中文判断
	 * @param str
	 * @return
	 */
	public static boolean checkChar(String str){

		for(int i=0;i<str.length();i++){

			String test=str.substring(i,i+1);
			if(test.matches("[\\u4E00-\\u9FA5]+")){
				return true;
			}

		}

		return false;
	}

	public final static String changeToUTF8(String str) {
		if (str == null)
			return null;
		if (str.equals(""))
			return "";
		try {
			return new String(str.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return null;
	}


	/**
	 * 将一个字符串转换为int型。默认值为0
	 * 
	 * @param str
	 * @return 转换后的int型数据。
	 */
	public final static int StrToInt(String str) {
		int r = 0;
		if (!isEmty(str)) {
			try {
				r = Integer.parseInt(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return r;
	}

	public static String strnull(String strn) {
		if (strn == null || strn.equals("null"))
			strn = "";
		String stmp = strn.trim();
		strn = "";
		int i = 0;
		String disableChar = "\'\"<>";

		while (i < stmp.length()) {
			if (disableChar.indexOf(stmp.charAt(i)) == -1)
				strn = strn + stmp.charAt(i);
			i++;
		}
		return (strn);
	}


	public static String datetime(String ttime){
		System.out.print("dddddddd="+ttime);
		if(ttime==null){

		}else{
			String OcRq =ttime;
			String Cjt_OcRq="";

			if(OcRq==""){
				Cjt_OcRq =OcRq;
			}else{
				String sj="";
				String sj1="";
				if(Integer.parseInt(OcRq.split("-")[1])<=9){
					sj="0"+OcRq.split("-")[1];
				}else{

					sj=OcRq.split("-")[1];	
				}

				if(Integer.parseInt(OcRq.split("-")[2])<=9){
					sj1="0"+OcRq.split("-")[2];
				}else{

					sj1=OcRq.split("-")[2];	
				}
				Cjt_OcRq =OcRq.split("-")[0]+"-"+sj+"-"+sj1;
			}

			return (Cjt_OcRq); 
		}
		return "";
	}

	public static String TransactSQLInjection(String str)
	{
		return str.replaceAll(".*([';]+|(--)+).*", " ");
	}


	public static String DXZH(String string){
		// System.out.print("请输入一个字母：");
		// Scanner scan = new Scanner(System.in);
		// char c = scan.nextLine().charAt(0);
		//  String ch =  Character.toUpperCase(str);
		//System.out.println("你输入的是:"+c+"转换过后为:"+ch);
		return string.toUpperCase() ;
	}

	public static String jsonChar(String parm,Object value,int type){

		String str = null;

		if(type == 0){
			str = "\""+parm+"\":"+value+",";  
		}else {
			str = "\""+parm+"\":"+value+"";
		}

		return str;

	}



	public static String charStr(String parm){
		if(parm == null ){
			parm = "";
		}
		String str = null;
		str = "\""+parm+"\""; 
		return str;
	}

	/**
	 * 获取客户端IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr1(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip; 
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		//ipAddress = this.getRequest().getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if(ipAddress.equals("127.0.0.1")){
				//根据网卡取本机配置的IP
				InetAddress inet=null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress= inet.getHostAddress();
			}

		}

		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
			if(ipAddress.indexOf(",")>0){
				ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 
	 * unicode 转换成 中文
	 * @param theString
	 * @return
	 */

	public static String decodeUnicode(String theString) {

		char aChar;

		int len = theString.length();

		StringBuffer outBuffer = new StringBuffer(len);

		for (int x = 0; x < len;) {

			aChar = theString.charAt(x++);

			if (aChar == '\\') {

				aChar = theString.charAt(x++);

				if (aChar == 'u') {

					// Read the xxxx

					int value = 0;

					for (int i = 0; i < 4; i++) {

						aChar = theString.charAt(x++);

						switch (aChar) {

						case '0':

						case '1':

						case '2':

						case '3':

						case '4':

						case '5':

						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';

					else if (aChar == 'n')

						aChar = '\n';

					else if (aChar == 'f')

						aChar = '\f';

					outBuffer.append(aChar);

				}

			} else

				outBuffer.append(aChar);

		}

		return outBuffer.toString();

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();

		buffer.append("{");
		buffer.append(StringUtils.jsonChar("msgid",3608, 0));
		buffer.append(StringUtils.jsonChar("subpacket","0", 0));
		buffer.append(StringUtils.jsonChar("encryption","0", 0));
		buffer.append(StringUtils.jsonChar("bodysize","0", 0));
		buffer.append(StringUtils.jsonChar("terminal",StringUtils.charStr("18668171282"),0));
		buffer.append(StringUtils.jsonChar("seq",1,0));
		buffer.append(StringUtils.jsonChar("lat",new BigDecimal(11 * Math.pow(10, 6)),0));
		buffer.append(StringUtils.jsonChar("lng",new BigDecimal(11 * Math.pow(10, 6)),0));
		buffer.append(StringUtils.jsonChar("addr",StringUtils.charStr("打发司法所的 "),1));
		buffer.append("}");

		System.out.println(charStr("18668171282")+","+buffer.toString());
	}

	//去掉 湖北省，北京市 ，多余的'省'和'市' ，即 湖北 | 北京
	public static String format_szname(String szname,int level) {
		if (level == 3)
			return szname;
		return (szname.endsWith("省") || szname.endsWith("市")) 
		? szname.substring(0,szname.length() - 1) : szname;

	}

	// 全角转半角的 转换函数  

	public static final String full2HalfChange(String QJstr)  
	throws UnsupportedEncodingException {  

		StringBuffer outStrBuf = new StringBuffer("");  

		String Tstr = "";  

		byte[] b = null;  

		for (int i = 0; i < QJstr.length(); i++) {  

			Tstr = QJstr.substring(i, i + 1);  

			// 全角空格转换成半角空格  

			if (Tstr.equals("　")) {  

				outStrBuf.append(" ");  

				continue;  

			}  

			b = Tstr.getBytes("unicode");  

			// 得到 unicode 字节数据  

			if (b[2] == -1) {  

				// 表示全角？  

				b[3] = (byte) (b[3] + 32);  

				b[2] = 0;  

				outStrBuf.append(new String(b, "unicode"));  

			} else {  

				outStrBuf.append(Tstr);  

			}  

		} // end for.  

		return outStrBuf.toString();  

	}  

	// 半角转全角  

	public static final String half2Fullchange(String QJstr)  
	throws UnsupportedEncodingException {  

		StringBuffer outStrBuf = new StringBuffer("");  

		String Tstr = "";  

		byte[] b = null;  

		for (int i = 0; i < QJstr.length(); i++) {  

			Tstr = QJstr.substring(i, i + 1);  

			if (Tstr.equals(" ")) {  

				// 半角空格  

				outStrBuf.append(Tstr);  

				continue;  

			}  

			b = Tstr.getBytes("unicode");  

			if (b[2] == 0) {  

				// 半角?  

				b[3] = (byte) (b[3] - 32);  

				b[2] = -1;  

				outStrBuf.append(new String(b, "unicode"));  

			} else {  

				outStrBuf.append(Tstr);  

			}  

		}  

		return outStrBuf.toString();  

	}  

	/**
	 * 判断字符串是否为Null值或“”值
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @return 如果字符串是Null或“”值返回真值（true)否则返回假值（false）
	 */
	public final static boolean isEmty(String str) {
		if (str == null)
			return true;
		else {
			if (str.equals("")|| "null".equals(str) 
					|| "undefined".equals(str) || "".equals(str.trim()))
				return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串是否页面进行了传值
	 * 
	 * @param str需要判断的字符串
	 * @return 传值返回真值（true)否则返回假值（false）
	 */
	public final static boolean isNotEmty(String str) {
		if (str == null || "".equals(str) || "null".equals(str) 
				|| "undefined".equals(str) || "".equals(str.trim())){
			return false;
		}
		return true;
	}

	public static String getCarstatus(Integer carstatus){
		 String typename = "";
         if(carstatus == null){
			typename = "长时间离线";
         }else if(carstatus==7){
			typename = "在线";
		 }else if(carstatus == 2){
			typename = "离线";
		 }else if(carstatus == 3){
			typename = "熄火";
		 }else if(carstatus== 5){
			typename = "行驶";
		 }else if(carstatus== 4){
			typename = "停车";
		 }else if(carstatus== 5){
			typename = "报警";
		 }else if(carstatus== 8){
			typename = "未定位";
          }else if(carstatus== 1){
         	 typename = "长时间离线";
          }
         return typename;
         
	}
	public static String getGpsflag(Integer gpsflag){
		 String typename = "";
        if(gpsflag == 1 || gpsflag == 2){
			typename = "已定位";
        }else {
        	 typename = "未定位";
         }
        return typename;
        
	}
	
	public static String getDirection(Integer direction){
		String dir = "";
		if(direction == null){
			return dir;
		}
        if(direction ==0){
        	dir = "正北";
		}else if(direction >0 && direction <90){
			dir = "东北";
		}else if(direction == 90){
			dir = "正东";
		}else if(direction >90 && direction <180){
			dir = "东南";
		}else if(direction == 180){
			dir = "正南";
		}else if(direction >180 && direction <270){
			dir = "西南";
		}else if(direction == 270){
			dir = "正西";
		}else if(direction >270 && direction <360){
			dir = "西北";
		}else if(direction == 360){
			dir = "正北";
		}
		return dir;
		
	}
	
	public static String getDirection(String dire){
		
		String typename = "";
		if(isEmty(dire)){
			return "";
		}
		Integer direction = Integer.parseInt(dire);
		if(direction ==0){
			return "正北";
		}else if(direction >0 && direction <90){
			return "东北";
		}else if(direction == 90){
			return "正东";
		}else if(direction >90 && direction <180){
			return "东南";
		}else if(direction == 180){
			return "正南";
		}else if(direction >180 && direction <270){
			return "西南";
		}else if(direction == 270){
			return "正西";
		}else if(direction >270 && direction <360){
			return "西北";
		}else if(direction == 360){
			return "正北";
		}else{
			return "";
		}
	}
	
	/**
	 * 保留6位小数
	 */
	public static final double getSixFloat(double value)  {
		DecimalFormat fnum = new DecimalFormat("##0.000000"); 
		return Double.parseDouble(fnum.format(value)); 
	}
	
	/**
	 * 保留2位小数
	 */
	public static final double getTwoFloat(double value)  {
		DecimalFormat fnum = new DecimalFormat("##0.00"); 
		return Double.parseDouble(fnum.format(value)); 
	}
}
