Ext.define('DispatcherRecordApp.view.DispatcherRecordListView' ,{
		    extend: 'Ext.grid.Panel',
		    id : 'dispatcherRecordgrid',
		    alias : 'widget.dispatcherRecordListView',
		    title: '调度记录信息列表',
			region: "center",
   			border: true,
			frame: true,
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			store: "DispatcherRecordListStore",
			multiSelect: true,
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'No',xtype: 'rownumberer',sortable: false},
			{ header: 'id', flex:1, dataIndex: 'id', hidden: true },
			{ header: '车牌号', flex:1, dataIndex: 'carnumber'},
			{ header: '终端设备号', flex:1, dataIndex: 'terminal'},
			{ header: '企业', flex:1, dataIndex: 'blocname'},
			{ header: '状态', flex:1, dataIndex: 'status',
			renderer:function(value){
					if(value == 1){
						return "<span style='color:green'>成功</span>";
					}else if(value==2){
						return "<span style='color:red'>失败</span>";
					}else{
					    return "";
					 }
			}},
			{ header: '紧急', width:50, dataIndex: 'emergency',
			renderer:function(value){
					if(value == 1){
						return "<span style='color:blue'>是</span>";
					}else if(value==0){
						return "否";
					}else{
					    return "";
					 }
			}},
			{ header: '终端显示器显示', width:100, dataIndex: 'lcd',
			renderer:function(value){
					if(value == 1){
						return "<span style='color:blue'>是</span>";
					}else if(value==0){
						return "否";
					}else{
					    return "";
					 }
			}},
			{ header: '终端TTS播读', width:90, dataIndex: 'tts',
			renderer:function(value){
					if(value == 1){
						return "<span style='color:blue'>是</span>";
					}else if(value==0){
						return "否";
					}else{
					    return "";
					 }
			}},
			{ header: '广告屏显示', width:80, dataIndex: 'adv',
			renderer:function(value){
					if(value == 1){
						return "<span style='color:blue'>是</span>";
					}else if(value==0){
						return "否";
					}else{
					    return "";
					 }
			}},
			{ header: '电召消息', width:60, dataIndex: 'action',
			renderer:function(value){
					if(value == 1){
						return "<span style='color:blue'>是</span>";
					}else if(value==0){
						return "否";
					}else{
					    return "";
					 }
			}},
			{ header: '备注',  flex:1, dataIndex: 'remark' },
			{ header: '创建人',  flex:1.5, dataIndex: 'username' },
			{ header: '创建时间', flex:1.5, dataIndex: 'createtime', sortable: true }
			],
			dockedItems: [
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
									 {
						                text:'导出',
						                id:'14040201',
						                tooltip:'Excel导出',
						                iconCls:'common-excel-icon',
						                action : 'export'
						            }
			                  ]
			              }
			          ],

			
			bbar : {
				xtype : 'pagingtoolbar',
				store: "DispatcherRecordListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});



