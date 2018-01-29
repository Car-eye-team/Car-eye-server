Ext.define('DialRuleApp.view.ZddListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id : 'zddListViewGrid',
    alias : 'widget.zddListView',
			title:'再调度历史规则',
//			region: "center",
			height:465,
			frame: true,
			store: "DialRuleListStore", 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
//			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
          	{ header: 'No',xtype: 'rownumberer'},
			{ header: '约租车数量(辆)',width:100,dataIndex: 'traincount', sortable: true },
			{ header: '加入开始时间',width:150, dataIndex: 'stime', sortable: true },	
			{ header: '加入结束时间',width:150, dataIndex: 'etime', sortable: true },	
			{ header: '优先原则', flex:1, dataIndex: 'principle' },
			{ header: '设置时间', width:150,dataIndex: 'createtime' }
			],
			dockedItems:[{
				xtype : 'toolbar',
				dock : 'top',
				items : [{
							xtype : "datetimefield",
							id : 'zdd_searchstime',
							name : 'dialRule.searchstime',
							width : 230,
							editable:false,
							labelWidth : 60,
							fieldLabel : '设置时间'
						}, {
							xtype : "datetimefield",
							id : 'zdd_searchetime',
							width : 190,
							editable:false,
							labelWidth : 20,
							name : 'dialRule.searchetime',
							fieldLabel : '到'
						}, {
							xtype: 'button',
							text : '查询',
							tooltip : '查询调度规则',
							iconCls : 'common-search-icon',
							action : 'search'
						}, {
							xtype: 'button',
							text : '重置',
							tooltip : '清空查询条件',
							iconCls : 'common-reset-icon',
							handler : function(button) {
								Ext.getCmp('zdd_searchstime').setValue('');
								Ext.getCmp('zdd_searchetime').setValue('');
							}
						}]
			}],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "DialRuleListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});

