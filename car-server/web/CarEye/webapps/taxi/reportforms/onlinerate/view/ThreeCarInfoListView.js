Ext.define('OnlineRateApp.view.ThreeCarInfoListView' ,{
    extend: 'Ext.grid.Panel',
    //上下线次数
    alias : 'widget.threeCarInfoListView',
    id : 'threeCarInfoListView',
			region: "center",
   			border: false,
			frame: true,
			height : 200,
			store: "ThreeCarInfoListStore", 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			selModel: { selType: 'checkboxmodel',
				listeners : {
					selectionchange : function(rowmodel) {
						var grid = Ext.getCmp('threeCarInfoListView');
						var records = grid.getSelectionModel().getSelection();
						if(records.length > 0){
							var carid = records[0].get("carid");
							var carnumber = records[0].get("carnumber");
							if(carid != old_carid2){
								$.ajax({
									url : window.BIZCTX_PATH
											+ '/onlineratejson/threePriterList.action',
									data : {
										blocid: encodeURI(Ext.getCmp('c_blocid').getValue()),
							            stime: Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d'),
							            etime: Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d'),
							            carid: carid
									},
									type : "post",
									success : function(data) {
										var list = data.result.list;
										row3.length=0;
							    		col3.length=0;
							    		col4.length=0;
							    		for(var i=0;i<list.length;i++){
							    			row3.push(list[i].createtime);
							    			col3.push(list[i].inlinecount);
							    			col4.push(list[i].offlinecount);
							    		}
							    		var control = Ext.create('OnlineRateApp.controller.OnlineRateCtrl');
	    								control.setecharts3(); //重新加载图表
									}
								});
								
							}
							$('#carnumber3').html('车牌号：'+carnumber);
						   old_carid2 = carid;
						}
					}
				}  }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'ID', flex:1, dataIndex: 'id', hidden: true },
			{ header: '企业ID', flex:1, dataIndex: 'blocid', hidden: true },
			{ header: '车辆ID', flex:1, dataIndex: 'carid', hidden: true },
			{ header: '企业', flex:1.5, dataIndex: 'blocname', sortable: true},
			{ header: '车牌号', flex:1, dataIndex: 'carnumber', sortable: true},
			{ header: '日期', flex:1, dataIndex: 'createtime', sortable: true },
			{ header: '上线次数', flex:1, dataIndex: 'inlinecount', sortable: true},
			{ header: '下线次数', flex:1, dataIndex: 'offlinecount', sortable: true }
			],
//			dockedItems: [
//			              {
//			                  xtype: 'toolbar',
//			                  layout: {
//							        overflowHandler: 'Menu'
//							  },   //溢出隐藏
//			                  dock: 'top',
//			                  items: [{
//										text:'Excel导出',
//										id: '',
//						                iconCls:'common-excel-icon',
//						                action : 'export'
////						                 handler: function(button){
////                                            var con = Ext.create("CommondLogApp.controller.CommondLogCtrl");
////                                            con.exportCommondLog(button);
////                                         }
//								    }]
//            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "ThreeCarInfoListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

