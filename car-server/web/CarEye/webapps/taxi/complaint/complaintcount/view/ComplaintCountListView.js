Ext.define('ComplaintCountApp.view.ComplaintCountListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.complaintCountListView',
			title: '投诉统计列表--双击查看日期投诉明细',
			autoWidth: true,
			autoHeight : true,
			region: "center",
			border: true,
			frame: true,
			store: "ComplaintCountListStore",
			multiSelect: true,
			viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
//			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: '日期', flex:1, dataIndex: 'datetime', sortable: true },
//			{ header: '公司ID', flex:0.5, dataIndex: 'blocid', hidden: true },
			{ header: '公司名称', flex:1, dataIndex: 'blocname', sortable: true },
			{ header: '投诉数', flex:1, dataIndex: 'count',sortable: true }
			],
			listeners:{
				
			    //双击单元格事件 
			    celldblclick: function(table, td, cellIndex, model, tr, rowIndex, e, eOpts){
				var datetime = model.data.datetime;
				var blocid = Ext.getCmp('tmp_blocid').getValue();
				var carnumber = Ext.getCmp('tmp_carnumber').getValue();
				
				var view = Ext.create('ComplaintCountApp.view.ComplaintCountDetailsWindow');
				view.show();
				
				var store = Ext.StoreManager.get("ComplaintCountDetailsStore");
				store.clearFilter(true);
				store.on('beforeload', function (store, options) {
			            var new_params = { 
			            	blocid: encodeURI(blocid),
			            	carnumber: encodeURI(carnumber),
				            datetime: encodeURI(datetime)
			            };
			            Ext.apply(store.proxy.extraParams, new_params);
			        });
			        store.loadPage(1);
			        
			    return false;
				
	   			}
			},
            tbar:[{
                text:'Excel导出',
                id: '',
                tooltip:'导出投诉',
                iconCls:'common-excel-icon',
                action : 'export'
            },{
                text:'Word导出',
                id: '',
                tooltip:'导出word文档格式',
                iconCls:'common-word-icon',
                action : 'exportWord'
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "ComplaintCountListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

