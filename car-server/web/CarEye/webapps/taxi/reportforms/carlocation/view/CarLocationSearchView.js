Ext.define('CarLocationApp.view.CarLocationSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.carLocationSearchView',
    title: '车辆在线情况搜索',
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
				fieldLabel : '企业',
				id : 'cl_blocid',
				itemId:'blocid',
				width:200,
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
			            	var store = Ext.getCmp('cl_deptid').store;
							store.clearFilter(true);
							store.on('beforeload', function (store, options) {
					            var new_params = { 
					            	blocname: encodeURI(Ext.getCmp('cl_deptid').getRawValue())
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
				labelWidth: 60,
				id : 'cl_carnumber',
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
				xtype : 'textfield',
		        width : 200,
		        maxLength : 20,
				labelAlign: 'right',
		        labelWidth: 60,
		        id : 'cl_terminal',
		        fieldLabel : '终端号码'
			},{
				xtype : 'combo',
				width : 180,
				maxLength : 20,
				id : 'cl_carstatus',
				store :"CarStatusStore",
				editable: false,
				valueField : 'id',
				displayField : 'value',
				fieldLabel : '车辆状态',
				labelWidth: 60,
				labelAlign: 'right'
			},{
				xtype : 'combo',
				width : 180,
				maxLength : 20,
				id : 'cl_devicetype',
				store :"DeviceTypeStore",
				editable: false,
				valueField : 'typeid',
				displayField : 'typename',
				fieldLabel : '设备类型',
				labelWidth: 60,
				labelAlign: 'right'
			},{
				xtype : 'combo',
				width : 200,
				maxLength : 20,
				id : 'cl_cartype',
				itemId : 'cl_cartype',
				store :"CarTypeStore",
				editable: false,
				displayField : 'typename',
				valueField : 'id',
				fieldLabel : '车辆类型',
				labelWidth: 60,
				labelAlign: 'right'
			},{
				xtype : 'combo',
				width : 200,
				maxLength : 20,
				itemId : 'cl_carusename',
				id : 'cl_carusename',
				store :"CarUseStore",
				editable: false,
				displayField : 'usename',
				valueField : 'usename',
				fieldLabel : '车辆用途',
				labelWidth: 60,
				labelAlign: 'right'
			},{
				xtype : 'fieldcontainer',
				fieldLabel : '',
				width:255,
				labelAlign: 'left',
				labelWidth : 50,
                layout : 'hbox',
                items : [{
					xtype : 'combo',
					fieldLabel : '省',
					labelWidth : 15,
					name : 'sprovince',
					itemId : 'province',
					id : 'province',
					store :'ProvinceStore',
					displayField : 'entryValue',
					valueField : 'entryKey',
					width:85,
					labelAlign: 'left',
					allowBlank : true,
					cls : 'x-textfield',
					emptyText:'请选择',
				   typeAhead:false, 
	               editable:false
				},{  
		            xtype : 'combo',
					fieldLabel : '市',
					labelWidth : 15,
					name : 'scity',
					itemId : 'city',
					id : 'city',
					store :'CityStore',
					displayField : 'entryValue',
					valueField : 'entryKey',
					width:85,
					allowBlank : true,
					cls : 'x-textfield',
					emptyText:'请选择',
					typeAhead:false, 
		            editable:false
				},{  
		            xtype : 'combo',
					fieldLabel : '县',
					labelWidth : 15,
					name : 'sdistrict',
					itemId : 'district',
					id : 'district',
					store :'DistrictStore',
					displayField : 'entryValue',
					valueField : 'entryKey',
					width:85,
					allowBlank : true,
					cls : 'x-textfield',
					emptyText:'请选择',
					typeAhead:false, 
		            editable:false
				}]
			},{
				xtype : 'datetimefield',
				width : 180,
				maxLength : 20,
				id : 'cl_stime',
				fieldLabel : '创建时间',
				labelWidth: 60,
				labelAlign: 'right'
			},  {
				xtype : 'datetimefield',
				width : 180,
				maxLength : 20,
				id : 'cl_etime',
				fieldLabel : '至',
				labelWidth: 60,
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
//	    action : 'reset',
        handler: function(button){
        	button.up('form').getForm().reset();
        }
	}]

});

