Ext.define('TerminalSetApp.view.ParameterSetView', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.parameterSetView',
			split : true,
			frame: false,
			width : 350,
			title:'参数设置',
			region : "east",
			layout : 'form',
			autoScroll:true,
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
									id : 'form_16',
									items : [{
												xtype : "displayfield",
												fieldLabel : '主服务器APN，无线通信拨号访问点'
											}, {
												xtype : "textfield",
												id:'p_16',
												allowBlank : false,
							                	blankText : '不能为空',
												maxLength : 32,
												regexText : '不能超过32位'
											}, {
												xtype : 'button',
												margin : '5 0 5 100',
												action : 'paramset',
												id:'b_16',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_19',
									items : [{
												xtype : "displayfield",
												fieldLabel : '主服务器地址,IP或域名'
											}, {
												xtype : "textfield",
												id:'p_19',
												allowBlank : false,
							                	blankText : '不能为空',
												maxLength : 32,
												regexText : '不能超过32位'
											}, {
												xtype : 'button',
												id:'b_19',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_21',
									items : [{
												xtype : "displayfield",
												fieldLabel : '备份服务器APN，无线通信拨号访问点'
											}, {
												xtype : "textfield",
												id:'p_21',
												allowBlank : false,
							                	blankText : '不能为空',
												maxLength : 32
											}, {
												xtype : 'button',
												id:'b_21',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_23',
									items : [{
												xtype : "displayfield",
												fieldLabel : '备份服务器地址,IP或域名'
											}, {
												xtype : "textfield",
												id:'p_23',
												allowBlank : false,
							                	blankText : '不能为空',
												maxLength : 32,
												regexText : '不能超过32位'
											}, {
												xtype : 'button',
												id:'b_23',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_24',
									items : [{
												xtype : "displayfield",
												fieldLabel : '服务器TCP端口'
											}, {
												xtype : "textfield",
												id:'p_24',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^[0-9]{0,5}?$/,
												regexText : '正数不能超过5位',
												maxLength : 5
											}, {
												xtype : 'button',
												id:'b_24',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_26',
									items : [{
												xtype : "displayfield",
												fieldLabel : '道路运输证IC卡认证主服务器IP地址或域名'
											}, {
												xtype : "textfield",
												id:'p_26',
												allowBlank : false,
							                	blankText : '不能为空',
												maxLength : 32,
												regexText : '不能超过32位'
											}, {
												xtype : 'button',
												id:'b_26',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_27',
									items : [{
												xtype : "displayfield",
												fieldLabel : '道路运输证IC卡认证主服务器TCP端口'
											}, {
												xtype : "textfield",
												id:'p_27',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^[0-9]{0,5}?$/,
												regexText : '正数不能超过5位',
												maxLength : 5
											}, {
												xtype : 'button',
												id:'b_27',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_28',
									items : [{
												xtype : "displayfield",
												fieldLabel : '道路运输证IC卡认证主服务器UDP端口'
											}, {
												xtype : "textfield",
												id:'p_28',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^[0-9]{0,5}?$/,
												regexText : '正数不能超过5位',
												maxLength : 5
											}, {
												xtype : 'button',
												id:'b_28',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_29',
									items : [{
												xtype : "displayfield",
												fieldLabel : '道路运输证IC 卡认证备份服务器IP地址或域名'
											}, {
												xtype : "textfield",
												id:'p_29',
												allowBlank : false,
							                	blankText : '不能为空',
												maxLength : 32,
												regexText : '不能超过32位'
											}, {
												xtype : 'button',
												id:'b_29',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_34',
									items : [{
												xtype : "displayfield",
												fieldLabel : '驾驶员未登录汇报时间间隔，单位为秒(s)'
											}, {
												xtype : "textfield",
												id:'p_34',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^\d+$/,
												regexText : '大于0的整数'
											}, {
												xtype : 'button',
												id:'b_34',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_39',
									items : [{
												xtype : "displayfield",
												fieldLabel : '休眠时汇报时间间隔，单位为秒(s)'
											}, {
												xtype : "textfield",
												id:'p_39',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^\d+$/,
												regexText : '大于0的整数'
											}, {
												xtype : 'button',
												id:'b_39',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_40',
									items : [{
												xtype : "displayfield",
												fieldLabel : '紧急报警时汇报时间间隔，单位为秒(s)'
											}, {
												xtype : "textfield",
												id:'p_40',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^\d+$/,
												regexText : '大于0的整数'
											}, {
												xtype : 'button',
												id:'b_40',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_41',
									items : [{
												xtype : "displayfield",
												fieldLabel : '缺省时间汇报间隔，单位为秒(s)'
											}, {
												xtype : "textfield",
												id:'p_41',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^\d+$/,
												regexText : '大于0的整数'
											}, {
												xtype : 'button',
												id:'b_41',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_44',
									items : [{
												xtype : "displayfield",
												fieldLabel : '缺省距离汇报间隔，单位为米(m)'
											}, {
												xtype : "textfield",
												id:'p_44',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^\d+$/,
												regexText : '大于0的整数'
											}, {
												xtype : 'button',
												id:'b_44',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_45',
									items : [{
												xtype : "displayfield",
												fieldLabel : '驾驶员未登录汇报距离间隔，单位为米(m)'
											}, {
												xtype : "textfield",
												id:'p_45',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^\d+$/,
												regexText : '大于0的整数'
											}, {
												xtype : 'button',
												id:'b_45',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_46',
									items : [{
												xtype : "displayfield",
												fieldLabel : '休眠时汇报距离间隔，单位为米(m)'
											}, {
												xtype : "textfield",
												id:'p_46',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^\d+$/,
												regexText : '大于0的整数'
											}, {
												xtype : 'button',
												id:'b_46',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_47',
									items : [{
												xtype : "displayfield",
												fieldLabel : '紧急报警时汇报距离间隔，单位为米(m)'
											}, {
												xtype : "textfield",
												id:'p_47',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^\d+$/,
												regexText : '大于0的整数'
											}, {
												xtype : 'button',
												id:'b_47',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_64',
									items : [{
												xtype : "displayfield",
												fieldLabel : '监控平台电话号码'
											}, {
												xtype : "textfield",
												id:'p_64',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
												regexText : '格式：3-4位区号，7-8位直播号码，1－4位分机号'
											}, {
												xtype : 'button',
												id:'b_64',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_65',
									items : [{
												xtype : "displayfield",
												fieldLabel : '复位电话号码'
											}, {
												xtype : "textfield",
												id:'p_65',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
												regexText : '格式：3-4位区号，7-8位直播号码，1－4位分机号'
											}, {
												xtype : 'button',
												id:'b_65',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_66',
									items : [{
												xtype : "displayfield",
												fieldLabel : '恢复出厂设置电话号码'
											}, {
												xtype : "textfield",
												id:'p_66',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
												regexText : '格式：3-4位区号，7-8位直播号码，1－4位分机号'
											}, {
												xtype : 'button',
												id:'b_66',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_67',
									items : [{
												xtype : "displayfield",
												fieldLabel : '监控平台SMS电话号码'
											}, {
												xtype : "textfield",
												id:'p_67',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
												regexText : '格式：3-4位区号，7-8位直播号码，1－4位分机号'
											}, {
												xtype : 'button',
												id:'b_67',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_68',
									items : [{
												xtype : "displayfield",
												fieldLabel : '接收终端SMS文本报警号码'
											}, {
												xtype : "textfield",
												id:'p_68',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
												regexText : '格式：3-4位区号，7-8位直播号码，1－4位分机号'
											}, {
												xtype : 'button',
												id:'b_68',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_69',
									items : [{
												xtype : "displayfield",
												fieldLabel : '终端电话接听策略'
											}, {
												xtype : 'combo',
												id:'p_69',
												editable:false,
												store : Ext.create('Ext.data.Store', {
												    fields: ['state', 'name'],
												    data : [
												        {"state":0, "name":"自动接听"},
												        {"state":1, "name":"ACC接通"}
												    ]
												}),
												displayField : 'name',
												valueField : 'state',
												value:0
											}, {
												xtype : 'button',
												id:'b_69',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_70',
									items : [{
												xtype : "displayfield",
												fieldLabel : '每次最长通话时间，单位为秒(s)'
											}, {
												xtype : "textfield",
												id:'p_70',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^\d+$/,
												regexText : '大于0的整数'
											}, {
												xtype : 'button',
												id:'b_70',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_71',
									items : [{
												xtype : "displayfield",
												fieldLabel : '当月最长通话时间，单位为秒(s)'
											}, {
												xtype : "textfield",
												id:'p_71',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /^\d+$/,
												regexText : '大于0的整数'
											}, {
												xtype : 'button',
												id:'b_71',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_72',
									items : [{
												xtype : "displayfield",
												fieldLabel : '监听电话号码'
											}, {
												xtype : "textfield",
												id:'p_72',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
												regexText : '格式：3-4位区号，7-8位直播号码，1－4位分机号'
											}, {
												xtype : 'button',
												id:'b_72',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_73',
									items : [{
												xtype : "displayfield",
												fieldLabel : '监管平台特权短信号码'
											}, {
												xtype : "textfield",
												id:'p_73',
												allowBlank : false,
							                	blankText : '不能为空',
												regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
												regexText : '格式：3-4位区号，7-8位直播号码，1－4位分机号'
											}, {
												xtype : 'button',
												id:'b_73',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_85',
									items : [{
												xtype : "displayfield",
												fieldLabel : '最高速度，单位为公里每小时(km/h)'
											}, {
												xtype : "textfield",
												id:'p_85',
												regex : /^[0-9]{0,3}?$/,
												regexText : '正数不能超过3位',
												maxLength : 3,
												allowBlank : true
											}, {
												xtype : 'button',
												id:'b_85',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_86',
									items : [{
												xtype : "displayfield",
												fieldLabel : '超速持续时间，单位为秒(s)'
											}, {
												xtype : "textfield",
												id:'p_86',
												regex : /^\d+$/,
												regexText : '大于0的整数',
												maxLength : 3,
												allowBlank : true
											}, {
												xtype : 'button',
												id:'b_86',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_87',
									items : [{
												xtype : "displayfield",
												fieldLabel : '连续驾驶时间门限，单位为秒(s)'
											}, {
												xtype : "textfield",
												id:'p_87',
												regex : /^\d+$/,
												regexText : '大于0的整数',
												maxLength : 3,
												allowBlank : true
											}, {
												xtype : 'button',
												id:'b_87',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_88',
									items : [{
												xtype : "displayfield",
												fieldLabel : '当天累计驾驶时间门限，单位为秒(s)'
											}, {
												xtype : "textfield",
												id:'p_88',
												regex : /^\d+$/,
												regexText : '大于0的整数',
												maxLength : 3,
												allowBlank : true
											}, {
												xtype : 'button',
												id:'b_88',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_89',
									items : [{
												xtype : "displayfield",
												fieldLabel : '最小休息时间，单位为秒(s)'
											}, {
												xtype : "textfield",
												id:'p_89',
												regex : /^\d+$/,
												regexText : '大于0的整数',
												maxLength : 3,
												allowBlank : true
											}, {
												xtype : 'button',
												id:'b_89',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_90',
									items : [{
												xtype : "displayfield",
												fieldLabel : '最长停车时间，单位为秒(s)'
											}, {
												xtype : "textfield",
												id:'p_90',
												regex : /^\d+$/,
												regexText : '大于0的整数',
												maxLength : 3,
												allowBlank : true
											}, {
												xtype : 'button',
												id:'b_90',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_91',
									items : [{
												xtype : "displayfield",
												fieldLabel : '超速报警预警差值，单位为 1/10Km/h '
											}, {
												xtype : "textfield",
												id:'p_91',
												regex : /^\d+$/,
												regexText : '大于0的整数',
												maxLength : 3,
												allowBlank : true
											}, {
												xtype : 'button',
												id:'b_91',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_92',
									items : [{
												xtype : "displayfield",
												fieldLabel : '疲劳驾驶预警差值，单位为秒(s)，>0'
											}, {
												xtype : "textfield",
												id:'p_92',
												regex : /^\d+$/,
												regexText : '大于0的整数',
												maxLength : 3,
												allowBlank : true
											}, {
												xtype : 'button',
												id:'b_92',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_93',
									items : [{
												xtype : "displayfield",
												fieldLabel : '碰撞报警参数设置b7-b0： 碰撞时间，单位 4ms； b15-b8：碰撞加速度，单位 0.1g，设置范围在：0-79 之间，默认为10'
											}, {
												xtype : "textfield",
												id:'p_93',
												regex : /^[0-79]{0,2}?$/,
												regexText : '0-79 之间',
												allowBlank : false
											}, {
												xtype : 'button',
												id:'b_93',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								},{
									xtype : 'panel',
									border : false,
									hidden:true,
									id : 'form_94',
									items : [{
												xtype : "displayfield",
												fieldLabel : '侧翻报警参数设置侧翻角度，单位 1 度，默认为 30 度'
											}, {
												xtype : "textfield",
												id:'p_94',
												regex : /^[0-9]{0,3}?$/,
												regexText : '正数不能超过3位',
												maxLength : 3,
												allowBlank : true
											}, {
												xtype : 'button',
												id:'b_94',
												margin : '5 0 5 100',
												action : 'paramset',
												text : '设置'
											}]
								}]
					}]
		});