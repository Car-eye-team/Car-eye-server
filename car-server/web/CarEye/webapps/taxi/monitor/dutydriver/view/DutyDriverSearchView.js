Ext.define('DutyDriverApp.view.DutyDriverSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.dutyDriverSearchView',
    title: '当班司机信息搜索',
    frame : true,
    region: "north",
    height:80,
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
    items : [
							
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询计价器信息',
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

