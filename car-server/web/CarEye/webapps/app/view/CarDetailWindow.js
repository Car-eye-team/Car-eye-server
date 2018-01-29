Ext.define('FMS.view.CarDetailWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.carDetailWindow',
	title : '车辆详细信息',
    width : 800,
//    height:300,
    id:'carDetailWindow',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	modal : true,
	closeAction : 'destroy',
	border:false,
	items :[ {
		xtype : 'form',
		frame : true,
		anchor : '100%',
		collapsible : false,
		"items": [
		          {
		              "xtype": "fieldset",
		              "title": "<b><font style='color:green'>企业信息 </font></b>",
		              fieldDefaults: {
		              	buttonAlign : 'left',
			            labelAlign : 'right',
		  	        	labelWidth: 80
		  	   		 },
		  		   items : [{
		  			layout : 'column',
		  			columnWidth : 1,
		  			border:false,
		              items : [{
		  						columnWidth : .25,
		  						border:false,
		  						items : [{
									xtype : 'displayfield',
									fieldLabel : '企业',
									id : 'blocname'
								}]
		  				}, {
		  					columnWidth : .25,
		  					border:false,
		  					items : [{
									xtype : 'displayfield',
									fieldLabel : '电话',
									id : 'bloctel'
								}]
		  					},{
		  						columnWidth : .25,
		  						border:false,
		  						items : [{
									xtype : 'displayfield',
									fieldLabel : '联系人',
									id : 'contract'
								}]
							}]
		                }]
		          },{
		              "xtype": "fieldset",
		              "title": "<b><font style='color:green'>车辆基本信息 </font></b>",
		              fieldDefaults: {
		              	buttonAlign : 'left',
			            labelAlign : 'right',
		  	        	labelWidth: 80
		  	   		 },
		  		   items : [{
		  			layout : 'column',
		  			columnWidth : 1,
		  			border:false,
		              items : [{
		  						columnWidth : .25,
		  						border:false,
		  						items : [
//		  						         {
//									xtype : 'displayfield',
//									fieldLabel : '企业',
//									id : 'blocname'
//								},
								{
									xtype : 'displayfield',
									fieldLabel : '车牌号',
									id : 'carnumber'
								},{
									xtype : 'displayfield',
									fieldLabel : '车牌颜色',
									id : 'carnumbercolorname'
								},{
									xtype : 'displayfield',
									fieldLabel : '车身颜色',
									id : 'carcolorname'
								},{
									xtype : 'displayfield',
									fieldLabel : '车辆用途',
									id : 'usename'
								}]
		  				}, {
		  					columnWidth : .25,
		  					border:false,
		  					items : [{
									xtype : 'displayfield',
										fieldLabel : '车辆归属地省',
										id : 'provincename'
									},{  
							            xtype : 'displayfield',
										fieldLabel : '市',
										id : 'cityname'
									},{  
							            xtype : 'displayfield',
										fieldLabel : '县',
										id : 'districtname'
									}]
		  					},{
		  						columnWidth : .25,
		  						border:false,
		  						items : [ {
							            xtype : 'displayfield',
										fieldLabel : '车主姓名',
										id : 'ownername'
									},{
										xtype : 'displayfield',
										fieldLabel : '车主手机号',
										id : 'phone'
							     	}, {
										xtype : 'displayfield',
										fieldLabel : '车主地址',
										id : 'owneraddress'
									}]
							},{
		  						columnWidth : .25,
		  						border:false,
		  						items : [{
	  								xtype : 'displayfield',
									fieldLabel : '发动机号',
									id : 'enginenumber'
								},{
									xtype : 'displayfield',
									fieldLabel : '车辆类型',
									id : 'cartypename'
								},{
									xtype : 'displayfield',
									fieldLabel : '车架号',
									id : 'framenumber'
								},{
										xtype : 'displayfield',
										fieldLabel : '密码',
										id : 'password'
									}
//								,{
//										xtype : 'displayfield',
//										fieldLabel : '当班司机',
//										id : 'drivername'
//									}
								]
							}]
		                }]
		          },
		           {
		              "xtype": "fieldset",
		              "title": "<b><font style='color:green'>设备信息 </font></b>",
		              fieldDefaults: {
		              	buttonAlign : 'left',
			            labelAlign : 'right',
		  	        	labelWidth: 80
		  	   		 },
		  		   items : [{
		  			layout : 'column',
		  			columnWidth : 1,
		  			border:false,
		              items : [{
		  						columnWidth : .25,
		  						border:false,
		  						items : [{
									xtype : 'displayfield',
									fieldLabel : '设备号',
									id : 'terminal'
								} ,{
									xtype : 'displayfield',
									fieldLabel : '设备类型',
									id : 'devicetypename'
								}]
		  				}, {
		  					columnWidth : .25,
		  					border:false,
		  					items : [{
								xtype : 'displayfield',
								fieldLabel : '设备手机号',
								id : 'terphone'
							},{
									xtype : 'displayfield',
									fieldLabel : '登记日期',
									id : 'registertime'
								}]
		  					},{
		  						columnWidth : .25,
		  						border:false,
		  						items : [{
									xtype : 'displayfield',
									fieldLabel : '设备型号',
									id : 'devicemodel'
								} ,{
									xtype : 'displayfield',
									fieldLabel : '安装日期',
									id : 'installtime'
								} ]
							},{
		  						columnWidth : .25,
		  						border:false,
		  						items : [{
									xtype : 'displayfield',
									fieldLabel : '备注',
									id : 'remark'
								}]
							}]
		                }]
		          },
		          {
		              "xtype": "fieldset",
		              "title": "<b><font style='color:green'>车辆属性信息 </font></b>",
		              fieldDefaults: {
		              	buttonAlign : 'left',
			            labelAlign : 'right',
		  	        	labelWidth: 80
		  	   		 },
		  		   items : [{
		  			layout : 'column',
		  			columnWidth : 1,
		  			border:false,
		              items : [{
		  						columnWidth : .25,
		  						border:false,
		  						items : [{
									xtype : 'displayfield',
									fieldLabel : '核定载客',
									id : 'seatnumber'
								},{
									xtype : 'displayfield',
									fieldLabel : '所有权性质',
									id : 'ownership'
								},{
									xtype : 'displayfield',
									fieldLabel : '燃料类型',
									id : 'fueltypename'
								},{
									xtype : 'displayfield',
									fieldLabel : '当前状态',
									id : 'nowstatusname'
								}]
		  				}, {
		  					columnWidth : .25,
		  					border:false,
		  					items : [{
									xtype : 'displayfield',
									fieldLabel : '发动机排量',
									id : 'enginecapacity'
								},{
									xtype : 'displayfield',
									fieldLabel : '排放标准',
									id : 'capacitystandard'
								},{
									xtype : 'displayfield',
									fieldLabel : '入户日期',
									id : 'entertime'
								},{
									xtype : 'displayfield',
									fieldLabel : '出厂日期',
									id : 'factorytime'
								}]
		  					},{
		  						columnWidth : .25,
		  						border:false,
		  						items : [{
									xtype : 'displayfield',
									fieldLabel : '财产险',
									id : 'proinsure'
								},{
									xtype : 'displayfield',
									fieldLabel : '交强险',
									id : 'accinsure'
								},{
									xtype : 'displayfield',
									fieldLabel : '乘座险',
									id : 'ridinsure'
								},{
									xtype : 'displayfield',
									fieldLabel : '三责险',
									id : 'cominsure'
								}]
							},{
		  						columnWidth : .25,
		  						border:false,
		  						items : [{
									xtype : 'displayfield',
									fieldLabel : '合同承包期(年)',
									labelWidth:90,
									id : 'contracttime'
								},{
									xtype : 'displayfield',
									fieldLabel : '经营性质',
									id : 'management_nature'
								},{
									xtype : 'displayfield',
									fieldLabel : '电子标签状态',
									id : 'electagstatusname'
								},{
									xtype : 'displayfield',
									fieldLabel : '车损险',
									id : 'dlwinsure'
								}]
							}]
		                }]
		          },{
		              "xtype": "fieldset",
		              "title": "<b><font style='color:green'>当班司机信息 </font></b>",
		              fieldDefaults: {
		              	buttonAlign : 'left',
			            labelAlign : 'right',
		  	        	labelWidth: 80
		  	   		 },
		  		   items : [{
		  			layout : 'column',
		  			columnWidth : 1,
		  			border:false,
		              items : [{
		  						columnWidth : .25,
		  						border:false,
		  						items : [{
									xtype : 'displayfield',
									fieldLabel : '司机姓名',
									id : 'drivername'
								},{
									xtype : 'displayfield',
									fieldLabel : '身份证',
									id : 'idnumber'
								}]
		  					},{
		  					columnWidth : .25,
		  					border:false,
		  					items : [{
									xtype : 'displayfield',
									fieldLabel : '手机号',
									id : 'driverphone'
								}]
		  					},{
		  						columnWidth : .25,
		  						border:false,
		  						items : [{
									xtype : 'displayfield',
									fieldLabel : '司机代码',
									id : 'drivercode'
								}]
							},{
		  						columnWidth : .25,
		  						border:false,
		  						items : [{
									xtype : 'displayfield',
									fieldLabel : '性别',
									id : 'driversex'
								}]
							}]
		                }]
		          }
		      ]
	   }]
});
