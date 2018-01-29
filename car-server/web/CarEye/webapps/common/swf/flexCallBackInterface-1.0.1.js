/**
 * 转换carstatus
 */
function chageCarStatus(carstatus){
	if(carstatus == 7){
		carstatus = '在线';
	}else if(carstatus == 1){
		carstatus = "长时间离线";
	}else if(carstatus == 2){
		carstatus = "离线";
	}else if(carstatus == 3){
		carstatus = '熄火';
	}else if(carstatus == 5){
		carstatus = '行驶';
	}else if(carstatus == 4){
		carstatus = '停车';
	}else if(carstatus == 6){
		carstatus = '报警';
	}else if(carstatus == 8){
		carstatus = '未定位';
	}else{
		carstatus = '';
	}
	return carstatus;
}

function chageKzstate(kzstate){
	if(kzstate == 1){
		kzstate = '重车';
	}else if(kzstate == 0){
		kzstate = '空车';
	}else{
		kzstate = '';
	}
	return kzstate;
}


/**
 * 转换方向
 * @param {} direction
 * @return {String}
 */
function chageDirection(direction){
   if(direction ==0){
		return "正北";
	}else if(direction >0 && direction <90){
		return "东北";
	}else if(direction == 90){
		return "正东";
	}else if(direction >90 && direction <180){
		return "东南";
	}else if(direction == 180){
		return "正南";
	}else if(direction >180 && direction <270){
		return "西南";
	}else if(direction == 270){
		return "正西";
	}else if(direction >270 && direction <360){
		return "西北";
	}else if(direction == 360){
		return "正北";
	}else{
		return "";
	}
}



/**
 * flex超时回调
 * @param {} msgid 发送消息ID
 * @param {} userid 用户ID
 * @param {} username 用户名
 * @param {} deptid 机构ID
 * @param {} carnumber 车牌号
 * @param {} datetime 发送时间
 * @param {} seq 消息序列
 */
function TimeoutCallback(msgid,userid,username,deptid,carnumber,datetime,seq){
	
	if(msgid == 33281){
	 	var control = Ext.create('FMS.controller.Controllers');
		if(multiple){
			control.loadCarPosition(carnumber,2); 
		}else{
			control.loadCarPosition(carnumber,1); 
		}
		if(Ext.getCmp('queryCarPosition')){
			Ext.getCmp('queryCarPosition').hide();
		}
		
	}else if(msgid == 33282){
		if(Ext.getCmp('timetrack')){
			Ext.getCmp('timetrack').hide();
		}
		Ext.Msg.alert('提示',"车辆定时跟踪未获取到数据!");
	}else if(msgid == 33028){
		if(Ext.getCmp('terminalparameter_mark')){
			Ext.getCmp('terminalparameter_mark').hide();
		}
		Ext.Msg.alert('提示',"终端参数未获取到数据!");
	}
//	alert("msgid="+msgid+",userid="+userid+",username="+username+",deptid="+deptid+",carnumber="+carnumber+",datetime="+datetime);
}

/**
 * 车辆状态推送
 * @param {} userid 用户ID
 * @param {} username 用户名
 * @param {} deptid 机构ID
 * @param {} carnumber 车牌号
 * @param {} carid 车辆ID
 * @param {} carstatus 车辆状态1长时间离线 2离线3 熄火4停车 5 行驶 6 报警 7在线 8未定位
 * @param {} travelposition 车辆运输状态 0 无状态 1待配货2运输中3休息中
 * @param {} seq 消息序列
 */
function VehicleStatePushCallback(userid,username,deptid,carnumber,carid,carstatus,travelposition,text,seq){
	var carTreeStore = Ext.StoreManager.get('CarTreeStore');
	//得到树节点为carid的节点
	var node = carTreeStore.getNodeById('C'+carid);
		var img;
		if(carstatus == 7){
			img = "<img src='resource/images/inline_2.png'></img>";
			text = "<font color='green'>"+carnumber + "(" + chageCarStatus(carstatus) + ")";
		}else if(carstatus == 1){
			img = "<img src='resource/images/offline_2.png'></img>";
			text = "<font color='#000000'>"+carnumber + "(" + chageCarStatus(carstatus) + ")";
		}else if(carstatus == 2){
			img = "<img src='resource/images/offline_2.png'></img>";
			text = "<font color='#000000'>"+carnumber + "(" + chageCarStatus(carstatus) + ")";
		}else if(carstatus == 3){
			img = "<img src='resource/images/shutdown_2.png'></img>";
			text = "<font color='#1200ff'>"+carnumber + "(" + chageCarStatus(carstatus) + ")";
		}else if(carstatus == 5){
			img = "<img src='resource/images/drivering_2.png'></img>";
			text = "<font color='green'>"+carnumber + "(" + chageCarStatus(carstatus) + ")";
		}else if(carstatus == 4){
			img = "<img src='resource/images/stop_2.png'></img>";
			text = "<font color='#9c9c9c'>"+carnumber + "(" + chageCarStatus(carstatus) + ")";
		}else if(carstatus == 6){
			img = "<img src='resource/images/warn_2.png'></img>";
			text = "<font color='red'>"+carnumber + "(" + chageCarStatus(carstatus) + ")";
		}else if(carstatus == 8){
			img = "<img src='resource/images/stop_2.png'></img>";
			text = "<font color='#9c9c9c'>"+carnumber + "(" + chageCarStatus(carstatus) + ")";
		}else{
			img = '';
		}
		node.set('text',img+text);
	
//	alert("车辆状态推送：deptid="+deptid+",carnumber="+carnumber+",carid="+carid+",carstatus="+carstatus+",travelposition="+travelposition+",text=="+text);
	
}

/**
 * 实时信息回调
 * @param {} carnumber 车牌号
 * @param {} carid 车辆ID
 * @param {} carstatus 车辆状态 2离线3 熄火4停车 5 行驶 6 报警 7在线 8 未定位
 * @param {} speed  速度
 * @param {} direction 方向
 * @param {} lng 经度
 * @param {} lat 纬度
 * @param {} address 地址
 */
function RealTimePositionCallback(carnumber,carid,carstatus,speed,direction,blng,blat,address,datetime,glng,glat,mileage,acc,kzstate){
	//alert("carnumber="+carnumber+",carid="+carid+",carstatus="+carstatus+",speed="+speed+",direction="+direction+",blng="+blng+",blat="+blat+",address="+address+",datetime="+datetime+",glng="+glng+",glat="+glat+",mileage="+mileage);
	var state = "";
	var terminalPositionStore = Ext.StoreManager.get('TerminalPositionStore');
	var num = terminalPositionStore.find('carid',carid);
	//遍历store根据carid修改对应的实时信息
	terminalPositionStore.each(function(recordall){
	if(recordall.get('carid') == carid){
		    var carstatusstr = chageCarStatus(carstatus);
		    var kzstatestr = chageKzstate(kzstate);
			 	//清除地图显示
		    if(maptype == 1){
		     	markermap.remove(carnumber);
		    }else{
		    	mapObj.remove(markermap.get(carnumber));
		    }
		       
		        
		        //更新数据
		        $("#gridview-"+realcaridvalue+"-table tr:eq("+num+") td:nth-child(4) div:nth-child(1)").html(kzstatestr);
		        $("#gridview-"+realcaridvalue+"-table tr:eq("+num+") td:nth-child(5) div:nth-child(1)").html(carstatusstr);
		        $("#gridview-"+realcaridvalue+"-table tr:eq("+num+") td:nth-child(6) div:nth-child(1)").html(acc=="0"?"关":"开");
		        $("#gridview-"+realcaridvalue+"-table tr:eq("+num+") td:nth-child(7) div:nth-child(1)").html(speed);
		        $("#gridview-"+realcaridvalue+"-table tr:eq("+num+") td:nth-child(8) div:nth-child(1)").html(chageDirection(direction));
		        if(parseFloat(blng) > 0){
		       	   $("#gridview-"+realcaridvalue+"-table tr:eq("+num+") td:nth-child(9) div:nth-child(1)").html(blng);
		        }
		        if(parseFloat(blat) > 0){
		       	   $("#gridview-"+realcaridvalue+"-table tr:eq("+num+") td:nth-child(10) div:nth-child(1)").html(blat);
		        }
		        $("#gridview-"+realcaridvalue+"-table tr:eq("+num+") td:nth-child(11) div:nth-child(1)").html(address);
		        $("#gridview-"+realcaridvalue+"-table tr:eq("+num+") td:nth-child(12) div:nth-child(1)").html(mileage);
		        $("#gridview-"+realcaridvalue+"-table tr:eq("+num+") td:nth-child(13) div:nth-child(1)").html(datetime);
		        
			  var icon = null;
			  if(maptype == 1){
			  _mapbaidu.locationMapCloseWindow(blng,blat,carnumber,carstatusstr,icon,direction,true,2);
			  }else{
			   _mapgaode.locationMapCloseWindow(blng,blat,carnumber,carstatusstr,icon,direction,true,2);
			  }
	   		  
		      //当实时位置主动推送时，如果为该车辆将重新刷新地图
				var grid = Ext.getCmp('carRealTime_list');
				var data = grid.getSelectionModel().getSelection();
				var record = data[0];
				if(record != undefined){
				    if(record.get('carid') == carid){
				    	if(maptype == 1){
					    	 bdzoomflag = false;
							 map.setCenter(new BMap.Point(blng,blat));
				    	}else{
				    		var marker = markermap.get(carnumber);
							var pointinfo = [];
					 		pointinfo.push(marker);
							mapObj.setFitView(pointinfo);
				    	}
						
				    }
				}
  		}
	})
}

/**
 * 报警回调
 * @param {} carnumber 车牌号
 * @param {} carid 车辆ID
 * @param {} carstatus  车辆状态 2离线3 熄火4停车 5 行驶 6 报警 7在线 8 未定位
 * @param {} speed 速度
 * @param {} direction 方向
 * @param {} lng 经纬度
 * @param {} lat
 * @param {} address 地址
 * @param {} alarmname 报警类型
 * @param {} alarmtime 报警时间
 */
function AlarmCallback(carnumber,carid,carstatus,speed,direction,lng,lat,address,alarmname,alarmtime,alarmkey,musicaddr,deptname,terminal,drivername,phone){
	if(admin.alarmcount == 0){	//所有报警类型都处于关闭状态，不处理推送信息
		return;
	}
	
	$("#alarmspan").html("["+alarmtime+"]"+carnumber+"处于"+alarmname+",请及时处理!");	
	
	//提醒来了之后播放背景音乐
	if(musicaddr != "false"){	//false关闭,其它为实际路径
		if(Ext.isIE){
//		     document.getElementById("song").src= window.BIZCTX_PATH + '/'+ musicaddr;
		     //无法用js来控制embed标签的播放，采用先移除控件，然后加上新的控件自动播放的方式来实现
		     $('#mod_player > embed').remove();
		     var str = "<embed id='song' src='"+window.BIZCTX_PATH + "/"+ musicaddr+"' hidden='true' autostart='true' loop='false'>";

          	 $('#mod_player').html(str);
		     
	    }else{
			 if(soundManager.getSoundById(alarmkey) == null){ //把报警类型的key作为声音控件的id，如果已经初始化了，则可以直接播放
	    		soundManager.createSound({
		            id: alarmkey, 
		            url: window.BIZCTX_PATH + '/'+ musicaddr //报警声音wav文件的路径
		        });
	    	}
		    soundManager.play(alarmkey);	//根据初始化id播放对应声音		}
    	 }
	}
	
}

/**
 *  位置应答回调
 * @param {} type 类型 1 位置查询 2 位置跟踪
 * @param {} carnumber 车牌号
 * @param {} carid 车辆ID
 * @param {} carstatus 车辆状态 2离线3 熄火4停车 5 行驶 6 报警 7在线 8未定位
 * @param {} speed  速度
 * @param {} direction 方向
 * @param {} lng 经度
 * @param {} lat 纬度
 * @param {} address 地址
 */
function PositionAnswerCallback(type,carnumber,carid,carstatus,speed,direction,lng,lat,address,datetime,deptname){
	//alert("位置应答回调：type="+type+",carnumber="+carnumber+",carid="+carid+",carstatus="+carstatus+",speed="+speed+",direction="+direction+",lng="+lng+",lat="+lat+",address="+address);
	if(Ext.getCmp('queryCarPosition')){
		Ext.getCmp('queryCarPosition').hide();
	}
	if(type ==1){
		 var state = "";
		 if(chageCarStatus(carstatus) == '离线'){
			state = "当前<font color='red'>离线</font>";
		 }else if(chageCarStatus(carstatus) == '长时间离线'){
			state = "当前<font color='red'>长时间离线</font>";
		 }else{
			state = "当前<font color='green'>" + chageCarStatus(carstatus) + "</font>";
		 }
		 
	  Ext.Ajax.request( {
		url: window.BIZCTX_PATH + '/terminalpositionjson/queryCarDetail.action', //请求的服务器地址
	      params:{ 
	      		carnumber : carnumber
	      },
			method : 'POST',  
			timeout : 5000,
			success : function(response) {
				 var action = Ext.JSON.decode(response.responseText);
				 var carid =  action.result.data.carid;
				 var glng =  action.result.data.glng;
				 var glat =  action.result.data.glat;
				 var direction =  action.result.data.direction;
				 var gaddress =  action.result.data.gaddress;
				 var carstatus = action.result.data.carstatus;

				 var icon = getIcon(carstatus);
				 var carstatusText = getCarstatusText(carstatus);
		      
		   		 _mapgaode.locationMapCloseWindow(glng,glat,carnumber,carstatusText,icon,direction,false,2);
			  
			  if(multiple){	//查看多车
			  	if(maptype == 1){
			  		points.push(new BMap.Point(blng,blat));
				 	map.setViewport(points);	
			  	}else{
			  		mapObj.setFitView();
			  	}
				 
				 
				 
			  }
			},
			failure : function() {
			}
	    });
		 
	}else if(type ==2){
		if(Ext.getCmp('timetrack')){
			Ext.getCmp('timetrack').hide();
		}
		var tabPanel = Ext.getCmp("taxiMapPanel");
		tabPanel.setActiveTab("taximap");
		var p = new AMap.LngLat(lng,lat);
		p.address = address;
		p.datetime= datetime;
		p.direction= direction;
		points.push(p);
		
		init();
	}
	
}

/**
 * 车辆定次跟踪回调
 * @param {} type 发送状态 1 失败 0 成功
 * @param {} userid 用户ID
 * @param {} deptid 机构ID
 * @param {} username 用户名
 * @param {} taskId 业务流水号
 */
function vehicleNumTrackingCallback(carnumber,carid,carstatus,speed,direction,lng,lat,address,datetime,deptname){
	if(Ext.getCmp('numtrack')){
		Ext.getCmp('numtrack').hide();
	}
	var tabPanel = Ext.getCmp("taxiMapPanel");
	tabPanel.setActiveTab("taximap");
	var p = new AMap.LngLat(lng,lat);
	p.address = address;
	p.datetime= datetime;
	p.direction= direction;
	points.push(p);
	
	init();
}

/**
 * 终端参数应答
 */
function TerminalParameterAnswerCallback(carid){
//	alert("终端参数应答=="+carid);
	var form ;
	if(typeof Ext.getCmp('basicset_form') != "undefined"){
		form = Ext.getCmp('basicset_form');
	}else if(typeof Ext.getCmp('areaset_form') != "undefined"){
		form = Ext.getCmp('areaset_form');
	}else if(typeof Ext.getCmp('driverset_form') != "undefined"){
		form = Ext.getCmp('driverset_form');
	}else if(typeof Ext.getCmp('othersset_form') != "undefined"){
		form = Ext.getCmp('othersset_form');
	}
	form.getForm().load({
	      url: window.BIZCTX_PATH + '/terminalpositionjson/loadTerminalParameter.action', //请求的服务器地址
	      params:{ 
	      		carid : carid
	      },
	      success : function(form, action) {
		   	 if(Ext.getCmp('terminalparameter_mark')){
				Ext.getCmp('terminalparameter_mark').hide();
			 }
		}
	});
}

/**
 * 固定短消息
 * @param {} type
 * @param {} carnumber
 * @param {} carid
 * @param {} content
 * @param {} createtime
 */
function FixedShortMessageCallback(type,carnumber,carid,content,createtime){
	var store = Ext.StoreManager.get('FixedShortMessageStore');
	var userid = admin.id;
	Ext.Ajax.request({
		url: window.BIZCTX_PATH + '/system/userjson/queryRemindStatus.action', //请求的服务器地址
	    params:{ 
	      	userid : userid,
	      	category:2,
	      	type:"2"+type
	    },
	    method : 'POST',  
		timeout : 15000,
		success : function(response) {
			var text = response.responseText;
			var result = eval( "(" + text + ")" );//转换后的JSON对象 
			var value =  result.result.value;	//1打开 2 关闭
			if(value == 1){
				//每来一条报警信息往store中加一条记录
				store.add({type: type,carnumber: carnumber,carid: carid,content: content,createtime: createtime});
				store.sort('createtime', 'DESC');
				if(Ext.getCmp('msgWindow') == undefined){
					var msgWindow = Ext.create('Ext.window.Window', {  
							    title : "系统消息提醒",  
							    id : 'msgWindow',  
							    width : 550,  
							    height : 200,  
							    closeAction : 'destroy',
							    closable : true,
							    border:false,
							    x:document.body.clientWidth-550,
							    y:document.body.clientHeight-225,
							    autoScroll : true,  
							    modal : false,  
							    bodyStyle : {  
							        background : '#ffffff',  
							        margin : 'auto'  
							    },
							    items: [ {
									    xtype: 'grid',
									    autoWidth: true,
										autoHeight : true,
										//minHeight : 168,
										minWidth : 530,
										frame: false,
										border:false,
										store: store,
										stripeRows:true, //表格是否隔行换色，默认为false
										loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
										autoScroll : true,  
										columns: [
										{ header: '提醒类型', width: 90, dataIndex: 'type', sortable: true,renderer:function(value){
													if(value ==1){
														return "固定消息提醒";
													}else if(value == 2){
														return "车辆年检提醒";
													}else if(value == 3){
														return "车辆轮胎提醒";
													}else if(value == 4){
														return "车辆罐检提醒";
													}else if(value == 5){
														return "车辆保险提醒";
													}else if(value == 6){
														return "车辆季审提醒";
													}else if(value == 7){
														return "车辆保养提醒";
													}else if(value == 8){
														return "驾驶证年审提醒";
													}
										} },
										{ header: '车牌号', width: 90, dataIndex: 'carnumber', sortable: true},
										{ header: '车辆ID', flex: 1, dataIndex: 'carid', hidden: true},
										{ header: '消息内容', flex: 4, dataIndex: 'content', sortable: true,renderer:function(value){
											return '<div style="white-space:normal">' + value + '</div>'; 
										}},
										{ header: '时间', flex: 2, dataIndex: 'createtime', sortable: true }
										]
								 } ]
							   
							});
						msgWindow.show();
						
						//关闭之后清空提醒记录
						msgWindow.on('beforeclose',function(panel){
							store.removeAll();
						})
				}
				
				//发送短信提示
				if(type == 2 || type == 7 || type == 8){
					Ext.Ajax.request({
						url: window.BIZCTX_PATH + '/commonjson/sendSms.action', //请求的服务器地址
					    params:{ 
					      	carid : carid,
					      	content:content
					    },
					    method : 'POST',  
						timeout : 15000,
						success : function(response) {
							var text = response.responseText;
							var result = eval( "(" + text + ")" );//转换后的JSON对象 
							var su =  result.result.su;	//0代表发送成功
						}
					});
				}
			}
		},
		failure : function() {
		}
	});

}

/**
 * 系统公告信息
 */
function AfficheCallback(deptname,content,createtime){
	
	var store = Ext.StoreManager.get('AfficheInfoStore');
	//每来一条报警信息往store中加一条记录
	store.add({deptname: deptname,content: content,createtime: createtime});
	store.sort('createtime', 'DESC');
	if(Ext.getCmp('afficheWindow') == undefined){
		var window = Ext.create('Ext.window.Window', {  
				    title : "系统公告信息",  
				    id : 'afficheWindow',  
				    width : 550,  
				    height : 200,  
				    closeAction : 'destroy',
				    closable : true,
				    border:false,
				    x:document.body.clientWidth-550,
				    y:document.body.clientHeight-225,
				    autoScroll : true,  
				    modal : false,  
				    bodyStyle : {  
				        background : '#ffffff',  
				        margin : 'auto'  
				    },
				    items: [ {
						    xtype: 'grid',
						    autoWidth: true,
							autoHeight : true,
							//minHeight : 168,
							minWidth : 530,
							frame: false,
							border:false,
							store: store,
							stripeRows:true, //表格是否隔行换色，默认为false
							loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
							autoScroll : true,  
							columns: [
							{ header: '发送者', flex: 1, dataIndex: 'deptname', sortable: true},
							{ header: '公告内容', flex: 4, dataIndex: 'content', sortable: true,renderer:function(value){
								return '<div style="white-space:normal">' + value + '</div>'; 
							}},
							{ header: '发送时间', flex: 2, dataIndex: 'createtime', sortable: true }
							]
					 } ]
				   
				});
		var userid = admin.id;
		Ext.Ajax.request({
			url: bizctxpath+'/system/userjson/queryRemindStatus.action', //请求的服务器地址
		    params:{ 
		      	userid : userid,
		      	category:3
		    },
		    method : 'POST',  
			timeout : 15000,
			success : function(response) {
				var text = response.responseText;
				var result = eval( "(" + text + ")" );//转换后的JSON对象 
				var value =  result.result.value;	//1打开 2 关闭
				if(value == 1){
					window.show();
					//关闭之后清空提醒记录
					window.on('beforeclose',function(panel){
						store.removeAll();
					})
				}
			},
			failure : function() {
			}
		});
	}
}

/**
 * 温度监控
 * @param {} carnumber 车牌号码
 * @param {} carid 车辆ID
 * @param {} normal 正常阀值
 * @param {} warn 警告阀值
 * @param {} alarm 报警阀值
 * @param {} temperature 当前温度
 */
function TemperatureMonitoringCallback(carnumber,carid,normal,warn,alarm,temperature,state,createtime){
	var tempListStore = Ext.StoreManager.get('TempListStore');
	//遍历store根据carid修改对应的实时信息
	for(var i=0;i<tempListStore.getCount();i++){
		var data = tempListStore.getAt(i).data;
		var record = tempListStore.getAt(i);
		if(data.carid == carid){
			record.set('normal',normal)
			record.set('warn',warn)
			record.set('alarm',alarm)
			record.set('temperature',temperature);
			record.set('state',state)
			record.set('createtime',createtime)
		}
	}
	
	//报警
	if(state != "1"){
			var store = Ext.StoreManager.get('TempMessageStore');
			//每来一条报警信息往store中加一条记录
			var record = {carid: carid,carnumber: carnumber,temperature:temperature,
					   normal: normal,warn: warn,alarm: alarm,state:state,createtime:createtime};
			for(var i=0;i<store.getCount();i++){
				if(store.getAt(i).data.carid == carid){
					store.removeAt(i);
				}
			}
			store.add(record);
			store.sort('createtime', 'DESC');
			if(Ext.getCmp('tempMessageWindow') == undefined){
				var window = Ext.create('Ext.window.Window', {  
					    title : "车辆温度报警",  
					    id : 'tempMessageWindow',  
					    width : 550,  
					    height : 200,  
					    closeAction : 'destroy',
					    closable : true,
					    border:false,
					    x:document.body.clientWidth-550,
					    y:document.body.clientHeight-225,
					    autoScroll : true,  
					    modal : false,  
					    bodyStyle : {  
					        background : '#ffffff',  
					        margin : 'auto'  
					    },
					    items: [ {
							    xtype: 'grid',
							    autoWidth: true,
								autoHeight : true,
								minWidth : 610,
								frame: false,
								border:false,
								store: store,
								stripeRows:true, //表格是否隔行换色，默认为false
								loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
								autoScroll : true,  
								columns: [
									{ header: '车牌号',  width:90, dataIndex: 'carnumber', sortable: true},
									{ header: '当前温度(°C)',  width:90, dataIndex: 'temperature', sortable: true,renderer:function(value,cellmeta,record){
										if(record.get('state') ==1){
											return "<font color = 'green'>"+value+"</font>";
										}else if(record.get('state') == 2){
											return "<font color = '#8b8b02'>"+value+"</font>";
										}else if(record.get('state') == 3){
											return "<font color = '#8b8b02'>"+value+"</font>";
										}else if(record.get('state') == 4){
											return "<font color = '#FF0000'>"+value+"</font>";
										}else {
											return "";
										}
									} },
									{ header: '状态',  width:60, dataIndex: 'state', sortable: true,renderer:function(value){
										if(value ==1){
											return "<font color = 'green'>正常</font>";
										}else if(value == 2){
											return "<font color = '#8b8b02'>警告</font>";
										}else if(value == 3){
											return "<font color = '#8b8b02'>报警</font>";
										}else if(value == 4){
											return "<font color = '#FF0000'>严重</font>";
										}else {
											return "";
										}
									} },
									{ header: '时间',  width:130, dataIndex: 'createtime', sortable: true },
									{ header: '正常阀值',  width:80, dataIndex: 'normal', sortable: true },
									{ header: '警告阀值',  width:80, dataIndex: 'warn', sortable: true },
									{ header: '报警阀值',  width:80, dataIndex: 'alarm', sortable: true }
								]
						 } ]
		  		 });
		  		 var userid = admin.id;
		         Ext.Ajax.request({
					url: window.BIZCTX_PATH + '/system/userjson/queryRemindStatus.action', //请求的服务器地址
				    params:{ 
				      	userid : userid,
				      	category:4
				    },
				    method : 'POST',  
					timeout : 15000,
					success : function(response) {
						var text = response.responseText;
						var result = eval( "(" + text + ")" );//转换后的JSON对象 
						var value =  result.result.value;	//1打开 2 关闭
						if(value == 1){
							window.show();
							 //关闭之后清空提醒记录
							window.on('beforeclose',function(panel){
								store.removeAll();
							})
						}
					},
					failure : function() {
					}
				});
		         
	        }
		}
	
}

/**
 * 回放视频列表
 */
function PlaybackListCallback(id,terminal,carid,count,vlist){
	var v_terminal = $('#terminal').val();
	
	if(v_terminal == terminal){
		//将字符串中的"|"替换为双引号
		var itemsstr = vlist.replace(/\|/g,'"');
		var items = eval(itemsstr);
		
		var store = Ext.StoreManager.get('PlaybackSearchFileStore');
		
		//回放查询时是否清空列表状态
		if(clearflag){
			store.removeAll();
			clearflag = false;
			eid = 0;
		}
		
		for(var i=0; i<items.length; i++){
			var videotime = '';
			if(items[i].filename.indexOf('.mp4') != -1){
				var filetime = items[i].filename.substring(0,items[i].filename.indexOf('.mp4'));
				if(filetime.length == 13){
					var filedate = new Date(parseInt(filetime));
					
					var Y = filedate.getFullYear() + '-';
					var M = (filedate.getMonth()+1 < 10 ? '0'+(filedate.getMonth()+1) : filedate.getMonth()+1) + '-';
					var D = (filedate.getDate() < 10 ? '0'+filedate.getDate() : filedate.getDate()) + ' ';
					var h = (filedate.getHours() < 10 ? '0'+filedate.getHours() : filedate.getHours()) + ':';
					var m = (filedate.getMinutes() < 10 ? '0'+filedate.getMinutes() : filedate.getMinutes()) + ':';
					var s = (filedate.getSeconds() < 10 ? '0'+filedate.getSeconds() : filedate.getSeconds()); 
					
					videotime = Y+M+D+h+m+s; 
					
				}
			} 
			
			var record = {id: id,filename: items[i].filename,videotime: videotime,splaysec: items[i].splaysec,eplaysec: items[i].eplaysec,eid: i+eid};
			store.add(record);
			
		}
		store.sort('filename', 'DESC');
		
		eid += items.length;
	}
	
}

/**
 * 回放视频结束
 */
function PlaybackFinishCallback(id,terminal,carid){
	var v_terminal = $('#terminal').val();
	
	if(v_terminal == terminal){
		Ext.Msg.alert("提示","回放预览已结束");
		if(id == '1'){
			var obj1 = document.getElementById("EasyPlayerOcx1");
			obj1.Close();
		}else if(id == '2'){
			var obj2 = document.getElementById("EasyPlayerOcx2");
			obj2.Close();
		}
	}
	
}













