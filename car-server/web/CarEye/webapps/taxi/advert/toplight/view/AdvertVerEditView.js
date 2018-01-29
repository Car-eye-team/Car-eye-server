Ext.define('TopLightApp.view.AdvertVerEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.advertVerEditView',
	title : '修改广告版本信息',
    width : 300,
	layout : 'form',
	itemId :'advertVerEditWindow',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',
	items :[ {
		xtype : 'form',
		frame : true,
		anchor : '100%',
		itemId :'advertVerEditPanel',
		collapsible : false,
		//buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 60
	    },
		layout : 'form',
		items : [{
					xtype : 'hidden',
					id:'id',
					name:'advertVer.id'
				},{
					xtype : 'textfield',
					fieldLabel : '<font color="red">*</font>版本名称',
					id:'version',
					name : 'advertVer.version',
					allowBlank : false,
					cls : 'x-textfield'
				},{
					xtype : 'textfield',
					fieldLabel : '<font color="red">*</font>版本号',
					id:'verid',
					name : 'advertVer.verid',
					allowBlank : false,
					cls : 'x-textfield'
				},{
					xtype : 'datefield',
					fieldLabel : '到期日期',
					id:'dtime',
					name : 'advertVer.dtime',
					editable : false,
					format:'Y-m-d',
					cls : 'x-textfield'
				},{
					xtype : 'hidden',
					fieldLabel : '发布时间',
					id:'reltime',
					name : 'advertVer.reltime',
					editable : false,
					format:'Y-m-d',
					cls : 'x-textfield'
				},{
					xtype : 'textarea',
					fieldLabel : '描述',
					id:'remark',
					name : 'advertVer.remark',
					rows:2,
					cls : 'x-textfield'
				} ]
	}],
	buttons: [{
		text: '保存',
		action: 'save'
	},{
		text: '取消',
		handler: function(btn){
			btn.up('window').close();
		}
	}]
	
});


				


