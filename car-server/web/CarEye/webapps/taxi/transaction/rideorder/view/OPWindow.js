Ext.define('RideOrderApp.view.OPWindow', {
	extend : 'Ext.grid.Panel',
	border : false,
	height : 180,
	id : 'op',
	alias : 'widget.oPWindow',
	store : "RidePassengerListStore",
	viewConfig : {
		enableTextSelection:true,//grid中的内容能够复制  
		stripeRows : true,
		loadMask : true
		}, 
	stripeRows : true, // 表格是否隔行换色，默认为false
	loadMask : true, // 是否在加载数据时显示遮罩效果，默认为false
//	selModel : {
//		selType : 'checkboxmodel'
//	}, // 在首列實現checkbox，僅此在首列
	columns : [
	           
	           {header : 'ID', width : 70, dataIndex : 'id', hidden : true},
	          
	           {header : '订单号',width : 120,dataIndex : 'orderid',hidden : true},
	           {header : '费用',width : 60,dataIndex : 'totalfee',hidden : true},
	           {header : '订单状态',width : 60,dataIndex : 'ordersatus',hidden : true,
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
	           {header : '时长',width : 100,dataIndex : 'sumtime',hidden : true},
	           {header : '里程',width : 100,dataIndex : 'summile',hidden : true},
	           {header : '创建时间',width : 150,dataIndex : 'createtime',hidden : true},
	           
	           {header : '车牌号',width : 70,dataIndex : 'carnumber',hidden : true},
	           {header : '车辆id',width : 70,dataIndex : 'carid',hidden : true},
	           {header : '备注',width : 70,dataIndex : 'remark',hidden : true}
	           
	           ]
	           
	           
	           
});
