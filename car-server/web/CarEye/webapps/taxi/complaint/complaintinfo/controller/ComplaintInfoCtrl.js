Ext.define('ComplaintInfoApp.controller.ComplaintInfoCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['ComplaintInfoListStore','DealStatusStore','TypeListStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['ComplaintInfoModel'],//声明该控制层要用到的model
    views: ['ComplaintInfoListView','ComplaintInfoSearchView','ComplaintInfoDealView'],
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'complaintInfoListView',
            selector: 'complaintInfoListView'
        },{
            ref: 'complaintInfoSearchView',
            selector: 'complaintInfoSearchView'
        },{
            ref: 'complaintInfoDealView',
            selector: 'complaintInfoDealView'
        }
    ],
    init: function() {
		this.control({
			'complaintInfoListView button[action=deal]' : {
				click : this.dealView
			},
			'complaintInfoListView button[action=delete]' : {
				click : this.deleteComplaintInfo
			},
			'complaintInfoListView button[action=export]' : {
				click : this.exportExcel
			},
			'complaintInfoSearchView button[action=search]' : {
				click : this.searchComplaintInfo
			},
			'complaintInfoDealView button[action=save]' : {
				click : this.dealComplaintInfo
			},
			'complaintInfoListView':{
                 render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['160701','160702','160703'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	exportExcel : function(button){
		var carnumber = Ext.getCmp('c_carnumber').getValue();
		var type = Ext.getCmp('c_type').getValue();
		var dealstatus = Ext.getCmp('c_dealstatus').getValue();
		var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s');
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
        
        var strParams="carnumber="+carnumber+"&type="+type+"&dealstatus="
        					+dealstatus+"&stime="+encodeURI(stime)+"&etime="+encodeURI(etime);
	    location.href=window.BIZCTX_PATH + '/complaintjson/exportExcel.action?'+strParams;
	},
	searchComplaintInfo : function(button){
		var stime = Ext.util.Format.date(Ext.getCmp('c_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('c_etime').getValue(), 'Y-m-d H:i:s');
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
		
		var store = this.getComplaintInfoListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
		            carnumber : encodeURI(Ext.getCmp('c_carnumber').getValue()),
		            dealstatus : encodeURI(Ext.getCmp('c_dealstatus').getValue()),
		            type : encodeURI(Ext.getCmp('c_type').getValue()),
		            stime : stime,
		            etime : etime
		            
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1);
	    return false;
	},
	dealView : function(button){
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.widget('complaintInfoDealView');
		
		var store = this.getComplaintInfoListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		
		view.show();
		return false;
	
	},
	dealComplaintInfo : function(button){
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getComplaintInfoListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			
			var dealcontent = Ext.getCmp('dealcontent').getValue().trim();
			if(dealcontent.length < 1){
				Ext.Msg.alert("提示","处理内容不能为空!");
				return;
			}else if(dealcontent.length > 512){
				Ext.Msg.alert("提示","处理内容不能超过512个字符!");
				return;
			}
			
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/complaintjson/dealComplaintInfo.action',
				method : 'post',
				waitMsg : '正在操作,请稍候...',
				success : function(form, action) {
						var resp = action.result.result;
						var su = resp.su;
						if (su == 0) {
							win.close();
							Ext.Msg.alert("提示","处理成功!");
							store.reload();
						}else {
							Ext.Msg.alert("提示","处理失败!");
						}
			    },
				failure : function(form, action) {
					Ext.MessageBox.updateProgress(1);
					Ext.MessageBox.close();
					win.close();
					Ext.Msg.alert('提示', "处理失败!");
					store.reload();
				}
			});
			return false;
	},
	deleteComplaintInfo : function(button) {
			var grid = button.up('grid');
			var data = grid.getSelectionModel().getSelection();
			var store = grid.getStore();
			if (data.length>0) {
				Ext.MessageBox.confirm('提示','你确认要删除选中的投诉？',
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
									url : window.BIZCTX_PATH + '/complaintjson/delComplaintInfo.action',
									method : 'POST',  
									params : "ids="+ jsonData,
									success : function(response) {
										Ext.Msg.alert('提示',"投诉删除成功");
										store.reload();
									},
									failure : function() {
										Ext.Msg.alert('提示',"投诉删除失败");
										store.reload();
									}
								});
					}
				});
			} else {
				Ext.Msg.alert('提示', '请选择要删除的投诉!');
			}
		return false;
	}
	
});

