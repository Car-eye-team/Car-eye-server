Ext.define('FeedbackAdviceApp.view.AdviceDisposeView', {
			extend : 'Ext.window.Window',
			alias : 'widget.adviceDisposeView',
			title : '处理意见反馈',
			width : 350,
			layout : 'form',
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
							labelWidth : 60
						},
						layout : 'form',
						items : [{
									xtype : 'hidden',
									name : 'feedbackadvice.id',
									id : 'id'
								}, {
									xtype : 'textarea',
									fieldLabel : '处理内容',
									id : 'dealcontent',
									name : 'feedbackadvice.dealcontent',
									// tabIndex : 2,
									rows : 3,
									anchor : '100%',
									minLength : 1,
									minLengthText : '最小长度不小于1位!',
									maxLength : 51,
									maxLengthText : '最大长度不超过51位!',
									cls : 'x-textfield'
								}

						]
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