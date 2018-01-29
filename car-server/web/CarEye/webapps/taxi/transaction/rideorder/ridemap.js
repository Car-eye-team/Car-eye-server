//获取所有点的坐标
var points = [];
var mappoints =[];
var markpoints =[];

var car;   //汽车图标
var endCar;
var label; //信息标签
var endLabel;
var centerPoint;


function pointHandler(response) {
	var text = response.responseText;
	var obj = eval( "(" + text + ")" );//转换后的JSON对象
	var result = obj.result;
	
	if (result) {
		var d = obj.result.list;
		points = [];
		if(maptype==1){
			
			//百度--轨迹
			
			$(d).each(
				function(i){
					var p = new BMap.Point(this.lng, this.lat);
					p.createtime=this.createtime;
					p.address= this.address;
					p.direction= this.direction;
					p.carstatus= this.carstatus;
					p.stoptime= this.stoptime;
					p.reserve3= this.reserve3;
					points.push(p);
					
					if(p.reserve3 != null &&　p.reserve3 > 0){
						markpoints.push(p);
					}
				}
			);
			if (d.length == 0) {
				Ext.Msg.alert('提示',"未查询到符合条件的历史轨迹数据");
				points=[];
				points[0]=new BMap.Point(113.885193, 22.57014)
				points[0].address="出厂位置：深圳市宝安区新湖路华美居D区1号楼923-926";
				return;
			}
		
				//初始化地图,选取第一个点为起始点
			map.centerAndZoom(points[0], 15);
			
			map.addOverlay(new BMap.Polyline(points, {strokeColor: "red", strokeWeight: 5, strokeOpacity: 1}));
		
			centerPoint = new BMap.Point(points[0].lng, points[0].lat);
			map.panTo(centerPoint);
		//显示文本
			label = new BMap.Label("", {offset: new BMap.Size(0, -30)});	//设置文本label相对偏移量
			label.setStyle({"border":"1px solid #ccc","padding":"2px"});
			endLabel = new BMap.Label("", {offset: new BMap.Size(0, -30)});	//设置文本label相对偏移量
			endLabel.setStyle({"border":"1px solid #ccc","padding":"2px"});
			if (points.length > 0){
				label.setContent("地址: " + points[0].address+ (points[0].createtime ==undefined ? "" :"<br/>时间："+points[0].createtime));
				endLabel.setContent("地址: " + points[points.length-1].address+ (points[points.length-1].createtime ==undefined ? "" :"<br/>时间："+points[points.length-1].createtime));
			}
			
			map.setViewport(points);
			startpos = new BMap.Marker(points[0], {icon: new BMap.Icon(window.BIZCTX_PATH+"/resource/images/start.png", new BMap.Size(35, 35), {imageOffset: new BMap.Size(0, 0)})});
			startpos.setLabel(label);
			endpos = new BMap.Marker(points[points.length - 1], {icon: new BMap.Icon(window.BIZCTX_PATH+"/resource/images/end.png", new BMap.Size(35, 35), {imageOffset: new BMap.Size(0, 0)})});
			endpos.setLabel(endLabel);
			map.addOverlay(startpos);
			map.addOverlay(endpos);
	
		}else{
			
			//  高德   --轨迹
			
			$(d).each(
				function(i){
					var p = new AMap.LngLat(this.lng, this.lat)
					p.createtime=this.createtime;
					p.address= this.address;
					p.direction= this.direction;
					p.carstatus= this.carstatus;
					p.stoptime= this.stoptime;
					p.reserve3= this.reserve3;
					points.push(p);
					
					if(p.reserve3 != null &&　p.reserve3 > 0){
						markpoints.push(p);
					}
				}
			);
			if (d.length == 0) {
				Ext.Msg.alert('提示',"未查询到符合条件的历史轨迹数据");
				points=[];
				points[0]=new AMap.Position(113.885193, 22.57014)
				points[0].address="出厂位置：深圳市宝安区新湖路华美居D区1号楼923-926";
				return;
			}
			
			centerPoint = new AMap.LngLat(points[0].lng, points[0].lat);
			mapObj.panTo(centerPoint);
			
			var polyline = new AMap.Polyline({
				map:mapObj,
				path:points,
				strokeColor:"red",//线颜色
				strokeOpacity:1,//线透明度
				strokeWeight:3,//线宽
				strokeStyle:"solid"//线样式
			});
			//轨迹起点
			var startcar = new AMap.Marker({                   
			   map:mapObj,                  
			   offset:new AMap.Pixel(-26,-13), //相对于基点的位置
			   icon:new AMap.Icon({image:window.BIZCTX_PATH+"/resource/images/start.png", imageOffset:new AMap.Size(35, 35)}),
			   position:points[0]
		    });
		     // 设置label标签
		    startcar.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
		        offset: new AMap.Pixel(0, -35),//修改label相对于maker的位置
		        content: "地址: " + points[0].address+ (points[0].createtime ==undefined ? "" :"<br/>时间："+points[0].createtime)
		    });
		    
		   
		    //轨迹终点
			var endcar = new AMap.Marker({                   
			   map:mapObj,                  
			   offset:new AMap.Pixel(-26,-13), //相对于基点的位置
			   icon:new AMap.Icon({image:window.BIZCTX_PATH+"/resource/images/end.png", imageOffset:new AMap.Size(35, 35)}),
			   position:points[points.length - 1]
		    });
			// 设置label标签
		    endcar.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
		        offset: new AMap.Pixel(0, -35),//修改label相对于maker的位置
		        content: "地址: " + points[points.length-1].address+ (points[points.length-1].createtime ==undefined ? "" :"<br/>时间："+points[points.length-1].createtime)
		    });
			
			
		}
		
		
		
		
	} else {
		Ext.Msg.alert('提示',"数据加载失败，请稍后再试");
	}
	
};




