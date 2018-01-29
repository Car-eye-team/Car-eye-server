Ext.define('RemoteControlRecordApp.controller.RemoteControlRecordCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['RemoteControlRecordListStore','CommandTypeStore','SetStatusStore'],//声明该控制层要用到的store
    models: ['RemoteControlRecordModel'],//声明该控制层要用到的model
    views: ['RemoteControlRecordListView',
            'RemoteControlRecordSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'remoteControlRecordListView',
            selector: 'remoteControlRecordListView'
        },
        {
            ref: 'remoteControlRecordSearchView',
            selector: 'remoteControlRecordSearchView'
        }
    ],
    init: function() {
    	
		this.control({
			'remoteControlRecordSearchView button[action=search]' : {
				click : this.searchRecord
			},
			'remoteControlRecordListView button[action=reset]' : {
				click : this.resetForm
			},
			'remoteControlRecordListView button[action=export]' : {
				click : this.exportRecord
			}
		});
	},
	searchRecord:function(){
			
			var stime=Ext.util.Format.date(Ext.getCmp('rcr_stime').getValue(), 'Y-m-d H:i:s');
			var etime=Ext.util.Format.date(Ext.getCmp('rcr_etime').getValue(), 'Y-m-d H:i:s');
	        var carnumber=Ext.getCmp('rcr_carnumber').getValue();
	    	var commandtype=Ext.getCmp('rcr_commandtype').getValue();
	    	var setstatus=Ext.getCmp('rcr_setstatus').getValue();
			var store = this.getRemoteControlRecordListStoreStore();
			store.clearFilter(true);
			store.on('beforeload', function (store, options) {
		            var new_params = { 
		            	carnumber: encodeURI(carnumber),
	        			stime : encodeURI(stime),
	        			etime : encodeURI(etime),
	        			commandtype  : encodeURI(commandtype),
	        			setstatus  : setstatus
		            };
		            Ext.apply(store.proxy.extraParams, new_params);
		        });
		        store.loadPage(1); 
		    return false;
		
	},
	
	resetForm : function(){
		Ext.getCmp('rcr_carnumber').setValue("");
    	Ext.getCmp('rcr_commandtype').setValue("");
    	Ext.getCmp('rcr_setstatus').setValue("");
    	Ext.getCmp('rcr_stime').setValue("");
    	Ext.getCmp('rcr_etime').setValue("");
	},
	
	exportRecord: function(){
		var stime=Ext.util.Format.date(Ext.getCmp('rcr_stime').getValue(), 'Y-m-d H:i:s');
		var etime=Ext.util.Format.date(Ext.getCmp('rcr_etime').getValue(), 'Y-m-d H:i:s');
		var carnumber=encodeURI(Ext.getCmp('rcr_carnumber').getValue());
    	var commandtype=encodeURI(Ext.getCmp('rcr_commandtype').getValue());
    	var setstatus=Ext.getCmp('rcr_setstatus').getValue();
    	
        	location.href= window.BIZCTX_PATH + '/remotecontrolrecord/remotecontrolrecordjson/ExportRemoteControlRecord.action?carnumber='
        	+carnumber+'&stime='+stime+'&etime='+etime+'&commandtype='+commandtype+'&setstatus='+setstatus;
	
	}
});

