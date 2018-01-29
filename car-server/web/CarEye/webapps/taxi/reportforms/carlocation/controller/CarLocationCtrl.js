Ext.define('CarLocationApp.controller.CarLocationCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarLocationStore','CarListStore','DeviceTypeStore','CarStatusStore','CarTypeStore',
    'CarUseStore','ProvinceStore','CityStore','DistrictStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['CityInfoModel','CarLocationModel','CarInfoModel','DeviceTypeModel',
    	'CarTypeModel','CarUseModel'],//声明该控制层要用到的model
    views: ['CarLocationSearchView','CarLocationListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'carLocationSearchView',
            selector: 'carLocationSearchView'
        },{
            ref: 'carLocationListView',
            selector: 'carLocationListView'
        }
    ],
    init: function() {
    	
		this.control({
			'carLocationSearchView button[action=search]' : {
				click : this.search
			},
			'carLocationListView button[action=export]' : {
				click : this.exportInfo
			},
			'carLocationListView button[action=exportWord]' : {
				click : this.exportWord
			},
			'carLocationSearchView #carnumber' : {
				change : this.loadCar
			},
			'carLocationSearchView #province' : {
				select : this.loadCitySear
			},
			'carLocationSearchView #city' : {
				select : this.loadDistrictSear
			},
			'carLocationSearchView #blocid' : {
				select : this.loadDeptCar
			}
		});
	},
	loadCar : function(){
		var store = this.getCarPageListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	carnumber: encodeURI(Ext.getCmp('cl_carnumber').getValue())
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	loadDeptCar : function(){
		Ext.getCmp('cl_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var deptid = Ext.getCmp('cl_blocid').getValue();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	deptid: deptid,
            	carnumber: ""
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	loadCitySear : function() {
		var store = this.getCityStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
                var new_params = { 
               		 clevel: 2,
               		 provinceszcode: encodeURI(Ext.getCmp('province').getValue())
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
	    store.load();
	    Ext.getCmp('city').setValue("");
		Ext.getCmp('district').setValue("");
		return false;
	},
	loadDistrictSear : function() {
		var store = this.getDistrictStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
                var new_params = { 
               		 clevel: 3,
               		 cityszcode: encodeURI(Ext.getCmp('city').getValue())
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
	    store.load();
		Ext.getCmp('district').setValue("");
		return false;
	},
	exportInfo : function (button){
        var stime = Ext.util.Format.date(Ext.getCmp('cl_stime').getValue(), 'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('cl_etime').getValue(), 'Y-m-d H:i:s');
	    var carnumber = encodeURI(Ext.getCmp('cl_carnumber').getValue());
    	var devicetype = encodeURI(Ext.getCmp('cl_devicetype').getValue());
    	var blocid = Ext.getCmp('cl_blocid').getValue();
    	var carstatus = Ext.getCmp('cl_carstatus').getValue();
    	var terminal = Ext.getCmp('cl_terminal').getValue();
    	var cartype = Ext.getCmp('cl_cartype').getValue();
    	var usename = Ext.getCmp('cl_carusename').getValue();
    	var province = encodeURI(Ext.getCmp('province').getRawValue());
	    var city = encodeURI(Ext.getCmp('city').getRawValue());
	    var district = encodeURI(Ext.getCmp('district').getRawValue());
	    var userid = admin.id;
			
        location.href= window.BIZCTX_PATH + '/reportformsjson/exportCarLocationList.action?carnumber='+carnumber
        	+'&stime='+stime+'&etime='+etime+'&devicetype='+devicetype+'&blocid='+blocid+'&carstatus='+carstatus
        	+'&terminal='+terminal+'&cartype='+cartype+'&usename='+usename+'&province='+province
        	+'&city='+city+'&district='+district+'&userid='+userid;
	},
	exportWord : function (button){
		var stime = Ext.util.Format.date(Ext.getCmp('cl_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('cl_etime').getValue(), 'Y-m-d H:i:s');
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
		
        var stime = Ext.util.Format.date(Ext.getCmp('cl_stime').getValue(), 'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('cl_etime').getValue(), 'Y-m-d H:i:s');
    	var blocid = Ext.getCmp('cl_blocid').getValue();
		
    	
        location.href= window.BIZCTX_PATH + '/reportformsjson/exportCarLocationWord.action?blocid='+blocid
        	+'&stime='+stime
        	+'&etime='+etime;
	},
	search :function(){
		var store = this.getCarLocationStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
        			carnumber: encodeURI(Ext.getCmp('cl_carnumber').getValue()),
	            	devicetype: encodeURI(Ext.getCmp('cl_devicetype').getValue()),
	            	blocid: Ext.getCmp('cl_blocid').getValue(),
	            	carstatus:Ext.getCmp('cl_carstatus').getValue(),
	            	terminal:Ext.getCmp('cl_terminal').getValue(),
	            	cartype:Ext.getCmp('cl_cartype').getValue(),
	            	usename:Ext.getCmp('cl_carusename').getValue(),
	            	province: encodeURI(Ext.getCmp('province').getRawValue()),
	                city: encodeURI(Ext.getCmp('city').getRawValue()),
	                district: encodeURI(Ext.getCmp('district').getRawValue()),
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

