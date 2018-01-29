Ext.define('DriverEvaluationApp.view.DriverEvaluationListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.driverEvaluationListView',
			title: '司机评价客户信息列表',
			region: "center",
   			border: true,
			frame: true,
			store: "DriverEvaluationListStore", 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
//			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			 new Ext.grid.RowNumberer(), 
			{ header: 'ID', width:60, dataIndex: 'id', hidden: true },
			{ header: '订单id', width:100, dataIndex: 'orderid', sortable: true },
			{ header: '司机id', width:100, dataIndex: 'did', hidden: true },
			{ header: '客户id', width:100, dataIndex: 'cid', hidden: true },
			{ header: '司机姓名', width:100, dataIndex: 'dname', sortable: true,locked:true },
			{ header: '司机手机号', width:100, dataIndex: 'dphone', sortable: true,locked:true },
			{ header: '星级', width:60, dataIndex: 'slevel', sortable: true,locked:true, renderer:function(value){
					if(value==1){
						return "一星";
					}else if(value==2){
						return "二星";
					}else if(value==3){
						return "三星";
					}else if(value==4){
						return "四星";
					}else if(value==5){
						return "五星";
					}else{
						return "";
					}
			}},
			{ header: '客户姓名', width:100, dataIndex: 'cname', sortable: true },
			{ header: '客户手机号', width:100, dataIndex: 'cphone', sortable: true },
			{ header: '评价内容', width:300, dataIndex: 'content', sortable: true },
			{ header: '上车地址', width:200, dataIndex: 'saddress', sortable: true },
			{ header: '下车地址', width:200, dataIndex: 'eaddress', sortable: true },
			{ header: '创建时间', width:130, dataIndex: 'createtime', sortable: true }
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
                                            var con = Ext.create("DriverEvaluationApp.controller.DriverEvaluationCtrl");
                                            con.exportExcel(button);
                                         }
								    }]
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "DriverEvaluationListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

