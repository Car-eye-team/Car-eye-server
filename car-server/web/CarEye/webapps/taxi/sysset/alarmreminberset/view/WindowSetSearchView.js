Ext.define('WindowSetApp.view.WindowSetSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.windowSetSearchView',
    title: '报警提示搜索',
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
    	width : 200
    },
    items : [{
				xtype : 'comboboxtree',
				fieldLabel : '企业',
				id : 'query_blocid',
				labelWidth: 60,
				width:200,
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
			            	var store = Ext.getCmp('query_blocid').store;
							store.clearFilter(true);
							store.on('beforeload', function (store, options) {
					            var new_params = { 
					            	blocname: encodeURI(Ext.getCmp('query_blocid').getRawValue())
					            };
					            Ext.apply(store.proxy.extraParams, new_params);
					        });
					        store.reload(); 
			            }
			        }
				 }
				
			}, {
				xtype : 'combo',
				maxLength : 20,
				id : 'query_blocgroupid',
				store : 'BlocGroupListStore',
				editable: true,
				valueField : 'blocgroupid',
				displayField : 'blocgroupname',
				fieldLabel : '企业组',
				labelWidth: 50,
				labelAlign: 'right'
			}, {
				xtype : 'textfield',
				maxLength : 20,
				id : 'query_loginname',
				name : 'user.loginname',
				fieldLabel : '登录名',
				labelWidth: 50,
				labelAlign: 'right'
			
			}],
	buttons : [{
				text : '查询',
				tooltip : '查询用户信息',
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

