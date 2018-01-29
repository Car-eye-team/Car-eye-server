Ext.define('AlarmmusicSetApp.view.AlarmmusicSetListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.alarmmusicSetListView',
			title: '报警类型列表',
			autoWidth: true,
			autoHeight : true,
			region: "center",
			border: true,
			frame: true,
			store: "AlarmmusicSetListStore",
			multiSelect: true,
			viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'ID', flex:0.5, dataIndex: 'id', hidden: true },
			{ header: '报警类型名称', flex:1, dataIndex: 'alarmname', sortable: true },
			{ header: '报警类型缩写', flex:1, dataIndex: 'alarmkey', sortable: true },
			{ header:'报警声音',  flex:1, dataIndex: 'musicaddr', sortable: true, 
				renderer: function(value){ 
					var musicaddr = $("#basepath").val()+value;
					if(value==null||value.length==0){
					   return "";
					}else{
					   return "<a href='"+musicaddr+"'>下载</a>";  
					}
				}
		    },
			{ header: '备注', flex:1, dataIndex: 'remark', sortable: true },
			{ header: '创建时间', flex:1, dataIndex: 'createtime',sortable: true }
			],
            tbar:[{
                text:'添加',
                id: '12040201',
                tooltip:'添加报警类型设置信息',
                iconCls:'add',
                action : 'add'
            }, {
                text:'修改',
                id: '12040202',
                tooltip:'修改报警类型设置信息',
                iconCls:'edit',
                action : 'edit'
            },{
                text:'删除',
                id: '12040203',
                tooltip:'删除报警类型设置信息',
                iconCls:'remove',
                action : 'delete'
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "AlarmmusicSetListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

