Ext.define('PhotoRecordApp.view.PhotoRecordSearchView', {
	extend : 'Ext.form.Panel',
	alias : 'widget.photoRecordSearchView',
	title : '拍摄记录搜索',
	frame : true,
	region : "north",
	height : 90,
	collapsible : true,
	collapseMode : "mini",
	split : true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	layout : {
		type : 'table',
		align : 'right',
		columns : 5
	},
	fieldDefaults : {
		labelAlign : 'right',
		labelWidth : 60
	},
	items : [ {
		xtype : 'combo',
		fieldLabel : '车牌号',
		width: 180,
		labelWidth: 40,
		id : 'pr_carnumber',
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
	}, {
		xtype : 'combo',
		width : 170,
		fieldLabel : '拍摄命令',
		labelWidth : 60,
		editable : false,
		id : 'pr_command',
		labelAlign : 'right',
		store : 'CommandTypeStore',
		displayField : 'commandtype',
		valueField : 'id'
	}, {
		xtype : 'combo',
		width : 170,
		fieldLabel : '保存标志',
		labelWidth : 60,
		editable : false,
		id : 'pr_saveflag',
		labelAlign : 'right',
		store : 'SaveFlagStore',
		displayField : 'saveflag',
		valueField : 'id'
	}, {
		xtype : 'datetimefield',
		width : 200,
		maxLength : 20,
		id : 'pr_stime',
		fieldLabel : '开始时间',
		labelWidth : 60,
		editable: false,
		labelAlign : 'right'
	}, {
		xtype : 'datetimefield',
		width : 200,
		maxLength : 20,
		id : 'pr_etime',
		fieldLabel : '结束时间',
		labelWidth : 60,
		editable: false,
		labelAlign : 'right'
	} ],
	buttons : [ {
		text : '查询',
		tooltip : '查询',
		iconCls : 'common-search-icon',
		action : 'search'
	}, {
		text : '重置',
		tooltip : '清空查询条件',
		iconCls : 'common-reset-icon',
		// action : 'resetcar',
		handler : function(button) {
			Ext.getCmp('pr_command').setValue("");
			Ext.getCmp('pr_saveflag').setValue("");
			Ext.getCmp('pr_carnumber').setValue("");
			Ext.getCmp('pr_stime').setValue("");
			Ext.getCmp('pr_etime').setValue("");
		}
	} ]

});
