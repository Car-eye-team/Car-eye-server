Ext.define('OnCallCountApp.view.CarDriverCancelListView' ,{
	extend : 'Ext.window.Window',
	alias : 'widget.carDriverCancelListView',
    title: '司机取消数量详细',
    width : 800,
    height : 510,
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
			minHeight:490,
			frame: true,
		    store: "CarDriverCancelListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			//selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
		    { header: 'No',xtype: 'rownumberer',sortable: false,locked:true },			
			{ header: 'ID',  width:150, dataIndex: 'id', sortable: true,hidden:true },
			{ header: '订单ID', width:150,dataIndex: 'orderid', sortable: true,locked:true },
			{ header: '企业', width:150,dataIndex: 'blocname', sortable: true },
			{ header: '司机', width:80,dataIndex: 'drivername', sortable: true },
			{ header: '车牌号',width:80,dataIndex: 'carnumber', sortable: true },
			{ header: '是否违约', width:80,dataIndex: 'wy', sortable: true ,
			renderer:function(value){
					if(value == 1){
						return "<span style='color:red'>违约</span>";
					}else if(value==0){
						return "不违约";
					}else{
					    return "";
					 }
			}},
			{ header: '备注', width:150,dataIndex: 'remark', sortable: true },
			{ header: '取消时间', width:150,dataIndex: 'canceltime', sortable: true }
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
							        		var con = Ext.create("OnCallCountApp.controller.OnCallCountCtrl");
							        		con.exportInfoDriverCancel();
							            }
									}
			                  ]
			              }
			          ],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CarDriverCancelListStore",  
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
	} ]
});


