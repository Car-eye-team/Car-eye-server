Ext.define('OnlineRateApp.view.CarInfoListView' ,{
    extend: 'Ext.grid.Panel',
    //车辆信息
    alias : 'widget.carInfoListView',
			region: "center",
   			border: false,
			frame: true,
			height : 465,
			store: "CarInfoListStore", 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
//			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'ID', flex:1, dataIndex: 'id', hidden: true },
			{ header: '企业ID', flex:1, dataIndex: 'blocid', hidden: true },
			{ header: '车辆ID', flex:1, dataIndex: 'carid', hidden: true },
			{ header: '企业', flex:1.5, dataIndex: 'blocname', sortable: true},
			{ header: '车牌号', flex:1, dataIndex: 'carnumber', sortable: true},
			{ header: '上线次数', flex:1, dataIndex: 'inlinecount', sortable: true},
			{ header: '下线次数', flex:1, dataIndex: 'offlinecount', sortable: true },
			{ header: '在线时长', flex:1, dataIndex: 'onlinetimes', sortable: true },
			{ header: '在线时间比', flex:1, dataIndex: 'onlinepercent', sortable: true }
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
								    },{
										text:'Word导出',
										id: '',
						                iconCls:'common-word-icon',
						                action : 'exportWord'
								    }]
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CarInfoListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

