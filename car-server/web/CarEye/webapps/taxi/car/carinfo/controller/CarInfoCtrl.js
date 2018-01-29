Ext.define('CarInfoApp.controller.CarInfoCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarInfoListStore','CarListStore','CarStatusStore','DeviceTypeStore','CarTypeStore','CarUseStore',
    		 'CarNumberColorStore','OilTypeStore','CarDriverStore','CityStore','DistrictStore','ProvinceStore',
    		 'CarMoveTreeStore','DeptWinTreeStore','YxDeviceListStore','FuelTypeStore','NowStatusStore','VedioTypeStore',
    		 'CarColorStore','OperateStatusStore','DrivercodeStore','YesOrNoStore','ElectagstatusStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['CarInfoModel','DeviceTypeModel','CarTypeModel','CarUseModel','CarDriverModel','CityInfoModel',
              'YxDeviceInfoModel'],//声明该控制层要用到的model
    views: ['CarInfoSearchView','CarInfoListView','CarInfoAddView','CarInfoEditView','CarWinTreeView',
     		'DeptWinTreeView','CarMoveWinView','CarInfoDetailsView','OperationCertificateAddView',
     		'OperationCertificateEditView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'carInfoSearchView',
            selector: 'carInfoSearchView' 
        },{
            ref: 'carInfoListView',
            selector: 'carInfoListView'
        },{
            ref: 'carInfoAddView',
            selector: 'carInfoAddView'
        },{
            ref: 'carInfoEditView',
            selector: 'carInfoEditView'
        },{
            ref: 'carMoveWinView',
            selector: 'carMoveWinView'
        },{
            ref: 'carWinTreeView',
            selector: 'carWinTreeView'
        },{
            ref: 'deptWinTreeView',
            selector: 'deptWinTreeView'
        }
    ],
    init: function() {
    	
		this.control({
			'carInfoListView button[action=add]' : {
				click : this.addCarInfo
			},
			'carInfoListView button[action=delete]' : {
				click : this.deleteCarInfo
			},
			'carInfoListView button[action=edit]' : {
				click : this.editCarInfo
			},
			'carInfoListView button[action=export]' : {
				click : this.exportCarInfo
			},
			'carInfoListView button[action=carmove]' : {
				click : this.createCarMove
			},
			'carInfoSearchView button[action=search]' : {
				click : this.searchCarInfo
			},
			'carInfoSearchView #blocid' : {
				select : this.loadDeptCar
			},
			'carInfoAddView button[action=save]' : {
				click : this.saveCarInfo
			},
			'carInfoAddView form #province' : {
				select : this.loadCityAdd
			},
			'carInfoAddView form #city' : {
				select : this.loadDistrictAdd
			},
			'carInfoAddView form #factory' : {
				select : this.loadCarBrandType
			},
			'carInfoAddView form #carnumber' : {
				blur : this.loadDrivercode
			},
			'carInfoAddView form #vediotype' : {
				select : this.loadVediono
			},
			'carInfoEditView button[action=save]' : {
				click : this.saveCarInfo
			},
			'carInfoEditView form #carnumber' : {
				blur : this.loadDrivercode
			},
			'carInfoEditView form #province' : {
				select : this.loadCityEdit
			},
			'carInfoEditView form #city' : {
				select : this.loadDistrictEdit
			},
			'carInfoEditView form #vediotype' : {
				select : this.loadVediono
			},
			'carInfoListView':{
                 render : this.buttonAccess
            },
			'operationCertificateAddView button[action=save]' : {
				click : this.saveOperate
			},
			'operationCertificateEditView button[action=save]' : {
				click : this.saveOperate
			}
		});
	},
	buttonAccess : function() {
		var buttonArray = ['130101','130102','130103','130104','130105','130112'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	loadDeptCar : function(){
		Ext.getCmp('c_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var deptid = Ext.getCmp('c_deptid').getValue();
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
	loadVediono : function(){
		var terminal = Ext.getCmp('terminal').getValue().trim();
		if(terminal != null && terminal.length > 0){
			Ext.getCmp('vediono').setValue(terminal);
		}
	},
	 loadCar : function(){
			var store = this.getCarPageListStoreStore();
			store.clearFilter(true);
			store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('c_carnumber').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.reload();
		},
	addCarInfo : function(button) {
		var view = Ext.widget('carInfoAddView');
		view.show();
		return false;
	},
	searchCarInfo : function(button) {
		var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s');
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
		
		var store = this.getCarInfoListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('c_carnumber').getValue()),
	            	devicetype: encodeURI(Ext.getCmp('c_devicetype').getValue()),
	            	deptid: Ext.getCmp('c_deptid').getValue(),
	            	carstatus:Ext.getCmp('c_carstatus').getValue(),
	            	terminal:Ext.getCmp('c_terminal').getValue(),
	            	cartype:Ext.getCmp('c_cartype').getValue(),
	            	caruse:Ext.getCmp('c_caruse').getValue(),
	            	
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	exportCarInfo : function (button){
        var carnumber = Ext.getCmp('c_carnumber').getValue();
    	var devicetype = Ext.getCmp('c_devicetype').getValue();
    	var deptid = Ext.getCmp('c_deptid').getValue();
    	var carstatus = Ext.getCmp('c_carstatus').getValue();
    	var terminal = Ext.getCmp('c_terminal').getValue();
    	var cartype = Ext.getCmp('c_cartype').getValue();
    	var caruse = Ext.getCmp('c_caruse').getValue();
		var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s');
		
		location.href=window.BIZCTX_PATH + '/carjson/exportExcel.action?carnumber='+carnumber+"&devicetype="+devicetype+
		"&deptid="+deptid+"&carstatus="+carstatus+"&terminal="+terminal+"&cartype="+cartype+
		"&caruse="+caruse+"&stime="+stime+"&etime="+etime;
		
	},
	saveCarInfo : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			if (!form.getForm().isValid()) {
				return;
			}
			var store = this.getCarInfoListStoreStore();
			
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/carjson/saveCarInfo.action',
				method : 'post',
//				timeout:90,
				waitMsg:'正在保存,请稍候...',
				success : function(form, action) {
						var resp = action.result.result;
						var su = resp.su;
						var deptid = resp.deptid;
						var predeptid = resp.predeptid;
						if (su == -2) {
							Ext.Msg.alert("提示","车牌号已存在，请更换车牌号!");
						}else if (su == -3) {
							Ext.Msg.alert("提示","手机卡号已存在，请更换手机卡号!");
						}else if (su == -4) {
							Ext.Msg.alert("提示","终端设备已存在，请更换终端设备号!");
						}else if (su == -5) {
							Ext.Msg.alert("提示","车牌号与终端号相同，请更换其一!");
						}else if (su == -6) {
							Ext.Msg.alert("提示","设备号已经存在，请更换设备号!");
						}else if (su == -1) {
							win.close();
							Ext.Msg.alert("提示","系统异常,请与管理员联系!");
						}else {
							win.close();
							Ext.Msg.alert("提示","操作车辆信息成功!");
							
//							if(typeof deptid != "undefined"){
//								var jsonData = "";
//								if(typeof predeptid != "undefined"){
//								     jsonData = deptid +　',' + predeptid;
//								}else{
//									 jsonData = deptid;
//								}
//								
//								Ext.Ajax.request( {
//									url : window.BIZCTX_PATH + '/carjson/updateDeptidCarCount.action',
//									method : 'POST',  
//									timeout : 150000,
//									params : {ids:jsonData},
//									success : function(response) {
//									},
//									failure : function() {
//									}
//								});
//								
//							}
							
							store.reload();
						}
			    },
				failure : function(form, action) {
					win.close();
					Ext.Msg.alert('提示', "操作车辆信息信息失败!");
					store.reload();
				}
			});
			return false;
	},
	deleteCarInfo : function(button) {
			var grid = button.up('grid');
			var data = grid.getSelectionModel().getSelection();
			var store = grid.getStore();
			if (data.length>0) {
				Ext.MessageBox.confirm('提示','你确认要删除选中的数据？',
				function(btn) {
					if (btn == "yes") {
						var m = grid.getSelectionModel().getSelection();
						var jsonData = "";
						var ters = "";
						for ( var i = 0, len = m.length; i < len; i++) {
							var ss = m[i].get("id");
							var tt =  m[i].get("terminal");
							if (i == 0) {
								jsonData = jsonData + ss;
								ters = ters + tt;
							} else {
								jsonData = jsonData + "," + ss;
								ters = ters + ","+ tt;
							}
						}
						
						Ext.Ajax.request( {
									url : window.BIZCTX_PATH + '/carjson/deleteCarInfo.action',
									method : 'POST',  
									params : "ids="+ jsonData+"&ters="+ters,
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
	editCarInfo : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		
		
		var view = Ext.widget('carInfoEditView');
		view.show();
		
		var store = this.getCarInfoListStoreStore();
		var data = store.getById(records[0].get('id'));
		
		//展示之前加载对应市
		var citystore = this.getCityStoreStore();
		citystore.clearFilter(true);
		citystore.on('beforeload', function (citystore, options) {
                var new_params = { 
               		 clevel: 2,
               		 provinceszcode: encodeURI(data.get('province'))
                };
                Ext.apply(citystore.proxy.extraParams, new_params);
            });
	    citystore.load();
	    
		//展示之前加载对应县
		var districtstore = this.getDistrictStoreStore();
		districtstore.clearFilter(true);
		districtstore.on('beforeload', function (districtstore, options) {
                var new_params = { 
               		 clevel: 3,
               		 cityszcode: encodeURI(data.get('city'))
                };
                Ext.apply(districtstore.proxy.extraParams, new_params);
            });
	    districtstore.load();
	    
	    
	    
	    //加载车辆对应的当班司机
	    var drivercodestore = this.getDrivercodeStoreStore();
		drivercodestore.clearFilter(true);
		drivercodestore.on('beforeload', function (drivercodestore, options) {
                var new_params = { 
               		 carnumber: encodeURI(data.get('carnumber'))
                };
                Ext.apply(drivercodestore.proxy.extraParams, new_params);
            });
	    drivercodestore.load();
	    
		view.down('form').loadRecord(data);
		return false;
	},
	detailsCarInfo : function(id) {
				var store = this.getCarInfoListStoreStore();
				var data = store.getById(id);
			
				var view = Ext.widget('carInfoDetailsView');
//				var view = Ext.create('TripApp.view.CarTripWindow');
				view.show();
				
				//展示之前加载对应市
				var citystore = this.getCityStoreStore();
				citystore.clearFilter(true);
				citystore.on('beforeload', function (citystore, options) {
		                var new_params = { 
		               		 clevel: 2,
		               		 provinceszcode: encodeURI(data.get('province'))
		                };
		                Ext.apply(citystore.proxy.extraParams, new_params);
		            });
			    citystore.load();
			    
				//展示之前加载对应县
				var districtstore = this.getDistrictStoreStore();
				districtstore.clearFilter(true);
				districtstore.on('beforeload', function (districtstore, options) {
		                var new_params = { 
		               		 clevel: 3,
		               		 cityszcode: encodeURI(data.get('city'))
		                };
		                Ext.apply(districtstore.proxy.extraParams, new_params);
		            });
			    districtstore.load();
			    
				view.down('form').loadRecord(data);
				return false;
	},
	
	loadCityAdd : function() {
		var store = this.getCityStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
                var new_params = { 
               		 clevel: 2,
               		 provinceszcode: encodeURI(Ext.getCmp('province').getValue())
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
	    store.load();
	    Ext.getCmp('city').setValue("");
		Ext.getCmp('district').setValue("");
		return false;
	},
	loadDistrictAdd : function() {
		var store = this.getDistrictStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
                var new_params = { 
               		 clevel: 3,
               		 cityszcode: encodeURI(Ext.getCmp('city').getValue())
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
	    store.load();
		Ext.getCmp('district').setValue("");
		return false;
	},
	loadCityEdit : function() {
		var store = this.getCityStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
                var new_params = { 
               		 clevel: 2,
               		 provinceszcode: encodeURI(Ext.getCmp('province').getValue())
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
	    store.load();
	    Ext.getCmp('city').setValue("");
		Ext.getCmp('district').setValue("");
		return false;
	},
	loadDistrictEdit : function() {
		var store = this.getDistrictStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
                var new_params = { 
               		 clevel: 3,
               		 cityszcode: encodeURI(Ext.getCmp('city').getValue())
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
	    store.load();
		Ext.getCmp('district').setValue("");
		return false;
	},
	loadCarBrandType : function() {
		var store = this.getCarBrandTypeStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
                var new_params = { 
               		 factory: encodeURI(Ext.getCmp('factory').getValue())
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
	    store.load();
	    Ext.getCmp('carmodel').setValue("");
		return false;
	},
	loadDrivercode : function() {
		var store = this.getDrivercodeStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
                var new_params = { 
               		 carnumber: encodeURI(Ext.getCmp('carnumber').getValue())
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
	    store.load();
		return false;
	},
	loadCarTypeName : function() {
		var store = this.getCarTypeNameStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
                var new_params = { 
               		 typeid: encodeURI(Ext.getCmp('car_typeid').getValue())
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
	    store.load();
	    Ext.getCmp('cartype').setValue("");
		return false;
	},
	searchCarTypeName : function() {
		var store = this.getCarTypeNameStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
                var new_params = { 
               		 typeid: encodeURI(Ext.getCmp('c_cartype').getValue())
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
	    store.load();
	    Ext.getCmp('c_cartypename').setValue("");
		return false;
	},
	createCarMove : function(button){
		
		var store = this.getCarMoveTreeStoreStore();
		store.clearFilter(true);
	    store.reload();
	    
		var store1 = this.getDeptWinTreeStoreStore();
		store1.clearFilter(true);
	    store1.reload();
		
		var view = Ext.widget('carMoveWinView');
		view.show();
	},
	createOperate : function(button){	
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录');
			return;
		}
		
		var carid = records[0].get('carid');
		
		Ext.Ajax.request( {
		url: window.BIZCTX_PATH + '/carjson/findOperateCertificateByCarid.action', //请求的服务器地址
	      params:{ 
	      		carid : carid
	      },
			method : 'POST',  
			timeout : 5000,
			success : function(response) {
				 var action = Ext.JSON.decode(response.responseText);
				 var data = action.result.data;
				 
	//			 var carid =  action.result.data.carid;
				 
				 var su = action.result.su;
				 if(su == 1){
				 	var view = Ext.widget('operationCertificateAddView');
					view.show();
					Ext.getCmp('carid').setValue(carid);
				 }else if(su == 2){
				 	var view = Ext.widget('operationCertificateEditView');
					view.show();
					Ext.getCmp('id').setValue(data.id);
					Ext.getCmp('carid').setValue(data.carid);
					Ext.getCmp('operatenumber').setValue(data.operatenumber);
					Ext.getCmp('operatestatus').setValue(data.operatestatus+'');
					Ext.getCmp('operateproperty').setValue(data.operateproperty);
					Ext.getCmp('licensetime').setValue(data.licensetime);
					Ext.getCmp('certificatetype').setValue(data.certificatetype);
					Ext.getCmp('firstregisttime').setValue(data.firstregisttime);
					Ext.getCmp('entryperson').setValue(data.entryperson);
					Ext.getCmp('entrytime').setValue(data.entrytime);
					Ext.getCmp('remark').setValue(data.remark);
				 }
			},
			failure : function() {
			}
	    });
		
		return false;
	},
	saveOperate : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			if (!form.getForm().isValid()) {
				return;
			}
			var store = this.getCarInfoListStoreStore();
			
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/carjson/saveOperateCertificate.action',
				method : 'post',
				timeout:90,
				waitMsg:'正在保存,请稍候...',
				success : function(form, action) {
					//	var resp = action.result.result;
					//	var su = resp.su;
							win.close();
							Ext.Msg.alert("提示","保存成功!");
							
							store.reload();
				} ,
				failure : function(form, action) {
					win.close();
					Ext.Msg.alert('提示', "保存失败!");
					store.reload();
				}
			});
			return false;
	},
	carMove : function(button){
		var win = button.up('window');
		var store = this.getCarMoveTreeStoreStore();
		var view = Ext.getCmp('carWinTreeView');
		var treeMenu = view.getChecked();
		var jsonData = "";
		if(treeMenu.length == 0||treeMenu == "undefined" ){
			Ext.Msg.alert('提示',"请选择需要转移的车辆");
			return;
		}
		
		var movedeptid = Ext.getCmp('move_deptid').getValue();
		var movedeptname = Ext.getCmp('move_deptname').getValue();
		
		if(movedeptid == "undefined" || movedeptid.length == 0){
			Ext.Msg.alert('提示',"请单击需要转移到的企业");
			return;
		}
		
		for ( var i = 0;i < treeMenu.length; i++) {
			var ss = treeMenu[i].get("id");
			if (i == 0) {
				jsonData = jsonData + ss;
			} else {
				jsonData = jsonData + "," + ss;
			}
		}
		
		Ext.MessageBox.confirm('提示','您确认要把选中的车辆转移至【'+movedeptname+'】?',
			function(btn) {
				if (btn == "yes") {
					var mask=new Ext.LoadMask('carMoveWinView',{msg:"正在转移车辆,请稍后......"});
					mask.show();
					Ext.Ajax.request( {
							url : window.BIZCTX_PATH + '/carjson/carMove.action',
							method : 'POST',  
							timeout : 150000,
							params : {deptid:movedeptid,ids:jsonData},
							success : function(response) {
								mask.hide();
								Ext.Msg.alert('提示',"车辆转移成功");
								
								var text = response.responseText;
								var result =  Ext.JSON.decode(text).result;
								var deptids = result.deptids;
								
								Ext.Ajax.request( {
									url : window.BIZCTX_PATH + '/carjson/updateDeptidCarCount.action',
									method : 'POST',  
									timeout : 150000,
									params : {ids:deptids},
									success : function(response) {
									},
									failure : function() {
									}
								});
								
								win.close();
								store.reload();
							},
							failure : function() {
								Ext.Msg.alert('提示',"车辆转移失败");
							}
					});
				}
			});
		return false;
	}
	
	
	
});

