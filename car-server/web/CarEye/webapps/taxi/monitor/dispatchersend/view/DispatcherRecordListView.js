Ext.define('DispatcherSendApp.view.DispatcherRecordListView' ,{
		    extend: 'Ext.grid.Panel',
		    id : 'dispatcherRecordgrid',
		    alias : 'widget.dispatcherRecordListView',
		    title: '调度信息',
			region: "center",
   			border: true,
			frame: true,
			multiSelect: false,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			store: "DispatcherRecordListStore",
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
            selModel: { selType: 'checkboxmodel',mode: 'SINGLE',
            listeners :{
                select : function(rowmodel,select){
	                var grid=Ext.getCmp('dispatcherRecordgrid');
					var records = grid.getSelectionModel().getSelection();
					var view = Ext.widget('dispatcherSendgrid');
					var store = Ext.StoreManager.get('DispatcherRecordListStore');
					var data = store.getById(records[0].get('id'));
					Ext.getCmp('id').setValue(data.data.id);
					Ext.getCmp('remark').setValue(data.data.remark);
					Ext.getCmp('emergency').setValue(data.data.emergency==1?true:false);
					Ext.getCmp('lcd').setValue(data.data.lcd==1?true:false);
					Ext.getCmp('tts').setValue(data.data.tts==1?true:false);
					Ext.getCmp('adv').setValue(data.data.adv==1?true:false);
					Ext.getCmp('action').setValue(data.data.action==1?true:false);
					
					view.down('form').loadRecord(data);
					return false;
				},
				deselect: function(model,record,index) {//取消选中时产生的事件
					Ext.getCmp('id').setValue('');
					Ext.getCmp('remark').setValue('');
					Ext.getCmp('emergency').setValue('');
					Ext.getCmp('lcd').setValue('');
					Ext.getCmp('tts').setValue('');
					Ext.getCmp('adv').setValue('');
					Ext.getCmp('action').setValue('');
                }
    }}, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'No',xtype: 'rownumberer',sortable: false},
			{ header: 'id', flex:1, dataIndex: 'id', hidden: true },
			{ header: '发送内容', width:70, dataIndex: 'remark'},
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
			{ header: '创建人',  width:80, dataIndex: 'username' },
			{ header: '创建时间', width:130, dataIndex: 'createtime', sortable: true }
			],
			dockedItems: [
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
//									 {
//						                text:'导出',
//						                id:'14040201',
//						                tooltip:'Excel导出',
//						                iconCls:'common-excel-icon',
//						                action : 'export'
//						            }
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


