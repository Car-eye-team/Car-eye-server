Ext.define('DialFeeSetApp.view.DialFeeSetListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.dialFeeSetListView',
    id:'dialfeesetgrid',
			title: '电召费用历史设置列表',
			autoWidth: true,
			autoHeight : true,
			region: "center",
			border: true,
			frame: true,
			store: "DialFeeSetListStore",
			multiSelect: true,
			viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			//selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			 new Ext.grid.RowNumberer(), 
			{ header: 'ID', flex:0.5, dataIndex: 'id', hidden: true },
			{ header: '电召费(元)', flex:1, dataIndex: 'dialfee', sortable: true },
			{ header: '燃油费(元)', flex:1, dataIndex: 'oilcost', sortable: true },
			{ header: '合乘折扣率(%)', flex:1, dataIndex: 'discount', sortable: true },
			{ header: '生效时间', flex:1, dataIndex: 'effecttime', sortable: true },
			{ header: '设置人', flex:1, dataIndex: 'username', sortable: true },
			{ header: '创建时间', flex:1, dataIndex: 'createtime',sortable: true }
			],
//            tbar:[{
//                text:'设置',
//                id: '180201',
//                tooltip:'电召设置信息',
//                iconCls:'set',
//                action : 'set'
//            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "DialFeeSetListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

