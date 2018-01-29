Ext.define('EvaluateCountApp.controller.EvaluateCountCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['SearchTypeStore','YearStore','CarListStore'
             ,'EvaluateCountListStore','EvaluateCountDetailsStore','CarPageListStore'
             ],//声明该控制层要用到的store
    models: [
             ],//声明该控制层要用到的model
    views: ['EvaluateCountSearchView','EvaluateCountListView'],//声明该控制层要用到的view
//    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
//        {
//            ref: 'driverEvaluationSearchView',
//            selector: 'driverEvaluationSearchView' 
//        },{
//            ref: 'driverEvaluationListView',
//            selector: 'driverEvaluationListView'
//        }
//    ],
    init: function() {
    	
		this.control({
			'evaluateCountSearchView #deptid' : {
				select : this.loadDeptCar
			},
			'evaluateCountSearchView #carnumber' : {
				change : this.loadCar
			},
			'evaluateCountSearchView #ec_search_type' : {
				select : this.loadWeChatStatementSearch
			},
	        'evaluateCountSearchView' : {
				afterrender : this.loadWeChatStatementSearch
			},
	        'evaluateCountListView button[action=export]' : {
				click : this.exportExcel
			},
	        'evaluateCountListView button[action=word]' : {
				click : this.exportWord
			}
			
			
		});
	},
	loadDeptCar : function() {
		Ext.getCmp('ec_carnumber').setValue("");
		var store = this.getCarPageListStoreStore();
		var deptid = Ext.getCmp('al_deptid').getValue();
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
		var carnumber = Ext.getCmp('ec_carnumber').getRawValue();
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
	loadWeChatStatementSearch : function(){
		var searchtype = Ext.getCmp('ec_search_type').getValue();
		var month = Ext.getCmp('ec_month');
		var year = Ext.getCmp('ec_year');
		if(parseInt(searchtype)==1){
			year.hide();
			month.show();
			
			var myDate = new Date();
			//将日期设置为当前时间
			var mon=myDate.getMonth()+1;
			if(mon!=11&&mon!=12){
				mon="0"+mon;
			}
			Ext.getCmp('ec_month').setValue(myDate.getFullYear()+'-'+mon);
			Ext.getCmp('ec_year').setValue("");
		}else if(parseInt(searchtype)==2){
			year.show();
			month.hide();
			var myDate = new Date();
			//将日期设置为当前时间
			Ext.getCmp('ec_year').setValue(myDate.getFullYear());
			Ext.getCmp('ec_month').setValue("");
		}else{ 
			Ext.getCmp('ec_search_type').setValue('1')
			year.hide();
			month.show();
			
			var myDate = new Date();
			//将日期设置为当前时间
			var mon=myDate.getMonth()+1;
			if(mon!=11&&mon!=12){
				mon="0"+mon;
			}
			Ext.getCmp('ec_month').setValue(myDate.getFullYear()+'-'+mon);
			Ext.getCmp('ec_year').setValue("");
		}
		return false;
	},
	searchWeChatStatement : function(button){
		var blocid = Ext.getCmp('al_deptid').getValue();
		var carnumber =Ext.getCmp('ec_carnumber').getValue();
		var searchtype = Ext.getCmp('ec_search_type').getValue();
		var month =Ext.util.Format.date(Ext.getCmp('ec_month').getValue(), 'Y-m');
		var year = Ext.util.Format.date(Ext.getCmp('ec_year').getValue(), 'Y');
	    if(searchtype == null || searchtype == ""){
	    	Ext.Msg.alert('提示','请选择统计方式！');
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
		
		var store = this.getEvaluateCountListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
	            var new_params = { 
		            year: encodeURI(year),
            		month : encodeURI(month),
            		blocid : encodeURI(blocid),
            		carnumber:encodeURI(carnumber)
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1);
	    return false;
	},
	exportExcel : function (button){
		var searchtype = Ext.getCmp('ec_search_type').getValue();
		var month =Ext.util.Format.date(Ext.getCmp('ec_month').getValue(), 'Y-m');
		var year = Ext.util.Format.date(Ext.getCmp('ec_year').getValue(), 'Y');
	    if(searchtype == null || searchtype == ""){
	    	Ext.Msg.alert('提示','请选择统计方式！');
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
        	
		    var carnumber = encodeURI(Ext.getCmp('ec_carnumber').getValue());
		    var blocid = encodeURI(Ext.getCmp('al_deptid').getValue());
            
		    var strPrams="carnumber="+carnumber+"&blocid="+blocid+
		                 "&month="+month+"&year="+year+"&searchtype="+searchtype
        	location.href= window.BIZCTX_PATH + "/evaluatecountjson/exportExcel.action?"+strPrams;
	},
	
	exportWord : function (button){
		var searchtype = Ext.getCmp('ec_search_type').getValue();
		var month =Ext.util.Format.date(Ext.getCmp('ec_month').getValue(), 'Y-m');
		var year = Ext.util.Format.date(Ext.getCmp('ec_year').getValue(), 'Y');
	    if(searchtype == null || searchtype == ""){
	    	Ext.Msg.alert('提示','请选择统计方式！');
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
        	
		    var carnumber = encodeURI(Ext.getCmp('ec_carnumber').getValue());
		    var blocid = encodeURI(Ext.getCmp('al_deptid').getValue());
            
		    var strPrams="carnumber="+carnumber+"&blocid="+blocid+
		                 "&month="+month+"&year="+year+"&searchtype="+searchtype;
        	location.href= window.BIZCTX_PATH + "/evaluatecountjson/exportWord.action?"+strPrams;
	}
	
	
	
});

