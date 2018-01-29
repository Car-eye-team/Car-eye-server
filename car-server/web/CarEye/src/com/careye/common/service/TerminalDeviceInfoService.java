package com.careye.common.service;

import java.util.List;
import java.util.Map;

import com.careye.base.action.TreeDomain;
import com.careye.car.domain.CarInfo;
import com.careye.common.domain.CarDetail;
import com.careye.common.domain.Carrealtime;
import com.careye.common.domain.MapInfo;
import com.careye.common.domain.OperaTimeAnalysis;
import com.careye.common.domain.PositionInfo;
import com.careye.common.domain.TerminalHisPosition;
import com.careye.common.domain.TerminalParameter;
import com.careye.common.domain.TerminalPositionInfo;
import com.careye.transaction.domain.Transaction;

/**
* @项目名称：car-eye
* @类名称：TerminalDeviceInfoService
* @类描述：处理车辆信息
* @创建人：zhangrong
* @创建时间：2014-5-22 上午11:38:23
* @修改人：zhangrong
* @修改时间：2014-5-22 上午11:38:23
* @修改备注：
* @version 1.0
 */
public interface TerminalDeviceInfoService {
	
	/**根据车牌号查询最新订单信息**/
	public Transaction queryLastOrderInfo(String carnumber);
	
	/**
	 * word 企业车辆在线向情况导出 获取企业名称
	 * getBname
	 */
	public String getBname(int blocid);
	
	/**
	 * word 企业车辆在线向情况导出 获取离线在线车辆数量
	 * getTpi
	 */
	public TerminalPositionInfo getTpi(TerminalPositionInfo terminalPositionInfo);
	
	/**
	 *  word 企业车辆在线向情况导出 获取离线车辆明细
	 * getTpiList1
	 */
	public List<TerminalPositionInfo> getTpiList1(TerminalPositionInfo terminalPositionInfo);
	
	/**
	 *  word 企业车辆在线向情况导出 获取在线车辆明细
	 * getTpiList2
	 */
	public List<TerminalPositionInfo> getTpiList2(TerminalPositionInfo terminalPositionInfo);
	
	/**
	 * 根据车牌号获取车辆最后位置信息
	 * @param carnumber
	 * @return
	 */
	public TerminalPositionInfo getTerminalPositionInfo(String carnumber);

	/**
	 * 根据终端号码查询位置信息表中是否存在记录
	 * @param terminalnum
	 * @return
	 */
	public Integer getPositionCount(String terminal);
	
	/**
	 * 添加终端设备位置信息
	 * @param terminalPositionInfo
	 * @return
	 */
	public Integer addTerminalPositionInfo(TerminalPositionInfo terminalPositionInfo);
	
	/**
	 * 更新终端位置信息
	 * @param terminalPositionInfo
	 * @return
	 */
	public Integer updateTerminalPositionInfo(TerminalPositionInfo terminalPositionInfo);
	
	/**
	 * 插入位置信息至终端历史位置表中
	 * @param terminalHisPosition
	 * @return
	 */
	public Integer insertTerminalHisPosition(TerminalHisPosition terminalHisPosition);
	
	
	/**
	 * 终端位置信息列表
	 * @param 
	 * @return
	 */
	public List<TerminalPositionInfo> terminalPositionList(TerminalPositionInfo terminalPositionInfo);
	
	/**
	 * 车辆分布列表
	 * @param currentPage
	 * @param everyPage
	 * @param terminalPositionInfo
	 * @return
	 */
	public Map getCarLocationList(final int currentPage, final int everyPage,TerminalPositionInfo terminalPositionInfo);
	/**
	 * Excel车辆分布列表
	 * @param terminalPositionInfo
	 * @return
	 */
	public List<TerminalPositionInfo> getCarLocationList(TerminalPositionInfo terminalPositionInfo);
	
	
	
	
	
	/**查询组织机构下面车辆状态位置信息列表**/
	public List<PositionInfo> loadCarPosByDept(TreeDomain baseDomain);
	
	/**根据车牌号查询车辆状态位置信息**/
	public MapInfo queryCarDetail(String carnumber);
	public MapInfo queryCarDetail(Integer carid);
	
	/**根据车牌号查询车辆位置以及资料信息**/
	public PositionInfo queryCarTerminalDetail(String carnumber);
	
	/**根据车牌号查询车辆档案信息**/
	public CarInfo queryCarArchiveDetail(String carnumber);
	
	/**
	 * 批量查询车辆位置信息
	 * @param ids
	 * @return
	 */
	public List<PositionInfo> queryCarAllDetail(List<Integer> idlist);
	
	/**读取车辆参数信息**/
	public CarDetail readCarParameter(String carnumber);
	
	
	/**
	 * 根据设备号删除最后位置标中的数据
	 * @param terminal
	 * @return
	 */
	public Integer deleteTerminalPosition(String terminal);
	
	/**
	 * 根据车牌号删除最后位置标中的数据
	 * @param carnumber
	 * @return
	 */
	public Integer deleteTerminalPositionCarNumber(String carnumber);
	
	/**
	 * 根据车辆ID删除最后位置信息
	 * @param carid
	 * @return
	 */
	public Integer deleteTerminalPositionCarId(int carid);
	
	/**
	 * 根据车辆ID更新车辆最后位置信息
	 * @param carid
	 * @return
	 */
	public Integer updateTerminalPosition(CarInfo carInfo);
	
	/**
	 * 创建车辆位置信息表
	 * @param tableName
	 */
	public void createCarPosTable(Map<String,String> map);
	/**
	 * 车辆下拉列表
	 * @param 
	 * @return
	 */
	public List<CarInfo> selectCarList(CarInfo carInfo);

	/**
	 * 查询终端参数记录
	 * @param id
	 * @return
	 */
	public TerminalParameter getTerminalParam(Integer id);
	
	/**
	 * 车辆实时信息
	 */
	public Map getCarrealtimeList(final int currentPage, final int everyPage,Carrealtime carrealtime);
	
	
	/**
	 * Excel车辆实时信息
	 */
	public List<Carrealtime> exportCarrealtime(Carrealtime carrealtime);
	
	
	/**
	 * 营运时长分析
     * 图表数据获取
     */
    public List<OperaTimeAnalysis> queryOta(Map map);
	/**
	 * 导出 营运时长分析 数据以及图表
	 */
    
//    public Map getOtaList(final int currentPage, final int everyPage,OperaTimeAnalysis ota);
    public List<OperaTimeAnalysis> exportOta(OperaTimeAnalysis ota);
    
    /**
	 * 营运里程统计列表
	 */
	public Map getOperaMileStatiList(final int currentPage, final int everyPage,OperaTimeAnalysis ota);
	
	/**
	 * 营运时长统计列表
	 */
	public Map getOperaTimeStatiList(final int currentPage, final int everyPage,OperaTimeAnalysis ota);
	
	/**
	 * 营运里程分析
     * 图表数据获取
     */
    public List<OperaTimeAnalysis> queryOma(Map map);
	 
	
	/**
	 * 日均营运统计列表
	 */
	public Map getOperaDayStatiList(final int currentPage, final int everyPage,OperaTimeAnalysis ota);
	
	/**
	 * 车辆下拉分页列表
	 */
	public Map selectCarPageList(final int currentPage, final int everyPage,CarInfo carInfo);
	
}





