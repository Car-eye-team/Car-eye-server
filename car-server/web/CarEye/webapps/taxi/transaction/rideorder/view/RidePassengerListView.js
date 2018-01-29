Ext.define('RideOrderApp.view.RidePassengerListView', {
	extend : 'Ext.grid.Panel',
	border : false,
	id : 'ridepassenger',
	alias : 'widget.ridePassengerListView',
	region : 'east',
	width : "40%",
	title : '顺风车乘客列表--双击显示乘客信息及轨迹',
	collapsible : true,
	store : "RidePassengerListStore",
	split : true,
	frame : true,
	multiSelect : true,
	viewConfig : {
		enableTextSelection:true,//grid中的内容能够复制  
		stripeRows : true,
		loadMask : true
		}, 
	stripeRows : true, // 表格是否隔行换色，默认为false
	loadMask : true, // 是否在加载数据时显示遮罩效果，默认为false
	selModel : {
		selType : 'checkboxmodel'
	}, // 在首列實現checkbox，僅此在首列
	columns : [{header : 'ID', width : 70, dataIndex : 'id', hidden : true},
	          
	           {header : '订单号',width : 120,dataIndex : 'orderid',sortable : true},
	           {header : '费用',width : 60,dataIndex : 'totalfee',sortable : true},
	           {header : '订单状态',width : 60,dataIndex : 'ordersatus',sortable : true,
					renderer : function(value) {
						if (value == 1) {
							return "进行中"
						} else if (value == 2){
							return "完成"
						}else{
							return ""
						}
					}
	           },
	           {header : '拼车序号',width : 60,dataIndex : 'seq',sortable : true},
	           {header : '乘客姓名',width : 60,dataIndex : 'passagename',sortable : true},
	           {header : '手机号码',width : 100,dataIndex : 'phone',sortable : true},
	           {header : '起点',width : 150,dataIndex : 'saddress',sortable : true},
	           {header : '起点纬度',width : 70,dataIndex : 'slat',hidden : true},
	           {header : '起点经度',width : 70,dataIndex : 'slng',hidden : true},
	           {header : '终点',width : 150,dataIndex : 'eaddress',sortable : true},
	           {header : '终点纬度',width : 70,dataIndex : 'elat',hidden : true},
	           {header : '终点经度',width : 70,dataIndex : 'elng',hidden : true},
	           {header : '上车时间',width : 150,dataIndex : 'stime',sortable : true},
	           {header : '下车时间',width : 150,dataIndex : 'etime',sortable : true},
	           {header : '时长',width : 100,dataIndex : 'sumtime',sortable : true},
	           {header : '里程',width : 100,dataIndex : 'summile',sortable : true},
	           {header : '创建时间',width : 150,dataIndex : 'createtime',sortable : true},
	           
	           {header : '车牌号',width : 70,dataIndex : 'carnumber',hidden : true},
	           {header : '车辆id',width : 70,dataIndex : 'carid',hidden : true},
	           {header : '备注',width : 70,dataIndex : 'remark',hidden : true}
	           ],
	           
				listeners:{
					  itemdblclick:function(view, records, store, grid, data){
					  		var store = Ext.StoreManager.get('RidePassengerListStore');
							var grid = Ext.getCmp('ridepassenger');
							var records = grid.getSelectionModel().getSelection();
							var view = Ext.create('RideOrderApp.view.PassengerTripWindow');
							var store = grid.getStore();
							var data = store.getById(records[0].get('id'));
							Ext.getCmp('passengerTripWindow').loadRecord(data);
							
								var carnumber = Ext.getCmp('carnumber').getValue();
								var stime = Ext.getCmp('stime').getValue();
								var etime = Ext.getCmp('etime').getValue();
								var con = Ext.create("RideOrderApp.controller.RideOrderCtrl");
									con.mapLookTrip(carnumber,stime,etime);
								view.show();
							return false;
					       }
					  },
	           
	dockedItems : [{
				xtype : 'toolbar',
				dock : 'top',
				enableOverflow : true,//溢出隐藏
				items : [ 
				          
						{
							xtype : 'datetimefield',
							width : 180,
							labelWidth : 55,
							id : 's_time',
							fieldLabel : '开始时间',
							emptyText : '请选择',
							labelAlign : 'right'
						},  {
							xtype : 'datetimefield',
							width : 180,
							labelWidth : 55,
							id : 'e_time',
							fieldLabel : '结束时间',
							emptyText : '请选择',
							labelAlign : 'right'
						}]
			}, {
				xtype : 'toolbar',
				dock : 'top',
				items : [ {
							text : '删除',
							id : '160603',
							tooltip : '删除乘客信息',
							iconCls : 'delete',
							action : 'delete'
						}, '->', {
							xtype : 'button',
							text : '查询',
							id : 'p_search',
							tooltip : '查询',
							iconCls : 'common-search-icon',
							action : 'search'
						}, {
							text : '重置',
							tooltip : '清空查询条件',
							iconCls : 'common-reset-icon',
							action : 'resettext',
							handler : function(button) {
								Ext.getCmp('s_time').setValue("");
								Ext.getCmp('e_time').setValue("");
							}
						}]
			}

	],
	bbar : {
		xtype : 'pagingtoolbar',
		store : "RidePassengerListStore",
		displayInfo : true,
		displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
		emptyMsg : "没有数据"
	}
});
