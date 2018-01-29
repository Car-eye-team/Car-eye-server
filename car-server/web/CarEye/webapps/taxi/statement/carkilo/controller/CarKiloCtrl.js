Ext.define('CarKiloApp.controller.CarKiloCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarKiloListStore','CarListStore'],//声明该控制层要用到的store
    models: ['CarKiloModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['CarKiloListView','CarKiloSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'carKiloListView',
            selector: 'carKiloListView'
        },{
            ref: 'carKiloSearchView',
            selector: 'carKiloSearchView'
        }
    ],
    init: function() {
    	
		this.control({
			'carKiloSearchView #cm_blocid' : {
				select : this.loadDeptCar
			},
			'carKiloSearchView #cd_carnumber' : {
				select : this.loadCar
			}
		});
	},
	
	loadCar : function(){
			var carnumber = Ext.getCmp('cd_carnumber').getRawValue();
			var carid = Ext.getCmp('cd_carnumber').getValue();
			var cdcarid = Ext.getCmp('day_carid').getValue();
			var cdcarnumber = Ext.getCmp('day_carnumber').getValue();
			if(cdcarnumber.length == 0){
				Ext.getCmp('day_carnumber').setValue(carnumber);
				Ext.getCmp('day_carid').setValue(carid);
			}else{
				Ext.getCmp('day_carnumber').setValue(cdcarnumber + ',' + carnumber);
				Ext.getCmp('day_carid').setValue(cdcarid + ',' + carid);
			}
	},
	
	loadDeptCar : function(){
		var store = Ext.StoreManager.get('CarListStore');
		store.clearFilter(true);
			store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	blocid: encodeURI(Ext.getCmp('cm_blocid').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	    store.reload();
	    Ext.getCmp('cd_carnumber').setValue("");
	    Ext.getCmp('day_carnumber').setValue("");
	    Ext.getCmp('day_carid').setValue("");
	},
	
	exportInfo : function (button){
        var stime=Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(), 'Y-m-d H:i:s');
		var etime=Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(), 'Y-m-d H:i:s');
		
		if (stime == null || stime == ""){
			Ext.Msg.alert('提示',"请选择开始时间");
			return;
		}
		if (etime == null || etime == ""){
			Ext.Msg.alert('提示',"请选择结束时间");
			return;
		}
	    
	    var beginTimes = stime.substring(0,10).split('-');
        var endTimes = etime.substring(0,10).split('-');

        var stimearray = stime.substring(10,19).split(':');
        var etimearray = etime.substring(10,19).split(':');
        var dt = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
        var dt1 = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
        
        if(dt1 < dt){
        	Ext.Msg.alert("提示","开始时间必须小于终止时间");
        	return;
        }
		
    	
    	var blocid=Ext.getCmp('cm_blocid').getValue();
    	var carnumber=Ext.getCmp('day_carid').getValue();
		if((blocid==null || blocid == "")&&(carnumber==null || carnumber == "")){
			Ext.Msg.alert("提示","企业和车牌号至少选择一个！");
        	return;
		}
			var userid = admin.id;
        	location.href= window.BIZCTX_PATH + '/statement/statementjson/exportCheckCarKilo.action?carnumber='
        	+carnumber+'&stime='+stime+'&etime='+etime+'&blocid='+blocid+'&userid='+userid;
	},
	
	searchCarKilo : function(button) {
		var stime=Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(), 'Y-m-d H:i:s');
		var etime=Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(), 'Y-m-d H:i:s');
		
		if (stime == null || stime == ""){
			Ext.Msg.alert('提示',"请选择开始时间");
			return;
		}
		if (etime == null || etime == ""){
			Ext.Msg.alert('提示',"请选择结束时间");
			return;
		}
	    
	    var beginTimes = stime.substring(0,10).split('-');
        var endTimes = etime.substring(0,10).split('-');

        var stimearray = stime.substring(10,19).split(':');
        var etimearray = etime.substring(10,19).split(':');
        var dt = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
        var dt1 = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
        if(dt1 < dt){
        	Ext.Msg.alert("提示","开始时间必须小于终止时间");
        	return;
        }
        
    	var blocid=Ext.getCmp('cm_blocid').getValue();
    	var carnumber=Ext.getCmp('day_carid').getValue();
		if((blocid==null || blocid == "")&&(carnumber==null || carnumber == "")){
			Ext.Msg.alert("提示","企业名称和车牌号至少选择一个！");
        	return;
		}
		var store = this.getCarKiloListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(carnumber),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(), 'Y-m-d H:i:s')),
        			blocid : encodeURI(Ext.getCmp('cm_blocid').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	    return false;
	}
	
});

