Ext.define('RegionSetApp.view.RegionSetSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.regionSetSearchView',
    title: '行政区域搜索',
    frame : true,
	region: "north",
    height:100,
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
    	width : 180
    },
    items : [{
				xtype : 'textfield',
				maxLength : 20,
				id : 'c_cnname',
				name : 'region.loginname',
				fieldLabel : '行政区名',
				labelWidth: 60,
				labelAlign: 'right'
				
			}, {
				xtype : 'combo',
				width : 150,
				id : 'c_clevel',
				itemId : 'c_clevel',
				name : 'region.clevel',
				store : 'ClevelStore',
				editable: false,
				valueField : 'clevel',
				displayField : 'regionlevel',
				fieldLabel : '行政级别',
				labelWidth: 60,
				labelAlign: 'right'
			}, {
				xtype : 'textfield',
				maxLength : 20,
				id : 'c_parentname',
				name : 'region.parentname',
				fieldLabel : '上级名称',
				labelWidth: 60,
				labelAlign: 'right'
			
			}],
	buttons : [{
				text : '查询',
				tooltip : '查询行政区域',
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

