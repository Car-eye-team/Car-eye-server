Ext.define('CusBreachApp.controller.CusBreachCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CusBreachListStore'],//声明该控制层要用到的store
    models: ['CusBreachModel'],//声明该控制层要用到的model
    views: ['CusBreachListView','CusBreachSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'cusBreachListView',
            selector: 'cusBreachListView'
        },{
            ref: 'cusBreachSearchView',
            selector: 'cusBreachSearchView'
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
		var buttonArray = ['18070601'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	exportInfo : function (button){
		var username=encodeURI(Ext.getCmp('cbs_username').getValue());
        var phone=encodeURI(Ext.getCmp('cbs_phone').getValue());
        var stime=encodeURI(Ext.util.Format.date(Ext.getCmp('cbs_stime').getValue(), 'Y-m-d H:i:s'));
		var etime=encodeURI(Ext.util.Format.date(Ext.getCmp('cbs_etime').getValue(), 'Y-m-d H:i:s'));
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
        location.href= window.BIZCTX_PATH + '/statement/transtatementjson/excelCusBreachCancel.action?' +
        		'username='+username+'&phone='+phone+
        		'&stime='+stime+'&etime='+etime;
	},
	searchCusBreach : function(button) {
		var store=this.getCusBreachListStoreStore();
        var username=Ext.getCmp('cbs_username').getValue();
        var phone=Ext.getCmp('cbs_phone').getValue();
		var stime=Ext.util.Format.date(Ext.getCmp('cbs_stime').getValue(), 'Y-m-d H:i:s');
		var etime=Ext.util.Format.date(Ext.getCmp('cbs_etime').getValue(), 'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	username:encodeURI(username),
	            	phone:encodeURI(phone),
        			stime : encodeURI(stime),
        			etime : encodeURI(etime)
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