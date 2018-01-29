Ext.define('OtaApp.view.OtaListView',{
	extend : 'Ext.panel.Panel',
	alias : 'widget.otaListView',
	region: 'center',
	title: '营运分析数据图表',
	autoWidth:true,
	autoScroll:true,
	id:'otaList',
//    tbar : [{
//    	text:'Excel导出',
//        iconCls:'common-excel-icon',
//        handler: function(button){
//			var con = Ext.create("OtaApp.controller.OtaCtrl");
//			con.exportOta();
//		}
//    }],

    
         items:[
      	       {
              	xtype:'dbaData'
      	       }
//      	       ,
//      	       		{
//                 	xtype:'otaDataView'
//         	       }
      	     
              ]
         
});