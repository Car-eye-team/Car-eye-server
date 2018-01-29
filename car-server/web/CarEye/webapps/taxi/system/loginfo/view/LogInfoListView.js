Ext.define('LogInfoApp.view.LogInfoListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.logInfoListView',
			title: '日志记录列表',
			autoWidth: true,
			autoHeight : true,
			region: "center",
			border: true,
			frame: true,
			store: "LogInfoListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			new Ext.grid.RowNumberer({header:"",width:35,align:"center"}), 
			{ header: '日志id', flex:1, dataIndex: 'logid', hidden: true},
			{ header: '操作用户', flex:1, dataIndex: 'loginname', sortable: true},
			{ header: '操作类型', flex:0.7, dataIndex: 'operattype', sortable: true,renderer:function(value){
				if(value ==1){
					return '添加';
				}else if(value ==2){
					return '修改';
				}else if(value ==3){
					return '删除';
				}else if(value ==4){
					return '导出';
				}else if(value ==5){
					return '查询';
				}else{
				    return '';
				}
			}},
			{ header: '日志内容', flex:5, dataIndex: 'content', sortable: true},
			{ header: '操作时间', flex:1, dataIndex: 'createtime', sortable: true}
			],
			tbar:[
//			      {
//	            text:'Excel导出',
//	            tooltip:'Excel导出登陆日志',
//	            iconCls:'common-excel-icon',
//	            action : 'export'
//	        	},
	        	{
		            text:'删除',
		            tooltip:'删除登陆日志',
		            iconCls:'delete',
		            action : 'delete'
		        	}],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "LogInfoListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

