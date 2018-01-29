Ext.define('CarInfoApp.controller.CarInfoCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarInfoListStore','CarNumberStore','CarPageListStore'],//声明该控制层要用到的store
    views: ['CarInfoSearchView','CarInfoListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'carInfoSearchView',
            selector: 'carInfoSearchView'
        },{
            ref: 'carInfoListView',
            selector: 'carInfoListView'
        }
    ],
    init: function() {
    	
		this.control({
			'carInfoListView button[action=query]' : {
				click : this.queryTextRecord
			},
			'carInfoSearchView button[action=search]' : {
				click : this.searchCarInfo
			},
			'carInfoListView':{
                render : this.buttonAccess
            },
			'carInfoSearchView #carnumber' : {
				change : this.loadCar
			}
		});
	},
	buttonAccess : function() {
		var buttonArray = ['14041001'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	loadCar : function(){
		var store = this.getCarNumberStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	carnumber: encodeURI(Ext.getCmp('c_carnumber').getValue())
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	searchCarInfo : function(button) {
		
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
		
		var store = this.getCarInfoListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('c_carnumber').getValue()),
	            	deptid: Ext.getCmp('c_blocid').getValue(),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s'))
	            	
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	
	queryTextRecord : function(button) {
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length>0) {
			var jsonData = "";
			for ( var i = 0, len = data.length; i < len; i++) {
				var ss = data[i].get("id");
				if (i == 0) {
					jsonData = jsonData + ss;
				} else {
					jsonData = jsonData + "," + ss;
				}
			}
			
			var mask=new Ext.LoadMask(Ext.getBody(),{msg:"正在查询LED屏信息,请稍后......"});
			mask.show();
			
			Ext.Ajax.request( {
				url : window.BIZCTX_PATH + '/textinfojson/queryLedMessage.action',
				method : 'POST',  
				params : {ids:jsonData},
				success : function(response) {
				var text = response.responseText;
				 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				 var su = obj.result.su;
				 if (su == -1) {
					Ext.Msg.alert('提示', "LED屏信息查询失败!");
					mask.hide();
					store.reload();
				}else {
					Ext.Msg.alert("提示","LED屏信息查询成功!");
					mask.hide();
					win.close();
					store.reload();
				}
	   			},
				failure : function() {
					Ext.Msg.alert('提示', "LED屏信息查询失败!");
					mask.hide();
					store.reload();
				}
			});
		} else {
			Ext.Msg.alert('提示', '请选择要查询的车辆!');
		}
	}
	
	
});

