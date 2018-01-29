Ext.define('authorityApp.view.AuthoritySearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.authoritySearchView',
    frame : true,
    title: '权限搜索',
	region: "north",
    height:100,
    collapsible: true,
    collapseMode: "mini",
    split: true,
//	bodyStyle : 'padding:4px 2px 2px 4px',
	buttonAlign : 'center',
	columnWidth:1,
	layout : {
		type : 'table',
		align : 'right'
	},
    items : [{
				xtype : 'combo',
				editable: true,
				labelWidth: 80,
				labelAlign: 'right',
				valueField : 'blocgroupid',
				displayField : 'blocgroupname',
				fieldLabel : '企业组名称',
				store : 'UserGroupListStore',
				width : 350,
				id : 'authority_blocgroupid'
				
	}],
	buttons : [{
				text : '查询权限',
				tooltip : '查询企业组权限',
				iconCls : 'common-search-icon',
				action: 'search'
			},{
				text : '分配权限',
				id: '110601',
				iconCls : 'common-assignauthority-icon',
				tooltip : '给企业组分配权限',
				action: 'assign'
			}]

});

