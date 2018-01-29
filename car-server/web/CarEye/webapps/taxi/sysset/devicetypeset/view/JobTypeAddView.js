Ext.define('JobTypeApp.view.JobTypeAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.jobTypeAddView',
	title : '新增职业类型信息',
    width : 400,
	layout : 'form',
	itemId :'jobTypeAddWindow',
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
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 90
	    },
		layout : 'form',
		items : [{
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
		text: '保存',
		action: 'save'
	},{
		text: '取消',
		handler: function(btn){
			btn.up('window').close();
		}
	}]
});