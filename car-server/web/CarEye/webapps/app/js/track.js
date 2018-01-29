//获取所有点的坐标
var points = [];
var mappoints =[];
var markpoints =[];

var car;   //汽车图标
var endCar;
var label; //信息标签
var endLabel;
var centerPoint;
var speed=100; //速度，300毫秒

var timer;     //定时器
var index = 0; //记录播放到第几个point
var currentCarList = [];	//当前车辆列表  tab切换为一页

function pointHandler(response) {
	var text = response.responseText;
	var obj = eval( "(" + text + ")" );//转换后的JSON对象
	var result = obj.result;
	
	if (result) {
		var d = obj.result.list;
		points = [];
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
//	points.reverse();
	map.centerAndZoom(points[0], 15);
	
	map.addOverlay(new BMap.Polyline(points, {strokeColor: "red", strokeWeight: 5, strokeOpacity: 1}));
	
	//点亮操作按钮
//		playBtn.disabled = false;
//		resetBtn.disabled = false;
	
	//画面移动到起点和终点的中间
//		centerPoint = new BMap.Point((points[0].lng + points[points.length - 1].lng) / 2, (points[0].lat + points[points.length - 1].lat) / 2);
		centerPoint = new BMap.Point(points[0].lng, points[0].lat);
		map.panTo(centerPoint);
	//显示小车子
		label = new BMap.Label("", {offset: new BMap.Size(0, -45)});	//设置小车label相对小车的偏移量
		label.setStyle({"border":"1px solid #ccc","padding":"2px"});
		endLabel = new BMap.Label("", {offset: new BMap.Size(0, -45)});	//设置小车label相对小车的偏移量
		endLabel.setStyle({"border":"1px solid #ccc","padding":"2px"});
//		label.setContent("经度: " + points[0].lng + "<br>纬度: " + points[0].lat+ (points[0].createtime ==undefined ? "" :"<br/>时间："+points[0].createtime));
		if (points.length > 0){
			label.setContent("地址: " + points[0].address+ (points[0].createtime ==undefined ? "" :"<br/>时间："+points[0].createtime));
			endLabel.setContent("地址: " + points[points.length-1].address+ (points[points.length-1].createtime ==undefined ? "" :"<br/>时间："+points[points.length-1].createtime));
		}
		car = new BMap.Marker(points[0], {icon: new BMap.Icon(window.BIZCTX_PATH+"/resource/images/drivering_1.png", new BMap.Size(30, 30), {imageOffset: new BMap.Size(0, 0)})});
		car.setLabel(label);
		car.setRotation(points[0].direction);
		map.addOverlay(car);
		//终点
		endCar = new BMap.Marker(points[points.length-1], {icon: new BMap.Icon(window.BIZCTX_PATH+"/resource/images/drivering_1.png", new BMap.Size(30, 30), {imageOffset: new BMap.Size(0, 0)})});
		endCar.setLabel(endLabel);
		endCar.setRotation(points[points.length-1].direction);
		map.addOverlay(endCar);
		
		map.setViewport(points);
		startpos = new BMap.Marker(points[0], {icon: new BMap.Icon(window.BIZCTX_PATH+"/resource/images/start.png", new BMap.Size(35, 35), {imageOffset: new BMap.Size(0, 0)})});
		endpos = new BMap.Marker(points[points.length - 1], {icon: new BMap.Icon(window.BIZCTX_PATH+"/resource/images/end.png", new BMap.Size(35, 35), {imageOffset: new BMap.Size(0, 0)})});
		map.addOverlay(startpos);
		map.addOverlay(endpos);
//		map.addOverlay(new BMap.Marker(new BMap.Point(points[0].lng, points[0].lat)));  	//增加起点标注
//		map.addOverlay(new BMap.Marker(new BMap.Point(points[points.length - 1].lng, points[points.length - 1].lat)));  	//增加终点标注

		for(var i=0;i<markpoints.length;i++){
			var iconimg = "/resource/images/none_tripteack_1.png";
			if(markpoints[i].reserve3 == 4){ //急加速
				iconimg = "/resource/images/none_tripteack_4.png";
			}else if(markpoints[i].reserve3 == 5){ //急减速
				iconimg = "/resource/images/none_tripteack_3.png";
			}else if(markpoints[i].reserve3 == 11){ //急变道
				iconimg = "/resource/images/none_tripteack_2.png";
			}else if(markpoints[i].reserve3 == 12){ //急转弯
				iconimg = "/resource/images/none_tripteack_7.png";
			}else if(markpoints[i].reserve3 == 22){ //点火
				iconimg = "/resource/images/none_tripteack_6.png";
			}else if(markpoints[i].reserve3 == 23){ //熄火
				iconimg = "/resource/images/none_tripteack_5.png";
			}
			
			var markpos = new BMap.Marker(markpoints[i], {icon: new BMap.Icon(window.BIZCTX_PATH+iconimg, new BMap.Size(39, 60), {imageOffset: new BMap.Size(0, 0)})});
		    map.addOverlay(markpos);
		}
		
	} else {
		Ext.Msg.alert('提示',"数据加载失败，请稍后再试");
	}
	
}

function init() {
	
	//初始化地图,选取第一个点为起始点
//	points.reverse();
	map.centerAndZoom(points[0], 15);
	
	map.addOverlay(new BMap.Polyline(points, {strokeColor: "red", strokeWeight: 5, strokeOpacity: 1}));
	
	//点亮操作按钮
//		playBtn.disabled = false;
//		resetBtn.disabled = false;
	
	//画面移动到起点和终点的中间
//		centerPoint = new BMap.Point((points[0].lng + points[points.length - 1].lng) / 2, (points[0].lat + points[points.length - 1].lat) / 2);
		centerPoint = new BMap.Point(points[0].lng, points[0].lat);
		map.panTo(centerPoint);
	//显示小车子
		label = new BMap.Label("", {offset: new BMap.Size(0, -45)});	//设置小车label相对小车的偏移量
		label.setStyle({"border":"1px solid #ccc","padding":"2px"});
//		label.setContent("经度: " + points[0].lng + "<br>纬度: " + points[0].lat+ (points[0].createtime ==undefined ? "" :"<br/>时间："+points[0].createtime));
		if (points.length > 0)
			label.setContent("地址: " + points[0].address+ (points[0].createtime ==undefined ? "" :"<br/>时间："+points[0].createtime+"<br/>状态："+chageCarStatus(points[0].carstatus)));
		car = new BMap.Marker(points[0], {icon: new BMap.Icon(window.BIZCTX_PATH+"/resource/images/drivering_1.png", new BMap.Size(30, 30), {imageOffset: new BMap.Size(0, 0)})});
		car.setLabel(label);
		car.setRotation(points[0].direction);
		map.addOverlay(car);
		
		map.setViewport(points);
		startpos = new BMap.Marker(points[0], {icon: new BMap.Icon(window.BIZCTX_PATH+"/resource/images/start.png", new BMap.Size(35, 35), {imageOffset: new BMap.Size(0, 0)})});
		endpos = new BMap.Marker(points[points.length - 1], {icon: new BMap.Icon(window.BIZCTX_PATH+"/resource/images/end.png", new BMap.Size(35, 35), {imageOffset: new BMap.Size(0, 0)})});
		map.addOverlay(startpos);
		map.addOverlay(endpos);
//		map.addOverlay(new BMap.Marker(new BMap.Point(points[0].lng, points[0].lat)));  	//增加起点标注
//		map.addOverlay(new BMap.Marker(new BMap.Point(points[points.length - 1].lng, points[points.length - 1].lat)));  	//增加终点标注
		
}

/**
 * 转换carstatus
 */
function chageCarStatus(carstatus){
	if(carstatus == 7){
		carstatus = '在线';
	}else if(carstatus == 1){
		carstatus = "长时间离线";
	}else if(carstatus == 2){
		carstatus = "离线";
	}else if(carstatus == 3){
		carstatus = '熄火';
	}else if(carstatus == 5){
		carstatus = '行驶';
	}else if(carstatus == 4){
		carstatus = '停车';
	}else if(carstatus == 6){
		carstatus = '报警';
	}else if(carstatus == 8){
		carstatus = '未定位';
	}else{
		carstatus = '';
	}
	return carstatus;
}
