Ext.define('CarConditionApp.view.CarConditionListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.carConditionListView',
			title: '车辆在线时长列表',
			region: "center",
   			border: false,
			frame: true,
			store: "CarConditionStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制  
			//selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			new Ext.grid.RowNumberer(), 
			{ header: '日期', flex: 1, dataIndex: 'createtime', sortable: true },
			{ header: '企业名称Id', flex: 1, dataIndex: 'blocid', hidden: true },
			{ header: '企业名称', flex: 1, dataIndex: 'blocname', sortable: true},
			{ header: '终端号码', flex: 1, dataIndex: 'terminal', sortable: true},
			{ header: '车牌号',flex: 1, dataIndex: 'carnumber', sortable: true },
			{ header: '在线时长',flex: 1, dataIndex: 'onlinetimes', sortable: true }
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
								    },{
										text:'Word导出',
										id: '',
						                iconCls:'common-word-icon',
						                action : 'exportWord'
								    }]
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CarConditionStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

