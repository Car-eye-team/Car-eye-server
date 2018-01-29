Ext.define('LoginLogApp.view.LoginLogListView' ,{
    	extend: 'Ext.grid.Panel',
   		alias : 'widget.loginLogListView',
	    title: '用户列表',
	    id:'loginPanel',
	    region: "center",
        frame: true,
        border: true,
        //height:Ext.getBody().getViewSize().height-110,
		//minHeight:Ext.getBody().getViewSize().height-110,
		store: "LoginLogListStore",
			viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
		multiSelect: true,
		stripeRows:true, //表格是否隔行换色，默认为false
		loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
		selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
		columns: [
		    new Ext.grid.RowNumberer({header:"",width:35,align:"center"}), 
		    { header: 'ID', flex: 0.5, dataIndex: 'id', hidden: true },
			{ header: '用户ID', flex: 0.5, dataIndex: 'userid', hidden: true },
			{ header: '用户登录名', flex: 1, dataIndex: 'loginname', sortable: true },
			{ header: '用户姓名', flex: 1, dataIndex: 'username', sortable: true },
			{ header: '企业', flex: 2, dataIndex: 'blocname', sortable: true },
			{ header: '登陆时间', flex: 2, dataIndex: 'logindate', sortable: true },
			{ header: '登陆IP', flex: 2, dataIndex: 'loginip', sortable: true },
			{ header: '登陆状态', flex: 1, dataIndex: 'loginflag', sortable: true },
			{ header: '状态', flex: 1, dataIndex: 'status', sortable: true,renderer:function(value){
				if(value == 2){
					return "<font color='red'>退出</font>"
				}else{
					return "<font color='green'>登陆</font>"
				}
			}},
			{ header: '备注', flex: 2, dataIndex: 'remark' ,sortable: true}
			],
	        tbar:[{
	            text:'Excel导出',
	            tooltip:'Excel导出登陆日志',
	            iconCls:'common-excel-icon',
	            action : 'export'
	        	},{
		            text:'删除',
		            tooltip:'删除登陆日志',
		            iconCls:'delete',
		            action : 'delete'
		        	}],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "LoginLogListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});

