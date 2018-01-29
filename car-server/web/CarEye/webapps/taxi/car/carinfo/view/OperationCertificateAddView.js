Ext.define('CarInfoApp.view.OperationCertificateAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.operationCertificateAddView',
	title : '新增营运证信息',
    width : 500,
	layout : 'form',
	itemId :'operationCertificateAddView',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',

	items :[ {
		xtype : 'form',
		frame : true,
		anchor : '100%',
		collapsible : false,
//	"items": [
//	          {
//	              "xtype": "fieldset",
//	              "title": "<b><font style='color:green'>营运证信息 </font></b>",
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
	  						columnWidth : .50,
	  						border:false,
	  						items : [{
	  							xtype : 'hidden',
								id : 'carid',
								name : 'operateCertificate.carid'
							},{
								xtype : 'textfield',
								fieldLabel : '营运证号',
								itemId : 'operatenumber',
								id : 'operatenumber',
								name : 'operateCertificate.operatenumber',
								width :　210,
								anchor : '100%',
								cls : 'x-textfield',
								allowBlank : true
							},{
								xtype : 'combo',
								fieldLabel : '营运状态',
								itemId : 'operatestatus',
								id : 'operatestatus',
								name : 'operateCertificate.operatestatus',
								width :　210,
								anchor : '100%',
								store : 'OperateStatusStore',
								editable: false,
								valueField : 'id',
								displayField : 'operatestatus',
								cls : 'x-textfield',
								value:"1",
								allowBlank : true
							},{
								xtype : 'textfield',
								fieldLabel : '营运性质',
								itemId : 'operateproperty',
								id : 'operateproperty',
								name : 'operateCertificate.operateproperty',
								width :　210,
								allowBlank : true
							},{
								xtype : 'datefield',
								fieldLabel : '发证日期',
								format:"Y-m-d",
								id : 'licensetime',
								maxValue : new Date(),
								name : 'operateCertificate.licensetime',
								width :　210,
								anchor : '100%',
								allowBlank : true,
								cls : 'x-textfield'
							},{
								xtype : 'textfield',
								fieldLabel : '办证类别',
								itemId : 'certificatetype',
								id : 'certificatetype',
								name : 'operateCertificate.certificatetype',
								width :　210,
								allowBlank : true
							}]
	  				}, {
	  					columnWidth : .50,
	  					border:false,
	  					items : [{
	  							xtype : 'datefield',
								fieldLabel : '首次登记日期',
								format:"Y-m-d",
								id : 'firstregisttime',
								maxValue : new Date(),
								name : 'operateCertificate.firstregisttime',
								width :　210,
								anchor : '100%',
								allowBlank : true,
								cls : 'x-textfield'
							},{
								xtype : 'textfield',
								fieldLabel : '录入人',
								itemId : 'entryperson',
								id : 'entryperson',
								name : 'operateCertificate.entryperson',
								width :　210,
								allowBlank : true
							},{
								xtype : 'datefield',
								fieldLabel : '录入日期',
								format:"Y-m-d",
								id : 'entrytime',
								maxValue : new Date(),
								name : 'operateCertificate.entrytime',
								width :　210,
								anchor : '100%',
								allowBlank : true,
								cls : 'x-textfield'
							},{  
								xtype : 'textarea',
								fieldLabel : '备注',
								itemId : 'remark',
								id : 'remark',
								name : 'operateCertificate.remark',
								rows : 1,
								width :　210,
								allowBlank : true
							}]
	  					}]
	                }]
	 
	                
	          }
//	      ]}
	      ],
	buttons: [{
		text: '保存',
		action: 'save'
	},{
		text: '取消',
		handler: function(btn){
			btn.up('window').close();
		}
	}]
});