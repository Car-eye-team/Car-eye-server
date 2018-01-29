Ext.define('PaymentParamSetApp.view.CompanyListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.companyListView',
			title: '收款公司',
			region: "center",
			id : 'companylist',
			width : "32%",
   			border: true,
			frame: true,
			split:true,
			store: "CompanyListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制  
			selModel: { selType: 'checkboxmodel' ,
				listeners : {
				selectionchange : function(rowmodel) {
					var grid = Ext.getCmp('companylist');
					var m = grid.getSelectionModel().getSelection();
					
					var id = null;
					if(m.length > 0){
						id = m[0].get("id");
					}
					Ext.getCmp('s_id').setValue(id);
					
					Ext.getCmp('parameterSet_Form').getForm().load({
					      url: window.BIZCTX_PATH + '/setjson/findPaymentParamById.action', //请求的服务器地址
					      params: {
						      id: id
						  }
				    });
				}
			} }, //在首列實現checkbox，僅此在首列
			columns: [
//			new Ext.grid.RowNumberer(), 
			{ header: 'ID', width: 40, dataIndex: 'id', hidden: true },
			{ header: '编号', width:40, dataIndex: 'type', sortable: true },
			{ header: '收款公司', width:140, dataIndex: 'company', sortable: true },
			{ header: '创建时间',width:130, dataIndex: 'createtime', sortable: true }
			
			],
			
            dockedItems : [{
				xtype : 'toolbar',
				dock : 'top',
				layout: {
				     overflowHandler: 'Menu'
				    },
				items : [{
							xtype : 'textfield',
							width : 200,
							labelAlign : 'right',
							labelWidth : 55,
							id : 'c_company',
							fieldLabel : '收款公司'
						}]
			}, {
				xtype : 'toolbar',
				dock : 'top',
				layout: {
				     overflowHandler: 'Menu'
				    },
				items : [ {
			                text : '添加',
							id : '121301',
							tooltip : '添加收款公司',
							iconCls : 'add',
							action : 'add'
						}, '-', {
							text : '修改',
							id : '121302',
							tooltip : '修改收款公司',
							iconCls : 'edit',
							action : 'edit'
						}, '-', {
							text : '删除',
							id : '121303',
							tooltip : '删除收款公司',
							iconCls : 'delete',
							action : 'delete'
			            },"->", {
							xtype : 'button',
							text : '查询',
							id : 'c_search',
							tooltip : '查询',
							iconCls : 'common-search-icon',
							action : 'search'
						}, {
							text : '重置',
							tooltip : '清空查询条件',
							iconCls : 'common-reset-icon',
							action : 'resettext',
							handler : function(button) {
								Ext.getCmp('c_company').setValue("");
							}
						}]
			}],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CompanyListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

