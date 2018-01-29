/**
 * Description: 多森商用车平台
 * 文件名：ToolsUtil.java
 * 版本信息：1.0
 * 日期：2014-5-27
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.mq;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.careye.common.domain.TerminalParameter;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @项目名称：FMS
 * @类名称：ToolsUtil
 * @类描述：
 * @创建人：zr
 * @创建时间：2014-5-27 上午11:53:53
 * @修改人：zr
 * @修改时间：2014-5-27 上午11:53:53
 * @修改备注：
 * @version 1.0
 */
public class ToolsUtil {

	/**
	 * 发送流水号与消息记录
	 */
	public static HashMap<String, Integer> sendMap = new HashMap<String, Integer>();

	/**
	 * @判断是否有报警信息
	 * @param protocol
	 * @return boolean
	 */
	public static boolean getAlarmStatus(Protocol protocol) {

		if (protocol.getA0() == 1 || protocol.getA1() == 1 || protocol.getA2() == 1 || protocol.getA3() == 1
				|| protocol.getA4() == 1 || protocol.getA5() == 1 || protocol.getA6() == 1 || protocol.getA7() == 1
				|| protocol.getA8() == 1 || protocol.getA9() == 1 || protocol.getA10() == 1 || protocol.getA11() == 1
				|| protocol.getA18() == 1 || protocol.getA19() == 1 || protocol.getA20() == 1 || protocol.getA21() == 1
				|| protocol.getA22() == 1 || protocol.getA23() == 1 || protocol.getA24() == 1 || protocol.getA25() == 1
				|| protocol.getA26() == 1 || protocol.getA27() == 1 || protocol.getA28() == 1 || protocol.getA29() == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取报警信息项
	 * @param protocol
	 * @return
	 */
	public static List getAlarmStatusKey(Protocol protocol) {
		List list =new ArrayList<String>();
		if(protocol.getA0() == 1){
			list.add("a0");
		}

		if(protocol.getA1() == 1){
			list.add("a1");
		}

		if(protocol.getA2() == 1){
			list.add("a2");
		}

		if(protocol.getA3() == 1){
			list.add("a3");
		}

		if(protocol.getA4() == 1){
			list.add("a4");
		}

		if(protocol.getA5() == 1){
			list.add("a5");
		}

		if(protocol.getA6() == 1){
			list.add("a6");
		}
		if(protocol.getA7() == 1){
			list.add("a7");
		}

		if(protocol.getA8() == 1){
			list.add("a8");
		}
		if(protocol.getA9() == 1){
			list.add("a9");
		}
		if(protocol.getA10() == 1){
			list.add("a10");
		}
		if(protocol.getA11() == 1){
			list.add("a11");
		}
		if(protocol.getA18() == 1){
			list.add("a18");
		}
		if(protocol.getA19() == 1){
			list.add("a19");
		}
		if(protocol.getA20() == 1){
			list.add("a20");
		}
		if(protocol.getA21() == 1){
			list.add("a21");
		}
		if(protocol.getA22() == 1){
			list.add("a22");
		}
		if(protocol.getA23() == 1){
			list.add("a23");
		}
		if(protocol.getA24() == 1){
			list.add("a24");
		}
		if(protocol.getA25() == 1){
			list.add("a25");
		}
		if(protocol.getA26() == 1){
			list.add("a26");
		}
		if(protocol.getA27() == 1){
			list.add("a27");
		}
		if(protocol.getA28() == 1){
			list.add("a28");
		}
		if(protocol.getA29() == 1){
			list.add("a29");
		}

		return list;
	}

	/**
	 * 判断是否存在状态标志
	 * @param protocol
	 * @return
	 */
	public static boolean getStatus(Protocol protocol){
		if(protocol.getS0() == 0 || protocol.getS0() == 1 || 
				protocol.getS1() == 0 || protocol.getS1() == 1 ||
				protocol.getS2() == 0 || protocol.getS2() == 1 ||
				protocol.getS3() == 0 || protocol.getS3() == 1 ||
				protocol.getS4() == 0 || protocol.getS4() == 1 ||
				protocol.getS5() == 0 || protocol.getS5() == 1 ||
				protocol.getS10() == 0 || protocol.getS10() == 1 ||
				protocol.getS11() == 0 || protocol.getS11() == 1 ||
				protocol.getS12() == 0 || protocol.getS12() == 1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 获取状态项
	 * @param protocol
	 * @return
	 */
	public static String getStatusKey(Protocol protocol){

		if(protocol.getS0() == 0 || protocol.getS0() == 1){
			return "s0,"+protocol.getS0();
		}else if(protocol.getS1() == 0 || protocol.getS1() == 1){
			return "s1,"+protocol.getS1();
		}else if(protocol.getS2() == 0 || protocol.getS2() == 1){
			return "s2,"+protocol.getS2();
		}else if(protocol.getS3() == 0 || protocol.getS3() == 1){
			return "s3,"+protocol.getS3();
		}else if(protocol.getS4() == 0 || protocol.getS4() == 1){
			return "s4,"+protocol.getS4();
		}else if(protocol.getS5() == 0 || protocol.getS5() == 1){
			return "s5,"+protocol.getS5();
		}else if(protocol.getS10() == 0 || protocol.getS10() == 1){
			return "s10,"+protocol.getS10();
		}else if(protocol.getS11() == 0 || protocol.getS11() == 1){
			return "s11,"+protocol.getS11();
		}else if(protocol.getS12() == 0 || protocol.getS12() == 1){
			return "s12,"+protocol.getS12();
		}
		return null;
	}


	/**
	 * 返回当前时间
	 * @param
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public static String returnNowDate() {
		Date now = new Date();
		return (now.getYear() + 1900) + "-" + (now.getMonth() + 1) + "-" + now.getDate();
	}

	/**
	 * 返回当前时间
	 * @param
	 * @return long
	 */
	public static long returnNow() {
		return System.currentTimeMillis() / 1000;
	}

	public static boolean isNumeric(String str) {

		Pattern pattern = Pattern.compile("^\\d+$|^\\d+\\.\\d+$|-\\d+$");
		return pattern.matcher(str).matches();

	}

	/**
	 * 根据选中的编号查找对应的文本值
	 * @param
	 */
	public static String getTerminalValue(int terminal, TerminalParameter terminalInfo) {

		String temp = "";

		switch (terminal) {

		case 1:
			temp = String.valueOf(terminalInfo.getTerminalHeatTime());
			break;

		case 2:
			temp = String.valueOf(terminalInfo.getTcpTimeOut());
			break;

		case 3:
			temp = String.valueOf(terminalInfo.getTcpResend());
			break;

		case 4:
			temp = String.valueOf(terminalInfo.getUdpTimeOut());
			break;

		case 5:
			temp = String.valueOf(terminalInfo.getUdpResend());
			break;

		case 6:
			temp = String.valueOf(terminalInfo.getSmsTimeOut());
			break;

		case 7:
			temp = String.valueOf(terminalInfo.getSmsRecount());
			break;
			
		case 16:
			temp = terminalInfo.getApnParam() + "#";
			break;

		case 17:
			temp = terminalInfo.getMainLogin() + "#";
			break;

		case 18:
			temp = terminalInfo.getMainPwd() + "#";
			break;

		case 19:
			temp = terminalInfo.getMainAddr() + "#";
			break;

		case 20:
			temp = terminalInfo.getBackApnParam() + "#";
			break;

		case 21:

			temp = terminalInfo.getBackLogin() + "#";
			break;

		case 22:
			temp = terminalInfo.getBackPwd() + "#";
			break;

		case 23:
			temp = terminalInfo.getBackAddr() + "#";
			break;
			
		case 24:
			temp = String.valueOf(terminalInfo.getTcpPort());
			break;
			
		case 25:
			temp = String.valueOf(terminalInfo.getUdpPort());
			break;

		case 32:
			temp = String.valueOf(terminalInfo.getPointUp());
			break;

		case 33:
			temp = String.valueOf(terminalInfo.getPointUpScheme());
			break;
			
		case 34:
			temp = String.valueOf(terminalInfo.getUnLoginTime());
			break;

		case 39:
			temp = String.valueOf(terminalInfo.getSleepUp());
			break;

		case 40:
			temp = String.valueOf(terminalInfo.getEmerSecond());
			break;

		case 41:
			temp = String.valueOf(terminalInfo.getDefaultSecondUp());
			break;

		case 44:
			temp = String.valueOf(terminalInfo.getDefaultUpMile());
			break;

		case 45:
			temp = String.valueOf(terminalInfo.getUnLoginSecond());
			break;

		case 46:
			temp = String.valueOf(terminalInfo.getSleepUpMile());
			break;

		case 47:
			temp = String.valueOf(terminalInfo.getEmerMileSecond());
			break;

		case 48:
			temp = String.valueOf(terminalInfo.getInflection());
			break;

		case 64:
			temp = terminalInfo.getPlatMonitorPhone() + "#";
			break;

		case 65:
			temp = terminalInfo.getResetPhone() + "#";
			break;

		case 66:
			temp = terminalInfo.getRecovePhone() + "#";
			break;

		case 67:
			temp = terminalInfo.getPlatformSmsPhone() + "#";
			break;

		case 68:
			temp = terminalInfo.getSmsPhone() + "#";
			break;

		case 69:
			temp = String.valueOf(terminalInfo.getTerminalPick());
			break;

		case 70:
			temp = String.valueOf(terminalInfo.getLongSecond());
			break;

		case 71:
			temp = String.valueOf(terminalInfo.getMonthSecond());
			break;

		case 72:
			temp = terminalInfo.getMonitorPhone() + "#";
			break;

		case 73:
			temp = terminalInfo.getPlatformAdminSmsPhone() + "#";
			break;

		case 85:
			temp = String.valueOf(terminalInfo.getHighSpeed());
			break;

		case 86:
			temp = String.valueOf(terminalInfo.getSuperSpeedSecood());
			break;

		case 87:
			temp = String.valueOf(terminalInfo.getContinuTimeTop());
			break;

		case 88:
			temp = String.valueOf(terminalInfo.getTotalDriverTime());
			break;

		case 89:
			temp = String.valueOf(terminalInfo.getSmallSleepTime());
			break;

		case 90:
			temp = String.valueOf(terminalInfo.getLongunDriverSecond());
			break;

		case 128:
			temp = String.valueOf(terminalInfo.getCarMile());
			break;

		case 129:
			temp = String.valueOf(terminalInfo.getCarProvinteId());
			break;

		case 130:
			temp = String.valueOf(terminalInfo.getCarCityId());
			break;

		case 131:
			temp = String.valueOf(terminalInfo.getCarCard());
			break;

		case 132:
			temp = String.valueOf(terminalInfo.getCarColor());
			break;

		}

		return temp;
	}

	/**
	 * 根据选中的编号查找对应的文本值
	 * @param
	 */
	public static TerminalParameter setTerminalInfo(int terminal, String value, TerminalParameter terminalInfo) {

		switch (terminal) {

		case 1:
			terminalInfo.setTerminalHeatTime(Integer.parseInt(value));
			break;

		case 2:
			terminalInfo.setTcpTimeOut(Integer.parseInt(value));
			break;

		case 3:
			terminalInfo.setTcpResend(Integer.parseInt(value));
			break;

		case 4:
			terminalInfo.setUdpTimeOut(Integer.parseInt(value));
			break;

		case 5:
			terminalInfo.setUdpResend(Integer.parseInt(value));
			break;

		case 6:
			terminalInfo.setSmsTimeOut(Integer.parseInt(value));
			break;
			
		case 7:
			terminalInfo.setSmsRecount(Integer.parseInt(value));
			break;

		case 16:
			terminalInfo.setApnParam(value);
			break;

		case 17:
			terminalInfo.setMainLogin(value);
			break;

		case 18:
			terminalInfo.setMainPwd(value);
			break;

		case 19:
			terminalInfo.setMainAddr(value);
			break;

		case 20:
			terminalInfo.setBackApnParam(value);
			break;

		case 21:
			terminalInfo.setBackLogin(value);
			break;

		case 22:
			terminalInfo.setBackPwd(value);
			break;

		case 23:
			terminalInfo.setBackAddr(value);
			break;
			
		case 24:
			terminalInfo.setTcpPort(value);
			break;
			
		case 25:
			terminalInfo.setUdpPort(value);
			break;

		case 32:
			terminalInfo.setPointUp(Integer.parseInt(value));
			break;

		case 33:
			terminalInfo.setPointUpScheme(Integer.parseInt(value));
			break;
			
		case 34:
			terminalInfo.setUnLoginTime(Integer.parseInt(value));
			break;

		case 39:
			terminalInfo.setSleepUp(Integer.parseInt(value));
			break;

		case 40:
			terminalInfo.setEmerSecond(Integer.parseInt(value));
			break;

		case 41:
			terminalInfo.setDefaultSecondUp(Integer.parseInt(value));
			break;

		case 44:
			terminalInfo.setDefaultUpMile(Integer.parseInt(value));
			break;

		case 45:
			terminalInfo.setUnLoginSecond(Integer.parseInt(value));
			break;

		case 46:
			terminalInfo.setSleepUpMile(Integer.parseInt(value));
			break;

		case 47:
			terminalInfo.setEmerMileSecond(Integer.parseInt(value));
			break;

		case 48:
			terminalInfo.setInflection(Integer.parseInt(value));
			break;

		case 64:
			terminalInfo.setPlatMonitorPhone(value);
			break;

		case 65:
			terminalInfo.setResetPhone(value);
			break;

		case 66:
			terminalInfo.setRecovePhone(value);
			break;

		case 67:
			terminalInfo.setPlatformSmsPhone(value);
			break;

		case 68:
			terminalInfo.setSmsPhone(value);
			break;

		case 69:
			terminalInfo.setTerminalPick(Integer.parseInt(value));
			break;

		case 70:
			terminalInfo.setLongSecond(Integer.parseInt(value));
			break;

		case 71:
			terminalInfo.setMonthSecond(Integer.parseInt(value));
			break;

		case 72:
			terminalInfo.setMonitorPhone(value);
			break;

		case 73:
			terminalInfo.setPlatformAdminSmsPhone(value);
			break;

		case 85:
			terminalInfo.setHighSpeed(Integer.parseInt(value));
			break;

		case 86:
			terminalInfo.setSuperSpeedSecood(Integer.parseInt(value));
			break;

		case 87:
			terminalInfo.setContinuTimeTop(Integer.parseInt(value));
			break;

		case 88:
			terminalInfo.setTotalDriverTime(Integer.parseInt(value));
			break;

		case 89:
			terminalInfo.setSmallSleepTime(Integer.parseInt(value));
			break;

		case 90:
			terminalInfo.setLongunDriverSecond(Integer.parseInt(value));
			break;

		case 128:
			terminalInfo.setCarMile(Integer.parseInt(value));
			break;

		case 129:
			terminalInfo.setCarProvinteId(Integer.parseInt(value));
			break;

		case 130:
			terminalInfo.setCarCityId(Integer.parseInt(value));
			break;

		case 131:
			terminalInfo.setCarCard(value);
			break;

		case 132:
			terminalInfo.setCarColor(Integer.parseInt(value));
			break;

		case 135:
			terminalInfo.setInsurancePhone(value);
			break;

		}
		return terminalInfo;
	}
	
	/**
	 * 根据协议id得到参数名称
	 */
	public static String getTypeById(int id) {
		String type = "";
		switch (id) {
		case 16:
			type = "主服务器APN";
			break;
			
		case 19:
			type = "主服务器地址,IP或域名";
			break;
			
		case 21:
			type = "备份服务器APN";
			break;
			
		case 23:
			type = "备份服务器地址,IP或域名";
			break;
			
		case 24:
			type = "服务器TCP端口";
			break;
			
		case 26:
			type = "道路运输证IC卡认证主服务器IP地址或域名";
			break;
			
		case 27:
			type = "道路运输证IC卡认证主服务器TCP端口";
			break;
			
		case 28:
			type = "道路运输证IC卡认证主服务器UDP端口";
			break;
			
		case 29:
			type = "道路运输证IC卡认证备份服务器IP地址";
			break;
			
		case 34:
			type = "驾驶员未登录汇报时间间隔，单位为秒（s）";
			break;
			
		case 39:
			type = "休眠时汇报时间间隔，单位为秒（s）";
			break;
			
		case 40:
			type = "紧急报警时汇报时间间隔，单位为秒（s）";
			break;
			
		case 41:
			type = "缺省时间汇报间隔，单位为秒（s）";
			break;
			
		case 44:
			type = "缺省距离汇报间隔，单位为米（m）";
			break;
			
		case 45:
			type = "驾驶员未登录汇报距离间隔，单位为米（m）";
			break;
			
		case 46:
			type = "休眠时汇报距离间隔，单位为米（m）";
			break;
			
		case 47:
			type = "紧急报警时汇报距离间隔，单位为米（m）";
			break;
			
		case 64:
			type = "监控平台电话号码";
			break;
			
		case 65:
			type = "复位电话号码";
			break;
			
		case 66:
			type = "恢复出厂设置电话号码";
			break;
			
		case 67:
			type = "监控平台SMS电话号码";
			break;
			
		case 68:
			type = "接收终端SMS文本报警号码";
			break;
			
		case 69:
			type = "终端电话接听策略";
			break;
			
		case 70:
			type = "每次最长通话时间";
			break;
			
		case 71:
			type = "当月最长通话时间";
			break;
			
		case 72:
			type = "监听电话号码";
			break;
			
		case 73:
			type = "监管平台特权短信号码";
			break;
			
		case 85:
			type = "最高速度";
			break;
			
		case 86:
			type = "超速持续时间";
			break;
			
		case 87:
			type = "连续驾驶时间门限";
			break;
			
		case 88:
			type = "当天累计驾驶时间门限";
			break;
			
		case 89:
			type = "最小休息时间";
			break;
			
		case 90:
			type = "最长停车时间";
			break;
			
		case 91:
			type = "超速报警预警差值";
			break;
			
		case 92:
			type = "疲劳驾驶预警差值";
			break;
			
		case 93:
			type = "碰撞报警参数设置";
			break;
			
		case 94:
			type = "侧翻报警参数设置";
			break;
			
			
		default:
			break;
		}
		return type;
	}

}
