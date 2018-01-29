package com.careye.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.careye.mq.MqClientUtil;
import com.careye.mq.domain.Protocol;
import com.careye.job.CarOffLineJob;
import com.careye.job.CarOnlineStatusJob;

public class test extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public test() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Protocol protocol = new Protocol();
		int param[] = {0};

		/*		CarOffLineJob carOffLineJob = new CarOffLineJob();
		carOffLineJob.startJob();*/

		int type = 24; 
		/*try {
			type = Integer.parseInt(request.getParameter("type"));
		} catch (Exception e) {
			e.printStackTrace();
		}*/	
		String aa = "";
		switch (type) {
		//注册
		case 1:
			aa = "{\"msgid\":256,\"subpacket\":0,\"encryption\":0,\"bodysize\":34,\"terminal\":\"123456789\",\"seq\":28,\"province\":44,\"city\":300,\"oem\":\"12345\",\"type\":\"12345678\",\"mid\":\"8171282\",\"plateColor\":1,\"plateCode\":\"粤B88888\"}";
			break;
			//心跳
		case 2:
			//aa = "{\"msgid\":512,\"subpacket\":0,\"encryption\":0,\"bodysize\":28,\"terminal\":\"18618485348\",\"seq\":19,\"alarm\":0,\"state\":262915,\"s0\":1,\"s1\":1,\"s9\":3,\"lat\":39886958,\"lng\":116664678,\"altitude\":28,\"speed\":\"0.0\",\"direction\":326,\"time\":\"150328232213\"}";
			//aa = "{\"msgid\":512,\"subpacket\":0,\"encryption\":0,\"bodysize\":28,\"terminal\":\"15073460890\",\"seq\":6,\"alarm\":0,\"state\":262147,\"s0\":1,\"s1\":1,\"lat\":22566505,\"lng\":113873976,\"altitude\":109,\"speed\":\"0.0\",\"direction\":73,\"time\":\"150325100426\"}";
			aa = "{\"msgid\":512,\"subpacket\":0,\"encryption\":0,\"bodysize\":34,\"terminal\":\"18668171282\",\"seq\":390,\"s18\":1,\"s9\":0,\"s4\":0,\"s1\":1,\"s0\":1,\"lat\":22566320,\"lng\":113874338,\"altitude\":68,\"speed\":0,\"direction\":308,\"mileage\":406,\"time\":\"150526121649\"}";
			break;
			//查询当前位置信息
		case 3:
			aa = "{\"msgid\":513, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"123456789\", \"seq\":5212, \"s0\":1, \"a1\":1, \"lat\":32145095, \"lng\":118647103, \"altitude\":37, \"speed\":3, \"direction\":0, \"time\":\"140623063407\", \"id\":1, \"len\":4, \"mileage\":603599, \"id\":6, \"len\":12, \"oilSum\":946.212280, \"oilAvg\":11.309633, \"oilAt\":1.173322, \"id\":8, \"len\":24, \"d8\":\"1:460,2:00,3:500C,4:8B3A\", \"id\":13, \"seqR\":4,\"len\":4, \"vat\":13.516224}";
			break;
			//抢答
		case 4:
			aa = "{\"msgid\":20740, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"15073460890\", \"seq\":5212,\"orderid\":\"142709641568712312\"}";
			break;

			//中标
		case 5:
			aa = "{\"msgid\":111111, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"123456789\", \"seq\":5212,\"orderid\":\"142709641568712345\"}";
			break;
			//执行电召
		case 6:
			aa = "{\"msgid\":20743, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"123456789\", \"seq\":5212,\"orderid\":\"142709641568712312\",\"callfee\":\"5\",\"carnum\":\"粤B88888\",\"result\":4}";
			break;
			
			//计价器开钥匙门信息
		case 7:
			aa = "{\"msgid\":20744, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"15073460890\", \"seq\":5212,\"orderid\":\"142709641568712312\",\"callfee\":\"5\",\"carnum\":\"粤B88888\",\"driverno\":71282}";
			break;
			
			//车辆服务评价
		case 8:
			aa = "{\"msgid\":20745, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"15073460890\", \"seq\":5212,\"result\":4,\"reason\":3}";
			break;
			
		case 9:
			aa = "{\"msgid\":20746, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"15073460890\", \"seq\":5212,\"number\":\"1,2,3,4,5,6,7,8,9,10,11,12\"}";
			break;
			
		case 10:
			aa = "{\"msgid\":20748, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"15073460890\", \"seq\":5212,\"time\":\"15-05-05-12-13-14\",\"type\":1,\"number\":20,\"usinglen\":10,\"flow\":100.5}";
			break;
			//召标发送车辆
		case 11:
			aa = "{\"msgid\":20753, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"15073460890\", \"seq\":5212,\"orderid\":\"142709641568712345\",\"ters\":\"135555555555555,11111111,333333\"}";
			break;
			//抢答
		case 12:
			aa = "{\"msgid\":20740, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"135555555555555\", \"seq\":5212,\"orderid\":\"142709641568712345\"}";
			break;
			
			
			//雅讯移动台主动上报数据
		case 13:
			aa = "{\"msgid\":340, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"123456789\", \"seq\":1," +
					"\"connum\":2,\"gpstype\":0,\"gpstime\":\"150325100426\",\"a0\":1,\"a1\":1,\"a2\":0,\"a3\":1,\"a4\":0,\"a5\":1," +
					"\"a6\":1,\"s0\":1,\"s1\":0,\"s2\":1,\"s3\":1,\"lat\":22566505,\"lng\":113873976,\"taxseq\":109,\"vector\":\"0.0\",\"direction\":73,}";
			break;
			
			//移动台登录请求
		case 14:
			aa = "{\"msgid\":1, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"123456789\", \"seq\":1,\"phone\":\"15253456789\"}";
			break;
			
			//设置报警手机号应答
		case 15:
			aa = "{\"msgid\":4162, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"123456789\", \"seq\":1,\"result\":1}";
			break;
			
			//监控数据
		case 16:
			aa = "{\"msgid\":325, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"123456789\", \"seq\":1," +
			"\"connum\":2,\"gpstype\":0,\"gpstime\":\"150325100426\",\"a0\":1,\"a1\":1,\"a2\":0,\"a3\":1,\"a4\":0,\"a5\":1," +
			"\"a6\":1,\"s0\":1,\"s1\":0,\"s2\":1,\"s3\":1,\"lat\":22566505,\"lng\":113873976,\"taxseq\":109,\"vector\":\"0.0\",\"direction\":73,}";
			break;
			
			//请求上传空重车信息
		case 17:
			aa = "{\"msgid\":24897, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"123456789\", \"seq\":1," +
			"\"gpstype\":0,\"gpstime\":\"150325100426\",\"s0\":1,\"s1\":0,\"s2\":1,\"s3\":1,\"lat\":22566505,\"lng\":113873976," +
			"\"taxseq\":109,\"vector\":\"0.0\",\"direction\":73,\"kzstate\":2}";
			break;
			
			//请求开始上传计价器数据
		case 18:
			aa = "{\"msgid\":24899, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"123456789\", \"seq\":1," +
			"\"framenum\":1,"+
			"\"slat\":22587878,"+
			"\"slng\":113454545,"+
			"\"elat\":23568787,"+
			"\"elng\":112457878,"+
			"\"taxseq\":1,"+
			"\"driverid\":\"1\","+
			"\"day\":1,"+
			"\"tradingdate\":\"20150615\","+
			"\"stime\":\"083010\","+
			"\"etime\":\"083415\","+
			"\"psamcount\":1,"+
			"\"psamno\":\"1\","+
			"\"mileage\":\"1\","+
			"\"waitingtime\":\"0830\","+
			"\"airmileage\":\"1\","+
			"\"tradetype\":1,"+
			"\"amountsreceivable\":\"1\","+
			"\"realamount\":\"1\","+
			"\"cardbalance\":\"1\","+
			"\"wlcardtype\":1,"+
			"\"cardtype\":1,"+
			"\"usrtradecount\":1,"+
			"\"tertradecount\":1,"+
			"\"psamcardnum\":\"1\","+
			"\"tradeauthcode\":1,"+
			"\"opernum\":1,"+
			"\"callcharge\":\"1\","+
			"\"fuelsurcharge\":\"1\","+
			"\"otherexpenses\":\"1\"}";
			break;
			
			//请求开始上传计价器数据
		case 19:
			aa = "{\"msgid\":24900, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"123456789\", \"seq\":1," +
			"\"slat\":22587878,"+
			"\"slng\":113454545,"+
			"\"elat\":23568787,"+
			"\"elng\":112457878,"+
			"\"taxseq\":1,"+
			"\"driverid\":\"1\","+
			"\"day\":1,"+
			"\"tradingdate\":\"20150615\","+
			"\"stime\":\"083010\","+
			"\"etime\":\"083415\","+
			"\"psamcount\":1,"+
			"\"psamno\":\"1\","+
			"\"mileage\":\"1\","+
			"\"waitingtime\":\"0830\","+
			"\"airmileage\":\"1\","+
			"\"tradetype\":1,"+
			"\"amountsreceivable\":\"1\","+
			"\"realamount\":\"1\","+
			"\"cardbalance\":\"1\","+
			"\"wlcardtype\":1,"+
			"\"cardtype\":1,"+
			"\"usrtradecount\":1,"+
			"\"tertradecount\":1,"+
			"\"psamcardnum\":\"1\","+
			"\"tradeauthcode\":1,"+
			"\"opernum\":1,"+
			"\"callcharge\":\"1\","+
			"\"fuelsurcharge\":\"1\","+
			"\"otherexpenses\":\"1\"}";
			break;
			
			//请求结束上传计价器数据
		case 20:
			aa = "{\"msgid\":24901, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"123456789\", \"seq\":1," +
			"\"framenum\":1,"+
			"\"slat\":22587878,"+
			"\"slng\":113454545,"+
			"\"elat\":23568787,"+
			"\"elng\":112457878,"+
			"\"taxseq\":1,"+
			"\"driverid\":\"1\","+
			"\"day\":1,"+
			"\"tradingdate\":\"20150615\","+
			"\"stime\":\"083010\","+
			"\"etime\":\"083415\","+
			"\"psamcount\":1,"+
			"\"psamno\":\"1\","+
			"\"mileage\":\"1\","+
			"\"waitingtime\":\"0830\","+
			"\"airmileage\":\"1\","+
			"\"tradetype\":1,"+
			"\"amountsreceivable\":\"1\","+
			"\"realamount\":\"1\","+
			"\"cardbalance\":\"1\","+
			"\"wlcardtype\":1,"+
			"\"cardtype\":1,"+
			"\"usrtradecount\":1,"+
			"\"tertradecount\":1,"+
			"\"psamcardnum\":\"1\","+
			"\"tradeauthcode\":1,"+
			"\"opernum\":1,"+
			"\"callcharge\":\"1\","+
			"\"fuelsurcharge\":\"1\","+
			"\"otherexpenses\":\"1\"}";
			break;
			
			//请求结束上传计价器数据
		case 21:
			aa = "{\"msgid\":24902, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"123456789\", \"seq\":1," +
			"\"slat\":22587878,"+
			"\"slng\":113454545,"+
			"\"elat\":23568787,"+
			"\"elng\":112457878,"+
			"\"taxseq\":1,"+
			"\"driverid\":\"1\","+
			"\"day\":1,"+
			"\"tradingdate\":\"20150615\","+
			"\"stime\":\"083010\","+
			"\"etime\":\"083415\","+
			"\"psamcount\":1,"+
			"\"psamno\":\"1\","+
			"\"mileage\":\"1\","+
			"\"waitingtime\":\"0830\","+
			"\"airmileage\":\"1\","+
			"\"tradetype\":1,"+
			"\"amountsreceivable\":\"1\","+
			"\"realamount\":\"1\","+
			"\"cardbalance\":\"1\","+
			"\"wlcardtype\":1,"+
			"\"cardtype\":1,"+
			"\"usrtradecount\":1,"+
			"\"tertradecount\":1,"+
			"\"psamcardnum\":\"1\","+
			"\"tradeauthcode\":1,"+
			"\"opernum\":1,"+
			"\"callcharge\":\"1\","+
			"\"fuelsurcharge\":\"1\","+
			"\"otherexpenses\":\"1\"}";
			break;
			
			//请求查询计价器数据应答
		case 22:
			aa = "{\"msgid\":24912, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"123456789\", \"seq\":1," +
			"\"stime\":\"083010\","+
			"\"etime\":\"083415\","+
			"\"psamcount\":1,"+
			"\"psamno\":\"1\","+
			"\"mileage\":\"1\","+
			"\"waitingtime\":\"0830\","+
			"\"airmileage\":\"1\","+
			"\"tradetype\":1,"+
			"\"amountsreceivable\":\"1\","+
			"\"realamount\":\"1\","+
			"\"cardbalance\":\"1\","+
			"\"wlcardtype\":1,"+
			"\"cardtype\":1,"+
			"\"usrtradecount\":1,"+
			"\"tertradecount\":1,"+
			"\"psamcardnum\":\"1\","+
			"\"tradeauthcode\":1,"+
			"\"opernum\":1,"+
			"\"callcharge\":\"1\","+
			"\"fuelsurcharge\":\"1\","+
			"\"otherexpenses\":\"1\"}";
			break;
			
			//请求计价器信息
		case 23:
			aa = "{\"msgid\":24931, \"subpacket\":0, \"encryption\":0, \"bodysize\":80, \"terminal\":\"123456789\", \"seq\":1," +
			"\"carnumber\":\"粤B06756\","+
			"\"psam\":\"1\","+
			"\"taximeterno\":123456789,"+
			"\"driverid\":1,"+
			"\"time\":\"20150525\","+
			"\"blacklistver\":1,"+
			"\"softever\":1}";
			break;
			//乘客人数统计
		case 24:
			aa = "{\"msgid\":20750,\"subpacket\":0,\"encryption\":0,\"bodysize\":34,\"terminal\":\"13264474880\",\"seq\":28,\"time\":\"150921100512\",\"number\":3,\"lat\":23568787,\"lng\":112457878,\"serialnumber\":\"123\"}";
			break;
			
		default:
			break;
		}


		MqClientUtil mqClientUtil = new MqClientUtil();
//		YxMqClientUtil mqClientUtil = new YxMqClientUtil();
		try {
			mqClientUtil.messageMqReceived(param, aa,1);
//			mqClientUtil.messageMqReceived(param, aa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*CarOnlineStatusJob carOnlineStatusJob = new CarOnlineStatusJob();
		carOnlineStatusJob.startJob();*/

		out.flush();
		out.close();
	}



	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
