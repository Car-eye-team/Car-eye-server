Ext.define('IntercomApp.view.MemberListView', {
			extend : 'Ext.grid.Panel',
			border : false,
			id : 'memberList',
			alias : 'widget.memberListView',
			title : '对讲组成员列表',
			region : 'east',
			frame : true,
			split : true,
			width : "35%",
			store : 'MemberListStore',
			multiSelect : true,
			viewConfig : {
					enableTextSelection:true//grid中的内容能够复制  
					},
			stripeRows : true,//表格是否隔行换色,默认为true
			loadMask : true , // 是否在加载数据时显示遮罩效果，默认为true
			selModel : {
				selType : 'checkboxmodel'
			}, // 在首列實現checkbox，僅此在首列
			columns : [{header : 'ID', flex : 1, dataIndex : 'id', hidden : true},
			           {header : '车辆id',flex : 1,dataIndex : 'carid',hidden : true},
			           {header : '对讲组id',flex : 1,dataIndex : 'gid',hidden : true},
			           
			           {header : '组名称',flex : 1,dataIndex : 'name',hidden : true},
			           {header : '车牌号',flex : 1,dataIndex : 'carnumber',sortable : true},
			           {header : '是否为管理员',flex : 1,dataIndex : 'isadmin',sortable : true,
							renderer : function(value) {
								if (value == 1) {
									return "是"
								} else if (value == 2){
									return "否"
								}else{
									return ""
								}
							}
			           },
			           {header : '加入时间',flex : 1.5,dataIndex : 'createtime',sortable : true}
			           
			           
			           
			           ],
			           
			           
			           
			dockedItems : [{
						xtype : 'toolbar',
						dock : 'top',
						items : [{
									xtype : 'textfield',
									width : 145,
									labelAlign : 'right',
									labelWidth : 40,
									id : 'im_carnumber',
									emptyText : '请输入',
									fieldLabel : '车牌号'
								}]
					}, {
						xtype : 'toolbar',
						dock : 'top',
						items : [ {
									text : '删除',
									id : '',
									tooltip : '删除组成员',
									iconCls : 'delete',
									action : 'delete'
								},"->", {
									xtype : 'button',
									text : '查询',
									id : 'im_search',
									tooltip : '查询',
									iconCls : 'common-search-icon',
									action : 'search'
								}, {
									text : '重置',
									tooltip : '清空查询条件',
									iconCls : 'common-reset-icon',
									action : 'resettext',
									handler : function(button) {
										Ext.getCmp('im_carnumber').setValue("");
									}
								}]
					}],

			bbar : {
				xtype : 'pagingtoolbar',
			    store: 'MemberListStore',
				displayInfo : true,
				displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
				emptyMsg : "没有数据"
			}
		});
