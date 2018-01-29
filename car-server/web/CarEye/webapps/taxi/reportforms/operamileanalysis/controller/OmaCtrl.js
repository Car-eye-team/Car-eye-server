Ext.Loader.setConfig({enabled:true});
var data = [];
var data1 = [];
var data2 = [];
var data3 = [];

Ext.define('OmaApp.controller.OmaCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarListStore','OmaListStore','CarPageListStore'],//声明该控制层要用到的store
    models: [],//声明该控制层要用到的model
    views: ['OmaListView','OmaSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'omaListView',
            selector: 'omaListView'
        }
    ],
    init: function() {
    	
		this.control({
			'omaSearchView #al_deptid' : {
				select : this.loadDeptCar
			},
			'omaListView' : {
				click : this.search
			}
		});
	},
	loadDeptCar : function() {
		Ext.getCmp('ota_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var deptid = Ext.getCmp('al_deptid').getValue();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
			var new_params = {
				deptid : deptid,
				carnumber : ""
			};
			Ext.apply(store.proxy.extraParams, new_params);
		});
		store.reload();
	},
	exportOma : function (button){
	    var blocid = Ext.getCmp('al_deptid').getValue();
    	var carnumber = encodeURI(Ext.getCmp('ota_carnumber').getValue());
		var stime = encodeURI(Ext.util.Format.date(Ext.getCmp('ota_stime').getValue(), 'Y-m-d'));
		var etime = encodeURI(Ext.util.Format.date(Ext.getCmp('ota_etime').getValue(), 'Y-m-d'));
    	location.href= window.BIZCTX_PATH + '/reportformsjson/exportOma.action?carnumber='+carnumber
    	+'&stime='+stime+'&etime='+etime+'&blocid='+blocid;
    	
	},
	search : function(){
	    
	    var blocid = Ext.getCmp("al_deptid").getValue();
	    var carnumber = Ext.getCmp("ota_carnumber").getValue();
		
		var stime = encodeURI(Ext.util.Format.date(Ext.getCmp('ota_stime').getValue(), 'Y-m-d'));
		if (stime == null || stime == '') {
			Ext.Msg.alert('提示', "请选择开始日期！");
			return;
		}
		var etime = encodeURI(Ext.util.Format.date(Ext.getCmp('ota_etime').getValue(), 'Y-m-d'));
		if (etime == null || etime == '') {
			Ext.Msg.alert('提示', "请选择结束日期！");
			return;
		}
		var con = this;
		Ext.Ajax.request({
		      url: window.BIZCTX_PATH + '/reportformsjson/queryOma.action', //请求的服务器地址
		      method : 'POST',
		      params : {
					blocid:blocid,
					carnumber:encodeURI(carnumber),
					stime:encodeURI(Ext.util.Format.date(Ext.getCmp('ota_stime').getValue(), 'Y-m-d')),
					etime:encodeURI(Ext.util.Format.date(Ext.getCmp('ota_etime').getValue(), 'Y-m-d'))
				},
				success : function(response) {
					con.initStime();
					
					var text = response.responseText;
					var result =  Ext.JSON.decode(text).result;
					
					if(typeof result.data1 != "undefined"){
						
						data1 = result.data1;
						data2 = result.data2;
						data3 = result.data3;
						
						con.initEcharts();	//重新加载图表
						
					}else{
						Ext.Msg.alert('提示', "没有查询到相关数据");
					}
				},
				failure : function() {
					Ext.Msg.alert('提示', "查询失败");
				}
		      
	    });
		

		return false;
	},
	initStime : function(){ //重新初始化横坐标
		var stime = Ext.util.Format.date(Ext.getCmp('ota_stime').getValue(), 'Y-m-d');
		var etime = Ext.util.Format.date(Ext.getCmp('ota_etime').getValue(), 'Y-m-d');
		var st=stime.replace("/-/g","/");
		var et=etime.replace("/-/g","/");
		
		var dtime = (new Date(et)).getTime() - (new Date(st)).getTime(); 
		
	    var days = dtime/(24 * 3600 * 1000) + 1 ;
		data = [];
		var beginTimes = etime.substring(0,10).split('-');
		var dividedate = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2]);
		dividedate.setDate(dividedate.getDate()-(days));
		for(var i=0;i<days;i++){
			dividedate.setDate(dividedate.getDate()+1);
			var sdivide = Ext.Date.format(dividedate, 'm-d'); 
			data.push(sdivide)
		}
	},
	initEcharts : function(){
		 // 使用
     require(
         [
             'echarts',
             'echarts/chart/bar',
             'echarts/chart/line'
         ],
         function (ec,theme) {
             // 基于准备好的dom，初始化echarts图表
             var myChart1 = ec.init(document.getElementById('echart1'),theme);
             var myChart2 = ec.init(document.getElementById('echart2'),theme);
             
             var option1 = {
             	title : {
	                    text: '行驶里程图表'
	                },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:[ {
			            	  name : '行驶里程',
 			            	  textStyle : {color:'black'}
 			            	}]
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
//				            boundaryGap : false,
				            data :data
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            axisLabel : {
				                formatter: '{value}H'
				            }
				        }
				    ],
				    series : [
				        {
				            name:'行驶时长',
				            type:'line',
				            data:data1,
				            markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            },
				            markLine : {
				                data : [
				                    {type : 'average', name: '平均值'}
				                ]
				            }
				        }
				    ]
				};
             var option2 = {
                  	title : {
     	                    text: '空车以及载客里程图表'
     	                },
     				    tooltip : {
     				        trigger: 'axis'
     				    },
     				    legend: {
     			            data:[{
     			            	  name : '空车里程',
     			            	  textStyle : {color:'black'}
     			            	},{
     			            	  name : '载客里程',
     			            	  textStyle : {color:'black'}
     			            	}]
     				    },
     				    toolbox: {
     				        show : true,
     				        feature : {
     				            magicType : {show: true, type: ['line', 'bar']},
     				            restore : {show: true},
     				            saveAsImage : {show: true}
     				        }
     				    },
     				    calculable : true,
     				    xAxis : [
     				        {
     				            type : 'category',
//     				            boundaryGap : false,
     				            data :data
     				        }
     				    ],
     				    yAxis : [
     				        {
     				            type : 'value',
     				            axisLabel : {
     				                formatter: '{value}km'
     				            }
     				        }
     				    ],
     				    series : [
     				        {
     				            name:'空车里程',
     				            type:'bar',
     				            data:data2,
     				            markPoint : {
     				                data : [
     				                    {type : 'max', name: '最大值'},
     				                    {type : 'min', name: '最小值'}
     				                ]
     				            },
     				            markLine : {
     				                data : [
     				                    {type : 'average', name: '平均值'}
     				                ]
     				            }
     				        },{
     				            name:'载客里程',
     				            type:'bar',
     				            data:data3,
     				            markPoint : {
     				                data : [
     				                    {type : 'max', name: '最大值'},
     				                    {type : 'min', name: '最小值'}
     				                ]
     				            },
     				            markLine : {
     				                data : [
     				                    {type : 'average', name: '平均值'}
     				                ]
     				            }
     				        }
     				    ]
     				};
     
             // 为echarts对象加载数据 
             myChart1.setOption(option1);
             myChart2.setOption(option2);
         }
     );
	}
	
	
	
});

