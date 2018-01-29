Ext.define('OnCallCountApp.view.OnCallCountListView', {
			extend : 'Ext.grid.Panel',
			border : true,
			alias : 'widget.onCallCountListView',
			title : '电召数据统计报表 ———————— 双击显示统计类型对应详情 ',
			region : "center",
			frame : true,
			store : "OnCallCountListStore",
			viewConfig : {
				enableTextSelection : true
			}, // grid中的内容能够复制
			multiSelect : true,
			stripeRows : true, // 表格是否隔行换色，默认为false
			loadMask : true, // 是否在加载数据时显示遮罩效果，默认为false
			columns : [new Ext.grid.RowNumberer(), {
						header : '统计类型',
						flex : 1,
						dataIndex : 'counttype',
						sortable : true,
						renderer : function(val) {
							switch (val) {
								case 1 :
									return "拨入电话数量";
								case 2 :
									return "接入系统数量";
								case 3 :
									return "下发业务数量";
								case 4 :
									return "抢答业务数量";
								case 5 :
									return "派车成功数量";
								case 6 :
									return "客户取消数量";
								case 7 :
									return "司机取消数量";
								case 8 :
									return "客户违约数量";
								case 9 :
									return "司机违约数量";
								default :
									return ""
							}
						}
					}, {
						header : '统计数量',
						flex : 1,
						dataIndex : 'countnumber',
						sortable : true
					}],
			listeners : {
				'itemdblclick' : function(grid, record, item, index, e, eOpts) {
					var stime = encodeURI(Ext.util.Format.date(Ext
									.getCmp('cd_stime').getValue(),
							'Y-m-d H:i:s'));
					var etime = encodeURI(Ext.util.Format.date(Ext
									.getCmp('cd_etime').getValue(),
							'Y-m-d H:i:s'));
					var blocid = Ext.getCmp('cm_blocid').getValue();
					var carnumber = encodeURI(Ext.getCmp('cd_carnumber')
							.getValue());
					var drivername = encodeURI(Ext.getCmp('cd_drivername')
							.getValue());
					var cusname = encodeURI(Ext.getCmp('cd_cusname').getValue());
					var store;
					var view;
					var counttype = record.data.counttype;
					switch (counttype) {
						case 1 :
							view = Ext.widget('inBoundListView');
							store = Ext.StoreManager.get("InBoundListStore");
							break;
						case 2 :
							view = Ext.widget('inSystemListView');
							store = Ext.StoreManager.get("InSystemListStore");
							break;
						case 3 :
							view = Ext.widget('transactionListView');
							store = Ext.StoreManager.get("TransactionListStore");
							break;
						case 4 :
							view = Ext.widget('answerCountListView');
							store = Ext.StoreManager.get("AnswerCountListStore");
							break;
						case 5 :
							view = Ext.widget('sendSuccessCountListView');
							store = Ext.StoreManager.get("SendSuccessCountListStore");
							break;
						case 6 :
							view = Ext.widget('cusCancelListView');
							store = Ext.StoreManager.get("CusCancelListStore");
							break;
						case 7 :
							view = Ext.widget('carDriverCancelListView');
							store = Ext.StoreManager.get("CarDriverCancelListStore");
							break;
						case 8 :
							view = Ext.widget('cusBreachListView');
							store = Ext.StoreManager.get("CusBreachListStore");
							break;
						case 9 :
							view = Ext.widget('carDriverBreakListView');
							store = Ext.StoreManager.get("CarDriverBreakListStore");
							break;
					}
					view.show();
					store.clearFilter(true);
					store.on('beforeload', function(store, options) {
								var new_params = {
									stime : stime,
									etime : etime,
									blocid : blocid,
									carnumber : carnumber,
									drivername : drivername,
									cusname : cusname
								};
								Ext.apply(store.proxy.extraParams, new_params);
							});
					store.load();
				}
			},
			dockedItems : [{
				xtype : 'toolbar',
				layout : {
					overflowHandler : 'Menu'
				}, // 溢出隐藏
				dock : 'top',
				items : [{
					text : 'Excel导出',
					iconCls : 'common-excel-icon',
					handler : function(button) {
						var con = Ext
								.create("OnCallCountApp.controller.OnCallCountCtrl");
						con.excelExport();
					}
				}]
			}]
		});
