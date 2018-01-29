Ext.define('CarhistoryApp.view.CarhistorySearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.carhistorySearchView',
    title: '车辆历史信息搜索',
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
		width:200,
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
			width : 200,
			fieldLabel : '车牌号',
			labelWidth: 45,
			id:'carhistory_carnumber',
			itemId:'carnumber',
			labelAlign: 'right',
			store : 'CarPageListStore',//CarPageListStore    CarListStore
			displayField : 'carnumber',
			valueField : 'carnumber',
			pageSize : 10,
			minChars:1,
			matchFieldWidth:false,
			listConfig :{
				width:235
			}
			
		},{
			xtype : 'textfield',
			width : 180,
			fieldLabel : '设备号',
			labelWidth: 45,
			id:'carhistory_terminal',
			labelAlign: 'right'
		},{
	        xtype : 'combo',
			width : 160,
			fieldLabel : '是否定位',
			labelWidth: 60,
			id:'carhistory_gpsflag',
			labelAlign: 'right',
			store : 'GpsFlagStore',
			displayField : 'value',
			valueField : 'id'
		},{
	        xtype : 'combo',
			width : 160,
			fieldLabel : '在线状态',
			labelWidth: 60,
			id:'carhistory_carstatus',
			labelAlign: 'right',
			store : 'CarStatusStore',
			displayField : 'value',
			valueField : 'id'
		},{
			xtype : 'datetimefield',
			width : 200,
			maxLength : 20,
			id : 'carhistory_stime',
			fieldLabel : '定位开始时间',
			labelWidth: 80,
			labelAlign: 'right'
		},{
			xtype : 'datetimefield',
			width : 200,
			maxLength : 20,
			id : 'carhistory_etime',
			fieldLabel : '定位结束时间',
			labelWidth: 80,
			labelAlign: 'right'
		}
    ],
    buttons : [{
		xtype: 'button',
		text : '查询',
		tooltip : '查询',
		iconCls : 'common-search-icon',
		handler: function(button){
    		var con = Ext.create("CarhistoryApp.controller.CarhistoryCtrl");
    		con.search();
        }
	},{
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
    	handler: function(button){
        	Ext.getCmp('al_deptid').setValue("");
        	Ext.getCmp('carhistory_terminal').setValue("");
        	Ext.getCmp('carhistory_carnumber').setValue("");
        	Ext.getCmp('carhistory_gpsflag').setValue("");
        	Ext.getCmp('carhistory_carstatus').setValue("");
        	Ext.getCmp('carhistory_stime').setValue("");
        	Ext.getCmp('carhistory_etime').setValue("");
        }
	}]

});

