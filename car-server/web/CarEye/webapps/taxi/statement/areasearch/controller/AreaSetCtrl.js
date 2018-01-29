var mapObj;
var marker;
var markermap = new Map();

var map;
var point;
var _record;

var id;
var areatype;
var areaname;
var ylng=null;
var ylat=null;
var radius=null;
var flag=null;
var flagDraw=1;

var latlt=null;
var lnglt=null;
var latrb=null;
var lngrb=null;


var idsStr=null;

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
			    
//设置多边形的属性
var polygonOption = {
		    strokeColor:"#35901A",	
		    strokeOpacity:1,
		    strokeWeight:3,
		    fillColor: "#B6D4A7", //填充颜色
		    fillOpacity: 0.35//填充透明度
};


Ext.define('AreaSetApp.controller.AreaSetCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['AreaSetListStore','CarInfoListStore','CarListStore','ImformationTypeStore'
            ,'AreaCarListStore','AreaSendRecordListStore','OperTypeStore','DateTimeTypeStore'],//声明该控制层要用到的store
    models: ['AreaSetModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['AreaSetSearchView','AreaSetListView','CarInfoListView','AreaSendRecordListView'
             ,'AreaSetAddView','AreaSetEditView','AreaMapView','AreaCarListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'areaSetEditView',
            selector: 'areaSetEditView'
        },{
            ref: 'areaSetListView',
            selector: 'areaSetListView'
        },{
            ref: 'carInfoListView',
            selector: 'carInfoListView'
        },{
            ref: 'areaSetAddView',
            selector: 'areaSetAddView'
        },{
            ref: 'areaSetSearchView',
            selector: 'areaSetSearchView'
        },{
            ref: 'areaMapView',
            selector: 'areaMapView'
        }
    ],
    init: function() {
    	
		this.control({
			'areaMapView': {
                afterrender:function(component, eOpts){
                    //初始化地图
                    render: this.initMap(component.el.dom);
                }
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
			'areaSetListView button[action=searcharea]' : {
				click : this.searchAreaSet
			},
			'areaSetSearchView button[action=searchtext]' : {
				click : this.searchtext
			},
			'areaSetListView':{
                render : this.buttonAccess
            },
			'carInfoListView #carnumber' : {
				change : this.loadCar
			}
		});
	},
	buttonAccess : function() {
		var buttonArray = ['180606','180607','180608'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	loadCar : function(){
		var store = this.getCarListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	carnumber: encodeURI(Ext.getCmp('cas_carnumber').getValue())
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	 /*
        初始化地图
    */
    initMap:function(div){
		//清空map中marker对象
		markermap.clear();
		mapObj = new AMap.Map("areaMapView",{
				resizeEnable: true,
		        rotateEnable:true,
		        dragEnable:true,
		        zoomEnable:true,
		        zooms:[3,18],
		        //二维地图显示视口
		        view: new AMap.View2D({
		            center:new AMap.LngLat(116.397428,39.90923),//地图中心点
		            zoom:10 //地图显示的缩放级别
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
                            mapObj.setZoom(10);
                        }
                    }else{
                    }
                });
            });
        }
        
        showCityInfo();  
    },
	addAreaSet : function(button) {
		var view = Ext.widget('areaSetAddView');
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
		var carnumber = Ext.getCmp('cas_carnumber').getRawValue();
		var blocid = Ext.getCmp('cas_blocid').getValue();
		
		  var grid=Ext.getCmp('sysareasetgrid');
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
		if(jsonData==null||jsonData==''){
		  	 Ext.Msg.alert('提示', '请选择多区域类型!');
			 return;
		 }  
		 
		if (carnumber.length == 0 && typeof blocid == "undefined") {
					Ext.Msg.alert('提示', '请选择企业或者车辆!');
					return;
		}
		var store = Ext.StoreManager.get('CarInfoListStore');
		//var store = this.getCarInfoListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(carnumber),
	            	blocid: blocid,
	            	ids:jsonData
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	       //store.loadPage(1); 
	        store.load();
	    return false;
	},
	lookAreaCar : function(grid, rowIndex, colIndex){//rowIndex，colIndex均从0开始  
		var view = Ext.widget('areaCarListView');
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
		var view = Ext.widget('areaSendRecordListView');
		view.show();
		var store = this.getAreaSendRecordListStoreStore();
		store.load();
		return false;
	},
	saveAreaSet : function(button) {
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
			
//			var areastime = Ext.util.Format.date(Ext.getCmp('stime').getValue(), 'Y-m-d H:i:s');
//	        var areaetime = Ext.util.Format.date(Ext.getCmp('etime').getValue(), 'Y-m-d H:i:s');
//			if(areastime >areaetime){
//				Ext.Msg.alert("提示","开始日期不能大于结束日期!");
//				return;
//			}
//		    
		     areatype = Ext.getCmp('areatype').getValue();
			 
			 areaname = encodeURI(Ext.getCmp('areaname').getValue());
//			 stime = encodeURI(Ext.util.Format.date(Ext.getCmp('stime').getValue(), 'Y-m-d H:i:s'));
//			 etime = encodeURI(Ext.util.Format.date(Ext.getCmp('etime').getValue(), 'Y-m-d H:i:s'));
//			 maxspeed = Ext.getCmp('maxspeed').getValue();
//			 termvalidity = Ext.getCmp('termvalidity').getValue();
//			 speedtime = Ext.getCmp('speedtime').getValue();
//			 attr0 = Ext.getCmp('attr0').getValue()==true?1:0;
//			 attr1 = Ext.getCmp('attr1').getValue()==true?1:0;
//			 attr2 = Ext.getCmp('attr2').getValue()==true?1:0;
//			 attr3 = Ext.getCmp('attr3').getValue()==true?1:0;
//			 attr4 = Ext.getCmp('attr4').getValue()==true?1:0;
//			 attr5 = Ext.getCmp('attr5').getValue()==true?1:0;
			 
			 /**
			  * 划区域
			  */
			 ylng=Ext.getCmp('ylng').getValue();
			 ylat=Ext.getCmp('ylat').getValue();
			 radius=Ext.getCmp('radius').getValue();
//			 latsrt=Ext.getCmp('latsrt').getValue();
//			 lngsrt=Ext.getCmp('lngsrt').getValue();
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
					  Ext.Msg.alert("提示","左键点击地图  开始绘制圆形 松开鼠标左键完成绘制过程!");
			               function gaodecompleteCircle(drawObj){
			          	    ylng=drawObj.getCenter().getLng();
			          	    ylat=drawObj.getCenter().getLat();
			          	    radius=drawObj.getRadius();
			          		Ext.MessageBox.wait('请稍候...', '提示');
							Ext.Ajax.request({
								url : window.BIZCTX_PATH + '/statement/areamorejson/saveAreaMore.action',
								method : 'POST',  
								params : {
									      id:id,
								          areaname:areaname,
								          ylng:ylng,
										  ylat:ylat,
										  radius:radius,
										  latlt:null,
										  lnglt:null,
										  latrb:null,
										  lngrb:null,
										  areatype:areatype
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
			    Ext.Msg.alert("提示","左键点击地图  开始绘制矩形形 松开鼠标左键完成绘制过程!");
				    
			    function  gaodecomplete(lnglat){
					           var array = lnglat.split(",")
						      //lat > lat1  lng1>lng
							   latlt = array[0];
							   lnglt = array[1];
							   latrb = array[2];
							   lngrb = array[5];
								
				          		Ext.MessageBox.wait('请稍候...', '提示');
								Ext.Ajax.request({
									url : window.BIZCTX_PATH + '/statement/areamorejson/saveAreaMore.action',
									method : 'POST',  
									params : {
										      id:id,
									          areaname:areaname,
									          ylng:null,
											  ylat:null,
											  radius:null,
											  latlt:latlt,
											  lnglt:lnglt,
											  latrb:latrb,
											  lngrb:lngrb,
											  areatype:areatype
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


			}
			 
			
			 win.close();

			return false;
	},
	deleteAreaSet : function(button) {
			var grid = button.up('grid');
			var data = grid.getSelectionModel().getSelection();
			var store = grid.getStore();
			if (data.length>0) {
				Ext.MessageBox.confirm('提示','你确认要删除选中的多区域？',
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
									url : window.BIZCTX_PATH + '/statement/areamorejson/deleteAreaMore.action',
									method : 'POST',  
									params : "ids="+ jsonData,
									success : function(response) {
										var text = response.responseText;
									    var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					    var su = obj.result.su;
										if( su == -2){
											// Ext.Msg.alert('提示',"系统区域被车辆追加的将不能删除");
										}else if(su == 1){
											Ext.Msg.alert('提示',"多区域删除成功");
										}else {
											Ext.Msg.alert('提示',"多区域删除失败");
										}
										store.reload();
									},
									failure : function() {
										Ext.Msg.alert('提示',"多区域删除失败");
										store.reload();
									}
								});
					}
				});
			} else {
				Ext.Msg.alert('提示', '请选择要删除的多区域!');
			}
		return false;
	},
	editAreaSet : function(button) {
		var menuid = button.id+"";
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条多区域编辑');
			return;
		}
		var view = Ext.widget('areaSetEditView');
		view.show();
		var store = this.getAreaSetListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		
		flag=1;
		return false;
	},
	searchtext : function(button) {
		var keyword = Ext.getCmp('warn_attr').getValue();
		if (keyword == null || keyword == ""){
			Ext.Msg.alert('提示',"请输入要搜索的关键字");
			return;
		}
		getwordSearch(keyword);
		Ext.getCmp('centerListView').expand();
	    return false;
	},
	 showCircleCarLocal:function(lng, lat, mileage){
		var store = Ext.StoreManager.get('CarInfoListStore');
		store.clearFilter(true);
			var grid=Ext.getCmp('sysareasetgrid');
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
								url: window.BIZCTX_PATH + '/statement/areamorejson/queryCarPositionInfoList.action',  //请求的服务器地址
//									url : window.BIZCTX_PATH + '/textinfojson/queryCarInfoList.action',
									method : 'POST',  
									params : {
											ids:jsonData
											
								            },
									success : function(response) {
										var text = response.responseText;
										var obj = eval( "(" + text + ")" );//转换后的JSON对象 
										var arrayObj = new Array(); 
										arrayObj = obj.result.list;
										for(var i=0;i<arrayObj.length;i++){
											 var glng =  arrayObj[i].glng;
											 var glat =  arrayObj[i].glat;
											 var colortype = arrayObj[i].colortype;
											 var carnumber = arrayObj[i].carnumber;
											 var direction = arrayObj[i].direction;
											 var carstatus = arrayObj[i].carstatus;
											 var icon = getIcon(carstatus);
											 //var icon ="http://webapi.amap.com/images/custom_a_j.png";
											locationMapCloseWindow(glng, glat,carnumber,carstatus,icon,direction,1,colortype);
										  
										}
									}
								}); 
	},
	showRectangleCarLocal:function(latlt,latrb,lnglt,lngrb){
		var store = Ext.StoreManager.get('CarInfoListStore');
		store.clearFilter(true);
		var grid=Ext.getCmp('sysareasetgrid');
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
								url: window.BIZCTX_PATH + '/statement/areamorejson/queryCarPositionInfoList.action',  //请求的服务器地址
//									url : window.BIZCTX_PATH + '/textinfojson/queryCarInfoList.action',
									method : 'POST',  
									params : {
											ids:jsonData
								            },
									success : function(response) {
										var text = response.responseText;
										var obj = eval( "(" + text + ")" );//转换后的JSON对象 
										var arrayObj = new Array(); 
										arrayObj = obj.result.list;
										for(var i=0;i<arrayObj.length;i++){
											 var glng =  arrayObj[i].glng;
											 var glat =  arrayObj[i].glat;
											 var colortype = arrayObj[i].colortype;
											 var carnumber = arrayObj[i].carnumber;
											 var direction = arrayObj[i].direction;
											 var carstatus = arrayObj[i].carstatus;
											 var icon = getIcon(carstatus);
											 //var icon ="http://webapi.amap.com/images/custom_a_j.png";
											locationMapCloseWindow(glng, glat,carnumber,carstatus,icon,direction,1,colortype);
										  
										}
									}
								}); 
	}
});


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
				 var control = Ext.create('AreaSetApp.controller.AreaSetCtrl'); 
				 control.showCircleCarLocal(result.ylng, result.ylat, result.radius);
			}else if(getItemFlag==2){
				 opsitionRectangle(result);
				 var control = Ext.create('AreaSetApp.controller.AreaSetCtrl'); 
				 control.showRectangleCarLocal(result.latlt,result.latrb, result.lnglt, result.lngrb);
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
function getItemClickList(){
	  var grid=Ext.getCmp('sysareasetgrid');
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
	  var store = Ext.StoreManager.get('CarInfoListStore');
	  
	  store.on('beforeload', function (store, options) {
			var new_params = { 
							ids:jsonData
							  };
			Ext.apply(store.proxy.extraParams, new_params);
	  });
	  store.load()
}

//地图定位,带关闭提示框
function locationMapCloseWindow(posX, posY,carnumber,carstatus,icon,direction,flag,colortype){
		var infoWindow;
		if(posX && posY){
			   var marker = new AMap.Marker({                   
				   map:mapObj,                  
				   position:new AMap.LngLat(posX,posY), //位置
				   icon:icon,
				   title:carnumber,
				   angle:direction,
				   topWhenClick:true
			   });
			   
			    //自定义点标记内容  
			    var markerContent = document.createElement("div");
			    markerContent.className = "gaodemarkerContentStyle";
			    //点标记中的图标
			    var markerImg= document.createElement("img");
			    markerImg.className="markerlnglat";
			    markerImg.src=icon;   
			    markerContent.appendChild(markerImg);
			      
			    //点标记中的文本
			    var markerSpan = document.createElement("span");
			    markerSpan.innerHTML = carnumber;
			    markerContent.appendChild(markerSpan);
			    marker.setContent(markerContent);//更新点标记内容
			   
				mapObj.setCenter(marker.getPosition());  //标注点显示为地图中心点
			    AMap.event.addListener(marker,'click',function(){ //鼠标点击marker弹出自定义的信息窗体
			        Ext.Ajax.request( {
					  url: window.BIZCTX_PATH + '/terminalpositionjson/queryCarTerminalDetail.action', //请求的服务器地址
				      params:{ 
				      		carnumber : carnumber
				      },
					  method : 'POST',  
					  timeout : 5000,
					  success : function(response) {
						 var text = response.responseText;
						 var objtext = eval( "(" + text + ")" );//转换后的JSON对象 
						 var obj = objtext.result;
						 var carid =  obj.data.carid;
						 var glng =  obj.data.glng;
						 var glat =  obj.data.glat;
						 var gaddress = obj.data.gaddress;
						 var carnumber = obj.data.carnumber;
						 var address = obj.data.address;
						 var icon = getIcon(obj.data.carstatus);
						 var carstatus = getCarstatusText(obj.data.carstatus);
						 var createtime = obj.data.createtime;
						 var speed = obj.data.speed;
						 var blocname = obj.data.blocname;
						 var mileage = obj.data.mileage;
						 
						 var terminalnum = obj.data.terminal;
						 var cartype = obj.data.cartype;
						 
						 var onedrivername = obj.data.onedrivername==null?"":obj.data.onedrivername;
						 var twodrivername = obj.data.twodrivername==null?"":obj.data.twodrivername;
						 var onephone = obj.data.onephone==null?"":obj.data.onephone;
						 var twophone = obj.data.twophone==null?"":obj.data.twophone;
						 var phone = obj.data.phone==null?"":obj.data.phone;
						 var buytime = obj.data.buytime==null?"":obj.data.buytime;
						 var usename = obj.data.usename==null?"":obj.data.usename;
						 var oiltype = obj.data.oiltype==null?"":obj.data.oiltype;
						 var drivlicnum = obj.data.drivlicnum==null?"":obj.data.drivlicnum;
						 var ventingmeasure = obj.data.ventingmeasure==null?"":obj.data.ventingmeasure;
						 var enginenumber = obj.data.enginenumber==null?"":obj.data.enginenumber;
						 var altitude = obj.data.altitude==null?"":obj.data.altitude;
						var info = [];
					    info.push("</div></div><table style='font-size: 12px;text-align: right;'><tr><td><font color = '#0000ff'><b>单位：</b></font></td><td style='text-align: left'><font color='#004a91'>"+deptname+"</font></td><td width='75px;'><font color = '#0000ff'><b>驾驶员：</b></font></td><td style='text-align: left'><font color='#004a91'>"+drivername+"</font></td></tr>"); 
					    info.push("<tr><td width='75px;'><font color = '#0000ff'><b>设备：</b></font></td><td style='text-align: left'><font color='#004a91'>"+terminalnum+"</font></td><td width='75px;'><font color = '#0000ff'><b>司机手机号：</b></font></td><td style='text-align: left'><font color='#004a91'>"+phone+"</font></td></tr>"); 
					    info.push("<tr><td width='75px;'><font color = '#0000ff'><b>车牌：</b></font></td><td style='text-align: left'><font color='#004a91'>"+carnumber+"</font></td><td width='75px;'><font color = '#0000ff'><b>车型：</b></font></td><td style='text-align: left'><font color='#004a91'>"+cartype==null?"":cartype+"</font></td></tr>"); 
					    info.push("<tr><td width='75px;'><font color = '#0000ff'><b>速度：</b></font></td><td style='text-align: left'><font color='#004a91'>"+speed+"(km/h)</font></td><td width='75px;'><font color = '#0000ff'><b>车辆状态：</b></font></td><td style='text-align: left'><font color='#004a91'>"+chageCarStatus(carstatus)+"</font></td></tr>"); 
					    info.push("<tr><td width='75px;'><font color = '#0000ff'><b>时间：</b></font></td><td style='text-align: left'><font color='#004a91'>"+createtime+"</font></td></tr>"); 
					    info.push("<tr><td width='75px;'><font color = '#0000ff'><b>位置：</b></font></td><td colspan='3' style='text-align: left'><font color='#004a91'>"+address+"</font></td></tr></table>"); 
					       
					    infoWindow = new AMap.InfoWindow({ 
					        content:info.join(""),  //使用默认信息窗体框样式，显示信息内容
					        offset:new AMap.Pixel(0, -20)//-113, -140
					    });
						infoWindow.open(mapObj,marker.getPosition()); 
			   		},
					failure : function() {
					}
				});
			});
			   //marker添加至map中
			  markermap.put(carnumber,marker,2);
			  //调整视野到合适的位置及级别                 
		      mapObj.setFitView();
		}
      
}
function Map() {
    this.elements = new Array();

    //获取MAP元素个数
    this.size = function() {
        return this.elements.length;
    };

    //判断MAP是否为空
    this.isEmpty = function() {
        return (this.elements.length < 1);
    };

    //删除MAP所有元素
    this.clear = function() {
        this.elements = new Array();
    };

    //向MAP中增加元素（key, value) 
    this.put = function(_key, _value,type) {
    	this.remove(_key);
    	//判断是否存在
        this.elements.push( {
            key : _key,
            value : _value
        });
    };

    //删除指定KEY的元素，成功返回True，失败返回False
    this.remove = function(_key) {
        var bln = false;
        try {
        	var size = this.elements.length;
            for (var i = 0; i < size; ++ i) {
                if (this.elements[i].key == _key) {
            		this.elements[i].value.setMap(null);
            		mapObj.clearInfoWindow();
                    this.elements.splice(i, 1);
                    -- i;
                     size = this.elements.length;
                    continue;
                }
            }
             return true;
        } catch (e) {
            bln = false;
        }
        return bln;
    };

    //获取指定KEY的元素值VALUE，失败返回NULL
    this.get = function(_key) {
        try {
            for (var i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    return this.elements[i].value;
                }
            }
        } catch (e) {
            return null;
        }
    };

    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
    this.element = function(_index) {
        if (_index < 0 || _index >= this.elements.length) {
            return null;
        }
        return this.elements[_index];
    };

    //判断MAP中是否含有指定KEY的元素
    this.containsKey = function(_key) {
        var bln = false;
        try {
            for (var i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };

    //判断MAP中是否含有指定VALUE的元素
    this.containsValue = function(_value) {
        var bln = false;
        try {
            for (var i = 0; i < this.elements.length; i++) {
                if (this.elements[i].value == _value) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };

    //获取MAP中所有VALUE的数组（ARRAY）
    this.values = function() {
        var arr = new Array();
        for (var i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].value);
        }
        return arr;
    };

    //获取MAP中所有KEY的数组（ARRAY）
    this.keys = function() {
        var arr = new Array();
        for (var i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].key);
        }
        return arr;
    };
}

function getIcon(carstatus){
	var icon = "";
	if(carstatus == 7){
		icon = window.BIZCTX_PATH +"/resource/images/inline_1.png";
	}else if(carstatus == 1){
		icon = window.BIZCTX_PATH +"/resource/images/offline_1.png";
	}else if(carstatus == 2){
		icon = window.BIZCTX_PATH +"/resource/images/offline_1.png";
	}else if(carstatus == 3){
		icon = window.BIZCTX_PATH +"/resource/images/shutdown_1.png";
	}else if(carstatus == 5){
		icon = window.BIZCTX_PATH +"/resource/images/drivering_1.png";
	}else if(carstatus == 4){
		icon = window.BIZCTX_PATH +"/resource/images/stop_1.png";
	}else if(carstatus == 6){
		icon = window.BIZCTX_PATH +"/resource/images/warn_1.png";
	}else{
		icon = window.BIZCTX_PATH +"/resource/images/drivering_1.png";
	}
	return icon;
}
