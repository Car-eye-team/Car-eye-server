Ext.define('CustomerTypeApp.view.CustomerTypeListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id : 'customergrid',
    alias : 'widget.customerTypeListView',
    title: '客户类型列表',
			region: "center",
			frame: true,
			store: "CustomerTypeListStore",
			multiSelect: true,
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel'
//			,listeners :{
//				selectionchange : function(rowmodel,select){
//			    	var store = Ext.StoreManager.get('CustomerListStore');
//			    	//Ext.getCmp('id').setValue(rowmodel.getSelection()[0].get('id'));
//			    	var grid=Ext.getCmp('customergrid');
//			    	var m = grid.getSelectionModel().getSelection();
//					var jsonData = "";
//					for ( var i = 0, len = m.length; i < len; i++) {
//						var ss = m[i].get("id");
//						if (i == 0) {
//							jsonData = jsonData + ss;
//						} else {
//							jsonData = jsonData + "," + ss;
//						}
//					}
//					store.clearFilter(true);
//					store.on('beforeload', function (store, options) {
//				            var new_params = { 
//				            	ids:jsonData
//				            };
//				            Ext.apply(store.proxy.extraParams, new_params);
//				        });
//				        store.loadPage(1);
//				}
//	}
	}, //在首列實現checkbox，僅此在首列
			columns: [
			new Ext.grid.RowNumberer(), 
			{ header: 'id', flex:1, dataIndex: 'id', hidden: true },
			{ header: '客户类型', flex:1, dataIndex: 'typename'},
			{ header: '备注',  flex:1.2, dataIndex: 'remark' },
			{ header: '创建人',  flex:1, dataIndex: 'username', sortable: true },
			{ header: '创建时间', flex:1.8, dataIndex: 'createtime', sortable: true }
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
								        id: '150205',
								        tooltip:'添加客户类型信息',
								        iconCls:'add',
								        //action : 'add',
								        handler: function(button){
                                            var con = Ext.create("CustomerTypeApp.controller.CustomerTypeCtrl");
                                            con.addCustomerType(button);
								        }
								    },'-',{
								        text:'修改',
								        id: '150206',
								        tooltip:'修改客户类型信息',
								        iconCls:'edit',
								        //action : 'edit',
								         handler: function(button){
                                            var con = Ext.create("CustomerTypeApp.controller.CustomerTypeCtrl");
                                            con.editCustomerType(button);
								        }
								    },'-',{
								        text:'删除',
								        id: '150207',
								        tooltip:'删除客户类型信息',
								        iconCls:'delete',
								       // action : 'delete',
								        handler: function(button){
                                            var con = Ext.create("CustomerTypeApp.controller.CustomerTypeCtrl");
                                            con.deleteCustomerType(button);
								        }
								    },{
						                text:'导出',
						                id:'150208',
						                tooltip:'Excel导出',
						                iconCls:'common-excel-icon',
						                action : 'export',
						                handler: function(button){
                                            var con = Ext.create("CustomerTypeApp.controller.CustomerTypeCtrl");
                                            con.exportInfo(button);
								        }
						            }
                                   ]
            }],  
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CustomerTypeListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});



