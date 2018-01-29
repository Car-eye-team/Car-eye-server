Ext.define('userGroupApp.view.UGUserListView' ,{
	extend : 'Ext.window.Window',
	alias : 'widget.uGUserListView',
	title : '用户信息列表',
    width : 700,
    height : 350,
	layout : 'form',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',
	items : [ {
		    xtype: 'grid',
		    autoWidth: true,
			autoHeight : true,
			minHeight:320,
			frame: true,
			store: "UGUserListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			columns: [
			{ header: '用户ID', flex: 1.5, dataIndex: 'id', sortable: true },
			{ header: '登录名', flex: 2, dataIndex: 'loginname', sortable: true },
			{ header: '姓名', flex: 2, dataIndex: 'username', sortable: true },
			{ header: '手机号', flex: 2, dataIndex: 'mobile', sortable: true },
			{ header: '固定电话', flex: 2.5, dataIndex: 'telephone', hidden:true},
			{ header: 'EMAIL', flex: 3, dataIndex: 'email', hidden:true },
			{ header: '用户状态', flex: 1.5, dataIndex: 'state', sortable: true,renderer:function(value){
					if(value == "禁用"){
						return "<font color='red'>停用</font>"
					}else{
						return "<font color='green'>激活</font>"
					}
			}},
			{ header: '创建时间', flex: 3, dataIndex: 'createtime' ,sortable: true}
			],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "UGUserListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
	} ]
});

