Ext.define('AppManageApp.controller.AppManageCtrl', {
	extend : 'Ext.app.Controller',
	stores : ['AppTypeListStore', 'AppVersionListStore', 'AppVersionStore'],// 声明该控制层要用到的store
	models : [],// 声明该控制层要用到的model
	views : ['AppTypeListView', 'AppVersionListView'],// 声明该控制层要用到的view
	init : function() {
		this.control({
					'appTypeListView button[action=search]' : {
						click : this.searchAppType
					},
					'appTypeListView button[action=add]' : {
						click : this.addAppType
					},
					' appTypeAddView button[action=save]' : {
						click : this.saveAppType
					},
					'appTypeListView button[action=edit]' : {
						click : this.editAppType
					},
					'appTypeEditView button[action=save]' : {
						click : this.saveAppType
					},
					'appTypeListView button[action=delete]' : {
						click : this.deleteAppType
					},
					'appVersionListView button[action=search]' : {
						click : this.searchAppVersion
					},
					'appVersionListView button[action=add]' : {
						click : this.addAppVersion
					},
					'appVersionAddView button[action=save]' : {
						click : this.saveAppVersion
					},
					'appVersionListView button[action=edit]' : {
						click : this.editAppVersion
					},
					'appVersionEditView button[action=save]' : {
						click : this.saveAppVersion
					},
					'appVersionListView button[action=delete]' : {
						click : this.deleteAppVersion
					},
					'appTypeListView' : {
						render : this.buttonAccess
					},
					'appVersionListView' : {
						render : this.buttonAccess2
					}
				});
	},
	buttonAccess : function() {
		var buttonArray = ['12100101', '12100102', '12100103'];
		for (var i = 0; i < buttonArray.length; i++) {
			if (parent.menuidArray.indexOf(buttonArray[i]) == -1) {
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	buttonAccess2 : function() {
		var buttonArray = ['12100104', '12100105', '12100106'];
		for (var i = 0; i < buttonArray.length; i++) {
			if (parent.menuidArray.indexOf(buttonArray[i]) == -1) {
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	searchAppType : function(button) {
		var store = this.getAppTypeListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
					var new_params = {
						typename : encodeURI(Ext.getCmp('search_type')
								.getValue())
					};
					Ext.apply(store.proxy.extraParams, new_params);
				});
		store.loadPage(1);
		// store.reload();
		return false;
	},
	searchAppVersion : function(button) {
		var store = this.getAppVersionListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
					var new_params = {
						versionname : encodeURI(Ext
								.getCmp('search_versionname').getValue()),
						typeid : Ext.getCmp('search_typeid').getValue(),
						s_time : Ext.util.Format.date(Ext
										.getCmp('s_time').getValue(),
								'Y-m-d H:i:s'),
						e_time : Ext.util.Format.date(Ext
										.getCmp('e_time').getValue(),
								'Y-m-d H:i:s')
						
					};
					Ext.apply(store.proxy.extraParams, new_params);
				});
		store.loadPage(1);
		// store.reload();
		return false;
	},
	addAppType : function(button) {
//		var view = Ext.widget('appTypeAddView');
		var view = Ext.create('AppManageApp.view.AppTypeAddView');
		view.show();
		return false;
	},
	addAppVersion : function(button) {
//		var view = Ext.widget('appVersionAddView');
		var view = Ext.create('AppManageApp.view.AppVersionAddView');
		view.show();
		this.uploadFile();
		return false;
	},
	saveAppType : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getAppTypeListStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		form.getForm().submit({
					url : window.BIZCTX_PATH
							+ '/appmanagejson/appTypeSave.action',// 请求的服务器地址
					method : 'post',
					waitMsg : '正在保存,请稍候...',
					success : function(form, action) {
						var returnType = action.result.result.returnType;
						if (returnType == 0) {
							win.close();
							store.reload();
							Ext.Msg.alert("提示", "保存成功!");

						} else if (returnType == 1) {
							Ext.Msg.alert('提示', "类型名称已存在", function() {
										Ext.getCmp('apptype_addtypename')
												.focus();
									});

						} else {
							Ext.Msg.alert('失败', "保存失败");
						}
					},
					failure : function(form, action) {
						Ext.Msg.alert('提示', "保存失败");
						win.close();

						// Ext.MessageBox.close();
						store.reload();
					}

				})
		return false;
	},
	saveAppVersion : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getAppVersionListStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		form.getForm().submit({
					url : window.BIZCTX_PATH
							+ '/appmanagejson/appVersionSave.action',// 请求的服务器地址
					method : 'post',
					waitMsg : '正在保存,请稍候...',
					success : function(form, action) {
						var returnType = action.result.result.returnType;
						if (returnType == 0) {
							win.close();
							store.reload();
							Ext.Msg.alert("提示", "保存成功!");

						} else if (returnType == 1) {
							Ext.Msg.alert('提示', "版本名称已存在", function() {
										Ext.getCmp('appversionname_add')
												.focus();
									});

						} else {
							Ext.Msg.alert('失败', "保存失败");
						}
					},
					failure : function(form, action) {
						Ext.Msg.alert('提示', "保存失败");
						win.close();

						// Ext.MessageBox.close();
						store.reload();
					}

				})
		return false;
	},
	editAppType : function(button) {
		var grid = Ext.getCmp('apptypelist');
		var records = grid.getSelectionModel().getSelection();
		if (records.length != 1) {
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
//		var view = Ext.widget('appTypeEditView');
		var view = Ext.create('AppManageApp.view.AppTypeEditView');
		var store = grid.getStore();
		var data = store.getById(records[0].get('id'));
		Ext.getCmp('editapptype').loadRecord(data);
		view.show();
		return false;
	},
	editAppVersion : function(button) {
		var grid = Ext.getCmp('appversionlist');
		var records = grid.getSelectionModel().getSelection();
		if (records.length != 1) {
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
//		var view = Ext.widget('appVersionEditView');
		var view = Ext.create('AppManageApp.view.AppVersionEditView');
		var store = grid.getStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		view.show();
	    this.uploadFile();
		return false;
	},
	deleteAppType : function(button) {
		var grid = Ext.getCmp('apptypelist');
		var records = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (records.length > 0) {
			// Ext.Msg.buttonText.yes='确定';
			// Ext.Msg.buttonText.no="取消";
			Ext.Msg.confirm('提示', '你确认要删除选中的数据?', function(btn) {
				if (btn == 'yes') {
					var m = records;
					var jsonData = "";
					for (var i = 0; i < m.length; i++) {
						var ss = m[i].get('id');
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					Ext.Ajax.request({
								url : window.BIZCTX_PATH
										+ '/appmanagejson/deleteAppType.action',
								method : 'POST',
								params : "ids=" + jsonData,
								success : function(response, opts) {
									var text = response.responseText;
									var su = Ext.JSON.decode(text).result.su;
									if (su == 0) {
										Ext.Msg.alert('提示', "删除成功！");
										store.reload();
									} else if (su == -1) {
										Ext.Msg.alert('提示', "数据下有版本型号，无法删除！");
										store.reload();
									}
								},
								failure : function() {
									Ext.Msg.alert('提示', "删除失败异常");
									store.reload();
								}

							})

				}

			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的数据项!');
		};

	},deleteAppVersion : function(button) {
		var grid = Ext.getCmp('appversionlist');
		var records = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (records.length > 0) {
			// Ext.Msg.buttonText.yes='确定';
			// Ext.Msg.buttonText.no="取消";
			Ext.Msg.confirm('提示', '你确认要删除选中的数据?', function(btn) {
				if (btn == 'yes') {
					var m = records;
					var jsonData = "";
					for (var i = 0; i < m.length; i++) {
						var ss = m[i].get('id');
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					Ext.Ajax.request({
								url : window.BIZCTX_PATH
										+ '/appmanagejson/deleteAppVersion.action',
								method : 'POST',
								params : "ids=" + jsonData,
								success : function(response, opts) {
									var text = response.responseText;
									var su = Ext.JSON.decode(text).result.su;
									if (su == 0) {
										Ext.Msg.alert('提示', "删除成功！");
										store.reload();
									} else if (su == -1) {
										Ext.Msg.alert('提示', "数据删除失败！");
										store.reload();
									}
								},
								failure : function() {
									Ext.Msg.alert('提示', "删除失败异常");
									store.reload();
								}

							})

				}

			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的数据项!');
		};

	},uploadFile : function(){
		$('#fileInput').uploadify({   
		   'uploader': window.BIZCTX_PATH + '/resource/js/uploadify/js/swf/uploadify.swf',
		  'script': window.BIZCTX_PATH + '/appmanagejson/appVersionUpload.action',   //指定服务端处理类的入口
		   'folder': window.BIZCTX_PATH + '/upload',   
		   'cancelImg': window.BIZCTX_PATH +'/resource/js/uploadify/images/cancel.png',
		   'fileDataName': 'fileInput', //和input的name属性值保持一致就好，Struts2就能处理了   
		   'queueID': 'fileQueue', 
		   'auto': true,//是否选取文件后自动上传   
		   'multi': false,//是否支持多文件上传
		   'simUploadLimit' : 1,//每次最大上传文件数  
		   'sizeLimit':1024*1024*1024*300,
		   'buttonImg': window.BIZCTX_PATH +'/resource/js/uploadify/images/upload.gif',  
		   'width':'56',
		   'height':'15',
		   'fileDesc': '请选择文件',
		   'fileExt': '*.*;',
		   'displayData': 'percentage',//有speed和percentage两种，一个显示速度，一个显示完成百分比    
		   'onComplete': function (event, queueID, fileObj, response, data){
		   	    //src=src+"%^+#%"+response
			   	Ext.getCmp('downloadaddr').setValue(response.trim());
			   	Ext.Msg.alert('提示',"文件上传成功");
		   }  
	   }); 
	}
});

function downLoad(filename, path) {
	location.href = window.BIZCTX_PATH + '/servlet/FileDownload?path=' + path
			+ '&filename=' + filename;
}
