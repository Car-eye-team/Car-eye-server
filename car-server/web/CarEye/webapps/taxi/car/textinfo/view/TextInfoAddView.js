Ext.define('TextInfoApp.view.TextInfoAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.textInfoAddView',
	title : '新增消息',
    width : 400,
	layout : 'form',
	itemId :'textInfoAddWindow',
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
	        labelWidth: 100
	    },
		layout : 'form',
		items : [
					         {  
					            xtype : 'combo',
								fieldLabel : '<font color="red">*</font>文本消息类型',
								name : 'textInfo.textinfotype',
								store : 'TextInfoTypeStore',
								labelAlign: 'right',
								editable: false,
								valueField : 'textinfotype',
								displayField : 'textinfoname',
								allowBlank : false,
								blankText : '请选择'
					         },{
								xtype : 'textfield',
								fieldLabel : '信息显示时长(分)',
								name : 'textInfo.time',
								anchor : '100%',
								id : 'time',
								regex : /^\d+$/,
								regexText : '大于0的整数',
								cls : 'x-textfield'
					         },{
								xtype : 'textarea',
								fieldLabel : '<font color="red">*</font>消息内容',
								name : 'textInfo.content',
								labelAlign: 'right',
								rows :4,
								anchor : '100%',
								itemId : 'content',
								id : 'content',
								minLength : 2,
								minLengthText : '最小长度不小于2位!',
								maxLength : 100,
								maxLengthText : '最大长度不超过100位!',
								cls : 'x-textfield',
								allowBlank : false,
								blankText : '请输入消息内容'
					         },
					         {//多选按钮组

						            xtype: 'checkboxgroup',
					        	    fieldLabel: '消息类型', 
					        	    labelAlign: 'right',
					        	    columns: 2,  //在上面定义的宽度上展示3列  
						            autoHeight: true,
						            //defaultType: 'checkbox', 可以设置默认，也可以分别设置xtype属性
						            hideLabels: true,
						            items: [
						                {
						                   boxLabel: '紧急',
						                   name: 'textInfo.emergency', 
						                   id: 'emergency',
										   itemId : 'emergency',
						                   inputValue: '1', 
						                   xtype: 'checkbox'
						                   //checked: true 
						                   },
						                { 
							                boxLabel: '终端显示器显示', 
							                name: 'textInfo.lcd', 
							                id: 'lcd', 
										    itemId : 'lcd',
							                inputValue: '1', 
							                xtype: 'checkbox' 
						                },
						                { 
							                boxLabel: '终端TTS播读', 
							                name: 'textInfo.tts', 
							                id: 'tts', 
										    itemId : 'tts',
							                inputValue: '1', 
							                xtype: 'checkbox' 
						                },
						                { 
							                boxLabel: '广告屏显示', 
							                name: 'textInfo.adv', 
							                id: 'adv', 
										    itemId : 'adv',
							                inputValue: '1', 
							                xtype: 'checkbox' 
						                },
						                { 
							                boxLabel: 'CAN故障码信息', 
							                name: 'textInfo.action', 
							                id: 'action', 
										    itemId : 'action',
							                inputValue: '1', 
							                xtype: 'checkbox' 
						                }
						                ,
						                { 
							                boxLabel: '带经纬度', 
							                name: 'textInfo.dist', 
							                id: 'dist', 
										    itemId : 'dist',
							                inputValue: '1', 
							                xtype: 'checkbox' 
						                }

					            ]
					
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