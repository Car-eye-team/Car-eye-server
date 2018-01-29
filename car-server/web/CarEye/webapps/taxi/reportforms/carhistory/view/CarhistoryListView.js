Ext.define('CarhistoryApp.view.CarhistoryListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.carhistoryListView',
			title: '车辆历史位置	——————————双击行显示地图信息',
			region: "center",
   			border: true,
			frame: true,
			store: "CarhistoryStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制  
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			new Ext.grid.RowNumberer({header:'序号',width:50}),
			{ header: 'ID', width: 40, dataIndex: 'id', hidden: true },
			{ header: 'blocid', width: 40, dataIndex: 'blocid', hidden: true },
			{ header: '企业名称', width: 160, dataIndex: 'blocname', sortable: true },
			{ header: '用户ID', width: 40, dataIndex: 'userid', hidden: true },
			{ header: '终端号码', width: 110, dataIndex: 'terminal', sortable: true },
			{ header: '车id',width: 80, dataIndex: 'carid', hidden: true },
			{ header: '车牌号',width: 80, dataIndex: 'carnumber', sortable: true },
			{ header: '车辆状态', width:100, dataIndex: 'carstatus', sortable: true , renderer:function(value){
					if(value == 7){
						return "在线";
					}else if(value==1){
						return "长时间离线";
					}else if(value==2){
						return "离线";
					}else if(value==3){
						return "熄火";
					}else if(value==5){
						return "行驶";
					}else if(value==4){
						return "停车";
					}else if(value==6){
						return "报警";
					}else if(value==8){
						return "未定位";
					}else{
						return "";
					}
			}},
			{ header: 'GPS是否定位', width:100, dataIndex: 'gpsflag', hidden: true, renderer:function(value){
				if(value == 1 || value == 2){
					return "已定位";
				}else{
					return "未定位";				
				}
			} },
			{ header: '高度', width: 100, dataIndex: 'blng', hidden: true },
			{ header: '速度', width: 80, dataIndex: 'speed', sortable: true, renderer:function(value){
				if(value == null || value == "null" || value == ""){
					return "";
				}else if(value == 0){
					return value;
				}else{
					return value+"km/h";				
				}
			}},
			{ header: '方向', width: 80, dataIndex: 'direction', sortable: true,renderer:function(value){
				if(value == null || value == "null" || value == ""){
					return "";
				}
				if(value ==0){
					return "正北";
				}else if(value >0 && value <90){
					return "东北";
				}else if(value == 90){
					return "正东";
				}else if(value >90 && value <180){
					return "东南";
				}else if(value == 180){
					return "正南";
				}else if(value >180 && value <270){
					return "西南";
				}else if(value == 270){
					return "正西";
				}else if(value >270 && value <360){
					return "西北";
				}else if(value == 360){
					return "正北";
				}else{
					return value;
				}
			}},
			{ header: 'GPS时间', width: 150, dataIndex: 'gpstime', sortable: true },
			{ header: '创建时间', width: 150, dataIndex: 'createtime', sortable: true },
			{ header: '百度经度', width: 100, dataIndex: 'blng', sortable: true },
			{ header: '百度纬度', width: 100, dataIndex: 'blat', sortable: true },
			{ header: '经度', width: 100, dataIndex: 'blng', hidden: true },
			{ header: '纬度', width: 100, dataIndex: 'blat', hidden: true },
			{ header: '地址', width: 200, dataIndex: 'address', sortable: true }
			],
			
			listeners : { 
				itemdblclick : function(view, record, item, index, event){
					var view = Ext.widget('searchMapView');
					
			        var addr=record.data.address;
			        var blng=record.data.blng;
			        var blat=record.data.blat;
			        var createtime=record.data.createtime;
			        var carnumber =record.data.carnumber;
			        var direction =record.data.direction;
					Ext.getCmp('c_cwaaddr').setValue(addr);
					Ext.getCmp('c_lng').setValue(blng);
					Ext.getCmp('c_lat').setValue(blat);
					Ext.getCmp('c_createtime').setValue(createtime);
					Ext.getCmp('c_carnumber').setValue(carnumber);
					Ext.getCmp('c_direction').setValue(direction);
					view.show();
					 return false;  
				}
				},
				
			dockedItems: [
					{
					    xtype: 'toolbar',
	                    layout: {
					        overflowHandler: 'Menu'
					    },   //溢出隐藏
					    dock: 'top',
					    items: [{
									text:'Excel导出',
					                iconCls:'common-excel-icon',
					                handler: function(button){
						        		var con = Ext.create("CarhistoryApp.controller.CarhistoryCtrl");
						        		con.exportInfo();
					                }
								},{
									text:'删除',
					                iconCls:'delete',
					                handler: function(button){
						        		var con1 = Ext.create("CarhistoryApp.controller.CarhistoryCtrl");
						        		con1.deleteInfo(button);
					                }
								}
						    ]
						}
			          ],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CarhistoryStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

