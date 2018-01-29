Ext.define('DeviceTypeApp.controller.DeviceTypeCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['DeviceTypeListStore'],//声明该控制层要用到的store
    models: ['DeviceTypeModel'],//声明该控制层要用到的model
    views: ['DeviceTypeSearchView','DeviceTypeListView','DeviceTypeAddView','DeviceTypeEditView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'deviceTypeSearchView',
            selector: 'deviceTypeSearchView'
        },{
            ref: 'deviceTypeListView',
            selector: 'deviceTypeListView'
        },{
            ref: 'deviceTypeAddView',
            selector: 'deviceTypeAddView'
        },{
            ref: 'deviceTypeEditView',
            selector: 'deviceTypeEditView'
        }
    ],
    init: function() {
    	
		this.control({
			'deviceTypeListView button[action=add]' : {
				click : this.addDeviceType
			},
			'deviceTypeListView button[action=delete]' : {
				click : this.deleteDeviceType
			},
			'deviceTypeListView button[action=export]' : {
				click : this.exportDeviceType
			},
			'deviceTypeListView button[action=edit]' : {
				click : this.editDeviceType
			},
			'deviceTypeSearchView button[action=search]' : {
				click : this.searchDeviceType
			},
			'deviceTypeAddView button[action=save]' : {
				click : this.saveDeviceType
			},
			'deviceTypeEditView button[action=save]' : {
				click : this.saveDeviceType
			},
			'deviceTypeListView':{
                render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['120301','120302','120303','120304'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	
	exportDeviceType : function (button){
		    var userid = admin.id;
		    var typename = encodeURI(Ext.getCmp('c_typename').getValue());
	        var    	usertype = encodeURI(Ext.getCmp('c_usertype').getValue());
	        var   	company = encodeURI(Ext.getCmp('c_company').getValue());
	        var    	inteltype = encodeURI(Ext.getCmp('c_inteltype').getValue());
        	location.href=window.BIZCTX_PATH + '/setjson/exportDeviceTypeList.action?userid='+
								userid+'&typename='+typename+'&usertype='+usertype+'&company='+company
								+'&inteltype='+inteltype;
	},
	
	
	addDeviceType : function(button) {
		var view = Ext.widget('deviceTypeAddView');
		view.show();
		return false;
	},
	searchDeviceType : function(button) {
		var store = this.getDeviceTypeListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	typename: encodeURI(Ext.getCmp('c_typename').getValue()),
	            	usertype: encodeURI(Ext.getCmp('c_usertype').getValue()),
	            	company: encodeURI(Ext.getCmp('c_company').getValue()),
	            	inteltype: encodeURI(Ext.getCmp('c_inteltype').getValue())
	            	
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	    return false;
	},
	saveDeviceType : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getDeviceTypeListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			Ext.MessageBox.wait('请稍候...', '提示');
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/setjson/saveDeviceType.action',
				method : 'post',
				success : function(form, action) {
						Ext.MessageBox.updateProgress(1);
					    Ext.MessageBox.close();
						var resp = action.result.result;
						var su = resp.su;
						if (su == -3) {
							Ext.Msg.alert("提示","终端类型已存在，请更换名称!");
						}else if (su == -1) {
							win.close();
							Ext.Msg.alert("提示","系统异常,请与管理员联系!");
						}else {
							win.close();
							Ext.Msg.alert("提示","操作终端类型信息成功!");
							store.reload();
						}
			    },
				failure : function(form, action) {
					Ext.MessageBox.updateProgress(1);
					Ext.MessageBox.close();
					win.close();
					Ext.Msg.alert('提示', "操作终端类型信息失败!");
					store.reload();
				}
			});
			return false;
	},
	
	deleteDeviceType : function(button) {
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
								url : window.BIZCTX_PATH + '/setjson/deleteDeviceType.action',
								method : 'POST',  
								params : "ids="+ jsonData,
								success : function(response) {
										var text = response.responseText;
                                        var result =  Ext.JSON.decode(text).result;
                                        var su = result.su;
                                   if(su==-2){
	                                    Ext.Msg.alert('提示',"该终端类型已使用，不能删除。");
                                   }else{
	                                    Ext.Msg.alert('提示',"数据删除成功");
										store.reload();
                                   }
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
	editDeviceType : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.widget('deviceTypeEditView');
		view.show();
		var store = this.getDeviceTypeListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	}
	
	
	
});

