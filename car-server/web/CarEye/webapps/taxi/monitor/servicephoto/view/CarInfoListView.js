Ext.define('PhotoSetApp.view.CarInfoListView' ,{
    extend: 'Ext.grid.Panel',
    border : true,
    id : 'carinfogridset',
    alias : 'widget.carInfoListView',
			region: "center",
			title:'司机电子服务证列表',
			store: "ServicePhotoListStore",
			frame: false,
			multiSelect: true,
			autoScroll:true,
			split: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel',listeners :{
			    select : function(rowmodel,record){
					  var carid = record.get('carid');
					  var carnumber = record.get('carnumber');
					  var blocname = record.get('blocname');
					  var servicenumber = record.get('servicenumber');
					  var drivername = record.get('drivername');
					  var driverid = record.get('driverid');
					  var starlevel = record.get('starlevel');
					  var starleveltext = chageStar(starlevel);
					  var version = record.get('version');
					  var picturepath = record.get('picturepath');
					  
					  Ext.getCmp('carid').setValue(carid);
					  Ext.getCmp('carnumber').setValue(carnumber);
					  Ext.getCmp('blocname').setValue(blocname);
					  Ext.getCmp('servicenumber').setValue(servicenumber);
					  Ext.getCmp('drivername').setValue(drivername);
					  Ext.getCmp('driverid').setValue(driverid);
					  Ext.getCmp('starlevel').setValue(starlevel);
					  Ext.getCmp('starleveltext').setValue(starleveltext);
					  Ext.getCmp('version').setValue(version);
					  Ext.getCmp('picturepath').setValue(picturepath);
					  
					  document.getElementById('service_photo').src = window.BIZCTX_PATH + picturepath;
				}
			}}, 
			columns: [
			{ header: 'carid', width:100 ,dataIndex: 'carid', hidden: true },
			{ header: '企业名称', width:150, dataIndex: 'blocname', sortable: true },
			{ header: '车牌号', width:70, dataIndex: 'carnumber', sortable: true },
			{ header: '车载号码', width:100, dataIndex: 'terminal', sortable: true },
			{ header: '司机姓名', width:70, dataIndex: 'drivername', sortable: true },
			{ header: '服务证名称', width:70, dataIndex: 'name', sortable: true },
			{ header: '照片路径', width:70, dataIndex: 'picturepath', hidden: true },
			{ header: '服务证号', width:100, dataIndex: 'servicenumber', sortable: true },
			{ header: '星级', width:70, dataIndex: 'starlevel', sortable: true,renderer:function(value){
				return chageStar(value);
			}},
			{ header: '版本', width:70, dataIndex: 'version', sortable: true },
			{ header: '有效年', width:70, dataIndex: 'validity', sortable: true }
			],
			dockedItems: [
					{
					    xtype: 'toolbar',
					    dock: 'top',
					    items: [
								  {
								xtype : 'comboboxtree',
								fieldLabel : '企业',
								editable:true,
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
								cls : 'x-textfield',
								valueField: 'id', 
								displayField: 'text',
								listeners: {
							        change: {
							            element: 'el', 
							            fn: function(){ 
							            	var store = Ext.getCmp('c_blocid').store;
											store.clearFilter(true);
											store.on('beforeload', function (store, options) {
									            var new_params = { 
									            	blocname: encodeURI(Ext.getCmp('c_blocid').getRawValue())
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
								width: 160,
								labelWidth: 40,
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
					            xtype : 'textfield',
								fieldLabel : '司机名',
								width: 160,
								labelWidth: 40,
								id : 'c_drivername',
								labelAlign: 'right'
					         },'->', {
								xtype: 'button',
								text : '查询',
								id : 'mailset_query_car',
								tooltip : '查询',
								iconCls : 'common-search-icon',
								action: 'search'
							 }, {
							    text : '重置',
							    tooltip : '清空查询条件',
							    iconCls : 'common-reset-icon',
//								    action : 'resetcar',
						        handler: function(button){
						        	Ext.getCmp('c_blocid').setValue("");
						        	Ext.getCmp('c_carnumber').setValue("");
						        }
							 }]
			              }
			          ],
		    bbar : {
				xtype : 'pagingtoolbar',
				store: "ServicePhotoListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});


function chageStar(value){
	var name = "";
	switch(value){
		case "0":
			name = "未评定";
			break;
		case "1":
			name = "一星";
			break;
		case "2":
			name = "二星";
			break;
		case "3":
			name = "三星";
			break;
		case "4":
			name = "四星";
			break;
		case "5":
			name = "五星";
			break;
	}
	return name;
}
