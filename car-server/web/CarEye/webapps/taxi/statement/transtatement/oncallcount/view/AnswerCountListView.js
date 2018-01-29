Ext.define('OnCallCountApp.view.AnswerCountListView' ,{
	extend : 'Ext.window.Window',
	alias : 'widget.answerCountListView',
    title: '抢答业务数量详细',
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
		    store: "AnswerCountListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			//selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
		    new Ext.grid.RowNumberer(), 
			{ header: '企业名称',  flex:1, dataIndex: 'blocname', sortable: true},
			{ header: '车牌号', flex:1,dataIndex: 'carnumber', sortable: true },
			{ header: '当班司机姓名', flex:1,dataIndex: 'drivername', sortable: true },
			{ header: '终端设备号', flex:1,dataIndex: 'terminal', sortable: true },
			{ header: '中标状态', flex:1,dataIndex: 'zbstatus', sortable: true,renderer:function(v){
				if(1==v){
					return '未中标';
				}else if(2==v){
					return '中标';
				}else {
					return '';				
				}
			} },
			{ header: '订单ID', flex:1,dataIndex: 'orderid', sortable: true },
			{ header: '创建时间', flex:1,dataIndex: 'createtime', sortable: true }
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
							        		con.exportInfoAnswerCount();
							            }
									}
			                  ]
			              }
			          ],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "AnswerCountListStore",  
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
	} ]
});
