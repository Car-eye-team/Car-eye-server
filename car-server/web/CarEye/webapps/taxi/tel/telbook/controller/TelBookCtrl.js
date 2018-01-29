var telpubcarnumber = "";
Ext.define('TelBookApp.controller.TelBookCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['TelBookListStore','CarInfoListStore','CarNumberStore','TelBookCarListStore','SendRecordListStore'
             ,'OperTypeStore','CallTypeStore','CarPageListStore'],//声明该控制层要用到的store
    models: [],//声明该控制层要用到的model
    views: ['TelBookListView','CarInfoListView'],//声明该控制层要用到的view
    init: function() {
    	
		this.control({
			'telbookListView button[action=add]' : {
				click : this.addTelBook
			},
			'telbookListView button[action=delete]' : {
				click : this.deleteTelBook
			},
			'telbookListView button[action=edit]' : {
				click : this.editTelBook
			},
			'telbookListView button[action=searchtext]' : {
				click : this.searchTelBook
			},
			'telbookAddView button[action=save]' : {
				click : this.saveTelBook
			},
			'telbookEditView button[action=save]' : {
				click : this.saveTelBook
			},
			'carInfoListView button[action=searchcar]' : {
				click : this.searchCarInfo
			},
			'carInfoListView button[action=addcar]' : {
				click : this.addcarTelBookCar
			},
			'carInfoListView button[action=searchsendrecord]' : {
				click : this.lookSendRecord
			},
			'carInfoListView #looktelbookcar' : {
				click : this.lookTelBookCar
			},
			'sendRecordListView button[action=searchsend]' : {
				click : this.searchSendRecord
			},
			'telbookCarListView button[action=searchtelbookcar]' : {
				click : this.searchTelBookCar
			},
			'telbookCarListView button[action=editcar]' : {
				click : this.updateTelBookCar
			},
			'telbookCarListView button[action=deletecar]' : {
				click : this.editSelectTelBookCar
			},
			'telbookCarListView button[action=deleteallcar]' : {
				click : this.deleteAllTelBookCar
			},
			'telbookListView':{
                render : this.buttonAccess
            },
			'carInfoListView':{
                render : this.button1Access
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
		var buttonArray = ['14040201','14040202','14040203'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	button1Access : function() {
		var buttonArray = ['14040204','14040205'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	
	addTelBook : function(button) {
//		var view = Ext.widget('telbookAddView');
		var view = Ext.create('TelBookApp.view.TelBookAddView');
		view.show();
		return false;
	},
	lookSendRecord : function (button){
//		var view = Ext.widget('sendRecordListView');
		var view = Ext.create('TelBookApp.view.SendRecordListView');
		view.show();
		var store = this.getSendRecordListStoreStore();
		store.load();
		return false;
	},
	lookTelBookCar : function(grid, rowIndex, colIndex){//rowIndex，colIndex均从0开始  
//		var view = Ext.widget('telbookCarListView');
		var view = Ext.create('TelBookApp.view.TelBookCarListView');
		view.show();
        var rec = grid.getStore().getAt(colIndex);  
        var carnumber=rec.get('carnumber');
        telpubcarnumber = carnumber;
        var store = this.getTelBookCarListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            		carnumber: encodeURI(carnumber)
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	    store.loadPage(1); 
	    //store.reload();
		return false;
	},
	
	searchTelBookCar : function(button) {
		var store = this.getTelBookCarListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	contacts: encodeURI(Ext.getCmp('car_contacts').getValue()),
	            	tel: encodeURI(Ext.getCmp('car_tel').getValue())
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
	            	tel: encodeURI(Ext.getCmp('s_tel').getValue()),
	            	opertype: encodeURI(Ext.getCmp('s_opertype').getValue()),
	            	contacts: encodeURI(Ext.getCmp('s_contacts').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	searchTelBook : function(button) {
		var store = this.getTelBookListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	contacts: encodeURI(Ext.getCmp('c_contacts').getValue()),
	            	tel: encodeURI(Ext.getCmp('c_tel').getValue())
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
	            	blocid: Ext.getCmp('c_blocid').getValue()
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	saveTelBook : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getTelBookListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			Ext.MessageBox.wait('请稍候...', '提示');
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/telbookjson/saveTelBookSystem.action',
				method : 'post',
				success : function(form, action) {
						Ext.MessageBox.updateProgress(1);
					    Ext.MessageBox.close();
						var resp = action.result.result;
						var su = resp.su;
						 if (su == -1) {
							win.close();
							Ext.Msg.alert("提示","系统异常,请与管理员联系!");
						}else if (su == -2) {
							Ext.Msg.alert("提示","电话号码已存在!");
						}else{
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
	
	editSelectTelBookCar : function(button){

			var telbookgrid = button.up('grid');
			var telbookdata = telbookgrid.getSelectionModel().getSelection();
			var telbookstore = telbookgrid.getStore();
			if(telbookdata.length<=0){
				Ext.Msg.alert('提示', '请选择要修改的联系人!');
				return false;
			}
			if (telbookdata.length>0) {
				Ext.MessageBox.confirm('提示','你确认下发修改联系人消息？',
				function(btn) {
					if (btn == "yes") {
						var te = telbookgrid.getSelectionModel().getSelection();
						var tjsonData = "";
						for ( var i = 0, len = te.length; i < len; i++) {
							var ss = te[i].get("cartelbookid");
							if (i == 0) {
								tjsonData = tjsonData + ss;
							} else {
								tjsonData = tjsonData + "," + ss;
							}
						}
						
						Ext.Ajax.request( {
									url : window.BIZCTX_PATH + '/telbookjson/editSelectTelBookCar.action',
									method : 'POST',  
									params : "ids="+ tjsonData,
									success : function(response) {
									var text = response.responseText;
									 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					 var su = obj.result.su;
									 if (su == -1) {
										Ext.Msg.alert('提示',"下发修改联系人失败");
										telbookstore.load();
									}else {
										Ext.Msg.alert('提示',"下发修改联系人成功");
										telbookstore.load();
										}
						   			},
									failure : function() {
										Ext.Msg.alert('提示',"下发修改联系人失败");
										telbookstore.load();
									}
								});
					}
				});
			} 
		return false;
	},
	//更新联系人
	updateTelBookCar : function(button){
		var telbookgrid = button.up('grid');
		var telbookdata = telbookgrid.getSelectionModel().getSelection();
		var telbookstore = telbookgrid.getStore();
		if(telbookdata.length<=0){
			Ext.Msg.alert('提示', '请选择要更新的联系人!');
			return false;
		}
		if (telbookdata.length>0) {
			Ext.MessageBox.confirm('提示','你确认下发更新联系人消息？',
			function(btn) {
				if (btn == "yes") {
					var te = telbookgrid.getSelectionModel().getSelection();
					var tjsonData = "";
					for ( var i = 0, len = te.length; i < len; i++) {
						var ss = te[i].get("cartelbookid");
						if (i == 0) {
							tjsonData = tjsonData + ss;
						} else {
							tjsonData = tjsonData + "," + ss;
						}
					}
					
					Ext.Ajax.request( {
								url : window.BIZCTX_PATH + '/telbookjson/updateTelBookCar.action',
								method : 'POST',  
								params : "ids="+ tjsonData,
								success : function(response) {
								var text = response.responseText;
								 var obj = eval( "(" + text + ")" );//转换后的JSON对象
			   					 var su = obj.result.su;
								 if (su == -1) {
									Ext.Msg.alert('提示',"下发更新联系人失败");
									telbookstore.load();
								}else {
									Ext.Msg.alert('提示',"下发更新联系人成功");
									telbookstore.load();
								}
					   			},
								failure : function() {
									Ext.Msg.alert('提示',"下发更新联系人失败");
									telbookstore.load();
								}
							});
				}
			});
		} 
	return false;
	},
	deleteAllTelBookCar : function(button){
	    var store=Ext.StoreManager.get('TelBookCarListStore');
	  //  var carnumber=store.data.get(0).get('carnumber');
	    Ext.Ajax.request( {
			url : window.BIZCTX_PATH + '/telbookjson/deleteAllTelBookCar.action',
			method : 'POST',  
			params : "carnumber="+ encodeURI(telpubcarnumber),
			success : function(response) {
			var text = response.responseText;
			 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				 var su = obj.result.su;
			 if (su == -1) {
				Ext.Msg.alert('提示',"下发删除所有联系人失败");
				store.load();
			}else {
				Ext.Msg.alert('提示',"下发删除所有联系人成功");
				store.load();
			}
   			},
			failure : function() {
				Ext.Msg.alert('提示',"下发删除所有联系人失败");
				store.load();
			}
		});
	return false;
},
	//追加联系人
	addcarTelBookCar : function(button){
	    var telbookgrid = Ext.getCmp('telbookgrid');
	    var cargrid = Ext.getCmp('carinfogrid');
		var telbookdata = telbookgrid.getSelectionModel().getSelection();
		var cardata = cargrid.getSelectionModel().getSelection();
		
		var telbookstore = telbookgrid.getStore();
		var carstore = cargrid.getStore();
		if(cardata.length<=0||telbookdata.length<=0){
			Ext.Msg.alert('提示', '联系人信息与车辆信息都至少选一条!');
			return false;
		}
		if (cardata.length>0) {
			Ext.MessageBox.confirm('提示','你确认追加联系人？',
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
					var te = telbookgrid.getSelectionModel().getSelection();
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
								url : window.BIZCTX_PATH + '/telbookjson/CheckTelBookSystem.action?eve='+tjsonData,
								method : 'POST',  
								params : "ids="+ cjsonData,
								success : function(response) {
								var text = response.responseText;
								 var obj = eval( "(" + text + ")" );//转换后的JSON对象
			   					 var su = obj.result.su;
								 if (su == -1) {
									Ext.Msg.alert('提示',"追加联系人失败");
								}else {
									Ext.Msg.alert('提示',"追加联系人成功");
								}
					   			},
								failure : function() {
									Ext.Msg.alert('提示',"追加联系人失败");
								}
							});
				}
			});
		} 
		return false;
	},
	deleteTelBook : function(button) {
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
									url : window.BIZCTX_PATH + '/telbookjson/deleteTelBookSystem.action',
									method : 'POST',  
									params : "ids="+ jsonData,
									success : function(response) {
										var text = response.responseText;
										 var obj = eval( "(" + text + ")" );//转换后的JSON对象
					   					 var su = obj.result.su;
										 if (su == -1) {
											Ext.Msg.alert('提示',"联系人不能被删除");
											store.reload();
										}else {
											Ext.Msg.alert('提示',"联系人删除成功");
											store.reload();
										}
							   		},
									failure : function() {
										Ext.Msg.alert('提示',"联系人删除失败");
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
	
	editTelBook : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
//		var view = Ext.widget('telbookEditView');
		var view = Ext.create('TelBookApp.view.TelBookEditView');
		view.show();
		var store = this.getTelBookListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	}
	
	
	
});

