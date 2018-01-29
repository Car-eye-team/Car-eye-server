
Ext.define('LoginLogApp.controller.LoginLogCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['LoginLogListStore'],//声明该控制层要用到的store
    models: ['LoginLogModel'],//声明该控制层要用到的model
    views: ['LoginLogListView','SpaceView','LoginLogSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'loginLogSearchView',
            selector: 'loginLogSearchView'
        },{
            ref: 'loginLogListView',
            selector: 'loginLogListView'
        }
    ],
    init: function() {
		this.control({
			'loginLogListView button[action=export]' : {
				click : this.exportLoginLog
			},
			'loginLogListView button[action=delete]' : {
				click : this.deleteLoginLog
			},
			'loginLogSearchView button[action=search]' : {
				click : this.searchlLoginLog
			}
		});
	},
	searchlLoginLog : function(button) {
		var store = this.getLoginLogListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	loginname: encodeURI(Ext.getCmp('ll_loginname').getValue()),
	            	blocname: encodeURI(Ext.getCmp('ll_deptname').getValue()),
	            	stime: encodeURI(Ext.util.Format.date(Ext.getCmp('ll_stime').getValue(), 'Y-m-d H:i:s')),
		            etime: encodeURI(Ext.util.Format.date(Ext.getCmp('ll_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	       // store.reload();
	    return false;
	},
	
	
	exportLoginLog : function (button){
		    var userid = admin.id;
        	var	loginname = encodeURI(Ext.getCmp('ll_loginname').getValue());
        	var	blocname = encodeURI(Ext.getCmp('ll_deptname').getValue());
        	var	stime = encodeURI(Ext.util.Format.date(Ext.getCmp('ll_stime').getValue(), 'Y-m-d H:i:s'));
        	var	etime = encodeURI(Ext.util.Format.date(Ext.getCmp('ll_etime').getValue(), 'Y-m-d H:i:s'));
        	location.href=window.BIZCTX_PATH + '/system/userjson/loginLogExport.action?userid='+
								userid+'&loginname='+loginname+'&blocname='+blocname+'&stime='+stime
								+'&etime='+etime;
	},
	
	deleteLoginLog : function(button) {
			var grid = button.up('grid');
			var data = grid.getSelectionModel().getSelection();
			var store = grid.getStore();
			if (data.length>0) {
				Ext.MessageBox.confirm('提示','你确认要删除选中的登陆日志？',
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
								url : window.BIZCTX_PATH + '/system/userjson/deleteLoginLog.action',
								method : 'POST',  
    							timeout : 15000,
								params : "ids="+ jsonData,
								success : function(response) {
									Ext.Msg.alert('提示',"登陆日志删除成功");
									store.reload();
								},
								failure : function() {
									Ext.Msg.alert('提示',"登陆日志删除失败");
									store.reload();
								}
						});
					}
				});
			} else {
				Ext.Msg.alert('提示', '请选择要删除的登陆日志!');
			}
		return false;
	}
	
});

