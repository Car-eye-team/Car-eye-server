Ext.define('MultiMediaDataApp.view.MultiMediaSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.multiMediaSearchView',
    title: '多媒体事件上传搜索',
    frame : true,
    region: "north",
    height:90,
    collapsible: true,
    collapseMode: "mini",
    split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	layout : {
		type : 'table',
		align : 'right',
		columns:5
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:60
	},
    items : [{
		xtype : 'combo',
		fieldLabel : '车牌号',
		width: 160,
		labelWidth: 40,
		id : 'm_carnumber',
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
		width : 140,
		fieldLabel : '多媒体类型',
		labelWidth: 70,
		editable:false,
		id:'m_mediatype',
		labelAlign: 'right',
		store : 'MultiMediaTypeStore',
		displayField : 'mediatype',
		valueField : 'id'
	},{
        xtype : 'combo',
		width : 200,
		editable:false,
		fieldLabel : '事件项编码',
		labelWidth: 70,
		id:'m_eventcode',
		labelAlign: 'right',
		store : 'EventCodeStore',
		displayField : 'eventcode',
		valueField : 'id'
	},{
		xtype : 'datetimefield',
		width : 180,
		maxLength : 20,
		id : 'm_stime',
		fieldLabel : '开始时间',
		labelWidth: 60,
		editable: false,
		labelAlign: 'right'
	},{
		xtype : 'datetimefield',
		width : 180,
		maxLength : 20,
		id : 'm_etime',
		fieldLabel : '结束时间',
		labelWidth: 60,
		editable: false,
		labelAlign: 'right'
	}
],
    buttons : [{
		text : '查询',
		tooltip : '查询',
		iconCls : 'common-search-icon',
		action: 'search'
	}, {
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
//	    action : 'resetcar',
    	handler: function(button){
        	Ext.getCmp('m_eventcode').setValue("");
        	Ext.getCmp('m_mediatype').setValue("");
        	Ext.getCmp('m_carnumber').setValue("");
        	Ext.getCmp('m_stime').setValue("");
        	Ext.getCmp('m_etime').setValue("");
        }
	}]

});

