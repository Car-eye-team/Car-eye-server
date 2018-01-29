Ext.define('SearchSetApp.controller.SearchSetCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarInfoListStore','CarListStore','ParmTypeTreeStore','CarPageListStore'],
    models: ['CarInfoModel'],//声明该控制层要用到的model
    views: ['ParameterSetView','CarInfoListView','TypeListView'],
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'parameterSetView',
            selector: 'parameterSetView'
        },
        {
            ref: 'carInfoListView',
            selector: 'carInfoListView'
        }
    ],
    init: function() {
		this.control({
			'carInfoListView button[action=search]' : {
				click : this.searchCarInfo
			},
			'parameterSetView button[action=save]':{
				click: this.commitParameterSet
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
		var blocid = Ext.getCmp('c_blocid').getValue();
		var store = this.getCarInfoListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(carnumber),
	            	blocid: blocid
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	    return false;
	},
	commitParameterSet:function(button){
	
		var id = button.id.substring(2);
		var type;	
		var prestr="";	
		if(id == "11"){
			type = 1;	//多媒体检索下发
			prestr = "1";
		}else if(id == "12"){
			type = 2;	//多媒体上传下发
			prestr = "2";
		}
	
		var grid = button.up('viewport').down('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length > 0) {
			var cars = grid.getSelectionModel().getSelection();
			var jsonData = "";
			for (var i = 0, len = cars.length; i < len; i++) {
				var ss = cars[i].get("carid");
				if (i == 0) {
					jsonData = jsonData + ss;
				} else {
					jsonData = jsonData + "," + ss;
				}
			}
			var form = Ext.getCmp('form_'+id);
			if (!form.getForm().isValid()) {
				return;
			}
			var carids = jsonData;
			var mediatype = Ext.getCmp('rp'+prestr+'_type').getValue();
			var channel = Ext.getCmp('rp'+prestr+'_channel').getValue();
			var fmt = Ext.getCmp('rp'+prestr+'_fmt').getValue();
			var flg = Ext.getCmp('rp'+prestr+'_fmt').getValue();
			var starttime = encodeURI(Ext.util.Format.date(Ext.getCmp('rp'+prestr+'_starttime').getValue(), 'Y-m-d H:i:s'));
			var endtime = encodeURI(Ext.util.Format.date(Ext.getCmp('rp'+prestr+'_endtime').getValue(), 'Y-m-d H:i:s'));
			
			Ext.Ajax.request( {
				url : window.BIZCTX_PATH + '/multimedia/multimediajson/searchSet.action',
				method : 'POST',  
				timeout : 5000,
				params : {carids:carids,mediatype : mediatype,accessid:channel,eventcode:fmt,
						  stime:starttime,etime:endtime,type:type,flg:flg},
				success : function(response) {
					Ext.Msg.alert('提示',"多媒体指令下发成功!");
				},
				failure : function() {
					Ext.Msg.alert('提示',"多媒体指令下发失败!");
				}
		});
		} else {
			Ext.Msg.alert('提示', '请选择要多媒体的车辆!');
		}
	}
});

