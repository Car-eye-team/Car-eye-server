Ext.define('DialRuleApp.view.ParameterSetView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.parameterSetView',
	frame : true,
	title : '参数设置',
	split : true,
	region : "center",
	layout : 'form',
	autoSrcoll : true,
	overflowY : 'auto',
	items : [{
		xtype : 'form',
		id : 'parameterSet_Form',
		anchor : '100%',
		border : false,
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults : {
			labelAlign : 'right',
			labelWidth : 200,
			margin : '5 0 5 30'
		},
		items : [{
					xtype : 'hidden',
					id : 'dr_type',
					name : 'dialRule.type'
				}, {
					xtype : 'form',
					border : false,
					id : 'form_jsdd',
					items : [{
						xtype : 'panel',
						border : false,
						layout : {
							type : 'table',
							columns : 2
						},
						items : [{// 9个item
							xtype : "numberfield",
							id : 'jsdd_radius',
							// name : 'jsdd_radius',
							fieldLabel : '<font color="red">*</font>半径范围大小(米)',
//							labelAlign : 'left',
							labelWidth : 150,
							allowBlank : false,
							blankText : '不能为空'
						}, {
							xtype : 'combo',
							fieldLabel : '<font color="red">*</font>车辆类型',
							labelWidth : 150,
//							labelAlign : 'left',
							id : 'jsdd_cartype',
							multiSelect : true,
							// name : 'jsdd_cartype',
							editable : false,
							allowBlank : false,
							store : Ext.create('Ext.data.Store', {
										fields : ['val', 'display'],
										data : [{
													"val" : "1",
													"display" : "车辆管理"
												}]
									}),
							displayField : 'display',
							valueField : 'val',
							value : "1"
						}, {
							xtype : "numberfield",
							id : 'jsdd_carcount',
							// name : 'jsdd_carcount',
							fieldLabel : '<font color="red">*</font>选择车辆数量(辆)',
//							labelAlign : 'left',
							labelWidth : 150,
							allowBlank : false,
							blankText : '不能为空'
						}, {
							xtype : 'combo',
							fieldLabel : '<font color="red">*</font>车辆调度状态',
							labelWidth : 150,
//							labelAlign : 'left',
							id : 'jsdd_carstatus',
							allowBlank : false,
							multiSelect : true,
							// name : 'jsdd_carstatus',
							editable : false,
							listeners : {
								'beforeshow' : {
									fn : function(cmp,opt) {
										if (cmp.getRawValue() != null) {
											return cmp.getRawValue().split(',');
										}
									}
								}
							},
							store : Ext.create('Ext.data.Store', {
										fields : ['val', 'display'],
										data : [{
													"val" : "1",
													"display" : "未调度"
												}, {
													"val" : '2',
													"display" : "调度中"
												}, {
													"val" : '3',
													"display" : "已调度"
												}]
									}),
							displayField : 'display',
							valueField : 'val'
						}, {
							xtype : "numberfield",
							id : 'jsdd_assigncount',
							// name : 'jsdd_assigncount',
							fieldLabel : '<font color="red">*</font>调派轮次(轮)',
//							labelAlign : 'left',
							labelWidth : 150,
							allowBlank : false,
							blankText : '不能为空'
						}, {
							xtype : 'combo',
							fieldLabel : '<font color="red">*</font>空重车状态',
							labelWidth : 150,
//							labelAlign : 'left',
							id : 'jsdd_zkstate',
							// name : 'jsdd_zkstate',
							multiSelect : true,
							editable : false,
							allowBlank : false,
							store : Ext.create('Ext.data.Store', {
										fields : ['val', 'display'],
										data : [{
													"val" : '0',
													"display" : "空车"
												}, {
													"val" : '1',
													"display" : "重车"
												}]
									}),
							displayField : 'display',
							valueField : 'val'
						}, {
							xtype : "numberfield",
							id : 'jsdd_assignmin',
							// name : 'jsdd_assignmin',
							fieldLabel : '<font color="red">*</font>调派间隔(秒)',
//							labelAlign : 'left',
							labelWidth : 150,
							allowBlank : false,
							blankText : '不能为空'
						}, {
							xtype : 'combo',
							fieldLabel : '<font color="red">*</font>欠费是否可调度',
							labelWidth : 150,
//							labelAlign : 'left',
							id : 'jsdd_arrearage',
							// name : 'jsdd_arrearage',
							editable : false,
							allowBlank : false,
							store : Ext.create('Ext.data.Store', {
										fields : ['val', 'display'],
										data : [{
													"val" : 1,
													"display" : "可调度"
												}, {
													"val" : 2,
													"display" : "不可调度"
												}]
									}),
							displayField : 'display',
							valueField : 'val',
							value : 2
						}, {
//							xtype : "datetimefield",
//							id : 'jsdd_effecttime',
//							// name : 'jsdd_effecttime',
//							fieldLabel : '<font color="red">*</font>生效时间',
////							labelAlign : 'left',
//							format : "Y-m-d",
//							labelWidth : 150,
//							editable : false,
//							allowBlank : false,
//							blankText : '不能为空'
//						}, {
							xtype : 'combo',
							fieldLabel : '<font color="red">*</font>违约是否可调度',
							labelWidth : 150,
//							labelAlign : 'left',
							id : 'jsdd_breach',
							// name : 'jsdd_breach',
							editable : false,
							allowBlank : false,
							store : Ext.create('Ext.data.Store', {
										fields : ['val', 'display'],
										data : [{
													"val" : 1,
													"display" : "可调度"
												}, {
													"val" : 2,
													"display" : "不可调度"
												}]
									}),
							displayField : 'display',
							valueField : 'val',
							value : 2
						}, {
							xtype : "numberfield",
							id : 'jsdd_effectmin',
							// name : 'jsdd_effectmin',
							fieldLabel : '<font color="red">*</font>几分钟后生效(0到10)',
//							labelAlign : 'left',
							allowBlank : false,
							value:0,
							labelWidth : 150,
//							listeners : {
//								blur : function(cmp) {
//									Ext.getCmp('jsdd_effecttime')
//											.setValue(new Date(new Date()
//													.getTime()
//													+ cmp.getValue() * 60000));
//								}
//							},
							maxValue : 10,
							minValue : 0
								// allowBlank : false,
								// blankText : '不能为空'
						}, {
							xtype : 'combo',
							fieldLabel : '<font color="red">*</font>黑名单是否可调度',
							labelWidth : 150,
//							labelAlign : 'left',
							id : 'jsdd_blacklist',
							// name : 'jsdd_blacklist',
							allowBlank : false,
							editable : false,
							store : Ext.create('Ext.data.Store', {
										fields : ['val', 'display'],
										data : [{
													"val" : 1,
													"display" : "可调度"
												}, {
													"val" : 2,
													"display" : "不可调度"
												}]
									}),
							displayField : 'display',
							valueField : 'val',
							value : 2
						}]
					}, {
						xtype : 'button',
						margin : '5 0 5 350',
						action : 'paramset',
						id : 'type_1',
						text : '设置'
					}, {
						xtype : "panel",
						border : false,
						items : [{
									xtype : 'jsddListView'
								}]
					}]
				}, {// 10个
					xtype : 'form',
					hidden : true,
					border : false,
					id : 'form_yydd',
					items : [{
						xtype : 'panel',
						border : false,
						layout : {
							type : 'table',
							columns : 2
						},
						items : [{
									xtype : "numberfield",
									id : 'yydd_radius',
									// name : 'yydd_radius',
									fieldLabel : '<font color="red">*</font>半径范围大小(米)',
//									labelAlign : 'left',
									labelWidth : 150,
									allowBlank : false,
									blankText : '不能为空'
								}, {
									xtype : 'combo',
									fieldLabel : '<font color="red">*</font>车辆类型',
									labelWidth : 150,
//									labelAlign : 'left',
									id : 'yydd_cartype',
									allowBlank : false,
									multiSelect : true,
									// name : 'yydd_cartype',
									editable : false,
									store : Ext.create('Ext.data.Store', {
												fields : ['val', 'display'],
												data : [{
															"val" : '1',
															"display" : "车辆管理"
														}]
											}),
									displayField : 'display',
									valueField : 'val',
									value : '1'
								}, {
									xtype : "numberfield",
									id : 'yydd_carcount',
									// name : 'yydd_carcount',
									fieldLabel : '<font color="red">*</font>选择车辆数量(辆)',
//									labelAlign : 'left',
									labelWidth : 150,
									allowBlank : false,
									blankText : '不能为空'
								}, {
									xtype : 'combo',
									fieldLabel : '<font color="red">*</font>车辆调度状态',
									labelWidth : 150,
//									labelAlign : 'left',
									id : 'yydd_carstatus',
									allowBlank : false,
									// name : 'yydd_carstatus',
									multiSelect : true,
									editable : false,
									store : Ext.create('Ext.data.Store', {
												fields : ['val', 'display'],
												data : [{
															"val" : "1",
															"display" : "未调度"
														}, {
															"val" : '2',
															"display" : "调度中"
														}, {
															"val" : '3',
															"display" : "已调度"
														}]
											}),
									displayField : 'display',
									valueField : 'val'
								}, {
									xtype : "numberfield",
									id : 'yydd_totalassigncount',
									// name : 'yydd_totalassigncount',
									fieldLabel : '<font color="red">*</font>总调派次数(次)',
//									labelAlign : 'left',
									labelWidth : 150,
									allowBlank : false,
									blankText : '不能为空'
								}, {
									xtype : 'combo',
									fieldLabel : '<font color="red">*</font>空重车状态',
									labelWidth : 150,
//									labelAlign : 'left',
									allowBlank : false,
									id : 'yydd_zkstate',
									// name : 'yydd_zkstate',
									multiSelect : true,
									editable : false,
									store : Ext.create('Ext.data.Store', {
												fields : ['val', 'display'],
												data : [{
															"val" : '0',
															"display" : "空车"
														}, {
															"val" : '1',
															"display" : "重车"
														}]
											}),
									displayField : 'display',
									valueField : 'val'
								}, {
									xtype : "numberfield",
									id : 'yydd_assignmin',
									fieldLabel : '<font color="red">*</font>调派间隔(秒)',
//									labelAlign : 'left',
									labelWidth : 150,
									allowBlank : false,
									blankText : '不能为空'
								}, {
									xtype : 'combo',
									fieldLabel : '<font color="red">*</font>欠费是否可调度',
									labelWidth : 150,
//									labelAlign : 'left',
									id : 'yydd_arrearage',
									allowBlank : false,
									// name : 'yydd_arrearage',
									editable : false,
									store : Ext.create('Ext.data.Store', {
												fields : ['val', 'display'],
												data : [{
															"val" : 1,
															"display" : "可调度"
														}, {
															"val" : 2,
															"display" : "不可调度"
														}]
											}),
									displayField : 'display',
									valueField : 'val',
									value : 2
								}, {
									xtype : "numberfield",
									id : 'yydd_aheadassignmin',
									// name : 'yydd_aheadassignmin',
									fieldLabel : '<font color="red">*</font>提前调派时间(分钟)',
//									labelAlign : 'left',
									labelWidth : 150,
									allowBlank : false,
									blankText : '不能为空'
								}, {
									xtype : 'combo',
									fieldLabel : '<font color="red">*</font>违约是否可调度',
									labelWidth : 150,
//									labelAlign : 'left',
									id : 'yydd_breach',
									allowBlank : false,
									// name : 'yydd_breach',
									editable : false,
									store : Ext.create('Ext.data.Store', {
												fields : ['val', 'display'],
												data : [{
															"val" : 1,
															"display" : "可调度"
														}, {
															"val" : 2,
															"display" : "不可调度"
														}]
											}),
									displayField : 'display',
									valueField : 'val',
									value : 2
								}, {
									xtype : "numberfield",
									id : 'yydd_immassigncount',
									// name : 'yydd_immassigncount',
									fieldLabel : '<font color="red">*</font>即时派送轮数(轮)',
//									labelAlign : 'left',
									labelWidth : 150,
									allowBlank : false,
									blankText : '不能为空'
								}, {
									xtype : 'combo',
									fieldLabel : '<font color="red">*</font>黑名单是否可调度',
									labelWidth : 150,
//									labelAlign : 'left',
									id : 'yydd_blacklist',
									// name : 'yydd_blacklist',
									allowBlank : false,
									editable : false,
									store : Ext.create('Ext.data.Store', {
												fields : ['val', 'display'],
												data : [{
															"val" : 1,
															"display" : "可调度"
														}, {
															"val" : 2,
															"display" : "不可调度"
														}]
											}),
									displayField : 'display',
									valueField : 'val',
									value : 2
//								}, {
//									xtype : "datetimefield",
//									id : 'yydd_effecttime',
//									// name : 'yydd_effecttime',
//									fieldLabel : '<font color="red">*</font>生效时间',
////									labelAlign : 'left',
//									labelWidth : 150,
//									format : "Y-m-d",
//									editable : false,
//									allowBlank : false,
//									blankText : '不能为空'
								}, {
									xtype : "numberfield",
									id : 'yydd_effectmin',
									// name : 'yydd_effectmin',
									fieldLabel : '<font color="red">*</font>几分钟后生效(0到10)',
//									labelAlign : 'left',
									labelWidth : 150,
									allowBlank : false,
									value:0,
									minValue : 0,
//									listeners : {
//										blur : function(cmp) {
//											Ext
//													.getCmp('yydd_effecttime')
//													.setValue(new Date(new Date()
//															.getTime()
//															+ cmp.getValue()
//															* 60000));
//										}
//									},
									maxValue : 10
								}]
					}, {
						xtype : 'button',
						margin : '5 0 5 350',
						action : 'paramset',
						id : 'type_2',
						text : '设置'
					}, {
						// 查询与列表
						xtype : "panel",
						border : false,
						items : [{
									xtype : 'yyddListView'
								}]
					}

					]
				}, {
					xtype : 'form',
					hidden : true,
					border : false,
					id : 'form_zpms',
					items : [{
						xtype : 'panel',
						border : false,
						layout : {
							type : 'table',
							columns : 2
						},
						items : [{
									xtype : "numberfield",
									id : 'zpms_radius',
									// name : 'zpms_radius',
									fieldLabel : '<font color="red">*</font>半径范围大小(米)',
//									labelAlign : 'left',
									labelWidth : 150,
									allowBlank : false,
									blankText : '不能为空'
								}, {
									xtype : 'combo',
									fieldLabel : '<font color="red">*</font>车辆调度状态',
									labelWidth : 150,
//									labelAlign : 'left',
									multiSelect : true,
									id : 'zpms_carstatus',
									// name : 'zpms_carstatus',
									allowBlank : false,
									editable : false,
									store : Ext.create('Ext.data.Store', {
												fields : ['val', 'display'],
												data : [{
															"val" : '1',
															"display" : "未调度"
														}, {
															"val" : '2',
															"display" : "调度中"
														}, {
															"val" : '3',
															"display" : "已调度"
														}]
											}),
									displayField : 'display',
									valueField : 'val'
								}, {
									xtype : "numberfield",
									id : 'zpms_carcount',
									// name : 'zpms_carcount',
									fieldLabel : '<font color="red">*</font>选择车辆数量',
//									labelAlign : 'left',
									labelWidth : 150,
									allowBlank : false,
									blankText : '不能为空'
								}, {
									xtype : 'combo',
									fieldLabel : '<font color="red">*</font>空重车状态',
									labelWidth : 150,
//									labelAlign : 'left',
									id : 'zpms_zkstate',
									// name : 'zpms_zkstate',
									allowBlank : false,
									multiSelect : true,
									editable : false,
									store : Ext.create('Ext.data.Store', {
												fields : ['val', 'display'],
												data : [{
															"val" : '0',
															"display" : "空车"
														}, {
															"val" : '1',
															"display" : "重车"
														}]
											}),
									displayField : 'display',
									valueField : 'val'
								}, {
									xtype : "numberfield",
									id : 'zpms_assignwaitmin',
									name : 'zpms_assignwaitmin',
									fieldLabel : '<font color="red">*</font>指派等待时长(分钟)',
//									labelAlign : 'left',
									labelWidth : 150,
									allowBlank : false,
									blankText : '不能为空'
								}, {
									xtype : 'combo',
									fieldLabel : '<font color="red">*</font>欠费是否可调度',
									labelWidth : 150,
//									labelAlign : 'left',
									id : 'zpms_arrearage',
									// name : 'zpms_arrearage',
									allowBlank : false,
									editable : false,
									store : Ext.create('Ext.data.Store', {
												fields : ['val', 'display'],
												data : [{
															"val" : 1,
															"display" : "可调度"
														}, {
															"val" : 2,
															"display" : "不可调度"
														}]
											}),
									displayField : 'display',
									valueField : 'val',
									value : 2
								}, {
									xtype : "numberfield",
									id : 'zpms_assignmin',
									// name : 'zpms_assignmin',
									fieldLabel : '<font color="red">*</font>调派间隔(秒)',
//									labelAlign : 'left',
									labelWidth : 150,
									allowBlank : false,
									blankText : '不能为空'
								}, {
									xtype : 'combo',
									fieldLabel : '<font color="red">*</font>违约是否可调度',
									labelWidth : 150,
//									labelAlign : 'left',
									id : 'zpms_breach',
									// name : 'zpms_breach',
									allowBlank : false,
									editable : false,
									store : Ext.create('Ext.data.Store', {
												fields : ['val', 'display'],
												data : [{
															"val" : 1,
															"display" : "可调度"
														}, {
															"val" : 2,
															"display" : "不可调度"
														}]
											}),
									displayField : 'display',
									valueField : 'val',
									value : 2
//								}, {
//									xtype : "datetimefield",
//									id : 'zpms_effecttime',
//									// name : 'zpms_effecttime',
//									fieldLabel : '<font color="red">*</font>生效时间',
////									labelAlign : 'left',
//									labelWidth : 150,
//									format : "Y-m-d",
//									editable : false,
//									allowBlank : false,
//									blankText : '不能为空'
								}, {
									xtype : 'combo',
									fieldLabel : '<font color="red">*</font>黑名单是否可调度',
									labelWidth : 150,
//									labelAlign : 'left',
									id : 'zpms_blacklist',
									// name : 'zpms_blacklist',
									allowBlank : false,
									editable : false,
									store : Ext.create('Ext.data.Store', {
												fields : ['val', 'display'],
												data : [{
															"val" : 1,
															"display" : "可调度"
														}, {
															"val" : 2,
															"display" : "不可调度"
														}]
											}),
									displayField : 'display',
									valueField : 'val',
									value : 2
								}, {
									xtype : "numberfield",
									id : 'zpms_effectmin',
									// name : 'zpms_effectmin',
									fieldLabel : '<font color="red">*</font>几分钟后生效(0到10)',
//									labelAlign : 'left',
									labelWidth : 150,
									allowBlank : false,
									value:0,
//									listeners : {
//										blur : function(cmp) {
//											Ext
//													.getCmp('zpms_effecttime')
//													.setValue(new Date(new Date()
//															.getTime()
//															+ cmp.getValue()
//															* 60000));
//										}
//									},
									minValue : 0,
									maxValue : 10
								}]
					}, {
						xtype : 'button',
						margin : '5 0 5 350',
						action : 'paramset',
						id : 'type_3',
						text : '设置'
					}, {
						// 查询与列表
						xtype : "panel",
						border : false,
						items : [{
									xtype : 'zpmsListView'
								}]
					}]
				}, {
					xtype : 'form',
					hidden : true,
					border : false,
					id : 'form_zdd',
					items : [{
						xtype : 'panel',
						border : false,
						layout : {
							type : 'table',
							columns : 2
						},
						items : [{
									xtype : "numberfield",
									id : 'zdd_traincount',
									// name : 'zdd_traincount',
									fieldLabel : '<font color="red">*</font>约车数量(辆)',
//									labelAlign : 'left',
									labelWidth : 150,
									allowBlank : false,
									blankText : '不能为空'
								}, {
									xtype : "datetimefield",
									id : 'zdd_stime',
									// name : 'zdd_stime',
									fieldLabel : '<font color="red">*</font>加入开始时间',
//									labelAlign : 'left',
									labelWidth : 150,
									format : "Y-m-d",
									editable : false,
									allowBlank : false,
									blankText : '不能为空'
								}, {
									xtype : "datetimefield",
									id : 'zdd_etime',
									// name : 'zdd_etime',
									fieldLabel : '<font color="red">*</font>加入结束时间',
//									labelAlign : 'left',
									format : "Y-m-d",
									labelWidth : 150,
									editable : false,
									allowBlank : false,
									blankText : '不能为空'
								}, {
									xtype : "textfield",
									id : 'zdd_principle',
									// name : 'zdd_principle',
									fieldLabel : '<font color="red">*</font>优先原则',
//									labelAlign : 'left',
									labelWidth : 150,
									allowBlank : false,
									blankText : '不能为空'
								}]
					}, {
						xtype : 'button',
						margin : '5 0 5 350',
						action : 'paramset',
						id : 'type_4',
						text : '设置'
					}, {
						xtype : "panel",
						border : false,
						items : [{
									xtype : 'zddListView'
								}]
					}

					]
				}]
	}]
});