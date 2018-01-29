Ext.define('FMS.view.ToolbarPanel',{
	extend:'Ext.Toolbar',
	alias:'widget.toolbarPanel',
    height: 25,
    border : false,
    layout: {
     overflowHandler: 'Menu'
    },
    items: [{
    	iconCls : 'icon_car_collapse',
        text: "车辆列表",
	    handler: function () {
	        if (Ext.getCmp('west-panel').collapsed == false) {
	             Ext.getCmp('west-panel').collapse();   
	        }else{
	             Ext.getCmp('west-panel').expand();
	        }
	    }
    },"-",{
    	iconCls : 'icon_reload',
    	text: "重新加载车辆",
    	id: "car_reload",
    	handler: function () {
    		
    		if(admin.blocid == 1 || admin.deptcount > 1){
    			depthflag = true;
    			var store = Ext.StoreManager.get('CarTreeStore');
		        var loadMarsk = new Ext.LoadMask('west-panel', {  
				    msg:'数据加载中，请稍候......',    
				    disabled:false,    
				    store:store  
				});  
				store.clearFilter(true);
					store.on('beforeload', function (store, options) {
			            var new_params = { 
			            	flag :0
			            };
			            Ext.apply(store.proxy.extraParams, new_params);
			            loadMarsk.show()
			        });
		        store.reload();
		        if(admin.blocid == 1 || admin.deptcount > 1){	//除物流公司无下级其它都清除
					var terminalPositionStore = Ext.StoreManager.get('TerminalPositionStore');
		        	terminalPositionStore.removeAll();
//		        
		        	map.clearOverlays();
		        	
		        	//刷新车辆时推送到后台
					realTimeCheckedCar(3,"",600);
			    }
    		}else{
				var deptTree = Ext.StoreManager.get('CarTreeStore');
					deptTree.clearFilter(true);
					deptTree.on('beforeload', function (deptTree, options) {
			            var new_params = { 
			           		 autoload: 1
			            };
			            Ext.apply(deptTree.proxy.extraParams, new_params);
			        });
				    
				     var loadMarsk = new Ext.LoadMask('west-panel', {  
					    msg:'数据加载中，请稍候......',    
					    disabled:false,    
					    store:deptTree  
					});  
					deptTree.addListener('beforeload',function(){loadMarsk.show();}); 
			        deptTree.reload();
    		}
	    
    	}
    },"-",{
    	iconCls : 'icon_car_realtime',
    	text: "车辆实时信息",
    	handler: function () {
	        if (Ext.getCmp('south-panel').collapsed == false) {
	             Ext.getCmp('south-panel').collapse(); 
	        }else{
	              Ext.getCmp('south-panel').expand();
	        }
	    }
    },"-",{
    	iconCls : 'sound',
		tooltip : '报警声音是否打开',
    	action:'openorclose',
    	id : 'oc_voice',
    	handler: function(){
			var con = Ext.create('FMS.controller.Controllers');
			con.opencloseVoice();
		}
    },"-",{
    	text: "<span style='color:red;' id='alarmspan' style='width:120px'>&nbsp;</span>" +
    			"<div id='mod_player'><embed id='song' src='' hidden='true' autostart='false' loop='false'></div>",
    	handler: function(){
			var con = Ext.create('FMS.controller.Controllers');
			con.alarmRecord();
		}
    }
//    ,"->",{
//    	xtype : 'textfield',
//    	id:'seat_text',
//    	allowBlank : false,
//    	regex : /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/,
//		regexText : '电话号码不正确!',
//		width : 140
//    },"-",{
//    	text: "模拟来电",
//	    handler: function(){
//			phonecall();
//		}
//    },"-",{
//    	text: "坐席置忙",
//    	id:'seat_busy',
//	    handler: function(){
//			seat_busy();
//		}
//    },{
//    	text: "坐席置闲",
//    	hidden:true,
//    	id:'seat_free',
//	    handler: function(){
//			seat_free();
//		}
//    },"-",{
//    	text: "保持通话",
//    	id:'seat_hold',
//	    handler: function(){
//			seat_hold();
//		}
//    },{
//    	text: "恢复通话",
//    	hidden:true,
//    	id:'seat_recoverhold',
//	    handler: function(){
//			seat_recoverhold();
//		}
//    },"-",{
//    	text: "挂机",
//	    handler: function(){
//			seat_onhook();
//		}
//    },"-",{
//    	text: "呼叫转接",
//	    handler: function(){
//			seat_phoneswitch();
//		}
//    },"-",{
//    	text: "呼出",
//	    handler: function(){
//			seat_callphone();
//		}
//    },"-",{
//    	text: "多方通话",
//	    handler: function(){
//			seat_manyphone();
//		}
//    }
    ,"->",{
    	iconCls:'map_orange',
        text: "<span style='color:blue;'>地图工具</span>",
        menu:
        {
            items: [{
				    	iconCls : 'mapcar',
				    	text: "拉框选车",
				    	action:'searchcar',
	                    handler: function(){
							var con = Ext.create('FMS.controller.Controllers');
							con.searchcar();
						}
				    },"-",{
				    	iconCls : 'icon_zoomin',
				    	text: "放大",
				    	action : 'zoomIn',
	                    handler: function(){
							var con = Ext.create('FMS.controller.Controllers');
							con.mapZoomIn();
						}
				    },"-",{
				    	iconCls : 'icon_zoomout',
				    	text: "缩小",
				    	action:'zoomOut',
	                    handler: function(){
							var con = Ext.create('FMS.controller.Controllers');
							con.mapZoomOut();
						}
				    },"-",{
				    	iconCls : 'icon_measure',
				    	text: "测距",
				    	action:'measuremap',
	                    handler: function(){
							var con = Ext.create('FMS.controller.Controllers');
							con.measuremap();
						}
				    },"-",{
				    	iconCls : 'icon_clear',
				    	text: "清除",
				    	action:'clearmap',
	                    handler: function(){
							var con = Ext.create('FMS.controller.Controllers');
							con.clearmap();
						}
				    }
            ]
        }
    },"-",{
    	iconCls : 'icon_search',
    	text: "一键导航",
    	action:'searchmap',
	    handler: function(){
				var con = Ext.create('FMS.controller.Controllers');
				con.searchmap();
		}
    }
    ]
});