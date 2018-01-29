Ext.define('TerParamSetApp.view.CarInfoListView', {
			extend : 'Ext.grid.Panel',
			border : true,
			alias : 'widget.carInfoListView',
			region : "center",
			width : 'auto',
			title : '车辆列表',
			collapsible : true,
			store : "CarInfoListStore",
			id : 'carinfogrid',
			collapseMode : "mini",
			split : true,
			frame : true,
			autoScroll : true,
			stripeRows : true, //表格是否隔行换色，默认为false
			loadMask : true, //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制  
			selModel: { 
				selType: 'checkboxmodel'}, //在首列實現checkbox，僅此在首列
			columns : [
					{
						header : 'carid',
						dataIndex : 'carid',
						hidden : true
					}, {
						header : '车牌号',
						flex : 1,
						dataIndex : 'carnumber',
						sortable : true
					}, {
						header : '设备号',
						flex : 1,
						dataIndex : 'terminal',
						sortable : true
					}, {
						header : '集团名称',
						flex : 1.5,
						dataIndex : 'blocname',
						sortable : true
					}],
			dockedItems : [{
				xtype : 'toolbar',
				dock : 'top',
				items : [{
					xtype : 'comboboxtree',
					fieldLabel : '集团',
					editable : true,
					labelWidth : 30,
					id : 'c_deptid',
					width : 150,
					store : Ext.create('Ext.data.TreeStore', {
								autoLoad : 'true',
								fields : ['text', 'id', 'parentId'],
								root : {
									expanded : true,
									text : '集团'
								},
								proxy : {
									type : 'ajax',
									url : window.BIZCTX_PATH + '/servlet/DeptTree?type=200',
									reader : {
										type : 'json'
									}
								}
							}),
					rootVisible : false,
					cls : 'x-textfield',
					valueField : 'id',
					displayField : 'text',
					listeners : {
						change : {
							element : 'el',
							fn : function() {
								var store = Ext.getCmp('c_deptid').store;
								store.clearFilter(true);
								store.on('beforeload',
										function(store, options) {
											var new_params = {
												deptname : encodeURI(Ext.getCmp('c_deptid').getRawValue())
											};
											Ext.apply(store.proxy.extraParams,new_params);
										});
								store.reload();
							}
						}
					}
				}, {
					xtype : 'textfield',
					fieldLabel : '车牌号',
					width : 140,
					labelWidth : 40,
					id : 'c_carnumber',
					itemId : 'carnumber',
//					store : 'CarListStore',
					labelAlign : 'right'
//					valueField : 'carnumber',
//					displayField : 'carnumber'
				}, {
					xtype : 'textfield',
					fieldLabel : '设备号',
					width : 140,
					labelWidth : 40,
					id : 'c_terminal',
					itemId : 'terminal',
					labelAlign : 'right'
				}, {
					
					xtype : 'button',
					text : '查询',
					id : 'mailset_query_car',
					tooltip : '查询',
					iconCls : 'common-search-icon',
					action : 'searchcar'
				}, {
					text : '重置',
					tooltip : '清空查询条件',
					iconCls : 'common-reset-icon',
//				    action : 'resetcar',
					handler : function(button) {
						Ext.getCmp('c_deptid').setValue("");
						Ext.getCmp('c_carnumber').setValue("");
						Ext.getCmp('c_terminal').setValue("");
					}
				}]
			},{
                  xtype: 'toolbar',
                  dock: 'top',
                  items: [{
						xtype: 'button',
		                text:'终端参数设置',
		                iconCls:'common-search-icon',
		                id:'q_11',
		                action : 'loadterparam'
					}
                  ]
			}],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CarInfoListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		});
