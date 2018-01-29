/**
 * Description: car-eye车辆管理平台
 * 文件名：CustomerService.java
 * 版本信息：1.0
 * 日期：2014-3-6
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.transaction.service;

import java.util.List;
import java.util.Map;

import com.careye.transaction.domain.Transaction;
import com.careye.transaction.domain.TransactionAnswer;
import com.careye.transaction.domain.TransactionType;

/**
 * @项目名称：car-eyeTms
 * @类名称：TransactionService
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-3-6 上午11:57:21
 * @修改人：lxh
 * @修改时间：2014-3-6 上午11:57:21
 * @修改备注：
 * @version 1.0
 */
public interface TransactionService {

	/**
	 * 得到所有的客户信息
	 * 
	 * @param petFence
	 * @return
	 */
	public Map<Object, Object> getAllTransaction(final int currentPage,
			final int everyPage, Transaction transaction);

	/**
	 * Excel 导出
	 * 
	 * @param transaction
	 * @return
	 */
	public List<Transaction> findTransactionPageList(Transaction transaction);

	/**
	 * 添加客户信息
	 * 
	 * @param petFence
	 * @return
	 */
	public int addTransaction(Transaction transaction);

	/**
	 * 删除客户信息
	 * 
	 * @param id
	 * @return
	 */
	public int delTransaction(List<String> list);

	/**
	 * 修改客户信息
	 * 
	 * @param petFence
	 * @return
	 */
	public int updateTransaction(Transaction transaction);

	/**
	 * 得到所有的客户类型下拉列表
	 * 
	 * @return
	 */
	public List<TransactionType> selTransactionTypeList();


	/******************************************************************************************/

	/**
	 * 得到所有的客户类型信息
	 * 
	 * @param petFence
	 * @return
	 */
	public Map<Object, Object> getAllTransactionType(final int currentPage,
			final int everyPage, TransactionType transactionType);

	/**
	 * Excel 导出
	 * 
	 * @param transactionType
	 * @return
	 */
	public List<TransactionType> findTransactionTypePageList(TransactionType transactionType);
	
	/**
	 * 添加客户类型信息
	 * 
	 * @param petFence
	 * @return
	 */
	public int addTransactionType(TransactionType transactionType);

	/**
	 * 删除客户类型信息
	 * 
	 * @param id
	 * @return
	 */
	public int delTransactionType(List<String> list);

	/**
	 * 修改客户类型信息
	 * 
	 * @param petFence
	 * @return
	 */
	public int updateTransactionType(TransactionType transactionType);

	/**
	 * 查询是否相同的名字存在
	 * 
	 * @param name
	 * @return
	 */
	public Integer queryTransactionTypeNameIsExits(TransactionType transactionType);

	/**
	 * 获取最后一次约车信息
	 * @param phone
	 * @return
	 */
	public Transaction loadLastTransaction(String phone);
	
	
	

	/******************************************************************************************/
	/**
	 * 抢单信息
	 */
	public Map<Object, Object> getAllTransactionAnswer(final int currentPage,
			final int everyPage, TransactionAnswer transactionAnswer);

	/**
	 * 抢单信息导出
	 * @param transactionAnswer
	 * @return
	 */
	public List<TransactionAnswer> exportTransactionAnswerList(
			TransactionAnswer transactionAnswer);
}
