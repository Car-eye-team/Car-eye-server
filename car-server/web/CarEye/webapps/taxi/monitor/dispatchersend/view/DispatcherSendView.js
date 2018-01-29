Ext.define('DispatcherSendApp.view.DispatcherSendView', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.dispatcherSendView',
			id:'dispatcherSendgrid',
			title: '调度发送信息',
			width : 500,
			collapsible: true,
			collapseMode: "mini",
            split: true,
			frame: true,
			multiSelect: true,
			autoScroll:true,
			region : "east",
			layout : 'form',
			overflowY:'auto',
			items : [{
						xtype : 'form',
						id : 'dispatcherSendForm',
						anchor : '100%',
						border : true,
						collapsible : false,
						buttonAlign : 'right',
						fieldDefaults : {
							labelAlign : 'left',
							labelWidth : 220,
							margin : '5 0 5 30'
						},
						items : [{
									xtype : 'panel',
									border : false,
									id : 'form_16',
									//title : '调度信息发送管理',
									items : [
										{
												xtype : 'hidden',
												id : 'id',
												name:'dispatcherRecord.id'
											},{
												xtype : 'hidden',
												id : 'carids',
												name:'dispatcherRecord.carids'
											},
//												{
//												xtype : 'hidden',
//												id : 'emergency',
//												name:'dispatcherRecord.emergency'
//											},
//											{
//												xtype : 'hidden',
//												id : 'lcd',
//												name:'dispatcherRecord.lcd'
//											},
//											{
//												xtype : 'hidden',
//												id : 'tts',
//												name:'dispatcherRecord.tts'
//											},
//											{
//												xtype : 'hidden',
//												id : 'adv',
//												name:'dispatcherRecord.adv'
//											},
//											{
//												xtype : 'hidden',
//												id : 'action',
//												name:'dispatcherRecord.action'
//											},
//											{
//												xtype : 'combo',
//												width : 260,
//												name : 'dispatcherRecord.type',
//												id:'type',
//												itemId:'type',
//												store :"TypeStore",
//												editable: false,
//												valueField : 'id',
//												displayField : 'type',
//												labelWidth : 60,
//												blankText : '请选择',
//												allowBlank : false,
//												fieldLabel : '<font color="red">*</font>调度类型',
//												emptyText:'请选择'
//											},
											{
												xtype : "displayfield",
												fieldLabel : '<font color="red">*</font>调度内容'
											}, {
												xtype : "textarea",
												id:'remark',
												name:'dispatcherRecord.remark',
												rows :10,
												width:420,
												allowBlank : false,
							                	blankText : '不能为空',
												minLength : 2,
												minLengthText : '最小长度不小于2位!',
												maxLength : 521,
												maxLengthText : '最大长度不超过521位!',
												cls : 'x-textfield',
								                validator : vd
											} , {
												xtype : "displayfield",
												fieldLabel : '消息类型'
											},{
											    xtype: "checkboxgroup",
											    columns: 3,
											    labelAlign : 'left',
											    labelWidth : 150,
											    width:410,
											    items: [
											        { boxLabel: "紧急", name: "emergency",id: 'emergency', itemId : 'emergency' },
											        { boxLabel: "终端显示器显示", name: "lcd", id: 'lcd',itemId : 'lcd' },
											        { boxLabel: "终端TTS播读", name: "tts", id: 'tts',itemId : 'tts' },
											        { boxLabel: "广告屏显示", name: "adv",id: 'adv',itemId : 'adv' },
											        { boxLabel: "电召消息", name: "action",id: 'action',itemId : 'action' }
											    ]
											},
												{
												xtype : "displayfield",
												fieldLabel : '设为预设'
											},{
											    xtype: "checkboxgroup",
											    columns: 3,
											    labelAlign : 'left',
											    labelWidth : 150,
											    width:210,
											    items: [
											    {
										         boxLabel: '是',xtype: 'checkbox',name: 'flag',id: 'flag', itemId : 'flag'}
											     ]},
										      {
												xtype : 'button',
												margin : '5 0 5 250',
												//action : 'send',
												text : '发送',
												 //action : 'export',
								                 handler: function(button){
		                                            var con = Ext.create("DispatcherSendApp.controller.DispatcherSendCtrl");
		                                            con.sendDsipatherSend(button);
		                                         }
											  }
											]
								}]
					}]
		});