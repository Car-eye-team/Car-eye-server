Ext.define('CarTypeApp.view.CarTypeSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.carTypeSearchView',
    itemId :'carTypeSearchView',
    title: '车辆类型搜索',
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
    	labelAlign: 'right'
	    },
    items : [{
				xtype : 'textfield',
				width : 200,
				maxLength : 40,
				id : 'ct_name',
				name:'ct_name',
				fieldLabel : '类型名称',
				regex : /^[^('"\\%|'"\\_)]+$/,
			    regexText : '不可输入特殊字符%或_',
				labelWidth: 60,
				labelAlign: 'right'
			}
			],
	buttons : [{
				text : '查询',
				id : 'mailset_query',
				tooltip : '查询',
				iconCls : 'common-search-icon',
				action: 'search'
			}, {
				text : '重置',
				id : 'mailset_reset',
				tooltip : '清空查询条件',
				iconCls : 'common-reset-icon',
//				action : 'reset',
	        	handler: function(button){
	        		button.up('form').getForm().reset();
	        	}
			}]

});

