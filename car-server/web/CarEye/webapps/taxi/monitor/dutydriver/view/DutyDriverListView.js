Ext.define('DutyDriverApp.view.DutyDriverListView' ,{
		    extend: 'Ext.grid.Panel',
		    id : 'transactiongrid',
		    alias : 'widget.dutyDriverListView',
		    title: '当班司机查询列表',
			region: "center",
   			border: true,
			frame: true,
			store: "DutyDriverListStore", 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			store: "DutyDriverListStore",
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			//selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'No',xtype: 'rownumberer',sortable: false},
			{ header: '工号', width:100, dataIndex: 'id', hidden: true },
			{ header: '企业Id', width:100, dataIndex: 'blocid', hidden: true },
			{ header: '企业', width:180, dataIndex: 'blocname', sortable: true },
			{ header: '服务监督卡号', width:120, dataIndex: 'sscno', sortable: true },
			{ header: '用户ID', width:100, dataIndex: 'userid', hidden: true },
			{ header: '姓名', width:100, dataIndex: 'drivername', sortable: true },
			{ header: '联系电话', width:100, dataIndex: 'tel', hidden: true },
			{ header: '手机号', width:100, dataIndex: 'phone', sortable: true },
			{ header: '性别', width:50, dataIndex: 'sex', sortable: true ,renderer:function(value){
					if(value == 1){
						return "男";
					}else if(value==2){
						return "女";
					}else{
					    return "";
					 }
			}},
			{ header: '联系地址', width:100, dataIndex: 'addr', sortable: true },
			{ header: '身份证号', width:200, dataIndex: 'idnumber', sortable: true },
			{ header: '出生日期', width:100, dataIndex: 'birthday', hidden: true },
			{ header: '从业资格证', width:100, dataIndex: 'qualificationcertificate', sortable: true },
			{ header: '发证机构', width:100, dataIndex: 'certifyingauthority', sortable: true },
			{ header: '驾驶证号', width:150, dataIndex: 'drivecrednum', sortable: true },
			{ header: '驾驶证年审日期', width:100, dataIndex: 'driverannualdate', sortable: true },
			{ header: '创建时间', width:200, dataIndex: 'createtime',sortable: true }
						],
			dockedItems: [
//			              {
//			                  xtype: 'toolbar',
//			                  dock: 'top',
//			                  items: [ {
//								xtype : 'comboboxtree',
//								fieldLabel : '企业',
//								editable:true,
//						        labelWidth: 30,
//								id : 'dt_blocid',
//								width:200,
//								store: Ext.create('Ext.data.TreeStore', {  
//							        autoLoad : 'true',
//							        fields: ['text','id','parentId'], 
//									root : {expanded : true,text : '企业' },
//									proxy: {
//										 type: 'ajax',
//										 url: window.BIZCTX_PATH + '/servlet/DeptTree?type=200', 
//										 reader: {
//											 type: 'json'
//										 }
//									}
//							    }) ,
//							    rootVisible: false,
//								cls : 'x-textfield',
//								valueField: 'id', 
//								displayField: 'text',
//								listeners: {
//							        change: {
//							            element: 'el', 
//							            fn: function(){ 
//							            	var store = Ext.getCmp('dt_blocid').store;
//											store.clearFilter(true);
//											store.on('beforeload', function (store, options) {
//									            var new_params = { 
//									            	blocname: encodeURI(Ext.getCmp('dt_blocid').getRawValue())
//									            };
//									            Ext.apply(store.proxy.extraParams, new_params);
//									        });
//									        store.reload(); 
//							            }
//							        }
//								 }
//							},
////								{
////					            xtype : 'combo',
////								fieldLabel : '车牌号',
////								width: 160,
////								labelWidth: 40,
////								id : 'dt_carnumber',
////								itemId : 'carnumber',
////								store : 'CarInfoListStore',
////								labelAlign: 'right',
////								valueField : 'carnumber',
////								displayField : 'carnumber'
////					         },
//			                      {
//								        xtype : 'textfield',
//								        width : 200,
//								        maxLength : 20,
//										labelAlign: 'right',
//								        labelWidth: 80,
//								        id : 'dt_drivername',
//								        fieldLabel : '司机名称'
//									}, "->",{
//										xtype: 'button',
//										text : '查询',
//										id : 'mailset_query_text',
//										tooltip : '查询',
//										iconCls : 'common-search-icon',
//										action: 'searchtext'
//									}, {
//									    text : '重置',
//									    tooltip : '清空查询条件',
//									    iconCls : 'common-reset-icon',
//									    action : 'resettext',
//								        	handler: function(button){
//								        	Ext.getCmp('dt_drivername').setValue("");
//								            Ext.getCmp('dt_blocid').setValue("");
//							        	   // Ext.getCmp('dt_carnumber').setValue("");
//								        	
//								        	}
//									}
//			                  ]
//			              }
			          ],

			
			bbar : {
				xtype : 'pagingtoolbar',
				store: "DutyDriverListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});



