Ext.define('CustomerTypeApp.view.CustomerTypeEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.customerTypeEditView',
	title : '编辑客户类型',
    width : 300,
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
	        labelWidth: 60
	    },
		layout : 'form',
		items : [
		                     {
								xtype : 'hidden',
								id : 'id',
								name:'customerType.id'
							},
					         {xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>类型名称',
								name : 'customerType.typename',
								id:"typename",
								tabIndex : 1,
								maxLength : 16,
								minLength : 2,
								minLengthText : '最小长度不小于2位!',
								maxLengthText : '最大长度不超过16位!',
								allowBlank : false,
								blankText : '不能为空',
								enableKeyEvents : true,
								itemId : 'typename',
								anchor : '100%',
								cls : 'x-textfield',
								regex : /^([\u4E00-\u9FA5]+|[a-zA-Z]+)$/,
								regexText : '只能输入2~16个字母或汉字!',
								validator : vd
							}, {
								xtype : 'textarea',
								fieldLabel : '备注',
								name : 'customerType.remark',
								rows :5,
								anchor : '100%',
								itemId : 'remark',
								id : 'remark',
								minLength : 2,
								minLengthText : '最小长度不小于2位!',
								maxLength : 51,
								maxLengthText : '最大长度不超过51位!',
								cls : 'x-textfield'
					         }
					 ]
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