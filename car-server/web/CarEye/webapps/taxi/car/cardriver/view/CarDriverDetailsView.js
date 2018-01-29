Ext.define('CarDriverApp.view.CarDriverDetailsView', {
	extend : 'Ext.window.Window',
	alias : 'widget.carDriverDetailsView',
	title : '驾驶员详细信息',
    width : 900,
	layout : 'fit',
	animCollapse:false,
	constrain : true,  //true则强制此window控制在viewport，默认为false
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	modal : true,  //modal:true为模式窗口，后面的内容都不能操作，默认为false
	border : false,
	closeAction : 'destroy',
	items :[ {
		xtype : 'form',
		frame : true,
		anchor : '100%',
		collapsible : false,
	"items": [
	          {
	              "xtype": "fieldset",
	              "title": "<b><font style='color:green'>基本信息 </font></b>",
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
	  						columnWidth : .25,
	  						border:false,
	  						items : [{
	  							xtype : 'hidden',
								name : 'carDriver.id',
								itemId : 'id',
								id : 'id'
							},{
								xtype : 'hidden',
								name : 'carDriver.driverid',
								itemId : 'driverid',
								id : 'driverid'
							},{
								xtype : 'comboboxtree',
								fieldLabel : '<font color="red">*</font>企业',
								name : 'carDriver.blocid',
								id : 'blocid',
								width :　210,
								store: Ext.create('Ext.data.TreeStore', {  
							        autoLoad : 'true',
							        fields: ['text','id','parentId'], 
									root : {expanded : true,text : '企业' },
									proxy: {
										 type: 'ajax',
										 url: window.BIZCTX_PATH + '/servlet/DeptTree?type=200', 
										 reader: {
											 type: 'json'
										 }
									}
							    }) ,
							    rootVisible: false,
								editable: true,
								allowBlank : false,
								readOnly : true,
								blankText : '请选择',
								cls : 'x-textfield',
								valueField: 'id', 
								displayField: 'text',
								listeners: {
							        change: {
							            element: 'el', 
							            fn: function(){ 
							            	var store = Ext.getCmp('blocid').store;
											store.clearFilter(true);
											store.on('beforeload', function (store, options) {
									            var new_params = { 
									            	blocname: encodeURI(Ext.getCmp('blocid').getRawValue())
									            };
									            Ext.apply(store.proxy.extraParams, new_params);
									        });
									        store.reload(); 
							            }
							        }
								 }
							},{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>姓名',
								name : 'carDriver.drivername',
								itemId : 'drivername',
								id : 'drivername',
								anchor : '100%',
								width :　210,
								cls : 'x-textfield',
								allowBlank : false,
								readOnly : true,
								blankText : '不能为空',
								validator:vd,
								regex : /^([\u4E00-\u9FA5]+|[a-zA-Z]+)$/,
								regexText : '只能输入2~10个字母或汉字的名字!',
								maxLength : 10,
								minLength:2,
								minLengthText : '最小长度不小于2位!',
								maxLengthText : '最大长度不超过10位!'
							},{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>身份证号',
								name : 'carDriver.idnumber',
								itemId : 'idnumber',
								id : 'idnumber',
								regex : /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
								regexText : '身份证号格式不正确',
								anchor : '100%',
								width :　210,
								cls : 'x-textfield',
								allowBlank : false,
								readOnly : true,
								listeners: {"blur":function (){//添加监听
										if(Ext.getCmp('drivingnumber').getValue() == null || Ext.getCmp('drivingnumber').getValue() == '')
										Ext.getCmp('drivingnumber').setValue(Ext.getCmp('idnumber').getValue());
									}
								}
							},{
								xtype : 'combo',
								fieldLabel : '<font color="red">*</font>性别',
								itemId : 'sex',
								id : 'sex',
								name : 'carDriver.sex',
								anchor : '100%',
								width :　210,
								store : 'SexStore',
								editable: false,
								readOnly : true,
								valueField : 'id',
								displayField : 'sex',
								value:'1'
							}]
	  				}, {
	  					columnWidth : .25,
	  					border:false,
	  					items : [{
								xtype : 'combo',
								width : 210,
								fieldLabel : '<font color="red">*</font>车牌号',
								id:'carid',
								name : 'carDriver.carid',
								itemId:'carid',
								store : 'CarListStore',
								displayField : 'carnumber',
								valueField : 'carid',
								readOnly : true,
								allowBlank : false
							},{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>手机号',
								name : 'carDriver.phone',
								itemId : 'phone',
								id : 'phone',
								width :　210,
								anchor : '100%',
								cls : 'x-textfield',
								minLength : 11,
								minLengthText : '长度为11为数字!',
								maxLength : 11,
								maxLengthText : '长度为11为数字!',
								allowBlank : false,
								readOnly : true,
								blankText : '不能为空',
								regex : /^[1][358][0-9]{9}$/,
								regexText : '手机号码不合法'
							},{
								xtype : 'datefield',
								fieldLabel : '出生日期',
								format:"Y-m-d",
								itemId : 'birthday',
								maxValue : new Date(),
								id : 'birthday',
								name : 'carDriver.birthday',
								anchor : '100%',
								width :　210,
								editable: false,
								allowBlank : true,
								readOnly : true,
								cls : 'x-textfield'
							}]
	  					},{
	  						columnWidth : .25,
	  						border:false,
	  						items : [{
								xtype : 'combo',
								fieldLabel : '文化程度',
								itemId : 'education',
								id : 'education',
								name : 'carDriver.education',
								width :　210,
								store : 'EducationStore',
								editable: false,
								valueField : 'id',
								displayField : 'education',
								blankText : '',
								allowBlank : true,
								readOnly : true,
								cls : 'x-textfield'
							},{
								xtype : 'textfield',
								fieldLabel : '民族',
								itemId : 'nation',
								id : 'nation',
								name : 'carDriver.nation',
								width :　210,
								readOnly : true,
								allowBlank : true
							},{
								xtype : 'textfield',
								fieldLabel : '司机代码',
								itemId : 'drivercode',
								id : 'drivercode',
								name : 'carDriver.drivercode',
								width :　210,
								readOnly : true,
								allowBlank : true
							}]
						},{
	  						columnWidth : .25,
	  						border:false,
	  						items : [{
	  								xtype : 'combo',
									fieldLabel : '政治面貌',
									itemId : 'political',
									id : 'political',
									name : 'carDriver.political',
									width :　210,
									store : 'PoliticalStore',
									editable: false,
									valueField : 'id',
									displayField : 'political',
									blankText : '',
									readOnly : true,
									allowBlank : true,
									cls : 'x-textfield'
								}, {
						            xtype : 'combo',
									fieldLabel : '当前状态',
									itemId : 'nowstatus',
									id : 'nowstatus',
									name : 'carDriver.nowstatus',
									width :　210,
									store : 'NowStatusStore',
									editable: false,
									valueField : 'id',
									displayField : 'nowstatus',
									blankText : '',
									readOnly : true,
									allowBlank : true,
									cls : 'x-textfield'
								},{
									xtype : 'textarea',
									fieldLabel : '家庭住址',
									name : 'carDriver.addr',
									readOnly : true,
									width :　210,
									rows :2,
									anchor : '100%',
									itemId : 'addr',
									id : 'addr',
									minLength : 2,
									minLengthText : '最小长度不小于2位!',
									maxLength : 251,
									maxLengthText : '最大长度不超过251位!',
									cls : 'x-textfield'
							}]
						}]
	                }]
	          },
	           {
	              "xtype": "fieldset",
	              "title": "<b><font style='color:green'>驾驶证信息 </font></b>",
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
	  						columnWidth : .25,
	  						border:false,
	  						items : [{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>驾驶证号',
								name : 'carDriver.drivingnumber',
								itemId : 'drivingnumber',
								id : 'drivingnumber',
								width :　210,
								anchor : '100%',
								cls : 'x-textfield',
								readOnly : true,
								allowBlank : false,
								blankText : '驾驶证号不能为空',
								maxLength : 20,
								minLength:2,
								minLengthText : '最小长度不小于2位!',
								maxLengthText : '最大长度不超过20位!',
								validator : vd
							} ,{
								xtype : 'textfield',
								fieldLabel : '准驾车型',
								itemId : 'zjcartype',
								id : 'zjcartype',
								name : 'carDriver.zjcartype',
								width :　210,
								readOnly : true,
								allowBlank : true
							}]
	  				}, {
	  					columnWidth : .25,
	  					border:false,
	  					items : [{
								xtype : 'textfield',
								fieldLabel : '驾驶证有效期(年)',
								itemId : 'validity',
								id : 'validity',
								name : 'carDriver.validity',
								width :　210,
								readOnly : true,
								allowBlank : true
							} ,{
								xtype : 'datefield',
								fieldLabel : '初次领证日期',
								format:"Y-m-d",
								id : 'firstlztime',
								maxValue : new Date(),
								name : 'carDriver.firstlztime',
								width :　210,
								anchor : '100%',
								readOnly : true,
								allowBlank : true,
								cls : 'x-textfield'
							}]
	  					},{
	  						columnWidth : .25,
	  						border:false,
	  						items : [{
								xtype : 'datefield',
								fieldLabel : '发证日期',
								format:"Y-m-d",
								id : 'fztime',
								maxValue : new Date(),
								name : 'carDriver.fztime',
								width :　210,
								anchor : '100%',
								readOnly : true,
								allowBlank : true,
								cls : 'x-textfield'
							} ,{
								xtype : 'textarea',
								fieldLabel : '备注',
								name : 'carDriver.remark',
								readOnly : true,
								rows :1,
								anchor : '100%',
								itemId : 'remark',
								id : 'remark',
								width :　210,
								maxLength : 512,
								maxLengthText : '最大长度不超过512位!',
								cls : 'x-textfield'
							}]}]
	                }]
			 },
	           {
	              "xtype": "fieldset",
	              "title": "<b><font style='color:green'>岗前考试信息 </font></b>",
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
	  						columnWidth : .25,
	  						border:false,
	  						items : [{
								xtype : 'textfield',
								fieldLabel : '合格证号',
								itemId : 'qualifiednumber',
								id : 'qualifiednumber',
								name : 'carDriver.qualifiednumber',
								width :　210,
								anchor : '100%',
								cls : 'x-textfield',
								readOnly : true,
								allowBlank : true
							},{
								xtype : 'datefield',
								fieldLabel : '合格证有效截止日期',
								format:"Y-m-d",
								id : 'closetime',
								maxValue : new Date(),
								name : 'carDriver.closetime',
								width :　210,
								anchor : '100%',
								readOnly : true,
								allowBlank : true,
								cls : 'x-textfield'
							}]
	  				}, {
	  					columnWidth : .25,
	  					border:false,
	  					items : [{
	  							xtype : 'textfield',
								fieldLabel : '培训编号',
								itemId : 'trainnumber',
								id : 'trainnumber',
								name : 'carDriver.trainnumber',
								width :　210,
								allowBlank : true
	  						},{
								xtype : 'textfield',
								fieldLabel : '考试证编号',
								itemId : 'examnumber',
								id : 'examnumber',
								name : 'carDriver.examnumber',
								width :　210,
								readOnly : true,
								allowBlank : true
							}]
					}, {
	  					columnWidth : .25,
	  					border:false,
	  					items : [{
								xtype : 'combo',
								fieldLabel : '结业状态',
								itemId : 'completestatus',
								id : 'completestatus',
								name : 'carDriver.completestatus',
								width :　210,
								anchor : '100%',
								store : 'CompleteStatusStore',
								editable: false,
								valueField : 'id',
								displayField : 'completestatus',
								cls : 'x-textfield',
								readOnly : true,
								allowBlank : true
							},{  
								xtype : 'combo',
								fieldLabel : '考试状态',
								itemId : 'examstatus',
								id : 'examstatus',
								name : 'carDriver.examstatus',
								width :　210,
								anchor : '100%',
								store : 'ExamStatusStore',
								editable: false,
								valueField : 'id',
								displayField : 'examstatus',
								cls : 'x-textfield',
								readOnly : true,
								allowBlank : true
							}]
	  					}, {
		  					columnWidth : .25,
		  					border:false,
		  					items : [{
								xtype : 'textfield',
								fieldLabel : '培训期数',
								itemId : 'trainexpect',
								id : 'trainexpect',
								name : 'carDriver.trainexpect',
								width :　210,
								readOnly : true,
								allowBlank : true
							},{
								xtype : 'datefield',
								fieldLabel : '考试证获取日期',
								format:"Y-m-d",
								id : 'gaintime',
								maxValue : new Date(),
								name : 'carDriver.gaintime',
								width :　210,
								anchor : '100%',
								readOnly : true,
								allowBlank : true,
								cls : 'x-textfield'
							}]
		  					}]
	                }]
	              },
	           {
	              "xtype": "fieldset",
	              "title": "<b><font style='color:green'>服务证信息 </font></b>",
	              fieldDefaults: {
	              	buttonAlign : 'left',
		            labelAlign : 'right',
	  	        	labelWidth: 80
	  	   		 },
	  		   items : [{
	  			layout : 'column',
	  			columnWidth : 1,
	  			border:false,
	              items : [ {
						columnWidth : .25,
	  					border:false,
	  					items : [{
								xtype : 'displayfield',
								fieldLabel : '图片预览',
								itemId : 'picture',
								id : 'picture',
								width :　210,
								readOnly : true,
								allowBlank : true
	                }]
	 
	                
	          },{
	  						columnWidth : .25,
	  						border:false,
	  						items : [{
								xtype : 'textfield',
								fieldLabel : '姓名',
								itemId : 'sname',
								id : 'sname',
								name : 'carDriver.sname',
								width :　210,
								anchor : '100%',
								cls : 'x-textfield',
								readOnly : true,
								allowBlank : true
							},{
								xtype : 'textfield',
								fieldLabel : '服务证号',
								itemId : 'sservicenumber',
								id : 'sservicenumber',
								name : 'carDriver.sservicenumber',
								width :　210,
								readOnly : true,
								allowBlank : true
							},{
								xtype : 'combo',
								width : 210,
								fieldLabel : '车牌号',
								id:'scarid',
								name : 'carDriver.scarid',
								itemId:'carid',
								store : 'CarListStore',
								displayField : 'carnumber',
								valueField : 'carid',
								readOnly:true,
								allowBlank : true
							}]
	  				}, {
	  					columnWidth : .25,
	  					border:false,
	  					items : [{
	  							xtype : 'datefield',
								fieldLabel : '登记日期',
								format:"Y-m-d",
								id : 'sregisttime',
								maxValue : new Date(),
								name : 'carDriver.sregisttime',
								width :　210,
								anchor : '100%',
								readOnly : true,
								allowBlank : true,
								cls : 'x-textfield'
							},{
								xtype : 'datefield',
								fieldLabel : '发证日期',
								format:"Y-m-d",
								id : 'sfztime',
								maxValue : new Date(),
								name : 'carDriver.sfztime',
								width :　210,
								anchor : '100%',
								readOnly : true,
								allowBlank : true,
								cls : 'x-textfield'
	  						},{
								xtype : 'textfield',
								fieldLabel : '有效期(年)',
								itemId : 'svalidity',
								id : 'svalidity',
								name : 'carDriver.svalidity',
								width :　210,
								readOnly : true,
								allowBlank : true
							}]
	                }, {
	  					columnWidth : .25,
	  					border:false,
	  					items : [{
							xtype : 'combo',
							width : 210,
							fieldLabel : '星级',
							id:'sstarlevel',
							name : 'carDriver.sstarlevel',
							itemId:'starlevel',
							store : 'StarlevelStore',
							displayField : 'starlevel',
							valueField : 'id',
							readOnly : true,
							allowBlank : true
							
						},{
								xtype : 'textfield',
								fieldLabel : '版本',
								itemId : 'sversion',
								id : 'sversion',
								name : 'carDriver.sversion',
								width :　210,
								readOnly : true,
								allowBlank : true
	               		 },{
								xtype : 'combo',
								fieldLabel : '证件状态',
								itemId : 'szjstatus',
								id : 'szjstatus',
								name : 'carDriver.szjstatus',
								width :　210,
								store : 'NowStatusStore',
								editable: false,
								valueField : 'id',
								displayField : 'nowstatus',
								blankText : '',
								readOnly : true,
								allowBlank : true,
								cls : 'x-textfield'
							}]
	                }
	      ]}
	      ]
	          }
	      ]}]
});