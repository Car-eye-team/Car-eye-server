Ext.define('OnlineRateApp.view.OnlineRateSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.onlineRateSearchView',
    title: '车辆在线率分析搜索',
    frame : true,
    region: "north",
    height:100,
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
								itemId : 'blocid',
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
								xtype : 'datefield',
								width : 170,
								maxLength : 20,
								id : 'c_stime',
								fieldLabel : '开始时间',
								format : 'Y-m-d',
								maxValue : new Date(),
								value: Ext.util.Format.date(new Date(), "Y-m-") + "01",
								labelWidth: 60,
								editable: false,
								labelAlign: 'right'
							},  {
								xtype : 'datefield',
								width : 170,
								maxLength : 20,
								id : 'c_etime',
								fieldLabel : '结束时间',
								format : 'Y-m-d',
								maxValue : new Date(),
								value : new Date(),
								labelWidth: 60,
								editable: false,
								labelAlign: 'right'
							}
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询新车上线监管',
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

