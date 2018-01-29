Ext.define('CarTrackApp.view.CarStopListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.carStopListView',
	height:180,
	frame: true,
	title:"车辆历史信息",
	id : 'carStopListView',
	store: "CarStopListStore",
	header : false, // 显示 header 默认 true
	markDirty : false,
    multiSelect: true,
	stripeRows:true, //表格是否隔行换色，默认为false
	loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false  //,mode:'SINGLE'
	columns: [
	new Ext.grid.RowNumberer(), 
	{ header: 'ID', flex: 0.5, dataIndex: 'id', hidden: true },
	{ header: '车牌号', flex: 1, dataIndex: 'carnumber', sortable: true },
	{ header: '车辆状态', flex: 1, dataIndex: 'carstatus', sortable: true , renderer:function(value){
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
	}},
	{ header: '终端号', flex: 1, dataIndex: 'terminal', sortable: true },
	{ header: '经度', flex: 1, dataIndex: 'lng', sortable: true },
	{ header: '纬度', flex: 1, dataIndex: 'lat', sortable: true },
	{ header: '速度(km/h)', flex: 1, dataIndex: 'speed', hidden: true },
	{ header: '方向', flex: 0.8, dataIndex: 'direction', hidden: true,renderer:function(value){
		if(value == null || value == "null" || value == ""){
			return "";
		}
		if(value ==0){
			return "正北";
		}else if(value >0 && value <90){
			return "东北";
		}else if(value == 90){
			return "正东";
		}else if(value >90 && value <180){
			return "东南";
		}else if(value == 180){
			return "正南";
		}else if(value >180 && value <270){
			return "西南";
		}else if(value == 270){
			return "正西";
		}else if(value >270 && value <360){
			return "西北";
		}else if(value == 360){
			return "正北";
		}else{
			return value;
		}
	}},
	{ header: '当前里程', flex: 1, dataIndex: 'mileage', hidden: true},
	{ header: '总里程', flex: 0.9, dataIndex: 'summileage', sortable: true },
	{ header: '当前位置', flex: 2.8, dataIndex: 'address', sortable: true },
	{ header: '开始时间', flex: 2, dataIndex: 'createtime' },
	{ header: '结束时间', flex: 2, dataIndex: 'createtime'},
	{ header: '停留时间', flex: 1.5, dataIndex: 'stoptime'}
	],
	tbar:[{
        text:'导出',
        tooltip:'导出停车信息',
        iconCls:'common-excel-icon',
        action : 'export'    
    }]
});

