Ext.define('DispatcherSendApp.controller.DispatcherSendCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarListStore','TypeStore','CarInfoListStore','DispatcherRecordListStore'],
    models: ['CarInfoModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['DispatcherRecordListView','DispatcherSendView','CarInfoListView'],
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'dispatcherRecordListView',
            selector: 'dispatcherRecordListView'
        },
        {
            ref: 'dispatcherSendView',
            selector: 'DispatcherSend'
        },
        {
            ref: 'carInfoListView',
            selector: 'carInfoListView'
        }
    ],
    init: function() {
		this.control({
			'carInfoListView button[action=searchcar]' : {
				click : this.searchCarInfo
			},
			'dispatcherSendView button[action=send]' : {
				click : this.sendDsipatherSend
			}
		});
	},
	searchCarInfo : function(button) {
		var carnumber = Ext.getCmp('c_carnumber').getRawValue();
		var blocid = Ext.getCmp('c_blocid').getValue();
		if (carnumber.length == 0 && typeof blocid == "undefined") {
					Ext.Msg.alert('提示', '请选择企业或者车辆!');
					return;
		}
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
	sendDsipatherSend:function(button){
	    var textgrid = Ext.getCmp('carinfogrid');
		var textdata = textgrid.getSelectionModel().getSelection();
//		var textstore = textgrid.getStore();
		if(textdata.length<=0){
			Ext.Msg.alert('提示', '请勾选车辆!');
			return false;
		}else{
			var carids = "";
			for (var i = 0, len = textdata.length; i < len; i++) {
				var ss = textdata[i].get("carid");
				if (i == 0) {
					carids = carids + ss;
				} else {
					carids = carids + "," + ss;
				}
			}
			//var win = Ext.widget('dispatcherSendView');
//			var win = button.up('window');
//			var form = win.down('form');
//			var store = this.getDispatcherRecordListStoreStore();
//			if (!form.getForm().isValid()) {
//				return;
//			}
			
			var filed=Ext.getCmp('remark');
			if (!filed.isValid()) {
				return;
			}
			
			//var id=Ext.getCmp('id').getValue();
			//var type=Ext.getCmp('type').getValue();
			var remark=Ext.getCmp('remark').getValue();
			var flag=Ext.getCmp('flag').getValue()==true?1:0;
			var emergency=Ext.getCmp('emergency').getValue()==true?1:0;
			var lcd=Ext.getCmp('lcd').getValue()==true?1:0;
			var tts=Ext.getCmp('tts').getValue()==true?1:0;
			var adv=Ext.getCmp('adv').getValue()==true?1:0;
			var action=Ext.getCmp('action').getValue()==true?1:0;
			//var dist=Ext.getCmp('dist').getValue()==true?1:0;
			
			//var params="?id="+id+"&carnumber="+ encodeURI(carnumber)+"&type="+type
								         // +"&remark="+ encodeURI(remark)+
								         // +"&flag="+flag;
			
			var store = this.getDispatcherRecordListStoreStore();
			var myMask = new Ext.LoadMask(Ext.getBody(), {//也可以是Ext.getCmp('').getEl()窗口名称
					msg    : "正在操作,请稍后...",//你要写成Loading...也可以
					msgCls : 'z-index:10000;'
			});
			myMask.show();
			Ext.Ajax.request({
								url : window.BIZCTX_PATH + '/dispatcherrecord/dispatcherrecordjson/saveDispatcherRecord.action',
								method : 'POST', 
								timeout:100000,
								params : {
									      //id:id,
								          carids:encodeURI(carids),
								          flag:flag,
								          emergency:emergency,
								          lcd:lcd,
								          tts:tts,
								          adv:adv,
								          action:action,
								          remark:encodeURI(remark)
										 },
										success : function(response) {
										var text = response.responseText;
										 var obj = eval( "(" + text + ")" );//转换后的JSON对象
					   					 var su = obj.result.su;
										 myMask.hide();
										 if (su == -1) {
											Ext.Msg.alert('提示',"操作失败");
										 }else {
											Ext.Msg.alert('提示',"操作成功");
											store.clearFilter(true);
											store.loadPage(1); 
											//store.load(); 
											//清空
											Ext.getCmp('id').setValue('');
											Ext.getCmp('type').setValue('');
											//Ext.getCmp('flag').setValue(false);
											Ext.getCmp('remark').setValue('');
										}
							   			},
										failure : function() {
											Ext.Msg.alert('提示',"操作失败");
										}
					     });
					     
		}
	}
});

