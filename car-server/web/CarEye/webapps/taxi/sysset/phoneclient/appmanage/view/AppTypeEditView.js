Ext.define('AppManageApp.view.AppTypeEditView', {
			extend : 'Ext.window.Window',
			alias : 'widget.appTypeEditView',
			title : '修改版本类型',
			width : 300,
			layout : 'form',
			itemId : 'appTypeEditWindow',
			animCollapse : false,
			constrain : true,
			constrainHeader : true,
			maximizable : false,
			minimizable : false,
			closable : true,
			modal : true,
			border : false,
			closeAction : 'destroy',
			items : [{
						xtype : 'form',
						id:'editapptype',
						frame : true,
						anchor : '100%',
						collapsible : false,
						buttonAlign : 'right',
						fieldDefaults : {
							labelAlign : 'right',
							labelWidth : 60
						},
						layout : 'form',
						items : [ {
							xtype : 'hidden',
							name : 'apptype.id',
							id : 'id'
								},{
									xtype : 'textfield',
									fieldLabel : '<font color="red">*</font>类型名称',
									name : 'apptype.typename',
									itemId : 'apptype',
									id:'typename',
									// tabIndex : 1,
									maxLength : 20,
									minLength : 1,
									minLengthText : '最小长度不小于1位!',
									maxLengthText : '最大长度不超过20位!',
									allowBlank : false,
									enableKeyEvents : true,
									anchor : '100%',
									cls : 'x-textfield',
									validator : vd
								}, {
									xtype : 'textarea',
									fieldLabel : '备注',
									name : 'apptype.remark',
									itemId : 'apptype_addremark',	
									id:'remark',
									// tabIndex : 2,
									rows : 3,
									anchor : '100%',
									minLength : 1,
									minLengthText : '最小长度不小于1位!',
									maxLength : 51,
									maxLengthText : '最大长度不超过51位!',
									cls : 'x-textfield'
								}

						]
					}],
			buttons : [{
						text : '保存',
						action : 'save'
					}, {
						text : '取消',
						handler : function(btn) {
							btn.up('window').close();
						}
					}]
		});