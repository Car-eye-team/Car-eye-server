Ext.define('CarLocationApp.view.CarLocationListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.carLocationListView',
			title: '车辆在线情况报表',
			region: "center",
   			border: true,
			frame: true,
			store: "CarLocationStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			//selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			new Ext.grid.RowNumberer(), 
			{ header: '企业', width:150, dataIndex: 'blocname', sortable: true ,locked:true},
			{ header: '车牌号', width:100, dataIndex: 'carnumber', sortable: true ,locked:true},
			{ header: '终端号码', width:100, dataIndex: 'terminal', sortable: true ,locked:true},
			{ header: '设备类型', width:60, dataIndex: 'typename', sortable: true },
			{ header: '车辆状态',  width:100, dataIndex: 'carstatus', sortable: true , renderer:function(value){
					if(value == 7){
						return "在线";
					}else if(value == 2){
						return "离线";
					}else if(value == 3){
						return "熄火";
					}else if(value == 5){
						return "行驶";
					}else if(value == 4){
						return "停车";
					}else if(value == 6){
						return "报警";
					}else if(value==8){
						return "未定位";
					}else{
						return "长时间离线";
					}
			}
			},
//			{ header: '运输状态',  width:100, dataIndex: 'travelposition', sortable: true , renderer:function(value){
//					if(value == 0){
//						return "休息";
//					}else if(value == 1){
//						return "待配货";
//					}else if(value == 2){
//						return "运输中";
//					}else{
//						return "未知";
//					}
//			}},
			{ header: '车牌颜色', width:100, dataIndex: 'color', sortable: true, renderer:function(value){
				if(value == 1){
					return "蓝色";
				}else if(value == 2){
					return "黄色";
				}else if(value == 3){
					return "黑色";
				}else if(value == 5){
					return "白色";
				}else if(value == 4){
					return "红色";
				}else if(value == 6){
					return "紫色";
				}else{
					return "其他";
				}
		}},
			{ header: '车辆类别', width:100, dataIndex: 'cartypename', sortable: true },
			{ header: '车辆用途', width:100, dataIndex: 'usename', sortable: true },
			{ header: '经度', width:100, dataIndex: 'lng', sortable: true },
			{ header: '纬度', width:100, dataIndex: 'lat', sortable: true },
			{ header: '所在省', width:100, dataIndex: 'province', sortable: true },
			{ header: '所在市', width:100, dataIndex: 'city', sortable: true },
			{ header: '所在县/区', width:100, dataIndex: 'district', sortable: true },
			{ header: '地址', width:200, dataIndex: 'address', sortable: true },
			{ header: '更新时间', width:150, dataIndex: 'createtime',sortable: true }
			],
			tbar:[{
				text:'Excel导出',
                iconCls:'common-excel-icon',
                action : 'export'            
            },{
				text:'Word导出',
                iconCls:'common-word-icon',
                action : 'exportWord'            
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CarLocationStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

