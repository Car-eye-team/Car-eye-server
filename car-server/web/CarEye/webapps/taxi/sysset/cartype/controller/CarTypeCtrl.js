Ext.define('CarTypeApp.controller.CarTypeCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarTypeListStore'],//声明该控制层要用到的store
    models: ['CarTypeModel'],//声明该控制层要用到的model
    views: ['CarTypeSearchView','CarTypeListView','CarTypeAddView','CarTypeEditView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'carTypeListView',
            selector: 'carTypeListView'
        },{
            ref: 'carTypeAddView',
            selector: 'carTypeAddView'
        },{
            ref: 'carTypeEditView',
            selector: 'carTypeEditView'
        }
    ],
    init: function() {
		this.control({
            'carTypeListView button[action=add]' : {
				click : this.addCarType
			},
			'carTypeListView button[action=edit]' : {
				click : this.editCarType
			},
			'carTypeListView button[action=delete]' : {
				click : this.deleteCarType
			},
			'carTypeSearchView button[action=search]' : {
				click : this.searchCarType
			},
			'carTypeAddView button[action=save]' : {
				click : this.saveCarType
			},
			'carTypeEditView button[action=save]' : {
				click : this.saveCarType
			},'carTypeListView button[action=export]' : {
				click : this.exportInfo
			},
			'carTypeListView':{
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
	editCarType : function(button){
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.widget('carTypeEditView');
		view.show();
		var store = this.getCarTypeListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	
	},
	saveCarType : function(button){
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getCarTypeListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/cartypejson/saveCarType.action',
				method : 'post',
				waitMsg:'请稍候...',
				success : function(form, action) {
						var resp = action.result.result;
						var su = resp.su;
						if (su == -3) {
							Ext.Msg.alert("提示","车辆类型名称已存在，请更换!");
						}else if (su == -2) {
							win.close();
							Ext.Msg.alert("提示","系统异常,请与管理员联系!");
						}else {
							win.close();
							Ext.Msg.alert("提示","保存车辆类型成功!");
							store.reload();
						}
			    },
				failure : function(form, action) {
					win.close();
					Ext.Msg.alert('提示', "保存车辆类型失败!");
					store.reload();
				}
			});
			return false;
	},
	addCarType : function(button){
		var view = Ext.widget('carTypeAddView');
		view.show();
		return false;
	},
	deleteCarType : function(button) {
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
								url : window.BIZCTX_PATH + '/cartypejson/deleteCarType.action',
								method : 'POST',  
    							timeout : 15000,
								params : "ids="+ jsonData,
								success : function(response) {
									 var text = response.responseText;
									 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					 var su = obj.result.su;
									 if (su == -3) {
										Ext.Msg.alert('提示',"该类型已被应用，暂时不能删除！");
										store.reload();
									}else if (su == -1) {
										Ext.Msg.alert('提示',"车辆类型删除失败！");
										store.reload();
									}else{
										Ext.Msg.alert('提示',"车辆类型删除成功！");
										store.reload();
									}
						   		},
								failure : function() {
									Ext.Msg.alert('提示',"车辆类型删除失败！");
									store.reload();
								}
						});
					}
				});
			} else {
				Ext.Msg.alert('提示', '请选择要删除的车辆类型!');
			}
		return false;
	},
	searchCarType : function(button){
		var store = this.getCarTypeListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	               typename: encodeURI(Ext.getCmp('typename').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	    store.loadPage(1);
	    return false;
	},exportInfo : function (button){
			var userid=admin.id;
        	location.href=window.BIZCTX_PATH + '/cartypejson/exportExcel.action?userid='+userid;
	}
});
