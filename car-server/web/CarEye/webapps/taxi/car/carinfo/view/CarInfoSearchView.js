Ext.define('CarInfoApp.view.CarInfoSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.carInfoSearchView',
    title: '车辆信息搜索',
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
    	labelAlign: 'right',
    	labelWidth:60
	},
    items : [{
								xtype : 'comboboxtree',
								fieldLabel : '企业名称',
								id : 'c_deptid',
								itemId:'blocid',
								width:170,
								store: Ext.create('Ext.data.TreeStore', {  
							        autoLoad : 'true',
							        fields: ['text','id','parentId'], 
									root : {expanded : true,text : '企业' },
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
							            	var store = Ext.getCmp('c_deptid').store;
											store.clearFilter(true);
											store.on('beforeload', function (store, options) {
									            var new_params = { 
									            	blocname: encodeURI(Ext.getCmp('c_deptid').getRawValue())
									            };
									            Ext.apply(store.proxy.extraParams, new_params);
									        });
									        store.reload(); 
							            }
							        }
								 }
							},{
						        xtype : 'combo',
								width : 170,
								fieldLabel : '车牌号码',
								labelWidth: 60,
								id:'c_carnumber',
								itemId:'carnumber',
//							    queryDelay:800,
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
						        width : 170,
						        maxLength : 20,
								labelAlign: 'right',
						        labelWidth: 60,
						        id : 'c_terminal',
						        fieldLabel : '终端设备'
							},{
								xtype : 'combo',
								width : 170,
								maxLength : 20,
								id : 'c_carstatus',
								store :"CarStatusStore",
								editable: false,
								valueField : 'id',
								displayField : 'value',
								fieldLabel : '车辆状态',
								labelWidth: 60,
								labelAlign: 'right'
							},{
								xtype : 'combo',
								width : 170,
								maxLength : 20,
								id : 'c_devicetype',
								store :"DeviceTypeStore",
								editable: false,
								valueField : 'typeid',
								displayField : 'typename',
								fieldLabel : '设备类型',
								labelWidth: 60,
								labelAlign: 'right'
							},{
								xtype : 'combo',
								width : 170,
								maxLength : 20,
								id : 'c_cartype',
								itemId : 'c_cartype',
								store :"CarTypeStore",
								editable: false,
								displayField : 'typename',
								valueField : 'id',
								fieldLabel : '车辆类型',
								labelWidth: 60,
								labelAlign: 'right'
							},{
								xtype : 'combo',
								width : 170,
								maxLength : 20,
								id : 'c_caruse',
								store :"CarUseStore",
								editable: false,
								displayField : 'usename',
								valueField : 'id',
								fieldLabel : '车辆用途',
								labelWidth: 60,
								labelAlign: 'right'
							},{
								xtype : 'datetimefield',
								width : 170,
								maxLength : 20,
								id : 'c_stime',
								fieldLabel : '开始时间',
								labelWidth: 60,
								editable: false,
								labelAlign: 'right'
							},  {
								xtype : 'datetimefield',
								width : 170,
								maxLength : 20,
								id : 'c_etime',
								fieldLabel : '结束时间',
								labelWidth: 60,
								editable: false,
								labelAlign: 'right'
							}
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询车辆信息',
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

