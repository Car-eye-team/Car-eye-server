Ext.define('GoodsFindApp.view.SearchDetailView',{
    extend:'Ext.window.Window',
    alias: 'widget.searchDetailView',
	title : '车辆详细信息',
    width : 700,
    id:'searchDetailView',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	modal : true,
	closeAction : 'destroy',
	border:false,
	items :[ {
		xtype : 'form',
		frame : true,
		anchor : '100%',
		collapsible : false,
	"items": [
	          {
	              "xtype": "fieldset",
	              "title": "<b><font style='color:green'>车辆详细信息 </font></b>",
	              fieldDefaults: {
	              	buttonAlign : 'left',
		            labelAlign : 'right',
	  	        	labelWidth: 80
	  	   		 },
	  		   items : [{
	  			layout : 'column',
	  			columnWidth : 1,
	  			border:false,
	              items : [{
	  						columnWidth : .32,
	  						border:false,
	  						items : [{
								xtype : 'displayfield',
								fieldLabel : '车牌号码',
								id : 'carnumber2'
							},{
								xtype : 'displayfield',
								fieldLabel : '终端设备号',
								id : 'terminal2'
							}]
	  				}, {
	  					columnWidth : .34,
	  					border:false,
	  					items : [{
								xtype : 'displayfield',
								fieldLabel : '主驾驶员姓名',
								id : 'drivernameone2'
							},{
								xtype : 'displayfield',
								fieldLabel : '主驾驶手机',
								id : 'phoneone2'
							}]
	  					},{
	  						columnWidth : .34,
	  						border:false,
	  						items : [{
								xtype : 'displayfield',
								fieldLabel : '当班司机姓名',
								id : 'drivername2'
							},{
								xtype : 'displayfield',
								fieldLabel : '企业',
								id : 'blocname2'
							}]}]
	                }]
	          }
	      ]},{
	      	xtype : 'carHistoryPositionView'
	      }]
});
