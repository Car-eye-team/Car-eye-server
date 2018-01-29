Ext.define('userApp.view.UserListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.userListView',
	title: '企业用户列表',
	autoHeight : true,
	region: "center",
    border: true,
	frame: true,
	store: "UserListStore",
	multiSelect: true,
	stripeRows:true, //表格是否隔行换色，默认为false
	loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
	selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
	columns: [
		new Ext.grid.RowNumberer({header:"编号",width:35,align:"center"}), 
		{ header: '用户ID', width:50, dataIndex: 'userid', hidden: true },
		{ header: '企业', width:180, dataIndex: 'blocname', sortable: true,locked:true },
		{ header: '企业组', width:150, dataIndex: 'blocgroupname', sortable: true,locked:true },
		{ header: '登录名', width:100, dataIndex: 'loginname', sortable: true,locked:true },
		{ header: '密码',width:100,dataIndex: 'password',sortable:true},
		{ header: '姓名', width:100, dataIndex: 'username', sortable: true },
		{ header: '分机号', width:100, dataIndex: 'runnumber', sortable: true },
		{ header: '手机号', width:100, dataIndex: 'mobile', sortable: true },
		{ header: '固定电话', width:100, dataIndex: 'telphone', sortable: true },
		{ header: 'EMAIL', width:200, dataIndex: 'email', sortable: true },
		{ header: '用户状态', width:100, dataIndex: 'state', sortable: true,renderer:function(value){
			if(value == 2){
				return "<font color='red'>停用</font>"
			}else{
				return "<font color='green'>激活</font>"
			}
		}},
		{ header: '创建时间', width:130, dataIndex: 'createtime' ,sortable: true},
		{ header: '企业id',width:100,dataIndex: 'blocid',hidden:true},
		{ header: '企业组id',width:100,dataIndex: 'blocgroupid',hidden:true},
		{ header: '性别',width:100,dataIndex: 'usersex',sortable:true,renderer:function(value){
			if(value == 1){
				return "男"
			}else{
				return "女"
			}
		}},
		{ header: '证件号',width:120,dataIndex:'cardnumber',sortable:true}
		],
		 dockedItems: [
                          {
                              xtype: 'toolbar',
                              layout: {
                                    overflowHandler: 'Menu'
                              },   //溢出隐藏
                              dock: 'top',
                              items: [
                                        {
								            text:'添加',
								            id: '110301',
								            tooltip:'添加用户',
								            iconCls:'add',
								            //action : 'add',
								            handler: function(button){
                                            var con = Ext.create("userApp.controller.UserCtrl");
                                            con.createUser(button);
                                            }
								        }, '-', {
								            text:'修改',
								            id: '110302',
								            tooltip:'修改用户',
								            iconCls:'edit',
								            //action : 'edit',
								            handler: function(button){
	                                            var con = Ext.create("userApp.controller.UserCtrl");
	                                            con.editUser(button);
                                            }
								        },'-',{
								            text:'删除',
								            id: '110303',
								            tooltip:'删除用户',
								            iconCls:'remove',
								            //action : 'delete',
								             handler: function(button){
	                                            var con = Ext.create("userApp.controller.UserCtrl");
	                                            con.deleteUser(button);
                                            }
								        },'-',{
								            text:'激活',
								            id: '110304',
								            tooltip:'激活用户',
								            iconCls:'active',
								            //action : 'active',
								             handler: function(button){
	                                            var con = Ext.create("userApp.controller.UserCtrl");
	                                            con.activeUser(button);
                                            }
								        },'-',{
								            text:'停用',
								            id: '110305',
								            tooltip:'停用用户',
								            iconCls:'inactive',
								            //action : 'inactive',
								             handler: function(button){
	                                            var con = Ext.create("userApp.controller.UserCtrl");
	                                            con.inactiveUser(button);
                                            }
								        }]
            }],  
		bbar : {
			xtype : 'pagingtoolbar',
			store: "UserListStore",   
            displayInfo: true,   
            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
            emptyMsg: "没有数据" 
		}
		
});

