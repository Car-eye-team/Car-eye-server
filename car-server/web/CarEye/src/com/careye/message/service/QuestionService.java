/**
* Description: 多森商用车平台
* 文件名：CarService.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.service;

import java.util.List;
import java.util.Map;

import com.careye.message.domain.AnswerInfo;
import com.careye.message.domain.EventCar;
import com.careye.message.domain.QuestionInfo;
import com.careye.message.domain.QuestionSendRecord;


/**
 * @项目名称：FMS
 * @类名称：EventService
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:00:03
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public interface QuestionService {
	
	/**
	 * id查询问题
	 * @param questionInfo
	 * @return
	 */
	public QuestionInfo selectQuestionInfoById(int id);

	/**
	 * 查询问题答案文本
	 * @param questionInfo
	 * @return
	 */
	public String selectAnswerContent(int aid);

	/**
	 * 通过问题ID查询 问题答案表
	 * @param AnswerInfo
	 * @return
	 */
	public List<AnswerInfo> selectAnswerInfoByQid(int qid);
	/**
	 * 通过问题ID查询 问题答案表  临时表
	 * @param AnswerInfo
	 * @return
	 */
	public List<AnswerInfo> selectAnswerInfoByQidLin(int qid);
	/**
	 * 通过问题ID查询 问题答案表  临时表
	 * @param AnswerInfo
	 * @return
	 */
	public List<AnswerInfo> selectAnswerInfoLin();
	
	
	/**
	 * 通过问题ID查询 问题表  
	 * @return
	 */
	public QuestionInfo selectCheckQuestionInfoById(int id);
	
	
	/**
	 * 更新问题发送记录
	 * @param QuestionSendRecord
	 * @return
	 */
	public int updateQuestionSendRecord(QuestionSendRecord questionSendRecord);
	
	
	/**
	 * 添加问题发送记录
	 * @param QuestionSendRecord
	 * @return
	 */
	public int insertQuestionSendRecord(QuestionSendRecord questionSendRecord);
	
	/**
	 * 更新问题答案 
	 * @param AnswerInfo
	 * @return
	 */
	public int updateAnswerInfo(AnswerInfo answerInfo);
	
	/**
	 * 更新问题答案 
	 * @param AnswerInfo
	 * @return
	 */
	public int updateAnswerInfoByQid(int qid);
	
	
	/**
	 * 添加问题答案 
	 * @param AnswerInfo
	 * @return
	 */
	public int insertAnswerInfo(AnswerInfo answerInfo);
	
	/**
	 * 根据流水号更新问题处理结果
	 * @param questionSendRecord
	 * @return
	 */
	public int updateQuestionResult(QuestionSendRecord questionSendRecord);
	
	/**
	 * 提问应答
	 * @param questionSendRecord
	 * @return
	 */
	public int updateQuestionAnswer(QuestionSendRecord questionSendRecord);
	
	/**
	 * 更新问题
	 * @param questionInfo
	 * @return
	 */
	public int updateQuestionInfo(QuestionInfo questionInfo);
	
	
	/**
	 * 删除问题
	 * @param id
	 * @return
	 */
	public int deleteQuestionInfo(int id);
	
	/**
	 * 删除临时问题答案
	 * @param id
	 * @return
	 */
	public int deleteAnswerInfoLin();	
	/**
	 * 删除真实问题答案
	 * @param id
	 * @return
	 */
	public int deleteAnswerInfoLinByFlag(int id);	
	
	/**
	 * 删除临时问题答案
	 * @param id
	 * @return
	 */
	public int deleteAnswerInfoLinById(int id);	
	
	/**
	 *  删除问题答案
	 * @param id
	 * @return
	 */
	public int deleteAnswerInfo(int qid);
	
	/**
	 * 添加问题
	 * @param questionInfo
	 * @return
	 */
	public int insertQuestionInfo(QuestionInfo questionInfo);
	
	
	/**
	 * 条件查询问题
	 * @param questionInfo
	 * @return
	 */
	public Map selectCheckQuestionInfo(final int currentPage, final int everyPage,QuestionInfo questionInfo);

	/**
	 * 条件查询问题发送记录
	 * @param questionSendRecord
	 * @return
	 */
	public Map selectCheckQuestionSendRecord(final int currentPage, final int everyPage,
			QuestionSendRecord questionSendRecord);

	
}
