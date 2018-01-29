Ext.define('CustomerTypeApp.controller.CustomerTypeCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CustomerTypeListStore'],//声明该控制层要用到的store
    models: ['CustomerTypeModel'],//声明该控制层要用到的model
    views: [
		    'CustomerTypeListView',
		    'CustomerTypeAddView',
		    'CustomerTypeEditView',
		    'CustomerTypeSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'customerTypeEditView',
            selector: 'customerTypekEditView'
        },{
            ref: 'customerTypeListView',
            selector: 'customerTypeListView'
        },{
            ref: 'customerTypeAddView',
            selector: 'customerTypeAddView'
        },{
            ref: 'customerCarListView',
            selector: 'customerCarListView'
        },{
            ref: 'customerTypeSearchView',
            selector: 'customerTypeSearchView'
        }
    ],
    init: function() {
    	
		this.control({
			'customerTypeListView button[action=add]' : {
				click : this.addCustomerType
			},
			'customerTypeListView button[action=delete]' : {
				click : this.deleteCustomerType
			},
			'customerTypeListView button[action=edit]' : {
				click : this.editCustomerType
			},
			'customerTypeSearchView button[action=search]' : {
				click : this.searchCustomerType
			},
			'customerTypeAddView button[action=save]' : {
				click : this.saveCustomerType
			},
			'customerTypeEditView button[action=save]' : {
				click : this.saveCustomerType
			},
			'customerTypeListView button[action=export]' : {
				click : this.exportInfo
			},
			'customerTypeListView':{
                render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['150205','150206','150207','150208'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	button1Access : function() {
		var buttonArray = ['150201','150202','150203','150204'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	addCustomerType : function(button) {
		var view = Ext.widget('customerTypeAddView');
		view.show();
		return false;
	},
	addCustomer : function(button) {
		var view = Ext.widget('customerAddView');
		view.show();
		return false;
	},
	searchCustomerType : function(button) {
		var store = this.getCustomerTypeListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	typename: encodeURI(Ext.getCmp('dbt_customertypename').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	saveCustomerType : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getCustomerTypeListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			Ext.MessageBox.wait('请稍候...', '提示');
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/customer/customerjson/saveCustomerType.action',
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
							Ext.Msg.alert("提示","存在相同的客户类型名!");
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
	deleteCustomerType : function(button) {
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
								url : window.BIZCTX_PATH + '/customer/customerjson/deleteCustomerType.action',
								method : 'POST',  
								params : "ids="+ jsonData,
								success : function(response) {
									var text = response.responseText;
									 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					 var su = obj.result.su;
									 if (su == -3) {
										Ext.Msg.alert('提示',"此客户类型下面有客户信息不能删除！");
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
	editCustomerType : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.widget('customerTypeEditView');
		view.show();
		var store = this.getCustomerTypeListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	},
	editCustomer : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.widget('customerEditView');
		view.show();
		  
		var store = this.getCustomerListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	},
	exportInfo : function (button){
		    var typename= encodeURI(Ext.getCmp('dbt_customertypename').getValue())
	        var strParams="&typename="+typename;
	        
        	location.href=window.BIZCTX_PATH + '/customer/customerjson/exportExcel.action?'+strParams;
	}
});
