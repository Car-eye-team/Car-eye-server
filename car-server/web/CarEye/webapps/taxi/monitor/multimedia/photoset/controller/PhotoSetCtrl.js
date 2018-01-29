Ext.define('PhotoSetApp.controller.PhotoSetCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarInfoListStore','CarListStore','CarPageListStore'],
    models: ['CarInfoModel'],//声明该控制层要用到的model
    views: ['ParameterSetView','CarInfoListView'],
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
			var form = Ext.getCmp('parameterSet_Form');
			if (!form.isValid()) {
				return;
			}
			var cmd = Ext.getCmp('rp_cmd').getValue();
			if(cmd != 65535 && cmd != 0){
				var picnum = Ext.getCmp('rp_picnum').getValue();
				if(picnum.length == 0){
					Ext.Msg.alert('提示',"请输入拍照张数!");
					return;
				}
				cmd = picnum;
			}
			var carids = jsonData;
			var channel = Ext.getCmp('rp_channel').getValue();
			var screen = Ext.getCmp('rp_screen').getValue();
			var inv = Ext.getCmp('rp_inv').getValue();
			var save = Ext.getCmp('rp_save').getValue();
			var quality = Ext.getCmp('rp_quality').getValue();
			var bright = Ext.getCmp('rp_bright').getValue();
			var contrast = Ext.getCmp('rp_contrast').getValue();
			var saturation = Ext.getCmp('rp_saturation').getValue();
			var chroma = Ext.getCmp('rp_chroma').getValue();
			Ext.Ajax.request( {
				url : window.BIZCTX_PATH + '/multimedia/multimediajson/takePhotos.action',
				method : 'POST',  
				timeout : 5000,
				params : {carids:carids,channel : channel,cmd:cmd,chroma:chroma,
						  screen:screen,inv:inv,save:save,quality:quality,
						  bright:bright,contrast:contrast,saturation:saturation},
				success : function(response) {
					Ext.Msg.alert('提示',"拍摄指令下发成功!");
				},
				failure : function() {
					Ext.Msg.alert('提示',"拍摄指令下发失败!");
				}
		});
		} else {
			Ext.Msg.alert('提示', '请选择要拍照的车辆!');
		}
	}
});

