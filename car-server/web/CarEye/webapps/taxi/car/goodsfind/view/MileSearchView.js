Ext.define('GoodsFindApp.view.MileSearchView', {
	extend : 'Ext.window.Window',
	alias : 'widget.mileSearchView',
	title : '周边范围搜索',
    width : 300,
	layout : 'form',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',
	items : [ {
		xtype : 'form',
		frame : true,
		anchor : '100%',
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 100
	    },
		items : [{
					xtype : 'hidden',
					fieldLabel : '经度',
					id :'lng'
				},{
					xtype : 'hidden',
					fieldLabel : '纬度',
					id :'lat'
				},{
					xtype : 'textfield',
					fieldLabel : '<font color="red">*</font>周边距离(米)',
					id :'mile',
					width:260,
					cls : 'x-textfield',
					regex : /^[\d\.]*$/,
					regexText : '只允许数字类型',
					allowBlank : false
				},{
					xtype : 'datetimefield',
					fieldLabel : '<font color="red">*</font>开始时间',
					format:"Y-m-d",
					id : 'gf_stime',
					width:260,
					allowBlank : false,
					cls : 'x-textfield'
				},{
					xtype : 'datetimefield',
					fieldLabel : '<font color="red">*</font>结束时间',
					format:"Y-m-d",
					id : 'gf_etime',
					width:260,
					allowBlank : false,
					cls : 'x-textfield'
			} ]
	} ],
	buttons: [{
		text: '确定',
		action: 'search'
	},{
		text: '取消',
		handler: function(btn){
			btn.up('window').close();
		}
	}]
});