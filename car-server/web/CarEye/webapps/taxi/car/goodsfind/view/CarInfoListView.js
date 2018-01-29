Ext.define('GoodsFindApp.view.CarInfoListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    alias : 'widget.carInfoListView',
			region: "west",
			width:420,
			title:'车辆列表',
            collapsible: true,
            id :"dialcarinfogrid",
			store: "CarInfoListStore",
            collapseMode: "mini",
            split: true,
			frame: true,
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
//			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'carid', width: 0 ,dataIndex: 'carid', hidden: true },
			{ header: 'deptid', width: 0 ,dataIndex: 'blocid', hidden: true },
			{ header: '企业', width: 130, dataIndex: 'blocname', sortable: true },
			{ header: '车牌号', width: 80, dataIndex: 'carnumber', sortable: true },
			{ header: '终端号码',width: 100, dataIndex: 'terminal', sortable: true },
			{ header: '联系电话',width: 100, dataIndex: 'phone', sortable: true }
			],
			
			dockedItems: [
					{
					    xtype: 'toolbar',
					    dock: 'top',
					    items: [{
						        xtype : 'combo',
								width : 170,
								fieldLabel : '车牌号',
								labelWidth: 50,
								id:'c_carnumber',
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
							}, {
								xtype: 'button',
								text : '查询',
								tooltip : '查询',
								iconCls : 'common-search-icon',
								action: 'searchcar'
							}]
						}
			          ]
});

