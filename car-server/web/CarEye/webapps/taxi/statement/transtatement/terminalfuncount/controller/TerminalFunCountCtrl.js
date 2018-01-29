Ext.define('TerminalFunCountApp.controller.TerminalFunCountCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['TerminalFunCountListStore','CarListStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['TerminalFunCountModel'],//声明该控制层要用到的model
    views: ['TerminalFunCountListView','TerminalFunCountSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'terminalFunCountListView',
            selector: 'terminalFunCountListView'
        },{
            ref: 'terminalFunCountSearchView',
            selector: 'terminalFunCountSearchView'
        }
    ],
    init: function() {
		this.control({
			'terminalFunCountSearchView #carnumber' : {
				change : this.loadCar
			},
			'terminalFunCountSearchView #blocid' : {
				select : this.loadDeptCar
			}
			
		});
	},
	loadDeptCar : function(){
		Ext.getCmp('tfc_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var deptid = Ext.getCmp('tfc_blocid').getValue();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	deptid: deptid,
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
            	carnumber: encodeURI(Ext.getCmp('tfc_carnumber').getValue())
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
		
	},
	exportInfo : function (button){
        var blocid= encodeURI(Ext.getCmp('tfc_blocid').getValue());
	    var terminal= encodeURI(Ext.getCmp('tfc_terminal').getValue());
	    var carnumber= encodeURI(Ext.getCmp('tfc_carnumber').getValue());
	    
        location.href= window.BIZCTX_PATH + '/statement/transtatementjson/excelTerminalFunCount.action?' +
        		'blocid='+blocid+'&terminal='+terminal+'&carnumber='+carnumber;
	},
	exportTFCWord : function (button){
        var blocid= encodeURI(Ext.getCmp('tfc_blocid').getValue());
	    var terminal= encodeURI(Ext.getCmp('tfc_terminal').getValue());
	    var carnumber= encodeURI(Ext.getCmp('tfc_carnumber').getValue());
	    
        location.href= window.BIZCTX_PATH + '/statement/transtatementjson/exportTFCWord.action?' +
        		'blocid='+blocid+'&terminal='+terminal+'&carnumber='+carnumber;
	},
	
	searchTerminalFunCount : function(button) {
		var store = this.getTerminalFunCountListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	blocid: encodeURI(Ext.getCmp('tfc_blocid').getValue()),
	            	terminal: encodeURI(Ext.getCmp('tfc_terminal').getValue()),
	            	carnumber: encodeURI(Ext.getCmp('tfc_carnumber').getValue())
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
 

