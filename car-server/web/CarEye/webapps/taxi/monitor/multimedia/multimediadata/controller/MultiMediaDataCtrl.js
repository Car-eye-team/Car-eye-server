Ext.define('MultiMediaDataApp.controller.MultiMediaDataCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['MultiMediaListStore','CarListStore','EventCodeStore','MultiMediaTypeStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['MultiMediaInfoModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['MultiMediaListView','MultiMediaSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'multiMediaListView',
            selector: 'multiMediaListView'
        },{
            ref: 'multiMediaSearchView',
            selector: 'multiMediaSearchView'
        }
    ],
    init: function() {
    	
		this.control({
			'multiMediaSearchView button[action=search]' : {
				click : this.search
			},
			'multiMediaSearchView #carnumber' : {
				change : this.loadCar
			},
			'multiMediaListView button[action=export]':{
				click:this.excelExport
			}
		});
	},
	loadCar : function(){
		var store = this.getCarListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	carnumber: encodeURI(Ext.getCmp('m_carnumber').getValue())
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	search :function(){
		
		var stime = Ext.util.Format.date(Ext.getCmp('m_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('m_etime').getValue(), 'Y-m-d H:i:s');
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
		
		var store = this.getMultiMediaListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('m_carnumber').getValue()),
	            	mediatype: Ext.getCmp('m_mediatype').getValue(),
	            	eventcode: Ext.getCmp('m_eventcode').getValue(),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('m_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('m_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	excelExport:function(button){
        var carnumber = Ext.getCmp('m_carnumber').getValue();
    	var mediatype = Ext.getCmp('m_mediatype').getValue();
    	var eventcode=Ext.getCmp('m_eventcode').getValue();
		var stime = Ext.util.Format.date(Ext.getCmp('m_stime').getValue(), 'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('m_etime').getValue(), 'Y-m-d H:i:s');
		
		location.href=window.BIZCTX_PATH + '/multimedia/multimediajson/exportMultiMediaList.action?carnumber='+carnumber+"&mediatype="+mediatype+
		"&eventcode="+eventcode+"&stime="+stime+"&etime="+etime;
		
	
	}
	
});

