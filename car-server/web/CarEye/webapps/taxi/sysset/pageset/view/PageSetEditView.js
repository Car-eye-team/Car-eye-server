Ext.define('PageSetApp.view.PageSetEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.pageSetEditView',
	title : '界面展开关闭设置',
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
	        labelWidth: 120
	    },
		layout : 'form',
		items:[{
					xtype : 'hidden',
					id:'userid',
					name : 'pageSet.userid'
				},{
					xtype : 'combo',
					fieldLabel : '<font color="red">*</font>左侧展开状态',
					name : 'pageSet.leftpage',
					id:'leftpage',
					editable: false,
					anchor : '100%',
					allowBlank : false,
					blankText : '不能为空',
					store : 'PageStatusStore',
					displayField : 'value',
					valueField : 'id'
				}, {
					xtype : 'combo',
					allowBlank : false,
					editable: false,
					anchor : '100%',
					blankText : '不能为空',
					fieldLabel : '<font color="red">*</font>底部展开状态',
					id : 'bottompage',
					name : 'pageSet.bottompage',
					store : 'PageStatusTwoStore',
					displayField : 'value',
					valueField : 'id'
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


				


