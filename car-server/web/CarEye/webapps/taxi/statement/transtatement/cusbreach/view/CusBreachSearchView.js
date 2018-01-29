Ext.define('CusBreachApp.view.CusBreachSearchView' ,{
    extend: 'Ext.form.Panel',
    border : true,
    alias : 'widget.cusBreachSearchView',
    title: '客户违约数统计搜索',
	region: "north",
	frame: true,
	layout:{
		type : 'table',
		align : 'right',
		columns:4
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:80
	},
	height:100,
	collapsible: true,
    collapseMode: "mini",
    split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	items:[{
		        xtype : 'textfield',
		        width : 170,
		        maxLength : 20,
		        labelWidth:60,
		        id : 'cbs_username',
		        fieldLabel : '乘客姓名'
		    },{
		        xtype : 'textfield',
		        width : 170,
		        maxLength : 20,
		        labelWidth:50,
		        id : 'cbs_phone',
		        fieldLabel : '手机号'
		    },{
				xtype : 'datetimefield',
				width :200,
				id : 'cbs_stime',
				maxLength : 20,
				fieldLabel : '取消开始时间',
				labelWidth: 80,
				editable: false,
				labelAlign: 'right'
			},{
					xtype : 'datetimefield',
					width : 160,
					id : 'cbs_etime',
					maxLength : 20,
					fieldLabel : '到',
					labelWidth: 30,
					editable: false,
					labelAlign: 'right'
			}],
	buttons:[{
		xtype: 'button',
		text : '查询',
		id : 'mailset_query_text',
		tooltip : '查询',
		iconCls : 'common-search-icon',
//		action: 'search',
        handler: function(button){
    		var con = Ext.create("CusBreachApp.controller.CusBreachCtrl");
    		con.searchCusBreach();
        }
	},{
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
        handler: function(button){
        	Ext.getCmp('cbs_username').setValue("");
        	Ext.getCmp('cbs_phone').setValue("");
        	Ext.getCmp('cbs_stime').setValue("");
        	Ext.getCmp('cbs_etime').setValue("");
        }
	}]
});
