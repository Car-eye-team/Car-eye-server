Ext.define('FMS.view.SearchCarView' ,{
	extend : 'Ext.window.Window',
    border : false,
    width : 800,
    height : 450,
    alias : 'widget.searchCarView',
    id : 'searchCarView',
    title: '车辆列表',
	layout : 'form',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : true,
	minimizable : true,
	closable : true,
	//modal : true,
	closeAction : 'destroy',
	items : [ {
		    xtype: 'grid',
		    store: 'SearchCarListStore',
		    frame: false,
			minHeight : 420,
			autoScroll:true,
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			columns: [
				{ header: '车辆id', flex: 0.5, dataIndex: 'carid', hidden: true },
				{ header: '车牌号', flex: 1, dataIndex: 'carnumber', sortable: true },
				{ header: '设备号', flex: 1.5, dataIndex: 'terminal', sortable: true },
				{ header: '车辆当前状态', width:100, dataIndex: 'carstatus', sortable: true , renderer:function(value){
						if(value == 7){
							return "在线";
						}else if(value==1){
							return "长时间离线";
						}else if(value==2){
							return "离线";
						}else if(value==3){
							return "熄火";
						}else if(value==5){
							return "行驶";
						}else if(value==4){
							return "停车";
						}else if(value==6){
							return "报警";
						}else if(value==8){
							return "未定位";
						}else{
							return "";
						}
				}},
				{ header: '经度', flex: 1, dataIndex: 'blng', sortable: true },
				{ header: '纬度', flex: 1, dataIndex: 'blat', sortable: true },
				{ header: '当前位置', flex: 3, dataIndex: 'address', sortable: true },
				{ header: '更新时间', flex: 2, dataIndex: 'createtime', sortable: true }
				],
		listeners : { 
			//单击事件
		 'itemclick' : function(view,record,item, rowIndex, e,opts){
					var view = Ext.widget('searchMapView');
					
					var address=record.data.address;
			        var lng=record.data.glng;
			        var lat=record.data.glat;
			        var carstatus = record.data.carstatus;
			        var createtime=record.data.createtime;
			        var carnumber =record.data.carnumber;
					
					Ext.getCmp('c_cwaaddr').setValue(address);
					Ext.getCmp('c_lng').setValue(lng);
					Ext.getCmp('c_lat').setValue(lat);
					Ext.getCmp('c_createtime').setValue(createtime);
					Ext.getCmp('c_carstatus').setValue(carstatus);
					Ext.getCmp('c_carnumber').setValue(carnumber);
					
					view.show();
			        
			        if(!first){
						var icons = getIcon(carstatus);
				     	var point = new AMap.LngLat(lng,lat);
						var marker = new AMap.Marker({                   
						   map:map,   
						   offset:new AMap.Pixel(0, -20),
						   position: point, //位置
						   size :new AMap.Size(30,30),
						   icon: icons
		//				   title: "<div>车牌号: " + carnumber+ (address ==undefined ? "" :"<br/>地址："+address)
		//				   + (createtime ==undefined ? "" :"<br/>时间："+createtime)+"</div>"
					    });
						
						
					    var info = [];
						info.push("<font color = '#0000ff'><b>车牌号：</b></font><font color='#004a91'>"+(carnumber ==undefined ? "" :carnumber)+"</font>"); 
						info.push("<font color = '#0000ff'><b>时间：</b></font><font color='#004a91'>"+(createtime ==undefined ? "" :createtime)+"</font>"); 
						info.push("<font color = '#0000ff'><b>位置：</b></font><font color='#004a91'>"+(address ==undefined ? "" :address)+"</font></div></div>"); 
					    infoWindow = new AMap.InfoWindow({ 
					        content:info.join("<br/>"),  //使用默认信息窗体框样式，显示信息内容
					        offset:new AMap.Pixel(0, -30)//-113, -140
					    });
					    AMap.event.addListener(marker,'click',function(){ //鼠标点击marker弹出自定义的信息窗体
					         infoWindow.open(map,marker.getPosition()); 
					    }); 
					    
			    		map.setCenter(marker.getPosition());
						marker.setMap(map);  
			        }
					
					return false;  
				}
				}
	}],
	
	bbar : {
				xtype : 'pagingtoolbar',
				store: "SearchCarListStore",   
	            displayInfo: true,   
	            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
	            emptyMsg: "没有数据" 
			}
});



