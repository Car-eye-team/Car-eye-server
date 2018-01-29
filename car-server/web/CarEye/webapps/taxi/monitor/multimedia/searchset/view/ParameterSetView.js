Ext.define('SearchSetApp.view.ParameterSetView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.parameterSetView',
	split : true,
	frame: false,
	width : 500,
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
		padding:'0 20',
		fieldDefaults : {
			labelAlign : 'right',
			labelWidth : 80
		},
		items : [ {
			xtype : 'form',
			id : 'form_11',
			border : false,
			items : [ {
				layout : 'column',
				border : false,
				items : [ {
					columnWidth : 0.5,
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'combo',
						fieldLabel : '<font color="red">*</font>选择通道号',
						id : 'rp1_channel',
						allowBlank : false,
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
					}, {
						xtype : 'combo',
						fieldLabel : '<font color="red">*</font>多媒体类型',
						id : 'rp1_type',
						allowBlank : false,
						editable:false,
						store : Ext.create('Ext.data.Store', {
							fields : [ 'state', 'name' ],
							data : [ {
								"state" : 0,
								"name" : "图像"
							}, {
								"state" : 1,
								"name" : "音频"
							}, {
								"state" : 2,
								"name" : "视频"
							} ]
						}),
						displayField : 'name',
						valueField : 'state'
					}, {
						xtype : 'combo',
						fieldLabel : '<font color="red">*</font>拍照原因',
						editable:false,
						id : 'rp1_fmt',
						allowBlank : false,
						store : Ext.create('Ext.data.Store', {
							fields : [ 'state', 'name' ],
							data : [ {
								"state" : 0,
								"name" : "平台下发指令"
							}, {
								"state" : 1,
								"name" : "定时动作"
							}, {
								"state" : 2,
								"name" : "抢劫报警触发"
							}, {
								"state" : 3,
								"name" : "碰撞侧翻报警触发"
							}, {
								"state" : 4,
								"name" : "进入重车拍照（抬表）"
							}, {
								"state" : 5,
								"name" : "进入空车拍照（压表）"
							}, {
								"state" : 6,
								"name" : "服务评价拍照"
							} ]
						}),
						displayField : 'name',
						valueField : 'state'
					},{
						xtype : 'button',
						margin : '5 0 5 150',
						action : 'save',
						id:'b_11',
						text : '确定'
					}]
				}, {
					columnWidth : 0.5,
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'datetimefield',
						fieldLabel : '起始时间',
						id : 'rp1_starttime',
						format:'Y-m-d'
					}, {
						xtype : 'datetimefield',
						fieldLabel : '结束时间',
						id : 'rp1_endtime',
						format:'Y-m-d'
					} ]
				} ]
			} ]
		} ,{
			
			xtype : 'form',
			id : 'form_12',
			hidden:true,
			border : false,
			items : [ {
				layout : 'column',
				border : false,
				items : [ {
					columnWidth : 0.5,
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'combo',
						fieldLabel : '<font color="red">*</font>选择通道号',
						id : 'rp2_channel',
						allowBlank : false,
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
					}, {
						xtype : 'combo',
						fieldLabel : '<font color="red">*</font>多媒体类型',
						id : 'rp2_type',
						allowBlank : false,
						editable:false,
						store : Ext.create('Ext.data.Store', {
							fields : [ 'state', 'name' ],
							data : [ {
								"state" : 0,
								"name" : "图像"
							}, {
								"state" : 1,
								"name" : "音频"
							}, {
								"state" : 2,
								"name" : "视频"
							} ]
						}),
						displayField : 'name',
						valueField : 'state'
					}, {
						xtype : 'combo',
						fieldLabel : '<font color="red">*</font>事件项编码',
						editable:false,
						id : 'rp2_fmt',
						allowBlank : false,
						store : Ext.create('Ext.data.Store', {
							fields : [ 'state', 'name' ],
							data : [ {
								"state" : 0,
								"name" : "平台下发指令"
							}, {
								"state" : 1,
								"name" : "定时动作"
							}, {
								"state" : 2,
								"name" : "抢劫报警触发"
							}, {
								"state" : 3,
								"name" : "碰撞侧翻报警触发"
							} ]
						}),
						displayField : 'name',
						valueField : 'state'
					},{
						xtype : 'button',
						margin : '5 0 5 150',
						action : 'save',
						id:'b_12',
						text : '确定'
					}]
				}, {
					columnWidth : 0.5,
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'datetimefield',
						fieldLabel : '起始时间',
						id : 'rp2_starttime',
						format:'Y-m-d'
					}, {
						xtype : 'datetimefield',
						fieldLabel : '结束时间',
						id : 'rp2_endtime',
						format:'Y-m-d'
					}, {
						xtype : 'combo',
						fieldLabel : '<font color="red">*</font>删除标志',
						id : 'rp2_flg',
						allowBlank : false,
						editable:false,
						store : Ext.create('Ext.data.Store', {
							fields : [ 'state', 'name' ],
							data : [ {
								"state" : 0,
								"name" : "保留"
							}, {
								"state" : 1,
								"name" : "删除"
							} ]
						}),
						displayField : 'name',
						valueField : 'state'
					} ]
				} ]
			} ]
		}]
	} ]
});
