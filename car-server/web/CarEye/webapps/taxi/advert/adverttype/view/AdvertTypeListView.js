Ext.define('AdvertTypeApp.view.AdvertTypeListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.advertTypeListView',
			title: '广告类型列表',
			region: "center",
			id : 'advertType',
   			border: true,
			frame: true,
			store: "AdvertTypeListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制  
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
//			new Ext.grid.RowNumberer(), 
			{ header: 'ID', width: 40, dataIndex: 'id', hidden: true },
			{ header: '类型名称', flex:1, dataIndex: 'typename', sortable: true },
			
			{ header: '创建时间',flex:1, dataIndex: 'createtime', sortable: true }
			],
			
			tbar:[{
                text : '添加',
				id : '19010101',
				tooltip : '添加广告类型',
				iconCls : 'add',
				action : 'add'
			}, '-', {
				text : '修改',
				id : '19010102',
				tooltip : '修改广告类型',
				iconCls : 'edit',
				action : 'edit'
			}, '-', {
				text : '删除',
				id : '19010103',
				tooltip : '删除广告类型',
				iconCls : 'delete',
				action : 'delete'
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "AdvertTypeListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

