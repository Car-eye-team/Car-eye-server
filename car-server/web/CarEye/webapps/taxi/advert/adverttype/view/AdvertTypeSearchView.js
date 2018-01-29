Ext.define('AdvertTypeApp.view.AdvertTypeSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.advertTypeSearchView',
    title: '广告类型搜索',
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
		columns:5
	},
	fieldDefaults: {
    	labelAlign: 'right'
	},
    items : [{
			xtype : 'textfield',
			width : 180,
			fieldLabel : '类型名称',
			id : 'a_typename',
			labelWidth: 60,
			labelAlign: 'right',
		},{
			xtype : 'datetimefield',
			width : 210,
			maxLength : 20,
			id : 'a_stime',
			fieldLabel : '创建时间',
			labelWidth: 60,
			labelAlign: 'right'
		},{
			xtype : 'datetimefield',
			width : 165,
			maxLength : 20,
			id : 'a_etime',
			fieldLabel : '至',
			labelWidth: 15,
			labelAlign: 'right'
		}
    ],
    buttons : [{
		xtype: 'button',
		text : '查询',
		tooltip : '查询',
		iconCls : 'common-search-icon',
		action: 'search'
//		handler: function(button){
//    		var con = Ext.create("AlarmApp.controller.AlarmCtrl");
//    		con.search();
//        }
	},{
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
    	handler: function(button){
        	Ext.getCmp('a_typename').setValue("");
        	Ext.getCmp('a_stime').setValue("");
        	Ext.getCmp('a_etime').setValue("");
        }
	}]

});

