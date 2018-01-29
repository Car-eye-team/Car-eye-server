Ext.define('KeyManageApp.controller.KeyManageCtrl', {
	extend : 'Ext.app.Controller',
	stores : ['KeyListStore', 'KeyTypeStore', 'KeyStatusStore'],// 声明该控制层要用到的store
	models : [],// 声明该控制层要用到的model
	views : ['KeySearchView', 'KeyListView'],// 声明该控制层要用到的view
	init : function() {
		this.control({
					'keyListView button[action=add]' : {
						click : this.addKey
					},
					'keyAddView button[action=save]' : {
						click : this.saveKey
					},
					'keyListView button[action=edit]' : {
						click : this.editKey
					},
					'keyEditView button[action=save]' : {
						click : this.saveKey
					},
					'keyListView button[action=delete]' : {
						click : this.deleteKey
					},
					'keyListView button[action=active]' : {
						click : this.activeKey
					},
					'keyListView button[action=inactive]' : {
						click : this.inactiveKey
					},
					'keySearchView button[action=search]' : {
						click : this.searchKey
					},
					'keyListView' : {
						render : this.buttonAccess
					}
				});
	},
	buttonAccess : function() {
		var buttonArray = ['12100201', '12100202', '12100203', '12100204',
				'12100205'];
		for (var i = 0; i < buttonArray.length; i++) {
			if (parent.menuidArray.indexOf(buttonArray[i]) == -1) {
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	searchKey : function(button) {
		var store = this.getKeyListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
					var new_params = {
						keyname : encodeURI(Ext.getCmp('search_key').getValue()),
						descs : encodeURI(Ext.getCmp('search_descs').getValue()),
						typeid : Ext.getCmp('search_typeid').getValue(),
						status : Ext.getCmp('search_status').getValue(),
						s_time : Ext.util.Format.date(Ext.getCmp('s_time')
										.getValue(), 'Y-m-d H:i:s'),
						e_time : Ext.util.Format.date(Ext.getCmp('e_time')
										.getValue(), 'Y-m-d H:i:s')
					};
					Ext.apply(store.proxy.extraParams, new_params);
				});
		store.loadPage(1);
		return false;
	},
	activeKey : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		var store = this.getKeyListStoreStore();
		if (records.length > 0) {
			Ext.MessageBox.confirm('提示', '你确认要激活选中的API密钥？', function(btn) {
						if (btn == "yes") {
							var m = grid.getSelectionModel().getSelection();
							var jsonData = "";
							for (var i = 0, len = m.length; i < len; i++) {
								var ss = m[i].get("id");
								if (i == 0) {
									jsonData = jsonData + ss;
								} else {
									jsonData = jsonData + "," + ss;
								}
							}
							Ext.Ajax.request({
										url : window.BIZCTX_PATH
												+ '/keyjson/activeKey.action',
										method : 'POST',
										params : "ids=" + jsonData,
										success : function(response, opts) {
											var text = response.responseText;
											var su = Ext.JSON.decode(text).result.su;
											if (su == 0) {
												Ext.Msg.alert('提示', "激活成功！");
												store.reload();
											} else if (su == -1) {
												Ext.Msg.alert('提示', "激活失败！");
												store.reload();
											}
										},
										failure : function() {
											Ext.Msg.alert('提示', "激活失败异常");
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
	inactiveKey : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		var store = this.getKeyListStoreStore();
		if (records.length > 0) {
			Ext.MessageBox.confirm('提示', '你确认要停用选中的API密钥？', function(btn) {
						if (btn == "yes") {
							var m = grid.getSelectionModel().getSelection();
							var jsonData = "";
							for (var i = 0, len = m.length; i < len; i++) {
								var ss = m[i].get("id");
								if (i == 0) {
									jsonData = jsonData + ss;
								} else {
									jsonData = jsonData + "," + ss;
								}
							}
							Ext.Ajax.request({
										url : window.BIZCTX_PATH
												+ '/keyjson/inactiveKey.action',
										method : 'POST',
										params : "ids=" + jsonData,
										success : function(response, opts) {
											var text = response.responseText;
											var su = Ext.JSON.decode(text).result.su;
											if (su == 0) {
												Ext.Msg.alert('提示', "停用成功！");
												store.reload();
											} else if (su == -1) {
												Ext.Msg.alert('提示', "停用失败！");
												store.reload();
											}
										},
										failure : function() {
											Ext.Msg.alert('提示', "停用失败异常");
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
	addKey : function(button) {
//		var view = Ext.widget('keyAddView');
		var view = Ext.create('KeyManageApp.view.KeyAddView');
		view.show();
		return false;
	},
	editKey : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if (records.length != 1) {
			Ext.Msg.alert('提示', '请选择一条记录进行编辑');
			return;
		}
//		var view = Ext.widget('keyEditView');
		var view = Ext.create('KeyManageApp.view.KeyEditView');
		var store = this.getKeyListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		view.show();
		return false;

	},

	deleteKey : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		var store = this.getKeyListStoreStore();
		if (records.length > 0) {
			Ext.MessageBox.confirm('提示', '你确认要删除选中的数据项？', function(btn) {
						if (btn == "yes") {
							var m = grid.getSelectionModel().getSelection();
							var jsonData = "";
							for (var i = 0, len = m.length; i < len; i++) {
								var ss = m[i].get("id");
								if (i == 0) {
									jsonData = jsonData + ss;
								} else {
									jsonData = jsonData + "," + ss;
								}
							}
							Ext.Ajax.request({
										url : window.BIZCTX_PATH
												+ '/keyjson/deleteKey.action',
										method : 'POST',
										params : "ids=" + jsonData,
										success : function(response, opts) {
											var text = response.responseText;
											var su = Ext.JSON.decode(text).result.su;
											if (su == 0) {
												Ext.Msg.alert('提示', "删除成功！");
												store.reload();
											} else if (su == -1) {
												Ext.Msg.alert('提示', "删除失败！");
												store.reload();
											}
										},
										failure : function() {
											Ext.Msg.alert('提示', "删除失败异常");
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
	saveKey : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getKeyListStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		form.getForm().submit({
					url : window.BIZCTX_PATH + '/keyjson/keySave.action',// 请求的服务器地址
					method : 'post',
					waitMsg : '正在保存,请稍候...',
					success : function(form, action) {
						var returnType = action.result.result.returnType;
						if (returnType == 0) {
							win.close();
							store.reload();
							Ext.Msg.alert("提示", "保存成功!");
						} else if (returnType == 1) {
							Ext.Msg.alert('提示', "密钥名已存在");

						} else {
							Ext.Msg.alert('失败', "保存失败");
						}
					},
					failure : function(form, action) {
						Ext.Msg.alert('提示', "保存失败");
						win.close();

						// Ext.MessageBox.close();
						store.reload();
					}

				})
		return false;
	}
})
