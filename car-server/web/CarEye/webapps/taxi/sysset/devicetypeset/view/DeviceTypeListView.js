Ext.define('DeviceTypeApp.view.DeviceTypeListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.deviceTypeListView',
			title: '终端类型设置信息列表',
			region: "center",
   			border: true,
			frame: true,
			store: "DeviceTypeListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: '序号', width:30, xtype: 'rownumberer'},
			{ header: 'id',  flex:1, dataIndex: 'id', hidden: true },
			{ header: '终端类型ID',  flex:1, dataIndex: 'typeid', sortable: true },
			{ header: '终端类型名称',  flex:1, dataIndex: 'typename', sortable: true },
			{ header: '用户类型',  flex:1, dataIndex: 'usertype', sortable: true },
			{ header: '所属厂家',  flex:1, dataIndex: 'company', sortable: true },
			{ header: '终端网络类型', flex:1, dataIndex: 'inteltype', sortable: true },
			{ header: '创建时间', flex:1, dataIndex: 'createtime', sortable: true },
			{ header: '操作员', flex:1, dataIndex: 'userid', hidden: true },
			{ header: '操作员', flex:1, dataIndex: 'username', sortable: true }
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
							                id: '120301',
							                tooltip:'添加终端类型设置信息',
							                iconCls:'add',
							                //action : 'add',
							                handler: function(button){
							                  var con = Ext.create("DeviceTypeApp.controller.DeviceTypeCtrl");
							                  con.addDeviceType(button);
							                }
							            }, '-', {
							                text:'修改',
							                id: '120302',
							                tooltip:'修改终端类型设置信息',
							                iconCls:'edit',
							                //action : 'edit',
							                handler: function(button){
							                  var con = Ext.create("DeviceTypeApp.controller.DeviceTypeCtrl");
							                  con.editDeviceType(button);
							                }
							            },'-',{
							                text:'删除',
							                id: '120303',
							                tooltip:'删除终端类型设置信息',
							                iconCls:'remove',
							                //action : 'delete',
							                handler: function(button){
							                  var con = Ext.create("DeviceTypeApp.controller.DeviceTypeCtrl");
							                  con.deleteDeviceType(button);
							                }
							            },'-',{
							                text:'导出',
							                id: '120304',
							                tooltip:'Excel终端类型设置信息',
							                iconCls:'common-excel-icon',
							                //action : 'export',
							                handler: function(button){
							                  var con = Ext.create("DeviceTypeApp.controller.DeviceTypeCtrl");
							                  con.exportDeviceType(button);
							                }
							            }]
            }],  
			bbar : {
				xtype : 'pagingtoolbar',
				store: "DeviceTypeListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

