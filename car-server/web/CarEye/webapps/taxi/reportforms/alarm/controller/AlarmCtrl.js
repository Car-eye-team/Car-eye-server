Ext.define('AlarmApp.controller.AlarmCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['AlarmStore','CarListStore','AlarmTypeStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['AlarmModel','CarInfoModel','AlarmTypeModel'],//声明该控制层要用到的model
    views: ['AlarmListView','SearchMapView','AlarmSreachView','MapView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'alarmListView',
            selector: 'alarmListView'
        }
    ],
    init: function() {
    	
		this.control({
//			'alarmListView button[action=search]' : {
//				click : this.search
//			},
//			'alarmListView button[action=export]' : {
//				click : this.exportInfo
//			},
			'alarmSreachView #al_deptid' : {
				select : this.loadDeptCar
			},
			'alarmListView #carnumber' : {
				change : this.loadCar
			},
			'mapView': {
                	afterrender:function(component, eOpts){
                    //初始化地图
                   	 render: this.initMaps(component.el.dom);
             }
            },
            'alarmListView #al_deptid' : {
				select : this.loadDeptCar
			}
		});
	},
	loadDeptCar : function(){
		Ext.getCmp('alarm_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var deptid = Ext.getCmp('al_deptid').getValue();
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
	/*
	    初始化地图
	*/
	initMaps:function(div){
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
	    
	     //在地图中添加鹰眼插件
	    mapObj.plugin(["AMap.OverView"],function(){
	        //加载鹰眼
	        overView = new AMap.OverView({
	            visible:true //初始化隐藏鹰眼
	        });
	        mapObj.addControl(overView);
	    });
	      //地图加载完后操作
		eventListener = AMap.event.addListener(mapObj,'complete',function(){
			var lng = Ext.getCmp('c_lng').getValue();
			var lat = Ext.getCmp('c_lat').getValue();
			var address = Ext.getCmp('c_cwaaddr').getValue();
			var createtime = Ext.getCmp('c_createtime').getValue();
			var carnumber =  Ext.getCmp('c_carnumber').getValue();
			var direction =  Ext.getCmp('c_direction').getValue();
			var icon = window.BIZCTX_PATH +"/resource/images/warn_1.png";
			
	     	centerPoint = new AMap.LngLat(lng,lat);
			mapObj.setZoomAndCenter(18,centerPoint); 
			
			var marker = new AMap.Marker({                   
			   	map:mapObj,                  
			   	icon:new AMap.Icon({image:icon, imageOffset:new AMap.Size(30, 30)}),
			   	position:centerPoint
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
				
		    
				
		    var info = [];
			info.push("<font color = '#0000ff'></font><font color='red'>"+(carnumber ==undefined ? "" :carnumber)+"</font>"+"</br>"); 
			info.push("<font><b>地址：</b></font><font>"+(address ==undefined ? "" :address)+"</font>"); 
			info.push("<font><b>报警时间：</b></font><font>"+(createtime ==undefined ? "" :createtime)+"</font>"); 
		    infoWindow = new AMap.InfoWindow({ 
		        content:info.join("<br/>"),  //使用默认信息窗体框样式，显示信息内容
		        offset:new AMap.Pixel(5, -15)//-113, -140
		    });
		    AMap.event.addListener(marker,'click',function(){ //鼠标点击marker弹出自定义的信息窗体
		         infoWindow.open(mapObj,marker.getPosition()); 
		    }); 
		   	infoWindow.open(mapObj,marker.getPosition()); //开启信息窗口
			marker.setMap(mapObj);  
	    });
	    
	},
	
	
	//百度
	initBaiDuMap:function(div){
	
	    tmap = new BMap.Map(div);
		// 默认位置
		var point = new BMap.Point(121.597998,29.888658);
		tmap.centerAndZoom(point, 8);
					
	    tmap.enableAutoResize();//解决第二次打开地图时异常问题
	    
	    tmap.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
	    tmap.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	    tmap.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
	    tmap.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
	    tmap.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件
	    //tmap.addControl(new BMap.MapTypeControl());          //添加地图类型控件
	   
	    //自定义控件
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
		  // 添加文字说明
		  div.appendChild(document.createTextNode("关闭"));
		  // 设置样式
		  div.style.cursor = "pointer";
		  div.style.border = "1px solid gray";
		  div.style.backgroundColor = "red";
		  // 绑定事件,关闭窗口
		  div.onclick = function(e){
		    var win = Ext.getCmp('searchMapView');
		    win.close();
		  }
		  // 添加DOM元素到地图中
		  tmap.getContainer().appendChild(div);
		  // 将DOM元素返回
		  return div;
		}
		// 创建控件
		var myZoomCtrl = new ZoomControl();
		// 添加到地图当中
		tmap.addControl(myZoomCtrl);
	
	    
		var tmapflag = true;
		  //地图加载完后操作
	    tmap.addEventListener("tilesloaded",function(){
			if(tmapflag){
				var lng=Ext.getCmp('c_lng').getValue();
				var lat=Ext.getCmp('c_lat').getValue();
				var address=Ext.getCmp('c_cwaaddr').getValue();
				var createtime=Ext.getCmp('c_createtime').getValue();
				var carnumber =  Ext.getCmp('c_carnumber').getValue();
				var direction =  Ext.getCmp('c_direction').getValue();
				var icon = window.BIZCTX_PATH +"/resource/images/warn_1.png";
				
		     	var point = new BMap.Point(lng,lat);
				tmap.centerAndZoom(point, 18);
				var myIcon = new BMap.Icon(icon, new BMap.Size(30,30));
				var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
				
				label = new BMap.Label("", {offset: new BMap.Size(0, -20)});	//设置小车label相对小车的偏移量
				label.setStyle({"border":"1px solid #ccc","padding":"2px"});
				label.setContent("<font color='#004a91'>"+carnumber+"</font>");
				marker.setTop(true);
				marker.setLabel(label);
				marker.setRotation(direction);
				tmap.addOverlay(marker);              // 将标注添加到地图中
						
				        var opts = {
						  width : 200,     // 信息窗口宽度
//						  height: 60,     // 信息窗口高度
						  title : "<font color='red'>"+carnumber+"</font>", // 信息窗口标题
						  enableMessage:false//设置允许信息窗发送短息
						}
						var infoWindow = new BMap.InfoWindow("地址："+address+"</br>报警时间："+createtime, opts);  // 创建信息窗口对象
						marker.addEventListener("click", function(){this.openInfoWindow(infoWindow);});
						tmap.openInfoWindow(infoWindow,point); //开启信息窗口
						tmapflag = false;
			}
	    });
	   
	},
	loadCar : function(){
		var store = this.getCarPageListStoreStore();
		var carnumber = Ext.getCmp('alarm_carnumber').getRawValue();
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
	exportInfo : function (button){
		    var blocid = Ext.getCmp('al_deptid').getValue();
        	var carnumber = Ext.getCmp('alarm_carnumber').getValue();
        	var alarmkey = Ext.getCmp('alarm_alarmkey').getValue();
			var stime = encodeURI(Ext.util.Format.date(Ext.getCmp('alarm_stime').getValue(), 'Y-m-d H:i:s'));
			var etime = encodeURI(Ext.util.Format.date(Ext.getCmp('alarm_etime').getValue(), 'Y-m-d H:i:s'));
        	location.href= window.BIZCTX_PATH + '/reportformsjson/exportExcel.action?carnumber='+carnumber
        	+'&alarmkey='+alarmkey+'&stime='+stime+'&etime='+etime+'&blocid='+blocid;
	},
	search :function(){
		var store = this.getAlarmStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	blocid: Ext.getCmp('al_deptid').getValue(),
	            	carnumber: encodeURI(Ext.getCmp('alarm_carnumber').getValue()),
	            	alarmkey: encodeURI(Ext.getCmp('alarm_alarmkey').getValue()),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('alarm_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('alarm_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	    return false;
	},
	deleteInfo : function(button) {
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length>0) {
			Ext.MessageBox.confirm('提示','你确认要删除选中的数据？',
			function(btn) {
				if (btn == "yes") {
					var m = grid.getSelectionModel().getSelection();
					var jsonData = "";
					var ters = "";
					for ( var i = 0, len = m.length; i < len; i++) {
						var ss = m[i].get("carnumber");
						var tt =  m[i].get("alarmtime");
						if (i == 0) {
							jsonData = jsonData + ss;
							ters = ters + tt;
						} else {
							jsonData = jsonData + "," + ss;
							ters = ters + "," + tt;
						}
					}
					
					Ext.Ajax.request( {
								url : window.BIZCTX_PATH + '/reportformsjson/deleteInfo.action',
								method : 'POST',  
								params : "cars="+ jsonData+"&times="+ters,
								success : function(response) {
									Ext.Msg.alert('提示',"数据删除成功");
									store.reload();
								},
								failure : function() {
									Ext.Msg.alert('提示',"数据删除失败");
									store.reload();
								}
							});
				}
			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的数据项!');
		}
		return false;
	}
	
	
	
	
});

