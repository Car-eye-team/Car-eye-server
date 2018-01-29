/*
    地图展示panel
*/
Ext.define('GoodsFindApp.view.SearchMapView',{
    extend:'Ext.panel.Panel',
    alias: 'widget.searchMapView',
    region:'center',
    border:true,
    id:'searchMapView' ,
//    autoWidth : true,
//    height : 400,
//    items: [
//           
//	]
    	dockedItems: [
					{
					    xtype: 'toolbar',
					    layout: {
					        overflowHandler: 'Menu'
					    },   //溢出隐藏
					    dock: 'top',
					    items: [{
						        xtype : 'textfield',
						        id : 'keyword',
						        width : 350,
						        maxLength : 20,
						        labelWidth: 200,
						        labelAlign : 'right',
						        fieldLabel : '区域搜索'
						    },{
						 	    xtype : 'hidden',
							    id:'poiname'
							},{
							    xtype : 'hidden',
							    id:'lng'
						    },{
								xtype : 'hidden',
								id:'lat'
							}, {
								xtype: 'button',
								text : '查询',
								tooltip : '查询',
								iconCls : 'common-search-icon',
//								action: 'searchpoi'
								handler: function(button){
									var con = Ext.create("GoodsFindApp.controller.GoodsFindCtrl");
									con.searchPOI();
								}
							}
					  ]
				}]
		
});