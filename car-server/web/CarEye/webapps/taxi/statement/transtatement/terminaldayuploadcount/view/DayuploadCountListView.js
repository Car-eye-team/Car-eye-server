Ext.define('DayuploadCountApp.view.DayuploadCountListView' ,{
    extend: 'Ext.grid.Panel',
    border : true,
    alias : 'widget.dayuploadCountListView',
    title: '终端日上报统计列表',
	region: "center",
	frame: true,
	store: "DayuploadCountListStore",
	viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
	multiSelect: true,
	stripeRows:true, //表格是否隔行换色，默认为false
	loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
	columns: [
            { header: 'No',xtype: 'rownumberer',sortable: false},			
			{ header: 'ID',  flex:1, dataIndex: 'id', sortable: true ,hidden:true},
			{ header: 'CARID',  flex:1, dataIndex: 'carid', sortable: true ,hidden:true},
			{ header: '终端设备号',  width:120,dataIndex: 'terminal', sortable: true ,locked:true },
			{ header: '车牌号',width:80 , dataIndex: 'carnumber', sortable: true},
			{ header: '企业', width:80,dataIndex: 'blocname', sortable: true },
			{ header: '功能类型', flex:1,dataIndex: 'type', sortable: true,renderer:function(v){
				if(1==v){
					return '考拉FM';
				}else{
					return '';
				}
			} },
			{ header: '使用次数(次)', width:80,dataIndex: 'numbercount', sortable: true },
			{ header: '使用时长(秒)', width:80,dataIndex: 'usinglen', sortable: true },
			{ header: '使用流量(kb)', width:80,dataIndex: 'flow', sortable: true },
			{ header: '统计时间', width:150,dataIndex: 'time', sortable: true},
			{ header: '创建时间', width:150,dataIndex: 'createtime', sortable: true }
			
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
										id:'18070701',
						                iconCls:'common-excel-icon',
							            handler: function(button){
							        		var con = Ext.create("DayuploadCountApp.controller.DayuploadCountCtrl");
							        		con.exportInfo();
							            }
									}
			                  ]
			              }
			          ],
			          
			bbar : {
				xtype : 'pagingtoolbar',
				store: "DayuploadCountListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});
