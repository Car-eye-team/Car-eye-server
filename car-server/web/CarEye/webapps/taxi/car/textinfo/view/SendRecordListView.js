Ext.define('TextInfoApp.view.SendRecordListView' ,{
	extend : 'Ext.window.Window',
    border : false,
    width : 800,
    height : 520,
    id : 'sendrecordgrid',
    alias : 'widget.sendRecordListView',
    title: '消息发送记录列表',
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
		    store: 'SendRecordListStore',
//		    autoWidth: true,
//			autoHeight : true,
    		height : 490,
			frame: true,
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			columns: [
			{ header: 'id',  width:100, dataIndex: 'id', hidden: true },
			{ header: '企业名称',  width:100, dataIndex: 'blocid', hidden: true },
			{ header: '企业名称',  width:100, dataIndex: 'blocname', sortable: true },
			{ header: '用户ID',  width:100, dataIndex: 'userid', hidden: true },
			{ header: '用户名',  width:100, dataIndex: 'username', sortable: true },
			{ header: '车牌号',  width:70, dataIndex: 'carnumber', sortable: true },
			{ header: '操作类型',  width:70, dataIndex: 'opertype', sortable: true , renderer:function(value){
				if(value == 1){
					return "下发 ";
				}else if(value==2){
					return "删除";
				}
			}},
			{ header: '信息显示时长(分)', width:100,dataIndex: 'time', sortable: true },
			{ header: '文本信息内容', width:200,dataIndex: 'content', sortable: true },
			{ header: '紧急',  width:50, dataIndex: 'emergency', sortable: true , renderer:function(value){
					if(value == 1){
						return "是 ";
					}else{
						return "否";
					}
			}},
			{ header: '终端显示器显示',  width:100, dataIndex: 'lcd', sortable: true, renderer:function(value){
					if(value == 1){
						return "是 ";
					}else{
						return "否";
					}
			}},
			{ header: '终端TTS播读',  width:100, dataIndex: 'tts', sortable: true , renderer:function(value){
					if(value == 1){
						return "是 ";
					}else{
						return "否";
					}
			}},
			{ header: '广告屏显示',  width:100, dataIndex: 'adv', sortable: true , renderer:function(value){
					if(value == 1){
						return "是 ";
					}else{
						return "否";
					}
			}},
			{ header: '电召消息',  width:80, dataIndex: 'action', sortable: true , renderer:function(value){
					if(value == 1){
						return "是 ";
					}else{
						return "否";
					}
			}},
			{ header: '带经纬度',  width:80, dataIndex: 'dist', sortable: true , renderer:function(value){
					if(value == 1){
						return "是 ";
					}else{
						return "否";
					}
			}},
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
								        id : 's_content',
								        fieldLabel : '消息内容'
									},{  
										xtype : 'combo',
										fieldLabel : '车牌号',
										width: 130,
										labelWidth: 40,
										id : 's_carnumber',
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
									    action : 'resetsend',
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



