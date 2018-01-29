Ext.define('PageSetApp.controller.PageSetCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['PageSetListStore','UserGroupListStore','PageStatusStore','PageStatusTwoStore'],//声明该控制层要用到的store
    models: ['PageSetModel','UserGroupModel'],//声明该控制层要用到的model
    views: ['PageSetSearchView','PageSetListView','PageSetEditView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'pageSetSearchView',
            selector: 'pageSetSearchView'
        },{
            ref: 'pageSetListView',
            selector: 'pageSetListView'
        },{
            ref: 'pageSetEditView',
            selector: 'pageSetEditView'
        }
    ],
    init: function() {
		this.control({
			'pageSetListView button[action=edit]' : {
				click : this.editPageSet
			},
			'pageSetSearchView button[action=search]' : {
				click : this.searchPageSet
			},
			'pageSetEditView button[action=save]' : {
				click : this.savePageSet
			},
			'pageSetSearchView #query_usergroupid' : {
				change : this.loadSearchUserGroup
			}
		});
	},
	loadSearchUserGroup : function() {
		var store = this.getUserGroupListStoreStore();
			store.clearFilter(true);
			store.on('beforeload', function (store, options) {
				var usergroupname = "";
				if(Ext.getCmp('query_blocgroupid')){
					usergroupname = Ext.getCmp('query_blocgroupid').getRawValue();
				}
	            var new_params = { 
	            	usergroupname: encodeURI(usergroupname)
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.reload();
		return false;
	},
	searchPageSet : function(button) {
		var store = this.getPageSetListStoreStore();
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
	savePageSet : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getPageSetListStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/setjson/savePageSet.action',
			method : 'post',
			waitMsg : '正在修改,请稍候...',
			success : function(form, action) {
				win.close();
				store.reload();
				Ext.Msg.alert("提示","界面展开关闭设置成功!");
			},
			failure : function(form, action) {
				win.close();
				Ext.Msg.alert('提示', "界面展开关闭设置失败!");
				store.reload();
			}
		});
		return false;
	},
	editPageSet : function(button) {
		var grid = button.up('grid');
		var store = this.getPageSetListStoreStore();
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条信息进行设置');
			return;
		}
	    
	    var view = Ext.widget('pageSetEditView');
		view.show();
		var data = store.getById(records[0].get('userid'));
		view.down('form').loadRecord(data);
		return false;
	}
	
});

