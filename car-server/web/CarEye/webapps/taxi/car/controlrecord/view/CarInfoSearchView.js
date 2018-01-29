Ext.define('CarInfoApp.view.CarInfoSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.carInfoSearchView',
    title: '车辆信息搜索',
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
								xtype : 'comboboxtree',
								fieldLabel : '企业名称',
								id : 'c_blocid',
								itemId:'blocid',
								width:200,
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
							            	var store = Ext.getCmp('c_blocid').store;
											store.clearFilter(true);
											store.on('beforeload', function (store, options) {
									            var new_params = { 
									            	blocname: encodeURI(Ext.getCmp('c_blocid').getRawValue())
									            };
									            Ext.apply(store.proxy.extraParams, new_params);
									        });
									        store.reload(); 
							            }
							        }
								 }
							},{  
					            xtype : 'combo',
								fieldLabel : '车牌号',
								width: 200,
								labelWidth: 80,
								id : 'c_carnumber',
								itemId : 'carnumber',
								labelAlign: 'right',
								store : 'CarPageListStore',//CarPageListStore    CarNumberStore
								displayField : 'carnumber',
								valueField : 'carnumber',
								pageSize : 10,
								minChars:1,
								matchFieldWidth:false,
								listConfig :{
									width:235
								}
					         },
		    
		    
		    {
//				xtype : 'combo',
				xtype : 'hidden',
				width : 200,
				maxLength : 20,
				id : 'c_cartype',
				store :"CarTypeStore",
				editable: false,
				valueField : 'id',
				displayField : 'typename',
				fieldLabel : '车辆类型',
				labelWidth: 80,
				labelAlign: 'right'
			},
   			 {
				xtype : 'datetimefield',
				width : 200,
				maxLength : 20,
				id : 'c_stime',
				fieldLabel : '开始时间',
				labelWidth: 80,
				labelAlign: 'right'
			},  {
				xtype : 'datetimefield',
				width : 200,
				maxLength : 20,
				id : 'c_etime',
				fieldLabel : '结束时间',
				labelWidth: 80,
				labelAlign: 'right'
			}
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询用户组信息',
	    iconCls : 'common-search-icon',
	    action: 'search'
	}, {
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
	    //action : 'reset',
        	handler: function(button){
        	button.up('form').getForm().reset();
        }
	}]

});

