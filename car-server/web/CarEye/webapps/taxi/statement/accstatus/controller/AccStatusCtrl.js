Ext.define('AccStatusApp.controller.AccStatusCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['AccStatusListStore','CarListStore','CarInfoListStore'],//声明该控制层要用到的store
    models: ['AccStatusModel','CarModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['AccStatusListView','CarInfoListView','AccStatusSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'accStatusListView',
            selector: 'accStatusListView'
        },{
            ref: 'accStatusSearchView',
            selector: 'accStatusSearchView'
        }
    ],
    init: function() {
    	
		this.control({
			'accStatusSearchView #cm_blocid' : {
				select : this.loadDeptCar
			},
			'accStatusSearchView #cd_carnumber' : {
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
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
    	var blocid=Ext.getCmp('cm_blocid').getValue();
    	var carids=Ext.getCmp('day_carid').getValue();
    
        location.href= window.BIZCTX_PATH + '/statement/statementjson/exportAccStatus.action?carids='
        +carids+'&stime='+stime+'&etime='+etime+'&blocid='+blocid;
	},
	
	searchAccStatus : function(button) {
		var stime=Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(), 'Y-m-d H:i:s');
		var etime=Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(), 'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		
    	var carids=Ext.getCmp('day_carid').getValue();
		var store = this.getAccStatusListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carids: encodeURI(carids),
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
