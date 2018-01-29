Ext.define('AreaSetApp.view.AreaSendRecordListView' ,{
	extend : 'Ext.window.Window',
    border : false,
    width : 800,
    height : 520,
    alias : 'widget.areaSendRecordListView',
    title: '区域下发记录列表',
	layout : 'form',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	closeAction : 'destroy',
	items : [ {
		    xtype: 'grid',
		    autoWidth: true,
			autoHeight : true,
    		height : 490,
			frame: true,
			store: "AreaSendRecordListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			columns: [
			{ header: 'id',  width:100, dataIndex: 'id', hidden: true },
			{ header: '企业',  width:100, dataIndex: 'blocname', sortable: true },
			{ header: '车牌号',  width:100, dataIndex: 'carnumber', sortable: true },
			{ header: '操作类型',  width:100, dataIndex: 'opertype', sortable: true , renderer:function(value){
				if(value == 1){
					return "追加 ";
				}else if(value==2){
					return "更新";
				}else if(value==3){
					return "删除";
				}else {
					return "";
				}
			}},
			{ header: '区域类型', width:70, dataIndex: 'areatype', sortable: true ,renderer : function(value){
				if(value ==1){
					return "圆形区域";
				}else if(value ==2){
					return "矩形区域";
				}else if(value ==3){
					return "多边形区域";
				}
			}},
			{ header: '区域名称', width:200,dataIndex: 'areaname', sortable: true },
			{ header: '根据时间报警', width:100, dataIndex: 'attr0', sortable: true,renderer :function(value){
				if(value == 1){
					return "是";
				}
				return "否";
			}},
			{ header: '限速', width:100, dataIndex: 'attr1', sortable: true,renderer :function(value){
				if(value == 1){
					return "是";
				}
				return "否";
			}},
			{ header: '进区域报警给驾驶员', width:120, dataIndex: 'attr2', sortable: true,renderer :function(value){
				if(value == 1){
					return "是";
				}
				return "否";
			}},
			{ header: '进区域报警给平台', width:120, dataIndex: 'attr3', sortable: true,renderer :function(value){
				if(value == 1){
					return "是";
				}
				return "否";
			}},
			{ header: '出区域报警给驾驶员', width:120, dataIndex: 'attr4', sortable: true ,renderer :function(value){
				if(value == 1){
					return "是";
				}
				return "否";
			}},
			{ header: '出区域报警给平台', width:120, dataIndex: 'attr5', sortable: true,renderer :function(value){
				if(value == 1){
					return "是";
				}
				return "否";
			}},
			{ header: '最高速度(km/h)', width:100, dataIndex: 'maxspeed', sortable: true },
			{ header: '超速持续时间(秒)', width:100, dataIndex: 'speedtime', sortable: true },
			{ header: '起始时间', width:120, dataIndex: 'stime', sortable: true },
			{ header: '结束时间', width:120, dataIndex: 'etime', sortable: true },
			{ header: '中心点纬度', width:120, dataIndex: 'ylat', sortable: true },
			{ header: '中心点经度', width:120, dataIndex: 'ylng', sortable: true },
			{ header: '半径', width:120, dataIndex: 'radius', sortable: true },
			{ header: '左上点纬度', width:120, dataIndex: 'latlt', sortable: true },
			{ header: '左上点经度', width:120, dataIndex: 'lnglt', sortable: true },
			{ header: '右下点纬度', width:120, dataIndex: 'latrb', sortable: true },
			{ header: '右下点经度', width:120, dataIndex: 'lngrb', sortable: true },
			{ header: '多边形多纬度', width:120, dataIndex: 'latsrt', sortable: true },
			{ header: '多边形多经度', width:120, dataIndex: 'lngsrt', sortable: true },
			{ header: '处理结果',  width:100, dataIndex: 'result', sortable: true , renderer:function(value){
					if(value == 1){
						return "成功 ";
					}else if(value==2){
						return "失败";
					}else{
						return "";
					}
			}},
			{ header: '创建时间',  width:200, dataIndex: 'createtime', sortable: true }
			
	],
			
			dockedItems: [
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                      {
								        xtype : 'textfield',
								        width : 120,
								        maxLength : 20,
								        labelWidth: 55,
										labelAlign: 'left',
								        id : 'asr_areaname',
								        fieldLabel : '区域名称'
									},{  
							            xtype : 'textfield',
										fieldLabel : '车牌号',
										width: 140,
										labelWidth: 40,
										id : 'asr_carnumber',
//										store : 'CarListStore',
										labelAlign: 'right'
//										valueField : 'carnumber',
//										displayField : 'carnumber'
							         }, {
											xtype : 'combo',
											width : 120,
											maxLength : 20,
											id : 'asr_opertype',
											store :"OperTypeStore",
											editable: false,
											valueField : 'id',
											displayField : 'opertype',
											fieldLabel : '操作类型',
											labelWidth: 55,
											labelAlign: 'right'
										},{
											xtype : 'datetimefield',
											width :150,
											id : 'asr_stime',
											maxLength : 20,
											fieldLabel : '开始时间',
											labelWidth: 60,
											labelAlign: 'right'
										},{
											xtype : 'datetimefield',
											width : 130,
											id : 'asr_etime',
											maxLength : 20,
											fieldLabel : '至',
											labelWidth: 25,
											labelAlign: 'right'
										}, "->",{
										xtype: 'button',
										text : '查询',
										id : 'mailset_query_sendrecord',
										tooltip : '查询',
										iconCls : 'common-search-icon',
										action: 'search'
									}, {
									    text : '重置',
									    tooltip : '清空查询条件',
									    iconCls : 'common-reset-icon',
//									    action : 'resetsend',
								        handler: function(button){
								        	Ext.getCmp('asr_areaname').setValue("");
								        	Ext.getCmp('asr_carnumber').setValue("");
								        	Ext.getCmp('asr_opertype').setValue("");
								        	Ext.getCmp('asr_stime').setValue("");
								        	Ext.getCmp('asr_etime').setValue("");
								        }
									}
			                  ]
			              }
			          ],

			
			bbar : {
				xtype : 'pagingtoolbar',
				store: "AreaSendRecordListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
	}]
});



