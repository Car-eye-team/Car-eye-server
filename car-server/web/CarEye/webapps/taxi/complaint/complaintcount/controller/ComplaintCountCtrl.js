Ext.define('ComplaintCountApp.controller.ComplaintCountCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['ComplaintCountListStore','SearchTypeStore','YearStore','CarListStore','ComplaintCountDetailsStore'
    ,'CarPageListStore'],//声明该控制层要用到的store
    models: ['ComplaintCountModel'],//声明该控制层要用到的model
    views: ['ComplaintCountListView','ComplaintCountSearchView'],
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'complaintCountListView',
            selector: 'complaintCountListView'
        },{
            ref: 'complaintCountSearchView',
            selector: 'complaintCountSearchView'
        }
    ],
    init: function() {
		this.control({
			'complaintCountListView button[action=export]' : {
				click : this.exportExcel
			},
			'complaintCountListView button[action=exportWord]' : {
				click : this.exportWord
			},
			'complaintCountSearchView button[action=search]' : {
				click : this.search
			},
			'complaintCountSearchView #blocid' : {
				select : this.loadDeptCar
			},
			'complaintCountSearchView #carnumber' : {
				change : this.loadCar
			},
			'complaintCountSearchView #c_search_type' : {
				select : this.loadSearchType
			},
			'complaintCountSearchView' : {
				afterrender : this.loadSearchType
			},
			'complaintCountListView':{
                 render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
//		var buttonArray = ['121101','121102','121103'];
//		for(var i=0;i<buttonArray.length;i++){
//			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
//				Ext.getCmp(buttonArray[i]).hide();
//			}
//		}
		return false;
	},
	loadDeptCar : function() {
		Ext.getCmp('c_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var deptid = Ext.getCmp('c_blocid').getValue();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
			var new_params = {
				deptid : deptid,
				carnumber : ""
			};
			Ext.apply(store.proxy.extraParams, new_params);
		});
		store.reload();
	},
	loadCar : function(){
		var store = this.getCarPageListStoreStore();
		var carnumber = Ext.getCmp('c_carnumber').getRawValue();
    	if(admin.deptid == 1){
    		if(carnumber.length >=3 ){
				store.clearFilter(true);
				store.on('beforeload', function (store, options) {
		            var new_params = { 
		            	carnumber: encodeURI(carnumber)
		            };
		            Ext.apply(store.proxy.extraParams, new_params);
		        });
		        store.reload();
    		}
    	}else{
			store.clearFilter(true);
			store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	carnumber: encodeURI(carnumber)
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.reload();
	    }
		
	},
	loadSearchType : function(){
		var searchtype = Ext.getCmp('c_search_type').getValue();
		var month = Ext.getCmp('c_month');
		var year = Ext.getCmp('c_year');
		if(parseInt(searchtype)==1){
			year.hide();
			month.show();
			
			var myDate = new Date();
			//将日期设置为当前时间
			var mon=myDate.getMonth()+1;
			if(mon!=11&&mon!=12){
				mon="0"+mon;
			}
			Ext.getCmp('c_month').setValue(myDate.getFullYear()+'-'+mon);
			Ext.getCmp('c_year').setValue("");
		}else if(parseInt(searchtype)==2){
			year.show();
			month.hide();
			var myDate = new Date();
			//将日期设置为当前时间
			Ext.getCmp('c_year').setValue(myDate.getFullYear());
			Ext.getCmp('c_month').setValue("");
		}else{ 
			Ext.getCmp('c_search_type').setValue('1')
			year.hide();
			month.show();
			
			var myDate = new Date();
			//将日期设置为当前时间
			var mon=myDate.getMonth()+1;
			if(mon!=11&&mon!=12){
				mon="0"+mon;
			}
			Ext.getCmp('c_month').setValue(myDate.getFullYear()+'-'+mon);
			Ext.getCmp('c_year').setValue("");
		}
		return false;
	},
	search : function(button){
		var blocid = Ext.getCmp('c_blocid').getValue();
		var carnumber = Ext.getCmp('c_carnumber').getValue();
		var searchtype = Ext.getCmp('c_search_type').getValue();
		var month =Ext.util.Format.date(Ext.getCmp('c_month').getValue(), 'Y-m');
		var year = Ext.util.Format.date(Ext.getCmp('c_year').getValue(), 'Y');
	    if(searchtype == null || searchtype == ""){
	    	Ext.Msg.alert('提示','请选择统计方式！');
	    	return ;
	    }
	    if(blocid == null || blocid == ""){
	    	Ext.Msg.alert('提示','请选择企业！');
	    	return ;
	    }
	    if(parseInt(searchtype)==1){
		  if(month == null || month == ""){
	    	Ext.Msg.alert('提示','请选择日期！');
	    	return ;
	       }
		}else if(parseInt(searchtype)==2){
		  if(year == null || year == ""){
	    	Ext.Msg.alert('提示','请选择年份！');
	    	return ;
	       }
		}
		
		Ext.getCmp('tmp_blocid').setValue(blocid);
		Ext.getCmp('tmp_carnumber').setValue(carnumber);
		
		var store = this.getComplaintCountListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
	            	blocid: encodeURI(blocid),
	            	carnumber: encodeURI(carnumber),
		            year: encodeURI(year),
            		month : encodeURI(month)
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1);
	        
	    return false;
	},
	exportExcel : function(button){
		var blocid = Ext.getCmp('c_blocid').getValue();
		var carnumber = Ext.getCmp('c_carnumber').getValue();
		var searchtype = Ext.getCmp('c_search_type').getValue();
		var month =Ext.util.Format.date(Ext.getCmp('c_month').getValue(), 'Y-m');
		var year = Ext.util.Format.date(Ext.getCmp('c_year').getValue(), 'Y');
	    if(searchtype == null || searchtype == ""){
	    	Ext.Msg.alert('提示','请选择统计方式！');
	    	return ;
	    }
	    if(blocid == null || blocid == ""){
	    	Ext.Msg.alert('提示','请选择企业！');
	    	return ;
	    }
	    if(parseInt(searchtype)==1){
		  if(month == null || month == ""){
	    	Ext.Msg.alert('提示','请选择日期！');
	    	return ;
	       }
		}else if(parseInt(searchtype)==2){
		  if(year == null || year == ""){
	    	Ext.Msg.alert('提示','请选择年份！');
	    	return ;
	       }
		}
        
        var strParams="blocid="+blocid+"&carnumber="+carnumber+"&month="+month+"&year="+year;
	    location.href=window.BIZCTX_PATH + '/complaintjson/exportExcelCount.action?'+strParams;
	},
	exportWord : function(button){
		var blocid = Ext.getCmp('c_blocid').getValue();
		var carnumber = Ext.getCmp('c_carnumber').getValue();
		var searchtype = Ext.getCmp('c_search_type').getValue();
		var month =Ext.util.Format.date(Ext.getCmp('c_month').getValue(), 'Y-m');
		var year = Ext.util.Format.date(Ext.getCmp('c_year').getValue(), 'Y');
	    if(searchtype == null || searchtype == ""){
	    	Ext.Msg.alert('提示','请选择统计方式！');
	    	return ;
	    }
	    if(blocid == null || blocid == ""){
	    	Ext.Msg.alert('提示','请选择企业！');
	    	return ;
	    }
	    if(parseInt(searchtype)==1){
		  if(month == null || month == ""){
	    	Ext.Msg.alert('提示','请选择日期！');
	    	return ;
	       }
		}else if(parseInt(searchtype)==2){
		  if(year == null || year == ""){
	    	Ext.Msg.alert('提示','请选择年份！');
	    	return ;
	       }
		}
        
        var strParams="blocid="+blocid+"&carnumber="+carnumber+"&month="+month+"&year="+year;
	    location.href=window.BIZCTX_PATH + '/complaintjson/exportWord.action?'+strParams;
	}
	
});

