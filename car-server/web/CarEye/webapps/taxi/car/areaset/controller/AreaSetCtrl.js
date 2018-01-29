var mapObj;
var marker;

var map;
var tmap;
var point;
var drawingManager;
var _record;

var id;
var areatype;
var areaname;
var termvalidity;
var stime;
var etime;
var maxspeed;
var speedtime;
var attr0;
var attr1;
var attr2;
var attr3;
var attr4;
var attr5;

var ylng;
var ylat;
var radius;
var flag=null;
var flagDraw=1;
var latsrt=null;
var lngsrt=null;

var latlt=null;
var lnglt=null;
var latrb=null;
var lngrb=null;

var styleOptions = {
		strokeColor:"red",    //边线颜色。
		fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
		strokeWeight: 1,       //边线的宽度，以像素为单位。
		strokeOpacity: 0.5,	   //边线透明度，取值范围0 - 1。
		fillOpacity: 0.1,      //填充的透明度，取值范围0 - 1。
		strokeStyle: 'solid' //边线的样式，solid或dashed。
}


//高德图形属性
//设置圆的属性
var circleOption = {
	    	strokeColor:"#FF33FF",
			fillColor:"#FF99FF",
			fillOpacity:0.5,
	    	strokeOpacity:1,
	    	strokeWeight:3	
};
//设置矩形
var rectangleOption = {
			        strokeColor:"#0000FF",
			        fillColor: "#8D95D2",
			        strokeOpacity:1,
			        strokeWeight:3,
			        fillOpacity: 0.35
			    };


Ext.define('AreaSetApp.controller.AreaSetCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['AreaSetListStore','CarInfoListStore','CarListStore','ImformationTypeStore'
            ,'AreaCarListStore','AreaSendRecordListStore','OperTypeStore','DateTimeTypeStore'
            ,'CarPageListStore'],//声明该控制层要用到的store
    models: [],//声明该控制层要用到的model
    views: ['AreaSetSearchView','AreaSetListView','CarInfoListView','AreaMapView','SearchMapView'],//声明该控制层要用到的view
    init: function() {
    	
		this.control({
			'areaMapView': {
                afterrender:function(component, eOpts){
                    //初始化地图
                    render: this.initMap(component.el.dom);
                }
            },
            'searchMapView': {
//            	afterrender:function(component, eOpts){
//                //初始化地图
//               	 	render: this.initAreaMap(component.el.dom);
//         		}
            },
			'areaSetListView' : {
//				select : this.loadAreaMap
			},
			'areaSetListView button[action=add]' : {
				click : this.addAreaSet
			},
			'areaSetListView button[action=delete]' : {
				click : this.deleteAreaSet
			},
			'areaCarListView button[action=delete]' : {
				click : this.deleteAreaCar
			},
			'areaCarListView button[action=edit]' : {
				click : this.updateAreaCar
			},
			'areaSetListView button[action=edit]' : {
				click : this.editAreaSet
			},
			'areaSetAddView button[action=save]' : {
				click : this.saveAreaSet
			},
			'areaSetEditView button[action=save]' : {
				click : this.saveAreaSet
			},
			'carInfoListView button[action=add]' : {
				click : this.saveAreaCar
			},
			'carInfoListView button[action=query]' : {
				click : this.queryAreaCarDetail
			},
			'carInfoListView button[action=searchcar]' : {
				click : this.searchCar
			},
			'carInfoListView #lookareacar' : {
				click : this.lookAreaCar
			},
			'areaSetListView button[action=searcharea]' : {
				click : this.searchAreaSet
			},
			'areaSetSearchView button[action=searchtext]' : {
				click : this.searchtext
			},
			'areaSendRecordListView button[action=search]' : {
				click : this.searchAreaRecord
			},
			'areaSetListView':{
                render : this.buttonAccess
            },
			'carInfoListView':{
                render : this.button1Access
            },
			'carInfoListView #carnumber' : {
				change : this.loadCar
			}
		});
	},
	buttonAccess : function() {
		var buttonArray = ['180601','180602','180603'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	button1Access : function() {
		var buttonArray = ['180604','180605'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	loadCar : function(){
		var store = this.getCarListStoreStore();
		var carnumber = Ext.getCmp('cas_carnumber').getRawValue();
    	if(admin.deptid == 1){
    		if(carnumber.length >=3 ){
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
		//清空map中marker对象
		mapObj = new AMap.Map(div,{
				resizeEnable: true,
		        rotateEnable:true,
		        dragEnable:true,
		        //zoomEnable:true,
		        zooms:[3,18],
		        offset: new AMap.Pixel(-12,-36),
		        //二维地图显示视口
		        view: new AMap.View2D({
		            center:new AMap.LngLat(116.397428,39.90923),//地图中心点
		            zoom:11 //地图显示的缩放级别
		        })
		    });
		    //添加地图类型切换插件
	    mapObj.plugin(["AMap.MapType"],function(){
	        //地图类型切换
	        var mapType= new AMap.MapType({
	            defaultType:0,//默认显示卫星图
	            showRoad:true //叠加路网图层
	        });
	        mapObj.addControl(mapType);
	    });	
		mapObj.plugin(["AMap.ToolBar"], function(){
			//加载工具条
			var tool = new AMap.ToolBar();
			mapObj.addControl(tool);
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
	                        mapObj.setZoom(11);
						}
					}else{
					}
				});
			});
		}
		
		showCityInfo();
		
	},
	
	//百度
    initBaiDuMap:function(div){

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

		circleOptions: styleOptions, //圆的样式
		//polylineOptions: styleOptions, //线的样式
		polygonOptions: styleOptions, //多边形的样式
		rectangleOptions: styleOptions //矩形的样式
		});

		//添加鼠标绘制工具监听事件，用于获取绘制结果
		drawingManager.addEventListener('overlaycomplete', overlaycomplete);        
 
    },
    initAreaMap:function(div){

        tmap = new BMap.Map(div);
        //默认位置
        var point = new BMap.Point(116.3972,39.9096);
        tmap.centerAndZoom(point, 12);
 
        var localCity = new BMap.LocalCity();
        localCity.get(function(result){
            var cityName = result.name;
            var localPoint = new BMap.Point(result.center.lng, result.center.lat);
            tmap.setCenter(localPoint,cityName);
        });
 
        tmap.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
        tmap.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
        tmap.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
        tmap.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
        tmap.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件
        
        var tmapflag = true;
        tmap.addEventListener("tilesloaded",function(){
			if(tmapflag){
				tmapflag = false;
		        //显示区域地图
				var areaid = Ext.getCmp('c_areaid').getValue();
				if(areaid != null && areaid != "" && areaid != 0){
					Ext.Ajax.request( {
						url : window.BIZCTX_PATH + '/areasetjson/getAreaSetDetail.action',
						method : 'POST',  
						timeout : 15000,
						params : "id="+ areaid,
						success : function(response) {
							var text = response.responseText;
						    var obj = eval( "(" + text + ")" );//转换后的JSON对象
						    var areatype = obj.result.areaSet.areatype;
						    var ylng = obj.result.areaSet.ylng;
						    var ylat = obj.result.areaSet.ylat;
						    var radius = obj.result.areaSet.radius;
						    var lnglt = obj.result.areaSet.lnglt;
						    var lngrb = obj.result.areaSet.lngrb;
						    var lnglt = obj.result.areaSet.lnglt;
						    var latlt = obj.result.areaSet.latlt;
						    var latrb = obj.result.areaSet.latrb;
						    var latlt = obj.result.areaSet.latlt;
						    
						    if(areatype == 1){	//圆形
								var point = new BMap.Point(ylng,ylat);
								tmap.centerAndZoom(point, 12);
								var circle = new BMap.Circle(point,radius,styleOptions);
								tmap.addOverlay(circle);
							}else if(areatype == 2){	//矩形
								var pStart = new BMap.Point(lnglt,latlt);
								var pEnd = new BMap.Point(lngrb,latrb);
								var point = new BMap.Point(lnglt, latlt);
								tmap.centerAndZoom(point, 12);
								var polygon = new BMap.Polygon([
								  new BMap.Point(pStart.lng,pStart.lat),
								  new BMap.Point(pEnd.lng,pStart.lat),
								  new BMap.Point(pEnd.lng,pEnd.lat),
								  new BMap.Point(pStart.lng,pEnd.lat)
								], styleOptions);
								tmap.addOverlay(polygon);
							}
						},
						failure : function() {
						}
					});
				}
			}
		});
    },
	loadAreaMap : function(view,record,item,index) {
		if(record.get('areatype') == 1){	//圆形
			var point = new BMap.Point(record.get('ylng'),record.get('ylat'));
			map.centerAndZoom(point, 12);
			var circle = new BMap.Circle(point,record.get('radius'),styleOptions);
			map.clearOverlays(); //清除上次标记点
			map.addOverlay(circle);
		}else if(record.get('areatype') == 2){	//矩形
			var pStart = new BMap.Point(record.get('lnglt'),record.get('latlt'));
			var pEnd = new BMap.Point(record.get('lngrb'),record.get('latrb'));
			var point = new BMap.Point(record.get('lnglt'), record.get('latlt'));
			map.centerAndZoom(point, 12);
			var polygon = new BMap.Polygon([
			  new BMap.Point(pStart.lng,pStart.lat),
			  new BMap.Point(pEnd.lng,pStart.lat),
			  new BMap.Point(pEnd.lng,pEnd.lat),
			  new BMap.Point(pStart.lng,pEnd.lat)
			], styleOptions);
			map.clearOverlays(); //清除上次标记点
			map.addOverlay(polygon);
		}else if(record.get('areatype') == 3){	//多边形
			var lngsrtArr = record.get('lngsrt').split(",");
			var latsrtArr = record.get('latsrt').split(",");
			var pointArr = [ ];
			for (var i=0 ; i< lngsrtArr.length ; i++){
			    pointArr.push(new BMap.Point(lngsrtArr[i],latsrtArr[i]));
			}
			var point = new BMap.Point(lngsrtArr[0],latsrtArr[0]);
			map.centerAndZoom(point, 12);
			var polygon = new BMap.Polygon(pointArr,styleOptions);
			map.clearOverlays(); //清除上次标记点
			map.addOverlay(polygon);
		}
		return false;
	},
	addAreaSet : function(button) {
//		var view = Ext.widget('areaSetAddView');
		var view = Ext.create('AreaSetApp.view.AreaSetAddView');
		view.show();
		return false;
	},
	searchAreaSet : function(button) {
		var store = this.getAreaSetListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	areaname: encodeURI(Ext.getCmp('sas_areaname').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
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
	       // store.reload();
	    return false;
	},
	lookAreaCar : function(grid, rowIndex, colIndex){//rowIndex，colIndex均从0开始  
//		var view = Ext.widget('areaCarListView');
		var view = Ext.create('AreaSetApp.view.AreaCarListView');
		view.show();
        var rec = grid.getStore().getAt(colIndex);  
        var carnumber=rec.get('carnumber');
        var store = this.getAreaCarListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { carnumber: encodeURI(carnumber)};
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	    store.loadPage(1); 
	    //store.reload();
		return false;
	},
	queryAreaCarDetail : function(button) {
//		var view = Ext.widget('areaSendRecordListView');
		var view = Ext.create('AreaSetApp.view.AreaSendRecordListView');
		view.show();
		var store = this.getAreaSendRecordListStoreStore();
		store.load();
		return false;
	},
	saveAreaSet : function(button) {
		if(maptype == 1){
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getAreaSetListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			
			var areastime = Ext.util.Format.date(Ext.getCmp('stime').getValue(), 'Y-m-d H:i:s');
	        var areaetime = Ext.util.Format.date(Ext.getCmp('etime').getValue(), 'Y-m-d H:i:s');
			if(areastime >areaetime){
				Ext.Msg.alert("提示","开始日期不能大于结束日期!");
				return;
			}
			
		    areatype = Ext.getCmp('areatype').getValue();
			if(areatype ==1){
				drawingManager.setDrawingMode(BMAP_DRAWING_CIRCLE);
			}else if(areatype ==2){
				drawingManager.setDrawingMode(BMAP_DRAWING_RECTANGLE);
			}else if(areatype ==3){
				drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON);
			}
			
			 if(typeof Ext.getCmp('id') == 'undefined'){
			 	id = null;
			 }else{
			 	id = Ext.getCmp('id').getValue();
			 }
			 areaname = Ext.getCmp('areaname').getValue();
			 stime = encodeURI(Ext.util.Format.date(Ext.getCmp('stime').getValue(), 'Y-m-d H:i:s'));
			 etime = encodeURI(Ext.util.Format.date(Ext.getCmp('etime').getValue(), 'Y-m-d H:i:s'));
			 maxspeed = Ext.getCmp('maxspeed').getValue();
			 termvalidity = Ext.getCmp('termvalidity').getValue();
			 speedtime = Ext.getCmp('speedtime').getValue();
			 attr0 = Ext.getCmp('attr0').getValue()==true?1:0;
			 attr1 = Ext.getCmp('attr1').getValue()==true?1:0;
			 attr2 = Ext.getCmp('attr2').getValue()==true?1:0;
			 attr3 = Ext.getCmp('attr3').getValue()==true?1:0;
			 attr4 = Ext.getCmp('attr4').getValue()==true?1:0;
			 attr5 = Ext.getCmp('attr5').getValue()==true?1:0;
			
			 win.close();
			 drawingManager.open();

			return false;
		}else{
			var win=null;
		    if(typeof Ext.getCmp('id') == 'undefined'){
			 	id = null;
			 	win =Ext.getCmp('areaSetAddView');
			 }else{
			 	id = Ext.getCmp('id').getValue();
			 	win =Ext.getCmp('areaSetEditView');
			 }
			var form = win.down('form');
			var store = this.getAreaSetListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			
			var areastime = Ext.util.Format.date(Ext.getCmp('stime').getValue(), 'Y-m-d H:i:s');
	        var areaetime = Ext.util.Format.date(Ext.getCmp('etime').getValue(), 'Y-m-d H:i:s');
			if(areastime >areaetime){
				Ext.Msg.alert("提示","开始日期不能大于结束日期!");
				return;
			}
		    
		     areatype = Ext.getCmp('areatype').getValue();
			 
			 areaname = Ext.getCmp('areaname').getValue();
			 stime = encodeURI(Ext.util.Format.date(Ext.getCmp('stime').getValue(), 'Y-m-d H:i:s'));
			 etime = encodeURI(Ext.util.Format.date(Ext.getCmp('etime').getValue(), 'Y-m-d H:i:s'));
			 maxspeed = Ext.getCmp('maxspeed').getValue();
			 termvalidity = Ext.getCmp('termvalidity').getValue();
			 speedtime = Ext.getCmp('speedtime').getValue();
			 attr0 = Ext.getCmp('attr0').getValue()==true?1:0;
			 attr1 = Ext.getCmp('attr1').getValue()==true?1:0;
			 attr2 = Ext.getCmp('attr2').getValue()==true?1:0;
			 attr3 = Ext.getCmp('attr3').getValue()==true?1:0;
			 attr4 = Ext.getCmp('attr4').getValue()==true?1:0;
			 attr5 = Ext.getCmp('attr5').getValue()==true?1:0;
			 
			 /**
			  * 划区域
			  */
			 ylng=Ext.getCmp('ylng').getValue();
			 ylat=Ext.getCmp('ylat').getValue();
			 radius=Ext.getCmp('radius').getValue();
			 latsrt=Ext.getCmp('latsrt').getValue();
			 lngsrt=Ext.getCmp('lngsrt').getValue();
			 latlt=Ext.getCmp('latlt').getValue();
			 lnglt=Ext.getCmp('lnglt').getValue();
			 latrb=Ext.getCmp('latrb').getValue();
			 lngrb=Ext.getCmp('lngrb').getValue();
			 
			 //去掉监听
			 var clickEventListener = AMap.event.addListener(mapObj,'click',callBackFn);
		     var drawEventListener = AMap.event.addListener(mapObj,'draw',callBackFn);
		     var dblclickEventListener = AMap.event.addListener(mapObj,'dblclick',callBackFn);
		     var mouseupEventListener = AMap.event.addListener(mapObj,'mouseup',callBackFn);
		     var rightclickEventListener = AMap.event.addListener(mapObj,'rightclick',callBackFn);
		 
		     
		     //事件回调函数
			var callBackFn = function(e){
				document.getElementById("eventInfo").innerHTML = e.type+" : "+e.lnglat.getLng()+","+e.lnglat.getLat();
			};
		    //移除地图的click事件的监听
			AMap.event.removeListener(clickEventListener);
			AMap.event.removeListener(drawEventListener);
			AMap.event.removeListener(dblclickEventListener);
			AMap.event.removeListener(mouseupEventListener);
			AMap.event.removeListener(rightclickEventListener);
			
			
			mapObj.clearMap(null);
			
			if(areatype ==1){//绘制圆形
					  Ext.Msg.alert("提示","点击地图  开始绘制圆形 鼠标拾起完成绘制过程!");
			               function gaodecompleteCircle(drawObj){
			          	    ylng=drawObj.getCenter().getLng();
			          	    ylat=drawObj.getCenter().getLat();
			          	    radius=drawObj.getRadius();
			          		Ext.MessageBox.wait('请稍候...', '提示');
							Ext.Ajax.request({
								url : window.BIZCTX_PATH + '/areasetjson/saveAreaSet.action',
								method : 'POST',  
								params : {
											'areaSet.id' : id,
											'areaSet.areaname' : areaname,
											'areaSet.stime' : stime,
											'areaSet.etime' : etime,
											'areaSet.termvalidity' : termvalidity,
											'areaSet.maxspeed' : maxspeed,
											'areaSet.speedtime' : speedtime,
											'areaSet.attr0' : attr0,
											'areaSet.attr1' : attr1,
											'areaSet.attr2' : attr2,
											'areaSet.attr3' : attr3,
											'areaSet.attr4' : attr4,
											'areaSet.attr5' : attr5,
								          	'areaSet.ylng':ylng,
										  	'areaSet.ylat':ylat,
										  	'areaSet.radius':radius,
										  	'areaSet.latsrt':null,
										  	'areaSet.lngsrt':null,
										  	'areaSet.latlt':null,
										  	'areaSet.lnglt':null,
										  	'areaSet.latrb':null,
										  	'areaSet.lngrb':null,
											'areaSet.areatype':areatype
									 },
									success : function(response) {
									var text = response.responseText;
									 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					 var su = obj.result.su;
									 if (su == -1) {
										Ext.Msg.alert('提示',"操作失败");
									 }else if (su == -3) {
										Ext.Msg.alert('提示',"存在相同的区域名称");
										return;
									 }else {
										Ext.Msg.alert('提示',"操作成功");
										mapObj.clearMap(null);
										//封装对象 并定位圆形
										var data =  new Object;
										data.ylng=ylng;
										data.ylat=ylat;
										data.radius=radius;
										opsitionCircle(data);
										store.load(); 
									}
						   			},
									failure : function() {
										Ext.Msg.alert('提示',"操作失败");
									}
					     });
			       }
			         	    //在地图中添加MouseTool插件
				    mapObj.plugin(["AMap.MouseTool"],function(){
					        mouseTool = new AMap.MouseTool(mapObj);
							mouseTool.circle(circleOption);   //使用鼠标工具绘制圆
					        AMap.event.addListener(mouseTool,"draw",function(e){
					            gaodecompleteCircle(e.obj);  //obj属性就是绘制完成的覆盖物对象。
					            mouseTool.close(true);
					        });
					        
				  });
				
			}else if(areatype ==2){//绘制矩形
				win.close();
			    Ext.Msg.alert("提示","鼠标在地图上点击绘制矩形，鼠标拾起结束绘制!");
				    
			    function  gaodecomplete(lnglat){
					           var array = lnglat.split(",")
						      //lat > lat1  lng1>lng
							   latlt = array[0];
							   lnglt = array[1];
							   latrb = array[2];
							   lngrb = array[5];
								
				          		Ext.MessageBox.wait('请稍候...', '提示');
								Ext.Ajax.request({
									url : window.BIZCTX_PATH + '/areasetjson/saveAreaSet.action',
									method : 'POST',  
									params : {
										      	'areaSet.id' : id,
												'areaSet.areaname' : areaname,
												'areaSet.stime' : stime,
												'areaSet.etime' : etime,
												'areaSet.termvalidity' : termvalidity,
												'areaSet.maxspeed' : maxspeed,
												'areaSet.speedtime' : speedtime,
												'areaSet.attr0' : attr0,
												'areaSet.attr1' : attr1,
												'areaSet.attr2' : attr2,
												'areaSet.attr3' : attr3,
												'areaSet.attr4' : attr4,
												'areaSet.attr5' : attr5,
									          	'areaSet.ylng':null,
											  	'areaSet.ylat':null,
											  	'areaSet.radius':null,
											  	'areaSet.latsrt':null,
											  	'areaSet.lngsrt':null,
											  	'areaSet.latlt':latlt,
											  	'areaSet.lnglt':lnglt,
											  	'areaSet.latrb':latrb,
											  	'areaSet.lngrb':lngrb,
												'areaSet.areatype':areatype
											 },
											success : function(response) {
											var text = response.responseText;
											 var obj = eval( "(" + text + ")" );//转换后的JSON对象
						   					 var su = obj.result.su;
											 if (su == -1) {
												Ext.Msg.alert('提示',"操作失败");
											 }else if (su == -3) {
												Ext.Msg.alert('提示',"存在相同的区域名称");
												return;
											 }else {
												Ext.Msg.alert('提示',"操作成功");
												mapObj.clearMap(null);
												//封装对象 并定位矩形
												var data=new Object();
												data.latlt=latlt;
												data.lnglt=lnglt;
												data.latrb=latrb;
												data.lngrb=lngrb;
	                                            opsitionRectangle(data)
												store.load(); 
											}
								   			},
											failure : function() {
												Ext.Msg.alert('提示',"操作失败");
											}
						       });
											  
					 	Ext.Msg.alert("提示","完成绘制过程!");
				}
				 	    //在地图中添加MouseTool插件
				  mapObj.plugin(["AMap.MouseTool"],function(){
					        mouseTool = new AMap.MouseTool(mapObj);
					        mouseTool.rectangle(rectangleOption);   
					        AMap.event.addListener(mouseTool,"draw",function(e){
					            var drawObj = e.obj;  //obj属性就是绘制完成的覆盖物对象。
					            gaodecomplete(e.obj.getPath().toString());
					            mouseTool.close(true);
					        });
					        
				});
			}else if(areatype ==3){//绘制多边形
					function getMore(path){
						var time=path.split(',').length;
						var arr= path.split(',');
						var reg=new RegExp("null","g"); 
						var s1=null;
						var s2=null;
						for(var i=0 ;i< time ; i++){
							if(i%2==0){             //经度
								s1 +=arr[i]+",";
							}else{                //纬度
								s2 +=arr[i]+",";
							}
						 }
						 if(s1!=null){
						 	 lngsrt=s1.replace(reg,""); 
						     latsrt=s2.replace(reg,""); 
						 }
				  }
				  Ext.Msg.alert("提示","鼠标在地图上点击绘制多边形，鼠标拾起结束绘制!");
				  function  gaodecompleteMore(){	       
				  Ext.MessageBox.wait('请稍候...', '提示');
							Ext.Ajax.request({
								url : window.BIZCTX_PATH + '/areasetjson/saveAreaSet.action',
								method : 'POST',  
								params : {
									      	'areaSet.id' : id,
											'areaSet.areaname' : areaname,
											'areaSet.stime' : stime,
											'areaSet.etime' : etime,
											'areaSet.termvalidity' : termvalidity,
											'areaSet.maxspeed' : maxspeed,
											'areaSet.speedtime' : speedtime,
											'areaSet.attr0' : attr0,
											'areaSet.attr1' : attr1,
											'areaSet.attr2' : attr2,
											'areaSet.attr3' : attr3,
											'areaSet.attr4' : attr4,
											'areaSet.attr5' : attr5,
								          	'areaSet.ylng':null,
										  	'areaSet.ylat':null,
										  	'areaSet.radius':null,
										  	'areaSet.latsrt':latsrt,
										  	'areaSet.lngsrt':lngsrt,
										  	'areaSet.latlt':null,
										  	'areaSet.lnglt':null,
										  	'areaSet.latrb':null,
										  	'areaSet.lngrb':null,
											'areaSet.areatype':areatype
										 },
										success : function(response) {
										var text = response.responseText;
										 var obj = eval( "(" + text + ")" );//转换后的JSON对象
					   					 var su = obj.result.su;
										 if (su == -1) {
											Ext.Msg.alert('提示',"操作失败");
										 }else if (su == -3) {
											Ext.Msg.alert('提示',"存在相同的区域名称");
											return;
										 }else {
											Ext.Msg.alert('提示',"操作成功");
											mapObj.clearMap(null);
											//封装对象 并定位多边形
											var data=new Object();
										    data.lngsrt=lngsrt;
										    data.latsrt=latsrt;
											opsitionPolygon(data);
											store.load(); 
										}
							   			},
										failure : function() {
											Ext.Msg.alert('提示',"操作失败");
										}
					     });
				 	   Ext.Msg.alert("提示","完成绘制过程!");
				  
				  }
				  
				  //在地图中添加MouseTool插件
				mapObj.plugin(["AMap.MouseTool"],function(){ 
						 var mouseTool = new AMap.MouseTool(mapObj); 
						 mouseTool.polygon(polygonOption);   //使用鼠标工具绘制多边形
						 AMap.event.addListener(mouseTool,"draw",function(e){
				            var drawObj = e.obj;  //obj属性就是绘制完成的覆盖物对象。
				            getMore(e.obj.getPath().toString());
				            gaodecompleteMore();
				            mouseTool.close(true);
		                });
			    });

			}
			 
			
		 	win.close();

			return false;
		}
			
	},
	saveAreaCar : function(button){
		var menuid = button.id+"";
	    var textgrid = Ext.getCmp('sysareasetgrid');
	    var cargrid = Ext.getCmp('carareasetgrid');
		var textdata = textgrid.getSelectionModel().getSelection();
		var cardata = cargrid.getSelectionModel().getSelection();
		
		var textstore = textgrid.getStore();
		var carstore = cargrid.getStore();
		if(cardata.length ==0 || textdata.length ==0){
			Ext.Msg.alert('提示', '系统区域与车辆信息都至少选一条!');
			return false;
		}
		if (cardata.length>0) {
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
			var te = textgrid.getSelectionModel().getSelection();
			var areaids = "";
			for ( var i = 0, len = te.length; i < len; i++) {
				var ss = te[i].get("id");
				if (i == 0) {
					areaids = areaids + ss;
				} else {
					areaids = areaids + "," + ss;
				}
			}
			
			Ext.Ajax.request( {
						url : window.BIZCTX_PATH + '/areasetjson/addCarAreaSet.action',
						method : 'POST',  
						params : {carids:carids,areaids:areaids},
						success : function(response) {
						var text = response.responseText;
						 var obj = eval( "(" + text + ")" );//转换后的JSON对象
	   					 var su = obj.result.su;
						 if (su == -1) {
							Ext.Msg.alert('提示',"追加区域失败");
						}else {
							Ext.Msg.alert('提示',"追加区域成功");
						}
			   			},
						failure : function() {
							Ext.Msg.alert('提示',"追加区域失败");
						}
					});
		}
		return false;
	
	
	},
	deleteAreaSet : function(button) {
			var grid = button.up('grid');
			var data = grid.getSelectionModel().getSelection();
			var store = grid.getStore();
			if (data.length>0) {
				Ext.MessageBox.confirm('提示','你确认要删除选中的系统区域？',
				function(btn) {
					if (btn == "yes") {
						var m = grid.getSelectionModel().getSelection();
						var jsonData = "";
						for ( var i = 0, len = m.length; i < len; i++) {
							var ss = m[i].get("id");
							if (i == 0) {
								jsonData = jsonData + ss;
							} else {
								jsonData = jsonData + "," + ss;
							}
						}
						Ext.Ajax.request( {
									url : window.BIZCTX_PATH + '/areasetjson/deleteAreaSet.action',
									method : 'POST',  
									params : "ids="+ jsonData,
									success : function(response) {
										var text = response.responseText;
									    var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					    var su = obj.result.su;
										if( su == -2){
											Ext.Msg.alert('提示',"系统区域被车辆追加的将不能删除");
										}else if(su == 1){
											Ext.Msg.alert('提示',"系统区域删除成功");
										}else {
											Ext.Msg.alert('提示',"系统区域删除失败");
										}
										store.reload();
									},
									failure : function() {
										Ext.Msg.alert('提示',"系统区域删除失败");
										store.reload();
									}
								});
					}
				});
			} else {
				Ext.Msg.alert('提示', '请选择要删除的系统区域!');
			}
		return false;
	},
	deleteAreaCar : function(button) {
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length>0) {
			Ext.MessageBox.confirm('提示','你确认要删除选中的车辆区域？',
			function(btn) {
				if (btn == "yes") {
					var m = grid.getSelectionModel().getSelection();
					var jsonData = "";
					for ( var i = 0, len = m.length; i < len; i++) {
						var ss = m[i].get("id");
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					Ext.Ajax.request( {
						url : window.BIZCTX_PATH + '/areasetjson/deleteAreaCar.action',
						method : 'POST',  
						params : "ids="+ jsonData,
						success : function(response) {
							Ext.Msg.alert('提示',"车辆区域删除成功");
							store.reload();
						},
						failure : function() {
							Ext.Msg.alert('提示',"车辆区域删除失败");
							store.reload();
						}
					});
				}
			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的车辆区域!');
		}
		return false;
	},
	updateAreaCar : function(button) {
			var grid = button.up('grid');
			var data = grid.getSelectionModel().getSelection();
			var store = grid.getStore();
			if (data.length>0) {
				Ext.MessageBox.confirm('提示','你确认要更新选中的车辆区域？',
				function(btn) {
					if (btn == "yes") {
						var carnumber ;
						var m = grid.getSelectionModel().getSelection();
						var jsonData = "";
						for ( var i = 0, len = m.length; i < len; i++) {
							var ss = m[i].get("areaid");
							if (i == 0) {
								jsonData = jsonData + ss;
								terminal = m[0].get("terminal");
							} else {
								jsonData = jsonData + "," + ss;
							}
						}
						Ext.Ajax.request( {
							url : window.BIZCTX_PATH + '/areasetjson/updateAreaCar.action?terminal='+terminal,
							method : 'POST',  
							params : "ids="+ jsonData,
							success : function(response) {
								Ext.Msg.alert('提示',"车辆区域更新成功");
								store.reload();
							},
							failure : function() {
								Ext.Msg.alert('提示',"车辆区域更新失败");
								store.reload();
							}
						});
					}
				});
			} else {
				Ext.Msg.alert('提示', '请选择要更新的车辆区域!');
			}
		return false;
	},
	
	editAreaSet : function(button) {
		var menuid = button.id+"";
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条系统区域编辑');
			return;
		}
//		var view = Ext.widget('areaSetEditView');
		var view = Ext.create('AreaSetApp.view.AreaSetEditView');
		view.show();
		var store = this.getAreaSetListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	},
	searchtext : function(button) {
		if(maptype == 1){
			findPOI(Ext.getCmp("warn_attr").getValue());
			return false;
		}else{
			var keyword = Ext.getCmp('warn_attr').getValue();
			if (keyword == null || keyword == ""){
				Ext.Msg.alert('提示',"请输入要搜索的关键字");
				return;
			}
			getwordSearch(keyword);
		    return false;
		}
		
	},
	searchAreaRecord : function(button) {
		var store = this.getAreaSendRecordListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('asr_carnumber').getValue()),
	            	opertype: Ext.getCmp('asr_opertype').getValue(),
	            	areaname: encodeURI(Ext.getCmp('asr_areaname').getValue()),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('asr_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('asr_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	}
	
	
	
});


function clearAll() {
	for(var i = 0; i < overlays.length; i++){
		map.removeOverlay(overlays[i]);
	}
	overlays.length = 0
}

//回调获得覆盖物信息
var overlays = [];
//回调获得覆盖物信息
var overlaycomplete = function(e){
	clearAll();
	overlays.push(e.overlay);

	if (e.drawingMode == BMAP_DRAWING_CIRCLE) {

		top.Ext.MessageBox.confirm(
				'消息',
				'确认要保存圆形区域?',
				function(btn) {
					if (btn == 'yes') {

						var myMask = new Ext.LoadMask(
								Ext.getBody(),
								{
									msg : "正在等待保存圆形区域，请稍后..."
								});
						myMask.show();
						
						//alert("e.overlay.getRadius().toFixed()=="+e.overlay.getRadius());

						Ext.Ajax.request( {
							method : 'POST',
							url : window.BIZCTX_PATH + '/areasetjson/saveAreaSet.action',
							params : {
							'areaSet.id' : id,
							'areaSet.ylng' : e.overlay.getCenter().lng,
							'areaSet.ylat' : e.overlay.getCenter().lat,
							'areaSet.radius' : e.overlay.getRadius(),
							'areaSet.areatype' : areatype,
							'areaSet.areaname' : areaname,
							'areaSet.stime' : stime,
							'areaSet.etime' : etime,
							'areaSet.termvalidity' : termvalidity,
							'areaSet.maxspeed' : maxspeed,
							'areaSet.speedtime' : speedtime,
							'areaSet.attr0' : attr0,
							'areaSet.attr1' : attr1,
							'areaSet.attr2' : attr2,
							'areaSet.attr3' : attr3,
							'areaSet.attr4' : attr4,
							'areaSet.attr5' : attr5
						},
						success : function(
								result) {
							myMask.hide();// 隐藏
							top.Ext.MessageBox.alert('提示','保存圆形区域成功');
							clearAll();
							Ext.StoreManager.get('AreaSetListStore').reload();
						},
						failure : function(result) {
							myMask.hide();// 隐藏
							top.Ext.MessageBox.alert('提示','保存圆形区域失败');
							clearAll();
						}
						});
					}else{
						clearAll();
					}
				});

		drawingManager.close();
	}

	if (e.drawingMode == BMAP_DRAWING_RECTANGLE) {

		top.Ext.MessageBox.confirm(
				'消息',
				'确认要保存矩形区域?',
				function(btn) {
					if (btn == 'yes') {


						var myMask = new Ext.LoadMask(
								Ext.getBody(),
								{
									msg : "正在等待保存矩形区域，请稍后..."
								});
						myMask.show();

						Ext.Ajax.request( {
							method : 'POST',
							url : window.BIZCTX_PATH + '/areasetjson/saveAreaSet.action',
							params : {
							'areaSet.id' : id,
							'areaSet.lnglt' : e.overlay.getPath()[0].lng,
							'areaSet.latlt' : e.overlay.getPath()[0].lat,
							'areaSet.lngrb' : e.overlay.getPath()[2].lng,
							'areaSet.latrb' : e.overlay.getPath()[2].lat,
							'areaSet.areatype' : areatype,
							'areaSet.areaname' : areaname,
							'areaSet.stime' : stime,
							'areaSet.etime' : etime,
							'areaSet.termvalidity' : termvalidity,
							'areaSet.maxspeed' : maxspeed,
							'areaSet.speedtime' : speedtime,
							'areaSet.attr0' : attr0,
							'areaSet.attr1' : attr1,
							'areaSet.attr2' : attr2,
							'areaSet.attr3' : attr3,
							'areaSet.attr4' : attr4,
							'areaSet.attr5' : attr5
						},
						success : function(
								result) {
							myMask.hide();// 隐藏
							top.Ext.MessageBox.alert('提示','保存矩形区域成功');
							clearAll();
							Ext.StoreManager.get('AreaSetListStore').reload();
						},
						failure : function(result) {
							myMask.hide();// 隐藏
							top.Ext.MessageBox.alert('提示','保存矩形区域失败');
							clearAll();
						}
						});
					}else{
						clearAll();
					}
				});
		drawingManager.close();
	}

	if( e.drawingMode == BMAP_DRAWING_POLYGON ){
		var lngsrt = "";
		var latsrt = "";
		for(var i=0 ;i< e.overlay.getPath().length ; i++){

			if((i+1) == e.overlay.getPath().length){
				lngsrt += e.overlay.getPath()[i].lng;
				latsrt += e.overlay.getPath()[i].lat;
			}else{
				lngsrt += e.overlay.getPath()[i].lng +",";
				latsrt += e.overlay.getPath()[i].lat +",";
			}
		}

		top.Ext.MessageBox.confirm(
				'消息',
				'确认要保存多边形区域?',
				function(btn) {
					if (btn == 'yes') {

						var myMask = new Ext.LoadMask(
								Ext.getBody(),
								{
									msg : "正在等待保存多边形区域，请稍后..."
								});
						myMask.show();

						Ext.Ajax.request( {
							method : 'POST',
							url : window.BIZCTX_PATH + '/areasetjson/saveAreaSet.action',
							params : {
							'areaSet.id' : id,
							'areaSet.lngsrt' : lngsrt,
							'areaSet.latsrt' : latsrt,
							'areaSet.areatype' : areatype,
							'areaSet.areaname' : areaname,
							'areaSet.stime' : stime,
							'areaSet.etime' : etime,
							'areaSet.termvalidity' : termvalidity,
							'areaSet.maxspeed' : maxspeed,
							'areaSet.speedtime' : speedtime,
							'areaSet.attr0' : attr0,
							'areaSet.attr1' : attr1,
							'areaSet.attr2' : attr2,
							'areaSet.attr3' : attr3,
							'areaSet.attr4' : attr4,
							'areaSet.attr5' : attr5
						},
						success : function(
								result) {
							myMask.hide();// 隐藏
							top.Ext.MessageBox.alert('提示','保存多边形区域成功');
							clearAll();
							Ext.StoreManager.get('AreaSetListStore').reload();
						},
						failure : function(result) {
							myMask.hide();// 隐藏
							top.Ext.MessageBox.alert('提示','保存多边形区域失败');
							clearAll();
						}
						});
					}else{
						clearAll();
					}
				});
		drawingManager.close();
	}
	
};

function mapElec(keyword) {

	//map.clearOverlays();
	clearAll();
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

function findPOI(addr) {
	mapElec(addr);
}



function getwordSearch(keyword){
		mapObj.clearMap();
	
		var marker = new Array();
		var windowsArr = new Array();
		//基本地图加载
		function placeSearch(){
		    var MSearch;
		    AMap.service(["AMap.PlaceSearch"], function() {       
		        MSearch = new AMap.PlaceSearch({ //构造地点查询类
		            pageSize:3,
		            pageIndex:1,
		            city:"0755" //城市
		        });
		        //关键字查询
		        MSearch.search(keyword, function(status, result){
		        	if(status === 'complete' && result.info === 'OK'){
		        		keywordSearch_CallBack(result);
		        	}
		        }); 
		    });
		}
		placeSearch();
		//添加marker&infowindow   
		function addmarker(i, d) {
		    var lngX = d.location.getLng();
		    var latY = d.location.getLat();
		    var markerOption = {
		        map:mapObj,
		        icon:"http://webapi.amap.com/images/" + (i + 1) + ".png",
		        position:new AMap.LngLat(lngX, latY),
		        topWhenMouseOver:true
		
		    };
		    var mar = new AMap.Marker(markerOption);   
		    marker.push(new AMap.LngLat(lngX, latY));
		    var infoWindow = new AMap.InfoWindow({
		        content:"<h3><font color=\"#00a6ac\">  " + (i + 1) + ". " + d.name + "</font></h3>" + TipContents(d.type, d.address, d.tel),
		        size:new AMap.Size(300, 0),
		        autoMove:true, 
		        offset:new AMap.Pixel(0,-20)
		    });
		    windowsArr.push(infoWindow);
		    var aa = function (e) {infoWindow.open(mapObj, mar.getPosition());};
		    AMap.event.addListener(mar, "mouseover", aa);
		}
		
		
		//回调函数
		function keywordSearch_CallBack(data) {
		    var resultStr = "";
		    var poiArr = data.poiList.pois;
		    var resultCount = poiArr.length;
		    for (var i = 0; i < resultCount; i++) {
		        resultStr += "<div id='divid" + (i + 1) + "'  onmouseover='openMarkerTipById1(" + i + ",this)' onmouseout='onmouseout_MarkerStyle(" + (i + 1) + ",this)' style=\"font-size: 12px;cursor:pointer;padding:0px 0 4px 2px; border-bottom:1px solid #C1FFC1;\"><table><tr><td><img src=\"http://webapi.amap.com/images/" + (i + 1) + ".png\"></td>" + "<td><h3><font color=\"#00a6ac\">名称: " + poiArr[i].name + "</font></h3>";
		            resultStr += TipContents(poiArr[i].type, poiArr[i].address, poiArr[i].tel) + "</td></tr></table></div>";
		            addmarker(i, poiArr[i]);
		    }
		    mapObj.setFitView();
		}
		function TipContents(type, address, tel) {  //窗体内容
		    if (type == "" || type == "undefined" || type == null || type == " undefined" || typeof type == "undefined") {
		        type = "暂无";
		    }
		    if (address == "" || address == "undefined" || address == null || address == " undefined" || typeof address == "undefined") {
		        address = "暂无";
		    }
		    if (tel == "" || tel == "undefined" || tel == null || tel == " undefined" || typeof address =="tel") {
		        tel = "暂无";
		    }
		    var str = "  地址：" + address + "<br />  电话：" + tel + " <br />  类型：" + type;
		    return str;
		}
		function openMarkerTipById1(pointid, thiss) {  //根据id 打开搜索结果点tip
		    thiss.style.background = '#CAE1FF';
		    windowsArr[pointid].open(mapObj, marker[pointid]);
		}
		function onmouseout_MarkerStyle(pointid, thiss) { //鼠标移开后点样式恢复
		    thiss.style.background = "";
		}
}

//定位圆
function  opsitionCircle(record){
	    var circle = new AMap.Circle({ 
		   center:new AMap.LngLat(record.ylng,record.ylat),// 圆心位置
		   radius:record.radius, //半径
		   strokeColor: "#FF33FF", //线颜色
		   strokeOpacity: 1, //线透明度
		   strokeWeight: 3, //线粗细度
		   fillColor: "#FF99FF", //填充颜色
		   fillOpacity: 0.35//填充透明度
	   }); 
	   
	   circle.setMap(mapObj);
}
//定位矩形
function opsitionRectangle(record){
		var arr = new Array(); //构建矩形经纬度坐标数组   
		arr.push(new AMap.LngLat(record.latlt,record.lnglt));//左上
		arr.push(new AMap.LngLat(record.latrb,record.lnglt));
		arr.push(new AMap.LngLat(record.latrb,record.lngrb));//右下
		arr.push(new AMap.LngLat(record.latlt, record.lngrb));
	
		var polygon = new AMap.Polygon({
			map: mapObj,
			path: arr,
			strokeColor: "#0000ff",
			strokeOpacity: 1,
			strokeWeight: 3,
			fillColor: "#8D95D2",
			fillOpacity: 0.35
		}); 
	
		polygon.setOptions(rectangleOption);
}
//定位多边形
function opsitionPolygon(record){
	    var arr = new Array(); //构建多边形经纬度坐标数组   
		var pointLngArr =record.lngsrt.split(',');
		var pointLatArr =record.latsrt.split(',');
		for (var i=0 ; i<pointLngArr.length-1  ; i++){
			   arr.push(new AMap.LngLat(pointLngArr[i], pointLatArr[i]));
		}

		var polygon = new AMap.Polygon({
			map: mapObj,
			path: arr,
		   strokeColor: "#35901A", //线颜色
		   strokeOpacity: 1, //线透明度
		   strokeWeight: 3, //线粗细度
		   fillColor: "#B6D4A7", //填充颜色
		   fillOpacity: 0.35//填充透明度
		}); 
		
		polygon.setOptions(polygonOption);
}

//绘制定位
function getItemPosition(result){

			var getItemFlag=result.areatype;
			if(getItemFlag==1){
				 opsitionCircle(result);
			}else if(getItemFlag==2){
				 opsitionRectangle(result);
			}else if(getItemFlag==3){
				 opsitionPolygon(result);
			}
		
}

function  getItemclick(){
	                mapObj.clearMap(null);
					var grid=Ext.getCmp('sysareasetgrid');
			    	var m = grid.getSelectionModel().getSelection();
			    	for ( var i = 0, len = m.length; i < len; i++) {
					     getItemPosition(m[i].data);
			    	}
					//调整视野到合适的位置及级别                 
			        mapObj.setFitView();
}

