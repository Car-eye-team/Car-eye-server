var s_lng=null;
var s_lat=null;
var e_lng=null;
var e_lat=null;
var flag=1;
var mapObj;
var marker;
var clicktime=1;
var markermap = new Map();
var address=null;
Ext.define('TransactionTypeApp.controller.TransactionTypeCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['TransactionTypeListStore'],//声明该控制层要用到的store
    models: ['TransactionTypeModel'],//声明该控制层要用到的model
    views: ['TransactionTypeListView',
		    'TransactionTypeAddView',
		    'TransactionTypeEditView',
		    'TransactionTypeSearchView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'transactionTypeEditView',
            selector: 'transactionTypekEditView'
        },{
            ref: 'transactionTypeListView',
            selector: 'transactionTypeListView'
        }
    ],
    init: function() {
    	
		this.control({
			'transactionTypeListView button[action=add]' : {
				click : this.addTransactionType
			},
			'transactionTypeListView button[action=delete]' : {
				click : this.deleteTransactionType
			},
			'transactionTypeListView button[action=edit]' : {
				click : this.editTransactionType
			},
			'transactionTypeSearchView button[action=search]' : {
				click : this.searchTransactionType
			},
			'transactionTypeAddView button[action=save]' : {
				click : this.saveTransactionType
			},
			'transactionTypeEditView button[action=save]' : {
				click : this.saveTransactionType
			},
			'transactionTypeListView':{
                render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['160105','160106','160107'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	addTransactionType : function(button) {
		var view = Ext.widget('transactionTypeAddView');
		view.show();
		return false;
	},
	searchTransactionType : function(button) {
		var store = this.getTransactionTypeListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	typename: encodeURI(Ext.getCmp('dbt_transactiontypename').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	saveTransactionType : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getTransactionTypeListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			Ext.MessageBox.wait('请稍候...', '提示');
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/transaction/transactionjson/saveTransactionType.action',
				method : 'post',
				success : function(form, action) {
						Ext.MessageBox.updateProgress(1);
					    Ext.MessageBox.close();
						var resp = action.result.result;
						var su = resp.su;
						 if (su == -1) {
							win.close();
							Ext.Msg.alert("提示","系统异常,请与管理员联系!");
						}else if (su == -3) {
							Ext.Msg.alert("提示","存在相同的约车类型名!");
						}else {
							win.close();
							Ext.Msg.alert("提示","操作信息成功!");
							store.reload();
						}
			    },
				failure : function(form, action) {
					Ext.MessageBox.updateProgress(1);
					Ext.MessageBox.close();
					win.close();
					Ext.Msg.alert('提示', "操作信息失败!");
					store.reload();
				}
			});
			return false;
	},
	deleteTransactionType : function(button) {
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
								url : window.BIZCTX_PATH + '/transaction/transactionjson/deleteTransactionType.action',
								method : 'POST',  
								params : "ids="+ jsonData,
								success : function(response) {
									var text = response.responseText;
									 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					 var su = obj.result.su;
									 if (su == -3) {
										Ext.Msg.alert('提示',"不能删除，该约车类型已被应用！");
										store.reload();
									}else if (su == -1) {
										Ext.Msg.alert('提示',"删除失败！");
										store.reload();
									}else{
										Ext.Msg.alert('提示',"删除成功！");
										store.reload();
									}
						   		},
								failure : function() {
									Ext.Msg.alert('提示',"删除失败");
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
	editTransactionType : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.widget('transactionTypeEditView');
		view.show();
		var store = this.getTransactionTypeListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	}
});



