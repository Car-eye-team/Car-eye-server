Ext.define('OnCallCountApp.view.CusBreachListView' ,{
	extend : 'Ext.window.Window',
	alias : 'widget.cusBreachListView',
    title: '客户违约数量详情',
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
		    store: "CusBreachListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			//selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
		   { header: 'No',xtype: 'rownumberer',sortable: false},			
			{ header: 'ID',  flex:1, dataIndex: 'id', sortable: true ,hidden:true},
			{ header: '订单ID', width:150, dataIndex: 'orderid', sortable: true ,locked:true},
			{ header: '乘客姓名', flex:1,dataIndex: 'username', sortable: true },
			{ header: '联系电话', flex:1,dataIndex: 'phone', sortable: true },
			{ header: '备注', flex:1,dataIndex: 'remark', sortable: true },
			{ header: '取消时间', flex:1,dataIndex: 'canceltime', sortable: true }
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
							        		con.exportInfoCusBreak();
							            }
									}
			                  ]
			              }
			          ],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CusBreachListStore",  
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
	} ]
});