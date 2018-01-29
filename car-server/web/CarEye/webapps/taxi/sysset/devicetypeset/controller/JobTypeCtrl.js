Ext.define('JobTypeApp.controller.JobTypeCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['JobTypeListStore'],//声明该控制层要用到的store
    models: ['JobTypeModel'],//声明该控制层要用到的model
    views: ['JobTypeSearchView','JobTypeListView','JobTypeAddView','JobTypeEditView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'jobTypeSearchView',
            selector: 'jobTypeSearchView'
        },{
            ref: 'jobTypeListView',
            selector: 'jobTypeListView'
        },{
            ref: 'jobTypeAddView',
            selector: 'jobTypeAddView'
        },{
            ref: 'jobTypeEditView',
            selector: 'jobTypeEditView'
        }
    ],
    init: function() {
    	
		this.control({
			'jobTypeListView button[action=add]' : {
				click : this.addJobType
			},
			'jobTypeListView button[action=delete]' : {
				click : this.deleteJobType
			},
			'jobTypeListView button[action=edit]' : {
				click : this.editJobType
			},
			'jobTypeSearchView button[action=search]' : {
				click : this.searchJobType
			},
			'jobTypeAddView button[action=save]' : {
				click : this.saveJobType
			},
			'jobTypeEditView button[action=save]' : {
				click : this.saveJobType
			},
			'jobTypeListView':{
                render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['200601','200602','200603'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	
	addJobType : function(button) {
		var view = Ext.widget('jobTypeAddView');
		view.show();
		return false;
	},
	searchJobType : function(button) {
		var store = this.getJobTypeListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	typename: encodeURI(Ext.getCmp('c_typename').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	    return false;
	},
	saveJobType : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getJobTypeListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			Ext.MessageBox.wait('请稍候...', '提示');
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/setjson/saveJobType.action',
				method : 'post',
				success : function(form, action) {
						Ext.MessageBox.updateProgress(1);
					    Ext.MessageBox.close();
						var resp = action.result.result;
						var su = resp.su;
						if (su == -3) {
							Ext.Msg.alert("提示","职业类型已存在，请更换名称!");
						}else if (su == -1) {
							win.close();
							Ext.Msg.alert("提示","系统异常,请与管理员联系!");
						}else {
							win.close();
							Ext.Msg.alert("提示","操作职业类型信息成功!");
							store.reload();
						}
			    },
				failure : function(form, action) {
					Ext.MessageBox.updateProgress(1);
					Ext.MessageBox.close();
					win.close();
					Ext.Msg.alert('提示', "操作职业类型信息失败!");
					store.reload();
				}
			});
			return false;
	},
	
	deleteJobType : function(button) {
		var grid = button.up('grid');
		var data = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (data.length>0) {
			Ext.MessageBox.confirm('提示','你确认要删除选中的数据？',
			function(btn) {
				if (btn == "yes") {
					var m = grid.getSelectionModel().getSelection();
					var jsonData = "";
					for ( var i = 0, len = m.length; i < len; i++) {
						var ss = m[i].get("id");
						if (i == 0) {
							jsonData = jsonData + ss;
						} else {
							jsonData = jsonData + "," + ss;
						}
					}
					Ext.Ajax.request( {
								url : window.BIZCTX_PATH + '/setjson/deleteJobType.action',
								method : 'POST',  
								params : "ids="+ jsonData,
								success : function(response) {
										var text = response.responseText;
                                        var result =  Ext.JSON.decode(text).result;
                                        var su = result.su;
                                   if(su==-2){
	                                    Ext.Msg.alert('提示',"该职业类型已使用，不能删除。");
                                   }else{
	                                    Ext.Msg.alert('提示',"数据删除成功");
										store.reload();
                                   }
								},
								failure : function() {
									Ext.Msg.alert('提示',"数据删除失败");
									store.reload();
								}
							});
				}
			});
		} else {
			Ext.Msg.alert('提示', '请选择要删除的数据项!');
		}
		return false;
	},
	editJobType : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.widget('jobTypeEditView');
		view.show();
		var store = this.getJobTypeListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	}
	
	
	
});

