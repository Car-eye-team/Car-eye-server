Ext.define('CommondLogApp.view.CommondLogListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.commondLogListView',
			title: '指令下发日志列表',
			region: "center",
   			border: true,
			frame: true,
			store: "CommondLogListStore", 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'ID', width:60, dataIndex: 'id', hidden: true },
			{ header: '企业ID', width:100, dataIndex: 'blocid', hidden: true },
			{ header: '车辆ID', width:100, dataIndex: 'carid', hidden: true },
			{ header: '企业', width:150, dataIndex: 'blocname', sortable: true,locked:true },
			{ header: '车牌号', width:80, dataIndex: 'carnumber', sortable: true,locked:true },
			{ header: '设备号', width:100, dataIndex: 'terminal', sortable: true,locked:true},
			{ header: '协议ID', width:100, dataIndex: 'msgid', sortable: true },
			{ header: '下发结果', width:100, dataIndex: 'status', sortable: true , renderer:function(value){
					if(value == "0"){
						return "成功/确认";
					}else if(value==1){
						return "失败";
					}else if(value==2){
						return "消息有误";
					}else if(value==3){
						return "不支持";
					}else{
						return "";
					}
			}},
			{ header: '流水号', width:100, dataIndex: 'seq', sortable: true },
			{ header: '协议名称', width:100, dataIndex: 'msgtype', sortable: true },
			{ header: '操作时间', width:130, dataIndex: 'createtime', sortable: true},
			{ header: '应答时间', width:130, dataIndex: 'restime', sortable: true},
			{ header: '下发数据', width:200, dataIndex: 'data', sortable: true },
			{ header: '备注', width:200, dataIndex: 'remark', sortable: true}
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
										id: '18070601',
						                iconCls:'common-excel-icon',
						                //action : 'export',
						                 handler: function(button){
                                            var con = Ext.create("CommondLogApp.controller.CommondLogCtrl");
                                            con.exportCommondLog(button);
                                         }
								    },{
										text:'删除',
										id: '',
						                iconCls:'delete',
						                //action : 'export',
						                 handler: function(button){
                                            var con = Ext.create("CommondLogApp.controller.CommondLogCtrl");
                                            con.deleteCommondLog(button);
                                         }
								    }]
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CommondLogListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

