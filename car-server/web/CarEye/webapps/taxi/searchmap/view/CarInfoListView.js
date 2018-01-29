Ext.define('SearchMapApp.view.CarInfoListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    alias : 'widget.carInfoListView',
			region: "west",
			width:250,
			title:'车辆区域',
            collapsible: true,
            id :"mapcarinfogrid",
			store: "CarInfoListStore",
            collapseMode: "mini",
            split: true,
			frame: true,
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'carid', flex: 1 ,dataIndex: 'carid', hidden: true },
			{ header: '车牌号', flex: 1, dataIndex: 'carnumber', sortable: true },
			{ header: '车载号码', flex: 1, dataIndex: 'terminal', sortable: true }
			],
			
			dockedItems: [
					{
					    xtype: 'toolbar',
					    dock: 'top',
					    items: [
								{
						        xtype : 'combo',
								width : 170,
								fieldLabel : '车牌号码',
								labelWidth: 60,
								id:'cas_carnumber',
								labelAlign: 'right',
								store : 'CarListStore',
								displayField : 'carnumber',
								valueField : 'carnumber'
							}, {
										xtype: 'button',
										text : '查询',
										tooltip : '查询',
										iconCls : 'common-search-icon',
										action: 'searchcar'
									}
						    ]
						}
			          ],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CarInfoListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});

