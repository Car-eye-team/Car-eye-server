Ext.define('userGroupApp.view.UserGroupSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.userGroupSearchView',
    title: '企业组搜索',
    frame : true,
    region: "north",
    height:100,
    collapsible: true,
    collapseMode: "mini",
    split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	layout : {
		type : 'table',
		align : 'right'
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:60
	},
    items : [{
        xtype : 'textfield',
        width : 200,
        maxLength : 20,
        labelWidth:80,
        id : 'ug_blocgroupname',
        itemId : 'ug_blocgroupname',
        fieldLabel : '企业组名称'
    },{
//						xtype : 'combo',
//						maxLength : 20,
//						itemId : 'ug_blocid',
//						id : 'ug_blocid',
//						store : 'BlocInfoStore',
//						editable: true,
//						valueField : 'blocid',
//						displayField : 'blocname',
//						fieldLabel : '企业',
//						labelWidth: 50,
//						labelAlign: 'right'
//			}
//    	{
        xtype : 'comboboxtree',
		fieldLabel : '企业',
		id : 'ug_blocid',
		labelWidth: 60,
		width:250,
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
	            	var store = Ext.getCmp('ug_blocid').store;
					store.clearFilter(true);
					store.on('beforeload', function (store, options) {
			            var new_params = { 
			            	blocname: encodeURI(Ext.getCmp('ug_blocid').getRawValue())
			            };
			            Ext.apply(store.proxy.extraParams, new_params);
			        });
			        store.reload(); 
	            }
	        }
		 }
    }
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询企业组信息',
	    iconCls : 'common-search-icon',
	    action: 'search'
	}, {
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
	    action : 'reset',
        	handler: function(button){
        	button.up('form').getForm().reset();
        }
	}]

});

