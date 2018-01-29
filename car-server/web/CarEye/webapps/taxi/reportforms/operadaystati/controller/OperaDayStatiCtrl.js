Ext.Loader.setConfig({enabled:true});

Ext.define('OperaDayStatiApp.controller.OperaDayStatiCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarListStore','OperaDayStatiListStore','CarPageListStore'],//声明该控制层要用到的store
    models: [],//声明该控制层要用到的model
    views: ['OperaDayStatiListView','OperaDayStatiSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'operaDayStatiListView',
            selector: 'operaDayStatiListView'
        }
    ],
    init: function() {
    	
		this.control({
			'operaDayStatiSearchView #blocid' : {
				select : this.loadDeptCar
			},
			'operaDayStatiSearchView button[action=search]' : {
				click : this.search
			},
			'operaDayStatiListView button[action=export]' : {
				click : this.exportExcel
			}
		});
	},
	loadDeptCar : function() {
		Ext.getCmp('c_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var deptid = Ext.getCmp('c_blocid').getValue();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
			var new_params = {
				deptid : deptid,
				carnumber : ""
			};
			Ext.apply(store.proxy.extraParams, new_params);
		});
		store.reload();
	},
	exportExcel : function (button){
	    var blocid = Ext.getCmp('c_blocid').getValue();
    	var carnumber = encodeURI(Ext.getCmp('c_carnumber').getValue());
		var stime = encodeURI(Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d'));
		var etime = encodeURI(Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d'));
    	location.href= window.BIZCTX_PATH + '/reportformsjson/exportOperaDayStati.action?carnumber='+carnumber
    	+'&stime='+stime+'&etime='+etime+'&blocid='+blocid;
	},
	search : function(){		
		var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d');
        var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d');
        
        if(etime != null && etime.length > 0){
            var beginTimes = stime.substring(0,10).split('-');
            var endTimes = etime.substring(0,10).split('-');
    
            var stimedate = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2]);
            var etimedate = new Date(endTimes[0], endTimes[1]-1, endTimes[2]);
            
            if(etimedate < stimedate){
                Ext.Msg.alert("提示","开始时间必须小于结束时间");
                return;
            }
        }
        
        var store = this.getOperaDayStatiListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	blocid: encodeURI(Ext.getCmp('c_blocid').getValue()),
	            	carnumber: encodeURI(Ext.getCmp('c_carnumber').getValue()),
	            	stime: stime,
	            	etime: etime
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1);
		
		return false;
	}

	
	
	
});

