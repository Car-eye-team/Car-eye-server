
var mapObj;
var marker;
var markermap = new Map();
var flag=1; //1 出发地  2 目的地
var city;

Ext.define('RemoteControlApp.controller.RemoteControlCtrl', {
			extend : 'Ext.app.Controller',
			stores : ['CarListStore', 'CarInfoListStore', 'ParmTypeTreeStore','PioPlaceStore'],
			models : ['CarInfoModel', 'CarInfoModel','PioPlaceModel'],// 声明该控制层要用到的model
			views : ['TypeListView', 'ParameterSetView', 'CarInfoListView'
					,'CenterListView','MapView','MapWindowView','SearchMapView'],
			refs : [// 相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
			{
						ref : 'typeListView',
						selector : 'typeListView'
					}, {
						ref : 'parameterSetView',
						selector : 'parameterSetView'
					}, {
						ref : 'carInfoListView',
						selector : 'carInfoListView'
					}],
			init : function() {
				this.control({
							'carInfoListView button[action=searchcar]' : {
								click : this.searchCarInfo
							},
							'parameterSetView button[action=paramset]' : {
								click : this.paramset
							},
							'parameterSetView button[action=sselmap]' : {
								click : this.saddress
							},
							'parameterSetView button[action=eselmap]' : {
								click : this.eaddress
							},
							'searchMapView': {
				                afterrender:function(component, eOpts){
				                    //初始化地图
				                    render: this.initMap(component.el.dom);
				                }
				            },
				            'mapView button[action=searchtext]' : {
								//click : this.searchtext
								click : this.searchPOI
							}
						});
			},
			searchCarInfo : function(button) {
				var carnumber = Ext.getCmp('c_carnumber').getRawValue();
				var blocid = Ext.getCmp('c_blocid').getValue();
				if (carnumber.length == 0 && typeof blocid == "undefined") {
					Ext.Msg.alert('提示', '请选择企业或者车辆!');
					return;
				}
				var store = this.getCarInfoListStoreStore();
				store.clearFilter(true);
				store.on('beforeload', function(store, options) {
							var new_params = {
								carnumber : encodeURI(carnumber),
								blocid : blocid
							};
							Ext.apply(store.proxy.extraParams, new_params);
						});
				store.loadPage(1);
				return false;
			},
			paramset : function(button) {
				var parmsvalue = "";
				var id = "";
				var type = "";
				var typename = "";

				var idstr = button.id.substring(2);
				if (idstr == "xh") {
					typename = "熄火";
					Ext.Msg.alert('提示', '暂不支持熄火控制!');
					return false;
				}
				if (idstr == "dy") {
					typename = "断油";
					Ext.Msg.alert('提示', '暂不支持断油控制!');
					return false;
				}
				if (idstr == "dd") {
					typename = "断电";
					Ext.Msg.alert('提示', '暂不支持断电控制!');
					return false;
				}
				if (idstr == "wbxx") {
					typename = "文本信息";
					Ext.Msg.alert('提示', '暂不支持文本信息控制!');
					return false;
				}

				var style = "";
				var color = "";
				var content = "";
				var delayed = "";
				var time = "";
				if (idstr == "fsfwm") {
					typename = "发送防伪码";
					type = 4;
					style = Ext.getCmp('style').getValue();
					color = Ext.getCmp('color').getValue();
					content = Ext.getCmp('content').getValue();
					delayed = Ext.getCmp('delayed').getValue();
					time = Ext.getCmp('time').getValue();

					if (!Ext.getCmp('content').isValid()) {
						return;
					}
					if (!Ext.getCmp('delayed').isValid()) {
						return;
					}
					if (!Ext.getCmp('time').isValid()) {
						return;
					}
				}
				var answermode = "";
				var ordertype = "";
				var phone = "";
				var answerphone = "";
				var contents = "";
				var saddress = "";
				var eaddress = "";
				var slat = "";
				var slng = "";
				var elat = "";
				var elng = "";
				if (idstr == "fsdz") {
					typename = "发送电召";
					type = 5;
					passengername = Ext.getCmp('zb_passengername').getValue();
					answerphone = Ext.getCmp('zb_phone').getValue();
					answermode = Ext.getCmp('answermode').getValue();
					ordertime = Ext.util.Format.date(Ext.getCmp('ordertime').getValue(), 'Y-m-d H:i:s');
					ordertype = Ext.getCmp('ordertype').getValue();
					contents = Ext.getCmp('contents').getValue();
					saddress = Ext.getCmp('saddress').getValue();
					eaddress = Ext.getCmp('eaddress').getValue();
					slng = Ext.getCmp('slng').getValue();
					slat = Ext.getCmp('slat').getValue();
					elat = Ext.getCmp('elat').getValue();
					elng = Ext.getCmp('elng').getValue();
					if (!Ext.getCmp('zb_passengername').isValid()) {
						return;
					}
					if (!Ext.getCmp('zb_phone').isValid()) {
						return;
					}
					if (!Ext.getCmp('ordertime').isValid()) {
						return;
					}
					if (!Ext.getCmp('saddress').isValid()) {
						return;
					}
					if (!Ext.getCmp('eaddress').isValid()) {
						return;
					}
					
				}
				var localphone = "";
				var callfee = "";
				var orderid = "";
				if (idstr == "zbfs") {
					typename = "中标发送";
					type = 6;
					passengername = Ext.getCmp('passengername').getValue();
					phone = Ext.getCmp('passengerphone').getValue();
					ordertime = Ext.util.Format.date(Ext.getCmp('zbfs_ordertime').getValue(), 'Y-m-d H:i:s');
					contents = Ext.getCmp('zb_contents').getValue();
					localphone = Ext.getCmp('localphone').getValue();
					callfee = Ext.getCmp('callfee').getValue();
					orderid = Ext.getCmp('orderid').getValue();

					if (!Ext.getCmp('passengername').isValid()) {
						return;
					}
					if (!Ext.getCmp('passengerphone').isValid()) {
						return;
					}
					if (!Ext.getCmp('callfee').isValid()) {
						return;
					}
					if (!Ext.getCmp('localphone').isValid()) {
						return;
					}
					if (!Ext.getCmp('orderid').isValid()) {
						return;
					}
				}
				if (idstr == "zdgj") {
					id = "3";
					type = 1;
					typename = "终端关机";
				}
				if (idstr == "zdfw") {
					id = "4";
					type = 1;
					typename = "终端复位";
				}
				if (idstr == "zdhfccsz") {
					id = "5";
					type = 1;
					typename = "终端恢复出厂设置";
				}
				if (idstr == "gbsjtx") {
					id = "6";
					type = 1;
					typename = "关闭数据通信";
				}
				if (idstr == "gbsywxtx") {
					id = "7";
					type = 1;
					typename = "关闭所有无线通信";
				}
				if (idstr == "sc") {
					parmsvalue = Ext.getCmp('p_sc').getValue();
					type = 2;
					typename = "锁车";
				}
				if (idstr == "rdkz") {
					parmsvalue = Ext.getCmp('p_rdkz').getValue();
					type = 3;
					typename = "WIFI热点控制";
				}

				var cargrid = Ext.getCmp('carinfogrid');
				var cardata = cargrid.getSelectionModel().getSelection();
				if (cardata.length == 0) {
					Ext.Msg.alert('提示', '请选择需要参数的车辆!');
					return false;
				}

				var ca = cargrid.getSelectionModel().getSelection();
				var carids = "";
				for (var i = 0, len = ca.length; i < len; i++) {
					var ss = ca[i].get("carid");
					if (i == 0) {
						carids = carids + ss;
					} else {
						carids = carids + "," + ss;
					}
				}

				var myMask = new Ext.LoadMask(Ext.getBody(), {
							msg : "正在下发远程控制指令，请稍后..."
						});
				myMask.show();

				Ext.Ajax.request({
							url : window.BIZCTX_PATH
									+ '/terminalpositionjson/terminalControl.action',
							method : 'POST',
							timeout : 5000,
							params : {
								carids : carids,
								id : id,
								type : type,
								typename : encodeURI(typename),
								size : parmsvalue,
								style : style,
								color : color,
								content : content,
								delayed : delayed,
								time : time,
								passengername : passengername,
								phone : phone,
								ordertype : ordertype,
								answerphone : answerphone,
								answermode : answermode,
								ordertime : ordertime,
								saddress : saddress,
								eaddress : eaddress,
								slng : slng,
								slat : slat,
								elat : elat,
								elng : elng,
								contents : contents,
								localphone : localphone,
								callfee : callfee,
								orderid : orderid
							},
							success : function(response) {
								myMask.hide();
								Ext.Msg.alert('提示', "远程控制指令下发成功!");
							},
							failure : function() {
								myMask.hide();
								Ext.Msg.alert('提示', "远程控制指令下发失败!");
							}
						});
			},
			saddress : function() {
				var view = Ext.widget('mapWindowView');
				view.show();
				Ext.getCmp('flag').setValue("1");
				return false;
			},
			eaddress : function() {
				var view = Ext.widget('mapWindowView');
				view.show();
				Ext.getCmp('flag').setValue("2");
				return false;
			},
			initMap : function(div) {
				markermap.clear();
				mapObj = new AMap.Map("searchMapView", {
					resizeEnable : true,
					rotateEnable : true,
					dragEnable : true,
					zoomEnable : true,
					zooms : [3, 18],
					// 二维地图显示视口
					view : new AMap.View2D({
						center : new AMap.LngLat(116.397428, 39.90923),// 地图中心点
						zoom : 13
					})
				});
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
			},
//			searchtext:function(){
//		    	var keyword = Ext.getCmp('addr_search').getValue();
//				if (keyword == null || keyword == ""){
//					Ext.Msg.alert('提示',"请输入要搜索的关键字");
//					return;
//				}
//				getlocation(keyword);
//		    },
		   searchPOI:function(button){
				var keyword = Ext.getCmp('addr_search').getValue();
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
			}
		});
		
		
function getlocation(keyword){
	   var cityinfo="";
		//加载城市查询插件
		AMap.service(["AMap.CitySearch"], function() {
				//实例化城市查询类
				var citysearch = new AMap.CitySearch();
				//自动获取用户IP，返回当前城市
				citysearch.getLocalCity(function(status, result){
					if(status === 'complete' && result.info === 'OK'){
						if(result && result.city && result.bounds) {
							cityinfo = result.city;
							var citybounds = result.bounds;
							
		var store = Ext.StoreManager.get('PioPlaceStore');
		// 显示
	    var myMask = new Ext.LoadMask(Ext.getBody(), {//也可以是Ext.getCmp('').getEl()窗口名称
            msg    : "正在操作,请稍后...",//你要写成Loading...也可以
            msgCls : 'z-index:10000;'
        }); 
        myMask.show();
        
	     Ext.getCmp('centerlistview').expand();
			store.on('beforeload', function (store, options) {
			            var new_params = { 
				            query: encodeURI(keyword),
				            city: encodeURI(cityinfo)
			            };
			            Ext.apply(store.proxy.extraParams, new_params);
			        });
			     store.load();
       			myMask.hide();
			}
					}
				});
			});
}



function addMarker(lng, lat,pioname) {
	map.clearOverlays();
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
    	var flag = Ext.getCmp('flag').getValue();
    	if (flag == 1) {	//出发地
			Ext.getCmp('saddress').setValue(pioname);
			Ext.getCmp('slng').setValue(lng);
			Ext.getCmp('slat').setValue(lat);
		} else if (flag == 2) {	//目的地
			Ext.getCmp('eaddress').setValue(pioname);
			Ext.getCmp('elng').setValue(lng);
			Ext.getCmp('elat').setValue(lat);
		}
	Ext.getCmp('mapWindowView').close();
    });
    mapObj.setFitView();
}
