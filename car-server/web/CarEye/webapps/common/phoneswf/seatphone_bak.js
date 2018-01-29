var Sp;    //SoftPhone.ocx控件
var Icc;   //IccStationOcx.ocx控件
var Acc;   //AccStationOcx.ocx控件

var SpStatus = false;    //SoftPhone初始化状态 true 成功 ，false 失败
var IccStatus = false;   //IccStationOcx初始化状态 true 成功 ，false 失败
var AccStatus = false;   //AccStationOcx初始化状态 true 成功 ，false 失败

var InfoSp; //状态信息
var AgentId = "101"; //工号
var ICOType = "1234567"; //ICCOCX控件能支持的ICO类型：1-文本；2-音频；3-视频；4-视频播放；5-文件传输；6-共享；7-白板
var DeviceID = "60000002"; //分机号码


/**
 * 加载软电话
 */
function initphone(){
	
	InfoSp = Ext.getCmp('message');
	
	Sp = document.getElementById("objSp");
    Icc = document.getElementById("objIcc");
    Acc = document.getElementById("objAcc");
    
    Sp.attachEvent('OnError',Error); //监听报错
    Sp.attachEvent('OnInService',InService); //初始化成功后回调
    Sp.attachEvent('OnInBoundCall',inBoundCall);  //来电事件
    
    startService(); //初始化服务
    if(SpStatus){
    	InitIcc(); //初始化Icc
    }
    if(IccStatus){
   	    InitACC(); //初始化ACC
    }
    
}

//初始化服务
function startService() {
	InfoSp.setText("启动软电话服务中...");
	try{
		Sp.StartService();
	}catch(e){
		alert("软电话控件不存在，请先注册相应控件！") 
		return;
	}
	
}

//初始化服务成功回调函数
function InService() {
	InfoSp.setText("软电话服务启动成功");
	InitSP(); //初始化Sp
}

//初始化SP
function InitSP() {
	var retCode = Sp.SPInit(AgentId, 1);
	if (retCode == 1) {
		InfoSp.setText("软电话启动成功");
		SpStatus = true;
	} else {
		InfoSp.setText("软电话启动失败");
	}
}

// 初始化Icc
function InitIcc() {
	var retIcc = Icc.IccInit(ICOType);
	if (retIcc == 1) {
		IccStatus = true;
		InfoSp.setText("Icc启动成功");
	} else {
		InfoSp.setText("Icc启动失败");
	}
}

// 初始化ACC    
function InitACC() {
	var retAcc = Acc.Register(DeviceID);
	if (retAcc == 1) {
		AccStatus = true;
		InfoSp.setText("ACC启动成功");
	} else {
		InfoSp.setText("ACC启动失败");
	}
}

//InBoundCallEvent来电弹屏事件
function inBoundCall(customercallid, callid, MediaType, customercallno,serviceno, currqueue, trunkno) {
	alert(MediaType + '|==' + customercallid + '|==' + customercallno + '|=='
			+ serviceno + '|==' + currqueue + '|==' + callid);

	if (MediaType == '0') { //MediaType 0-电话
		InfoSp.setText("电话");
		
	}else if (MediaType == '1') { //MediaType=1，Icc处理
		InfoSp.setText("客户来电ICC");
//		Icc.IccAddCallCtrl(callid);
//		Icc.IccAnswer(callid);
		var control = Ext.create('FMS.controller.Controllers');
		control.phonecall(customercallid, callid, MediaType, customercallno,serviceno, currqueue, trunkno);
		
	}else if (MediaType ==2){ //MediaType=2,短信处理
		InfoSp.setText("客户来电短信");
		
	}else if (MediaType == 3){ //MediaType=3,邮件处理
		InfoSp.setText("客户来电邮件呼叫来电");
		
	}else if (MediaType == 4){ //4-传真
		InfoSp.setText("客户来电传真");
		
	}else if (MediaType == 5){ //5-VoiceMail
		InfoSp.setText("客户来电VoiceMail");
	}

}

//销毁软电话
function destory() {
	if(SpStatus){
		Sp.SPUnInit();
		InfoSp.setText("软电话成功销毁");
		SpStatus = false;
	}else{
		InfoSp.setText("软电话初始化失败，无法销毁");
		return;
	}
	if(IccStatus){
		Icc.IccUnInit();
		InfoSp.setText("Icc成功销毁");
		IccStatus = false;
	}else{
		InfoSp.setText("Icc初始化失败，无法销毁");
		return;
	}
	if(AccStatus){
		Acc.UnRegister();
		InfoSp.setText("ACC成功销毁");
		AccStatus = false;
	}else{
		InfoSp.setText("ACC初始化失败，无法销毁");
		return;
	}

}

//客户来电
function phonecall(){
	if(SpStatus){
		InfoSp.setText("软电话初始化失败，无法弹屏");
	}else{
		Sp.fireEvent("OnInBoundCall",1,1,1,1,1,1,1);
	}
}


function Error(ErrorCode, ErrorStr) {
	InfoSp.setText("启动软电话失败," + ErrorCode);
}

/**
 * 坐席登陆
 */
function seatlogin(){
	if(SpStatus){
		Sp.AgentLogin();
		Ext.Msg.alert("提示","登陆成功");
	}else{
		InfoSp.setText("Sp初始化失败，无法登录");
	}
}

/**
 * 坐席签退
 */
function seatlogout(){
	if(SpStatus){
		Sp.AgentLogout();
		Ext.Msg.alert("提示","签退成功");
	}else{
		InfoSp.setText("Sp初始化失败，无法签退");
	}
}

/**
 * 坐席就绪
 */
function seatready(){
	if(SpStatus){
		Sp.AgentReady();
		Ext.Msg.alert("提示","就绪成功");
	}else{
		InfoSp.setText("Sp初始化失败，无法就绪");
	}
}

/**
 * 电话呼出
 */
function callphone(){
	if(SpStatus){
		Ext.create('Ext.window.Window', {
			    title: '电话呼出',
			    width: 250,
			    animCollapse:false,
				constrain : true,
				constrainHeader : true,
				maximizable : false,
				minimizable : false,
				frame : true,
			    modal : true,
			    closable : false,
			    layout: 'fit',
			    items: {  
			        xtype: 'form',
			        id:'open_lock_form',
			        border:false,
			        frame : false,
			        items : [ {
					xtype : 'form',
					frame : false,
					border:false,
					anchor : '100%',
					collapsible : false,
					buttonAlign : 'right',
					fieldDefaults: {
				        labelAlign: 'right',
				        labelWidth: 80
				    },
					items : [ {
						xtype : 'textfield',
						fieldLabel : '电话号码',
						margin:'10 5 0 0',
						id :'CalledNumber',
						allowBlank : false,
						blankText : '原密码不能为空',
						regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
						regexText : '电话号码格式不正确!'
					},{
						xtype : 'textfield',
						fieldLabel : 'UUI数据',
						margin:'10 5 0 0',
						id :'UUI'
					}]
				} ]
				    },
				    buttons: [{
						text: '呼出',
						handler: function(button){
							
							var win = button.up('window');
							var form = win.down('form');
							if (!form.getForm().isValid()) {
								return;
							}
							var CalledNumber = Ext.getCmp('CalledNumber').getValue();
							var UUI = Ext.getCmp('UUI').getValue();
							
							Sp.MakeCall("T"+CalledNumber,UUI);
							Ext.Msg.alert("提示","电话呼出成功");
							win.close();
						
						}
					},{
						text: '取消',
						handler: function(btn){
							btn.up('window').close();
							}
						}]
		}).show();
	}else{
		InfoSp.setText("Sp初始化失败，无法电话呼出");
	}
}

/**
 * 电话转接
 */
function phoneswitch(){
	if(SpStatus){
		Sp.TransferCall();
		Ext.Msg.alert("提示","电话转接成功");
	}else{
		InfoSp.setText("Sp初始化失败，无法电话转接");
	}
}

/**
 * 小休
 */
function shortrest(){
	if(SpStatus){
		Sp.ConferenceCall();
		Ext.Msg.alert("提示","小休设置成功");
	}else{
		InfoSp.setText("Sp初始化失败，无法设置小休");
	}
}

/**
 * 置闲
 */
function free(){
	alert("置闲");
}

/**
 * 置忙
 */
function busy(){
	alert("置忙");
}

/**
 * 挂机
 */
function onhook(){
	if(SpStatus){
		Sp.HangupCall();
	}else{
		InfoSp.setText("Sp初始化失败，无法挂机");
	}
}