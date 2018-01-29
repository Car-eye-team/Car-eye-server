/**
 * Description: car-eye车辆管理平台
 * 文件名：BaiDuHttp.java
 * 版本信息：1.0
 * 日期：2013-8-10
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.http;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.careye.common.domain.BaiDuInfo;
import com.careye.common.domain.PioPlace;
import com.careye.constant.ConfigProperties;



/**
 * @项目名称：car-eye
 * @类名称：BaiDuHttp
 * @类描述：请求百度接口
 * @创建人：zr
 * @创建时间：2013-8-10 下午02:02:25
 * @修改人：zr
 * @修改时间：2013-8-10 下午02:02:25
 * @修改备注：
 * @version 1.0
 */
public class BaiDuHttp {
	private static final Logger logger = Logger.getLogger(BaiDuHttp.class);

	/**
	 * 根据转换百度经纬度
	 * @author sj
	 * @param lng
	 * @param lat
	 * @return
	 * @date 2012-08-01
	 */

	public static String distanceByLngLatForbaidu(double lng, double lat) {
		String lnglat = null;
		try {
			HttpRequester request = new HttpRequester();
			StringBuffer sendParm = new StringBuffer();
			request.setDefaultContentEncoding("utf-8");
			sendParm.append("http://api.map.baidu.com/geoconv/v1/?coords=");
			sendParm.append(lng);
			sendParm.append(",");
			sendParm.append(lat);
			sendParm.append("&from=1&to=5&ak=");
			sendParm.append(ConfigProperties.MAPBAIDU_AK);
			System.out.println("请求URL="+sendParm.toString());
			HttpRespons hr = request.sendGet(sendParm.toString());
			String msg = hr.getContent();
			JSONObject jsonObject = JSONObject.fromObject(msg);
			JSONArray array = JSONArray.fromObject(jsonObject.get("result"));
			for(int i=0; i<array.size(); i++){
				JSONObject jot = (JSONObject)array.get(i);
				if (jot.get("x") != null && jot.get("y") != null) {
					lnglat = jot.get("x")+","+jot.get("y");
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return lnglat;
	}



	/**
	 * 百度根据经纬度获取详细地址描述并返回城市名称
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public static String geocodeBaiduCityName(double latitude, double longitude) {
		try {

			logger.info("百度根据经纬度获取详细地址描述并返回城市名称");
			String lnglat = distanceByLngLatForbaidu(longitude, latitude);
			if(lnglat!=null){
				String lng = lnglat.split(",")[0];
				String lat = lnglat.split(",")[1];

				HttpRequester request = new HttpRequester();
				StringBuffer sendParm = new StringBuffer();
				request.setDefaultContentEncoding("utf-8");
				sendParm.append("http://api.map.baidu.com/geocoder?");
				sendParm.append("location=");
				sendParm.append(lat);
				sendParm.append(",");
				sendParm.append(lng);
				sendParm.append("&output=json&key=");
				sendParm.append(ConfigProperties.MAPBAIDU_AK);
				//String url = "http://api.map.baidu.com/geocoder?location=" + lng + "," + lat+"&output=json&key="+ConfigProperties.MAPBAIDU_AK+"";
				logger.info("请求百度URL："+sendParm.toString());
				HttpRespons hr = request.sendGet(sendParm.toString());
				String test = hr.getContent();
				JSONObject jsonObject = JSONObject.fromObject(test);
				String address = jsonObject.getJSONObject("result").get("formatted_address").toString();
				return address.substring(address.indexOf("省") + 1, address.indexOf("市") + 1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 百度根据经纬度获取详细地址描述并返回城市名称
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public static String geocodeBaiduAddress(double latitude, double longitude) {
		try {
			logger.info("百度根据经纬度获取详细地址描述并返回城市地址");
			String lnglat = distanceByLngLatForbaidu(longitude, latitude);

			if(lnglat!=null){
				String lng = lnglat.split(",")[0];
				String lat = lnglat.split(",")[1];

				HttpRequester request = new HttpRequester();
				StringBuffer sendParm = new StringBuffer();
				request.setDefaultContentEncoding("utf-8");

				sendParm.append("http://api.map.baidu.com/geocoder?location=");
				sendParm.append(lng);
				sendParm.append(",");
				sendParm.append(lat);
				sendParm.append("&output=json&key=");
				sendParm.append(ConfigProperties.MAPBAIDU_AK);

				//String url = "http://api.map.baidu.com/geocoder?location=" + lng + "," + lat+"&output=json&key="+ConfigProperties.MAPBAIDU_AK+"";
				logger.info("请求百度URL："+sendParm.toString());
				HttpRespons hr = request.sendGet(sendParm.toString());
				String test = hr.getContent();
				JSONObject jsonObject = JSONObject.fromObject(test);
				String address = jsonObject.getJSONObject("result").get("formatted_address").toString();
				return address;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据城市内检索请求地址
	 * @param query
	 * @return
	 */

	public static List<PioPlace> getResultByCity(String query,String city) {
		List<PioPlace> list = new ArrayList<PioPlace>();
		try {
			HttpRequester request = new HttpRequester();
			StringBuffer sendParm = new StringBuffer();
			request.setDefaultContentEncoding("utf-8");
			sendParm.append(ConfigProperties.MAPBAIDU_CITYINFO_URL);
			sendParm.append(query);
			sendParm.append("&region=");
			sendParm.append(city);
			sendParm.append("&output=json&MAPBAIDU_AK=");
			sendParm.append(ConfigProperties.MAPBAIDU_AK);
			HttpRespons hr = request.sendGet(sendParm.toString());
			String msg = hr.getContent();
			JSONObject jsonObject = JSONObject.fromObject(msg);
			JSONArray array = JSONArray.fromObject(jsonObject.get("results"));
			for(int i=0; i<array.size(); i++){
				JSONObject jobject = (JSONObject)array.get(i);
				PioPlace pioPlace = new PioPlace();
				pioPlace.setName(jobject.getString("name"));
				JSONObject location = JSONObject.fromObject(jobject.get("location"));
				String lnglat = location.get("lng")+","+location.get("lat");
				pioPlace.setLnglat(lnglat);
				pioPlace.setAddress(jobject.containsKey("address")==false?"":jobject.getString("address"));
				pioPlace.setTelephone(jobject.containsKey("telephone")==false?"":jobject.getString("telephone"));
				list.add(pioPlace);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据经纬度获取百度相关信息（偏转后经纬度、所在省、市、县）
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public static BaiDuInfo getBaiDuInfo(double latitude, double longitude) {
		try {
			if(latitude != 0 && longitude !=0){
				BaiDuInfo baiDuInfo = new BaiDuInfo();
				HttpRequester request = new HttpRequester();
				StringBuffer sendParm = new StringBuffer();
				request.setDefaultContentEncoding("utf-8");

				sendParm.append("http://api.map.baidu.com/geocoder?location=");
				sendParm.append(latitude);
				sendParm.append(",");
				sendParm.append(longitude);
				sendParm.append("&output=json&key=");
				sendParm.append(ConfigProperties.MAPBAIDU_AK);
				//String url = "http://api.map.baidu.com/geocoder?location=" + lat + "," + lng+"&output=json&key="+ConfigProperties.MAPBAIDU_AK+"";
				//logger.info("请求百度解析经纬度信息URL："+sendParm.toString());
				HttpRespons hr = request.sendGet(sendParm.toString());
				String str = hr.getContent();
				JSONObject jsonObject = JSONObject.fromObject(str);

				String su = jsonObject.get("status").toString();

				if("OK".equals(su)){
					String address = jsonObject.getJSONObject("result").get("formatted_address").toString();
					String pcdStr = jsonObject.getJSONObject("result").get("addressComponent").toString();
					JSONObject json = JSONObject.fromObject(pcdStr);
					String province = json.get("province").toString();
					String city = json.get("city").toString();
					String district = json.get("district").toString();
					baiDuInfo.setAddress(address);
					baiDuInfo.setProvince(province);
					baiDuInfo.setCity(city);
					baiDuInfo.setDistrict(district);
					return baiDuInfo;
				}
			}
		} catch (Exception ex) {
			logger.info("根据经纬度获取百度相关信息（偏转后经纬度、所在省、市、县）异常");
			ex.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
//		List<PioPlace> list = BaiDuHttp.getResultByCity("酒店","深圳");
//		System.out.println(list.size());
//		BaiDuHttp.getBaiDuInfo(39.77763,116.29898);
	}


}






