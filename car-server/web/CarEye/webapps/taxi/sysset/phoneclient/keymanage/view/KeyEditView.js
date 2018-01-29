Ext.define('KeyManageApp.view.KeyEditView', {
			extend : 'Ext.window.Window',
			alias : 'widget.keyEditView',
			title : '修改API密钥信息',
			width : 350,
			layout : 'form',
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
						frame : true,
						anchor : '100%',
						collapsible : false,
						buttonAlign : 'right',
						fieldDefaults : {
							labelAlign : 'right',
							labelWidth : 60
						},
						layout : 'form',
						items : [{
							xtype : 'hidden',
							name : 'key.id',
							id : 'id'
								},{
							xtype : 'displayfield',
							name : 'key.id',
							fieldLabel: '密钥名称',
							id : 'key'
								},{
							xtype : 'combo',
							name:'key.typeid',
							id:'typeid',
							store : 'KeyTypeStore',
							width : 150,
							maxLength : 20,
							editable : false,
							labelWidth : 80,
							fieldLabel : '<font color="red">*</font>版本类型',
							displayField : 'typename',
							valueField : 'id',
							allowBlank : false,
							// blankText : '请选择',
							// emptyText : '请选择',
							labelAlign : 'right'
								/*
								 * listeners:{ change:function(button) { var
								 * store =
								 * Ext.StoreManager.get('AppVersionListStore');
								 * store.clearFilter(true);
								 * store.on('beforeload', function(store,
								 * options) { var new_params = { versionname :
								 * encodeURI(Ext
								 * .getCmp('search_versionname').getValue()),
								 * typeid :
								 * Ext.getCmp('search_typeid').getValue(),
								 * s_time : Ext.util.Format.date(Ext
								 * .getCmp('s_time').getValue(), 'Y-m-d H:i:s'),
								 * e_time : Ext.util.Format.date(Ext
								 * .getCmp('e_time').getValue(), 'Y-m-d H:i:s') };
								 * Ext.apply(store.proxy.extraParams,
								 * new_params); }); store.loadPage(1); //
								 * store.reload(); return false;
								 *  }
								 *  }
								 */
							}, {
							xtype : 'combo',
							id:'status',
							name:'key.status',
							store : 'KeyStatusStore',
							width : 150,
							maxLength : 20,
							editable : false,
							labelWidth : 80,
							fieldLabel : '<font color="red">*</font>密钥状态',
							displayField : 'status',
							valueField : 'id',
							labelAlign : 'right'
						}, {
							xtype : 'textarea',
							fieldLabel : '描述',
							id:'descs',
							name : 'key.descs',
							// tabIndex : 2,
							rows : 3,
							anchor : '100%',
							itemId : 'factory_desc',
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