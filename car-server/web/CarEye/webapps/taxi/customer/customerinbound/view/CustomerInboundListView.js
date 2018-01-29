Ext.define('CustomerInboundApp.view.CustomerInboundListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id : 'customerInboundListViewGrid',
    alias : 'widget.customerInboundListView',
			region: "center",
			width:750,
			title:'客户来电信息列表',
            collapsible: true,
			store: "CustomerInboundListStore",
            collapseMode: "mini",
            split: true,
			frame: true,
			multiSelect: true,
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			 new Ext.grid.RowNumberer(), 
			{ header: 'id',  flex:1 , dataIndex: 'id', hidden: true },
			{ header: '录音', flex:0.5,dataIndex: 'recordfile', sortable: true,renderer:function(value){
					if (value == null || value.length == 0) {
						return "";
					} else {		
										
						var path = "http://www.mixcall.cn/admin/?m=interface&c=api&a=record_play&recording=/" +
								"var/spool/asterisk/monitor/" + value;
						return "<a href='javascript:void(0);' onclick=\"openView('"
								+ path
								+ "')\" style='color:blue'>播放</a>";
					}
			} 
			},
			{ header: '流水号',flex:1, dataIndex: 'uniqueid', sortable: true },
			{ header: '客户id',  flex:1,  dataIndex: 'cid', hidden: true},
			{ header: '客户姓名', flex:1,  dataIndex: 'cname', sortable: true 
//			,renderer:function(value){
//					if(value.length>0){
//						return value;
//					}else{
//					    return "<span style='color:red'>未知</span>";
//					 }
//			}
			},
			{ header: '分机号码',  flex:1,  dataIndex: 'agentid'},
			//{ header: '客户类型', width:150,dataIndex: 'typename', sortable: true
//			,renderer:function(value){
//					if(value.length>0){
//						return value;
//					}else{
//					    return "<span style='color:red'>未知</span>";
//					 }
//			} 
			//},
			{ header: '来电号码',flex:1, dataIndex: 'callnumber', sortable: true },
			{ header: '来电时间', flex:1,dataIndex: 'inboundcalltime', sortable: true },
			{ header: '挂机时间', flex:1, dataIndex: 'hangupcalltime', sortable: true },
			{ header: '创建人',  flex:1,  dataIndex: 'username', sortable: true },
			{ header: '创建时间', flex:1,  dataIndex: 'createtime', sortable: true }
			],
			dockedItems: [
					{
					    xtype: 'toolbar',
					    dock: 'top',
					    items: [
								
						    ]
						},
						 {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
								{
								        text:'删除',
								        id: '150301',
								        tooltip:'删除客户信息',
								        iconCls:'delete',
								        action : 'delete'
								    },{
						                text:'导出',
						                id:'150302',
						                tooltip:'Excel导出',
						                iconCls:'common-excel-icon',
						                action : 'export'
						            }
			                  ]
			              }
			             
			          ],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CustomerInboundListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});

