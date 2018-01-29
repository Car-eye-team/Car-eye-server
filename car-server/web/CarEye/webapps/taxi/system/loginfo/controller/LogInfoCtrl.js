Ext.define('LogInfoApp.controller.LogInfoCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['LogInfoListStore','TypeStore'],//声明该控制层要用到的store
    models: ['LogInfoModel'],//声明该控制层要用到的model
    views: ['LogInfoSearchView','LogInfoListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
       {
            ref: 'logInfoSearchView',
            selector: 'logInfoSearchView'
        },
        {
            ref: 'logInfoListView',
            selector: 'logInfoListView'
        }
    ],
    init: function() {
		this.control({
			'logInfoSearchView button[action=search]' : {
				click : this.searchLogInfo
			},
			'logInfoListView button[action=delete]' : {
				click : this.deleteLogInfo
			},
			'logInfoListView button[action=export]' : {
				click : this.exportInfo
			}
		});
	},
//	exportInfo : function (button){
//        	var	logInfo = encodeURI(Ext.getCmp('e_logInfo').getValue());
//        	location.href= window.BIZCTX_PATH + '/servlet/ExportTmsTwo?type=102&logInfo='+logInfo;
//	},
	exportInfo : function (button){
		
    	var	loginname = encodeURI(Ext.getCmp('s_loginname').getValue());
    	var	operattype = Ext.getCmp('s_operattype').getValue();
    	var	stime = encodeURI(Ext.util.Format.date(Ext.getCmp('s_stiem').getValue(), 'Y-m-d H:i:s'));
    	var	etime = encodeURI(Ext.util.Format.date(Ext.getCmp('s_etime').getValue(), 'Y-m-d H:i:s'));
    	location.href=window.BIZCTX_PATH + '/loginfojson/exportLogInfo.action?operattype='+
    						operattype+'&loginname='+loginname+'&stime='+stime
							+'&etime='+etime;
	},
	
	deleteLogInfo : function(button) {
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length>0) {
			Ext.MessageBox.confirm('提示','你确认要删除选中的操作日志？',
			function(btn) {
				if (btn == "yes") {
					var m = grid.getSelectionModel().getSelection();
					var jsonData = "";
					for ( var i = 0, len = m.length; i < len; i++) {
						var ss = m[i].get("logid");
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					Ext.Ajax.request( {
							url : window.BIZCTX_PATH + '/loginfojson/deleteLogInfo.action',
							method : 'POST',  
							timeout : 15000,
							params : "ids="+ jsonData,
							success : function(response) {
								Ext.Msg.alert('提示',"操作日志删除成功");
								store.reload();
							},
							failure : function() {
								Ext.Msg.alert('提示',"操作日志删除失败");
								store.reload();
							}
					});
				}
			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的登陆日志!');
		}
	return false;
	},
	
	searchLogInfo : function(button){
		var stime  = encodeURI(Ext.util.Format.date(Ext.getCmp('s_stiem').getValue(), 'Y-m-d H:i:s'));
        var etime  = encodeURI(Ext.util.Format.date(Ext.getCmp('s_etime').getValue(), 'Y-m-d H:i:s'));
        if(validTime(stime,etime)==-1){
			Ext.Msg.alert("提示","结束日期不能小于开始日期!");
			return;
		}
		var store = this.getLogInfoListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            		operattype: encodeURI(Ext.getCmp('s_operattype').getValue()),
	            		loginname: encodeURI(Ext.getCmp('s_loginname').getValue()),
	            		stime : Ext.util.Format.date(Ext.getCmp('s_stiem').getValue(),'Y-m-d H:i:s'),
	            		etime : Ext.util.Format.date(Ext.getCmp('s_etime').getValue(),'Y-m-d H:i:s')
	            		
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
        	store.loadPage(1);
	    return false;
	}
});

function validTime(startTime,endTime){
       var arr1 = startTime.split("-");
       var arr2 = endTime.split("-");
       var date1=new Date(parseInt(arr1[0]),parseInt(arr1[1])-1,parseInt(arr1[2]),0,0,0);
       var date2=new Date(parseInt(arr2[0]),parseInt(arr2[1])-1,parseInt(arr2[2]),0,0,0);
       if(date1.getTime()>date2.getTime()) {                               
               return -1;
         }else{
             return 1;
         }
         return -1;
}