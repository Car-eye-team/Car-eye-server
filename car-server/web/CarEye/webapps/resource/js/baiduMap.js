var map = null;
var maxLen = 20; //获取结果数据条数
var _mapbaidu = {//初始化地图
	
	cityName : null, 
	lng : null,
	lat : null,
	
	//地图定位
	locationMap:function(posX, posY){
		if(posX != "" && posY !=""){
			var marker = new BMap.Marker(new BMap.Point(posX, posY));  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			map.centerAndZoom(new BMap.Point(posX, posY), 16);     // 初始化地图,设置中心点坐标和地图级别
		}else{
			map.centerAndZoom("深圳",11);
		}
	},
	
	
	
	//自定义标注点
	customLocationMap:function(type,posX, posY,address){
		if(posX != "" && posY !=""){
			var point = new BMap.Point(posX, posY);
			var icon = "";
			if(type=="1"){
				icon = window.BIZCTX_PATH +"/resource/images/start.png";
			}else{
				icon = window.BIZCTX_PATH +"/resource/images/end.png";
			}
			var myIcon = new BMap.Icon(icon, new BMap.Size(34,43));
			var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			map.centerAndZoom(point, 7);     // 初始化地图,设置中心点坐标和地图级别
			
			var opts = {
			  width : 100,     // 信息窗口宽度
			  height: 20,     // 信息窗口高度
			  enableMessage:false//设置允许信息窗发送短息
			};
			//创建信息窗口
			var infoWindow = new BMap.InfoWindow(address,opts);
			marker.addEventListener("click", function(){this.openInfoWindow(infoWindow);});
			
		}else{
			map.centerAndZoom("深圳",11);
		}
	},
	
	//返回marker
	locationMarker:function(posX, posY,motorcade,carnumber,carstatus,speed,addr,createtime,icon,terminalnum,cartype,flag,
		drivername,phone,followphone,inspectiondate,insuranceexpires,jobcertification,roadtransport,drivecrednum,direction,seasontime,tanktime,colortype){
		
		if(posX != "" && posY !=""){
			var point = new BMap.Point(posX,posY);
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
			
			var myIcon = new BMap.Icon(icon, new BMap.Size(30,30));
			var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
			
			label = new BMap.Label("", {offset: new BMap.Size(0, -20)});	//设置小车label相对小车的偏移量
			label.setStyle({"border":"1px solid #ccc","padding":"2px"});
			if(colortype==1){
				label.setContent("<font color='red'><b>"+carnumber+"</b></font>");
			}else{
				label.setContent("<font color='#004a91'>"+carnumber+"</font>");
			}
			marker.setTop(true);
			marker.setLabel(label);
			marker.setRotation(direction);
			map.addOverlay(marker);              // 将标注添加到地图中
			
			if(!flag){
				map.centerAndZoom(point, bdzoom);     // 初始化地图,设置中心点坐标和地图级别
			}
			
			//marker添加至map中
			markermap.put(carnumber,marker,1);
			
			var opts = {
			  width : 380,     // 信息窗口宽度
			 // height: 150,     // 信息窗口高度
			  //title : infoTitle , // 信息窗口标题
			  enableMessage:false //设置允许信息窗发送短息
			};
			marker.addEventListener("click", function(){
				var infoWindow = new BMap.InfoWindow("" +
							"<table style='font-size: 12px;text-align: right;'><tr><td><font color = '#0000ff'><b>单位：</b></font></td><td style='text-align: left'><font color='#004a91'>"+deptname+"</font></td><td width='75px;'><font color = '#0000ff'><b>驾驶员：</b></font></td><td style='text-align: left'><font color='#004a91'>"+drivername+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>设备：</b></font></td><td style='text-align: left'><font color='#004a91'>"+terminalnum+"</font></td><td width='75px;'><font color = '#0000ff'><b>司机手机号：</b></font></td><td style='text-align: left'><font color='#004a91'>"+phone+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>车牌：</b></font></td><td style='text-align: left'><font color='#004a91'>"+carnumber+"</font></td><td width='75px;'><font color = '#0000ff'><b>车型：</b></font></td><td style='text-align: left'><font color='#004a91'>"+cartype==null?"":cartype+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>速度：</b></font></td><td style='text-align: left'><font color='#004a91'>"+speed+"(km/h)</font></td><td width='75px;'><font color = '#0000ff'><b>车辆状态：</b></font></td><td style='text-align: left'><font color='#004a91'>"+chageCarStatus(carstatus)+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>时间：</b></font></td><td style='text-align: left'><font color='#004a91'>"+createtime+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>位置：</b></font></td><td colspan='3' style='text-align: left'><font color='#004a91'>"+address+"</font></td></tr></table>", opts);  // 创建信息窗口对象道路运输证：
				infoWindow.enableAutoPan();
				infoWindow.disableCloseOnClick();
				this.openInfoWindow(infoWindow);
			});
		}else{
			map.centerAndZoom("深圳",11);
		}
		return marker;	
	},
		
	//聚合方式加载marker
	locationMarkerClusterer:function(markers){
		var markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers});
	},
	//地图定位,带关闭提示框
	locationMapCloseWindow:function(posX, posY,carnumber,carstatus,icon,direction,flag,colortype){
		if(posX != "" && posY !=""){
			var point = new BMap.Point(posX,posY);
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
			
			var myIcon = new BMap.Icon(icon, new BMap.Size(30,30));
			var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
			
			label = new BMap.Label("", {offset: new BMap.Size(0, -20)});	//设置小车label相对小车的偏移量
			label.setStyle({"border":"1px solid #ccc","padding":"2px"});
			if(colortype==1){
				label.setContent("<font color='red'><b>"+carnumber+"</b></font>");
			}else{
				label.setContent("<font color='#004a91'>"+carnumber+"</font>");
			}
			marker.setTop(true);
			marker.setLabel(label);
			marker.setRotation(direction);
			map.addOverlay(marker);              // 将标注添加到地图中
			
			if(!flag){
				map.centerAndZoom(point, bdzoom);     // 初始化地图,设置中心点坐标和地图级别
			}
			
			//marker添加至map中
			markermap.put(carnumber,marker,1);
			
			var opts = {
			  width : 400,     // 信息窗口宽度
			 // height: 150,     // 信息窗口高度
			  //title : infoTitle , // 信息窗口标题
			  enableMessage:false //设置允许信息窗发送短息
			};
			marker.addEventListener("click", function(){
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
						 
						var infoWindow = new BMap.InfoWindow("" +
							"<table style='font-size: 12px;text-align: right;'><tr><td><font color = '#0000ff'><b>单位：</b></font></td><td style='text-align: left'><font color='#004a91'>"+deptname+"</font></td><td width='75px;'><font color = '#0000ff'><b>驾驶员：</b></font></td><td style='text-align: left'><font color='#004a91'>"+drivername+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>设备：</b></font></td><td style='text-align: left'><font color='#004a91'>"+terminalnum+"</font></td><td width='75px;'><font color = '#0000ff'><b>司机手机号：</b></font></td><td style='text-align: left'><font color='#004a91'>"+phone+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>车牌：</b></font></td><td style='text-align: left'><font color='#004a91'>"+carnumber+"</font></td><td width='75px;'><font color = '#0000ff'><b>车型：</b></font></td><td style='text-align: left'><font color='#004a91'>"+cartype+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>速度：</b></font></td><td style='text-align: left'><font color='#004a91'>"+speed+"(km/h)</font></td><td width='75px;'><font color = '#0000ff'><b>车辆状态：</b></font></td><td style='text-align: left'><font color='#004a91'>"+chageCarStatus(carstatus)+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>时间：</b></font></td><td style='text-align: left'><font color='#004a91'>"+createtime+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>位置：</b></font></td><td colspan='3' style='text-align: left'><font color='#004a91'>"+address+"</font></td></tr>", opts);  // 创建信息窗口对象道路运输证：
						infoWindow.enableAutoPan();
						infoWindow.disableCloseOnClick();
						marker.openInfoWindow(infoWindow);
						 },
					failure : function() {
					}
			    });
			});
		}else{
			map.centerAndZoom("深圳",11);
		}
	},
	
	//地图定位,带提打开示框
	locationMapWindow:function(posX, posY,carnumber,carstatus,icon,direction,flag,colortype){
		if(posX != "" && posY !=""){
			
			map.clearOverlays(); //清除上次标记点
			var point = new BMap.Point(posX,posY);

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
			
			var myIcon = new BMap.Icon(icon, new BMap.Size(30,30));
			var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
			label = new BMap.Label("", {offset: new BMap.Size(0, -20)});	//设置小车label相对小车的偏移量
			label.setStyle({"border":"1px solid #ccc","padding":"2px"});
			label.setContent("<font color='#004a91'>"+carnumber+"</font>");
			marker.setLabel(label);
			map.addOverlay(marker);              // 将标注添加到地图中
			
			map.centerAndZoom(point, 16);     // 初始化地图,设置中心点坐标和地图级别
			
			var opts = {
			  width : 400,     // 信息窗口宽度
//			  height: 150,     // 信息窗口高度
			  //title : infoTitle , // 信息窗口标题
			  enableMessage:false//设置允许信息窗发送短息
			};
			
			if(speed == null || speed == ""){
				speed = 0;
			}
			
			marker.addEventListener("click", function(){
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
						 var address = obj.data.gaddress;
						 var carstatus = obj.data.carstatus;
						 var createtime = obj.data.createtime;
						 var speed = obj.data.speed;
						 var direction = obj.data.direction;
						 var deptname = obj.data.blocname;
						 var mileage = obj.data.mileage;
						 var icon = obj.data.icon;
						 var terminalnum = obj.data.terminal;
						 var cartype = obj.data.cartype==null?"":obj.data.cartype;;
						 
						 var drivername = obj.data.drivername==null?"":obj.data.drivername;
						 var phone = obj.data.phone==null?"":obj.data.phone;
						 var followphone = obj.data.followphone==null?"":obj.data.followphone;
						 var drivecrednum = obj.data.drivecrednum==null?"":obj.data.drivecrednum;
						var infoWindow = new BMap.InfoWindow("" +
							"<table style='font-size: 12px;text-align: right;'><tr><td><font color = '#0000ff'><b>单位：</b></font></td><td style='text-align: left'><font color='#004a91'>"+deptname+"</font></td><td width='75px;'><font color = '#0000ff'><b>驾驶员：</b></font></td><td style='text-align: left'><font color='#004a91'>"+drivername+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>设备：</b></font></td><td style='text-align: left'><font color='#004a91'>"+terminalnum+"</font></td><td width='75px;'><font color = '#0000ff'><b>司机手机号：</b></font></td><td style='text-align: left'><font color='#004a91'>"+phone+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>车牌：</b></font></td><td style='text-align: left'><font color='#004a91'>"+carnumber+"</font></td><td width='75px;'><font color = '#0000ff'><b>车型：</b></font></td><td style='text-align: left'><font color='#004a91'>"+cartype+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>速度：</b></font></td><td style='text-align: left'><font color='#004a91'>"+speed+"(km/h)</font></td><td width='75px;'><font color = '#0000ff'><b>车辆状态：</b></font></td><td style='text-align: left'><font color='#004a91'>"+chageCarStatus(carstatus)+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>时间：</b></font></td><td style='text-align: left'><font color='#004a91'>"+createtime+"</font></td></tr>" +
							"<tr><td width='75px;'><font color = '#0000ff'><b>位置：</b></font></td><td colspan='3' style='text-align: left'><font color='#004a91'>"+address+"</font></td></tr>", opts);  // 创建信息窗口对象道路运输证：
						marker.openInfoWindow(infoWindow);
						map.openInfoWindow(infoWindow,point); //开启信息窗口
					   },
					failure : function() {
					}
				});
			});
		}else{
			map.centerAndZoom("深圳",11);
		}
	},
	
//地图搜索	
baiduCssSearch:function(){
		var keyword;
		if($("#keyword").length == 0 || $("#keyword").val() == "")
			keyword = "湖南省衡阳市蒸湘区 大厦";
		else {
			keyword = $("#keyword").val();
		}
		
	$("#searchFuzzy").html("数据正在请求...");
	window.openInfoWinFuns = null;
	var options = {
	  onSearchComplete: function(results){
	    // 判断状态是否正确
	    if (local.getStatus() == BMAP_STATUS_SUCCESS){
	        var s = [];
	        s.push('<div style="font-family: arial,sans-serif; border: 1px solid rgb(153, 153, 153); font-size: 12px;">');
	        s.push('<div style="background: none repeat scroll 0% 0% rgb(255, 255, 255);">');
	        s.push('<ol style="list-style: none outside none; padding: 0pt; margin: 0pt;">');
	        openInfoWinFuns = [];
	        if (results && results.getCurrentNumPois() > 0) {
	        	var pos = results.getPoi(0).point.lng+","+results.getPoi(0).point.lat;
	        	$("#myPoint").val(pos);
	        }
	        for (var i = 0; i < results.getCurrentNumPois(); i++){
	            var marker = addMarker(results.getPoi(i).point,i);
	            var openInfoWinFun = addInfoWindow(marker,results.getPoi(i),i);
	            openInfoWinFuns.push(openInfoWinFun);
	            // 默认打开第一标注的信息窗口
	            var selected = "";
	            if(i == 0){
	                selected = "background-color:#f0f0f0;";
	                openInfoWinFun();
	            }
	            s.push('<li id="list' + i + '" style="margin: 1px 0pt; padding: 0pt 2px 0pt 3px; cursor: pointer; overflow: hidden; line-height: 25px;' + selected + '" onclick="openInfoWinFuns[' + i + ']()" >');
	            s.push('<span style="width:1px;background:url(red_labels.gif) 0 ' + ( 2 - i*20 ) + 'px no-repeat;padding-left:10px;margin-right:3px"> </span>');
	            s.push('<span style="color:red;padding-right:2px;font-weight: bold;" > ' + (i+1)+ '.</span>');
	            s.push('<span style="color:#00c;text-decoration:underline">' + results.getPoi(i).title.replace(new RegExp(results.keyword,"g"),'<b>' + results.keyword + '</b>') + '</span>');
	            s.push('<span style="color:#666;"> - ' + results.getPoi(i).address + '</span>');
	            s.push('</li>');
	            s.push('');
	        }
	        s.push('</ol></div></div>');
	        document.getElementById("searchFuzzy").innerHTML = s.join("");
	    }else{
	    	 document.getElementById("searchFuzzy").innerHTML = "未搜索到关键字:"+keyword+",记录,请输入详细所搜名称";
	    }
	  }
	};

	// 添加标注
	function addMarker(point, index){
		var marker = new BMap.Marker(point);  // 创建标注
		map.addOverlay(marker);              // 将标注添加到地图中
	  return marker;
	}
	
	// 添加信息窗口
	function addInfoWindow(marker,poi,index){
	    var name = null;
	    if(poi.type == BMAP_POI_TYPE_NORMAL){
	        name = "地址：  "
	    }else if(poi.type == BMAP_POI_TYPE_BUSSTOP){
	        name = "公交：  "
	    }else if(poi.type == BMAP_POI_TYPE_SUBSTOP){
	        name = "地铁：  "
	    }
	    // infowindow的标题
	    var infoWindowTitle = '<div style="font-weight:bold;color:#CE5521;font-size:14px">'+poi.title+'</div>';
	    // infowindow的显示信息
	    var infoWindowHtml = [];
	    infoWindowHtml.push('<table cellspacing="0" style="table-layout:fixed;width:100%;font:12px arial,simsun,sans-serif"><tbody>');
	    infoWindowHtml.push('<tr>');
	    infoWindowHtml.push('<td style="vertical-align:top;line-height:16px;width:38px;white-space:nowrap;word-break:keep-all">' + name + '</td>');
	    infoWindowHtml.push('<td style="vertical-align:top;line-height:16px">' + poi.address + ' </td>');
	    infoWindowHtml.push('</tr>');
	    infoWindowHtml.push('</tbody></table>');
	    var infoWindow = new BMap.InfoWindow(infoWindowHtml.join(""),{title:infoWindowTitle,width:200}); 
	    var openInfoWinFun = function(){
	        marker.openInfoWindow(infoWindow);
	        for(var cnt = 0; cnt < maxLen; cnt++){
	            if(!document.getElementById("list" + cnt)){continue;}
	            if(cnt == index){
	            	var lng = poi.point.lng;
	    			var lat = poi.point.lat;
	    			var poiname = poi.title;
	    			$("#myPoint").val(lng+","+lat);
	    			$("#selectedPoint").val(poiname);
	    			//showPoiOnMap(poiname,lng,lat);
	    			map.centerAndZoom(new BMap.Point(lng, lat), 12);
	                document.getElementById("list" + cnt).style.backgroundColor = "#f0f0f0";
	            }else{
	                document.getElementById("list" + cnt).style.backgroundColor = "#fff";
	            }
	        }
	    }
	    marker.addEventListener("click", openInfoWinFun);
	    return openInfoWinFun;
	}
	
	var local = new BMap.LocalSearch(map, options);
	local.setPageCapacity(maxLen);
	local.search(keyword);
}
	
	
}