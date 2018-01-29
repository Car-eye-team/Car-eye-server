Ext.define('RemoteControlApp.view.ParameterSetView', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.parameterSetView',
			split : true,
			frame: false,
			width : 350,
			title:'参数设置',
			region : "east",
			layout : 'form',
			autoSrcoll:true,
			items : [{
						xtype : 'form',
						id : 'parameterSet_Form',
						anchor : '100%',
						border : false,
						collapsible : false,
						buttonAlign : 'right',
						fieldDefaults : {
							labelAlign : 'left',
							labelWidth : 220,
							margin : '5 0 5 30'
						},
						items : [{
									xtype : 'panel',
									border : false,
									id : 'form_sc',
									items : [{
												xtype : "displayfield",
												fieldLabel : '加锁/解锁'
											}, {
												xtype : 'combo',
												id:'p_sc',
												editable:false,
												store : Ext.create('Ext.data.Store', {
												    fields: ['state', 'name'],
												    data : [
												        {"state":0, "name":"加锁"},
												        {"state":1, "name":"解锁"}
												    ]
												}),
												displayField : 'name',
												valueField : 'state',
												value:0
											}, {
												xtype : 'button',
												margin : '5 0 5 100',
												action : 'paramset',
												id:'b_sc',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_xh',
									items : [{
												xtype : "displayfield",
												fieldLabel : '熄火'
											}, {
												xtype : 'button',
												id:'b_xh',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_dy',
									items : [{ 
									            xtype : "displayfield",
												fieldLabel : '断油'
											}, {
												xtype : 'button',
												id:'b_dy',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_dd',
									items : [{
											    xtype : "displayfield",
												fieldLabel : '断电'
											}, {
												xtype : 'button',
												id:'b_dd',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_wbxx',
									items : [{
												xtype : "displayfield",
												fieldLabel : '文本信息'
											}, {
												xtype : 'button',
												id:'b_wbxx',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_fsfwm',
									items : [{
												xtype : 'combo',
												fieldLabel : '<font color="red">*</font>显示方式',
												labelWidth : 120,
												labelAlign : 'left',
												id:'style',
												editable:false,
												store : Ext.create('Ext.data.Store', {
												    fields: ['state', 'name'],
												    data : [
												        {"state":161, "name":"固定显示"},
												        {"state":162, "name":"闪烁显示"}
												    ]
												}),
												displayField : 'name',
												valueField : 'state',
												value:161
											}, {
												xtype : 'combo',
												editable:false,
												fieldLabel : '<font color="red">*</font>字符的颜色',
												labelWidth : 120,
												labelAlign : 'left',
												id:'color',
												store : Ext.create('Ext.data.Store', {
												    fields: ['state', 'name'],
												    data : [
												        {"state":1, "name":"红色"},
												        {"state":2, "name":"绿色"},
												        {"state":3, "name":"蓝色"}
												    ]
												}),
												displayField : 'name',
												valueField : 'state',
												value:1
											}, {
												xtype : "textfield",
												id:'content',
												fieldLabel : '<font color="red">*</font>显示内容',
												labelAlign : 'left',
												labelWidth : 120,
												allowBlank : false,
							                	blankText : '不能为空'
											}, {
												xtype : "textfield",
												id:'delayed',
												labelAlign : 'left',
												fieldLabel : '<font color="red">*</font>延迟(单位s)',
												labelWidth : 120,
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^[0-9]{0,3}?$/,
												regexText : '正数不能超过3位',
												maxLength : 3
											}, {
												xtype : "textfield",
												id:'time',
												labelAlign : 'left',
												fieldLabel : '<font color="red">*</font>显示时间(单位s)',
												labelWidth : 120,
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^[0-9]{0,5}?$/,
												regexText : '正数不能超过5位',
												maxLength : 5
											}, {
												xtype : 'button',
												id:'b_fsfwm',
												margin : '5 0 5 150',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_fsdz',
									items : [{
												xtype : 'hidden',
												id : 'slat'
											}, {
												xtype : 'hidden',
												id : 'slng'
											}, {
												xtype : 'hidden',
												id : 'elat'
											}, {
												xtype : 'hidden',
												id : 'elng'
											}, {
												xtype : 'combo',
												id:'answermode',
												labelWidth : 80,
												margin : '5 0 5 10',
												editable:false,
												fieldLabel : '<font color="red">*</font>抢答方式',
												store : Ext.create('Ext.data.Store', {
												    fields: ['state', 'name'],
												    data : [
												        {"state":1, "name":"电话抢答"},
												        {"state":2, "name":"无线抢答"}
												    ]
												}),
												displayField : 'name',
												valueField : 'state',
												value:1
											}, {
												xtype : 'combo',
												id:'ordertype',
												labelWidth : 80,
												margin : '5 0 5 10',
												editable:false,
												fieldLabel : '<font color="red">*</font>订单类型',
												store : Ext.create('Ext.data.Store', {
												    fields: ['state', 'name'],
												    data : [
												        {"state":0, "name":"即时订单"},
												        {"state":1, "name":"预约订单"}
												    ]
												}),
												displayField : 'name',
												valueField : 'state',
												value:1
											}, {
												xtype : 'textfield',
												fieldLabel : '<font color="red">*</font>乘客姓名',
												id : 'zb_passengername',
												margin : '5 0 5 10',
												blankText : '不能为空',
												allowBlank : false,
												labelWidth : 80,
												cls : 'x-textfield',
												regex : /^([\u4E00-\u9FA5]+|[a-zA-Z]+)$/,
												regexText : '只能输入2~10个字母或汉字的名字!',
												validator : vd
											}, {
												xtype : 'textfield',
												fieldLabel : '<font color="red">*</font>乘客电话',
												name : 'transaction.phone',
												id : 'zb_phone',
												allowBlank : false,
												labelWidth : 80,
												margin : '5 0 5 10',
												blankText : '不能为空',
												regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
												regexText : '格式：3-4位区号，7-8位直播号码，1－4位分机号',
												anchor : '100%',
												cls : 'x-textfield',
												validator : vd
											}, {
												xtype : 'datetimefield',
												maxLength : 20,
												id : 'ordertime',
												format:"Y-m-d",
												anchor : '100%',
												margin : '5 0 5 10',
												fieldLabel : '<font color="red">*</font>订单时间',
												labelWidth: 80,
												editable: false,
												value:new Date()
											}, {
												xtype : "fieldcontainer",
												margin : '5 0 5 10',
												layout : {
													type : "hbox"
												},
												width : 330,
												items : [{
													xtype : 'textfield',
													fieldLabel : '<font color="red">*</font>乘客出发地',
													name : 'transaction.saddress',
													id : "saddress",
													tabIndex : 1,
													margin : '0 0',
													maxLength : 40,
													labelWidth : 80,
													maxLengthText : '最大长度不超过40位!',
													allowBlank : false,
													blankText : '不能为空',
													enableKeyEvents : true,
													width : 230,
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
												margin : '5 0 5 10',
												layout : {
													type : "hbox"
												},
												width : 330,
												items : [{
													xtype : 'textfield',
													fieldLabel : '<font color="red">*</font>乘客目的地',
													name : 'transaction.eaddress',
													id : "eaddress",
													margin : '0 0',
													tabIndex : 1,
													maxLength : 40,
													width : 230,
													labelWidth : 80,
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
												xtype : 'textarea',
												fieldLabel : '调度信息',
												id : 'contents',
												margin : '5 0 5 10',
												labelWidth : 80,
												maxLength : 51,
												maxLengthText : '最大长度不超过51位!',
												cls : 'x-textfield'
											},{
												xtype : 'button',
												id:'b_fsdz',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '发送'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_zbfs',
									items : [{
												xtype : 'textfield',
												fieldLabel : '<font color="red">*</font>订单号',
												id : 'orderid',
												allowBlank : false,
												labelWidth : 80,
												cls : 'x-textfield',
												regex : /^\d+$/,
												regexText : '格式：大于0的整数',
												validator : vd
											}, {
												xtype : 'datetimefield',
												id : 'zbfs_ordertime',
												format:"Y-m-d",
												fieldLabel : '<font color="red">*</font>订单时间',
												labelWidth: 80,
												editable: false,
												value:new Date()
											}, {
												xtype : 'textfield',
												fieldLabel : '<font color="red">*</font>乘客姓名',
												id : 'passengername',
												allowBlank : false,
												labelWidth : 80,
												cls : 'x-textfield',
												validator : vd
											}, {
												xtype : 'textfield',
												fieldLabel : '<font color="red">*</font>乘客电话',
												id : 'passengerphone',
												allowBlank : false,
												regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
												regexText : '格式：3-4位区号，7-8位直播号码，1－4位分机号',
												labelWidth : 80,
												blankText : '不能为空'
//											}, {
//												xtype : 'textfield',
//												fieldLabel : '<font color="red">*</font>车牌号',
//												id : 'carnum',
//												allowBlank : false,
//												labelWidth : 80,
//												regex : /^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4}[a-zA-Z_0-9_\u4e00-\u9fa5]$|^[a-zA-Z]{2}\d{7}$/,
//												regexText : '车牌号格式不正确',
//												blankText : '不能为空',
//												anchor : '100%',
//												cls : 'x-textfield'
											}, {
												xtype : 'textfield',
												fieldLabel : '<font color="red">*</font>本机手机号',
												id : 'localphone',
												allowBlank : false,
												regex : /^[1][358][0-9]{9}$/,
												regexText : '以1开头的11位数字',
												labelWidth : 80,
												blankText : '不能为空',
												cls : 'x-textfield'
											}, {
												xtype : 'textfield',
												fieldLabel : '<font color="red">*</font>电召费',
												id : 'callfee',
												allowBlank : false,
												regex : /^[\d\.]*$/,
												regexText : '只允许数字类型',
												labelWidth : 80,
												blankText : '不能为空',
												cls : 'x-textfield'
											}, {
												xtype : 'textarea',
												fieldLabel : '调度信息',
												labelWidth : 80,
												id : 'zb_contents',
												allowBlank : false,
												maxLength : 51,
												maxLengthText : '最大长度不超过51位!',
												cls : 'x-textfield'
											}, {
												xtype : 'button',
												id:'b_zbfs',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_zdgj',
									items : [{
												xtype : "displayfield",
												fieldLabel : '终端关机'
											}, {
												xtype : "hidden",
												id:'p_zdgj'
											}, {
												xtype : 'button',
												id:'b_zdgj',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_zdfw',
									items : [{
												xtype : "displayfield",
												fieldLabel : '终端复位'
											}, {
												xtype : "hidden",
												id:'p_zdfw'
											}, {
												xtype : 'button',
												id:'b_zdfw',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_zdhfccsz',
									items : [{
												xtype : "displayfield",
												fieldLabel : '终端恢复出厂设置'
											}, {
												xtype : "hidden",
												id:'p_zdhfccsz'
											}, {
												xtype : 'button',
												id:'b_zdhfccsz',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_gbsjtx',
									items : [{
												xtype : "displayfield",
												fieldLabel : '关闭数据通信'
											}, {
												xtype : "hidden",
												id:'p_gbsjtx'
											}, {
												xtype : 'button',
												id:'b_gbsjtx',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_gbsywxtx',
									items : [{
												xtype : "displayfield",
												fieldLabel : '关闭所有无线通信'
											}, {
												xtype : "hidden",
												id:'p_gbsywxtx'
											}, {
												xtype : 'button',
												id:'b_gbsywxtx',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_rdkz',
									items : [{
												xtype : "displayfield",
												fieldLabel : 'WIFI热点控制'
											}, {
												xtype : 'combo',
												id:'p_rdkz',
												editable:false,
												store : Ext.create('Ext.data.Store', {
												    fields: ['state', 'name'],
												    data : [
												        {"state":1, "name":"打开"},
												        {"state":2, "name":"关闭"}
												    ]
												}),
												displayField : 'name',
												valueField : 'state',
												value:1
											}, {
												xtype : 'button',
												id:'b_rdkz',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								}]
					}]
		});