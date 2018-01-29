Ext.define('CarPhotoApp.controller.CarPhotoCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarInfoListStore','CarListStore','TypeStore','CarPageListStore'],
    models: ['CarInfoModel'],//声明该控制层要用到的model
    views: ['PhotoListView','CarInfoListView','PhotoSearchView','RightView'],
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'photoListView',
            selector: 'photoListView'
        },{
            ref: 'carInfoListView',
            selector: 'carInfoListView'
        },{
            ref: 'photoSearchView',
            selector: 'photoSearchView'
        },{
            ref: 'rightView',
            selector: 'rightView'
        }
    ],
    init: function() {
		this.control({
			'carInfoListView button[action=search]' : {
				click : this.searchCarInfo
			},
			'carInfoListView #blocid' : {
				select : this.loadDeptCar
			},
			'photoSearchView button[action=search]' : {
				click : this.searchCarPhoto
			},
			'photoListView button[action=save]':{
				click: this.commitPhotoList
			},
			'photoListView button[action=exportWord]':{
				click: this.exportWord
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
	
	exportWord : function(button) {
	        	var grid = button.up('viewport').down('grid');
	    		var data = grid.getSelectionModel().getSelection();
	    		var store = grid.getStore();
	    		if (data.length == 1) {
	    			var cars = grid.getSelectionModel().getSelection();
	    			var carid = cars[0].get("carid");
	    			
	    			var eventcode = Ext.getCmp('cp_eventcode').getValue();
	    			var stime = Ext.util.Format.date(Ext.getCmp('cp_stime').getValue(), 'Y-m-d H:i:s');
	    	   	    var etime = Ext.util.Format.date(Ext.getCmp('cp_etime').getValue(), 'Y-m-d H:i:s');
	    	   	    
	    	    	location.href= window.BIZCTX_PATH + '/multimedia/multimediajson/exportWord.action?carid='+carid
	    	    	+'&stime='+stime+'&etime='+etime+'&eventcode='+eventcode;
	    		} else {
	    			Ext.Msg.alert('提示', '请选择一条车辆记录!');
	    		}
		
		
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
	commitPhotoList:function(button){
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
	},
	searchCarPhoto:function(button){
		var grid = button.up('viewport').down('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length ==1) {
			var cars = grid.getSelectionModel().getSelection();
			var carid = cars[0].get("carid");
			
			var eventcode = Ext.getCmp('cp_eventcode').getValue();
			var stime = Ext.util.Format.date(Ext.getCmp('cp_stime').getValue(), 'Y-m-d H:i:s');
	   	    var etime = Ext.util.Format.date(Ext.getCmp('cp_etime').getValue(), 'Y-m-d H:i:s');
			
			Ext.Ajax.request( {
				url :window.BIZCTX_PATH +"/multimedia/multimediajson/queryCarPhotos.action", // 请求路径
				method : 'POST',  
				timeout : 120000,
				params : {
					carid:carid,
					eventcode:eventcode,
					stime:stime,
					etime:etime
				},
				success : function(response) {
				    var text = response.responseText;
					var obj = eval( "(" + text + ")" );//转换后的JSON对象
					var list = obj.result.list;
					document.getElementById('photolist').innerHTML = "";
					var len=list.length;
					if(len == 0){
						Ext.Msg.alert('提示',"没有找到相关图片信息");
					}else{
						var photodiv = ""
						for(var i=0;i<len;i++){
							var eventcodestr = "";
							var value = list[i].eventcode;
							if(value == 0){
								eventcodestr =  "平台下发指令";
							}else if(value == 1){
								eventcodestr =  "定时动作";
							}else if(value == 2){
								eventcodestr =  "抢劫报警触发";
							}else if(value == 3){
								eventcodestr =  "碰撞侧翻报警触发";
							}else if(value == 4){
								eventcodestr =  "司机上班签到";
							}else if(value == 5){
								eventcodestr =  "司机下班签退";
							}else if(value == 6){
								eventcodestr =  "空车转重车";
							}else if(value == 7){
								eventcodestr =  "重车转空车";
							}else{
								eventcodestr =  "";
							}
							
							var divcss = '<div style="width:45%;height:230px;float:left;margin:10px;">';
							var div = divcss + '<a target="_blank" href="'+ list[i].multimediapath +'">' +
									'<img style="width:100%;height:100%;" src="'+ list[i].multimediapath +'"></img></a>' +
									'<div style="text-align:center">类型：'+eventcodestr+'，时间：'+list[i].createtime+'</div></div>';
							photodiv += div;
						}
						document.getElementById('photolist').innerHTML = photodiv;
					}
					Ext.getCmp('photoListView').doLayout();
				},
				failure : function() {
					Ext.Msg.alert('提示',"查询图片信息失败");
				}
			});
			
		} else {
			Ext.Msg.alert('提示', '请选择一条车辆记录!');
		}
	}
});

