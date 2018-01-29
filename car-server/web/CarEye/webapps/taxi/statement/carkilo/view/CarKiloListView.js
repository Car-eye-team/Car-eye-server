Ext.define('CarKiloApp.view.CarKiloListView' ,{
    extend: 'Ext.grid.Panel',
    border : true,
    alias : 'widget.carKiloListView',
    title: '车辆里程报表',
	region: "center",
	frame: true,
	store: "CarKiloListStore",
	viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
	multiSelect: true,
	stripeRows:true, //表格是否隔行换色，默认为false
	loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
	columns: [
			new Ext.grid.RowNumberer(), 
			{ header: 'id',  flex:2, dataIndex: 'id', hidden: true },
			{ header: '企业',  flex:2, dataIndex: 'blocname', sortable: true },
			{ header: '终端号码', flex:1,dataIndex: 'terminal', sortable: true },
			{ header: '车牌号', flex:1,dataIndex: 'carnumber', sortable: true },
			{ header: '里程(km)', flex:1,dataIndex: 'mileage', sortable: true }
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
							        		var con = Ext.create("CarKiloApp.controller.CarKiloCtrl");
							        		con.exportInfo();
							            }
									}
			                  ]
			              }
			          ]
});
