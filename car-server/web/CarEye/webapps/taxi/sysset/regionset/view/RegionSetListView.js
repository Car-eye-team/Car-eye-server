Ext.define('RegionSetApp.view.RegionSetListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.regionSetListView',
			title: '行政区域列表',
			region: "center",
   			border: true,
			frame: true,
			store: "RegionSetListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			new Ext.grid.RowNumberer({width:40}), 
			{ header: 'ID', flex: 1, dataIndex: 'id', hidden: true },
			{ header: 'PARENTID', flex: 1, dataIndex: 'parentid', hidden: true },
			{ header: 'ENNAME', flex: 1, dataIndex: 'enname', hidden: true },
			{ header: 'STCNNAME', flex: 1, dataIndex: 'stcnname', hidden: true },
			{ header: 'STENNAME', flex: 1, dataIndex: 'stenname', hidden: true },
			{ header: '行政编码', flex: 1, dataIndex: 'szcode', sortable: true },
			{ header: '行政区名', flex: 1.5, dataIndex: 'cnname', sortable: true },
			{ header: '行政级别', flex: 1, dataIndex: 'clevel', sortable: true,renderer:function(value){
				if(value==1){
					return "省级";
				}else if(value==2){
					return "市级";
				}else {
					return "县级";
				}
			} },
			{ header: '上级名称', flex: 1, dataIndex: 'parentname', sortable: true },
			{ header: '上级编码', flex: 1, dataIndex: 'parentcode', sortable: true },
			{ header: '创建时间', flex:2, dataIndex: 'createtime',sortable: true }
			],
			dockedItems: [
                          {
                              xtype: 'toolbar',
                              layout: {
                                    overflowHandler: 'Menu'
                              },   //溢出隐藏
                              dock: 'top',
                              items: [{
						                text:'添加',
//						                id:'12010101',
						                tooltip:'添加行政区域',
						                iconCls:'add',
						                //action : 'add',
						                handler: function(button){
                                            var con = Ext.create("RegionSetApp.controller.RegionSetCtrl");
                                            con.addRegion(button);
                                        }
						            }, '-', {
						                text:'修改',
//						                id:'12010102',
						                tooltip:'修改行政区域',
						                iconCls:'edit',
						                //action : 'edit',
						                handler: function(button){
                                            var con = Ext.create("RegionSetApp.controller.RegionSetCtrl");
                                            con.editRegion(button);
                                        }
						            },'-',{
						                text:'删除',
//						                id:'12010103',
						                tooltip:'删除行政区域',
						                iconCls:'remove',
						                //action : 'delete',
						                handler: function(button){
                                            var con = Ext.create("RegionSetApp.controller.RegionSetCtrl");
                                            con.deleteRegion(button);
                                        }
						            }]
            }], 
			bbar : {
				xtype : 'pagingtoolbar',
				store: "RegionSetListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

