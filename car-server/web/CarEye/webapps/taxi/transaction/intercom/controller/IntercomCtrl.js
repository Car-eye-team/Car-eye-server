

Ext.define('IntercomApp.controller.IntercomCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['GroupListStore','MemberListStore'],//声明该控制层要用到的store
    models: ['IntercomModel'],//声明该控制层要用到的model
    views: ['GroupListView','MemberListView'],//声明该控制层要用到的view
    
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
           {
            	ref: 'groupListView',
               selector: 'groupListView'
           },{
           	ref: 'memberListView',
            selector: 'memberListView'
           }
       ],
    
    init: function() {
		this.control({
			'groupListView button[action=search]' : {
				click : this.searchGroup
			},
			'groupListView button[action=delete]' : {
				click : this.deleteGroup
			},
			'memberListView button[action=search]' : {
				click : this.searchMember
			},
			'memberListView button[action=delete]' : {
				click : this.deleteMember
			}
		
		});
	},
	//搜索组
	searchGroup : function(button) {
		var store = this.getGroupListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
					var new_params = {
							name : encodeURI(Ext.getCmp('ig_name').getValue()),
							s_time : Ext.util.Format.date(Ext.getCmp('ig_stime').getValue(),'Y-m-d H:i:s'),
							e_time : Ext.util.Format.date(Ext.getCmp('ig_etime').getValue(),'Y-m-d H:i:s')
					};
					Ext.apply(store.proxy.extraParams, new_params);
				});
		store.loadPage(1);
		return false;
	},
	//删除组
	deleteGroup : function(button) {
		var grid = Ext.getCmp('groupList');
		var records = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (records.length > 0) {
			Ext.Msg.buttonText.yes='确定';
			Ext.Msg.buttonText.no="取消";
			Ext.Msg.confirm('提示', '你确认要删除选中的数据?', function(btn) {
				if (btn == 'yes') {
					var m = records;
					var jsonData = "";
					for (var i = 0; i < m.length; i++) {
						var ss = m[i].get('id');
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					Ext.Ajax.request({
								url : window.BIZCTX_PATH
										+ '/intercomjson/deleteGroup.action',
								method : 'POST',
								params : "ids=" + jsonData,
								success : function(response, opts) {
									var text = response.responseText;
									var su = Ext.JSON.decode(text).result.su;
									if (su == 0) {
										Ext.Msg.alert('提示', "删除成功！");
										store.reload();
									}else if(su == -1){
										Ext.Msg.alert('提示', "该组下存在组成员，请核实！");
										store.reload();
									}else{
										Ext.Msg.alert('提示', "删除失败！");
										store.reload();
									}
								},
								failure : function() {
									Ext.Msg.alert('提示', "服务器异常，稍后再试！");
									store.reload();
								}

							})

				}

			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的数据项!');
		};

	},
	//搜索组员
	searchMember : function(button) {
		var store = this.getMemberListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
					var new_params = {
							carnumber : encodeURI(Ext.getCmp('im_carnumber').getValue())
					};
					Ext.apply(store.proxy.extraParams, new_params);
				});
		store.loadPage(1);
		return false;
	},
	//删除组员
	deleteMember : function(button) {
		var grid = Ext.getCmp('memberList');
		var records = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (records.length > 0) {
			Ext.Msg.buttonText.yes='确定';
			Ext.Msg.buttonText.no="取消";
			Ext.Msg.confirm('提示', '你确认要删除选中的数据?', function(btn) {
				if (btn == 'yes') {
					var m = records;
					var jsonData = "";
					for (var i = 0; i < m.length; i++) {
						var ss = m[i].get('id');
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					Ext.Ajax.request({
								url : window.BIZCTX_PATH
										+ '/intercomjson/deleteMember.action',
								method : 'POST',
								params : "ids=" + jsonData,
								success : function(response, opts) {
									var text = response.responseText;
									var su = Ext.JSON.decode(text).result.su;
									if (su == 0) {
										Ext.Msg.alert('提示', "删除成功！");
										store.reload();
									}else{
										Ext.Msg.alert('提示', "删除失败！");
										store.reload();
									}
								},
								failure : function() {
									Ext.Msg.alert('提示', "服务器异常，稍后再试！");
									store.reload();
								}

							})

				}

			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的数据项!');
		};

	}
	
	
	
});

