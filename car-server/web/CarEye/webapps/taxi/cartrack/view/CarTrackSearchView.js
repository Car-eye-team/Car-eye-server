Ext.define('CarTrackApp.view.CarTrackSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.carTrackSearchView',
    frame: true,
    itemId :'carTrackSearchView',
    region: "north",
    height :30,
    bodyStyle: 'border-width:1px 0 0 0;',
    tbar:{
	    layout: {
	     overflowHandler: 'Menu'
	    },
     items:[{
     			xtype : 'combo',
				width : 150,
				fieldLabel : '车牌',
				labelWidth: 30,
				emptyText: '车牌号码',
				minChars:1,
				queryDelay:800,
				id : 'track_carnumber',
				itemId : 'track_carnumber',
				labelAlign: 'right',
				editable:false,
				store : 'CarListStore',
				displayField : 'carnumber',
				valueField : 'carnumber'
     		},{
				xtype : 'datetimefield',
				width : 250,
				maxLength : 20,
				id : 'ct_stime',
				fieldLabel : '日期(从)',
				labelWidth: 60,
				labelAlign: 'right',
				value:Ext.Date.format(new Date(), 'Y-m-d')
			},  {
				xtype : 'datetimefield',
				width : 220,
				maxLength : 20,
				id : 'ct_etime',
				fieldLabel : '(至)',
				labelWidth: 30,
				labelAlign: 'right',
				value:new Date()
			},{
				xtype: 'button',
				text : '查询',
				tooltip : '查询',
				iconCls : 'common-search-icon',
//				action: 'search',
				handler: function(button){
						var con = Ext.create("CarTrackApp.controller.CarTrackCtrl");
						con.searchCarTrack();
				}
			},{
				text : '重置',
				tooltip : '清空查询条件',
				iconCls : 'common-reset-icon',
	        	handler: function(button){
	        		Ext.getCmp('ct_stime').setValue("");
	        		Ext.getCmp('ct_etime').setValue("");
	        		Ext.getCmp('track_carnumber').setValue("");
	        	}
            },{
            	xtype: 'button',
				text : '测距',
				tooltip : '测距',
				iconCls : 'icon_measure',
//				action: 'mapcj',
				handler: function(button){
						var con = Ext.create("CarTrackApp.controller.CarTrackCtrl");
						con.mapcj();
				}
            },'->',{
            	xtype: 'button',
				text : '新页面轨迹查询',
				tooltip : '新页面轨迹查询',
				iconCls : 'common-search-icon',
//				action: 'newpage',
				handler: function(button){
						var con = Ext.create("CarTrackApp.controller.CarTrackCtrl");
						con.newpage();
				}
            }]
    }

});

