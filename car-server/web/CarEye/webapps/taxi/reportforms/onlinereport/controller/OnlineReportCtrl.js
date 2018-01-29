var col = new Array();
var type = 0;

Ext.define('OnlineReportApp.controller.OnlineReportCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['OnlineReportListStore','CarInfoListStore'],//声明该控制层要用到的store
    models: ['OnlineReportModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['OnlineReportSearchView','OnlineReportListView','PrinterView','CarInfoListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'onlineReportSearchView',
            selector: 'onlineReportSearchView' 
        },{
            ref: 'onlineReportListView',
            selector: 'onlineReportListView'
        }
    ],
    init: function() {
    	
		this.control({
			'onlineReportSearchView button[action=search]' : {
				click : this.search
			}
//			'onlineReportSearchView #carnumber' : {
//				change : this.loadCar
//			},
//			'onlineReportSearchView #blocid' : {
//				select : this.loadDeptCar
//			}
			
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
	loadDeptCar : function(){
		var store = this.getCarListStoreStore();
		var blocid = Ext.getCmp('c_blocid').getValue();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	blocid: blocid,
            	carnumber: ""
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	 loadCar : function(){
			var store = this.getCarListStoreStore();
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
		var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s');
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
		
		var store = this.getOnlineReportListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	blocid: encodeURI(Ext.getCmp('c_blocid').getValue()),
	            	stime: stime,
	            	etime: etime
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	exportCo : function (button){
		var blocid = Ext.getCmp('c_blocid').getValue();
    	var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s');
    	var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s');
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
	    location.href= window.BIZCTX_PATH + '/onlinereportjson/exportCo.action?blocid='+blocid+
		"&type="+type+"&stime="+stime+"&etime="+etime;
	},
	exportCarInfo : function (button){
    	var blocid = Ext.getCmp('c_blocid').getValue();
    	var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s');
    	var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s');
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
		
		location.href=window.BIZCTX_PATH + '/onlinereportjson/exportExcel.action?blocid='+blocid+
		"&type="+type+"&stime="+stime+"&etime="+etime;
		
	},
	setecharts:function() {
	// Step:3 conifg ECharts's path, link to echarts.js from current page.
	// Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径

	require.config( {
		paths : {
			 echarts: window.BIZCTX_PATH + '/resource/echarts-2.2.7/dist'
		}
	});

	// Step:4 require echarts and use it in the callback.
	// Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
	// require( [ 'echarts', 'echarts/chart/bar', 'echarts/chart/line',
	//		'echarts/chart/map' ], function(ec) { 

	require( [ 'echarts', 'echarts/chart/bar', 'echarts/chart/line' ],
			function(ec) {
				//--- 折柱 ---
			var myChart = ec.init(document.getElementById('echart'));
			myChart.setOption( {
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '数量(辆)' ]
				},
				toolbox : {
					show : true,
					feature : {
						mark : {
							show : true
						},
						dataView : {
							show : true,
							readOnly : false
						},
						magicType : {
							show : true,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					data : [ '新增车辆', '上线车辆', '未上线车辆' ]
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
                		formatter: '{value}辆'
            		},
					splitArea : {
						show : true
					}
				} ],
				series : [ {
					name : '数量(辆)',
					type : 'bar',
					data : col
				} ]
			});
			myChart.on('click', function (param) {
				var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s');
		        var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s');
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
		        
		        
//		        var vv = Ext.getCmp('printerView');
//		        	vv.getEl().mask();
//		        	if(view.hidden()){
//			        	vv.getEl().unmask();
//			        }
		        
		        var view = Ext.widget('carInfoListView');
					view.show();
		        
		        var xname = param.name;
		        if(xname == "新增车辆"){
		        	type = 1;
		        	
					var store = Ext.StoreManager.get("CarInfoListStore");
//		        	var store = this.getCarInfoListStoreStore();
					store.clearFilter(true);
					store.on('beforeload', function (store, options) {
				            var new_params = { 
				            	blocid: encodeURI(Ext.getCmp('c_blocid').getValue()),
				            	stime: stime,
				            	etime: etime,
				            	type : type
				            };
				            Ext.apply(store.proxy.extraParams, new_params);
				        });
//				        store.load();
				        store.loadPage(1); 
				        //store.reload();
				    return false;
		        }else if(xname == "上线车辆"){
		        	type = 2;
		        	Ext.getCmp('carInfoListView').setTitle("上线车辆明细");
		        	
		        	var store = Ext.StoreManager.get("CarInfoListStore");
//		        	var store = this.getCarInfoListStoreStore();
					store.clearFilter(true);
					store.on('beforeload', function (store, options) {
				            var new_params = { 
				            	blocid: encodeURI(Ext.getCmp('c_blocid').getValue()),
				            	stime: stime,
				            	etime: etime,
				            	type : type
				            };
				            Ext.apply(store.proxy.extraParams, new_params);
				        });
//				        store.load();
				        store.loadPage(1); 
				        //store.reload();
				    return false;
		        }else if(xname == "未上线车辆"){
		        	type = 3;
		        	Ext.getCmp('carInfoListView').setTitle("未上线车辆明细");
		        	
		        	var store = Ext.StoreManager.get("CarInfoListStore");
//		        	var store = this.getCarInfoListStoreStore();
					store.clearFilter(true);
					store.on('beforeload', function (store, options) {
				            var new_params = { 
				            	blocid: encodeURI(Ext.getCmp('c_blocid').getValue()),
				            	stime: stime,
				            	etime: etime,
				            	type : type
				            };
				            Ext.apply(store.proxy.extraParams, new_params);
				        });
//				        store.load();
				        store.loadPage(1); 
				        //store.reload();
				    return false;
		        }
		        
		});
	});
}
	
	
	
});

