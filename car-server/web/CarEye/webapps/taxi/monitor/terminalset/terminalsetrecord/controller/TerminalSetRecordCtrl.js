Ext.define('TerminalSetRecordApp.controller.TerminalSetRecordCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['TerminalSetRecordListStore','CommandTypeStore','SetStatusStore'],//声明该控制层要用到的store
    models: ['TerminalSetRecordModel'],//声明该控制层要用到的model
    views: ['TerminalSetRecordListView',
            'TerminalSetRecordSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'terminalSetRecordListView',
            selector: 'terminalSetRecordListView'
        },
        {
            ref: 'terminalSetRecordSearchView',
            selector: 'terminalSetRecordSearchView'
        }
    ],
    init: function() {
    	
		this.control({
			'terminalSetRecordSearchView button[action=search]' : {
				click : this.searchRecord
			},
			'terminalSetRecordListView button[action=reset]' : {
				click : this.resetForm
			},
			'terminalSetRecordListView button[action=export]' : {
				click : this.exportRecord
			}
		});
	},
	searchRecord:function(){
			
		var stime = Ext.util.Format.date(Ext.getCmp('rcr_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('rcr_etime').getValue(), 'Y-m-d H:i:s');
        if(etime != null && etime.length > 0){
            var beginTimes = stime.substring(0,10).split('-');
            var endTimes = etime.substring(0,10).split('-');
    
            var stimearray = stime.substring(10,19).split(':');
            var etimearray = etime.substring(10,19).split(':');
            var stimedate = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
            var etimedate = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
            
            if(etimedate < stimedate){
                Ext.Msg.alert("提示","开始时间必须小于结束时间");
                return;
            }
        } 
		
		var stime=Ext.util.Format.date(Ext.getCmp('rcr_stime').getValue(), 'Y-m-d H:i:s');
		var etime=Ext.util.Format.date(Ext.getCmp('rcr_etime').getValue(), 'Y-m-d H:i:s');
        var carnumber=Ext.getCmp('rcr_carnumber').getValue();
    	var commandtype=Ext.getCmp('rcr_commandtype').getValue();
    	var setstatus=Ext.getCmp('rcr_setstatus').getValue();
		var store = this.getTerminalSetRecordListStoreStore();
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
		var carnumber=Ext.getCmp('rcr_carnumber').getValue();
    	var commandtype=Ext.getCmp('rcr_commandtype').getValue();
    	var setstatus=Ext.getCmp('rcr_setstatus').getValue();
        	location.href= window.BIZCTX_PATH + '/remotecontrolrecord/remotecontrolrecordjson/ExportTerminalSetRecord.action?carnumber='
        	+carnumber+'&stime='+stime+'&etime='+etime+'&commandtype='+commandtype+'&setstatus='+setstatus;
	
	}
});

