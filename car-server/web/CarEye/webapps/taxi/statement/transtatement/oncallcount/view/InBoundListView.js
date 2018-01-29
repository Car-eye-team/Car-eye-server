Ext.define('OnCallCountApp.view.InBoundListView', {
	extend : 'Ext.window.Window',
	alias : 'widget.inBoundListView',
	title : '拨入电话记录列表',
	width : 800,
	height : 510,
	layout : 'form',
	animCollapse : false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',
	items : [{
		xtype : 'grid',
		autoWidth : true,
		autoHeight : true,
		minHeight : 490,
		frame : true,
		store : "InBoundListStore",
		multiSelect : true,
		stripeRows : true, // 表格是否隔行换色，默认为false
		loadMask : true,
//		selModel : {
//			selType : 'checkboxmodel'
//		},
		columns : [{
					header : 'id',
					width : 30,
					dataIndex : 'id',
					hidden : true
				}, {
					header : '来电号码',
					flex : 1,
					dataIndex : 'callnumber',
					sortable : true
				}, {
					header : '客户名称',
					flex : 1,
					dataIndex : 'cusname',
					sortable : true
				}, {
					header : '来电时间',
					flex : 1,
					dataIndex : 'inboundcalltime',
					sortable : true
				}, {
					header : '挂机时间',
					flex : 1,
					dataIndex : 'hangupcalltime',
					sortable : true
				}],
		dockedItems : [{
			xtype : 'toolbar',
			layout : {
				overflowHandler : 'Menu'
			}, // 溢出隐藏
			dock : 'top',
			items : [{
				text : '导出',
				tooltip : 'Excel导出',
				iconCls : 'common-excel-icon',
				handler : function(button) {
					var con = Ext
							.create("OnCallCountApp.controller.OnCallCountCtrl");
					con.exportInBoundList(button);
				}
			}]
		}],
		bbar : {
			xtype : 'pagingtoolbar',
			store : "InBoundListStore",
			displayInfo : true,
			displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
			emptyMsg : "没有数据"
		}
	}]
});
