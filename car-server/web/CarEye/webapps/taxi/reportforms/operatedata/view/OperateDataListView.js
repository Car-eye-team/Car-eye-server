Ext.define('OperateDataApp.view.OperateDataListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.operateDataListView',
			title: '营运信息列表',
			region: "center",
   			border: true,
			frame: true,
			store: "OperateDataListStore", 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true},//grid中的内容能够复制
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'ID', width:60, dataIndex: 'id', hidden: true },
			{ header: '企业ID', width:100, dataIndex: 'blocid', hidden: true },
			{ header: '车辆ID', width:100, dataIndex: 'carid', hidden: true },
			{ header: '企业', width:150, dataIndex: 'blocname', sortable: true,locked:true },
			{ header: '车牌号', width:80, dataIndex: 'carnumber', sortable: true,locked:true },
			{ header: '设备号', width:100, dataIndex: 'terminal', sortable: true,locked:true},
			{ header: '创建时间', width:130, dataIndex: 'createtime', sortable: true },
			{ header: '营运ID', width:100, dataIndex: 'runid', sortable: true },
			{ header: '人数', width:100, dataIndex: 'peoplenumber', sortable: true },
			{ header: '评价ID', width:100, dataIndex: 'evaluateid', sortable: true },
			{ header: '评价选项', width:100, dataIndex: 'options', sortable: true , renderer:function(value){
					if(value == "0"){
						return "没有做出评价";
					}else if(value==1){
						return "非常满意";
					}else if(value==2){
						return "满意";
					}else if(value==3){
						return "一般";
					}else if(value==4){
						return "不满意";
					}else if(value==5){
						return "投诉";
					}else{
						return "";
					}
			}},
			{ header: '单位代码', width:100, dataIndex: 'companycode', hidden: true },
			{ header: '司机代码', width:100, dataIndex: 'drivercode', sortable: true },
			{ header: '驾驶员唯一编号', width:100, dataIndex: 'driverid', sortable: true },
			{ header: '上车时间', width:130, dataIndex: 'stime', sortable: true },
			{ header: '下车时间', width:100, dataIndex: 'etime', sortable: true },
			{ header: '计程公里', width:100, dataIndex: 'mileage', sortable: true },
			{ header: '空驶公里', width:100, dataIndex: 'airmileage', sortable: true },
			{ header: '附加费', width:100, dataIndex: 'fuelsurcharge', sortable: true },
			{ header: '等待计时时间', width:100, dataIndex: 'waitingtime', sortable: true },
			{ header: '交易金额', width:100, dataIndex: 'tradeamount', sortable: true },
			{ header: '当前车次', width:100, dataIndex: 'vehicletrips', sortable: true },
			{ header: '交易时间', width:130, dataIndex: 'tradetime', hidden: true },
			{ header: '交易类型', width:100, dataIndex: 'tradetype', sortable: true , renderer:function(value){
					if(value == 0){
						return "现金交易";
					}else if(value == 1){
						return "M1 卡交易";
					}else if(value==3){
						return "CPU卡交易";
					}else if(value==9){
						return "其他";
					}else if(value==192){
						return "合乘现金交易";
					}else if(value==193){
						return "合乘M1 卡交易";
					}else if(value==195){
						return "合乘CPU卡交易";
					}else{
						return "";
					}
			}},
			{ header: '卡类型', width:100, dataIndex: 'cardtype', hidden: true , renderer:function(value){
					if(value == 1){
						return "M1卡";
					}else if(value==2){
						return "CPU电子钱包";
					}else if(value==3){
						return "CPU电子现金";
					}else{
						return "";
					}
			}},
			{ header: '卡厂商', width:100, dataIndex: 'cardoem', hidden: true , renderer:function(value){
					if(value==1){
						return "天府通";
					}else if(value==2){
						return "和信通";
					}else{
						return "";
					}
			}},
			{ header: '物理卡号', width:100, dataIndex: 'csn', hidden: true },
			{ header: '交易卡号', width:100, dataIndex: 'tradecardno', hidden: true },
			{ header: '硬件交易流水号', width:100, dataIndex: 'tradeseq', hidden: true },
			{ header: 'SAM卡卡号', width:100, dataIndex: 'samno', hidden: true },
			{ header: 'SAM交易序号', width:100, dataIndex: 'samseq', hidden: true },
			{ header: '卡交易金额', width:100, dataIndex: 'cardtradeamount', sortable: true },
			{ header: '交易后余额', width:100, dataIndex: 'tradebalance', hidden: true },
			{ header: '空车报警信息', width:100, dataIndex: 'kalarminfo', sortable: true },
			{ header: '空车状态信息', width:100, dataIndex: 'kstateinfo', sortable: true },
			{ header: '空车纬度', width:100, dataIndex: 'klat', sortable: true },
			{ header: '空车经度', width:100, dataIndex: 'klng', sortable: true },
			{ header: '空车高程', width:100, dataIndex: 'kaltitude', sortable: true },
			{ header: '空车速度', width:100, dataIndex: 'kspeed', sortable: true },
			{ header: '空车方向', width:100, dataIndex: 'kdirection', sortable: true },
			{ header: '空车时间', width:130, dataIndex: 'ktime', sortable: true },
			{ header: '空车百度地址', width:200, dataIndex: 'kaddress', sortable: true },
			{ header: '重车报警信息', width:100, dataIndex: 'zalarminfo', sortable: true },
			{ header: '重车状态信息', width:100, dataIndex: 'zstateinfo', sortable: true },
			{ header: '重车纬度', width:100, dataIndex: 'zlat', sortable: true },
			{ header: '重车经度', width:100, dataIndex: 'zlng', sortable: true },
			{ header: '重车高程', width:100, dataIndex: 'zaltitude', sortable: true },
			{ header: '重车速度', width:100, dataIndex: 'zspeed', sortable: true },
			{ header: '重车方向', width:100, dataIndex: 'zdirection', sortable: true },
			{ header: '重车时间', width:130, dataIndex: 'ztime', sortable: true },
			{ header: '重车百度地址', width:200, dataIndex: 'zaddress', sortable: true },
			{ header: '评价选项扩展', width:100, dataIndex: 'extend', sortable: true }
			],
			dockedItems: [
			              {
			                  xtype: 'toolbar',
			                  layout: {
							        overflowHandler: 'Menu'
							  },   //溢出隐藏
			                  dock: 'top',
			                  items: [{
										text:'Excel导出',
										id: '18070601',
						                iconCls:'common-excel-icon',
						                //action : 'export',
						                 handler: function(button){
                                            var con = Ext.create("OperateDataApp.controller.OperateDataCtrl");
                                            con.exportOperateData(button);
                                         }
								    }]
            }],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "OperateDataListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
		
});

