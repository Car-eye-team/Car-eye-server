Ext.define('JobTypeApp.view.JobTypeListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.jobTypeListView',
			title: '职业类型信息列表',
			region: "center",
   			border: true,
			frame: true,
			store: "JobTypeListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: '序号', width:50, xtype: 'rownumberer'},
			{ header: 'id',  flex:1, dataIndex: 'id', hidden: true },
			{ header: '职业类型名称',  flex:1, dataIndex: 'typename', sortable: true },
			{ header: '创建时间', flex:1, dataIndex: 'createtime', sortable: true }
			],
			 dockedItems: [
                          {
                              xtype: 'toolbar',
                              layout: {
                                    overflowHandler: 'Menu'
                              },   //溢出隐藏
                              dock: 'top',
                              items: [{
							                text:'添加',
							                id: '200601',
							                tooltip:'添加职业类型信息',
							                iconCls:'add',
							                action : 'add'
							            }, '-', {
							                text:'修改',
							                id: '200602',
							                tooltip:'修改职业类型信息',
							                iconCls:'edit',
							                action : 'edit'
							            },'-',{
							                text:'删除',
							                id: '200603',
							                tooltip:'删除职业类型信息',
							                iconCls:'remove',
							                action : 'delete'
							            }]
            }],  
			bbar : {
				xtype : 'pagingtoolbar',
				store: "JobTypeListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

