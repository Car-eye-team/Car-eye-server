Ext.define('EventApp.view.EventListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id : 'eventgrid',
    alias : 'widget.eventListView',
    title: '事件列表',
			region: "center",
			frame: true,
			store: "EventListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'id',  flex:1, dataIndex: 'id', hidden: true },
			{ header: '企业名称',  flex:1, dataIndex: 'blocid', hidden: true },
			{ header: '用户ID',  flex:1, dataIndex: 'userid', hidden: true },
			{ header: '事件内容', flex:3,dataIndex: 'content', sortable: true },
			{ header: '创建时间',  flex:2, dataIndex: 'createtime', sortable: true }
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
										labelAlign: 'right',
								        labelWidth: 80,
								        id : 'c_content',
								        fieldLabel : '事件内容'
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
									    //action : 'resettext',
								        	handler: function(button){
								        	Ext.getCmp('c_content').setValue("");
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
								        id: '14040501',
								        tooltip:'添加事件信息',
								        iconCls:'add',
								        action : 'add'
								    },'-',{
								        text:'修改',
								        id: '14040502',
								        tooltip:'保存事件信息',
								        iconCls:'edit',
								        action : 'edit'
								    },'-',{
								        text:'删除',
								        id: '14040503',
								        tooltip:'删除事件信息',
								        iconCls:'delete',
								        action : 'delete'
								    }
			                  ]
			              }
			          ],

			
			bbar : {
				xtype : 'pagingtoolbar',
				store: "EventListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});



