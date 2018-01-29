Ext.define('OnCallCountApp.view.TransactionAnswerListView', {
	extend : 'Ext.window.Window',
	alias : 'widget.transactionAnswerListView',
	title : '抢答业务记录列表',
	width : 800,
	height : 400,
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
		xtype : 'grid',
		autoWidth : true,
		autoHeight : true,
		minHeight : 490,
		frame : true,
		store : "TransactionAnswerListStore",
		multiSelect : true,
		stripeRows : true, // 表格是否隔行换色，默认为false
		loadMask : true, 
		selModel : {
			selType : 'checkboxmodel'
		}, 
		columns : [{
					header : 'id',
					width : 30,
					dataIndex : 'id',
					hidden : true
				}, {
					header : '订单号',
					width : 150,
					dataIndex : 'orderid',
					sortable : true,
					locked : true
				}, {
					header : '企业',
					width : 100,
					dataIndex : 'blocname',
					sortable : true,
					locked : true
				}, {
					header : '车牌号',
					width : 80,
					dataIndex : 'carnumber',
					sortable : true,
					locked : true
				}, {
					header : '终端设备号',
					width : 100,
					dataIndex : 'terminal',
					sortable : true,
					locked : true
				}, {
					header : '发送时间',
					width : 150,
					dataIndex : 'sendtime',
					sortable : true,
					locked : true
				}, {
					header : '是否中标',
					width : 80,
					dataIndex : 'isbidwin',
					sortable : true,
					locked : true,
					renderer : function(v) {
						if (v == null) {
							return '';
						} else if (v == 1) {
							return '未中标';
						} else if (v == 2) {
							return '中标';
						}
					}
				}, {
					header : '车辆当前状态',
					width : 100,
					dataIndex : 'carstatus',
					sortable : true,
					renderer : function(value) {
						if (value == 7) {
							return "在线";
						} else if (value == 1) {
							return "长时间离线";
						} else if (value == 2) {
							return "离线";
						} else if (value == 3) {
							return "熄火";
						} else if (value == 5) {
							return "行驶";
						} else if (value == 4) {
							return "停车";
						} else if (value == 6) {
							return "报警";
						} else if (value == 8) {
							return "未定位";
						} else {
							return "";
						}
					}
				}, {
					header : '设备类型',
					width : 60,
					dataIndex : 'typename',
					sortable : true
				}, {
					header : '车辆类型',
					width : 60,
					dataIndex : 'cartypename',
					sortable : true
				}, {
					header : '终端手机号',
					width : 100,
					dataIndex : 'phone',
					sortable : true
				}, {
					header : '车牌颜色',
					width : 100,
					dataIndex : 'color',
					sortable : true
				}, {
					header : '省',
					width : 80,
					dataIndex : 'provincename',
					sortable : true
				}, {
					header : '市',
					width : 80,
					dataIndex : 'cityname',
					sortable : true
				}, {
					header : '县/区',
					width : 80,
					dataIndex : 'districtname',
					sortable : true
				}, {
					header : '查询密码',
					width : 150,
					dataIndex : 'password',
					sortable : true
				}, {
					header : '车辆用途',
					width : 100,
					dataIndex : 'caruse',
					sortable : true
				}, {
					header : '主驾驶员',
					width : 100,
					dataIndex : 'drivername1',
					sortable : true
				}, {
					header : '副驾驶员',
					width : 100,
					dataIndex : 'drivername2',
					sortable : true
				}, {
					header : '燃油种类',
					width : 100,
					dataIndex : 'oiltype',
					sortable : true
				}, {
					header : '车架号',
					width : 100,
					dataIndex : 'framenumber',
					sortable : true
				}, {
					header : '行驶证号',
					width : 100,
					dataIndex : 'drivlicnum',
					sortable : true
				}, {
					header : '变速箱号',
					width : 100,
					dataIndex : 'transnumber',
					sortable : true
				}, {
					header : '排气量',
					width : 100,
					dataIndex : 'ventingmeasure',
					sortable : true
				}, {
					header : '发动机号',
					width : 100,
					dataIndex : 'enginenumber',
					sortable : true
				}, {
					header : '购车日期',
					width : 100,
					dataIndex : 'buytime',
					sortable : true
				}
		],
		dockedItems : [{
			xtype : 'toolbar',
			layout : {
				overflowHandler : 'Menu'
			}, // 溢出隐藏
			dock : 'top',
			items : [{
				text : '导出',
				tooltip : 'Excel导出',
				iconCls : 'common-excel-icon',
				handler : function(button) {
					var con = Ext
							.create("OnCallCountApp.controller.OnCallCountCtrl");
					con.exportTransactionAnswerList(button);
				}
			}]
		}],
		bbar : {
			xtype : 'pagingtoolbar',
			store : "TransactionAnswerListStore",
			displayInfo : true,
			displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
			emptyMsg : "没有数据"
		}
	}]
});
