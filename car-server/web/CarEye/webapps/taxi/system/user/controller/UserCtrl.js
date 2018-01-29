Ext.define('userApp.controller.UserCtrl', {
	extend : 'Ext.app.Controller',
	stores : ['UserGroupListStore', 'UserListStore', 'UserGroupEditStore',
			'BlocInfoStore', 'BlocInfoTwoStore'],
	 models: ['UserModel','UserGroupModel'],//声明该控制层要用到的model
	views : ['UserSearchView', 'UserListView', 'UserAddView', 'UserEditView'],// 声明该控制层要用到的view
	refs : [// 相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
	{
				ref : 'userSearchView',
				selector : 'userSearchView'
			}, {
				ref : 'spaceView',
				selector : 'spaceView'
			}, {
				ref : 'userListView',
				selector : 'userListView'
			}, {
				ref : 'userAddView',
				selector : 'userAddView'
			}, {
				ref : 'userEditView',
				selector : 'userEditView'
			}, {
				ref : 'userContactView',
				selector : 'userContactView'
			}],
	init : function() {
		this.control({
					'userListView button[action=add]' : {
						click : this.createUser
					},
					'userAddView button[action=save]' : {
						click : this.saveUser
					},
					'userListView button[action=active]' : {
						click : this.activeUser
					},
					'userListView button[action=inactive]' : {
						click : this.inactiveUser
					},
					'userListView button[action=delete]' : {
						click : this.deleteUser
					},
					'userListView button[action=edit]' : {
						click : this.editUser
					},
					'userContactView button[action=save]' : {
						click : this.saveUserContact
					},
					'userSearchView button[action=search]' : {
						click : this.searchUser
					},
					'userEditView button[action=save]' : {
						click : this.updateUser
					},
					'userEditView #blocgroupid' : {
						select : this.loadBloc
					},
					'userAddView #blocgroupid' : {
						change : this.loadBlocGroup
					},
					'userSearchView #query_blocgroupid' : {
						change : this.loadSearchBlocGroup
					},
					'userCarTreeView button[action=assigncar]' : {
						click : this.assigncar
					},
					'ptcarListView button[action=add]' : {
						click : this.assigntpcar
					},
					'ptcarListView button[action=delete]' : {
						click : this.deletePtcar
					},
					'nbcarListView button[action=add]' : {
						click : this.assignnbcar
					},
					'nbcarListView button[action=delete]' : {
						click : this.deleteNbcar
					},
					'userListView' : {
						render : this.buttonAccess
					}
				});
	},
	buttonAccess : function() {
		var buttonArray = ['110301', '110302', '110303', '110304', '110305'];
		for (var i = 0; i < buttonArray.length; i++) {
			if (parent.menuidArray.indexOf(buttonArray[i]) == -1) {
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	saveUserContact : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getUserListStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		form.getForm().submit({
			url : window.BIZCTX_PATH
					+ '/system/userjson/saveUserContact.action',
			method : 'post',
			waitMsg : '请稍候...',
			success : function(form, action) {
				var resp = action.result.result;
				var su = resp.su;
				if (su > 0) {
					Ext.Msg.alert("提示", "保存成功!");
					store.reload();
					win.close();
				} else {
					Ext.Msg.alert("提示", "保存失败!");
				}
			},
			failure : function(form, action) {
				Ext.Msg.alert('提示', "保存失败!");
			}
		});
		return false;
	},
	loadBloc : function() {
		var blocgroupid = Ext.getCmp('blocgroupid').getValue();
		if (blocgroupid) {
			Ext.Ajax.request({
						url : window.BIZCTX_PATH
								+ '/userGroupJson/quertDeptByUserGroupId.action',
						method : 'POST',
						params : "usergroupid=" + usergroupid,
						success : function(response) {
							var text = response.responseText;
							var deptid = Ext.JSON.decode(text).result.deptid;
							Ext.getCmp('blocid').setValue(deptid);
						},
						failure : function() {
						}
					});
		}
		return false;
	},
	loadBlocGroup : function() {
		var store = this.getUserGroupListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
			var blocgroupname = "";
			if (Ext.getCmp('blocgroupid') && !Ext.getCmp('userEditView')) {
				blocgroupname = Ext.getCmp('blocgroupid').getRawValue();
			}
			var new_params = {
				blocgroupname : encodeURI(blocgroupname)
			};
			Ext.apply(store.proxy.extraParams, new_params);
			if (blocgroupname != "") {
				Ext.Ajax.request({
							url : window.BIZCTX_PATH
									+ '/userGroupJson/quertDeptByUsergroupname.action',
							method : 'POST',
							params : "blocgroupname="
									+ encodeURI(blocgroupname),
							success : function(response) {
								var text = response.responseText;
								var blocid = Ext.JSON.decode(text).result.blocid;
								if (blocid != null) {
									Ext.getCmp('blocid').setValue(blocid);
								}
							},
							failure : function() {
							}
						});
			}

		});
		store.reload();
		return false;
	},
	loadSearchBlocGroup : function() {
		var store = this.getUserGroupListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
					var blocgroupname = "";
					if (Ext.getCmp('query_blocgroupid')) {
						blocgroupname = Ext.getCmp('query_blocgroupid')
								.getRawValue();
					}
					var new_params = {
						blocgroupname : encodeURI(blocgroupname)
					};
					Ext.apply(store.proxy.extraParams, new_params);
				});
		store.reload();
		return false;
	},
	createUser : function(button) {

		var view = Ext.widget('userAddView');
		view.show();

		return false;
	},
	searchUser : function(button) {
		var store = this.getUserListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
			var new_params = {
				loginname : encodeURI(Ext.getCmp('query_loginname').getValue()),
				username : encodeURI(Ext.getCmp('query_username').getValue()),
				blocid : encodeURI(Ext.getCmp('query_blocid').getValue()),
				blocgroupname : encodeURI(Ext.getCmp('query_blocgroupid')
						.getRawValue())
			};
			Ext.apply(store.proxy.extraParams, new_params);
		});
		store.loadPage(1);
		// store.reload();
		return false;
	},
	saveUser : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getUserListStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		var blocgroupid = Ext.getCmp('blocgroupid').getValue();
		if (isNaN(blocgroupid)) {
			Ext.Msg.alert("提示", "请选择企业组");
			return;
		}
		form.getForm().submit({
			url : window.BIZCTX_PATH
					+ '/system/userjson/addOrUpdateUser.action',
			method : 'post',
			waitMsg : '正在添加,请稍候...',
			success : function(form, action) {
				var returnType = action.result.result.returnType;
				if (returnType == 0) {
					win.close();
					store.reload();
					Ext.Msg.alert("提示", "用户添加成功!");
				} else if (returnType == 1) {
					Ext.Msg.alert('添加失败', '用户名已存在,请更换用户名!', function() {
								Ext.getCmp('loginname').focus();
							});
				} else {
					Ext.Msg.alert('失败', "添加失败");
				}
			},
			failure : function(form, action) {
				Ext.Msg.alert('失败', "添加失败");
			}
		});
		return false;
	},
	updateUser : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getUserListStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		var blocgroupid = Ext.getCmp('blocgroupid').getValue();
		if (isNaN(blocgroupid)) {
			Ext.Msg.alert("提示", "请选择企业组");
			return;
		}
		form.getForm().submit({
			url : window.BIZCTX_PATH
					+ '/system/userjson/addOrUpdateUser.action',
			method : 'post',
			waitMsg : '正在修改,请稍候...',
			success : function(form, action) {
				var returnType = action.result.result.returnType;
				if (returnType == 0) {
					win.close();
					store.reload();
					Ext.Msg.alert("提示", "修改用户信息成功!");
				} else if (returnType == 1) {
					Ext.Msg.alert('修改失败', '用户名已存在,请更换用户名!', function() {
								Ext.getCmp('loginname').focus();
							});
				} else {
					win.close();
					Ext.Msg.alert('提示', "修改用户信息失败!");
					store.reload();
				}
			},
			failure : function(form, action) {
				win.close();
				Ext.Msg.alert('提示', "修改用户信息失败!");
				store.reload();
			}
		});
		return false;
	},
	deleteUser : function(button) {
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length > 0) {
			Ext.MessageBox.confirm('提示', '你确认要删除选中的用户？', function(btn) {
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
												+ '/system/userjson/deleteUser.action',
										method : 'POST',
										timeout : 15000,
										params : "ids=" + jsonData,
										success : function(response) {
											Ext.Msg.alert('提示', "用户删除成功");
											store.reload();
										},
										failure : function() {
											Ext.Msg.alert('提示', "用户删除失败");
											store.reload();
										}
									});
						}
					});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的用户!');
		}
		return false;
	},

	editUser : function(button) {
		var grid = button.up('grid');
		var store = this.getUserListStoreStore();
		var records = grid.getSelectionModel().getSelection();
		if (records.length != 1) {
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}

		var view = Ext.widget('userEditView');
		view.show();

		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	},
	assigncontact : function(button) {
		var grid = button.up('grid');
		var store = this.getUserListStoreStore();
		var records = grid.getSelectionModel().getSelection();
		if (records.length != 1) {
			Ext.Msg.alert('提示', '请选择一条记录');
			return;
		}

		var view = Ext.widget('userContactView');
		view.show();
		var data = store.getById(records[0].get('userid'));
		view.down('form').loadRecord(data);

		return false;
	},

	// 激活用户
	activeUser : function activeUser(button) {
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length > 0) {
			Ext.MessageBox.confirm('提示', '你确认要激活选中的用户？', function(btn) {
						if (btn == "yes") {
							var users = grid.getSelectionModel().getSelection();
							var jsonData = "";
							for (var i = 0, len = users.length; i < len; i++) {
								var ss = users[i].get("id");
								if (i == 0) {
									jsonData = jsonData + ss;
								} else {
									jsonData = jsonData + "," + ss;
								}
							}
							Ext.Ajax.request({
										url : window.BIZCTX_PATH
												+ '/system/userjson/activeUser.action',
										method : 'POST',
										timeout : 15000,
										params : "ids=" + jsonData,
										success : function(response) {
											store.reload();
											Ext.Msg.alert('提示', "用户激活成功");
										},
										failure : function() {
											store.reload();
											Ext.Msg.alert('提示', "用户激活失败");
										}
									});
						}
					});
		} else {
			Ext.Msg.alert('提示', '请选择要激活的用户!');
		}
	},

	// 禁用用户
	inactiveUser : function inactiveUser(button) {
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length > 0) {
			Ext.MessageBox.confirm('提示', '你确认要停用选中的用户？', function(btn) {
				if (btn == "yes") {
					var users = grid.getSelectionModel().getSelection();
					var jsonData = "";
					for (var i = 0, len = users.length; i < len; i++) {
						var ss = users[i].get("id");
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					Ext.Ajax.request({
								url : window.BIZCTX_PATH
										+ '/system/userjson/inactiveUser.action',
								method : 'POST',
								timeout : 15000,
								params : "ids=" + jsonData,
								success : function(response) {
									store.reload();
									Ext.Msg.alert('提示', "用户停用成功");
								},
								failure : function() {
									store.reload();
									Ext.Msg.alert('提示', "用户停用失败");
								}
							});
				}
			});
		} else {
			Ext.Msg.alert('提示', '请选择要停用的用户!');
		}
		return false;
	},
	assigncar : function(button) {
		var userid = Ext.getCmp('assignuserid').getValue();
		var view = Ext.getCmp('userCarTreeView');
		var treeMenu = view.getChecked();
		var jsonData = "";
		for (var i = 0; i < treeMenu.length; i++) {
			var ss = treeMenu[i].get("id");
			if (i == 0) {
				jsonData = jsonData + ss;
			} else {
				jsonData = jsonData + "," + ss;
			}
		}
		var mask = new Ext.LoadMask('userCarTreeView', {
					msg : "正在给用户分配车辆,请稍后......"
				});
		mask.show();
		Ext.Ajax.request({
					url : window.BIZCTX_PATH
							+ '/system/userjson/assignUserCar.action',
					method : 'POST',
					timeout : 180000,
					params : {
						userid : userid,
						ids : jsonData
					},
					success : function(response) {
						mask.hide();
						Ext.Msg.alert('提示', "车辆分配成功");
					},
					failure : function() {
						Ext.Msg.alert('提示', "车辆分配失败");
					}
				});
		return false;
	},

	assigntpcar : function(button) {
		var store = this.getPtcarListStoreStore();
		var view = Ext.getCmp('carWinTreeView');
		var treeMenu = view.getChecked();
		var jsonData = "";
		if (treeMenu.length == 0) {
			Ext.Msg.alert('提示', "请选择需要平台分配的车辆");
			return;
		}

		var userid = Ext.getCmp('pt_userid').getValue();

		for (var i = 0; i < treeMenu.length; i++) {
			var ss = treeMenu[i].get("id");
			if (i == 0) {
				jsonData = jsonData + ss;
			} else {
				jsonData = jsonData + "," + ss;
			}
		}

		Ext.Ajax.request({
					url : window.BIZCTX_PATH
							+ '/system/userjson/assigntpcar.action',
					method : 'POST',
					timeout : 150000,
					params : {
						userid : userid,
						ids : jsonData
					},
					success : function(response) {
						Ext.Msg.alert('提示', "平台车辆分配成功");
						store.reload();
					},
					failure : function() {
						Ext.Msg.alert('提示', "平台车辆分配失败");
					}
				});
		return false;
	},
	deletePtcar : function(button) {
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length > 0) {
			Ext.MessageBox.confirm('提示', '你确认要删除选中的车辆？', function(btn) {
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
										+ '/system/userjson/deletePtcar.action',
								method : 'POST',
								timeout : 150000,
								params : "ids=" + jsonData,
								success : function(response) {
									Ext.Msg.alert('提示', "平台分配车辆删除成功");
									store.reload();
								},
								failure : function() {
									Ext.Msg.alert('提示', "平台分配车辆删除失败");
									store.reload();
								}
							});
				}
			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的车辆!');
		}
		return false;
	},
	assignnbcar : function(button) {
		var store = this.getNbcarListStoreStore();
		var view = Ext.getCmp('nbcarWinTreeView');
		var treeMenu = view.getChecked();
		var jsonData = "";
		if (treeMenu.length == 0) {
			Ext.Msg.alert('提示', "请选择需要内部分配的车辆");
			return;
		}

		var userid = Ext.getCmp('nb_userid').getValue();

		for (var i = 0; i < treeMenu.length; i++) {
			var ss = treeMenu[i].get("id");
			if (i == 0) {
				jsonData = jsonData + ss;
			} else {
				jsonData = jsonData + "," + ss;
			}
		}

		Ext.Ajax.request({
					url : window.BIZCTX_PATH
							+ '/system/userjson/assignNbcar.action',
					method : 'POST',
					timeout : 150000,
					params : {
						userid : userid,
						ids : jsonData
					},
					success : function(response) {
						Ext.Msg.alert('提示', "内部车辆分配成功");
						store.reload();
					},
					failure : function() {
						Ext.Msg.alert('提示', "内部车辆分配失败");
					}
				});
		return false;
	},
	deleteNbcar : function(button) {
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length > 0) {
			Ext.MessageBox.confirm('提示', '你确认要删除选中的车辆？', function(btn) {
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
										+ '/system/userjson/deleteNbcar.action',
								method : 'POST',
								timeout : 150000,
								params : "ids=" + jsonData,
								success : function(response) {
									Ext.Msg.alert('提示', "内部分配车辆删除成功");
									store.reload();
								},
								failure : function() {
									Ext.Msg.alert('提示', "内部分配车辆删除失败");
									store.reload();
								}
							});
				}
			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的车辆!');
		}
		return false;
	}

});
