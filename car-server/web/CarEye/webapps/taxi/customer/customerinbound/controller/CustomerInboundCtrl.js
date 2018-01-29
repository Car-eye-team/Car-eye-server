Ext.define('CustomerInboundApp.controller.CustomerInboundCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CustomerInboundListStore'],//声明该控制层要用到的store
    models: ['CustomerInboundModel'],//声明该控制层要用到的model
    views: ['CustomerInboundListView','CustomerInboundSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
       {
            ref: 'customerInboundListView',
            selector: 'customerInboundListView'
        },
        {
            ref: 'customerInboundSearchView',
            selector: 'customerInboundSearchView'
        }
    ],
    init: function() {
    	
		this.control({
			'customerInboundSearchView button[action=search]' : {
				click : this.searchCustomerInbound
			},
			'customerInboundListView button[action=delete]' : {
				click : this.deleteCustomerInbound
			},
			'customerInboundListView button[action=export]' : {
				click : this.exportInfo
			},
			'customerInboundListView':{
                render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['150301','150302'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	searchCustomerInbound : function(button) {
		var store = this.getCustomerInboundListStoreStore();
		store.clearFilter(true);
		var stime=Ext.util.Format.date(Ext.getCmp('db_stime').getValue(), 'Y-m-d H:i:s');
		var etime=Ext.util.Format.date(Ext.getCmp('db_etime').getValue(), 'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
			Ext.Msg.alert("提示","结束日期不能小于开始日期!");
			return;
		}
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	cname: encodeURI(Ext.getCmp('db_cname').getValue()),
	            	callnumber: encodeURI(Ext.getCmp('db_callnumber').getValue()),
	            	stime: encodeURI(Ext.util.Format.date(Ext.getCmp('db_stime').getValue(), 'Y-m-d H:i:s')),
	            	etime: encodeURI(Ext.util.Format.date(Ext.getCmp('db_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	deleteCustomerInbound : function(button) {
		var grid = button.up('grid');
		//var grid=Ext.getCmp('dataBankListViewGrid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length>0) {
			Ext.MessageBox.confirm('提示','你确认要删除选中的数据？',
			function(btn) {
				if (btn == "yes") {
					var m = grid.getSelectionModel().getSelection();
					var jsonData = "";
					var customertypeids="";
					for ( var i = 0, len = m.length; i < len; i++) {
						var ss = m[i].get("id");
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					Ext.Ajax.request( {
								url : window.BIZCTX_PATH + '/customer/customerInboundjson/deleteCustomerInbound.action',
								method : 'POST',  
								params : "ids="+ jsonData,
								success : function(response) {
									var text = response.responseText;
									 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					 var su = obj.result.su;
									 if (su == -1) {
										Ext.Msg.alert('提示',"不能被删除");
										store.reload();
									}else {
										Ext.Msg.alert('提示',"删除成功");
										store.reload();
									}
						   		},
								failure : function() {
									Ext.Msg.alert('提示',"删除失败");
									store.reload();
								}
							});
				}
			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的数据项!');
		}
		return false;
	},
	exportInfo : function (button){
		    var cname=encodeURI(Ext.getCmp('db_cname').getValue());
	        var callnumber= encodeURI(Ext.getCmp('db_callnumber').getValue());
	        var stime= encodeURI(Ext.util.Format.date(Ext.getCmp('db_stime').getValue(), 'Y-m-d H:i:s'));
	        var etime= encodeURI(Ext.util.Format.date(Ext.getCmp('db_etime').getValue(), 'Y-m-d H:i:s'));
	        
			if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
			}
		
	        var strParams="cname="+cname+"&callnumber="+callnumber+"&stime="+stime+"&etime="+etime;
	        
        	location.href=window.BIZCTX_PATH + '/customer/customerInboundjson/exportExcel.action?'+strParams;
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

function openView(path) {
	window.open(path); 
}
