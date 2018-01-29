Ext.define('OnlineReportApp.view.CarInfoListView' ,{
    extend: 'Ext.window.Window',
    alias : 'widget.carInfoListView',
    id : 'carInfoListView',
    width : 750,
    height : 450,
	title: '新增车辆详细',
	modal:true,
   	border: false,
   	closable : true,
   	closeAction : 'destroy',
   	items : [ {
		xtype: 'grid',
		autoWidth: true,
		height:430,
		frame: true,
		border: false,
		store: "CarInfoListStore", 
//		multiSelect: true,
		stripeRows:true, //表格是否隔行换色，默认为false
		loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
		viewConfig : {enableTextSelection:true},//grid中的内容能够复制
//		selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
		columns: [
//			new Ext.grid.RowNumberer(),
			{ header: '车辆ID', width:60, dataIndex: 'id', sortable: true },
			{ header: '企业ID', width:100, dataIndex: 'blocid', hidden: true },
			{ header: '车辆ID', width:100, dataIndex: 'carid', hidden: true },
			{ header: '用户ID', width:100, dataIndex: 'userid', hidden: true },
			{ header: '企业', width:150, dataIndex: 'blocname', sortable: true,locked:true },
			{ header: '车牌号', width:80, dataIndex: 'carnumber', sortable: true,locked:true },
			{ header: '设备号', width:100, dataIndex: 'terminal', sortable: true,locked:true},
//			{ header: '设备号', width:100, dataIndex: 'devicenumber', sortable: true,locked:true},
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
			{ header: '财产险', width:100, dataIndex: 'proinsure', hidden: true },
			{ header: '交强险', width:100, dataIndex: 'accinsure', hidden: true },
			{ header: '乘座险', width:100, dataIndex: 'ridinsure', hidden: true },
			{ header: '三责险', width:100, dataIndex: 'cominsure', hidden: true },
			{ header: '车损险', width:100, dataIndex: 'dlwinsure', hidden: true },
			{ header: '电子标签状态', width:100, dataIndex: 'electagstatus', hidden: true }
			],
			 dockedItems: [
			              {
			                  xtype: 'toolbar',
			                  layout: {
							        overflowHandler: 'Menu'
							  },   //溢出隐藏
			                  dock: 'top',
			                  items: [{
										text:'Excel导出',
										id: '130104',
						                iconCls:'common-excel-icon',
						                //action : 'export',
						                 handler: function(button){
                                            var con = Ext.create("OnlineReportApp.controller.OnlineReportCtrl");
                                            con.exportCarInfo(button);
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
	}]	
});

