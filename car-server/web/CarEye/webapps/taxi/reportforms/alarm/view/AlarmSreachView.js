Ext.define('AlarmApp.view.AlarmSreachView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.alarmSreachView',
    title: '车辆报警信息搜索',
    frame : true,
    region: "north",
    height:120,
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
    	labelAlign: 'right'
	},
    items : [{
		xtype : 'comboboxtree',
		fieldLabel : '企业名称',
		id : 'al_deptid',
		width:150,
		labelWidth: 60,
		store: Ext.create('Ext.data.TreeStore', {  
	        autoLoad : 'true',
	        fields: ['text','id','parentId'], 
			root : {expanded : true,text : '企业名称' },
			proxy: {
				 type: 'ajax',
				 url: window.BIZCTX_PATH + '/servlet/DeptTree?type=200', 
				 reader: {
					 type: 'json'
				 }
			}
	    }) ,
	    rootVisible: false,
		editable: true,
		cls : 'x-textfield',
		valueField: 'id', 
		displayField: 'text',
		listeners: {
	        change: {
	            element: 'el', 
	            fn: function(){ 
	            	var store = Ext.getCmp('al_deptid').store;
					store.clearFilter(true);
					store.on('beforeload', function (store, options) {
			            var new_params = { 
			            	deptname: encodeURI(Ext.getCmp('al_deptid').getRawValue())
			            };
			            Ext.apply(store.proxy.extraParams, new_params);
			        });
			        store.reload(); 
	            }
	        }
		 }
	},{
			xtype : 'combo',
			width : 155,
			fieldLabel : '车牌号',
			labelWidth: 45,
			id:'alarm_carnumber',
			itemId:'carnumber',
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
			width : 180,
			fieldLabel : '报警类型',
			labelWidth: 60,
			id:'alarm_alarmkey',
			labelAlign: 'right',
			store : 'AlarmTypeStore',
			displayField : 'alarmname',
			valueField : 'alarmkey'
		},{
			xtype : 'datetimefield',
			width : 210,
			maxLength : 20,
			id : 'alarm_stime',
			fieldLabel : '报警时间',
			labelWidth: 60,
//			format:'Y-m-d 00:00:00',
			value : new Date(),
			labelAlign: 'right'
		},{
			xtype : 'datetimefield',
			width : 165,
			maxLength : 20,
			id : 'alarm_etime',
			fieldLabel : '至',
			labelWidth: 15,
//			format:'Y-m-d 23:59:59',
			value : new Date(),
			labelAlign: 'right'
		}
    ],
    buttons : [{
		xtype: 'button',
		text : '查询',
		tooltip : '查询',
		iconCls : 'common-search-icon',
		//action: 'search',
		handler: function(button){
    		var con = Ext.create("AlarmApp.controller.AlarmCtrl");
    		con.search();
        }
	},{
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
	    //action : 'resetcar',
    	handler: function(button){
        	Ext.getCmp('al_deptid').setValue("");
        	Ext.getCmp('alarm_alarmkey').setValue("");
        	Ext.getCmp('alarm_carnumber').setValue("");
        	Ext.getCmp('alarm_stime').setValue("");
        	Ext.getCmp('alarm_etime').setValue("");
        }
	}]

});

