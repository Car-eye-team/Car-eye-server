package com.careye.threadpool;

import java.io.Serializable;

import javax.jms.MapMessage;

import com.careye.mq.MqClientUtil;

/**
* @项目名称：TAXISERVER
* @类名称：ThreadPoolTask
* @类描述：线程任务处理
* @创建人：zr
* @创建时间：2015-3-11 下午07:07:24
* @修改人：zr
* @修改时间：2015-3-11 下午07:07:24
* @修改备注：
* @version 1.0
 */
public class ThreadPoolTask implements Runnable, Serializable{  
	private static final long serialVersionUID = 0;  

	private Object msgData;
	
	//1 老MQ 2 新MQ
	private int type = 1;

	public ThreadPoolTask(Object msgData){  
		this.msgData = msgData;
		this.type = 1;
	}
	
	public ThreadPoolTask(MapMessage msg,int type){
		this.type = type;
		this.msgData = msg;
	}

	@Override
	public void run() {
		try {
			MqClientUtil.getInstance().messageMqReceived(null,msgData,type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  

}  
