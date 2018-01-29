Ext.define('RemoteControlRecordApp.view.RemoteControlRecordSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.remoteControlRecordSearchView',
    title: '远程控制记录信息搜索',
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
									width : 140,
									id:'rcr_carnumber',
									labelAlign: 'right'
								},{
							        xtype : 'textfield',
									width : 140,
									fieldLabel : '指令类型',
									labelWidth: 60,
									id:'rcr_commandtype',
									labelAlign: 'right'
								},{
							        xtype : 'combo',
									width : 120,
									fieldLabel : '状态',
									labelWidth: 40,
									id:'rcr_setstatus',
									editable:false,
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
									editable: false,
									fieldLabel : '开始时间',
									labelAlign: 'right'
								},{
									xtype : 'datetimefield',
									width : 200,
									maxLength : 20,
									id : 'rcr_etime',
									fieldLabel : '结束时间',
									labelWidth: 60,
									editable: false,
									labelAlign: 'right'
								}				
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询远程控制信息',
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

