Ext.define('RegionSetApp.view.RegionSetEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.regionSetEditView',
	title : '编辑行政区域',
	width : 600,
	layout : 'form',
	itemId : 'regionSetEditWindow',
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
			labelWidth : 120
		},
		layout : 'form',
		items : [{
			layout : 'column',
			columnWidth : 1,
			items : [{
						columnWidth : .49,
						layout : 'form',
						border : false,
						items : [{
									xtype : 'combo',
									fieldLabel : '<font color="red">*</font>行政级别',
									itemId : 'clevel',
									id : 'clevel',
									name : 'region.clevel',
									width : 188,
									editable : false,
									store : 'ClevelStore',
									// readOnly:true,
									valueField : 'clevel',
									displayField : 'regionlevel',
									// blankText : '请选择',
									// allowBlank : false,
									cls : 'x-textfield'
								}, {
									xtype : 'textfield',
									fieldLabel : '<font color="red">*</font>行政区名',
									name : 'region.cnname',
									itemId : 'cnname',
									id : 'cnname',
									// readOnly:true,
									cls : 'x-textfield',
									regex : /^[\u4E00-\u9FA5A-Za-z]+$/,
									regexText : '行政区名只能为中文和英文!',
									allowBlank : false
								}, {
									xtype : 'textfield',
									fieldLabel : '名称拼音',
									itemId : 'enname',
									id : 'enname',
									name : 'region.enname',
									maxLength : 10,
									enforceMaxLength : true,
									anchor : '100%',
									cls : 'x-textfield'
								}, {
									xtype : 'hidden',
									fieldLabel : '区域id',
									name : 'region.id',
									itemId : 'id',
									id : 'id',
									cls : 'x-textfield',
									allowBlank : false
								}, {
									xtype : 'textfield',
									fieldLabel : '英文简称',
									name : 'region.stenname',
									maxLength : 10,
									enforceMaxLength : true,
									itemId : 'stenname',
									id : 'stenname',
									cls : 'x-textfield',
									allowBlank : true
								}]
					}, {
								columnWidth : .49,
								layout : 'form',
								border : false,
								items : [{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>行政编码',
								name : 'region.szcode',
								itemId : 'szcode',
								id : 'szcode',
								anchor : '100%',
								cls : 'x-textfield',
								allowBlank : false,
								blankText : '行政编码不能为空',
								maxLength : 7,
								minLength:6,
								minLengthText : '最小长度不小于6位!',
								maxLengthText : '最大长度不超过7位!',
								regex : /^[0-9]+$/,
								regexText : '行政编码不正确!',
								validator : vd
							}, {
									xtype : 'combo',
									fieldLabel : '<font color="red">*</font>上级区域',
									itemId : 'parentid',
									id : 'parentid',
									name : 'region.parentid',
									width : 188,
									editable : false,
									store : 'SzcodeStore',
									valueField : 'areaid',
									displayField : 'cnname',
									allowBlank : false,
									cls : 'x-textfield'
								}, {
									xtype : 'textfield',
									fieldLabel : '区域编码(默认0)',
									name : 'region.bmcode',
									itemId : 'bmcode',
									id : 'bmcode',
									value : '0',
									cls : 'x-textfield',
									allowBlank : true
								}, {
									xtype : 'textfield',
									fieldLabel : '中文简称',
									name : 'region.stcnname',
									itemId : 'stcnname',
									maxLength : 10,
									enforceMaxLength : true,
									id : 'stcnname',
									cls : 'x-textfield',
									allowBlank : true
								}]
					}]
		}]
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