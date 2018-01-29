Ext.define('QuestionApp.view.QuestionListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id : 'questiongrid',
    alias : 'widget.questionListView',
    title: '问题列表',
			region: "center",
			frame: true,
			store: "QuestionListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'id',  flex:1, dataIndex: 'id', hidden: true },
			{ header: '企业名称',  flex:1, dataIndex: 'blocid', hidden: true },
			{ header: '用户ID',  flex:1, dataIndex: 'userid', hidden: true },
			{ header: '提问内容', flex:3,dataIndex: 'content', sortable: true },
			{ header: '紧急',  flex:1, dataIndex: 'emergency', sortable: true , renderer:function(value){
					if(value == 1){
						return "是 ";
					}else{
						return "否";
					}
			}},
			{ header: '终端TTS播读',  flex:1, dataIndex: 'tts', sortable: true , renderer:function(value){
					if(value == 1){
						return "是 ";
					}else{
						return "否";
					}
			}},
			{ header: '广告屏显示',  flex:1, dataIndex: 'adv', sortable: true , renderer:function(value){
					if(value == 1){
						return "是 ";
					}else{
						return "否";
					}
			}},
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
								        labelWidth: 80,
										labelAlign: 'right',
								        id : 'c_content',
								        fieldLabel : '问题内容'
									}, "->",{
										xtype: 'button',
										text : '查询',
										id : 'mailset_query_ques',
										tooltip : '查询',
										iconCls : 'common-search-icon',
										action: 'searchques'
									}, {
									    text : '重置',
									    tooltip : '清空查询条件',
									    iconCls : 'common-reset-icon',
									    //action : 'resetques',
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
								        id: '14040601',
								        tooltip:'添加问题信息',
								        iconCls:'add',
								        action : 'add'
								    },'-',{
								        text:'修改',
								        id: '14040602',
								        tooltip:'保存问题信息',
								        iconCls:'edit',
								        action : 'edit'
								    },'-',{
								        text:'删除',
								        id: '14040603',
								        tooltip:'删除问题信息',
								        iconCls:'delete',
								        action : 'delete'
								    }
			                  ]
			              }
			          ],

			
			bbar : {
				xtype : 'pagingtoolbar',
				store: "QuestionListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});



