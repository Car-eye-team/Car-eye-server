Ext.define('AccStatusApp.view.CarInfoListView' ,{
extend : 'Ext.window.Window',
    border : false,
    width : 800,
    height : 350,
    alias : 'widget.carInfoListView',
	title: '车辆位置信息列表',
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
			store: "CarInfoListStore",
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			columns: [
			{ header: 'No',xtype: 'rownumberer',sortable: false},			
			{ header: '车牌号', flex: 1.6, dataIndex: 'carnumber', sortable: true,hideable:false},
			{ header: '在线状态', flex: 1, dataIndex: 'carstatus', sortable: true,hideable:false },
			{ header: 'ACC状态', flex: 1, dataIndex: 'acc', sortable: true ,hideable:false,renderer:function(value){
				if(value == 0){
					return "关";
				}else if(value == 1){
					return "开";
				}else{
					return "";
				}
			}},
			{ header: '速度(km/h)', flex: 1.5, dataIndex: 'speed', sortable: true,hideable:false },
			{ header: '方向', flex: 1, dataIndex: 'direction', sortable: true,hideable:false,renderer:function(value){
				if(value == null || value == "null" || value == ""){
					return "";
				}
				if(value ==0){
					return "正北";
				}else if(value >0 && value <90){
					return "东北";
				}else if(value == 90){
					return "正东";
				}else if(value >90 && value <180){
					return "东南";
				}else if(value == 180){
					return "正南";
				}else if(value >180 && value <270){
					return "西南";
				}else if(value == 270){
					return "正西";
				}else if(value >270 && value <360){
					return "西北";
				}else if(value == 360){
					return "正北";
				}else{
					return value;
				}
			}},
			{ header: '经度', flex: 1, dataIndex: 'glng', sortable: false,hideable:false },
			{ header: '纬度', flex: 1, dataIndex: 'glat', sortable: false,hideable:false },
			{ header: '当前位置', flex: 3, dataIndex: 'gaddress', sortable: false,hideable:false },
			{ header: '总里程(千米)', flex: 1, dataIndex: 'mileage', sortable: true,hideable:false},
			{ header: '更新时间', flex: 2, dataIndex: 'createtime', sortable: true ,hideable:false},
			{ header: 'GPS是否有效', flex: 1, dataIndex: 'gpsflagtext', hidden: true,hideable:false },
			{ header: '车辆id', flex: 0.5, dataIndex: 'carid', hidden: true,hideable:false },
			{ header: '高度', flex: 1, dataIndex: 'altitude', hidden: true,hideable:false },
			{ header: 'gps经度', flex: 1, dataIndex: 'lng', hidden: true ,hideable:false},
			{ header: 'gps纬度', flex: 1, dataIndex: 'lat', hidden: true ,hideable:false},
			{ header: '终端设备号', flex: 1, dataIndex: 'terminal', hidden: true,hideable:false },
			{ header: '手机号', flex: 1, dataIndex: 'phone', hidden: true,hideable:false }
			],
//				 dockedItems: [
//			              {
//			                  xtype: 'toolbar',
//			                  layout: {
//							        overflowHandler: 'Menu'
//							  },   //溢出隐藏
//			                  dock: 'top',
//			                  items: [{
//									xtype : 'hidden',
//									fieldLabel : 'flag',
//									width : 150,
//									labelWidth: 40,
//									id : 'ci_ddddd'
//								},{
//									xtype : 'hidden',
//									fieldLabel : 'deptid',
//									width : 150,
//									labelWidth: 40,
//									id : 'ci_deptid'
//								},{
//									xtype : 'hidden',
//									fieldLabel : 'alarmtype',
//									width : 150,
//									labelWidth: 40,
//									id : 'ci_alarmtype'
//								},
//							{
//									xtype : 'textfield',
//									width : 150,
//									maxLength : 40,
//									id : 'ci_terminal',
//									name:'ci_terminal',
//									fieldLabel : '终端号',
//									labelWidth: 40,
//									labelAlign: 'right'
//								},{
//									xtype : 'datetimefield',
//									width : 150,
//									maxLength : 20,
//									id : 'ci_stime',
//									name:'ci_stime',
//									fieldLabel : '从',
//									labelWidth: 25,
//									editable: false,
//									labelAlign: 'right',
//									emptyText:'请选择',
//									typeAhead:false, 
//						            editable:false
//								}, {
//									xtype : 'datetimefield',
//									width : 150,
//									maxLength : 20,
//									id : 'ci_etime',
//									name:'ci_etime',
//									fieldLabel : '至',
//									labelWidth: 25,
//									editable: false,
//									labelAlign: 'right',
//									emptyText:'请选择',
//									typeAhead:false, 
//						            editable:false
//								},"->",{
//									text : '查询',
//									id : 'car_query',
//									tooltip : '查询',
//									iconCls : 'common-search-icon',
//									action: 'carsearch'
//								},"-",{
//									text : '重置',
//									id : 'car_reset',
//									tooltip : '清空查询条件',
//									iconCls : 'common-reset-icon',
////									action : 'carreset',
//						        	handler: function(button){
//						        		Ext.getCmp('ci_terminal').setValue("");
//						        		Ext.getCmp('ci_stime').setValue("");
//						        		Ext.getCmp('ci_etime').setValue("");
//						        	}
//								}]
//            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "CarInfoListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
	}]
});

