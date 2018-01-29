Ext.define('FMS.view.Top',{
	extend:'Ext.panel.Panel',
	alias:'widget.top',
	region:'north',
    height: 60,
	border : false,
	collapsible : true,
	header : false, // 显示 header 默认 true
	items : [{
				xtype : 'menuPanel'
			},{
				xtype : 'toolbarPanel'
			}]
});