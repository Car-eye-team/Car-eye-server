Ext.define('KeyManageApp.view.KeyListView', {
			extend : 'Ext.grid.Panel',
			border : true,
			alias : 'widget.keyListView',
			title : 'API密钥列表',
			region : 'center',
			frame : true,
			store : 'KeyListStore',
			multiSelect : true,
			stripeRows : true,// 表格是否隔行换色,默认为true
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制  
			loadMask : true, // 是否在加载数据时显示遮罩效果，默认为true
			/*
			 * viewConfig : { stripeRows : true,//表格是否隔行换色,默认为true loadMask :
			 * true // 是否在加载数据时显示遮罩效果，默认为true },
			 */
			selModel : {
				selType : 'checkboxmodel'
			}, // 在首列實現checkbox，僅此在首列
			columns : [/*
						 * new Ext.grid.RowNumberer( {header:'序号', width : 40}),
						 */
			{
						header : 'ID',
						flex : 1,
						dataIndex : 'id',
						sortable : true,
						hidden : true
					}, {
						header : '密钥名称',
						flex : 3,
						dataIndex : 'key',
						sortable : true
					}, {
						header : 'APP版本类型',
						flex : 2,
						dataIndex : 'typename',
						sortable : true
					}, {
						header : '状态',
						flex : 1,
						dataIndex : 'status',
						sortable : true,
						displayField : 'status',
						valueField : 'id',
						renderer : function(value) {
							if (value == 1) {
								return "<font color='green'>启用</font>"
							} else {
								return "<font color='red'>停用</font>"
							}
						}
					}, {
						header : '描述',
						flex : 2,
						dataIndex : 'descs',
						sortable : true
					}, {
						header : '请求次数',
						flex : 1,
						dataIndex : 'requestcount',
						sortable : true
					}, {
						header : '创建者',
						flex : 2,
						dataIndex : 'creater',
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
									text : '添加',
									id : '12100201',
									tooltip : '添加API密钥',
									iconCls : 'add',
									action : 'add'
								}, '-', {
									text : '修改',
									id : '12100202',
									tooltip : '修改API密钥',
									iconCls : 'edit',
									action : 'edit'
								}, '-', {
									text : '删除',
									id : '12100203',
									tooltip : '删除API密钥',
									iconCls : 'delete',
									action : 'delete'
								}, '-', {
									text : '启用',
									id : '12100204',
									tooltip : '激活API密钥',
									iconCls : 'active',
									action : 'active'
								}, '-', {
									text : '停用',
									id : '12100205',
									tooltip : '停用API密钥',
									iconCls : 'inactive',
									action : 'inactive'
								}]
					}],

			bbar : {
				xtype : 'pagingtoolbar',
				store : 'KeyListStore',
				displayInfo : true,
				displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
				emptyMsg : "没有数据"
			}
		});
