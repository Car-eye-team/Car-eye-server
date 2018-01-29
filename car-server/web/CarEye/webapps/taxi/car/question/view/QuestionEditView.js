Ext.define('QuestionApp.view.QuestionEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.questionEditView',
	title : '编辑问题',
    width : 450,
	layout : 'fit',
	animCollapse:false,
	constrain : true,  //true则强制此window控制在viewport，默认为false
	constrainHeader : true,
	closable : false,
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
								name:'questionInfo.id'
							},
					         {
								xtype : 'textarea',
								fieldLabel : '<font color="red">*</font>问题内容',
								name : 'questionInfo.content',
								rows :4,
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
					         ,
					         {//多选按钮组

						            xtype: 'checkboxgroup',
					        	    fieldLabel: '消息类型', 
					        	    width: 80,  //宽度220  
					        	    columns: 3,  //在上面定义的宽度上展示3列  
						            autoHeight: true,
						            //defaultType: 'checkbox', 可以设置默认，也可以分别设置xtype属性
						            hideLabels: true,
						            items: [
						                {
						                   boxLabel: '紧急',
						                   name: 'questionInfo.emergency', 
						                   id: 'emergency',
										   itemId : 'emergency',
						                   inputValue: '1', 
						                   xtype: 'checkbox',
						                   checked: true 
						                   },
						                { 
							                boxLabel: '终端TTS播读', 
							                name: 'questionInfo.tts', 
							                id: 'tts', 
										    itemId : 'tts',
							                inputValue: '1', 
							                xtype: 'checkbox' 
						                },
						                { 
							                boxLabel: '广告屏显示', 
							                name: 'questionInfo.adv', 
							                id: 'adv', 
										    itemId : 'adv',
							                inputValue: '1', 
							                xtype: 'checkbox' 
						                }

					            ]
					
					         },
					          {
								xtype : 'answerListView'
							  }
					 ]
}],
	buttons: [{
		text: '修改',
		action: 'save'
	},{
		text: '取消',
		action : 'quit'
	}]
});