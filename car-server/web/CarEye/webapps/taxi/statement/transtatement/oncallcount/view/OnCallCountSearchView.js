Ext.define('OnCallCountApp.view.OnCallCountSearchView' ,{
    extend: 'Ext.form.Panel',
    border : true,
    alias : 'widget.onCallCountSearchView',
    title: '电召数据统计搜索',
	region: "north",
	frame: true,
	layout:{
		type : 'table',
		align : 'right',
		columns:3
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:60
	},
	height:120,
	collapsible: true,
    collapseMode: "mini",
    split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	items:[{
				xtype : 'comboboxtree',
				fieldLabel : '企业',
				id : 'cm_blocid',
				width : 250,
				store : Ext.create('Ext.data.TreeStore', {
					autoLoad : 'true',
					fields : ['text', 'id', 'parentId'],
					root : {
						expanded : true,
						text : '企业'
					},
					proxy : {
						type : 'ajax',
						url : window.BIZCTX_PATH + '/servlet/DeptTree?type=200',
						reader : {
							type : 'json'
						}
					}
				}),
				rootVisible : false,
				editable : true,
				cls : 'x-textfield',
				valueField : 'id',
				displayField : 'text',
				listeners : {
					change : {
						element : 'el',
						fn : function() {
							var store = Ext.getCmp('cm_blocid').store;
							store.clearFilter(true);
							store.on('beforeload', function(store, options) {
										var new_params = {
											blocname : encodeURI(Ext
													.getCmp('cm_blocid')
													.getRawValue())
										};
										Ext.apply(store.proxy.extraParams,
												new_params);
									});
							store.reload();
						}
					}
				}
			},{
				xtype : 'textfield',
				fieldLabel : '车牌号',
				width : 250,
				id : 'cd_carnumber',
				minChars : 1,
				labelAlign : 'right'
			},{
				xtype : 'textfield',
				fieldLabel : '司机姓名',
				width : 185,
				id : 'cd_drivername',
				minChars : 1,
				labelAlign : 'right'
			},{
		        xtype : 'textfield',
		        width : 250,
		        maxLength : 20,
		        id : 'cd_cusname',
		        fieldLabel : '客户姓名'
		    },{
					xtype : 'datetimefield',
					width :250,
					id : 'cd_stime',
					maxLength : 20,
					fieldLabel : '电召时间',
					editable:false,
					labelAlign: 'right'
				},{
					xtype : 'datetimefield',
					width : 185,
					editable:false,
					id : 'cd_etime',
					maxLength : 20,
					fieldLabel : '至',
					labelWidth: 15,
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
			        		var con = Ext.create("OnCallCountApp.controller.OnCallCountCtrl");
			        		con.search(button);
			            }
					},{
					    text : '重置',
					    tooltip : '清空查询条件',
					    iconCls : 'common-reset-icon',
				        handler: function(button){
				        	Ext.getCmp('cm_blocid').setValue("");
				        	Ext.getCmp('cd_carnumber').setValue("");
				        	Ext.getCmp('cd_drivername').setValue("");
				        	Ext.getCmp('cd_cusname').setValue("");
				        	Ext.getCmp('cd_stime').setValue("");
				        	Ext.getCmp('cd_etime').setValue("");
				        }
					}]
			});
