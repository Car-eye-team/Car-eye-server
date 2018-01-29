Ext.define('FMS.view.Center',{
		extend:'Ext.tab.Panel',
		//layout:'fit',
		//注意 加上widget.
		alias:'widget.center',
		region:'center',
		loadMask: true,
		activeTab:0, //指定默认的活动tab
		plugins : Ext.create('Ext.ux.TabCloseMenu', {  
                closeTabText : '关闭当前页',  
                closeOthersTabsText : '关闭其他页',  
                closeAllTabsText : '关闭所有页'  
         }),  
        resizeTabs: true,
		enableTabScroll: true, //当Tab标签过多时,出现滚动条
		id:'taxiMapPanel',
		margins: '0 0 0 0',
		items:[{
	    	   title:'我的工作台',
	    	   layout:'fit',
	    	   id:'myWorkPlacePanal',
	    	   html:"<iframe id='myWorkPlace' width=100% height=100% frameborder='no' style='padding:5px;' border='0' marginwidth='0'" +
	    	   		"marginheight='0' scrolling='auto'  src='" + workplaceurl + "'></iframe>"
	       },{
		    	   title:'车辆实时监控',
		    	   layout:'fit',
		    	   id:'taximap',
		    	   items:[{xtype : 'mapView'}]
		       }
		],
		initComponent:function(){
			this.callParent(arguments);
		},
		listeners: {
        	'tabchange' : function(tab, newc, oldc) {
	        	if(newc.id == "taximap"){	//监控中心
					Ext.getCmp('west-panel').expand();
					
//				  //重新加载地图仪表
//				  var grid = Ext.getCmp('south-panel');
//				  var records = grid.getSelectionModel().getSelection();
//				  if(records.length > 0){
//				  	  var speed = chartMap.get(records[0].get('carid') + '_speed');
//				  	  var mileage = chartMap.get(records[0].get('carid') + '_mileage');
//				  	  
//					  if(chartMap.get(records[0].get('carid') + '_speed') == null){
//				         showChart(records[0].get('speed'),records[0].get('mileage'));
//					  }else{
//					   	 showChart(speed,mileage);
//					  }
//				  	  
//				  }
					
				}else if(newc.id == "myWorkPlacePanal"){	//个人工作台
					//Ext.getCmp('west-panel').collapse();   
				}
			}
       }
	}
)