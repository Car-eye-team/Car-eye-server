Ext.define('OnlineRateApp.view.Bottom' ,{
    extend: 'Ext.panel.Panel',
	alias : 'widget.bottom',
//	autoHeight : true,
	region: "center",
	border: false,
	split: true,
	autoWidth: true,
	collapsed: false,
	id:'trackbottom',
    collapsible: true,
    header : false, // 显示 header 默认 true
    collapseMode: "mini",
    layout: 'fit',
    margins: '0 2 2 2',
    items: [{
         xtype: 'tabpanel',
         id:'tracktabpanel',
		 defaults: {
            autoScroll: true    //是否有滚动条
         },
         items: [
	        {
                xtype: 'panel',
                id:'oneTabView',
                title: '车辆信息',
                items: [{xtype : 'carInfoListView'}]
            },
            {
                xtype: 'panel',
                id:'twoTabView',
                title: '在线时长',
                items: [{xtype : 'twoView'}]
            },
            {
                xtype: 'panel',
                id:'threeTabView',
                title: '上下线次数',
                items: [{xtype : 'threeView'}]
            }
        ],
        listeners: {
       	 'tabchange' : function(tab, newc, oldc) {
//       	 		var carnumber = Ext.getCmp('track_carnumber').getRawValue();
//				var stime = Ext.util.Format.date(Ext.getCmp('ct_stime').getValue(), 'Y-m-d H:i:s');
//				var etime = Ext.util.Format.date(Ext.getCmp('ct_etime').getValue(), 'Y-m-d H:i:s');
//				
//				if(carnumber.length > 0 && stime.length > 0 && etime.length > 0){
//					if(newc.id == "alarmList" && alarmflag){
//						//加载车辆超速报表
//						var alarmStore = Ext.StoreManager.get('AlarmStore');
//						alarmStore.clearFilter(true);
//						alarmStore.on('beforeload', function (store, options) {
//					            var new_params = { 
//					            	carnumber: encodeURI(carnumber),
//				        			stime : encodeURI(stime),
//				        			etime : encodeURI(etime)
//					            };
//					            Ext.apply(store.proxy.extraParams, new_params);
//					        });
//					    alarmStore.loadPage(1); 
//					    
//					    alarmflag = false;
					    
//					}
//				}
			}
        }
     }],
     listeners: {
			'resize': {
	            fn: function(com,width,height,oldwidth,oldheight,obj){
	            	
//	            	var tabpanel = Ext.getCmp('tracktabpanel');
//	 				var treepanel = tabpanel.getActiveTab();
//	 				var view = Ext.getCmp(treepanel.id + 'View')
//	            	
//	            	if(typeof oldheight != "undefined"){
//	            		Ext.getCmp('carTrackListView').setHeight(view.getHeight()+(height-oldheight));
//	            		Ext.getCmp('carStopListView').setHeight(view.getHeight()+(height-oldheight));
//	            		Ext.getCmp('alarmListView').setHeight(view.getHeight()+(height-oldheight));
//	            		Ext.getCmp('alarmRiListView').setHeight(view.getHeight()+(height-oldheight));
//	            	}
	            }
            }
			
       }
});


