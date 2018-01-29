Ext.define('PassageStatisticApp.view.PassageStatisticSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.passageStatisticSearchView',
    title: '乘客人数统计搜索',
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
		        xtype : 'textfield',
		        width : 180,
		        maxLength : 20,
				labelAlign: 'right',
		        labelWidth: 60,
		        id : 'cl_terminal',
		        fieldLabel : '终端号码'
			},{
		        xtype : 'combo',
				width : 160,
				fieldLabel : '车牌号码',
				labelWidth: 60,
				id:'cl_carnumber',
				itemId:'carnumber',
				labelAlign: 'right',
				store : 'CarListStore',
				displayField : 'carnumber',
				valueField : 'carnumber'
			},{
				xtype : 'datetimefield',
				width : 180,
				maxLength : 20,
				id : 'cl_stime',
				fieldLabel : '上传时间',
				labelWidth: 60,
				labelAlign: 'right'
			},  {
				xtype : 'datetimefield',
				width : 140,
				maxLength : 20,
				id : 'cl_etime',
				fieldLabel : '至',
				labelWidth: 20,
				labelAlign: 'right'
			}
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询乘客人数',
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

