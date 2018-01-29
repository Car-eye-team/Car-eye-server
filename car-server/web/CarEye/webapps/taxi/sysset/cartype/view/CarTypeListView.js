Ext.define('CarTypeApp.view.CarTypeListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.carTypeListView',
			title: '车辆类型名称列表',
			width:350,
			region: "center",
			border: true,
			frame: true,
			store: "CarTypeListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'ID', flex:1, dataIndex: 'id', hidden: true },
			{ header: '类型名称', flex:1, dataIndex: 'typename', sortable: true },
			{ header: '备注', flex:1, dataIndex: 'remark', sortable: true },	
			{ header: '操作人', flex:1, dataIndex: 'username', sortable: true },	
			{ header: '创建时间', flex:2, dataIndex: 'createtime' }
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
						                id:'12010101',
						                tooltip:'添加车辆类型',
						                iconCls:'add',
						                //action : 'add',
						                handler: function(button){
                                            var con = Ext.create("CarTypeApp.controller.CarTypeCtrl");
                                            con.addCarType(button);
                                        }
						            }, '-', {
						                text:'修改',
						                id:'12010102',
						                tooltip:'修改车辆类型',
						                iconCls:'edit',
						                //action : 'edit',
						                handler: function(button){
                                            var con = Ext.create("CarTypeApp.controller.CarTypeCtrl");
                                            con.editCarType(button);
                                        }
						            },'-',{
						                text:'删除',
						                id:'12010103',
						                tooltip:'删除车辆类型',
						                iconCls:'remove',
						                //action : 'delete',
						                handler: function(button){
                                            var con = Ext.create("CarTypeApp.controller.CarTypeCtrl");
                                            con.deleteCarType(button);
                                        }
						            },{
						                text:'导出',
						                id:'12010104',
						                tooltip:'Excel导出',
						                iconCls:'common-excel-icon',
						                //action : 'export',
						                handler: function(button){
                                            var con = Ext.create("CarTypeApp.controller.CarTypeCtrl");
                                            con.exportInfo(button);
                                        }
						            }]
            }],  
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CarTypeListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

