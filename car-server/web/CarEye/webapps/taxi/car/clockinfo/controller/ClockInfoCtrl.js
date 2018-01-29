var map;
var mapObj;

Ext.define('ClockInfoApp.controller.ClockInfoCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['ClockInfoListStore','CarListStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['ClockInfoModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['ClockInfoSearchView','ClockInfoListView','ClockMapView','MapView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'clockInfoSearchView',
            selector: 'clockInfoSearchView' 
        },{
            ref: 'clockInfoListView',
            selector: 'clockInfoListView'
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
			'clockInfoSearchView button[action=search]' : {
				click : this.search
			},
			'clockInfoSearchView #carnumber' : {
				change : this.loadCar
			},
			'clockInfoSearchView #blocid' : {
				select : this.loadDeptCar
			}
			
		});
	},
	buttonAccess : function() {
//		var buttonArray = ['130101','130102','130103','130104','130105','130112'];
//		for(var i=0;i<buttonArray.length;i++){
//			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
//				Ext.getCmp(buttonArray[i]).hide();
//			}
//		}
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
	            center:new AMap.LngLat(116.3972,39.9096),//地图中心点
	            zoom:15 //地图显示的缩放级别
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
	            visible:true //初始化隐藏鹰眼
	        });
	        mapObj.addControl(overView);
	    });
	     
	    
	},
	
	
	//百度
    initBaiDuMap:function(div){

        map = new BMap.Map(div);
        //默认位置
        var point = new BMap.Point(116.3972,39.9096);
        map.centerAndZoom(point, 12);
        
        map.enableAutoResize();//解决第二次打开地图时异常问题
 	
        map.centerAndZoom(point, 12);//设置中心点
        
        map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
        map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
        map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
        map.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
        map.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件
        map.addControl(new BMap.MapTypeControl());          //添加地图类型控件
 
    },
	loadDeptCar : function(){
		Ext.getCmp('c_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var blocid = Ext.getCmp('c_blocid').getValue();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	deptid: blocid,
            	carnumber: ""
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	 loadCar : function(){
			var store = this.getCarPageListStoreStore();
			store.clearFilter(true);
			store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('c_carnumber').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.reload();
		},
	search : function(button) {
		var startstime = Ext.util.Format.date(Ext.getCmp('c_startstime').getValue(), 'Y-m-d H:i:s');
        var startetime = Ext.util.Format.date(Ext.getCmp('c_startetime').getValue(), 'Y-m-d H:i:s');
        if(startetime != null && startetime.length > 0){
            var beginTimes = startstime.substring(0,10).split('-');
            var endTimes = startetime.substring(0,10).split('-');
    
            var stimearray = startstime.substring(10,19).split(':');
            var etimearray = startetime.substring(10,19).split(':');
            var stimedate = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
            var etimedate = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
            
            if(etimedate < stimedate){
                Ext.Msg.alert("提示","开始时间必须小于结束时间");
                return;
            }
        }
        var endstime = Ext.util.Format.date(Ext.getCmp('c_endstime').getValue(), 'Y-m-d H:i:s');
        var endetime = Ext.util.Format.date(Ext.getCmp('c_endetime').getValue(), 'Y-m-d H:i:s');
        if(endetime != null && endetime.length > 0){
            var beginTimes = endstime.substring(0,10).split('-');
            var endTimes = endetime.substring(0,10).split('-');
    
            var stimearray = endstime.substring(10,19).split(':');
            var etimearray = endetime.substring(10,19).split(':');
            var stimedate = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
            var etimedate = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
            
            if(etimedate < stimedate){
                Ext.Msg.alert("提示","开始时间必须小于结束时间");
                return;
            }
        }
		
		var store = this.getClockInfoListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('c_carnumber').getValue()),
	            	blocid: encodeURI(Ext.getCmp('c_blocid').getValue()),
	            	terminal: Ext.getCmp('c_terminal').getValue(),
	            	startstime: startstime,
	            	startetime: startetime,
	            	endstime: endstime,
	            	endetime: endetime
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	exportClockInfo : function (button){
        var carnumber = Ext.getCmp('c_carnumber').getValue();
    	var blocid = Ext.getCmp('c_blocid').getValue();
    	var terminal = Ext.getCmp('c_terminal').getValue();
    	var startstime = Ext.util.Format.date(Ext.getCmp('c_startstime').getValue(), 'Y-m-d H:i:s');
    	var startetime = Ext.util.Format.date(Ext.getCmp('c_startetime').getValue(), 'Y-m-d H:i:s');
    	var endstime = Ext.util.Format.date(Ext.getCmp('c_endstime').getValue(), 'Y-m-d H:i:s');
    	var endetime = Ext.util.Format.date(Ext.getCmp('c_endetime').getValue(), 'Y-m-d H:i:s');
		
		location.href=window.BIZCTX_PATH + '/clockjson/exportExcel.action?carnumber='+carnumber+"&blocid="+blocid+
		"&terminal="+terminal+"&startstime="+startstime+"&startetime="+startetime+"&endstime="+endstime+
		"&endetime="+endetime;
		
	},
	clockMap : function (data){
		
		var sbblng = data.sbblng;//上班经度
		var sbblat = data.sbblat;//上班纬度
		var sbbaddress = data.sbbaddress;//上班地址
		var stime = data.stime;//上班时间
				
		var xbblng = data.xbblng;//下班经度
		var xbblat = data.xbblat;//下班纬度
		var xbbaddress = data.xbbaddress;//下班地址
		var etime = data.etime;//下班时间
		
		if(maptype==1){//百度地图
			map.clearOverlays(); //清除上次标记点--百度
			if(sbblng != '' && sbblat != ''){
				var label = new BMap.Label("", {offset: new BMap.Size(0, -30)});	//设置小车label相对小车的偏移量
				label.setStyle({"border":"1px solid #ccc","padding":"2px"});
				label.setContent("地址: " + sbbaddress+ (stime ==undefined ? "" :"<br/>时间: "+stime));
				
				var point1 = new BMap.Point(sbblng,sbblat);
				var startpos = new BMap.Marker(point1, {icon: new BMap.Icon(window.BIZCTX_PATH+"/resource/images/start02.png", new BMap.Size(35, 35), {imageOffset: new BMap.Size(0, 0)})});
				startpos.setLabel(label);
				map.centerAndZoom(point1, 15);//设置中心点
				map.addOverlay(startpos);
			}
			if(xbblng != '' && xbblat != ''){
				var endLabel = new BMap.Label("", {offset: new BMap.Size(0, -30)});	//设置小车label相对小车的偏移量
				endLabel.setStyle({"border":"1px solid #ccc","padding":"2px"});
				endLabel.setContent("地址: " + xbbaddress+ (etime ==undefined ? "" :"<br/>时间: "+etime));
				
				var point2 = new BMap.Point(xbblng,xbblat);
				var endpos = new BMap.Marker(point2, {icon: new BMap.Icon(window.BIZCTX_PATH+"/resource/images/end02.png", new BMap.Size(35, 35), {imageOffset: new BMap.Size(0, 0)})});
				endpos.setLabel(endLabel);
				map.addOverlay(endpos);
			}
		}else{//高德地图
			mapObj.clearMap(); //清除上次标记点--高德
			
			if(sbblng != '' && sbblat != ''){
				//上班点
				var startpos = new AMap.Marker({                   
				   map:mapObj,                  
				   offset:new AMap.Pixel(0,0), //相对于基点的位置
				   icon:new AMap.Icon({image:window.BIZCTX_PATH+"/resource/images/start02.png", imageOffset:new AMap.Size(35, 35)}),
				   position:[sbblng,sbblat]
			    });
			     // 设置label标签
			    startpos.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
			        offset: new AMap.Pixel(0, -35),//修改label相对于maker的位置
			        content: "地址: " + sbbaddress+ (stime ==undefined ? "" :"<br/>时间："+stime)
			    });
			    
			}
			if(xbblng != '' && xbblat != ''){
				//下班点
				var endpos = new AMap.Marker({                   
				   map:mapObj,                  
				   offset:new AMap.Pixel(0,0), //相对于基点的位置
				   icon:new AMap.Icon({image:window.BIZCTX_PATH+"/resource/images/end02.png", imageOffset:new AMap.Size(35, 35)}),
				   position:[xbblng,xbblat]
			    });
				// 设置label标签
			    endpos.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
			        offset: new AMap.Pixel(0, -35),//修改label相对于maker的位置
			        content: "地址: " + xbbaddress+ (etime ==undefined ? "" :"<br/>时间："+etime)
			    });
			}
			
			//移至上班点
			centerPoint = new AMap.LngLat(sbblng,sbblat);
			mapObj.panTo(centerPoint);
			
		}
		
	}
	
	
});

