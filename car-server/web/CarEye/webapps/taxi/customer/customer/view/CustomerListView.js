Ext.define('CustomerApp.view.CustomerListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id : 'customerListViewGrid',
    alias : 'widget.customerListView',
			region: "center",
			title:'客户列表',
            collapsible: true,
			store: "CustomerListStore",
            collapseMode: "mini",
            split: true,
			frame: true,
			multiSelect: true,
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			 new Ext.grid.RowNumberer(), 
			{ header: 'id',  width:100, dataIndex: 'id', hidden: true },
			{ header: '客户名称',  width:150, dataIndex: 'cname'},
			{ header: '类型', width:100,dataIndex: 'typename', sortable: true },
			{ header: '性别', width:50, dataIndex: 'sex', sortable: true ,renderer:function(value){
					if(value == 1){
						return "男";
					}else if(value==2){
						return "女";
					}else{
					    return "";
					 }
			}},
			{ header: '联系电话', width:100,dataIndex: 'tel', sortable: true },
			{ header: '手机', width:100,dataIndex: 'phone', sortable: true },
			{ header: '住址', width:100,dataIndex: 'postaddr', sortable: true },
			{ header: '邮政编码', width:100,dataIndex: 'postalcode', sortable: true },
			{ header: '来源', width:100,dataIndex: 'source', sortable: true,hidden: true },
		    { header: '信息描述',  width:200, dataIndex: 'remark' },
			{ header: '创建人',  width:60, dataIndex: 'username', sortable: true },
			{ header: '创建时间',  width:150, dataIndex: 'createtime', sortable: true }
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
								        id: '150201',
								        tooltip:'添加客户信息',
								        iconCls:'add',
								        //action : 'add',
								        handler: function(button){
                                            var con = Ext.create("CustomerApp.controller.CustomerCtrl");
                                            con.addCustomer(button);
								        }
								    },{
								        text:'修改',
								        id: '150202',
								        tooltip:'修改客户信息',
								        iconCls:'edit',
								        //action : 'edit',
								         handler: function(button){
                                            var con = Ext.create("CustomerApp.controller.CustomerCtrl");
                                            con.editCustomer(button);
								        }
								    },{
								        text:'删除',
								        id: '150203',
								        tooltip:'删除客户信息',
								        iconCls:'delete',
								       // action : 'delete',
								        handler: function(button){
                                            var con = Ext.create("CustomerApp.controller.CustomerCtrl");
                                            con.deleteCustomer(button);
								        }
								    },{
						                text:'导出',
						                id:'150204',
						                tooltip:'Excel导出',
						                iconCls:'common-excel-icon',
						               // action : 'export', 
						                handler: function(button){
                                            var con = Ext.create("CustomerApp.controller.CustomerCtrl");
                                            con.exportInfoTwo(button);
								        }
						            }
                                   ]
            }],  
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CustomerListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});

