var alarmflag = true;
var alarmriflag = true;

var map;
var markermap = new Map();

var mapTrack;

Ext.define('CarTrackApp.controller.CarTrackCtrl', {
    extend: 'Ext.app.Controller',
    models: ['CarTrackModel','CarInfoModel','AlarmModel','AlarmRiModel'],//声明该控制层要用到的view
    stores: ['CycleStore','RateStore','CarTrackListStore','CarListStore','CarStatusStore'],//声明该控制层要用到的view
    views: ['CarTrackSearchView','CarTrackListView','MapView','Bottom','SearchMapView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'carTrackSearchView',
            selector: 'carTrackSearchView'
        },{
            ref: 'carTrackMapView',
            selector: 'carTrackMapView'
        },{
            ref: 'carTrackListView',
            selector: 'carTrackListView'
        },{
            ref: 'mapView',
            selector: 'mapView'
        },{
            ref: 'bottom',
            selector: 'bottom'
        }
    ],
    init: function() {
		this.control({
			'mapView': {
                afterrender:function(component, eOpts){
                    //初始化地图
                    render: this.initMap(component.el.dom);
                }
            },
			'carTrackSearchView button[action=search]' : {
				click : this.searchCarTrack
			},
			'carTrackAddView button[action=confirm]' : {
				click : this.queryCarTrack
			},
			'carTrackSearchView button[action=mapcj]' : {
				click : this.mapcj
			},
			'carTrackSearchView button[action=newpage]' : {
				click : this.newpage
			},
			'carTrackSearchView' : {
				afterrender : this.loadCarnumber
			},
			'carTrackListView button[action=export]' : {
				click : this.exportTrack
			},
			'carTrackListView button[action=statussearch]' : {
				click : this.statussearch
			}
		});
	},
	 /*
        初始化地图
    */
    initMap:function(div){
    	
    	if(maptype == 1){
    		map = new BMap.Map(div);
	        //默认位置
	        var point = new BMap.Point(116.3972,39.9096);
	        map.centerAndZoom(point, 12);
	 
	        /*
	            获取当前位置
	        */
	        var localCity = new BMap.LocalCity();
	        localCity.get(function(result){
	            var cityName = result.name;
	            var localPoint = new BMap.Point(result.center.lng, result.center.lat);
	            map.setCenter(localPoint,cityName);
	        });
	 
	        map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
	        map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	        map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
	        map.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
	        map.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件
	        map.addControl(new BMap.MapTypeControl());          //添加地图类型控件
    	}else{
    		markermap.clear();
			mapTrack = new AMap.Map(div,{
				resizeEnable: true,
		        rotateEnable:true,
		        dragEnable:true,
		        zoomEnable:true,
		        zooms:[3,18],
		        //二维地图显示视口
		        view: new AMap.View2D({
		            center:new AMap.LngLat(113.897276,22.573035),//地图中心点
		            zoom:11 //地图显示的缩放级别
		        })
		    });
		     //加载比例尺插件
		    mapTrack.plugin(["AMap.Scale"],function(){       
		        scale = new AMap.Scale();
		        mapTrack.addControl(scale);
		    });
		    
		    //在地图中添加ToolBar插件
		    mapTrack.plugin(["AMap.ToolBar"],function(){     
		        toolBar = new AMap.ToolBar();
		        mapTrack.addControl(toolBar);    
		    });
		    
		    //添加地图类型切换插件
		    mapTrack.plugin(["AMap.MapType"],function(){
		        //地图类型切换
		        var mapType= new AMap.MapType({
		            showRoad:true //叠加路网图层
		        });
		        mapTrack.addControl(mapType);
		    }); 
		    
		     //在地图中添加鹰眼插件
		    mapTrack.plugin(["AMap.OverView"],function(){
		        //加载鹰眼
		        overView = new AMap.OverView({
		            visible:true //初始化隐藏鹰眼
		        });
		        mapTrack.addControl(overView);
		    });
		    mapTrack.plugin(["AMap.RangingTool"],function(){ 
		         ruler1 = new AMap.RangingTool(mapTrack);
				 AMap.event.addListener(ruler1,"end",function(e){
					ruler1.turnOff();
				 });
			});
    	}
 
    },
    mapcj:  function(){
    	if(maptype == 1){
    		var myDis = new BMapLib.DistanceTool(map);
			myDis.open();
    	}else{
    		var ruler1;
			mapTrack.plugin(["AMap.RangingTool"],function(){
		         ruler1 = new AMap.RangingTool(mapTrack);
		         AMap.event.addListener(ruler1,"end",function(e){
		         	ruler1.turnOff();
		         });
		    });
		    ruler1.turnOn();
    	}
    	
    },
    newpage:  function(){
    	var carnumber = Ext.getCmp('track_carnumber').getValue();
    	var url = window.BIZCTX_PATH + "/cvp/cartrack/index.jsp?carnumber=" + carnumber;
		window.open(url);
    },
    loadCarnumber:  function(){
    	var carnumber = document.getElementById('carnumber').value;
    	Ext.getCmp('track_carnumber').setValue(carnumber);
    },
    loadCar : function(){
    	var carnumber = Ext.getCmp('track_carnumber').getRawValue();
    	if(admin.deptid == 1){
    		if(carnumber.length >=3 ){
				var store = this.getCarListStoreStore();
				store.clearFilter(true);
				store.on('beforeload', function (store, options) {
		            var new_params = { 
		            	carnumber: encodeURI(carnumber)
		            };
		            Ext.apply(store.proxy.extraParams, new_params);
		        });
		        store.reload();
    		}
    	}else{
    		var store = this.getCarListStoreStore();
			store.clearFilter(true);
			store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(carnumber)
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.reload();
    	}
		},
	exportAlarmListInfo : function (button){
        	var carnumber = encodeURI(Ext.getCmp('track_carnumber').getValue());
        	var an = 'a1';
			var stime = encodeURI(Ext.util.Format.date(Ext.getCmp('ct_stime').getValue(), 'Y-m-d H:i:s'));
			var etime = encodeURI(Ext.util.Format.date(Ext.getCmp('ct_etime').getValue(), 'Y-m-d H:i:s'));
        	location.href= window.BIZCTX_PATH + '/servlet/ExportFms?type=2&carnumber='+carnumber
        	+'&an='+an+'&stime='+stime+'&etime='+etime;
	},
	exportAlarmRiListInfo : function (button){
        	var carnumber = encodeURI(Ext.getCmp('track_carnumber').getValue());
        	var an = 'a1';
			var stime = encodeURI(Ext.util.Format.date(Ext.getCmp('ct_stime').getValue(), 'Y-m-d H:i:s'));
			var etime = encodeURI(Ext.util.Format.date(Ext.getCmp('ct_etime').getValue(), 'Y-m-d H:i:s'));
        	location.href= window.BIZCTX_PATH + '/servlet/ExportFms?type=11&carnumber='+carnumber
        	+'&an='+an+'&stime='+stime+'&etime='+etime;
	},
	exportCarStopListInfo : function (button){
        	var carnumber = Ext.getCmp('track_carnumber').getValue();
		if (carnumber == null || carnumber == ""){
			Ext.Msg.alert('提示',"请选择车牌号码");
			return;
		}
		
		var stime = Ext.util.Format.date(Ext.getCmp('ct_stime').getValue(), 'Y-m-d H:i:s');
	    var etime = Ext.util.Format.date(Ext.getCmp('ct_etime').getValue(), 'Y-m-d H:i:s');
		
		if (stime == null || stime == ""){
			Ext.Msg.alert('提示',"请输入开始时间");
			return;
		}
		if (etime == null || etime == ""){
			Ext.Msg.alert('提示',"请输入结束时间");
			return;
		}
		
	    var beginTimes = stime.substring(0,10).split('-');
        var endTimes = etime.substring(0,10).split('-');

        var stimearray = stime.substring(10,19).split(':');
        var etimearray = etime.substring(10,19).split(':');
        var dt = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
        var dt1 = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
        var now = new Date();
        
        if(dt1 < dt){
        	Ext.Msg.alert("提示","开始时间必须小于终止时间");
        	return;
        }
        if(dt1 - now > 0){
    		Ext.Msg.alert("提示","截止时间不能大于当前时间");
        	return;
    	}
    	if(now - dt > 15552000000){
    		Ext.Msg.alert("提示","只能查询6个月以内的数据");
        	return;
    	}
	    
	    location.href= window.BIZCTX_PATH + '/servlet/ExportFms?type=23&carnumber='+carnumber
        	+'&stime='+stime+'&etime='+etime;
	},
	searchCarTrack : function(){
		if(maptype == 1){
			var carnumber = Ext.getCmp('track_carnumber').getRawValue();
			if (carnumber == null || carnumber.length != 7){
				Ext.Msg.alert('提示',"请选择车牌号码");
				return;
			}
			
			var stime = Ext.util.Format.date(Ext.getCmp('ct_stime').getValue(), 'Y-m-d H:i:s');
		    var etime = Ext.util.Format.date(Ext.getCmp('ct_etime').getValue(), 'Y-m-d H:i:s');
			
			if (stime == null || stime == ""){
				Ext.Msg.alert('提示',"请输入开始时间");
				return;
			}
			if (etime == null || etime == ""){
				Ext.Msg.alert('提示',"请输入结束时间");
				return;
			}
			
		    var beginTimes = stime.substring(0,10).split('-');
	        var endTimes = etime.substring(0,10).split('-');
	
	        var stimearray = stime.substring(10,19).split(':');
	        var etimearray = etime.substring(10,19).split(':');
	        var dt = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
	        var dt1 = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
	        var now = new Date();
	        
	        if(dt1 < dt){
	        	Ext.Msg.alert("提示","开始时间必须小于终止时间");
	        	return;
	        }
	        if(dt1 - now > 0){
	    		Ext.Msg.alert("提示","截止时间不能大于当前时间");
	        	return;
	    	}
	    	if(now - dt > 15552000000){
	    		Ext.Msg.alert("提示","只能查询6个月以内的数据");
	        	return;
	    	}
	    	
	    	var mask=new Ext.LoadMask('trackMap',{msg:"数据加载......"});
		    mask.show(stime+","+etime);
		    
	    	Ext.getCmp("trackbottom").expand();
	    	
	    	//查询之后先暂停播放上次轨迹
	    	if(timer) {
				window.clearTimeout(timer);
				Ext.getCmp('slider').setValue(0);
			}
			
	    	var mask1=new Ext.LoadMask('carTrackListView',{msg:"数据加载......"});
		    mask1.show();
		    
	    	Ext.Ajax.request( {
				url :window.BIZCTX_PATH +"/carjson/findNotStopCarTrackPointList.action", // 请求路径
				method : 'POST',  
				timeout : 120000,
				params : {
					"queryParams.carnumber":encodeURI(carnumber),
					"queryParams.queryTime1":stime,
					"queryParams.queryTime2":etime
				  },
				success : function(response) {
					mask.hide();
					mask1.hide();
				    var text = response.responseText;
					var obj = eval( "(" + text + ")" );//转换后的JSON对象
					var list = obj.result.list;
					var store = Ext.data.StoreManager.get("CarTrackListStore");
					store.loadData(list);
					store.sort('createtime', 'ASC');
					
					map.clearOverlays(); //清除上次标记点
					pointHandler(response);
					
					alarmflag = true;
					alarmriflag = true;
					
				},
				failure : function() {
					Ext.Msg.alert('提示',"查询轨迹信息失败");
				}
			});
		}else{
			var carnumber = Ext.getCmp('track_carnumber').getRawValue();
			if (carnumber == null || carnumber.length != 7){
				Ext.Msg.alert('提示',"请选择车牌号码");
				return;
			}
			
			var stime = Ext.util.Format.date(Ext.getCmp('ct_stime').getValue(), 'Y-m-d H:i:s');
		    var etime = Ext.util.Format.date(Ext.getCmp('ct_etime').getValue(), 'Y-m-d H:i:s');
			
			if (stime == null || stime == ""){
				Ext.Msg.alert('提示',"请输入开始时间");
				return;
			}
			if (etime == null || etime == ""){
				Ext.Msg.alert('提示',"请输入结束时间");
				return;
			}
			
		    var beginTimes = stime.substring(0,10).split('-');
	        var endTimes = etime.substring(0,10).split('-');
	
	        var stimearray = stime.substring(10,19).split(':');
	        var etimearray = etime.substring(10,19).split(':');
	        var dt = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
	        var dt1 = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
	        var now = new Date();
	        
	        if(dt1 < dt){
	        	Ext.Msg.alert("提示","开始时间必须小于终止时间");
	        	return;
	        }
	        if(dt1 - now > 0){
	    		Ext.Msg.alert("提示","截止时间不能大于当前时间");
	        	return;
	    	}
	    	if(now - dt > 15552000000){
	    		Ext.Msg.alert("提示","只能查询6个月以内的数据");
	        	return;
	    	}
	    	
	    	var mask=new Ext.LoadMask('trackMap',{msg:"数据加载......"});
		    mask.show(stime+","+etime);
		    
	    	Ext.getCmp("trackbottom").expand();
	    	
	    	//查询之后先暂停播放上次轨迹
	    	if(timer) {
				window.clearTimeout(timer);
				Ext.getCmp('slider').setValue(0);
			}
			
	    	var mask1=new Ext.LoadMask('carTrackListView',{msg:"数据加载......"});
		    mask1.show();
		    
	    	Ext.Ajax.request( {
				url :window.BIZCTX_PATH +"/carjson/findNotStopCarTrackPointList.action", // 请求路径
				method : 'POST',  
				timeout : 120000,
				params : {
					"queryParams.carnumber":encodeURI(carnumber),
					"queryParams.queryTime1":stime,
					"queryParams.queryTime2":etime
				  },
				success : function(response) {
					mask.hide();
					mask1.hide();
				    var text = response.responseText;
					var obj = eval( "(" + text + ")" );//转换后的JSON对象
					var list = obj.result.list;
					var store = Ext.data.StoreManager.get("CarTrackListStore");
					store.loadData(list);
					store.sort('createtime', 'ASC');
					
					mapTrack.clearMap(); //清除上次标记点
					pointHandler(response);
					
					alarmflag = true;
					alarmriflag = true;
					
				},
				failure : function() {
					Ext.Msg.alert('提示',"查询轨迹信息失败");
				}
			});
		}
		
    	
		
	},
	exportTrack : function (button){
		var carstatus = Ext.getCmp('ct_status').getValue();
		
        var carnumber = Ext.getCmp('track_carnumber').getValue();
		if (carnumber == null || carnumber == ""){
			Ext.Msg.alert('提示',"请选择车牌号码");
			return;
		}
		
		var stime = Ext.util.Format.date(Ext.getCmp('ct_stime').getValue(), 'Y-m-d H:i:s');
	    var etime = Ext.util.Format.date(Ext.getCmp('ct_etime').getValue(), 'Y-m-d H:i:s');
		
		if (stime == null || stime == ""){
			Ext.Msg.alert('提示',"请输入开始时间");
			return;
		}
		if (etime == null || etime == ""){
			Ext.Msg.alert('提示',"请输入结束时间");
			return;
		}
		
	    var beginTimes = stime.substring(0,10).split('-');
        var endTimes = etime.substring(0,10).split('-');

        var stimearray = stime.substring(10,19).split(':');
        var etimearray = etime.substring(10,19).split(':');
        var dt = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
        var dt1 = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
        var now = new Date();
        
        if(dt1 < dt){
        	Ext.Msg.alert("提示","开始时间必须小于终止时间");
        	return;
        }
        if(dt1 - now > 0){
    		Ext.Msg.alert("提示","截止时间不能大于当前时间");
        	return;
    	}
    	if(now - dt > 15552000000){
    		Ext.Msg.alert("提示","只能查询6个月以内的数据");
        	return;
    	}
	    
	    location.href= window.BIZCTX_PATH + '/carjson/exportTrackExcel.action?queryParams.carnumber='+carnumber
        	+'&queryParams.queryTime1='+stime+'&queryParams.queryTime2='+etime+'&carstatus='+carstatus;
	},
	statussearch : function (button){
		var status = Ext.getCmp('ct_status').getValue();
		var store = Ext.data.StoreManager.get("CarTrackListStore");
		store.clearFilter(true);
		if(status == null || status == ""){
			store.filter([
			    {filterFn: function(item) { return true; }}
			]);
		}else{
			store.filter([
			    {property: "carstatus", value: status}
			]);
		}
		return;
	},
	queryCarTrack : function(){
	},
	playCarTrack : function(){
		play();	
	},
	pauseCarTrack : function(){
		pause();	
	},
	resetCarTrack : function(){
		reset();	
	}
	
});


/**
 * 获取URL参数
 * @param {} param
 * @return {}
 */
function getUrlParam(param) {
    var params = Ext.urlDecode(location.search.substring(1));
    return param ? params[param] : params;
} 

function diffTime(stime, etime) {
	
	var beginTimes = stime.substring(0,10).split('-');
    var endTimes = etime.substring(0,10).split('-');

    var stimearray = stime.substring(10,19).split(':');
    var etimearray = etime.substring(10,19).split(':');
    var startDate = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
    var endDate = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
    
	var diff = endDate.getTime() - startDate.getTime();
	// 时间差的毫秒数//计算出相差天数var
	days = Math.floor(diff / (24 * 3600 * 1000));// 计算出小时数var
	leave1 = diff % (24 * 3600 * 1000);
	// 计算天数后剩余的毫秒数var
	hours = Math.floor(leave1 / (3600 * 1000));// 计算相差分钟数var
	leave2 = leave1 % (3600 * 1000);
	// 计算小时数后剩余的毫秒数var
	minutes = Math.floor(leave2 / (60 * 1000));// 计算相差秒数var
	leave3 = leave2 % (60 * 1000);
	// 计算分钟数后剩余的毫秒数var
	seconds = Math.round(leave3 / 1000);
	var returnStr = seconds + "秒";
	if (minutes > 0) {
		returnStr = minutes + "分" + returnStr;
	}
	if (hours > 0) {
		returnStr = hours + "小时" + returnStr;
	}
	if (days > 0) {
		returnStr = days + "天" + returnStr;
	}
	return returnStr;
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}





