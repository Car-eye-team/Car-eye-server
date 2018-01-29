Ext.define('TelCallApp.view.TelCallListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id : 'telcallgrid',
    alias : 'widget.telcallListView',
    title: '电话回拨列表',
			region: "center",
			frame: true,
			store: "TelCallListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'id',  flex:1, dataIndex: 'id', hidden: true },
			{ header: '企业名称',  flex:1.5, dataIndex: 'blocid', hidden: true },
			{ header: '用户ID',  flex:1, dataIndex: 'userid', hidden: true },
			{ header: '电话号码', flex:1.5,dataIndex: 'tel', sortable: true },
			{ header: '标志',  flex:1, dataIndex: 'flag', sortable: true , renderer:function(value){
					if(value == 0){
						return "普通通话 ";
					}else{
						return "监听";
					}
			}},
			{ header: '呼叫类型', flex:1, dataIndex: 'calltype', sortable: true, renderer:function(value){
					if(value == 1){
						return "呼入 ";
					}else if(value==2){
						return "呼出";
					}else{
					    return "呼入/呼出";
					}
			}},
			
			{ header: '备注',  flex:1, dataIndex: 'remark', sortable: true },
			{ header: '创建时间',  flex:1.5, dataIndex: 'createtime', sortable: true }
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
								        id : 'c_tel',
								        fieldLabel : '电话号码'
									}, "->",{
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
								        id: '14040301',
								        tooltip:'添加电话回拨信息',
								        iconCls:'add',
								        action : 'add'
								    },'-',{
								        text:'修改',
								        id: '14040302',
								        tooltip:'保存电话回拨信息',
								        iconCls:'edit',
								        action : 'edit'
								    },'-',{
								        text:'删除',
								        id: '14040303',
								        tooltip:'删除电话回拨信息',
								        iconCls:'delete',
								        action : 'delete'
								    }
			                  ]
			              }
			          ],

			
			bbar : {
				xtype : 'pagingtoolbar',
				store: "TelCallListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});



