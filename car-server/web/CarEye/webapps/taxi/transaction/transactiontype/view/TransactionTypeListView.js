Ext.define('TransactionTypeApp.view.TransactionTypeListView' ,{
		    extend: 'Ext.grid.Panel',
		    id : 'transactiongrid',
		    alias : 'widget.transactionTypeListView',
		    title: '约车业务类型信息列表',
			region: "center",
   			border: true,
			frame: true,
			store: "TransactionTypeListStore", 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			store: "TransactionTypeListStore",
			multiSelect: true,
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'No',xtype: 'rownumberer',sortable: false},
			{ header: 'id', flex:1, dataIndex: 'id', hidden: true },
			{ header: '约车类型名称', flex:1, dataIndex: 'typename'},
			{ header: '备注',  flex:1, dataIndex: 'remark' },
			{ header: '创建人',  flex:1.5, dataIndex: 'username' },
			{ header: '创建时间', flex:1.5, dataIndex: 'createtime', sortable: true }
			],
			
			dockedItems: [
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                      {
								        text:'添加',
								        id: '160105',
								        tooltip:'添加约车业务类型信息',
								        iconCls:'add',
								        action : 'add'
								    },'-',{
								        text:'修改',
								        id: '160106',
								        tooltip:'修改约车业务类型信息',
								        iconCls:'edit',
								        action : 'edit'
								    },'-',{
								        text:'删除',
								        id: '160107',
								        tooltip:'删除约车业务类型信息',
								        iconCls:'delete',
								        action : 'delete'
								    }
			                  ]
			              }
			          ],

			
			bbar : {
				xtype : 'pagingtoolbar',
				store: "TransactionTypeListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});



