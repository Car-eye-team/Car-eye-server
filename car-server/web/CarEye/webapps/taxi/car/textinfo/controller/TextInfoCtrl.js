Ext.define('TextInfoApp.controller.TextInfoCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['TextInfoListStore','CarInfoListStore','CarNumberStore','SendRecordListStore','OperTypeStore'
    	,'TextInfoTypeStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['TextInfoModel','CarInfoModel','CarNumberModel','SendRecordModel','TextInfoTypeModel'],//声明该控制层要用到的model
    views: ['TextInfoListView','CarInfoListView'],//声明该控制层要用到的view
    init: function() {
    	
		this.control({
			'textInfoListView button[action=add]' : {
				click : this.addTextInfo
			},
			'textInfoListView button[action=delete]' : {
				click : this.deleteTextInfo
			},
			'textInfoListView button[action=edit]' : {
				click : this.editTextInfo
			},
			'textInfoListView button[action=searchtext]' : {
				click : this.searchTextInfo
			},
			'textInfoAddView button[action=save]' : {
				click : this.saveTextInfo
			},
			'textInfoEditView button[action=save]' : {
				click : this.saveTextInfo
			},
			'carInfoListView button[action=send]' : {
				click : this.sendTextInfo
			},
			'carInfoListView button[action=searchsendrecord]' : {
				click : this.lookSendRecord
			},
			'carInfoListView button[action=searchcar]' : {
				click : this.searchCarInfo
			},
			'sendRecordListView button[action=searchsend]' : {
				click : this.searchSendRecord
			},
			'textInfoListView':{
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
		var buttonArray = ['14040901','14040902','14040903'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	button1Access : function() {
		var buttonArray = ['14040904','14040905'];
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
	lookSendRecord : function (button){
		var menuid = button.id+"";
//		var view = Ext.widget('sendRecordListView');
		var view = Ext.create('TextInfoApp.view.SendRecordListView');
		view.show();
		var store = this.getSendRecordListStoreStore();
        store.load();
		return false;
	},
	addTextInfo : function(button) {
		var menuid = button.id+"";
//		var view = Ext.widget('textInfoAddView');
		var view = Ext.create('TextInfoApp.view.TextInfoAddView');
		view.show();
		return false;
	},
	searchSendRecord : function(button) {
		
		var stime = Ext.util.Format.date(Ext.getCmp('s_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('s_etime').getValue(), 'Y-m-d H:i:s');
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
	searchTextInfo : function(button) {
		var store = this.getTextInfoListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	content: encodeURI(Ext.getCmp('c_content').getValue()),
	            	textinfotype:Ext.getCmp('c_textinfotype').getValue()
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
	saveTextInfo : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getTextInfoListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			Ext.MessageBox.wait('请稍候...', '提示');
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/textinfojson/saveTextInfo.action',
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
	sendTextInfo : function(button){
		var menuid = button.id+"";
	    var textgrid = Ext.getCmp('textinfogrid');
	    var cargrid = Ext.getCmp('carinfogrid');
		var textdata = textgrid.getSelectionModel().getSelection();
		var cardata = cargrid.getSelectionModel().getSelection();
		
		var textstore = textgrid.getStore();
		var carstore = cargrid.getStore();
		if(cardata.length<=0||textdata.length<=0){
			Ext.Msg.alert('提示', '下发消息与车辆信息都至少选一条!');
			return false;
		}
		if (cardata.length>0) {
			Ext.MessageBox.confirm('提示','你确认下发消息？',
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
					var te = textgrid.getSelectionModel().getSelection();
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
								url : window.BIZCTX_PATH + '/textinfojson/CheckTextInfo.action?tes='+tjsonData,
								method : 'POST',  
								params : "ids="+ cjsonData,
								success : function(response) {
								var text = response.responseText;
								 var obj = eval( "(" + text + ")" );//转换后的JSON对象
			   					 var su = obj.result.su;
								 if (su == -1) {
									Ext.Msg.alert('提示',"消息下发失败");
								}else {
									Ext.Msg.alert('提示',"消息下发成功");
								}
					   			},
								failure : function() {
									Ext.Msg.alert('提示',"消息下发失败");
								}
							});
				}
			});
			}
		return false;
	
	
	},
	deleteTextInfo : function(button) {
		var menuid = button.id+"";
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
									url : window.BIZCTX_PATH + '/textinfojson/deleteTextInfo.action',
									method : 'POST',  
									params : "ids="+ jsonData,
									success : function(response) {
										Ext.Msg.alert('提示',"数据删除成功");
										store.reload();
									},
									failure : function() {
										Ext.Msg.alert('提示',"数据删除失败");
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
	
	editTextInfo : function(button) {
		var menuid = button.id+"";
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
//		var view = Ext.widget('textInfoEditView');
		var view = Ext.create('TextInfoApp.view.TextInfoEditView');
		view.show();
		var store = this.getTextInfoListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	}
	
	
	
});

