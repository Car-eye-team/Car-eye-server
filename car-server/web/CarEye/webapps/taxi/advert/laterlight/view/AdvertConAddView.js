Ext.define('AdvertManageApp.view.AdvertConAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.advertConAddView',
	title : '新增广告内容信息',
    width : 320,
	layout : 'form',
	itemId :'advertConAddWindow',
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
		itemId :'advertConAddPanel',
		collapsible : false,
		//buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 60
	    },
		layout : 'form',
		items : [{
			xtype : 'combo',
			fieldLabel : '<font color="red">*</font>广告类型',
			id:'typeid',
			name : 'advertCon.typeid',
			allowBlank : false,
			editable : false,
			cls : 'x-textfield',
			store : 'TypeStore',
			displayField : 'typename',
			valueField : 'id'
		},{
			xtype : 'hidden',
			fieldLabel : '<font color="red">*</font>版本名称',
			id:'versionid',
			name : 'advertCon.versionid',
			allowBlank : false,
			editable : false,
			cls : 'x-textfield',
			store : 'VersionStore',
			displayField : 'version',
			valueField : 'id'
		},{
					xtype : 'textfield',
					fieldLabel : '<font color="red">*</font>广告名称',
					id:'adname',
					name : 'advertCon.adname',
					allowBlank : false,
					cls : 'x-textfield'
				},{
					xtype : "fieldcontainer",
					layout : {
						type : "hbox"
					},
					items : [{
								xtype : 'textfield',
								fieldLabel : '上传附件',
								name : 'advertCon.path',
								id : 'path',
								anchor : '100%',
								width : 230,
								editable : true
							}, {
								xtype : 'filefield',
								width : 100,
								buttonText : "请选择",
								name : 'fileInput',
								id : 'fileInput'
							}]
				},{
					xtype : 'fieldcontainer',
					border : false,
					html : '<span id="fileQueue"></span>'
				}, {
					xtype : 'textfield',
					fieldLabel : '链接地址',
					name : 'advertCon.linkpath',
					itemId : 'linkpath',
					// tabIndex : 1,
					maxLength : 200,
					maxLengthText : '最大长度不超过200位!',
					allowBlank : true,//设置是否允许为空
					enableKeyEvents : true,
					anchor : '100%',
					cls : 'x-textfield',
					validator : vd
				},{
					xtype : 'datefield',
					fieldLabel : '到期日期',
					id:'dtime',
					name : 'advertCon.dtime',
					editable : false,
					format:'Y-m-d',
					cls : 'x-textfield'
				},{
					xtype : 'hidden',
					fieldLabel : '发布时间',
					id:'reltime',
					name : 'advertCon.reltime',
					editable : false,
					format:'Y-m-d',
					cls : 'x-textfield'
				},{
					xtype : 'textarea',
					fieldLabel : '<font color="red">*</font>广告描述',
					id:'remark',
					name : 'advertCon.remark',
					allowBlank : false,//设置是否允许为空
					rows:2,
					cls : 'x-textfield'
				}]
	}],
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


				


