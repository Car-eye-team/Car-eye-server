Ext.define('CarInfoApp.view.ControlRecordListView' ,{
	extend : 'Ext.window.Window',
    alias : 'widget.controlRecordListView',
	title: '车辆控制记录列表',
    width : 800,
    height : 520,
	layout : 'form',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',
	items : [ {
		    xtype: 'grid',
		    autoWidth: true,
			autoHeight : true,
    		height : 490,
			frame: true,
			store: "ControlRecordListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			columns: [
			{ header: 'ID', flex: 1.5, dataIndex: 'id', hidden: true },
			{ header: '企业名称',  width:100, dataIndex: 'blocid', hidden: true },
			{ header: '企业名称',  width:100, dataIndex: 'blocname', sortable: true },
			{ header: '用户ID',  width:100, dataIndex: 'userid', hidden: true },
			{ header: '用户名',  width:100, dataIndex: 'username', sortable: true },
			{ header: '车牌号', width:100, dataIndex: 'carnumber', sortable: true },
			{ header: '流水号', width:100, dataIndex: 'seq', hidden: true },
			{ header: '处理结果', width: 100, dataIndex: 'result', sortable: true , renderer:function(value){
					if(value == 1){
						return "成功";
					}else if(value==2) {
						return "失败";
					}else{
					    return "";
					}
			}},
			{ header: '控制类型', flex: 2, dataIndex: 'controltype' ,hidden: true, renderer:function(value){
				if(value == 1){
					return "加锁";
				}else if(value==2) {
					return "解锁";
				}else{
				    return "";
				}
		} },
		//{ header: '控制内容', width: 400, dataIndex: 'controltext' ,sortable: true },
		{ header: '原始数据包', width: 200, dataIndex: 'data' ,hidden: true },
		{ header: '创建时间', width:150, dataIndex: 'createtime' }
			],
			
			dockedItems: [
			              
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                     {  
										xtype : 'combo',
										fieldLabel : '车牌号',
										width: 160,
										labelWidth: 40,
										id : 'r_carnumber',
										itemId : 'r_carnumber',
										labelAlign: 'left',
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
										xtype : 'datetimefield',
										width :160,
										id : 'r_stime',
										maxLength : 20,
										fieldLabel : '开始时间',
										labelWidth: 60,
										labelAlign: 'right'
									},{
										xtype : 'datetimefield',
										width : 160,
										id : 'r_etime',
										maxLength : 20,
										fieldLabel : '结束时间',
										labelWidth: 60,
										labelAlign: 'right'
									},
									"->",{
										xtype: 'button',
										text : '查询',
										id : 'mailset_query_text',
										tooltip : '查询',
										iconCls : 'common-search-icon',
										action: 'search'
									}, {
									    text : '重置',
									    tooltip : '清空查询条件',
									    iconCls : 'common-reset-icon',
									    //action : 'reset',
								        	handler: function(button){
								        	Ext.getCmp('r_carnumber').setValue("");
								        	Ext.getCmp('r_stime').setValue("");
								        	Ext.getCmp('r_etime').setValue("");
								        }
									}
			                  ]
			              }
			          ],
			
			
			bbar : {
				xtype : 'pagingtoolbar',
				store: "ControlRecordListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
	} ]
});