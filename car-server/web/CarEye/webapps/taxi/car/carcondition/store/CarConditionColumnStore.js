Ext.define("CarConditionApp.store.CarConditionColumnStore",{
	extend:"Ext.data.Store",
	autoLoad:false,
	model:"CarConditionApp.model.CarConditionModel",
	pageSize: 31,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/reportformsjson/findCarConditionList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	},
	listeners: {
	    load: function(store,records,successful,eOpts ){
	    	if(records.length ==0){
	    		Ext.Msg.alert('提示',"没有找到相关数据记录");
	    	}else{
	    		row.length=0;
	    		col1.length=0;
	    		col2.length=0;
	    		col3.length=0;
	    		col4.length=0;
	    		for(var i=0;i<records.length;i++){
	    			row.push(records[i].data.createtime);
	    			col1.push(records[i].data.mileage);
	    			col2.push(records[i].data.oilwe);
	    			col3.push(records[i].data.faultnumber);
	    			col4.push(records[i].data.alarmnumber);
	    		}
	    		var control = Ext.create('CarConditionApp.controller.CarConditionCtrl');
	    		control.setecharts1(); //重新加载图表
	    		control.setecharts2(); 
	    		control.setecharts3(); 
	    		control.setecharts4(); 
	    	}
	    }
	  }
 });