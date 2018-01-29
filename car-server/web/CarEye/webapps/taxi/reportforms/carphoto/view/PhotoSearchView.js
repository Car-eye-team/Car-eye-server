Ext.define('CarPhotoApp.view.PhotoSearchView' ,{
     extend: 'Ext.form.Panel',
     alias : 'widget.photoSearchView',
     title: '上传图片搜索',
        height:100,
        collapsible: true,
        collapseMode: "mini",
        split: true,
	    frame : true,
	    border : false,
		bodyStyle : 'padding:4px 2px 3px 4px',
		layout : {
			type : 'table',
			align : 'right'
		},
		fieldDefaults: {
	    	labelAlign: 'right',
	    	width : 200,
	  		labelWidth: 60
	    },
	    items : [{
	    			xtype : 'combo',
					width : 180,
					labelWidth: 60,
					fieldLabel : '上传类型',
					id : 'cp_eventcode',
					editable: false,
					store : 'TypeStore',
					displayField : 'name',
					valueField : 'type'
				},  {	
					xtype : 'datetimefield',
					width : 240,
					maxLength : 20,
					id : 'cp_stime',
					fieldLabel : '上传时间(从)',
					labelWidth: 80,
					value:Ext.Date.format(new Date(), 'Y-m-d'),
					editable: true,
					labelAlign: 'right'
				},  {
					xtype : 'datetimefield',
					width : 190,
					maxLength : 20,
					id : 'cp_etime',
					labelWidth: 30,
					editable: true,
					fieldLabel : '(至)',
					value:new Date(),
					labelAlign: 'right'
				}],
		buttons : [{
					text : '查询',
					tooltip : '查询用户信息',
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

