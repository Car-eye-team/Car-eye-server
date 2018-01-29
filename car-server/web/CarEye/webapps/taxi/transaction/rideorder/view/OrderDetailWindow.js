Ext.define('RideOrderApp.view.OrderDetailWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.orderDetailWindow',
	title : '顺风车订单详情信息',
	width : 800,
	height : 380,
	animCollapse : false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	closeAction : 'destroy',
	border : false,
	items : [ {
		xtype : 'form',
		id : 'orderDetailWindow',
		frame : true,
		anchor : '100%',
		collapsible : false,
		"items" : [ {
			"xtype" : "fieldset",
			"title" : "<b><font style='color:green'>订单信息</font></b>",
			fieldDefaults : {
				buttonAlign : 'left',
				labelAlign : 'right',
				labelWidth : 60
			},
			items : [ {
				layout : 'column',
				columnWidth : 1,
				border : false,
				items : [ {
					columnWidth : .25,
					border : false,
					items : [ {
						xtype : 'displayfield',
						fieldLabel : '订单号',
						id : 'orderid'
					}, {
						xtype : 'displayfield',
						fieldLabel : '起点',
						id : 'saddress'
					}, {
						xtype : 'displayfield',
						fieldLabel : '总时长',
						id : 'sumtime'
					}, {
						xtype : 'displayfield',
						fieldLabel : '创建时间',
						id : 'createtime'
					} ]
				}, {
					columnWidth : .25,
					border : false,
					items : [ {
						xtype : 'displayfield',
						fieldLabel : '企业名称',
						id : 'bloc_name'
					}, {
						xtype : 'displayfield',
						fieldLabel : '终点',
						id : 'eaddress'
					}, {
						xtype : 'displayfield',
						fieldLabel : '总里程',
						id : 'summile'
					} ]
				}, {
					columnWidth : .25,
					border : false,
					items : [ {
						xtype : 'displayfield',
						fieldLabel : '车牌号',
						id : 'carnumber'
					}, {
						xtype : 'displayfield',
						fieldLabel : '开始时间',
						id : 'stime'
					}, {
						xtype : 'displayfield',
						fieldLabel : '总费用',
						id : 'totalfee'
					} ]
				}, {
					columnWidth : .25,
					border : false,
					items : [ {
						xtype : 'displayfield',
						fieldLabel : '设备号',
						id : 'terminal'
					}, {
						xtype : 'displayfield',
						fieldLabel : '结束时间',
						id : 'etime'
					}, {
						xtype : 'displayfield',
						fieldLabel : '订单状态',
						id : 'ordersatus',
						renderer : function(value) {
							if (value == 1) {
								return "进行中"
							} else if (value == 2) {
								return "完成"
							} else {
								return ""
							}
						}
					} ]
				} ]
			} ]
		}, {
			"xtype" : "fieldset",
			"title" : "<b><font style='color:green'>乘客 </font></b>",
			fieldDefaults : {
				buttonAlign : 'left',
				labelAlign : 'right',
				labelWidth : 80
			},
			items : [ {
				layout : 'column',
				columnWidth : 1,
				border : false,
				items : [ {
					columnWidth : 1,
					border : false,
					items : [ {
						xtype:'oPWindow'
					} ]
				} ]
			} ]
		} ]
	} ]

});
