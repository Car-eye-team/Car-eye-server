//默认将坐席置闲
var extension = admin.runNumber //要操作的分机号码。
var first = true;

/**
 * 来电
 * @param terminal 坐席号码
 * @param subid 消息ID
 * @param data Callerid： 主叫号码
			Calleeid： 被叫号码
			Method： 拨入还是拨出，Dialout 为拨出，Dialin 为拨入
			RecordFile：本次通话的录音文件名
			Uniqueid： 唯一标识，是一个流水号
			DateTime： 日期时间
 */
function belling(str){
//		var str = "Event=Popscreen&Poptime=Ring&Callerid=075529966501&CallerIDName=075529966501&Calleeid=6633&CID=075529966501&Uniqueid=1394164395.351&DateTime=2014-03-07 11:53:18&Server=localhost&RecordFile=  &Method=Dialin";
		var str = "Event=Popscreen&Poptime=Link&Callerid=15814403512&CallerIDName=<unknown>&CID=61865551&Calleeid=15602963704&CalleeIDName=<unknown>&Recordfile=20150722-095943-7210-10086-1437530383.9679.WAV&Uniqueid=1399426129.14295&DateTime=2014-05-07 09:28:58&Server=localhost&Method=Dialout";
	
	    //0:Event=Popscreen
		//1:Poptime=Ring
		//2:Callerid=075529966501
		//3:CallerIDName=075529966501
		//4:Calleeid=6111
		//5:CalleeIDName=
		//6:DID=075561861935
		//7:Recordfile=20160324-160820-6111-075529966501-1458806895.165.WAV
		//8:Uniqueid=1458806895.165
		//9:DateTime=2016-03-24 16:08:20
		//10:Server=localhost
		//11:Method=Dialin
	//解析来电
	var Callerid = "";   //主叫号码
	var Calleeid = "";   //被叫号码
	var Recordfile = ""; //本次通话的录音文件名
	var callbegintime = "";//日期时间
	var Uniqueid = "";   //流水号(唯一标示)
	
	Callerid = str.split("&")[2].replace("Callerid=","");
	
	if (Callerid.charAt(0) == '0') {
		Callerid = Callerid.substring(1,Callerid.length);
		
		//判断是否为手机号
		if(/^13\d{9}$/g.test(Callerid)||(/^15[8,9]\d{8}$/g.test(Callerid))){
		
		}else{
			Callerid = "0"+Callerid;
		}
		
	}
	
	Calleeid = str.split("&")[5].replace("Calleeid=","");
	Recordfile = str.split("&")[7].replace("Recordfile=","");
	callbegintime = str.split("&")[9].replace("DateTime=","");
	Uniqueid= str.split("&")[8].replace("Uniqueid=","");
    //console.log("用户:"+Callerid+"来电,被叫号码:"+Calleeid);
   // alert("Callerid=="+Callerid);
	//客户来电弹框
	var con = Ext.create('FMS.controller.Controllers');
	con.phonecall(Callerid,Calleeid,Recordfile,callbegintime,Uniqueid);
	
	//来电将坐席置忙
	Ext.getCmp('seat_busy').hide();
	Ext.getCmp('seat_free').show();
}



/**
 * 模拟来电
 */
function phonecall(){
	var phonetext = Ext.getCmp('seat_text').getValue();
	if(!Ext.getCmp('seat_text').isValid()){ 
		Ext.Msg.alert('提示', '您输入的电话号码格式不正确');
		return false; 
	} 
	//客户来电弹框
	var con = Ext.create('FMS.controller.Controllers');
	con.phonecall(phonetext);
}

/**
 * 默认设置软电话为置忙状态
 */
function initphone(){
	seat_free();
}

/**
 * 挂机
 */
function seat_onhook(){
	$.ajax({
            type: "GET",
            cache: false,
            url: admin.softphoneurl+"/admin/?m=interface&c=api&a=hangup",
            data : {
				"extension":extension
			},
            dataType: "jsonp",
            //jsonp: "callback",
            jsonpCallback: "seat_onhookCallback"
     });
}

/**
 * 挂机回调
 */
function seat_onhookCallback(data) {
	var obj = eval(data);//转换后的JSON对象
    //处理data
    if(obj.result == 1){
    	Ext.Msg.alert('提示', '挂机操作成功');
    	Ext.getCmp('seat_busy').show();
		Ext.getCmp('seat_free').hide();
    }else{
    	Ext.Msg.alert('提示', '挂机操作失败');
    }
    //更新坐席最后一条挂机时间
	jQuery.ajax( {
		url : window.BIZCTX_PATH + "/customer/customerInboundjson/updateHangupcalltime.action", // 请求路径
		data : {
			agentid : admin.runNumber
		}, // 参数
		async:false,
		type : "post", // 请求类型，默认为get
		success : function(data) {
		}
	});  	  
}

/**
 * 坐席置忙
 */
function seat_busy(){
	$.ajax({
            type: "GET",
            cache: false,
            url: admin.softphoneurl+"/admin/?m=interface&c=api&a=setdnd",
            data : {
			"extension":extension,
			"dnd":1
			},
            dataType: "jsonp",
            //jsonp: "callback",
            jsonpCallback: "seat_busyCallback"
     });
}

/**
 * 置忙回调
 */
function seat_busyCallback(data) {
	var obj = eval(data);//转换后的JSON对象
    //处理data
    if(obj.result == 1){
    	Ext.Msg.alert('提示', '置忙操作成功');
    	Ext.getCmp('seat_busy').hide();
		Ext.getCmp('seat_free').show();
    }else{
    	Ext.Msg.alert('提示', '置忙操作失败');
    }
}

/**
 * 坐席置闲
 */
function seat_free(){
	$.ajax({
            type: "GET",
            cache: false,
            url: admin.softphoneurl+"/admin/?m=interface&c=api&a=setdnd",
            data : {
			"extension":extension,
			"dnd":-1
			},
            dataType: "jsonp",
            //jsonp: "callback",
            jsonpCallback: "seat_freeCallback"
     });
}

/**
 * 置闲回调
 */
function seat_freeCallback(data) {
	var obj = eval(data);//转换后的JSON对象
    //处理data
    if(obj.result == 1){
    	if(!first){
    		Ext.Msg.alert('提示', '置闲操作成功');
    	}
    	first = false;
    	Ext.getCmp('seat_busy').show();
		Ext.getCmp('seat_free').hide();
    }else{
    	Ext.Msg.alert('提示', '置闲操作失败');
    }
}



/**
 * 保持通话
 */
function seat_hold(){
	$.ajax({
            type: "GET",
            cache: false,
            url: admin.softphoneurl+"/admin/?m=interface&c=api&a=hold",
            data : {
				"extension":extension
			},
            dataType: "jsonp",
            //jsonp: "callback",
            jsonpCallback: "seat_holdCallback"
     });
}


/**
 * 保持通话回调
 */
function seat_holdCallback(data) {
	var obj = eval(data);//转换后的JSON对象
    //处理data
    if(obj.result == 1){
    	Ext.Msg.alert('提示', '通话保持操作成功');
    	Ext.getCmp('seat_recoverhold').show();
		Ext.getCmp('seat_hold').hide();
    }else{
    	Ext.Msg.alert('提示', '保持通话操作失败');
    }
}

/**
 * 恢复通话
 */
function seat_recoverhold(){
	$.ajax({
            type: "GET",
            cache: false,
            url: admin.softphoneurl+"/admin/?m=interface&c=api&a=hold",
            data : {
				"extension":extension
			},
            dataType: "jsonp",
            //jsonp: "callback",
            jsonpCallback: "seat_recoverholdCallback"
     });
}

/**
 * 恢复通话回调
 */
function seat_recoverholdCallback(data) {
	var obj = eval(data);//转换后的JSON对象
    //处理data
    if(obj.result == 1){
    	Ext.Msg.alert('提示', '恢复通话操作成功');
    	Ext.getCmp('seat_hold').show();
		Ext.getCmp('seat_recoverhold').hide();
    }else{
    	Ext.Msg.alert('提示', '恢复通话操作失败');
    }
}

/**
 * 呼叫转接
 */
function seat_phoneswitch(){
	var phonetext = Ext.getCmp('seat_text').getValue();
	if(!Ext.getCmp('seat_text').isValid()){ 
		Ext.Msg.alert('提示', '您输入的电话号码格式不正确');
		return false; 
	} 
	$.ajax({
            type: "GET",
            cache: false,
            url: admin.softphoneurl+"/admin/?m=interface&c=api&a=transfer",
            data : {
				"extension":extension,
				"extensionDst":phonetext
			},
            dataType: "jsonp",
            //jsonp: "callback",
            jsonpCallback: "seat_phoneswitchCallback"
     });
	
}

/**
 * 呼叫转接回调
 */
function seat_phoneswitchCallback(data) {
	var obj = eval(data);//转换后的JSON对象
    //处理data
    if(obj.result == 1){
    	Ext.Msg.alert('提示', '呼叫转移操作成功!');
    	Ext.getCmp('seat_busy').show();
		Ext.getCmp('seat_free').hide();
    }
}

/**
 * 呼出
 */
function seat_callphone(){
	var phonetext = Ext.getCmp('seat_text').getValue();
	if(!Ext.getCmp('seat_text').isValid()){ 
		Ext.Msg.alert('提示', '您输入的电话号码格式不正确');
		return false; 
	} 
	$.ajax({
            type: "GET",
            cache: false,
            url: admin.softphoneurl+"/admin/?m=interface&c=api&a=dial",
            data : {
			"extension":extension,
			"extensionDst":phonetext
			},
            dataType: "jsonp",
            //jsonp: "callback",
            jsonpCallback: "seat_callphoneCallback"
     });
	
}

/**
 * 呼出回调
 */
function seat_callphoneCallback(data) {
	var obj = eval(data);//转换后的JSON对象
    //处理data
    if(obj.result == 1){
    	Ext.Msg.alert('提示', '呼出操作成功!');
    	//来电将坐席置忙
		Ext.getCmp('seat_busy').hide();
		Ext.getCmp('seat_free').show();
    }else{
    	Ext.Msg.alert('提示', '呼出操作失败');
    }
}

/**
 * 多方通话
 */
function seat_manyphone(){
	var phonetext = Ext.getCmp('seat_text').getValue();
	if(!Ext.getCmp('seat_text').isValid()){ 
		Ext.Msg.alert('提示', '您输入的电话号码格式不正确,注：外地手机号码前面需要加0');
		return false; 
	} 
	$.ajax({
            type: "GET",
            cache: false,
            url: admin.softphoneurl+"/admin/?m=interface&c=api&a=conference",
            data : {
				"extension":extension,
				"number":phonetext
			},
            dataType: "jsonp",
            jsonpCallback: "seat_manyphoneCallback"
     });
}

/**
 * 多方通话回调
 */
function seat_manyphoneCallback(data) {
	var obj = eval(data);//转换后的JSON对象
    //处理data
    if(obj.result == 1){
    	Ext.Msg.alert('提示', '多方通话操作成功!')
    }else{
    	Ext.Msg.alert('提示', '多方通话操作失败');
    }
}





