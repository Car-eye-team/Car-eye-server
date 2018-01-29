Ext.define('WindowSetApp.view.WindowSetListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.windowSetListView',
	title: '报警提示列表',
	autoHeight : true,
	region: "center",
    border: true,
	frame: true,
	store: "WindowSetListStore",
	multiSelect: true,
	stripeRows:true, //表格是否隔行换色，默认为false
	loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
	selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
	columns: [
		{ header: '用户ID', flex:0.5, dataIndex: 'userid', hidden: true },
		{ header: '企业', flex:1.5, dataIndex: 'blocname', sortable: true},
		{ header: '企业组', flex:1, dataIndex: 'blocgroupname', sortable: true},
		{ header: '登录名', flex:1, dataIndex: 'loginname', sortable: true},
		{ header: '报警是否打开',flex:1,dataIndex: 'type',sortable:true,renderer:function(value){
			if(value >= 1){
				return "打开";
			}else{
				return "<font color='red'>关闭</font>";
			}
		}}
//		{ header: '设置时间', flex:1, dataIndex: 'createtime', sortable: true}
		],
        tbar:[{
            text:'设置报警提醒',
            tooltip:'设置报警提醒',
            iconCls:'edit',
            action : 'edit'
        }],
		bbar : {
			xtype : 'pagingtoolbar',
			store: "WindowSetListStore",   
            displayInfo: true,   
            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
            emptyMsg: "没有数据" 
		}
		
});

