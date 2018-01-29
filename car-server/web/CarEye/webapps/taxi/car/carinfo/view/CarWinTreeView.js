Ext.define('CarInfoApp.view.CarWinTreeView' ,{
    extend: 'Ext.tree.Panel', 
    alias : 'widget.carWinTreeView',
	border : false, // 没有边框
	width:420,
	height:360,
	frame: true,
	title : '车辆树列表',
	store: "CarMoveTreeStore",
	layout:'fit',
	id:'carWinTreeView',
    minWidth: 175,
    animCollapse: true,
    rootVisible: false,  //默认不显示根节点
    viewConfig : {   //checkbox联动
         onCheckboxChange : function(e, t) {
	          var item = e.getTarget(this.getItemSelector(), this.getTargetEl()), record;
	          if (item){
		           record = this.getRecord(item);
		           var check = !record.get('checked');
		           record.set('checked', check);
		           if (check) {
			            record.bubble(function(parentNode) {
				             parentNode.set('checked', true);
				             parentNode.expand(false, true);
			            });
			            record.cascadeBy(function(node) {
				             node.set('checked', true);
				             node.expand(false, true);
			            });
		           } else {
			            record.cascadeBy(function(node) {
			            	 node.set('checked', false);
			            });
		           }
	          }
	     }
    },
    listeners: {
	  	 'itemdblclick':{
	    	
	        fn: function(view, record, item, index, event) {
	        	var store = Ext.StoreManager.get('CarMoveTreeStore');
	        	//查找响应的节点
				var treeNodeInterface = store.getNodeById(record.get('id')); 
	        	if(treeNodeInterface.hasChildNodes())
	        		return;
	        	
		        Ext.Ajax.request({
		        	url: window.BIZCTX_PATH + '/servlet/CarTree', 
		        	params : {deptid:record.get('id')},
					success : function(response, options){
						var treeNodeArray = [];
					    treeNodeArray = Ext.JSON.decode(response.responseText);
						treeNodeInterface.appendChild(treeNodeArray);
					}
				});
	        }
	    
	    }
	},
	dockedItems: [{
	      xtype: 'toolbar',
	      dock: 'top',
	      items: [{
	      			xtype : 'comboboxtree',
					id : 'cw_deptid',
					fieldLabel : '企业',
					labelWidth: 40,
					width:160,
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
				            	var store = Ext.getCmp('cw_deptid').store;
								store.clearFilter(true);
								store.on('beforeload', function (store, options) {
									var blocname = Ext.getCmp('cw_deptid').getRawValue();
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
		            xtype : 'textfield',
					fieldLabel : '车牌号',
					width: 140,
					labelWidth: 40,
					id : 'ct_carnumber',
					itemId : 'carnumber',
//					store : 'CarListStore',
					labelAlign: 'right'
//					valueField : 'carnumber',
//					displayField : 'carnumber',
//					listeners: {
//						change :{
//							element: 'el',
//							fn: function(me,newValue,oldValue){
//								var store =Ext.StoreManager.get('CarListStore');
//								store.clearFilter(true);
//								store.on('beforeload', function (store, options) {
//						            var new_params = { 
//						            	carnumber: encodeURI(Ext.getCmp('ct_carnumber').getValue())
//						            };
//						            Ext.apply(store.proxy.extraParams, new_params);
//						        });
//						        store.reload();
//							}
//						}
//					}
		         },{
					xtype: 'button',
					text : '查询',
					tooltip : '查询',
					iconCls : 'common-search-icon',
					handler:function(){
						var store = Ext.StoreManager.get('CarMoveTreeStore');
						var deptid = Ext.getCmp('cw_deptid').getValue();
						var car = Ext.getCmp('ct_carnumber').getValue();
						
						if((deptid == null || deptid =="") && (car == null || car =="")){
							Ext.Msg.alert('提示', "请先选择企业或者车牌号!");
							return;
						}
						
						var loadMarsk = new Ext.LoadMask('carWinTreeView', {  
						    msg:'数据加载中，请稍候......',    
						    disabled:false,    
						    store:store  
						});  
						store.clearFilter(true);
						store.on('beforeload', function (store, options) {
							var carnumber = "";
							var deptid = "";
							var flag = 0;
							if(Ext.getCmp('ct_carnumber')){
								carnumber = Ext.getCmp('ct_carnumber').getValue();
								flag = 1
							}
							if(Ext.getCmp('cw_deptid')){
								deptid = Ext.getCmp('cw_deptid').getValue();
							}
				            var new_params = { 
				            	carnumber: encodeURI(carnumber),
				            	querydeptid:deptid,
				            	flag:flag
				            };
				            Ext.apply(store.proxy.extraParams, new_params);
				            loadMarsk.show();
				        });
				        store.reload();
		
					}
				}, {
				    text : '重置',
				    tooltip : '清空查询条件',
				    iconCls : 'common-reset-icon',
//				    action : 'reset',
		        	handler: function(button){
						Ext.getCmp('cw_deptid').setValue('');
						Ext.getCmp('ct_carnumber').setValue('');
						var store = Ext.StoreManager.get('CarMoveTreeStore');
						store.clearFilter(true);
						store.on('beforeload', function (store, options) {
				            var new_params = { 
				            	flag :0
				            };
				            Ext.apply(store.proxy.extraParams, new_params);
				        });
						store.reload();
						
						var store1 = Ext.getCmp('cw_deptid').store;
						store1.clearFilter(true);
				        store1.reload(); 
						
		        	}
				}]
	 }]
});

