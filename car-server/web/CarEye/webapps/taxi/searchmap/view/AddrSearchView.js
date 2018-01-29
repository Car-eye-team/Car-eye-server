Ext.define('SearchMapApp.view.AddrSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.addrSearchView',
    title: '搜索地址',
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
    	labelAlign: 'right'
    	//labelWidth:60
	},
	  tbar: [
	      {
		        xtype : 'textfield',
		        id : 'keyword',
		        width : 380,
		        maxLength : 20,
		        labelWidth: 60,
		        fieldLabel : 'POI搜索'
			}, {
				xtype: 'button',
				text : '查询',
				tooltip : '查询',
				iconCls : 'common-search-icon',
				action: 'searchpoi'
			},"->", {
				xtype: 'button',
				text : '查看POI发送记录',
				tooltip : '查看POI发送记录',
				iconCls : 'common-search-icon',
				action: 'poisendrecord'
			}
	  ]
});

