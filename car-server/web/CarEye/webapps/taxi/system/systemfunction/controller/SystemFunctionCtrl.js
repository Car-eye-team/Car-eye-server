
Ext.define('systemFunctionApp.controller.SystemFunctionCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['SystemFunctionListStore','MenuStore','MenuLevelStore'],//声明该控制层要用到的store
    models: ['SystemFunctionModel','MenuModel'],//声明该控制层要用到的model
    views: ['SystemFunctionSearchView','SpaceView','SystemFunctionListView','SystemFunctionAddView','SystemFunctionEditView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'systemFunctionSearchView',
            selector: 'systemFunctionSearchView'
        },{ 
            ref: 'spaceView',
            selector: 'spaceView'
        },{
            ref: 'systemFunctionListView',
            selector: 'systemFunctionListView'
        },{
            ref: 'systemFunctionAddView',
            selector: 'systemFunctionAddView'
        },{
            ref: 'systemFunctionEditView',
            selector: 'systemFunctionEditView'
        }
    ],
    init: function() {
		this.control({
			'systemFunctionListView button[action=add]' : {
				click : this.createSystemFunction
			},
			'systemFunctionAddView button[action=save]' : {
				click : this.saveSystemFunction
			},
			'systemFunctionListView button[action=active]' : {
				click : this.activeSystemFunction
			},
			'systemFunctionListView button[action=inactive]' : {
				click : this.inactiveSystemFunction
			},
			'systemFunctionListView button[action=edit]' : {
				click : this.editSystemFunction
			},
			'systemFunctionSearchView button[action=search]' : {
				click : this.searchSystemFunction
			},
			'systemFunctionEditView button[action=save]' : {
				click : this.updateSystemFunction
			},
			'systemFunctionAddView #menulevel' : {
				select : this.reloadMenuStoreAdd
			},
			'systemFunctionEditView #menulevel' : {
				select : this.reloadMenuStoreEdit
			},
			'systemFunctionListView':{
                render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
//		var buttonArray = ['180501','180502','180503','180504'];
//		for(var i=0;i<buttonArray.length;i++){
//			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
//				Ext.getCmp(buttonArray[i]).hide();
//			}
//		}
//		return false;
	},
	createSystemFunction : function(button) {
		var view = Ext.widget('systemFunctionAddView');
		view.show();
		return false;
	},
	searchSystemFunction : function(button) {
		var store = this.getSystemFunctionListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
		                            var new_params = { 
		                           		 parentmenuname: encodeURI(Ext.getCmp('systemFunction_parentmenuname').getValue()),
		                           		 menuname: encodeURI(Ext.getCmp('systemFunction_menuname').getValue()),
		                           		 menulevel: encodeURI(Ext.getCmp('systemFunction_menulevel').getValue()),
		                           		 medetype: encodeURI(Ext.getCmp('systemFunction_medetype').getValue())
		                            };
		                            Ext.apply(store.proxy.extraParams, new_params);
		                        });
		                       store.loadPage(1); 
	   // store.reload();
	    return false;
	},
	saveSystemFunction : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getSystemFunctionListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/system/systemfunctionjson/addOrUpdateSystemFunction.action',
				method : 'post',
				waitMsg : '正在添加,请稍候...',
				success : function(form, action) {
					var returnType = action.result.result.returnType;
					if (returnType == 0) {
						win.close();
						store.reload();
						Ext.Msg.alert("提示","系统菜单添加成功!");
					}else if(returnType == 1) {
						Ext.Msg.alert('添加失败', '系统功能名已存在,请更换名称!',
							function(){
								 Ext.getCmp('menuname').focus();
							}
						);
					}else {
						Ext.Msg.alert('失败', "添加失败");
					}
				},
				failure : function(form, action) {
					Ext.Msg.alert('失败', "添加失败");
				}
			});
			return false;
	},
	updateSystemFunction : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getSystemFunctionListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/system/systemfunctionjson/addOrUpdateSystemFunction.action',
				method : 'post',
				waitMsg : '正在修改,请稍候...',
				success : function(form, action) {
					var returnType = action.result.result.returnType;
					if (returnType == 0) {
						win.close();
						store.reload();
						Ext.Msg.alert("提示","修改系统功能成功!");
					}else if(returnType == 1) {
						Ext.Msg.alert('修改失败', '系统功能名已存在,请更换名称!',
							function(){
								 Ext.getCmp('menuname').focus();
							}
						);
					}else {
						win.close();
						Ext.Msg.alert('提示', "修改系统功能信息失败!");
						store.reload();
					}
				},
				failure : function(form, action) {
					win.close();
					Ext.Msg.alert('提示', "修改系统功能信息失败!");
					store.reload();
				}
			});
			return false;
	},
	editSystemFunction : function(button) {
		var grid = button.up('grid');
		var store = this.getSystemFunctionListStoreStore();
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.widget('systemFunctionEditView');
		view.show();
		view.down('form').loadRecord(store.getById(records[0].get('menuid')));
		return false;
	},
	
	//激活用户
	activeSystemFunction : function activeSystemFunction(button){
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length>0) {
			Ext.MessageBox.confirm('提示','你确认要激活选中的系统功能？',
							function(btn) {
								if (btn == "yes") {
									var systemFunctions = grid.getSelectionModel().getSelection();
									var jsonData = "";
									for ( var i = 0, len = systemFunctions.length; i < len; i++) {
										var ss = systemFunctions[i].get("menuid");
										if (i == 0) {
											jsonData = jsonData + ss;
										} else {
											jsonData = jsonData + "," + ss;
										}
									}
									Ext.Ajax.request( {
												url : window.BIZCTX_PATH + '/system/systemfunctionjson/activeSystemFunction.action',
												method : 'POST',  
                    							timeout : 15000,
												params : "ids="+ jsonData,
												success : function(response) {
													Ext.Msg.alert('提示',"系统功能激活成功");
													store.reload();
												},
												failure : function() {
													Ext.Msg.alert('提示',"系统功能激活失败");
													store.reload();
												}
											});
								}
							});
		} else {
			Ext.Msg.alert('提示', '请选择要激活的系统功能!');
		}
		},
		
	//禁用用户
	inactiveSystemFunction : function inactiveSystemFunction(button){
			var grid = button.up('grid');
			var data = grid.getSelectionModel().getSelection();
			var store = grid.getStore();
			if (data.length>0) {
				Ext.MessageBox.confirm('提示','你确认要停用选中的系统功能？',
								function(btn) {
									if (btn == "yes") {
										var systemFunctions = grid.getSelectionModel().getSelection();
										var jsonData = "";
										for ( var i = 0, len = systemFunctions.length; i < len; i++) {
											var ss = systemFunctions[i].get("menuid");
											if (i == 0) {
												jsonData = jsonData + ss;
											} else {
												jsonData = jsonData + "," + ss;
											}
										}
										Ext.Ajax.request( {
													url : window.BIZCTX_PATH + '/system/systemfunctionjson/inactiveSystemFunction.action',
													method : 'POST',  
                        							timeout : 15000,
													params : "ids="+ jsonData,
													success : function(response) {
														Ext.Msg.alert('提示',"系统功能停用成功");
														store.reload();
													},
													failure : function() {
														Ext.Msg.alert('提示',"系统功能停用失败");
														store.reload();
													}
												});
									}
								});
			} else {
				Ext.Msg.alert('提示', '请选择要停用的系统功能!');
			}
	},
	reloadMenuStoreAdd : function() {
		var store = this.getMenuStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
                var new_params = { 
               		 menulevel: encodeURI(Ext.getCmp('menulevel').getValue())
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
	    store.load();
		return false;
	},
	reloadMenuStoreEdit : function() {
		var store = this.getMenuStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
                var new_params = { 
               		 parentmenuid : encodeURI(Ext.getCmp('parentmenuid').getValue()),
               		 menulevel: encodeURI(Ext.getCmp('menulevel').getValue())
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
	    store.load();
		return false;
	}
	
});

