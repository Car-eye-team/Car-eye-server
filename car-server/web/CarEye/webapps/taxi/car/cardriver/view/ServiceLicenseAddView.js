Ext.define('CarDriverApp.view.ServiceLicenseAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.serviceLicenseAddView',
	title : '新增服务证信息',
    width : 500,
	layout : 'form',
	itemId :'serviceLicenseAddView',
	id :'serviceLicenseAddView',
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
	  					height:270,
	  					items : [{
							xtype : 'displayfield',
							fieldLabel : '图片预览',
							itemId : 'picturepath',
							id : 'picturepath',
//							width :　210,
							anchor : '100%',
//							value:"window.BIZCTX_PATH+/taxi/car/cardriver/cd.png",
							allowBlank : true
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
											width : 155,
											editable : true,
											allowBlank : true,
											validator : vd
										}, {
											xtype : 'filefield',
											width : 45,
											buttonText : "请选择",
											name : 'fileInput',
											id : 'fileInput'
										}]
							},{
								xtype : 'fieldcontainer',
								border : false,
								html : '<span id="fileQueue"></span>'
							}]
	 
	                
	          },{
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
							}
//							,{
//	  							xtype : 'hidden',
//								id : 'picturepath',
//								name : 'serviceLicense.picturepath'
//							}
							,{
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
								readOnly:true,
								allowBlank : true
							},{
								xtype : 'combo',
								width : 210,
								fieldLabel : '星级',
								id:'starlevel',
								name : 'serviceLicense.starlevel',
								itemId:'starlevel',
								store : 'StarlevelStore',
								displayField : 'starlevel',
								valueField : 'id',
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
							},{
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