Ext.define('WindowSetApp.controller.WindowSetCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['WindowSetListStore','BlocGroupListStore','RemindTreeStore'],//声明该控制层要用到的store
    models: ['WindowSetModel','BlocGroupModel'],//声明该控制层要用到的model
    views: ['WindowSetSearchView','WindowSetListView','WindowSetEditView','RemindTreeView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'windowSetSearchView',
            selector: 'windowSetSearchView'
        },{
            ref: 'windowSetListView',
            selector: 'windowSetListView'
        },{
            ref: 'windowSetEditView',
            selector: 'windowSetEditView'
        },{
            ref: 'remindTreeView',
            selector: 'remindTreeView'
        }
    ],
    init: function() {
		this.control({
			'windowSetListView button[action=edit]' : {
				click : this.editWindowSet
			},
			'windowSetSearchView button[action=search]' : {
				click : this.searchWindowSet
			},
			'windowSetEditView button[action=save]' : {
				click : this.updateWindowSet
			},
			'remindTreeView button[action=assignremind]' : {
				click : this.assignremind
			},
			'windowSetSearchView #query_usergroupid' : {
				change : this.loadSearchUserGroup
			}
		});
	},
	loadSearchUserGroup : function() {
		var store = this.getBlocGroupListStoreStore();
			store.clearFilter(true);
			store.on('beforeload', function (store, options) {
				var blocgroupname = "";
				if(Ext.getCmp('query_usergroupid')){
					blocgroupname = Ext.getCmp('query_blocgroupid').getRawValue();
				}
	            var new_params = { 
	            	blocgroupname: encodeURI(blocgroupname)
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.reload();
		return false;
	},
	searchWindowSet : function(button) {
		var store = this.getWindowSetListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
           		 loginname: encodeURI(Ext.getCmp('query_loginname').getValue()),
           		 blocid: encodeURI(Ext.getCmp('query_blocid').getValue()),
           		 blocgroupname: encodeURI(Ext.getCmp('query_blocgroupid').getRawValue())
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.loadPage(1); 
	    return false;
	},
	updateWindowSet : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getUserListStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/system/userjson/addOrUpdateUser.action',
			method : 'post',
			waitMsg : '正在修改,请稍候...',
			success : function(form, action) {
				var returnType = action.result.result.returnType;
				if (returnType == 0) {
					win.close();
					store.reload();
					Ext.Msg.alert("提示","修改用户信息成功!");
				}else if(returnType == 1) {
					Ext.Msg.alert('修改失败', '用户名已存在,请更换用户名!',
						function(){
							 Ext.getCmp('loginname').focus();
						}
					);
				}else {
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
	editWindowSet : function(button) {
		var grid = button.up('grid');
		var store = this.getWindowSetListStoreStore();
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		
		var store = this.getRemindTreeStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
                var new_params = { 
               		 reminduserid: records[0].get('userid')
                };
                Ext.apply(store.proxy.extraParams, new_params);
        });
	    store.reload();
	    
	    var view = Ext.widget('windowSetEditView');
		view.show();
		Ext.getCmp('reminduserid').setValue(records[0].get('userid'));
		return false;
	},
	assignremind : function(button) {
		var store = this.getWindowSetListStoreStore();
		var userid = Ext.getCmp('reminduserid').getValue();
		var view = Ext.getCmp('remindTreeView');
		var treeMenu = view.getChecked();
		var jsonData = "";
		for ( var i = 0;i < treeMenu.length; i++) {
			var ss = treeMenu[i].get("id");
			if (i == 0) {
				jsonData = jsonData + ss;
			} else {
				jsonData = jsonData + "," + ss;
			}
		}
		//判断是否选中
		if(jsonData==null||jsonData.length<0||jsonData==''){
			Ext.Msg.alert('提示',"请勾选报警类型!");
			return;
		}
		
		var mask=new Ext.LoadMask('remindTreeView',{msg:"正在打开选中提醒项,请稍后......"});
		mask.show();
		Ext.Ajax.request( {
				url : window.BIZCTX_PATH + '/system/userjson/assignRemind.action',
				method : 'POST',  
				timeout : 150000,
				params : {userid:userid,ids:jsonData},
				success : function(response) {
					mask.hide();
					store.reload();
					Ext.Msg.alert('提示',"打开选中提醒项成功,需重新登录才生效");
				},
				failure : function() {
					Ext.Msg.alert('提示',"打开选中提醒项失败");
				}
		});
		return false;
	}
	
});

