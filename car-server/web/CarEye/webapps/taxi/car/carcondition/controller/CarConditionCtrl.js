var row = new Array();
var col1 = new Array();
var col2 = new Array();
var col3 = new Array();
var col4 = new Array();

Ext.define('CarConditionApp.controller.CarConditionCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarConditionStore','CarListStore','CarConditionColumnStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['CarConditionModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['CarConditionSearchView','CarConditionListView','PrinterView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'carConditionListView',
            selector: 'carConditionListView'
        }
    ],
    init: function() {
    	
		this.control({
//			'alarmRiListView button[action=search]' : {
//				click : this.search
//			},
			'carConditionListView button[action=export]' : {
				click : this.exportInfo
			},
			'carConditionListView button[action=exportWord]' : {
				click : this.exportWord
			},
			'carConditionSearchView':{
                afterrender : this.buttonAccess
            },
			'carConditionSearchView #carnumber' : {
				change : this.loadCar
			},
			'carConditionSearchView #blocid' : {
				select : this.loadDeptCar
			}
		});
	},
	buttonAccess : function() {
		var myDate = new Date();
		//将日期设置为当前时间
		var mon=myDate.getMonth()+1;
		if(mon!=10&&mon!=11&&mon!=12){
			mon="0"+mon;
		}
		Ext.getCmp('c_month').setValue(myDate.getFullYear()+'-'+mon);
		return false;
	},
	loadDeptCar : function(){
		Ext.getCmp('c_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var deptid = Ext.getCmp('c_blocid').getValue();
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
	exportWord : function (button){
		var deptid = Ext.getCmp('c_blocid').getValue();

    	var carnumber = Ext.getCmp('c_carnumber').getValue();
    	if(carnumber == null || carnumber == ''){
			Ext.Msg.alert('提示',"请选择车牌号");
			return;
		}
		var month = encodeURI(Ext.util.Format.date(Ext.getCmp('c_month').getValue(), 'Y-m'));
		if(month == null || month == ''){
			Ext.Msg.alert('提示',"请选择月份");
			return;
		}
		
    	location.href= window.BIZCTX_PATH + '/carconditionjson/exportWord.action?blocid='+null
    	+'&carnumber='+carnumber+'&month='+month;
    return false;
	},
	exportInfo : function (button){
			var deptid = Ext.getCmp('c_blocid').getValue();

        	var carnumber = Ext.getCmp('c_carnumber').getValue();
        	if(carnumber == null || carnumber == ''){
				Ext.Msg.alert('提示',"请选择车牌号");
				return;
			}
			var month = encodeURI(Ext.util.Format.date(Ext.getCmp('c_month').getValue(), 'Y-m'));
			if(month == null || month == ''){
				Ext.Msg.alert('提示',"请选择月份");
				return;
			}
			
        	location.href= window.BIZCTX_PATH + '/carconditionjson/exportExcel.action?blocid='+null
        	+'&carnumber='+carnumber+'&month='+month;
        return false;
	},
	search :function(){
		
			var deptid = Ext.getCmp('c_blocid').getValue();

        	var carnumber = Ext.getCmp('c_carnumber').getValue();
        	if(carnumber == null || carnumber == ''){
				Ext.Msg.alert('提示',"请选择车牌号");
				return;
			}
			var month = encodeURI(Ext.util.Format.date(Ext.getCmp('c_month').getValue(), 'Y-m'));
			if(month == null || month == ''){
				Ext.Msg.alert('提示',"请选择月份");
				return;
			}
		
		var store = this.getCarConditionStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	blocid: null,
	            	carnumber: encodeURI(carnumber),
	            	month: month
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	    return false;
	},
	columnInfo:function(button){
		
		var deptid = Ext.getCmp('c_deptid').getValue();

        	var carnumber = Ext.getCmp('c_carnumber').getValue();
        	if(carnumber == null || carnumber == ''){
				Ext.Msg.alert('提示',"请选择车牌号");
				return;
			}
			var month = encodeURI(Ext.util.Format.date(Ext.getCmp('c_month').getValue(), 'Y-m'));
			if(month == null || month == ''){
				Ext.Msg.alert('提示',"请选择月份");
				return;
			}
		var view = Ext.widget('printerView');
		view.show();
		var store = this.getCarConditionColumnStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	deptid: deptid,
	            	carnumber: encodeURI(carnumber),
	            	month: month
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.load(); 
	},
	setecharts1:function() {
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
			var myChart = ec.init(document.getElementById('echart1'));
			myChart.setOption( {
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '里程(km)' ]
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					data : row
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
                		formatter: '{value}km'
            		},
					splitArea : {
						show : true
					}
				} ],
				series : [ {
					name : '里程(km)',
					type : 'line',
					data : col1
				} ]
			});
	});
},
setecharts2:function() {
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
			var myChart = ec.init(document.getElementById('echart2'));
			myChart.setOption( {
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '耗油(升)' ]
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					data : row
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
                		formatter: '{value}升'
            		},
					splitArea : {
						show : true
					}
				} ],
				series : [ {
					name : '耗油(升)',
					type : 'line',
					data : col2
				} ]
			});
	});
},
setecharts3:function() {
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
			var myChart = ec.init(document.getElementById('echart3'));
			myChart.setOption( {
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '故障(个)' ]
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					data : row
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
                		formatter: '{value}km'
            		},
					splitArea : {
						show : true
					}
				} ],
				series : [ {
					name : '故障(个)',
					type : 'line',
					data : col3
				} ]
			});
	});
},
setecharts4:function() {
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
			var myChart = ec.init(document.getElementById('echart4'));
			myChart.setOption( {
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '报警(个)' ]
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					data : row
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
                		formatter: '{value}个'
            		},
					splitArea : {
						show : true
					}
				} ],
				series : [ {
					name : '报警(个)',
					type : 'line',
					data : col4
				} ]
			});
	});
}
});

