Ext.define('QuestionApp.view.AnswerAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.answerAddView',
	title : '新增问题答案',
    width : 300,
	layout : 'form',
	itemId :'answerAddWindow',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : false,
	modal : true,
	border : false,
	layout : 'form',
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
								fieldLabel : '<font color="red">*</font>答案内容',
								name : 'answerInfo.answer',
								rows :4,
								width : 230,
								anchor : '100%',
								minLength : 1,
								minLengthText : '最小长度不小于1位!',
								maxLength : 51,
								maxLengthText : '最大长度不超过51位!',
								cls : 'x-textfield',
								allowBlank : false,
								blankText : '请选择'
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