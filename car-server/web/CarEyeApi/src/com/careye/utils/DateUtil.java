package com.careye.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.felix.main.Main;

import com.careye.constant.Constant;

public class DateUtil {

	public DateUtil() {

	}

	/***
	 * 这里进行对自己进行正则表达
	 * @param oriStr
	 * @return
	 */
	public static String filterDistrictName(String oriStr) {

		if (!oriStr.endsWith("自治县") && oriStr.length() > 2) {
			String result = oriStr.replaceAll("(市|地区|县|市市辖区)$", "");
			return result;
		} else {
			return oriStr;
		}

	}
	
	/**
	 * 把日期格式转换为YYMMDDHHMMSS格式
	 * @param date
	 * @return
	 */
	public static String numberToDate12 (String time){
		if(time == null || time.equals("")){
			return "";
		}
		SimpleDateFormat newFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat oldFormat  = new SimpleDateFormat("yyMMddHHmmss");
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
	 * 将一个字符串中字母大小转换
	 * @param String
	 * @return
	 */
	public static String StringChange(String s){
		  char[] c=s.toCharArray();
		  for(int i=0;i<s.length();i++){
			    //小写转大写
			   //if(c[i]>='a'&&c[i]<='z')c[i]=Character.toUpperCase(c[i]);
			    //大写转小写
			    if(c[i]>='A'&&c[i]<='Z')c[i]=Character.toLowerCase(c[i]);
		   }
		  return String.valueOf(c);
	}
	
	/**
	 * 产生4位随机数
	 */
	public static String getFourRandom(){
		int random1 = (int) (Math.random() * 9000 + 1000);
		String random = String.valueOf(random1);
		return random;
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
	public static String addMonth(String format,String StrDate,int year,int month,int day){

		Calendar   cal = Calendar.getInstance(); 
		SimpleDateFormat sFmt = new SimpleDateFormat(format); 
		cal.setTime(sFmt.parse( (StrDate), new ParsePosition(0))); 

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
	 * 计算时间差，相差多少天、小时、分、秒
	 * @param stime
	 * @param etime
	 * @return
	 */
	public static String dateDiffStr(String stime,String etime){
		String diff = "";
		try {
			SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date begin=dfs.parse(stime);
			java.util.Date end = dfs.parse(etime);
			long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
			long day1=between/(24*3600);
			long hour1=between%(24*3600)/3600;
			long minute1=between%3600/60;
			long second1=between%60;
			System.out.println(""+day1+"天"+hour1+"小时"+minute1+"分"+second1+"秒");
			if(day1>0){
				diff = ""+day1+"天"+hour1+"小时"+minute1+"分"+second1+"秒";
				return diff;
			}
			
			if(hour1>0){
				diff = ""+hour1+"小时"+minute1+"分"+second1+"秒";
				return diff;
			}
			
			if(minute1>0){
				diff = ""+minute1+"分"+second1+"秒";
				return diff;
			}
			
			if(second1>=0){
				diff = ""+second1+"秒";
				return diff;
			}
			
			return diff;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diff;
		
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
	 * 产生6位随机数
	 */
	public static String getSixRandom(){
		int random1 = (int) (Math.random() * 900000 + 100000);
		String random = String.valueOf(random1);
		return random;
	}
	/**
	 * 产生2位随机数
	 */
	public static String getTwoRandom(){
		int random1 = (int) (Math.random() * 90 + 10);
		String random = String.valueOf(random1);
		return random;
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

	/**
	 * 
	 * @param date1 <String>
	 * @param date2 <String>
	 * @return int
	 * @throws ParseException
	 */
	public static int getMonthSpace(String date1, String date2)
	throws ParseException {

		int result = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(sdf.parse(date1));
		c2.setTime(sdf.parse(date2));

		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

		return result == 0 ? 1 : Math.abs(result);

	}

	/**
	 * 根据毫秒数计算时分秒
	 * @param timeMillis
	 * @return
	 */
	public static String myTimeStr(long timeMillis) {  
		int timezone = 8;  
		long totalSeconds = timeMillis / 1000;  
		totalSeconds += 60 * 60 * timezone;  
		int second = (int)(totalSeconds % 60);// 秒  
		long totalMinutes = totalSeconds / 60;  
		int minute = (int)(totalMinutes % 60);// 分  
		long totalHours = totalMinutes / 60;  
		int hour = (int)(totalHours % 24);// 时  
		int totalDays = (int)(totalHours / 24);  

		int _year = 1970;  

		int year = _year + totalDays / 366;  
		int month = 1;  
		int day = 1;  

		int diffDays;  
		boolean leapYear;  
		while (true) {  
			int diff = (year - _year) * 365;  
			diff += (year - 1) / 4 - (_year - 1) / 4;  
			diff -= ((year - 1) / 100 - (_year - 1) / 100);  
			diff += (year - 1) / 400 - (_year - 1) / 400;  

			diffDays = totalDays - diff;  

			leapYear = (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0);  
			if (!leapYear && diffDays < 365 || leapYear && diffDays < 366) {  
				break;  
			} else {  
				year++;  
			}  
		}  

		int[] monthDays;  
		if (diffDays >= 59 && leapYear) {  
			monthDays = new int[]{-1,0,31,60,91,121,152,182,213, 244, 274, 305, 335 };  
		} else {  
			monthDays = new int[]{-1,0,31,59,90,120,151,181,212, 243, 273, 304, 334 };  
		}  
		for (int i = monthDays.length - 1; i >= 1; i--) {  
			if (diffDays >= monthDays[i]) {  
				month = i;  
				day = diffDays - monthDays[i] + 1;  
				break;  
			}  
		}  

		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;  

	}  
	
	/**
	 * 根据时间差和5位随机数得到订单号
	 * @param stime
	 * @return
	 */
	public static String getOrderid() {
		String time =  String.valueOf(Calendar.getInstance().getTimeInMillis()).substring(0, 11);
		if(Constant.ORDERNO_MAP.contains(time)){
			try {
				Thread.sleep(100);
				time =  String.valueOf(Calendar.getInstance().getTimeInMillis()).substring(0, 11);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Constant.ORDERNO_MAP.add(time);
		return time;
	}
	
	/**
	 * 转成时间戳
	 * @param datetime
	 * @return
	 */
	public static long NumberTimeToTimeStamp(String datetime){
		try {
			SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyMMddHHmmss");
			Date date=simpleDateFormat.parse(datetime);
			long timeStemp = date.getTime()/1000;
			return timeStemp;
		} catch (Exception e) {
			e.printStackTrace();
			return System.currentTimeMillis()/1000 ;
		}
		
	}
	
	/**
	 * 时间戳转换成时间
	 * @param time
	 * @return
	 */
	public static String TimeStampToDateTime(long time){
		String datetime = null;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			datetime = sdf.format(new Date(time*1000)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datetime;
	}
	
	/**
	 * 得到当前年月日
	 */
	public static String getNowDate() {
		Date date = new Date();
		SimpleDateFormat newFormat  = new SimpleDateFormat("yyyy-MM-dd");
		return newFormat.format(date);
	}
	/**
	 * 得到当前年月
	 */
	public static String getNowMonth() {
		Date date = new Date();
		SimpleDateFormat newFormat  = new SimpleDateFormat("yyyy-MM");
		return newFormat.format(date);
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
	
	public static String numToDate (String time){
		SimpleDateFormat oldFormat  = new SimpleDateFormat("yyMMddHHmmss");
		SimpleDateFormat newFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
	 * 测试用
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		System.out.println(getOrderid());
		System.out.println(getOrderid());
	}



}








