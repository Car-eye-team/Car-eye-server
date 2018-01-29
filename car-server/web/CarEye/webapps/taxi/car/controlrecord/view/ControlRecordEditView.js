Ext.define('CarInfoApp.view.ControlRecordEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.controlRecordEditView',
	title : '车辆控制选项',
    width : 300,
	layout : 'form',
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
		items:[{
					xtype : 'combo',
					fieldLabel : '<font color="red">*</font>车辆油路',
					id:'con1',
					editable: false,
					anchor : '100%',
					allowBlank : false,
					blankText : '不能为空',
					store : 'Type1Store',
					displayField : 'value',
					valueField : 'type'
				},{
					xtype : 'combo',
					fieldLabel : '<font color="red">*</font>车辆电路',
					id:'con2',
					editable: false,
					anchor : '100%',
					allowBlank : false,
					blankText : '不能为空',
					store : 'Type1Store',
					displayField : 'value',
					valueField : 'type'
				},{
					xtype : 'combo',
					fieldLabel : '<font color="red">*</font>车门',
					id:'con3',
					editable: false,
					anchor : '100%',
					allowBlank : false,
					blankText : '不能为空',
					store : 'Type2Store',
					displayField : 'value',
					valueField : 'type'
				},{
					xtype : 'combo',
					fieldLabel : '<font color="red">*</font>计价器',
					id:'con4',
					editable: false,
					anchor : '100%',
					allowBlank : false,
					blankText : '不能为空',
					store : 'Type2Store',
					displayField : 'value',
					valueField : 'type'
				},{
					xtype : 'combo',
					fieldLabel : '<font color="red">*</font>刷卡模块',
					id:'con5',
					editable: false,
					anchor : '100%',
					allowBlank : false,
					blankText : '不能为空',
					store : 'Type3Store',
					displayField : 'value',
					valueField : 'type'
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


				


