Ext.define('RemoteControlRecordApp.view.RemoteControlRecordListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.remoteControlRecordListView',
			title: '远程控制记录列表',
			region: "center",
   			border: true,
			frame: true,
			store: "RemoteControlRecordListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
//			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			new Ext.grid.RowNumberer(), 
			{ header: 'ID', width:50, dataIndex: 'id', hidden: true },
			{ header: '车牌号', width:100, dataIndex: 'carnumber', sortable: true },
			{ header: '指令类型', width:180, dataIndex: 'type', sortable: true},
			{ header: '状态', width:100, dataIndex: 'status', sortable: true, renderer:function(value,cellmeta,record,rowIndex,columnIndex,store){
				if(value == 1){
					return "成功";
				}else if(value == 2){
					return "失败";
				}else{
					return "";
				}
			} },
			{ header: '协议指令', width:800, dataIndex: 'remark', sortable: true },
			{ header: '创建账号', width:100, dataIndex: 'loginname', sortable: true },
			{ header: '创建时间', width:150, dataIndex: 'createtime', sortable: true}
			],
			dockedItems: [
					{
					    xtype: 'toolbar',
					    dock: 'top',
					    items: [{
									text:'Excel导出',
					                iconCls:'common-excel-icon',
					                action:'export'
								}

						    ]
						}
			          ],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "RemoteControlRecordListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});
