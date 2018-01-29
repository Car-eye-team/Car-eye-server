/**
 * Description: 出租车系统
 * 文件名：ToolsUtil.java
 * 版本信息：1.0
 * 日期：2015-3-14
 * Copyright car-eye车辆管理平台 Copyright (c) 2015
 * 版权所有
 */
package com.careye.mq.utils;

import java.util.ArrayList;
import java.util.List;

import com.careye.car.domain.CarInfo;
import com.careye.mq.domain.Protocol;
import com.careye.mq.domain.TerminalParameter;

/**
 * @项目名称：TAXISERVER
 * @类名称：ToolsUtil
 * @类描述：消息处理工具类
 * @创建人：zr
 * @创建时间：2015-3-14 下午01:48:17
 * @修改人：zr
 * @修改时间：2015-3-14 下午01:48:17
 * @修改备注：
 * @version 1.0
 */
public class ToolsUtil {

	public static int seqn = 0;

	//获得发送序列号
	public static int getSerialId(){

		if(seqn > 65000){
			seqn = 0;
		}
		seqn++;
		return seqn;
	}

	/**
	 * 更改缓存中的车辆状态
	 * @param terminal 设备号
	 * @param carstatus 车辆状态
	 */
	public static void updateCarStatus(String terminal,int carstatus,int kzstate){

		try {
			CarInfo cInfo = null;
//			RedisManager redisManager = RedisManager.getInstance();
//			cInfo = (CarInfo)redisManager.getObject("dstaxi_"+terminal);
			if(cInfo!=null){
				cInfo.setCarstatus(carstatus);
				cInfo.setKzstate(kzstate);
//				redisManager.setObject("dstaxi_"+cInfo.getTerminal(), cInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @判断是否有报警信息
	 * @param protocol
	 * @return boolean
	 */
	public static boolean getAlarmStatus(Protocol protocol) {

		if (protocol.getA0() == 1 || protocol.getA1() == 1 || protocol.getA2() == 1 || protocol.getA3() == 1
				|| protocol.getA4() == 1 || protocol.getA5() == 1 || protocol.getA6() == 1 || protocol.getA7() == 1
				|| protocol.getA8() == 1 || protocol.getA9() == 1 || protocol.getA10() == 1 || protocol.getA11() == 1
				|| protocol.getA12() == 1|| protocol.getA13() == 1|| protocol.getA14() == 1
				|| protocol.getA18() == 1 || protocol.getA19() == 1 || protocol.getA20() == 1 || protocol.getA21() == 1
				|| protocol.getA22() == 1 || protocol.getA23() == 1 || protocol.getA24() == 1 || protocol.getA25() == 1
				|| protocol.getA26() == 1 || protocol.getA27() == 1 || protocol.getA15() == 1
				|| protocol.getA16() == 1 || protocol.getA17() == 1) {
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
	public static List<String> getAlarmStatusKey(Protocol protocol) {

		List<String> list =new ArrayList<String>();
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
		if(protocol.getA12() == 1){
			list.add("a12");
		}
		if(protocol.getA13() == 1){
			list.add("a13");
		}
		if(protocol.getA14() == 1){
			list.add("a14");
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
		if(protocol.getA15() == 1){
			list.add("a15");
		}
		if(protocol.getA16() == 1){
			list.add("a16");
		}
		if(protocol.getA17() == 1){
			list.add("a17");
		}

		return list;
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
			terminalInfo.setMonthSecond(Integer.parseInt("0"));
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

}
