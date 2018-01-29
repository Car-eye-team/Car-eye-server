Ext.define('systemFunctionApp.view.SystemFunctionEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.systemFunctionEditView',
	title : '修改系统菜单',
    width : 600,
	layout : 'form',
	frame: true,
	itemId :'systemFunctionEditWindow',
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
		itemId :'editSystemFunctionPanel',
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 80
	    },
		layout : 'form',
		items : [{
			layout : 'column',
			columnWidth : 1,
			items : [{
					columnWidth : .5,
					layout : 'form',
					border:false,
					items : [{
			                    xtype: 'hidden',
			                    name : 'systemFunction.menuid',
								id : 'menuid'
							},{
								xtype : 'combo',
								width : 250,
								id : 'menulevel',
								name : 'systemFunction.menulevel',
								hiddenName : 'menulevel',
								fieldLabel : '<font color="red">*</font>菜单等级',
								itemId : 'menulevel',
								id : 'menulevel',
								anchor : '100%',
								cls : 'x-textfield',
								editable: false,
								allowBlank : false,
								blankText : '请选择',
								validateOnChange: false,
								store : 'MenuLevelStore',
								displayField : 'name',
								valueField : 'menulevel'
							}, {
								xtype : 'textfield',
								fieldLabel : '功能访问路径',
								name : 'systemFunction.menuaddr',
								itemId : 'menuaddr',
								id : 'menuaddr',
								anchor : '100%',
								cls : 'x-textfield',
								maxLength : 100,
								enableKeyEvents : true
							}, {
								xtype : 'combo',
								width : 250,
								id : 'menutype',
								hiddenName : 'menulevel',
								fieldLabel : '<font color="red">*</font>菜单类型',
								name : 'systemFunction.menutype',
								itemId : 'menutype',
								anchor : '100%',
								cls : 'x-textfield',
								allowBlank : false,
								blankText : '请选择',
								editable: false,
								validateOnChange: false,
								store : Ext.create('Ext.data.Store', {
								    fields: ['menutype', 'name'],
								    data : [
								        {"menutype":"0", "name":"菜单"},
								        {"menutype":"1", "name":"按钮"}
								    ]
								}),
								displayField : 'name',
								valueField : 'menutype'
							}, {
								xtype : 'textfield',
								fieldLabel : '排序号',
								id : 'menusort',
								itemId : 'menusort',
								name : 'systemFunction.menusort',
								anchor : '100%',
								maxLength : 50,
								cls : 'x-textfield'
							}]
				}, {
					columnWidth : .49,
					layout : 'form',
					border:false,
					items : [{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>菜单名称',
								itemId : 'menuname',
								id : 'menuname',
								name : 'systemFunction.menuname',
								anchor : '100%',
								allowBlank : false,
								blankText : '请输入菜单名称',
								maxLength : 50,
								cls : 'x-textfield'
							},{
								xtype : 'combo',
								width : 250,
								id : 'parentmenuid',
								hiddenName : 'parentmenuid',
								fieldLabel : '父级菜单',
								name : 'systemFunction.parentmenuid',
								itemId : 'parentmenuid',
								anchor : '100%',
								cls : 'x-textfield',
								editable: false,
								validateOnChange: false,
								store : 'MenuStore',
								displayField : 'menuname',
								valueField : 'menuid'
							}, {
								xtype : 'combo',
								width : 250,
								id : 'medetype',
								hiddenName : 'medetype',
								fieldLabel : '<font color="red">*</font>具体类型',
								name : 'systemFunction.medetype',
								itemId : 'medetype',
								anchor : '100%',
								cls : 'x-textfield',
								allowBlank : false,
								blankText : '请选择',
								editable: false,
								validateOnChange: false,
								store : Ext.create('Ext.data.Store', {
								    fields: ['medetype', 'name'],
								    data : [
								        {"medetype":"0", "name":"树枝"},
								        {"medetype":"1", "name":"叶子"},
								        {"medetype":"2", "name":"左按钮"},
								        {"medetype":"3", "name":"右键"},
								        {"medetype":"4", "name":"右按钮"},
								        {"medetype":"5", "name":"页面按钮"}
								    ]
								}),
								displayField : 'name',
								valueField : 'medetype'
							}, {
								xtype : 'combo',
								fieldLabel : '<font color="red">*</font>是否激活',
								name : 'systemFunction.ifactivate',
								hiddenName : 'ifactivate',
								id : 'ifactivate',
								itemId : 'ifactivate',
								anchor : '100%',
								allowBlank : false,
								blankText : '请选择',
								cls : 'x-textfield',
								editable: false,
								validateOnChange: false,
								store : Ext.create('Ext.data.Store', {
								    fields: ['ifactivate', 'name'],
								    data : [
								        {"ifactivate":"1", "name":"激活"},
								        {"ifactivate":"2", "name":"停用"}
								    ]
								}),
								displayField : 'name',
								valueField : 'ifactivate'
							}]
						}]
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


				


