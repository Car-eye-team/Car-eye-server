Ext.define('TopLightApp.view.AdvertConView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.advertConView',
			title: '广告内容列表',
			region: "east",
			id : 'advertCon',
			width : "50%",
   			border: true,
			frame: true,
			split:true,
			store: "AdvertConStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制  
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
//			new Ext.grid.RowNumberer(), 
			{ header: 'ID', width: 40, dataIndex: 'id', hidden: true },
			{ header: '位置ID', width:100, dataIndex: 'positionid', hidden: true },
			{ header: '发布人ID', width:100, dataIndex: 'userid', hidden: true },
			{ header: '审核人ID', width:100, dataIndex: 'adminid', hidden: true },
			{ header: '广告类型ID', width:100, dataIndex: 'typeid', hidden: true },
			{ header: '广告版本ID', width:100, dataIndex: 'versionid', hidden: true },
			
			{ header: '广告名称', width:100, dataIndex: 'adname', sortable: true },
			{ header: '广告描述', width:100, dataIndex: 'remark', sortable: true },
			{ header: '发布人', width:100, dataIndex: 'username', sortable: true },
			{ header: '序号', width:100, dataIndex: 'connumber', sortable: true },
			{ header: '广告类型名称', width:100, dataIndex: 'typename', sortable: true },
			{ header: '显示位置', width:100, dataIndex: 'position', sortable: true },
			{ header: '版本名称', width:150, dataIndex: 'version', sortable: true },
			
			{ header: '特效', width:100, dataIndex: 'con1', sortable: true },
			{ header: '速度', width:100, dataIndex: 'con2', sortable: true },
			{ header: '暂停', width:100, dataIndex: 'con3', sortable: true },
			{ header: '重复', width:100, dataIndex: 'con4', sortable: true },
//			{ header: '偏移', width:100, dataIndex: 'con5', sortable: true },
			
			
			
			{ header: '到期日期', width:100, dataIndex: 'dtime', sortable: true },
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
							id : 'con_adname',
							fieldLabel : '广告名称'
						},
//						{
//							xtype : 'combo',
//							width : 125,
//							labelAlign : 'right',
//							labelWidth : 55,
//							id : 'con_auditstatus',
//							fieldLabel : '审核状态',
//							store : 'AuditStatusStore',
//							displayField : 'type',
//							valueField : 'id'
//						},{
//							xtype : 'combo',
//							width : 125,
//							labelAlign : 'right',
//							labelWidth : 55,
//							id : 'con_onstatus',
//							fieldLabel : '在线状态',
//							store : 'OnStatusStore',
//							displayField : 'type',
//							valueField : 'id'
//						},
						{
							xtype : 'datetimefield',
							width : 155,
							id : 'con_stime',
							fieldLabel : '发布时间',
							labelWidth: 55,
							labelAlign: 'right'
						},{
							xtype : 'datetimefield',
							width : 115,
							id : 'con_etime',
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
							id : '19010308',
							tooltip : '添加广告内容',
							iconCls : 'add',
							action : 'add'
						}, '-', {
							text : '修改',
							id : '19010309',
							tooltip : '修改广告内容',
							iconCls : 'edit',
							action : 'edit'
						}, '-', {
							text : '删除',
							id : '19010310',
							tooltip : '删除广告内容',
							iconCls : 'delete',
							action : 'delete'
			            },"->", {
							xtype : 'button',
							text : '查询',
							id : 'con_search',
							tooltip : '查询',
							iconCls : 'common-search-icon',
							action : 'search'
						}, {
							text : '重置',
							tooltip : '清空查询条件',
							iconCls : 'common-reset-icon',
							action : 'resettext',
							handler : function(button) {
								Ext.getCmp('con_adname').setValue("");
//								Ext.getCmp('con_auditstatus').setValue("");
//								Ext.getCmp('con_onstatus').setValue("");
								Ext.getCmp('con_stime').setValue("");
								Ext.getCmp('con_etime').setValue("");
							}
						}]
			}],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "AdvertConStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

