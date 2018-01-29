Ext.define('HostApp.view.AdvertVerAuditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.advertVerAuditView',
	title : '审核广告版本信息',
    width : 300,
	layout : 'form',
	itemId :'advertVerAuditWindow',
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
		itemId :'advertVerAuditPanel',
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
					disabled:true,
					editable : false,
					readOnly:true,
					cls : 'x-textfield'
				},{
					xtype : 'combo',
					fieldLabel : '审核状态',
					id:'auditstatus',
					name : 'advertVer.auditstatus',
					editable : false,
					store : 'AuditStatusStore',
					displayField : 'type',
					valueField : 'id',
					cls : 'x-textfield'
				},{
					xtype : 'textarea',
					fieldLabel : '审核描述',
					id:'auditremark',
					name : 'advertVer.auditremark',
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


				


