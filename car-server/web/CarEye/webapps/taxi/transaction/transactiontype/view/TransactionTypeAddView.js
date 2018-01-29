Ext.define('TransactionTypeApp.view.TransactionTypeAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.transactionTypeAddView',
	title : '新增约车业务类型',
    width : 300,
	layout : 'form',
	itemId :'transactionAddWindow',
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
	        labelWidth: 60
	    },
		layout : 'form',
		items : [        {xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>类型名称',
								name : 'transactionType.typename',
								id:"typename",
								tabIndex : 1,
								maxLength : 20,
								minLength:2,
								minLengthText : '最小长度不小于2位!',
								maxLengthText : '最大长度不超过20位!',
								allowBlank : false,
								blankText : '不能为空',
								enableKeyEvents : true,
								itemId : 'typename',
								anchor : '100%',
								cls : 'x-textfield',
								regex : /^[^\s]{2,20}$/,
								regexText : '不能输入空格!',
								validator : vd
							}, {
								xtype : 'textarea',
								fieldLabel : '备注',
								name : 'transactionType.remark',
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
		text: '保存',
		action: 'save'
	},{
		text: '取消',
		handler: function(btn){
			btn.up('window').close();
		}
	}]
});