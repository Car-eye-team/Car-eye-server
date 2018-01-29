Ext.define('TerminalFunCountApp.view.TerminalFunCountSearchView' ,{
    extend: 'Ext.form.Panel',
    border : true,
    alias : 'widget.terminalFunCountSearchView',
    title: '终端功能使用统计搜索',
	region: "north",
	frame: true,
	layout:{
		type : 'table',
		align : 'right',
		columns:5
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:80
	},
	height:100,
	collapsible: true,
    collapseMode: "mini",
    split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	items:[{
								xtype : 'comboboxtree',
								fieldLabel : '企业',
								editable:true,
						        labelWidth: 30,
								id : 'tfc_blocid',
								itemId:'blocid',
								width:150,
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
								listeners: {
							        change: {
							            element: 'el', 
							            fn: function(){ 
							            	var store = Ext.getCmp('tfc_blocid').store;
											store.clearFilter(true);
											store.on('beforeload', function (store, options) {
									            var new_params = { 
									            	blocname: encodeURI(Ext.getCmp('tfc_blocid').getRawValue())
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
				width: 170,
				labelWidth: 50,
				id : 'tfc_carnumber',
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
		    },{
		        xtype : 'textfield',
		        width : 180,
		        maxLength : 20,
		        labelWidth:60,
		        id : 'tfc_terminal',
		        fieldLabel : '终端设备'
		    }],
	buttons:[{
		xtype: 'button',
		text : '查询',
		id : 'mailset_query_text',
		tooltip : '查询',
		iconCls : 'common-search-icon',
//		action: 'search',
        handler: function(button){
    		var con = Ext.create("TerminalFunCountApp.controller.TerminalFunCountCtrl");
    		con.searchTerminalFunCount();
        }
	},{
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
        handler: function(button){
        	Ext.getCmp('tfc_blocid').setValue("");
        	Ext.getCmp('tfc_terminal').setValue("");
        	Ext.getCmp('tfc_carnumber').setValue("");
        }
	}]
});
