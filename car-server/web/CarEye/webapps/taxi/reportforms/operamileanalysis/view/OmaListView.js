Ext.define('OmaApp.view.OmaListView',{
	extend : 'Ext.panel.Panel',
	alias : 'widget.omaListView',
	region: 'center',
	title: '营运里程数据图表',
	autoWidth:true,
	autoScroll:true,
	
//    tbar : [{
//    	text:'Excel导出',
//        iconCls:'common-excel-icon',
//        handler: function(button){
//			var con = Ext.create("OmaApp.controller.OmaCtrl");
//			con.exportOma();
//		}
//    }],
       html:'<div id="echart1" style="width:99%;height:300px;float:left;"></div>' +
        	'<div id="echart2" style="width:99%;height:300px;float:left;"></div>' 
});