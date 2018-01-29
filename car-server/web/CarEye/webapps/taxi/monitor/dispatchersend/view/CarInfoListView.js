Ext.define('DispatcherSendApp.view.CarInfoListView' ,{
    extend: 'Ext.grid.Panel',
    border : true,
    id : 'carinfogrid',
    alias : 'widget.carInfoListView',
			region: "west",
			width:290,
			title:'车辆列表',
            collapsible: true,
			store: "CarInfoListStore",
            collapseMode: "mini",
            split: true,
			frame: true,
			multiSelect: true,
			autoScroll:true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			//selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			selModel: { selType: 'checkboxmodel',
            listeners :{
                selectionchange  : function(rowmodel,select){
	                var grid=Ext.getCmp('carinfogrid');
                    var m = grid.getSelectionModel().getSelection();
                    var jsonData = "";
                    
                    for ( var i = 0, len = m.length; i < len; i++) {
                        var ss = m[i].get("carid");
                        if (i == 0) {
                            jsonData = jsonData + ss;
                        } else {
                            jsonData = jsonData + "," + ss;
                        }
                    }
//					var records = grid.getSelectionModel().getSelection();
//					var view = Ext.widget('dispatcherSendgrid');
//					var store = Ext.StoreManager.get('CarInfoListStore');
//					var data = store.getById(records[0].get('carid'));
					Ext.getCmp('carids').setValue(jsonData);
					return false;
				},
				deselect: function(model,record,index) {//取消选中时产生的事件
					Ext.getCmp('id').setValue('');
				 	Ext.getCmp('type').setValue('');
				    Ext.getCmp('remark').setValue('');
				    Ext.getCmp('carids').setValue('');
                }
    }}, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'carid', flex: 1 ,dataIndex: 'carid', hidden: true },
			{ header: '车牌号', flex: 1, dataIndex: 'carnumber', sortable: true },
			{ header: '车载号码', flex:1.5, dataIndex: 'terminal', sortable: true },
			{ header: '企业id', flex: 2, dataIndex: 'blocid',  hidden: true },
			{ header: '企业名称', flex: 2, dataIndex: 'blocname',  hidden: true }
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
								width:140,
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
							},
//								{
//					            xtype : 'combo',
//								fieldLabel : '车牌号',
//								width: 125,
//								labelWidth: 40,
//								id : 'c_carnumber',
//								itemId : 'carnumber',
//								store : 'CarListStore',
//								labelAlign: 'right',
//								valueField : 'carnumber',
//								displayField : 'carnumber'
//					         }
							{
						        xtype : 'textfield',
						        width : 125,
						        maxLength : 20,
						        id : 'c_carnumber',
						        labelAlign: 'right',
						        labelWidth:40,
						        fieldLabel : '车牌号'
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
									action: 'searchcar'
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
			          ]
});

