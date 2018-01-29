Ext.define('VoiceOrderApp.view.VoiceOrderEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.voiceOrderEditView',
	title : '编辑语音订单业务信息',
    width : 650,
	layout : 'form',
	itemId :'voiceOrderEditWindow',
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
		itemId :'voiceOrderEditPanel',
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 90
	    },
		layout : 'form',
		items : [{
			layout : 'column',
			columnWidth : 1,
			items : [{
					columnWidth : .35,
					layout : 'form',
					border:false,
					items : [{
								xtype : 'hidden',
								id : 'id',
								name:'transaction.id'
							},{
								xtype : 'hidden',
								id : 'slat',
								name:'transaction.slat'
							},{
								xtype : 'hidden',
								id : 'slng',
								name:'transaction.slng'
							},{
								xtype : 'hidden',
								id : 'elat',
								name:'transaction.elat'
							},{
								xtype : 'hidden',
								id : 'elng',
								name:'transaction.elng'
							},
							{
								xtype : 'hidden',
								id : 'calltype',
								name:'transaction.calltype'
							},
					 {xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>用户名称',
								name : 'transaction.usernametwo',
								id:"usernametwo",
								tabIndex : 1,
								maxLength : 20,
								minLength:2,
								minLengthText : '最小长度不小于2位!',
								maxLengthText : '最大长度不超过20位!',
								allowBlank : false,
								blankText : '不能为空',
								enableKeyEvents : true,
								itemId : 'usernametwo',
								anchor : '100%',
								cls : 'x-textfield',
								regex : /^[\u4E00-\u9FA5]+$/,
								regexText : '用户名称只能为中文!',
								validator : vd
							}, {
								xtype : 'combo',
								width : 180,
								name : 'transaction.typeid',
								id:'typeid',
								itemId:'typeid',
								store :"TypeListStore",
								editable: false,
								allowBlank : false,
								valueField : 'id',
								displayField : 'typename',
								blankText : '请选择',
								fieldLabel : '<font color="red">*</font>约车类型'
							},{
									xtype : 'textfield',
									fieldLabel : '<font color="red">*</font>召车联系电话',
									name : 'transaction.phone',
									id : 'phone',
									itemId : 'phone',
									allowBlank : false,
									blankText : '不能为空',
									minLength : 11,
									minLengthText : '长度为11为数字!',
									maxLength : 11,
									maxLengthText : '长度为11为数字!',
									regex : /^[1][358][0-9]{9}$/,
									regexText : '输入的号码有误!',
									anchor : '100%',
									cls : 'x-textfield',
								    validator : vd
								},{
									xtype : 'combo',
									maxLength : 20,
									id : 'resstatus',
									name : 'transaction.resstatus',
									itemId : 'resstatus',
									store : "ResstatusStore",
									valueField : 'id',
									displayField : 'resstatus',
									editable : false,
									allowBlank : false,
									fieldLabel : '<font color="red">*</font>是否抢答'
						     },{
									xtype : 'combo',
									maxLength : 20,
									id : 'tratype',
									name : 'transaction.tratype',
									itemId : 'tratype',
									store : "TraTypeStore",
									valueField : 'id',
									displayField : 'tratype',
									editable : false,
									allowBlank : false,
									fieldLabel : '<font color="red">*</font>业务类型'
						     }
							]
				}, {
					columnWidth : .64,
					layout : 'form',
					border:false,
					items : [{ xtype: "fieldcontainer",
							     layout: { type: "hbox" },
							     width:440, 
							     items: [ 
							    {
							        xtype : 'textfield',
									fieldLabel : '<font color="red">*</font>召车出发地',
									name : 'transaction.saddress',
									id:"saddress",
									tabIndex : 1,
									maxLength :40,
									minLength:2,
									minLengthText : '最小长度不小于2位!',
									maxLengthText : '最大长度不超过40位!',
									allowBlank : false,
									blankText : '不能为空',
									enableKeyEvents : true,
									itemId : 'saddress',
									width:350, 
									cls : 'x-textfield',
									validator : vd
							  }, { 
							      text : '地图', 
				                  xtype: "button",id : 
				                  'sselmap',action:'sselmap', 
				                  labelWidth: 90, 
				                  margin: "0 0 0 5",
				                  labelAlign:"right" 
				                  }  
                                 ]  
                             },
//                             { xtype: "fieldcontainer",
//							     layout: { type: "hbox" },
//							     width:440, 
//							     items: [ 
//							    {
//							        xtype : 'textfield',
//									fieldLabel : '<font color="red">*</font>召车目的地',
//									name : 'transaction.eaddress',
//									id:"eaddress",
//									tabIndex : 1,
//									maxLength : 40,
//									width:350, 
//									minLength:2,
//									minLengthText : '最小长度不小于2位!',
//									maxLengthText : '最大长度不超过40位!',
//									allowBlank : false,
//									blankText : '不能为空',
//									enableKeyEvents : true,
//									itemId : 'eaddress',
//									cls : 'x-textfield',
//									validator : vd
//							  }, { 
//							      text : '地图', 
//				                  xtype: "button",
//				                  id : 'eselmap',
//				                  action:'eselmap', 
//				                  labelWidth: 90, 
//				                  margin: "0 0 0 5",
//				                  labelAlign:"right" 
//				                  }  
//                                 ]  
//                             },
								{
								xtype : 'textarea',
								fieldLabel : '备注',
								name : 'transaction.remark',
								rows :4,
								anchor : '100%',
								itemId : 'remark',
								id : 'remark',
								minLength : 2,
								minLengthText : '最小长度不小于2位!',
								maxLength : 51,
								maxLengthText : '最大长度不超过51位!',
								cls : 'x-textfield'
					         }
					    ]
						}]
				}]
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


				





