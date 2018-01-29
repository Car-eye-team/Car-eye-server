Ext.define('CarInfoApp.view.DeptWinTreeView' ,{
    extend: 'Ext.tree.Panel', 
    alias : 'widget.deptWinTreeView',
	border : false, // 没有边框
	autoWidth: true,
	height:360,
	frame: true,
	title : '企业列表',
	store: "DeptWinTreeStore",
	layout:'fit',
    id:'deptWinTreeView',
    animCollapse: true,
    rootVisible: false,  //默认不显示根节点
    listeners: {
        itemclick: {
            fn: function(view,record,item,index,e){
            	Ext.getCmp('move_deptid').setValue(record.get('id'));
            	Ext.getCmp('move_deptname').setValue(record.get('text'));
            }
        }
    },
    dockedItems: [{
	      xtype: 'toolbar',
	      dock: 'top',
	      items: [{
	      			xtype : 'comboboxtree',
					id : 'dw_deptid',
					fieldLabel : '企业',
					labelWidth: 40,
					width:200,
					labelAlign: 'right',
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
				            	var store = Ext.getCmp('dw_deptid').store;
								store.clearFilter(true);
								store.on('beforeload', function (store, options) {
									var blocname = Ext.getCmp('dw_deptid').getRawValue();
						            var new_params = { 
						            	blocname: encodeURI(blocname)
						            };
						            Ext.apply(store.proxy.extraParams, new_params);
						        });
						        store.reload(); 
				            }
				        }
				 	}
	      		},{
					xtype: 'button',
					text : '查询',
					tooltip : '查询',
					iconCls : 'common-search-icon',
					handler:function(){
						var store = Ext.StoreManager.get('DeptWinTreeStore');
						var deptid = Ext.getCmp('dw_deptid').getValue();
						
						store.clearFilter(true);
						store.on('beforeload', function (store, options) {
						   var blocname = "";
				           if(Ext.getCmp('dw_deptid')){
					            blocname = Ext.getCmp('dw_deptid').getRawValue();
				           }else{
				          	 	blocname = "";
				           }
				           Ext.apply(store.proxy.extraParams, {blocname: encodeURI(blocname)});
				        });
				        store.reload();
		
					}
				}, {
				    text : '重置',
				    tooltip : '清空查询条件',
				    iconCls : 'common-reset-icon',
//				    action : 'reset',
		        	handler: function(button){
						Ext.getCmp('dw_deptid').setValue('');
						var store = Ext.StoreManager.get('DeptWinTreeStore');
						store.clearFilter(true);
						store.reload();
		        	}
				}]
	 }]
});

