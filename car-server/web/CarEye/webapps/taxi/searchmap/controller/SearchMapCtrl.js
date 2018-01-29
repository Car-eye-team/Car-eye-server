var mapObj;
var marker;
var clicktime = 0;
var juedgeflag = 1;
var sm_s_lng = null;
var sm_s_lat = null;
var sm_e_lng = null;
var sm_e_lat = null;
var city;//当前所在城市
var caridsStr=null;
var titleStr=null;
var latStr=null;
var lngStr=null;


Ext.define('SearchMapApp.controller.SearchMapCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarListStore','CarInfoListStore','PoiInfoRecordListStore','PioPlaceStore'],//声明该控制层要用到的store
    models: ['CarInfoModel','PoiInfoModel', 'PioPlaceModel'],//声明该控制层要用到的model
    views: ['AddrSearchView','CarInfoListView','SearchMapView','CenterListView','PoiInfoRecordListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'addrSearchView',
            selector: 'addrSearchView'
        },{
            ref: 'carInfoListView',
            selector: 'carInfoListView'
        },{
            ref: 'searchMapView',
            selector: 'searchMapView'
        },{
            ref: 'searchMapListView',
            selector: 'searchMapListView'
        },{
            ref: 'poiInfoRecordListView',
            selector: 'poiInfoRecordListView'
        }
    ],
    init: function() {
    	
		this.control({
			'searchMapView': {
                afterrender:function(component, eOpts){
                    //初始化地图
                    render: this.initMap(component.el.dom);
                }
            },
			'carInfoListView button[action=searchcar]' : {
				click : this.searchCar
			},
			'addrSearchView button[action=searchpoi]' : {
//				click : this.searchpoi//百度
				click : this.searchPOI//高德
			},
			'addrSearchView button[action=poisendrecord]' : {
				click : this.poisendrecord
			},
			'poiInfoRecordListView button[action=searchrecord]' : {
				click : this.searchrecord
			},
			'poiInfoRecordListView button[action=delete]' : {
				click : this.deleteRecord
			}
			
		});
	},
	 /*
        初始化地图
    */
    initMap:function(div){
    	if(maptype == 1){//baidu
    		searchmap = new BMap.Map(div);
	        //默认位置
	        var point = new BMap.Point(116.3972,39.9096);
	        searchmap.centerAndZoom(point, 12);
	        /*
	           	 获取当前位置
	        */
	        var localCity = new BMap.LocalCity();
	        localCity.get(function(result){
	            var cityName = result.name;
	            var localPoint = new BMap.Point(result.center.lng, result.center.lat);
	            searchmap.setCenter(localPoint,cityName);
	        });
	 
	        searchmap.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
	        searchmap.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	        searchmap.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
	        searchmap.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
	        searchmap.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件
	        searchmap.addControl(new BMap.MapTypeControl());          //添加地图类型控件
	 		// 显示地图中心地点的坐标
			searchmap.addEventListener("click",
									function(e) {
										if (e.overlay) {
											var center = e.point;
											top.Ext.MessageBox.confirm(
															'消息',
															'确认下发POI信息点?',
															function(btn) {
																if (btn == 'yes') {
	
																	var cargrid = Ext.getCmp('mapcarinfogrid');
																	var cardata = cargrid.getSelectionModel().getSelection();
																	if(cardata.length ==0){
																		Ext.Msg.alert('提示', '请选择需要发送POI信息车辆!');
																		return false;
																	}
																	
																	var ca = cargrid.getSelectionModel().getSelection();
																	var carids = "";
																	for ( var i = 0, len = ca.length; i < len; i++) {
																		var ss = ca[i].get("carid");
																		if (i == 0) {
																			carids = carids + ss;
																		} else {
																			carids = carids + "," + ss;
																		}
																	}
	
																	var myMask = new Ext.LoadMask(Ext.getBody(),
																			{
																				msg : "正在等待下发信息点，请稍后..."
																			});
																	myMask.show();
	
																	Ext.Ajax.request( {
																				method : 'POST',
																				url : window.BIZCTX_PATH + '/poiinfojson/sendPoiInfo.action',
																				params : {
																					'poiInfo.lng' : center.lng,
																					'poiInfo.lat' : center.lat,
																					'carids' : carids,
																					'poiInfo.poiname' : e.overlay.getTitle()
																				},
																				success : function(result) {
																					myMask.hide();// 隐藏
																					top.Ext.MessageBox.alert('提示','下发POI信息点成功');
																				},
																				failure : function(result) {
																					myMask.hide();// 隐藏
																					top.Ext.MessageBox.alert('提示','下发POI信息点失败');
																				}
																			});
																}
															});
										}
									});
    	}else{
    		//gaode   高德
    		//清空map中marker对象
			mapObj = new AMap.Map(div, {
						resizeEnable : true,
						rotateEnable : true,
						dragEnable : true,
						zoomEnable : true,
						zooms : [3, 18],
						//二维地图显示视口
						view : new AMap.View2D({
							center : new AMap.LngLat(116.397428, 39.90923),//地图中心点
							zoom : 13
								//地图显示的缩放级别
							})
					});
			//添加地图类型切换插件
			mapObj.plugin(["AMap.MapType"], function() {
						//地图类型切换
						var mapType = new AMap.MapType({
							defaultType : 0,//默认显示卫星图
							showRoad : true
								//叠加路网图层
							});
						mapObj.addControl(mapType);
					});
			mapObj.plugin(["AMap.ToolBar"], function() {
						//加载工具条
						var tool = new AMap.ToolBar();
						mapObj.addControl(tool);
					});
					
			AMap.service(["AMap.CitySearch"], function() {
						//实例化城市查询类
						var citysearch = new AMap.CitySearch();
						//自动获取用户IP，返回当前城市
						citysearch.getLocalCity(function(status, result) {
									if (status === 'complete' && result.info === 'OK') {
										if (result && result.city && result.bounds) {
											city = result.city;
											if(city.indexOf('市') > 0){
												city = result.city.replace('市','');
											}
											var citybounds = result.bounds;
											//地图显示当前城市
											mapObj.setBounds(citybounds);
					                        mapObj.setZoom(10);
										}
									}
								});
			});
					
			 //获取用户所在城市信息
	        function showCityInfo() { 
	            //加载城市查询插件
	            AMap.service(["AMap.CitySearch"], function() {
	                //实例化城市查询类
	                var citysearch = new AMap.CitySearch();
	                //自动获取用户IP，返回当前城市
	                citysearch.getLocalCity(function(status, result){
	                    if(status === 'complete' && result.info === 'OK'){
	                        if(result && result.city && result.bounds) {
	                            var cityinfo = result.city;
	                            var citybounds = result.bounds;
	                            //地图显示当前城市
	                            mapObj.setBounds(citybounds);
	                            mapObj.setZoom(10);
	                        }
	                    }else{
	                    }
	                });
	            });
	        }
	        
	        showCityInfo(); 
	    	}

        
    },
	searchCar : function(button) {
		var store = this.getCarInfoListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('cas_carnumber').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	searchpoi : function(button){
		var keyword = Ext.getCmp('keyword').getValue();
		if (keyword == null || keyword == ""){
			Ext.Msg.alert('提示',"请输入要搜索的关键字");
			return;
		}
		searchmap.clearOverlays();
		
		var myMask = new Ext.LoadMask("poiPanel",{msg : "正在查询,请稍后..."});
		myMask.show();
		// 搜索
		var local = new BMap.LocalSearch(searchmap, {
			renderOptions : {
				map : searchmap,
				panel : 'poiPanel'
			},
			pageCapacity : 4
		});
		local.search(keyword);
		local.getResults();
		local.setSearchCompleteCallback(function(searchResult) {
			myMask.hide();// 隐藏
		});
		
	},
	
	searchPOI:function(button){
		var keyword = Ext.getCmp('keyword').getValue();
		if (keyword == null || keyword == "") {
			Ext.Msg.alert('提示', "请输入要搜索的关键字");
			return;
		}
		var store=Ext.StoreManager.get("PioPlaceStore");
			store.removeAll();
		var arrays=new Array();
		
		//获取用户所在城市信息
		function showCityInfo() { 
			//加载城市查询插件
			AMap.service(["AMap.CitySearch"], function() {
				//实例化城市查询类
				var citysearch = new AMap.CitySearch();
				//自动获取用户IP，返回当前城市
				citysearch.getLocalCity(function(status, result){
					if(status === 'complete' && result.info === 'OK'){
						if(result && result.city && result.bounds) {
							city = result.city;
						}
					}else{
						city=result.info ;
					}
				});
			});
		}
		showCityInfo();
		AMap.service(["AMap.PlaceSearch"], function() {       
	        MSearch = new AMap.PlaceSearch({ //构造地点查询类
	            pageSize:poipagesize,
	            pageIndex:1,
	            city:city //城市
	        });
	        //关键字查询
	        MSearch.search(keyword, function(status, result){
	        	if(status === 'complete' && result.info === 'OK'){
	        		var poiList= result.poiList.pois;
	        		
	        		for(var i=0;i<poiList.length;i++){
        			   var poi=poiList[i];
        			   var location=poi.location.lng+","+poi.location.lat;
						
					   var obj=new Object();
					   obj.name=poi.name;
					   obj.address=poi.address;
					   obj.lnglat=location;
					   obj.telephone=poi.tel;
					   arrays[i]=obj;
	        		}
	        	}else if(status === 'no_data'){
	        	}
	        	 store.addSorted(arrays);  
	        }); 
	        
	    });
	    
	},
	
	poisendrecord : function(button){
		var grid = button.up('grid');
		var view = Ext.widget('poiInfoRecordListView');
		view.show();
		var store = this.getPoiInfoRecordListStoreStore();
		store.clearFilter(true);
	    store.load({ params: { start: 0, limit: 15} });
		return false;
	},
	searchrecord : function(button){
		
		var stime = Ext.util.Format.date(Ext.getCmp('record_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('record_etime').getValue(), 'Y-m-d H:i:s');
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
        
		var store = this.getPoiInfoRecordListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('record_carnumber').getValue()),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('record_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('record_etime').getValue(), 'Y-m-d H:i:s'))
	            	
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	deleteRecord : function(button) {
		var grid = Ext.getCmp('prt');
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
										+ '/poiinfojson/deleteRecord.action',
								method : 'POST',
								params : "ids=" + jsonData,
								success : function(response, opts) {
									var text = response.responseText;
									var su = Ext.JSON.decode(text).result.su;
									if (su == 0) {
										Ext.Msg.alert('提示', "删除成功！");
										store.reload();
									} else{
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

	}
});

function addMarker(lng, lat,pioname,flag) {
 	if(flag==-1){
	mapObj.clearMap();
	marker = new AMap.Marker({                   
	   map:mapObj,  
	   title:pioname,
	   position:new AMap.LngLat(lng,lat), //位置
	   icon:"http://webapi.amap.com/images/marker_sprite.png"
    });
			    //自定义点标记内容  
			    var markerContent = document.createElement("div");
			    markerContent.className = "markerContentStyle";
			    //点标记中的图标
			    var markerImg= document.createElement("img");
			    markerImg.className="markerlnglat";
			    markerImg.src="http://webapi.amap.com/images/marker_sprite.png";   
			    markerContent.appendChild(markerImg);
			      
			    //点标记中的文本
			    var markerSpan = document.createElement("span");
			    markerSpan.style.width=100;
			    markerSpan.style.height=20;
			    markerSpan.innerHTML = pioname;
			    
			    markerContent.appendChild(markerSpan);
			    marker.setContent(markerContent);//更新点标记内容
    AMap.event.addListener(marker, 'click', function(e) {
			top.Ext.MessageBox.confirm('消息', '确认下发POI信息点?', function(btn) {
						if (btn == 'yes') {
							caridsStr = [];
								var cargrid = Ext.getCmp('mapcarinfogrid');
								var cardata = cargrid.getSelectionModel().getSelection();
								if (cardata.length == 0) {
									Ext.Msg.alert('提示', '请选择需要发送POI信息车辆!');
									return false;
								}
	
								var ca = cargrid.getSelectionModel().getSelection();
								for (var i = 0, len = ca.length; i < len; i++) {
									var ss = ca[i].get("carid");
									if (i == 0) {
										caridsStr = caridsStr + ss;
									} else {
										caridsStr = caridsStr + "," + ss;
									}
								}
								titleStr=pioname;
								latStr=lat;
								lngStr= lng;
							
							var myMask = new Ext.LoadMask(Ext.getBody(), {
										msg : "正在等待下发信息点，请稍后..."
							});
							myMask.show();
							
							Ext.Ajax.request({
										method : 'POST',
										url : window.BIZCTX_PATH + '/poiinfojson/sendPoiInfo.action',
										params : {
											'poiInfo.lng': lngStr,
											'poiInfo.lat' : latStr,
											'poiInfo.poiname' :titleStr ,
											'carids' : caridsStr
										},
										success : function(result) {
											myMask.hide();// 隐藏
											top.Ext.MessageBox.alert('提示','下发POI信息点成功');
										},
										failure : function(result) {
											myMask.hide();// 隐藏
											top.Ext.MessageBox.alert('提示','下发POI信息点失败');
										}
									});
						}
					});
		});
 	}else if(flag==2){
 			var myMask = new Ext.LoadMask(Ext.getBody(), {
										msg : "正在等待下发信息点，请稍后..."
							});
							myMask.show();
							
							Ext.Ajax.request({
										method : 'POST',
										url : window.BIZCTX_PATH + '/poiinfojson/sendPoiInfo.action',
										params : {
											'poiInfo.lng': lngStr,
											'poiInfo.lat' : latStr,
											'poiInfo.poiname' :titleStr ,
											'carids' : caridsStr
										},
										success : function(result) {
											myMask.hide();// 隐藏
											top.Ext.MessageBox.alert('提示','下发POI信息点成功');
											var store =  Ext.StoreManager.get('PoiInfoRecordListStore');
											store.load();
											
										},
										failure : function(result) {
											myMask.hide();// 隐藏
											top.Ext.MessageBox.alert('提示','下发POI信息点失败');
										}
			});
 	}
    mapObj.setFitView();
}


