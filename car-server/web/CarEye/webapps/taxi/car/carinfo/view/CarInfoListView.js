Ext.define('CarInfoApp.view.CarInfoListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.carInfoListView',
			title: '车辆信息列表———————双击查看详情',
			region: "center",
   			border: true,
			frame: true,
			store: "CarInfoListStore", 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: '车辆ID', width:60, dataIndex: 'id', sortable: true },
			{ header: '企业ID', width:100, dataIndex: 'blocid', hidden: true },
			{ header: '车辆ID', width:100, dataIndex: 'carid', hidden: true },
			{ header: '用户ID', width:100, dataIndex: 'userid', hidden: true },
			{ header: '企业', width:150, dataIndex: 'blocname', sortable: true,locked:true },
			{ header: '车牌号', width:80, dataIndex: 'carnumber', sortable: true,locked:true },
			{ header: '设备号', width:100, dataIndex: 'terminal', sortable: true,locked:true},
			
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
			{ header: '设备类型', width:80, dataIndex: 'typename', sortable: true},
			{ header: '设备型号', width:80, dataIndex: 'devicemodel', sortable: true},
			{ header: '设备手机号', width:100, dataIndex: 'terphone', sortable: true},
			{ header: '视频类型', width:100, dataIndex: 'vediotype', sortable: true,renderer:function(value){
					if(value == 1){
						return "双路视频";
					}else if(value==2){
						return "国基";
					}else{
						return value;
					}
			}},
			{ header: '视频编号', width:100, dataIndex: 'vediono', sortable: true},
			{ header: '车辆类型', width:80, dataIndex: 'cartypename', sortable: true},
			{ header: '车主手机号', width:100, dataIndex: 'phone', sortable: true },
			{ header: '密码', width:150, dataIndex: 'password', sortable: true },
			{ header: '车辆用途', width:100, dataIndex: 'usename', sortable: true },
			{ header: '车牌颜色', width:100, dataIndex: 'color', sortable: true, renderer:function(value){
					if(value == 1){
						return "蓝色";
					}else if(value==2){
						return "黄色";
					}else if(value==3){
						return "黑色";
					}else if(value==4){
						return "白色";
					}else if(value==5){
						return "红色";
					}else if(value==6){
						return "紫色";
					}else if(value==9){
						return "其他";
					}else{
						return value;
					}
			}},
			{ header: '司机代码', width:80, dataIndex: 'drivercode', sortable: true },
			{ header: '司机名称', width:80, dataIndex: 'drivername', sortable: true },
			{ header: '省', width:80, dataIndex: 'provincename', sortable: true },
			{ header: '市', width:80, dataIndex: 'cityname', sortable: true },
			{ header: '县/区', width:80, dataIndex: 'districtname', sortable: true },
			{ header: '车架号', width:100, dataIndex: 'framenumber', sortable: true },
			{ header: '发动机号', width:100, dataIndex: 'enginenumber', sortable: true },
			{ header: '车身颜色', width:100, dataIndex: 'carcolor', sortable: true, renderer:function(value){
					if(value == 1){
						return "蓝色";
					}else if(value==2){
						return "黄色";
					}else if(value==3){
						return "黑色";
					}else if(value==4){
						return "白色";
					}else if(value==5){
						return "红色";
					}else if(value==6){
						return "紫色";
					}else if(value==7){
						return "绿黄";
					}else if(value==8){
						return "青绿加亮银";
					}else if(value==9){
						return "蓝黄 ";
					}else if(value==10){
						return "艳绿加银灰";
					}else if(value==11){
						return "绿色";
					}else if(value==12){
						return "晶蓝加亮银";
					}else if(value==13){
						return "灰色";
					}else if(value==14){
						return "墨绿色";
					}else if(value==15){
						return "深蓝色";
					}else if(value==16){
						return "棕色";
					}else if(value==99){
						return "其他";
					}else{
						return value;
					}
			} },
			{ header: '生产厂家', width:100, dataIndex: 'factory', sortable: true },
			{ header: '车辆型号', width:100, dataIndex: 'carmodel', sortable: true },
			{ header: '经营权号', width:100, dataIndex: 'managenumber', sortable: true },
			{ header: '车主姓名', width:100, dataIndex: 'ownername', sortable: true },
			{ header: '车主地址', width:200, dataIndex: 'owneraddress', sortable: true },
			{ header: '安装日期', width:100, dataIndex: 'installtime', sortable: true },
			{ header: '登记日期', width:100, dataIndex: 'registertime', sortable: true },
			{ header: '创建时间', width:200, dataIndex: 'createtime', sortable: true  },
			{ header: '备注', width:100, dataIndex: 'remark', sortable: true },
			{ header: '核定载客', width:100, dataIndex: 'seatnumber', hidden: true },
			{ header: '所有权性质', width:100, dataIndex: 'ownership', hidden: true },
			{ header: '入户日期', width:100, dataIndex: 'entertime', hidden: true },
			{ header: '出厂日期', width:100, dataIndex: 'factorytime', hidden: true },
			{ header: '燃料类型', width:100, dataIndex: 'fueltype', hidden: true },
			{ header: '发动机排量', width:100, dataIndex: 'enginecapacity', hidden: true },
			{ header: '排放标准', width:100, dataIndex: 'capacitystandard', hidden: true },
			{ header: '当前状态', width:100, dataIndex: 'nowstatus', hidden: true },
			{ header: '合同承包期(年)', width:100, dataIndex: 'contracttime', hidden: true },
			{ header: '经营性质', width:100, dataIndex: 'management_nature', hidden: true },
			{ header: '财产险', width:100, dataIndex: 'proinsure', hidden: true },
			{ header: '交强险', width:100, dataIndex: 'accinsure', hidden: true },
			{ header: '乘座险', width:100, dataIndex: 'ridinsure', hidden: true },
			{ header: '三责险', width:100, dataIndex: 'cominsure', hidden: true },
			{ header: '车损险', width:100, dataIndex: 'dlwinsure', hidden: true },
			{ header: '电子标签状态', width:100, dataIndex: 'electagstatus', hidden: true },
			
			
			
			{ header: '营运证号', width:100, dataIndex: 'operatenumber', hidden: true },
			{ header: '营运状态', width:100, dataIndex: 'operatestatus', hidden: true , renderer:function(value){
				if(value == 1){
					return "正常营运	";
				}else if(value==2){
					return "已登记未营运";
				}else if(value==3){
					return "已转非营运";
				}else if(value==4){
					return "停运";
				}else{
					return "";
				}
			}},
			{ header: '营运性质', width:100, dataIndex: 'operateproperty', hidden: true },
			{ header: '发证日期', width:100, dataIndex: 'licensetime', hidden: true },
			{ header: '办证类别', width:100, dataIndex: 'certificatetype', hidden: true },
			{ header: '首次登记日期', width:100, dataIndex: 'firstregisttime', hidden: true },
			{ header: '录入人', width:100, dataIndex: 'entryperson', hidden: true },
			{ header: '录入日期', width:100, dataIndex: 'entrytime', hidden: true },
			{ header: '备注', width:100, dataIndex: 'entryremark', hidden: true }
			],
			listeners:{
				
			    //双击单元格事件 
			    celldblclick: function(table, td, cellIndex, model, tr, rowIndex, e, eOpts){
				var id = model.data.id;
				
	//			var carid = model.data.carid;
				var con = Ext.create("CarInfoApp.controller.CarInfoCtrl");
				con.detailsCarInfo(id);
				
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
						                text:'添加',
						                id: '130101',
						                tooltip:'添加车辆信息',
						                iconCls:'add',
						               // action : 'add',
						                 handler: function(button){
                                            var con = Ext.create("CarInfoApp.controller.CarInfoCtrl");
                                            con.addCarInfo(button);
                                         }
						            }, '-', {
						                text:'修改',
						                id: '130102',
						                tooltip:'修改车辆信息',
						                iconCls:'edit',
						                //action : 'edit',
						                 handler: function(button){
                                            var con = Ext.create("CarInfoApp.controller.CarInfoCtrl");
                                            con.editCarInfo(button);
                                         }
						            },'-',{
						                text:'删除',
						                id: '130103',
						                tooltip:'删除车辆信息',
						                iconCls:'remove',
						                //action : 'delete',
						                handler: function(button){
                                            var con = Ext.create("CarInfoApp.controller.CarInfoCtrl");
                                            con.deleteCarInfo(button);
                                         }
						            },'-',{
										text:'Excel导出',
										id: '130104',
						                iconCls:'common-excel-icon',
						                //action : 'export',
						                 handler: function(button){
                                            var con = Ext.create("CarInfoApp.controller.CarInfoCtrl");
                                            con.exportCarInfo(button);
                                         }
						            },'-',{
								        text:'车辆转移',
								        id:'130105',
								        tooltip:'车辆转移',
								        iconCls:'deptmove',
								        //action : 'carmove',
								         handler: function(button){
                                            var con = Ext.create("CarInfoApp.controller.CarInfoCtrl");
                                            con.createCarMove(button);
                                         }
                                     },{
								        text:'营运证',
								        id:'130112',
								        tooltip:'营运证信息',
								        iconCls:'add',
								        //action : 'carmove',
								         handler: function(button){
                                            var con = Ext.create("CarInfoApp.controller.CarInfoCtrl");
                                            con.createOperate(button);
                                         }
								    }]
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CarInfoListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

