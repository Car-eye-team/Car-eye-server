Ext.define('AreaSetApp.view.AreaCarListView' ,{
	extend : 'Ext.window.Window',
	alias : 'widget.areaCarListView',
	title : '车辆区域列表',
    width : 800,
    height : 520,
	layout : 'form',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',
	items : [ {
		    xtype: 'grid',
		    autoWidth: true,
			autoHeight : true,
			minHeight:490,
			frame: true,
			store: "AreaCarListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'id', width:30 ,dataIndex: 'id', hidden: true },
			{ header: '车牌号码', width:70 ,dataIndex: 'carnumber', sortable: true },
			{ header: '区域类型', width:70, dataIndex: 'areatype', sortable: true ,renderer : function(value){
				if(value ==1){
					return "圆形区域";
				}else if(value ==2){
					return "矩形区域";
				}else if(value ==3){
					return "多边形区域";
				}
			}},
			{ header: '区域名称', width:70, dataIndex: 'areaname', sortable: true },
			{ header: '最高速度(km/h)', width:100, dataIndex: 'maxspeed', sortable: true },
			{ header: '超速持续时间(秒)', width:100, dataIndex: 'speedtime', sortable: true },
			{ header: '起始时间', width:130, dataIndex: 'stime', sortable: true },
			{ header: '结束时间', width:130, dataIndex: 'etime', sortable: true },
			{ header: '创建时间', width:130, dataIndex: 'createtime', sortable: true }
			],
			tbar:[{
                text:'更新区域',
                tooltip:'更新区域',
                iconCls:'edit',
                action : 'edit'
			},{
                text:'删除区域',
                tooltip:'删除区域',
                iconCls:'delete',
                action : 'delete'
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "AreaCarListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
	} ]
});

