Ext.define('RideOrderApp.view.RideOrderListView', {
			extend : 'Ext.grid.Panel',
			border : false,
			id : 'rideorder',
			alias : 'widget.rideOrderListView',
			title : '顺风车订单列表--双击显示订单详情',
			region : 'center',
			frame : true,
			store : 'RideOrderListStore',
			multiSelect : true,
			viewConfig : {
					enableTextSelection:true,//grid中的内容能够复制  
					stripeRows : true,
					loadMask : true
					},
			stripeRows : true,//表格是否隔行换色,默认为true
			loadMask : true , // 是否在加载数据时显示遮罩效果，默认为true
			selModel : {
				selType : 'checkboxmodel'
					,
				listeners : {
					selectionchange : function(rowmodel) {
						var store = Ext.StoreManager.get('RidePassengerListStore');
						var grid = Ext.getCmp('rideorder');
						var m = grid.getSelectionModel().getSelection();
						var jsonData = "";
						for (var i = 0, len = m.length; i < len; i++) {
							var ss = m[i].get("orderid");
							if (i == 0) {
								jsonData = jsonData + ss;
							} else {
								jsonData = jsonData + "," + ss;
							}
						}
						store.clearFilter(true);
						store.on('beforeload', function(store, options) {
									var new_params = {
										ids : jsonData									
									};
									Ext.apply(store.proxy.extraParams,
											new_params);
								});
						store.loadPage(1);
					}
				}
			}, // 在首列實現checkbox，僅此在首列
			columns : [{header : 'ID', width : 70, dataIndex : 'id', hidden : true},
			           {header : '车辆id',width : 70,dataIndex : 'carid',hidden : true},
			           {header : '企业id',width : 70,dataIndex : 'blocid',hidden : true},
			           {header : '订单号',width : 120,dataIndex : 'orderid',sortable : true},
			           {header : '总费用',width : 60,dataIndex : 'totalfee',sortable : true},
			           {header : '订单状态',width : 60,dataIndex : 'ordersatus',sortable : true,
							renderer : function(value) {
								if (value == 1) {
									return "进行中"
								} else if (value == 2){
									return "完成"
								}else{
									return ""
								}
							}
			           },
			           {header : '企业名称',width : 100,dataIndex : 'bloc_name',sortable : true},
			           {header : '车牌号',width : 80,dataIndex : 'carnumber',sortable : true},
			           {header : '设备号',width : 100,dataIndex : 'terminal',sortable : true},
			           
			           {header : '起点',width : 150,dataIndex : 'saddress',sortable : true},
			           {header : '起点纬度',width : 70,dataIndex : 'slat',hidden : true},
			           {header : '起点经度',width : 70,dataIndex : 'slng',hidden : true},
			           
			           {header : '终点',width : 150,dataIndex : 'eaddress',sortable : true},
			           {header : '终点纬度',width : 70,dataIndex : 'elat',hidden : true},
			           {header : '终点经度',width : 70,dataIndex : 'elng',hidden : true},
			           
			           {header : '开始时间',width : 150,dataIndex : 'stime',sortable : true},
			           {header : '结束时间',width : 150,dataIndex : 'etime',sortable : true},
			           {header : '总用车时长',width : 100,dataIndex : 'sumtime',sortable : true},
			           {header : '总行车里程',width : 100,dataIndex : 'summile',sortable : true},
			           {header : '创建时间',width : 150,dataIndex : 'createtime',sortable : true},
			           
			           {header : '备注',width : 70,dataIndex : 'remark',hidden : true}
			           ],
			           
			           
			           listeners:{
							  itemdblclick:function(view, records, store, grid, data){
							  		var store1 = Ext.StoreManager.get('RideOrderListStore');
									var grid1 = Ext.getCmp('rideorder');
									var records = grid1.getSelectionModel().getSelection();
									var view = Ext.widget('orderDetailWindow');
									view.show();
									var data = store1.getById(records[0].get('id'));
									Ext.getCmp('orderDetailWindow').loadRecord(data);
										
										var store2= Ext.StoreManager.get('RidePassengerListStore');
										var grid2 = Ext.getCmp('rideorder');
										var m = grid2.getSelectionModel().getSelection();
										var jsonData = "";
										for (var i = 0, len = m.length; i < len; i++) {
											var ss = m[i].get("orderid");
											if (i == 0) {
												jsonData = jsonData + ss;
											} else {
												jsonData = jsonData + "," + ss;
											}
										}
										store2.clearFilter(true);
										store2.on('beforeload', function(store2, options) {
													var new_params = {
														ids : jsonData									
													};
													Ext.apply(store2.proxy.extraParams,
															new_params);
												});
										store2.loadPage(1);
									
									return false;
							       }
							  },
			           
			dockedItems : [{
						xtype : 'toolbar',
						dock : 'top',
						enableOverflow : true,//溢出隐藏
						items : [{
								xtype : 'comboboxtree',
								fieldLabel : '企业名称',
								emptyText : '请选择',
								id : 'c_deptid',
								itemId:'blocid',
								width:165,
								labelWidth : 55,
								store: Ext.create('Ext.data.TreeStore', {  
							        autoLoad : 'true',
							        fields: ['text','id','parentId'], 
									root : {expanded : true,text : '企业' },
									proxy: {
										 type: 'ajax',
										 url: window.BIZCTX_PATH + '/servlet/DeptTree?type=200', 
										 reader: {
											 type: 'json'
										 }
									}
							    }) ,
							    rootVisible: false,
								editable: true,
								cls : 'x-textfield',
								valueField: 'id', 
								displayField: 'text',
								listeners: {
							        change: {
							            element: 'el', 
							            fn: function(){ 
							            	var store = Ext.getCmp('c_deptid').store;
											store.clearFilter(true);
											store.on('beforeload', function (store, options) {
									            var new_params = { 
									            	blocname: encodeURI(Ext.getCmp('c_deptid').getRawValue())
									            };
									            Ext.apply(store.proxy.extraParams, new_params);
									        });
									        store.reload(); 
							            }
							        }
								 }
							},{
								 	xtype : 'combo',
									fieldLabel : '车牌号',
									emptyText : '请选择',
									width: 165,
									labelWidth: 40,
									id : 'z_carnumber',
									labelAlign: 'right',
									store : 'CarPageListStore',
									displayField : 'carnumber',
									valueField : 'carnumber',
									pageSize : 10,
									minChars:1,
									matchFieldWidth:false,
									listConfig :{
										width:235
									}
								},{
									xtype : 'textfield',
									width : 165,
									labelAlign : 'right',
									labelWidth : 40,
									id : 'z_termianl',
									emptyText : '请输入',
									fieldLabel : '设备号'
								}]
					}, {
						xtype : 'toolbar',
						dock : 'top',
						items : [ {
									xtype : 'datetimefield',
									width : 180,
									labelWidth : 55,
									id : 'z_stime',
									fieldLabel : '开始时间',
									emptyText : '请选择',
									labelAlign : 'right'
								},  {
									xtype : 'datetimefield',
									width : 180,
									labelWidth : 55,
									id : 'z_etime',
									fieldLabel : '结束时间',
									emptyText : '请选择',
									labelAlign : 'right'
								},"->", {
									xtype : 'button',
									text : '查询',
									id : 'nt_search',
									tooltip : '查询',
									iconCls : 'common-search-icon',
									action : 'search'
								}, {
									text : '重置',
									tooltip : '清空查询条件',
									iconCls : 'common-reset-icon',
									action : 'resettext',
									handler : function(button) {
										Ext.getCmp('c_deptid').setValue("");
										Ext.getCmp('z_carnumber').setValue("");
										Ext.getCmp('z_termianl').setValue("");
										Ext.getCmp('z_stime').setValue("");
										Ext.getCmp('z_etime').setValue("");
									}
								}]
					}, {
						xtype : 'toolbar',
						dock : 'top',
						items : [ {
									text : '删除',
									id : '160601',
									tooltip : '删除顺风车订单',
									iconCls : 'delete',
									action : 'delete'
								}, '-', {
									text : '导出',
									id : '160602',
									tooltip : '导出顺风车订单',
									iconCls:'common-excel-icon',
					                action : 'export'
								}]
					}],

			bbar : {
				xtype : 'pagingtoolbar',
			    store: 'RideOrderListStore',
				displayInfo : true,
				displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
				emptyMsg : "没有数据"
			}
		});
