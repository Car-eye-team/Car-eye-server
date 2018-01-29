Ext.define('LoginLogApp.view.LoginLogSearchView' ,{
    extend: 'Ext.form.Panel',
     alias : 'widget.loginLogSearchView',
    title: '用户登陆日志搜索',
        region: "north",
        height:100,
        collapsible: true,
        collapseMode: "mini",
        split: true,
        title: '用户登陆日志搜索',
	    frame : true,
		autoHeight : true,
		bodyStyle : 'padding:4px 2px 3px 4px',
		layout : {
			type : 'table',
			align : 'right'
		},
		fieldDefaults: {
	    	labelAlign: 'right',
	    	width : 200,
	  		labelWidth: 60
	    },
	    items : [{
					xtype : 'textfield',
					maxLength : 20,
					id : 'll_loginname',
					labelWidth: 70,
					fieldLabel : '用户登录名',
					labelAlign: 'right'
				}, {
					xtype : 'textfield',
					maxLength : 20,
					id : 'll_deptname',
					fieldLabel : '企业',
					labelAlign: 'right'
				}
				, {
					xtype : 'datetimefield',
					width : 250,
					maxLength : 20,
					id : 'll_stime',
					fieldLabel : '登陆时间(从)',
					labelWidth: 80,
					editable: false,
					labelAlign: 'right'
				},  {
					xtype : 'datetimefield',
					width : 200,
					maxLength : 20,
					id : 'll_etime',
					labelWidth: 30,
					editable: false,
					fieldLabel : '(至)',
					labelAlign: 'right'
				}],
		buttons : [{
					text : '查询',
					tooltip : '查询用户信息',
					iconCls : 'common-search-icon',
					action: 'search'
				}, {
					text : '重置',
					tooltip : '清空查询条件',
					iconCls : 'common-reset-icon',
					action : 'reset',
		        	handler: function(button){
		        		button.up('form').getForm().reset();
		        	}
				}]
});

