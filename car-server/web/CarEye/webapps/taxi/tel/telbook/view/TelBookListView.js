Ext.define('TelBookApp.view.TelBookListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id : 'telbookgrid',
    alias : 'widget.telbookListView',
    title: '电话本列表',
			region: "center",
			frame: true,
			store: "TelBookListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'id',  width:100, dataIndex: 'id', hidden: true },
			{ header: '企业名称',  width:100, dataIndex: 'blocid', hidden: true },
			{ header: '用户ID',  width:100, dataIndex: 'userid', hidden: true },
			{ header: '电话号码', width:100,dataIndex: 'tel', sortable: true },
			{ header: '联系人', width:100,dataIndex: 'contacts', sortable: true },
			{ header: '呼叫类型',  width:100, dataIndex: 'calltype', sortable: true, renderer:function(value){
				if(value == 1){
					return "呼入 ";
				}else if(value==2){
					return "呼出";
				}else{
				    return "呼入/呼出";
				}
			}},
			{ header: '备注', width:200,dataIndex: 'remark', sortable: true },
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
								        labelWidth: 80,
										labelAlign: 'right',
								        id : 'c_contacts',
								        fieldLabel : '联系人'
									},{
								        xtype : 'textfield',
								        width : 200,
								        maxLength : 20,
								        labelWidth: 80,
										labelAlign: 'right',
								        id : 'c_tel',
								        fieldLabel : '联系人电话'
									},  "->",{
										xtype: 'button',
										text : '查询',
										id : 'mailset_query_text',
										tooltip : '查询',
										iconCls : 'common-search-icon',
										action: 'searchtext'
									}, {
									    text : '重置',
									    tooltip : '清空查询条件',
									    iconCls : 'common-reset-icon',
									    action : 'resettext',
								        	handler: function(button){
								        	Ext.getCmp('c_contacts').setValue("");
								        	Ext.getCmp('c_tel').setValue("");
								        }
									}
			                  ]
			              },
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                      {
								        text:'添加',
								        id: '14040201',
								        tooltip:'添加电话本信息',
								        iconCls:'add',
								        action : 'add'
								    },'-',{
								        text:'修改',
								        id: '14040202',
								        tooltip:'保存电话本信息',
								        iconCls:'edit',
								        action : 'edit'
								    },'-',{
								        text:'删除',
								        id: '14040203',
								        tooltip:'删除电话本信息',
								        iconCls:'delete',
								        action : 'delete'
								    }
			                  ]
			              }
			          ],

			
			bbar : {
				xtype : 'pagingtoolbar',
				store: "TelBookListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});



