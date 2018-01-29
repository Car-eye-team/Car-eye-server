Ext.define('CustomerInboundApp.view.CustomerInboundSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.customerInboundSearchView',
    itemId :'mailsetSearchView',
    title: '客户来电信息搜索',
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
						        width : 150,
						        maxLength : 20,
						        labelWidth : 60,
						        id : 'db_cname',
						        fieldLabel : '客户名称'
							},{
						        xtype : 'textfield',
						        width : 150,
						        maxLength : 20,
						        labelWidth : 60,
						        id : 'db_callnumber',
						        fieldLabel : '来电号码'
							},{
								xtype : 'datetimefield',
								width : 200,
								id : 'db_stime',
								name:'db_stime',
								fieldLabel : '来电开始时间',
								editable: false,
								labelAlign: 'right',
								emptyText:'请选择',
								typeAhead:false, 
								labelWidth : 80,
					            editable:false
							},  {
								xtype : 'datetimefield',
								width : 180,
								maxLength : 20,
								id : 'db_etime',
								name:'db_etime',
								fieldLabel : '到',
								editable: false,
								labelAlign: 'right',
								labelWidth : 30,
								emptyText:'请选择',
								typeAhead:false, 
					            editable:false
							}],
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
				//action : 'reset',
	        	handler: function(button){
	        		button.up('form').getForm().reset();
	        	}
			}]

});

