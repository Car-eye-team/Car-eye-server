Ext.define('AreaSetApp.view.CarInfoListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id:'carareasetgrid',
    alias : 'widget.carInfoListView',
			region: "east",
			width:250,
			title:'车辆区域',
            collapsible: true,
			store: "CarInfoListStore",
            collapseMode: "mini",
            split: true,
			frame: true,
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'carid', flex: 0.4 ,dataIndex: 'carid', hidden: true },
			{ header: '车牌号', flex: 0.8, dataIndex: 'carnumber', sortable: true },
			{ header: '车载号码', flex: 1, dataIndex: 'terminal', sortable: true },
			{  
				   text:'车辆区域',  
				   menuDisabled: true,  
				   sortable: false,  
				   align:'center',  
				   id: 'lookareacar',
				   xtype: 'actioncolumn',  
				   width: 55,  
				   items: [{  
					   iconCls : 'common-search-icon',
				       tooltip: '查看车辆区域',  
				       text: '查看车辆区域'
				   }]  
			}],
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
								itemId:'carnumber',
								labelAlign: 'right',
								store : 'CarPageListStore',//CarPageListStore    CarListStore
								displayField : 'carnumber',
								valueField : 'carnumber',
								pageSize : 10,
								minChars:1,
								matchFieldWidth:false,
								listConfig :{
									width:235
								}
							}, {
										xtype: 'button',
										text : '查询',
										tooltip : '查询',
										iconCls : 'common-search-icon',
										action: 'searchcar'
									}
						    ]
						}, {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                      {
								        text:'追加区域',
								        id: '180604',
								        tooltip:'追加区域',
								        iconCls:'add',
								        action : 'add'
//								    },'-',{
//								        text:'查看区域下发记录',
//								        id: '180605',
//								        tooltip:'查看区域下发记录',
//								        iconCls:'common-search-icon',
//								        action : 'query'
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

