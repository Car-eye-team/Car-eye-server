Ext.define('ComplaintInfoApp.view.ComplaintInfoSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.complaintInfoSearchView',
    itemId :'complaintInfoSearchView',
    title: '投诉搜索',
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
    	labelWidth: 70
    },
    items : [{
				xtype : 'combo',
				fieldLabel : '车牌号',
				width: 160,
				labelWidth: 40,
				id : 'c_carnumber',
				labelAlign: 'right',
				store : 'CarPageListStore',
				displayField : 'carnumber',
				valueField : 'carnumber',
				pageSize : 10,
				minChars:1,
				matchFieldWidth:false,
				listConfig :{
					width:235
				}
			},{
				xtype : 'combo',
				width : 170,
				fieldLabel : '投诉类型',
				id:'c_type',
				itemId:'type',
				minChars:1,
				queryDelay:800,
				labelAlign: 'right',
				store : 'TypeListStore',
				displayField : 'typename',
				valueField : 'type'
			},{
				xtype : 'combo',
				width : 170,
				maxLength : 20,
				id : 'c_dealstatus',
				store :"DealStatusStore",
				editable: false,
				valueField : 'key',
				displayField : 'value',
				fieldLabel : '处理状态'
			},{
				xtype : 'datetimefield',
				width : 200,
				maxLength : 20,
				id : 'c_stime',
				fieldLabel : '投诉时间',
				labelWidth: 70,
				editable: false
			}, {
				xtype : 'datetimefield',
				width : 160,
				maxLength : 20,
				id : 'c_etime',
				fieldLabel : '至',
				labelWidth: 30,
				editable: false
			}],
	buttons : [{
				text : '查询',
				tooltip : '查询',
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

