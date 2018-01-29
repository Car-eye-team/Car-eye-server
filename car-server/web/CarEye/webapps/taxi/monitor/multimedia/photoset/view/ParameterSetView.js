Ext.define('PhotoSetApp.view.ParameterSetView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.parameterSetView',
	split : true,
	frame: false,
	width : 600,
	title:'参数设置',
	region : "east",
	layout : 'form',
	autoScroll:true,
	region : "east",
	title : '设置选项',
	items : [ {
		xtype : 'form',
		id : 'parameterSet_Form',
		anchor : '100%',
		border : false,
		collapsible : false,
		padding:'10 30 0 10',
		fieldDefaults : {
			labelAlign : 'right',
			labelWidth : 100
		},
		items : [ {
			layout : 'column',
			border : false,
			items : [ {
				columnWidth : 0.5,
				layout : 'form',
				border : false,
				dafaultType : 'numberfield',
				items : [ {
					xtype : 'fieldcontainer',
					anchor : '100%',
					layout : 'hbox',
					items : [ {
						xtype : 'combo',
						fieldLabel : '<font color="red">*</font>通道ID',
						id : 'rp_channel',
						allowBlank : false,
						editable:false,
						store : Ext.create('Ext.data.Store', {
							fields : [ 'state', 'name' ],
							data : [ {
								"state" : 1,
								"name" : "通道1"
							}, {
								"state" : 2,
								"name" : "通道2"
							} ]
						}),
						displayField : 'name',
						valueField : 'state'
					} ]
				}, {
					xtype : 'fieldcontainer',
					anchor : '100%',
					layout : 'hbox',
					items : [ {
						xtype : 'combo',
						fieldLabel : '<font color="red">*</font>拍摄命令',
						id : 'rp_cmd',
						width : 180,
						allowBlank : false,
						editable:false,
						store : Ext.create('Ext.data.Store', {
							fields : [ 'state', 'name' ],
							data : [ {
								"state" : 0,
								"name" : "停止拍摄"
							}, {
								"state" : 65535,
								"name" : "录像"
							}, {
								"state" : 3,
								"name" : "拍照"
							} ]
						}),
						displayField : 'name',
						valueField : 'state'
					}, {
						xtype : 'fieldcontainer',
						anchor : '100%',
						layout : 'hbox',
						items : [ {
							xtype : 'textfield',
							emptyText : '拍照张数',
							id : 'rp_picnum',
							width : 60,
							regex : /^[\d]*$/,
							regexText : '只允许数字类型',
							nanText : '您只能输入正整数',
							maxValue : 999
						} ]
					} ]
				}, {
					xtype : 'fieldcontainer',
					anchor : '100%',
					layout : 'hbox',
					items : [ {
						xtype : 'combo',
						width : 240,
						fieldLabel : '<font color="red">*</font>分辨率',
						editable:false,
						id : 'rp_screen',
						allowBlank : false,
						store : Ext.create('Ext.data.Store', {
							fields : [ 'state', 'name' ],
							data : [ {
								"state" : 1,
								"name" : "320*210"
							}, {
								"state" : 2,
								"name" : "640*480"
							}, {
								"state" : 3,
								"name" : "800*600"
							}, {
								"state" : 4,
								"name" : "1024*768"
							}, {
								"state" : 5,
								"name" : "176*144"
							}, {
								"state" : 6,
								"name" : "352*288"
							}, {
								"state" : 7,
								"name" : "704*288"
							}, {
								"state" : 8,
								"name" : "701*576"
							} ]
						}),
						displayField : 'name',
						valueField : 'state'
					} ]

				}, {
					xtype : 'fieldcontainer',
					anchor : '100%',
					layout : 'hbox',
					items : [ {
						xtype : 'numberfield',
						width : 240,
						fieldLabel : '<font color="red">*</font>图像/视频',
						id : 'rp_quality',
						allowBlank : false,
						allowDecimals : false,
						allowNegative : false,
						nanText : '您只能输入正整数',
						value : 1
					} ]
				}, {
					xtype : 'fieldcontainer',
					anchor : '100%',
					layout : 'hbox',
					items : [ {
						xtype : 'numberfield',
						fieldLabel : '<font color="red">*</font>亮度',
						width : 240,
						id : 'rp_bright',
						allowBlank : false,
						allowDecimals : false,
						allowNegative : false,
						nanText : '您只能输入正整数',
						value : 0,
						maxValue : 255
					} ]
				} ]
			}, {
				columnWidth : 0.5,
				layout : 'form',
				border : false,
				items : [ {
					xtype : 'numberfield',
					fieldLabel : '<font color="red">*</font>拍照间隔(>=0)',
					id : 'rp_inv',
					allowBlank : false,
					allowDecimals : false,
					allowNegative : false,
					nanText : '您只能输入非负整数',
					minValue : 0,
					value : 1
				}, {
					xtype : 'combo',
					fieldLabel : '<font color="red">*</font>保存标志',
					id : 'rp_save',
					allowBlank : false,
					editable:false,
					store : Ext.create('Ext.data.Store', {
						fields : [ 'state', 'name' ],
						data : [ {
							"state" : 1,
							"name" : "保存"
						}, {
							"state" : 0,
							"name" : "上传"
						} ]
					}),
					displayField : 'name',
					valueField : 'state'
				}, {
					xtype : 'numberfield',
					fieldLabel : '<font color="red">*</font>对比度',
					id : 'rp_contrast',
					allowBlank : false,
					allowDecimals : false,
					allowNegative : false,
					nanText : '您只能输入正整数',
					value : 1,
					maxValue : 127
				}, {
					xtype : 'numberfield',
					fieldLabel : '<font color="red">*</font>饱和度',
					id : 'rp_saturation',
					allowBlank : false,
					allowDecimals : false,
					allowNegative : false,
					nanText : '您只能输入正整数',
					value : 0,
					maxValue : 127
				}, {
					xtype : 'numberfield',
					fieldLabel : '<font color="red">*</font>色度',
					id : 'rp_chroma',
					allowBlank : false,
					allowDecimals : false,
					allowNegative : false,
					nanText : '您只能输入正整数',
					value : 0,
					maxValue : 255
				},{
					xtype : 'button',
					margin : '5 0 5 20',
					action : 'save',
					text : '确定'
				} ]
			} ]
		} ]
	} ]
});
