Ext.define('CustomerApp.controller.CustomerCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CustomerListStore','TypeListStore','SexStore'],//声明该控制层要用到的store
    models: ['CustomerModel','TypeModel'],//声明该控制层要用到的model
    views: ['CustomerListView',
		    'CustomerAddView',
		    'CustomerEditView',
		    'CustomerSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
       {
            ref: 'customerListView',
            selector: 'customerListView'
        },{
            ref: 'customerTypeAddView',
            selector: 'customerTypeAddView'
        },{
            ref: 'customerCarListView',
            selector: 'customerCarListView'
        },{
            ref: 'customerSearchView',
            selector: 'customerSearchView'
        }
    ],
    init: function() {
    	
		this.control({
			'customerSearchView button[action=search]' : {
				click : this.searchCustomer
			},
			'customerListView button[action=add]' : {
				click : this.addCustomer
			},
			'customerListView button[action=delete]' : {
				click : this.deleteCustomer
			},
			'customerListView button[action=edit]' : {
				click : this.editCustomer
			},
			'customerAddView button[action=save]' : {
				click : this.saveCustomer
			},
			'customerEditView button[action=save]' : {
				click : this.saveCustomer
			},
			'customerListView button[action=export]' : {
				click : this.exportInfoTwo
			},
			'customerListView':{
                render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['150201','150202','150203','150204'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
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
	searchCustomer : function(button) {
		var store = this.getCustomerListStoreStore();
		store.clearFilter(true);
		var stime=Ext.util.Format.date(Ext.getCmp('db_stime').getValue(), 'Y-m-d H:i:s');
		var etime=Ext.util.Format.date(Ext.getCmp('db_etime').getValue(), 'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
			Ext.Msg.alert("提示","结束日期不能小于开始日期!");
			return;
		}else{
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	cname: encodeURI(Ext.getCmp('db_cname').getValue()),
	            	typename: encodeURI(Ext.getCmp('db_typename').getValue()),
	            	stime: encodeURI(stime),
	            	etime: encodeURI(etime)
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
		}
	    return false;
	},
	saveCustomer : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getCustomerListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			Ext.MessageBox.wait('请稍候...', '提示');
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/customer/customerjson/saveCustomer.action',
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
							Ext.Msg.alert("提示","存在相同的客户名!");
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
	deleteCustomer : function(button) {
		var grid = button.up('grid');
		//var grid=Ext.getCmp('dataBankListViewGrid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length>0) {
			Ext.MessageBox.confirm('提示','你确认要删除选中的数据？',
			function(btn) {
				if (btn == "yes") {
					var m = grid.getSelectionModel().getSelection();
					var jsonData = "";
					var customertypeids="";
					for ( var i = 0, len = m.length; i < len; i++) {
						var ss = m[i].get("id");
						
						var types=m[i].get("customertypeids");
					
						if (i == 0) {
							jsonData = jsonData + ss;
							customertypeids=customertypeids+types;
						} else {
							jsonData = jsonData + "," + ss;
							customertypeids=customertypeids+","+types;
						}
						
					}
					Ext.Ajax.request( {
								url : window.BIZCTX_PATH + '/customer/customerjson/deleteCustomer.action?customertypeids='+customertypeids,
								method : 'POST',  
								params : "ids="+ jsonData,
								success : function(response) {
									var text = response.responseText;
									 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					 var su = obj.result.su;
									 if (su == -1) {
										Ext.Msg.alert('提示',"已抢答客户不能被删除");
										store.reload();
									}else {
										Ext.Msg.alert('提示',"删除成功");
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
	exportInfoTwo : function (button){
	        var cname= encodeURI(Ext.getCmp('db_cname').getValue());
	        var typename= encodeURI(Ext.getCmp('db_typename').getValue());
	        var stime= Ext.util.Format.date(Ext.getCmp('db_stime').getValue(), 'Y-m-d H:i:s');
	        var etime= Ext.util.Format.date(Ext.getCmp('db_etime').getValue(), 'Y-m-d H:i:s');
			if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
			}else{
		        var strParams="&cname="+cname+"&typename="+typename+"&stime="+encodeURI(stime)+"&etime="+encodeURI(etime);
	        	location.href=window.BIZCTX_PATH + '/customer/customerjson/exportExcelTwo.action?'+strParams;
			}
	}
});

 function validTime(startTime,endTime){
       var arr1 = startTime.split("-");
       var arr2 = endTime.split("-");
       var date1=new Date(parseInt(arr1[0]),parseInt(arr1[1])-1,parseInt(arr1[2]),0,0,0);
       var date2=new Date(parseInt(arr2[0]),parseInt(arr2[1])-1,parseInt(arr2[2]),0,0,0);
       if(date1.getTime()>date2.getTime()) {                               
               return -1;
         }else{
             return 1;
         }
         return -1;
}
