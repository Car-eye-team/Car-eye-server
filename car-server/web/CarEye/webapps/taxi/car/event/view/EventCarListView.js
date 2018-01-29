Ext.define('EventApp.view.EventCarListView' ,{
	extend : 'Ext.window.Window',
    border : false,
    width : 800,
    height : 350,
    id : 'eventcargrid',
    alias : 'widget.eventCarListView',
    title: '车辆事件列表',
	layout : 'form',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	closeAction : 'destroy',
	items : [ {
		    xtype: 'grid',
		    autoWidth: true,
			autoHeight : true,
    		height : 320,
			frame: true,
			store: "EventCarListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'id',  width:100, dataIndex: 'careventid', hidden: true },
			{ header: '企业名称',  width:100, dataIndex: 'blocid', hidden: true },
			{ header: '用户ID',  width:100, dataIndex: 'userid', hidden: true },
			{ header: '车牌号',  width:100, dataIndex: 'carnumber', sortable: true },
			{ header: '事件内容', width:250,dataIndex: 'content', sortable: true },
			{ header: '事件ID', width:200,dataIndex: 'eventid', hidden: true },
			{ header: '事件报告',  width:100, dataIndex: 'report', sortable: true , renderer:function(value){
					if(value == 1){
						return "未报告";
					}else if(value==2){
						return "报告";
					}else{
						return "未报告";
					}
			}},
			{ header: '处理结果',  width:100, dataIndex: 'result', sortable: true , renderer:function(value){
					if(value == 1){
						return "成功 ";
					}else if(value==2){
						return "失败";
					}else{
						return "";
					}
			}},
			{ header: '流水号',  width:100, dataIndex: 'seq', hidden: true },
			{ header: '创建时间',  width:200, dataIndex: 'createtime', sortable: true }
			
			
	],
			
			dockedItems: [
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                      {
								        xtype : 'textfield',
								        width : 200,
								        maxLength : 20,
								        labelWidth: 60,
										labelAlign: 'right',
								        id : 'car_content',
								        fieldLabel : '事件内容'
									},{
										xtype : 'datetimefield',
										width :200,
										id : 'car_stime',
										maxLength : 20,
										fieldLabel : '开始时间',
										labelWidth: 60,
										labelAlign: 'right'
									},{
										xtype : 'datetimefield',
										width : 200,
										id : 'car_etime',
										maxLength : 20,
										fieldLabel : '结束时间',
										labelWidth: 60,
										labelAlign: 'right'
									},"->",{
										xtype: 'button',
										text : '查询',
										id : 'mailset_query_eventcar',
										tooltip : '查询',
										iconCls : 'common-search-icon',
										action: 'searcheventcar'
									}, {
									    text : '重置',
									    tooltip : '清空查询条件',
									    iconCls : 'common-reset-icon',
									    //action : 'reseteventcar',
								        	handler: function(button){
								        	Ext.getCmp('car_content').setValue("");
								        	Ext.getCmp('car_stime').setValue("");
								        	Ext.getCmp('car_etime').setValue("");
								        }
									}
			                  ]
			              },
			             
			              {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                     {
							        text:'更新事件',
							        tooltip:'保存事件信息',
							        iconCls:'edit',
							        action : 'editcar'
							    },'-',{
							        text:'删除事件',
							        tooltip:'删除事件信息',
							        iconCls:'delete',
							        action : 'deletecar'
							    },'-',{
							        text:'删除所有事件',
							        tooltip:'删除事件信息',
							        iconCls:'delete',
							        action : 'deleteallcar'
							    }
			                  ]
			              }
			          ],

			bbar : {
				xtype : 'pagingtoolbar',
				store: "EventCarListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
	}]
});



