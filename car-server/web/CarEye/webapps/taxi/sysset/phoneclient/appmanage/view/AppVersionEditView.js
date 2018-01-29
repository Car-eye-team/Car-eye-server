Ext.define('AppManageApp.view.AppVersionEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.appVersionEditView',
	title : '修改版本型号',
	width : 320,
	layout : 'form',
	itemId : 'appVersionAddWindow',
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
							name : 'appversion.id',
							id : 'id'
								},{
							xtype : 'textfield',
							fieldLabel : '<font color="red">*</font>版本号',
							name : 'appversion.version',
							itemId : 'appversion',
							id:'version',
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
							xtype : 'textfield',
							fieldLabel : '<font color="red">*</font>版本名称',
							name : 'appversion.versionname',
							itemId : 'appversionname',
							id : 'versionname',
							// tabIndex : 1,
							maxLength : 50,
							minLength : 1,
							minLengthText : '最小长度不小于1位!',
							maxLengthText : '最大长度不超过50位!',
							allowBlank : false,
							enableKeyEvents : true,
							anchor : '100%',
							cls : 'x-textfield',
							validator : vd
						}, {
							xtype : "fieldcontainer",
							layout : {
								type : "hbox"
							},
							items : [{
										xtype : 'textfield',
										fieldLabel : '<font color="red">*</font>上传资料',
										name : 'appversion.downloadaddr',
										itemId : 'downloadaddr',
										id : 'downloadaddr',
										anchor : '100%',
										width : 230,
										editable : false,
										allowBlank : false,
										validator : vd
									}, {
										xtype : 'filefield',
										width : 100,
										buttonText : "请选择",
										name : 'fileInput',
										id : 'fileInput'
									}]
						},{
							xtype : 'fieldcontainer',
							border : false,
							html : '<span id="fileQueue"></span>'
						}, {
							xtype : 'combo',
							id:'typeid',
							fieldLabel : '<font color="red">*</font>版本类型',
							name : 'appversion.typeid',
							itemId : 'apptypenid',
							store : 'AppVersionStore',
							displayField : 'typename',						
							valueField : 'id',
							editable : false,
							allowBlank : false
							// blankText : '请选择',
						// emptyText : '请选择'
					}	, {
							xtype : 'htmleditor',
							width : 550,
							height : 120,
							fieldLabel : '版本升级内容',
							name : 'appversion.upgradecontent',
							id:'upgradecontent',
							itemId : 'upgradecontent'
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