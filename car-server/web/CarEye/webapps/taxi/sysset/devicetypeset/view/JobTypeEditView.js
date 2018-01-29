Ext.define('JobTypeApp.view.JobTypeEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.jobTypeEditView',
	title : '编辑终端类型信息',
    width : 400,
	layout : 'fit',
	animCollapse:false,
	constrain : true,  //true则强制此window控制在viewport，默认为false
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	modal : true,  //modal:true为模式窗口，后面的内容都不能操作，默认为false
	border : false,
	closeAction : 'destroy',
	items :[ {
		xtype : 'form',
		frame : true,
		anchor : '100%',
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 90
	    },
		layout : 'form',
		items : [{
								xtype : 'hidden',
								id:'id',
								name:'jobType.id'
							},{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>职业类型名称',
								name : 'jobType.typename',
								itemId : 'typename',
								id : 'typename',
								anchor : '100%',
								maxLength : 50,  
								maxLengthText : '最大长度不超过50位!', 
								allowBlank : false,
								blankText : '不能为空',
								cls : 'x-textfield'
							}]
						
	}],
	buttons: [{
		text: '修改',
		action: 'save'
	},{
		text: '取消',
		handler: function(btn){
			btn.up('window').close();
		}
	}]
});