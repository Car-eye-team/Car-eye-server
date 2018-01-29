Ext.define('EventApp.view.EventEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.eventEditView',
	title : '编辑事件',
    width : 300,
	layout : 'fit',
	animCollapse:false,
	constrain : true,  //true则强制此window控制在viewport，默认为false
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	modal : true,  //modal:true为模式窗口，后面的内容都不能操作，默认为false
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
								xtype : 'hidden',
								id : 'id',
								name:'eventSystem.id'
							},
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
								blankText : '请选择'
					         }
					 ]
}],
	buttons: [{
		text: '修改',
		action: 'save'
	},{
		text: '取消',
		handler: function(btn){
			btn.up('window').close();
		}
	}]
});