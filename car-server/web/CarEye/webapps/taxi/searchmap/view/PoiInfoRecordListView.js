Ext.define('SearchMapApp.view.PoiInfoRecordListView' ,{
	extend : 'Ext.window.Window',
    alias : 'widget.poiInfoRecordListView',
	title: 'POI发送记录列表',
    width : 700,
    height : 400,
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
    		height : 370,
			frame: true,
			id : 'prt',
			store: "PoiInfoRecordListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'ID', flex: 1, dataIndex: 'id', hidden: true },
			{ header: '企业名称', flex: 1, dataIndex: 'blocname', sortable: true },
			{ header: '用户ID', flex: 1, dataIndex: 'userid', hidden: true },
			{ header: '车牌号', flex: 1, dataIndex: 'carnumber', sortable: true },
			
			{ header: '经度', flex: 1, dataIndex: 'lng', sortable: true },
			{ header: '纬度', flex: 1, dataIndex: 'lat', sortable: true },
			{ header: 'POI名称', flex: 2, dataIndex: 'poiname', sortable: true },
			
			{ header: '处理结果', flex: 1, dataIndex: 'result', sortable: true , renderer:function(value){
					if(value == 1){
						return "成功";
					}else if(value==2) {
						return "失败";
					}else{
					    return "";
					}
			}},
			{ header: '发送时间', flex: 2, dataIndex: 'createtime' }
			],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "PoiInfoRecordListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
			,
			dockedItems: [
					{
					    xtype: 'toolbar',
					    dock: 'top',
					    items: [
								{
							        xtype : 'combo',
									width : 170,
									fieldLabel : '车牌号码',
									labelWidth: 60,
									id:'record_carnumber',
									labelAlign: 'right',
									store : 'CarListStore',
									displayField : 'carnumber',
									valueField : 'carnumber'
								},{
									xtype : 'datetimefield',
									width : 200,
									maxLength : 20,
									id : 'record_stime',
									fieldLabel : '开始时间',
									labelWidth: 80,
									labelAlign: 'right'
								},{
									xtype : 'datetimefield',
									width : 200,
									maxLength : 20,
									id : 'record_etime',
									fieldLabel : '结束时间',
									labelWidth: 80,
									labelAlign: 'right'
								},{
											xtype: 'button',
											text : '查询',
											tooltip : '查询',
											iconCls : 'common-search-icon',
											action: 'searchrecord'
								}
						    ]
						},{
							xtype : 'toolbar',
							dock : 'top',
							layout: {
							     overflowHandler: 'Menu'
							    },
							items : [  {
										text : '删除',
										id : '',
										tooltip : '删除POI发送记录',
										iconCls : 'delete',
										action : 'delete'
						            }]
						}
			          ]
	} ]
});