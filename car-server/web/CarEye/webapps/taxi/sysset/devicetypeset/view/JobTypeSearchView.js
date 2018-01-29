Ext.define('JobTypeApp.view.JobTypeSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.jobTypeSearchView',
    title: '职业类型搜索',
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
		        id : 'c_typename',
		        fieldLabel : '职业类型名称'
		    }
		    
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询职业类型信息',
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

