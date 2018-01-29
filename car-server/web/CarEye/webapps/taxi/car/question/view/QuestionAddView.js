Ext.define('QuestionApp.view.QuestionAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.questionAddView',
	title : '新增问题',
    width : 450,
	layout : 'form',
	itemId :'questionAddWindow',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : false,
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
								fieldLabel : '<font color="red">*</font>问题内容',
								name : 'questionInfo.content',
								rows :4,
								anchor : '100%',
								minLength : 2,
								minLengthText : '最小长度不小于2位!',
								maxLength : 51,
								maxLengthText : '最大长度不超过51位!',
								cls : 'x-textfield',
								allowBlank : false,
								blankText : '请选择'
					         },
					        
					         {//多选按钮组

						            xtype: 'checkboxgroup',
					        	    fieldLabel: '消息类型', 
					        	    columns:3,  //在上面定义的宽度上展示3列  
						            autoHeight: true,
						            //defaultType: 'checkbox', 可以设置默认，也可以分别设置xtype属性
						            hideLabels: true,
						            items: [
						                {
						                   boxLabel: '紧急',
						                   name: 'questionInfo.emergency', 
						                   inputValue: '1', 
						                   xtype: 'checkbox',
						                   checked: true 
						                   },
						                { 
							                boxLabel: '终端TTS播读', 
							                name: 'questionInfo.tts', 
							                inputValue: '1', 
							                xtype: 'checkbox' 
						                },
						                { 
							                boxLabel: '广告屏显示', 
							                name: 'questionInfo.adv', 
							                inputValue: '1', 
							                xtype: 'checkbox' 
						                }

					            ]
					
					         }, {
								xtype : 'answerListView'
							  }
					 ]
}],
	buttons: [{
		text: '保存',
		action: 'save'
	},{
		text: '取消',
		action : 'quit'
	}]
});