/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsbusiness.utlis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public DateUtil() {

	}


	/**
	 * 测试用
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {

	}
	
	/**
	 * GPS实际那转换成年月日时间
	 * @param gpstime
	 * @return
	 */
	public static String gpsTimeToTime(String gpstime,String format){
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(Long.valueOf(gpstime));
			SimpleDateFormat df=new SimpleDateFormat(format);
			String datestring = df.format(calendar.getTime());
			return datestring;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 时间转换
	 * @param time
	 * @return
	 */
	public static String trafficTimeToTime(String time){
		StringBuffer datetimeBuffer = new StringBuffer();
		try {
			datetimeBuffer.append(time.split("/")[0]);
			datetimeBuffer.append("-");
			datetimeBuffer.append(time.split("/")[1]);
			datetimeBuffer.append("-");
			datetimeBuffer.append(time.split("/")[2]);
			datetimeBuffer.append(" ");
			datetimeBuffer.append(time.split("/")[3]);
			datetimeBuffer.append(":");
			datetimeBuffer.append(time.split("/")[4]);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datetimeBuffer.toString();
	}

	/**
	 * 把日期格式转换为字符串格式
	 * @param date
	 * @return
	 */
	public static String dateToString (Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}

	/**
	 * 把日期格式转换为YYMMDDHHMMSS格式
	 * @param date
	 * @return
	 */
	public static String dateToNumber (String time){
		SimpleDateFormat oldFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat newFormat  = new SimpleDateFormat("yyMMddHHmmss");
		Date date = new Date();
		try {
			date = oldFormat.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newFormat.format(date);
	}
	

	/**
	 * 日期格式化为标准格式 yyyy-MM-dd HH:mm:ss
	 * @param time
	 * @return
	 */
	public static String numberTodate(String time){
		SimpleDateFormat newFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat oldFormat  = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		try {
			date = oldFormat.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newFormat.format(date);
	}


	/**
	 * 根据有效期格式化时间
	 * @param type  1每年 2 每月 3 每天 4 每小时 5 每分钟
	 * @param time  yyMMddHHmmss
	 * @return
	 */
	public static String termvalidityFormat(int type,String time){
		String retime = null;
		try {

			if(type == 1){
				retime = "00"+time.substring(2, time.length());
			}else if(type == 2){
				retime = "0000"+time.substring(4, time.length());
			}else if(type == 3){
				retime = "000000"+time.substring(6, time.length());
			}else if(type == 4){
				retime = "00000000"+time.substring(8, time.length());
			}else if(type == 5){
				retime = "0000000000"+time.substring(10, time.length());
			}else {
				retime = time;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retime;

	}

	/**
	 * 把原始字符串扩展成自定的长度
	 * @param oriStr 原始字符串
	 * @param fillChar 要填充的字符
	 * @param setLength 要扩展成的长度
	 * @param preFill 前扩充还是后扩充 true-在前面填充，false-后面填充
	 * @return
	 */
	public static String fixStringToSetLength(String oriStr, String fillChar, int setLength, boolean preFill) {
		if(oriStr == null || fillChar.equals("")) {
			return oriStr;
		}

		int oriLength = oriStr.length();
		StringBuilder sb = null;
		if(preFill == true) {
			sb = new StringBuilder();
			for(int i = oriLength; i < setLength; i++) {
				sb.append(fillChar);
			}
			sb.append(oriStr);
		} else {
			sb = new StringBuilder(oriStr);
			for(int i = oriLength; i < setLength; i++) {
				sb.append(fillChar);
			}
		}

		return sb.toString();
	}



	//日期往后加多少个月，多少天，多少年
	@SuppressWarnings("static-access")
	public static String addMonth(String format,String StrDate,int year,int month,int day,int hours,int minute,int seconds){

		Calendar   cal = Calendar.getInstance(); 
		SimpleDateFormat sFmt = new SimpleDateFormat(format); 
		cal.setTime(sFmt.parse( (StrDate), new ParsePosition(0))); 
		if (seconds != 0) { 
			cal.add(cal.SECOND,seconds); 
		} 
		
		if (minute != 0) { 
			cal.add(cal.MINUTE,minute); 
		} 
		
		if (hours != 0) { 
			cal.add(cal.HOUR,hours); 
		} 

		if (day != 0) { 
			cal.add(cal.DATE,day); 
		} 
		if (month != 0) { 
			cal.add(cal.MONTH, month); 
		} 
		if (year != 0) { 
			cal.add(cal.YEAR, year); 
		} 
		return sFmt.format(cal.getTime()); 
	}
	/**
	 * 判断两个时间字符串之差
	 * @param date1
	 * @param date2
	 * @return 毫秒数
	 */
	public static Long dateDiff(String date1,String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return df.parse(date1).getTime() - df.parse(date2).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 和当前时间差 
	 * @param date
	 * @return 当前时间 - date 毫秒数
	 */
	public static Long currentTimeDiff(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return System.currentTimeMillis() -  df.parse(date).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 和当前时间差(不需要时分秒) 
	 * @param date
	 * @return 当前时间 - date 
	 * 
	 * 	 */
	public static Long currentTimeDiff2(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return System.currentTimeMillis() -  df.parse(date).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 日期比较
	 * @param DATE1
	 * @param DATE2
	 * @return DATE1 > DATE2 返回1 
	 */
	public static int compare_date(String DATE1, String DATE2) {


		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 将日期yyyy-MM-dd HH:mm:ss 格式化为 yyyyMMddHHmmss
	 * @param date
	 * @return
	 */
	public static String fomatDate(String date){
		String dateStr = null;
		if(date == null || "".equals(date)){
			dateStr = "";
		}else{
			try {
				dateStr = date.replaceAll(":", "").replaceAll("-", "").replaceAll(" ", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dateStr;
	}

	/**
	 * 获取插入数据库格式的时间
	 * @return
	 */
	public static String getSQLDate() {
		String systemdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		.format(Calendar.getInstance().getTime()); // 获取系统当前时间
		return systemdate;
	}

	/**
	 * 对月份进行加减
	 * @param i
	 * @return
	 * @throws ParseException 
	 */
	public static Date datePlusOrMinus(String systime ,Integer i) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date systDate =df.parse(systime);
		GregorianCalendar gc =new GregorianCalendar();
		gc.setTime(systDate);
		gc.add(2,i);
		return gc.getTime();
	}

	/**
	 * 得到月日时分秒10位时间戳字符串
	 */
	public static String getTenTimestamp() {
		String systemdate = new SimpleDateFormat("MMddHHmmss").format(Calendar.getInstance().getTime());
		return systemdate;
	}

	/**
	 * 计算两个日期之间相差的月数
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMonths(Date date1, Date date2) {
		int iMonth = 0;
		int flag = 0;
		try {
			Calendar objCalendarDate1 = Calendar.getInstance();
			objCalendarDate1.setTime(date1);

			Calendar objCalendarDate2 = Calendar.getInstance();
			objCalendarDate2.setTime(date2);

			if (objCalendarDate2.equals(objCalendarDate1))
				return 0;
			if (objCalendarDate1.after(objCalendarDate2)) {
				Calendar temp = objCalendarDate1;
				objCalendarDate1 = objCalendarDate2;
				objCalendarDate2 = temp;
			}
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1
					.get(Calendar.DAY_OF_MONTH))
				flag = 1;

			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1
					.get(Calendar.YEAR))
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1
						.get(Calendar.YEAR))
						* 12 + objCalendarDate2.get(Calendar.MONTH) - flag)
						- objCalendarDate1.get(Calendar.MONTH);
			else
				iMonth = objCalendarDate2.get(Calendar.MONTH)
				- objCalendarDate1.get(Calendar.MONTH) - flag;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return iMonth;
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static final int daysBetween(Date date1, Date date2) { 

		java.util.Calendar time1 = java.util.Calendar.getInstance();   
		java.util.Calendar time2 = java.util.Calendar.getInstance();  
		time1.setTime(date1);
		time2.setTime(date2);
		int days = 0;
		if(time1.getTime().getTime() >= time2.getTime().getTime()){
			days = ((int) (time1.getTime().getTime() / 1000) - (int) (time2.getTime().getTime() / 1000)) / 3600 / 24;   
		}else{
			days = ((int) (time2.getTime().getTime() / 1000) - (int) (time1.getTime().getTime() / 1000)) / 3600 / 24;   
		}
		return days;   
	} 

	/**
	 * 计算两个日期之间相差秒
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static final int secBetween(String sdate1, String sdate2) { 
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date1 = null;
		try {
			date1 = df.parse(sdate1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date2 = null;
		try {
			date2 = df.parse(sdate2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		java.util.Calendar time1 = java.util.Calendar.getInstance();   
		java.util.Calendar time2 = java.util.Calendar.getInstance();  
		time1.setTime(date1);
		time2.setTime(date2);

		int sec = 0;
		if(time1.getTime().getTime() >= time2.getTime().getTime()){
			sec = ((int) (time1.getTime().getTime() / 1000) - (int) (time2.getTime().getTime() / 1000));   
		}else{
			sec = ((int) (time2.getTime().getTime() / 1000) - (int) (time1.getTime().getTime() / 1000));   
		}
		return sec;   
	} 


}








