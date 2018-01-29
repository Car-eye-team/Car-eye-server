var pubcarnumber = "";
Ext.define('EventApp.controller.EventCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['EventListStore','CarInfoListStore','CarNumberStore','EventCarListStore','SendRecordListStore'
             ,'OperTypeStore','CarPageListStore'],//声明该控制层要用到的store
    models: [],//声明该控制层要用到的model
    views: ['EventListView','CarInfoListView','EventCarListView'],//声明该控制层要用到的view
    init: function() {
    	
		this.control({
			'eventListView button[action=add]' : {
				click : this.addEvent
			},
			'eventListView button[action=delete]' : {
				click : this.deleteEvent
			},
			'eventListView button[action=edit]' : {
				click : this.editEvent
			},
			'eventListView button[action=searchtext]' : {
				click : this.searchEvent
			},
			'eventAddView button[action=save]' : {
				click : this.saveEvent
			},
			'eventEditView button[action=save]' : {
				click : this.saveEvent
			},
			'carInfoListView button[action=searchcar]' : {
				click : this.searchCarInfo
			},
			'carInfoListView button[action=addcar]' : {
				click : this.addcarEventCar
			},
			'carInfoListView button[action=searchsendrecord]' : {
				click : this.lookSendRecord
			},
			'carInfoListView #lookeventcar' : {
				click : this.lookEventCar
			},
			'sendRecordListView button[action=searchsend]' : {
				click : this.searchSendRecord
			},
			'eventCarListView button[action=searcheventcar]' : {
				click : this.searchEventCar
			},
			'eventCarListView button[action=editcar]' : {
				click : this.updateEventCar
			},
			'eventCarListView button[action=deletecar]' : {
				click : this.deleteSelectEventCar
			},
			'eventCarListView button[action=deleteallcar]' : {
				click : this.deleteAllEventCar
			},
			'eventListView':{
                render : this.buttonAccess
            },
			'carInfoListView':{
                render : this.button1Access
            },
			'carInfoListView #carnumber' : {
				change : this.loadCar
			},
			'carInfoListView #blocid' : {
				select : this.loadDeptCar
			}
		});
	},
	
	loadDeptCar : function(){
		Ext.getCmp('c_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var deptid = Ext.getCmp('c_blocid').getValue();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	deptid: deptid,
            	carnumber: ""
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	
	buttonAccess : function() {
		var buttonArray = ['14040501','14040502','14040503'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	button1Access : function() {
		var buttonArray = ['14040504','14040505'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	loadCar : function(){
		var store = this.getCarNumberStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	carnumber: encodeURI(Ext.getCmp('c_carnumber').getValue())
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	addEvent : function(button) {
//		var view = Ext.widget('eventAddView');
		var view = Ext.create('EventApp.view.EventAddView');
		view.show();
		return false;
	},
	lookSendRecord : function (){
//		var view = Ext.widget('sendRecordListView');
		var view = Ext.create('EventApp.view.SendRecordListView');
		view.show();
		var store = this.getSendRecordListStoreStore();
		store.load();
		
		return false;
	},
	lookEventCar : function(grid, rowIndex, colIndex){//rowIndex，colIndex均从0开始  
//		var view = Ext.widget('eventCarListView');
		var view = Ext.create('EventApp.view.EventCarListView');
		view.show();
        var rec = grid.getStore().getAt(colIndex);  
        var carnumber=rec.get('carnumber');
        pubcarnumber = carnumber;
        var store = this.getEventCarListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            		carnumber: encodeURI(carnumber)
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	    store.reload();
		return false;
	},
	
	searchEventCar : function(button) {
		var stime = Ext.util.Format.date(Ext.getCmp('car_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('car_etime').getValue(), 'Y-m-d H:i:s');
        if(etime != null && etime.length > 0){
            var beginTimes = stime.substring(0,10).split('-');
            var endTimes = etime.substring(0,10).split('-');
    
            var stimearray = stime.substring(10,19).split(':');
            var etimearray = etime.substring(10,19).split(':');
            var stimedate = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
            var etimedate = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
            
            if(etimedate < stimedate){
                Ext.Msg.alert("提示","开始时间必须小于结束时间");
                return;
            }
        } 
		
		var store = this.getEventCarListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	content: encodeURI(Ext.getCmp('car_content').getValue()),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('car_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('car_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	searchSendRecord : function(button) {
		var store = this.getSendRecordListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('s_carnumber').getValue()),
	            	opertype: encodeURI(Ext.getCmp('s_opertype').getValue()),
	            	content: encodeURI(Ext.getCmp('s_content').getValue()),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('s_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('s_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	searchEvent : function(button) {
		var store = this.getEventListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	content: encodeURI(Ext.getCmp('c_content').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	searchCarInfo : function(button) {
		var store = this.getCarInfoListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('c_carnumber').getValue()),
	            	terminal: encodeURI(Ext.getCmp('c_terminal').getValue()),
	            	deptid: Ext.getCmp('c_blocid').getValue()
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	saveEvent : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getEventListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			Ext.MessageBox.wait('请稍候...', '提示');
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/eventjson/saveEventSystem.action',
				method : 'post',
				success : function(form, action) {
						Ext.MessageBox.updateProgress(1);
					    Ext.MessageBox.close();
						var resp = action.result.result;
						var su = resp.su;
						 if (su == -1) {
							win.close();
							Ext.Msg.alert("提示","系统异常,请与管理员联系!");
						}else {
							win.close();
							Ext.Msg.alert("提示","操作信息成功!");
							store.reload();
						}
			    },
				failure : function(form, action) {
					Ext.MessageBox.updateProgress(1);
					Ext.MessageBox.close();
					win.close();
					Ext.Msg.alert('提示', "操作信息失败!");
					store.reload();
				}
			});
			return false;
	},
	
	deleteSelectEventCar : function(button){

			var eventgrid = button.up('grid');
			var eventdata = eventgrid.getSelectionModel().getSelection();
			var eventstore = eventgrid.getStore();
			if(eventdata.length<=0){
				Ext.Msg.alert('提示', '请选择要删除的事件!');
				return false;
			}
			if (eventdata.length>0) {
				Ext.MessageBox.confirm('提示','你确认下发删除事件消息？',
				function(btn) {
					if (btn == "yes") {
						var te = eventgrid.getSelectionModel().getSelection();
						var tjsonData = "";
						for ( var i = 0, len = te.length; i < len; i++) {
							var ss = te[i].get("careventid");
							if (i == 0) {
								tjsonData = tjsonData + ss;
							} else {
								tjsonData = tjsonData + "," + ss;
							}
						}
						
						Ext.Ajax.request( {
									url : window.BIZCTX_PATH + '/eventjson/deleteSelectEventCar.action',
									method : 'POST',  
									params : "ids="+ tjsonData,
									success : function(response) {
									var text = response.responseText;
									 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					 var su = obj.result.su;
									 if (su == -1) {
										Ext.Msg.alert('提示',"下发删除事件失败");
										eventstore.load();
									}else {
										Ext.Msg.alert('提示',"下发删除事件成功");
										eventstore.load();
										}
						   			},
									failure : function() {
										Ext.Msg.alert('提示',"下发删除事件失败");
										eventstore.load();
									}
								});
					}
				});
			} 
		return false;
	},
	updateEventCar : function(button){
		var eventgrid = button.up('grid');
		var eventdata = eventgrid.getSelectionModel().getSelection();
		var eventstore = eventgrid.getStore();
		if(eventdata.length<=0){
			Ext.Msg.alert('提示', '请选择要更新的事件!');
			return false;
		}
		if (eventdata.length>0) {
			Ext.MessageBox.confirm('提示','你确认下发更新事件消息？',
			function(btn) {
				if (btn == "yes") {
					var te = eventgrid.getSelectionModel().getSelection();
					var tjsonData = "";
					for ( var i = 0, len = te.length; i < len; i++) {
						var ss = te[i].get("careventid");
						if (i == 0) {
							tjsonData = tjsonData + ss;
						} else {
							tjsonData = tjsonData + "," + ss;
						}
					}
					
					Ext.Ajax.request( {
								url : window.BIZCTX_PATH + '/eventjson/updateEventCar.action',
								method : 'POST',  
								params : "ids="+ tjsonData,
								success : function(response) {
								var text = response.responseText;
								 var obj = eval( "(" + text + ")" );//转换后的JSON对象
			   					 var su = obj.result.su;
								 if (su == -1) {
									Ext.Msg.alert('提示',"下发更新事件失败");
								}else {
									Ext.Msg.alert('提示',"下发更新事件成功");
								}
					   			},
								failure : function() {
									Ext.Msg.alert('提示',"下发更新事件失败");
								}
							});
				}
			});
		} 
	return false;
	},
	deleteAllEventCar : function(button){
	    var cargrid = Ext.getCmp('eventcargrid');
	    var store=Ext.StoreManager.get('EventCarListStore');
	    
//	    var carnumber=store.data.get(0).get('carnumber');
	    Ext.Ajax.request( {
			url : window.BIZCTX_PATH + '/eventjson/deleteAllEventCar.action',
			method : 'POST',  
			params : "carnumber="+ encodeURI(pubcarnumber),
			success : function(response) {
			var text = response.responseText;
			 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				 var su = obj.result.su;
			 if (su == -1) {
				Ext.Msg.alert('提示',"下发删除所有事件失败");
				store.load();
			}else {
				Ext.Msg.alert('提示',"下发删除所有事件成功");
				store.load();
			}
   			},
			failure : function() {
				Ext.Msg.alert('提示',"下发删除所有事件失败");
				store.load();
			}
		});
	return false;
},
	
	addcarEventCar : function(button){
	    var eventgrid = Ext.getCmp('eventgrid');
	    var cargrid = Ext.getCmp('carinfogrid');
		var eventdata = eventgrid.getSelectionModel().getSelection();
		var cardata = cargrid.getSelectionModel().getSelection();
		
		var eventstore = eventgrid.getStore();
		var carstore = cargrid.getStore();
		if(cardata.length<=0||eventdata.length<=0){
			Ext.Msg.alert('提示', '事件信息与车辆信息都至少选一条!');
			return false;
		}
		if (cardata.length>0) {
			Ext.MessageBox.confirm('提示','你确认追加事件？',
			function(btn) {
				if (btn == "yes") {
					var ca = cargrid.getSelectionModel().getSelection();
					var cjsonData = "";
					for ( var i = 0, len = ca.length; i < len; i++) {
						var ss = ca[i].get("carid");
						if (i == 0) {
							cjsonData = cjsonData + ss;
						} else {
							cjsonData = cjsonData + "," + ss;
						}
					}
					var te = eventgrid.getSelectionModel().getSelection();
					var tjsonData = "";
					for ( var i = 0, len = te.length; i < len; i++) {
						var ss = te[i].get("id");
						if (i == 0) {
							tjsonData = tjsonData + ss;
						} else {
							tjsonData = tjsonData + "," + ss;
						}
					}
					
					Ext.Ajax.request( {
								url : window.BIZCTX_PATH + '/eventjson/CheckEventSystem.action?eve='+tjsonData,
								method : 'POST',  
								params : "ids="+ cjsonData,
								success : function(response) {
								var text = response.responseText;
								 var obj = eval( "(" + text + ")" );//转换后的JSON对象
			   					 var su = obj.result.su;
								 if (su == -1) {
									Ext.Msg.alert('提示',"追加事件失败");
								}else {
									Ext.Msg.alert('提示',"追加事件成功");
								}
					   			},
								failure : function() {
									Ext.Msg.alert('提示',"追加事件失败");
								}
							});
				}
			});
		} 
		return false;
	},
	deleteEvent : function(button) {
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length>0) {
			Ext.MessageBox.confirm('提示','你确认要删除选中的数据？',
			function(btn) {
				if (btn == "yes") {
					var m = grid.getSelectionModel().getSelection();
					var jsonData = "";
					for ( var i = 0, len = m.length; i < len; i++) {
						var ss = m[i].get("id");
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					Ext.Ajax.request( {
								url : window.BIZCTX_PATH + '/eventjson/deleteEventSystem.action',
								method : 'POST',  
								params : "ids="+ jsonData,
								success : function(response) {
									var text = response.responseText;
									 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					 var su = obj.result.su;
									 if (su == -1) {
										Ext.Msg.alert('提示',"事件不能被删除");
										store.reload();
									}else {
										Ext.Msg.alert('提示',"事件删除成功");
										store.reload();
									}
						   		},
								failure : function() {
									Ext.Msg.alert('提示',"事件删除失败");
									store.reload();
								}
							});
				}
			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的数据项!');
		}
		return false;
	},
	
	editEvent : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
//		var view = Ext.widget('eventEditView');
		var view = Ext.create('EventApp.view.EventEditView');
		view.show();
		var store = this.getEventListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	}
	
	
	
});

