Ext.define('PhotoSetApp.controller.PhotoSetCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['ServicePhotoListStore','CarListStore','CarPageListStore'],
    views: ['ParameterSetView','CarInfoListView','PhotoView','ServiceDetailView'],
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'serviceDetailView',
            selector: 'serviceDetailView'
        },{
            ref: 'carInfoListView',
            selector: 'carInfoListView'
        }
    ],
    init: function() {
		this.control({
			'carInfoListView button[action=search]' : {
				click : this.searchCarInfo
			},
			'serviceDetailView button[action=save]':{
				click: this.reissueServicePhoto
			},
			'carInfoListView #blocid' : {
				select : this.loadDeptCar
			}
		});
	},
	
	
	loadDeptCar : function(){
		Ext.getCmp('c_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var deptid = Ext.getCmp('c_blocid').getValue();
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
	searchCarInfo : function(button) {
		var carnumber = Ext.getCmp('c_carnumber').getRawValue();
		var drivername = Ext.getCmp('c_drivername').getRawValue();
		var blocid = Ext.getCmp('c_blocid').getValue();
		var store = this.getServicePhotoListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(carnumber),
	            	drivername: encodeURI(drivername),
	            	blocid: blocid
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	    return false;
	},
	reissueServicePhoto:function(button){
		
		  var carid = Ext.getCmp('carid').getValue();
		  var carnumber = Ext.getCmp('carnumber').getValue();
		  var blocname = Ext.getCmp('blocname').getValue();
		  var servicenumber = Ext.getCmp('servicenumber').getValue();
		  var drivername = Ext.getCmp('drivername').getValue();
		  var driverid = Ext.getCmp('driverid').getValue();
		  var starlevel = Ext.getCmp('starlevel').getValue();
		  var starleveltext = Ext.getCmp('starleveltext').getValue();
		  var version = Ext.getCmp('version').getValue();
		  
		  var picturepath = Ext.getCmp('picturepath').getValue();
		  var picturepath2 = document.getElementById('service_photo').src;
		  if(picturepath == null || picturepath.length == 0){
		  	 Ext.Msg.alert('提示',"服务证图片未找到,请前往司机信息管理功能完善服务证信息!");
		  	 return;
		  }
		  if(servicenumber == null || servicenumber.length == 0){
		  	 Ext.Msg.alert('提示',"服务证号不能为空,请前往司机信息管理功能完善服务证信息!");
		  	 return;
		  }
		  if(drivername == null || drivername.length == 0){
		  	 Ext.Msg.alert('提示',"司机名称不能为空,请前往司机信息管理功能完善服务证信息!");
		  	 return;
		  }
		  if(driverid == null || driverid.length == 0){
		  	 Ext.Msg.alert('提示',"司机代码不能为空,请前往司机信息管理功能完善服务证信息!");
		  	 return;
		  }
		  if(starlevel == null || starlevel.length == 0){
		  	 Ext.Msg.alert('提示',"星级不能为空,请前往司机信息管理功能完善服务证信息!");
		  	 return;
		  }
		  if(version == null || version.length == 0){
		  	 Ext.Msg.alert('提示',"版本不能为空,请前往司机信息管理功能完善服务证信息!");
		  	 return;
		  }
		  
		  
		  Ext.Ajax.request( {
			url : window.BIZCTX_PATH + '/cardriverjson/reissueServicePhoto.action',
			method : 'POST',  
			timeout : 5000,
			params : {carid:carid,servicenumber:servicenumber,starlevel:starlevel,drivername:drivername,
					  driverid:driverid,starleveltext:starleveltext,version:version,picturepath:picturepath2},
			success : function(response) {
				Ext.Msg.alert('提示',"服务证补发指令下发成功!");
			},
			failure : function() {
				Ext.Msg.alert('提示',"服务证补发指令下发失败!");
			}
	     });
	}
});

