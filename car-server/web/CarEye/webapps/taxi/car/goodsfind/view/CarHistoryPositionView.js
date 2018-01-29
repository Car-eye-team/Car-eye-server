Ext.define('GoodsFindApp.view.CarHistoryPositionView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id : 'carHistoryPositionView',
    alias : 'widget.carHistoryPositionView',
	autoSrcoll:'auto',
	height:300,
	title:'车辆位置信息列表',
            collapsible: true,
			store: "CarHistoryPositionStore",
            collapseMode: "mini",
            split: true,
			frame: true,
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
			columns: [
			{ header: 'No',xtype: 'rownumberer',width:40,sortable: false},		
			{ header: '车牌号', width:80, dataIndex: 'carnumber', sortable: true },
			{ header: '终端设备号', width:100, dataIndex: 'terminal', sortable: true },
			{ header: '地址',width:400,dataIndex: 'gaddress', sortable: true },
			{ header: '经度',width:80, dataIndex: 'glng', sortable: true,hidden:true },
			{ header: '纬度', width:80, dataIndex: 'glat', sortable: true,hidden:true },
			{ header: '定位时间', width:140, dataIndex: 'gpstime', sortable: true },
			{ header: '车辆状态', width:100,dataIndex: 'carstatus', sortable: true , renderer:function(value){
					if(value == 7){
						return "在线";
					}else if(value==1){
						return "长时间离线";
					}else if(value==2){
						return "离线";
					}else if(value==3){
						return "熄火";
					}else if(value==5){
						return "行驶";
					}else if(value==4){
						return "停车";
					}else if(value==6){
						return "报警";
					}else if(value==8){
						return "未定位";
					}else{
						return "";
					}
			}}
			]
			
});
