Ext.define('FeedbackAdviceApp.view.FeedbackAdviceSearchView', {
			extend : 'Ext.form.Panel',
			alias : 'widget.feedbackAdviceSearchView',
			title : '意见反馈搜索',
			frame : true,
			region : "north",
			height : 100,
			collapsible : true,
			collapseMode : "mini",
			split : true,
			bodyStyle : 'padding:4px 2px 3px 4px',
			layout : {
				type : 'table',
				align : 'right'
			},
			fieldDefaults : {
				labelAlign : 'right',
				width : 170
			},
			items : [ {
						xtype : 'combo',
						store : 'KeyTypeStore',
						width : 165,
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
						/*
						 * listeners:{ change:function(button) { var store =
						 * Ext.StoreManager.get('AppVersionListStore');
						 * store.clearFilter(true); store.on('beforeload',
						 * function(store, options) { var new_params = {
						 * versionname : encodeURI(Ext
						 * .getCmp('search_versionname').getValue()), typeid :
						 * Ext.getCmp('search_typeid').getValue(), s_time :
						 * Ext.util.Format.date(Ext
						 * .getCmp('s_time').getValue(), 'Y-m-d H:i:s'), e_time :
						 * Ext.util.Format.date(Ext
						 * .getCmp('e_time').getValue(), 'Y-m-d H:i:s')
						 *  }; Ext.apply(store.proxy.extraParams, new_params);
						 * }); store.loadPage(1); // store.reload(); return
						 * false;
						 * 
						 *  }
						 * 
						 *  }
						 */
				}, {
						xtype : 'datetimefield',
						width : 200,
						maxLength : 20,
						id : 's_time',
						fieldLabel : '反馈时间',
						labelWidth : 55,
						labelAlign : 'right'
					}, {
						xtype : 'datetimefield',
						width : 165,
						maxLength : 20,
						id : 'e_time',
						fieldLabel : '至',
						labelWidth : 20,
						labelAlign : 'right'
					}],
			buttons : [{
						text : '查询',
						tooltip : '查询客户信息',
						iconCls : 'common-search-icon',
						action : 'search'
					}, {
						text : '重置',
						tooltip : '清空查询条件',
						iconCls : 'common-reset-icon',
						action : 'reset',
						handler : function(button) {
							button.up('form').getForm().reset();
						}
					}]

		});
