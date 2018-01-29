Ext.define("OnlineReportApp.store.OnlineReportListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"OnlineReportApp.model.OnlineReportModel",
//	pageSize: 50,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/onlinereportjson/getOnlineReportList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
//			totalProperty: 'result.totalCount'
		}
	},
	listeners: {
	    load: function(store,records,successful,eOpts ){
	    	if(records.length ==0){
	    		Ext.Msg.alert('提示',"没有找到相关数据记录");
	    	}else{
	    		col.length=0;
	    		for(var i=0;i<records.length;i++){
	    			col.push(records[i].data.addnumber);
	    			col.push(records[i].data.onlinenumber);
	    			col.push(records[i].data.unonlinenumber);
	    		}
	    		var control = Ext.create('OnlineReportApp.controller.OnlineReportCtrl');
	    		control.setecharts(); //重新加载图表
	    	}
	    }
	  }
 });