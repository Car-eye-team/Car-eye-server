Ext.define('CusBreachApp.view.CusBreachListView' ,{
    extend: 'Ext.grid.Panel',
    border : true,
    alias : 'widget.cusBreachListView',
    title: '客户违约数统计',
	region: "center",
	frame: true,
	store: "CusBreachListStore",
	viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
	multiSelect: true,
	stripeRows:true, //表格是否隔行换色，默认为false
	loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
	columns: [
           { header: 'No',xtype: 'rownumberer',sortable: false},			
			{ header: 'ID',  flex:1, dataIndex: 'id', sortable: true ,hidden:true},
			{ header: '订单ID', width:150, dataIndex: 'orderid', sortable: true ,locked:true},
			{ header: '乘客姓名', flex:1,dataIndex: 'username', sortable: true },
			{ header: '联系电话', flex:1,dataIndex: 'phone', sortable: true },
			{ header: '备注', flex:1,dataIndex: 'remark', sortable: true },
			{ header: '取消时间', flex:1,dataIndex: 'canceltime', sortable: true }
			],
//			listeners:{
//				
//			    //单击单元格事件 
//			    cellclick: function(table, td, cellIndex, model, tr, rowIndex, e, eOpts){
//                //debugger
//                //var carnumber=model.data.carnumber;
//                var blocname=model.data.blocname;
//                var flag="";
//                var flagTwo="";
//				if( cellIndex == 0|| cellIndex == 5){
//					return;
//				}else{
//					if(cellIndex==3){
//						flag=1;
//					}
//					else if(cellIndex==4){
//						flagTwo=1;
//					}
//				}
//				//var stime=encodeURI(Ext.util.Format.date(Ext.getCmp('ga_stime').getValue(), 'Y-m-d H:i:s'));
//				//var etime=encodeURI(Ext.util.Format.date(Ext.getCmp('ga_etime').getValue(), 'Y-m-d H:i:s'));
//				//var deptid=Ext.getCmp('ga_deptid').getValue();
//				var view = Ext.widget('carInfoListView');
//				view.show();
//				//Ext.getCmp('ci_stime').setValue(Ext.getCmp('ga_stime').getValue());
//				//Ext.getCmp('ci_etime').setValue(Ext.getCmp('ga_etime').getValue());
//				//Ext.getCmp('ci_deptid').setValue(deptid);
//				var store = Ext.StoreManager.get("CusCancelListStore");
//				store.clearFilter(true);
//				store.on('beforeload', function (store, options) {
//		            var new_params = { 
//		                //deptid:deptid,
//		                //carids:encodeURI(carids),
//		                flag:flag,
//		                flagTwo:flagTwo,
//		                blocname:encodeURI(blocname)
//		                //,
//		                //stime:stime,
//		               // etime:etime
//		            };
//		            Ext.apply(store.proxy.extraParams, new_params);
//	       		 });
//	   			 store.load();
//	   			}
//			},
			dockedItems: [
			              	{
			                  xtype: 'toolbar',
			                  layout: {
						         overflowHandler: 'Menu'
						      },   //溢出隐藏
			                  dock: 'top',
			                  items: [{
										text:'Excel导出',
										id:'18070601',
						                iconCls:'common-excel-icon',
							            handler: function(button){
							        		var con = Ext.create("CusBreachApp.controller.CusBreachCtrl");
							        		con.exportInfo();
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
		
});
