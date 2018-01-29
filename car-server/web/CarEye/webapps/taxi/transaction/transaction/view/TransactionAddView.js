Ext.define('TransactionApp.view.TransactionAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.transactionAddView',
	title : '添加约车业务信息',
    width : 550,
	layout : 'form',
	itemId :'transactionAddWindow',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : false,
	modal : true,
	border : false,
	closeAction : 'destroy',
		items :[ {
		xtype : 'form',
		frame : true,
		anchor : '100%',
		itemId :'transactionAddPanel',
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 80
	    },
		layout : 'form',
		items : [{
			layout : 'column',
			columnWidth : 1,
			items : [{
					columnWidth : .5,
					layout : 'form',
					border:false,
					items : [
					 {xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>用户名称',
								name : 'transaction.usernametwo',
								id:"usernametwo",
								tabIndex : 1,
								maxLength : 20,
								minLength:2,
								minLengthText : '最小长度不小于2位!',
								maxLengthText : '最大长度不超过20位!',
								allowBlank : false,
								blankText : '不能为空',
								enableKeyEvents : true,
								itemId : 'usernametwo',
								anchor : '100%',
								cls : 'x-textfield',
								validator : vd
							}, {
								xtype : 'combo',
								width : 180,
								name : 'transaction.typeid',
								id:'typeid',
								itemId:'typeid',
								store :"TypeListStore",
								editable: false,
								allowBlank : false,
								valueField : 'id',
								displayField : 'typename',
								blankText : '请选择',
								fieldLabel : '<font color="red">*</font>约车业务类型'
							},{
									xtype : 'textfield',
									fieldLabel : '<font color="red">*</font>手机号',
									name : 'transaction.phone',
									id : 'phone',
									itemId : 'phone',
									allowBlank : false,
									blankText : '不能为空',
									minLength : 11,
									minLengthText : '长度为11为数字!',
									maxLength : 11,
									maxLengthText : '长度为11为数字!',
									regex : /^[1][358][0-9]{9}$/,
									regexText : '以1开头的11位数字',
									anchor : '100%',
									cls : 'x-textfield',
								    validator : vd
								}, {
									xtype : 'textfield',
									fieldLabel : '联系电话',
									itemId : 'tel',
									id : 'tel',
									name : 'transaction.tel',
									anchor : '100%',
									cls : 'x-textfield',
									allowBlank : true,
							     	validator : vd,
									regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
									regexText : '格式：3-4位区号，7-8位直播号码，1－4位分机号'
								},
							{xtype : 'textfield',
								fieldLabel : '邮政编码',
								name : 'transaction.postalcode',
								id:"postalcode",
								tabIndex : 1,
								maxLength : 20,
								minLength:2,
								minLengthText : '最小长度不小于2位!',
								maxLengthText : '最大长度不超过20位!',
								allowBlank : true,
								itemId : 'postalcode',
								anchor : '100%',
								cls : 'x-textfield',
								validator : vd
							}
							]
				}, {
					columnWidth : .49,
					layout : 'form',
					border:false,
					items : [
					{
								xtype : 'textarea',
								fieldLabel : '<font color="red">*</font>地址',
								rows :1,
								itemId : 'postaddr',
								id:'postaddr',
								name : 'transaction.postaddr',
								anchor : '100%',
								cls : 'x-textfield',
								allowBlank : false,
								blankText : '不能为空',
								maxLength : 512,
								maxLengthText : '长度不能超过512个字!'
					       },
								{
								xtype : 'textarea',
								fieldLabel : '信息描述',
								name : 'transaction.remark',
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
						}]
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


				




