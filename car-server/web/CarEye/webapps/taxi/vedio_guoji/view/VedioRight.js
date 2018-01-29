Ext.define('VedioGuojiApp.view.VedioRight',{
			extend:'Ext.grid.Panel',
			alias:'widget.vedioRight',
			region: 'east',
	        title: '视频回放',
	        //collapsed: true,
		    collapsible: true,
			markDirty : false,
			split: true,
		    collapseMode: "mini",
		    store: "",
	        width: 280,
	        border:true,
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			//selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			    new Ext.grid.RowNumberer(),
			    { header: '通道',width:120, dataIndex: 'lChannelNumber', hidden: true },
				{ header: '录像文件名称',width:150, dataIndex: 'sFileName', sortable: true },
				{ header: '文件大小', width:150, dataIndex: 'dwFileSize',sortable: true },
				{ header: '开始时间', width:150, dataIndex: 'struStartTime', sortable: true },
				{ header: '结束时间', width:150, dataIndex: 'struStopTime', sortable: true }
				],
				dockedItems: [{
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [{
										xtype : 'combo',
										width : 120,
										maxLength : 20,
										id : 'v_channel',
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
										id : 'v_fileType',
										store :"FileTypeStore",
										editable: false,
										valueField : 'id',
										displayField : 'value',
										fieldLabel : '文件类型',
										labelWidth: 60,
										labelAlign: 'right'
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
											text : '录像搜索回放',
											id : 'mailset_query_ques_bootm',
											tooltip : '查询',
											iconCls : 'common-search-icon',
											action: 'searchbottom'
										},{
										    text : '重置',
										    tooltip : '清空查询条件',
										    iconCls : 'common-reset-icon',
										    action : 'reset',
									        	handler: function(button){
									        	Ext.getCmp('v_channel').setValue("");
									        	Ext.getCmp('v_fileType').setValue("");
									        	Ext.getCmp('v_stime').setValue("");
									        	Ext.getCmp('v_etime').setValue("");
									        }
										},{
											xtype: 'button',
											text : '下载录像',
											tooltip : '查询',
											iconCls : 'icon_recovery',
											action: 'download'
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
											text : '抓拍图片',
											tooltip : '抓拍图片',
											iconCls : 'icon_recovery',
											handler: function() {
												var lChannelNumber = Ext.getCmp('v_channel').getValue();
												if(lChannelNumber != null && lChannelNumber != ""){
													CapturePicture(lChannelNumber-1);
												}else{
													CaptureAllWindowPicture();
												}
							                }
										},"-",
										{
											xtype: 'button',
											text : '开始录像',
											tooltip : '开始录像',
											iconCls : 'icon_recovery',
											handler: function() {
												var lChannelNumber = Ext.getCmp('v_channel').getValue();
												if(lChannelNumber != null && lChannelNumber != ""){
													StartRecord(lChannelNumber);
												}else{
													alert("请选择需要录像的通道!");
												}
												
							                }
										},"-",
										{
											xtype: 'button',
											text : '停止录像',
											tooltip : '停止录像',
											iconCls : 'icon_pause',
											handler: function() {
												var lChannelNumber = Ext.getCmp('v_channel').getValue();
												if(lChannelNumber != null && lChannelNumber != ""){
													StopRecord(lChannelNumber);
												}else{
													alert("请选择需要录像的通道!");
												}
							                }
										},"-"
//										{
//											xtype: 'button',
//											text : '下载',
//											tooltip : '下载',
//											iconCls : 'icon_recovery',
//											action: 'download'
//										}
										]
			              }
			          ],
				listeners : { 
					'itemclick' : function(view, record, item, index, event){
					        var lChannelNumber = record.data.lChannelNumber;
					        var lpFileName=record.data.sFileName;
					        var lFileSize =record.data.dwFileSize;
							vedioStartPlaybackByFile(lChannelNumber,lpFileName,lFileSize);
							return false;  
					},
	    			'collapse': {
		                fn: function(){
		                	//Ext.getCmp('fmsMapPanel').setSize(Ext.getCmp('fmsMapPanel').getSize().width+220,Ext.getCmp('fmsMapPanel').getSize().height);
		                }
	                },
	                'expand':{
		                fn: function(){
		                	//$("#ppvs1").css("width","600");
		                }          	
	                }
           
				}
		}
);