Ext.define('CarDriverApp.view.CarDriverListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.carDriverListView',
			title: '司机信息列表',
			region: "center",
   			border: true,
			frame: true,
			store: "CarDriverListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: '工号', width:100, dataIndex: 'id', hidden: true },
			{ header: '驾驶员id', width:0, dataIndex: 'driverid', hidden: true },
			{ header: '车辆id', width:0, dataIndex: 'carid', hidden: true },
			{ header: '企业Id', width:100, dataIndex: 'blocid', hidden: true },
			{ header: '用户ID', width:100, dataIndex: 'userid', hidden: true },
			{ header: '司机代码', width:100, dataIndex: 'drivercode', sortable: true ,locked:true},
			{ header: '姓名', width:100, dataIndex: 'drivername', sortable: true ,locked:true},
			{ header: '企业', width:180, dataIndex: 'blocname', sortable: true },
//			{ header: '驾驶证号', width:150, dataIndex: 'drivecrednum', sortable: true },
//			{ header: '服务监督卡号', width:120, dataIndex: 'sscno', sortable: true },
//			{ header: '联系电话', width:100, dataIndex: 'tel', hidden: true },
			{ header: '手机号', width:100, dataIndex: 'phone', sortable: true },
			{ header: '性别', width:50, dataIndex: 'sex', sortable: true ,renderer:function(value){
					if(value == 1){
						return "男";
					}else if(value==2){
						return "女";
					}else{
					    return "";
					 }
			}},
			{ header: '车牌号', width:100, dataIndex: 'carnumber', sortable: true },
			{ header: '文化程度', width:80, dataIndex: 'education', sortable: true ,renderer:function(value){
					if(value == 1){
						return "小学";
					}else if(value==2){
						return "初中";
					}else if(value==3){
						return "高中";
					}else if(value==4){
						return "中专";
					}else if(value==5){
						return "大专";
					}else if(value==6){
						return "职高";
					}else if(value==7){
						return "本科";
					}else if(value==8){
						return "硕士研究生";
					}else if(value==9){
						return "博士研究生";
					}else if(value==10){
						return "博士后";
					}else if(value==99){
						return "其它";
					}else{
					    return "";
					 }
			}},
			{ header: '政治面貌', width:80, dataIndex: 'political', sortable: true ,renderer:function(value){
					if(value == 1){
						return "党员";
					}else if(value==2){
						return "团员";
					}else if(value==3){
						return "群众";
					}else if(value==4){
						return "九三学社";
					}else if(value==99){
						return "其它";
					}else{
					    return "";
					 }
			}},
			{ header: '当前状态', width:80, dataIndex: 'nowstatus', sortable: true ,renderer:function(value){
					if(value == 1){
						return "正常";
					}else if(value==2){
						return "注销";
					}else{
					    return "";
					 }
			}},
			{ header: '出生日期', width:100, dataIndex: 'birthday', sortable: true },
			{ header: '身份证号', width:150, dataIndex: 'idnumber', sortable: true },
			{ header: '民族', width:150, dataIndex: 'nation', sortable: true },
			
//			{ header: '从业资格证', width:100, dataIndex: 'qualificationcertificate', sortable: true },
//			{ header: '发证机构', width:100, dataIndex: 'certifyingauthority', sortable: true },
//			{ header: '驾驶证年审日期', width:100, dataIndex: 'driverannualdate', sortable: true },
			{ header: '家庭住址', width:200, dataIndex: 'addr', sortable: true },
			{ header: '创建时间', width:200, dataIndex: 'createtime',sortable: true },
			{ header: '驾驶证号', width:100, dataIndex: 'drivingnumber', sortable: true },
			{ header: '准驾车型', width:100, dataIndex: 'zjcartype', sortable: true },
			{ header: '驾驶证有效期(年)', width:100, dataIndex: 'validity', sortable: true },
			{ header: '备注', width:100, dataIndex: 'remark', sortable: true },
			{ header: '初次领证日期', width:100, dataIndex: 'firstlztime', sortable: true },
			{ header: '发证日期', width:100, dataIndex: 'fztime', sortable: true }
			],
			listeners:{
				
			    //双击单元格事件 
			    celldblclick: function(table, td, cellIndex, model, tr, rowIndex, e, eOpts){
				var id = model.data.id;
				
	//			var carid = model.data.carid;
				var con = Ext.create("CarDriverApp.controller.CarDriverCtrl");
				con.detailsCarDriver(id);
				
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
						                id: '130201',
						                tooltip:'添加司机信息',
						                iconCls:'add',
						                //action : 'add',
						                 handler: function(button){
                                            var con = Ext.create("CarDriverApp.controller.CarDriverCtrl");
                                            con.addCarDriver(button);
                                         }
						            }, '-', {
						                text:'修改',
						                id: '130202',
						                tooltip:'修改司机信息',
						                iconCls:'edit',
						                //action : 'edit',
						                handler: function(button){
                                            var con = Ext.create("CarDriverApp.controller.CarDriverCtrl");
                                            con.editCarDriver(button);
                                         }
						            },'-',{
						                text:'删除',
						                id: '130203',
						                tooltip:'删除司机信息',
						                iconCls:'remove',
						                //action : 'delete',
						                 handler: function(button){
                                            var con = Ext.create("CarDriverApp.controller.CarDriverCtrl");
                                            con.deleteCarDriver(button);
                                         }
						            },'-',{
						                text:'导出',
						                id:'130204',
						                tooltip:'Excel导出',
						                iconCls:'common-excel-icon',
						                //action : 'export',
						                 handler: function(button){
                                            var con = Ext.create("CarDriverApp.controller.CarDriverCtrl");
                                            con.exportInfo(button);
                                         }
                                    }, '-', {
						                text:'岗前考试信息',
						                id: '130205',
						                tooltip:'岗前考试信息',
						                iconCls:'add',
						                //action : 'edit',
						                handler: function(button){
                                            var con = Ext.create("CarDriverApp.controller.CarDriverCtrl");
                                            con.createExam(button);
                                         }
                                     }, '-', {
						                text:'服务证信息',
						                id: '130206',
						                tooltip:'服务证信息',
						                iconCls:'add',
						                //action : 'edit',
						                handler: function(button){
                                            var con = Ext.create("CarDriverApp.controller.CarDriverCtrl");
                                            con.createLicense(button);
                                         }
						            }]
            }],
            tbar:[],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CarDriverListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

