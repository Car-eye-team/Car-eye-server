Ext.define('PassageStatisticApp.view.PassageStatisticListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.passageStatisticListView',
			title: '乘客人数统计',
			region: "center",
   			border: true,
			frame: true,
			store: "PassageStatisticStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			//selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			new Ext.grid.RowNumberer(), 
			{ header: 'id', width:60, dataIndex: 'id', hidden: true },
			{ header: '车辆id', width:60, dataIndex: 'carid', hidden: true },
			{ header: '企业id', width:60, dataIndex: 'blocid', hidden: true },
			{ header: '企业', width:150, dataIndex: 'blocname', sortable: true ,locked:true},
			{ header: '车牌号', width:100, dataIndex: 'carnumber', sortable: true ,locked:true},
			{ header: '终端号码', width:120, dataIndex: 'terminal', sortable: true ,locked:true},
			{ header: '人数',  width:60, dataIndex: 'peoplenumber', sortable: true , renderer:function(value){
					if(value != null && value != ''){
						return value+'人';
					}else{
						return value;
					}
			}},
			{ header: '计价器业务流水号', width:150, dataIndex: 'serialnumber', sortable: true},
			{ header: '地址', width:200, dataIndex: 'address', sortable: true },
			{ header: '上传时间', width:150, dataIndex: 'uploadtime', sortable: true },
			{ header: '创建时间', width:150, dataIndex: 'createtime', sortable: true }
			],
			tbar:[{
				text:'Excel导出',
                iconCls:'common-excel-icon',
                action : 'export'            
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "PassageStatisticStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

