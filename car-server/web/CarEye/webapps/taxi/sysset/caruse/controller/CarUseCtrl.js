Ext.define('CarUseApp.controller.CarUseCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarUseListStore'],//声明该控制层要用到的store
    models: ['CarUseModel'],//声明该控制层要用到的model
    views: ['CarUseSearchView','CarUseListView','CarUseAddView','CarUseEditView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'carUseListView',
            selector: 'carUseListView'
        },{
            ref: 'carUseAddView',
            selector: 'carUseAddView'
        },{
            ref: 'carUseEditView',
            selector: 'carUseEditView'
        }
    ],
    init: function() {
		this.control({
            'carUseListView button[action=add]' : {
				click : this.addCarUse
			},
			'carUseListView button[action=edit]' : {
				click : this.editCarUse
			},
			'carUseListView button[action=delete]' : {
				click : this.deleteCarUse
			},
			'carUseSearchView button[action=search]' : {
				click : this.searchCarUse
			},
			'carUseAddView button[action=save]' : {
				click : this.saveCarUse
			},
			'carUseEditView button[action=save]' : {
				click : this.saveCarUse
			},
			'carUseListView':{
                render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['12010101','12010102','12010103'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	editCarUse : function(button){
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.widget('carUseEditView');
		view.show();
		var store = this.getCarUseListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	
	},
	saveCarUse : function(button){
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getCarUseListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/carusejson/saveCarUse.action',
				method : 'post',
				waitMsg:'请稍候...',
				success : function(form, action) {
						var resp = action.result.result;
						var su = resp.su;
						if (su == -3) {
							Ext.Msg.alert("提示","车辆用途名称已存在，请更换!");
						}else if (su == -2) {
							win.close();
							Ext.Msg.alert("提示","系统异常,请与管理员联系!");
						}else {
							win.close();
							Ext.Msg.alert("提示","保存车辆用途成功!");
							store.reload();
						}
			    },
				failure : function(form, action) {
					win.close();
					Ext.Msg.alert('提示', "保存车辆用途失败!");
					store.reload();
				}
			});
			return false;
	},
	addCarUse : function(button){
		var view = Ext.widget('carUseAddView');
		view.show();
		return false;
	},
	deleteCarUse : function(button) {
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
								url : window.BIZCTX_PATH + '/carusejson/deleteCarUse.action',
								method : 'POST',  
    							timeout : 15000,
								params : "ids="+ jsonData,
								success : function(response) {
									 var text = response.responseText;
									 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					 var su = obj.result.su;
									 if (su == -3) {
										Ext.Msg.alert('提示',"该数据不能被删除！");
										store.reload();
									 }else if (su == -1) {
										Ext.Msg.alert('提示',"车辆用途删除失败！");
										store.reload();
									 }else{
										Ext.Msg.alert('提示',"车辆用途删除成功！");
										store.reload();
									}
						   		},
								failure : function() {
									Ext.Msg.alert('提示',"车辆用途删除失败！");
									store.reload();
								}
						});
					}
				});
			} else {
				Ext.Msg.alert('提示', '请选择要删除的车辆用途!');
			}
		return false;
	},
	searchCarUse : function(button){
		var store = this.getCarUseListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	               usename: encodeURI(Ext.getCmp('usename').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	    store.loadPage(1);
	    return false;
	}
});
