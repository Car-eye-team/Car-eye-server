Ext.define('AppManageApp.view.AppVersionListView', {
	extend : 'Ext.grid.Panel',
	border : false,
	id : 'appversionlist',
	alias : 'widget.appVersionListView',
	region : 'east',
	width : 650,
	title : '型号列表',
	collapsible : true,
	store : "AppVersionListStore",
	collapseMode : "454654",
	split : true,
	frame : true,
	multiSelect : true,
	viewConfig : {enableTextSelection:true},//grid中的内容能够复制  
	stripeRows : true, // 表格是否隔行换色，默认为false
	loadMask : true, // 是否在加载数据时显示遮罩效果，默认为false
	selModel : {
		selType : 'checkboxmodel'
	}, // 在首列實現checkbox，僅此在首列
	columns : [{
				header : 'ID',
				width : 50,
				dataIndex : 'id',
				hidden : true
			}, {
				header : '版本号',
				width : 100,
				dataIndex : 'version',
				sortable : true
			}, {
				header : '版本名称',
				width : 100,
				dataIndex : 'versionname',
				sortable : true
			}, {
				header : '版本类型',
				width : 100,
				dataIndex : 'typename',
				sortable : true
			}, {
				header : '下载',
				width : 50,
				dataIndex : 'downloadaddr',
				sortable : true,
				renderer : function(value, metadata, record) {
					
					//var photospath = $("#basepath").val() + value;
					if (value == null || value.length == 0) {
						return "";
					} else {		
						
						var path = record.get('downloadaddr');					
						var filename = path.substring(
								path.lastIndexOf("/") + 1, path.length);
						return "<a href='javascript:void(0);' onclick=\"downLoad('"
								+ filename
								+ "','"
								+ path
								+ "')\" style='color:blue'>下载</a>";
					}
				}
			}, {
				header : '版本升级内容',
				width : 100,
				dataIndex : 'upgradecontent',
				sortable : true
			}, {
				header : '创建者',
				width : 100,
				dataIndex : 'creater',
				sortable : true
			}, {
				header : '创建时间',
				width : 100,
				dataIndex : 'createtime',
				sortable : true
			}],
	dockedItems : [{
				xtype : 'toolbar',
				dock : 'top',
				items : [{
							xtype : 'textfield',
							width : 150,
							maxLength : 20,
							labelWidth : 80,
							id : 'search_versionname',
							fieldLabel : '版本型号名称'
						},  {
							xtype : 'combo',
							 store : 'AppVersionStore',
							width : 150,
							maxLength : 20,
							editable : false,
							labelWidth : 55,
							id : 'search_typeid',
							fieldLabel : '版本类型',
							displayField : 'typename',
							valueField : 'id',
							// allowBlank : false,
							// blankText : '请选择',
							// emptyText : '请选择',
							labelAlign : 'right'
							/*listeners:{
								change:function(button) {
		var store = Ext.StoreManager.get('AppVersionListStore');
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
					var new_params = {
						versionname : encodeURI(Ext
								.getCmp('search_versionname').getValue()),
						typeid : Ext.getCmp('search_typeid').getValue(),
						s_time : Ext.util.Format.date(Ext
										.getCmp('s_time').getValue(),
								'Y-m-d H:i:s'),
						e_time : Ext.util.Format.date(Ext
										.getCmp('e_time').getValue(),
								'Y-m-d H:i:s')
						
					};
					Ext.apply(store.proxy.extraParams, new_params);
				});
		store.loadPage(1);
		// store.reload();
		return false;
	
						
								}
									
								
							}*/
						},  {
							xtype : 'datetimefield',
							width : 160,
							maxLength : 20,
							labelWidth : 55,
							id : 's_time',
							fieldLabel : '开始时间',
							emptyText : '请选择',
							labelAlign : 'right'
						},  {
							xtype : 'datetimefield',
							width : 160,
							maxLength : 20,
							labelWidth : 55,
							id : 'e_time',
							fieldLabel : '结束时间',
							emptyText : '请选择',
							labelAlign : 'right'
						}]
			}, {
				xtype : 'toolbar',
				dock : 'top',
				items : [{
							text : '上传',
							id : '12100104',
							tooltip : '上传版本型号',
							iconCls : 'add',
							action : 'add'
						}, '-', {
							text : '修改',
							id : '12100105',
							tooltip : '修改版本型号',
							iconCls : 'edit',
							action : 'edit'
						}, '-', {
							text : '删除',
							id : '12100106',
							tooltip : '删除版本型号',
							iconCls : 'delete',
							action : 'delete'
						}, '->', {
							xtype : 'button',
							text : '查询',
							id : 'versionlist_search',
							tooltip : '查询',
							iconCls : 'common-search-icon',
							action : 'search'
						}, {
							text : '重置',
							tooltip : '清空查询条件',
							iconCls : 'common-reset-icon',
							action : 'resettext',
							handler : function(button) {
								Ext.getCmp('search_versionname').setValue("");
								Ext.getCmp('search_typeid').setValue("");
								Ext.getCmp('s_time').setValue("");
								Ext.getCmp('e_time').setValue("");
							}
						}]
			}

	],
	bbar : {
		xtype : 'pagingtoolbar',
		store : "AppVersionListStore",
		displayInfo : true,
		displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
		emptyMsg : "没有数据"
	}
});
