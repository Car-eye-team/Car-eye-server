Ext.define('TerParamSetApp.controller.TerParamSetCtrl', {
			extend : 'Ext.app.Controller',
			stores : ['CarInfoListStore'],
			models : ['CarInfoModel'],//声明该控制层要用到的model
			views : ['CarInfoListView'],
			refs : [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
					{
						ref : 'carInfoListView',
						selector : 'carInfoListView'
					}],
			init : function() {
				this.control({
					'carInfoListView button[action=searchcar]' : {
						click : this.searchCarInfo
					},
					'carInfoListView button[action=loadterparam]' : {
						click : this.loadterparam
					},
					'terParamSetWindow button[action=save]' : {
						click : this.setterparam
					}
				});
			},
			searchCarInfo : function(button) {
				var carnumber = Ext.getCmp('c_carnumber').getRawValue();
				var deptid = Ext.getCmp('c_deptid').getValue();
				var terminal = Ext.getCmp('c_terminal').getValue();
				if (carnumber.length == 0 && terminal.length == 0 &&( typeof deptid == "undefined" || deptid.length == 0)) {
					Ext.Msg.alert('提示', '请选择搜索条件!');
					return;
				}
				var store = this.getCarInfoListStoreStore();
				store.clearFilter(true);
				store.on('beforeload', function(store, options) {
							var new_params = {
								carnumber : encodeURI(carnumber),
								deptid : deptid,
								terminal : terminal,
								usertype : 29
							};
							Ext.apply(store.proxy.extraParams, new_params);
						});
				store.loadPage(1);
				return false;
			},
			loadterparam : function(button) {
				var grid = button.up('grid');
				var records = grid.getSelectionModel().getSelection();
				if(records.length < 1){
					Ext.Msg.alert('提示', '请选择车辆');
					return;
				}
				var jsonData = "";
				var ters = "";
				for ( var i = 0, len = records.length; i < len; i++) {
					var ss = records[i].get("carid");
					if (i == 0) {
						jsonData = jsonData + ss;
					} else {
						jsonData = jsonData + "," + ss;
					}
				}
				
				var view = Ext.create('TerParamSetApp.view.TerParamSetWindow');
				
				Ext.getCmp('carids').setValue(jsonData);
				view.show();
				return false;
			},
			setterparam : function(button){
				var win = button.up('window');
				var form = win.down('form');
				if (!form.isValid()) {
					return;
				}																								
				
				if (!form.isValid()) {
						return;
				}
																							
				var checks = [];
				var count = 0;
				var texts = form.query('checkbox');
				for ( var i = 0; i < texts.length; i++) {
					if (texts[i].checked) {
						checks.push(texts[i].inputValue);
					}
				}
																							
				var objectform = form.getForm().getValues();
				var items = [];
				for ( var p in objectform ){
					if ( typeof ( objectform [ p ]) != " function " ){
						if(objectform[p].length > 0){
							var obj = {};
							obj.id = p.substring(10,p.length);
							obj.value = objectform[p];
							obj.size = objectform[p].length;
							if(checks.indexOf(obj.id) != -1){
								items.push(obj);
								count ++;
							}
						}
					} 
				}
				if(items.length == 0){
					Ext.Msg.alert('提示',"请填写需要设置的终端参数!");
					return;
				}
				Ext.Ajax.request( {
					url : window.BIZCTX_PATH + '/setjson/terParamSet.action',
					method : 'POST',  
					timeout : 5000,
					params : {
						ids : Ext.getCmp('carids').getValue(),
						count : count,
						items : JSON.stringify(items)
					},
					success : function(response) {
						Ext.Msg.alert('提示',"终端参数设置指令下发成功!");
						win.close();
					},
					failure : function() {
						Ext.Msg.alert('提示',"终端参数设置指令下发失败!");
					}
				});
			}
			
});
