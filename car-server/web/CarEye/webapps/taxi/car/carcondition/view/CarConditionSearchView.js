Ext.define('CarConditionApp.view.CarConditionSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.carConditionSearchView',
    itemId :'carConditionSearchView',
    title: '车辆在线时长搜索',
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
    items : [
    		  {
				xtype : 'comboboxtree',
				fieldLabel : '企业',
				labelWidth: 30,
				id : 'c_blocid',
				itemId:'blocid',
				width:160,
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
				width: 170,
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
				xtype : 'monthfield',
				width :200,
				id : 'c_month',
				format: 'Y-m'  ,
				fieldLabel : '月份',
				labelWidth: 60,
				labelAlign: 'right',
				editable:false 
			}
			],
	buttons : [{
									xtype: 'button',
									text : '查询',
									tooltip : '查询',
									iconCls : 'common-search-icon',
									//action: 'search',
									handler: function(button){
						        		var con = Ext.create("CarConditionApp.controller.CarConditionCtrl");
						        		con.search();
					                }
								},{
								    text : '重置',
								    tooltip : '清空查询条件',
								    iconCls : 'common-reset-icon',
								    //action : 'resetcar',
						        	handler: function(button){
						        		button.up('form').getForm().reset();
						            }
			}]

});

