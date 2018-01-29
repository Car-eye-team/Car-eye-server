Ext.define('DayuploadCountApp.view.DayuploadCountSearchView' ,{
    extend: 'Ext.form.Panel',
    border : true,
    alias : 'widget.dayuploadCountSearchView',
    title: '终端日上报统计搜索',
	region: "north",
	frame: true,
	layout:{
		type : 'table',
		align : 'right',
		columns:6
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:70
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
						        labelWidth: 40,
								id : 'dc_blocid',
								width:140,
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
							            	var store = Ext.getCmp('dc_blocid').store;
											store.clearFilter(true);
											store.on('beforeload', function (store, options) {
									            var new_params = { 
									            	blocname: encodeURI(Ext.getCmp('dc_blocid').getRawValue())
									            };
									            Ext.apply(store.proxy.extraParams, new_params);
									        });
									        store.reload(); 
							            }
							        }
								 }
							},{
		        xtype : 'textfield',
		        width : 170,
		        maxLength : 20,
//		        labelWidth:80,
		        id : 'dc_terminal',
		        fieldLabel : '终端设备号'
		    },{
		        xtype : 'textfield',
		        width : 160,
		        maxLength : 20,
		        labelWidth:60,
		        id : 'dc_carnumber',
		        fieldLabel : '车牌号'
		    },{
				maxLength : 20,
				valueField : 'id',
				displayField : 'value',
		        xtype : 'combo',
		        width : 170,
				editable:false,
//		        labelWidth:50,
		        id : 'dc_type',
		        store:"TypeStore",
		        fieldLabel : '功能类型'
		    },{
				xtype : 'datetimefield',
				width :190,
				id : 'dc_stime',
				maxLength : 20,
				fieldLabel : '统计开始时间',
				labelWidth: 90,
				editable: false,
				labelAlign: 'right'
			},{
				xtype : 'datetimefield',
				width : 190,
				id : 'dc_etime',
				maxLength : 20,
				fieldLabel : '统计结束时间',
				labelWidth:90,
				editable: false,
				labelAlign: 'right'
			}],
	buttons:[{
		xtype: 'button',
		text : '查询',
		id : 'mailset_query_text',
		tooltip : '查询',
		iconCls : 'common-search-icon',
//		action: 'search',
        handler: function(button){
    		var con = Ext.create("DayuploadCountApp.controller.DayuploadCountCtrl");
    		con.searchRecordList(button);
        }
	},{
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
        handler: function(button){
        	Ext.getCmp('dc_blocid').setValue("");
        	Ext.getCmp('dc_terminal').setValue("");
        	Ext.getCmp('dc_carnumber').setValue("");
        	Ext.getCmp('dc_type').setValue("");
        	Ext.getCmp('dc_stime').setValue("");
        	Ext.getCmp('dc_etime').setValue("");
        }
	}]
});
