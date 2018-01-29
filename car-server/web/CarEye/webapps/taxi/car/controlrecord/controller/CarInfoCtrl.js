Ext.define('CarInfoApp.controller.CarInfoCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarInfoListStore','CarTypeStore','ControlRecordListStore','ControlTypeStore','CarNumberStore2','CarNumberStore'
    		,'Type1Store','Type2Store','Type3Store','CarPageListStore'],//声明该控制层要用到的store
    views: ['CarInfoSearchView','ControlRecordEditView','CarInfoListView','ControlRecordListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'carInfoSearchView',
            selector: 'carInfoSearchView'
        },{
            ref: 'controlRecordEditView',
            selector: 'controlRecordEditView'
        },{
            ref: 'carInfoListView',
            selector: 'carInfoListView'
        },{
            ref: 'controlRecordListView',
            selector: 'controlRecordListView'
        }
    ],
    init: function() {
    	
		this.control({
			'carInfoListView button[action=edit]' : {
				click : this.addControl
			},
			'controlRecordEditView button[action=save]' : {
				click : this.saveControl
			},
			'carInfoListView button[action=query]' : {
				click : this.lookControlRecord
			},
			'carInfoSearchView button[action=search]' : {
				click : this.searchCarInfo
			},
			'controlRecordListView button[action=search]' : {
				click : this.searchControlRecord
			},
			'carInfoListView':{
                render : this.buttonAccess
            },
			'carInfoSearchView #carnumber' : {
				change : this.loadCar
			},
			'controlRecordListView #r_carnumber' : {
				change : this.loadCar2
			},
			'carInfoSearchView #blocid' : {
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
		var buttonArray = ['14040401'];
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
	loadCar2 : function(){
		var store = this.getCarNumberStore2Store();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	carnumber: encodeURI(Ext.getCmp('r_carnumber').getValue())
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	searchControlRecord : function(button) {
		
		var stime = Ext.util.Format.date(Ext.getCmp('r_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('r_etime').getValue(), 'Y-m-d H:i:s');
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
		
		var store = this.getControlRecordListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('r_carnumber').getValue()),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('r_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('r_etime').getValue(), 'Y-m-d H:i:s'))
	            	
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
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
	            	cartype: encodeURI(Ext.getCmp('c_cartype').getValue()),
	            	deptid: Ext.getCmp('c_blocid').getValue(),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s'))
	            	
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	
	lookControlRecord : function(button) {
		var grid = button.up('grid');
		var view = Ext.widget('controlRecordListView');
		view.show();
		var store = this.getControlRecordListStoreStore();
		store.clearFilter(true);
	    store.load({ params: { start: 0, limit: 15} });
		return false;
	},
	addControl : function(button) {
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length>0) {
			var view = Ext.widget('controlRecordEditView');
			view.show();
		} else {
			Ext.Msg.alert('提示', '请选择要控制的车辆!');
		}
	},
	saveControl : function(button) {
		
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getCarInfoListStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		
		var grid = Ext.getCmp('carInfoListGrid');
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
		var con1 = Ext.getCmp('con1').getValue();
		var con2 = Ext.getCmp('con2').getValue();
		var con3 = Ext.getCmp('con3').getValue();
		var con4 = Ext.getCmp('con4').getValue();
		var con5 = Ext.getCmp('con5').getValue();
		var type = con1 + con2 + con3 + con4 + con5+"000";
		
		if(con1 == 0){
			var con1text = "车辆油路:"+"恢复"; 
		}else{
			var con1text = "车辆油路:"+"断开";
		}
		
		if(con2 == 0){
			var con2text = "车辆电路:"+"恢复"; 
		}else{
			var con2text = "车辆电路:"+"断开";
		}
		
		if(con3 == 0){
			var con3text = "车门:"+"解锁"; 
		}else{
			var con3text = "车门:"+"加锁";
		}
		
		if(con4 == 0){
			var con4text = "计价器:"+"解锁"; 
		}else{
			var con4text = "计价器:"+"加锁";
		}
		
		if(con5 == 0){
			var con5text = "刷卡模块:"+"恢复"; 
		}else{
			var con5text = "刷卡模块:"+"锁定";
		}
		
		var controltext = con1text+","+con2text+","+con3text+","+con4text+","+con5text+"!";
		
		Ext.Ajax.request( {
			url : window.BIZCTX_PATH + '/carjson/CheckCarInfo.action',
			method : 'POST',  
			params : {ids:jsonData,type:type,controltext:controltext},
			success : function(response) {
			var text = response.responseText;
			 var obj = eval( "(" + text + ")" );//转换后的JSON对象
			 var su = obj.result.su;
			 if (su == -1) {
				Ext.Msg.alert('提示', "车辆控制设置失败!");
				store.reload();
			}else {
				Ext.Msg.alert("提示","车辆控制设置成功!");
				win.close();
				store.reload();
			}
   			},
			failure : function() {
				Ext.Msg.alert('提示', "车辆控制设置失败!");
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
						for ( var i = 0, len = m.length; i < len; i++) {
							var ss = m[i].get("id");
							if (i == 0) {
								jsonData = jsonData + ss;
							} else {
								jsonData = jsonData + "," + ss;
							}
						}
						Ext.Ajax.request( {
									url : window.BIZCTX_PATH + '/carjson/deleteCarInfo.action',
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
	}
	
});

