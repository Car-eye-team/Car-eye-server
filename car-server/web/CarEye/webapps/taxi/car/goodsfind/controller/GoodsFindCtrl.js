var map;
var point;
var drawingManager;
var drawingManager2;

var mapObj;
var marker;
var markermap = new Map();


var placeSearch;


var styleOptions = {
		strokeColor:"red",    //边线颜色。
		fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
		strokeWeight: 1,       //边线的宽度，以像素为单位。
		strokeOpacity: 0.5,	   //边线透明度，取值范围0 - 1。
		fillOpacity: 0.1,      //填充的透明度，取值范围0 - 1。
		strokeStyle: 'solid' //边线的样式，solid或dashed。
}

//设置属性--gaode  高德
		    var circleOption = {
		        strokeColor:"red",
		        fillColor:"red",
		        fillOpacity:0.5,
		        strokeOpacity:0.1,
		        strokeWeight:1 
		    };

Ext.define('GoodsFindApp.controller.GoodsFindCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarInfoListStore','PioPlaceStore','CarHistoryPositionStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['CarInfoModel','PoiInfoModel','PioPlaceModel','CarHistoryPositioModel'],//声明该控制层要用到的model
    views: ['AddrSearchView','CarInfoListView',
            'SearchMapView','SearchMapListView',
            'MileSearchView','CenterListView','CarHistoryPositionView','SearchDetailView'],//声明该控制层要用到的view
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
            ref: 'mileSearchView',
            selector: 'mileSearchView'
        }
    ],
    init: function() {
    	
		this.control({
            'searchMapView' : {
						afterrender : function(component, eOpts) {
							//初始化地图
							render : this.initMap(component.el.dom);
						}
					},
			'carInfoListView button[action=searchcar]' : {
				click : this.searchCar
			},
			'addrSearchView button[action=searchpoi]' : {
				//click : this.searchpoi
				click : this.searchPOI
			},
			'addrSearchView button[action=upAddress]' : {
				//click : this.searchpoi
				click : this.upAddress
			},
			'addrSearchView button[action=downAddress]' : {
				//click : this.searchpoi
				click : this.downAddress
			},
			'addrSearchView button[action=search]' : {
				click : this.search
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
	        
	        //实例化鼠标绘制工具
			drawingManager = new BMapLib.DrawingManager(map, {
				isOpen: false, //是否开启绘制模式
				enableDrawingTool: false, //是否显示工具栏
				drawingToolOptions: {
				anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
				offset: new BMap.Size(5, 5), //偏离值
				scale: 0.8 //工具栏缩放比例
				},
	
				//circleOptions: styleOptions, //圆的样式
				//polylineOptions: styleOptions, //线的样式
				//polygonOptions: styleOptions, //多边形的样式
				rectangleOptions: styleOptions //矩形的样式
			});
	
			//添加鼠标绘制工具监听事件，用于获取绘制结果
			drawingManager.addEventListener('overlaycomplete', overlaycomplete); 
			
			//实例化鼠标绘制工具
			drawingManager2 = new BMapLib.DrawingManager(map, {
				isOpen: false, //是否开启绘制模式
				enableDrawingTool: false, //是否显示工具栏
				drawingToolOptions: {
				anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
				offset: new BMap.Size(5, 5), //偏离值
				scale: 0.8 //工具栏缩放比例
				},
	
				//circleOptions: styleOptions, //圆的样式
				//polylineOptions: styleOptions, //线的样式
				//polygonOptions: styleOptions, //多边形的样式
				rectangleOptions: styleOptions //矩形的样式
			});
	
			//添加鼠标绘制工具监听事件，用于获取绘制结果
			drawingManager2.addEventListener('overlaycomplete', overlaycomplete2);
	 		// 显示地图中心地点的坐标
	//		map.addEventListener("click",
	//			function(e) {
	//				if (e.overlay) {
	//					var center = e.point;
	//					
	//					var view = Ext.widget('mileSearchView');
	//		            view.show();
	//		            
	//		            Ext.getCmp('lng').setValue(center.lng);
	//		            Ext.getCmp('lat').setValue(center.lat);
	//		            Ext.getCmp('poiname').setValue(center.poiname);
	//					
	//				}
	//			});
    	}else{
    	
    		//gaode   高德
    		//清空map中marker对象
			markermap.clear();
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
//			mapObj.plugin(["AMap.MapType"], function() {
//						//地图类型切换
//						var mapType = new AMap.MapType({
//							defaultType : 0,//默认显示卫星图
//							showRoad : true
//								//叠加路网图层
//							});
//						mapObj.addControl(mapType);
//					});
			mapObj.plugin(["AMap.ToolBar"], function() {
						//加载工具条
						var tool = new AMap.ToolBar({
							offset:new AMap.Pixel(0,50)
						});
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
    upAddress : function(button) {
    	if(maptype == 1){
    		//    	map.clearOverlays(); //清除上次标记点
	    	clearAll();
	    	drawingManager.setDrawingMode(BMAP_DRAWING_RECTANGLE);
	    	drawingManager.open();
    	}else{
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

    },
    downAddress : function(button) {
    	
    	if(maptype == 1){
	 //    	map.clearOverlays(); //清除上次标记点
	    	clearAll2();
	    	drawingManager2.setDrawingMode(BMAP_DRAWING_RECTANGLE);
	    	drawingManager2.open();   	
    	}else{
		 	 //在地图中添加MouseTool插件
		    mapObj.plugin(["AMap.MouseTool"],function(){
		        mouseTool = new AMap.MouseTool(mapObj);
		        mouseTool.rectangle(circleOption);   
		        AMap.event.addListener(mouseTool,"draw",function(e){
		            var drawObj = e.obj;  //obj属性就是绘制完成的覆盖物对象。
		            gaodecomplete1(e.obj.getPath().toString());
		            mouseTool.close(true);
		        });
		    });
    	}

    },
	searchCar : function(button) {
//		if(lng.length == 0 || lat.length == 0){
//			return;
//		}
		var carnumber = Ext.getCmp('c_carnumber').getValue();
		
		var store = this.getCarInfoListStoreStore();
		store.clearFilter(true);
		if(carnumber == null || carnumber == ""){
			store.filter([
			    {filterFn: function(item) { return true; }}
			]);
		}else{
			store.filter([
			   {filterFn: function(item) { return item.get("carnumber").indexOf(carnumber.toUpperCase().trim())>=　0; }}
			]);
		}
	    return false;
	},
	searchPOI:function(button){
		var keyword = Ext.getCmp('keyword').getValue();
		if (keyword == null || keyword == "") {
			Ext.Msg.alert('提示', "请输入要搜索的关键字");
			return;
		}
		if(maptype == 1){//百度
			findPOI(keyword);
			return false;
		}else{//高德
			findPOIByGaoDe(keyword);
			return false;
		}
		
	},
	search : function(button){
		
	    var uplng1 = Ext.getCmp('uplng1').getValue();
		var uplat1 = Ext.getCmp('uplat1').getValue();
		var uplng2 = Ext.getCmp('uplng2').getValue();
		var uplat2 = Ext.getCmp('uplat2').getValue();
		var downlng1 = Ext.getCmp('downlng1').getValue();
		var downlat1 = Ext.getCmp('downlat1').getValue();
		var downlng2 = Ext.getCmp('downlng2').getValue();
		var downlat2 = Ext.getCmp('downlat2').getValue();
		var stime1 = Ext.util.Format.date(Ext.getCmp('c_stime1').getValue(), 'Y-m-d H:i:s');
		var etime1 = Ext.util.Format.date(Ext.getCmp('c_etime1').getValue(), 'Y-m-d H:i:s');
		var stime2 = Ext.util.Format.date(Ext.getCmp('c_stime2').getValue(), 'Y-m-d H:i:s');
		var etime2 = Ext.util.Format.date(Ext.getCmp('c_etime2').getValue(), 'Y-m-d H:i:s');
		if(stime1 == null || stime1 == '' || etime1 == null || etime1 == ''){
			Ext.Msg.alert('提示', "请选择上车时间");
			return;
		}
		if(uplng1 == null || uplng1 == ''){
			Ext.Msg.alert('提示', "请画出上车地点区域");
			return;
		}
		if(stime2 == null || stime2 == '' || etime2 == null || etime2 == ''){
			Ext.Msg.alert('提示', "请选择下车时间");
			return;
		}
		if(downlng1 == null || downlng1 == ''){
			Ext.Msg.alert('提示', "请画出下车地点区域");
			return;
		}
		if(validTime(stime1,etime1)==-1){
			Ext.Msg.alert("提示","结束日期不能小于开始日期!");
			return;
		}
		if(validTime(stime2,etime2)==-1){
			Ext.Msg.alert("提示","结束日期不能小于开始日期!");
			return;
		}

		var store = this.getCarInfoListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	'uplng1' : uplng1,
				'uplat1' : uplat1,
				'uplng2' : uplng2,
				'uplat2' : uplat2,
				'downlng1' : downlng1,
				'downlat1' : downlat1,
				'downlng2' : downlng2,
				'downlat2' : downlat2,
				'stime1' : stime1,
				'etime1' : etime1,
				'stime2' : stime2,
				'etime2' : etime2
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
//        win.close();
		
	}

	
});
//高德
function findPOIByGaoDe(addr) {
	mapElecByGaoDe(addr);
}

function mapElecByGaoDe(keyword) {
	
	AMap.service(["AMap.PlaceSearch"], function() {       
        placeSearch = new AMap.PlaceSearch({ //构造地点查询类
	        pageSize: 5,
	        pageIndex: 1,
	        map: mapObj,
	        panel: "poiPanel"
	    });
	 });   
	    //关键字查询
 	placeSearch.search(keyword);
		
	placeSearch.getResults();
	
	placeSearch.setSearchCompleteCallback(function(searchResult) {
	});   
	
	 
    
}


//百度
function findPOI(addr) {
	mapElec(addr);
}

function mapElec(keyword) {

	//map.clearOverlays();
//	clearAll();
	// 搜索
	var local = new BMap.LocalSearch(map, {
		renderOptions : {
		map : map,
		panel : 'poiPanel'
	},
	pageCapacity : 5
	});

	map.panBy(point);

	local.search(keyword);

	local.getResults();

	local.setSearchCompleteCallback(function(searchResult) {
	});
}

//回调获得覆盖物信息
var overlays = [];
var overlays2 = [];

function clearAll() {
	for(var i = 0; i < overlays.length; i++){
		map.removeOverlay(overlays[i]);
	}
	overlays.length = 0;
}

function clearAll2() {
	for(var i = 0; i < overlays2.length; i++){
		map.removeOverlay(overlays2[i]);
	}
	overlays2.length = 0;
}

//回调获得覆盖物信息
var overlaycomplete = function(e){
//	clearAll();
	overlays.push(e.overlay);

	if (e.drawingMode == BMAP_DRAWING_RECTANGLE) {
		Ext.getCmp('uplng1').setValue(e.overlay.getPath()[0].lng);
		Ext.getCmp('uplat1').setValue(e.overlay.getPath()[0].lat);
		Ext.getCmp('uplng2').setValue(e.overlay.getPath()[2].lng);
		Ext.getCmp('uplat2').setValue(e.overlay.getPath()[2].lat);

		drawingManager.close();	
	}
}
	
//回调获得覆盖物信息
var overlaycomplete2 = function(e){
//	clearAll();
	overlays2.push(e.overlay);

	if (e.drawingMode == BMAP_DRAWING_RECTANGLE) {
		Ext.getCmp('downlng1').setValue(e.overlay.getPath()[0].lng);
		Ext.getCmp('downlat1').setValue(e.overlay.getPath()[0].lat);
		Ext.getCmp('downlng2').setValue(e.overlay.getPath()[2].lng);
		Ext.getCmp('downlat2').setValue(e.overlay.getPath()[2].lat);

		drawingManager2.close();
	}
	
}
 function validTime(startTime,endTime){
       var arr1 = startTime.split("-");
       var arr2 = endTime.split("-");
       var date1=new Date(parseInt(arr1[0]),parseInt(arr1[1])-1,parseInt(arr1[2]),0,0,0);
       var date2=new Date(parseInt(arr2[0]),parseInt(arr2[1])-1,parseInt(arr2[2]),0,0,0);
       if(date1.getTime()>date2.getTime()) {                               
               return -1;
         }else{
             return 1;
         }
         return -1;
}


//高德地图矩形拉框
var gaodecomplete = function(lnglat){
	    var array = lnglat.split(",")
	    //lat > lat1  lng1>lng
		var lng = array[0];
		var lat = array[1];
		var lng1 = array[2];
		var lat1 = array[5];
		
		Ext.getCmp('uplng1').setValue(lng);
		Ext.getCmp('uplat1').setValue(lat);
		Ext.getCmp('uplng2').setValue(lng1);
		Ext.getCmp('uplat2').setValue(lat1);
}


//高德地图矩形拉框
var gaodecomplete1 = function(lnglat){
	    var array = lnglat.split(",")
	    //lat > lat1  lng1>lng
		var lng = array[0];
		var lat = array[1];
		var lng1 = array[2];
		var lat1 = array[5];
		
		Ext.getCmp('downlng1').setValue(lng);
		Ext.getCmp('downlat1').setValue(lat);
		Ext.getCmp('downlng2').setValue(lng1);
		Ext.getCmp('downlat2').setValue(lat1);
}
