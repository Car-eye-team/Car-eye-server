Ext.define('CarUseApp.view.CarUseListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.carUseListView',
			title: '车辆用途名称列表',
			width:350,
			region: "center",
			border: true,
			frame: true,
			store: "CarUseListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'ID', flex:1, dataIndex: 'id', hidden: true },
			{ header: '用途名称', flex:1, dataIndex: 'usename', sortable: true },
			{ header: '备注', flex:1, dataIndex: 'remark', sortable: true },	
			{ header: '操作人', flex:1, dataIndex: 'username', sortable: true },	
			{ header: '创建时间', flex:2, dataIndex: 'createtime' }
			],
            tbar:[{
                text:'添加',
                id:'12010101',
                tooltip:'添加车辆用途',
                iconCls:'add',
                action : 'add'
            }, '-', {
                text:'修改',
                id:'12010102',
                tooltip:'修改车辆用途',
                iconCls:'edit',
                action : 'edit'
            },'-',{
                text:'删除',
                id:'12010103',
                tooltip:'删除车辆用途',
                iconCls:'remove',
                action : 'delete'
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CarUseListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

