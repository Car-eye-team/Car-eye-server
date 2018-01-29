Ext.define('TelCallApp.controller.TelCallCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['TelCallListStore','CarInfoListStore','CarNumberStore','SendRecordListStore','FlagStore'
             ,'CallTypeStore','CarPageListStore'],//声明该控制层要用到的store
    models: [],//声明该控制层要用到的model
    views: ['TelCallListView','CarInfoListView'],//声明该控制层要用到的view
    init: function() {
    	
		this.control({
			'telcallListView button[action=add]' : {
				click : this.addTelCall
			},
			'telcallListView button[action=delete]' : {
				click : this.deleteTelCall
			},
			'telcallListView button[action=edit]' : {
				click : this.editTelCall
			},
			'telcallListView button[action=searchtext]' : {
				click : this.searchTelCall
			},
			'telcallAddView button[action=save]' : {
				click : this.saveTelCall
			},
			'telcallEditView button[action=save]' : {
				click : this.saveTelCall
			},
			'carInfoListView button[action=send]' : {
				click : this.sendTelCall
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
			'telcallListView':{
                render : this.buttonAccess
            },
			'carInfoListView':{
                render : this.button1Access
            },
			'carInfoListView #carnumber' : {
				change : this.loadCar1
			},
			'sendRecordListView #carnumber' : {
				change : this.loadCar2
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
	
	
	loadCar1 : function(){
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
	loadCar2 : function(){
		var store = this.getCarNumberStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	carnumber: encodeURI(Ext.getCmp('s_carnumber').getValue())
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	buttonAccess : function() {
		var buttonArray = ['14040301','14040302','14040303'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	button1Access : function() {
		var buttonArray = ['14040304','14040305'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	lookSendRecord : function (button){
//		var view = Ext.widget('sendRecordListView');
		var view = Ext.create('TelCallApp.view.SendRecordListView');
		view.show();
		var store = this.getSendRecordListStoreStore();
		store.load();
		return false;
	},
	addTelCall : function(button) {
//		var view = Ext.widget('telcallAddView');
		var view = Ext.create('TelCallApp.view.TelCallAddView');
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
	            	flag: encodeURI(Ext.getCmp('s_flag').getValue()),
	            	tel: encodeURI(Ext.getCmp('s_tel').getValue()),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('s_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('s_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	searchTelCall : function(button) {
		var store = this.getTelCallListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
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
	saveTelCall : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getTelCallListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			Ext.MessageBox.wait('请稍候...', '提示');
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/telcalljson/saveTelCall.action',
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
							Ext.Msg.alert("提示","电话号码已存在请更换!");
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
	sendTelCall : function(button){
	    var textgrid = Ext.getCmp('telcallgrid');
	    var cargrid = Ext.getCmp('carinfogrid');
		var textdata = textgrid.getSelectionModel().getSelection();
		var cardata = cargrid.getSelectionModel().getSelection();
		
		var textstore = textgrid.getStore();
		var carstore = cargrid.getStore();
		if(cardata.length<=0||textdata.length<=0){
			Ext.Msg.alert('提示', '电话回拨与车辆信息都至少选一条!');
			return false;
		}
		if (cardata.length>0) {
			Ext.MessageBox.confirm('提示','你确认下发电话回拨？',
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
								url : window.BIZCTX_PATH + '/telcalljson/CheckTelCall.action?tes='+tjsonData,
								method : 'POST',  
								params : "ids="+ cjsonData,
								success : function(response) {
								var text = response.responseText;
								 var obj = eval( "(" + text + ")" );//转换后的JSON对象
			   					 var su = obj.result.su;
								 if (su == -1) {
									Ext.Msg.alert('提示',"电话回拨下发失败");
								}else {
									Ext.Msg.alert('提示',"电话回拨下发成功");
								}
					   			},
								failure : function() {
									Ext.Msg.alert('提示',"电话回拨下发失败");
								}
							});
				}
			});
		}
		return false;
	
	
	},
	deleteTelCall : function(button) {
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
								url : window.BIZCTX_PATH + '/telcalljson/deleteTelCall.action',
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
	
	editTelCall : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
//		var view = Ext.widget('telcallEditView');
		var view = Ext.create('TelCallApp.view.TelCallEditView');
		view.show();
		var store = this.getTelCallListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	}
	
	
	
});

