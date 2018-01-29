Ext.define('AdvertTypeApp.controller.AdvertTypeCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['AdvertTypeListStore'],//声明该控制层要用到的store
    models: ['AdvertTypeListModel'],//声明该控制层要用到的model
    views: ['AdvertTypeSearchView','AdvertTypeListView','AdvertTypeAddView','AdvertTypeEditView'],//声明该控制层要用到的view
//    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
//        {
//            ref: 'advertTypeListView',
//            selector: 'advertTypeListView'
//        }
//    ],
    init: function() {
    	
		this.control({
			'advertTypeSearchView button[action=search]' : {
				click : this.searchType
			},
			'advertTypeListView button[action=add]' : {
				click : this.addAdvertType
			},
			'advertTypeListView button[action=edit]' : {
				click : this.editAdvertType
			},
			'advertTypeAddView button[action=save]' : {
				click : this.saveAdvertType
			},
			'advertTypeEditView button[action=save]' : {
				click : this.saveAdvertType
			},
			'advertTypeListView button[action=delete]' : {
				click : this.deleteAdvertType
			}
			
			,
			'advertTypeListView' : {
				render : this.buttonAccess
			}
		});
	},
	
	buttonAccess : function() {
		var buttonArray = ['19010101', '19010102', '19010103'];
		for (var i = 0; i < buttonArray.length; i++) {
			if (parent.menuidArray.indexOf(buttonArray[i]) == -1) {
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	searchType : function(button) {
		var stime = Ext.util.Format.date(Ext.getCmp('a_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('a_etime').getValue(), 'Y-m-d H:i:s');
        if(etime != null && etime.length > 0){
            var beginTimes = stime.substring(0,10).split('-');
            var endTimes = etime.substring(0,10).split('-');
    
            var stimearray = stime.substring(10,19).split(':');
            var etimearray = etime.substring(10,19).split(':');
            var stimedate = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2],stimearray[0],stimearray[1],stimearray[2]);
            var etimedate = new Date(endTimes[0], endTimes[1]-1, endTimes[2],etimearray[0],etimearray[1],etimearray[2]);
            
            if(etimedate < stimedate){
                Ext.Msg.alert("提示","开始时间必须小于结束时间");
                return;
            }
        } 
		
		var store = this.getAdvertTypeListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	typename: encodeURI(Ext.getCmp('a_typename').getValue()),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('a_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('a_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	
	
	addAdvertType : function(button){
		var view = Ext.create('AdvertTypeApp.view.AdvertTypeAddView');
			view.show();
		return false;
	},
	editAdvertType : function(button){
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.create('AdvertTypeApp.view.AdvertTypeEditView');
			view.show();
		var store = this.getAdvertTypeListStoreStore();
		var data = store.getById(records[0].get('id'));
			view.down('form').loadRecord(data);
		return false;
	
	},
	saveAdvertType : function(button){
		var win = button.up('window');
		var form = win.down('form');
		var store = this.getAdvertTypeListStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/adverttypejson/saveAdvertType.action',
			method : 'post',
			waitMsg:'请稍候...',
			success : function(form, action) {
				var returnType = action.result.result.returnType;
				if (returnType == 0) {
					win.close();
					store.reload();
					Ext.Msg.alert("提示", "保存成功!");
				} else if (returnType == 1) {
					Ext.Msg.alert('提示', "广告类型名称已存在，请核实！");

				} else {
					Ext.Msg.alert('失败', "保存失败");
				}
			},
			failure : function(form, action) {
				win.close();
				Ext.Msg.alert('提示', "保存广告类型失败!");
				store.reload();
			}
		});
		return false;
	},
	deleteAdvertType : function(button) {
		var grid = Ext.getCmp('advertType');
		var records = grid.getSelectionModel().getSelection();
		var store = grid.getStore();
		if (records.length > 0) {
			Ext.Msg.buttonText.yes='确定';
			Ext.Msg.buttonText.no="取消";
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
										+ '/adverttypejson/deleteAdvertType.action',
								method : 'POST',
								params : "ids=" + jsonData,
								success : function(response, opts) {
									var text = response.responseText;
									var su = Ext.JSON.decode(text).result.su;
									if (su == 0) {
										Ext.Msg.alert('提示', "删除成功！");
										store.reload();
									} else if (su == -1) {
										Ext.Msg.alert('提示', "该广告类型已被使用，无法删除！");
										store.reload();
									}else{
										Ext.Msg.alert('提示', "删除失败！");
										store.reload();
									}
								},
								failure : function() {
									Ext.Msg.alert('提示', "服务器异常，稍后再试！");
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

