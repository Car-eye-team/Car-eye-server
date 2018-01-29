Ext.define('TransactionApp.view.TransactionListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id : 'transactionListViewGrid',
    alias : 'widget.transactionListView',
			title:'约车业务信息列表 —————— 双击行 显示订单抢单记录',
			region: "center",
   			border: true,
			frame: true,
			store: "TransactionListStore", 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'No',width:40, xtype: 'rownumberer',align : 'center', sortable: false},
			{ header: 'id',  width:100, dataIndex: 'id', hidden: true },
			{ header: '订单号', width:150, dataIndex: 'orderid', sortable:true,locked:true},
			{ header: '是否抢答', width:100,dataIndex: 'resstatus', sortable: true ,renderer:function(value){
					if(value == "0"){
						return "未抢答";
					}else if(value=="1"){
						return "<span style='color:green'>抢答</span>";
					}else{
					    return "";
					 } 
			}},
		    { header: '业务来源', width:80, dataIndex: 'source', sortable: true ,renderer:function(value){
					if(value == 1){
						return "电召";
					}else if(value==2){
						return "网站";
					}else if(value==3){
						return "APP";
					}else if(value==5){
						return "微信";
					}else{
					    return "";
					 }
			}},
			{ header: '约车类型', width:100,dataIndex: 'typename', hidden: true },
			{ header: '业务类型', width:100,dataIndex: 'tratype', sortable: true ,renderer:function(value){
					if(value == '0'){
						return "即时召车";
					}else if(value==1){
						return "<span style='color:green'>预约召车</span>";
					}else if(value==2){
						return "<span style='color:blue'>车辆指派</span>";
					}else{
					    return "";
					}
			}},
			{ header: '业务状态', width:80,dataIndex: 'status', sortable: true ,renderer:function(value){
					if(value == '0'){
						return "无应答";
					}else if(value==1){
						return "<span style='color:green'>调派中</span>";
					}else if(value==2){
						return "<span style='color:blue'>已调派</span>";
					}else if(value==3){
						return "<span style='color:red'>取消</span>";
					}else if(value==4){
						return "超时";
					}else if(value==5){
						return "<span style='color:orange'>载客中</span>";
					}else if(value==6){
						return "<span style='color:orange'>待支付</span>";
					}else if(value==7){
						return "<span style='color:orange'>待评价</span>";
					}else if(value==8){
						return "<span style='color:orange'>完成</span>";
					}else{
					    return "";
					}
			}},
			{ header: '是否支付', width:80,dataIndex: 'pay', sortable: true ,renderer:function(value){
					if(value == 2){
						return "<span style='color:green'>已支付</span>";
					}else if(value==1){
						return "<span style='color:red'>待支付</span>";
					}else{
					    return "";
					}
			}},
			{ header: '用户名称', width:100,dataIndex: 'usernametwo', sortable: true },
			{ header: '当班司机', width:100,dataIndex: 'drivername', sortable: true },
			{ header: '性别', width:50,dataIndex: 'sex', hidden: true
			,renderer:function(value){
					if(value == "MALE"){
						return "男";
					}else if(value=="FEMALE"){
						return "女";
					}else{
					    return "";
					 }
			}
			},
			{ header: '联系电话', width:100,dataIndex: 'phone', sortable: true },
			{ header: '出发地', width:140,dataIndex: 'saddress', sortable: true },
			{ header: '目的地', width:140,dataIndex: 'eaddress', sortable: true },
			{ header: '抢答成功车牌号', width:100,dataIndex: 'carnumber', sortable: true},
			{ header: '抢答时间', width:130,dataIndex: 'answertime', sortable: true },
			{ header: '业务开始时间', width:130,dataIndex: 'starttime', sortable: true },
			{ header: '业务结束时间', width:130,dataIndex: 'endtime', sortable: true },
			{ header: '回拨乘客电话', width:100,dataIndex: 'callbackphone', hidden: true ,
			renderer:function(value){
					if(value == '0'){
						return "失败";
					}else if(value=='1'){
						return "<span style='color:green'>成功</span>";
					}else{
					    return "";
					}
			} },
			{ header: '是否合乘', width:80,dataIndex: 'carpool', hidden: true ,renderer:function(value){
					if(value == '0'){
						return "不合乘";
					}else if(value=='1'){
						return "<span style='color:green'>合乘</span>";
					}else{
					    return "";
					}
			} },
			{ header: '乘客人数', width:80,dataIndex: 'carpoolpersonnum', sortable: true },
			{ header: '预约时间', width:130,dataIndex: 'appointmenttime', sortable: true },
			{ header: '电召费(元)', width:100,dataIndex: 'callfee', hidden: true },
		    { header: '备注',  width:200, dataIndex: 'remark' },
			{ header: '创建人',  width:60, dataIndex: 'username', sortable: true },
			{ header: '创建时间',  width:150, dataIndex: 'createtime', sortable: true }
			],
			listeners:{
			    'itemdblclick' : function(grid, record,item,index, e,eOpts){
				    var orderid=record.data.orderid;
					var view = Ext.widget('transactionAnswerListView');
					view.show();
					var store = Ext.StoreManager.get("TransactionAnswerListStore");
					store.clearFilter(true);
					store.on('beforeload', function (store, options) {
			            var new_params = { 
			                orderid : orderid
			                //,
			                 //carnumber:carnumber
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
                              items: [
                                      {
								        text:'添加',
								        id: '160101',
								        tooltip:'添加约车业务信息',
								        iconCls:'add',
								        //action : 'add',
								         handler: function(button){
                                           // var con = Ext.create("TransactionApp.controller.TransactionCtrl");
                                            //con.addTransaction(button);
								        }
								    },
								    	{
								        text:'修改',
								        id: '160102',
								        tooltip:'修改约车业务信息',
								        iconCls:'edit',
								       // action : 'edit',
								        handler: function(button){
                                            var con = Ext.create("TransactionApp.controller.TransactionCtrl");
                                            con.editTransaction(button);
								        }
								    },{
								        text:'删除',
								        id: '160103',
								        tooltip:'删除约车业务信息',
								        iconCls:'delete',
								        //action : 'delete',
								        handler: function(button){
                                            var con = Ext.create("TransactionApp.controller.TransactionCtrl");
                                            con.deleteTransaction(button);
								        }
								    },{
						                text:'导出',
						                id:'160104',
						                tooltip:'Excel导出',
						                iconCls:'common-excel-icon',
						                //action : 'export',
						                 handler: function(button){
                                            var con = Ext.create("TransactionApp.controller.TransactionCtrl");
                                            con.exportInfo(button);
								        }
						            }
                                   ]
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "TransactionListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

