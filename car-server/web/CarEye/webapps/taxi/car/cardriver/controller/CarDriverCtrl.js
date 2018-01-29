Ext.define('CarDriverApp.controller.CarDriverCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['CarDriverListStore','SexStore','EducationStore','NowStatusStore','PoliticalStore','CarListStore',
    			'CompleteStatusStore','ExamStatusStore','StarlevelStore','CarPageListStore'],//声明该控制层要用到的store
    models: ['CarDriverModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['CarDriverSearchView','SpaceView','CarDriverListView','CarDriverAddView','CarDriverEditView','CarDriverDetailsView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'carDriverSearchView',
            selector: 'carDriverSearchView'
        },{
            ref: 'spaceView',
            selector: 'spaceView'
        },{
            ref: 'carDriverListView',
            selector: 'carDriverListView'
        },{
            ref: 'carDriverAddView',
            selector: 'carDriverAddView'
        },{
            ref: 'carDriverEditView',
            selector: 'carDriverEditView'
        }
    ],
    init: function() {
    	
		this.control({
			'carDriverListView button[action=add]' : {
				click : this.addCarDriver
			},
			'carDriverListView button[action=delete]' : {
				click : this.deleteCarDriver
			},
			'carDriverListView button[action=edit]' : {
				click : this.editCarDriver
			},
			'carDriverListView button[action=export]' : {
				click : this.exportInfo
			},
			'carDriverSearchView button[action=search]' : {
				click : this.searchCarDriver
			},
			'carDriverAddView button[action=save]' : {
				click : this.saveCarDriver
			},
			'carDriverEditView button[action=save]' : {
				click : this.saveCarDriver
			},
			'carDriverListView':{
                render : this.buttonAccess
            },
            'preJobExamAddView button[action=save]' : {
				click : this.saveExam
			},
			'preJobExamEditView button[action=save]' : {
				click : this.saveExam
			},
			 'serviceLicenseAddView button[action=save]' : {
				click : this.saveLicense
			},
			 'serviceLicenseEditView button[action=save]' : {
				click : this.saveLicense
			},
			'carDriverAddView #blocid' : {
				select : this.loadDeptCar
			}
			,
			'carDriverEditView #blocid' : {
				select : this.loadDeptCar
			}
		});
	},
	
	
	loadDeptCar : function(){
		Ext.getCmp('carid').setValue("");
		var store = this.getCarListStoreStore();
		var deptid = Ext.getCmp('blocid').getValue();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	deptid: deptid
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
	},
	
	
	buttonAccess : function() {
		var buttonArray = ['130201','130202','130203','130203'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	addCarDriver : function(button) {
		var view = Ext.widget('carDriverAddView');
		view.show();
		return false;
	},
	detailsCarDriver : function(id) {
				var store = this.getCarDriverListStoreStore();
				var data = store.getById(id);
			
				var view = Ext.widget('carDriverDetailsView');
//				var view = Ext.create('TripApp.view.CarTripWindow');
				view.show();
			    
				view.down('form').loadRecord(data);
				
	Ext.Ajax.request( {
		url: window.BIZCTX_PATH + '/cardriverjson/findPreJobExamByDriverid.action', //请求的服务器地址
	      params:{ 
	      		driverid : id
	      },
			method : 'POST',  
			timeout : 5000,
			success : function(response) {
				 var action = Ext.JSON.decode(response.responseText);
				 var data = action.result.data;
				 
					Ext.getCmp('qualifiednumber').setValue(data.qualifiednumber);
					Ext.getCmp('closetime').setValue(data.closetime);
					Ext.getCmp('trainexpect').setValue(data.trainexpect);
					Ext.getCmp('trainnumber').setValue(data.trainnumber);
					Ext.getCmp('examnumber').setValue(data.examnumber);
					Ext.getCmp('gaintime').setValue(data.gaintime);
					if(data.completestatus == null){
						Ext.getCmp('completestatus').setValue('');
					}else{
						Ext.getCmp('completestatus').setValue(data.completestatus+'');
					}
					if(data.examstatus == null){
						Ext.getCmp('examstatus').setValue('');
					}else{
						Ext.getCmp('examstatus').setValue(data.examstatus+'');
					}
			},
			failure : function() {
			}
	    });
	    Ext.Ajax.request( {
		url: window.BIZCTX_PATH + '/cardriverjson/findServiceLicenseByDriverid.action', //请求的服务器地址
	      params:{ 
	      		driverid : id
	      },
			method : 'POST',  
			timeout : 5000,
			success : function(response) {
				 var action = Ext.JSON.decode(response.responseText);
				 var data = action.result.data;
					Ext.getCmp('sname').setValue(data.name);
					Ext.getCmp('sservicenumber').setValue(data.servicenumber);
					Ext.getCmp('scarid').setValue(data.carid);
					Ext.getCmp('sstarlevel').setValue(data.starlevel);
					Ext.getCmp('sregisttime').setValue(data.registtime);
					Ext.getCmp('sfztime').setValue(data.fztime);
					Ext.getCmp('svalidity').setValue(data.validity);
					Ext.getCmp('sversion').setValue(data.version);
					if(data.zjstatus == null){
						Ext.getCmp('szjstatus').setValue('');
					}else{
						Ext.getCmp('szjstatus').setValue(data.zjstatus+'');
					}
					if(data == null){
						Ext.getCmp('picture').setValue("<img src='cd.png' width='100px' height='80px'/>");
					}else{
						if(data.picturepath == null){
							Ext.getCmp('picture').setValue("<img src='cd.png' width='100px' height='80px'/>");
						}else{
							Ext.getCmp('picture').setValue("<img src='"+ $("#basepath").val() + data.picturepath +"' class='photosrc' width='100px' height='100px'/>");
//							Ext.getCmp('picturepath').setValue("<img src='"+ $("#basepath").val() + data.picturepath +"' class='photosrc' width='150px' height='200px'/>");
						}
					}
					
					
					
			},
			failure : function() {
			}
	    });
				return false;
	},
	searchCarDriver : function(button) {
		var store = this.getCarDriverListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	drivername: encodeURI(Ext.getCmp('cd_drivername').getValue()),
	            	carnumber: encodeURI(Ext.getCmp('cd_carnumber').getRawValue()),
	            	phone: encodeURI(Ext.getCmp('cd_phone').getValue()),
	            	sscno: null
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1); 
	        //store.reload();
	    return false;
	},
	exportInfo : function (button){
		var drivername= encodeURI(Ext.getCmp('cd_drivername').getValue());
	    var phone=encodeURI(Ext.getCmp('cd_phone').getValue());
	    var carnumber=encodeURI(Ext.getCmp('cd_carnumber').getRawValue());
	    var sscno= null;
	    var strParams="&drivername="+drivername+"&phone="+phone+"&sscno="+sscno+"&carnumber="+carnumber;
	        
        location.href=window.BIZCTX_PATH + '/cardriverjson/exportExcel.action?'+strParams;
	},
	createLicense : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var driverid = records[0].get('id');
		var drivername = records[0].get('drivername');
		var view = Ext.create('CarDriverApp.view.ServiceLicenseAddView');
		Ext.getCmp('picturepath').setValue("<img src='cd.png' width='150px' height='200px'/>");
		view.show();
		Ext.Ajax.request( {
		url: window.BIZCTX_PATH + '/cardriverjson/findServiceLicenseByDriverid.action', //请求的服务器地址
	      params:{ 
	      		driverid : driverid
	      },
			method : 'POST',  
			timeout : 5000,
			success : function(response) {
				 var action = Ext.JSON.decode(response.responseText);
				 var data = action.result.data;
				 
	//			 var carid =  action.result.data.carid;
				
					
				 var su = action.result.su;
				 if(su == 1){
//				 	var view = Ext.create('CarDriverApp.view.ServiceLicenseAddView');
//					view.show();
					Ext.getCmp('driverid').setValue(driverid);
					Ext.getCmp('carid').setValue(records[0].get('carid'));
					Ext.getCmp('name').setValue(drivername);
						
				 }else if(su == 2){
				 	Ext.getCmp('serviceLicenseAddView').setTitle("修改服务证信息");
//				 	var view = Ext.create('CarDriverApp.view.ServiceLicenseEditView');
//					view.show();
					Ext.getCmp('id').setValue(data.id);
					Ext.getCmp('driverid').setValue(data.driverid);
					Ext.getCmp('downloadaddr').setValue(data.picturepath);
					Ext.getCmp('name').setValue(data.name);
					Ext.getCmp('servicenumber').setValue(data.servicenumber);
					Ext.getCmp('carid').setValue(records[0].get('carid'));
					Ext.getCmp('starlevel').setValue(data.starlevel);
					Ext.getCmp('registtime').setValue(data.registtime);
					Ext.getCmp('fztime').setValue(data.fztime);
					Ext.getCmp('validity').setValue(data.validity);
					Ext.getCmp('version').setValue(data.version);
					if(data.zjstatus == null){
						Ext.getCmp('zjstatus').setValue('');
					}else{
						Ext.getCmp('zjstatus').setValue(data.zjstatus+'');
					}
					
					if(data.picturepath == null){
						Ext.getCmp('picturepath').setValue("<img src='cd.png' width='150px' height='200px'/>");
					}else{
						Ext.getCmp('picturepath').setValue("<img src='"+ $("#basepath").val() + data.picturepath +"' class='photosrc' width='150px' height='200px'/>");
					}
//					document.getElementById('service_photo').src = window.BIZCTX_PATH + picturepath;
//					Ext.getCmp('picturepath').setValue("<img src='"+ $("#basepath").val() + data.picturepath +"' class='photosrc' width='150px' height='200px'/>");
				 }
			},
			failure : function() {
			}
	    });
	    this.uploadFile();
		return false;
	},
	saveLicense : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			if (!form.getForm().isValid()) {
				return;
			}
			var store = this.getCarDriverListStoreStore();
			
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/cardriverjson/saveServiceLicense.action',
				method : 'post',
				timeout:90,
				waitMsg:'正在保存,请稍候...',
				success : function(form, action) {
					//	var resp = action.result.result;
					//	var su = resp.su;
							win.close();
							Ext.Msg.alert("提示","保存成功!");
							
							store.reload();
				} ,
				failure : function(form, action) {
					win.close();
					Ext.Msg.alert('提示', "保存失败!");
					store.reload();
				}
			});
			return false;
	},
	createExam : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		var driverid = records[0].get('id');
		
		Ext.Ajax.request( {
		url: window.BIZCTX_PATH + '/cardriverjson/findPreJobExamByDriverid.action', //请求的服务器地址
	      params:{ 
	      		driverid : driverid
	      },
			method : 'POST',  
			timeout : 5000,
			success : function(response) {
				 var action = Ext.JSON.decode(response.responseText);
				 var data = action.result.data;
				 
	//			 var carid =  action.result.data.carid;
				 
				 var su = action.result.su;
				 if(su == 1){
				 	var view = Ext.create('CarDriverApp.view.PreJobExamAddView');
					view.show();
					Ext.getCmp('driverid').setValue(driverid);
				 }else if(su == 2){
				 	var view = Ext.create('CarDriverApp.view.PreJobExamEditView');
					view.show();
					Ext.getCmp('id').setValue(data.id);
					Ext.getCmp('driverid').setValue(data.driverid);
					Ext.getCmp('qualifiednumber').setValue(data.qualifiednumber);
					Ext.getCmp('closetime').setValue(data.closetime);
					Ext.getCmp('trainexpect').setValue(data.trainexpect);
					Ext.getCmp('trainnumber').setValue(data.trainnumber);
					Ext.getCmp('examnumber').setValue(data.examnumber);
					Ext.getCmp('gaintime').setValue(data.gaintime);
					if(data.completestatus == null){
						Ext.getCmp('completestatus').setValue('');
					}else{
						Ext.getCmp('completestatus').setValue(data.completestatus+'');
					}
					if(data.examstatus == null){
						Ext.getCmp('examstatus').setValue('');
					}else{
						Ext.getCmp('examstatus').setValue(data.examstatus+'');
					}
				 }
			},
			failure : function() {
			}
	    });
		return false;
	},
	saveExam : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			if (!form.getForm().isValid()) {
				return;
			}
			var store = this.getCarDriverListStoreStore();
			
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/cardriverjson/savePreJobExam.action',
				method : 'post',
				timeout:90,
				waitMsg:'正在保存,请稍候...',
				success : function(form, action) {
					//	var resp = action.result.result;
					//	var su = resp.su;
							win.close();
							Ext.Msg.alert("提示","保存成功!");
							
							store.reload();
				} ,
				failure : function(form, action) {
					win.close();
					Ext.Msg.alert('提示', "保存失败!");
					store.reload();
				}
			});
			return false;
	},
	saveCarDriver : function(button) {
			var win = button.up('window');
			var form = win.down('form');
			var store = this.getCarDriverListStoreStore();
			if (!form.getForm().isValid()) {
				return;
			}
			var birthday = Ext.util.Format.date(Ext.getCmp('birthday').getValue(), 'Y-m-d');
			var beginTimes = birthday.substring(0,10).split('-');
			var birth = new Date(beginTimes[0], beginTimes[1]-1, beginTimes[2]);
			var now = new Date();
			if(birth - now > 0){
	    		Ext.Msg.alert("提示","生日不能大于当前时间");
	        	return;
	    	}
			Ext.MessageBox.wait('请稍候...', '提示');
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/cardriverjson/saveCarDriver.action',
				method : 'post',
				success : function(form, action) {
						Ext.MessageBox.updateProgress(1);
					    Ext.MessageBox.close();
						var resp = action.result.result;
						var su = resp.su;
						if (su == -2) {
							Ext.Msg.alert("提示","司机已存在，请更换名称!");
						}else if (su == -3) {
							Ext.Msg.alert("提示","司机代码已存在，请更换!");
						}else if (su == -1) {
							win.close();
							Ext.Msg.alert("提示","系统异常,请与管理员联系!");
						}else {
							win.close();
							Ext.Msg.alert("提示","操作司机信息成功!");
							store.reload();
						}
			    },
				failure : function(form, action) {
					Ext.MessageBox.updateProgress(1);
					Ext.MessageBox.close();
					win.close();
					Ext.Msg.alert('提示', "操作司机信息信息失败!");
					store.reload();
				}
			});
			return false;
	},
	
	deleteCarDriver : function(button) {
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
								url : window.BIZCTX_PATH + '/cardriverjson/deleteCarDriver.action',
								method : 'POST',  
								params : "ids="+ jsonData,
								success : function(response) {
									var text = response.responseText;
									 var obj = eval( "(" + text + ")" );//转换后的JSON对象
				   					 var su = obj.result.su;
									 if (su == -3) {
										Ext.Msg.alert('提示',"司机已被使用不能被删除！");
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
	editCarDriver : function(button) {
		var grid = button.up('grid');
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条记录编辑');
			return;
		}
		
		
		var view = Ext.widget('carDriverEditView');
		view.show();
		var store = this.getCarDriverListStoreStore();
		var data = store.getById(records[0].get('id'));
		
		
		view.down('form').loadRecord(data);
		return false;
	},
	queryCarDriver : function(button) {
		alert('功能开发中......');
	},
	uploadFile : function(){ 
//		debugger;
	   $('#fileInput').uploadify({   
		'uploader': window.BIZCTX_PATH + '/resource/js/uploadify/js/swf/uploadify.swf',
	   'script': window.BIZCTX_PATH + '/cardriverjson/serviceLicenseUpload.action',   //指定服务端处理类的入口
	   'folder': window.BIZCTX_PATH + '/upload',   
		'cancelImg': window.BIZCTX_PATH +'/resource/js/uploadify/images/cancel.png',
	   'fileDataName': 'fileInput', //和input的name属性值保持一致就好，Struts2就能处理了   
	   'queueID': 'fileQueue', 
	   'auto': true,//是否选取文件后自动上传   
	   'multi': false,//是否支持多文件上传
	   'simUploadLimit' : 50,//每次最大上传文件数  
	   'sizeLimit':1024*1024*1024*10,
	   'buttonImg': window.BIZCTX_PATH +'/resource/js/uploadify/images/upload.gif',//按钮上的文字   
	   'buttonText':'123456',
	   //'wmode':'transparent',
	   'width':'150', 
	   'height':'200',
	   'fileDesc': '*.bmp;*.bng;*.png;*.gif;*.jpg;*.jpeg;',
	   'fileExt': '*.bmp;*.bng;*.png;*.gif;*.jpg;*.jpeg;',
	   'displayData': 'percentage',//有speed和percentage两种，一个显示速度，一个显示完成百分比    
	   'onComplete': function (event, queueID, fileObj, response, data){   
		   Ext.getCmp('downloadaddr').setValue(response);
//		   alert(window.BASEPATH);
		   Ext.getCmp('picturepath').setValue("<img src='"+ window.BIZCTX_PATH + response +"' class='photosrc' width='150px' height='200px'/>");
//		   $("#showImg").append(
//				   "<div class='block'><div class='closeWrapper' /><img src='"+ window.BASEPATH + response +"' alt='产品图片' class='photosrc' /></div>"
//		   );
	   }  
	   });   
	}
	
});

