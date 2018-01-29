Ext.define('OnCallCountApp.view.TransactionListView', {
	extend : 'Ext.window.Window',
	alias : 'widget.transactionListView',
	title : '下发业务记录列表',
	width : 800,
	height : 510,
	layout : 'form',
	animCollapse : false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',
	items : [{
		xtype : 'grid',
		autoWidth : true,
		autoHeight : true,
		minHeight : 490,
		frame : true,
		store : "TransactionListStore",
		multiSelect : true,
		stripeRows : true, // 表格是否隔行换色，默认为false
		loadMask : true, 
		selModel : {
			selType : 'checkboxmodel'
		}, 
		columns : [{ header: 'No',xtype: 'rownumberer',sortable: false},
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
						return "96106网站";
					}else if(value==3){
						return "飞嘀";
					}else if(value==4){
						return "服务窗";
					}else{
					    return "";
					 }
			}},
			{ header: '约车类型', width:100,dataIndex: 'typename', sortable: true },
			{ header: '业务类型', width:100,dataIndex: 'tratype', sortable: true ,renderer:function(value){
					if(value == 0){
						return "即派订单";
					}else if(value==1){
						return "<span style='color:green'>预约订单</span>";
					}else{
					    return "";
					}
			}},
			{ header: '业务状态', width:80,dataIndex: 'status', sortable: true ,renderer:function(value){
					if(value == 0){
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
						return "<span style='color:orange'>完成</span>";
					}else{
					    return "";
					}
			}},
			{ header: '用户名称', width:100,dataIndex: 'usernametwo', sortable: true },
			{ header: '性别', width:50,dataIndex: 'sex', sortable: true
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
//			{ header: '目的地', width:140,dataIndex: 'eaddress', sortable: true },
			{ header: '抢答成功车牌号', width:100,dataIndex: 'carnumber', sortable: true},
			{ header: '抢答时间', width:130,dataIndex: 'answertime', sortable: true },
			{ header: '业务开始时间', width:130,dataIndex: 'starttime', sortable: true },
			{ header: '业务结束时间', width:130,dataIndex: 'endtime', sortable: true },
			{ header: '回拨乘客电话', width:100,dataIndex: 'callbackphone', sortable: true ,
			renderer:function(value){
					if(value == '0'){
						return "失败";
					}else if(value=='1'){
						return "<span style='color:green'>成功</span>";
					}else{
					    return "";
					}
			} },
			{ header: '是否合乘', width:80,dataIndex: 'carpool', sortable: true ,renderer:function(value){
					if(value == '0'){
						return "不合乘";
					}else if(value=='1'){
						return "<span style='color:green'>合乘</span>";
					}else{
					    return "";
					}
			} },
			{ header: '合乘人数', width:80,dataIndex: 'carpoolpersonnum', sortable: true },
			{ header: '预约时间', width:100,dataIndex: 'appointmenttime', sortable: true },
			{ header: '电召费(元)', width:100,dataIndex: 'callfee', sortable: true },
		    { header: '备注',  width:200, dataIndex: 'remark' },
			{ header: '创建人',  width:60, dataIndex: 'username', sortable: true },
			{ header: '创建时间',  width:150, dataIndex: 'createtime', sortable: true }],
		dockedItems : [{
			xtype : 'toolbar',
			layout : {
				overflowHandler : 'Menu'
			}, // 溢出隐藏
			dock : 'top',
			items : [{
				text : '导出',
				tooltip : 'Excel导出',
				iconCls : 'common-excel-icon',
				handler : function(button) {
					var con = Ext
							.create("OnCallCountApp.controller.OnCallCountCtrl");
					con.exportTransactionList(button);
				}
			}]
		}],
		bbar : {
			xtype : 'pagingtoolbar',
			store : "TransactionListStore",
			displayInfo : true,
			displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
			emptyMsg : "没有数据"
		}
	}]
});
