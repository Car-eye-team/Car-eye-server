Ext.define('CarParamApp.controller.CarParamCtrl', {
			extend : 'Ext.app.Controller',
			stores : ['CarInfoListStore','ParmTypeTreeStore','CarPageListStore'],
			models : ['CarInfoModel'],//声明该控制层要用到的model
			views : ['ParameterSetView', 'TypeListView','CarInfoListView'],
			refs : [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
					{
						ref : 'parameterSetView',
						selector : 'parameterSetView'
					}, {
						ref : 'carInfoListView',
						selector : 'carInfoListView'
					}],
			init : function() {
				this.control({
					'carInfoListView button[action=searchcar]' : {
						click : this.searchCarInfo
					},
					'parameterSetView button[action=paramquery]' : {
						click : this.paramquery
					},
					'parameterSetView button[action=paramset]' : {
						click : this.paramset
					},
					'carInfoListView #blocid' : {
						select : this.loadDeptCar
					}
				});
			},
			
			loadDeptCar : function(){
				Ext.getCmp('c_carnumber').setValue("");
				var store = this.getCarPageListStoreStore();
				var deptid = Ext.getCmp('c_deptid').getValue();
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
			paramquery : function(button) {
				var id = button.id.substring(2);
				var grid = Ext.getCmp('carinfogrid');
				var records = grid.getSelectionModel().getSelection();
				if(records.length != 1){
					Ext.Msg.alert('提示', '请选择一条车辆信息');
					return;
				}
				
				var type = 1;	
				if(id == "12"){
					type = 2;	
				}else if(id == "13"){
					type = 3;	
				}else if(id == "14"){
					type = 4;	
				}
				var form = Ext.getCmp('form_'+id);
				
				var carid = records[0].get('carid');
				
				var myMask = new Ext.LoadMask(Ext.getBody(), {
					msg : "正在查询终端参数，请稍后..."
				});
				myMask.show();
				
				form.getForm().load({
	        		  url: window.BIZCTX_PATH + '/terminalpositionjson/queryTerminalParm.action', //请求的服务器地址
				      params:{ 
				      		carid : carid
				      },
				      success : function(form, action) {
				      		myMask.hide();// 隐藏
				      	    var su = action.result.result.su;
						    if(su == 0){
						    	Ext.Msg.alert('提示',"终端没有返回参数信息");
						    }else if(su == 1){
						    	Ext.Msg.alert('提示',"终端参数查询成功!");
						    }else{
						    	Ext.Msg.alert('提示',"服务器错误!");
						    }
				      },
				      failure : function(form, action) {
				      	  myMask.hide();// 隐藏
				      	  Ext.Msg.alert('提示',"服务器错误!");
				      }
        	    });
				
				return false;
			},
			searchCarInfo : function(button) {
				var carnumber = Ext.getCmp('c_carnumber').getRawValue();
				var deptid = Ext.getCmp('c_deptid').getValue();
				var store = this.getCarInfoListStoreStore();
				store.clearFilter(true);
				store.on('beforeload', function(store, options) {
							var new_params = {
								carnumber : encodeURI(carnumber),
								blocid : deptid
							};
							Ext.apply(store.proxy.extraParams, new_params);
						});
				store.loadPage(1);
				return false;
			},
			paramset : function(button) {
				
				var id = button.id.substring(2);
				var type = 1;	//车辆参数设置
				if(id == "12"){
					type = 2;	//设备上传参数设置
				}else if(id == "13"){
					type = 3;	//设备应用参数设置
				}else if(id == "14"){
					type = 4;	//设备应用数据设置
				}
				
				var form = Ext.getCmp('form_'+id);
				if (!form.getForm().isValid()) {
					return;
				}
				
				var cargrid = Ext.getCmp('carinfogrid');
				var cardata = cargrid.getSelectionModel().getSelection();
				if (cardata.length == 0) {
					Ext.Msg.alert('提示', '请选择需要参数的车辆!');
					return false;
				}

				var carids = "";
				for (var i = 0, len = cardata.length; i < len; i++) {
					var ss = cardata[i].get("carid");
					if (i == 0) {
						carids = carids + ss;
					} else {
						carids = carids + "," + ss;
					}
				}
				
				var objectform = form.getForm().getValues();
				var items = [];
				for ( var p in objectform ){
				  if ( typeof ( objectform [ p ]) != " function " ){
				  		if(objectform[p].length > 0){
				  			var obj = {};
							obj.id = p.substring(10,p.length);
							obj.value = objectform[p];
							if(obj.value != null && obj.value != ""){
								items.push(obj);
							}
				  		}
				  } 
				}
				if(items.length == 0){
					Ext.Msg.alert('提示',"请填写需要设置的其它参数!");
					return;
				}
				var myMask = new Ext.LoadMask(Ext.getBody(), {
					msg : "正在设置参数，请稍后..."
				});
				myMask.show();
				
				Ext.Ajax.request( {
					url : window.BIZCTX_PATH + '/terminalpositionjson/terminalParmSet.action',
					method : 'POST',  
					timeout : 5000,
					params : {carids:carids,items:JSON.stringify(items)},
					success : function(response) {
						myMask.hide();// 隐藏
						Ext.Msg.alert('提示',"终端参数设置指令下发成功!");
					},
					failure : function() {
						Ext.Msg.alert('提示',"终端参数设置指令下发失败!");
					}
				});
			}

	});
