Ext.define('FeedbackAdviceApp.view.FeedbackAdviceListView', {
			extend : 'Ext.grid.Panel',
			id:'feedbackAdviceListView',
			border : true,
			alias : 'widget.feedbackAdviceListView',
			title : 'APP意见反馈列表',
			region : 'center',
			frame : true,
			 store : 'FeedbackAdviceListStore',
			multiSelect : true,
			stripeRows : true,// 表格是否隔行换色,默认为true
			loadMask : true, // 是否在加载数据时显示遮罩效果，默认为true
			/*
			 * viewConfig : { stripeRows : true,//表格是否隔行换色,默认为true loadMask :
			 * true // 是否在加载数据时显示遮罩效果，默认为true },
			 */
			selModel : {
				selType : 'checkboxmodel'
			}, // 在首列實現checkbox，僅此在首列
			columns : [new Ext.grid.RowNumberer(),

			{
						header : 'ID',
						flex : 1,
						dataIndex : 'id',
						sortable : true,
						hidden : true
					}, {
						header : 'APP类型',
						flex : 1,
						dataIndex : 'typename',
						sortable : true
					}, {
						header : '版本名称',
						flex : 2,
						dataIndex : 'versionname',
						sortable : true
					}, {
						header : '反馈内容',
						flex : 2,
						dataIndex : 'feedbackcontent',
						sortable : true
					}, {
						header : '处理状态',
						flex : 1,
						dataIndex : 'status',
						sortable : true,
						displayField : 'status',
						valueField : 'id',
						renderer : function(value) {
							if (value == 2) {
								return "<font color='green'>处理完成</font>"
							} else {
								return "<font color='red'>未处理</font>"
							}
						}
					}, {
						header : '处理人',
						flex : 1,
						dataIndex : 'creater',
						sortable : true
					}, {
						header : '处理内容',
						flex : 2,
						dataIndex : 'dealcontent',
						sortable : true
					}, {
						header : '反馈时间',
						flex : 2,
						dataIndex : 'createtime',
						sortable : true
					}],
			dockedItems : [{
						xtype : 'toolbar',
						dock : 'top',
						items : [{
									text : '处理',
									id : '12100301',
									tooltip : '修改API密钥',
									iconCls : 'edit',
									action : 'dispose'
								}, '-', {
									text : '删除',
									id : '12100302',
									tooltip : '删除API密钥',
									iconCls : 'delete',
									action : 'delete'
								}]
					}],

			bbar : {
				xtype : 'pagingtoolbar',
				 store : 'FeedbackAdviceListStore',
				displayInfo : true,
				displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
				emptyMsg : "没有数据"
			}
		});
