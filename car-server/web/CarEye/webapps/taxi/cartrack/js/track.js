//获取所有点的坐标
//var points = [];
var mappoints =[];

var car;   //汽车图标
var label; //信息标签
var centerPoint;
var speed=100; //速度，300毫秒

var timer;     //定时器
var index = 0; //记录播放到第几个point
var currentCarList = [];	//当前车辆列表  tab切换为一页

//点标记中的文本
var markerSpan = document.createElement("span");

function pointHandler(response) {
	var text = response.responseText;
	var obj = eval( "(" + text + ")" );//转换后的JSON对象
	var result = obj.result;
	
	if (result) {
		var d = obj.result.list;
		points = [];
		if(maptype == 1){
			$(d).each(
				
					function(i){
						var p = new BMap.Point(this.lng, this.lat);
						p.createtime=this.createtime;
						p.address= this.address;
						p.direction= this.direction;
						p.carstatus= this.carstatus;
						p.stoptime= this.stoptime;
						points.push(p);
					}
			);
			if (d.length == 0) {
				Ext.Msg.alert('提示',"未查询到符合条件的历史轨迹数据");
				points=[];
				points[0]=new BMap.Point(113.885193, 22.57014)
				points[0].address="出厂位置：深圳市宝安区新湖路华美居D区1号楼923-926";
				return;
			}
		}else{
			index = 0;
			$(d).each(
					function(i){
						var p = new AMap.LngLat(this.lng, this.lat)
						p.createtime=this.createtime;
						p.address= this.address;
						p.direction= this.direction;
						p.carstatus= this.carstatus;
						points.push(p);
					}
			);
			if (d.length == 0) {
				Ext.Msg.alert('提示',"未查询到符合条件的历史轨迹数据");
				points=[];
				points[0]=new AMap.LngLat(113.885193, 22.57014);
				points[0].address="出厂位置：深圳市宝安区新湖路华美居D区1号楼923-926";
				return;
			}
		}
		init();
	} else {
		Ext.Msg.alert('提示',"数据加载失败，请稍后再试");
	}
	
}

function init() {
	
	if(maptype == 1){
		//初始化地图,选取第一个点为起始点   ---百度
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
			
			Ext.getCmp('play').setDisabled(false);
			Ext.getCmp('reset').setDisabled(false);
			Ext.getCmp('slider').setDisabled(false);
	}else{
			//初始化地图,选取第一个点为起始点--高德
	//	points.reverse();
		
		centerPoint = new AMap.LngLat(points[0].lng, points[0].lat);
		mapTrack.panTo(centerPoint);
		
		var polyline = new AMap.Polyline({
			map:mapTrack,
			path:points,
			strokeColor:"red",//线颜色
			strokeOpacity:1,//线透明度
			strokeWeight:3,//线宽
			strokeStyle:"solid"//线样式
		});
		
		Ext.getCmp('play').setDisabled(false);
		Ext.getCmp('reset').setDisabled(false);
		Ext.getCmp('slider').setDisabled(false);
		
		car = new AMap.Marker({                   
		   map:mapTrack,                  
		   offset:new AMap.Pixel(-26,-13), //相对于基点的位置
		   icon:window.BIZCTX_PATH+"/resource/images/drivering_1.png",
		   position:points[0],
		   content:getCarContent(points[0]),
		   angle:points[0].direction
	   });
	   
	    //轨迹起点
		var startcar = new AMap.Marker({                   
		   map:mapTrack,                  
		   offset:new AMap.Pixel(-26,-13), //相对于基点的位置
		   icon:new AMap.Icon({image:window.BIZCTX_PATH+"/resource/images/start.png", imageOffset:new AMap.Size(35, 35)}),
		   position:points[0]
	   });
	   
	    //轨迹终点
		var endcar = new AMap.Marker({                   
		   map:mapTrack,                  
		   offset:new AMap.Pixel(-26,-13), //相对于基点的位置
		   icon:new AMap.Icon({image:window.BIZCTX_PATH+"/resource/images/end.png", imageOffset:new AMap.Size(35, 35)}),
		   position:points[points.length - 1]
	   });
			
		mapTrack.setFitView();
		
		}

	
}


/**
 * 播放
 */
function play() {
    Ext.getCmp('play').setDisabled(true);
    Ext.getCmp('pause').setDisabled(false);
    Ext.getCmp('reset').setDisabled(false);
    speed = Ext.getCmp('ct_rate').getValue();
	var point = points[index];
	
	if(maptype == 1){
		if(index > 0) {
			map.addOverlay(new BMap.Polyline([points[index - 1], point], {strokeColor: "red", strokeWeight: 1, strokeOpacity: 1}));
		}
	
		label.setContent("地址: " + point.address+ (point.createtime ==undefined ? "" :"<br/>时间："+point.createtime+"<br/>状态："+chageCarStatus(point.carstatus)));
		car.setPosition(point);
		car.setRotation(point.direction);
	}else{
		if(index > 0) {
			car.setPosition(point);
		    car.setContent(getCarContent(point));//更新点标记内容
		    car.setAngle(parseInt(point.direction));
		}
	}
	
	index++;
	Ext.getCmp('slider').setValue(parseInt(index/points.length*100));
	if(Ext.getCmp('checkbox').checked) {
		if(maptype == 1){
			map.panTo(point);
		}else{
			mapTrack.panTo(point);
		}
		
	}
	if(index < points.length) {
		timer = window.setTimeout("play(" + index + ")", speed);
	} else {
		Ext.Msg.alert('提示',"历史轨迹播放完成!");
		Ext.getCmp('play').setDisabled(false);
    	Ext.getCmp('pause').setDisabled(false);
    	if(maptype == 1){
			map.panTo(point);
		}else{
			mapTrack.panTo(point);
		}
		reset();
	}
}

/**
 * 暂停
 */
function pause() {
	Ext.getCmp('play').setDisabled(false);
    Ext.getCmp('pause').setDisabled(true);
    Ext.getCmp('reset').setDisabled(false);
	if(timer) {
		window.clearTimeout(timer);
	}
}

/**
 * 重置
 */
function reset() {
	Ext.getCmp('play').setDisabled(false);
    Ext.getCmp('pause').setDisabled(true);
    Ext.getCmp('reset').setDisabled(false);
    Ext.getCmp('slider').setValue(0);
	if(timer) {
		window.clearTimeout(timer);
	}
	index = 0;
	car.setPosition(points[0]);
	
	if(maptype == 1){
		if (points.length > 0)
			label.setContent("地址: " + points[0].address+ (points[0].createtime ==undefined ? "" :"<br/>时间："+points[0].createtime+"<br/>状态："+chageCarStatus(points[0].carstatus)));
			map.panTo(centerPoint);
	}else{
		if (points.length > 0)
			car.setContent(getCarContent(points[0]));
			car.setAngle(parseInt(points[0].direction));
			mapTrack.setFitView();
	}
	
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

function getCarContent(point){
	//自定义点标记内容  
	var markerContent = document.createElement("div");
	markerContent.className = "gaodecarmarkerContentStyle";
	//点标记中的图标
	var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	markerImg.src=window.BIZCTX_PATH+"/resource/images/drivering_1.png";   
	markerContent.appendChild(markerImg);
	markerSpan.innerHTML = "地址: " + point.address+ (point.createtime ==undefined ? "" :"<br/>时间："+point
.createtime+"<br/>状态："+chageCarStatus(point.carstatus));
    markerContent.appendChild(markerSpan);
    return markerContent;
}

