/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.decoder;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.careye.dsparse.bbdomain.CircleArea;
import com.careye.dsparse.bbdomain.CircleAreaItems;
import com.careye.dsparse.bbdomain.EventSet;
import com.careye.dsparse.bbdomain.EventTerm;
import com.careye.dsparse.bbdomain.InfoDemandMenu;
import com.careye.dsparse.bbdomain.InfoDemandMenuItems;
import com.careye.dsparse.bbdomain.LineInfo;
import com.careye.dsparse.bbdomain.LineInfoItems;
import com.careye.dsparse.bbdomain.MulMediaDataLoadResponse;
import com.careye.dsparse.bbdomain.ParameterInfo;
import com.careye.dsparse.bbdomain.PhoneBook;
import com.careye.dsparse.bbdomain.PhoneBookItems;
import com.careye.dsparse.bbdomain.PolygonArea;
import com.careye.dsparse.bbdomain.PolygonAreaItems;
import com.careye.dsparse.bbdomain.PositionInfo;
import com.careye.dsparse.bbdomain.QuesSendCandidateAnswer;
import com.careye.dsparse.bbdomain.QuestionsSend;
import com.careye.dsparse.bbdomain.RectangleArea;
import com.careye.dsparse.bbdomain.RectangleAreaItems;
import com.careye.dsparse.bbdomain.TerminalParameter;
import com.careye.dsparse.utlis.DateUtil;
import com.careye.dsparse.utlis.ExceptionUtil;
import com.careye.dsparse.utlis.StringUtils;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：ByteDecoderUtil    
 * 类描述：解码工具类    
 * 创建人：zr    
 * 创建时间：2015-5-14 下午07:47:12    
 * 修改人：zr    
 * 修改时间：2015-5-14 下午07:47:12    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BbByteDecoderUtil {

	/**
	 * 解析提问下发
	 * @param msg
	 * @return
	 */
	public static QuestionsSend getQuestionsSend(JSONObject jsonObject){

		QuestionsSend qscaAnswer = new QuestionsSend();
		try {

			JSONArray jsons = jsonObject.getJSONArray("items");
			qscaAnswer.setLen(jsons.size());
			qscaAnswer.setTag0(jsonObject.getInt("tag0"));
			qscaAnswer.setTag3(jsonObject.getInt("tag3"));
			qscaAnswer.setTag4(jsonObject.getInt("tag4"));
			qscaAnswer.setContent(jsonObject.getString("content"));
			List<QuesSendCandidateAnswer> list = new ArrayList<QuesSendCandidateAnswer>();
			for (int i = 0; i < jsons.size(); i++) {
				JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
				QuesSendCandidateAnswer qsAnswer = new QuesSendCandidateAnswer();
				qsAnswer.setId(tempJson.getInt("id"));
				qsAnswer.setLen(tempJson.getInt("len"));
				qsAnswer.setContent(tempJson.getString("content"));
				list.add(qsAnswer);
			}
			qscaAnswer.setItems(list);

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return qscaAnswer;
	}


	/**
	 * 解析事件设置
	 * @param msg
	 * @return
	 */
	public static EventSet getEventSet(JSONObject jsonObject){
		EventSet eventSet = new EventSet();
		try {

			int type = jsonObject.getInt("type");
			if(type != 0){
				JSONArray jsons = jsonObject.getJSONArray("items");
				eventSet.setCount(jsons.size());
				eventSet.setType(type);
				eventSet.setCount(jsonObject.getInt("count"));

				List<EventTerm> list = new ArrayList<EventTerm>();
				for (int i = 0; i < jsons.size(); i++) {
					JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
					EventTerm eventTerm = new EventTerm();
					int id = tempJson.getInt("id");
					eventTerm.setId(id);
					if(type == 1 || type == 2 || type ==3){
						eventTerm.setLen(tempJson.getInt("len"));
						eventTerm.setContent(tempJson.getString("content"));
					}
					list.add(eventTerm);
				}
				eventSet.setItems(list);
			}

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return eventSet;
	}

	/**
	 * 删除圆形区域
	 * @param jsonObject
	 * @return
	 */
	public static CircleArea deleteCircleArea(JSONObject jsonObject){
		CircleArea circleArea = new CircleArea();
		try {
			JSONArray jsons = jsonObject.getJSONArray("items");
			circleArea.setCount(jsons.size());

			List<CircleAreaItems> list = new ArrayList<CircleAreaItems>();
			for (int i = 0; i < jsons.size(); i++) {
				JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
				CircleAreaItems circleAreaItems = new CircleAreaItems();
				circleAreaItems.setAreaId(tempJson.getInt("areaId"));
				list.add(circleAreaItems);
			}
			circleArea.setItems(list);

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return circleArea;
	} 

	/**
	 * 删除矩形区域
	 * @param jsonObject
	 * @return
	 */
	public static RectangleArea deleteRectangleArea(JSONObject jsonObject){
		RectangleArea rectangleArea = new RectangleArea();
		try {
			JSONArray jsons = jsonObject.getJSONArray("items");
			rectangleArea.setCount(jsons.size());

			List<RectangleAreaItems> list = new ArrayList<RectangleAreaItems>();
			for (int i = 0; i < jsons.size(); i++) {
				JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
				RectangleAreaItems rectangleAreaItems = new RectangleAreaItems();
				rectangleAreaItems.setAreaId(tempJson.getInt("areaId"));
				list.add(rectangleAreaItems);
			}
			rectangleArea.setItems(list);
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
		return rectangleArea;
	} 


	/**
	 * 删除多边形区域
	 * @param jsonObject
	 * @return
	 */
	public static PolygonArea deletePolygonArea(JSONObject jsonObject){
		PolygonArea polygonArea = new PolygonArea();
		try {
			JSONArray jsons = jsonObject.getJSONArray("items");
			polygonArea.setCount(jsons.size());

			List<PolygonAreaItems> list = new ArrayList<PolygonAreaItems>();
			for (int i = 0; i < jsons.size(); i++) {
				JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
				PolygonAreaItems polygonAreaItems = new PolygonAreaItems();
				polygonAreaItems.setAreaId(tempJson.getInt("areaId"));
				list.add(polygonAreaItems);
			}
			polygonArea.setItems(list);
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
		return polygonArea;
	} 

	/**
	 * 删除线路
	 * @param jsonObject
	 * @return
	 */
	public static LineInfo deleteLineInfo(JSONObject jsonObject){
		LineInfo lineInfo = new LineInfo();
		try {
			JSONArray jsons = jsonObject.getJSONArray("items");
			lineInfo.setCount(jsons.size());

			List<LineInfoItems> list = new ArrayList<LineInfoItems>();
			for (int i = 0; i < jsons.size(); i++) {
				JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
				LineInfoItems lineInfoItems = new LineInfoItems();
				lineInfoItems.setId(tempJson.getInt("id"));
				list.add(lineInfoItems);
			}
			lineInfo.setItems(list);
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
		return lineInfo;
	} 


	/**
	 * 解析多媒体数据上传应答
	 * @param msg
	 * @return
	 */
	public static MulMediaDataLoadResponse getMmdlResponseInfo(JSONObject jsonObject){

		MulMediaDataLoadResponse mmdlResponse = new MulMediaDataLoadResponse();
		try {

			mmdlResponse.setDataId(jsonObject.getInt("dataId"));
			mmdlResponse.setPacketSum(jsonObject.getInt("packetSum"));
			String ids = jsonObject.getString("ids");

			if(mmdlResponse.getPacketSum() > 0){
				if(ids != null){
					String[] idsarr = ids.split(",");
					mmdlResponse.setPacketSum(idsarr.length);
					List<Integer> list = new ArrayList<Integer>();
					for (int i = 0; i < idsarr.length; i++) {
						list.add(Integer.parseInt(idsarr[i]));
					}
					mmdlResponse.setIds(list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.getInfo(e);
			return null;
		}

		return mmdlResponse;
	}

	/**
	 * 设置圆形区域
	 * @param jsonObject
	 * @return
	 */
	public static CircleArea getCircleArea(JSONObject jsonObject){

		CircleArea circleArea = new CircleArea();
		try {
			JSONArray jsons = jsonObject.getJSONArray("items");
			circleArea.setUpdate(jsonObject.getInt("update"));
			circleArea.setCount(jsons.size());

			List<CircleAreaItems> list = new ArrayList<CircleAreaItems>();
			for (int i = 0; i < jsons.size(); i++) {
				try {
					JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
					CircleAreaItems cItems = new CircleAreaItems();
					cItems.setAreaId(tempJson.getInt("areaId"));
					String attr = null;
					try {
						if(tempJson.containsKey("attr")){
							attr = tempJson.getString("attr");	
						}
					} catch (Exception e) {
						e.printStackTrace();ExceptionUtil.getInfo(e);
					}
					if(attr != null){
						cItems.setAttr0(Integer.parseInt(attr.substring(0, 1)));
						cItems.setAttr1(Integer.parseInt(attr.substring(1, 2)));
						cItems.setAttr2(Integer.parseInt(attr.substring(2, 3)));
						cItems.setAttr3(Integer.parseInt(attr.substring(3, 4)));
						cItems.setAttr4(Integer.parseInt(attr.substring(4, 5)));
						cItems.setAttr5(Integer.parseInt(attr.substring(5, 6)));
						cItems.setAttr6(Integer.parseInt(attr.substring(6, 7)));
						cItems.setAttr7(Integer.parseInt(attr.substring(7, 8)));
					}else{
						cItems.setAttr0(tempJson.getInt("attr0"));
						cItems.setAttr1(tempJson.getInt("attr1"));
						cItems.setAttr2(tempJson.getInt("attr2"));
						cItems.setAttr3(tempJson.getInt("attr3"));
						cItems.setAttr4(tempJson.getInt("attr4"));
						cItems.setAttr5(tempJson.getInt("attr5"));
						cItems.setAttr6(tempJson.getInt("attr6"));
						cItems.setAttr7(tempJson.getInt("attr7"));
					}

					cItems.setLat(tempJson.getInt("lat"));
					cItems.setLng(tempJson.getInt("lng"));
					cItems.setRadius(tempJson.getInt("radius"));
					if(tempJson.containsKey("timeS")){
						cItems.setTimeS(DateUtil.dateToNumber(tempJson.getString("timeS")));
					}
					if(tempJson.containsKey("timeE")){
						cItems.setTimeE(DateUtil.dateToNumber(tempJson.getString("timeE")));
					}
					if(tempJson.containsKey("speedLimit")){
						cItems.setSpeedLimit(tempJson.getInt("speedLimit"));
					}
					if(tempJson.containsKey("speedTime")){
						cItems.setSpeedTime(tempJson.getInt("speedTime"));
					}
					list.add(cItems);
				} catch (Exception e) {
					e.printStackTrace();ExceptionUtil.getInfo(e);
				}
			}
			circleArea.setItems(list);

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return circleArea;
	}


	/**
	 * 设置矩形区域
	 * @param jsonObject
	 * @return
	 */
	public static RectangleArea getRectangleArea(JSONObject jsonObject){

		RectangleArea rectangleArea = new RectangleArea();
		try {
			JSONArray jsons = jsonObject.getJSONArray("items");
			rectangleArea.setUpdate(jsonObject.getInt("update"));
			rectangleArea.setCount(jsons.size());

			List<RectangleAreaItems> list = new ArrayList<RectangleAreaItems>();
			for (int i = 0; i < jsons.size(); i++) {
				try {
					JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
					RectangleAreaItems rItems = new RectangleAreaItems();
					rItems.setAreaId(tempJson.getInt("areaId"));
					String attr = null;
					try {
						if(tempJson.containsKey("attr")){
							attr = tempJson.getString("attr");	
						}
					} catch (Exception e) {
						e.printStackTrace();ExceptionUtil.getInfo(e);
					}
					if(attr != null){
						rItems.setAttr0(Integer.parseInt(attr.substring(0, 1)));
						rItems.setAttr1(Integer.parseInt(attr.substring(1, 2)));
						rItems.setAttr2(Integer.parseInt(attr.substring(2, 3)));
						rItems.setAttr3(Integer.parseInt(attr.substring(3, 4)));
						rItems.setAttr4(Integer.parseInt(attr.substring(4, 5)));
						rItems.setAttr5(Integer.parseInt(attr.substring(5, 6)));
						rItems.setAttr6(Integer.parseInt(attr.substring(6, 7)));
						rItems.setAttr7(Integer.parseInt(attr.substring(7, 8)));
					}else{
						rItems.setAttr0(tempJson.getInt("attr0"));
						rItems.setAttr1(tempJson.getInt("attr1"));
						rItems.setAttr2(tempJson.getInt("attr2"));
						rItems.setAttr3(tempJson.getInt("attr3"));
						rItems.setAttr4(tempJson.getInt("attr4"));
						rItems.setAttr5(tempJson.getInt("attr5"));
						rItems.setAttr6(tempJson.getInt("attr6"));
						rItems.setAttr7(tempJson.getInt("attr7"));
					}

					rItems.setLatlt(tempJson.getInt("latlt"));
					rItems.setLnglt(tempJson.getInt("lnglt"));
					rItems.setLatrb(tempJson.getInt("latrb"));
					rItems.setLngrb(tempJson.getInt("lngrb"));

					if(tempJson.containsKey("timeS")){
						rItems.setTimeS(DateUtil.dateToNumber(tempJson.getString("timeS")));
					}
					if(tempJson.containsKey("timeE")){
						rItems.setTimeE(DateUtil.dateToNumber(tempJson.getString("timeE")));
					}
					if(tempJson.containsKey("speedLimit")){
						rItems.setSpeedLimit(tempJson.getInt("speedLimit"));
					}
					if(tempJson.containsKey("speedTime")){
						rItems.setSpeedTime(tempJson.getInt("speedTime"));
					}
					list.add(rItems);
				} catch (Exception e) {
					e.printStackTrace();ExceptionUtil.getInfo(e);
				}
			}
			rectangleArea.setItems(list);

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return rectangleArea;
	}


	/**
	 * 设置多边形区域
	 * @param jsonObject
	 * @return
	 */
	public static PolygonArea getPolygonArea(JSONObject jsonObject){

		PolygonArea polygonArea = new PolygonArea();
		try {
			JSONArray jsons = jsonObject.getJSONArray("items");
			polygonArea.setCount(jsons.size());

			polygonArea.setAreaId(jsonObject.getInt("areaId"));

			String attr = null;
			try {
				if(jsonObject.containsKey("attr")){
					attr = jsonObject.getString("attr");	
				}
			} catch (Exception e) {
				e.printStackTrace();ExceptionUtil.getInfo(e);
			}
			if(attr != null){
				polygonArea.setAttr0(Integer.parseInt(attr.substring(0, 1)));
				polygonArea.setAttr1(Integer.parseInt(attr.substring(1, 2)));
				polygonArea.setAttr2(Integer.parseInt(attr.substring(2, 3)));
				polygonArea.setAttr3(Integer.parseInt(attr.substring(3, 4)));
				polygonArea.setAttr4(Integer.parseInt(attr.substring(4, 5)));
				polygonArea.setAttr5(Integer.parseInt(attr.substring(5, 6)));
				polygonArea.setAttr6(Integer.parseInt(attr.substring(6, 7)));
				polygonArea.setAttr7(Integer.parseInt(attr.substring(7, 8)));
			}else{
				polygonArea.setAttr0(jsonObject.getInt("attr0"));
				polygonArea.setAttr1(jsonObject.getInt("attr1"));
				polygonArea.setAttr2(jsonObject.getInt("attr2"));
				polygonArea.setAttr3(jsonObject.getInt("attr3"));
				polygonArea.setAttr4(jsonObject.getInt("attr4"));
				polygonArea.setAttr5(jsonObject.getInt("attr5"));
				polygonArea.setAttr6(jsonObject.getInt("attr6"));
				polygonArea.setAttr7(jsonObject.getInt("attr7"));
			}	
			if(polygonArea.getAttr0() != 0){
				polygonArea.setTimeS(DateUtil.dateToNumber(jsonObject.getString("timeS")));
				polygonArea.setTimeE(DateUtil.dateToNumber(jsonObject.getString("timeE")));
			}

			if(polygonArea.getAttr1() != 0){
				polygonArea.setSpeedLimit(jsonObject.getInt("speedLimit"));
				polygonArea.setSpeedTime(jsonObject.getInt("speedTime"));
			}


			List<PolygonAreaItems> list = new ArrayList<PolygonAreaItems>();
			for (int i = 0; i < jsons.size(); i++) {
				JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
				PolygonAreaItems pItems = new PolygonAreaItems();

				pItems.setLat(tempJson.getInt("lat"));
				pItems.setLng(tempJson.getInt("lng"));

				list.add(pItems);
			}
			polygonArea.setItems(list);

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return polygonArea;
	}


	/**
	 * 设置线路
	 * @param jsonObject
	 * @return
	 */
	public static LineInfo getLineInfo(JSONObject jsonObject){

		LineInfo lineInfo = new LineInfo();
		try {
			JSONArray jsons = jsonObject.getJSONArray("items");
			lineInfo.setCount(jsons.size());

			lineInfo.setRid(jsonObject.getInt("rid"));

			String attr = null;
			try {
				if(jsonObject.containsKey("rattr")){
					attr = jsonObject.getString("rattr");	
				}
			} catch (Exception e) {
				e.printStackTrace();ExceptionUtil.getInfo(e);
			}
			if(attr != null){
				lineInfo.setRattr0(Integer.parseInt(attr.substring(0, 1)));
				lineInfo.setRattr2(Integer.parseInt(attr.substring(2, 3)));
				lineInfo.setRattr3(Integer.parseInt(attr.substring(3, 4)));
				lineInfo.setRattr4(Integer.parseInt(attr.substring(4, 5)));
				lineInfo.setRattr5(Integer.parseInt(attr.substring(5, 6)));
			}else{
				lineInfo.setRattr0(jsonObject.getInt("rattr0"));
				lineInfo.setRattr2(jsonObject.getInt("rattr2"));
				lineInfo.setRattr3(jsonObject.getInt("rattr3"));
				lineInfo.setRattr4(jsonObject.getInt("rattr4"));
				lineInfo.setRattr5(jsonObject.getInt("rattr5"));
			}	
			if(lineInfo.getRattr0() != 0){
				lineInfo.setSdate(DateUtil.dateToNumber(jsonObject.getString("sdate")));
				lineInfo.setEdate(DateUtil.dateToNumber(jsonObject.getString("edate")));
			}
			List<LineInfoItems> list = new ArrayList<LineInfoItems>();
			for (int i = 0; i < jsons.size(); i++) {
				JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
				LineInfoItems lItems = new LineInfoItems();

				String lattr = null;
				try {
					if(tempJson.containsKey("lattr")){
						lattr = tempJson.getString("lattr");	
					}
				} catch (Exception e) {
					e.printStackTrace();ExceptionUtil.getInfo(e);
				}
				if(attr != null){
					lItems.setLattr0(Integer.parseInt(lattr.substring(0, 1)));
					lItems.setLattr1(Integer.parseInt(lattr.substring(1, 2)));
					lItems.setLattr2(Integer.parseInt(lattr.substring(2, 3)));
					lItems.setLattr3(Integer.parseInt(lattr.substring(3, 4)));
				}else{
					lItems.setLattr0(tempJson.getInt("lattr0"));
					lItems.setLattr1(tempJson.getInt("lattr1"));
					lItems.setLattr2(tempJson.getInt("lattr2"));
					lItems.setLattr3(tempJson.getInt("lattr3"));
				}	

				lItems.setIid(tempJson.getInt("iid"));
				lItems.setLid(tempJson.getInt("lid"));
				lItems.setLat(tempJson.getInt("lat"));
				lItems.setLng(tempJson.getInt("lng"));
				lItems.setWidth(tempJson.getInt("width"));
				if(lItems.getLattr0() != 0){
					lItems.setRtime(tempJson.getInt("rtime"));
					lItems.setStime(tempJson.getInt("stime"));
				}

				if(lItems.getLattr1() != 0){
					lItems.setTspeed(tempJson.getInt("tspeed"));
					lItems.setPspeed(tempJson.getInt("pspeed"));
				}

				list.add(lItems);
			}
			lineInfo.setItems(list);

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}
		return lineInfo;
	}


	/**
	 * 设置电话本
	 * @param jsonObject
	 * @return
	 */
	public static PhoneBook getPhoneBook(JSONObject jsonObject){

		PhoneBook phoneBook = new PhoneBook();
		try {
			int type = jsonObject.getInt("type");
			if(type !=0 ){
				JSONArray jsons = jsonObject.getJSONArray("items");
				phoneBook.setType(type);
				phoneBook.setCount(jsons.size());

				List<PhoneBookItems> list = new ArrayList<PhoneBookItems>();
				for (int i = 0; i < jsons.size(); i++) {
					try {
						JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
						PhoneBookItems idmItems = new PhoneBookItems();
						idmItems.setTag(tempJson.getInt("tag"));
						idmItems.setTelLen(tempJson.getInt("telLen"));
						idmItems.setTel(tempJson.getString("tel"));
						idmItems.setCtcLen(tempJson.getInt("ctcLen"));
						idmItems.setContact(tempJson.getString("contact"));
						list.add(idmItems);
					} catch (Exception e) {
						e.printStackTrace();ExceptionUtil.getInfo(e);
					}
				}
				phoneBook.setItems(list);
			}
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return phoneBook;
	}


	/**
	 * 信息点播菜单设置
	 * @param jsonObject
	 * @return
	 */
	public static InfoDemandMenu getInfoDemandMenu(JSONObject jsonObject){

		InfoDemandMenu idm = new InfoDemandMenu();
		try {
			JSONArray jsons = jsonObject.getJSONArray("items");
			idm.setType(jsonObject.getInt("type"));
			idm.setCount(jsons.size());

			List<InfoDemandMenuItems> list = new ArrayList<InfoDemandMenuItems>();
			for (int i = 0; i < jsons.size(); i++) {
				JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
				InfoDemandMenuItems idmItems = new InfoDemandMenuItems();
				idmItems.setType(tempJson.getInt("type"));
				idmItems.setLen(tempJson.getInt("len"));
				idmItems.setContent(tempJson.getString("content"));
				list.add(idmItems);
			}
			idm.setItems(list);

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return idm;
	}

	/**
	 * 解析终端 参数
	 * @param msg
	 * @return
	 */
	public static TerminalParameter getTerminalParameterInfo(JSONObject jsonObject){

		TerminalParameter terminalParameter = new TerminalParameter();
		try {

			JSONArray jsons = jsonObject.getJSONArray("items");
			terminalParameter.setCount(jsons.size());

			List<ParameterInfo> list = new ArrayList<ParameterInfo>();
			for (int i = 0; i < jsons.size(); i++) {
				JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
				ParameterInfo parameterInfo = new ParameterInfo();
				parameterInfo.setId(tempJson.getInt("id"));
				parameterInfo.setSize(tempJson.getInt("size"));
				parameterInfo.setValue(tempJson.getString("value"));
				list.add(parameterInfo);
			}
			terminalParameter.setItems(list);

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return terminalParameter;
	}


	/**
	 * 查询指定终端参数
	 * @param msg
	 * @return
	 */
	public static TerminalParameter querySpecifyingTerminalParam(JSONObject jsonObject){

		TerminalParameter terminalParameter = new TerminalParameter();
		try {

			JSONArray jsons = jsonObject.getJSONArray("items");
			terminalParameter.setCount(jsons.size());

			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < jsons.size(); i++) {
				JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
				list.add(tempJson.getInt("id"));
			}
			terminalParameter.setInititems(list);

		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

		return terminalParameter;
	}

	/**
	 * 设置状态位
	 * @param sn
	 * @param positionInfo
	 */
	public static PositionInfo setState(String sn,PositionInfo positionInfo){

		try {

			StringBuffer buffer = new StringBuffer();

			//22-31 保留
			/*if(sn.substring(0, 1).equals("1")){

			}else if(sn.substring(1, 2).equals("1")){

			}else if(sn.substring(2, 3).equals("1")){

			}else if(sn.substring(3, 4).equals("1")){

			}else if(sn.substring(4, 5).equals("1")){

			}else if(sn.substring(5, 6).equals("1")){

			}else if(sn.substring(6, 7).equals("1")){

			}else if(sn.substring(7, 8).equals("1")){

			}else if(sn.substring(8, 9).equals("1")){

			}else if(sn.substring(9, 10).equals("1")){

			}else */

			/*if(sn.substring(10, 11).equals("1")){

			}else if(sn.substring(11, 12).equals("1")){

			}else if(sn.substring(12, 13).equals("1")){

			}*/

			positionInfo.setS18(Integer.parseInt(sn.substring(13, 14)));
			buffer.append(StringUtils.jsonChar("s18", positionInfo.getS18(), 0));

			/*else if(sn.substring(14, 15).equals("1")){

			}else if(sn.substring(15, 16).equals("1")){

			}else if(sn.substring(16, 17).equals("1")){

			}else if(sn.substring(17, 18).equals("1")){

			}else if(sn.substring(18, 19).equals("1")){

			}else if(sn.substring(19, 20).equals("1")){

			}else if(sn.substring(20, 21).equals("1")){

			}else if(sn.substring(21, 22).equals("1")){

			}else */

			String s89 = sn.substring(23, 24)+sn.substring(22, 23);
			if("00".equals(s89)){
				positionInfo.setS9(0);
			}else if("01".equals(s89)){
				positionInfo.setS9(1);
			}else if("10".equals(s89)){
				positionInfo.setS9(2);
			}else if("11".equals(s89)){
				positionInfo.setS9(3);
			}
			buffer.append(StringUtils.jsonChar("s9", positionInfo.getS9(), 0));
			/*else if(sn.substring(24, 25).equals("1")){

			}else if(sn.substring(25, 26).equals("1")){

			}else if(sn.substring(26, 27).equals("1")){

			}*/

			positionInfo.setS4(Integer.parseInt(sn.substring(27, 28)));
			buffer.append(StringUtils.jsonChar("s4", positionInfo.getS4(), 0));
			/*else if(sn.substring(28, 29).equals("1")){

			}else if(sn.substring(29, 30).equals("1")){

			}*/
			positionInfo.setS1(Integer.parseInt(sn.substring(30, 31)));
			buffer.append(StringUtils.jsonChar("s1", positionInfo.getS1(), 0));
			positionInfo.setS0(Integer.parseInt(sn.substring(31, 32)));
			buffer.append(StringUtils.jsonChar("s0", positionInfo.getS0(), 0));

			positionInfo.setStateinfo(buffer.toString());

			return positionInfo;
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

	}

	/**
	 * 设置报警状态
	 * @param an
	 * @param positionInfo
	 */
	public static PositionInfo setAlarmState(String an,PositionInfo positionInfo){
		try {
			StringBuffer buffer = new StringBuffer();
			if(an.substring(0, 1).equals("1")){
				positionInfo.setA31(1);
				buffer.append(StringUtils.jsonChar("a31", positionInfo.getA31(), 0));
			}else if(an.substring(1, 2).equals("1")){
				positionInfo.setA30(1);
				buffer.append(StringUtils.jsonChar("a30", positionInfo.getA30(), 0));
			}else if(an.substring(2, 3).equals("1")){
				positionInfo.setA29(1);
				buffer.append(StringUtils.jsonChar("a29", positionInfo.getA29(), 0));
			}else if(an.substring(3, 4).equals("1")){
				positionInfo.setA28(1);
				buffer.append(StringUtils.jsonChar("a28", positionInfo.getA28(), 0));
			}else if(an.substring(4, 5).equals("1")){
				positionInfo.setA27(1);
				buffer.append(StringUtils.jsonChar("a27", positionInfo.getA27(), 0));
			}else if(an.substring(5, 6).equals("1")){
				positionInfo.setA26(1);
				buffer.append(StringUtils.jsonChar("a26", positionInfo.getA26(), 0));
			}else if(an.substring(6, 7).equals("1")){
				positionInfo.setA25(1);
				buffer.append(StringUtils.jsonChar("a25", positionInfo.getA25(), 0));
			}else if(an.substring(7, 8).equals("1")){
				positionInfo.setA24(1);
				buffer.append(StringUtils.jsonChar("a24", positionInfo.getA24(), 0));
			}else if(an.substring(8, 9).equals("1")){
				positionInfo.setA23(1);
				buffer.append(StringUtils.jsonChar("a23", positionInfo.getA23(), 0));
			}else if(an.substring(9, 10).equals("1")){
				positionInfo.setA22(1);
				buffer.append(StringUtils.jsonChar("a22", positionInfo.getA22(), 0));
			}else if(an.substring(10, 11).equals("1")){
				positionInfo.setA21(1);
				buffer.append(StringUtils.jsonChar("a21", positionInfo.getA21(), 0));
			}else if(an.substring(11, 12).equals("1")){
				positionInfo.setA20(1);
				buffer.append(StringUtils.jsonChar("a20", positionInfo.getA20(), 0));
			}else if(an.substring(12, 13).equals("1")){
				positionInfo.setA19(1);
				buffer.append(StringUtils.jsonChar("a19", positionInfo.getA19(), 0));
			}else if(an.substring(13, 14).equals("1")){
				positionInfo.setA18(1);
				buffer.append(StringUtils.jsonChar("a18", positionInfo.getA18(), 0));
			}else if(an.substring(14, 15).equals("1")){
				positionInfo.setA17(1);
				buffer.append(StringUtils.jsonChar("a17", positionInfo.getA17(), 0));
			}else if(an.substring(15, 16).equals("1")){
				positionInfo.setA16(1);
				buffer.append(StringUtils.jsonChar("a16", positionInfo.getA16(), 0));
			}else if(an.substring(16, 17).equals("1")){
				positionInfo.setA15(1);
				buffer.append(StringUtils.jsonChar("a15", positionInfo.getA15(), 0));
			}else if(an.substring(17, 18).equals("1")){
				positionInfo.setA14(1);
				buffer.append(StringUtils.jsonChar("a14", positionInfo.getA14(), 0));
			}else if(an.substring(18, 19).equals("1")){
				positionInfo.setA13(1);
				buffer.append(StringUtils.jsonChar("a13", positionInfo.getA13(), 0));
			}else if(an.substring(19, 20).equals("1")){
				positionInfo.setA12(1);
				buffer.append(StringUtils.jsonChar("a12", positionInfo.getA12(), 0));
			}else if(an.substring(20, 21).equals("1")){
				positionInfo.setA11(1);
				buffer.append(StringUtils.jsonChar("a11", positionInfo.getA11(), 0));
			}else if(an.substring(21, 22).equals("1")){
				positionInfo.setA10(1);
				buffer.append(StringUtils.jsonChar("a10", positionInfo.getA10(), 0));
			}else if(an.substring(22, 23).equals("1")){
				positionInfo.setA9(1);
				buffer.append(StringUtils.jsonChar("a9", positionInfo.getA9(), 0));
			}else if(an.substring(23, 24).equals("1")){
				positionInfo.setA8(1);
				buffer.append(StringUtils.jsonChar("a8", positionInfo.getA8(), 0));
			}else if(an.substring(24, 25).equals("1")){
				positionInfo.setA7(1);
				buffer.append(StringUtils.jsonChar("a7", positionInfo.getA7(), 0));
			}else if(an.substring(25, 26).equals("1")){
				positionInfo.setA6(1);
				buffer.append(StringUtils.jsonChar("a6", positionInfo.getA6(), 0));
			}else if(an.substring(26, 27).equals("1")){
				positionInfo.setA5(1);
				buffer.append(StringUtils.jsonChar("a5", positionInfo.getA5(), 0));
			}else if(an.substring(27, 28).equals("1")){
				positionInfo.setA4(1);
				buffer.append(StringUtils.jsonChar("a4", positionInfo.getA4(), 0));
			}else if(an.substring(28, 29).equals("1")){
				positionInfo.setA3(1);
				buffer.append(StringUtils.jsonChar("a3", positionInfo.getA3(), 0));
			}else if(an.substring(29, 30).equals("1")){
				positionInfo.setA2(1);
				buffer.append(StringUtils.jsonChar("a2", positionInfo.getA2(), 0));
			}else if(an.substring(30, 31).equals("1")){
				positionInfo.setA1(1);
				buffer.append(StringUtils.jsonChar("a1", positionInfo.getA1(), 0));
			}else if(an.substring(31, 32).equals("1")){
				positionInfo.setA0(1);
				buffer.append(StringUtils.jsonChar("a0", positionInfo.getA0(), 0));
			}
			positionInfo.setAlarminfo(buffer.toString());
			return positionInfo;
		} catch (Exception e) {
			e.printStackTrace();ExceptionUtil.getInfo(e);
			return null;
		}

	}

}
