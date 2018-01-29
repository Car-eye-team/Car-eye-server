Ext.define('CommondLogApp.controller.CommondLogCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CommondLogListStore','CarListStore','StatusStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['CommondLogModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['CommondLogSearchView','CommondLogListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'commondLogSearchView',
            selector: 'commondLogSearchView' 
        },{
            ref: 'commondLogListView',
            selector: 'commondLogListView'
        }
    ],
    init: function() {
    	
		this.control({
			'commondLogSearchView button[action=search]' : {
				click : this.search
			},
			'commondLogSearchView #carnumber' : {
				change : this.loadCar
			},
			'commondLogSearchView #c_blocid' : {
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
		
		var store = this.getCommondLogListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('c_carnumber').getValue()),
	            	blocid: encodeURI(Ext.getCmp('c_blocid').getValue()),
	            	terminal: encodeURI(Ext.getCmp('c_terminal').getValue()),
	            	status : Ext.getCmp('c_status').getValue(),
	            	stime: stime,
	            	etime: etime
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	exportCommondLog : function (button){
        var carnumber = Ext.getCmp('c_carnumber').getValue();
    	var blocid = Ext.getCmp('c_blocid').getValue();
    	var terminal = Ext.getCmp('c_terminal').getValue();
    	var status = Ext.getCmp('c_status').getValue();
    	var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s');
    	var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s');
		
		location.href=window.BIZCTX_PATH + '/commondlogjson/exportExcel.action?carnumber='+carnumber+"&blocid="+blocid+
		"&terminal="+terminal+"&status="+status+"&stime="+stime+"&etime="+etime;
		
	},
	deleteCommondLog : function(button) {
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length>0) {
			Ext.MessageBox.confirm('提示','你确认要删除选中的数据？',
			function(btn) {
				if (btn == "yes") {
					var m = grid.getSelectionModel().getSelection();
					var jsonData = "";
					for ( var i = 0, len = m.length; i < len; i++) {
						var ss = m[i].get("id");
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					
					Ext.Ajax.request( {
								url : window.BIZCTX_PATH + '/commondlogjson/deleteCommondLog.action',
								method : 'POST',  
								params : "ids="+ jsonData,
								success : function(response) {
									Ext.Msg.alert('提示',"数据删除成功");
									store.reload();
								},
								failure : function() {
									Ext.Msg.alert('提示',"数据删除失败");
									store.reload();
								}
							});
				}
			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的数据项!');
		}
		return false;
	}
	
	
	
});

