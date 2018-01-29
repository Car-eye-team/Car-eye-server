Ext.define('PaymentParamSetApp.controller.PaymentParamSetCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CompanyListStore'],//声明该控制层要用到的store
    models: ['CompanyModel'],//声明该控制层要用到的model
    views: ['CompanyListView','PaymentParamSetView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
       {
            ref: 'companyListView',
            selector: 'companyListView'
         },{
            ref: 'paymentParamSetView',
            selector: 'paymentParamSetView'
        }
    ],
    init: function() {
		this.control({
			'companyListView button[action=add]' : {
				click : this.add
			},
			'companyListView button[action=edit]' : {
				click : this.edit
			},
			'companyListView button[action=delete]' : {
				click : this.del
			},
			'companyAddView button[action=save]' : {
				click : this.save
			},
			'companyEditView button[action=save]' : {
				click : this.save
			},
			'companyListView button[action=search]' : {
				click : this.search
			},
			'paymentParamSetView button[action=paramset]' : {
				click : this.updatePaymentParamSet
			},
			'companyListView':{
                render : this.buttonAccess
            }
			
		});
	},
	buttonAccess : function() {
		var buttonArray = ['121301','121302','121303'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	add : function(button){
		var view = Ext.create('PaymentParamSetApp.view.CompanyAddView');
		view.show();
		return false;
	},
	edit : function(button){
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.create('PaymentParamSetApp.view.CompanyEditView');
		view.show();
		var store = this.getCompanyListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	
	},
	save : function(button){
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getCompanyListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/setjson/addCompany.action',
				method : 'post',
				waitMsg:'请稍候...',
				success : function(form, action) {
						var resp = action.result.result;
						var su = resp.su;
						if (su == -1) {
							Ext.Msg.alert("提示","付款公司已存在，请更换!");
						}else if (su == -3) {
							Ext.Msg.alert("提示","编号已存在，请更换!");
						}else if (su == -2) {
							win.close();
							Ext.Msg.alert("提示","系统异常,请与管理员联系!");
						}else {
							win.close();
							Ext.Msg.alert("提示","保存成功!");
							store.reload();
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
	del : function(button) {
			var grid = button.up('grid');
			var data = grid.getSelectionModel().getSelection();
			var store = grid.getStore();
			if (data.length>0) {
				Ext.MessageBox.confirm('提示','你确认要删除？',
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
								url : window.BIZCTX_PATH + '/setjson/deleteCompany.action',
								method : 'POST',  
    							timeout : 15000,
								params : "ids="+ jsonData,
								success : function(response) {
									 var text = response.responseText;
									 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					 var su = obj.result.su;
									if (su == -1) {
										Ext.Msg.alert('提示',"删除失败！");
										store.reload();
									}else{
										Ext.Msg.alert('提示',"删除成功！");
										store.reload();
									}
						   		},
								failure : function() {
									Ext.Msg.alert('提示',"删除失败！");
									store.reload();
								}
						});
					}
				});
			} else {
				Ext.Msg.alert('提示', '请选择要删除的数据!');
			}
		return false;
	},
	search : function(button){
		var store = this.getCompanyListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	               company: encodeURI(Ext.getCmp('c_company').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	    store.loadPage(1);
	    return false;
	},
	//设置付款参数
	updatePaymentParamSet : function(button) {
		var s_id = Ext.getCmp('s_id').getValue();
		if(s_id == null || s_id == ''){
			Ext.Msg.alert("提示","请选择付款公司!");
			return;
		}
		
		var form = Ext.getCmp('parameterSet_Form');
		if (!form.getForm().isValid()) {
			return;
		}
		
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/setjson/updatePaymentParamSet.action',
			method : 'post',
			waitMsg:'正在设置,请稍侯...',
			
			success : function(form,action) {
				var resp = action.result.result;
				var su = resp.su;
				if (su > 0) {
					Ext.Msg.alert("提示","设置成功!");
				}else {
					Ext.Msg.alert("提示","设置失败!"); 
				}
		    },
			failure : function(form,action) {
				Ext.Msg.alert("提示","设置失败!"); 
			}
		});
		
		return false;
	}
	
	
	
});

