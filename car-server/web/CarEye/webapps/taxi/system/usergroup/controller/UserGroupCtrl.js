Ext.define('userGroupApp.controller.UserGroupCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['UserGroupListStore','UGUserListStore','BlocInfoStore','BlocInfoTwoStore'],//声明该控制层要用到的store
    models: ['UserGroupModel','UserModel'],//声明该控制层要用到的model
    views: ['UserGroupSearchView','SpaceView','UserGroupListView','UserGroupAddView','UserGroupEditView','UGUserListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'userGroupSearchView',
            selector: 'userGroupSearchView'
        },{
            ref: 'spaceView',
            selector: 'spaceView'
        },{
            ref: 'userGroupListView',
            selector: 'userGroupListView'
        },{
            ref: 'userGroupAddView',
            selector: 'userGroupAddView'
        },{
            ref: 'userGroupEditView',
            selector: 'userGroupEditView'
        },{
            ref: 'uGUserListView',
            selector: 'uGUserListView'
        }
    ],
    init: function() {
    	
		this.control({
			'userGroupListView button[action=add]' : {
				click : this.createUserGroup
			},
			'userGroupAddView button[action=save]' : {
				click : this.saveUserGroup
			},
			'userGroupListView button[action=delete]' : {
				click : this.deleteUserGroup
			},
			'userGroupListView button[action=edit]' : {
				click : this.editUserGroup
			},
			'userGroupListView button[action=query]' : {
				click : this.queryUserGroup
			},
			'userGroupSearchView button[action=search]' : {
				click : this.searchUserGroup
			},
			'userGroupEditView button[action=save]' : {
				click : this.updateUserGroup
			},
			'userGroupListView':{
                render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['110201','110202','110203','110204'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	createUserGroup : function(button) {
		var record = Ext.create('userGroupApp.model.UserGroupModel');
		var view = Ext.widget('userGroupAddView');
		view.show();
		view.down('form').loadRecord(record);
		return false;
	},
	searchUserGroup : function(button) {
		var store = this.getUserGroupListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	blocgroupname: encodeURI(Ext.getCmp('ug_blocgroupname').getValue()),
	            	blocid: Ext.getCmp('ug_blocid').getValue()
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	saveUserGroup : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getUserGroupListStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		Ext.MessageBox.wait('请稍候...', '提示');
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/userGroupJson/addUserGroupInfo.action',
			method : 'post',
			success : function(form, action) {
					Ext.MessageBox.updateProgress(1);
				    Ext.MessageBox.close();
					var resp = action.result.result;
					var su = resp.su;
					if (su == -1) {
						Ext.Msg.alert("提示","企业组已存在，请更换名称!");
					}else if (su == -2) {
						win.close();
						Ext.Msg.alert("提示","系统异常,请与管理员联系!");
					}else {
						win.close();
						Ext.Msg.alert("提示","创建企业组信息成功!");
						store.reload();
					}
		    },
			failure : function(form, action) {
				Ext.MessageBox.updateProgress(1);
				Ext.MessageBox.close();
				win.close();
				Ext.Msg.alert('提示', "创建企业组信息失败!");
				store.reload();
			}
		});
		return false;
	},
	updateUserGroup : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getUserGroupListStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		Ext.MessageBox.wait('请稍候...', '提示');
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/userGroupJson/addUserGroupInfo.action',
			method : 'post',
			success : function(form, action) {
					Ext.MessageBox.updateProgress(1);
				    Ext.MessageBox.close();
					var resp = action.result.result;
					var su = resp.su;
					if (su == -1) {
						Ext.Msg.alert("提示","企业组已存在，请更换名称!");
					}else if (su == -2) {
						win.close();
						Ext.Msg.alert("提示","系统异常,请与管理员联系!");
					}else {
						win.close();
						store.reload();
						Ext.Msg.alert("提示","修改企业组信息成功!");
					}
		    },
			failure : function(form, action) {
				Ext.MessageBox.updateProgress(1);
				Ext.MessageBox.close();
				win.close();
				Ext.Msg.alert('提示', "修改企业组信息失败!");
				store.reload();
			}
		});
		return false;
	},
	deleteUserGroup : function(button) {
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
								url : window.BIZCTX_PATH + '/userGroupJson/deleteUserGroup.action',
								method : 'POST',  
								params : "ids="+ jsonData,
								success : function(response) {
									var text = response.responseText;
									var result =  Ext.JSON.decode(text).result;
									var su = result.su;
									if(su == -1) {
										Ext.Msg.alert("提示","该企业组下面有用户不能删除!");
									}else if (su == 1) {
										Ext.Msg.alert("提示","删除企业组成功!");
										store.reload();
									}else {
										Ext.Msg.alert("提示","系统异常,请与管理员联系!");
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
	editUserGroup : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.widget('userGroupEditView');
		view.show();
		var store = this.getUserGroupListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	},
	queryUserGroup : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条企业组信息');
			return;
		}
		var view = Ext.widget('uGUserListView');
		view.show();
		var m = grid.getSelectionModel().getSelection();
		var store = this.getUGUserListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { usergroupid: m[0].get("id")};
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	    store.loadPage(1); 
		return false;
	}
});

