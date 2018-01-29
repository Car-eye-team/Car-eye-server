Ext.define('DeviceTypeApp.view.DeviceTypeSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.deviceTypeSearchView',
    title: '终端类型设置搜索',
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
		        width : 170,
		        maxLength : 20,
		        labelWidth:80,
		        id : 'c_typename',
		        fieldLabel : '终端类型名称'
		    },{
		        xtype : 'textfield',
		        width : 170,
		        maxLength : 20,
		        id : 'c_usertype',
		        fieldLabel : '用户类型'
		    },{
		        xtype : 'textfield',
		        width : 170,
		        maxLength : 20,
		        id : 'c_company',
		        fieldLabel : '所属厂家'
		    },{
		        xtype : 'textfield',
		        width : 170,
		        maxLength : 20,
		        labelWidth:80,
		        id : 'c_inteltype',
		        fieldLabel : '终端网络类型'
		    }
		    
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询终端类型设置信息',
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

