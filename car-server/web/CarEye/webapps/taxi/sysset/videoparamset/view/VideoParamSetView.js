Ext.define('VideoParamSetApp.view.VideoParamSetView', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.videoParamSetView',
			split : true,
			frame: false,
//			title:'视频参数设置',
			region : "center",
			layout : 'form',
			autoScroll:true,
			modal : true,
			border : false,
		items :[ {
		xtype : 'form',
		frame : false,
		border : false,
		anchor : '100%',
		collapsible : false,
	"items": [
	          {
	              "xtype": "fieldset",
	              "title": "<b><font style='color:green'>多森视频参数设置</font></b>",
	              fieldDefaults: {
	              	buttonAlign : 'left',
		            labelAlign : 'right',
	  	        	labelWidth: 80
	  	   		 },
	  	items :[ {
	  	xtype : 'form',
		id : 'parameterSet_Form',
		border : false,
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults : {
			labelAlign : 'right',
			labelWidth : 150
//			margin : '20 0 10 50'
			},   		 
	  		   items : [{
	  			layout : 'column',
	  			columnWidth : 1,
	  			border:false,
	              items : [{
	  						columnWidth : .5,
	  						border:false,
	  						margin : '0 0 0 50',
	  						items : [{
								xtype : 'combo',
								fieldLabel : '<font color="red">*</font>协议类型',
//								width : 450,
								itemId : 'protocol',
								id : 'protocol',
								name : 'videoParamSet.protocol',
								store : Ext.create('VideoParamSetApp.store.ProtocolStore'),
								editable: false,
								valueField : 'id',
								displayField : 'value',
								allowBlank : false,
								cls : 'x-textfield',
								value: 'rtsp://'
							},{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>IP',
								name : 'videoParamSet.ip',
								id:'ip',
								allowBlank : false,
								regex: /^[0-9a-zA-Z.]+$/,
						        regexText: "输入的IP不正确！",
								anchor : '100%',
								maxLength : 50,
								blankText : '不能为空'
							},{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>端口',
								name : 'videoParamSet.port',
								id:'port',
								allowBlank : false,
								regex: /^[0-9]\d*$/,
						        regexText: "输入的端口不正确！",
								anchor : '100%',
								maxLength : 50,
								blankText : '不能为空'
							},{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>用户名',
								name : 'videoParamSet.name',
								id:'name',
								allowBlank : false,
								anchor : '100%',
								maxLength : 20,
								blankText : '不能为空'
							},{ 
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>密码',
								name : 'videoParamSet.password',
								id:'password',
								allowBlank : false,
								anchor : '100%',
								maxLength : 20,
								blankText : '不能为空'
							}]
	  				}, {
	  						columnWidth : .5,
	  						border:false,
	  						margin : '0 0 0 0',
	  						items : [{
	  							xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>提供类型',
								name : 'videoParamSet.rendertype',
								id:'rendertype',
								allowBlank : false,
								anchor : '100%',
								maxLength : 1,
								regex: /^[0-9]\d*$/,
						        regexText: "请输入一个整数！",
								blankText : '不能为空'
							},{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>缓存',
								name : 'videoParamSet.inspectionday',
								id:'inspectionday',
								allowBlank : false,
								anchor : '100%',
								maxLength : 3,
								regex: /^[0-9]\d*$/,
						        regexText: "请输入一个整数！",
								blankText : '不能为空'
							},{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>播放声音',
								name : 'videoParamSet.playsound',
								id:'playsound',
								allowBlank : false,
								anchor : '100%',
								maxLength : 3,
								regex: /^[0-9]\d*$/,
						        regexText: "请输入一个整数！",
								blankText : '不能为空'
							},{ 
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>按比例显示',
								name : 'videoParamSet.showtoscale',
								id:'showtoscale',
								allowBlank : false,
								anchor : '100%',
								maxLength : 3,
								regex: /^[0-9]\d*$/,
						        regexText: "请输入一个整数！",
								blankText : '不能为空'
							},{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>显示码率信息',
								name : 'videoParamSet.showsatic',
								id:'showsatic',
								allowBlank : false,
								anchor : '100%',
								maxLength : 10,
								regex: /^[1-9]\d*$/,
						        regexText: "请输入一个整数！",
								blankText : '不能为空'
							}]
						}]
	                },{
							xtype : 'button',
							margin : '10 0 5 450',
							action : 'paramset',
							id:'videoparamset',
							text : '设置'
						}]
	          }
	      ]
	          }]
	      }]
		});