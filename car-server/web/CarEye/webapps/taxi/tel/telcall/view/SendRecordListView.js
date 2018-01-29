Ext.define('TelCallApp.view.SendRecordListView' ,{
	extend : 'Ext.window.Window',
    border : false,
    width : 800,
    height : 520,
    id : 'sendrecordgrid',
    alias : 'widget.sendRecordListView',
    title: '电话回拨记录列表',
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
			{ header: '企业名称',  width:100, dataIndex: 'deptid', hidden: true },
			{ header: '企业名称',  width:100, dataIndex: 'deptname', sortable: true },
			{ header: '用户ID',  width:100, dataIndex: 'userid', hidden: true },
			{ header: '用户名',  width:100, dataIndex: 'username', sortable: true },
			{ header: '车牌号',  width:70, dataIndex: 'carnumber', sortable: true },
			{ header: '标志',  width:70, dataIndex: 'flag', sortable: true , renderer:function(value){
				if(value == 0){
					return "普通通话 ";
				}else {
					return "监听";
				}
			}},
			{ header: '电话号码', width:100,dataIndex: 'tel', sortable: true },
			{ header: '呼叫类型',  width:100, dataIndex: 'calltype', sortable: true , renderer:function(value){
					if(value == 1){
						return "呼入 ";
					}else if(value==2){
						return "呼出";
					}else{
					    return "呼入/呼出";
					}
			}},
			
			{ header: '回拨消息ID',  width:100, dataIndex: 'msgid', hidden: true },
			{ header: '流水号',  width:100, dataIndex: 'seq', hidden: true },
			{ header: '处理结果',  width:80, dataIndex: 'result', sortable: true , renderer:function(value){
				if(value == 1){
					return "成功";
				}else if(value==2){
					return "失败";
				}else{
					return "";
				}
			}},
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
								        id : 's_tel',
								        fieldLabel : '电话号码'
									},{  
									 	xtype : 'combo',
										fieldLabel : '车牌号',
										width: 130,
										labelWidth: 40,
										id : 's_carnumber',
										itemId : 'carnumber',
										labelAlign: 'right',
										store : 'CarPageListStore',
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
											width : 120,
											maxLength : 20,
											id : 's_flag',
											store :"FlagStore",
											editable: false,
											valueField : 'id',
											displayField : 'flag',
											fieldLabel : '标志',
											labelWidth: 30,
											labelAlign: 'right'
										},{
											xtype : 'datetimefield',
											width :135,
											id : 's_stime',
											maxLength : 20,
											fieldLabel : '开始时间',
											labelWidth: 60,
											labelAlign: 'right'
										},{
											xtype : 'datetimefield',
											width : 135,
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
									    action : 'resetsend',
								        	handler: function(button){
								        	Ext.getCmp('s_tel').setValue("");
								        	Ext.getCmp('s_carnumber').setValue("");
								        	Ext.getCmp('s_flag').setValue("");
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



