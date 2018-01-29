Ext.define('OperaDayStatiApp.view.OperaDayStatiListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.operaDayStatiListView',
			title: '日均营运统计报表',
			region: "center",
   			border: true,
			frame: true,
			store: "OperaDayStatiListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制  
//			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			new Ext.grid.RowNumberer(), 
			{ header: '日期', width: 140, dataIndex: 'createtime', sortable: true },
			{ header: '日均营运收入（元）', width: 150, dataIndex: 'income', sortable: true },
			{ header: '日均行驶里程（公里）', width: 150, dataIndex: 'drivermile', sortable: true },
			{ header: '日均载客里程（公里）',width: 150, dataIndex: 'passengermile', sortable: true },
			{ header: '日均在线时长（小时）', width: 150, dataIndex: 'inlinetime', sortable: true},
			{ header: '日均载客时长（小时）', width: 150, dataIndex: 'passengertime', sortable: true},
			{ header: '日均等待时间（分钟）', width: 150, dataIndex: 'waittime', sortable: true },
			{ header: '日均计费时间（分钟）', width: 150, dataIndex: 'feetime', sortable: true },
			{ header: '日均载客次数', width: 150, dataIndex: 'passengercount', sortable: true }
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
								    }]
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "OperaDayStatiListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

