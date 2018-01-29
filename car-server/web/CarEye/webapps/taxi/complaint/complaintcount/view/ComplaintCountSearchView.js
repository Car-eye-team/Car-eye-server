Ext.define('ComplaintCountApp.view.ComplaintCountSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.complaintCountSearchView',
    itemId :'complaintCountSearchView',
    title: '投诉统计搜索',
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
    	labelWidth: 60
    },
    items : [{
    			xtype : 'hidden',
				id:'tmp_blocid'
			},{	
				xtype : 'hidden',
				id:'tmp_carnumber'
			},{	
				xtype : 'comboboxtree',
								fieldLabel : '企业',
								id : 'c_blocid',
								itemId : 'blocid',
								width:180,
								labelWidth: 60,
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
				width: 150,
				labelWidth: 50,
				id : 'c_carnumber',
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
				width :170,
				anchor : '100%',
				maxLength : 20,
				id:'c_search_type',
				store :"SearchTypeStore",
				emptyText : '请选择',
				editable: false,
				valueField : 'id',
				displayField : 'searchtype',
				fieldLabel : '统计方式',
				value:"1"
			},{
				xtype : 'monthfield',
				width :200,
				id : 'c_month',
			    format: 'Y-m'  ,
				fieldLabel : '日期月份',
				labelWidth: 60,
				labelAlign: 'right',
                editable:false 
			},{
				xtype : 'combo',
				id : 'c_year',
				store :"YearStore",
				editable: false,
				valueField : 'id',
				displayField : 'year',
				fieldLabel : '日期年份',
				emptyText:'请选择',
				width:200,
				labelAlign: 'right'
			}],
	buttons : [{
				text : '查询',
				tooltip : '查询',
				iconCls : 'common-search-icon',
				action: 'search'
			}, {
				text : '重置',
				tooltip : '清空查询条件',
				iconCls : 'common-reset-icon',
				action : 'reset',
	        	handler: function(button){
	        		button.up('form').getForm().reset();
	        	}
			}]

});

