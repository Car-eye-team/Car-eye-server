Ext.define('EventApp.view.CarInfoListView' ,{
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
			{ header: 'carid', width : 30 ,dataIndex: 'carid', hidden: true },
			{ header: '车牌号', width : 80, dataIndex: 'carnumber', sortable: true },
			{ header: '车载号码', wdith : 80, dataIndex: 'terminal', sortable: true },
			{ header: '企业名称', wdith:60, dataIndex: 'blocname', sortable: true },
			{  
				   text:'查看车辆事件',  
				   menuDisabled: true,  
				   sortable: false,  
				   align:'center',  
				   id: 'lookeventcar',
			       itemId: 'lookeventcar',
				   xtype: 'actioncolumn',  
				   width: 80,  
				   items: [{  
					   iconCls : 'common-search-icon',
				       tooltip: '查看车辆事件',  
				       text: '查看'
				       /*handler: function(grid, rowIndex, colIndex) {   //rowIndex，colIndex均从0开始  
			            var rec = grid.getStore().getAt(rowIndex);  
			            var carid=rec.get('carid');
			            return carid;
			       }  */
				   }]  
				}
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
								cls : 'x-textfield',
								editable: true,
								valueField: 'id', 
								displayField: 'text',
								listeners: {
							        change: {
							            element: 'el', 
							            fn: function(){ 
							            	var store = Ext.getCmp('c_blocid').store;
											store.clearFilter(true);
											store.on('beforeload', function (store, options) {
									            var new_params = { 
									            	blocname: encodeURI(Ext.getCmp('c_blocid').getRawValue())
									            };
									            Ext.apply(store.proxy.extraParams, new_params);
									        });
									        store.reload(); 
							            }
							        }
								 }
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
								    text:'追加事件',
								    id: '14040504',
								    tooltip:'添加事件信息',
								    iconCls:'add',
								    action : 'addcar'
								},'-',{
									xtype: 'button',
									id: '14040505',
									text : '查看事件下发记录',
									tooltip : '查询',
									iconCls : 'common-search-icon',
									action: 'searchsendrecord'
								},  '->',{
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
								    action : 'resetcar',
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

