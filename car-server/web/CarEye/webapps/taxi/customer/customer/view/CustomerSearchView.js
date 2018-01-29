Ext.define('CustomerApp.view.CustomerSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.customerSearchView',
    title: '客户信息搜索',
    frame : true,
    region: "north",
    height:90,
    collapsible: true,
    collapseMode: "mini",
    split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	layout : {
		type : 'table',
		align : 'right'
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:60
	},
    items : [{
						        xtype : 'textfield',
						        width : 130,
						        maxLength : 20,
						        labelWidth : 60,
						        id : 'db_cname',
						        fieldLabel : '客户名称'
							},{
								xtype : 'combo',
								width : 160,
								name : 'db_typename',
								id:'db_typename',
								itemId:'db_typename',
								store :"TypeListStore",
								editable: false,
								valueField : 'typename',
								displayField : 'typename',
								labelWidth : 60,
								blankText : '请选择',
								fieldLabel : '客户类型'
							},{
								xtype : 'datetimefield',
								width : 180,
								id : 'db_stime',
								name:'db_stime',
								fieldLabel : '开始时间',
								editable: false,
								labelAlign: 'right',
								emptyText:'请选择',
								typeAhead:false, 
								labelWidth : 55,
					            editable:false
							},  {
								xtype : 'datetimefield',
								width : 140,
								maxLength : 20,
								id : 'db_etime',
								name:'db_etime',
								fieldLabel : '至',
								editable: false,
								labelAlign: 'right',
								labelWidth : 20,
								emptyText:'请选择',
								typeAhead:false, 
					            editable:false
							}
		    
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询客户信息',
	    iconCls : 'common-search-icon',
	    action: 'search'
	}, {
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
//	    action : 'reset',
        handler: function(button){
        	button.up('form').getForm().reset();
        }
	}]

});

