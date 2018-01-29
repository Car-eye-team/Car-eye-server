Ext.define('LogInfoApp.view.LogInfoSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.logInfoSearchView',
    itemId :'logInfoSearchView',
    title: '日志记录搜索',
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
    items : [
			{
				xtype : 'textfield',
				width : 170,
				maxLength : 40,
				labelWidth: 55,
				id : 's_loginname',
				fieldLabel : '操作用户',
				regex : /^[^('"\\%|'"\\_)]+$/,
			    regexText : '不可输入特殊字符%或_',
				labelWidth: 65,
				emptyText:'请输入',
				labelAlign: 'right'
			},{
				xtype : 'combo',
				width : 170,
				maxLength : 20,
				labelWidth: 55,
				id : 's_operattype',
				store :"TypeStore",
				editable: false,
				valueField : 'id',
				displayField : 'operattype',
				fieldLabel : '操作类型',
				labelWidth: 80,
				labelAlign: 'right',
				emptyText:'请选择',
			    editable:false
			}, {
				xtype : 'datetimefield',
				width : 200,
				maxLength : 20,
				id : 's_stiem',
				fieldLabel : '开始时间',
				labelWidth: 65,
				labelAlign: 'right',
				emptyText:'请选择',
				typeAhead:false, 
		        editable:false
			},  {
				xtype : 'datetimefield',
				width : 200,
				maxLength : 20,
				id : 's_etime',
				fieldLabel : '结束时间',
				labelWidth: 65,
				labelAlign: 'right',
				emptyText:'请选择',
				typeAhead:false, 
		        editable:false
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
				action : 'reset',
	        	handler: function(button){
	        		button.up('form').getForm().reset();
	        	}
			}]

});

