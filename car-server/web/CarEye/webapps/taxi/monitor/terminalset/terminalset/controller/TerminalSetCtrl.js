Ext.define('TerminalSetApp.controller.TerminalSetCtrl', {
			extend : 'Ext.app.Controller',
			stores : ['CarListStore','CarInfoListStore','ParmTypeTreeStore'],
			models : ['CarInfoModel', 'CarInfoModel'],//声明该控制层要用到的model
			views : ['TypeListView', 'ParameterSetView', 'CarInfoListView'],
			refs : [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
			{
						ref : 'typeListView',
						selector : 'typeListView'
					}, {
						ref : 'parameterSetView',
						selector : 'parameterSetView'
					}, {
						ref : 'carInfoListView',
						selector : 'carInfoListView'
					}],
			init : function() {
				this.control({
							'carInfoListView button[action=searchcar]' : {
								click : this.searchCarInfo
							},
							'parameterSetView button[action=paramset]' : {
								click : this.paramset
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
				store.on('beforeload', function(store, options) {
							var new_params = {
								carnumber : encodeURI(carnumber),
								blocid : blocid
							};
							Ext.apply(store.proxy.extraParams, new_params);
						});
				store.loadPage(1);
				return false;
			},
			paramset : function(button) {
				var id = button.id.substring(2);
				var field = Ext.getCmp('p_'+id);
				if (!field.isValid()) {
					return;
				}
				
				var cargrid = Ext.getCmp('carinfogrid');
				var cardata = cargrid.getSelectionModel().getSelection();
				if (cardata.length == 0) {
					Ext.Msg.alert('提示', '请选择需要参数的车辆!');
					return false;
				}

				var ca = cargrid.getSelectionModel().getSelection();
				var carids = "";
				for (var i = 0, len = ca.length; i < len; i++) {
					var ss = ca[i].get("carid");
					if (i == 0) {
						carids = carids + ss;
					} else {
						carids = carids + "," + ss;
					}
				}

				var myMask = new Ext.LoadMask(Ext.getBody(), {
							msg : "正在设置终端参数，请稍后..."
				});
				myMask.show();
				
				Ext.Ajax.request({
							url : window.BIZCTX_PATH + '/terminalpositionjson/terminalParmSet.action',
							method : 'POST',
							timeout : 5000,
							params : {
								carids : carids,
								id : id,
								value:field.getValue()
							},
							success : function(response) {
								myMask.hide();// 隐藏
								Ext.Msg.alert('提示', "终端参数设置指令下发成功!");
							},
							failure : function() {
								Ext.Msg.alert('提示', "终端参数设置指令下发失败!");
							}
						});
			}

		});
