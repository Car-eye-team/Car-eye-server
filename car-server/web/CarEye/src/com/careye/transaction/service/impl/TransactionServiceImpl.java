/**
 * Description: car-eye车辆管理平台
 * 文件名：CustomerInfoServiceImpl.java
 * 版本信息：1.0
 * 日期：2014-3-6
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.transaction.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.transaction.domain.Transaction;
import com.careye.transaction.domain.TransactionAnswer;
import com.careye.transaction.domain.TransactionType;
import com.careye.transaction.service.TransactionService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：car-eyeTms
 * @类名称：TransactionServiceImpl
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-3-6 下午12:39:55
 * @修改人：lxh
 * @修改时间：2014-3-6 下午12:39:55
 * @修改备注：
 * @version 1.0
 */
public class TransactionServiceImpl extends GenericService implements
		TransactionService {

	private SysOperateLogService logService;

	public Map<Object, Object> getAllTransaction(int currentPage,
			int everyPage, Transaction transaction) {
		return this.baseDao.findPageList(
				"oracle-transactionSQL.getAllTransaction",
				"oracle-transactionSQL.getAllTransactionCount", transaction,
				currentPage, everyPage);
	}

	/**
	 *Excel导出
	 */
	@Override
	public List<Transaction> findTransactionPageList(Transaction transaction) {
		logService.addLogInfo(SessionUtils.getUserId(), "Excel导出约车业务信息", 4);
		return this.baseDao.queryForList(
				"oracle-transactionSQL.getAllTransaction", transaction);
	}

	/**
	 * 添加约车业务信息
	 * 
	 * @param carDriver
	 * @return
	 */
	// @Log(operationType="add操作:",operationName="添加约车业务信息记录")
	public int addTransaction(Transaction transaction) {
		int result = 0;
		transaction.setCreatetime(DateUtil.getSQLDate());
		transaction.setStarttime(DateUtil.getSQLDate());
		transaction.setStatus(0);
		result = this.baseDao.save("oracle-transactionSQL.addTransaction",
				transaction);
		if (result > 0) {
			logService.addLogInfo(transaction.getUserid(),
					"添加约车业务信息记录:用户名称:" + transaction.getUsernametwo()
							+ ";创建时间:" + transaction.getCreatetime()
							+ "召车联系电话：" + transaction.getPhone() + ";影响数据ID:"
							+ transaction.getId(), 1);
		}
		return result;
	}

	/**
	 * 删除约车业务信息记录
	 * 
	 * @param id
	 * @return
	 */
	// @Log(operationType="delete操作:",operationName="删除约车业务信息记录")
	public int delTransaction(List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			// 根据id得到对象
			Transaction transaction = this.getTransactionById(Integer
					.parseInt(list.get(i)));

			logService.addLogInfo(transaction.getUserid(), "删除约车业务信息记录:用户名称:"
					+ transaction.getUsernametwo() + ";创建时间:"
					+ transaction.getCreatetime() + ";影响数据ID:"
					+ transaction.getId(), 3);
		}

		return this.baseDao
				.delete("oracle-transactionSQL.delTransaction", list);
	}

	/**
	 * 根据id得到约车业务信息
	 * 
	 * @param id
	 * @return
	 */
	private Transaction getTransactionById(int id) {

		return (Transaction) this.baseDao.queryForObject(
				"oracle-transactionSQL.getTransactionById", id);
	}

	/**
	 * 更新约车业务信息
	 * 
	 * @param carDriver
	 * @return
	 */
	// @Log(operationType="update操作:",operationName="更新约车业务信息记录")
	public int updateTransaction(Transaction transaction) {
		int result = 0;
		transaction.setCreatetime(DateUtil.getSQLDate());

		if (!SessionUtils.getUser().equals("admin")) { // 普通用户
			transaction.setUserid(SessionUtils.getUser().getUserid());
		}
		result = this.baseDao.update("oracle-transactionSQL.updateTransaction",
				transaction);

		logService.addLogInfo(SessionUtils.getUserId(), "更新约车业务信息记录:用户名称:"
				+ transaction.getUsernametwo() + ";创建时间:"
				+ transaction.getCreatetime() + ";影响数据ID:"
				+ transaction.getId(), 2);
		return result;
	}

	public Integer queryTransactionIsNew(Transaction transaction) {
		Object obj = this.baseDao.queryForObject(
				"oracle-transactionSQL.queryTransactionIsNew", transaction);
		int num = obj == null ? 0 : (Integer) obj;
		return num;
	}

	@Override
	public List<TransactionType> selTransactionTypeList() {
		return this.baseDao
				.queryForList("oracle-transactionSQL.selTransactionTypeList");
	}

	/**
	 * 通过Id 获取约车业务
	 */
	public String selectTransactionByID(int id) {
		return (String) this.baseDao.queryForObject(
				"oracle-transactionSQL.selectTransactionByID", id);
	}

	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	@Override
	public int addTransactionType(TransactionType transactionType) {
		int areaName = queryTransactionTypeNameIsExits(transactionType);

		int result = 0;
		if (areaName > 0) {
			result = -3;
		} else {
			transactionType.setCreatetime(DateUtil.getSQLDate());
			result = this.baseDao
					.save("oracle-transactionSQL.addTransactionType",
							transactionType);
			if (result > 0) {
				logService.addLogInfo(transactionType.getUserid(),
						"添加约车业务类型信息记录:约车业务类型名称:"
								+ transactionType.getTypename() + ";创建时间:"
								+ transactionType.getCreatetime() + ";影响数据ID:"
								+ transactionType.getId(), 1);
			}
		}
		return result;
	}

	@Override
	public int delTransactionType(List<String> list) {
		int result = 0;
		for (int i = 0; i < list.size(); i++) {
			// 查找是否包含子项
			if (this.getTransactionCountById(Integer.parseInt(list.get(i))) > 0) {
				result = -3;
				return result;
			} else {
				// 根据id得到对象
				TransactionType transactionType = this
						.getTransactionTypeById(Integer.parseInt(list.get(i)));
				logService.addLogInfo(transactionType.getUserid(),
						"删除约车业务类型信息记录:约车业务类型名称:"
								+ transactionType.getTypename() + ";创建时间:"
								+ transactionType.getCreatetime() + ";影响数据ID:"
								+ transactionType.getId(), 3);
			}
		}

		result = this.baseDao.delete(
				"oracle-transactionSQL.delTransactionType", list);
		return result;
	}

	/**
	 * 查找约车业务类型下的资料数量
	 * 
	 * @param id
	 * @return
	 */
	private Integer getTransactionCountById(Integer databanktypeid) {

		return (Integer) this.baseDao
				.queryForObject(
						"oracle-transactionSQL.getTransactionCountById",
						databanktypeid);
	}

	/**
	 * 根据id得到约车业务信息
	 * 
	 * @param id
	 * @return
	 */
	private TransactionType getTransactionTypeById(int id) {

		return (TransactionType) this.baseDao.queryForObject(
				"oracle-transactionSQL.getTransactionTypeById", id);
	}

	@Override
	public Map<Object, Object> getAllTransactionType(int currentPage,
			int everyPage, TransactionType transactionType) {
		return this.baseDao.findPageList(
				"oracle-transactionSQL.getAllTransactionType",
				"oracle-transactionSQL.getAllTransactionTypeCount",
				transactionType, currentPage, everyPage);
	}

	@Override
	public Integer queryTransactionTypeNameIsExits(
			TransactionType transactionType) {
		Object obj = this.baseDao.queryForObject(
				"oracle-transactionSQL.queryTransactionTypeNameIsExits",
				transactionType);
		int num = obj == null ? 0 : (Integer) obj;
		return num;
	}

	@Override
	public int updateTransactionType(TransactionType transactionType) {
		 int areaName = queryTransactionTypeNameIsExits(transactionType);
		int result = 0;
		 if(areaName>0){
		 result=-3;
		 }else{
			transactionType.setCreatetime(DateUtil.getSQLDate());
	
			if (!SessionUtils.getUser().equals("admin")) { // 普通用户
				transactionType.setUserid(SessionUtils.getUser().getId());
			}
	
			result = this.baseDao.update(
					"oracle-transactionSQL.updateTransactionType", transactionType);
			if (result > 0) {
				logService.addLogInfo(transactionType.getUserid(),
						"更新约车业务类型信息记录:约车业务类型名称:" + transactionType.getTypename()
								+ ";创建时间:" + transactionType.getCreatetime()
								+ ";影响数据ID:" + transactionType.getId(), 2);
		}
		 }
		return result;
		// }
	}

	/**
	 * Excel 导出
	 */
	@Override
	public List<TransactionType> findTransactionTypePageList(
			TransactionType transactionType) {
		logService.addLogInfo(SessionUtils.getUserId(), "Excel导出约车业务类型信息", 4);
		return this.baseDao.queryForList(
				"oracle-transactionSQL.getAllTransactionType", transactionType);
	}

	/**
	 * 获取最后一次约车信息
	 * 
	 * @param phone
	 * @return
	 */
	public Transaction loadLastTransaction(String phone) {
		return (Transaction) this.baseDao.queryForObject(
				"oracle-transactionSQL.loadLastTransaction", phone);
	}

	/************************************************************/
	@Override
	public Map<Object, Object> getAllTransactionAnswer(int currentPage,
			int everyPage, TransactionAnswer transactionAnswer) {
		return this.baseDao.findPageList(
				"oracle-transactionSQL.getAllTransactionAnswer",
				"oracle-transactionSQL.getAllTransactionAnswerCount",
				transactionAnswer, currentPage, everyPage);
	}

	/**
	 * findTransactionAnswerList TODO
	 * 
	 * @param transactionAnswer
	 * @return
	 * @see com.careye.transaction.service.TransactionService#findTransactionAnswerList(com.careye.transaction.domain.TransactionAnswer)
	 */
	@Override
	public List<TransactionAnswer> exportTransactionAnswerList(
			TransactionAnswer transactionAnswer) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList(
				"oracle-transactionSQL.getAllTransactionAnswer",
				transactionAnswer);
	}

}
