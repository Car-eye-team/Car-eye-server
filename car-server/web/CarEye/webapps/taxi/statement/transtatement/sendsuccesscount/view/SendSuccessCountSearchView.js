Ext.define('SendSuccessCountApp.view.SendSuccessCountSearchView' ,{
    extend: 'Ext.form.Panel',
    border : true,
    alias : 'widget.sendSuccessCountSearchView',
    title: '派车成功统计搜索',
    
	region: "north",
	frame: true,
	layout:{
		type : 'table',
		align : 'right',
		columns:3
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:80
	},
	height:120,
	collapsible: true,
    collapseMode: "mini",
    split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	items:[{
					xtype : 'datetimefield',
					width :220,
					id : 'cd_stime',
					maxLength : 20,
					fieldLabel : '开始时间',
					labelWidth: 80,
					editable:false,
					labelAlign: 'right'
				},{
					xtype : 'datetimefield',
					width : 220,
					editable:false,
					id : 'cd_etime',
					maxLength : 20,
					fieldLabel : '结束时间',
					labelWidth: 80,
					labelAlign: 'right'
				},{
					xtype : 'comboboxtree',
					fieldLabel : '企业',
					id : 'cm_blocid',
					labelWidth: 40,
					width :220,
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
							var store = Ext.getCmp('cm_blocid').store;
							store.clearFilter(true);
							store.on('beforeload', function (store, options) {
						        var new_params = { 
						        	blocname: encodeURI(Ext.getCmp('cs_blocid').getRawValue())
						        };
						        Ext.apply(store.proxy.extraParams, new_params);
						    });
						    store.reload(); 
						}
						}
						}
				},{  
		            xtype : 'textfield',
					fieldLabel : '车牌号',
					width :220,
				    labelWidth: 80,
					id : 'cd_carnumber',
				    minChars:1,
					labelAlign: 'right'
				},{  
		            xtype : 'textfield',
					fieldLabel : '司机姓名',
					width :220,
				    labelWidth: 80,
					id : 'cd_drivername',
				    minChars:1,
					labelAlign: 'right'
				}],
	              buttons:[{
						xtype: 'button',
						text : '查询',
						id : 'mailset_query_text',
						tooltip : '查询',
						iconCls : 'common-search-icon',
//						action: 'search',
			            handler: function(button){
			        		var con = Ext.create("SendSuccessCountApp.controller.SendSuccessCountCtrl");
			        		con.search();
			            }
					},{
					    text : '重置',
					    tooltip : '清空查询条件',
					    iconCls : 'common-reset-icon',
				        handler: function(button){
				        	Ext.getCmp('cd_carnumber').setValue("");
				        	Ext.getCmp('cm_blocid').setValue("");
				        	Ext.getCmp('cd_drivername').setValue("");
				        	Ext.getCmp('cd_stime').setValue("");
				        	Ext.getCmp('cd_etime').setValue("");
				        }
					}]
			});
