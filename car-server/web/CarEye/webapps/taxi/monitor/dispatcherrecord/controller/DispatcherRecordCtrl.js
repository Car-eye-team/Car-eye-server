Ext.define('DispatcherRecordApp.controller.DispatcherRecordCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['DispatcherRecordListStore','CarInfoListStore','StatusStore','TypeStore'],//声明该控制层要用到的store
    models: ['DispatcherRecordModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['DispatcherRecordListView',
		    'DispatcherRecordSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'dispatcherRecordEditView',
            selector: 'dispatcherRecordEditView'
        },{
            ref: 'dispatcherRecordListView',
            selector: 'dispatcherRecordListView'
        }
    ],
    init: function() {
    	
		this.control(
		    {'dispatcherRecordSearchView button[action=search]' : {
				click : this.searchDispatcherRecord
			},
			'dispatcherRecordListView button[action=export]' : {
				click : this.exportInfo
			},
			'dispatcherRecordListView':{
                render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['14040201'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	searchDispatcherRecord : function(button) {
		var store = this.getDispatcherRecordListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	blocid:encodeURI(Ext.getCmp('dr_blocid').getValue()),
	            	carnumber:encodeURI(Ext.getCmp('dr_carnumber').getValue()),
	            	status:encodeURI(Ext.getCmp('dr_status').getValue()),
	            	stime:encodeURI(Ext.util.Format.date(Ext.getCmp('dr_stime').getValue(), 'Y-m-d H:i:s')),
	            	etime:encodeURI(Ext.util.Format.date(Ext.getCmp('dr_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	exportInfo : function (button){
		  var blocid=encodeURI(Ext.getCmp('dr_blocid').getValue());
		  var carnumber=encodeURI(Ext.getCmp('dr_carnumber').getValue());
	      var status=encodeURI(Ext.getCmp('dr_status').getValue());
	      var stime=encodeURI(Ext.util.Format.date(Ext.getCmp('dr_stime').getValue(), 'Y-m-d H:i:s'));
	      var etime=encodeURI(Ext.util.Format.date(Ext.getCmp('dr_etime').getValue(), 'Y-m-d H:i:s'));
		
	      var strParams="carnumber="+carnumber+"&status="+status+"&stime="+stime+"&etime="+etime
		   +"&blocid="+blocid;
	        
        	location.href=window.BIZCTX_PATH + '/dispatcherrecord/dispatcherrecordjson/exportDispatcherRecord.action?'+strParams;
	}
});



