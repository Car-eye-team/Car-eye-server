Ext.define('CustomerEvaluationApp.controller.CustomerEvaluationCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CustomerEvaluationListStore','SlevelStore'],//声明该控制层要用到的store
    models: ['CustomerEvaluationModel'],//声明该控制层要用到的model
    views: ['CustomerEvaluationSearchView','CustomerEvaluationListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'customerEvaluationSearchView',
            selector: 'customerEvaluationSearchView' 
        },{
            ref: 'customerEvaluationListView',
            selector: 'customerEvaluationListView'
        }
    ],
    init: function() {
    	
		this.control({
			'customerEvaluationSearchView button[action=search]' : {
				click : this.search
			}
			
		});
	},
	buttonAccess : function() {
//		var buttonArray = ['130101','130102','130103','130104','130105','130112'];
//		for(var i=0;i<buttonArray.length;i++){
//			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
//				Ext.getCmp(buttonArray[i]).hide();
//			}
//		}
		return false;
	},
	search : function(button) {
		var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s');
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
		
		var store = this.getCustomerEvaluationListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	dname: encodeURI(Ext.getCmp('c_dname').getValue()),
	            	cname: encodeURI(Ext.getCmp('c_cname').getValue()),
	            	slevel: encodeURI(Ext.getCmp('c_slevel').getValue()),
	            	stime: stime,
	            	etime: etime
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	exportExcel : function (button){
        var dname = Ext.getCmp('c_dname').getValue();
    	var cname = Ext.getCmp('c_cname').getValue();
    	var slevel = Ext.getCmp('c_slevel').getValue();
    	var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s');
    	var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s');
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
		
		location.href=window.BIZCTX_PATH + '/customerevaluationjson/exportExcel.action?dname='+dname+"&cname="+cname+
		"&slevel="+slevel+"&stime="+stime+"&etime="+etime;
		
	}
	
	
	
});

