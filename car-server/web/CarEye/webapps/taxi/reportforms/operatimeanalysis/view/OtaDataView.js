Ext.define('OtaApp.view.OtaDataView', {
	extend : 'Ext.grid.Panel',
	border : true,
	alias : 'widget.otaDataView',
	store : "OtaListStore",
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
	           
	           {header : '企业名称',width :100,dataIndex : 'blocname',sortable : true},
	           {header : '车牌号',width : 100,dataIndex : 'carnumber',sortable : true},
	           {header : '行驶时长',width : 100,dataIndex : 'drivertime',sortable : true},
	           {header : '载客时长',width : 100,dataIndex : 'passengertime',sortable : true},
	           {header : '空车时长',width : 100,dataIndex : 'offlinetime',sortable : true},
	           {header : '创建时间',width : 150,dataIndex : 'createtime',sortable : true}
	           ]
	           
	           
	           
});
