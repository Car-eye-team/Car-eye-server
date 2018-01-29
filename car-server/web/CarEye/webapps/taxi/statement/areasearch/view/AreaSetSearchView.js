Ext.define('AreaSetApp.view.AreaSetSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.areaSetSearchView',
    title: '区域搜索',
    frame : true,
    region: "north",
    height:58,
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
	  tbar: [
	      {
		        xtype : 'textfield',
		        width : 380,
		        id:'warn_attr',
		        maxLength : 20,
		        labelWidth: 100,
		        fieldLabel : '区域搜索'
			}, {
				xtype: 'button',
				text : '查询',
				tooltip : '查询',
				iconCls : 'common-search-icon',
				action: 'searchtext'
			}
	  ]
});

