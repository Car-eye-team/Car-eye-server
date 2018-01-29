Ext.define('EventApp.view.SendRecordListView' ,{
	extend : 'Ext.window.Window',
    border : false,
    width : 800,
    height : 520,
    id : 'sendrecordgrid',
    alias : 'widget.sendRecordListView',
    title: '事件下发记录列表',
	layout : 'form',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	closeAction : 'destroy',
	items : [ {
		    xtype: 'grid',
		    autoWidth: true,
			autoHeight : true,
    		height : 490,
			frame: true,
			store: "SendRecordListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			columns: [
			{ header: 'id',  width:100, dataIndex: 'id', hidden: true },
			{ header: '企业名称',  width:100, dataIndex: 'blocid', hidden: true },
			{ header: '企业名称',  width:100, dataIndex: 'blocname', sortable: true },
			{ header: '用户ID',  width:100, dataIndex: 'userid', hidden: true },
			{ header: '用户名',  width:100, dataIndex: 'username', sortable: true },
			{ header: '车牌号',  width:100, dataIndex: 'carnumber', sortable: true },
			{ header: '操作类型',  width:100, dataIndex: 'opertype', sortable: true , renderer:function(value){
				if(value == 1){
					return "追加 ";
				}else if(value==2){
					return "更新";
				}else if(value==3){
					return "删除";
				}else {
					return "删除所有";
				}
			}},
			{ header: '事件内容', width:200,dataIndex: 'content', sortable: true },
			{ header: '事件ID', width:200,dataIndex: 'eventid', hidden: true },
			{ header: '事件报告',  width:100, dataIndex: 'report', sortable: true , renderer:function(value){
					if(value == 1){
						return "未报告";
					}else if(value==2){
						return "报告";
					}else{
						return "未报告";
					}
			}},
			{ header: '处理结果',  width:100, dataIndex: 'result', sortable: true , renderer:function(value){
					if(value == 1){
						return "成功 ";
					}else if(value==2){
						return "失败";
					}else{
						return "";
					}
			}},
			{ header: '流水号',  width:100, dataIndex: 'seq', hidden: true },
			{ header: '原始数据包',  width:100, dataIndex: 'data', hidden: true },
			{ header: '创建时间',  width:200, dataIndex: 'createtime', sortable: true }
			
	],
			
			dockedItems: [
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                      {
								        xtype : 'textfield',
								        width : 130,
								        maxLength : 20,
								        labelWidth: 55,
										labelAlign: 'left',
								        id : 's_content',
								        fieldLabel : '事件内容'
									},{  
										
										xtype : 'combo',
										fieldLabel : '车牌号',
										width: 120,
										labelWidth: 40,
										id : 's_carnumber',
										labelAlign: 'right',
										store : 'CarPageListStore',//CarPageListStore    CarNumberStore
										displayField : 'carnumber',
										valueField : 'carnumber',
										pageSize : 10,
										minChars:1,
										matchFieldWidth:false,
										listConfig :{
											width:235
										}
							         }, {
											xtype : 'combo',
											width : 130,
											maxLength : 20,
											id : 's_opertype',
											store :"OperTypeStore",
											editable: false,
											valueField : 'id',
											displayField : 'opertype',
											fieldLabel : '操作类型',
											labelWidth: 60,
											labelAlign: 'right'
										},{
											xtype : 'datetimefield',
											width :130,
											id : 's_stime',
											maxLength : 20,
											fieldLabel : '开始时间',
											labelWidth: 60,
											labelAlign: 'right'
										},{
											xtype : 'datetimefield',
											width : 130,
											id : 's_etime',
											maxLength : 20,
											fieldLabel : '结束时间',
											labelWidth: 60,
											labelAlign: 'right'
										}, "->",{
										xtype: 'button',
										text : '查询',
										id : 'mailset_query_sendrecord',
										tooltip : '查询',
										iconCls : 'common-search-icon',
										action: 'searchsend'
									}, {
									    text : '重置',
									    tooltip : '清空查询条件',
									    iconCls : 'common-reset-icon',
									    //action : 'resetsend',
								        	handler: function(button){
								        	Ext.getCmp('s_content').setValue("");
								        	Ext.getCmp('s_carnumber').setValue("");
								        	Ext.getCmp('s_opertype').setValue("");
								        	Ext.getCmp('s_stime').setValue("");
								        	Ext.getCmp('s_etime').setValue("");
								        }
									}
			                  ]
			              }
			          ],

			
			bbar : {
				xtype : 'pagingtoolbar',
				store: "SendRecordListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
	}]
});



