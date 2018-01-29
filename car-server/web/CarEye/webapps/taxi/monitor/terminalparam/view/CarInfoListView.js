Ext.define('CarParamApp.view.CarInfoListView', {
			extend : 'Ext.grid.Panel',
			border : true,
			alias : 'widget.carInfoListView',
			region : "west",
			width : "30%",
			title : '车辆列表',
			collapsible : true,
			store : "CarInfoListStore",
			id : 'carinfogrid',
			collapseMode : "mini",
			split : true,
			frame : true,
			stripeRows : true, //表格是否隔行换色，默认为false
			loadMask : true, //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制  
			selModel: { selType: 'checkboxmodel',listeners :{
			    select : function(rowmodel,select){
			    	
			    }
			}}, //在首列實現checkbox，僅此在首列
			columns : [{
						header : 'carid',
						dataIndex : 'carid',
						hidden : true
					}, {
						header : '企业名称',
						width : 120,
						dataIndex : 'blocname',
						sortable : true
					}, {
						header : '车牌号',
						width : 70,
						dataIndex : 'carnumber',
						sortable : true
					}, {
						header : '车载号码',
						width : 100,
						dataIndex : 'terminal',
						sortable : true
					}],
			dockedItems : [{
				xtype : 'toolbar',
				dock : 'top',
				items : [{
					xtype : 'comboboxtree',
					fieldLabel : '企业',
					editable : true,
					labelWidth : 30,
					id : 'c_deptid',
					itemId:'blocid',
					width : 130,
					store : Ext.create('Ext.data.TreeStore', {
								autoLoad : 'true',
								fields : ['text', 'id', 'parentId'],
								root : {
									expanded : true,
									text : '企业'
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
				 	xtype : 'combo',
					fieldLabel : '车牌号',
					width: 160,
					labelWidth: 45,
					id : 'c_carnumber',
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
					
				}]
			},{
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                       '->', {
								xtype : 'button',
								text : '查询',
								id : 'mailset_query_car',
								tooltip : '查询',
								iconCls : 'common-search-icon',
								action : 'searchcar'
							}, {
								tooltip : '清空查询条件',
								text : '重置',
								iconCls : 'common-reset-icon',
								handler : function(button) {
									Ext.getCmp('c_deptid').setValue("");
									Ext.getCmp('c_carnumber').setValue("");
								}
							}
			                  ]
			              }]
		});
