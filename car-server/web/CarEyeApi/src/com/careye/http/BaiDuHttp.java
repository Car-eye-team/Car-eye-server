/**
 * Description: 多森商用车平台
 * 文件名：BaiDuHttp.java
 * 版本信息：1.0
 * 日期：2013-8-10
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.careye.constant.ConfigProperties;
import com.careye.constant.Constant;
import com.careye.http.domain.BaiDuInfo;
import com.careye.http.domain.Point;
import com.careye.http.domain.TrafficInfo;
import com.careye.utils.Base64Tool;
import com.careye.utils.DateUtil;
import com.careye.utils.StringUtils;


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
	 * 调用百度API获取当前客户端所在城市及经纬度信息
	 * @return
	 */
	public static String getBaiDuCityInfo(String ip){

		logger.info("访问者IP："+ip);

		String cityInfo = null;
		OutputStream out = null;

		StringBuffer sendParm = new StringBuffer();
		try {
			sendParm.append(ConfigProperties.MAPBAIDU_CITYINFO_URL);
			sendParm.append(ip);
			URL url = new URL(sendParm.toString());
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			//设置连接主机超时（单位：毫秒）
			con.setConnectTimeout(Constant.CONNECT_TIMEOUT);
			//设置从主机读取数据超时（单位：毫秒）
			con.setReadTimeout(Constant.READ_TIMEOUT); 
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			out = con.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = "";
			for (line = br.readLine(); line != null; line = br.readLine()) {
				cityInfo = StringUtils.decodeUnicode(line);
			}
			logger.info("调用百度API获取当前客户端所在城市及经纬度信息="+cityInfo);

		}catch (Exception e) {
			e.printStackTrace();
			logger.error("调用百度API获取当前客户端所在城市及经纬度信息异常",e);
		}finally{
			try {
				if (out != null){
					out.close();
					out = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return cityInfo;

	}

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
			sendParm.append("&output=json");
			HttpRespons hr = request.sendGet(sendParm.toString());
			//logger.info("转换百度经纬度URL:"+sendParm.toString());
			String msg = hr.getContent();
			JSONObject jsonObject = JSONObject.fromObject(msg);
			JSONArray array = JSONArray.fromObject(jsonObject.get("result"));
			for(int i=0; i<array.size(); i++){
				JSONObject jot = (JSONObject)array.get(i);
				if (jot.get("x") != null && jot.get("y") != null) {
					lnglat = jot.get("x")+","+jot.get("y");
				}
			}
			return lnglat;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String gpsByLngLatForbaidu(double lng, double lat) {
		String lnglat = null;
		try {
			HttpRequester request = new HttpRequester();
			StringBuffer sendParm = new StringBuffer();
			request.setDefaultContentEncoding("utf-8");
			sendParm.append("http://api.map.baidu.com/ag/coord/convert?from=0&to=4&mode=1");
			sendParm.append("&x=" + lng);
			sendParm.append("&y=" + lat);
			HttpRespons hr = request.sendGet(sendParm.toString());
			String test = hr.getContent();
			JSONArray array = JSONArray.fromObject(test);

			for(int i=0; i<array.size(); i++){
				JSONObject jot = (JSONObject)array.get(i);
				if (jot.get("x") != null && jot.get("y") != null) {

					String x = Base64Tool.getFromBASE64(jot.get("x").toString());
					String y = Base64Tool.getFromBASE64(jot.get("y").toString());
					lnglat = x+","+y;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return lnglat;
	}

	
	/**
	 * 批量转换经纬度
	 * @param list
	 * @param lats
	 * @param lngs
	 * @return
	 */
	public static List<Point> distanceByLngLatListForbaidu(List<Point> list,String lats, String lngs) {
		try {
			HttpRequester request = new HttpRequester();
			StringBuffer sendParm = new StringBuffer();
			request.setDefaultContentEncoding("utf-8");
			sendParm.append("http://api.map.baidu.com/ag/coord/convert?from=2&to=4&mode=1");
			sendParm.append("&x=" + lngs);
			sendParm.append("&y=" + lats);
			HttpRespons hr = request.sendGet(sendParm.toString());
			String test = hr.getContent();
			JSONArray array = JSONArray.fromObject(test);

			for(int i=0; i<array.size(); i++){
				JSONObject jot = (JSONObject)array.get(i);
				if (jot.get("x") != null && jot.get("y") != null) {

					list.get(i).setLng(Double.valueOf(Base64Tool.getFromBASE64(jot.get("x").toString())));
					list.get(i).setLat(Double.valueOf(Base64Tool.getFromBASE64(jot.get("y").toString())));
				}
			}
			return list;
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
	public static String geocodeBaiduCityName(double latitude, double longitude) {
		try {

			//logger.info("百度根据经纬度获取详细地址描述并返回城市名称");
			String lnglat = distanceByLngLatForbaidu(longitude, latitude);
			if(lnglat == null){
				lnglat = gpsByLngLatForbaidu(longitude, latitude);
			}
			if(lnglat!=null){
				String lng = lnglat.split(",")[0];
				String lat = lnglat.split(",")[1];

				HttpRequester request = new HttpRequester();
				StringBuffer sendParm = new StringBuffer();
				request.setDefaultContentEncoding("utf-8");
				sendParm.append("http://api.map.baidu.com/geocoder?location=");
				sendParm.append(lat);
				sendParm.append(",");
				sendParm.append(lng);
				sendParm.append("&output=json&key=");
				sendParm.append(ConfigProperties.MAPBAIDU_AK);
				//String url = "http://api.map.baidu.com/geocoder?location=" + lat + "," + lng+"&output=json&key="+ConfigProperties.MAPBAIDU_AK+"";
				//logger.info("请求百度解析经纬度信息URL："+sendParm.toString());
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
			//logger.info("百度根据经纬度获取详细地址描述并返回城市地址");
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
				//logger.info("请求百度URL："+sendParm.toString());
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
	 * 根据城市名称 地址获取百度经纬度信息
	 * @param city
	 * @param address
	 * @return
	 */
	public static BaiDuInfo getGeocoding(String city,String address){

		try {

			BaiDuInfo baiDuInfo = new BaiDuInfo();
			HttpRequester request = new HttpRequester();
			StringBuffer sendParm = new StringBuffer();
			request.setDefaultContentEncoding("utf-8");
			sendParm.append("http://api.map.baidu.com/geocoder/v2/?ak=");
			sendParm.append(ConfigProperties.MAPBAIDU_AK);
			sendParm.append("&output=json&address=");
			sendParm.append(address);
			sendParm.append("&city=");
			sendParm.append(city);
			//logger.info("请求百度URL："+sendParm.toString());
			HttpRespons hr = request.sendGet(sendParm.toString());
			String str = hr.getContent();

			logger.info("str=="+str);
			JSONObject jsonObject = JSONObject.fromObject(str);
			String su = jsonObject.get("status").toString();

			if("0".equals(su)){
				String location = jsonObject.getJSONObject("result").get("location").toString();
				JSONObject locationObject = JSONObject.fromObject(location);
				String lng = locationObject.get("lng").toString();
				String lat = locationObject.get("lat").toString();
				baiDuInfo.setBlng(Double.parseDouble(lng));
				baiDuInfo.setBlat(Double.parseDouble(lat));
			}

			return baiDuInfo;

		} catch (Exception e) {
			logger.info("根据城市名称 地址获取百度经纬度信息",e);
			e.printStackTrace();
			return null;
		}

	}


	/**
	 * 根据经纬度获取百度相关信息（偏转后经纬度、所在省、市、县）
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public static BaiDuInfo getBaiDuInfo(double latitude, double longitude) {

		if(latitude != 0 && longitude !=0){
			BaiDuInfo baiDuInfo = new BaiDuInfo();

			String lnglat = distanceByLngLatForbaidu(longitude, latitude);
			if(lnglat == null){
				lnglat = gpsByLngLatForbaidu(longitude, latitude);
			}

			if(lnglat!=null){
				String lng = lnglat.split(",")[0];
				String lat = lnglat.split(",")[1];

				baiDuInfo.setBlng(Double.valueOf(lng));
				baiDuInfo.setBlat(Double.valueOf(lat));

				//保留6位
				try {
					BigDecimal bglng = new BigDecimal(baiDuInfo.getBlng());
					double blng = bglng.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
					baiDuInfo.setBlng(blng);

					BigDecimal bglat = new BigDecimal(baiDuInfo.getBlat());
					double blat = bglat.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
					baiDuInfo.setBlat(blat);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					HttpRequester request = new HttpRequester();
					StringBuffer sendParm = new StringBuffer();
					request.setDefaultContentEncoding("utf-8");

					sendParm.append("http://api.map.baidu.com/geocoder/v2/?location=");
					sendParm.append(lat);
					sendParm.append(",");
					sendParm.append(lng);
					sendParm.append("&output=json&ak=");
					sendParm.append(ConfigProperties.MAPBAIDU_AK);
					//logger.info("请求百度解析经纬度信息URL："+sendParm.toString());
					HttpRespons hr = request.sendGet(sendParm.toString());
					String str = hr.getContent();

					if(str == null){
						baiDuInfo.setAddress("");
						baiDuInfo.setProvince("");
						baiDuInfo.setCity("");
						baiDuInfo.setDistrict("");
					}else{
						JSONObject jsonObject = JSONObject.fromObject(str);

						String su = jsonObject.get("status").toString();

						if("0".equals(su)){
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
						}else {
							baiDuInfo.setAddress("");
							baiDuInfo.setProvince("");
							baiDuInfo.setCity("");
							baiDuInfo.setDistrict("");
						}
					}

				} catch (Exception ex) {
					logger.info("====根据经纬度获取百度相关信息（偏转后经纬度、所在省、市、县）异常=====");
					ex.printStackTrace();
					baiDuInfo.setAddress("");
					baiDuInfo.setProvince("");
					baiDuInfo.setCity("");
					baiDuInfo.setDistrict("");
				}

				return baiDuInfo;
			}else {
				return baiDuInfo;
			}
		}

		return null;
	}


	/**
	 * 失败后重新获取，根据经纬度获取百度相关信息（偏转后经纬度、所在省、市、县）
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public static BaiDuInfo getBaiDuInfoBak(double latitude, double longitude) {
		try {
			if(latitude != 0 && longitude !=0){
				BaiDuInfo baiDuInfo = new BaiDuInfo();

				String lnglat = distanceByLngLatForbaidu(longitude, latitude);
				if(lnglat == null){
					lnglat = gpsByLngLatForbaidu(longitude, latitude);
				}
				
				if(lnglat!=null){
					String lng = lnglat.split(",")[0];
					String lat = lnglat.split(",")[1];

					baiDuInfo.setBlng(Double.valueOf(lng));
					baiDuInfo.setBlat(Double.valueOf(lat));

					//保留6位
					try {
						BigDecimal bglng = new BigDecimal(baiDuInfo.getBlng());
						double blng = bglng.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
						baiDuInfo.setBlng(blng);

						BigDecimal bglat = new BigDecimal(baiDuInfo.getBlat());
						double blat = bglat.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
						baiDuInfo.setBlat(blat);
					} catch (Exception e) {
						e.printStackTrace();
					}

					HttpRequester request = new HttpRequester();
					StringBuffer sendParm = new StringBuffer();
					request.setDefaultContentEncoding("utf-8");

					sendParm.append("http://api.map.baidu.com/geocoder?location=");
					sendParm.append(lat);
					sendParm.append(",");
					sendParm.append(lng);
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
					}else {
						baiDuInfo = null;
					}
					return baiDuInfo;
				}else {
					return null;
				}
			}
		} catch (Exception ex) {
			logger.info("根据经纬度获取百度相关信息（偏转后经纬度、所在省、市、县）异常");
			ex.printStackTrace();
		}
		return null;
	}



	/**
	 * 获取路况信息
	 * @param lng 经度
	 * @param lat 纬度
	 * @param direction 方向
	 * @param speed 速度
	 * @return
	 */
	public static List<TrafficInfo> getTrafficInfo(double lng, double lat,int direction,String speed,int uid){
		List<TrafficInfo> list = new ArrayList<TrafficInfo>();
		try {

			HttpRequester request = new HttpRequester();
			BaiDuInfo baiDuInfo = BaiDuHttp.getBaiDuInfo(lat, lng);
			String cityname = baiDuInfo.getCity();

			StringBuffer sendParm = new StringBuffer();
			request.setDefaultContentEncoding("utf-8");
			sendParm.append("http://api.map.baidu.com/telematics/v3/trafficEvent?location=");
			sendParm.append(cityname);
			sendParm.append("&output=json&ak=");
			sendParm.append(ConfigProperties.MAPBAIDU_AK);
			HttpRespons hr = request.sendGet(sendParm.toString());
			logger.info("请求路况URL:"+sendParm.toString());
			String msg = hr.getContent();
			System.out.println("msg=="+msg);
			JSONObject jsonObject = JSONObject.fromObject(msg);
			String su = jsonObject.get("status").toString();
			if("success".equals(su)){
				if(jsonObject.get("results")!= null){
					String location = jsonObject.get("results").toString();
					System.out.println(location);
					JSONArray array = JSONArray.fromObject(location);

					for (int i = 0; i < array.size(); i++) {
						try {
							JSONObject jot = (JSONObject)array.get(i);
							TrafficInfo trafficInfo = new TrafficInfo();

							if(jot.get("startTime") != null){
								trafficInfo.setStartTime(DateUtil.trafficTimeToTime(jot.get("startTime").toString()));
							}

							if(jot.get("endTime") != null){
								trafficInfo.setEndTime(DateUtil.trafficTimeToTime(jot.get("endTime").toString()));
							}

							if(jot.get("title") != null){
								trafficInfo.setGeoName(jot.get("title").toString());
							}

							if(jot.get("description") != null){
								trafficInfo.setReportText(jot.get("description").toString());
							}
							list.add(trafficInfo);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 调用道道通接口
	 * 根据经纬度获取城市名称
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public static String City(double longitude, double latitude) {
		try {
			HttpRequester request = new HttpRequester();
			StringBuffer sendParm = new StringBuffer();
			request.setDefaultContentEncoding("utf-8");
			// api.365ditu.mobi
			sendParm.append("http://211.147.248.105/service/BaseService.ashx?action=getreversergcinfo");//
			sendParm.append("&userid=hbsdz2011");
			sendParm.append("&password=hbsdz0421");
			sendParm.append("&lng=");
			sendParm.append(longitude);
			sendParm.append("&lat=");
			sendParm.append(latitude);
			sendParm.append("&IsEncrypted=true");
			HttpRespons hr = request.sendGet(sendParm.toString());
			String test = hr.getContent();
			if (!test.equals("")) {
				test = test.trim();
				test = test.replaceAll("\r\n", "");
				// test=test.substring(1, test.length());
			}

			Document doc = DocumentHelper.parseText(test);
			Element root = doc.getRootElement();

			List list = root.elements("ReverseGeocodedLocation");
			Element foo = (Element) list.get(0);
			String name = foo.elementText("City");
			return name;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	 * 计算地球上任意两点(经纬度)距离 
	 *  
	 * @param long1 
	 *            第一点经度 
	 * @param lat1 
	 *            第一点纬度 
	 * @param long2 
	 *            第二点经度 
	 * @param lat2 
	 *            第二点纬度 
	 * @return 返回距离 单位：米 
	 */  
	public static double Distance(double long1, double lat1, double long2,double lat2) {  
	    double a, b, R;  
	    R = 6378137; // 地球半径  
	    lat1 = lat1 * Math.PI / 180.0;  
	    lat2 = lat2 * Math.PI / 180.0;  
	    a = lat1 - lat2;  
	    b = (long1 - long2) * Math.PI / 180.0;  
	    double d;  
	    double sa2, sb2;  
	    sa2 = Math.sin(a / 2.0);  
	    sb2 = Math.sin(b / 2.0);  
	    d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));  
	    return d;  
	}
	
	/**
	 * 判断是否在矩形区域之内
	 * @param long1 左上角经度
	 * @param lat1   左上角纬度
	 * @param long2 右上角经度
	 * @param lat2  右下角纬度
	 * @param lng 待测点经度
	 * @param lat 待测点纬度
	 * @return
	 */
	public static boolean inArea(double long1, double lat1, double long2,double lat2,double lng, double lat) {
		boolean flag = false;
		if(lng>long1 && lng<long2 && lat>lat2 && lat<lat1){
			flag = true;
		}
		return flag;
	}
	
	public static void main(String[] args){
//		double str = Distance(113.902356,25.584001,113.902354,22.585421);
//		System.out.println("str == "+str);
		System.out.println(getBaiDuInfo(36.541318, 104.133233).getAddress());
	}

}
