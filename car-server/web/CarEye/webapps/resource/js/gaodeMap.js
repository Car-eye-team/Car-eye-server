var _mapgaode = {//初始化地图
	
	
	//地图定位
	locationMap:function(posX, posY){
	
	},
	
	
	//地图定位,带关闭提示框
	locationMapCloseWindow:function(posX, posY,carnumber,carstatus,icon,direction,flag,colortype){
		var infoWindow;
		if(posX && posY){
			  //mapObj.clearMap();
				if(icon == null || icon == ""){
					if(carstatus.indexOf("在线") != -1){
						icon = window.BIZCTX_PATH +"/resource/images/inline_1.png";
					}else if(carstatus.indexOf("离线") != -1){
						icon = window.BIZCTX_PATH +"/resource/images/offline_1.png";
					}else if(carstatus.indexOf("报警") != -1){
						icon = window.BIZCTX_PATH +"/resource/images/warn_1.png";
					}else if(carstatus.indexOf("停车") != -1){
						icon = window.BIZCTX_PATH +"/resource/images/stop_1.png";
					}else if(carstatus.indexOf("熄火") != -1){
						icon = window.BIZCTX_PATH +"/resource/images/shutdown_1.png";
					}else if(carstatus.indexOf("行驶") != -1){
						icon = window.BIZCTX_PATH +"/resource/images/drivering_1.png";
					}else if(carstatus.indexOf("未定位") != -1){
						icon = window.BIZCTX_PATH +"/resource/images/stop_1.png";
					}else{
						icon = window.BIZCTX_PATH +"/resource/images/offline_1.png";
					}
				}
			
			   var marker = new AMap.Marker({                   
				   map:mapObj,                  
				   position:new AMap.LngLat(posX,posY), //位置
				   icon:icon,
				   title:carnumber,
				   angle:direction,
				   topWhenClick:true
			   });
			   
			   
			    //自定义点标记内容  
			    var markerContent = document.createElement("div");
			    markerContent.className = "gaodemarkerContentStyle";
			    //点标记中的图标
			    var markerImg= document.createElement("img");
			    markerImg.className="markerlnglat";
			    markerImg.src=icon;   
			    markerContent.appendChild(markerImg);
			      
			    //点标记中的文本
			    var markerSpan = document.createElement("span");
			    markerSpan.innerHTML = carnumber;
			    markerContent.appendChild(markerSpan);
			    marker.setContent(markerContent);//更新点标记内容
			   
			   if(!flag){
				 mapObj.setZoom(16);
				 mapObj.setCenter(marker.getPosition());  //标注点显示为地图中心点
				}
				
			   AMap.event.addListener(marker,'click',function(){ //鼠标点击marker弹出自定义的信息窗体
			        Ext.Ajax.request( {
					  url: window.BIZCTX_PATH + '/terminalpositionjson/queryCarTerminalDetail.action', //请求的服务器地址
				      params:{ 
				      		carnumber : carnumber
				      },
					  method : 'POST',  
					  timeout : 5000,
					  success : function(response) {
						 var text = response.responseText;
						 var objtext = eval( "(" + text + ")" );//转换后的JSON对象 
						 var obj = objtext.result;
						 var carid =  obj.data.carid;
						 var blng =  obj.data.blng;
						 var blat =  obj.data.blat;
						 var glng =  obj.data.glng;
						 var glat =  obj.data.glat;
						 var carnumber = obj.data.carnumber;
						 var address = obj.data.address;
						 var carstatus = obj.data.carstatus;
						 var createtime = obj.data.createtime;
						 var speed = obj.data.speed;
						 var direction = obj.data.direction;
						 var deptname = obj.data.blocname;
						 var mileage = obj.data.mileage;
						 var icon = obj.data.icon;
						 var terminalnum = obj.data.terminal;
						 var cartype = obj.data.cartype==null?"":obj.data.cartype;
						 
						 var drivername = obj.data.drivername==null?"":obj.data.drivername;
						 var phone = obj.data.phone==null?"":obj.data.phone;
						 var followphone = obj.data.followphone==null?"":obj.data.followphone;
						 var drivecrednum = obj.data.drivecrednum==null?"":obj.data.drivecrednum;
						var info = "<table style='font-size: 12px;text-align: right;'><tr><td><font color = '#0000ff'><b>单位：</b></font></td><td style='text-align: left'><font color='#004a91'>"+deptname+"</font></td><td width='75px;'><font color = '#0000ff'><b>驾驶员：</b></font></td><td style='text-align: left'><font color='#004a91'>"+drivername+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>设备：</b></font></td><td style='text-align: left'><font color='#004a91'>"+terminalnum+"</font></td><td width='75px;'><font color = '#0000ff'><b>司机手机号：</b></font></td><td style='text-align: left'><font color='#004a91'>"+phone+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>车牌：</b></font></td><td style='text-align: left'><font color='#004a91'>"+carnumber+"</font></td><td width='75px;'><font color = '#0000ff'><b>车型：</b></font></td><td style='text-align: left'><font color='#004a91'>"+cartype+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>速度：</b></font></td><td style='text-align: left'><font color='#004a91'>"+speed+"(km/h)</font></td><td width='75px;'><font color = '#0000ff'><b>车辆状态：</b></font></td><td style='text-align: left'><font color='#004a91'>"+chageCarStatus(carstatus)+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>时间：</b></font></td><td style='text-align: left'><font color='#004a91'>"+createtime+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>位置：</b></font></td><td colspan='3' style='text-align: left'><font color='#004a91'>"+address+"</font></td></tr></table>"; 
					       
					    infoWindow = new AMap.InfoWindow({ 
					        content:info,  //使用默认信息窗体框样式，显示信息内容
					        offset:new AMap.Pixel(0, 0)//-113, -140
					    });
						infoWindow.open(mapObj,marker.getPosition()); 
			   		},
					failure : function() {
					}
				});
			});
			   //marker添加至map中
			  markermap.put(carnumber,marker,2);
		}

	},
	
	//地图定位,带提打开示框
	locationMapWindow:function(posX, posY,motorcade,carnumber,carstatus,speed,addr,createtime){
		if(posX != "" && posY !=""){
			
		}else{
		
		}
	}	
}