/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.dss.commtest;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import com.dss.commtest.mq.JmsSender;

/**    
 *     
 * 项目名称：commtest    
 * 类名称：test    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2015-10-19 下午01:45:50    
 * 修改人：Administrator    
 * 修改时间：2015-10-19 下午01:45:50    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class MqTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//位置跟踪控制
		//String jsonMsg = "{\"msgid\":0x8202,\"subpacket\":0,\"encryption\":0,\"bodysize\":34,\"terminal\":\"15666666666\",\"seq\":28,\"inv\":5,\"expire\":120,\"distance\":500,\"effdistance\":1000,\"way\":0}";

		//文本信息下发
		//String jsonMsg = "{\"msgid\":0x8300,\"subpacket\":0,\"encryption\":0,\"bodysize\":34,\"terminal\":\"18668171282\",\"seq\":28,\"emergency\":1,\"lcd\":1,\"tts\":1,\"adv\":1,\"action\":1,\"content\":\"文本信息下发\",\"textseq\":1,\"time\":1}";

		//1.35	多媒体数据上传应答
		//String jsonMsg = "{\"msgid\":0x8800,\"subpacket\":0,\"encryption\":0,\"bodysize\":34,\"terminal\":\"15666666666\",\"seq\":28,\"dataId\":44,\"packetSum\":5,\"ids\":\"1,2,3,4,5\"}";

		//摄像头立即拍摄命令
		//String jsonMsg = "{\"msgid\":0x8801,\"subpacket\":0,\"encryption\":0,\"bodysize\":34,\"terminal\":\"15666666666\",\"seq\":28,\"channel\":1,\"cmd\":1,\"inv\":1,\"save\":0,\"screen\":1,\"quality\":1,\"bright\":127,\"contrast\":127,\"saturation\":127,\"chroma\":127}";

		//3.2.39多媒体检索
		//String jsonMsg = "{\"msgid\":0x8802,\"subpacket\":0,\"encryption\":0,\"bodysize\":34,\"terminal\":\"15666666666\",\"seq\":28,\"type\":0,\"id\":1,\"fmt\":1,\"starttime\":\"151019122800\",\"endtime\":\"151019142800\"}";

		//订单任务下发
		//String jsonMsg = "{\"msgid\":0x8B00,\"subpacket\":0,\"encryption\":0,\"bodysize\":34,\"terminal\":\"100200290050\",\"seq\":28,\"orderid\":\"201510200947\",\"ordertype\":0,\"ordertime\":\"2015-10-20 09:47:22\",\"startaddr\":\"深圳宝安华美居\",\"endaddr\":\"深圳高铁站\",\"remark\":\"要求真多\",\"startlat\":22566428,\"startlng\":113874468,\"endlat\":22566428,\"endlng\":113874468}";

		//订单取消
		//String jsonMsg = "{\"msgid\":0x8B09,\"subpacket\":0,\"encryption\":0,\"bodysize\":34,\"terminal\":\"18668171282\",\"seq\":28,\"orderid\":\"201510200947\"}";

		//订单确认
		//String jsonMsg = "{\"msgid\":0x8B01,\"subpacket\":0,\"encryption\":0,\"bodysize\":34,\"terminal\":\"100200290050\",\"seq\":28,\"orderid\":\"201510200947\",\"ordertype\":0,\"ordertime\":\"2015-10-20 09:47:22\",\"startaddr\":\"深圳宝安华美居\",\"endaddr\":\"深圳高铁站\",\"passengerphone\":\"18668171282\",\"remark\":\"要求真多\"}";


		//String jsonMsg = "{\"msgid\":53507,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"18221641095\",\"seq\":10,\"systemnumber\":0}";
		//String jsonMsg = "{\"msgid\":53508,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"13795401586\",\"seq\":15,\"systemnumber\":0,\"count\":1,\"items\":[{\"id\":16705794}]}";
		//String jsonMsg = "{\"msgid\":0x8300,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"18668171282\",\"seq\":10,\"msgtype\":1,\"msgcontent\":\"报警消息\"}";

		//String jsonMsg = "{\"msgid\":0x5111,\"subpacket\":0,\"encryption\":0,\"bodysize\":34,\"terminal\":\"18668171282\",\"seq\":28,\"terid\":1,\"userid\":1,\"phone\":\"15073460890\",\"ternumber\":\"18665847458\",\"serialnumber \":\"1125645678584\"}";
		//String jsonMsg = "{\"msgid\":20753,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"0\",\"seq\":0,\"orderid\":\"514488543943\",\"ters\":\"100200290050\"}";
		//String jsonMsg = "{\"msgid\":20753,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"15814403512\",\"seq\":1,\"terid\":6,\"userid\":6,\"phone\":\"15814403512\",\"ternumber\":\"18888888888\",\"terphone\":\"18888888888\",\"serialnumber\":\"61447831640817\"}";
		//String jsonMsg = "{\"msgid\":0x5112,\"subpacket\":0,\"encryption\":0,\"bodysize\":34,\"terminal\":\"18668171282\",\"seq\":28,\"serialnumber \":\"1125645678584\",\"result\":1,\"reason\":1}";

		//17.5	比亚迪充电结束通知
		//String jsonMsg = "{\"msgid\":0x5114,\"subpacket\":0,\"encryption\":0,\"bodysize\":34,\"terminal\":\"18668171282\",\"seq\":28,\"serialnumber \":\"1125645678584\",\"chargetime \":\"2.5\",\"money \":\"20.8\",\"starttime \":\"151111114730\",\"endtime \":\"151111115030\"}";

		//String jsonMsg = "{\"msgid\":12,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":0,\"type\":1,\"time\":\"151210002020\"}";

		//String jsonMsg = "{\"msgid\":2,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":0,\"type\":0}";

		//String jsonMsg = "{\"msgid\":5,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":0,\"type\":1}";

		//String jsonMsg = "{\"msgid\":13,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":36, \"type\":0, \"mileage\":0}";

		//String jsonMsg = "{\"msgid\":4097,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":36, \"type\":1, \"ip\":\"202.104.149.86\", \"port\":9999, \"apn\":\"CMNET\"}";

		//String jsonMsg = "{\"msgid\":4097,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":36, \"type\":1, \"ip\":\"202.104.150.177\", \"port\":9999, \"apn\":\"CMNET\"}";

		//String jsonMsg = "{\"msgid\":4100,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":36, \"type\":1, \"phone\":\"08618668171282\"}";

		//String jsonMsg = "{\"msgid\":4103,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":36, \"type\":1, \"interval\":30}";
		//String jsonMsg = "{\"msgid\":8193,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":36, \"type\":1, \"channel\":0, \"iinterval\":15, \"rinterval\":65}";

		//String jsonMsg = "{\"msgid\":8194,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":36, \"type\":0}";

		//String jsonMsg = "{\"msgid\":8195,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":36, \"type\":1, \"interval\":12}";

		//String jsonMsg = "{\"msgid\":12289,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":36, \"type\":1, \"interval\":13}";

		//String jsonMsg = "{\"msgid\":12292,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":36, \"type\":0}";

		//String jsonMsg = "{\"msgid\":12293,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":36, \"type\":0}";

		//String jsonMsg = "{\"msgid\":12294,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"601508310104\",\"seq\":36, \"type\":1}";

		//String jsonMsg = "{\"msgid\":21001,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"\",\"seq\":84,\"orderid\":314498924392,\"answermode\":1,\"answerphone\":\"13424180647\",\"startlat\":22569751,\"startlng\":113885859,\"startaddr\":\"http://taxiapi.21letu.com:80/upload/voice/201512121153594912.mp3\",\"Url\":\"广东省深圳市宝安区新湖路\",\"contents\":\"\"}";

		//String jsonMsg = "{\"msgid\":3,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"866762028545844\",\"seq\":0,\"cmd\":0}";
		//String jsonMsg = "{\"msgid\":16386,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"18668171282\",\"seq\":3,\"time\":\"151218100219\",\"result\":0,\"s10\":0,\"s11\":1,\"s12\":0,\"s13\":0,\"s14\":0,\"s15\":0,\"s16\":1,\"s17\":0}";
		//String jsonMsg = "{\"msgid\":16386,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"18668171282\",\"seq\":12,\"time\":\"151218115931\",\"result\":3,\"s10\":0,\"s11\":0,\"s12\":0,\"s13\":0,\"s14\":0,\"s15\":0,\"s16\":0,\"s17\":0}";

		//String jsonMsg = "{\"msgid\":20768,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":13424180647,\"seq\":11,\"orderid\":\"314505151405\",\"orderstatus\":2,\"carnumber\":\"粤B09544\",\"phone\":\"100200290050\",\"drivername\":\"小温\",\"driverphone\":\"\",\"time\":\"151219165228\",\"lng\":113885295,\"lat\":22569247}";

		//String jsonMsg = "{\"msgid\":141,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"627151221001\",\"seq\":0}";

		//String jsonMsg = "{\"msgid\":83,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"627151221001\",\"seq\":0,\"time\":\"2015-12-23 11:21:28\"}";

		//String jsonMsg = "{\"msgid\":84,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"627151221001\",\"seq\":0,\"interval\":15}";

		//String jsonMsg = "{\"msgid\":91,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"627151221001\",\"seq\":0,\"mileage\":200}";

		//String jsonMsg = "{\"msgid\":98,\"subpacket\":0,\"encryption\":0,\"bodysize\":26,\"terminal\":\"627151221001\",\"seq\":0,\"type\":1,\"ip\":\"139.196.107.48\",\"domain\":\"NULL\",\"port\":\"6789\"}";

		//String jsonMsg = "{\"msgid\":4098,\"subpacket\":0,\"encryption\":0,\"bodysize\":26,\"terminal\":\"865558020249935\",\"seq\":0,\"type\":1,\"ip\":\"202.104.149.86\",\"port\":9999,\"apn\":\"CMNET\"}";
		//String jsonMsg = "{\"msgid\":0x1004,\"subpacket\":0,\"encryption\":0,\"bodysize\":26,\"terminal\":\"865558020249935\",\"seq\":0,\"type\":1}";
		//String jsonMsg = "{\"msgid\":0x1005,\"subpacket\":0,\"encryption\":0,\"bodysize\":26,\"terminal\":\"865558020249935\",\"seq\":0,\"type\":1,\"name\":\"LOVE_CAR_3\",\"password\":\"66666666\"}";
		//String jsonMsg = "{\"msgid\":0x1006,\"subpacket\":0,\"encryption\":0,\"bodysize\":26,\"terminal\":\"865558020249935\",\"seq\":0,\"type\":1,\"miles\":\"560\"}";

		//String jsonMsg = "{\"msgid\":0x1007,\"subpacket\":0,\"encryption\":0,\"bodysize\":26,\"terminal\":\"865558020249935\",\"seq\":0,\"type\":1}";
		//String jsonMsg = "{\"msgid\":0x1008,\"subpacket\":0,\"encryption\":0,\"bodysize\":26,\"terminal\":\"865558020249935\",\"seq\":0,\"type\":1,\"datatype\":0,\"interval\":15}";
		//String jsonMsg = "{\"msgid\":4105,\"subpacket\":0,\"encryption\":0,\"bodysize\":26,\"terminal\":\"865558020249935\",\"seq\":0,\"type\":0}";
		//急加速
		/*String jsonMsg = "{\"msgid\":0x2001,\"subpacket\":0,\"encryption\":0,\"bodysize\":26,\"terminal\":\"213GL2015016712\",\"seq\":101,\"count\":1,\"items\":[{\"tag\":0x1004,\"length\":3,\"value\":2}]}";
		runtest(jsonMsg);
		//急减速
		String jsonMsg1 = "{\"msgid\":0x2001,\"subpacket\":0,\"encryption\":0,\"bodysize\":26,\"terminal\":\"213GL2015016712\",\"seq\":101,\"count\":1,\"items\":[{\"tag\":0x1005,\"length\":3,\"value\":3}]}";
		runtest(jsonMsg1);
		//急变道
		String jsonMsg2 = "{\"msgid\":0x2001,\"subpacket\":0,\"encryption\":0,\"bodysize\":26,\"terminal\":\"213GL2015016712\",\"seq\":101,\"count\":1,\"items\":[{\"tag\":0x100B,\"length\":3,\"value\":2}]}";
		runtest(jsonMsg2);
		//急转弯
		String jsonMsg3 = "{\"msgid\":0x2001,\"subpacket\":0,\"encryption\":0,\"bodysize\":26,\"terminal\":\"213GL2015016712\",\"seq\":101,\"count\":1,\"items\":[{\"tag\":0x100C,\"length\":3,\"value\":3}]}";
		runtest(jsonMsg3);
		//水温报警
		String jsonMsg4 = "{\"msgid\":0x2001,\"subpacket\":0,\"encryption\":0,\"bodysize\":26,\"terminal\":\"213GL2015016712\",\"seq\":101,\"count\":1,\"items\":[{\"tag\":0x1003,\"length\":3,\"value\":108}]}";
		runtest(jsonMsg4);*/
		
		//String jsonMsg = "{\"msgid\":0x80,\"subpacket\":0,\"encryption\":0,\"bodysize\":26,\"terminal\":\"864291100198092\",\"seq\":0,\"mark\":11111,\"content\":\"DWXX#\",\"language\":1}";
		//String jsonMsg = "{\"msgid\":8194,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"RCOBD2016000291\",\"seq\":1,\"count\":3,\"items\":[{\"tag\":4097},{\"tag\":4098},{\"tag\":10241}]}";
		
		//String jsonMsg = "{\"msgid\":0x1010,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"tertype\":1,\"terminal\":\"867602029290579\",\"seq\":30,\"lat\":22566420,\"lng\":113874551,\"poiname\":\"华美居\"}";
		
		//String jsonMsg = "{\"msgid\":0x1011,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"tertype\":1,\"terminal\":\"867602029290579\",\"seq\":30,\"type\":1,\"time\":160627151212,\"content\":\"您的火花塞运行已到5000公里请定期保养\"}";
		
		//String jsonMsg = "{\"msgid\":4115,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"15814403500\",\"seq\":2,\"type\":2,\"adid\":21,\"starttime\":\"0110\",\"endtime\":\"0230\",\"position\":6,\"expiredate\":\"\",\"content\":\"\",\"url\":\"http://ad.51tsp.com//upload/advert/201607181625194000.mp4\"}";
		//String jsonMsg = "{\"msgid\":0x1013,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"tertype\":1,\"terminal\":\"867602029290579\",\"seq\":30,\"type\":1,\"adid\":100,\"starttime\":1100,\"endtime\":1200,\"position\":10,\"expiredate\":\"160628\",\"content\":\"多森软件提醒您\",\"url\":\"http://www.duosensoft.com/\"}";
		
		//String jsonMsg = "{\"msgid\":0x1012,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"tertype\":1,\"terminal\":\"867602029290579\",\"seq\":30,\"type\":1,\"starttime\":16062715,\"endtime\":16062718,\"title\":\"多森软件K歌活动\",\"name\":\"深圳市多森软件车友会\",\"content\":\"西藏10天自驾游\"}";
		
		//String jsonMsg = "{\"msgid\":0x1003,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"tertype\":1,\"terminal\":\"867602029290579\",\"seq\":30,\"type\":1,\"ip\":\"192.168.1.234\",\"port\":9999,\"apn\":\"3gnet\"}";
		
		//String jsonMsg = "{\"msgid\":0x1014,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"tertype\":1,\"terminal\":\"867602029290579\",\"seq\":30,\"count\":3,\"sname\":\"多森4s店\",\"items\":[{\"id\":1,\"name\":\"用品一\"},{\"id\":2,\"name\":\"用品2\"},{\"id\":3,\"name\":\"用品3\"}]}";
		
		//String jsonMsg = "{\"msgid\":0x1015,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"tertype\":1,\"terminal\":\"867602029290579\",\"seq\":30,\"id\":1,\"name\":\"多森车友会\",\"sname\":\"多森4s店\"}";
		String jsonMsg = "{\"msgid\":4117,\"subpacket\":0,\"encryption\":0,\"bodysize\":0,\"terminal\":\"860646022700263\",\"seq\":17,\"id\":141,\"name\":\"茂名老乡\",\"sname\":\"一甲丙益4S店\"}";
		
		runtest(jsonMsg);

	}

	public static void runtest(String jsonMsg){

		MapMessage message = null;
		try {
			message = JmsSender.getInstance().getSession().createMapMessage();
			message.setString("jsonMsg", jsonMsg);
			message.setString("terminal", "0");
			message.setInt("devicetype", 38);
		} catch (JMSException e) {
			e.printStackTrace();
		}

		JmsSender.getInstance().sendMapMessage(message);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
