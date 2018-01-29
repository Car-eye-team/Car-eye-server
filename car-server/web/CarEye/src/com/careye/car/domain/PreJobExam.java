package com.careye.car.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-10-30 上午10:00:36
 * @修改人：ll
 * @修改时间：2015-10-30 上午10:00:36
 * @修改备注：
 * @version 1.0
 */
public class PreJobExam {
	
	private Integer id;
	
	/**驾驶员id**/
	private Integer driverid;
	
	/**合格证号**/
	private String qualifiednumber;
	
	/**合格证有效截止日期**/
	private String closetime;
	
	/**培训期数**/
	private Integer trainexpect;
	
	/**培训编号**/
	private String trainnumber;
	
	/**考试证编号**/
	private String examnumber;
	
	/**考试证获取日期**/
	private String gaintime;
	
	/**结业状态(1已结业2未结业3未参与)**/
	private Integer completestatus;
	
	/**考试状态(1通过2不通过3未考试)**/
	private Integer examstatus;
	
	/**创建时间**/
	private String createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDriverid() {
		return driverid;
	}

	public void setDriverid(Integer driverid) {
		this.driverid = driverid;
	}

	public String getQualifiednumber() {
		return qualifiednumber;
	}

	public void setQualifiednumber(String qualifiednumber) {
		this.qualifiednumber = qualifiednumber;
	}

	public String getClosetime() {
		return closetime;
	}

	public void setClosetime(String closetime) {
		this.closetime = closetime;
	}

	public Integer getTrainexpect() {
		return trainexpect;
	}

	public void setTrainexpect(Integer trainexpect) {
		this.trainexpect = trainexpect;
	}

	public String getTrainnumber() {
		return trainnumber;
	}

	public void setTrainnumber(String trainnumber) {
		this.trainnumber = trainnumber;
	}

	public String getExamnumber() {
		return examnumber;
	}

	public void setExamnumber(String examnumber) {
		this.examnumber = examnumber;
	}

	public String getGaintime() {
		return gaintime;
	}

	public void setGaintime(String gaintime) {
		this.gaintime = gaintime;
	}

	public Integer getCompletestatus() {
		return completestatus;
	}

	public void setCompletestatus(Integer completestatus) {
		this.completestatus = completestatus;
	}

	public Integer getExamstatus() {
		return examstatus;
	}

	public void setExamstatus(Integer examstatus) {
		this.examstatus = examstatus;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
