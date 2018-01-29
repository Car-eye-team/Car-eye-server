Ext.define('EvaluateCountApp.view.EvaluateCountSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.evaluateCountSearchView',
    itemId :'evaluateCountSearchView',
    title: '评论统计搜索',
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
	items: [{
				xtype : 'hidden',
				id:'tmp_blocid'
			},{	
				xtype : 'hidden',
				id:'tmp_carnumber'
			},{	
				xtype : 'comboboxtree',
				fieldLabel : '企业名称',
				id : 'al_deptid',
				itemId:'deptid',
				width:170,
				labelWidth: 60,
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
			            	var store = Ext.getCmp('al_deptid').store;
							store.clearFilter(true);
							store.on('beforeload', function (store, options) {
					            var new_params = { 
					            	deptname: encodeURI(Ext.getCmp('al_deptid').getRawValue())
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
					id : 'ec_carnumber',
					itemId : 'carnumber',
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
			},
			{		
				xtype : 'combo',
				anchor : '100%',
				maxLength : 20,
				id:'ec_search_type',
				store :"SearchTypeStore",
				emptyText : '请选择',
				editable: false,
				valueField : 'id',
				width :170,
				displayField : 'searchtype',
				fieldLabel : '统计方式',
				value:"1"
			},{
				xtype : 'monthfield',
				width :170,
				id : 'ec_month',
			    format: 'Y-m'  ,
				fieldLabel : '日期月份',
				labelWidth: 60,
				labelAlign: 'right',
                editable:false 
			},
			{
				xtype : 'combo',
				id : 'ec_year',
				store :"YearStore",
				editable: false,
				valueField : 'id',
				displayField : 'year',
				fieldLabel : '日期年份',
				emptyText:'请选择',
				width:170,
				labelAlign: 'right'
				
				}
//				,{
//					xtype : 'datetimefield',
//					width : 180,
//					maxLength : 20,
//					id : 'ota_stime',
//					fieldLabel : '开始日期',
//					labelWidth: 60,
//					labelAlign: 'right'
//				},{
//					xtype : 'datetimefield',
//					width : 180,
//					maxLength : 20,
//					id : 'ota_etime',
//					fieldLabel : '结束日期',
//					labelWidth: 60,
//					labelAlign: 'right'
//				}
		],          
	buttons : [{
				text : '查询',
				id : 'mailset_query',
				tooltip : '查询',
				iconCls : 'common-search-icon',
				handler: function(button){
					var con = Ext.create("EvaluateCountApp.controller.EvaluateCountCtrl");
					con.searchWeChatStatement(button);
				}
			}, {
				text : '重置',
				id : 'mailset_reset',
				tooltip : '清空查询条件',
				iconCls : 'common-reset-icon',
				action : 'reset',
	        	handler: function(button){
	        		button.up('form').getForm().reset();
	        	    var month = Ext.getCmp('ec_month');
		            var year = Ext.getCmp('ec_year');
	        		Ext.getCmp('ec_search_type').setValue('1')
					year.hide();
					month.show();
//	        		
//	        		  var   getCK=document.getElementsByName('x-checkbox-list');  
//					  var   j=0;  
//					   
//					  for(var   i=0;i<getCK.length;i++)  
//					  {  
//					      whichObj=getCK[i];  
//					      if(whichObj.type=="checkbox")  
//					      {  
//					          whichObj.checked=false;  
//					          j++;  
//					      }  
//					  }   
	        	}
			}]

});

