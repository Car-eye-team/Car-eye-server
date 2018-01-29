Ext.define('SendSuccessCountApp.view.SendSuccessCountListView' ,{
    extend: 'Ext.grid.Panel',
    border : true,
    alias : 'widget.sendSuccessCountListView',
    title: '派车成功统计报表',
	region: "center",
	frame: true,
	store: "SendSuccessCountListStore",
	viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
	multiSelect: true,
	stripeRows:true, //表格是否隔行换色，默认为false
	loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
	columns: [
			new Ext.grid.RowNumberer(), 
			{ header: '企业名称',  flex:1, dataIndex: 'blocname', sortable: true},
			{ header: '车牌号', flex:1,dataIndex: 'carnumber', sortable: true },
			{ header: '当班司机姓名', flex:1,dataIndex: 'drivername', sortable: true },
			{ header: '终端设备号', flex:1,dataIndex: 'terminal', sortable: true },
			{ header: '订单ID', flex:1,dataIndex: 'orderid', sortable: true },
			{ header: '创建时间', flex:1,dataIndex: 'createtime', sortable: true },
			],
			dockedItems: [
			              	{
			                  xtype: 'toolbar',
			                  layout: {
						         overflowHandler: 'Menu'
						      },   //溢出隐藏
			                  dock: 'top',
			                  items: [{
										text:'Excel导出',
						                iconCls:'common-excel-icon',
							            handler: function(button){
							        		var con = Ext.create("SendSuccessCountApp.controller.SendSuccessCountCtrl");
							        		con.excelExport();
							            }
									}
			                  ]
			              }
			          ]
});
