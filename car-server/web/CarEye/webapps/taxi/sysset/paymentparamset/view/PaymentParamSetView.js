Ext.define('PaymentParamSetApp.view.PaymentParamSetView', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.paymentParamSetView',
			split : true,
			frame: false,
			title:'付款参数',
			region : "east",
			width : "68%",
			layout : 'form',
			autoScroll:true,
			modal : true,
			border : false,
		items :[ {
		xtype : 'form',
		id : 'parameterSet_Form',
		frame : false,
		border : false,
		anchor : '100%',
		collapsible : false,
	"items": [
	          {
	              "xtype": "fieldset",
	              "title": "<b><font style='color:green'>微信公众号参数</font></b>",
	              fieldDefaults: {
	              	buttonAlign : 'left',
		            labelAlign : 'right',
	  	        	labelWidth: 80
	  	   		 },
	  	items :[ {
	  	xtype : 'form',
		border : false,
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults : {
			labelAlign : 'right',
			labelWidth : 90,
			width : 330
//			margin : '20 0 10 50'
			},   		 
	  		   items : [{
	  			layout : 'column',
	  			columnWidth : 1,
	  			border:false,
	              items : [{
	  						columnWidth : .5,
	  						border:false,
	  						margin : '0 0 0 0',
	  						items : [{
	  							xtype : 'hidden',
								name : 'paymentParamSet.id',
								id:'s_id',
								value: null
							},{
								xtype : 'textfield',
								fieldLabel : 'appsecret',
								name : 'paymentParamSet.appsecret',
								id:'appsecret',
								anchor : '100%',
								maxLength : 128
							},{
								xtype : 'textfield',
								fieldLabel : 'appid',
								name : 'paymentParamSet.appid',
								id:'appid',
								anchor : '100%',
								maxLength : 128
							},{
								xtype : 'textfield',
								fieldLabel : 'mchid',
								name : 'paymentParamSet.mchid',
								id:'mchid',
								anchor : '100%',
								maxLength : 128
							},{ 
								xtype : 'textfield',
								fieldLabel : 'key',
								name : 'paymentParamSet.key',
								id:'key',
								anchor : '100%',
								maxLength : 128
							}]
	  				}, {
	  						columnWidth : .5,
	  						border:false,
	  						margin : '0 0 0 0',
	  						items : [{
	  							xtype : 'textfield',
								fieldLabel : 'token',
								name : 'paymentParamSet.token',
								id:'token',
								anchor : '100%',
								maxLength : 128
							},{
								xtype : 'textfield',
								fieldLabel : 'encodingaeskey',
								name : 'paymentParamSet.encodingaeskey',
								id:'encodingaeskey',
								anchor : '100%',
								maxLength : 128
							},{
								xtype : 'textfield',
								fieldLabel : '证书路径',
								name : 'paymentParamSet.apiclientpath',
								id:'apiclientpath',
								anchor : '100%',
								maxLength : 128
							}]
						}]
						}]
	          }
	      ]
		},{
	              "xtype": "fieldset",
	              "title": "<b><font style='color:green'>手机微信公众号参数</font></b>",
	              fieldDefaults: {
	              	buttonAlign : 'left',
		            labelAlign : 'right',
	  	        	labelWidth: 80
	  	   		 },
	  	items :[ {
	  	xtype : 'form',
		border : false,
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults : {
			labelAlign : 'right',
			labelWidth : 90,
			width : 330
//			margin : '20 0 10 50'
			},   		 
	  		   items : [{
	  			layout : 'column',
	  			columnWidth : 1,
	  			border:false,
	              items : [{
	  						columnWidth : .5,
	  						border:false,
	  						margin : '0 0 0 0',
	  						items : [{
								xtype : 'textfield',
								fieldLabel : 'appid',
								name : 'paymentParamSet.app_appid',
								id:'app_appid',
								anchor : '100%',
								maxLength : 128
							},{
								xtype : 'textfield',
								fieldLabel : 'mchid',
								name : 'paymentParamSet.app_mchid',
								id:'app_mchid',
								anchor : '100%',
								maxLength : 128
							}]
	  				}, {
	  						columnWidth : .5,
	  						border:false,
	  						margin : '0 0 0 0',
	  						items : [{
	  							xtype : 'textfield',
								fieldLabel : 'key',
								name : 'paymentParamSet.app_key',
								id:'app_key',
								anchor : '100%',
								maxLength : 128
							}]
						}]
					}]
	          }
	      ]
	   },{
	              "xtype": "fieldset",
	              "title": "<b><font style='color:green'>支付宝参数</font></b>",
	              fieldDefaults: {
	              	buttonAlign : 'left',
		            labelAlign : 'right',
	  	        	labelWidth: 80
	  	   		 },
	  	items :[ {
	  	xtype : 'form',
		border : false,
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults : {
			labelAlign : 'right',
			labelWidth : 90,
			width : 330
//			margin : '20 0 10 50'
			},   		 
	  		   items : [{
	  			layout : 'column',
	  			columnWidth : 1,
	  			border:false,
	              items : [{
	  						columnWidth : .5,
	  						border:false,
	  						margin : '0 0 0 0',
	  						items : [{
								xtype : 'textfield',
								fieldLabel : '订单标题',
								name : 'paymentParamSet.subject',
								id:'subject',
								anchor : '100%',
								maxLength : 128
							}]
	  				}, {
	  						columnWidth : .5,
	  						border:false,
	  						margin : '0 0 0 0',
	  						items : [{
	  							xtype : 'textfield',
								fieldLabel : '配置文件路径',
								name : 'paymentParamSet.alipay_config_path',
								id:'alipay_config_path',
								anchor : '100%',
								maxLength : 128
							}]
						}]
					}]
	          }
	      ]
	      },{
							xtype : 'button',
							margin : '10 0 5 350',
							action : 'paramset',
							id:'paymentparamset',
							text : '设置'
	          }]
	      }]
		});