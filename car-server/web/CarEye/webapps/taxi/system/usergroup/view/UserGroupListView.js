Ext.define('userGroupApp.view.UserGroupListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.userGroupListView',
			title: '企业组列表',
			region: "center",
   			border: true,
			frame: true,
			store: "UserGroupListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			new Ext.grid.RowNumberer({header:"编号",width:35,align:"center"}), 
			{ header: '企业组ID', flex: 0.5, dataIndex: 'id', sortable: true, hidden: true },
			{ header: '所属企业', flex: 1, dataIndex: 'blocname', sortable: true },
			{ header: '企业组名称', flex: 1, dataIndex: 'blocgroupname', sortable: true },
			{ header: '企业组描述', flex: 2, dataIndex: 'blocgroupdesc', sortable: true },
			{ header: '创建者', flex: 1, dataIndex: 'username', sortable: true },
			{ header: '创建时间', flex: 1, dataIndex: 'createtime' }
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
							                id: '110201',
							                tooltip:'添加企业组',
							                iconCls:'add',
							                //action : 'add',
							                handler: function(button){
	                                            var con = Ext.create("userGroupApp.controller.UserGroupCtrl");
	                                            con.createUserGroup(button);
                                            }
								            }, '-', {
								                text:'修改',
								                id: '110202',
								                tooltip:'修改企业组',
								                iconCls:'edit',
								                //action : 'edit',
								                handler: function(button){
	                                            var con = Ext.create("userGroupApp.controller.UserGroupCtrl");
	                                            con.editUserGroup(button);
                                               }
								            },'-',{
								                text:'删除',
								                id: '110203',
								                tooltip:'删除企业组',
								                iconCls:'remove',
								                //action : 'delete',
								                handler: function(button){
	                                            var con = Ext.create("userGroupApp.controller.UserGroupCtrl");
	                                            con.deleteUserGroup(button);
                                               }
								            },'-',{
								                text:'查看企业信息',
								                id: '110204',
								                tooltip:'查看企业组下用户信息',
								                iconCls:'common-search-icon',
								                //action : 'query',
								                handler: function(button){
	                                            var con = Ext.create("userGroupApp.controller.UserGroupCtrl");
	                                            con.queryUserGroup(button);
                                               }
								            }]
            }],  
            tbar:[],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "UserGroupListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

