Ext.define('PassageStatisticApp.controller.PassageStatisticCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['PassageStatisticStore','CarListStore'],//声明该控制层要用到的store
    models: ['PassageStatisticModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['PassageStatisticSearchView','PassageStatisticListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'passageStatisticSearchView',
            selector: 'passageStatisticSearchView'
        },{
            ref: 'passageStatisticListView',
            selector: 'passageStatisticListView'
        }
    ],
    init: function() {
    	
		this.control({
			'passageStatisticSearchView button[action=search]' : {
				click : this.search
			},
			'passageStatisticListView button[action=export]' : {
				click : this.exportInfo
			},
			'passageStatisticSearchView #carnumber' : {
				change : this.loadCar
			}
		});
	},
	loadCar : function(){
		var store = this.getCarListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	carnumber: encodeURI(Ext.getCmp('cl_carnumber').getValue())
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	exportInfo : function (button){
        var stime = Ext.util.Format.date(Ext.getCmp('cl_stime').getValue(), 'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('cl_etime').getValue(), 'Y-m-d H:i:s');
	    var carnumber = encodeURI(Ext.getCmp('cl_carnumber').getValue());
    	var blocid = Ext.getCmp('cl_blocid').getValue();
    	var terminal = Ext.getCmp('cl_terminal').getValue();
			
        location.href= window.BIZCTX_PATH + '/transaction/passagestatisticjson/exportExcel.action?carnumber='+carnumber
        	+'&stime='+stime+'&etime='+etime+'&blocid='+blocid+'&terminal='+terminal;
	},
	search :function(){
		var store = this.getPassageStatisticStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
        			carnumber: encodeURI(Ext.getCmp('cl_carnumber').getValue()),
	            	blocid: Ext.getCmp('cl_blocid').getValue(),
	            	terminal:Ext.getCmp('cl_terminal').getValue(),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('cl_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('cl_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	}
	
	
	
	
});

