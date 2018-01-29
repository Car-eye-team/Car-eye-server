Ext.define('GoodsFindApp.view.AddrSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.addrSearchView',
    title: '搜索',
    frame : true,
    region: "north",
    height: 90,
    collapsible: true,
    collapseMode: "mini",
    split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	layout : {
		type : 'table',
		align : 'right',
		overflowHandler: 'Menu'//溢出隐藏
	},
	fieldDefaults: {
    	labelAlign: 'right'
    	//labelWidth:60
	},
	dockedItems: [
					{
					    xtype: 'toolbar',
					    layout: {
					        overflowHandler: 'Menu'
					    },   //溢出隐藏
					    dock: 'top',
					    items: [{
//						        xtype : 'textfield',
//						        id : 'keyword',
//						        width : 150,
//						        maxLength : 20,
//						        labelWidth: 60,
//						        fieldLabel : '区域搜索'
//						    },{
//						 	    xtype : 'hidden',
//							    id:'poiname'
//							},{
//							    xtype : 'hidden',
//							    id:'lng'
//						    },{
//								xtype : 'hidden',
//								id:'lat'
//							}, {
//								xtype: 'button',
//								text : '查询',
//								tooltip : '查询',
//								iconCls : 'common-search-icon',
//								action: 'searchpoi'
//							},{
								xtype : 'datetimefield',
								width : 160,
								maxLength : 20,
								id : 'c_stime1',
								fieldLabel : '上车时间',
								labelWidth: 60,
								labelAlign: 'right'
							},{
								xtype : 'datetimefield',
								width : 120,
								maxLength : 20,
								id : 'c_etime1',
								fieldLabel : '至',
								labelWidth: 20,
								labelAlign: 'right'
						    },{
						 	    xtype: 'button',
								text : '上车地点',
								tooltip : '上车地点区域',
								iconCls : 'add',
				//				action: 'upAddress'
								handler: function(button){
									var con = Ext.create("GoodsFindApp.controller.GoodsFindCtrl");
									con.upAddress();
								}
							},{
								xtype: 'textfield',
								id : 'uplng1',
								width : 120,
						        labelWidth: 40,
						        fieldLabel : '经度'
							},{
								xtype: 'textfield',
								id : 'uplat1',
								width : 110,
						        labelWidth: 30,
						        fieldLabel : '纬度'
							},{
								xtype: 'textfield',
								id : 'uplng2',
								width : 140,
						        labelWidth: 55,
						        fieldLabel : '对角经度'
							},{
								xtype: 'textfield',
								id : 'uplat2',
								width : 140,
						        labelWidth: 55,
						        fieldLabel : '对角纬度'
							}
						]},{
						xtype: 'toolbar',
					    layout: {
					        overflowHandler: 'Menu'
					    },   //溢出隐藏
					    dock: 'top', 
						items: [{
							    xtype : 'datetimefield',
								width : 160,
								maxLength : 20,
								id : 'c_stime2',
								fieldLabel : '下车时间',
								labelWidth: 60,
								labelAlign: 'right'
							},{
								xtype : 'datetimefield',
								width : 120,
								maxLength : 20,
								id : 'c_etime2',
								fieldLabel : '至',
								labelWidth: 20,
								labelAlign: 'right'
						    },{
								xtype: 'button',
								text : '下车地点',
								tooltip : '下车地点区域',
								iconCls : 'add',
					//			action: 'downAddress'
								handler: function(button){
									var con = Ext.create("GoodsFindApp.controller.GoodsFindCtrl");
									con.downAddress();
								}
							}, {
								xtype: 'textfield',
								id : 'downlng1',
								width : 120,
						        labelWidth: 40,
						        fieldLabel : '经度'
							},{
								xtype: 'textfield',
								id : 'downlat1',
								width : 110,
						        labelWidth: 30,
						        fieldLabel : '纬度'
							},{
								xtype: 'textfield',
								id : 'downlng2',
								width : 140,
						        labelWidth: 55,
						        fieldLabel : '对角经度'
							},{
								xtype: 'textfield',
								id : 'downlat2',
								width : 140,
						        labelWidth: 55,
						        fieldLabel : '对角纬度'
							},'->',{
								xtype: 'button',
								text : '查询',
								tooltip : '查询',
								iconCls : 'common-search-icon',
					//			action: 'search'
								handler: function(button){
									var con = Ext.create("GoodsFindApp.controller.GoodsFindCtrl");
									con.search();
								}
							}, {
								xtype: 'button',
							    text : '重置',
							    tooltip : '清空查询条件',
							    iconCls : 'common-reset-icon',
							    //action : 'reset',
						        handler: function(button){
						        	button.up('form').getForm().reset();
						        	map.clearOverlays(); //清除上次标记点
						        }
							}
					  ]
				}]
		
	  
});

