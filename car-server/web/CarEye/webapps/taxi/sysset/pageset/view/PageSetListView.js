Ext.define('PageSetApp.view.PageSetListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.pageSetListView',
	title: '企业列表',
	autoHeight : true,
	region: "center",
    border: true,
	frame: true,
	store: "PageSetListStore",
	multiSelect: true,
	stripeRows:true, //表格是否隔行换色，默认为false
	loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
	selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
	columns: [
		{ header: '用户ID', flex:0.5, dataIndex: 'userid', hidden: true },
		{ header: '企业', flex:1.5, dataIndex: 'blocname', sortable: true},
		{ header: '企业组', flex:1, dataIndex: 'blocgroupname', sortable: true},
		{ header: '登录名', flex:1, dataIndex: 'loginname', sortable: true},
		{ header: '页面左侧状态',flex:1,dataIndex: 'leftpage',sortable:true,renderer:function(value){
			if(value == 1){
				return "打开";
			}else if(value == 2){
				return "<font color='red'>关闭</font>";
			}else {
				return "";
			}
		}},
		{ header: '页面底部状态',flex:1,dataIndex: 'bottompage',sortable:true,renderer:function(value){
			if(value == 1){
				return "打开";
			}else if(value == 2){
				return "<font color='red'>关闭</font>";
			}else {
				return "<font color='red'></font>";
			}
		}}
		],
        tbar:[{
            text:'设置界面展开关闭',
            tooltip:'设置界面展开关闭',
            iconCls:'edit',
            action : 'edit'
        }],
		bbar : {
			xtype : 'pagingtoolbar',
			store: "PageSetListStore",   
            displayInfo: true,   
            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
            emptyMsg: "没有数据" 
		}
		
});

