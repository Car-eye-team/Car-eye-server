Ext.define('systemFunctionApp.view.SystemFunctionSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.systemFunctionSearchView',
    title: '系统菜单搜索',
    frame : true,
	autoHeight : true,
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
				xtype : 'textfield',
				maxLength : 20,
				id : 'systemFunction_parentmenuname',
				fieldLabel : '父级菜单名',
				labelWidth: 70,
				labelAlign: 'right'
			}, {
				xtype : 'textfield',
				maxLength : 20,
				id : 'systemFunction_menuname',
				fieldLabel : '菜单名',
				labelWidth: 50,
				labelAlign: 'right'
			}, {
				xtype : 'combo',
				width : 160,
				labelWidth: 60,
				labelAlign: 'right',
				id : 'systemFunction_menulevel',
				hiddenName : 'menulevel',
				fieldLabel : '菜单等级',
				name : 'systemfunction.menulevel',
				itemId : 'menulevel',
				anchor : '100%',
				cls : 'x-textfield',
				editable: false,
				validateOnChange: false,
				store : 'MenuLevelStore',
				displayField : 'name',
				valueField : 'menulevel'
			}, {
				xtype : 'combo',
				id : 'systemFunction_medetype',
				hiddenName : 'medetype',
				fieldLabel : '菜单具体类型',
				itemId : 'medetype',
				anchor : '100%',
				cls : 'x-textfield',
				editable: false,
				labelWidth: 80,
				labelAlign: 'right',
				validateOnChange: false,
				store : Ext.create('Ext.data.Store', {
				    fields: ['medetype', 'name'],
				    data : [
				        {"medetype":"0", "name":"树枝"},
				        {"medetype":"1", "name":"叶子"},
				        {"medetype":"2", "name":"左按钮"},
				        {"medetype":"3", "name":"右按钮"},
				        {"medetype":"4", "name":"右键"},
				        {"medetype":"5", "name":"页面按钮"}
				    ]
				}),
				displayField : 'name',
				valueField : 'medetype'
			}],
	buttons : [{
				text : '查询',
				tooltip : '查询系统菜单',
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

