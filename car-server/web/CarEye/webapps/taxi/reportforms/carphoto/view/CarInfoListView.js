Ext.define('CarPhotoApp.view.CarInfoListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    frame : true,
    id : 'carinfogridset',
    alias : 'widget.carInfoListView',
			region: "west",
			width:350,
			title:'车辆列表',
			store: "CarInfoListStore",
			multiSelect: true,
			split: true,
			autoScroll:true,
			stripeRows:true, //表格是否隔行换色，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'carid', flex: 1 ,dataIndex: 'carid', hidden: true },
			{ header: '企业名称', flex: 1, dataIndex: 'blocname', sortable: true },
			{ header: '车牌号', flex: 1, dataIndex: 'carnumber', sortable: true },
			{ header: '车载号码', flex: 1, dataIndex: 'terminal', sortable: true }
			],
			dockedItems: [
					{
					    xtype: 'toolbar',
					    dock: 'top',
					    items: [
								  {
								xtype : 'comboboxtree',
								fieldLabel : '企业',
								editable:true,
						        labelWidth: 30,
								id : 'c_blocid',
								itemId : 'blocid',
								width:160,
								store: Ext.create('Ext.data.TreeStore', {  
							        autoLoad : 'true',
							        fields: ['text','id','parentId'], 
									root : {expanded : true,text : '企业' },
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
								width : 140,
								fieldLabel : '车牌号',
								labelWidth: 45,
								id:'c_carnumber',
								itemId:'carnumber',
								labelAlign: 'right',
								store : 'CarPageListStore',//CarPageListStore    CarListStore
								displayField : 'carnumber',
								valueField : 'carnumber',
								pageSize : 10,
								minChars:1,
								matchFieldWidth:false,
								listConfig :{
									width:235
								}
								
					         }
						    ]
						},
			            
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                     '->',{
									xtype: 'button',
									text : '查询',
									id : 'mailset_query_car',
									tooltip : '查询',
									iconCls : 'common-search-icon',
									action: 'search'
								}, {
								    text : '重置',
								    tooltip : '清空查询条件',
								    iconCls : 'common-reset-icon',
//								    action : 'resetcar',
							        handler: function(button){
							        	Ext.getCmp('c_blocid').setValue("");
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

