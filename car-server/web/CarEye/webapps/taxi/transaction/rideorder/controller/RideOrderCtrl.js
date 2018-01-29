var map;

var mapObj;//高德地图对象

Ext.define('RideOrderApp.controller.RideOrderCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['RideOrderListStore','RidePassengerListStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['RideOrderListModel','RidePassengerListModel'],//声明该控制层要用到的model
    views: ['RideOrderListView','RidePassengerListView','TrackMapView','OPWindow','OrderDetailWindow'],//声明该控制层要用到的view
    
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
           {
            	ref: 'passengerTripWindow',
               selector: 'passengerTripWindow'
           }
       ],
    
    init: function() {
		this.control({
			'trackMapView': {
			            afterrender:function(component, eOpts){
			                //初始化地图
			                render: this.initMap(component.el.dom);
			            }
			 },
			'rideOrderListView button[action=search]' : {
				click : this.searchRideOrder
			},
			'rideOrderListView button[action=export]' : {
				click : this.exportRideOrder
			},
			'rideOrderListView button[action=delete]' : {
				click : this.deleteRideOrder
			},
			'ridePassengerListView button[action=search]' : {
				click : this.searchRidePassenger
			},
			'ridePassengerListView button[action=delete]' : {
				click : this.deleteRidePassenger
			},
			'rideOrderListView #blocid' : {
				select : this.loadDeptCar
			},
			'rideOrderListView':{
                render : this.buttonAccess
            },
            'ridePassengerListView':{
                render : this.button1Access
            }
		
		});
	},
	
	loadDeptCar : function(){
		Ext.getCmp('z_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var deptid = Ext.getCmp('c_deptid').getValue();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	deptid: deptid,
            	carnumber: ""
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	
	buttonAccess : function() {
		var buttonArray = ['160601','160602'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	button1Access : function() {
		var buttonArray = ['160603'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	
	
		 /*
	    初始化地图
	*/
	initMap:function(div){
//		maptype = 1;
		if(maptype==1){
			this.initBaiDuMap(div);
		}else{
			this.initGaoDeMap(div);
		}
	},
	//高德地图
	initGaoDeMap : function(div) {
		
		
	    mapObj = new AMap.Map(div,{
	        rotateEnable:true,
	        dragEnable:true,
	        zoomEnable:true,
	        zooms:[3,18],
	        //二维地图显示视口
	        view: new AMap.View2D({
	            center:new AMap.LngLat(113.897276,22.573035),//地图中心点
	            zoom:13 //地图显示的缩放级别
	        })
	    });
	    
	    //加载比例尺插件
	    mapObj.plugin(["AMap.Scale"],function(){       
	        scale = new AMap.Scale();
	        mapObj.addControl(scale);
	    });
	    
	    //在地图中添加ToolBar插件
	    mapObj.plugin(["AMap.ToolBar"],function(){     
	        toolBar = new AMap.ToolBar();
	        mapObj.addControl(toolBar);    
	    });
	    
	    //添加地图类型切换插件
	    mapObj.plugin(["AMap.MapType"],function(){
	        //地图类型切换
	        var mapType= new AMap.MapType({
	            showRoad:true //叠加路网图层
	        });
	        mapObj.addControl(mapType);
	    }); 
		    
	},
	
	
	//baoidu
	initBaiDuMap : function(div) {
		 map = new BMap.Map(div);
	    //默认位置
	    var point = new BMap.Point(116.3972,39.9096);
	    map.centerAndZoom(point, 12);
	    
	    map.enableAutoResize();//解决第二次打开地图时异常问题
	
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
	},
	//搜索顺风车订单
	searchRideOrder : function(button) {
		var store = this.getRideOrderListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
					var new_params = {
							blocid : Ext.getCmp('c_deptid').getValue(),
							carnumber : encodeURI(Ext.getCmp('z_carnumber').getValue()),
							terminal : encodeURI(Ext.getCmp('z_termianl').getValue()),
							s_time : Ext.util.Format.date(Ext.getCmp('z_stime').getValue(),'Y-m-d H:i:s'),
							e_time : Ext.util.Format.date(Ext.getCmp('z_etime').getValue(),'Y-m-d H:i:s')
					};
					Ext.apply(store.proxy.extraParams, new_params);
				});
		store.loadPage(1);
		return false;
	},
	
	//导出顺风车订单
	exportRideOrder : function (button){
    	var	blocid = Ext.getCmp('c_deptid').getValue();
    	var	carnumber = Ext.getCmp('z_carnumber').getValue();
    	var	terminal = Ext.getCmp('z_termianl').getValue();
        var s_time = Ext.util.Format.date(Ext.getCmp('z_stime').getValue(), 'Y-m-d H:i:s');
        var e_time = Ext.util.Format.date(Ext.getCmp('z_etime').getValue(), 'Y-m-d H:i:s');
	    
    	location.href= window.BIZCTX_PATH + '/rideorderjson/exportExcel.action?blocid='+blocid
    		+'&carnumber='+carnumber+'&terminal='+terminal+'&s_time='+s_time+'&e_time='+e_time;
	},
	//删除顺风车订单
	deleteRideOrder : function(button) {
		var grid = Ext.getCmp('rideorder');
		var records = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (records.length > 0) {
			Ext.Msg.buttonText.yes='确定';
			Ext.Msg.buttonText.no="取消";
			Ext.Msg.confirm('提示', '你确认要删除选中的数据?', function(btn) {
				if (btn == 'yes') {
					var m = records;
					var jsonData = "";
					for (var i = 0; i < m.length; i++) {
						var ss = m[i].get('orderid');
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					Ext.Ajax.request({
								url : window.BIZCTX_PATH
										+ '/rideorderjson/deleteRideOrder.action',
								method : 'POST',
								params : "ids=" + jsonData,
								success : function(response, opts) {
									var text = response.responseText;
									var su = Ext.JSON.decode(text).result.su;
									if (su == 0) {
										Ext.Msg.alert('提示', "删除成功！");
										store.reload();
									}else{
										Ext.Msg.alert('提示', "删除失败！");
										store.reload();
									}
								},
								failure : function() {
									Ext.Msg.alert('提示', "服务器异常，稍后再试！");
									store.reload();
								}

							})

				}

			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的数据项!');
		};

	},
	
	
	//搜索乘客
	searchRidePassenger : function(button) {
		var store = this.getRidePassengerListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
					var new_params = {
							s_time : Ext.util.Format.date(Ext.getCmp('s_time').getValue(),'Y-m-d H:i:s'),
							e_time : Ext.util.Format.date(Ext.getCmp('e_time').getValue(),'Y-m-d H:i:s')
					};
					Ext.apply(store.proxy.extraParams, new_params);
				});
		store.loadPage(1);
		return false;
	},
	//删除乘客
	deleteRidePassenger : function(button) {
		var grid = Ext.getCmp('ridepassenger');
		var records = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (records.length > 0) {
			Ext.Msg.buttonText.yes='确定';
			Ext.Msg.buttonText.no="取消";
			Ext.Msg.confirm('提示', '你确认要删除选中的数据?', function(btn) {
				if (btn == 'yes') {
					var m = records;
					var jsonData = "";
					for (var i = 0; i < m.length; i++) {
						var ss = m[i].get('id');
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					Ext.Ajax.request({
								url : window.BIZCTX_PATH
										+ '/rideorderjson/deleteRidePassenger.action',
								method : 'POST',
								params : "ids=" + jsonData,
								success : function(response, opts) {
									var text = response.responseText;
									var su = Ext.JSON.decode(text).result.su;
									if (su == 0) {
										Ext.Msg.alert('提示', "删除成功！");
										store.reload();
									}else{
										Ext.Msg.alert('提示', "删除失败！");
										store.reload();
									}
								},
								failure : function() {
									Ext.Msg.alert('提示', "服务器异常，稍后再试！");
									store.reload();
								}

							})

				}

			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的数据项!');
		};

	},
	
	//显示乘客坐车轨迹
	mapLookTrip : function(carnumber,stime,etime){
		
		Ext.Ajax.request( {
			url :window.BIZCTX_PATH +"/carjson/findNotStopCarTrackPointList.action", // 请求路径
			method : 'POST',  
			params : {
				"queryParams.carnumber":encodeURI(carnumber),
				"queryParams.queryTime1":stime,
				"queryParams.queryTime2":etime
			  },                                           
			success : function(response) {				
//				map.clearOverlays(); //清除上次标记点
				
				pointHandler(response);//轨迹显示
				
			},
			failure : function() {
				Ext.MessageBox.updateProgress(1);
				Ext.MessageBox.close();
				Ext.Msg.alert('提示',"查询轨迹信息失败");
			}
		});
	}
	
});

