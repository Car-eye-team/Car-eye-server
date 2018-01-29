Ext.define('ComplaintTypeApp.view.ComplaintTypeSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.complaintTypeSearchView',
    itemId :'complaintTypeSearchView',
    title: '投诉类型搜索',
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
    	labelWidth: 90
    },
    items : [{
				xtype : 'textfield',
				id:'c_typename',
				fieldLabel : '投诉类型名称',
				emptyText:'',
				width : 220,
				cls : 'x-textfield'
			}],
	buttons : [{
				text : '查询',
				tooltip : '查询',
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

