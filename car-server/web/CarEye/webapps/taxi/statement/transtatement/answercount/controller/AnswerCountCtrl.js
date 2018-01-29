Ext.define('AnswerCountApp.controller.AnswerCountCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['AnswerCountListStore'],// 声明该控制层要用到的store
    models: ['AnswerCountModel'],// 声明该控制层要用到的model
    views: ['AnswerCountListView','AnswerCountSearchView'],// 声明该控制层要用到的view
    refs: [// 相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'answerCountListView',
            selector: 'answerCountListView'
        },{
            ref: 'answerCountSearchView',
            selector: 'answerCountSearchView'
        }
    ],
    init: function() {
    	
		this.control({});
	},
	excelExport : function (button){
        var stime=Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(), 'Y-m-d H:i:s');
		var etime=Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(), 'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
//		if (stime == null || stime == ""){
//			Ext.Msg.alert('提示',"请选择开始时间");
//			return;
//		}
//		if (etime == null || etime == ""){
//			Ext.Msg.alert('提示',"请选择结束时间");
//			return;
//		}
//	    var beginTimes = stime.substring(0,10).split('-');
//        var endTimes = etime.substring(0,10).split('-');
//        var stimearray = stime.substring(10,19).split(':');
//        var etimearray = etime.substring(10,19).split(':');
//        var dt = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
//        var dt1 = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
//        if(dt1 < dt){
//        	Ext.Msg.alert("提示","开始时间必须小于终止时间");
//        	return;
//        }
        var blocid=Ext.getCmp('cm_blocid').getValue();
    	var carnumber=Ext.getCmp('cd_carnumber').getValue();
    	var drivername=Ext.getCmp('cd_drivername').getValue();
    	var zbstatus=Ext.getCmp('cd_zbstatus').getValue();
        	location.href= window.BIZCTX_PATH + '/statement/oncalltransaction/answercountjson/excelExportAnswerCountList.action?stime='
        	+stime+'&etime='+etime+'&blocid='+blocid
        	+'&carnumber='+carnumber+'&drivername='+drivername+'&zbstatus='+zbstatus;
	},
	
	search : function(button) {
		var stime=Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(), 'Y-m-d H:i:s');
		var etime=Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(), 'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
//		if (stime == null || stime == ""){
//			Ext.Msg.alert('提示',"请选择开始时间");
//			return;
//		}
//		if (etime == null || etime == ""){
//			Ext.Msg.alert('提示',"请选择结束时间");
//			return;
//		}
//	    var beginTimes = stime.substring(0,10).split('-');
//        var endTimes = etime.substring(0,10).split('-');
//        var stimearray = stime.substring(10,19).split(':');
//        var etimearray = etime.substring(10,19).split(':');
//        var dt = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
//        var dt1 = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
//        if(dt1 < dt){
//        	Ext.Msg.alert("提示","开始时间必须小于终止时间");
//        	return;
//        }
        
		var store = this.getAnswerCountListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(), 'Y-m-d H:i:s')),
        			blocid:encodeURI(Ext.getCmp('cm_blocid').getValue()),
        			carnumber:encodeURI(Ext.getCmp('cd_carnumber').getValue()),
        			drivername:encodeURI(Ext.getCmp('cd_drivername').getValue()),
        			zbstatus:encodeURI(Ext.getCmp('cd_zbstatus').getValue())
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

