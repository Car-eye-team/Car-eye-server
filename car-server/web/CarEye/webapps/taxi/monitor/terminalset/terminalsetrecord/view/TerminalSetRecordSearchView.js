Ext.define('TerminalSetRecordApp.view.TerminalSetRecordSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.terminalSetRecordSearchView',
    title: '终端参数设置搜索',
    frame : true,
    region: "north",
    height:100,
    collapsible: true,
    collapseMode: "mini",
    split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	layout : {
		type : 'table',
		align : 'right',
		columns:6
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:60
	},
    items : [
			{
							        xtype : 'textfield',
							        maxLength : 20,
									fieldLabel : '车牌号',
									labelWidth: 60,
									id:'rcr_carnumber',
									labelAlign: 'right'
								},{
							        xtype : 'textfield',
									width : 150,
									fieldLabel : '指令类型',
									labelWidth: 60,
									id:'rcr_commandtype',
									labelAlign: 'right'
								},{
							        xtype : 'combo',
									width : 140,
									editable:false,
									fieldLabel : '状态',
									labelWidth: 30,
									id:'rcr_setstatus',
									labelAlign: 'right',
									store : 'SetStatusStore',
									displayField : 'setstatus',
									valueField : 'id'
								},{
									xtype : 'datetimefield',
									width : 200,
									maxLength : 20,
									id : 'rcr_stime',
									name : 'remotecontrol.stime',
									labelWidth: 60,
									editable:false,
									fieldLabel : '开始时间',
									labelAlign: 'right'
								},{
									xtype : 'datetimefield',
									width : 200,
									editable:false,
									maxLength : 20,
									id : 'rcr_etime',
									fieldLabel : '结束时间',
									labelWidth: 60,
									labelAlign: 'right'
								}			
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询终端参数设置信息',
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

