/**
 * 
 */
package com.careye.transaction.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.transaction.domain.EvaluateCount;
import com.careye.transaction.service.EvaluateCountService;

/**
 * @author Administrator
 *
 */
public class EvaluateCountServiceImpl extends GenericService implements EvaluateCountService{
	private SysOperateLogService logService;
	
	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	public List<EvaluateCount> getEvaluateCountListByYear(
			EvaluateCount evaluateCount) {
		List <EvaluateCount> eclist=new ArrayList<EvaluateCount>();
		
		evaluateCount.setEvalevel(1);
		EvaluateCount  ec1 =  (EvaluateCount) this.baseDao.queryForObject("oracle-evaluateCountSQL.getEvaluateCountListByYear",evaluateCount);
		evaluateCount.setEvalevel(3);
		EvaluateCount  ec2 =  (EvaluateCount) this.baseDao.queryForObject("oracle-evaluateCountSQL.getEvaluateCountListByYear",evaluateCount);
		evaluateCount.setEvalevel(2);
		EvaluateCount  ec3 =  (EvaluateCount) this.baseDao.queryForObject("oracle-evaluateCountSQL.getEvaluateCountListByYear",evaluateCount);
		
		EvaluateCount statement=new EvaluateCount();
		statement.setDatetime(evaluateCount.getYear()+"-01");
	    statement.setCount1(ec1.getOne());
	    statement.setCount2(ec2.getOne());
	    statement.setCount3(ec3.getOne());
	    eclist.add(statement);
	    statement=new EvaluateCount();
	    statement.setDatetime(evaluateCount.getYear()+"-02");
	    statement.setCount1(ec1.getTwo());
	    statement.setCount2(ec2.getTwo());
	    statement.setCount3(ec3.getTwo());
	    eclist.add(statement);
	    statement=new EvaluateCount();
	    statement.setDatetime(evaluateCount.getYear()+"-03");
	    statement.setCount1(ec1.getThree());
	    statement.setCount2(ec2.getThree());
	    statement.setCount3(ec3.getThree());
	    eclist.add(statement);
	    statement=new EvaluateCount();
	    statement.setDatetime(evaluateCount.getYear()+"-04");
	    statement.setCount1(ec1.getFour());
	    statement.setCount2(ec2.getFour());
	    statement.setCount3(ec3.getFour());
	    eclist.add(statement);
	    statement=new EvaluateCount();
	    statement.setDatetime(evaluateCount.getYear()+"-05");
	    statement.setCount1(ec1.getFive());
	    statement.setCount2(ec2.getFive());
	    statement.setCount3(ec3.getFive());
	    eclist.add(statement);
	    statement=new EvaluateCount();
	    statement.setDatetime(evaluateCount.getYear()+"-06");
	    statement.setCount1(ec1.getSix());
	    statement.setCount2(ec2.getSix());
	    statement.setCount3(ec3.getSix());
	    eclist.add(statement);
	    statement=new EvaluateCount();
	    statement.setDatetime(evaluateCount.getYear()+"-07");
	    statement.setCount1(ec1.getSeven());
	    statement.setCount2(ec2.getSeven());
	    statement.setCount3(ec3.getSeven());
	    eclist.add(statement);
	    statement=new EvaluateCount();
	    statement.setDatetime(evaluateCount.getYear()+"-08");
	    statement.setCount1(ec1.getEight());
	    statement.setCount2(ec2.getEight());
	    statement.setCount3(ec3.getEight());
	    eclist.add(statement);
	    statement=new EvaluateCount();
	    statement.setDatetime(evaluateCount.getYear()+"-09");
	    statement.setCount1(ec1.getNine());
	    statement.setCount2(ec2.getNine());
	    statement.setCount3(ec3.getNine());
	    eclist.add(statement);
	    statement=new EvaluateCount();
	    statement.setDatetime(evaluateCount.getYear()+"-10");
	    statement.setCount1(ec1.getTen());
	    statement.setCount2(ec2.getTen());
	    statement.setCount3(ec3.getTen());
	    eclist.add(statement);
	    statement=new EvaluateCount();
	    statement.setDatetime(evaluateCount.getYear()+"-11");
	    statement.setCount1(ec1.getEleven());
	    statement.setCount2(ec2.getEleven());
	    statement.setCount3(ec3.getEleven());
	    eclist.add(statement);
	    statement=new EvaluateCount();
	    statement.setDatetime(evaluateCount.getYear()+"-12");
	    statement.setCount1(ec1.getTwelve());
	    statement.setCount2(ec2.getTwelve());
	    statement.setCount3(ec3.getTwelve());
	    eclist.add(statement);
	    
	    return eclist;
	}

	public List<EvaluateCount> getEvaluateCountListByMonth(
			EvaluateCount evaluateCount) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-evaluateCountSQL.getEvaluateCountListByMonth",evaluateCount);
	}

	@Override
	public String selBlocname(int blocid) {
		// TODO Auto-generated method stub
		return (String) this.baseDao.queryForObject("oracle-evaluateCountSQL.selBlocname", blocid);
	}

	/**
	 * word导出的详情列表
	 */
	//年
	public List<EvaluateCount> getECListByYear(EvaluateCount evaluateCount) {
		return this.baseDao.queryForList("oracle-evaluateCountSQL.getECListByMonth",evaluateCount);
	}
	//月
	public List<EvaluateCount> getECListByMonth(EvaluateCount evaluateCount) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-evaluateCountSQL.getECListByMonth",evaluateCount);
	}
	
	
	
	/**
	 * 分页查询评价统计详情
	 */
	public Map getEvaluateCountDetails(Integer currentPage, Integer everyPage, EvaluateCount evaluateCount){
		return this.baseDao.findPageList("oracle-evaluateCountSQL.getEvaluateCountDetails", 
				"oracle-evaluateCountSQL.getEvaluateCountDetailsCount", evaluateCount, currentPage, everyPage);
	}

}
