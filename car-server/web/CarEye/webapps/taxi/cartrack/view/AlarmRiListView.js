Ext.define('CarTrackApp.view.AlarmRiListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.alarmRiListView',
			title: '超速报警提醒信息',
			header : false, // 显示 header 默认 true
			region: "center",
   			border: true,
   			height:180,
			frame: true,
			id : 'alarmRiListView',
			store: "AlarmRiStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			//selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			new Ext.grid.RowNumberer(), 
			{ header: 'ID', flex: 1, dataIndex: 'id', hidden: true },
			{ header: '企业名称Id', flex: 1, dataIndex: 'deptid', hidden: true },
			{ header: '企业名称', width: 150, dataIndex: 'deptname', hidden: true},
			{ header: '用户ID', flex: 1, dataIndex: 'userid', hidden: true },
			{ header: '终端号码', width: 100, dataIndex: 'terminal', hidden: true},
			{ header: '车牌号',width: 80, dataIndex: 'carnumber', sortable: true },
			{ header: '提醒类型', width: 100, dataIndex: 'an', hidden: true,renderer:function(value){
				return value+"提醒"; 
			}},
			{ header: '提醒内容', width: 250, dataIndex: 'content', sortable: true,renderer:function(value){
				return '<div style="white-space:normal">' + value + '</div>'; 
			}},
			{ header: '经度', width: 100, dataIndex: 'lng', sortable: true },
			{ header: '纬度', width: 100, dataIndex: 'lat', sortable: true },
			{ header: '地址', width: 300, dataIndex: 'address', sortable: true,renderer:function(value){
				return '<div style="white-space:normal">' + value + '</div>'; 
			}},
			{ header: '提醒时间', width: 150, dataIndex: 'createtime', sortable: true }
			],
			tbar:[{
	            text:'导出',
	            tooltip:'导出超速报警提醒信息',
	            iconCls:'common-excel-icon',
	            action : 'export'    
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "AlarmRiStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

