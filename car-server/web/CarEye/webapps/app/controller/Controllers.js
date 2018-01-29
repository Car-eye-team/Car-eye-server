var myMask;

var multiple = false;	//查看多辆车位置信息

var depth = 3; //树展现层数
var depthflag = true;	//是否按层数展现

var markermap = new Map();	//标注车辆
var fullscreenflag = false;  //js控制浏览器全屏，为true 为全屏状态 false 非全屏状态
var map;//弹出框地图
var mapObj;//高德地图对象
var first = true;
var eventListener;//地图监听事件

var points =[];//存放标注查堪人员的对象
var fullscreenflag = false;  //js控制浏览器全屏，为true 为全屏状态 false 非全屏状态
var initload = true;	//是否第一次地图加载完
var bdzoom = 16; //百度地图级别
var bdzoomflag = true; //地图级别控制开关

var collapsed;
var bottomcollapsed;
var hasquery = false;	 //是否点击了左侧查询

if(admin.bottompage == 2){	//底部1 打开 2 关闭
	bottomcollapsed = true;	
}else{	 
    bottomcollapsed = false;
}
if(admin.leftpage == 2){	//左侧1 打开 2 关闭
	collapsed = true;  
}else{	
    collapsed = false;
}

var realtimecarcount = 100; //每次选中车辆推送后台最大数
var realcaridvalue = 1082; //实时车辆信息grid的id

var drawingManager;	//拉框选车-鼠标选择地图
var styleOptions = {
	strokeColor:"red",    //边线颜色。
	fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
	strokeWeight: 2,       //边线的宽度，以像素为单位。
	strokeOpacity: 0.5,	   //边线透明度，取值范围0 - 1。
	fillOpacity: 0.1,      //填充的透明度，取值范围0 - 1。
	strokeStyle: 'solid' //边线的样式，solid或dashed。
}

var workplaceurl = window.BIZCTX_PATH + "/taxi/myworkplace/signin.jsp";

Ext.Loader.setConfig({enabled:true});
Ext.define('FMS.controller.Controllers',{
		extend:'Ext.app.Controller',
		views:[
			'Left',
			'Center',
			'Top',
			'Bottom',
			'Right',
			'CarRealTime',
			'MenuPanel',
			'CarDetailWindow',
			'MapView',
			'ToolbarPanel',
			'SearchCarView',
			'SearchMapView'
		],
		stores:['CarListStore','CarTreeStore','SearchCarListStore','TerminalPositionStore'
		        
		        
		        ],	   
		//查找到组件
		refs:[{
		      	ref:'center',
		      	selector:'center'
			},{
				ref : 'menuPanel',
				selector : 'menuPanel'
			},{
				ref : 'bottom',
				selector : 'bottom'
			},{
				ref : 'carRealTime',
				selector : 'carRealTime'
			}
		],
		init:function(){
			this.control({
				'mapView' : {
					afterrender : function(component, eOpts) {
						// 初始化地图
						render : this.initOneMap();
					}
				},
				'menuPanel button[action=fullscreen]' : {
					click : this.fullscreen
				},
				'toolbarPanel' : {
					afterrender : this.loadinit
				},
				'toolbarPanel button[action=clearmap]' : {
					click : this.clearmap
				},
				'menuPanel button[action=exit]' : {
					click : this.logout
				},
				'menuPanel menuitem' : {
					click : this.addTabPanel
            	},
				'searchMapView': {
                	afterrender:function(component, eOpts){
                    //初始化地图
                   	 render: this.initMaps(component.el.dom);
                	},
                	close : function(pl, eOpts){
                		var con = Ext.create('FMS.controller.Controllers');
                		 con.closeMap();
                	}
            	}
	         });
		},
		loadinit : function(){
			
			//显示报警声音状态
			this.loadVoice();
			
			//加载软电话
			initphone();
			
		    return false;
		},
		loadVoice : function(){
		  var status = admin.warnswitch;
		  if(parseInt(status) == 2){
		       Ext.getCmp('oc_voice').setIconCls('sound_delete');
               Ext.getCmp('oc_voice').value = 2;
		  }else{
		   	   Ext.getCmp('oc_voice').setIconCls('sound');
               Ext.getCmp('oc_voice').value = 1;
		  }
		  return false;
		},
		closeMap : function(){
		   myMask.hide();
		   map.clearInfoWindow();
		   AMap.event.removeListener(eventListener);
		   map.destroy();
		},
		
		//声音开启关闭
		opencloseVoice : function(button){
			var userid = admin.id;
		    var status = Ext.getCmp('oc_voice').value;
			if(parseInt(status) == 1){
				Ext.Ajax.request( {
                                    url : window.BIZCTX_PATH + '/system/userjson/updateUserWarnswitch.action',
                                    method : 'POST',  
                                    timeout : 15000,
                                    params : {userid:userid,status:2},
                                    success : function(response) {
                                        Ext.getCmp('oc_voice').setIconCls('sound_delete');
                                        Ext.getCmp('oc_voice').value = 2;
                                    }
             });
			}else{
				Ext.Ajax.request( {
                                    url : window.BIZCTX_PATH + '/system/userjson/updateUserWarnswitch.action',
                                    method : 'POST',  
                                    timeout : 15000,
                                    params : {userid:userid,status:1},
                                    success : function(response) {
                                        Ext.getCmp('oc_voice').setIconCls('sound');
                                        Ext.getCmp('oc_voice').value = 1;
                                    }
             });
			}
             return false;
			
		},
		
		
		/*
	    初始化地图
	*/
	initMaps:function(div){
		
	    myMask = new Ext.LoadMask(Ext.getCmp('searchCarView').getEl(), {//也可以是Ext.getCmp('').getEl()窗口名称
            msg    : "正在操作,请稍后...",//你要写成Loading...也可以
            msgCls : 'z-index:10000;'
        });  
        myMask.show();
        
		//清空map中marker对象
		markermap.clear();
		map = new AMap.Map("searchMapWin",{
				resizeEnable: true,
		        rotateEnable:true,
		        zooms:[3,18],
		        //二维地图显示视口
		        view: new AMap.View2D({
		            center:new AMap.LngLat(116.397428,39.90923),//地图中心点
		            zoom:13 //地图显示的缩放级别
		        })
		    });
		  //地图加载完后操作
		eventListener = AMap.event.addListener(map,'complete',function(){
				var lng=Ext.getCmp('c_lng').getValue();
				var lat=Ext.getCmp('c_lat').getValue();
				var address=Ext.getCmp('c_cwaaddr').getValue();
				var createtime=Ext.getCmp('c_createtime').getValue();
				var carstatus = Ext.getCmp('c_carstatus').getValue();
				var carnumber =  Ext.getCmp('c_carnumber').getValue();
				
				var icons = getIcon(carstatus);
		     	var point = new AMap.LngLat(lng,lat);
				var marker = new AMap.Marker({                   
				   map:map,   
				   offset:new AMap.Pixel(0, -20),
				   position: point, //位置
				   size :new AMap.Size(30,30),
				   icon: icons
//				   title: "<div>车牌号: " + carnumber+ (address ==undefined ? "" :"<br/>地址："+address)
//				   + (createtime ==undefined ? "" :"<br/>时间："+createtime)+"</div>"
			    });
				
				
			    var info = [];
				info.push("<font color = '#0000ff'><b>车牌号：</b></font><font color='#004a91'>"+(carnumber ==undefined ? "" :carnumber)+"</font>"); 
				info.push("<font color = '#0000ff'><b>时间：</b></font><font color='#004a91'>"+(createtime ==undefined ? "" :createtime)+"</font>"); 
				info.push("<font color = '#0000ff'><b>位置：</b></font><font color='#004a91'>"+(address ==undefined ? "" :address)+"</font></div></div>"); 
			    infoWindow = new AMap.InfoWindow({ 
			        content:info.join("<br/>"),  //使用默认信息窗体框样式，显示信息内容
			        offset:new AMap.Pixel(0, -30)//-113, -140
			    });
			    AMap.event.addListener(marker,'click',function(){ //鼠标点击marker弹出自定义的信息窗体
			         infoWindow.open(map,marker.getPosition()); 
			    }); 
			    
	    		map.setCenter(marker.getPosition());
				marker.setMap(map);  
				first = false;
	    });
	   
		},
		
		//车辆实时监控画面--初始化地图
		initOneMap : function() {
			if(maptype==1){
				this.initBaiDuMap();
			}else{
				this.initGaoDeMap();
			}
		},
		//高德地图
		initGaoDeMap : function() {
			
			//清空map中marker对象
			markermap.clear();
			
			Ext.getCmp("taxiMapPanel").setActiveTab("taxismap");
			mapflag = false;
			
		    mapObj = new AMap.Map("map",{
		        rotateEnable:true,
		        dragEnable:true,
		        zoomEnable:true,
		        zooms:[3,18],
		        //二维地图显示视口
		        view: new AMap.View2D({
		            center:new AMap.LngLat(113.897276,22.573035),//地图中心点
		            zoom:10 //地图显示的缩放级别
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
		    
		     //在地图中添加鹰眼插件
		    mapObj.plugin(["AMap.OverView"],function(){
		        //加载鹰眼
		        overView = new AMap.OverView({
		            visible:false //初始化隐藏鹰眼
		        });
		        mapObj.addControl(overView);
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
			    
			//创建右键菜单
			var contextMenu = new AMap.ContextMenu();
			//拉框选车
			contextMenu.addItem("拉框选车",function(){
				//高德地图
				 	//Ext.Msg.alert('提示',"暂不支持高德地图拉框选车!");
				 	//设置圆的属性
				    var circleOption = {
				        strokeColor:"red",
				        fillColor:"red",
				        fillOpacity:0.5,
				        strokeOpacity:0.1,
				        strokeWeight:1 
				    };
				 	 //在地图中添加MouseTool插件
				    mapObj.plugin(["AMap.MouseTool"],function(){
				        mouseTool = new AMap.MouseTool(mapObj);
				        mouseTool.rectangle(circleOption);   
				        AMap.event.addListener(mouseTool,"draw",function(e){
				            var drawObj = e.obj;  //obj属性就是绘制完成的覆盖物对象。
				            gaodecomplete(e.obj.getPath().toString());
				            mouseTool.close(true);
				        });
				    });
				    
				return false;
			},0);
			//右键放大
			contextMenu.addItem("放大一级",function(){
			mapObj.zoomIn();	
			},1);
			//右键缩小
			contextMenu.addItem("缩小一级",function(){
				mapObj.zoomOut();
			},2);
			//右键显示全国范围
			contextMenu.addItem("缩放至全国范围",function(e){
				mapObj.setZoomAndCenter(4,new AMap.LngLat(108.946609,34.262324));
			},3);
			//测距
			contextMenu.addItem("测距",function(){
				   Ext.getCmp("taxiMapPanel").setActiveTab("taximap");
					 var ruler1;
					 mapObj.plugin(["AMap.RangingTool"],function(){
				         ruler1 = new AMap.RangingTool(mapObj);
				         AMap.event.addListener(ruler1,"end",function(e){
				         	ruler1.turnOff();
				         });
				    });
				    ruler1.turnOn();
			},4);
			//清除map
			contextMenu.addItem("清除map",function(){
				 Ext.getCmp("taxiMapPanel").setActiveTab("taximap");
				 mapObj.clearMap();
			},5);
			
			//地图绑定鼠标右击事件——弹出右键菜单
			AMap.event.addListener(mapObj,'rightclick',function(e){
				contextMenu.open(mapObj,e.lnglat);
				contextMenuPositon = e.lnglat;
			});
		    //加载左侧选中车辆位置
//			this.switchingMapLoad();
		},
		
		//百度地图
		initBaiDuMap : function() {
			
			//清空map中marker对象
			markermap.clear();
			
			Ext.getCmp("taxiMapPanel").setActiveTab("taxismap");
			mapflag = true;
			map = new BMap.Map("map");
			// 默认位置
			var point = new BMap.Point(113.897276,22.573035);
			map.centerAndZoom(point, 12);
			
			//获取当前城市
			function myFun(result){
			  var cityName = result.name;
			  // 默认位置
			  var point = new BMap.Point(result.center.lng,result.center.lat);
			  map.centerAndZoom(point, 8);
		    }
			var myCity = new BMap.LocalCity();
			myCity.get(myFun);
			
			map.enableScrollWheelZoom(); // 启用滚轮放大缩小，默认禁用
			map.enableContinuousZoom(); // 启用地图惯性拖拽，默认禁用
			map.addControl(new BMap.NavigationControl()); // 添加平移缩放控件
			map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
			map.addControl(new BMap.OverviewMapControl()); // 添加缩略地图控件
			map.addControl(new BMap.MapTypeControl()); // 添加地图类型控件
			
			//实例化拉框选车鼠标绘制工具
			drawingManager = new BMapLib.DrawingManager(map, {
					isOpen: false, //是否开启绘制模式
					enableDrawingTool: false, //是否显示工具栏
					drawingToolOptions: {
						anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
						offset: new BMap.Size(5, 5), //偏离值
						scale: 0.8 //工具栏缩放比例
					},
				rectangleOptions: styleOptions //矩形的样式
			});
			//添加鼠标绘制工具监听事件，用于获取绘制结果
			drawingManager.addEventListener('overlaycomplete', overlaycomplete);    
			
			var ctrl = new BMapLib.TrafficControl({
	           showPanel: false //是否显示路况提示面板
	       });
	       ctrl.setOffset(new BMap.Size(10, 40));
	       map.addControl(ctrl);
	       map.enableKeyboard();
	     //  ctrl.setAnchor(BMAP_ANCHOR_BOTTOM_RIGHT);  
	       
	       //地图右键菜单
	       var menu = new BMap.ContextMenu();
			var txtMenuItem = [
			  {
			   text:'放大',
			   callback:function(){map.zoomIn()}
			  },
			  {
			   text:'缩小',
			   callback:function(){map.zoomOut()}
			  },
			  {
			   text:'放置到最大级',
			   callback:function(){map.setZoom(18)}
			  },
			  {
			   text:'查看全国',
			   callback:function(){map.setZoom(4)}
			  },
			  {
			   text:'自定义控件',
			   callback:function(){
					alert("自定义控件");
				}
			  }
			 ];
			 for(var i=0; i < txtMenuItem.length; i++){
			  menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
			  if(i==1){
			   menu.addSeparator();
			  }
			 }
			 map.addContextMenu(menu);
			  
			  map.addEventListener("zoomend",function(){
			  	if(bdzoomflag){
			  	  bdzoom = map.getZoom();
			  	}
			  });
			  map.addEventListener("zoomend",function(){
			  	if(bdzoomflag){
			  	  map.setZoom(bdzoom);
			  	  bdzoom = map.getZoom();
			  	}
			  });
			  
			
			 // 定义一个控件类,即function
			function ZoomControl(){
			  // 默认停靠位置和偏移量
			  this.defaultAnchor = BMAP_ANCHOR_BOTTOM_RIGHT;
			  this.defaultOffset = new BMap.Size(10, 10);
			}
		
			// 通过JavaScript的prototype属性继承于BMap.Control
			ZoomControl.prototype = new BMap.Control();
		
			// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
			// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
			ZoomControl.prototype.initialize = function(map){
			  // 创建一个DOM元素
			  var div = document.createElement("div");
			  div.innerHTML = '<div id="speed_chart" style="width:50%;height:100%;float:left;"></div>' +
			  				  '<div id="mile_chart" style="width:50%;height:100%;float:left;"></div>';
			  // 设置样式
			  div.style.width = '340px';
			  div.style.height = '200px';
//			  div.style.z-index = '9999';
			  div.setAttribute("class", "map_chart"); 
			  // 添加DOM元素到地图中
			  map.getContainer().appendChild(div);
			  // 将DOM元素返回
			  return div;
			}
			// 创建控件
			var myZoomCtrl = new ZoomControl();
			// 添加到地图当中
			map.addControl(myZoomCtrl);
			  
		},
		
		
		searchcar : function(){
			 if(mapflag){	//百度地图
				drawingManager.setDrawingMode(BMAP_DRAWING_RECTANGLE);
				drawingManager.open();
			 }else{	//高德地图
			 	//Ext.Msg.alert('提示',"暂不支持高德地图拉框选车!");
			 	//设置圆的属性
			    var circleOption = {
			        strokeColor:"red",
			        fillColor:"red",
			        fillOpacity:0.5,
			        strokeOpacity:0.1,
			        strokeWeight:1 
			    };
			 	 //在地图中添加MouseTool插件
			    mapObj.plugin(["AMap.MouseTool"],function(){
			        mouseTool = new AMap.MouseTool(mapObj);
			        mouseTool.rectangle(circleOption);   
			        AMap.event.addListener(mouseTool,"draw",function(e){
			            var drawObj = e.obj;  //obj属性就是绘制完成的覆盖物对象。
			            gaodecomplete(e.obj.getPath().toString());
			            mouseTool.close(true);
			        });
			    });
			    
			 }
			return false;
		},
		
		//地图放大
		mapZoomIn : function(button){
			//切换至当前标签
	        Ext.getCmp("taxiMapPanel").setActiveTab("taximap");
			if(maptype == 1){
				map.zoomIn();
			}else{
				mapObj.zoomIn();
			}
		},
		//地图缩小
		mapZoomOut : function(button){
			Ext.getCmp("taxiMapPanel").setActiveTab("taximap");
			if(maptype == 1){
				map.zoomOut();
			}else{
				mapObj.zoomOut();
			}
		},
		//清除map
		clearmap : function(button){
			Ext.getCmp("taxiMapPanel").setActiveTab("taximap");
			if(maptype == 1){
				map.clearOverlays();
			}else{
			   mapObj.clearMap();
			}
			
		},
		//测距
		measuremap : function(button){
			Ext.getCmp("taxiMapPanel").setActiveTab("taximap");
			if(maptype == 1){
				var myDis = new BMapLib.DistanceTool(map);
				myDis.open();
			}else{
				
				 var ruler1;
				 mapObj.plugin(["AMap.RangingTool"],function(){
			         ruler1 = new AMap.RangingTool(mapObj);
			         AMap.event.addListener(ruler1,"end",function(e){
			         	ruler1.turnOff();
			         });
			    });
			    ruler1.turnOn();
			}
			
		},
		//全屏
		fullscreen : function(button){
			if(fullscreenflag){
				if(document.exitFullscreen) {
				    document.exitFullscreen();
				  } else if(document.mozCancelFullScreen) {
				    document.mozCancelFullScreen();
				  } else if(document.webkitExitFullscreen) {
				    document.webkitExitFullscreen();
				  }
				fullscreenflag = false;
			}else{
				var el = document.documentElement; 
				var rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullScreen; 
				if (typeof rfs != "undefined" && rfs) { 
				   rfs.call(el); 
				} else if (typeof window.ActiveXObject != "undefined") { 
				var wscript = new ActiveXObject("WScript.Shell"); 
				if (wscript != null) { 
				   wscript.SendKeys("{F11}"); 
				} 
				}
				fullscreenflag = true;
			}
		},
		
		
		//地图搜索
		searchmap : function(button){
			addTab("searchmap","一键导航","/taxi/searchmap/index.jsp");
		},
		//添加tab
		addTabPanel :function(item,eve){
			var tabid = item.id;
			var text = item.text;
			var hrefTarget = item.hrefTarget;
			if(hrefTarget.length > 0){
				addTab(tabid,text,hrefTarget);
			}
		},
		//退出
		logout :function(button){
			Ext.MessageBox.confirm('提示','您确认要退出系统?',
				function(btn) {
					if (btn == "yes") {
						//添加退出日志
						Ext.Ajax.request( {
								url : window.BIZCTX_PATH + '/userLoginJson/logout.action',
								method : 'POST',  
								timeout : 15000,
								success : function(response) {
									top.window.location.href = window.BIZCTX_PATH+"/index.jsp";
								},
								failure : function() {
								}
						});
					}
				}
			);
		},
		editpassword : function() {
			editpassword();
		},
		//点击报警文本框弹窗
		alarmRecord : function(button){
			addTab("alarmRecord","报警记录报表","/taxi/reportforms/alarm/index.jsp");
		},
		queryManyCarByParent :function(button){	//父节点选中后查看子节点车辆信息
			multiple = true;   ////查看多辆车位置信息
			points = [];
			var tree = Ext.getCmp("tree-panel");
			var nodes = tree.getChecked();
			if(nodes.length > 0){
				var tabPanel = Ext.getCmp("taxiMapPanel");
        		tabPanel.setActiveTab("taximap");
        		if(maptype == 1){
        			map.clearOverlays();
        		}else{
        			mapObj.clearMap();
        		}
			}else{
				Ext.Msg.alert("提示","请选择车辆");
			}
			var ids = "";
			for(var i=0;i<nodes.length;i++){
				if (nodes[i].get('leaf') && nodes[i].get('id').indexOf('C') == 0) { 
				    ids = ids+nodes[i].get('id').substring(1)+",";
				}
			}
			
			if(nodes.length>0){
			  this.loadSelectedCarPos(ids,true);
			}
		},
		loadCarPosition : function(carnumber,type){	//type为1查看单车清空地图标注，type为2查看多车不清空 type为3点击事件不清空
		   var tree = Ext.getCmp("tree-panel");
		    var state = "";
		    
		    if(maptype == 1){
			    if(type == 1){
			    	multiple = false;
			    	points = [];
			        map.clearOverlays();
		        }else{
		        	multiple = true;
		        }
		    }else{
		    	if(type == 1){
			    	multiple = false;
			    	points = [];
			        mapObj.clearMap();
		        }else{
		        	multiple = true;
		        }
		    }
		    
		    
	         var mask=new Ext.LoadMask('taxiMapPanel',{msg:"数据加载......"});
	         mask.show();
	         
	         //最新订单信息
	         Ext.Ajax.request( {
			      url: window.BIZCTX_PATH + '/terminalpositionjson/queryLastOrderInfo.action', //请求的服务器地址
			      params:{ 
			      		carnumber : carnumber
			      },
			      success : function(response) {
			         	 var action = Ext.JSON.decode(response.responseText);
			         	 var loi = action.result.lastOrderInfo;
			         	 if(loi != null && loi != '' && loi != 'undefined'){
			         	 	Ext.getCmp('east-panel').expand();
			         	 	
			         	 	var loi_orderid = loi.orderid;
			         	 	var loi_createtime = loi.createtime;
			         	 	//来源
			         	 	var loi_source = loi.source;
			         	 		if(loi_source == '1'){
									loi_source = "电召";
								}else if(loi_source == '2'){
									loi_source = "网站";
								}else if(loi_source == '3'){
									loi_source =  "APP";
								}else if(loi_source == '5'){
									loi_source =  "微信";
								}else{
								    loi_source =  "";
								}
			         	 	//业务订单类型
			         	 	var loi_tratype = loi.tratype;
				         	 	if(loi_tratype == '0'){
									loi_tratype = "即时召车";
								}else if(loi_tratype == '1'){
									loi_tratype = "预约召车";
								}else if(loi_tratype == '2'){
									loi_tratype = "车辆指派</span>";
								}else{
								    loi_tratype = "";
								}
			         	 	//业务订单状态
			         	 	var loi_status = loi.status;
			         	 		if(loi_status == '0'){
									loi_status = "无应答";
								}else if(loi_status == '1'){
									loi_status = "调派中";
								}else if(loi_status == '2'){
									loi_status = "已调派";
								}else if(loi_status == '3'){
									loi_status = "取消";
								}else if(loi_status == '4'){
									loi_status = "超时";
								}else if(loi_status == '5'){
									loi_status = "载客中";
								}else if(loi_status == '6'){
									loi_status = "待支付";
								}else if(loi_status == '7'){
									loi_status = "待评价";
								}else if(loi_status == '8'){
									loi_status = "完成";
								}else{
								    loi_status = "";
								}
			         	 	//支付状态	
			         	 	var loi_pay = loi.pay;
				         	 	if(loi_pay == '2'){
									loi_pay = "已支付";
								}else if(loi_pay == '1'){
									loi_pay = "待支付";
								}else{
								    loi_pay = "";
								}
			         	 	var loi_usernametwo = loi.usernametwo;
			         	 	var loi_phone = loi.phone;
			         	 	var loi_saddress = loi.saddress;
			         	 	var loi_eaddress = loi.eaddress;
			         	 	var loi_answertime = loi.answertime;
			         	 	var loi_drivername = loi.drivername;
			         	 	
			         	 	Ext.getCmp('loi_orderid').setValue(loi_orderid);
			         	 	Ext.getCmp('loi_createtime').setValue(loi_createtime);
			         	 	Ext.getCmp('loi_source').setValue(loi_source);
			         	 	Ext.getCmp('loi_tratype').setValue(loi_tratype);
			         	 	Ext.getCmp('loi_status').setValue(loi_status);
			         	 	Ext.getCmp('loi_pay').setValue(loi_pay);
			         	 	Ext.getCmp('loi_usernametwo').setValue(loi_usernametwo);
			         	 	Ext.getCmp('loi_phone').setValue(loi_phone);
			         	 	Ext.getCmp('loi_saddress').setValue(loi_saddress);
			         	 	Ext.getCmp('loi_eaddress').setValue(loi_eaddress);
			         	 	Ext.getCmp('loi_answertime').setValue(loi_answertime);
			         	 	Ext.getCmp('loi_drivername').setValue(loi_drivername);
			         	 	
			         	 }else{
			         	 	Ext.getCmp('east-panel').collapse();
			         	 }
		    	  },
				  failure : function() {
				  	   	 Ext.getCmp('east-panel').collapse();
				  }
		    });
	         
	         
	         //地图显示
		   	Ext.Ajax.request( {
			      url: window.BIZCTX_PATH + '/terminalpositionjson/queryCarDetail.action', //请求的服务器地址
			      params:{ 
			      		carnumber : carnumber
			      },
			      success : function(response) {
			         	 var action = Ext.JSON.decode(response.responseText);
						 var carid =  action.result.data.carid;
						 var blng =  action.result.data.blng;
						 var blat =  action.result.data.blat;
						 var lng =  action.result.data.lng;
						 var lat =  action.result.data.lat;
						 var glng =  action.result.data.glng;
						 var glat =  action.result.data.glat;
						 var gaddress =  action.result.data.gaddress;
						 var carstatus = action.result.data.carstatus;
						 var icon = action.result.data.icon;
						 var direction = action.result.data.direction;
						 var speed = action.result.data.speed;
						 var address = action.result.data.address;
						 var mileage = action.result.data.mileage;
						 var createtime = action.result.data.createtime;
						 var terminal = action.result.data.terminal;
						 var phone = action.result.data.phone;
						 var kzstate = action.result.data.kzstate;
						 var acc = action.result.data.acc;

						 var icon = getIcon(carstatus);
						 var carstatusText = getCarstatusText(carstatus);
						 
						 if(carstatus == 1 || carstatus == 7){
				   			state = "<font color='#777676'>离线</font>";
				   		 }else{
				   			state = "<font color='green'>" + carstatusText + "</font>";
				   		 }
				   		  var colortype = 2;
				   		  if(type == 3){
				   		  	  colortype = 1;
				   		  }
				   		 
			   		 	if(maptype == 1){//百度
			   		 		if(blng != null && blng != 0){  
						      	_mapbaidu.locationMapCloseWindow(blng,blat,carnumber,carstatus,icon,direction,false,colortype);
						         if(type == 3){
					   		 	     var pointinfo = [];
									 pointinfo.push(new BMap.Point(blng,blat));
									 map.setViewport(pointinfo);
						   		 }else{
						   		 	map.setViewport(points);
						   		 }
					   		 }
				   		 }else{//高德  --高德没有数据  使用百度的 有数据在更改
				   		 	if(blng != null && blng != 0){				   		 		
								_mapgaode.locationMapCloseWindow(blng,blat,carnumber,carstatus,icon,direction,false,colortype);
								 if(type == 3){
									var marker = markermap.get(carnumber);
									var pointinfo = [];
							 		pointinfo.push(marker);
									mapObj.setFitView(pointinfo); 
						   		 }else{
						   		 	mapObj.setFitView();
						   		 }
							}
				   		 }
				   	     mask.hide();
				   	     
				   	     //车辆实时信息添加数据
				   	     var store = Ext.StoreManager.get('TerminalPositionStore');
				   	     var flag = true;
				   	     for(var i=0;i<store.getCount();i++){
							if(store.getAt(i).data.carid == carid){
								flag = false;
								break;
							}
						 }
						 if(flag){
							 store.add({carid: carid,carnumber: carnumber,kzstate: kzstate,carstatus: carstatus,speed: speed,direction: direction,
								lng: lng,lat: lat,blng: blng,blat: blat,address: address,mileage:mileage,createtime: createtime
								,terminal: terminal,phone: phone,acc: acc});
						 
							//单选车辆后调用后台
							realTimeCheckedCar(1,carid.toString(),300);
							
						 }
				   	     
		    	}
		    });
		},
		loadSelectedCarPos:function(ids,storeflag){
				
				var mask = new Ext.LoadMask('taxiMapPanel',{msg:"数据加载......"});
	            mask.show();
				Ext.Ajax.request( {
						url: window.BIZCTX_PATH + '/terminalpositionjson/queryCarAllDetail.action', //请求的服务器地址
					      params:{ 
					      		ids : ids
					      },
							method : 'POST',  
							timeout : 10000,
							success : function(response) {
								var text = response.responseText;
								var obj = eval( "(" + text + ")" );//转换后的JSON对象 
								var arrayObj = new Array(); 
								arrayObj = obj.result.list;
								var mark = [];
								for(var i=0;i<arrayObj.length;i++){
									 var state = "";
									 var carid =  arrayObj[i].carid;
									 var blng =  arrayObj[i].blng;
									 var blat =  arrayObj[i].blat;
									 var lng =  arrayObj[i].lng;
									 var lat =  arrayObj[i].lat;
									 var glng =  arrayObj[i].glng;
									 var glat =  arrayObj[i].glat;
									 var gaddress = arrayObj[i].gaddress;
									 var carnumber = arrayObj[i].carnumber;
									 var address = arrayObj[i].address;
									 var carstatus = arrayObj[i].carstatus;
									 var createtime = arrayObj[i].createtime;
									 var speed = arrayObj[i].speed;
									 var direction = arrayObj[i].direction;
									 var mileage = arrayObj[i].mileage;
									 var kzstate = arrayObj[i].kzstate;
									 
									 var icon = getIcon(carstatus);
									 var carstatusText = getCarstatusText(carstatus);
									 
									 if(carstatus == 1 || carstatus == 7){
							   			state = "<font color='#777676'>离线</font>";
							   		 }else{
							   			state = "<font color='green'>" + carstatusText + "</font>";
							   		 }
							   		 
							if(maptype == 1){//百度
								_mapbaidu.locationMapCloseWindow(blng,blat,carnumber,carstatus,icon,direction,false,2);
				   		 	}else{//高德  --高德没有数据  使用百度的 有数据在更改
								_mapgaode.locationMapCloseWindow(blng,blat,carnumber,carstatus,icon,direction,false,2);
				   		 	}							   		
							   		 mark.push(markermap.get(carnumber));
							   		 
							   		 
							   		 
							   		if(storeflag){
							   			//车辆实时信息添加数据
								   	     var store = Ext.StoreManager.get('TerminalPositionStore');
								   	     var flag = true;
								   	     for(var i=0;i<store.getCount();i++){
											if(store.getAt(i).data.carid == carid){
												flag = false;
												break;
											}
										 }
										 if(flag){
										    store.add({carid: carid,carnumber: carnumber,kzstate: kzstate,carstatus: carstatus,speed: speed,direction: direction,
											lng: lng,lat: lat,blng: blng,blat: blat,address: address,mileage:mileage,createtime: createtime});
										 }
							   		}
							   		
								}
								mask.hide();
								map.setViewport(points);
							},
							failure : function() {
							}
					    });
		},
		loadCarPosByDept:function(deptid){
			
				var mask = new Ext.LoadMask('taxiMapPanel',{msg:"数据加载......"});
	            mask.show();
				Ext.Ajax.request( {
						url: window.BIZCTX_PATH + '/terminalpositionjson/loadCarPosByDept.action', //请求的服务器地址
					      params:{ 
					      		deptid : deptid
					      },
							method : 'POST',  
							timeout : 10000,
							success : function(response) {
								var text = response.responseText;
								var obj = eval( "(" + text + ")" );//转换后的JSON对象 
								var arrayObj = new Array(); 
								arrayObj = obj.result.list;
								var mark = [];
								for(var i=0;i<arrayObj.length;i++){
									 var state = "";
									 var carid =  arrayObj[i].carid;
									 var blng =  arrayObj[i].blng;
									 var blat =  arrayObj[i].blat;
									 var lng =  arrayObj[i].lng;
									 var lat =  arrayObj[i].lat;
									 var glng =  arrayObj[i].glng;
									 var glat =  arrayObj[i].glat;
									 var gaddress = arrayObj[i].gaddress;
									 var carnumber = arrayObj[i].carnumber;
									 var carstatus = arrayObj[i].carstatus;
									 var direction = arrayObj[i].direction;
									 var icon = getIcon(carstatus);
									 var carstatusText = getCarstatusText(carstatus);
									 
									 if(carstatus == 1 || carstatus == 7){
							   			state = "<font color='#777676'>离线</font>";
							   		 }else{
							   			state = "<font color='green'>" + carstatusText + "</font>";
							   		 }
							   		  
						   		 	 _mapbaidu.locationMapCloseWindow(blng,blat,carnumber,carstatus,icon,direction,false,2);
						   		 	 
						   		 	 mark.push(markermap.get(carnumber));
						   		 	  
								}
								mask.hide();
								map.setViewport(points);
							},
							failure : function() {
							}
					    });
		},
		phonecall : function(customercallno,Calleeid,Recordfile,callbegintime,Uniqueid){
		    var tabPanel = Ext.getCmp("taxiMapPanel");
		    var id = 'tabpanel'+customercallno+new Date().getMilliseconds();
//			if (!Ext.getCmp('tabpanel'+ customercallno)) {
//				 var url = window.BIZCTX_PATH + "/taxi/softphone/index.jsp?phone="+customercallno;
				 var url = window.BIZCTX_PATH + "/inboundcus/findInBoundCus.action?phone="+customercallno
				 			+"&agentid="+Calleeid+"&recordfile="+Recordfile+"&inboundcalltime="+callbegintime
				 			+"&uniqueid="+Uniqueid;
				 tabPanel.add({
					title:'客户信息',
					id:id,
					//加载修改首页内容的form表单页面
					html:"<iframe width=100% height=100% frameborder='no' style='padding:5px;'  border='0' marginwidth='0' marginheight='0'" +
							"scrolling='auto'  src='" + url + "'></iframe>",
					//可以被关闭
					closable:true,
					//自动销毁
					autoDestroy:true
				});
//			}
			tabPanel.setActiveTab(id);
		    return false;
		}
	}
);

function clearAll() {
	for(var i = 0; i < overlays.length; i++){
		map.removeOverlay(overlays[i]);
	}
	overlays.length = 0
}
//百度地图回调获得覆盖物信息
var overlays = [];
//百度地图回调获得覆盖物信息
var overlaycomplete = function(e){
	clearAll();
	overlays.push(e.overlay);
	if (e.drawingMode == BMAP_DRAWING_RECTANGLE) {
		var lng = e.overlay.getPath()[0].lng;
		var lat = e.overlay.getPath()[0].lat;
		var lng1 = e.overlay.getPath()[2].lng;
		var lat1 = e.overlay.getPath()[2].lat;
		clearAll();
		drawingManager.close();
		var view = Ext.widget('searchCarView');
        view.show();
		var store =Ext.StoreManager.get('SearchCarListStore');
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	lng:lng,
            	lat:lat,
            	lng1:lng1,
            	lat1:lat1,
            	maptype:1	//百度地图
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.loadPage(1); 
	}
};


//高德地图矩形拉框
var gaodecomplete = function(lnglat){
	    var array = lnglat.split(",")
	    //lat > lat1  lng1>lng
		var lng = array[0];
		var lat = array[1];
		var lng1 = array[2];
		var lat1 = array[5];
		var view = Ext.widget('searchCarView');
        view.show();
		var store =Ext.StoreManager.get('SearchCarListStore');
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	lng:lng,
            	lat:lat,
            	lng1:lng1,
            	lat1:lat1,
            	maptype:2	//高德地图
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.loadPage(1); 
};
