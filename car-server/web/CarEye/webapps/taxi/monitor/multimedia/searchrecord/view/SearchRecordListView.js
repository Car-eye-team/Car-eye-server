Ext.define('SearchRecordApp.view.SearchRecordListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.multiMediaListView',
			title: '多媒体下发记录列表',
			region: "center",
   			border: true,
			frame: true,
			store: "SearchRecordListStore",
			multiSelect: true,
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			//selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			new Ext.grid.RowNumberer(), 
			{ header: 'ID', flex: 1, dataIndex: 'id', hidden: true },
			{ header: '企业名称Id', flex: 1, dataIndex: 'blocid', hidden: true },
			{ header: '企业名称', flex: 1.5, dataIndex: 'blocname', sortable: true },
			{ header: '用户ID', flex: 1, dataIndex: 'userid', hidden: true },
			{ header: '终端号码', flex: 1, dataIndex: 'terminal', sortable: true },
			{ header: '车牌号', flex: 1, dataIndex: 'carnumber', sortable: true },
			{ header: '多媒体数据ID', flex: 1, dataIndex: 'dataid', hidden: true },
			
			{ header: '多媒体类型',  flex: 1, dataIndex: 'mediatype', sortable: true , renderer:function(value,cellmeta,record,rowIndex,columnIndex,store){
					if(value == 0){
						var src = record.get('multimediapath');
						if(src.length > 0){
							return "图像 &nbsp<a target='_blank' href='"+src+"'>查看</a>";
						}
						return "图像";
					}else if(value == 1){
						return "音频";
					}else if(value == 2){
						return "视频";
					}else{
						return "";
					}
			}},
			
			{ header: '格式',  flex: 1, dataIndex: 'format', sortable: true , renderer:function(value){
					if(value == 0){
						return "JPEG";
					}else if(value == 1){
						return "TIF";
					}else if(value == 2){
						return "MP3";
					}else if(value == 3){
						return "WAV";
					}else if(value == 4){
						return "WMV";
					}else{
						return "";
					}
			}},
			
			{ header: '事件项编码',  flex: 1, dataIndex: 'eventcode', sortable: true , renderer:function(value){
					if(value == 0){
						return "平台下发指令";
					}else if(value == 1){
						return "定时动作";
					}else if(value == 2){
						return "抢劫报警触发";
					}else if(value == 3){
						return "碰撞侧翻报警触发";
					}else if(value == 4){
						return "司机上班签到";
					}else if(value == 5){
						return "司机下班签退";
					}else if(value == 6){
						return "空车转重车";
					}else if(value == 7){
						return "重车转空车";
					}else{
						return "";
					}
			}},
			{ header: '通道ID', flex: 1, dataIndex: 'accessid', sortable: true },
			{ header: '处理结果', flex: 1, dataIndex: 'result', sortable: true ,renderer:function(value){
					if(value == 1){
						return "成功";
					}else if(value == 2){
						return "失败";
					}else{
						return "";
					}
			}},
			{ header: '流水号', flex: 1, dataIndex: 'seq', sortable: true },
			{ header: '创建时间', flex:2, dataIndex: 'createtime',sortable: true }
			],
			tbar:[{
				text:'Excel导出',
                iconCls:'common-excel-icon',
                action : 'export'            
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "SearchRecordListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

