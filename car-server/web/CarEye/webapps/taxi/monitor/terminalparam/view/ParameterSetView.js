Ext.define('CarParamApp.view.ParameterSetView', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.parameterSetView',
			split : true,
			frame: false,
			width : "58%",
			title:'车辆参数设置',
			region : "east",
			layout : 'form',
			autoScroll:true,
			items : [{
						xtype : 'form',
						padding:'0 5',
						id : 'parameterSet_Form',
						anchor : '100%',
						border : false,
						collapsible : false,
						buttonAlign : 'right',
						fieldDefaults : {
							labelAlign : 'left',
							labelWidth : 170
						},
						items : [{ 
							xtype : 'form',
							id : 'form_11',
							border : false,
							items : [ {
								layout : 'column',
								border:false,
								items : [{
									columnWidth : 0.49,
									layout : 'form',
									border:false,
									items : [
										{
											xtype : "textfield",
											fieldLabel : '终端心跳发送间隔(S)',
											name : 'basicset_p1',
											id:'terminalHeatTime',
											regex : /^[0-9]{0,6}?$/,
											regexText : '正数不能超过6位',
											maxLength : 6
										},
										{
											xtype : "textfield",
											fieldLabel : 'TCP消息重传次数',
											name : 'basicset_p3',
											id:'tcpResend',
											regex : /^[0-9]{0,6}?$/,
											regexText : '正数不能超过6位',
											maxLength : 6
										},
										{
											xtype : "textfield",
											fieldLabel : 'UDP消息重传次数',
											name : 'basicset_p5',
											id:'udpResend',
											regex : /^[0-9]{0,6}?$/,
											regexText : '正数不能超过6位',
											maxLength : 6
										},
										{
											xtype : "textfield",
											fieldLabel : '主服务器APN参数',
											name : 'basicset_p16',
											id:'apnParam',
											maxLength : 32,
											regexText : '不能超过32位'
										},
										{
											xtype : "textfield",
											fieldLabel : '主服务器无线通讯拨号密码',
											name : 'basicset_p18',
											id:'mainPwd',
											maxLength : 32,
											regexText : '不能超过32位'
										},
										{
											xtype : "textfield",
											fieldLabel : '备份服务器APN参数',
											name : 'basicset_p20',
											id:'backApnParam',
											maxLength : 50,
											regexText : '不能超过50位'
										},
										{
											xtype : "textfield",
											fieldLabel : '备份服务器无线通讯拨号密码',
											name : 'basicset_p22',
											id:'backPwd',
											regex : /^[0-9]{0,32}?$/,
											regexText : '正数不能超过32位',
											maxLength : 32
										},{
											xtype : 'combo',
											fieldLabel : '位置汇报策略',
											name : 'basicset_p32',
											id:'pointUp',
											store : Ext.create('Ext.data.Store', {
											    fields: ['state', 'name'],
											    data : [
											        {"state":0, "name":"定时汇报"},
											        {"state":1, "name":"定距汇报"},
											        {"state":2, "name":"定时定距汇报"}
											    ]
											}),
											displayField : 'name',
											valueField : 'state'
										},{
											xtype : "textfield",
											fieldLabel : '休眠时汇报时间间隔(S)',
											name : 'basicset_p39',
											id:'sleepUp',
											regex : /^[0-9]{0,10}?$/,
											regexText : '正数不能超过10位',
											maxLength : 10
										},
										{
											xtype : "textfield",
											fieldLabel : '缺省时间汇报间隔(S)',
											name : 'basicset_p41',
											id:'defaultSecondUp',
											regex : /^[0-9]{0,10}?$/,
											regexText : '正数不能超过10位',
											maxLength : 10
										},
										{
											xtype : "textfield",
											fieldLabel : '驾驶员未登陆汇报时间间隔(S)',
											name : 'basicset_p34',
											id:'unLoginTime',
											regex : /^[0-9]{0,10}?$/,
											regexText : '正数不能超过10位',
											maxLength : 10
										},
										{
											xtype : "textfield",
											fieldLabel : '驾驶员未登陆汇报距离间隔(S)',
											name : 'basicset_p45',
											id:'unLoginSecond',
											regex : /^[0-9]{0,10}?$/,
											regexText : '正数不能超过10位',
											maxLength : 10
										},
										{
											xtype : "textfield",
											fieldLabel : '紧急报警时汇报距离间隔(S)',
											name : 'basicset_p47',
											id:'emerMileSecond',
											regex : /^[0-9]{0,10}?$/,
											regexText : '正数不能超过10位',
											maxLength : 10
										},
										{
											xtype : "textfield",
											fieldLabel : '监控平台电话号码',
											name : 'basicset_p64',
											id:'platMonitorPhone',
											regex : /^[0-9]{0,15}?$/,
											regexText : '正数不能超过15位',
											maxLength : 15
										},
										{
											xtype : "textfield",
											fieldLabel : '恢复出厂设置电话号码',
											name : 'basicset_p66',
											id:'recovePhone',
											regex : /^[0-9]{0,15}?$/,
											regexText : '正数不能超过15位',
											maxLength : 15
										},
										{
											xtype : "textfield",
											fieldLabel : '接受终端SMS文本报警号码',
											name : 'basicset_p68',
											id:'smsPhone',
											regex : /^[0-9]{0,15}?$/,
											regexText : '正数不能超过15位',
											maxLength : 15
										},
										{
											xtype : "textfield",
											fieldLabel : '每次最长通话时间(S)',
											name : 'basicset_p70',
											id:'longSecond',
											regex : /^[0-9]{0,10}?$/,
											regexText : '正数不能超过10位',
											maxLength : 10
										},
										{
											xtype : "textfield",
											fieldLabel : '监听电话号码',
											name : 'basicset_p72',
											id:'monitorPhone',
											regex : /^[0-9]{0,15}?$/,
											regexText : '正数不能超过15位',
											maxLength : 15
										},{
											xtype : 'button',
											margin : '10 0 10 100',
											action : 'paramset',
											id:'b_11',
											text : '基本参数设置' 
										}]
								}, {
									columnWidth : 0.49,
									layout : 'form',
									padding:'0 10',
									border:false,
									items : [
										{
											xtype : "textfield",
											fieldLabel : 'TCP消息应答超时时间(S)',
											name : 'basicset_p2',
											id:'tcpTimeOut',
											regex : /^[0-9]{0,6}?$/,
											regexText : '正数不能超过6位',
											maxLength : 6
										},
										{
											xtype : "textfield",
											fieldLabel : 'UDP消息应答超时时间(S)',
											name : 'basicset_p4',
											id:'udpTimeOut',
											regex : /^[0-9]{0,6}?$/,
											regexText : '正数不能超过6位',
											maxLength : 6
										},
										{
											xtype : "textfield",
											fieldLabel : 'SMS消息重传次数',
											name : 'basicset_p7',
											id:'smsRecount',
											regex : /^[0-9]{0,6}?$/,
											regexText : '正数不能超过6位',
											maxLength : 6
										},
										{
											xtype : "textfield",
											fieldLabel : '主服务器无线通讯拨号用户名',
											name : 'basicset_p17',
											id:'mainLogin',
											regex : /^[0-9]{0,32}?$/,
											regexText : '正数不能超过32位',
											maxLength : 32
										},
										{
											xtype : "textfield",
											fieldLabel : '主服务器地址',
											name : 'basicset_p19',
											id:'mainAddr',
											maxLength : 100,
											regexText : '不能超过100位'
										},
										{
											xtype : "textfield",
											fieldLabel : '备份服务器无线通讯拨号用户名',
											name : 'basicset_p21',
											id:'backLogin',
											regex : /^[0-9]{0,32}?$/,
											regexText : '正数不能超过32位',
											maxLength : 32
										},
										{
											xtype : "textfield",
											fieldLabel : '备份服务器地址',
											name : 'basicset_p23',
											id:'backAddr',
											regexText : '不能超过100位',
											maxLength : 100
										},{
											xtype : 'combo',
											fieldLabel : '位置汇报方案',
											name : 'basicset_p33',
											id:'pointUpScheme',
											store : Ext.create('Ext.data.Store', {
											    fields: ['state', 'name'],
											    data : [
											        {"state":0, "name":"根据ACC状态"},
											        {"state":1, "name":"根据登录状态和ACC状态"}
											    ]
											}),
											displayField : 'name',
											valueField : 'state'
										},{
											xtype : "textfield",
											fieldLabel : '紧急报警时汇报时间间隔(秒)',
											regex : /^[0-9]{0,10}?$/,
											name : 'basicset_p40',
											id:'emerSecond',
											regexText : '正数不能超过10位',
											maxLength : 10
										},{
											xtype : "textfield",
											fieldLabel : '缺省距离汇报间隔(秒)',
											regex : /^[0-9]{0,10}?$/,
											name : 'basicset_p44',
											id:'defaultUpMile',
											regexText : '正数不能超过10位',
											maxLength : 10
										},
										{
											xtype : "textfield",
											fieldLabel : '休眠时汇报距离间隔(秒)',
											regex : /^[0-9]{0,10}?$/,
											name : 'basicset_p46',
											id:'sleepUpMile',
											regexText : '正数不能超过10位',
											maxLength : 10,
											allowBlank : true
										},
										{
											xtype : "textfield",
											fieldLabel : '拐点补传角度(度)',
											regex : /^[0-9]{0,6}?$/,
											name : 'basicset_p48',
											id:'inflection',
											regexText : '正数不能超过6位',
											maxLength : 6
										},
										{
											xtype : "textfield",
											fieldLabel : '复位电话号码',
											name : 'basicset_p65',
											id:'resetPhone',
											regex : /^[0-9]{0,15}?$/,
											regexText : '正数不能超过15位',
											maxLength : 15
										},
										{
											xtype : "textfield",
											fieldLabel : '监控平台SMS电话号码',
											name : 'basicset_p67',
											id:'platformSmsPhone',
											wnameth : 100,
											regex : /^[0-9]{0,15}?$/,
											regexText : '正数不能超过15位',
											maxLength : 15
										},{
											xtype : 'combo',
											fieldLabel : '终端电话接通策略',
											name : 'basicset_p69',
											id:'terminalPick',
											store : Ext.create('Ext.data.Store', {
											    fields: ['state', 'name'],
											    data : [
											        {"state":0, "name":"自动接听"},
											        {"state":1, "name":"ACC接通"}
											    ]
											}),
											displayField : 'name',
											valueField : 'state'
										},{
											xtype : "textfield",
											name : 'basicset_p71',
											id:'monthSecond',
											fieldLabel : '当月最长通话时间',
											regex : /^[0-9]{0,10}?$/,
											regexText : '正数不能超过10位',
											maxLength : 10
										},
										{
											xtype : "textfield",
											name : 'basicset_p73',
											id:'platformAdminSmsPhone',
											fieldLabel : '监管平台特权短信号码',
											regex : /^[0-9]{0,15}?$/,
											regexText : '正数不能超过15位',
											maxLength : 15
										},{
											xtype : 'button',
											margin : '25 0 0 100',
											action : 'paramquery',
											id:'q_11',
											text : '查询基本参数' 
										} ]
								} ]
							} ]
						},{
							xtype : 'form',
							id : 'form_12',
							border : false,
							hidden:true,
							items : [ {
								layout : 'column',
								border:false,
								items : [{
									columnWidth : 0.49,
									layout : 'form',
									border:false,
									items : [
										{
											xtype : "textfield",
											fieldLabel : '最高速度(km/小时)',
											name : 'basicset_p85',
											id:'highSpeed',
											regex : /^[0-9]{0,3}?$/,
											regexText : '正数不能超过3位',
											maxLength : 3,
											allowBlank : true
										},
										{
											xtype : "textfield",
											fieldLabel : '连续驾驶时间上限(S)',
											name : 'basicset_p87',
											id:'continuTimeTop',
											regex : /^[0-9]{0,10}?$/,
											regexText : '正数不能超过10位',
											maxLength : 10,
											allowBlank : true
										},
										{
											xtype : "textfield",
											fieldLabel : '最小休息时间(S)',
											name : 'basicset_p89',
											id:'smallSleepTime',
											regex : /^[0-9]{0,10}?$/,
											regexText : '正数不能超过10位',
											maxLength : 10,
											allowBlank : true
										},{
											xtype : 'button',
											margin : '10 0 0 200',
											action : 'paramset',
											id:'b_12',
											text : '行驶参数设置' 
										}]
								},{
									columnWidth : 0.49,
									padding:'0 10',
									layout : 'form',
									border:false,
									items : [
										{
											xtype : "textfield",
											fieldLabel : '超速持续时间(S)',
											name : 'basicset_p86',
											id:'superSpeedSecood',
											regex : /^[0-9]{0,10}?$/,
											regexText : '正数不能超过10位',
											maxLength : 10,
											allowBlank : true
										},
										{
											xtype : "textfield",
											fieldLabel : '当天累计驾驶时间上限(S)',
											name : 'basicset_p88',
											id:'totalDriverTime',
											regex : /^[0-9]{0,10}?$/,
											regexText : '正数不能超过10位',
											maxLength : 10,
											allowBlank : true
										},
										{
											xtype : "textfield",
											fieldLabel : '最长停车时间(S)',
											name : 'basicset_p90',
											id:'longunDriverSecond',
											regex : /^[0-9]{0,10}?$/,
											regexText : '正数不能超过10位',
											maxLength : 10,
											allowBlank : true
										},{
											xtype : 'button',
											margin : '10 0 10 100',
											action : 'paramquery',
											id:'q_12',
											text : '查询行驶参数' 
										} ]
								} ]
							}]
						},{
							xtype : 'form',
							border : false,
							id : 'form_13',
							hidden:true,
							items : [ {
								layout : 'column',
								border:false,
								items : [{
								columnWidth : 0.49,
								layout : 'form',
								border:false,
								items : [
									{
										xtype : "textfield",
										fieldLabel : '车辆里程表读数',
										name : 'basicset_p128',
										id:'carMile',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},
									{
										xtype : "textfield",
										fieldLabel : '车辆所在的市域ID',
										name : 'basicset_p130',
										id:'carCityId',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : 'combo',
										fieldLabel : '车牌颜色',
										name : 'basicset_p132',
										id:'carColor',
										store : Ext.create('Ext.data.Store', {
										    fields: ['state', 'name'],
										    data : [
										        {"state":1, "name":"红"},
										        {"state":2, "name":"黄"},
										        {"state":3, "name":"蓝"}
										    ]
										}),
										displayField : 'name',
										valueField : 'state'
									},{
										xtype : 'button',
										margin : '10 0 5 200',
										action : 'paramset',
										id:'b_13',
										text : '区域参数设置' 
								 }]
							},{
								columnWidth : 0.49,
								layout : 'form',
								border:false,
								padding:'0 10', 
								items : [
									{
										xtype : "textfield",
										fieldLabel : '车辆所在省域ID',
										name : 'basicset_p129',
										id:'carProvinteId',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},
									{
										xtype : "textfield",
										fieldLabel : '机动车号牌',
										name : 'basicset_p131',
										id:'carCard',
										width : 100,
										maxLength : 10,
										regexText : '不能超过10位',
										allowBlank : true
									},{
										xtype : "displayfield"
									},{
										xtype : 'button',
										margin : '10 0 10 100',
										action : 'paramquery',
										id:'q_13',
										text : '查询区域参数' 
									}]
							} ]
						}]
						},{
							xtype : 'form',
							border : false,
							id : 'form_14',
							hidden:true,
							items : [{ 
								layout : 'column',
								border:false,
								items : [ {
								columnWidth : 0.49,
								layout : 'form',
								border:false,
								items : [
									{
										xtype : "textfield",
										fieldLabel : '车辆里程表读数',
										name : 'basicset_p128',
										id:'qtcarMile',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},
									{
										xtype : "textfield",
										fieldLabel : '车辆所在的市域ID',
										name : 'basicset_p130',
										id:'qtcarCityId',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},{
										xtype : 'combo',
										fieldLabel : '车牌颜色',
										name : 'basicset_p132',
										id:'qtcarColor',
										store : Ext.create('Ext.data.Store', {
										    fields: ['state', 'name'],
										    data : [
										        {"state":1, "name":"红"},
										        {"state":2, "name":"黄"},
										        {"state":3, "name":"蓝"}
										    ]
										}),
										displayField : 'name',
										valueField : 'state'
									},{
										xtype : 'button',
										margin : '10 0 5 200',
										action : 'paramset',
										id:'b_14',
										text : '其它参数设置' 
								 }]
							},{
								columnWidth : 0.49,
								layout : 'form',
								border:false,
								padding:'0 10', 
								items : [
									{
										xtype : "textfield",
										fieldLabel : '车辆所在省域ID',
										name : 'basicset_p129',
										id:'qtcarProvinteId',
										width : 100,
										regex : /^[0-9]{0,10}?$/,
										regexText : '正数不能超过10位',
										maxLength : 10,
										allowBlank : true
									},
									{
										xtype : "textfield",
										fieldLabel : '机动车号牌',
										name : 'basicset_p131',
										id:'qtcarCard',
										width : 100,
										maxLength : 10,
										regexText : '不能超过10位',
										allowBlank : true
									},{
										xtype : "displayfield"
									},{
										xtype : 'button',
										margin : '10 0 10 100',
										action : 'paramquery',
										id:'q_14',
										text : '查询其它参数' 
									}]
							} ]
																						
							}]
						}]
					}]
		});