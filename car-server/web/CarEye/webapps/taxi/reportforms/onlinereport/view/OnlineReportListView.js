Ext.define('OnlineReportApp.view.OnlineReportListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.onlineReportListView',
			title: '新车上线监管',
			region: "center",
   			border: true,
			frame: true,
			store: "OnlineReportListStore", 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
//			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
//			{ header: 'ID', width:60, dataIndex: 'id', hidden: true },
//			{ header: '企业ID', width:100, dataIndex: 'blocid', hidden: true },
//			{ header: '车辆ID', width:100, dataIndex: 'carid', hidden: true },
//			{ header: '企业', width:150, dataIndex: 'blocname', sortable: true,locked:true },
			{ header: '新增车辆', flex:1, dataIndex: 'addnumber', sortable: true},
			{ header: '上线车辆', flex:1, dataIndex: 'onlinenumber', sortable: true},
			{ header: '未上线车辆', flex:1, dataIndex: 'unonlinenumber', sortable: true },
			{ header: '上线比例', flex:1, dataIndex: 'onlinepercent', sortable: true }
			],
			 dockedItems: [
				              {
				                  xtype: 'toolbar',
				                  layout: {
								        overflowHandler: 'Menu'
								  },   //溢出隐藏
				                  dock: 'top',
				                  items: [{
											text:'Word导出',
											id: '',
											iconCls:'common-word-icon',
							                 handler: function(button){
	                                            var con = Ext.create("OnlineReportApp.controller.OnlineReportCtrl");
	                                            con.exportCo(button);
	                                         }
									    }]
	            }]
//			bbar : {
//				xtype : 'pagingtoolbar',
//				store: "OnlineReportListStore",   
//	            displayInfo: true,   
//	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
//	            emptyMsg: "没有数据" 
//			}
		
});

