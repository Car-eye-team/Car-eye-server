Ext.define('DeviceTypeApp.view.DeviceTypeAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.deviceTypeAddView',
	title : '新增终端类型设置信息',
    width : 400,
	layout : 'form',
	itemId :'deviceTypeAddWindow',
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
								fieldLabel : '<font color="red">*</font>终端类型名称',
								name : 'deviceType.typename',
								itemId : 'typename',
								id : 'typename',
								anchor : '100%',
								maxLength : 20,  
								maxLengthText : '最大长度不超过20位!', 
								allowBlank : false,
								blankText : '不能为空',
								validator : vd,
								regex : /^[\u4E00-\u9FA5]+$/,
								regexText : '终端类型名称只能为中文!',
								cls : 'x-textfield'
							},{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>用户类型',
								name : 'deviceType.usertype',
								itemId : 'usertype',
								id : 'usertype',
								value: 11,
								regex : /^[\d\.\,]*$/,  
								regexText : '只允许数字类型',
								maxLength : 10,  
								maxLengthText : '最大长度不超过10位!', 
								anchor : '100%',
								allowBlank : false,
								blankText : '不能为空',
								cls : 'x-textfield'
							},{
								xtype : 'textfield',
								fieldLabel : '所属厂家',
								name : 'deviceType.company',
								itemId : 'company',
								id : 'company',
								maxLength : 20,  
								maxLengthText : '最大长度不超过20位!', 
								anchor : '100%',
								validator : vd,
								cls : 'x-textfield'
							},{
								xtype : 'textfield',
								fieldLabel : '终端网络类型',
								name : 'deviceType.inteltype',
								itemId : 'inteltype',
								id : 'inteltype',
								anchor : '100%',
								maxLength : 20,  
								validator : vd,
								maxLengthText : '最大长度不超过20位!', 
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