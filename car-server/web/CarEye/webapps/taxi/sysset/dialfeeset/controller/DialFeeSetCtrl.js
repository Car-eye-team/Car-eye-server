Ext.define('DialFeeSetApp.controller.DialFeeSetCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['DialFeeSetListStore'],//声明该控制层要用到的store
    models: ['DialFeeSetModel'],//声明该控制层要用到的model
    views: ['DialFeeSetListView','DialFeeSetSetView'],
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'dialFeeSetListView',
            selector: 'dialFeeSetListView'
        },{
            ref: 'dialFeeSetListView',
            selector: 'dialFeeSetListView'
        }
    ],
    init: function() {
		this.control({
			'dialFeeSetSetView button[action=set]' : {
				click : this.setDialFeeSet
			},
//			'dialFeeSetListView button[action=search]' : {
//				click : this.searchDialFeeSet
//			},
			'dialFeeSetListView':{
                 render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var dialfee;
		var oilcost;
		var discount;
		var effecttime;
		
		Ext.Ajax.request({
				url : window.BIZCTX_PATH + '/dialfeesetjson/getDialfeeSetMaxId.action',
				method : 'POST', 
				timeout:500000,
				success : function(response) {
					var text = response.responseText;
					var obj = eval( "(" + text + ")" );//转换后的JSON对象
					var dialfeeSet = obj.result.dialfeeSet;
					dialfee=dialfeeSet.dialfee;
					oilcost=dialfeeSet.oilcost;
					discount=dialfeeSet.discount;
					effecttime=dialfeeSet.effecttime;
					Ext.getCmp('dianfee').setValue(dialfee);
					Ext.getCmp('oilcost').setValue(oilcost);
					Ext.getCmp('discount').setValue(discount);
					Ext.getCmp('effecttime').setValue(effecttime);
					
					var buttonArray = ['120801'];
					for(var i=0;i<buttonArray.length;i++){
						if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
							Ext.getCmp(buttonArray[i]).hide();
						}
					}
				}
		  });
		
		return false;
	},
	searchDialFeeSet : function(button){
		var store = this.getDialFeeSetListStoreStore();
		store.clearFilter(true);
		var dianfee=encodeURI(Ext.getCmp('dianfee').getValue());	
		
		store.on('beforeload', function (store, options) {
	            var new_params = { 
		            dianfee: dianfee
	            };
	            Ext.apply(store.proxy.extraParams, new_params);
	        });
	        store.loadPage(1);
	    return false;
	},
	setDialFeeSet : function(button){
			var store = this.getDialFeeSetListStoreStore();
			var dianfee=Ext.getCmp('dianfee').getValue();
			var oilcost=Ext.getCmp('oilcost').getValue();
			var discount=Ext.getCmp('discount').getValue();
		    var effecttime=Ext.getCmp('effecttime').getValue();
		    
		    if (dianfee.length<=0) {
		    	Ext.Msg.alert('提示',"电召费不能为空!");
                return;
            }else{
            	if(isNaN(dianfee)){
            		Ext.Msg.alert('提示',"电召费不是数字!");
            		return;
            	}
            }
            
            if (oilcost.length<=0) {
		    	Ext.Msg.alert('提示',"燃油费不能为空!");
                return;
            }else{
            	if(isNaN(oilcost)){
            		Ext.Msg.alert('提示',"燃油费不是数字!");
            		return;
            	}
            }
            
            if (discount.length<=0) {
		    	Ext.Msg.alert('提示',"合乘折扣率不能为空!");
                return;
            }else{
            	if(isNaN(discount)){
            		Ext.Msg.alert('提示',"合乘折扣率不是数字!");
            		return;
            	}
            }
            
            
			if (effecttime == null) {
				Ext.Msg.alert('提示',"生效时间不能为空!");
				return;
			}else{
//		         var str = CurentTime();
//                 if(Ext.util.Format.date(effecttime, 'Y-m-d H:i:s') <str){  //生效时间不能比系统时间小
//                   Ext.Msg.alert('提示',"生效时间不能比当前时间小!");
//                 	return;
//                 }
			}
			var myMask = new Ext.LoadMask(Ext.getBody(), {//也可以是Ext.getCmp('').getEl()窗口名称
					msg    : "正在操作,请稍后...",//你要写成Loading...也可以
					msgCls : 'z-index:10000;'
			});
			myMask.show();
			Ext.Ajax.request({
								url : window.BIZCTX_PATH + '/dialfeesetjson/saveDialFeeSet.action',
								method : 'POST', 
								timeout:100000,
								params : {
									      dianfee:dianfee,
									      oilcost:oilcost,
									      discount:discount,
								          effecttime:encodeURI(Ext.util.Format.date(effecttime, 'Y-m-d H:i:s'))
										 },
										success : function(response) {
										var text = response.responseText;
										 var obj = eval( "(" + text + ")" );//转换后的JSON对象
					   					 var su = obj.result.su;
										 myMask.hide();
										 if (su == -1) {
											Ext.Msg.alert('提示',"设置失败");
										 }else {
											Ext.Msg.alert('提示',"设置成功");
											store.clearFilter(true);
											store.loadPage(1); 
											//store.load(); 
											//清空
											//Ext.getCmp('typeid').setValue('');
										}
							   			},
										failure : function() {
											Ext.Msg.alert('提示',"设置失败");
										}
		  });
	}
			
});
function CurentTime()
    { 
        var now = new Date();
        
        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日
        
        var hh = now.getHours();            //时
        var mm = now.getMinutes();          //分
        var ss = now.getSeconds();           //秒
        
        var clock = year + "-";
        
        if(month < 10)
            clock += "0";
        
        clock += month + "-";
        
        if(day < 10)
            clock += "0";
            
        clock += day + " ";
        
        if(hh < 10)
            clock += "0";
            
        clock += hh + ":";
        if (mm < 10) clock += '0'; 
        clock += mm + ":"; 
         
        if (ss < 10) clock += '0'; 
        clock += ss; 
        return(clock); 
}
