Ext.define('ComplaintTypeApp.view.ComplaintTypeListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.complaintTypeListView',
			title: '投诉类型列表',
			autoWidth: true,
			autoHeight : true,
			region: "center",
			border: true,
			frame: true,
			store: "ComplaintTypeListStore",
			multiSelect: true,
			viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'ID', flex:0.5, dataIndex: 'id', hidden: true },
			{ header: '投诉类型', flex:1, dataIndex: 'type', sortable: true },
			{ header: '投诉类型名称', flex:1, dataIndex: 'typename', sortable: true },
			{ header: '创建时间', flex:1, dataIndex: 'createtime',sortable: true }
			],
            tbar:[{
                text:'添加',
                id: '121101',
                tooltip:'添加投诉类型',
                iconCls:'add',
                action : 'add'
            }, {
                text:'修改',
                id: '121102',
                tooltip:'修改投诉类型',
                iconCls:'edit',
                action : 'edit'
            },{
                text:'删除',
                id: '121103',
                tooltip:'删除投诉类型',
                iconCls:'remove',
                action : 'delete'
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "ComplaintTypeListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

