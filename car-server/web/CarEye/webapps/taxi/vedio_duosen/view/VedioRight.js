Ext.define('VedioDuosenApp.view.VedioRight',{
			extend:'Ext.grid.Panel',
			alias:'widget.vedioRight',
			id: 'vedioRight',
			region: 'east',
	        title: '视频回放',
	        //collapsed: true,
		    collapsible: true,
			markDirty : false,
			split: true,
		    collapseMode: "mini",
		    store: "PlaybackSearchFileStore",
	        width: 280,
	        border:true,
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
//			    new Ext.grid.RowNumberer(),
			    { header: '通道',width:40, dataIndex: 'id', hidden: true },
				{ header: '录像文件名称',width:130, dataIndex: 'filename', sortable: true },
				{ header: '录像时间',width:130, dataIndex: 'videotime', sortable: true },
				{ header: '开始时间(秒)', width:80, dataIndex: 'splaysec', sortable: true },
				{ header: '结束时间(秒)', width:80, dataIndex: 'eplaysec', sortable: true },
				{ header: '', width:0, dataIndex: 'eid', hidden: true }
				],
				dockedItems: [{
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [{
										xtype : 'combo',
										width : 120,
										maxLength : 20,
										id : 'v_id',
										store :"ChannelStore",
										editable: false,
										valueField : 'id',
										displayField : 'value',
										fieldLabel : '通道',
										labelWidth: 30,
										labelAlign: 'right',
										blankText : '通道不能为空',
										allowBlank : false
									},{
										xtype : 'combo',
										width : 150,
										maxLength : 20,
										id : 'v_type',
										store :"FileTypeStore",
										editable: false,
										valueField : 'id',
										displayField : 'value',
										fieldLabel : '类型',
										labelWidth: 60,
										labelAlign: 'right',
										value: '1',
										allowBlank : false
									}
			                  ]
			              },{
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [{
											xtype : 'datetimefield',
											width :150,
											id : 'v_stime',
											maxLength : 20,
											fieldLabel : '时间',
											labelWidth: 30,
											labelAlign: 'right'
										},{
											xtype : 'datetimefield',
											width : 120,
											id : 'v_etime',
											maxLength : 20,
											labelAlign: 'right'
										}
			                  ]
			              
			              },{
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [{
											xtype: 'button',
											text : '视频搜索回放',
											id : 'mailset_query_ques_bootm',
											tooltip : '查询',
											iconCls : 'common-search-icon',
//											action: 'searchbottom'
											handler: function(button){
												clearflag = true;//回放查询时是否清空列表状态
									        	queryPlaybackList();
									        }
										},{
										    text : '重置',
										    tooltip : '清空查询条件',
										    iconCls : 'common-reset-icon',
										    action : 'reset',
									        	handler: function(button){
									        	Ext.getCmp('v_id').setValue("");
									        	Ext.getCmp('v_type').setValue("");
									        	Ext.getCmp('v_stime').setValue("");
									        	Ext.getCmp('v_etime').setValue("");
									        }
									    },'->',{
									    	html : "<div id='commserver'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>"
										}
			                  ]
			              
			              },{
			                  xtype: 'toolbar',
			                  dock: 'top',
						      layout: {
						        overflowHandler: 'Menu'
						      }, 
			                  items: [{
											xtype: 'button',
											text : '开始回放',
//											tooltip : '开始预览',
											iconCls : 'icon_recovery',
//											action : 'startRecord',
											handler: function() {
												var conn = Ext.create('VedioDuosenApp.controller.VedioDuosenCtrl');
												conn.startRecord();
												
							                }
										},"-",
										{
											xtype: 'button',
											text : '停止回放',
//											tooltip : '停止预览',
											iconCls : 'icon_pause',
//											action : 'stopRecord'
											handler: function() {
												var id = Ext.getCmp('v_id').getValue();
												if(id != null && id != ""){
													stopRecord(id);
												}else{
													alert("请选择需要视频的通道!");
												}
							                }
//										},"-"
//										{
//											xtype: 'button',
//											text : '下载',
//											tooltip : '下载',
//											iconCls : 'icon_recovery',
//											action: 'download'
										}
										]
			              }
			          ],
				listeners : { 
//					'itemclick' : function(view, record, item, index, event){
//					        var id = record.data.id;
//					        var filename = record.data.filename;
//					        var splaysec = record.data.splaysec;
//					        var eplaysec = record.data.eplaysec;
//					        alert('id='+id+',filename='+filename+',splaysec='+splaysec+',eplaysec='+eplaysec);
//							vedioStartPlaybackByFile(id,filename,splaysec,eplaysec);
//							return false;  
//					},
	    			'collapse': {
		                fn: function(){
		                	//Ext.getCmp('fmsMapPanel').setSize(Ext.getCmp('fmsMapPanel').getSize().width+220,Ext.getCmp('fmsMapPanel').getSize().height);
		                	
		                	Ext.getCmp('vediopanel').setSize(Ext.getBody().getViewSize().width*0.5,Ext.getCmp('vediopanel').getSize().height);
		                	Ext.getCmp('vediopanel1').setSize(Ext.getBody().getViewSize().width*0.5,Ext.getCmp('vediopanel1').getSize().height);
		                	
		                	Ext.getCmp('vediopanel_1').setSize(Ext.getBody().getViewSize().width*0.49,Ext.getCmp('vediopanel_1').getSize().height);
		                	Ext.getCmp('vediopanel1_1').setSize(Ext.getBody().getViewSize().width*0.49,Ext.getCmp('vediopanel1_1').getSize().height);
		                	
		                }
	                },
	                'expand':{
		                fn: function(){
		                	//$("#ppvs1").css("width","600");
		                	
		                	Ext.getCmp('vediopanel').setSize((Ext.getBody().getViewSize().width-280)*0.5,Ext.getCmp('vediopanel').getSize().height);
		                	Ext.getCmp('vediopanel1').setSize((Ext.getBody().getViewSize().width-280)*0.5,Ext.getCmp('vediopanel1').getSize().height);
		                
		                	Ext.getCmp('vediopanel_1').setSize((Ext.getBody().getViewSize().width-280)*0.5,Ext.getCmp('vediopanel_1').getSize().height);
		                	Ext.getCmp('vediopanel1_1').setSize((Ext.getBody().getViewSize().width-280)*0.5,Ext.getCmp('vediopanel1_1').getSize().height);
		                }          	
	                }
           
				}
		}
);