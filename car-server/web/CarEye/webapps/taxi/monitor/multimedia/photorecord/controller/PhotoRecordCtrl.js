Ext.define('PhotoRecordApp.controller.PhotoRecordCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['PhotoRecordListStore','CarListStore','CommandTypeStore','SaveFlagStore','MediaListStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['PhotoRecordModel','CarInfoModel','MultiMediaInfoModel'],//声明该控制层要用到的model
    views: ['PhotoRecordListView','PhotoRecordSearchView','MediaListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'photoRecordListView',
            selector: 'photoRecordListView'
        },
        {
            ref: 'photoRecordSearchView',
            selector: 'photoRecordSearchView'
        }
    ],
    init: function() {
    	
		this.control({
			'photoRecordSearchView button[action=search]' : {
				click : this.search
			},'photoRecordListView button[action=export]':{
				click:this.excelExport
			},
			'photoRecordSearchView #carnumber' : {
				change : this.loadCar
			}
		});
	},
	loadCar : function(){
		var store = this.getCarListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	carnumber: encodeURI(Ext.getCmp('pr_carnumber').getValue())
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	search :function(button){
		
		var stime = Ext.util.Format.date(Ext.getCmp('pr_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('pr_etime').getValue(), 'Y-m-d H:i:s');
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
		
		var store = this.getPhotoRecordListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('pr_carnumber').getValue()),
	            	cmd: Ext.getCmp('pr_command').getValue(),
	            	save: Ext.getCmp('pr_saveflag').getValue(),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('pr_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('pr_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	    store.loadPage(1); 
	    //store.reload();
	    return false;
	},
	excelExport:function(button){
        var carnumber = Ext.getCmp('pr_carnumber').getValue();
    	var cmd = Ext.getCmp('pr_command').getValue();
    	var save=Ext.getCmp('pr_saveflag').getValue();
		var stime = Ext.util.Format.date(Ext.getCmp('pr_stime').getValue(), 'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('pr_etime').getValue(), 'Y-m-d H:i:s');
		
		location.href=window.BIZCTX_PATH + '/multimedia/multimediajson/exportPhotoRecordList.action?carnumber='+carnumber+"&cmd="+cmd+
		"&save="+save+"&stime="+stime+"&etime="+etime;
		
	
	},
	showPic :function(id,value){
		var view = Ext.widget('mediaListView');
		view.show();
		var store = this.getMediaListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { id: id};
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	    store.loadPage(1); 
	    return false;
	}
	
	
});

