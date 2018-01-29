Ext.define('RegionSetApp.controller.RegionSetCtrl',{
					extend : 'Ext.app.Controller',
					stores : [ 'RegionSetListStore', 'ClevelStore','SzcodeStore' ],// 声明该控制层要用到的store
					models : [ 'RegionSetListModel', 'SzcodeModel' ],// 声明该控制层要用到的model
					views : [ 'RegionSetListView', 'RegionSetAddView','RegionSetEditView','RegionSetSearchView' ],// 声明该控制层要用到的view
					refs : [// 相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
					{
						ref : 'regionSetListView',
						selector : 'regionSetListView'
					}, {
						ref : 'regionSetAddView',
						selector : 'regionSetAddView'
					}, {
						ref : 'regionSetEditView',
						selector : 'regionSetEditView'
					} ],
					init : function() {

						this.control( {
//							'regionSetListView button[action=add]' : {
//								click : this.addRegion
//							},
//							'regionSetListView button[action=edit]' : {
//								click : this.editRegion
//							},
//							'regionSetListView button[action=delete]' : {
//								click : this.deleteRegion
//							},
							'regionSetSearchView button[action=search]' : {
								click : this.searchRegion
							},
							'regionSetAddView button[action=save]':{
								click : this.saveRegion
							},
							'regionSetEditView button[action=save]':{
								click : this.saveRegion
							},
							'regionSetAddView #clevel' : {
								change : this.findParentRegionList
							},
							'regionSetEditView #clevel' : {
								change : this.findParentRegionList
							}
						});
					},
					searchRegion : function(button) {
						var store = this.getRegionSetListStoreStore();
						store.clearFilter(true);
						store.on('beforeload', function (store, options) {
				            var new_params = { 
				           		 cnname: encodeURI(Ext.getCmp('c_cnname').getValue()),
				           		 clevel: encodeURI(Ext.getCmp('c_clevel').getValue()),
				           		 parentname: encodeURI(Ext.getCmp('c_parentname').getValue())
				            };
				            Ext.apply(store.proxy.extraParams, new_params);
				        });
				        store.loadPage(1); 
					    return false;
					},
					addRegion : function(button) {
						var view = Ext.widget('regionSetAddView');
						view.show();
						return false;
					},
					editRegion : function(button){
						var grid = button.up('grid');
						var records = grid.getSelectionModel().getSelection();
						if(records.length != 1){
							Ext.Msg.alert('提示', '请选择一条记录编辑');
							return;
						}
						var view = Ext.widget('regionSetEditView');
						view.show();
						var store = this.getRegionSetListStoreStore();
						var data = store.getById(records[0].get('id'));
						view.down('form').loadRecord(data);
						return false;
					
					},
					deleteRegion : function(button) {
						var grid = button.up('grid');
						var data = grid.getSelectionModel().getSelection();
						var store = grid.getStore();
						if (data.length > 0) {
							Ext.MessageBox.confirm('提示','你确认要删除？',function(btn) {
												if (btn == "yes") {
													var m = grid.getSelectionModel().getSelection();
													var jsonData = "";
													for ( var i = 0, len = m.length; i < len; i++) {
														var ss = m[i].get("id");
														if (i == 0) {
															jsonData = jsonData
																	+ ss;
														} else {
															jsonData = jsonData
																	+ "," + ss;
														}
													}
													Ext.Ajax.request( {
																url : window.BIZCTX_PATH + '/regionjson/deleteRegion.action',
																method : 'POST',
//																timeout : 15000,
																params : "ids="+ jsonData,
																success : function(response) {
																	var text = response.responseText;
																	var obj = eval( "(" + text + ")" );//转换后的JSON对象
//																	var obj = eval(text);//转换后的JSON对象
																	var su = obj.result.su;
																	if(su==-3){
																		Ext.Msg.alert("提示","请先删除下级行政区域!");
																	}
																	 else {
																		Ext.Msg.alert("提示","行政区域删除成功!");
																		store.reload();
																	}
																},
																failure : function() {
																	Ext.Msg.alert('提示',"行政区域删除失败");
																	store.reload();
																}
															});
												}
											});
						} else {
							Ext.Msg.alert('提示', '请选择要删除的行政区域!');
						}
						return false;
					},
					saveRegion : function(button) {
						var win = button.up('window');
						var form = win.down('form');
						var store = this.getRegionSetListStoreStore();
						if (!form.getForm().isValid()) {
							return;
						}
						form.getForm().submit(
										{
											url : window.BIZCTX_PATH + '/regionjson/saveOrUpdateRegion.action',
											method : 'post',
											waitMsg : '请稍候...',
											success : function(form, action) {
												var resp = action.result.result;
												var su = resp.su;
												if(su==-4){
													Ext.Msg.alert("提示","行政区域代码已存在，请更换!");
												}
												else if (su == -3) {
													Ext.Msg.alert("提示","行政区域名称已存在，请更换!");
												} else if (su == -2) {
													win.close();
													Ext.Msg.alert("提示","系统异常,请与管理员联系!");
												} else {
													win.close();
													Ext.Msg.alert("提示","保存行政区域成功!");
													store.reload();
												}
											},
											failure : function(form, action) {
												win.close();
												Ext.Msg.alert('提示',"保存行政区域失败!");
												store.reload();
											}
										});
						return false;
					},
					findParentRegionList:function(){
						Ext.getCmp('parentid').setValue("");
						var store=this.getSzcodeStoreStore();
						var id="";
						if(typeof Ext.getCmp('id')!='undefined'){
							id=Ext.getCmp('id').getValue();
						};
						store.clearFilter(true);
						store.on('beforeload', function (store, options) {
					            var new_params = { 
					               clevel: encodeURI(Ext.getCmp('clevel').getValue()),
					               id: id
					            };
					            Ext.apply(store.proxy.extraParams, new_params);
					        });
					    store.reload();
					    return false;
					}
				});
