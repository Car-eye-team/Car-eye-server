Ext.define('TelBookApp.view.SendRecordListView' ,{
	extend : 'Ext.window.Window',
    border : false,
    width : 800,
    height : 520,
    id : 'sendrecordgrid',
    alias : 'widget.sendRecordListView',
    title: '电话本下发记录列表',
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
			{ header: '电话本ID',  width:100, dataIndex: 'telbookid', hidden: true },
			{ header: '车牌号',  width:100, dataIndex: 'carnumber', sortable: true },
			{ header: '联系人', width:100,dataIndex: 'contacts', sortable: true },
			{ header: '联系人电话', width:100,dataIndex: 'tel', sortable: true },
			{ header: '呼叫类型',  width:100, dataIndex: 'calltype', sortable: true, renderer:function(value){
				if(value == 1){
					return "呼入 ";
				}else if(value==2){
					return "呼出";
				}else{
				    return "呼入/呼出";
				}
			}},
			{ header: '操作类型',  width:100, dataIndex: 'opertype', sortable: true , renderer:function(value){
				if(value == 2){
					return "追加电话本 ";
				}else if(value==1){
					return "更新电话本";
				}else if(value==3){
					return "修改电话本";
				}else {
					return "删除所有";
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
			{ header: '备注',  width:200, dataIndex: 'remark', sortable: true },
			{ header: '创建时间',  width:200, dataIndex: 'createtime', sortable: true }
			
	],
			
			dockedItems: [
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                      {
								        xtype : 'textfield',
								        width : 200,
								        maxLength : 20,
								        labelWidth: 60,
										labelAlign: 'right',
								        id : 's_contacts',
								        fieldLabel : '联系人'
									},{
								        xtype : 'textfield',
								        width : 200,
								        maxLength : 20,
								        labelWidth: 60,
										labelAlign: 'right',
								        id : 's_tel',
								        fieldLabel : '联系电话'
									},{  
							            xtype : 'combo',
										fieldLabel : '操作类型',
										width: 200,
										labelWidth: 60,
										id : 's_opertype',
										store : 'OperTypeStore',
										labelAlign: 'right',
										valueField : 'id',
										displayField : 'opertype'
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
								        	Ext.getCmp('s_contacts').setValue("");
								        	Ext.getCmp('s_opertype').setValue("");
								        	Ext.getCmp('s_tel').setValue("");
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



