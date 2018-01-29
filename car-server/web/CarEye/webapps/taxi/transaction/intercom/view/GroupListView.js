Ext.define('IntercomApp.view.GroupListView', {
			extend : 'Ext.grid.Panel',
			border : false,
			id : 'groupList',
			alias : 'widget.groupListView',
			title : '对讲组列表',
			region : 'center',
			frame : true,
			store : 'GroupListStore',
			multiSelect : true,
			viewConfig : {
					enableTextSelection:true//grid中的内容能够复制  
					},
			stripeRows : true,//表格是否隔行换色,默认为true
			loadMask : true , // 是否在加载数据时显示遮罩效果，默认为true
			selModel : {
				selType : 'checkboxmodel'
					,
				listeners : {
					selectionchange : function(rowmodel) {
						var store = Ext.StoreManager.get('MemberListStore');
						var grid = Ext.getCmp('groupList');
						var m = grid.getSelectionModel().getSelection();
						var jsonData = "";
						for (var i = 0, len = m.length; i < len; i++) {
							var ss = m[i].get("id");
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
			columns : [{header : 'ID', width:100, dataIndex : 'id', hidden : true},
			           {header : '车辆id',width:100,dataIndex : 'carid',hidden : true},
			           
			           {header : '组名称',width:100,dataIndex : 'name',sortable : true},
			           
			           {header : '管理员',width:100,dataIndex : 'carnumber',sortable : true},
			           
			           {header : '经度',width:100,dataIndex : 'lng',sortable : true},
			           {header : '纬度',width:100,dataIndex : 'lat',sortable : true},
			           
			           {header : '省',width:100,dataIndex : 'province',hidden : true},
			           {header : '市',width:100,dataIndex : 'city',hidden : true},
			           {header : '县',width:100,dataIndex : 'district',hidden : true},
			           {header : '百度经度',width:100,dataIndex : 'blng',hidden : true},
			           {header : '百度纬度',width:100,dataIndex : 'blat',hidden : true},
			           
			           {header : '地址',width:200,dataIndex : 'baddress',sortable : true},
			           
			           {header : '描述',width:200,dataIndex : 'remark',sortable : true},
			           {header : '创建时间',width:130,dataIndex : 'createtime',sortable : true}
			           
			           
			           
			           ],
			           
			           
			           
			dockedItems : [{
						xtype : 'toolbar',
						dock : 'top',
						items : [{
									xtype : 'textfield',
									width : 145,
									labelAlign : 'right',
									labelWidth : 40,
									id : 'ig_name',
									emptyText : '请输入',
									fieldLabel : '组名称'
								},{
									xtype : 'datetimefield',
									width : 175,
									labelWidth : 55,
									id : 'ig_stime',
									fieldLabel : '创建时间',
									emptyText : '请选择',
									labelAlign : 'right'
								},  {
									xtype : 'datetimefield',
									width : 145,
									labelWidth : 25,
									id : 'ig_etime',
									fieldLabel : '至',
									emptyText : '请选择',
									labelAlign : 'right'
								}]
					}, {
						xtype : 'toolbar',
						dock : 'top',
						items : [ {
									text : '删除',
									id : '',
									tooltip : '删除对讲组',
									iconCls : 'delete',
									action : 'delete'
								},"->", {
									xtype : 'button',
									text : '查询',
									id : 'ig_search',
									tooltip : '查询',
									iconCls : 'common-search-icon',
									action : 'search'
								}, {
									text : '重置',
									tooltip : '清空查询条件',
									iconCls : 'common-reset-icon',
									action : 'resettext',
									handler : function(button) {
										Ext.getCmp('ig_name').setValue("");
										Ext.getCmp('ig_stime').setValue("");
										Ext.getCmp('ig_etime').setValue("");
									}
								}]
					}],

			bbar : {
				xtype : 'pagingtoolbar',
			    store: 'GroupListStore',
				displayInfo : true,
				displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
				emptyMsg : "没有数据"
			}
		});
