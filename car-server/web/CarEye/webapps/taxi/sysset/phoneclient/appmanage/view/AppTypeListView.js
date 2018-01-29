Ext.define('AppManageApp.view.AppTypeListView', {
			extend : 'Ext.grid.Panel',
			border : false,
			id : 'apptypelist',
			alias : 'widget.appTypeListView',
			title : '类型列表',
			region : 'center',
			frame : true,
			store : 'AppTypeListStore',
			multiSelect : true,
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制  
			stripeRows : true,//表格是否隔行换色,默认为true
				loadMask : true , // 是否在加载数据时显示遮罩效果，默认为true
			/*viewConfig : {
				stripeRows : true,//表格是否隔行换色,默认为true
				loadMask : true  // 是否在加载数据时显示遮罩效果，默认为true
			},*/
			selModel : {
				selType : 'checkboxmodel',
				listeners : {
					selectionchange : function(rowmodel) {
						// alert(rowmodel.getSelection()[0].get('factory'));
						var store = Ext.StoreManager.get('AppVersionListStore');
						// Ext.getCmp('id').setValue(rowmodel.getSelection()[0].get('id'));
						var grid = Ext.getCmp('apptypelist');
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
			}}, // 在首列實現checkbox，僅此在首列
			columns : [{
						header : 'ID',
						flex : 1,
						dataIndex : 'id',
						sortable : true
					}, {
						header : '版本类型名称',
						flex : 2,
						dataIndex : 'typename',
						sortable : true
					}, {
						header : '备注',
						flex : 2,
						dataIndex : 'remark',
						sortable : true
					}, {
						header : '创建时间',
						flex : 2,
						dataIndex : 'createtime',
						sortable : true
					}],
			dockedItems : [{
						xtype : 'toolbar',
						dock : 'top',
						items : [{
									xtype : 'textfield',
									width : 200,
									maxLength : 20,
									labelAlign : 'right',
									labelWidth : 80,
									id : 'search_type',
									fieldLabel : '版本类型名称'
								}, "->", {
									xtype : 'button',
									text : '查询',
									id : 'typelist_search',
									tooltip : '查询',
									iconCls : 'common-search-icon',
									action : 'search'
								}, {
									text : '重置',
									tooltip : '清空查询条件',
									iconCls : 'common-reset-icon',
									action : 'resettext',
									handler : function(button) {
										Ext.getCmp('search_type')
												.setValue("");
									}
								}]
					}, {
						xtype : 'toolbar',
						dock : 'top',
						items : [{  
									text : '添加',
									id : '12100101',
									tooltip : '添加版本类型',
									iconCls : 'add',
									action : 'add'
								}, '-', {
									text : '修改',
									id : '12100102',
									tooltip : '修改版本类型',
									iconCls : 'edit',
									action : 'edit'
								}, '-', {
									text : '删除',
									id : '12100103',
									tooltip : '删除版本类型',
									iconCls : 'delete',
									action : 'delete'
								}]
					}],

			bbar : {
				xtype : 'pagingtoolbar',
			    store: 'AppTypeListStore',
				displayInfo : true,
				displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
				emptyMsg : "没有数据"
			}
		});
