/**
* Description: 多森商用车平台
* 文件名：QuestionInfoAction.java
* 版本信息：1.0
* 日期：2014-5-26
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarInfo;
import com.careye.car.service.CarService;
import com.careye.message.domain.AnswerInfo;
import com.careye.message.domain.QuestionInfo;
import com.careye.message.domain.QuestionSendRecord;
import com.careye.message.domain.SendRecord;
import com.careye.message.domain.TextInfo;
import com.careye.message.service.QuestionService;
import com.careye.mq.HandleUtil;
import com.careye.mq.domain.Question;
import com.careye.system.domain.BlocUser;
import com.careye.utils.SessionUtils;

import common.Logger;

/**
 * @项目名称：FMS
 * @类名称：QuestionAction
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-26 下午01:47:32
 * @修改人：lxh
 * @修改时间：2014-5-26 下午01:47:32
 * @修改备注：
 * @version 1.0
 */
public class QuestionAction extends BasePageAction{
	private static final Logger logger = Logger.getLogger(QuestionAction.class);
	private QuestionService questionService;
	private CarService carService;
	private CarInfo carInfo;
	private QuestionInfo questionInfo;
	private AnswerInfo answerInfo;
	private HandleUtil handleUtil;
	private QuestionSendRecord questionSendRecord;
	
	private Map result;
	private String success;
	private List list;
	private String ids;
	private String ques;
	private BlocUser user;
	private int su;
	private String questionid;
	private String content;
	private String carnumber;
	private String etime;
	private String stime;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 查询问题答案
	 * @return
	 */
	public String queryAnswerList() {
		
		try {
			initMap();
			if (questionid!=null&&!questionid.equals("")&&!questionid.equals("null")) {
				list=questionService.selectAnswerInfoByQidLin(Integer.parseInt(questionid));
			} else {
                list=questionService.selectAnswerInfoLin();
			}
			result.put("list", list);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("QuestionInfoAction的方法 queryAnswerList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	public String insertLinAnswer(){
		try {
			initMap();
				List<AnswerInfo> list=questionService.selectAnswerInfoByQid(Integer.parseInt(questionid));
				if(list !=null && !list.isEmpty()){
					//把此问题答案存一份往临时记录中
					for (AnswerInfo answerInfo : list) {
						answerInfo.setId(null);
						answerInfo.setFlag(1);
						answerInfo.setQid(null);
						questionService.insertAnswerInfo(answerInfo);
					}
				}
			this.success="true";
			return SUCCESS;
		} catch (Exception e) {
			this.success="false";
			logger.error("QuestionInfoAction的方法 insertLinAnswer执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 分页查询问题发送记录
	 * @return
	 */
	public String queryQuestionSendRecordList() {
		
		try {
			initMap();
			if(questionSendRecord==null){
				questionSendRecord=new QuestionSendRecord();
			}
			if(stime!=null&&!stime.equals("")&&!stime.equals("null")){
				questionSendRecord.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(etime!=null&&!etime.equals("")&&!etime.equals("null")){
				questionSendRecord.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(content!=null&&!content.equals("")&&!content.equals("null")){
				questionSendRecord.setContent(URLDecoder.decode(content,"UTF-8"));
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				questionSendRecord.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				questionSendRecord.setUserid(SessionUtils.getUserId());
			}
			result=questionService.selectCheckQuestionSendRecord(this.getPage(),this.getLimit(), questionSendRecord);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("QuestionInfoAction的方法 queryQuestionSendRecordList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 分页查询问题
	 * @return
	 */
	public String queryQuestionList() {
		
		try {
			initMap();
			if(questionInfo==null){
				questionInfo=new QuestionInfo();
			}
			//删除临时数据
			questionService.deleteAnswerInfoLin();
			
			if(content!=null&&!content.equals("")&&!content.equals("null")){
				questionInfo.setContent(URLDecoder.decode(content,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				questionInfo.setUserid(SessionUtils.getUserId());
			}
			result=questionService.selectCheckQuestionInfo(this.getPage(),this.getLimit(), questionInfo);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("QuestionInfoAction的方法 queryQuestionList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 添加或修改问题
	 * @param questionInfo
	 * @return
	 */
	public String saveQuestionInfo(){
		try {
			initMap();
			if(questionInfo==null){
				questionInfo=new QuestionInfo();
			}
			this.user = SessionUtils.getUser();
			questionInfo.setUserid(SessionUtils.getUserId());
			questionInfo.setBlocid(SessionUtils.getBlocId());
			if (questionInfo.getEmergency()==null||questionInfo.getEmergency().equals("")||questionInfo.getEmergency().equals("null")) {
				questionInfo.setEmergency(0);
			} 
		    if (questionInfo.getTts()==null||questionInfo.getTts().equals("")||questionInfo.getTts().equals("null")) {
				questionInfo.setTts(0);
			} 
		    if (questionInfo.getAdv()==null||questionInfo.getAdv().equals("")||questionInfo.getAdv().equals("null")) {
				questionInfo.setAdv(0);
			} 
				if(questionInfo.getId()==null){
					int count = questionService.insertQuestionInfo(questionInfo);
					if(count<=0){
						result.put("su", -1);
					}else{
							//把临时数据变成真实数据
							int qu= questionService.updateAnswerInfoByQid(count);
							if(qu<=0){
								result.put("su", -1);
							}else{
								result.put("su", 1);
							}
					}
				}else{
					int re=questionService.updateQuestionInfo(questionInfo);
					if(re<=0){
						result.put("su", -1);
					}else{
						//先删除真实答案
						questionService.deleteAnswerInfoLinByFlag(questionInfo.getId());
						//把临时数据变成真实数据
						questionService.updateAnswerInfoByQid(questionInfo.getId());
						result.put("su", 1);
					}
			}
			this.success="true";
			return SUCCESS;
			
		} catch (Exception e) {
			this.success="false";
			logger.error("QuestionInfoAction的方法 saveQuestionInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 添加或修改问题答案
	 * @param questionInfo
	 * @return
	 */
	public String saveAnswerInfo(){
		try {
			initMap();
			if(answerInfo==null){
				answerInfo=new AnswerInfo();
			}
				if(answerInfo.getId()==null){
					this.user = SessionUtils.getUser();
					answerInfo.setUserid(SessionUtils.getUserId());
					answerInfo.setBlocid(SessionUtils.getBlocId());
					answerInfo.setFlag(1);
					int count = questionService.insertAnswerInfo(answerInfo);
					if(count<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					}
				}else{
					int re=questionService.updateAnswerInfo(answerInfo);
					if(re<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					}
			}
			this.success="true";
			return SUCCESS;
			
		} catch (Exception e) {
			this.success="false";
			logger.error("QuestionInfoAction的方法 saveAnswerInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 问题下发
	 * @param questionInfo
	 * @return
	 */
	public String CheckQuestion(){
		try {
			initMap();
			if(questionInfo==null){
				questionInfo=new QuestionInfo();
			}
			if(questionSendRecord==null){
				questionSendRecord=new QuestionSendRecord();
			}
			String id[] = ques.split(",");
			
			List<CarInfo> list=this.SelectCarInfo();
			for (CarInfo carInfo : list) {
				
				for (int i = 0; i < id.length; i++) {
					//下发
					List<Question> lists=this.SelectQuestion(Integer.parseInt(id[i]));
					questionInfo=questionService.selectCheckQuestionInfoById(Integer.parseInt(id[i]));
					String type="";
					if (questionInfo.getEmergency()==0) {
						type=type+"000";
					} else{
						type=type+"100";
					}
				    if (questionInfo.getTts()==0) {
				    	type=type+"0";
					} else{
						type=type+"1";
					}
				    if (questionInfo.getAdv()==0) {
				    	type=type+"0000";
					} else{
						type=type+"1000";
					}
					int seq=HandleUtil.getSerialId(); 
					String data=HandleUtil.sendQuestion(carInfo.getUsertype(),carInfo.getTerminal(),lists,questionInfo.getContent()
							,questionInfo.getEmergency(),questionInfo.getTts(),questionInfo.getAdv(),seq,carInfo.getCarnumber());
					
					questionSendRecord.setBlocid(questionInfo.getBlocid());
					questionSendRecord.setUserid(questionInfo.getUserid());
					questionSendRecord.setContent(questionInfo.getContent());
					questionSendRecord.setEmergency(questionInfo.getEmergency());
					questionSendRecord.setTts(questionInfo.getTts());
					questionSendRecord.setAdv(questionInfo.getAdv());
					questionSendRecord.setSeq(seq);
					questionSendRecord.setQid(questionInfo.getId());
					questionSendRecord.setData(data);
					questionSendRecord.setCarnumber(carInfo.getCarnumber());
					//添加到记录表中
					questionService.insertQuestionSendRecord(questionSendRecord);
							
				}
			}
			
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("TextInfoAction的方法 CheckTextInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 *  查询答案列表——Question对象
	 * @param questionInfo
	 * @return
	 */
	public List<Question> SelectQuestion(int id){
		try {
				initMap();
				List<Question> list=new ArrayList<Question>();
				List<AnswerInfo> alist=questionService.selectAnswerInfoByQid(id);
				for (AnswerInfo answerInfo : alist) {
					Question q=new Question();
					q.setId(answerInfo.getAnswerid());
					q.setContent(answerInfo.getAnswer());
					q.setLen(answerInfo.getAnswer().getBytes().length);
					list.add(q);
				}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("QuestionInfoAction的方法 SelectQuestion执行出错，原因：" + e, e);
			return null;
		}
	}
	
	/**
	 * 查询车辆信息
	 * @param questionInfo
	 * @return
	 */
	public List<CarInfo> SelectCarInfo(){
		try {
			initMap();
			List<CarInfo> list=new ArrayList<CarInfo>();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				list.add(carService.getCarInfoCarId(Integer.parseInt(id[i])));
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("QuestionInfoAction的方法 SelectCarInfo执行出错，原因：" + e, e);
			return null;
		}
	}
	
	/**
	 * 删除问题
	 * @param questionInfo
	 * @return
	 */
	public String deleteQuestionInfo(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
					questionService.deleteQuestionInfo(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("QuestionInfoAction的方法 deleteQuestionInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 删除问题临时答案
	 * @param deleteAnswerInfo
	 * @return
	 */
	public String deleteAnswerInfo(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
					questionService.deleteAnswerInfoLinById(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("QuestionInfoAction的方法 deleteAnswerInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 删除问题临时答案
	 * @param deleteAnswerInfo
	 * @return
	 */
	public String deleteAnswer(){
		try {
			initMap();
			questionService.deleteAnswerInfoLin();
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("QuestionInfoAction的方法 deleteAnswer执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	public QuestionService getQuestionService() {
		return questionService;
	}


	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}


	public CarService getCarService() {
		return carService;
	}


	public void setCarService(CarService carService) {
		this.carService = carService;
	}


	public CarInfo getCarInfo() {
		return carInfo;
	}


	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}


	public QuestionInfo getQuestionInfo() {
		return questionInfo;
	}


	public void setQuestionInfo(QuestionInfo questionInfo) {
		this.questionInfo = questionInfo;
	}


	public AnswerInfo getAnswerInfo() {
		return answerInfo;
	}


	public void setAnswerInfo(AnswerInfo answerInfo) {
		this.answerInfo = answerInfo;
	}


	public HandleUtil getHandleUtil() {
		return handleUtil;
	}


	public void setHandleUtil(HandleUtil handleUtil) {
		this.handleUtil = handleUtil;
	}


	public QuestionSendRecord getQuestionSendRecord() {
		return questionSendRecord;
	}


	public void setQuestionSendRecord(QuestionSendRecord questionSendRecord) {
		this.questionSendRecord = questionSendRecord;
	}


	public Map getResult() {
		return result;
	}


	public void setResult(Map result) {
		this.result = result;
	}


	public String getSuccess() {
		return success;
	}


	public void setSuccess(String success) {
		this.success = success;
	}


	public List getList() {
		return list;
	}


	public void setList(List list) {
		this.list = list;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public String getQues() {
		return ques;
	}


	public void setQues(String ques) {
		this.ques = ques;
	}





	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public int getSu() {
		return su;
	}


	public void setSu(int su) {
		this.su = su;
	}


	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public BlocUser getUser() {
		return user;
	}

	public void setUser(BlocUser user) {
		this.user = user;
	}
	
	
}
