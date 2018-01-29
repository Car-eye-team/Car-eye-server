package com.careye.customer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.customer.domain.ComplaintCount;
import com.careye.customer.domain.ComplaintInfo;
import com.careye.customer.domain.ComplaintType;
import com.careye.customer.service.ComplaintService;
import com.opensymphony.xwork2.Result;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-12 下午05:10:26
 * @修改人：ll
 * @修改时间：2016-5-12 下午05:10:26
 * @修改备注：
 * @version 1.0
 */
public class ComplaintServiceImpl extends GenericService implements
		ComplaintService {
	
	/**
	 * 得到所有投诉类型列表
	 */
	public List getAllComplaintTypeList(){
		return this.baseDao.queryForList("oracle-complaintSQL.getAllComplaintTypeList");
	}

	/**
	 * 分页查询投诉类型列表
	 */
	public Map getComplaintTypeList(Integer currentPage, Integer everyPage, ComplaintType complaintType){
		return this.baseDao.findPageList("oracle-complaintSQL.getComplaintTypeList", 
				"oracle-complaintSQL.getComplaintTypeListCount", complaintType, currentPage, everyPage);
	}
	
	/**
	 * 新增投诉类型
	 */
	public int saveComplaintType(ComplaintType complaintType){
		int resultCount = -2;
		int typeCount = (Integer) this.baseDao.queryForObject("oracle-complaintSQL.queryComplaintTypeIsExits", complaintType);
		if(typeCount > 0){
			return -1;
		}else{
			int typenameCount = (Integer) this.baseDao.queryForObject("oracle-complaintSQL.queryComplaintTypenameIsExits", complaintType);
			if(typenameCount > 0){
				return -3;
			}else{
				Integer id = complaintType.getId();
				if(id == null){
					resultCount = this.baseDao.save("oracle-complaintSQL.addComplaintType", complaintType);
				}else{
					resultCount = this.baseDao.update("oracle-complaintSQL.updateComplaintType", complaintType);
				}
			}
		}
		return resultCount;
		
	}
	
	/**
	 * 删除投诉类型
	 */
	public int delComplaintType(List list){
		return this.baseDao.delete("oracle-complaintSQL.delComplaintType", list);
	}
	
	/**
	 * 分页查询投诉列表
	 */
	public Map getComplaintInfoList(Integer currentPage, Integer everyPage, ComplaintInfo complaintInfo){
		return this.baseDao.findPageList("oracle-complaintSQL.getComplaintInfoList", 
				"oracle-complaintSQL.getComplaintInfoListCount", complaintInfo, currentPage, everyPage);
	}
	
	/**
	 * 删除投诉
	 */
	public int delComplaintInfo(List list){
		return this.baseDao.delete("oracle-complaintSQL.delComplaintInfo", list);
	}
	
	/**
	 * 投诉处理
	 */
	public int dealComplaintInfo(ComplaintInfo complaintInfo){
		return this.baseDao.update("oracle-complaintSQL.dealComplaintInfo", complaintInfo);
	}
	
	/**
	 * 导出投诉信息
	 */
	public List exportExcel(ComplaintInfo complaintInfo){
		return this.baseDao.queryForList("oracle-complaintSQL.getComplaintInfoList", complaintInfo);
	}
	
	/**
	 * 查询投诉按年列表
	 */
	public List<ComplaintCount> getAllComplaintCountByYear(ComplaintCount complaintCount){
		List<ComplaintCount> comList = new ArrayList<ComplaintCount>();
		
		ComplaintCount comCount = (ComplaintCount) this.baseDao.queryForObject("oracle-complaintSQL.getAllComplaintCountByYear", complaintCount);
		
		ComplaintCount info = new ComplaintCount();
		info.setDatetime(complaintCount.getYear()+"-01");
		info.setBlocname(complaintCount.getBlocname());
		info.setCount(comCount.getOne());
		comList.add(info);
		info = new ComplaintCount();
		info.setDatetime(complaintCount.getYear()+"-02");
		info.setBlocname(complaintCount.getBlocname());
		info.setCount(comCount.getTwo());
		comList.add(info);
		info = new ComplaintCount();
		info.setDatetime(complaintCount.getYear()+"-03");
		info.setBlocname(complaintCount.getBlocname());
		info.setCount(comCount.getThree());
		comList.add(info);
		info = new ComplaintCount();
		info.setDatetime(complaintCount.getYear()+"-04");
		info.setBlocname(complaintCount.getBlocname());
		info.setCount(comCount.getFour());
		comList.add(info);
		info = new ComplaintCount();
		info.setDatetime(complaintCount.getYear()+"-05");
		info.setBlocname(complaintCount.getBlocname());
		info.setCount(comCount.getFive());
		comList.add(info);
		info = new ComplaintCount();
		info.setDatetime(complaintCount.getYear()+"-06");
		info.setBlocname(complaintCount.getBlocname());
		info.setCount(comCount.getSix());
		comList.add(info);
		info = new ComplaintCount();
		info.setDatetime(complaintCount.getYear()+"-07");
		info.setBlocname(complaintCount.getBlocname());
		info.setCount(comCount.getSeven());
		comList.add(info);
		info = new ComplaintCount();
		info.setDatetime(complaintCount.getYear()+"-08");
		info.setBlocname(complaintCount.getBlocname());
		info.setCount(comCount.getEight());
		comList.add(info);
		info = new ComplaintCount();
		info.setDatetime(complaintCount.getYear()+"-09");
		info.setBlocname(complaintCount.getBlocname());
		info.setCount(comCount.getNine());
		comList.add(info);
		info = new ComplaintCount();
		info.setDatetime(complaintCount.getYear()+"-10");
		info.setBlocname(complaintCount.getBlocname());
		info.setCount(comCount.getTen());
		comList.add(info);
		info = new ComplaintCount();
		info.setDatetime(complaintCount.getYear()+"-11");
		info.setBlocname(complaintCount.getBlocname());
		info.setCount(comCount.getEleven());
		comList.add(info);
		info = new ComplaintCount();
		info.setDatetime(complaintCount.getYear()+"-12");
		info.setBlocname(complaintCount.getBlocname());
		info.setCount(comCount.getTwelve());
		comList.add(info);
		
		return comList;
	}
	
	/**
	 * 查询投诉按月列表
	 */
	public List<ComplaintCount> getAllComplaintCountByMonth(ComplaintCount complaintCount){
		return this.baseDao.queryForList("oracle-complaintSQL.getAllComplaintCountByMonth", complaintCount);
	}
	
	/**
	 * 分页查询投诉统计详情
	 */
	public Map getComplaintCountDetails(Integer currentPage, Integer everyPage, ComplaintCount complaintCount){
		return this.baseDao.findPageList("oracle-complaintSQL.getComplaintCountDetails", 
				"oracle-complaintSQL.getComplaintCountDetailsCount", complaintCount, currentPage, everyPage);
	}

	@Override
	public List<ComplaintInfo> getTSWordByMonth(ComplaintCount complaintCount) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-complaintSQL.getTSWordByMonth",complaintCount);
	}

	@Override
	public List<ComplaintInfo> getTSWordByYear(ComplaintCount complaintCount) {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-complaintSQL.getTSWordByYear",complaintCount);
	}

}
