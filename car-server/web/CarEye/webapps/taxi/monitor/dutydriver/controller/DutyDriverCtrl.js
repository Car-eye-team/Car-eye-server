Ext.define('DutyDriverApp.controller.DutyDriverCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['DutyDriverListStore','CarInfoListStore','CarListStore'],//声明该控制层要用到的store
    models: ['DutyDriverModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['DutyDriverListView',
		    'DutyDriverSearchView',
		    'CarInfoListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'dutyDriverEditView',
            selector: 'dutyDriverkEditView'
        },{
            ref: 'dutyDriverListView',
            selector: 'dutyDriverListView'
        }
    ],
    init: function() {
    	
		this.control({
			'dutyDriverListView button[action=add]' : {
				click : this.addDutyDriver
			},
			'dutyDriverListView button[action=delete]' : {
				click : this.deleteDutyDriver
			},
			'dutyDriverListView button[action=edit]' : {
				click : this.editDutyDriver
			},
			'dutyDriverListView button[action=searchtext]' : {
				//click : this.searchDutyDriver
			},
			'dutyDriverAddView button[action=save]' : {
				click : this.saveDutyDriver
			},
			'dutyDriverEditView button[action=save]' : {
				click : this.saveDutyDriver
			},
			'carInfoListView button[action=searchcar]' : {
				click : this.searchCarInfo
			},
			'dutyDriverListView':{
                render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['160105','160106','160107'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	searchCarInfo : function(button) {
		var carnumber = Ext.getCmp('c_carnumber').getRawValue();
		var blocid = Ext.getCmp('c_blocid').getValue();
		if (carnumber.length == 0 && typeof blocid == "undefined") {
					Ext.Msg.alert('提示', '请选择企业或者车辆!');
					return;
		}
		var store = this.getCarInfoListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(carnumber),
	            	blocid: blocid
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	    return false;
	},
	addDutyDriver : function(button) {
		var view = Ext.widget('transactionTypeAddView');
		view.show();
		return false;
	},
	searchDutyDriver : function(button) {
		var menuid = button.id+"";
	    var textgrid = Ext.getCmp('carInfogrid');
		var textdata = textgrid.getSelectionModel().getSelection();
		var textstore = textgrid.getStore();
		if(textdata.length<=0){
			Ext.Msg.alert('提示', '请勾选车辆!');
			return false;
		}else{
			       var tjsonData = "";
					for ( var i = 0, len = textdata.length; i < len; i++) {
						var ss = textdata[i].get("carid");
						if (i == 0) {
							tjsonData = tjsonData + ss;
						} else {
							tjsonData = tjsonData + "," + ss;
						}
		}
		var store = this.getDutyDriverListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	drivername: encodeURI(Ext.getCmp('dt_drivername').getValue()),
	            	blocid: encodeURI(Ext.getCmp('dt_blocid').getValue()),
	            	carids: encodeURI(tjsonData)
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
		}
	},
	saveDutyDriver : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getDutyDriverListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			Ext.MessageBox.wait('请稍候...', '提示');
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/transaction/transactionjson/saveDutyDriver.action',
				method : 'post',
				success : function(form, action) {
						Ext.MessageBox.updateProgress(1);
					    Ext.MessageBox.close();
						var resp = action.result.result;
						var su = resp.su;
						 if (su == -1) {
							win.close();
							Ext.Msg.alert("提示","系统异常,请与管理员联系!");
						}else if (su == -3) {
							Ext.Msg.alert("提示","存在相同的约车类型名!");
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
	deleteDutyDriver : function(button) {
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
								url : window.BIZCTX_PATH + '/transaction/transactionjson/deleteDutyDriver.action',
								method : 'POST',  
								params : "ids="+ jsonData,
								success : function(response) {
									var text = response.responseText;
									 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					 var su = obj.result.su;
									 if (su == -3) {
										Ext.Msg.alert('提示',"该数据不能被删除！");
										store.reload();
									}else if (su == -1) {
										Ext.Msg.alert('提示',"删除失败！");
										store.reload();
									}else{
										Ext.Msg.alert('提示',"删除成功！");
										store.reload();
									}
						   		},
								failure : function() {
									Ext.Msg.alert('提示',"删除失败");
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
	editDutyDriver : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.widget('transactionTypeEditView');
		view.show();
		var store = this.getDutyDriverListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	}
});



