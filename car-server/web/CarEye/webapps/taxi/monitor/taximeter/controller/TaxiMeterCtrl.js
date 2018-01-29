Ext.define('TaxiMeterApp.controller.TaxiMeterCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['TaxiMeterListStore','CarInfoListStore'],//声明该控制层要用到的store
    models: ['TaxiMeterModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['TaxiMeterListView',
		    'TaxiMeterSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'taxiMeterEditView',
            selector: 'taxiMeterEditView'
        },{
            ref: 'taxiMeterListView',
            selector: 'taxiMeterListView'
        }
    ],
    init: function() {
    	
		this.control(
		    {'taxiMeterSearchView button[action=search]' : {
				click : this.searchTaxiMeter
			},
			'taxiMeterListView button[action=export]' : {
				click : this.exportInfo
			},
			'taxiMeterListView':{
                render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['14060201'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	searchTaxiMeter : function(button) {
		var flagnew=Ext.getCmp('tm_new').getValue();
		var carnumber=Ext.getCmp('tm_carnumber').getValue();
		if(flagnew){
				if(carnumber==null){
					Ext.Msg.alert('提示', '请选择车牌号!');
					return;
				}
			}else{
				flagnew=null;
			}
		var store = this.getTaxiMeterListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(carnumber),
	            	flagnew: encodeURI(flagnew),
	            	stime: encodeURI(Ext.getCmp('tm_stime').getValue()),
	            	etime: encodeURI(Ext.getCmp('tm_etime').getValue()),
	            	stime1: encodeURI(Ext.getCmp('tm_stime1').getValue()),
	            	etime1: encodeURI(Ext.getCmp('tm_etime1').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	exportInfo : function (button){
			var flagnew=Ext.getCmp('tm_new').getValue();
			var carnumber=Ext.getCmp('tm_carnumber').getValue();
			if(flagnew){
				if(carnumber==null){
					Ext.Msg.alert('提示', '请选择车牌号!');
					return;
				}
			}else{
				flagnew=null;
			}
		    carnumber= encodeURI(carnumber);
	        flagnew= encodeURI(flagnew);
	        var stime= encodeURI(Ext.getCmp('tm_stime').getValue());
	        var etime= encodeURI(Ext.getCmp('tm_etime').getValue());
	        var stime1= encodeURI(Ext.getCmp('tm_stime1').getValue());
	        var etime1= encodeURI(Ext.getCmp('tm_etime1').getValue());
		 
		   var strParams="carnumber="+carnumber+"&flagnew="+flagnew+"&stime="+stime+"&etime="+etime
		   +"&stime1="+stime1+"&etime1="+etime1;
	        
        	location.href=window.BIZCTX_PATH + '/cardriverjson/exportTaxiMeterExcel.action?'+strParams;
	}
});



