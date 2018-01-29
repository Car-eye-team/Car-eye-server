Ext.define('HostApp.view.AdvertVerView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.advertVerView',
			title: '广告版本列表',
			region: "center",
			id : 'advertVer',
			width : "50%",
   			border: true,
			frame: true,
			split:true,
			store: "AdvertVerStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制  
			selModel: { selType: 'checkboxmodel' ,
				listeners : {
				selectionchange : function(rowmodel) {
					var store = Ext.StoreManager.get('AdvertConStore');
					var grid = Ext.getCmp('advertVer');
					var m = grid.getSelectionModel().getSelection();
					var jsonData = "";
					for (var i = 0, len = m.length; i < len; i++) {
						var ss = m[i].get("id");
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					store.clearFilter(true);
					store.on('beforeload', function(store, options) {
								var new_params = {
									versionid : jsonData
								};
								Ext.apply(store.proxy.extraParams,
										new_params);
							});
					store.loadPage(1);
				}
			} }, //在首列實現checkbox，僅此在首列
			columns: [
//			new Ext.grid.RowNumberer(), 
			{ header: 'ID', width: 40, dataIndex: 'id', hidden: true },
			{ header: '位置ID', width:100, dataIndex: 'positionid', hidden: true },
			{ header: '发布人ID', width:100, dataIndex: 'userid', hidden: true },
			{ header: '审核人ID', width:100, dataIndex: 'adminid', hidden: true },
			
			
			{ header: '版本名称', width:150, dataIndex: 'version', sortable: true },
			{ header: '版本号', width:100, dataIndex: 'verid', sortable: true },
			{ header: '发布人', width:100, dataIndex: 'username', sortable: true },
			{ header: '在线状态', width:100, dataIndex: 'onstatus', sortable: true,
				renderer:function(value){
					if(value == 1){
						return "<font color='green'>上架</font>";
					}else if(value == 2){
						return "<font color='red'>下架</font>";
					}else {
						return "";
					}
				}
			},
			{ header: '描述', width:100, dataIndex: 'remark', sortable: true },
			{ header: '审核状态', width:100, dataIndex: 'auditstatus', sortable: true,
				renderer:function(value){
					if(value == "0"){
						return "未审核";
					}else if(value == "1"){
						return "<font color='green'>审核通过</font>";
					}else if(value == "2"){
						return "<font color='red'>审核不通过</font>";
					}else {
						return "";
					}
				} 
				
			},
			{ header: '审核人', width:100, dataIndex: 'auditname', sortable: true },
			{ header: '审核描述', width:100, dataIndex: 'auditremark', sortable: true },
			{ header: '到期日期', width:100, dataIndex: 'dtime', sortable: true },
			{ header: '审核时间', width:150, dataIndex: 'audittime', sortable: true },
			{ header: '发布时间', width:150, dataIndex: 'reltime', sortable: true },
			{ header: '创建时间',width:150, dataIndex: 'createtime', sortable: true }
			
			],
			
            dockedItems : [{
				xtype : 'toolbar',
				dock : 'top',
				layout: {
				     overflowHandler: 'Menu'
				    },
				items : [{
							xtype : 'textfield',
							width : 130,
							labelAlign : 'right',
							labelWidth : 55,
							id : 'ver_version',
							fieldLabel : '版本名称'
						},{
							xtype : 'combo',
							width : 125,
							labelAlign : 'right',
							labelWidth : 55,
							id : 'ver_auditstatus',
							fieldLabel : '审核状态',
							store : 'AuditStatusStore',
							displayField : 'type',
							valueField : 'id'
						},{
							xtype : 'combo',
							width : 125,
							labelAlign : 'right',
							labelWidth : 55,
							id : 'ver_onstatus',
							fieldLabel : '在线状态',
							store : 'OnStatusStore',
							displayField : 'type',
							valueField : 'id'
						},{
							xtype : 'datetimefield',
							width : 155,
							id : 'ver_stime',
							fieldLabel : '发布时间',
							labelWidth: 55,
							labelAlign: 'right'
						},{
							xtype : 'datetimefield',
							width : 115,
							id : 'ver_etime',
							fieldLabel : '至',
							labelWidth: 15,
							labelAlign: 'right'
						}]
			}, {
				xtype : 'toolbar',
				dock : 'top',
				layout: {
				     overflowHandler: 'Menu'
				    },
				items : [ {
			                text : '添加',
							id : '19010401',
							tooltip : '添加广告版本',
							iconCls : 'add',
							action : 'add'
						}, '-', {
							text : '修改',
							id : '19010402',
							tooltip : '修改广告版本',
							iconCls : 'edit',
							action : 'edit'
						}, '-', {
							text : '删除',
							id : '19010403',
							tooltip : '删除广告版本',
							iconCls : 'delete',
							action : 'delete'
			            }, '-', {
							text : '导出',
							id : '19010404',
							tooltip : '导出广告版本',
							iconCls : 'common-excel-icon',
							action : 'export'
						}, '-', {
							text : '审核',
							id : '19010405',
							tooltip : '审核广告版本',
							iconCls : 'edit',
							action : 'audit',
		                    handler: function(){
								var con = Ext.create('HostApp.controller.HostCtrl');
								con.auditVer();
							}
			            }, '-', {
							text : '上架',
							id : '19010406',
							tooltip : '上架广告版本',
							iconCls : 'add',
							action : 'active',
		                    handler: function(){
								var con = Ext.create('HostApp.controller.HostCtrl');
								con.activeVer();
							}
						}, '-', {
							text : '下架',
							id : '19010407',
							tooltip : '下架广告版本',
							iconCls : 'delete',
							action : 'inactive',
		                    handler: function(){
								var con = Ext.create('HostApp.controller.HostCtrl');
								con.inactiveVer();
							}
			            },"->", {
							xtype : 'button',
							text : '查询',
							id : 'ver_search',
							tooltip : '查询',
							iconCls : 'common-search-icon',
							action : 'search'
						}, {
							text : '重置',
							tooltip : '清空查询条件',
							iconCls : 'common-reset-icon',
							action : 'resettext',
							handler : function(button) {
								Ext.getCmp('ver_version').setValue("");
								Ext.getCmp('ver_auditstatus').setValue("");
								Ext.getCmp('ver_onstatus').setValue("");
								Ext.getCmp('ver_stime').setValue("");
								Ext.getCmp('ver_etime').setValue("");
							}
						}]
			}],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "AdvertVerStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

