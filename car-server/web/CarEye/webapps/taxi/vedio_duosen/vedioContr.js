var devid;
var serverurl;
var name;
var password;
var rendertype;
var cache;
var playsound;
var showtoscale;
var showsatic;

function init(){
	jQuery.ajax( {
		url :window.BIZCTX_PATH +'/videoparamsetjson/queryVideoParamSet.action', // 请求路径
		data : {}, // 参数
		type : "post", // 请求类型
		error : function(request, textStatus, errorThrown) { // 设置表单提交出错
			if (textStatus == 'error') {
				swal("警告", "亲，网络好像有问题！", "warning");
			}
		},
		success : function(data) {
			var info = data.data;
			serverurl = info.protocol + info.ip + ':' + info.port + '/';
			name = info.name;
			password = info.password;
			rendertype = info.rendertype;
			cache = info.cache;
			playsound = info.playsound;
			showtoscale = info.showtoscale;
			showsatic = info.showsatic;
		}
	});
}

function config(){
	devid = document.getElementById("terminal").value;
	var obj1 = document.getElementById("EasyPlayerOcx1");
	
	obj1.Config(cache, playsound, showtoscale, showsatic);
}

function config1(){
	devid = document.getElementById("terminal").value;
	var obj2 = document.getElementById("EasyPlayerOcx2");

	obj2.Config(cache, playsound, showtoscale, showsatic);
}

//开始视频
function StartShow() {
	
	jQuery.ajax( {
		url :window.BIZCTX_PATH +'/videoparamsetjson/playOrStopSend.action', // 请求路径
		data : {
			'terminal': devid,
			'id': '1',
			'type': '0'
		}, // 参数
		type : "post", // 请求类型
		error : function(request, textStatus, errorThrown) { // 设置表单提交出错
			if (textStatus == 'error') {
				swal("警告", "亲，网络好像有问题！", "warning");
			}
		},
		success : function(data) {
			var su = data.result.su;
			if(su == 0){
				var obj1 = document.getElementById("EasyPlayerOcx1");
				obj1.Close();
				var url1 = serverurl+devid+"?channel=1.sdp";
				obj1.Start(url1, rendertype, name, password);
				config();
				Ext.Msg.alert("提示","实时预览指令已发送，请等待...");
			}
		}
	});
	
}

//开始视频
function StartShow1() {
	jQuery.ajax( {
		url :window.BIZCTX_PATH +'/videoparamsetjson/playOrStopSend.action', // 请求路径
		data : {
			'terminal': devid,
			'id': '2',
			'type': '0'
		}, // 参数
		type : "post", // 请求类型
		error : function(request, textStatus, errorThrown) { // 设置表单提交出错
			if (textStatus == 'error') {
				swal("警告", "亲，网络好像有问题！", "warning");
			}
		},
		success : function(data) {
			var su = data.result.su;
			if(su == 0){
				var obj2 = document.getElementById("EasyPlayerOcx2");
				obj2.Close();
				var url2 = serverurl+devid+"?channel=2.sdp";
				obj2.Start(url2, rendertype, name, password);
				config1();
				Ext.Msg.alert("提示","实时预览指令已发送，请等待...");
			}
		}
	});
    
}

//停止视频
function StopShow() {
	jQuery.ajax( {
		url :window.BIZCTX_PATH +'/videoparamsetjson/playOrStopSend.action', // 请求路径
		data : {
			'terminal': devid,
			'id': '1',
			'type': '1'
		}, // 参数
		type : "post", // 请求类型
		error : function(request, textStatus, errorThrown) { // 设置表单提交出错
			if (textStatus == 'error') {
				swal("警告", "亲，网络好像有问题！", "warning");
			}
		},
		success : function(data) {
			var su = data.result.su;
			if(su == 0){
				var obj1 = document.getElementById("EasyPlayerOcx1");
				obj1.Close();
				Ext.Msg.alert("提示","已停止预览");
			}
		}
	});
	
}

//停止视频
function StopShow1() {
	jQuery.ajax( {
		url :window.BIZCTX_PATH +'/videoparamsetjson/playOrStopSend.action', // 请求路径
		data : {
			'terminal': devid,
			'id': '2',
			'type': '1'
		}, // 参数
		type : "post", // 请求类型
		error : function(request, textStatus, errorThrown) { // 设置表单提交出错
			if (textStatus == 'error') {
				swal("警告", "亲，网络好像有问题！", "warning");
			}
		},
		success : function(data) {
			var su = data.result.su;
			if(su == 0){
				var obj2 = document.getElementById("EasyPlayerOcx2");
				obj2.Close();
				Ext.Msg.alert("提示","已停止预览");
			}
		}
	});
	
}
	
//回放
function queryPlaybackList() {
	var id = Ext.getCmp('v_id').getValue();
	if(id == null || id.length == 0){
		Ext.Msg.alert("提示","请选择通道");
        return;
	}
	var type = Ext.getCmp('v_type').getValue();
	if(type == null || type.length == 0){
		Ext.Msg.alert("提示","请选择类型");
        return;
	}
	
	var stime = Ext.util.Format.date(Ext.getCmp('v_stime').getValue(), 'Y-m-d H:i:s');
	var etime = Ext.util.Format.date(Ext.getCmp('v_etime').getValue(), 'Y-m-d H:i:s');
	if(stime == null || stime.length == 0){
		Ext.Msg.alert("提示","请选择开始时间");
        return;
	}
	if(etime == null || etime.length == 0){
		Ext.Msg.alert("提示","请选择结束时间");
        return;
	}
	if(etime != null && etime.length > 0){
            var beginTimes = stime.substring(0,10).split('-');
            var endTimes = etime.substring(0,10).split('-');
    
            var stimearray = stime.substring(10,19).split(':');
            var etimearray = etime.substring(10,19).split(':');
            var stimedate = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
            var etimedate = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
            
            if(etimedate < stimedate){
                Ext.Msg.alert("提示","开始时间必须小于结束时间");
                return;
            }
        } 

	jQuery.ajax( {
		url :window.BIZCTX_PATH +'/videoparamsetjson/playbackSend.action', // 请求路径
		data : {
			'terminal': devid,
			'id': id,
			'type': type,
			'stime': stime,
			'etime': etime
		}, // 参数
		type : "post", // 请求类型
		error : function(request, textStatus, errorThrown) { // 设置表单提交出错
			if (textStatus == 'error') {
				swal("警告", "亲，网络好像有问题！", "warning");
			}
		},
		success : function(data) {
//			var su = data.result.su;
//			if(su == 0){
//				var obj1 = document.getElementById("EasyPlayerOcx1");
//				var url1 = serverurl+devid+"-0.sdp";
//				obj1.Start(url1, rendertype, name, password);
//				config();
//				Ext.Msg.alert("提示","回放指令已发送，请等待...");
//			}
		}
	});
}
	
//回放指定文件
function startRecord(id,filename,splaysec,eplaysec){
	jQuery.ajax( {
		url :window.BIZCTX_PATH +'/videoparamsetjson/playbackAppoint.action', // 请求路径
		data : {
			'terminal': devid,
			'id': id,
			'filename': filename,
			'splaysec': splaysec,
			'eplaysec': eplaysec
		}, // 参数
		type : "post", // 请求类型
		error : function(request, textStatus, errorThrown) { // 设置表单提交出错
			if (textStatus == 'error') {
				swal("警告", "亲，网络好像有问题！", "warning");
			}
		},
		success : function(data) {
			var su = data.result.su;
			if(su == 0){
				if(id == '1'){
					var obj1 = document.getElementById("EasyPlayerOcx1");
					obj1.Close();
					var url1 = serverurl+devid+"-channel=1.sdp";
					obj1.Start(url1, rendertype, name, password);
					config();
					Ext.Msg.alert("提示","回放预览指令已发送，请等待...");
				}else if(id == '2'){
					var obj2 = document.getElementById("EasyPlayerOcx2");
					obj2.Close();
					var url2 = serverurl+devid+"-channel=2.sdp";
					obj2.Start(url2, rendertype, name, password);
					config1();
					Ext.Msg.alert("提示","回放预览指令已发送，请等待...");
				}
				
			}
		}
	});
}
//视频回放结束
function stopRecord(id){
	jQuery.ajax( {
		url :window.BIZCTX_PATH +'/videoparamsetjson/playbackStop.action', // 请求路径
		data : {
			'terminal': devid,
			'id': id
		}, // 参数
		type : "post", // 请求类型
		error : function(request, textStatus, errorThrown) { // 设置表单提交出错
			if (textStatus == 'error') {
				swal("警告", "亲，网络好像有问题！", "warning");
			}
		},
		success : function(data) {
			var su = data.result.su;
			if(su == 0){
				if(id == '1'){
					var obj1 = document.getElementById("EasyPlayerOcx1");
					obj1.Close();
					Ext.Msg.alert("提示","已停止回放预览");
				}else if(id == '2'){
					var obj2 = document.getElementById("EasyPlayerOcx2");
					obj2.Close();
					Ext.Msg.alert("提示","已停止回放预览");
				}
				
			}
		}
	});
}
	












