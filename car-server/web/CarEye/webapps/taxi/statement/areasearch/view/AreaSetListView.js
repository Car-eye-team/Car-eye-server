Ext.define('AreaSetApp.view.AreaSetListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id:'sysareasetgrid',
    alias : 'widget.areaSetListView',
			region: "west",
			width:300,
			title:'多区域',
            collapsible: true,
			store: "AreaSetListStore",
            collapseMode: "mini",
            split: true,
			frame: true,
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			//selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列,
//			selModel: { selType: 'checkboxmodel',listeners :{
//			selectionchange  : function(rowmodel,select){
//				getItemclick();
//			},
//		     select: function(rowmodel,select){
//				},
//				deselect: function(model,record,index) {//取消选中时产生的事件
//				}
//	}}, //在首列實現checkbox，僅此在首列
			selModel: { selType: 'checkboxmodel'
			,listeners :{
					    select : function(rowmodel,select){
							
						},
						selectionchange  : function(rowmodel,select){
//				            var id=rowmodel.getSelection()[0].get('id');
//							var storeArea = Ext.StoreManager.get('AreaSetListStore');
//							var data = storeArea.getById(id);
//							var store = Ext.StoreManager.get('CarInfoListStore');
//							store.clearFilter(true);
//							getItemclick();
//							var control = Ext.create('AreaSetApp.controller.AreaSetCtrl'); 
//						
//							if(data.data.areatype==1){
//								store.on('beforeload', function (store, options) {
//								            var new_params = { 
//								            	id:data.data.id,
//									            lng:data.data.ylng,
//									            lat: data.data.ylat,
//									            mileage :data.data.radius,
//									            areatype:1
//								            };
//								            Ext.apply(store.proxy.extraParams, new_params);
//								});
//							    store.load()
//						       control.showCircleCarLocal(data.data.ylng, data.data.ylat, data.data.radius);
//							}else if(data.data.areatype==2){
//							   store.on('beforeload', function (store, options) {
//							            var new_params = { 
//								            latlt:data.data.latlt,
//											latrb:data.data.latrb,
//											lnglt:data.data.lnglt,
//											lngrb:data.data.lngrb,
//								            areatype:2
//							            };
//							            Ext.apply(store.proxy.extraParams, new_params);
//							});
//						    store.load()
//							   control.showRectangleCarLocal(data.data.latlt,data.data.latrb, data.data.lnglt, data.data.lngrb);
//							}
						    getItemclick();
						    getItemClickList();
						    return false;
			            }
			}
			}, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'id', width:30 ,dataIndex: 'id', hidden: true },
			{ header: '区域类型', width:70, dataIndex: 'areatype', sortable: true ,renderer : function(value){
				if(value ==1){
					return "圆形区域";
				}else if(value ==2){
					return "矩形区域";
				}else if(value ==3){
					return "多边形区域";
				}
			}},
			{ header: '区域名称', width:70, dataIndex: 'areaname', sortable: true },
			{ header: '创建时间', width:120, dataIndex: 'createtime', sortable: true }
			],
			dockedItems: [
					{
					    xtype: 'toolbar',
					    dock: 'top',
					    items: [
								{
						        xtype : 'textfield',
						        width : 200,
						        maxLength : 30,
						        labelWidth: 60,
						        id:'sas_areaname',
						        fieldLabel : '区域名称'
							}, {
										xtype: 'button',
										text : '查询',
										tooltip : '查询',
										iconCls : 'common-search-icon',
										action: 'searcharea'
									}
						    ]
						}
						,{
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                      {
								        text:'添加',
								        id: '180606',
								        tooltip:'添加多系统区域',
								        iconCls:'add',
								        action : 'add'
								    },'-',{
								        text:'修改',
								        id: '180607',
								        tooltip:'保存多系统区域',
								        iconCls:'edit',
								        action : 'edit'
								    },'-',{
								        text:'删除',
								        id: '180608',
								        tooltip:'删除多系统区域',
								        iconCls:'delete',
								        action : 'delete'
								    }
			                  ]
			              }
			          ],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "AreaSetListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});

