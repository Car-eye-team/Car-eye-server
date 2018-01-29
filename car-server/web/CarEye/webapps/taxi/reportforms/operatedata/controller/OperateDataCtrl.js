Ext.define('OperateDataApp.controller.OperateDataCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['OperateDataListStore','CarListStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['OperateDataModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['OperateDataSearchView','OperateDataListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'operateDataSearchView',
            selector: 'operateDataSearchView' 
        },{
            ref: 'operateDataListView',
            selector: 'operateDataListView'
        }
    ],
    init: function() {
    	
		this.control({
			'operateDataSearchView button[action=search]' : {
				click : this.search
			},
			'operateDataSearchView #carnumber' : {
				change : this.loadCar
			},
			'operateDataSearchView #c_blocid' : {
				select : this.loadDeptCar
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
	loadDeptCar : function(){
		Ext.getCmp('c_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var blocid = Ext.getCmp('c_blocid').getValue();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	deptid: blocid,
            	carnumber: ""
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	 loadCar : function(){
			var store = this.getCarPageListStoreStore();
			store.clearFilter(true);
			store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('c_carnumber').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.reload();
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
		
		var store = this.getOperateDataListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('c_carnumber').getValue()),
	            	blocid: encodeURI(Ext.getCmp('c_blocid').getValue()),
	            	terminal: encodeURI(Ext.getCmp('c_terminal').getValue()),
	            	stime: stime,
	            	etime: etime
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	exportOperateData : function (button){
        var carnumber = Ext.getCmp('c_carnumber').getValue();
    	var blocid = Ext.getCmp('c_blocid').getValue();
    	var terminal = Ext.getCmp('c_terminal').getValue();
    	var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s');
    	var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s');
		
		location.href=window.BIZCTX_PATH + '/operatedatajson/exportExcel.action?carnumber='+carnumber+"&blocid="+blocid+
		"&terminal="+terminal+"&stime="+stime+"&etime="+etime;
		
	}
	
	
	
});

