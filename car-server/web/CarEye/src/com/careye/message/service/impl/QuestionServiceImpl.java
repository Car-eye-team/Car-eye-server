/**
* Description: 多森商用车平台
* 文件名：CarServiceImpl.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.message.domain.AnswerInfo;
import com.careye.message.domain.QuestionInfo;
import com.careye.message.domain.QuestionSendRecord;
import com.careye.message.domain.SendRecord;
import com.careye.message.service.EventService;
import com.careye.message.service.QuestionService;
import com.careye.utils.DateUtil;

/**
 * @项目名称：FMS
 * @类名称：QuestionServiceImpl
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:05:25
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:05:25
 * @修改备注：
 * @version 1.0
 */
public class QuestionServiceImpl extends GenericService implements QuestionService{

	/**
	 * id查询问题
	 * @param questionInfo
	 * @return
	 */
	@Override
	public QuestionInfo selectQuestionInfoById(int id) {
		// TODO Auto-generated method stub
		return (QuestionInfo)this.baseDao.queryForObject("oracle-questionSQL.selectQuestionInfoById", id);
	}

	/**
	 * id查询问题
	 * @param questionInfo
	 * @return
	 */
	@Override
	public String selectAnswerContent(int id) {
		// TODO Auto-generated method stub
		return (String)this.baseDao.queryForObject("oracle-questionSQL.selectAnswerContent", id);
	}

	
	/**
	 * 通过问题ID查询 问题答案表
	 * @param questionInfo
	 * @return
	 */
	@Override
	public List<AnswerInfo> selectAnswerInfoByQid(int qid) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-questionSQL.selectAnswerInfoByQid", qid);
	}

	/**
	 * 通过问题ID查询 问题答案表   临时表
	 * @param questionInfo
	 * @return
	 */
	@Override
	public List<AnswerInfo> selectAnswerInfoByQidLin(int qid) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-questionSQL.selectAnswerInfoByQidLin", qid);
	}
	/**
	 * 通过问题ID查询 问题答案表   临时表
	 * @param questionInfo
	 * @return
	 */
	@Override
	public List<AnswerInfo> selectAnswerInfoLin() {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-questionSQL.selectAnswerInfoLin");
	}

	/**
	 * 通过问题ID查询 问题表
	 * @return
	 */
	@Override
	public QuestionInfo selectCheckQuestionInfoById(int id) {
		// TODO Auto-generated method stub
		return (QuestionInfo)this.baseDao.queryForObject("oracle-questionSQL.selectCheckQuestionInfoById", id);
	}
	/**
	 * 添加问题发送记录
	 * @param questionInfo
	 * @return
	 */
	@Override
	public int insertQuestionSendRecord(QuestionSendRecord questionSendRecord) {
		// TODO Auto-generated method stub
		questionSendRecord.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-questionSQL.insertQuestionSendRecord", questionSendRecord);
	}


	/**
	 * 更新问题发送记录
	 * @param questionInfo
	 * @return
	 */
	@Override
	public int updateQuestionSendRecord(QuestionSendRecord questionSendRecord) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-questionSQL.updateQuestionSendRecord", questionSendRecord);
	}

	/**
	 * 添加问题答案 
	 * @param questionInfo
	 * @return
	 */
	@Override
	public int insertAnswerInfo(AnswerInfo eventCar) {
		// TODO Auto-generated method stub
		eventCar.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-questionSQL.insertAnswerInfo", eventCar);
	}


	/**
	 * 更新问题答案 
	 * @param questionInfo
	 * @return
	 */
	@Override
	public int updateAnswerInfo(AnswerInfo answerInfo) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-questionSQL.updateAnswerInfo", answerInfo);
	}
	/**
	 * 更新问题答案 
	 * @param questionInfo
	 * @return
	 */
	@Override
	public int updateAnswerInfoByQid(int qid) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-questionSQL.updateAnswerInfoByQid", qid);
	}
	/**
	 * 删除问题
	 * @param id
	 * @return
	 */
	@Override
	public int deleteQuestionInfo(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-questionSQL.deleteQuestionInfo", id);
	}

	/**
	 * 删除问题答案
	 * @param id
	 * @return
	 */
	@Override
	public int deleteAnswerInfo(int qid) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-questionSQL.deleteAnswerInfo", qid);
	}
	/**
	 * 删除临时问题答案
	 * @param id
	 * @return
	 */
	@Override
	public int deleteAnswerInfoLin() {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-questionSQL.deleteAnswerInfoLin",null);
	}
	/**
	 * 删除临时问题答案
	 * @param id
	 * @return
	 */
	@Override
	public int deleteAnswerInfoLinByFlag(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-questionSQL.deleteAnswerInfoLinByFlag",id);
	}
	/**
	 * 删除临时问题答案
	 * @param id
	 * @return
	 */
	@Override
	public int deleteAnswerInfoLinById(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-questionSQL.deleteAnswerInfoLinById",id);
	}
	/**
	 * 添加问题
	 * @param questionInfo
	 * @return
	 */
	@Override
	public int insertQuestionInfo(QuestionInfo questionInfo) {
		// TODO Auto-generated method stub
		questionInfo.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-questionSQL.insertQuestionInfo", questionInfo);
	}

     /**
	 * 查询问题
	 * @param questionInfo
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckQuestionInfo(int currentPage, int everyPage,
			QuestionInfo questionInfo) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-questionSQL.selectCheckQuestionInfo",
				"oracle-questionSQL.selectQuestionInfo", questionInfo,currentPage,everyPage);
	}

	/**
	 * 更新问题
	 * @param questionInfo
	 * @return
	 */
	@Override
	public int updateQuestionInfo(QuestionInfo questionInfo) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-questionSQL.updateQuestionInfo", questionInfo);
	}

	/**
	 * 根据流水号更新问题处理结果
	 * @param questionSendRecord
	 * @return
	 */
	@Override
	public int updateQuestionResult(QuestionSendRecord questionSendRecord) {
		return this.baseDao.update("oracle-questionSQL.updateQuestionResult", questionSendRecord);
	}

	/**
	 * 提问应答
	 * @param questionSendRecord
	 * @return
	 */
	@Override
	public int updateQuestionAnswer(QuestionSendRecord questionSendRecord) {
		return this.baseDao.update("oracle-questionSQL.updateQuestionAnswer", questionSendRecord);
	}

    /**
	 * 查询问题发送记录
	 * @param questionSendRecord
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckQuestionSendRecord(int currentPage, int everyPage,
			QuestionSendRecord questionSendRecord) {
		// TODO Auto-generated method stub
		Map m= this.baseDao.findPageList("oracle-questionSQL.selectCheckQuestionSendRecord",
				"oracle-questionSQL.selectQuestionSendRecord", questionSendRecord,currentPage,everyPage);
		List<QuestionSendRecord> list=(List<QuestionSendRecord>)m.get("list");
		for (QuestionSendRecord qsr : list) {
			if (qsr.getAid()!=null) {
				qsr.setAnswer(this.selectAnswerContent(qsr.getAid()));
			} 
		}
		return m;
	}

	
}
