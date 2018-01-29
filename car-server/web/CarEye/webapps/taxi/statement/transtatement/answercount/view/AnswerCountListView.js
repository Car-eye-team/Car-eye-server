Ext.define('AnswerCountApp.view.AnswerCountListView' ,{
    extend: 'Ext.grid.Panel',
    border : true,
    alias : 'widget.answerCountListView',
    title: '抢答统计报表',
	region: "center",
	frame: true,
	store: "AnswerCountListStore",
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
							        		var con = Ext.create("AnswerCountApp.controller.AnswerCountCtrl");
							        		con.excelExport();
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
});
