Ext.define('SoftPhoneApp.view.CustomInfoView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.customInfoView',
	autoWidth : true,
	frame : true,
	header : false,
	border : false,
	autoSrcoll : 'auto',
	layout : 'form',
	items : [{
		xtype : 'form',
		border : false,
		anchor : '100%',
		collapsible : false,
		buttonAlign : 'right',
		autoScroll : true,
		items : [{
			xtype : 'panel',
			border : false,
			anchor : '100%',
			items : [{
				"xtype" : "fieldset",
				"title" : "<b><font style='color:green'>客户信息 </font></b>",
				fieldDefaults : {
					labelAlign : 'right',
					labelWidth : 100,
					width : 300
				},
				items : [{
					xtype : 'form',
					id : 'customerform',
					border : false,
					layout : 'form',
					items : [{
						layout : 'column',
						border : false,
						columnWidth : 1,
						items : [{
							columnWidth : .25,
							layout : 'form',
							border : false,
							items : [{
										xtype : 'hidden',
										id : 'id',
										name : 'customer.id'
									}, {
										xtype : 'displayfield',
										fieldLabel : '客户类型',
										id : 'typename',
										renderer : function(value) {
											return "<font size=5px; color ='red'>"
													+ value + "</font>"
										}
									}, {
										xtype : 'textfield',
										fieldLabel : '<font color="red">*</font>客户名称',
										name : 'customer.cname',
										id : "cname",
										itemId : "cname",
										maxLength : 20,
										minLength : 2,
										minLengthText : '最小长度不小于2位!',
										maxLengthText : '最大长度不超过20位!',
										allowBlank : false,
										blankText : '不能为空',
										enableKeyEvents : true,
										cls : 'x-textfield',
										validator : vd
									}, {
										xtype : 'combo',
										name : 'customer.typeid',
										id : 'typeid',
										store : "TypeListStore",
										editable : false,
										allowBlank : false,
										valueField : 'id',
										displayField : 'typename',
										blankText : '请选择',
										fieldLabel : '<font color="red">*</font>客户类型'
									}]
						}, {
							columnWidth : .25,
							layout : 'form',
							border : false,
							items : [{
										xtype : 'combo',
										anchor : '100%',
										maxLength : 20,
										id : 'sex',
										name : 'customer.sex',
										store : "SexStore",
										valueField : 'id',
										displayField : 'sex',
										editable : false,
										fieldLabel : '<font color="red">*</font>性别',
										value : '1'
									}, {
										xtype : 'textfield',
										fieldLabel : '<font color="red">*</font>手机号',
										name : 'customer.phone',
										id : 'phone',
										allowBlank : false,
										blankText : '不能为空',
										minLength : 11,
										minLengthText : '长度为11为数字!',
										maxLength : 11,
										maxLengthText : '长度为11为数字!',
										regex : /^[1][358][0-9]{9}$/,
										regexText : '以1开头的11位数字',
										cls : 'x-textfield',
										validator : vd
									}, {
										xtype : 'textfield',
										fieldLabel : '联系电话',
										id : 'tel',
										name : 'customer.tel',
										cls : 'x-textfield',
										allowBlank : true,
										validator : vd,
										regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
										regexText : '格式：3-4位区号，7-8位直播号码，1－4位分机号'
									}]
						}, {
							columnWidth : .25,
							layout : 'form',
							border : false,
							items : [{
										xtype : 'textfield',
										fieldLabel : '邮政编码',
										name : 'customer.postalcode',
										id : "postalcode",
										tabIndex : 1,
										maxLength : 6,
										minLength : 6,
										minLengthText : '长度为6位!',
										maxLengthText : '长度为6位!',
										allowBlank : true,
										cls : 'x-textfield'
									}, {
										xtype : 'textfield',
										fieldLabel : '地址',
										id : 'postaddr',
										name : 'customer.postaddr',
										cls : 'x-textfield',
										maxLength : 512,
										maxLengthText : '长度不能超过512个字!'
									}, {
										xtype : 'textfield',
										fieldLabel : '信息描述',
										name : 'customer.remark',
										itemId : 'remark',
										id : 'remark',
										maxLength : 51,
										maxLengthText : '最大长度不超过51位!',
										cls : 'x-textfield'
									}]
						}]
					}, {
						xtype : 'button',
						// margin : '5 0 5 350',
						itemId : 'saveCustomInfo',
						text : '保存'
					}]
				}]
			}]
		}, {
			xtype : 'panel',
			border : false,
			anchor : '100%',
			items : [{
				"xtype" : "fieldset",
				"title" : "<b><font style='color:green'>最后一次约车记录 </font></b>",
				fieldDefaults : {
					labelAlign : 'right',
					labelWidth : 120,
					width : 300
				},
				items : [{
					xtype : 'form',
					layout : 'column',
					border : false,
					columnWidth : 1,
					id : 'transaction_form',
					hidden : true,
					items : [{
						columnWidth : .3,
						layout : 'form',
						border : false,
						items : [{
									xtype : 'hidden',
									id : 't_id',
									name : 'transaction.id'
								}, {
									xtype : 'hidden',
									id : 'cid',
									name : 'transaction.cid'
								}, {
									xtype : 'hidden',
									id : 'slat',
									name : 'transaction.slat'
								}, {
									xtype : 'hidden',
									id : 'slng',
									name : 'transaction.slng'
								}, {
									xtype : 'hidden',
									id : 'elat',
									name : 'transaction.elat'
								}, {
									xtype : 'hidden',
									id : 'elng',
									name : 'transaction.elng'
								}, {
									xtype : 'textfield',
									fieldLabel : '<font color="red">*</font>用户名称',
									name : 'transaction.usernametwo',
									id : "username",
									tabIndex : 1,
									maxLength : 20,
									minLength : 2,
									minLengthText : '最小长度不小于2位!',
									maxLengthText : '最大长度不超过20位!',
									allowBlank : false,
									blankText : '不能为空',
									enableKeyEvents : true,
									anchor : '100%',
									cls : 'x-textfield',
									validator : vd
								}, {
									xtype : 'combo',
									width : 180,
									name : 'transaction.typeid',
									id : 't_typeid',
									itemId : 'typeid',
									store : "TransactionTypeListStore",
									editable : false,
									allowBlank : false,
									valueField : 'id',
									displayField : 'typename',
									blankText : '请选择',
									fieldLabel : '<font color="red">*</font>业务类型'
								}, {
									xtype : 'datetimefield',
									maxLength : 20,
									width : 180,
									name : 'transaction.usetime',
									id : 'usetime',
									format : "Y-m-d",
									anchor : '100%',
									margin : '5 0 5 10',
									fieldLabel : '<font color="red">*</font>订单时间',
									allowBlank : false,
									value : new Date()
								}, {
									xtype : 'combo',
									maxLength : 20,
									id : 'resstatus',
									name : 'transaction.resstatus',
									store : "ResstatusStore",
									valueField : 'id',
									displayField : 'resstatus',
									editable : false,
									allowBlank : false,
									fieldLabel : '<font color="red">*</font>是否抢答'
								}, {
									xtype : 'combo',
									maxLength : 20,
									id : 'tratype',
									name : 'transaction.tratype',
									store : "TraTypeStore",
									valueField : 'id',
									displayField : 'tratype',
									editable : false,
									allowBlank : false,
									fieldLabel : '<font color="red">*</font>订单类型'

								}]
					}, {
						columnWidth : .4,
						layout : 'form',
						border : false,
						items : [{
									xtype : 'textfield',
									fieldLabel : '<font color="red">*</font>召车联系电话',
									name : 'transaction.phone',
									id : 't_phone',
									allowBlank : false,
									blankText : '不能为空',
									minLength : 11,
									minLengthText : '长度为11为数字!',
									maxLength : 11,
									maxLengthText : '长度为11为数字!',
									regex : /^[1][358][0-9]{9}$/,
									regexText : '以1开头的11位数字',
									anchor : '100%',
									cls : 'x-textfield',
									validator : vd
								}, {
									xtype : "fieldcontainer",
									layout : {
										type : "hbox"
									},
									width : '100%',
									items : [{
										xtype : 'textfield',
										fieldLabel : '<font color="red">*</font>召车出发地',
										name : 'transaction.saddress',
										id : "saddress",
										tabIndex : 1,
										maxLength : 40,
										maxLengthText : '最大长度不超过40位!',
										allowBlank : false,
										blankText : '不能为空',
										enableKeyEvents : true,
										itemId : 'saddress',
										width : '90%',
										cls : 'x-textfield',
										validator : vd
									}, {
										text : '地图',
										xtype : "button",
										id : 'sselmap',
										action : 'sselmap',
										labelWidth : 90,
										margin : "0 0 0 5",
										labelAlign : "right"
									}]
								}, {

									xtype : "fieldcontainer",
									layout : {
										type : "hbox"
									},
									width : '100%',
									items : [{
										xtype : 'textfield',
										fieldLabel : '<font color="red">*</font>召车目的地',
										name : 'transaction.eaddress',
										id : "eaddress",
										tabIndex : 1,
										maxLength : 40,
										width : '90%',
										maxLengthText : '最大长度不超过40位!',
										allowBlank : false,
										blankText : '不能为空',
										enableKeyEvents : true,
										cls : 'x-textfield',
										validator : vd
									}, {
										text : '地图',
										xtype : "button",
										id : 'eselmap',
										action : 'eselmap',
										labelWidth : 90,
										margin : "0 0 0 5",
										labelAlign : "right"
									}]
								}, {
									xtype : 'textfield',
									fieldLabel : '调度消息',
									name : 'transaction.remark',
									rows : 2,
									id : 't_remark',
									maxLength : 51,
									maxLengthText : '最大长度不超过51位!',
									cls : 'x-textfield'
								}]
					}]

				}, {
					xtype : 'button',
					margin : '5 0 5 350',
					id : 'tiredset',
					hidden : true,
					itemId : 'tiredset',
					text : '保存'
				}, {
					xtype : 'displayfield',
					id : 'emptymessage',
					margin : '10 0 10 350',
					value : '没有约车记录信息'
				}]
			}]
		}, {
			xtype : 'panel',
			anchor : '100%',
			border : false,
			items : [{
				"xtype" : "fieldset",
				"title" : "<b><font style='color:green'>召车信息 </font></b>",
				fieldDefaults : {
					labelAlign : 'right',
					labelWidth : 120,
					width : 300
				},
				items : [{
					xtype : 'form',
					layout : 'column',
					border : false,
					columnWidth : 1,
					id : 'transaction_add_form',
					items : [{
						columnWidth : .3,
						layout : 'form',
						border : false,
						items : [{
									xtype : 'hidden',
									id : 'tr_cid',
									name : 'transaction.cid'
								}, {
									xtype : 'hidden',
									id : 'tr_slat',
									name : 'transaction.slat'
								}, {
									xtype : 'hidden',
									id : 'tr_slng',
									name : 'transaction.slng'
								}, {
									xtype : 'hidden',
									id : 'tr_elat',
									name : 'transaction.elat'
								}, {
									xtype : 'hidden',
									id : 'tr_elng',
									name : 'transaction.elng'
								}, {
									xtype : 'textfield',
									fieldLabel : '<font color="red">*</font>用户名称',
									name : 'transaction.usernametwo',
									id : "tr_username",
									tabIndex : 1,
									maxLength : 20,
									minLength : 2,
									minLengthText : '最小长度不小于2位!',
									maxLengthText : '最大长度不超过20位!',
									allowBlank : false,
									blankText : '不能为空',
									enableKeyEvents : true,
									anchor : '100%',
									cls : 'x-textfield',
									validator : vd
								}, {
									xtype : 'combo',
									width : 180,
									name : 'transaction.typeid',
									id : 'tr_typeid',
									store : "TransactionTypeListStore",
									editable : false,
									allowBlank : false,
									valueField : 'id',
									displayField : 'typename',
									blankText : '请选择',
									value:1,
									fieldLabel : '<font color="red">*</font>业务类型'
								}, {
									xtype : 'datetimefield',
									maxLength : 20,
									name : 'transaction.usetime',
									id : 'tr_usetime',
									format : "Y-m-d",
									anchor : '100%',
									margin : '5 0 5 10',
									fieldLabel : '<font color="red">*</font>订单时间',
									width : 180,
									allowBlank : false,
									value : new Date()
								}, {
									xtype : 'combo',
									maxLength : 20,
									id : 'tr_tratype',
									name : 'transaction.tratype',
									store : "TraTypeStore",
									valueField : 'id',
									displayField : 'tratype',
									editable : false,
									allowBlank : false,
									fieldLabel : '<font color="red">*</font>订单类型',
									value : 0
								}, {
									xtype : 'combo',
									maxLength : 20,
									id : 'tr_isgroup',
									itemId : 'tr_isgroup',
									store : "GroupStore",
									valueField : 'type',
									displayField : 'value',
									editable : false,
									allowBlank : false,
									fieldLabel : '<font color="red">*</font>指定召车',
									value : 0
								}]
					}, {
						columnWidth : .4,
						layout : 'form',
						border : false,
						items : [{
									xtype : 'textfield',
									fieldLabel : '<font color="red">*</font>召车联系电话',
									name : 'transaction.phone',
									id : 'tr_phone',
									allowBlank : false,
									blankText : '不能为空',
									minLength : 11,
									minLengthText : '长度为11为数字!',
									maxLength : 11,
									maxLengthText : '长度为11为数字!',
									regex : /^[1][358][0-9]{9}$/,
									regexText : '以1开头的11位数字',
									anchor : '100%',
									cls : 'x-textfield',
									validator : vd
								}, {
									xtype : "fieldcontainer",
									layout : {
										type : "hbox"
									},
									width : '100%',
									items : [{
										xtype : 'textfield',
										fieldLabel : '<font color="red">*</font>召车出发地',
										name : 'transaction.saddress',
										id : "tr_saddress",
										tabIndex : 1,
										maxLength : 40,
										maxLengthText : '最大长度不超过40位!',
										allowBlank : false,
										blankText : '不能为空',
										enableKeyEvents : true,
										width : '90%',
										cls : 'x-textfield',
										validator : vd
									}, {
										text : '地图',
										xtype : "button",
										action : 'tr_sselmap',
										labelWidth : 90,
										margin : "0 0 0 5",
										labelAlign : "right"
									}]
								}, {

									xtype : "fieldcontainer",
									layout : {
										type : "hbox"
									},
									width : '100%',
									items : [{
										xtype : 'textfield',
										fieldLabel : '<font color="red">*</font>召车目的地',
										name : 'transaction.eaddress',
										id : "tr_eaddress",
										tabIndex : 1,
										maxLength : 40,
										width : '90%',
										maxLengthText : '最大长度不超过40位!',
										allowBlank : false,
										blankText : '不能为空',
										enableKeyEvents : true,
										cls : 'x-textfield',
										validator : vd
									}, {
										text : '地图',
										xtype : "button",
										action : 'tr_eselmap',
										labelWidth : 90,
										margin : "0 0 0 5",
										labelAlign : "right"
									}]
								}, {
									xtype : 'textfield',
									fieldLabel : '调度消息',
									name : 'transaction.remark',
									id : 'tr_remark',
									maxLength : 51,
									maxLengthText : '最大长度不超过51位!',
									cls : 'x-textfield'
								}, {
									xtype : 'combo',
									fieldLabel : '车牌号',
									width : 180,
									labelWidth : 80,
									id : 'tr_carnumber',
									hidden : true,
									name : 'transaction.carnumber',
									queryDelay : 800,
									minChars : 1,
									editable : false,
									store : 'CarListStore',
									valueField : 'carnumber',
									displayField : 'carnumber'
								}]
					}]

				}, {
					xtype : 'button',
					margin : '5 0 5 350',
					itemId : 'addTransactionInfo',
					text : '发送电召'
				}]
			}]
		}]
	}]
});
