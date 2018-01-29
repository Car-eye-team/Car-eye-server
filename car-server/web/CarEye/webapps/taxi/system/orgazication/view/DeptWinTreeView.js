Ext.define('OrgazicationDeptApp.view.DeptWinTreeView' ,{
    extend: 'Ext.tree.Panel', 
    alias : 'widget.deptWinTreeView',
	border : false, // 没有边框
	autoWidth: true,
	height:400,
	frame: true,
	store: "DeptWinTreeStore",
	layout:'fit',
	id:'deptWinTreeView',
    minWidth: 175,
    animCollapse: true,
    rootVisible: false,  //默认不显示根节点
    listeners: {
        itemclick: {
            fn: function(view,record,item,index,e){
            	Ext.getCmp('move_blocid').setValue(record.get('id'));
            	Ext.getCmp('move_blocname').setValue(record.get('text'));
            }
        }
    },
    dockedItems: [{
	      xtype: 'toolbar',
	      dock: 'top',
	      items: [
	      	{
	      			xtype : 'comboboxtree',
					id : 'dwt_blocid',
					fieldLabel : '企业',
					labelWidth: 60,
					width:250,
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
				            	var store = Ext.getCmp('dwt_blocid').store;
								store.clearFilter(true);
								store.on('beforeload', function (store, options) {
									var blocname = Ext.getCmp('dwt_blocid').getRawValue();
						            var new_params = { 
						            	blocname: encodeURI(blocname)
						            };
						            Ext.apply(store.proxy.extraParams, new_params);
						        });
						        store.reload(); 
				            }
				        }
				 	}
	      		},
	      			{
					xtype: 'button',
					text : '查询',
					tooltip : '查询',
					iconCls : 'common-search-icon',
					handler:function(){
						var store = Ext.StoreManager.get('DeptWinTreeStore');
						var blocid = Ext.getCmp('dwt_blocid').getValue();
						
						store.clearFilter(true);
						store.on('beforeload', function (store, options) {
				           var blocname = "";
				           if(Ext.getCmp('dwt_blocid')){
					            blocname = Ext.getCmp('dwt_blocid').getRawValue();
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
				    action : 'reset',
		        	handler: function(button){
						Ext.getCmp('dwt_blocid').setValue('');
						var store = Ext.StoreManager.get('DeptWinTreeStore');
						store.clearFilter(true);
						store.reload();
		        	}
				}]
	 }]
});

