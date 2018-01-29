/**
 * Description: 车队管理系统
 * 文件名：GaoDeHttp.java
 * 版本信息：1.0
 * 日期：2014-7-18
 * Copyright car-eye车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.http;

import java.text.DecimalFormat;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.careye.constant.ConfigProperties;
import com.careye.http.domain.BaiDuInfo;
import com.careye.utils.Gps;
import com.careye.utils.PositionUtil;


/**
 * @项目名称：FMSSERVER
 * @类名称：GaoDeHttp
 * @类描述：高德地图接口
 * @创建人：lenovo
 * @创建时间：2014-7-18 下午01:10:12
 * @修改人：lenovo
 * @修改时间：2014-7-18 下午01:10:12
 * @修改备注：
 * @version 1.0
 */
public class GaoDeHttp {

	private static final Logger logger = Logger.getLogger(GaoDeHttp.class);

	public static void main(String[] args) {
		BaiDuInfo gaoDeInfo = gpsLngLatForGaoDe(113.874448,22.566362);
//		System.out.println(gaoDeInfo.getGlat());
//		System.out.println(psLngLatForAddr(113.254545, 23.252555));
	}
	/**
	 * GPS经纬度转换成高德地图经纬度
	 * @param lng
	 * @param lat
	 * @return http://search1.mapabc.com/sisserver?config=RGC&resType=xml&y1=39.8&x1=116.9&cr=0&flag=true&a_k=fc05daba5bcbe6c12c318d6688d52334e09f9700bc463410c9dd15d1797b69b31c7170da257f7ea3
	 */
	public static BaiDuInfo gpsLngLatForGaoDe(double lng, double lat) {
		
		if(lat != 0.0 && lng !=0.0){
			BaiDuInfo gaoDeInfo = new BaiDuInfo();

			try {
				DecimalFormat df = new DecimalFormat("#.000000");
				Gps gps = new Gps(lat,lng);
				Gps gcj = PositionUtil.gps84_To_Gcj02(gps.getWgLat(), gps.getWgLon());
				gaoDeInfo.setBlng(Double.valueOf(df.format(gcj.getWgLon())));
				gaoDeInfo.setBlat(Double.valueOf(df.format(gcj.getWgLat())));
				try {
					psLngLatForAddr(gaoDeInfo,gaoDeInfo.getBlng(),gaoDeInfo.getBlat());
					
					logger.info("调用高德API根据经纬度获取地址信息="+gaoDeInfo.getAddress());
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("调用高德API根据经纬度获取地址信息异常",e);
				}
				
				/*HttpRequester request = new HttpRequester();
				StringBuffer sendParm = new StringBuffer();
				request.setDefaultContentEncoding("utf-8");
				sendParm.append("http://search1.mapabc.com/sisserver?config=RGC&resType=json");
				sendParm.append("&x1=");
				sendParm.append(lng);
				sendParm.append("&y1=");
				sendParm.append(lat);
				sendParm.append("&flag=true&a_k=");
				sendParm.append(ConfigProperties.GAODE_AK);
				HttpRespons hr = request.sendGet(sendParm.toString());
				//logger.info("转换高德地图经纬度URL:"+sendParm.toString());
				String msg = hr.getContent();
				JSONObject jsonObject = JSONObject.fromObject(msg);
				JSONArray array = JSONArray.fromObject(jsonObject.get("list"));
				for(int i=0; i<array.size(); i++){
					JSONObject jot = (JSONObject)array.get(i);
					if (jot.get("x") != null && jot.get("y") != null) {
						gaoDeInfo.setGlng(Double.valueOf(jot.get("x").toString()));
						gaoDeInfo.setGlat(Double.valueOf(jot.get("y").toString()));
						try {
							gaoDeInfo.setAddress(psLngLatForAddr(gaoDeInfo.getGlng(),gaoDeInfo.getGlat()));
						} catch (Exception e) {
							e.printStackTrace();
						}

						return gaoDeInfo;
					}
				}*/
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
			return gaoDeInfo;
		}else{
			return null;
		}
		
	}

	/**
	 * 根据经纬度解析地址
	 * @param lng
	 * @param lat
	 * @return
	 */
	public static void psLngLatForAddr(BaiDuInfo gaoDeInfo, double lng, double lat){
		String msg = null;
		StringBuffer sendParm = new StringBuffer();
		try {
			HttpRequester request = new HttpRequester();
			request.setDefaultContentEncoding("UTF-8");
			sendParm.append("http://restapi.amap.com/v3/geocode/regeo?key=");
			sendParm.append(ConfigProperties.GAODE_AK);
			sendParm.append("&location=");
			sendParm.append(lng);
			sendParm.append(",");
			sendParm.append(lat);
			HttpRespons hr = request.sendGet(sendParm.toString());
			msg = hr.getContent();
			JSONObject jsonObject = JSONObject.fromObject(msg);
			JSONObject regeocodesObj = (JSONObject) jsonObject.get("regeocode");
			JSONObject componentObj = (JSONObject) regeocodesObj.get("addressComponent");
			if(regeocodesObj != null){
				String addr = regeocodesObj.get("formatted_address").toString();
				
				gaoDeInfo.setAddress(addr);
			}
			if(componentObj != null){
				String province = componentObj.get("province").toString();
				String city = componentObj.get("city").toString();
				String district = componentObj.get("district").toString();
				
				gaoDeInfo.setProvince(province);
				gaoDeInfo.setCity(city);
				gaoDeInfo.setDistrict(district);
			}
			
		} catch (Exception e) {
			logger.info("高德根据经纬度解析地址获取异常，请求=="+sendParm.toString()+",返回："+msg);
			
		}
	}

}
