Ext.define('CustomerEvaluationApp.view.CustomerEvaluationSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.customerEvaluationSearchView',
    title: '客户评价司机信息搜索',
    frame : true,
    region: "north",
    height:120,
    collapsible: true,
    collapseMode: "mini",
    split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	layout : {
		type : 'table',
		align : 'right',
		columns:6
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:60
	},
    items : [{
    							xtype : 'textfield',
						        width : 170,
						        maxLength : 20,
								labelAlign: 'right',
						        labelWidth: 60,
						        id : 'c_cname',
						        fieldLabel : '客户姓名'
						    },{
								xtype : 'textfield',
						        width : 170,
						        maxLength : 20,
								labelAlign: 'right',
						        labelWidth: 60,
						        id : 'c_dname',
						        fieldLabel : '司机姓名'
							},{
								xtype : 'combo',
								width : 120,
								maxLength : 20,
								id : 'c_slevel',
								store :"SlevelStore",
								editable: false,
								valueField : 'id',
								displayField : 'value',
								fieldLabel : '星级',
								labelWidth: 30,
								labelAlign: 'right'
							},{
								xtype : 'datetimefield',
								width : 190,
								maxLength : 20,
								id : 'c_stime',
								fieldLabel : '开始时间',
								labelWidth: 60,
								editable: false,
								labelAlign: 'right'
							},  {
								xtype : 'datetimefield',
								width : 190,
								maxLength : 20,
								id : 'c_etime',
								fieldLabel : '结束时间',
								labelWidth: 60,
								editable: false,
								labelAlign: 'right'
							}
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询客户评价信息',
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

