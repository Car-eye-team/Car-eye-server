Ext.define('DialRuleApp.view.JsddSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.jsddSearchView',
    title: '即时调度搜索',
//    region: "center",
    height:100,
    frame: true,
//    border:false,
//    collapsible: true,
//    collapseMode: "mini",
//    split: true,
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
   		  xtype : "datetimefield",
	      id:'jsdd_searchstime',
	      name:'dialRule.searchstime',
	      width : 200,
	      editable:false,
	      fieldLabel : '设置时间'
//	      labelWidth : 150
  },{
	      xtype : "datetimefield",
	      id:'jsdd_searchetime',
	      width : 200,
	      editable:false,
	      name:'dialRule.searchetime',
	      fieldLabel : '    到'
//	      labelWidth : 150
  }],
    buttons : [{
	    text : '查询',
	    tooltip : '查询调度规则',
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

