Ext.define('TelBookApp.view.TelBookCarListView' ,{
	extend : 'Ext.window.Window',
    border : false,
    width : 800,
    height : 520,
    id : 'telbookcargrid',
    alias : 'widget.telbookCarListView',
    title: '车辆电话本列表',
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
			store: "TelBookCarListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'id',  width:100, dataIndex: 'cartelbookid', hidden: true },
			{ header: '企业名称',  width:100, dataIndex: 'blocid', hidden: true },
			{ header: '用户ID',  width:100, dataIndex: 'userid', hidden: true },
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
								        id : 'car_contacts',
								        fieldLabel : '联系人'
									},{
								        xtype : 'textfield',
								        width : 200,
								        maxLength : 20,
								        labelWidth: 60,
										labelAlign: 'right',
								        id : 'car_tel',
								        fieldLabel : '联系电话'
									},"->",{
										xtype: 'button',
										text : '查询',
										id : 'mailset_query_eventcar',
										tooltip : '查询',
										iconCls : 'common-search-icon',
										action: 'searchtelbookcar'
									}, {
									    text : '重置',
									    tooltip : '清空查询条件',
									    iconCls : 'common-reset-icon',
									    action : 'reseteventcar',
								        	handler: function(button){
								        	Ext.getCmp('car_contacts').setValue("");
								        	Ext.getCmp('car_tel').setValue("");
								        }
									}
			                  ]
			              },
			             
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                     {
							        text:'更新联系人',
							        tooltip:'保存联系人信息',
							        iconCls:'edit',
							        action : 'editcar'
							    },'-',{
							        text:'修改联系人',
							        tooltip:'修改联系人信息',
							        iconCls:'edit',
							        action : 'deletecar'
							    },'-',{
							        text:'删除所有联系人',
							        tooltip:'删除联系人信息',
							        iconCls:'delete',
							        action : 'deleteallcar'
							    }
			                  ]
			              }
			          ],

			bbar : {
				xtype : 'pagingtoolbar',
				store: "TelBookCarListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
	}]
});



