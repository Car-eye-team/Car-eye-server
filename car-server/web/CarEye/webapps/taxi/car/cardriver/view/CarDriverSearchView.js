Ext.define('CarDriverApp.view.CarDriverSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.carDriverSearchView',
    title: '司机信息搜索',
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
    	labelWidth:60
	},
    items : [{
		        xtype : 'textfield',
		        width : 200,
		        maxLength : 20,
		        labelWidth:60,
		        id : 'cd_drivername',
		        fieldLabel : '姓名'
		    },{
		        xtype : 'textfield',
		        width : 200,
		        maxLength : 20,
		        id : 'cd_phone',
		        fieldLabel : '手机号'
		    },{
		    	xtype : 'combo',
				width : 210,
				fieldLabel : '车牌号',
				id:'cd_carnumber',
				pageSize : 10,
				itemId:'carid',
				store : 'CarPageListStore',
				displayField : 'carnumber',
				valueField : 'carid',
				minChars:1,
				matchFieldWidth:false,
				listConfig :{
					width:235
				}
		    }
		    
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询司机信息',
	    iconCls : 'common-search-icon',
	    action: 'search'
	}, {
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
//	    action : 'reset',
        handler: function(button){
        	button.up('form').getForm().reset();
        }
	}]

});

