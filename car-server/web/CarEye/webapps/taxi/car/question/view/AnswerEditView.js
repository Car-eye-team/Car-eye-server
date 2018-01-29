Ext.define('QuestionApp.view.AnswerEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.answerEditView',
	title : '编辑问题答案',
    width : 300,
	layout : 'form',
	animCollapse:false,
	constrain : true,  //true则强制此window控制在viewport，默认为false
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	modal : true,  //modal:true为模式窗口，后面的内容都不能操作，默认为false
	border : false,
	closeAction : 'destroy',
	closable : false,
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
								id : 'answerid',
								name:'answerInfo.id'
							},  {
								xtype : 'hidden',
								id : 'qid',
								name:'answerInfo.qid'
							}, 
					         {
								xtype : 'textarea',
								fieldLabel : '<font color="red">*</font>答案内容',
								name : 'answerInfo.answer',
								rows :4,
								width : 230,
								anchor : '100%',
								itemId : 'answer',
								id : 'answer',
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
		text: '修改',
		action: 'save'
	},{
		text: '取消',
		handler: function(btn){
			btn.up('window').close();
		}
	}]
});