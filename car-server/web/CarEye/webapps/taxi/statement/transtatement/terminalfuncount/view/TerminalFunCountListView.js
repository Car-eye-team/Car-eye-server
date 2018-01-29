Ext.define('TerminalFunCountApp.view.TerminalFunCountListView' ,{
    extend: 'Ext.grid.Panel',
    border : true,
    alias : 'widget.terminalFunCountListView',
    title: '终端功能使用统计',
	region: "center",
	frame: true,
	store: "TerminalFunCountListStore",
	viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
	multiSelect: true,
	stripeRows:true, //表格是否隔行换色，默认为false
	loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
	columns: [
            { header: '',width:30,xtype: 'rownumberer',sortable: false},		
            { header: 'ID',width:50, dataIndex: 'id', hidden : true},
            { header: '终端设备号',  width:100,dataIndex: 'terminal', hidden: true ,},
            { header: '企业', width:100,dataIndex: 'blocname', hidden: true },
			{ header: '车辆ID',width:150, dataIndex: 'carid', hidden: true },
			{ header: '车牌号',width:100, dataIndex: 'carnumber', sortable: true},
			{ header: '终端设备使用数(单位:次)', sortable: true ,
              columns: [
                { header: '支付',width:100,dataIndex: 'count12', sortable: true},
				{ header: '电召', width:100,dataIndex: 'count13', sortable: true },
				{ header: '顺风车', width:100,dataIndex: 'count14', sortable: true },
				{ header: '导航', width:100,dataIndex: 'count4', sortable: true },
				{ header: '娱乐', width:100,dataIndex: 'count15', sortable: true },
				{ header: '对讲', width:100,dataIndex: 'count16', sortable: true },
				{ header: '设置', width:100,dataIndex: 'count8', sortable: true },
				{ header: '蓝牙', width:100,dataIndex: 'count17', sortable: true },
				{ header: '软件扩展', width:100,dataIndex: 'count11', sortable: true },
				{ header: '行车记录仪', width:100,dataIndex: 'count18', sortable: true }
              ]}
			
			
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
										id:'',
						                iconCls:'common-excel-icon',
							            handler: function(button){
							        		var con = Ext.create("TerminalFunCountApp.controller.TerminalFunCountCtrl");
							        		con.exportInfo();
							            }
									},{
										text:'Word导出',
										id:'',
						                iconCls:'common-word-icon',
							            handler: function(button){
							        		var con = Ext.create("TerminalFunCountApp.controller.TerminalFunCountCtrl");
							        		con.exportTFCWord();
							            }
									}
			                  ]
			              }
			          ],
			          
			bbar : {
				xtype : 'pagingtoolbar',
				store: "TerminalFunCountListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});
