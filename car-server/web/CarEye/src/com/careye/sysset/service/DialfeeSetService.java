package com.careye.sysset.service;

import java.util.List;
import java.util.Map;

import com.careye.sysset.domain.AlarmtypeSet;
import com.careye.sysset.domain.DeviceType;
import com.careye.sysset.domain.DialfeeSet;
import com.careye.sysset.domain.PageSet;
import com.careye.utils.SessionUtils;

/**
* @项目名称：FMS
* @类名称：SetService
* @类描述：
* @创建人：lenovo
* @创建时间：2014-7-13 上午10:24:30
* @修改人：lenovo
* @修改时间：2014-7-13 上午10:24:30
* @修改备注：
* @version 1.0
 */
public interface DialfeeSetService {
	
	
	/**
	 * 分页电召费用设置列表
	 * @return
	 */
	public Map findPageDialfeeSetList(int currentPage, int everyPage,DialfeeSet dialfeeSet);
	
	/**
	 * 添加电召费用设置
	 * @return
	 */
	public int insertDialfeeSet(DialfeeSet DialfeeSet);
	
	
	/**
	 * 得到电召费用最新的一条记录
	 * @return
	 */
	public DialfeeSet getDialfeeSetMaxId();
	
}
