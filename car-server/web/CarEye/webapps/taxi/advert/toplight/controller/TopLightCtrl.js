Ext.define('TopLightApp.controller.TopLightCtrl', {
    extend: 'Ext.app.Controller',
    stores: [
             'AdvertVerStore',
             'AdvertConStore',
             'AuditStatusStore',//审核状态
             'OnStatusStore',//在线状态
             'TXStore',
             'VersionStore',
             'TypeStore'
             
             ],//声明该控制层要用到的store
    models: [],//声明该控制层要用到的model
    views: ['AdvertVerView','AdvertConView'],//声明该控制层要用到的view
//    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
//        {
//            ref: 'advertTypeListView',
//            selector: 'advertTypeListView'
//        }
//    ],
    init: function() {
    	
		this.control({
			'advertVerView button[action=search]' : {
				click : this.searchVer
			},
			'advertVerView button[action=add]' : {
				click : this.addVer
			},
			'advertVerView button[action=edit]' : {
				click : this.editVer
			},
			'advertVerAddView button[action=save]' : {
				click : this.saveVer
			},
			'advertVerEditView button[action=save]' : {
				click : this.saveVer
			},
			'advertVerView button[action=delete]' : {
				click : this.deleteVer
			},
			'advertVerView button[action=export]' : {
				click : this.exportVer
			},
			'advertVerView button[action=audit]' : {
				click : this.auditVer
			},
			'advertVerAuditView button[action=save]' : {
				click : this.saveAuditVer
			},
			'advertVerView button[action=active]' : {
				click : this.activeVer
			},
			'advertVerView button[action=inactive]' : {
				click : this.inactiveVer
			},
			'advertConView button[action=search]' : {
				click : this.searchCon
			},
			'advertConView button[action=add]' : {
				click : this.addCon
			},
			'advertConView button[action=edit]' : {
				click : this.editCon
			},
			'advertConAddView button[action=save]' : {
				click : this.saveCon
			},
			'advertConEditView button[action=save]' : {
				click : this.saveCon
			},
			'advertConView button[action=delete]' : {
				click : this.deleteCon
			},
			'advertVerView':{
                render : this.buttonAccess
            },
			'advertConView':{
                render : this.buttonAccess1
            }
		});
	},
	
	
	buttonAccess : function() {
		var buttonArray = ['19010301','19010302','19010303','19010304','19010305','19010306','19010307'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	buttonAccess1 : function() {
		var buttonArray = ['19010308','19010309','19010310'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	
	/**  广告版本  */
	
	searchVer : function(button) {
		var stime = Ext.util.Format.date(Ext.getCmp('ver_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('ver_etime').getValue(), 'Y-m-d H:i:s');
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
		
		var store = this.getAdvertVerStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	version: encodeURI(Ext.getCmp('ver_version').getValue()),
	            	auditstatus: Ext.getCmp('ver_auditstatus').getValue(),
	            	onstatus: Ext.getCmp('ver_onstatus').getValue(),
	            	stime : encodeURI(Ext.util.Format.date(Ext.getCmp('ver_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('ver_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	addVer : function(button){
		
		var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    var strHour = date.getHours();
	    var strMin = date.getMinutes();
	    var strSec = date.getSeconds();
	    
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    if (strHour >= 0 && strHour <= 9) {
	    	strHour = "0" + strHour;
	    }
	    if (strMin >= 0 && strMin <= 9) {
	    	strMin = "0" + strMin;
	    }
	    if (strSec >= 0 && strSec <= 9) {
	    	strSec = "0" + strSec;
	    }
	    
	    var d = date.getFullYear() + seperator1 + month + seperator1 + strDate
	            + " " + strHour + seperator2 + strMin
	            + seperator2 + strSec;
		var y = d.substring(0, 4);
		var m = d.substring(5, 7);
		var today = d.substring(8, 10);
		var h = d.substring(11, 13);
		var f = d.substring(14, 16);
		var s = d.substring(17, 19);
		
		var vername = "V"+y+m+today+h+f+s;
		
		
		var view = Ext.create('TopLightApp.view.AdvertVerAddView');
		view.show();
		
		Ext.getCmp('version').setValue(vername);
		
		return false;
	},
	editVer : function(button){
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.create('TopLightApp.view.AdvertVerEditView');
			view.show();
		var store = this.getAdvertVerStoreStore();
		var data = store.getById(records[0].get('id'));
			view.down('form').loadRecord(data);
		return false;
	
	},
	saveVer : function(button){
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getAdvertVerStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/toplightjson/saveVer.action',
			method : 'post',
			waitMsg:'请稍候...',
			success : function(form, action) {
				var returnType = action.result.result.returnType;
				if (returnType == 0) {
					win.close();
					store.reload();
					Ext.Msg.alert("提示", "保存成功!");
				} else if (returnType == 1) {
					Ext.Msg.alert('提示', "版本名称已存在，请核实！");
				} else if(returnType == 2){
					Ext.Msg.alert('提示', "版本号已存在，请核实！");
				} else {
					Ext.Msg.alert('失败', "保存失败");
				}
			},
			failure : function(form, action) {
				win.close();
				Ext.Msg.alert('提示', "保存失败!");
				store.reload();
			}
		});
		return false;
	},
	deleteVer : function(button) {
		var grid = Ext.getCmp('advertVer');
		var records = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (records.length > 0) {
			Ext.Msg.buttonText.yes='确定';
			Ext.Msg.buttonText.no="取消";
			Ext.Msg.confirm('提示', '你确认要删除选中的数据?', function(btn) {
				if (btn == 'yes') {
					var m = records;
					var jsonData = "";
					for (var i = 0; i < m.length; i++) {
						var ss = m[i].get('id');
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					Ext.Ajax.request({
								url : window.BIZCTX_PATH
										+ '/toplightjson/deleteVer.action',
								method : 'POST',
								params : "ids=" + jsonData,
								success : function(response, opts) {
									var text = response.responseText;
									var su = Ext.JSON.decode(text).result.su;
									if (su == 0) {
										Ext.Msg.alert('提示', "删除成功！");
										store.reload();
									} else if (su == -1) {
										Ext.Msg.alert('提示', "该广告版本已被使用，无法删除！");
										store.reload();
									}else{
										Ext.Msg.alert('提示', "删除失败！");
										store.reload();
									}
								},
								failure : function() {
									Ext.Msg.alert('提示', "服务器异常，稍后再试！");
									store.reload();
								}

							})

				}

			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的数据项!');
		};

	},
	exportVer : function (button){
		var stime = Ext.util.Format.date(Ext.getCmp('ver_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('ver_etime').getValue(), 'Y-m-d H:i:s');
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
        
		version = encodeURI(Ext.getCmp('ver_version').getValue());
    	auditstatus = Ext.getCmp('ver_auditstatus').getValue();
    	onstatus = Ext.getCmp('ver_onstatus').getValue();
    	stime = encodeURI(Ext.util.Format.date(Ext.getCmp('ver_stime').getValue(), 'Y-m-d H:i:s'));
		etime  = encodeURI(Ext.util.Format.date(Ext.getCmp('ver_etime').getValue(), 'Y-m-d H:i:s'));
		
		location.href=window.BIZCTX_PATH + '/toplightjson/exportVer.action?version='+version+
		"&auditstatus="+auditstatus+
		"&onstatus="+onstatus+
		"&stime="+stime+
		"&etime="+etime;
		
	},
	auditVer : function(button){
		var button = Ext.getCmp('19010305');
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条版本审核');
			return;
		}
		
		var as = records[0].get('auditstatus');
		if(as != 0){
			Ext.Msg.alert('提示', '该版本已审核！');
			return;
		}
		
		var view = Ext.create('TopLightApp.view.AdvertVerAuditView');
			view.show();
		var store = this.getAdvertVerStoreStore();
		var data = store.getById(records[0].get('id'));
			view.down('form').loadRecord(data);
		return false;
	
	},
	saveAuditVer : function(button){
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getAdvertVerStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/toplightjson/saveAuditVer.action',
			method : 'post',
			waitMsg:'请稍候...',
			success : function(form, action) {
				var returnType = action.result.result.returnType;
				if (returnType == 0) {
					win.close();
					store.reload();
					Ext.Msg.alert("提示", "审核成功!");
				} else  {
					Ext.Msg.alert('失败', "审核失败");
				}
			},
			failure : function(form, action) {
				win.close();
				Ext.Msg.alert('提示', "审核失败!");
				store.reload();
			}
		});
		return false;
	},
	//上架
	activeVer : function (button){
			var button = Ext.getCmp('19010306');
			var grid = button.up('grid');
			var data = grid.getSelectionModel().getSelection();
			var store = grid.getStore();
			if (data.length>0) {
				Ext.MessageBox.confirm('提示','你确认要上架选中的广告版本？',
								function(btn) {
									if (btn == "yes") {
										var Products = grid.getSelectionModel().getSelection();
										var jsonData = "";
										for ( var i = 0, len = Products.length; i < len; i++) {
											var ss = Products[i].get("id");
											if (i == 0) {
												jsonData = jsonData + ss;
											} else {
												jsonData = jsonData + "," + ss;
											}
										}
										Ext.Ajax.request( {
													url : window.BIZCTX_PATH + '/toplightjson/activeVer.action',
													method : 'POST',  
													params : "ids="+ jsonData,
													success : function(response) {
														store.reload();
														Ext.Msg.alert('提示',"广告版本上架成功");
													},
													failure : function() {
														store.reload();
														Ext.Msg.alert('提示',"广告版本上架失败");
													}
												});
									}
								});
			} else {
				Ext.Msg.alert('提示', '请选择要上架的广告版本!');
			}
		},
		
	//下架
	inactiveVer : function(){
		var button = Ext.getCmp('19010307');
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length>0) {
			Ext.MessageBox.confirm('提示','你确认要下架选中的广告版本？',
							function(btn) {
								if (btn == "yes") {
									var Products = grid.getSelectionModel().getSelection();
									var jsonData = "";
									for ( var i = 0, len = Products.length; i < len; i++) {
										var ss = Products[i].get("id");
										if (i == 0) {
											jsonData = jsonData + ss;
										} else {
											jsonData = jsonData + "," + ss;
										}
									}
									Ext.Ajax.request( {
												url : window.BIZCTX_PATH + '/toplightjson/inactiveVer.action',
												method : 'POST',  
												params : "ids="+ jsonData,
												success : function(response) {
													store.reload();
													Ext.Msg.alert('提示',"广告版本下架成功");
												},
												failure : function() {
													store.reload();
													Ext.Msg.alert('提示',"广告版本下架失败");
												}
											});
								}
							});
		} else {
			Ext.Msg.alert('提示', '请选择要下架的广告版本!');
		}
		return false;
	},
	
	
	searchCon : function(button) {
		var stime = Ext.util.Format.date(Ext.getCmp('con_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('con_etime').getValue(), 'Y-m-d H:i:s');
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
		
		var store = this.getAdvertConStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	adname: encodeURI(Ext.getCmp('con_adname').getValue()),
	            	stime : encodeURI(Ext.util.Format.date(Ext.getCmp('con_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('con_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	
	addCon : function(button){
		
		var grid1 = Ext.getCmp('advertVer');
		var records1 = grid1.getSelectionModel().getSelection();
		if (records1.length != 1) {
			Ext.Msg.alert('提示', '请选择一个广告版本');
			return;
		}
		
		var versionid = records1[0].get('id');
		
		var view = Ext.create('TopLightApp.view.AdvertConAddView');
		view.show();
		
		Ext.getCmp('versionid').setValue(versionid);
		
		return false;
	},
	editCon : function(button){
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.create('TopLightApp.view.AdvertConEditView');
			view.show();
		var store = this.getAdvertConStoreStore();
		var data = store.getById(records[0].get('id'));
			view.down('form').loadRecord(data);
		return false;
	
	},
	saveCon : function(button){
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getAdvertConStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/toplightjson/saveCon.action',
			method : 'post',
			waitMsg:'请稍候...',
			success : function(form, action) {
				var returnType = action.result.result.returnType;
				if (returnType == 0) {
					win.close();
					store.reload();
					Ext.Msg.alert("提示", "保存成功!");
				} else if (returnType == 1) {
					Ext.Msg.alert('提示', "广告名称已存在，请核实！");

				} else {
					Ext.Msg.alert('失败', "保存失败");
				}
			},
			failure : function(form, action) {
				win.close();
				Ext.Msg.alert('提示', "保存失败!");
				store.reload();
			}
		});
		return false;
	},
	deleteCon : function(button) {
		var grid = Ext.getCmp('advertCon');
		var records = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (records.length > 0) {
			Ext.Msg.buttonText.yes='确定';
			Ext.Msg.buttonText.no="取消";
			Ext.Msg.confirm('提示', '你确认要删除选中的数据?', function(btn) {
				if (btn == 'yes') {
					var m = records;
					var jsonData = "";
					for (var i = 0; i < m.length; i++) {
						var ss = m[i].get('id');
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					Ext.Ajax.request({
								url : window.BIZCTX_PATH
										+ '/toplightjson/deleteCon.action',
								method : 'POST',
								params : "ids=" + jsonData,
								success : function(response, opts) {
									var text = response.responseText;
									var su = Ext.JSON.decode(text).result.su;
									if (su == 0) {
										Ext.Msg.alert('提示', "删除成功！");
										store.reload();
									} else{
										Ext.Msg.alert('提示', "删除失败！");
										store.reload();
									}
								},
								failure : function() {
									Ext.Msg.alert('提示', "服务器异常，稍后再试！");
									store.reload();
								}

							})

				}

			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的数据项!');
		};

	}
});

