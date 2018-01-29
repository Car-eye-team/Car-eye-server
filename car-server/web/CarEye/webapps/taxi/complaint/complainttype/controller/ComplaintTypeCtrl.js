Ext.define('ComplaintTypeApp.controller.ComplaintTypeCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['ComplaintTypeListStore'],//声明该控制层要用到的store
    models: ['ComplaintTypeModel'],//声明该控制层要用到的model
    views: ['ComplaintTypeListView','ComplaintTypeSearchView','ComplaintTypeAddView','ComplaintTypeEditView'],
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'complaintTypeListView',
            selector: 'complaintTypeListView'
        },{
            ref: 'complaintTypeSearchView',
            selector: 'complaintTypeSearchView'
        },{
            ref: 'complaintTypeAddView',
            selector: 'complaintTypeAddView'
        },{
            ref: 'complaintTypeEditView',
            selector: 'complaintTypeEditView'
        }
    ],
    init: function() {
		this.control({
			'complaintTypeListView button[action=add]' : {
				click : this.addComplaintType
			},
			'complaintTypeListView button[action=edit]' : {
				click : this.editComplaintType
			},
			'complaintTypeListView button[action=delete]' : {
				click : this.deleteComplaintType
			},
			'complaintTypeSearchView button[action=search]' : {
				click : this.searchComplaintType
			},
			'complaintTypeAddView button[action=save]' : {
				click : this.saveComplaintType
			},
			'complaintTypeEditView button[action=save]' : {
				click : this.saveComplaintType
			},
			'complaintTypeListView':{
                 render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['121101','121102','121103'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	searchComplaintType : function(button){
		var store = this.getComplaintTypeListStoreStore();
		store.clearFilter(true);
		var typename=encodeURI(Ext.getCmp('c_typename').getValue());	
		store.on('beforeload', function (store, options) {
	            var new_params = { 
		            typename: typename
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1);
	    return false;
	},
	editComplaintType : function(button){
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.widget('complaintTypeEditView');
		
		var store = this.getComplaintTypeListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		
		view.show();
		return false;
	
	},
	saveComplaintType : function(button){
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getComplaintTypeListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/complaintjson/saveComplaintType.action',
				method : 'post',
				waitMsg : '正在操作,请稍候...',
				success : function(form, action) {
						var resp = action.result.result;
						var su = resp.su;
						if (su == -1) {
							Ext.Msg.alert("提示","此投诉类型已存在，请更换!");
						}else if (su == -2) {
							win.close();
							Ext.Msg.alert("提示","系统异常,请与管理员联系!");
						}else if (su == -3) {
							Ext.Msg.alert("提示","此投诉类型名称已存在，请更换!");
						}else {
							win.close();
							Ext.Msg.alert("提示","投诉类型设置成功!");
							store.reload();
						}
			    },
				failure : function(form, action) {
					Ext.MessageBox.updateProgress(1);
					Ext.MessageBox.close();
					win.close();
					Ext.Msg.alert('提示', "投诉类型设置失败!");
					store.reload();
				}
			});
			return false;
	},
	addComplaintType : function(button){
		var view = Ext.widget('complaintTypeAddView');
		view.show();
		return false;
	},
	
	deleteComplaintType : function(button) {
			var grid = button.up('grid');
			var data = grid.getSelectionModel().getSelection();
			var store = grid.getStore();
			if (data.length>0) {
				Ext.MessageBox.confirm('提示','你确认要删除选中的投诉类型？',
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
									url : window.BIZCTX_PATH + '/complaintjson/delComplaintType.action',
									method : 'POST',  
									params : "ids="+ jsonData,
									success : function(response) {
										Ext.Msg.alert('提示',"投诉类型删除成功");
										store.reload();
									},
									failure : function() {
										Ext.Msg.alert('提示',"投诉类型删除失败");
										store.reload();
									}
								});
					}
				});
			} else {
				Ext.Msg.alert('提示', '请选择要删除的投诉类型!');
			}
		return false;
	}
	
});

