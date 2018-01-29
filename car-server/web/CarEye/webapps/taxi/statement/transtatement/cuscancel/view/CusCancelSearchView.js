Ext.define('CusCancelApp.view.CusCancelSearchView' ,{
    extend: 'Ext.form.Panel',
    border : true,
    alias : 'widget.cusCancelSearchView',
    title: '客户取消数统计搜索',
	region: "north",
	frame: true,
	layout:{
		type : 'table',
		align : 'right',
		columns:5
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
		        id : 'ccs_username',
		        fieldLabel : '客户姓名'
		    },{
		        xtype : 'textfield',
		        width : 170,
		        maxLength : 20,
		        labelWidth:50,
		        id : 'ccs_phone',
		        fieldLabel : '手机号'
		    },{
								xtype : 'combo',
								width : 130,
								maxLength : 20,
								id : 'css_type',
								store :"TypeStore",
								editable: false,
								valueField : 'id',
								displayField : 'type',
								fieldLabel : '是否违约',
								labelWidth: 60,
								labelAlign: 'right'
							},{
				xtype : 'datetimefield',
				width :200,
				id : 'ccs_stime',
				maxLength : 20,
				fieldLabel : '取消开始时间',
				labelWidth: 80,
				editable: false,
				labelAlign: 'right'
			},{
					xtype : 'datetimefield',
					width : 160,
					id : 'ccs_etime',
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
    		var con = Ext.create("CusCancelApp.controller.CusCancelCtrl");
    		con.searchCusCancel();
        }
	},{
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
        handler: function(button){
        	Ext.getCmp('ccs_username').setValue("");
        	Ext.getCmp('ccs_phone').setValue("");
        	Ext.getCmp('css_type').setValue("");
        	Ext.getCmp('ccs_stime').setValue("");
        	Ext.getCmp('ccs_etime').setValue("");
        }
	}]
});
