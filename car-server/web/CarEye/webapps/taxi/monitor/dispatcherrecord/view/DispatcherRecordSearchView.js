Ext.define('DispatcherRecordApp.view.DispatcherRecordSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.dispatcherRecordSearchView',
    title: '调度记录信息搜索',
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
		columns:6
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:60
	},
    items : [
			                  {
								xtype : 'comboboxtree',
								fieldLabel : '企业',
								editable:true,
						        labelWidth: 30,
								id : 'dr_blocid',
								width:180,
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
								cls : 'x-textfield',
								valueField: 'id', 
								displayField: 'text',
								emptyText:'请选择企业',
								listeners: {
							        change: {
							            element: 'el', 
							            fn: function(){ 
							            	var store = Ext.getCmp('dr_blocid').store;
											store.clearFilter(true);
											store.on('beforeload', function (store, options) {
									            var new_params = { 
									            	blocname: encodeURI(Ext.getCmp('dr_blocid').getRawValue())
									            };
									            Ext.apply(store.proxy.extraParams, new_params);
									        });
									        store.reload(); 
							            }
							        }
								 }
							}, {
					            xtype : 'textfield',
								fieldLabel : '车牌号',
								width: 130,
								labelWidth: 40,
								id : 'dr_carnumber',
								itemId : 'dr_carnumber',
								labelAlign: 'right'
//								emptyText:'请选择车辆'
                             },{
								xtype : 'combo',
								width : 120,
								name : 'dr_status',
								id:'dr_status',
								itemId:'dr_status',
								store :"StatusStore",
								editable: false,
								valueField : 'id',
								displayField : 'status',
								labelWidth : 35,
								blankText : '请选择',
								fieldLabel : '状态',
								emptyText:'请选择状态'
							},
//								{
//								xtype : 'combo',
//								width : 200,
//								name : 'dr_type',
//								id:'dr_type',
//								itemId:'dr_type',
//								store :"TypeStore",
//								editable: false,
//								valueField : 'id',
//								displayField : 'type',
//								labelWidth : 80,
//								blankText : '请选择',
//								fieldLabel : '消息类型',
//								emptyText:'请选择消息类型'
//							},
						   {
								xtype : 'datetimefield',
								width : 200,
								id : 'dr_stime',
								name:'dr_stime',
								fieldLabel : '发送开始时间',
								editable: false,
								labelAlign: 'right',
								emptyText:'请选择',
								typeAhead:false, 
								labelWidth : 80,
					            editable:false
							},  {
								xtype : 'datetimefield',
								width : 150,
								maxLength : 20,
								id : 'dr_etime',
								name:'dr_etime',
								fieldLabel : '到',
								editable: false,
								labelAlign: 'right',
								labelWidth : 25,
								emptyText:'请选择',
								typeAhead:false, 
					            editable:false
							}
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询计价器信息',
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

