Ext.define('RideOrderApp.view.PassengerTripWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.passengerTripWindow',
	title : '顺风车乘客详情',
    width : 700,
    height:500,
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	closeAction : 'destroy',
	border:false,
	items :[ {
		xtype : 'form',
		frame : true,
		id:'passengerTripWindow',
		anchor : '100%',
		collapsible : false,
		"items": [{
	              "xtype": "fieldset",
	              "title": "<b><font style='color:green'>乘客信息 </font></b>",
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
		  						columnWidth : .33,
		  						border:false,	  						
		  						items : [{
									xtype : 'displayfield',
									fieldLabel : '乘客姓名',
									id : 'passagename'
								},{
									xtype : 'displayfield',
									fieldLabel : '上车地点',
									id : 'saddress'
								},{
									xtype : 'displayfield',
									fieldLabel : '上车时间',
									id : 'stime'
								}]
		  						}, {
			  					columnWidth : .33,
			  					border:false,
			  					items : [{
									xtype : 'displayfield',
									fieldLabel : '乘客电话',
									id : 'phone'
								},{
									xtype : 'displayfield',
									fieldLabel : '下车地点',
									id : 'eaddress'
								},{
									xtype : 'displayfield',
									fieldLabel : '下车时间',
									id : 'etime'
								}]
			  					},{
			  						columnWidth : .33,
			  						border:false,	  						
			  						items : [{
										xtype : 'displayfield',
										fieldLabel : '车牌号',
										id : 'carnumber'
									},{
										xtype : 'displayfield',
										fieldLabel : '订单号',
										id : 'orderid'
									},{
										xtype : 'displayfield',
										fieldLabel : '拼车序号',
										id : 'seq'
									}]
			  						}
		  					]
	                }]
	          },{
	              "xtype": "fieldset",
	              "title": "<b><font style='color:green'>地图信息</font></b>",
	              fieldDefaults: {
	              	buttonAlign : 'left',
		            labelAlign : 'right',
	  	        	labelWidth: 80
	  	   		 },
	  		   items : [{
	  			layout : 'column',
	  			columnWidth : 1,
	  			height:450,
	  			border:false,
	              items : [{
	  					columnWidth : 1,
	  					border:false,
	  					items : [
	  						{xtype:'trackMapView'} 
							]
	  					
	  					}]
	                }]
	      }]
	        
	}]
	
});

	  							
