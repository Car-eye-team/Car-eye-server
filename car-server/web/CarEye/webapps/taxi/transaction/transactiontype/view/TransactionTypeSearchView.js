Ext.define('TransactionTypeApp.view.TransactionTypeSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.transactionTypeSearchView',
    title: '约车业务类型信息搜索',
    frame : true,
    region: "north",
    height:90,
    frame: true,
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
    items : [
  {
								        xtype : 'textfield',
								        width : 200,
								        maxLength : 20,
										labelAlign: 'right',
								        labelWidth: 80,
								        id : 'dbt_transactiontypename',
								        fieldLabel : '约车类型名称'
									}
							
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询约车业务信息',
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

