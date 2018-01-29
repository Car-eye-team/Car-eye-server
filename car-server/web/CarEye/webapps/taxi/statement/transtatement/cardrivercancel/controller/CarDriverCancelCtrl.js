Ext.define('CarDriverCancelApp.controller.CarDriverCancelCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarDriverCancelListStore','TypeStore'],//声明该控制层要用到的store
    models: ['CarDriverCancelModel'],//声明该控制层要用到的model
    views: ['CarDriverCancelListView','CarDriverCancelSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'carDriverCancelListView',
            selector: 'carDriverCancelListView'
        },{
            ref: 'carDriverCancelSearchView',
            selector: 'carDriverCancelSearchView'
        }
    ],
    init: function() {
		this.control({
			'cusBreachListView':{
                 render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['18070501'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	exportInfo : function (button){
		var blocid= Ext.getCmp('cdc_blocid').getValue();
        var carnumber=	Ext.getCmp('cdc_carnumber').getValue();
        var drivername=	Ext.getCmp('cdc_drivername').getValue();
        var wy=	Ext.getCmp('css_type').getValue();
        var stime=Ext.util.Format.date(Ext.getCmp('cdc_stime').getValue(), 'Y-m-d H:i:s');
		var etime=Ext.util.Format.date(Ext.getCmp('cdc_etime').getValue(), 'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
    
        location.href= window.BIZCTX_PATH + '/statement/transtatementjson/excelCarDriverCancel.action?' +
        		'&blocid='+blocid+'&carnumber='+carnumber+
        		'&drivername='+drivername+'&wy='+wy+
        		'&stime='+stime+'&etime='+etime;
	},
	searchCarDriver : function(button) {
		var store = this.getCarDriverCancelListStoreStore();
		var blocid= Ext.getCmp('cdc_blocid').getValue();
        var carnumber=	Ext.getCmp('cdc_carnumber').getValue();
        var drivername=	Ext.getCmp('cdc_drivername').getValue();
        var wy=	Ext.getCmp('css_type').getValue();
        var stime=Ext.util.Format.date(Ext.getCmp('cdc_stime').getValue(), 'Y-m-d H:i:s');
		var etime=Ext.util.Format.date(Ext.getCmp('cdc_etime').getValue(), 'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	blocid:blocid,
	            	carnumber:encodeURI(carnumber),
	            	drivername:encodeURI(drivername),
	            	wy:wy,
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('cdc_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('cdc_etime').getValue(), 'Y-m-d H:i:s'))
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
