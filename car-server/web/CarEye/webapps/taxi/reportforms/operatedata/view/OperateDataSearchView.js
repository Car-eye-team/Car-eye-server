Ext.define('OperateDataApp.view.OperateDataSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.operateDataSearchView',
    title: '营运信息搜索',
    frame : true,
    region: "north",
    height:120,
    collapsible: true,
    collapseMode: "mini",
    split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	layout : {
		type : 'table',
		align : 'right',
		columns:6
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:60
	},
    items : [{
								xtype : 'comboboxtree',
								fieldLabel : '企业',
								id : 'c_blocid',
								width:170,
								labelWidth: 60,
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
								editable: true,
								cls : 'x-textfield',
								valueField: 'id', 
								displayField: 'text',
								listeners: {
							        change: {
							            element: 'el', 
							            fn: function(){ 
							            	var store = Ext.getCmp('c_blocid').store;
											store.clearFilter(true);
											store.on('beforeload', function (store, options) {
									            var new_params = { 
									            	blocname: encodeURI(Ext.getCmp('c_blocid').getRawValue())
									            };
									            Ext.apply(store.proxy.extraParams, new_params);
									        });
									        store.reload(); 
							            }
							        }
								 }
							},{
								xtype : 'combo',
								width : 150,
								fieldLabel : '车牌号',
								labelWidth: 45,
								id:'c_carnumber',
								labelAlign: 'right',
								store : 'CarPageListStore',
								displayField : 'carnumber',
								valueField : 'carnumber',
								pageSize : 10,
								minChars:1,
								matchFieldWidth:false,
								listConfig :{
									width:235
								}
							},{
						        xtype : 'textfield',
						        width : 170,
						        maxLength : 20,
								labelAlign: 'right',
						        labelWidth: 60,
						        id : 'c_terminal',
						        fieldLabel : '终端号码'
							},{
								xtype : 'datetimefield',
								width : 170,
								maxLength : 20,
								id : 'c_stime',
								fieldLabel : '开始时间',
								labelWidth: 60,
								editable: false,
								labelAlign: 'right'
							},  {
								xtype : 'datetimefield',
								width : 170,
								maxLength : 20,
								id : 'c_etime',
								fieldLabel : '结束时间',
								labelWidth: 60,
								editable: false,
								labelAlign: 'right'
							}
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询营运信息',
	    iconCls : 'common-search-icon',
	    action: 'search'
	}, {
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
//	    action : 'reset',
        handler: function(button){
        	button.up('form').getForm().reset();
        }
	}]

});

