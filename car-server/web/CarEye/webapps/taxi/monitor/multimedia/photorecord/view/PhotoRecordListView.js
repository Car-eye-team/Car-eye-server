Ext.define('PhotoRecordApp.view.PhotoRecordListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.photoRecordListView',
			title: '拍摄记录列表',
			region: "center",
   			border: true,
			frame: true,
			store: "PhotoRecordListStore",
			multiSelect: true,
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
//			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			new Ext.grid.RowNumberer(), 
			{ header: 'ID', width:100, dataIndex: 'id', hidden: true },
			{ header: '企业', width:160, dataIndex: 'blocname', sortable: true },
			{ header: '终端号码', width:100, dataIndex: 'terminal', sortable: true },
			{ header: '车牌号', width:100, dataIndex: 'carnumber', sortable: true },
			{ header: '拍摄命令', width:100, dataIndex: 'command', sortable: true, renderer:function(value,cellmeta,record,rowIndex,columnIndex,store){
					if(value == 0){
						return "停止拍摄";
					}else if(value == 65535){
						return "录像";
					}else{
						return value + "张";
//						var id = record.get('id');
//						return value + "张&nbsp<a href='javascript:void(0);' onclick=showPic("+id+","+value+");>查看</a>";
					}
			}},
			{ header: '拍照间隔/录像时间', width:110, dataIndex: 'pstime', sortable: true },
			{ header: '保存标志', width:100, dataIndex: 'saveflag', sortable: true, renderer:function(value){
					if(value == 0){
						return "实时上传";
					}else if(value == 1){
						return "保存";
					}else{
						return "";
					}
			}},
			{ header: '分辨率',  width:100, dataIndex: 'resolutionratio', sortable: true , renderer:function(value){
					if(value == 1){
						return "320*210";
					}else if(value == 2){
						return "640*480";
					}else if(value == 3){
						return "800*600";
					}else if(value == 4){
						return "1024*768";
					}else if(value == 5){
						return "176*144";
					}else if(value == 6){
						return "352*288";
					}else if(value == 7){
						return "704*288";
					}else if(value == 8){
						return "701*576";
					}else{
						return "";
					}
			}},
			{ header: '图像/视频质量', width:100, dataIndex: 'picturequality', sortable: true },
			{ header: '亮度', width:100, dataIndex: 'lighteness', sortable: true },
			{ header: '对比度', width:100, dataIndex: 'contrast', sortable: true },
			{ header: '饱和度', width:100, dataIndex: 'saturation', sortable: true },
			{ header: '色度', width:100, dataIndex: 'chroma', sortable: true },
			{ header: '处理结果', width:100, dataIndex: 'result', sortable: true ,renderer:function(value){
					if(value == 1){
						return "成功";
					}else if(value == 2){
						return "失败";
					}else{
						return "";
					}
			}},
			{ header: '创建时间', width:150, dataIndex: 'createtime',sortable: true }
			],
          tbar:[{
				text:'Excel导出',
//				id: '130104',
                iconCls:'common-excel-icon',
                action : 'export'            
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "PhotoRecordListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

function showPic(id,value){
	var control = Ext.create('PhotoRecordApp.controller.PhotoRecordCtrl');
	control.showPic(id,value);
}

