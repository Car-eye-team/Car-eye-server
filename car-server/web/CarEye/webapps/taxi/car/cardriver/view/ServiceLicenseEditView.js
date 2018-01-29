Ext.define('CarDriverApp.view.ServiceLicenseEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.serviceLicenseEditView',
	title : '修改服务证信息',
    width : 500,
	layout : 'form',
	itemId :'serviceLicenseEditView',
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
//	              "title": "<b><font style='color:green'>服务证信息 </font></b>",
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
								id : 'id',
								name : 'serviceLicense.id'
							},{
	  							xtype : 'hidden',
								id : 'driverid',
								name : 'serviceLicense.driverid'
							},{
								xtype : 'hidden',
								id : 'picturepath',
								name : 'serviceLicense.picturepath'
							},{
								xtype : 'textfield',
								fieldLabel : '姓名',
								itemId : 'name',
								id : 'name',
								name : 'serviceLicense.name',
								width :　210,
								anchor : '100%',
								cls : 'x-textfield',
								allowBlank : true
							},{
								xtype : 'textfield',
								fieldLabel : '服务证号',
								itemId : 'servicenumber',
								id : 'servicenumber',
								name : 'serviceLicense.servicenumber',
								width :　210,
								allowBlank : true
							},{
								xtype : 'combo',
								width : 210,
								fieldLabel : '车牌号',
								id:'carid',
								name : 'serviceLicense.carid',
								itemId:'carid',
								store : 'CarListStore',
								displayField : 'carnumber',
								valueField : 'carid',
								allowBlank : true
							},{
								xtype : 'textfield',
								fieldLabel : '星级',
								itemId : 'starlevel',
								id : 'starlevel',
								name : 'serviceLicense.starlevel',
								width :　210,
								allowBlank : true
							},{
								xtype : 'datefield',
								fieldLabel : '登记日期',
								format:"Y-m-d",
								id : 'registtime',
								maxValue : new Date(),
								name : 'serviceLicense.registtime',
								width :　210,
								anchor : '100%',
								allowBlank : true,
								cls : 'x-textfield'
							},{
								xtype : 'datefield',
								fieldLabel : '发证日期',
								format:"Y-m-d",
								id : 'fztime',
								maxValue : new Date(),
								name : 'serviceLicense.fztime',
								width :　210,
								anchor : '100%',
								allowBlank : true,
								cls : 'x-textfield'
							}]
	  				}, {
	  					columnWidth : .50,
	  					border:false,
	  					items : [{
								xtype : 'textfield',
								fieldLabel : '有效期(年)',
								itemId : 'validity',
								id : 'validity',
								name : 'serviceLicense.validity',
								width :　210,
								allowBlank : true
							},{
								xtype : 'textfield',
								fieldLabel : '版本',
								itemId : 'version',
								id : 'version',
								name : 'serviceLicense.version',
								width :　210,
								allowBlank : true
							},{  
								xtype : 'combo',
								fieldLabel : '证件状态',
								itemId : 'zjstatus',
								id : 'zjstatus',
								name : 'serviceLicense.zjstatus',
								width :　210,
								store : 'NowStatusStore',
								editable: false,
								valueField : 'id',
								displayField : 'nowstatus',
								blankText : '',
								allowBlank : true,
								cls : 'x-textfield'
							},{
								xtype : "fieldcontainer",
								width :　210,
								layout : {
									type : "hbox"
								},
								items : [{
											xtype : 'textfield',
											fieldLabel : '上传图片',
											name : 'serviceLicense.downloadaddr',
											itemId : 'downloadaddr',
											id : 'downloadaddr',
											anchor : '100%',
											width : 160,
											editable : true,
											allowBlank : true,
											validator : vd
										}, {
											xtype : 'filefield',
											width : 38,
											buttonText : "请选择",
											name : 'file_Input',
											id : 'file_Input'
										}]
							},{
								xtype : 'fieldcontainer',
								border : false,
								html : '<span id="fileQueue"></span>'
	                }]
	 
	                
	          }
	      ]}
	      ]}
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