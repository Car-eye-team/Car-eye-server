/**
* Description: car-eye车辆管理平台
* 文件名：GpsDistance.java
* 版本信息：1.0
* 日期：2014-5-7
* Copyright car-eye车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.utils;

/**
 * @car-eye车辆管理平台业务处理Tms
 * @类名称：GpsDistance
 * @类描述：
 * @创建人：wuyongde
 * @创建时间：2014-5-7 下午02:45:07
 * @修改人：wuyongde
 * @修改时间：2014-5-7 下午02:45:07
 * @修改备注：
 * @version 1.0
 */
public class GpsDistance {
	
	 private static final double EARTH_RADIUS = 6378137;
	 
	 private static double rad(double d){
	      return d * Math.PI / 180.0;
	 }
	
	 /** 
      * 根据两点间经纬度坐标（double值），计算两点间距离，单位为公里
      * @param lng1
      * @param lat1
      * @param lng2
      * @param lat2
      * @return
      */
     public static double GetDistance(double lng1, double lat1, double lng2, double lat2){
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
         Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) /10000/1000;
        return s;
     }
     
     
     /** 
      * @param args
      */
     public static void main(String[] args){
         double distance = GetDistance(121.391909,31.033234,121.411994,31.206134);
         System.out.println("Distance is:" + distance);
     }
	 
}
