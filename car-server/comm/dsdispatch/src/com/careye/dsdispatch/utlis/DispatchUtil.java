/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.utlis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.careye.dsdispatch.constant.Constant;
import com.careye.dsdispatch.redis.RedisManager;
import com.careye.redis.domain.CarInfo;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：DispatchUtil    
 * 类描述：调度工具类    
 * 创建人：zr    
 * 创建时间：2015-5-19 下午07:35:13    
 * 修改人：zr    
 * 修改时间：2015-5-19 下午07:35:13    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DispatchUtil {

	private final static Logger logger = Logger.getLogger(DispatchUtil.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//cityGridArray(115.75,117.48);
	}

	/**
	 * 得到两个参数之间整数数组
	 * @param par1 参数1
	 * @param par2 参数2
	 * @return
	 */
	/*	public static int[] cityGridArray(double par1,double par2){

		try {
			//转换成整数
			par1 = 100*par1;
			par2 = 100*par2;

			int start = 0;
			int diff = 0;
			if(par1 > par2){
				diff = (int) (par1 - par2);
				start = (int) par2;
			}else{
				diff = (int) (par2 - par1);
				start = (int) par1;
			}

			int[] array = new int[diff];

			for (int i = 0; i < diff; i++) {
				array[i] = start;
				start += 1;
			}

			return array;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} */


	public static int[] cityGridArray(double par1,double par2,int[] latlngarray){

		try {
			//转换成整数
			par1 = 100*par1;
			par2 = 100*par2;

			int start = 0;
			int diff = 0;
			if(par1 > par2){
				diff = (int) (par1 - par2);
				start = (int) par2;
			}else{
				diff = (int) (par2 - par1);
				start = (int) par1;
			}

			if(latlngarray != null){
				diff = diff+latlngarray.length;
			}

			int[] array = new int[diff];

			if(latlngarray == null){

				for (int i = 0; i < diff; i++) {
					array[i] = start;
					start += 1;
				}

			}else{

				for (int i = 0; i < diff; i++) {

					if(i < latlngarray.length){
						array[i] = latlngarray[i];
					}else if(i>=latlngarray.length){
						array[i] = start;
						start += 1;
					}
				}

			}

			return array;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 

	/**
	 * 组装二维数组
	 * @param lngarray
	 * @param latarray
	 * @return
	 */
	public static int[][] cityGrid2dArray(int[] lngarray,int[] latarray){
		try {
			int[][] lnglatarray = new int[lngarray.length][latarray.length];
			for (int i = 0; i < lngarray.length; i++) {
				for (int j = 0; j < latarray.length; j++) {
					StringBuffer lnglatBuffer = new StringBuffer();
					lnglatBuffer.append(lngarray[i]);
					lnglatBuffer.append(latarray[j]);
					lnglatarray[i][j] = Integer.parseInt(lnglatBuffer.toString());
				}
			}

			return lnglatarray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 二分法查找
	 * @param arr  数据从小到大
	 * @param key
	 * @return
	 */
	public static int binarySearch(int[] arr, int key){
		/*if(arr != null){
			int start = 0;
			int end = arr.length - 1;
			while (start <= end) {
				int middle = (start + end) / 2;
				if (key < arr[middle]) {
					end = middle - 1;
				} else if (key > arr[middle]) {
					start = middle + 1;
				} else {
					return middle;
				}
			}
		}
		return -1;*/
		int re = -1;
		try {
			re = Arrays.binarySearch(arr, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 初始化系统车辆MAP
	 * @param lnglen
	 * @param latlen
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean initSystemTerMap(int lnglen,int latlen){

		try {
			for (int i = 0; i < lnglen; i++) {

				for (int j = 0; j < latlen; j++) {
					StringBuffer keyBuffer = new StringBuffer();
					keyBuffer.append(Constant.TERMINALINFO_REDIS_NAME);
					keyBuffer.append(i);
					keyBuffer.append("_");
					keyBuffer.append(j);
					Map<String,CarInfo> map = (Map<String, CarInfo>) RedisManager.getInstance().getObject(keyBuffer.toString());
					if(map == null){
						map = new HashMap<String,CarInfo>();
						RedisManager.getInstance().setObject(keyBuffer.toString(), map);
					}
					
					//logger.info("i="+i+",j="+j);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 查找乘客周边车辆MAP(千米范围内)
	 * @param lng 乘客经度
	 * @param lat 乘客纬度
	 * @param diff 距离（千米为单位整数）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<CarInfo> searchTerMap(int lng,int lat,int diff){

		try {
			logger.info("[lng:"+lng+"] [lat:"+lat+"]");
			/*BigDecimal blng = new BigDecimal(lng * Math.pow(10, 6));
			BigDecimal blat = new BigDecimal(lat * Math.pow(10, 6));

			int ilng = (int) blng.doubleValue() / 10000;
			int ilat = (int) blat.doubleValue() / 10000;*/

			int ilng = lng / 10000;
			int ilat = lat / 10000;

			List<CarInfo> list = new ArrayList<CarInfo>();

			//二分法查找成功经纬度在网格中的下标
			int m = DispatchUtil.binarySearch(Constant.LNG_ARRAY, ilng);
			int n = DispatchUtil.binarySearch(Constant.LAT_ARRAY, ilat);

			logger.info("乘客对应经纬度数组下标 ["+m+"] ["+n+"] 查找 ["+diff+"] 千米范围内的车辆");

			int num = diff*2+1;
			int sm = m - diff;
			//根据乘客标查找对应车辆
			for (int i = 0; i < num; i++) {
				int sn = n - diff;
				for (int j = 0; j < num; j++) {

					StringBuffer keyBuffer = new StringBuffer(); 
					keyBuffer.append(Constant.TERMINALINFO_REDIS_NAME);
					keyBuffer.append(sm);
					keyBuffer.append("_");
					keyBuffer.append(sn);

					//logger.info("从redis缓存中查找周边车辆MAP ["+keyBuffer.toString()+"]");
					Map<String,CarInfo> map = (Map<String, CarInfo>) RedisManager.getInstance().getObject(keyBuffer.toString());

					if(map != null){
						List<String> dellist = new ArrayList<String>();
						Iterator<String> keys = map.keySet().iterator();
						while(keys.hasNext()) {
							String key = (String) keys.next();
							CarInfo carInfo = map.get(key);
							int sec = DateUtil.secBetween(DateUtil.getSQLDate(), carInfo.getAddtime());
							//如果系统MAP中车辆位置超过3分钟未更新，系统将不给当前车辆调派任务
							if(sec <= 180){
								logger.info("找到车辆["+keyBuffer.toString()+"] ["+carInfo.getTerminal()+"] ");
								list.add(carInfo);
							}else{
								logger.info("车辆["+keyBuffer.toString()+"] ["+carInfo.getTerminal()+"] 位置信息超过3分钟未更新,车辆应该离线,系统将不调派该车辆");
								dellist.add(key);
							}
						} 

						//删除离线车辆
						for (String key : dellist) {
							map.remove(key);
						}
						if(dellist.size() > 0){
							//更新缓存
							RedisManager.getInstance().setObject(keyBuffer.toString(), map);
						}

					}
					sn +=1;
				}
				sm +=1;
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 获取符合条件的车辆信息
	 * @param list 获取乘客周边1千米范围内的车辆列表
	 * @param distance 距离
	 * @param lng 乘客经度
	 * @param lat 乘客纬度
	 * @return
	 */
	public static List<CarInfo> getEligibleCarInfo(List<CarInfo> list,int distance,double lng,double lat){

		try {

			List<CarInfo> carlist = new ArrayList<CarInfo>();
			for (CarInfo carInfo : list) {

				double clng = carInfo.getLng();
				double clat = carInfo.getLat();
				double dlng = new BigDecimal(lng).doubleValue() / 1000000;
				double dlat = new BigDecimal(lat).doubleValue() / 1000000;

				//查找精确在调度规则返回内的车辆(乘客跟车辆之间的距离)
				int dist = DispatchUtil.getDistance(clng, clat, dlng, dlat);
				logger.info("车辆 ["+carInfo.getTerminal()+"] 与乘客相距 ["+dist+"]米");
				if(dist <= distance){
					carInfo.setDistance(dist);
					carlist.add(carInfo);
				}	
			}

			if(carlist.size()>0){
				//排序
				Collections.sort(carlist, new Comparator<CarInfo>() {
					public int compare(CarInfo arg0, CarInfo arg1) {
						return arg0.getDistance().compareTo(arg1.getDistance());
					}
				});
			}
			return carlist;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

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
	public static int getDistance(double long1, double lat1, double long2,  double lat2) {  
		try {
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
			d = 2 * R  * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));  
			return (int)d;  
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	} 



}
