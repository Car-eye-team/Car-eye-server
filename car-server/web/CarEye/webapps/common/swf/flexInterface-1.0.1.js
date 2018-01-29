//创建socket连接
setTimeout(function(){createFlexConnection();},3*1000);  	

function getSWF(movieName){
	if (window.document[movieName]) {
		return window.document[movieName];
	}
	if (navigator.appName.indexOf("Microsoft Internet") == -1) {
		if (document.embeds && document.embeds[movieName])
			return document.embeds[movieName];
	} else { // if (navigator.appName.indexOf("Microsoft Internet")!=-1)
		return document.getElementById(movieName);
	} 

}

var socketNum = 0;   //控制flex登陆服务器，达到10次将不再重复登录
var timer; //socket断开后重复登录定时器
var state = false;
var loginstate = true;  //登陆成功变为false 


/**
 * 创建flex链接
 */
function createFlexConnection(){
	var commserverobj = document.getElementById("commserver");
	if(commserverobj!=null){
		commserverobj.innerHTML = "服务器连接中...";
	}
	if(loginstate){
		var flexObj = getSWF("taxiFlex");
		//alert("admin.commip=="+commip);
		//alert("admin.commip=="+commport);
		flexObj.createFmsFlexConnection(commip,commport);
	}
}

/**
 * 链接回调
 * @param ResponseType  0 链接成功，1 链接失败
 */
function RecreateFmsFlexConnection(ResponseType){
	
	if(ResponseType=="0"){
		//alert("链接成功!");
		userLoggin(admin.id,admin.blocid,admin.username,"56");
	}
}

/**
 * 服务器端socket断开通知
 * @param ResponseType
 */
function AutoRepeatLogin(ResponseType){
	//alert("服务器端socket断开=="+ResponseType);
	document.getElementById("commserver").innerHTML = "<font color='red'>服务器断开请刷新</font>";
}

/**
 * 用户登陆
 * @param {} userid 用户ID
 * @param {} deptid 机构ID
 * @param {} username 用户名
 * @param {} seq 序列号
 */
function userLoggin(userid,deptid,username,seq){
	var flexObj = getSWF("taxiFlex");
	flexObj.userLoggin(userid,deptid,username,seq);
}

/**
 * 用户登陆状态返回
 */
function ReUserLoggin(ResponseType,userid,deptid,username,seq){
	var commserverobj = document.getElementById("commserver");
	if(ResponseType == "0"){
		loginstate = false;
		if(commserverobj!=null){
			commserverobj.innerHTML = "<font color='green'>连接服务器成功</font>";
		}
		//alert("登陆成功");
	}else{
		if(commserverobj!=null){
			commserverobj.innerHTML = commserverobj
		}
		//alert("登陆失败");
	}	
}

/**
 * 查看车辆位置
 * @param {} userid 用户ID
 * @param {} deptid 机构ID
 * @param {} username 用户名
 * @param {} carid 车辆ID
 * @param {} taskId 流水号
 */
function checkVehiclePosition(userid,deptid,username,carid,taskId){
	new Ext.LoadMask('taxiMapPanel',{id : 'queryCarPosition',msg:"正在查询请稍候..."}).show();
	var flexObj = getSWF("taxiFlex");
	flexObj.checkVehiclePosition(userid,deptid,username,carid,taskId);
}

/**
 * 查看车辆位置回调
 * @param {} type 发送状态 1 失败 0 成功
 * @param {} userid 用户ID
 * @param {} deptid 机构ID
 * @param {} username 用户名
 * @param {} taskId 业务流水号
 */
function RecheckVehiclePosition(type,userid,deptid,username,taskId){
	
}

/**
 * 车辆定时跟踪
 * @param {} userid 用户ID
 * @param {} deptid 机构ID
 * @param {} username 用户名
 * @param {} carid 车辆ID
 * @param {} inv 时间间隔
 * @param {} expire 跟踪时长
 * @param {} taskId 业务流水号
 */
function vehicleTimingTracking(userid,deptid,username,carid,inv,expire,distance,effdistance,way,taskId){
	if(inv == 0 && expire ==0){
		Ext.Msg.alert("提示","停止监控指令下发成功");
		if(Ext.getCmp('timetrack')){
			Ext.getCmp('timetrack').hide();
		}
		if(Ext.getCmp('numtrack')){
			Ext.getCmp('numtrack').hide();
		}
	}else{
		new Ext.LoadMask('taxiMapPanel',{id : 'timetrack',msg:"车辆定时跟踪指令下发成功,请稍候..."}).show();
	}
	if(Ext.getCmp('timetrackwin')){
		Ext.getCmp('timetrackwin').close();
	}
	if(maptype == 1){
		map.clearOverlays();
	}else{
		mapObj.clearMap();
	}
	
	points = [];
	var flexObj = getSWF("taxiFlex");
	flexObj.vehicleTimingTracking(userid,deptid,username,carid,inv,expire,distance,effdistance,way,taskId);
}

/**
 * 车辆定次跟踪
 * @param {} userid 用户ID
 * @param {} deptid 机构ID
 * @param {} username 用户名
 * @param {} carid 车辆ID
 * @param {} inv 时间间隔
 * @param {} expire 跟踪时长,次数*时间间隔
 * @param {} taskId 业务流水号
 */
function vehicleNumTracking(userid,deptid,username,carid,inv,expire,distance,effdistance,way,taskId){
	if(inv == 0){
		Ext.Msg.alert("提示","停止定次跟踪指令下发成功");
	}else{
		new Ext.LoadMask('taxiMapPanel',{id : 'numtrack',msg:"车辆定次跟踪指令下发成功,请稍候..."}).show();
	}
	if(Ext.getCmp('numtrackwin')){
		Ext.getCmp('numtrackwin').close();
	}
	if(maptype == 1){
		map.clearOverlays();
	}else{
		mapObj.clearMap();
	}
	points = [];
	var flexObj = getSWF("taxiFlex");
	flexObj.vehicleTimingTracking(userid,deptid,username,carid,inv,expire,distance,effdistance,way,taskId);
}

/**
 * 车辆定时跟踪回调
 * @param {} type 发送状态 1 失败 0 成功
 * @param {} userid 用户ID
 * @param {} deptid 机构ID
 * @param {} username 用户名
 * @param {} taskId 业务流水号
 */
function RevehicleTimingTracking(type,userid,deptid,username,taskId){
	
}


/**
 * 查询终端参数
 * @param {} userid 用户ID
 * @param {} deptid 机构ID
 * @param {} username 用户名
 * @param {} carid 车辆ID
 * @param {} taskId 流水号
 */
function queryterminalparameters(userid,deptid,username,carid,taskId){
	var flexObj = getSWF("taxiFlex");
	flexObj.queryterminalparameters(userid,deptid,username,carid,taskId);
	if(taskId ==1){
		new Ext.LoadMask('basicsetwin',{id : 'terminalparameter_mark',msg:"终端参数信息查询中,请稍候..."}).show();
	}else if(taskId ==2){
		new Ext.LoadMask('areasetwin',{id : 'terminalparameter_mark',msg:"终端参数信息查询中,请稍候..."}).show();
	}else if(taskId ==3){
		new Ext.LoadMask('driversetwin',{id : 'terminalparameter_mark',msg:"终端参数信息查询中,请稍候..."}).show();
	}else if(taskId ==4){
		new Ext.LoadMask('otherssetwin',{id : 'terminalparameter_mark',msg:"终端参数信息查询中,请稍候..."}).show();
	}
}

/**
 * 查询终端参数回调
 * @param {} type 发送状态 1 失败 0 成功
 * @param {} userid 用户ID
 * @param {} deptid 机构ID
 * @param {} username 用户名
 * @param {} taskId 业务流水号
 */
function Requeryterminalparameters(type,userid,deptid,username,taskId){
	
	
}

/**
 * 发送公告信息
 * @param {} userid
 * @param {} deptid
 * @param {} content
 * @param {} taskId
 */
function sendAffiche(userid,deptid,username,content,taskId){
	var flexObj = getSWF("taxiFlex");
	flexObj.sendAffiche(userid,deptid,username,content,taskId);
}

/**
 * 发送公告信息回调
 * @param {} type
 * @param {} userid
 * @param {} deptid
 * @param {} username
 * @param {} taskId
 */
function RecheckSendAffiche(type,userid,deptid,username,taskId){
	
}

/**
 * 根据选择车辆进行实时推送
 * @param {} type 类型 1单选车辆、全选 2 取消单选、取消全选 3 重新加载车辆,此时caridvalue为空
 * @param {} carid 车辆ID,多值以英文,隔开
 * @param {} taskId 流水号
 */
function realTimeCheckedCar(type,caridvalue,taskId){
	var flexObj = getSWF("taxiFlex");
	var value = caridvalue.split(',')
	//数组分割，超过realtimecarcount长度部分多次发送
	for(var i=0,len=value.length;i<len;i+=realtimecarcount){
	   var result = [];
	   result.push(value.slice(i,i+realtimecarcount));
	   flexObj.realTimeCheckedCar(admin.id,admin.blocid,admin.username,type,result.toString(),taskId);
	}
	
}









