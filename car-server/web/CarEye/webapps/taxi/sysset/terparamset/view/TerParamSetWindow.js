Ext.define('TerParamSetApp.view.TerParamSetWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.terParamSetWindow',
	itemId :'paramQueryWindow',
	title : '终端参数设置',
    width : 900,
    height:542,
    id:'terParamSetWindow',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
//	autoScroll : true,
	bodyStyle:'overflow-y:auto;overflow-x:hidden',
	closable : true,
	modal : true,
	closeAction : 'destroy',
	border:true,
	items :[ {
			xtype: 'form',
			id :'terparam_form',
			border:false,
			fieldDefaults: {
				labelWidth: 180,
				labelAlign: 'left'
			},
			items : [ {
					layout : 'column',
					items : [ {
							columnWidth : 0.05,
							layout : 'form',
							border:false,
							defaultType:'checkbox',
							defaults:{height:23},
							margin:'5 0 0 10',
							items : [{
									inputValue : '1',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('hbInterval');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '2',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('tcpAnswerTimeout');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '3',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('tcpReuploadNumber');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '4',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('udpAnswerTimeout');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '5',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('udpReuploadNumber');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '6',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('smsAnswerTimeout');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '7',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('smsReuploadNumber');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '16',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('masterServerApn');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '17',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('masterServerName');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '18',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('masterServerPassword');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '19',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('masterServerIp');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '20',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('spareServerApn');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '21',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('spareServerName');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '22',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('spareServerPassword');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '23',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('spareServerIp');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '24',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('serverTcpPort');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '25',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('serverUdpPort');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '32',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('posReportStrategy');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '33',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('posReportProgramme');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '34',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('driverUnloginTimeInterval');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '39',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('dormancyTimeInterval');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '40',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('alarmTimeInterval');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '41',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('defaultTimeInterval');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '44',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('defaultDistanceInterval');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '45',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('driverUnloginDistanceInterval');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '46',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('dormancyDistanceInterval');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '47',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('alarmDistanceInterval');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '48',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('inflectionPoint');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '64',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('nmpsPhone');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '65',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('resetPhone');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '66',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('factoryResetPhone');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '67',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('nmpsSmsPhone');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								}]
							},{
								columnWidth : 0.43,
								layout : 'form',
								border:false,
								margin:'5 auto', 
								items : [{
										xtype : "hidden",
										id:'carids'
									},{
										xtype : "textfield",
										fieldLabel : '终端心跳发送间隔(s)',
										name : 'basicset_p1',
										id:'hbInterval',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : 'TCP消息应答超时时间(s)',
										name : 'basicset_p2',
										id:'tcpAnswerTimeout',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : 'TCP消息重传次数',
										name : 'basicset_p3',
										id:'tcpReuploadNumber',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : 'UDP消息应答超时时间(s)',
										name : 'basicset_p4',
										id:'udpAnswerTimeout',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : 'UDP消息重传次数',
										name : 'basicset_p5',
										id:'udpReuploadNumber',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : 'SMS消息应答超时时间(s)',
										name : 'basicset_p6',
										id:'smsAnswerTimeout',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : 'SMS消息重传次数',
										name : 'basicset_p7',
										id:'smsReuploadNumber',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '主服务器APN',
										name : 'basicset_p16',
										id:'masterServerApn',
										width : 100,
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '主服务器无线通信拨号用户名',
										name : 'basicset_p17',
										id:'masterServerName',
										width : 100,
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '主服务器无线通信拨号密码',
										name : 'basicset_p18',
										id:'masterServerPassword',
										width : 100,
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '主服务器地址(IP或域名)',
										name : 'basicset_p19',
										id:'masterServerIp',
										width : 100,
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '备份服务器APN',
										name : 'basicset_p20',
										id:'spareServerApn',
										width : 100,
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '备份服务器无线通信拨号用户名',
										name : 'basicset_p21',
										id:'spareServerName',
										width : 100,
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '备份服务器无线通信拨号密码',
										name : 'basicset_p22',
										id:'spareServerPassword',
										width : 100,
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '备份服务器地址(IP或域名)',
										name : 'basicset_p23',
										id:'spareServerIp',
										width : 100,
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '服务器TCP端口',
										name : 'basicset_p24',
										id:'serverTcpPort',
										width : 100,
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '服务器UDP端口',
										name : 'basicset_p25',
										id:'serverUdpPort',
										width : 100,
										maxLength : 20,
										allowBlank : true
									},{	
										xtype : 'combo',
										fieldLabel : '位置汇报策略',
										name : 'basicset_p32',
										id:'posReportStrategy',
										store : Ext.create('Ext.data.Store', {
											fields: ['state', 'name'],
											data : [
													{"state":'0', "name":"定时汇报"},
													{"state":'1', "name":"定距汇报"},
													{"state":'2', "name":"定时和定距汇报"}
													]
												}),
										displayField : 'name',
										valueField : 'state'
									},{
										xtype : 'combo',
										fieldLabel : '位置汇报方案',
										name : 'basicset_p33',
										id:'posReportProgramme',
										store : Ext.create('Ext.data.Store', {
											fields: ['state', 'name'],
											data : [
													{"state":'0', "name":"根据ACC状态"},
													{"state":'1', "name":"根据登录状态和ACC状态"}
													]
												}),
										displayField : 'name',
										valueField : 'state'
									},{	
										xtype : "textfield",
										fieldLabel : '驾驶员未登录汇报时间间隔(s)',
										name : 'basicset_p34',
										id:'driverUnloginTimeInterval',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '休眠时汇报时间间隔(s)',
										name : 'basicset_p39',
										id:'dormancyTimeInterval',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '紧急报警时汇报时间间隔(s)',
										name : 'basicset_p40',
										id:'alarmTimeInterval',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '缺省时间汇报间隔(s)',
										name : 'basicset_p41',
										id:'defaultTimeInterval',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '缺省距离汇报间隔(m)',
										name : 'basicset_p44',
										id:'defaultDistanceInterval',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '驾驶员未登录汇报距离间隔(m)',
										name : 'basicset_p45',
										id:'driverUnloginDistanceInterval',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '休眠时汇报距离间隔(m)',
										name : 'basicset_p46',
										id:'dormancyDistanceInterval',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '紧急报警时汇报距离间隔(m)',
										name : 'basicset_p47',
										id:'alarmDistanceInterval',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "numberfield",
										fieldLabel : '拐点补传角度(度)',
										name : 'basicset_p48',
										id:'inflectionPoint',
										width : 100,
										maxValue : 180,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '监控平台电话号码',
										name : 'basicset_p64',
										id:'nmpsPhone',
										width : 100,
										regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
										regexText : '格式不正确',
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '复位电话号码',
										name : 'basicset_p65',
										id:'resetPhone',
										width : 100,
										regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
										regexText : '格式不正确',
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '恢复出厂设置电话号码',
										name : 'basicset_p66',
										id:'factoryResetPhone',
										width : 100,
										regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
										regexText : '格式不正确',
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '监控平台SMS电话号码',
										name : 'basicset_p67',
										id:'nmpsSmsPhone',
										width : 100,
										regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
										regexText : '格式不正确',
										maxLength : 20,
										allowBlank : true
									}]
					},{
						columnWidth : 0.05,
							layout : 'form',
							border:false,
							defaultType:'checkbox',
							defaults:{height:24},
							margin:'5 0 0 10',
							items : [{
									inputValue : '68',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('smsAlarmPhone');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '69',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('phoneAnswerStrategy');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '70',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('everyLongestCallTime');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '71',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('monthLongestCallTime');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '72',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('monitorPhone');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '73',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('privilegeSmsPhone');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '80',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('alarmMaskWord');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '81',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('alarmSmsSwitch');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '82',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('alarmShotSwitch');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '83',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('alarmShotStorageFlag');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '84',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('keyFlag');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '85',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('highSpeed');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '86',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('overSpeedTime');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '87',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('continueDrivingTime');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '88',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('dayTotalDrivingTime');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '89',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('minRestTime');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '90',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('maxParkingTime');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '112',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('imageQuality');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '113',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('brightness');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '114',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('contrastRatio');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '115',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('saturation');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '116',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('chroma');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '128',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('vehicleMileage');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '129',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('carProvince');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '130',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('carCity');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '131',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('carCarnumber');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '132',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('carColor');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '133',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('engineDisplacement');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '134',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('totalOilInit');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								},{
									inputValue : '135',
									listeners : {
										'change' : function(obj,ischecked) {
											var textfield = Ext.getCmp('insurancePhone');
											if (ischecked) {
												if (textfield.getValue()==null ||textfield.getValue().length == 0) {
													textfield.allowBlank = false;
													textfield.focus();
												}
											} else {
												textfield.allowBlank = true;
												textfield.focus();
											}
										}
									}
								}]
						},{
								columnWidth : 0.43,
								layout : 'form',
								border:false,
								margin:'5 auto', 
								items : [{
										xtype : "textfield",
										fieldLabel : '接收终端SMS文本报警号码',
										name : 'basicset_p68',
										id:'smsAlarmPhone',
										width : 100,
										regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
										regexText : '格式不正确',
										maxLength : 20,
										allowBlank : true
									},{	
										xtype : 'combo',
										fieldLabel : '终端电话接听策略',
										name : 'basicset_p69',
										id:'phoneAnswerStrategy',
										store : Ext.create('Ext.data.Store', {
											fields: ['state', 'name'],
											data : [
													{"state":'0', "name":"自动接听"},
													{"state":'1', "name":"ACC ON时自动接听,OFF时手动接听"}
													]
												}),
										displayField : 'name',
										valueField : 'state'
									},{
										xtype : "numberfield",
										fieldLabel : '每次最长通话时间(s)',
										name : 'basicset_p70',
										id:'everyLongestCallTime',
										width : 100,
										minValue : 0,
										maxValue : 4294967295,
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "numberfield",
										fieldLabel : '当月最长通话时间(s)',
										name : 'basicset_p71',
										id:'monthLongestCallTime',
										width : 100,
										minValue : 0,
										maxValue : 4294967295,
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '监听电话号码',
										name : 'basicset_p72',
										id:'monitorPhone',
										width : 100,
										regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
										regexText : '格式不正确',
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '监管平台特权短信号码',
										name : 'basicset_p73',
										id:'privilegeSmsPhone',
										width : 100,
										regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
										regexText : '格式不正确',
										maxLength : 20,
										allowBlank : true
									},{	
										xtype : 'combo',
										fieldLabel : '报警屏蔽字',
										name : 'basicset_p80',
										id:'alarmMaskWord',
										store : Ext.create('Ext.data.Store', {
											fields: ['state', 'name'],
											data : [
													{"state":'0', "name":"不屏蔽"},
													{"state":'1', "name":"屏蔽"}
													]
												}),
										displayField : 'name',
										valueField : 'state'
									},{
										xtype : 'combo',
										fieldLabel : '报警发送文本SMS开关',
										name : 'basicset_p81',
										id:'alarmSmsSwitch',
										store : Ext.create('Ext.data.Store', {
											fields: ['state', 'name'],
											data : [
													{"state":'0', "name":"关闭"},
													{"state":'1', "name":"开启"}
													]
												}),
										displayField : 'name',
										valueField : 'state'
									},{
										xtype : 'combo',
										fieldLabel : '报警拍摄开关',
										name : 'basicset_p82',
										id:'alarmShotSwitch',
										store : Ext.create('Ext.data.Store', {
											fields: ['state', 'name'],
											data : [
													{"state":'0', "name":"关闭"},
													{"state":'1', "name":"开启"}
													]
												}),
										displayField : 'name',
										valueField : 'state'
									},{
										xtype : 'combo',
										fieldLabel : '报警拍摄存储标志',
										name : 'basicset_p83',
										id:'alarmShotStorageFlag',
										store : Ext.create('Ext.data.Store', {
											fields: ['state', 'name'],
											data : [
													{"state":'0', "name":"不存储"},
													{"state":'1', "name":"存储"}
													]
												}),
										displayField : 'name',
										valueField : 'state'
									},{
										xtype : 'combo',
										fieldLabel : '关键标志',
										name : 'basicset_p84',
										id:'keyFlag',
										store : Ext.create('Ext.data.Store', {
											fields: ['state', 'name'],
											data : [
													{"state":'0', "name":"不是关键报警"},
													{"state":'1', "name":"关键报警"}
													]
												}),
										displayField : 'name',
										valueField : 'state'
									},{
										xtype : "textfield",
										fieldLabel : '最高速度(km/h)',
										name : 'basicset_p85',
										id:'highSpeed',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '超速持续时间(s)',
										name : 'basicset_p86',
										id:'overSpeedTime',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '连续驾驶时间门限(s)',
										name : 'basicset_p87',
										id:'continueDrivingTime',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '当天累计驾驶时间门限(s)',
										name : 'basicset_p88',
										id:'dayTotalDrivingTime',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '最小休息时间(s)',
										name : 'basicset_p89',
										id:'minRestTime',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '最长停车时间(s)',
										name : 'basicset_p90',
										id:'maxParkingTime',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "numberfield",
										fieldLabel : '图像/视频质量(1-10,1最好)',
										name : 'basicset_p112',
										id:'imageQuality',
										width : 100,
										minValue : 1,
										maxValue : 10,
										allowBlank : true
									},{
										xtype : "numberfield",
										fieldLabel : '亮度(0-255)',
										name : 'basicset_p113',
										id:'brightness',
										width : 100,
										minValue : 0,
										maxValue : 255,
										allowBlank : true
									},{
										xtype : "numberfield",
										fieldLabel : '对比度(0-127)',
										name : 'basicset_p114',
										id:'contrastRatio',
										width : 100,
										minValue : 0,
										maxValue : 127,
										allowBlank : true
									},{
										xtype : "numberfield",
										fieldLabel : '饱和度(0-127)',
										name : 'basicset_p115',
										id:'saturation',
										width : 100,
										minValue : 0,
										maxValue : 127,
										allowBlank : true
									},{
										xtype : "numberfield",
										fieldLabel : '色度(0-255)',
										name : 'basicset_p116',
										id:'chroma',
										width : 100,
										minValue : 0,
										maxValue : 255,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '车辆里程表读数(0.1km)',
										name : 'basicset_p128',
										id:'vehicleMileage',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '车辆所在的省域ID',
										name : 'basicset_p129',
										id:'carProvince',
										width : 100,
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '车辆所在的市域ID',
										name : 'basicset_p130',
										id:'carCity',
										width : 100,
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '公安交通部门颁发的机动车号牌',
										name : 'basicset_p131',
										id:'carCarnumber',
										width : 100,
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '车牌颜色',
										name : 'basicset_p132',
										id:'carColor',
										width : 100,
										maxLength : 20,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '车辆发动机排量(0.1L)',
										name : 'basicset_p133',
										id:'engineDisplacement',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '总耗油初值(0.1L)',
										name : 'basicset_p134',
										id:'totalOilInit',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : "textfield",
										fieldLabel : '报险电话号码',
										name : 'basicset_p135',
										id:'insurancePhone',
										width : 100,
										regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
										regexText : '格式不正确',
										maxLength : 20,
										allowBlank : true
									}]
							}]
					}]
		 }],
			buttons : [{
						text : '设置',
						action : 'save'
					}, {
						text : '取消',
						handler : function(btn) {
							btn.up('window').close();
						}
					}]
});
	  							
