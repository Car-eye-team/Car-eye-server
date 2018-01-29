Ext.define('PaymentParamSetApp.view.CompanyEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.companyAddView',
	title : '修改收款公司',
    width : 400,
	layout : 'form',
	itemId :'companyEditView',
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
		itemId :'companyEditPanel',
		collapsible : false,
		//buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 60
	    },
		layout : 'form',
		items : [
				{
					xtype : 'hidden',
					id:'id',
					name : 'paymentParamSet.id'
				},{
					xtype : 'textfield',
					fieldLabel : '<font color="red">*</font>编号',
					id:'type',
					name : 'paymentParamSet.type',
					tabIndex : 1,
					maxLength : 10,
					maxLengthText : '最大长度不超过10位!',
					allowBlank : false,
					blankText : '不能为空',
					enableKeyEvents : true,
					itemId : 'type',
					cls : 'x-textfield'
				},{	
					xtype : 'textfield',
					fieldLabel : '<font color="red">*</font>收款公司',
					id:'company',
					name : 'paymentParamSet.company',
					tabIndex : 1,
					maxLength : 128,
					maxLengthText : '最大长度不超过128位!',
					allowBlank : false,
					blankText : '不能为空',
					enableKeyEvents : true,
					itemId : 'company',
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


				


