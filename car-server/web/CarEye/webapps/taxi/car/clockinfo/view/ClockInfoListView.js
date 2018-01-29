Ext.define('ClockInfoApp.view.ClockInfoListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.clockInfoListView',
			title: '考勤信息列表--双击查看上下班位置',
			region: "center",
   			border: true,
			frame: true,
			store: "ClockInfoListStore", 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'ID', width:60, dataIndex: 'id', hidden: true },
			{ header: '企业ID', width:100, dataIndex: 'blocid', hidden: true },
			{ header: '车辆ID', width:100, dataIndex: 'carid', hidden: true },
			{ header: '企业', width:150, dataIndex: 'blocname', sortable: true,locked:true },
			{ header: '车牌号', width:80, dataIndex: 'carnumber', sortable: true,locked:true },
			{ header: '设备号', width:100, dataIndex: 'terminal', sortable: true,locked:true},
			{ header: '单位代码', width:100, dataIndex: 'companycode', sortable: true },
			{ header: '司机代码', width:100, dataIndex: 'drivercode', sortable: true },
			{ header: '驾驶员唯一编号', width:130, dataIndex: 'driverid', sortable: true },
			{ header: '上班签到',
				columns: [
					{ header: '车辆自编号', width:100, dataIndex: 'vehicleid', sortable: true},
					{ header: '时间', width:130, dataIndex: 'signintime', sortable: true },
					{ header: '总营运次数', width:80, dataIndex: 'count', sortable: true},
					{ header: '操作结果', width:100, dataIndex: 'result', sortable: true , renderer:function(value){
							if(value == 144){
								return "执行成功";
							}else if(value==255){
								return "执行错误";
							}else{
								return "";
							}
					}},
					{ header: '上班百度经度', width:90, dataIndex: 'sbblng', sortable: true},
					{ header: '上班百度纬度', width:90, dataIndex: 'sbblat', sortable: true},
					{ header: '上班百度地址', width:200, dataIndex: 'sbbaddress', sortable: true}
			]},
			{ header: '下班签到',
				columns: [
					{ header: '脉冲数', width:80, dataIndex: 'mcs', sortable: true},
					{ header: '卡次', width:80, dataIndex: 'cardnum', sortable: true},
					{ header: '总营运次数', width:100, dataIndex: 'totalnumber', sortable: true },
					{ header: '车次', width:150, dataIndex: 'vehicletrips', sortable: true },
					{ header: '上班时间', width:130, dataIndex: 'stime', sortable: true },
					{ header: '下班时间', width:130, dataIndex: 'etime', sortable: true },
					{ header: '当班公里', width:100, dataIndex: 'dbmileage', sortable: true },
					{ header: '当班营运公里', width:100, dataIndex: 'dbyymileage', sortable: true },
					{ header: '计时时间', width:100, dataIndex: 'jstmie', sortable: true },
					{ header: '总计金额(元)', width:100, dataIndex: 'totalamount', sortable: true },
					{ header: '卡收金额(元)', width:100, dataIndex: 'cardamount', sortable: true },
					{ header: '班间公里', width:100, dataIndex: 'bjmileage', sortable: true },
					{ header: '总计公里', width:100, dataIndex: 'totalmileage', sortable: true },
					{ header: '总营运公里', width:100, dataIndex: 'totalyymileage', sortable: true },
					{ header: '单价(元)', width:100, dataIndex: 'price', sortable: true },
					{ header: '总等待时间', width:100, dataIndex: 'totalwaittime', sortable: true  },
					{ header: '下班百度经度', width:90, dataIndex: 'xbblng', sortable: true},
					{ header: '下班百度纬度', width:90, dataIndex: 'xbblat', sortable: true},
					{ header: '下班百度地址', width:200, dataIndex: 'xbbaddress', sortable: true}
			]}
			],
			listeners:{
				
			    //双击单元格事件 
			    celldblclick: function(table, td, cellIndex, model, tr, rowIndex, e, eOpts){
				    var data = model.data;
				    
				    var view = Ext.create('ClockInfoApp.view.ClockMapView');
						view.show();
					
					var con = Ext.create("ClockInfoApp.controller.ClockInfoCtrl");
						con.clockMap(data);
				
	   			}
			},
			dockedItems: [
			              {
			                  xtype: 'toolbar',
			                  layout: {
							        overflowHandler: 'Menu'
							  },   //溢出隐藏
			                  dock: 'top',
			                  items: [{
										text:'Excel导出',
										id: '18070401',
						                iconCls:'common-excel-icon',
						                //action : 'export',
						                 handler: function(button){
                                            var con = Ext.create("ClockInfoApp.controller.ClockInfoCtrl");
                                            con.exportClockInfo(button);
                                         }
								    }]
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "ClockInfoListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

