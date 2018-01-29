Ext.define('FMS.view.Left',{
			extend:'Ext.panel.Panel',
			alias:'widget.left',
			region: 'west',
			title:'车辆列表',
            id: 'west-panel',
            collapsed :collapsed,
            collapsible: true,
            collapseMode: "mini",
            header : false,
            split: true,
            width: 320,
            minSize: 175,
            maxSize: 275,
            layout: 'fit',
            margins: '0 0 2 2',
            tbar:{
        	layout: {
		     overflowHandler: 'Menu'
		    },
            items:[ {
				xtype : 'textfield',
				emptyText : '企业',
				id : 'car_blocid',
				width:100,
				editable: true,
				cls : 'x-textfield'
            },{
            	xtype : 'textfield',
				width : 80,
				emptyText: '车牌号码',
				itemId : 'carnumber',
				id : 'left_carnumber',
				labelAlign: 'right'
            },{
				xtype: 'button',
				tooltip : '查询',
				iconCls : 'common-search-icon',
				handler:function(){
					var editdeptname = Ext.getCmp('car_blocid').getValue();
					var carnumber = Ext.getCmp('left_carnumber').getValue();
					if(admin.blocid == 1){
						if(editdeptname == null || editdeptname == "" || editdeptname == "组织机构"){
							if(carnumber == null || carnumber == ""){
								Ext.Msg.alert("提示","请选择组织机构或者车牌号");
								return;
							}
						}
					}
					var store = Ext.StoreManager.get('CarTreeStore');
					var loadMarsk = new Ext.LoadMask('west-panel', {  
					    msg:'数据加载中，请稍候......',    
					    disabled:false,    
					    store:store  
					});  
					
					store.clearFilter(true);
					store.on('beforeload', function (store, options) {
			            var new_params = { 
			            	carnumber: encodeURI(carnumber),
			            	flag :1,
			            	editdeptname:editdeptname
			            };
			            Ext.apply(store.proxy.extraParams, new_params);
			            loadMarsk.show();
			        });
			        
			        store.reload();
			        
//			        if(admin.blocid == 1 || admin.deptcount > 1){	//除物流公司无下级其它都清除
						var terminalPositionStore = Ext.StoreManager.get('TerminalPositionStore');
			        	terminalPositionStore.removeAll();
			        
			        	map.clearOverlays();
//				    }
				    
				    hasquery = true;	//已查询
				}
            },{
            	xtype: 'button',
	    		tooltip : '清空查询条件',
	    		iconCls : 'common-reset-icon',
        		handler: function(button){
        			//重置查询条件
			        Ext.getCmp('car_blocid').setValue();
			        Ext.getCmp('left_carnumber').setValue();
       		    }
			},"-",{
				 iconCls: 'icon_expand_all',
			     tooltip: '打开',
			     handler: function () {
			     	if((!hasquery) || (admin.blocid ==1 || admin.deptcount > 1)){
				        Ext.getCmp('tree-panel').expandAll();
			     	}
			     }
			},"-",{
				 iconCls: 'icon_collapse_all',
			     tooltip: '折叠',
			     handler: function () {
			     	if((!hasquery) || (admin.blocid ==1 || admin.deptcount > 1)){
				        Ext.getCmp('tree-panel').collapseAll();
			     	}
			     }
			}
			]}, 
			items:[{
			    xtype: 'tabpanel',
			    activeTab:0,
			    id: 'bustabpanel',
			    border: false,
				items:[{
				       border: false,
			    	   title:'实时车辆',
			    	   layout: 'fit',
   					   id: 'tabBusTree',
			    	   items:[{
			    	        xtype: 'treepanel',
			    	        border: false,
			    	        id: 'tree-panel',
			    	        bodyStyle : 'overflow-x:auto; overflow-y:auto',
			    	   		store: 'CarTreeStore',
			    	   		viewConfig : {   //checkbox联动
						        onCheckboxChange : function(e, t) {
						        	  
							          var item = e.getTarget(this.getItemSelector(), this.getTargetEl()), record;
							          if (item){
							          	Ext.getCmp('east-panel').collapse();
								           record = this.getRecord(item);
								           var check = !record.get('checked');
								           record.set('checked', check);
								           
								           if (check) {
									            record.bubble(function(parentNode) {
										             parentNode.set('checked', true);
										             parentNode.expand(false, true);
									            });
									            record.cascadeBy(function(node) {
										             node.set('checked', true);
										             node.expand(false, true);
									            });
//									            if(admin.blocid == 1 || admin.deptcount > 1){
										            var control = Ext.create('FMS.controller.Controllers');
										            var checkflag = false;  //处理当车辆节点未展开时获取数据问题
										            var caridlist = [];
										            //先清除父节点下面的子节点标注
										            record.cascadeBy(function(node) {
										            	if(node.get('hrefTarget') == null || node.get('hrefTarget') == ""){
										            		
										            	}else{
										            		 //清除地图显示
				  											markermap.remove(node.get('hrefTarget'));
				  											checkflag = true;
				  											
				  											//往车辆value里面赋值推送到后台
				  											caridlist.push(node.get('id').substring(1));
										            	}
								                		
										            });
										            if(checkflag){
									            	 //父节点下面所有选中节点地图标注
									            		control.queryManyCarByParent();
									            		
									            		//选中车辆推送到后台
									            		realTimeCheckedCar(1,caridlist.toString(),400);
									            	}
//									            }
									            
								           } else {
								           	    var caridlist = [];
									            record.cascadeBy(function(node) {
									            	 node.set('checked', false);
									            	 
													//当为企业用户登陆时取消全选不删除地图点跟实时位置列表
//									            	if(admin.blocid == 1 || admin.deptcount > 1){
									           		    
										            	 //清除地图显示
				  										if(maptype == 1){//百度
				  											map.clearOverlays();
								                			markermap.remove(node.get('hrefTarget'));
								                		}else{//高德
								                			mapObj.clearMap();
								                			markermap.remove(node.get('hrefTarget'));
								                		}

								                		 
								                		 //清空实时位置列表对应信息
					   	    							 var store = Ext.StoreManager.get('TerminalPositionStore');
								                		 for(var i=0;i<store.getCount();i++){
															if(store.getAt(i).data.carid == node.get('id').substring(1)){
																store.removeAt(i);
																
																//往车辆value里面赋值推送到后台
				  											    caridlist.push(node.get('id').substring(1));
																
																break; 
															}
														 }
														 
//								           			} 
									            });
									            
									            if(caridlist.length > 0){
									            	//选中车辆推送到后台
									                realTimeCheckedCar(2,caridlist.toString(),500);
									            }
									            
								           }
							          }
							     },
								markDirty : false
					        },
				   		    rootVisible: false,
				   		    listeners: {
				    			'itemcontextmenu': {
					                fn: function(view, record, item, index, event) {
					                	//禁用浏览器的右键相应事件
					                	 event.preventDefault();
					                     event.stopEvent();
					                     
					                     var tabPanel = Ext.getCmp("taxiMapPanel");
					                     tabPanel.setActiveTab("taximap");
					                     
					                     var control = Ext.create('FMS.controller.Controllers');  
										if (record.get('leaf') && record.get('id').indexOf('C') == 0) { // 车辆叶子节点
											var tree = Ext.getCmp("tree-panel");
					                		var nodes = tree.getChecked();
											if(nodes && nodes.length > 0){
												for(var i=0;i<nodes.length;i++){
													nodes[i].set('checked',false);
												}
											}
					                		record.set('checked',true);
					                		
					                        var menu = new Ext.menu.Menu({
						                            //控制右键菜单位置
						                            //float:true,
						                            items:[{
						                                text:"获取当前位置",
						                                iconCls:'leaf',
						                                hidden:hidderContent('130106'),
						                                handler:function(it,e){
						                                    this.up("menu").hide();
						                                    //扩展ie不支持contains方法
						                                    Array.prototype.contains = function(item){
																return RegExp("\\b"+item+"\\b").test(this);
															}; 
					                                    	if(record.get('text').indexOf("离线") > 0){
															    Ext.Msg.alert("当前车辆离线","显示最后一次有效位置信息!",function(){  
														              var carnumer = record.get('hrefTarget');
																	  control.loadCarPosition(carnumer,1); 
													            });  
					                                    		
					                                    	}else{
																checkVehiclePosition(admin.id,admin.blocid,admin.username,record.get('id').substring(1),100);
					                                    	}
						                                }
						                            },{
						                                text:"车辆定时跟踪",
						                                iconCls:'leaf',
						                                hidden:hidderContent('130107'),
						                                handler:function(it,e){
								                            Ext.create('Ext.window.Window', {
															    title: '车辆定时跟踪',
															    id:'timetrackwin',
															    width: 350,
															    animCollapse:false,
																constrain : true,
																constrainHeader : true,
																maximizable : false,
																minimizable : false,
															    closable : true,
															    layout: 'fit',
															    items: {  
															        xtype: 'form',
															        id:'timetrack_form',
															        border:false,
															        items : [ {
																		xtype : "numberfield",
																		fieldLabel : "请输入监控时长(分钟)",
																		id : "tt_expire",
																		labelWidth : 130,
																		allowBlank : false,
																		labelAlign: 'right',
																		width: 320,
																		margin : '10 auto',
																		blankText : "不能为空,只能输入正整数",
																		regex : /^[0-9]{0,10}?$/,
																		maxValue: 99,
																		minValue: 1,
																		value: 1
																	}, {
																		xtype : "numberfield",
																		fieldLabel : "请输入间隔时间(秒)",
																		id : "tt_inv",
																		labelWidth : 130,
																		labelAlign: 'right',
																		width: 320,
																		margin : '10 auto',
																		allowBlank : false,
																		blankText : "不能为空,只能输入正整数",
																		regex : /^[0-9]{0,10}?$/,
																		maxValue: 99,
																		minValue: 1,
																		value: 1
																	}, {
																		xtype : "textfield",
																		fieldLabel : "请输入距离间隔(米)",
																		id : "tt_distance",
																		labelWidth : 130,
																		labelAlign: 'right',
																		width: 320,
																		margin : '10 auto',
																		allowBlank : false,
																		blankText : "不能为空,只能输入正整数",
																		regex : /^\d+$/,
																		value: 1
																	}, {
																		xtype : "textfield",
																		fieldLabel : "请输入位置跟踪有效距离(米)",
																		id : "tt_effdistance",
																		labelWidth : 130,
																		labelAlign: 'right',
																		width: 320,
																		margin : '10 auto',
																		allowBlank : false,
																		blankText : "不能为空,只能输入正整数",
																		regex : /^\d+$/,
																		value: 1
																	}, {
																		xtype : 'combo',
																		width : 320,
																		fieldLabel : '跟踪方式',
																		id : "tt_way",
																		labelWidth : 130,
																		labelAlign: 'right',
																		margin : '10 auto',
																		allowBlank : false,
																		blankText : '请选择',
																		editable: false,
																		store : Ext.create('Ext.data.Store', {
																		    fields: ['type', 'name'],
																		    data : [
																		        {"type":"0", "name":"按时间间隔、持续时间"},
																		        {"type":"1", "name":"按时间间隔、持续距离"}
																		    ]
																		}),
																		displayField : 'name',
																		value : '0',
																		valueField : 'type'
																	} ]
															    },
															    buttons: [{
																	text: '确定',
																	iconCls : 'edit',
																	handler: function(){
																		var form = Ext.getCmp('timetrack_form');
																		if (!form.isValid()) {
																			return;
																		}
																		var carid = record.get('id').substring(1);
																		var inv = Ext.getCmp('tt_inv').getValue();
																		var expire = Ext.getCmp('tt_expire').getValue();
																		var distance = Ext.getCmp('tt_distance').getValue();
																		var effdistance = Ext.getCmp('tt_effdistance').getValue();
																		var way = Ext.getCmp('tt_way').getValue();
																		points = [];
																		map.clearOverlays();
																		vehicleTimingTracking(admin.id,admin.blocid,admin.username,carid,inv,expire*60,distance,effdistance,way,200);
																		
																	}
																}]
															}).show();
						                                }
						                            },{
						                                text:"定次跟踪",
						                                hidden:hidderContent('130108'),
						                                iconCls:'leaf',
						                                handler:function(it,e){
								                            Ext.create('Ext.window.Window', {
															    title: '车辆定次跟踪',
															    id:'numtrackwin',
															    width: 350,
															    animCollapse:false,
																constrain : true,
																constrainHeader : true,
																maximizable : false,
																minimizable : false,
															    closable : true,
															    layout: 'fit',
															    items: {  
															        xtype: 'form',
															        id:'numtrack_form',
															        border:false,
															        items : [{
																		xtype : "numberfield",
																		fieldLabel : "请输入间隔时间(秒)",
																		id : "tt_inv",
																		labelWidth : 130,
																		labelAlign: 'right',
																		width: 320,
																		margin : '10 auto',
																		allowBlank : false,
																		blankText : "不能为空,只能输入正整数",
																		regex : /^[0-9]{0,10}?$/,
																		maxValue: 99,
																		minValue: 1,
																		value: 1
																	}, {
																		xtype : "numberfield",
																		fieldLabel : "请输入次数",
																		id : "tt_num",
																		labelWidth : 130,
																		labelAlign: 'right',
																		width: 320,
																		margin : '10 auto',
																		allowBlank : false,
																		blankText : "不能为空,只能输入正整数",
																		regex : /^[0-9]{0,10}?$/,
																		maxValue: 99,
																		minValue: 1,
																		value: 1
																	}, {
																		xtype : "textfield",
																		fieldLabel : "请输入距离间隔(米)",
																		id : "tt_distance",
																		labelWidth : 130,
																		labelAlign: 'right',
																		width: 320,
																		margin : '10 auto',
																		allowBlank : false,
																		blankText : "不能为空,只能输入正整数",
																		regex : /^\d+$/,
																		value: 1
																	}, {
																		xtype : "textfield",
																		fieldLabel : "请输入位置跟踪有效距离(米)",
																		id : "tt_effdistance",
																		labelWidth : 130,
																		labelAlign: 'right',
																		width: 320,
																		margin : '10 auto',
																		allowBlank : false,
																		blankText : "不能为空,只能输入正整数",
																		regex : /^\d+$/,
																		value: 1
																	}, {
																		xtype : 'combo',
																		fieldLabel : '跟踪方式',
																		id : "tt_way",
																		labelWidth : 130,
																		labelAlign: 'right',
																		width: 320,
																		margin : '10 auto',
																		allowBlank : false,
																		blankText : '请选择',
																		editable: false,
																		store : Ext.create('Ext.data.Store', {
																		    fields: ['type', 'name'],
																		    data : [
																		        {"type":"10", "name":"按距离间隔、持续时间"},
																		        {"type":"11", "name":"按距离间隔、持续距离"}
																		    ]
																		}),
																		displayField : 'name',
																		value : '10',
																		valueField : 'type'
																	} ]
															    },
															    buttons: [{
																	text: '确定',
																	iconCls : 'edit',
																	handler: function(){
																		var form = Ext.getCmp('numtrack_form');
																		if (!form.isValid()) {
																			return;
																		}
																		var carid = record.get('id').substring(1);
																		var inv = Ext.getCmp('tt_inv').getValue();
																		var num = Ext.getCmp('tt_num').getValue();
																		var distance = Ext.getCmp('tt_distance').getValue();
																		var effdistance = Ext.getCmp('tt_effdistance').getValue();
																		var way = Ext.getCmp('tt_way').getValue();
																		points = [];
																		map.clearOverlays();
																		vehicleNumTracking(admin.id,admin.blocid,admin.username,carid,inv,inv*num,distance,effdistance,way,200);
																		
																	}
																}]
															}).show();
						                                }
						                            },{
						                                text:"停止监控",
						                                hidden:hidderContent('130109'),
						                                iconCls:'leaf',
						                                handler:function(it,e){
						                                	var carid = record.get('id').substring(1);
						                                	map.clearOverlays();
															vehicleTimingTracking(admin.id,admin.blocid,admin.username,carid,0,0,300);
						                                }
						                            },{
						                                text:"查看历史轨迹",
						                                hidden:hidderContent('130110'),
						                                iconCls:'leaf',
						                                handler:function(it,e){
						                                    this.up("menu").hide();
						                                    //扩展ie不支持contains方法
						                                    Array.prototype.contains = function(item){
																return RegExp("\\b"+item+"\\b").test(this);
															}; 
					                                    	var carnumber = record.get('hrefTarget');
														    var url = window.BIZCTX_PATH + "/taxi/cartrack/index.jsp?carnumber=" + carnumber;
															window.open(url);
						                                }
						                            },{
						                                text:"车辆详细信息",
						                                hidden:hidderContent('130113'),
						                                iconCls:'leaf',
						                                handler:function(it,e){
						                                	var view = Ext.widget('carDetailWindow')
						                                	var carnumber = record.get('hrefTarget');
						                                	var carid = record.get('id');
						                                	view.down('form').getForm().load({
						                                		  url: window.BIZCTX_PATH + '/carjson/readCarDetailInfo.action', //请求的服务器地址
															      params:{ 
															      		carnumber : carnumber,
															      		carid : carid
															      },
															      success : function(form, action) {
															      	 view.show();
															      },
															      failure : function(form, action) {
															      	 return false;
															      }
						                                	 });
															 view.show();
						                                }
						                            },{
						                                text:"查看视频",
						                                iconCls:'leaf',
						                                hidden:hidderContent('130114'),
						                                handler:function(it,e){
						                                    this.up("menu").hide();
						                                    //扩展ie不支持contains方法
						                                    Array.prototype.contains = function(item){
																return RegExp("\\b"+item+"\\b").test(this);
															}; 
					                                    	var carnumber = record.get('hrefTarget');
					                                    	var carid = record.get('id').substring(1);
					                                    	
				                                    		Ext.Ajax.request( {
														      url: window.BIZCTX_PATH + '/terminalpositionjson/queryCarDetail.action', //请求的服务器地址
														      params:{ 
														      		carid : carid
														      },
														      success : function(response) {
													         	 var action = Ext.JSON.decode(response.responseText);
																 var carid =  action.result.data.carid;
																 var terminal = action.result.data.terminal;
																 var vediotype = action.result.data.vediotype;
																 var vediono = action.result.data.vediono;
																 if(vediono == null  || vediono.length == 0){
																 	 Ext.Msg.alert("提示","视频编号为空，无法查看视频!");
																 }else{
																 	var url = "";
																 	if(vediotype ==2){
																	   url = window.BIZCTX_PATH + "/taxi/vedio_guoji/index.jsp?terminal=" + vediono;
																 	}else{
																 	   url = window.BIZCTX_PATH + "/taxi/vedio_duosen/index.jsp?terminal=" + vediono;
																 	}	
																	 window.open(url);
																 }
													    	  }
														    });
						                                }
						                            }]
					                        }).showAt(event.getXY());//让右键菜单跟随鼠标位置
					                        
					                     }
					                }
				                },
				                'itemclick': {
					                fn: function(view, record, item, index, event) {
					                	if (!record.get('checked') && record.get('leaf') && record.get('id').indexOf('C') == 0) { // 车辆叶子节点
					                		var tree = Ext.getCmp("tree-panel");
					                		record.set('checked',true);
						                	var tabPanel = Ext.getCmp("taxiMapPanel");
	                                    	tabPanel.setActiveTab("taximap");
											var control = Ext.create('FMS.controller.Controllers');  
											var carnumber = record.get('hrefTarget');
											control.loadCarPosition(carnumber,3); 
					                	}else if(record.get('checked') && record.get('leaf') && record.get('id').indexOf('C') == 0) { // 车辆叶子节点
					                		record.set('checked',false);
											//取消选中清除标注
					                		if(maptype == 1){
					                			markermap.remove(record.get('hrefTarget'));
					                		}else{
					                			mapObj.remove(markermap.get(record.get('hrefTarget')));
					                			Ext.getCmp('east-panel').collapse();
					                		}
				                			
			  								
					                		 //清空实时位置列表对应信息
		   	    							 var store = Ext.StoreManager.get('TerminalPositionStore');
					                		 for(var i=0;i<store.getCount();i++){
												if(store.getAt(i).data.carid == record.get('id').substring(1)){
													store.removeAt(i);
													break;
												}
											 }
											 
											 //单选车辆后调用后台
											var carid = record.get('id').substring(1);
											realTimeCheckedCar(2,carid.toString(),300);

											
					                	}
					                	
					                }
					            },
					            'itemdblclick':{
					            	
					                fn: function(view, record, item, index, event) {
					                	var store = Ext.StoreManager.get('CarTreeStore');
					                	//查找响应的节点
										var treeNodeInterface = store.getNodeById(record.get('id')); 
					                	if(treeNodeInterface.hasChildNodes())
					                		return;
					                    var mask=new Ext.LoadMask('west-panel',{msg:"车辆数据加载中,请稍后......"});
	         							mask.show();
								        Ext.Ajax.request({
								        	url: window.BIZCTX_PATH + '/servlet/CarTree', 
								        	params : {deptid:record.get('id')},
											success : function(response, options){
												var treeNodeArray = [];
											    treeNodeArray = Ext.JSON.decode(response.responseText);
											    if(treeNodeArray.length == 1 && treeNodeArray[0] ==null){
													
											    }else{
											    	treeNodeInterface.appendChild(treeNodeArray);
											    	treeNodeInterface.expand();
											    }
												mask.hide();
											}
										});
					                }
					            
					            }
				   		    }
			    	   }]
					}]
			}],
    		listeners: {
    			'collapse': {
                	fn: function(){
                		Ext.getCmp('taxiMapPanel').setSize(Ext.getCmp('taxiMapPanel').getSize().width+220,Ext.getCmp('taxiMapPanel').getSize().height);
                	}
                },
                'expand':{
                	fn: function(){
                	}          	
            	}
           }
			             
		}
);