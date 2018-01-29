Ext.define('TextInfoApp.view.TextInfoListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id : 'textinfogrid',
    alias : 'widget.textInfoListView',
    title: '消息列表',
			region: "center",
			frame: true,
			store: "TextInfoListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'id',  width:100, dataIndex: 'id', hidden: true },
			{ header: '企业名称',  width:100, dataIndex: 'blocid', hidden: true },
			{ header: '用户ID',  width:100, dataIndex: 'userid', hidden: true },
			{ header: '信息类型id',  width:100, dataIndex: 'textinfotype', hidden: true },
			{ header: '信息类型',  width:80, dataIndex: 'textinfoname', sortable: true },
			{ header: '信息显示时长(分)', width:100,dataIndex: 'time', sortable: true },
			{ header: '文本信息内容', width:200,dataIndex: 'content', sortable: true },
			{ header: '紧急',  width:100, dataIndex: 'emergency', sortable: true , renderer:function(value){
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
			{ header: 'CAN故障码信息',  width:100, dataIndex: 'action', sortable: true , renderer:function(value){
					if(value == 1){
						return "是 ";
					}else{
						return "否";
					}
			}},
			{ header: '带经纬度',  width:100, dataIndex: 'dist', sortable: true , renderer:function(value){
					if(value == 1){
						return "是 ";
					}else{
						return "否";
					}
			}},
			{ header: '创建人',  width:100, dataIndex: 'username', sortable: true },
			{ header: '创建时间',  width:100, dataIndex: 'createtime', sortable: true }
			],
			
			
			dockedItems: [
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                       {  
							            xtype : 'combo',
										fieldLabel : '消息类型',
										 width : 150,
								        labelWidth : 55,
										name : 'textInfo.textinfotype',
										id : 'c_textinfotype',
										store : 'TextInfoTypeStore',
										labelAlign: 'right',
										valueField : 'textinfotype',
										displayField : 'textinfoname'
							         },{
								        xtype : 'textfield',
								        width : 200,
								        maxLength : 20,
										labelAlign: 'right',
								        labelWidth: 60,
								        id : 'c_content',
								        fieldLabel : '消息内容'
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
								        	Ext.getCmp('c_content').setValue("");
								        	Ext.getCmp('c_textinfotype').setValue("");
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
								        id: '14040901',
								        tooltip:'添加文本信息',
								        iconCls:'add',
								        action : 'add'
								    },'-',{
								        text:'修改',
								        id: '14040902',
								        tooltip:'保存文本信息',
								        iconCls:'edit',
								        action : 'edit'
								    },'-',{
								        text:'删除',
								        id: '14040903',
								        tooltip:'删除文本信息',
								        iconCls:'delete',
								        action : 'delete'
								    }
			                  ]
			              }
			          ],

			
			bbar : {
				xtype : 'pagingtoolbar',
				store: "TextInfoListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});



