Ext.define('CarTrackApp.view.CarTrackListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.carTrackListView',
	height:180,
	frame: true,
	title:"车辆历史信息",
	id : 'carTrackListView',
	store: "CarTrackListStore",
	header : false, // 显示 header 默认 true
	markDirty : false,
    multiSelect: true,
	stripeRows:true, //表格是否隔行换色，默认为false
	loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false  //,mode:'SINGLE'
	columns: [
	new Ext.grid.RowNumberer(), 
	{ header: 'ID', flex: 0.5, dataIndex: 'id', hidden: true },
	{ header: '车牌号', flex: 1, dataIndex: 'carnumber', sortable: true },
	{ header: '车辆状态', flex: 1, dataIndex: 'carstatus', sortable: true , renderer:function(value){
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
	{ header: '终端号', flex: 1, dataIndex: 'terminal', sortable: true },
	{ header: '经度', flex: 1, dataIndex: 'lng', sortable: true },
	{ header: '纬度', flex: 1, dataIndex: 'lat', sortable: true },
	{ header: '速度(km/h)', flex: 1, dataIndex: 'speed', sortable: true },
	{ header: '方向', flex: 0.8, dataIndex: 'direction', sortable: true,renderer:function(value){
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
	{ header: '当前里程', flex: 1, dataIndex: 'mileage', sortable: true},
	{ header: '总里程', flex: 0.9, dataIndex: 'summileage', sortable: true },
	{ header: '当前位置', flex: 2.8, dataIndex: 'address', sortable: true },
	{ header: '停留时间', flex: 1.5, dataIndex: 'stoptime'},
	{ header: '创建时间', flex: 2, dataIndex: 'createtime' }
	],
	dockedItems: [
					{
					    xtype: 'toolbar',
					    dock: 'top',
					    layout: {
					     overflowHandler: 'Menu'
					    },
					    items: [{
				xtype : 'combo',
				id : 'ct_status',
				fieldLabel : '车辆状态',
				labelWidth: 60,
				store :"CarStatusStore",
				valueField : 'id',
				cls : 'x-textfield',
				width : 180,
				editable:false,
				displayField : 'value',
				allowBlank : true
			},{
				xtype: 'button',
				text : '查询',
				tooltip : '查询',
				iconCls : 'common-search-icon',
				handler: function(button){
					var con = Ext.create("CarTrackApp.controller.CarTrackCtrl");
					con.statussearch();
				}
            },{
	            text:'导出',
	            tooltip:'导出历史轨迹信息',
	            iconCls:'common-excel-icon',
				handler: function(button){
					var con = Ext.create("CarTrackApp.controller.CarTrackCtrl");
					con.exportTrack();
				}    
        },"->",{
				xtype : 'combo',
				fieldLabel : '频率',
				labelWidth: 30,
				cls : 'x-textfield',
				width : 90,
				value : '100',
				id : 'ct_rate',
				store :"RateStore",
				editable:false,
				valueField : 'type',
				displayField : 'name',
				allowBlank : true
            },{
				xtype : 'checkboxfield',
				boxLabel  : '地图跟随',
                checked: true,
                id : 'checkbox',
				allowBlank : true
            },{
				xtype: 'button',
				id : 'play',
				text : '播放',
				tooltip : '播放',
				iconCls : 'play',
				disabled : true,
				handler: function(button){
					var con = Ext.create("CarTrackApp.controller.CarTrackCtrl");
					con.playCarTrack();
				} 
			},{
           			xtype:'slider',
                    id : 'slider',                  
                    width: 200,
                    height:24,
                    disabled:true,
                    minValue: 0,
                    maxValue: 100,
                    listeners : {
	            		"changecomplete" : function (currSlider,value){
	            			if(points.length > 0){
	            				index = parseInt(value/100*points.length);
								var point = points[index];
								if(maptype == 1){
									if(index > 0) {
										map.addOverlay(new BMap.Polyline([points[index - 1], point], {strokeColor: "red", strokeWeight: 1, strokeOpacity: 1}));
									}
									label.setContent("地址: " + point.address+ (point.createtime ==undefined ? "" :"<br/>时间："+point.createtime+"<br/>状态："+chageCarStatus(point.carstatus)));
									car.setPosition(point);
									car.setRotation(point.direction);
								}else{
									if(index > 0) {
										car.setPosition(point);
							   			car.setContent(getCarContent(point));//更新点标记内容
							            car.setAngle(parseInt(point.direction));
									}
								}
								
								
	            			}
	     				}
	            	}
			},{
				text : '暂停',
				id : 'pause',
				tooltip : '暂停',
				iconCls : 'pause',
				disabled : true,
				handler: function(button){
					var con = Ext.create("CarTrackApp.controller.CarTrackCtrl");
					con.pauseCarTrack();
				} 
			},{
				text : '重置',
				id : 'reset',
				tooltip : '重置',
				iconCls : 'undo',
				disabled : true,
				handler: function(button){
					var con = Ext.create("CarTrackApp.controller.CarTrackCtrl");
					con.resetCarTrack();
				} 
            }
						    ]
						}
			          ],
	listeners : { 
			'itemclick' : function(view, record, item, recordindex, event){
	//				index = points.length - recordindex;
					index = recordindex;
					var point = points[index];
					if(index >= 0) {
						car.setPosition(point);
			   			car.setContent(getCarContent(point));//更新点标记内容
			            car.setAngle(parseInt(point.direction));
					}
					
					return false;  
			}
		}
});


