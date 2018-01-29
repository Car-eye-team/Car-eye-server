Ext.define('EvaluateCountApp.view.EvaluateCountListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.evaluateCountListView',
			title: '评价统计列表--双击查看日期评价明细',
			region: "center",
   			border: true,
			frame: true,
			store: "EvaluateCountListStore", 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
//			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			 new Ext.grid.RowNumberer(), 
			{ header: 'ID', flex:0.5, dataIndex: 'id', hidden: true },
			{ header: '企业id', flex:1, dataIndex: 'blocid', hidden: true },
			{ header: '车辆id', flex:1, dataIndex: 'carid', hidden: true },
			
			{ header: '日期', flex:1, dataIndex: 'datetime', sortable: true },
			{ header: '企业名称', flex:1, dataIndex: 'blocname', hidden: true },
			{ header: '车牌号码', flex:1, dataIndex: 'carnumber', hidden: true },
			{ header: '好评数', flex:1, dataIndex: 'count1', sortable: true},
			{ header: '中评数', flex:1, dataIndex: 'count2', sortable: true },
			{ header: '差评数',flex:1, dataIndex: 'count3', sortable: true },
			{ header: '好评百分比', flex:1, dataIndex: 'count4', sortable: true }
			],
			listeners:{
				
			    //双击单元格事件 
			    celldblclick: function(table, td, cellIndex, model, tr, rowIndex, e, eOpts){
			    var evalevel = '';
				if(cellIndex==1){
				  return;
				}else if(cellIndex==2){
					evalevel='1';
				}else if(cellIndex==3){
					evalevel='3';
				}else if(cellIndex==4){
					evalevel='2';
				}else if(cellIndex==5){
					return;
				}
				
				var datetime = model.data.datetime;
				var blocid = Ext.getCmp('tmp_blocid').getValue();
				var carnumber = Ext.getCmp('tmp_carnumber').getValue();
				
				var view = Ext.create('EvaluateCountApp.view.EvaluateCountDetailsWindow');
				view.show();
				
				var store = Ext.StoreManager.get("EvaluateCountDetailsStore");
				store.clearFilter(true);
				store.on('beforeload', function (store, options) {
			            var new_params = { 
			            	blocid: encodeURI(blocid),
			            	carnumber: encodeURI(carnumber),
				            datetime: encodeURI(datetime),
				            evalevel: evalevel
			            };
			            Ext.apply(store.proxy.extraParams, new_params);
			        });
			        store.loadPage(1);
			        
			    return false;
				
	   			}
			},
			dockedItems: [
			              {
			                  xtype: 'toolbar',
			                  layout: {
							        overflowHandler: 'Menu'
							  },   //溢出隐藏
			                  dock: 'top',
			                  items: [{
										text:'Excel导出',
										id: '',
						                iconCls:'common-excel-icon',
						                action : 'export'
//						                ,
//						                 handler: function(button){
//                                            var con = Ext.create("DriverEvaluationApp.controller.DriverEvaluationCtrl");
//                                            con.exportExcel(button);
//                                         }
								    },{
										text:'Word导出',
										id: '',
						                iconCls:'common-word-icon',
						                action : 'word'
//						                ,
//						                 handler: function(button){
//                                            var con = Ext.create("DriverEvaluationApp.controller.DriverEvaluationCtrl");
//                                            con.exportExcel(button);
//                                         }
								    }]
            }]
		
});

