Ext.define('CusCancelApp.controller.CusCancelCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CusCancelListStore','TypeStore'],//声明该控制层要用到的store
    models: ['CusCancelModel'],//声明该控制层要用到的model
    views: ['CusCancelListView','CusCancelSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'cusCancelListView',
            selector: 'cusCancelListView'
        },{
            ref: 'cusCancelSearchView',
            selector: 'cusCancelSearchView'
        }
    ],
    init: function() {
		this.control({
			'cusCancelListView':{
                 render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['18070401'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	exportInfo : function (button){
        var username= encodeURI(Ext.getCmp('ccs_username').getValue());
	    var	phone= encodeURI(Ext.getCmp('ccs_phone').getValue());
	    var wy= encodeURI(Ext.getCmp('css_type').getValue());
        var stime= encodeURI(Ext.util.Format.date(Ext.getCmp('ccs_stime').getValue(), 'Y-m-d H:i:s'));
        var etime= encodeURI(Ext.util.Format.date(Ext.getCmp('ccs_etime').getValue(), 'Y-m-d H:i:s'));
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		
        location.href= window.BIZCTX_PATH + '/statement/transtatementjson/excelCusCancel.action?' +
        		'username='+username+'&phone='+phone+'&wy='+wy
        		'&stime='+stime+'&etime='+etime;
	},
	searchCusCancel : function(button) {
		var stime= encodeURI(Ext.util.Format.date(Ext.getCmp('ccs_stime').getValue(), 'Y-m-d H:i:s'));
        var etime= encodeURI(Ext.util.Format.date(Ext.getCmp('ccs_etime').getValue(), 'Y-m-d H:i:s'));
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		var store = this.getCusCancelListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	username: encodeURI(Ext.getCmp('ccs_username').getValue()),
	            	phone: encodeURI(Ext.getCmp('ccs_phone').getValue()),
	            	wy: encodeURI(Ext.getCmp('css_type').getValue()),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('ccs_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('ccs_etime').getValue(), 'Y-m-d H:i:s'))
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
