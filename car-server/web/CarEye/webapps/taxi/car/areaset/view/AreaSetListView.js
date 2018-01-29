Ext.define('AreaSetApp.view.AreaSetListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id:'sysareasetgrid',
    alias : 'widget.areaSetListView',
			region: "west",
			width:300,
			title:'系统区域',
            collapsible: true,
			store: "AreaSetListStore",
            collapseMode: "mini",
            split: true,
			frame: true,
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			
			
			selModel: { selType: 'checkboxmodel',listeners :{
				
				/**高德*/
		    	selectionchange  : function(rowmodel,select){
		    		if(maptype == 2){
		    			getItemclick();
		    		}else{
		    			
		    		}
					
				},
		     	select: function(rowmodel,select){
				},
				deselect: function(model,record,index) {//取消选中时产生的事件
				},
				
				/**百度*/
		    	select : function(rowmodel,record){
		    		if(maptype == 1){
		    			if(record.get('areatype') == 1){	//圆形
							var point = new BMap.Point(record.get('ylng'),record.get('ylat'));
							map.centerAndZoom(point, 12);
							var circle = new BMap.Circle(point,record.get('radius'),styleOptions);
							map.clearOverlays(); //清除上次标记点
							map.addOverlay(circle);
						}else if(record.get('areatype') == 2){	//矩形
							var pStart = new BMap.Point(record.get('lnglt'),record.get('latlt'));
							var pEnd = new BMap.Point(record.get('lngrb'),record.get('latrb'));
							var point = new BMap.Point(record.get('lnglt'), record.get('latlt'));
							map.centerAndZoom(point, 12);
							var polygon = new BMap.Polygon([
							  new BMap.Point(pStart.lng,pStart.lat),
							  new BMap.Point(pEnd.lng,pStart.lat),
							  new BMap.Point(pEnd.lng,pEnd.lat),
							  new BMap.Point(pStart.lng,pEnd.lat)
							], styleOptions);
							map.clearOverlays(); //清除上次标记点
							map.addOverlay(polygon);
						}else if(record.get('areatype') == 3){	//多边形
							var lngsrtArr = record.get('lngsrt').split(",");
							var latsrtArr = record.get('latsrt').split(",");
							var pointArr = [ ];
							for (var i=0 ; i< lngsrtArr.length ; i++){
							    pointArr.push(new BMap.Point(lngsrtArr[i],latsrtArr[i]));
							}
							var point = new BMap.Point(lngsrtArr[0],latsrtArr[0]);
							map.centerAndZoom(point, 12);
							var polygon = new BMap.Polygon(pointArr,styleOptions);
							map.clearOverlays(); //清除上次标记点
							map.addOverlay(polygon);
						}
		    		}else{
		    		
		    		}
					
		    	}
		      }
			}, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: 'id', width:30 ,dataIndex: 'id', hidden: true },
			{ header: '区域类型', width:70, dataIndex: 'areatype', sortable: true ,renderer : function(value){
				if(value ==1){
					return "圆形区域";
				}else if(value ==2){
					return "矩形区域";
				}else if(value ==3){
					return "多边形区域";
				}
			}},
			{ header: '区域名称', width:70, dataIndex: 'areaname', sortable: true },
			{ header: '最高速度(km/h)', width:100, dataIndex: 'maxspeed', sortable: true },
			{ header: '超速持续时间(秒)', width:100, dataIndex: 'speedtime', sortable: true },
			{ header: '区域顶灯提醒信息)', width:100, dataIndex: 'ddalertinfo	', sortable: true },
			{ header: '区域司机提醒信息', width:100, dataIndex: 'driveralertinfo', sortable: true },
			{ header: '起始时间', width:120, dataIndex: 'stime', sortable: true },
			{ header: '结束时间', width:120, dataIndex: 'etime', sortable: true },
			{ header: '创建时间', width:120, dataIndex: 'createtime', sortable: true }
			],
			
			dockedItems: [
					{
					    xtype: 'toolbar',
					    dock: 'top',
					    items: [
								{
						        xtype : 'textfield',
						        width : 200,
						        maxLength : 30,
						        labelWidth: 60,
						        id:'sas_areaname',
						        fieldLabel : '区域名称'
							}, {
										xtype: 'button',
										text : '查询',
										tooltip : '查询',
										iconCls : 'common-search-icon',
										action: 'searcharea'
									}
						    ]
						}, {
			                  xtype: 'toolbar',
			                  dock: 'top',
			                  items: [
			                      {
								        text:'添加',
								        id: '180601',
								        tooltip:'添加系统区域',
								        iconCls:'add',
								        action : 'add'
								    },'-',{
								        text:'修改',
								        id: '180602',
								        tooltip:'保存系统区域',
								        iconCls:'edit',
								        action : 'edit'
								    },'-',{
								        text:'删除',
								        id: '180603',
								        tooltip:'删除系统区域',
								        iconCls:'delete',
								        action : 'delete'
								    }
			                  ]
			              }
			          ],
			bbar : {
				xtype : 'pagingtoolbar',
				store: "AreaSetListStore",
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});

