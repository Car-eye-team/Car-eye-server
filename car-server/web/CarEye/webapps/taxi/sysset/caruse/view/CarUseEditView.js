Ext.define('CarUseApp.view.CarUseEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.carUseEditView',
	title : '修改车辆用途信息',
    width : 300,
	layout : 'form',
	itemId :'carUseEditWindow',
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
		itemId :'carUseEditPanel',
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
					name:'carUse.id'
				},
					{
					xtype : 'textfield',
					fieldLabel : '<font color="red">*</font>用途名称',
					id:'usename',
					name : 'carUse.usename',
					tabIndex : 1,
					maxLength : 20,
					maxLengthText : '最大长度不超过20位!',
					allowBlank : false,
					blankText : '不能为空',
					enableKeyEvents : true,
					itemId : 'usename',
					regex : /^[\u4E00-\u9FA5]+$/,
					regexText : '用途名称只能为中文!',
					cls : 'x-textfield'
				},{
					xtype : 'textarea',
					fieldLabel : '备注',
					name : 'carUse.remark',
				    id : 'remark',
				    itemId : 'remark',
					maxLength : 250,
					maxLengthText : "输入不能大于250位"
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


				


