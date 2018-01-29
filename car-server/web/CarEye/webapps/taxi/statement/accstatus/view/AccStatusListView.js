Ext.define('AccStatusApp.view.AccStatusListView' ,{
    extend: 'Ext.grid.Panel',
    border : true,
    alias : 'widget.accStatusListView',
    title: 'ACC状态报表',
	region: "center",
	frame: true,
	store: "AccStatusListStore",
	viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
	multiSelect: true,
	stripeRows:true, //表格是否隔行换色，默认为false
	loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
	columns: [
            { header: 'No',xtype: 'rownumberer',sortable: false},			
			{ header: '企业',  flex:2, dataIndex: 'blocname', sortable: true },
			{ header: '车辆总数', flex:1,dataIndex: 'carcount', sortable: true },
			{ header: 'acc开数量', flex:1,dataIndex: 'accon', sortable: true },
			{ header: 'acc关数量', flex:1,dataIndex: 'accoff', sortable: true }
			],
			listeners:{
				
			    //单击单元格事件 
			    cellclick: function(table, td, cellIndex, model, tr, rowIndex, e, eOpts){
                //debugger
                //var carnumber=model.data.carnumber;
                var blocname=model.data.blocname;
                var flag="";
                var flagTwo="";
				if( cellIndex == 0|| cellIndex == 5){
					return;
				}else{
					if(cellIndex==3){
						flag=1;
					}
					else if(cellIndex==4){
						flagTwo=1;
					}
				}
				//var stime=encodeURI(Ext.util.Format.date(Ext.getCmp('ga_stime').getValue(), 'Y-m-d H:i:s'));
				//var etime=encodeURI(Ext.util.Format.date(Ext.getCmp('ga_etime').getValue(), 'Y-m-d H:i:s'));
				//var deptid=Ext.getCmp('ga_deptid').getValue();
				var view = Ext.widget('carInfoListView');
				view.show();
				//Ext.getCmp('ci_stime').setValue(Ext.getCmp('ga_stime').getValue());
				//Ext.getCmp('ci_etime').setValue(Ext.getCmp('ga_etime').getValue());
				//Ext.getCmp('ci_deptid').setValue(deptid);
				var store = Ext.StoreManager.get("CarInfoListStore");
				store.clearFilter(true);
				store.on('beforeload', function (store, options) {
		            var new_params = { 
		                //deptid:deptid,
		                //carids:encodeURI(carids),
		                flag:flag,
		                flagTwo:flagTwo,
		                blocname:encodeURI(blocname)
		                //,
		                //stime:stime,
		               // etime:etime
		            };
		            Ext.apply(store.proxy.extraParams, new_params);
	       		 });
	   			 store.load();
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
						                iconCls:'common-excel-icon',
							            handler: function(button){
							        		var con = Ext.create("AccStatusApp.controller.AccStatusCtrl");
							        		con.exportInfo();
							            }
									}
			                  ]
			              }
			          ],
			          
			bbar : {
				xtype : 'pagingtoolbar',
				store: "AccStatusListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});
