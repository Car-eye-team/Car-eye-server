Ext.define('AlarmmusicSetApp.controller.AlarmmusicSetCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['AlarmmusicSetListStore'],//声明该控制层要用到的store
    models: ['AlarmmusicSetModel'],//声明该控制层要用到的model
    views: ['AlarmmusicSetListView','AlarmmusicSetSearchView','AlarmmusicSetAddView','AlarmmusicSetEditView'],
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'alarmmusicSetListView',
            selector: 'alarmmusicSetListView'
        },{
            ref: 'alarmmusicSetSearchView',
            selector: 'alarmmusicSetSearchView'
        },{
            ref: 'alarmmusicSetAddView',
            selector: 'alarmmusicSetAddView'
        },{
            ref: 'alarmmusicSetEditView',
            selector: 'alarmmusicSetEditView'
        }
    ],
    init: function() {
		this.control({
			'alarmmusicSetListView button[action=add]' : {
				click : this.addAlarmmusicSet
			},
			'alarmmusicSetListView button[action=edit]' : {
				click : this.editAlarmmusicSet
			},
			'alarmmusicSetListView button[action=delete]' : {
				click : this.deleteAlarmmusicSet
			},
			'alarmmusicSetSearchView button[action=search]' : {
				click : this.searchAlarmmusicSet
			},
			'alarmmusicSetAddView button[action=save]' : {
				click : this.saveAlarmmusicSet
			},
			'alarmmusicSetEditView button[action=save]' : {
				click : this.saveAlarmmusicSet
			},
			'alarmmusicSetListView':{
                 render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['12040201','12040202','12040203'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	searchAlarmmusicSet : function(button){
		var store = this.getAlarmmusicSetListStoreStore();
		store.clearFilter(true);
		var alarmname=encodeURI(Ext.getCmp('as_alarmname').getValue());	
		store.on('beforeload', function (store, options) {
	            var new_params = { 
		            alarmname: alarmname
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1);
	    return false;
	},
	editAlarmmusicSet : function(button){
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var view = Ext.widget('alarmmusicSetEditView');
		
		var store = this.getAlarmmusicSetListStoreStore();
		var data = store.getById(records[0].get('id'));
		view.down('form').loadRecord(data);
		
		view.show();
		this.uploadFile();
		return false;
	
	},
	saveAlarmmusicSet : function(button){
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getAlarmmusicSetListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/setjson/saveAlarmmusicSet.action',
				method : 'post',
				waitMsg : '正在操作,请稍候...',
				success : function(form, action) {
						var resp = action.result.result;
						var su = resp.su;
						if (su == -1) {
							Ext.Msg.alert("提示","此报警类型已存在，请更换!");
						}else if (su == -2) {
							win.close();
							Ext.Msg.alert("提示","系统异常,请与管理员联系!");
						}else if (su == -3) {
							win.close();
							Ext.Msg.alert("提示","此报警类型缩写已存在，请更换!");
						}else {
							win.close();
							Ext.Msg.alert("提示","报警类型设置成功!");
							store.reload();
						}
			    },
				failure : function(form, action) {
					Ext.MessageBox.updateProgress(1);
					Ext.MessageBox.close();
					win.close();
					Ext.Msg.alert('提示', "报警类型设置失败!");
					store.reload();
				}
			});
			return false;
	},
	addAlarmmusicSet : function(button){
		var view = Ext.widget('alarmmusicSetAddView');
		view.show();
		this.uploadFile();
		return false;
	},
	
	deleteAlarmmusicSet : function(button) {
			var grid = button.up('grid');
			var data = grid.getSelectionModel().getSelection();
			var store = grid.getStore();
			if (data.length>0) {
				Ext.MessageBox.confirm('提示','你确认要删除选中的报警类型？',
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
									url : window.BIZCTX_PATH + '/setjson/deleteAlarmType.action',
									method : 'POST',  
									params : "ids="+ jsonData,
									success : function(response) {
										Ext.Msg.alert('提示',"报警类型删除成功");
										store.reload();
									},
									failure : function() {
										Ext.Msg.alert('提示',"报警类型删除失败");
										store.reload();
									}
								});
					}
				});
			} else {
				Ext.Msg.alert('提示', '请选择要删除的报警类型!');
			}
		return false;
	},
	uploadFile : function(){
		$('#fileInput').uploadify({   
		   'uploader': window.BIZCTX_PATH + '/resource/js/uploadify/js/swf/uploadify.swf',
		   'script': window.BIZCTX_PATH + '/setjson/musicUpload.action',   //指定服务端处理类的入口
		   'folder': window.BIZCTX_PATH + '/upload',   
		   'cancelImg': window.BIZCTX_PATH +'/resource/js/uploadify/images/cancel.png',
		   'fileDataName': 'fileInput', //和input的name属性值保持一致就好，Struts2就能处理了   
		   'queueID': 'fileQueue', 
		   'auto': true,//是否选取文件后自动上传   
		   'multi': false,//是否支持多文件上传
		   'simUploadLimit' : 1,//每次最大上传文件数  
		   'sizeLimit':1024*1024*1024*10,
		   'buttonImg': window.BIZCTX_PATH +'/resource/js/uploadify/images/upload.gif',  
		   'width':'56',
		   'height':'15',
		   'fileDesc': '请选择wav、mp3、aac、voc文件',
		   'fileExt': '*.wav;*.mp3;*.aac;*.voc',
		   'displayData': 'percentage',//有speed和percentage两种，一个显示速度，一个显示完成百分比    
		   'onComplete': function (event, queueID, fileObj, response, data){
			   	Ext.getCmp('musicaddr').setValue(response.trim());
			   	Ext.Msg.alert('提示',"文件上传成功");
		   }  
	   }); 
	}
	
});

