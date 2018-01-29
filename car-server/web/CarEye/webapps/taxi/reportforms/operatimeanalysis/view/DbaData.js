Ext.define('OtaApp.view.DbaData',{
			extend:'Ext.panel.Panel',
			alias:'widget.dbaData',
			header : false,
            border:false,
            layout: 'fit',
            autoScroll:true,
            html:'<div id="echart1" style="width:99%;height:300px;float:left;"></div>' +
            		'<div id="echart2" style="width:99%;height:300px;float:left;"></div>'
		}
);