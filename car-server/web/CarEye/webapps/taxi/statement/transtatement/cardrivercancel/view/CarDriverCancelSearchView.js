Ext.define('CarDriverCancelApp.view.CarDriverCancelSearchView' ,{
    extend: 'Ext.form.Panel',
    border : true,
    alias : 'widget.carDriverCancelSearchView',
    title: '司机取消数统计搜索',
	region: "north",
	frame: true,
	layout:{
		type : 'table',
		align : 'right',
		columns:6
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:80
	},
	height:100,
	collapsible: true,
    collapseMode: "mini",
    split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	items:[{
								xtype : 'comboboxtree',
								fieldLabel : '企业',
								editable:true,
						        labelWidth: 30,
								id : 'cdc_blocid',
								width:140,
								store: Ext.create('Ext.data.TreeStore', {  
							        autoLoad : 'true',
							        fields: ['text','id','parentId'], 
									root : {expanded : true,text : '企业' },
									proxy: {
										 type: 'ajax',
										 url: window.BIZCTX_PATH + '/servlet/DeptTree?type=200', 
										 reader: {
											 type: 'json'
										 }
									}
							    }) ,
							    rootVisible: false,
								cls : 'x-textfield',
								valueField: 'id', 
								displayField: 'text',
								listeners: {
							        change: {
							            element: 'el', 
							            fn: function(){ 
							            	var store = Ext.getCmp('cdc_blocid').store;
											store.clearFilter(true);
											store.on('beforeload', function (store, options) {
									            var new_params = { 
									            	blocname: encodeURI(Ext.getCmp('cdc_blocid').getRawValue())
									            };
									            Ext.apply(store.proxy.extraParams, new_params);
									        });
									        store.reload(); 
							            }
							        }
								 }
							},{
						        xtype : 'textfield',
								width : 160,
								fieldLabel : '车牌号码',
								labelWidth: 60,
								id:'cdc_carnumber',
								itemId:'carnumber',
								minChars:1,
//							    queryDelay:800,
								labelAlign: 'right'
//								store : 'CarListStore',
//								displayField : 'carnumber',
//								valueField : 'carnumber'
							},{
						        xtype : 'textfield',
						        width : 160,
						        maxLength : 20,
								labelAlign: 'right',
						        labelWidth: 40,
						        id : 'cdc_drivername',
						        fieldLabel : '司机'
							},{
								xtype : 'combo',
								width : 130,
								maxLength : 20,
								id : 'css_type',
								store :"TypeStore",
								editable: false,
								valueField : 'id',
								displayField : 'type',
								fieldLabel : '是否违约',
								labelWidth: 60,
								labelAlign: 'right'
							},{
								xtype : 'datetimefield',
								width :200,
								id : 'cdc_stime',
								maxLength : 20,
								fieldLabel : '取消开始时间',
								labelWidth: 80,
								editable: false,
								labelAlign: 'right'
							},{
								xtype : 'datetimefield',
								width : 180,
								id : 'cdc_etime',
								maxLength : 20,
								fieldLabel : '到',
								labelWidth: 30,
								editable: false,
								labelAlign: 'right'
							}],
	buttons:[{
		xtype: 'button',
		text : '查询',
		id : 'mailset_query_text',
		tooltip : '查询',
		iconCls : 'common-search-icon',
//		action: 'search',
        handler: function(button){
    		var con = Ext.create("CarDriverCancelApp.controller.CarDriverCancelCtrl");
    		con.searchCarDriver();
        }
	},{
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
        handler: function(button){
        	Ext.getCmp('cdc_blocid').setValue("");
        	Ext.getCmp('cdc_carnumber').setValue("");
        	Ext.getCmp('cdc_drivername').setValue("");
        	Ext.getCmp('css_type').setValue("");
        	Ext.getCmp('cdc_stime').setValue("");
        	Ext.getCmp('cdc_etime').setValue("");
        }
	}]
});
