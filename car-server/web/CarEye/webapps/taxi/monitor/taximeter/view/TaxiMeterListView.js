Ext.define('TaxiMeterApp.view.TaxiMeterListView' ,{
		    extend: 'Ext.grid.Panel',
		    id : 'transactiongrid',
		    alias : 'widget.taxiMeterListView',
		    title: '计价器信息列表',
			region: "center",
   			border: true,
			frame: true,
			store: "taxiMeterListStore", 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			store: "TaxiMeterListStore",
			multiSelect: true,
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'No',xtype: 'rownumberer',sortable: false},
			{ header: 'id', flex:1, dataIndex: 'id', hidden: true },
			{ header: '车载SIM号', flex:1, dataIndex: 'typename'},
			{ header: '上传时间', flex:1, dataIndex: 'typename'},
			{ header: '司机标志号', flex:1, dataIndex: 'typename'},
			{ header: '上车天数', flex:1, dataIndex: 'typename'},
			{ header: '交易时间', flex:1, dataIndex: 'typename'},
			{ header: '上车时间', flex:1, dataIndex: 'typename'},
			{ header: '下车时间', flex:1, dataIndex: 'typename'},
			{ header: '户卡内部序列号', flex:1, dataIndex: 'typename'},
			{ header: '用户卡发行号', flex:1, dataIndex: 'typename'},
			{ header: '备注',  flex:1, dataIndex: 'remark' },
			{ header: '创建人',  flex:1.5, dataIndex: 'username' },
			{ header: '创建时间', flex:1.5, dataIndex: 'createtime', sortable: true }
			],
			
			dockedItems: [
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
									 {
						                text:'导出',
						                id:'14060201',
						                tooltip:'Excel导出',
						                iconCls:'common-excel-icon',
						                action : 'export'
						            }
			                  ]
			              }
			          ],

			
			bbar : {
				xtype : 'pagingtoolbar',
				store: "TaxiMeterListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});



