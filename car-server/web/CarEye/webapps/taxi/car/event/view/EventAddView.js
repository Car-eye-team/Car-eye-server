Ext.define('EventApp.view.EventAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.eventAddView',
	title : '新增事件',
    width : 300,
	layout : 'form',
	itemId :'eventAddWindow',
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
		items : [
					         {
								xtype : 'textarea',
								fieldLabel : '<font color="red">*</font>事件内容',
								name : 'eventSystem.content',
								rows :5,
								anchor : '100%',
								itemId : 'content',
								id : 'content',
								minLength : 2,
								minLengthText : '最小长度不小于2位!',
								maxLength : 51,
								maxLengthText : '最大长度不超过51位!',
								cls : 'x-textfield',
								allowBlank : false,
								blankText : '事件内容不能为空'
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