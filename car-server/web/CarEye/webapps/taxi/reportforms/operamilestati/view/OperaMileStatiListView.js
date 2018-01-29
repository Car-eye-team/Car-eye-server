Ext.define('OperaMileStatiApp.view.OperaMileStatiListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.operaMileStatiListView',
			title: '营运里程统计报表',
			region: "center",
   			border: true,
			frame: true,
			store: "OperaMileStatiListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制  
//			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			new Ext.grid.RowNumberer(), 
			{ header: '日期', width: 140, dataIndex: 'createtime', sortable: true },
			{ header: '日均行驶总里程(km)', width: 120, dataIndex: 'drivermile', sortable: true },
			{ header: '日均载客里程(km)', width: 120, dataIndex: 'passengermile', sortable: true },
			{ header: '日均7:30-21:30行驶总里程(km)',width: 180, dataIndex: 'drivermile1', sortable: true },
			{ header: '日均7:30-21:30行驶总比例', width: 180, dataIndex: 'drivermilepercent', sortable: true},
			{ header: '日均7:30-21:30载客里程(km)', width: 180, dataIndex: 'passengermile1', sortable: true},
			{ header: '日均7:30-21:30载客里程比例', width: 180, dataIndex: 'passengermilepercent', sortable: true }
			],
			dockedItems: [
			              {
			                  xtype: 'toolbar',
			                  layout: {
							        overflowHandler: 'Menu'
							  },   //溢出隐藏
			                  dock: 'top',
			                  items: [{
										text:'Excel导出',
										id: '',
						                iconCls:'common-excel-icon',
						                action : 'export'
//						                 handler: function(button){
//                                            var con = Ext.create("CommondLogApp.controller.CommondLogCtrl");
//                                            con.exportCommondLog(button);
//                                         }
								    }]
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "OperaMileStatiListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

