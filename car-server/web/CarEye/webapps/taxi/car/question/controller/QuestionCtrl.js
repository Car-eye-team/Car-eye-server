Ext.define('QuestionApp.controller.QuestionCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['QuestionListStore','CarInfoListStore','AnswerListStore','SendRecordListStore','CarNumberStore'
    	,'CarPageListStore'],//声明该控制层要用到的store
    models: [],//声明该控制层要用到的model
    views: ['QuestionListView','CarInfoListView','AnswerListView'],//声明该控制层要用到的view
    init: function() {
    	
		this.control({
			'questionListView button[action=add]' : {
				click : this.addQuestion
			},
			'questionListView button[action=delete]' : {
				click : this.deleteQuestion
			},
			'questionListView button[action=edit]' : {
				click : this.editQuestion
			},
			'questionListView button[action=searchques]' : {
				click : this.searchQuestion
			},
			'questionAddView button[action=save]' : {
				click : this.saveQuestion
			},
			'questionAddView button[action=quit]' : {
				click : this.quitQuestion
			},
			'questionEditView button[action=save]' : {
				click : this.saveQuestion
			},
			'questionEditView button[action=quit]' : {
				click : this.quitQuestion
			},
			'carInfoListView button[action=send]' : {
				click : this.sendQuestion
			},
			'carInfoListView button[action=searchcar]' : {
				click : this.searchCarInfo
			},
			'carInfoListView button[action=searchsendrecord]' : {
				click : this.lookSendRecord
			},
			'sendRecordListView button[action=searchsend]' : {
				click : this.searchSendRecord
			},
			'answerListView button[action=add]' : {
				click : this.addAnswer
			},
			'answerAddView button[action=save]' : {
				click : this.saveAnswer
			},
			'answerListView button[action=edit]' : {
				click : this.editAnswer
			},
			'answerEditView button[action=save]' : {
				click : this.saveAnswer
			},
			'answerListView button[action=delete]' : {
				click : this.deleteAnswer
			},
			'answerListView':{
                render : this.buttonAccess
            },
			'carInfoListView':{
                render : this.button1Access
            },
			'carInfoListView #carnumber' : {
				change : this.loadCar
			},
			'sendRecordListView #s_carnumber' : {
				change : this.loadCar2
			},
			'carInfoListView #blocid' : {
				select : this.loadDeptCar
			}
		});
	},
	
	
	loadDeptCar : function(){
		Ext.getCmp('c_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var deptid = Ext.getCmp('c_blocid').getValue();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	deptid: deptid,
            	carnumber: ""
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	loadCar2 : function(){
		var store = this.getCarNumberStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	carnumber: encodeURI(Ext.getCmp('s_carnumber').getValue())
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	buttonAccess : function() {
		var buttonArray = ['14040601','14040602','14040603'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	button1Access : function() {
		var buttonArray = ['14040604','14040605'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	loadCar : function(){
		var store = this.getCarNumberStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	carnumber: encodeURI(Ext.getCmp('c_carnumber').getValue())
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	lookSendRecord : function(button){
//		var view = Ext.widget('sendRecordListView');
		var view = Ext.create('QuestionApp.view.SendRecordListView');
		view.show();
		var store = this.getSendRecordListStoreStore();
		store.load();
		return false;
	},
	addQuestion : function(button) {
		var menuid = button.id+"";
//		var view = Ext.widget('questionAddView');
		var view = Ext.create('QuestionApp.view.QuestionAddView');
		view.show();
		//清除缓存
		//view.down('grid').getView().dataSource = null;
		var store = this.getAnswerListStoreStore();
		store.removeAll(true);
		return false;
	},
	editAnswer : function (button){
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
//		var view = Ext.widget('answerEditView');
		var view = Ext.create('QuestionApp.view.AnswerEditView');
		view.show();
		view.down('form').loadRecord(records[0]);
		return false;
	},
	addAnswer : function() {
//		var view = Ext.widget('answerAddView');
		var view = Ext.create('QuestionApp.view.AnswerAddView');
		view.show();
		return false;
	},
	searchQuestion : function(button) {
		var store = this.getQuestionListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	content: encodeURI(Ext.getCmp('c_content').getValue())
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	searchSendRecord : function(button) {
		var stime = Ext.util.Format.date(Ext.getCmp('s_stime').getValue(), 'Y-m-d H:i:s');
        var etime = Ext.util.Format.date(Ext.getCmp('s_etime').getValue(), 'Y-m-d H:i:s');
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
		
		var store = this.getSendRecordListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('s_carnumber').getValue()),
	            	content: encodeURI(Ext.getCmp('s_content').getValue()),
        			stime : encodeURI(Ext.util.Format.date(Ext.getCmp('s_stime').getValue(), 'Y-m-d H:i:s')),
        			etime : encodeURI(Ext.util.Format.date(Ext.getCmp('s_etime').getValue(), 'Y-m-d H:i:s'))
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	searchCarInfo : function(button) {
		var store = this.getCarInfoListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(Ext.getCmp('c_carnumber').getValue()),
	            	terminal: encodeURI(Ext.getCmp('c_terminal').getValue()),
	            	blocid: Ext.getCmp('c_blocid').getValue()
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	saveQuestion : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getQuestionListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			Ext.MessageBox.wait('请稍候...', '提示');
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/questionjson/saveQuestionInfo.action',
				method : 'post',
				success : function(form, action) {
					Ext.MessageBox.updateProgress(1);
				    Ext.MessageBox.close();
					var resp = action.result.result;
					var su = resp.su;
					win.close();
					Ext.Msg.alert("提示","操作信息成功!");
					store.reload();
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

	saveAnswer : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getAnswerListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			Ext.MessageBox.wait('请稍候...', '提示');
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/questionjson/saveAnswerInfo.action',
				method : 'post',
				success : function(form, action) {
						var resp = action.result.result;
						var su = resp.su;
						 if (su == -1) {
							win.close();
							Ext.Msg.alert("提示","系统异常,请与管理员联系!");
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
	sendQuestion : function(button){
		var menuid = button.id+"";
	    var quesgrid = Ext.getCmp('questiongrid');
	    var cargrid = Ext.getCmp('carinfogrid');
		var quesdata = quesgrid.getSelectionModel().getSelection();
		var cardata = cargrid.getSelectionModel().getSelection();
		
		var quesstore = quesgrid.getStore();
		var carstore = cargrid.getStore();
		if(cardata.length<=0||quesdata.length<=0){
			Ext.Msg.alert('提示', '下发问题与车辆信息都至少选一条!');
			return false;
		}
		if (cardata.length>0) {
			Ext.MessageBox.confirm('提示','你确认下发问题？',
			function(btn) {
				if (btn == "yes") {
					var ca = cargrid.getSelectionModel().getSelection();
					var cjsonData = "";
					for ( var i = 0, len = ca.length; i < len; i++) {
						var ss = ca[i].get("carid");
						if (i == 0) {
							cjsonData = cjsonData + ss;
						} else {
							cjsonData = cjsonData + "," + ss;
						}
					}
					var te = quesgrid.getSelectionModel().getSelection();
					var tjsonData = "";
					for ( var i = 0, len = te.length; i < len; i++) {
						var ss = te[i].get("id");
						if (i == 0) {
							tjsonData = tjsonData + ss;
						} else {
							tjsonData = tjsonData + "," + ss;
						}
					}
					
					Ext.Ajax.request( {
								url : window.BIZCTX_PATH + '/questionjson/CheckQuestion.action?ques='+tjsonData,
								method : 'POST',  
								params : "ids="+ cjsonData,
								success : function(response) {
								var ques = response.responseText;
								 var obj = eval( "(" + ques + ")" );//转换后的JSON对象
			   					 var su = obj.result.su;
								 if (su == -1) {
									Ext.Msg.alert('提示',"问题下发失败");
								}else {
									Ext.Msg.alert('提示',"问题下发成功");
								}
					   			},
								failure : function() {
									Ext.Msg.alert('提示',"问题下发失败");
								}
							});
				}
				});
			}
		return false;
	
	
	},
	
	quitQuestion : function(button){
		var win = button.up('window');
			Ext.Ajax.request( {
				url : window.BIZCTX_PATH + '/questionjson/deleteAnswer.action',
				method : 'POST',  
				success : function(response) {
				  win.close();
				},
				failure : function() {
				  win.close();
				}
			});
	
	},
	deleteQuestion : function(button) {
		var menuid = button.id+"";
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
								url : window.BIZCTX_PATH + '/questionjson/deleteQuestionInfo.action',
								method : 'POST',  
								params : "ids="+ jsonData,
								success : function(response) {
									Ext.Msg.alert('提示',"数据删除成功");
									store.reload();
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
	deleteAnswer : function(button) {
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
							var ss = m[i].get("answerid");
							if (i == 0) {
								jsonData = jsonData + ss;
							} else {
								jsonData = jsonData + "," + ss;
							}
						}
						Ext.Ajax.request( {
									url : window.BIZCTX_PATH + '/questionjson/deleteAnswerInfo.action',
									method : 'POST',  
									params : "ids="+ jsonData,
									success : function(response) {
										Ext.Msg.alert('提示',"数据删除成功");
										store.on('beforeload', function (store, options) {
							            var new_params = { 
							            	questionid: null
							            };
							            Ext.apply(store.proxy.extraParams, new_params);
								        });
								        store.reload();
									},
									failure : function() {
										Ext.Msg.alert('提示',"数据删除失败");
									}
								});
					}
				});
			} else {
				Ext.Msg.alert('提示', '请选择要删除的数据项!');
			}
		return false;
	},
	
	editQuestion : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		
		var storeAnswer = this.getAnswerListStoreStore();
		//编辑之前把问题答案添加一份到临时记录
	    Ext.Ajax.request( {
				url : window.BIZCTX_PATH + '/questionjson/insertLinAnswer.action',
				method : 'POST',  
				timeout : 15000,
				params : "questionid="+ records[0].get("id"),
				success : function(response) {
					storeAnswer.reload();
				},
				failure : function() {
					storeAnswer.reload();
				}
			});
		
//		var view = Ext.widget('questionEditView');
		var view = Ext.create('QuestionApp.view.QuestionEditView');
		view.show();
		var store = this.getQuestionListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		return false;
	}
	
});

