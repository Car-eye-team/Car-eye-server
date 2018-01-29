Ext.define('QuestionApp.view.CarInfoListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id : 'carinfogrid',
    alias : 'widget.carInfoListView',
			region: "east",
			width:500,
			title:'车辆列表',
            collapsible: true,
			store: "CarInfoListStore",
            collapseMode: "mini",
            split: true,
			frame: true,
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'carid', flex: 1 ,dataIndex: 'carid', hidden: true },
			{ header: '车牌号', flex: 1, dataIndex: 'carnumber', sortable: true },
			{ header: '车载号码', flex: 1, dataIndex: 'terminal', sortable: true },
			{ header: '企业名称', flex: 1, dataIndex: 'blocname', sortable: true }
			],
			
			dockedItems: [
					{
					    xtype: 'toolbar',
					    dock: 'top',
					    items: [
								  {
								xtype : 'comboboxtree',
								fieldLabel : '企业名称',
						        labelWidth: 55,
								id : 'c_blocid',
								itemId:'blocid',
								width:160,
								store: Ext.create('Ext.data.TreeStore', {  
							        autoLoad : 'true',
							        fields: ['text','id','parentId'], 
									root : {expanded : true,text : '企业名称' },
									proxy: {
										 type: 'ajax',
										 url: window.BIZCTX_PATH + '/servlet/DeptTree?type=200', 
										 reader: {
											 type: 'json'
										 }
									}
							    }) ,
							    rootVisible: false,
								editable: false,
								cls : 'x-textfield',
								valueField: 'id', 
								displayField: 'text'
							},{  
					            xtype : 'combo',
								fieldLabel : '车牌号',
								width: 160,
								labelWidth: 40,
								id : 'c_carnumber',
								labelAlign: 'right',
								store : 'CarPageListStore',//CarPageListStore    CarNumberStore
								displayField : 'carnumber',
								valueField : 'carnumber',
								pageSize : 10,
								minChars:1,
								matchFieldWidth:false,
								listConfig :{
									width:235
								}
					         },{
						        xtype : 'textfield',
						        width : 160,
						        maxLength : 20,
						        labelWidth : 55,
						        id : 'c_terminal',
						        fieldLabel : '车载号码'
							}
						    ]
						},
			            
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                      {
									xtype: 'button',
					                text:'下发提问',
					                id: '14040604',
					                iconCls:'common-assignauthority-icon',
					                action : 'send'
					            },{
									xtype: 'button',
									id: '14040605',
									text : '查看发送记录',
									iconCls : 'common-search-icon',
									action: 'searchsendrecord'
								}, '->',{
									xtype: 'button',
									text : '查询',
									id : 'mailset_query_car',
									tooltip : '查询',
									iconCls : 'common-search-icon',
									action: 'searchcar'
								}, {
								    text : '重置',
								    tooltip : '清空查询条件',
								    iconCls : 'common-reset-icon',
								    //action : 'resetcar',
							        	handler: function(button){
							        	Ext.getCmp('c_blocid').setValue("");
							        	Ext.getCmp('c_terminal').setValue("");
							        	Ext.getCmp('c_carnumber').setValue("");
							        }
								}
			                  ]
			              }
			          ],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CarInfoListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});

