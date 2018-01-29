Ext.define('FeedbackAdviceApp.controller.FeedbackAdviceCtrl', {
	extend : 'Ext.app.Controller',
	stores : [ 'FeedbackAdviceListStore', 'KeyTypeStore' ],
	models : [ 'FeedbackAdviceModel', 'AppTypeModel' ],
	views : [ 'FeedbackAdviceListView', 'FeedbackAdviceSearchView','AdviceDisposeView'],
	init : function() {

		this.control( {
			'feedbackAdviceSearchView button[action=search]' : {
				click : this.searchFeedbackAdvice
			},
			'feedbackAdviceListView button[action=dispose]' : {
				click : this.disposeFeedbackAdvice
			},
			'adviceDisposeView button[action=save]' : {
				click : this.saveAdviceDispose
			},
			'feedbackAdviceListView button[action=delete]' : {
				click : this.deleteFeedbackAdvice
			},
			'feedbackAdviceListView' : {
				render : this.buttonAccess
			}
		});
	},
	buttonAccess : function() {
		var buttonArray = [ '12100301', '12100302' ];
		for ( var i = 0; i < buttonArray.length; i++) {
			if (parent.menuidArray.indexOf(buttonArray[i]) == -1) {
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	searchFeedbackAdvice : function(button) {
		var store = this.getFeedbackAdviceListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
			var new_params = {
				typeid : Ext.getCmp('search_typeid').getValue(),
				s_time : Ext.util.Format.date(Ext.getCmp('s_time').getValue(),
						'Y-m-d H:i:s'),
				e_time : Ext.util.Format.date(Ext.getCmp('e_time').getValue(),
						'Y-m-d H:i:s')
			};
			Ext.apply(store.proxy.extraParams, new_params);
		});
		store.loadPage(1);
		return false;
	},
	disposeFeedbackAdvice : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if (records.length != 1) {
			Ext.Msg.alert('提示', '请选择一条记录进行处理');
			return;
		}
		var status = records[0].get('status');
		if(status == 2){
			Ext.Msg.alert('提示', '该数据已处理完成');
			return;
		}
		var view = Ext.widget('adviceDisposeView');
		var store = grid.getStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		view.show();
		return false;

	},
	saveAdviceDispose : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getFeedbackAdviceListStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		form.getForm().submit({
					url : window.BIZCTX_PATH + '/feedbackadvicejson/saveAdviceDispose.action',// 请求的服务器地址
					method : 'post',
					waitMsg : '正在保存,请稍候...',
					success : function(form, action) {
						var returnType = action.result.result.returnType;
						if (returnType == 0) {
							win.close();
							store.reload();
							Ext.Msg.alert("提示", "保存成功!");
						} else if (returnType == 1) {
							Ext.Msg.alert('提示', "密钥名已存在");

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
	},deleteFeedbackAdvice : function(button) {
		var grid = button.up('grid');
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
										+ '/feedbackadvicejson/deleteFeedbackAdvice.action',
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

	}
});