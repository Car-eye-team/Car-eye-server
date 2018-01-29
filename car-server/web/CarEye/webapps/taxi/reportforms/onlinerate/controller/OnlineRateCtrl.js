var row2 = new Array();
var col2 = new Array();
var old_carid2;
var old_carid3;
var row3 = new Array();
var col3 = new Array();
var col4 = new Array();

Ext.define('OnlineRateApp.controller.OnlineRateCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarInfoListStore','TwoCarInfoListStore','ThreeCarInfoListStore'],//声明该控制层要用到的store
    models: ['CarInfoModel'],//声明该控制层要用到的model
    views: ['OnlineRateSearchView','Bottom','TwoView','ThreeView','CarInfoListView',
    	'TwoPrinterView','TwoCarInfoListView','ThreePrinterView','ThreeCarInfoListView'],//声明该控制层要用到的view
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
			'onlineRateSearchView button[action=search]' : {
				click : this.search
			},
			'carInfoListView button[action=export]' : {
				click : this.exportCarInfo
			},
			'carInfoListView button[action=exportWord]' : {
				click : this.exportWord
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
		var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d');
        var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d');
        
        if(etime != null && etime.length > 0){
            var beginTimes = stime.substring(0,10).split('-');
            var endTimes = etime.substring(0,10).split('-');
    
            var stimedate = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2]);
            var etimedate = new Date(endTimes[0], endTimes[1]-1, endTimes[2]);
            
            if(etimedate < stimedate){
                Ext.Msg.alert("提示","开始时间必须小于结束时间");
                return;
            }
        }
        
        var store = this.getCarInfoListStoreStore();
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
		
		var store2 = this.getTwoCarInfoListStoreStore();
		store2.clearFilter(true);
		store2.on('beforeload', function (store2, options) {
	            var new_params = { 
	            	blocid: encodeURI(Ext.getCmp('c_blocid').getValue()),
	            	stime: stime,
	            	etime: etime
	            };
	            Ext.apply(store2.proxy.extraParams, new_params);
	        });
	        store2.loadPage(1);
	        
	    var store3 = this.getThreeCarInfoListStoreStore();
		store3.clearFilter(true);
		store3.on('beforeload', function (store3, options) {
	            var new_params = { 
	            	blocid: encodeURI(Ext.getCmp('c_blocid').getValue()),
	            	stime: stime,
	            	etime: etime
	            };
	            Ext.apply(store3.proxy.extraParams, new_params);
	        });
	        store3.loadPage(1);
	    return false;
	},
	
	
	exportWord : function (button){
    	var blocid = Ext.getCmp('c_blocid').getValue();
    	var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d');
    	var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d');
    	if(etime != null && etime.length > 0){
		            var beginTimes = stime.substring(0,10).split('-');
		            var endTimes = etime.substring(0,10).split('-');
		    
		            var stimedate = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2]);
		            var etimedate = new Date(endTimes[0], endTimes[1]-1, endTimes[2]);
		            
		            if(etimedate < stimedate){
		                Ext.Msg.alert("提示","开始时间必须小于结束时间");
		                return;
		            }
		   }
		
		location.href=window.BIZCTX_PATH + '/onlineratejson/exportWordOnlineRate.action?blocid='+blocid+
		"&stime="+stime+"&etime="+etime;
		
	},
	exportCarInfo : function (button){
    	var blocid = Ext.getCmp('c_blocid').getValue();
    	var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d');
    	var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d');
    	if(etime != null && etime.length > 0){
		            var beginTimes = stime.substring(0,10).split('-');
		            var endTimes = etime.substring(0,10).split('-');
		    
		            var stimedate = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2]);
		            var etimedate = new Date(endTimes[0], endTimes[1]-1, endTimes[2]);
		            
		            if(etimedate < stimedate){
		                Ext.Msg.alert("提示","开始时间必须小于结束时间");
		                return;
		            }
		   }
		
		location.href=window.BIZCTX_PATH + '/onlineratejson/exportExcel.action?blocid='+blocid+
		"&stime="+stime+"&etime="+etime;
		
	},
	setecharts:function() {
	// Step:3 conifg ECharts's path, link to echarts.js from current page.
	// Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径

	// Step:4 require echarts and use it in the callback.
	// Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
	// require( [ 'echarts', 'echarts/chart/bar', 'echarts/chart/line',
	//		'echarts/chart/map' ], function(ec) { 

	require( [ 'echarts', 'echarts/chart/bar', 'echarts/chart/line' ],
			function(ec) {
			
				//--- 折柱 ---
			var myChart2 = ec.init(document.getElementById('echart2'));
			
			var option2 = {
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '在线时长(h)' ]
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
					name : '日期',
					type : 'category',
					data : row2
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
                		formatter: '{value}h'
            		},
					splitArea : {
						show : true
					}
				} ],
				series : [ {
					name : '在线时长',
					type : 'line',
					data : col2
				} ]
			}
			
			myChart2.setOption(option2); 
	});
},
	setecharts3:function() {
	// Step:3 conifg ECharts's path, link to echarts.js from current page.
	// Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径

	// Step:4 require echarts and use it in the callback.
	// Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
	// require( [ 'echarts', 'echarts/chart/bar', 'echarts/chart/line',
	//		'echarts/chart/map' ], function(ec) { 

	require( [ 'echarts', 'echarts/chart/bar', 'echarts/chart/line' ],
			function(ec) {
			
				//--- 折柱 ---
			var myChart3 = ec.init(document.getElementById('echart3'));
			
			//--- 折柱 ---
			var option3 = {
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '上线次数','下线次数' ]
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
					name : '日期',
					type : 'category',
					data : row3
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
                		formatter: '{value}次'
            		},
					splitArea : {
						show : true
					}
				} ],
				series : [ {
						name : '上线次数',
						type : 'line',
						data : col3
					},{
						name : '下线次数',
						type : 'line',
						data : col4
				} ]
			}
			
			myChart3.setOption(option3);
	});
}
	
	
	
});

