Ext.define('CustomerApp.view.CustomerAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.customerAddView',
	title : '添加客户信息',
    width : 550,
	layout : 'form',
	itemId :'customerAddWindow',
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
		itemId :'customerAddPanel',
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
								fieldLabel : '<font color="red">*</font>客户名称',
								name : 'customer.cname',
								id:"cname",
								tabIndex : 1,
								maxLength : 50,
								minLength:2,
								minLengthText : '最小长度不小于2位!',
								maxLengthText : '最大长度不超过50位!',
								allowBlank : false,
								blankText : '不能为空',
								enableKeyEvents : true,
								itemId : 'cname',
								anchor : '100%',
								cls : 'x-textfield',
								validator : vd
							}, {
								xtype : 'combo',
								width : 180,
								name : 'customer.typeid',
								id:'typeid',
								itemId:'typeid',
								store :"TypeListStore",
								editable: false,
								allowBlank : false,
								valueField : 'id',
								displayField : 'typename',
								blankText : '请选择',
								fieldLabel : '<font color="red">*</font>客户类型'
							},{
									xtype : 'combo',
									anchor : '100%',
									maxLength : 20,
									id : 'sex',
									name : 'customer.sex',
									itemId : 'sex',
									store : "SexStore",
									valueField : 'id',
									displayField : 'sex',
									editable : false,
									fieldLabel : '<font color="red">*</font>性别',
									value : '1'
								},{
									xtype : 'textfield',
									fieldLabel : '<font color="red">*</font>手机号',
									name : 'customer.phone',
									id : 'phone',
									itemId : 'phone',
									allowBlank : false,
									blankText : '不能为空',
									minLength : 11,
									minLengthText : '长度为11为数字!',
									maxLength : 11,
									maxLengthText : '长度为11为数字!',
									regex : /^[1][0-9]{10}$/,
									regexText : '输入的手机号码有误',
									anchor : '100%',
									cls : 'x-textfield',
								    validator : vd
								}, {
									xtype : 'textfield',
									fieldLabel : '联系电话',
									itemId : 'tel',
									id : 'tel',
									name : 'customer.tel',
									anchor : '100%',
									cls : 'x-textfield',
									allowBlank : true,
							     	validator : vd,
									regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
									regexText : '格式：3-4位区号，7-8位直播号码，1－4位分机号'
								},
							{xtype : 'textfield',
								fieldLabel : '邮政编码',
								name : 'customer.postalcode',
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
								fieldLabel : '地址',
								rows :1,
								itemId : 'postaddr',
								id:'postaddr',
								name : 'customer.postaddr',
								anchor : '100%',
								cls : 'x-textfield',
								maxLength : 218,
								maxLengthText : '长度不能超过218个字!'
					       },
								{
								xtype : 'textarea',
								fieldLabel : '信息描述',
								name : 'customer.remark',
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


				




