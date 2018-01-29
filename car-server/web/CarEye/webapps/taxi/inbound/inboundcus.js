var searchmap;
var mapObj;
var marker;
var markermap = new Map();
var flag = 1; // 1 出发地 2 目的地
var type = 1; // 1代表最后一次约车信息，2代表召车信息
var city;
var maxLen = 20; //获取结果数据条数
var poiaddress;
var poilng;
var poilat;
var oldObj;
var carSelList = new Array();// 存放选择的车辆
$(document).ready(function() {
	$("#moveable_title").not("input").mousedown(function(event) {
				var offset = $("#popupMapDiv").offset();
				x1 = event.pageX - offset.left;
				y1 = event.pageY - offset.top;
				$("#moveable_title").not("input").mousemove(function(event) {
							$("#popupMapDiv").css("left",
									(event.pageX - x1) + "px");
							$("#popupMapDiv").css("top",
									(event.pageY - y1) + "px");
						});
				$("#moveable_title").not("input").mouseup(function(event) {
							$("#moveable_title").unbind("mousemove");
						});
			});
});

/*
 * 初始化地图
 */
function initMap(div) {
	
	poiaddress = "";
	poilng = "";
	poilat = "";
	
	if(maptype == 1){
		// 清空map中marker对象
		searchmap = new BMap.Map("map");
	    //默认位置
	    var point = new BMap.Point(116.3972,39.9096);
	    searchmap.centerAndZoom(point, 12);
	 
	    //获取当前位置
	    var localCity = new BMap.LocalCity();
	    localCity.get(function(result){
	        var cityName = result.name;
	        var localPoint = new BMap.Point(result.center.lng, result.center.lat);
	        searchmap.setCenter(localPoint,cityName);
	    });
	 
	    searchmap.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
	    searchmap.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	    searchmap.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
	    searchmap.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
	    searchmap.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件
	    searchmap.addControl(new BMap.MapTypeControl());          //添加地图类型控件
	    
	    searchmap.addEventListener("click",
			function(e) {
				if (e.overlay) {
					var center = e.point;
					top.Ext.MessageBox.confirm(
					'消息',
					'确认选定此位置为目的地?',
					function(btn) {
						if (btn == 'yes') {
							if (flag == 1) { // 出发地
								if (type == 1) {
									$('#t_saddress').val(poiaddress);
									$('#t_slng').val(center.lng);
									$('#t_slat').val(center.lat);
								} else {
									$('#tr_saddress').val(poiaddress);
									$('#tr_slng').val(center.lng);
									$('#tr_slat').val(center.lat);
								}
							} else if (flag == 2) { // 目的地
								if (type == 1) { // 最后一次约车信息
									$('#t_eaddress').val(poiaddress);
									$('#t_elng').val(center.lng);
									$('#t_elat').val(center.lat);
								} else { // 召车信息
									$('#tr_eaddress').val(poiaddress);
									$('#tr_elng').val(center.lng);
									$('#tr_elat').val(center.lat);
								}
							}
							closeDiv();
						}
				});
			}
		});
	}else{
		// 清空map中marker对象
		markermap.clear();
		mapObj = new AMap.Map("map", {
					resizeEnable : true,
					rotateEnable : true,
					dragEnable : true,
					zoomEnable : true,
					zooms : [3, 18],
					// 二维地图显示视口
					view : new AMap.View2D({
						center : new AMap.LngLat(116.3972,39.9096),// 地图中心点
						zoom : 13
							// 地图显示的缩放级别
						})
				});
		mapObj.plugin(["AMap.ToolBar"], function() {
					// 加载工具条
					var tool = new AMap.ToolBar();
					mapObj.addControl(tool);
				});
	
		marker = new AMap.Marker();
		
		//获取用户所在城市信息
		function showCityInfo() { 
			//加载城市查询插件
			AMap.service(["AMap.CitySearch"], function() {
				//实例化城市查询类
				var citysearch = new AMap.CitySearch();
				//自动获取用户IP，返回当前城市
				citysearch.getLocalCity(function(status, result){
					if(status === 'complete' && result.info === 'OK'){
						if(result && result.city && result.bounds) {
							var cityinfo = result.city;
							var citybounds = result.bounds;
							//地图显示当前城市
							mapObj.setBounds(citybounds);
	                        mapObj.setZoom(10);
						}
					}else{
					}
				});
			});
		}
		
		showCityInfo();
	}
	
	
	
	
	$("#keyWord").val("");
	$("#searchFuzzy").html("");
}


//保存客户信息
$("#customerSaveButton").click(function() {
//	alert("欢迎点击--保存客户信息");
//	return;
	if($("#c_cname").val() == null || $("#c_cname").val().length == 0){
		alert("请输入客户名称");
		return;
	}
	
	if($("#c_phone").val() == null || $("#c_phone").val().length != 11){
		alert("请输入正确的手机号");
		return;
	}
	
	$.ajax({
		url : window.BIZCTX_PATH + '/customer/customerjson/saveCustomer.action',
		data : {
			"customer.id" : $("#c_id").val(),
			"customer.sex" : $("#c_sex").val(),
			"customer.postalcode" : $("#c_postalcode").val(),
			"customer.cname" : $("#c_cname").val(),
			"customer.phone" : $("#c_phone").val(),
			"customer.postaddr" : $("#c_postaddr").val(),
			"customer.typeid" : $("#c_typeid").val(),
			"customer.tel" : $("#c_tel").val(),
			"customer.remark" : $("#c_remark").val()
		},
		type : "post",
		success : function(data) {
			var resp = data.result;
			var su = resp.su;
			if (su == -1) {
				alert("系统异常,请与管理员联系!");
			} else if (su == -3) {
				alert("保存失败，客户名称已被使用!");
			} else {
				alert("保存成功!");
				$('#typename').val($('#typeid').text());
			}
		}
	});
});

//最后一次召车信息-出发地
$("#t_sselmap").click(function() {
			flag = 1;
			var s_lng = $('#t_slng').val();
			var s_lat = $('#t_slat').val();
			$('#mask').fadeIn();
			$('#popupMapDiv').fadeIn();
			initMap($('#popupMapDiv'));
			return false;
		});
		
//最后一次召车信息-目的地	
$("#t_eselmap").click(function() {
			flag = 2;
			var e_lng = $('#t_elng').val();
			var e_lat = $('#t_elat').val();
			$('#mask').fadeIn();
			$('#popupMapDiv').fadeIn();
			initMap($('#popupMapDiv'));
			return false;
		});
		
//保存最后一次召车信息
$("#transactionSaveButton").click(function() {
//	alert("欢迎点击--保存电招信息");
//	return;
	$.ajax({
				url : window.BIZCTX_PATH
						+ '/transaction/transactionjson/saveTransaction.action',
				data : {
					"transaction.id" : $("#t_id").val(),
					"transaction.cid" : $("#t_cid").val(),
					"transaction.slat" : $("#t_slat").val(),
					"transaction.slng" : $("#t_slng").val(),
					"transaction.elat" : $("#t_elat").val(),
					"transaction.elng" : $("#t_elng").val(),
					"transaction.usernametwo" : $("#t_usernametwo").val(),
					"transaction.phone" : $("#t_phone").val(),
					"transaction.typeid" : $("#t_typeid").val(),
					"transaction.saddress" : $("#t_saddress").val(),
					"transaction.usetime" : $("#t_usetime").val(),
					"transaction.eaddress" : $("#t_eaddress").val(),
					"transaction.resstatus" : $("#t_resstatus").val(),
					"transaction.remark" : $("#t_remark").val(),
					"transaction.tratype" : $("#t_tratype").val()
				},
				type : "post",
				success : function(data) {
					var resp = data.result;
					var su = resp.su;
					if (su == -1) {
						alert("系统异常,请与管理员联系!");
					} else {
						alert("保存成功!");
					}
				}
			});
});

//召车信息-出发地
$("#tr_sselmap").click(function() {
			flag = 1;
			type = 2;
			var tr_s_lng = $('#tr_slng').val();
			var tr_s_lat = $('#tr_slat').val();
			$('#mask').fadeIn();
			$('#popupMapDiv').fadeIn();
			initMap($('#popupMapDiv'));
			return false;
		});
		
//召车信息-目的地
$("#tr_eselmap").click(function() {
			flag = 2;
			type = 2;
			var tr_e_lng = $('#tr_elng').val();
			var tr_e_lat = $('#tr_elat').val();
			$('#mask').fadeIn();
			$('#popupMapDiv').fadeIn();
			initMap($('#popupMapDiv'));
			return false;
		});
		
//发送电召
$("#transactionAddButton").click(function() {
//	alert("欢迎点击--发送招车信息");
//	return;
	if($("#tr_slat").val().length == 0 || $("#tr_slng").val().length == 0 ){
		alert("请选择召车出发地");
		return;
	}
	if($("#tr_elat").val().length == 0 || $("#tr_elng").val().length == 0 ){
		alert("请选择召车目的地");
		return;
	}
	if($("#usernametwo").val().length == 0 ){
		alert("请输入用户名");
		return;
	}
	
	if($("#tr_phone").val() == null || $("#tr_phone").val().length != 11){
		alert("请输入正确的联系电话");
		return;
	}
	
	if($("#tr_usetime").val().length == 0 ){
		alert("请选择订单时间");
		return;
	}
	
	$.ajax({
				url : window.BIZCTX_PATH
						+ '/transaction/transactionjson/saveTransaction.action',
				data : {
					"transaction.cid" : $("#tr_cid").val(),
					"transaction.slat" : $("#tr_slat").val(),
					"transaction.slng" : $("#tr_slng").val(),
					"transaction.elat" : $("#tr_elat").val(),
					"transaction.elng" : $("#tr_elng").val(),
					"transaction.usernametwo" : $("#usernametwo").val(),
					"transaction.phone" : $("#tr_phone").val(),
					"transaction.typeid" : $("#tr_typeid").val(),
					"transaction.saddress" : $("#tr_saddress").val(),
					"transaction.usetime" : $("#tr_usetime").val(),
					"transaction.eaddress" : $("#tr_eaddress").val(),
					"transaction.resstatus" : $("#t_resstatus").val(),
					"transaction.remark" : $("#tr_remark").val(),
					"transaction.tratype" : $("#tr_tratype").val(),
					"transaction.carNumbers" : $("#carNumbers").val()
				},
				type : "post",
				success : function(data) {
					var resp = data.result;
					var su = resp.su;
					if (su == -1) {
						alert("系统异常,请与管理员联系!");
					} else {
						alert("召车信息发送成功!");
					}
				}
			});
});

//搜索poi
$("#submitSearch").click(function() {
	
	if(maptype == 1){
		var keyword = $("#keyWord").val();
		if (keyword == null || keyword == "") {
			alert("请输入要搜索的关键字");
			return;
		}
	
		$('#searchFuzzy').html("正在加载数据,请稍侯...");
		baiduPlaceSearch(keyword);
	}else{
		var keyword = $("#keyWord").val();
		if (keyword == null || keyword == "") {
			alert("请输入要搜索的关键字");
			return;
		}
		var arrays = new Array();
		// 获取用户所在城市信息
		function showCityInfo() {
			// 加载城市查询插件
			AMap.service(["AMap.CitySearch"], function() {
						// 实例化城市查询类
						var citysearch = new AMap.CitySearch();
						// 自动获取用户IP，返回当前城市
						citysearch.getLocalCity(function(status, result) {
									if (status === 'complete'
											&& result.info === 'OK') {
										if (result && result.city && result.bounds) {
											city = result.city;
										}
									} else {
										city = result.info;
									}
								});
					});
		}
		showCityInfo();
	
		AMap.service(["AMap.PlaceSearch"], function() {
			MSearch = new AMap.PlaceSearch({ // 构造地点查询类
				pageSize : poipagesize,
				pageIndex : 1,
				city : city
					// 城市
			});
			// 关键字查询
			MSearch.search(keyword, function(status, result) {
				var data = "<div class=\"poi\">";
				if (status === 'complete' && result.info === 'OK') {
					var poiList = result.poiList.pois;
					for (var i = 0; i < poiList.length; i++) {
						var poi = poiList[i];
						var location = poi.location.lng + "," + poi.location.lat;
						
//						data += '<div class="poilist" style="cursor:pointer" onclick="addMarker('
//								+ poi.location.lng
//								+ ','
//								+ poi.location.lat
//								+ ',\''
//								+ poi.name
//								+ '\')"><div style="text-align:left" class="poicontent" lnglat='
//								+ location
//								+ ' title="'
//								+ poi.name
//								+ '">'
//								+ poi.name
//								+ '<br/><span style="font-size:14px;color:#4F4F4F">'
//								+ poi.address + '</span></div>' + '</div>';
								
						data += "<div class=\"kk\" bgcolor=\"#FFFFFF\" style=\"cursor:hand;\" ";
						data += "onclick=\"addMarker(this,+'"+poi.location.lng+"','"+poi.location.lat+"','"+poi.name+"')\">";
						data += "<div class=\"xian04\"></div>";
						data += "<div class=\"dm\"><p>"+poi.name+"</p></div>";
						data += "<p class=\"p01\">"+poi.address+"</p><p class=\"p02\">"+poi.tel+"</p></div>";
						
					}
				} else if (status === 'no_data') {
					data += "<div class=\"am-list-item\">没有找到相关记录</div>";
				}
				data += "</div>";
				$("#searchFuzzy").html(data);
			});
	
		});
	}
	
//	searchmap.clearOverlays();
//	
//	// 搜索
//	var local = new BMap.LocalSearch(searchmap, {
//		renderOptions : {
//			map : searchmap,
//			panel : 'searchFuzzy'
//		},
//		pageCapacity : 4
//	});
//	local.search(keyword);
//	local.getResults();
/*	local.setSearchCompleteCallback(function(searchResult) {
		//组装list
		var data = "<div class=\"poi\">";
		if (local.getStatus() == BMAP_STATUS_SUCCESS) {
			for (var i = 0; i < searchResult.ZP; i++) {
				var poi = searchResult.getPoi(i);;
				var location = poi.point.lng + "," + poi.point.lat;

				data += '<div class="poilist" onclick="addMarker('
						+ location.lng
						+ ','
						+ location.lat
						+ ',\''
						+ poi.title
						+ '\')"><div style="text-align:left" class="poicontent" lnglat='
						+ location
						+ ' title="'
						+ poi.title
						+ '">'
						+ poi.title
						+ '<br/><span style="font-size:14px;color:#4F4F4F">'
						+ poi.address + '</span></div>' + '</div>';
			}
		} else if (status === 'no_data') {
			data += "<div class=\"am-list-item\">没有找到相关记录</div>";
		}
		data += "</div>";
		$("#searchFuzzy").html(data);
		
	});*/
	
});

//百度地图加载
function baiduPlaceSearch(keyword) {
	mapsearch = 1;
    searchmap.clearOverlays();
	// 面板结果搜索
/*	var local = new BMap.LocalSearch(baidumap, {
		renderOptions : {
			map : baidumap,
			city: cityName, //城市
			panel : 'searchFuzzy'
		},
		pageCapacity : 5
	});
	local.search(keyword);
	local.setSearchCompleteCallback(function(searchResult) {
	});*/
    
    //数据搜索
    var options = {
		onSearchComplete: function(results){
			if (local.getStatus() == BMAP_STATUS_SUCCESS) {
				var count = results.getNumPois();
				if(count == 0){
					$('#searchFuzzy').html("无查询结果。");
				}else{
					//百度搜索关键字回调
					baiduKeywordSearch_CallBack(results);
				}
			}
		}
	};
	var local = new BMap.LocalSearch(searchmap, options);
	local.setPageCapacity(maxLen);
	local.search(keyword);
    
}

//百度关键字搜索回调函数
function baiduKeywordSearch_CallBack(results) {
	var poiArr = [];
	for (var i = 0; i < results.getCurrentNumPois(); i ++){
		poiArr.push(results.getPoi(i));
	}
				
    var resultCount = poiArr.length;
    
	var resultStr = '';		
	try{
		for(var i=0; i < resultCount; i++){
			//var obj = _gaodeMap.loaclResu[i];
			var name = poiArr[i].title;
			name=name.replace(/'/g,"’");
			var daddr = poiArr[i].address;
			var posX =  poiArr[i].point.lng;
			var posY =  poiArr[i].point.lat;
			var phoneNumber = poiArr[i].phoneNumber;
			if(phoneNumber == undefined){
				phoneNumber = "";
			}
			//
			resultStr += "<div class=\"kk\" bgcolor=\"#FFFFFF\" style=\"cursor:hand;\" ";
			resultStr += "onclick=\"searchPoiClick(this,'"+name+"','"+posX+"','"+posY+"','"+daddr+"')\">";
			resultStr += "<div class=\"xian04\"></div>";
			resultStr += "<div class=\"dm\"><p>"+name+"</p></div>";
			resultStr += "<p class=\"p01\">"+daddr+"</p><p class=\"p02\">"+phoneNumber+"</p></div>";
		}
		
	}catch(ee){
	}
	$('#searchFuzzy').html(resultStr);
}

//百度地图点击搜索结果显示地图
function searchPoiClick(obj,poiName,posX,posY,address){
	if(oldObj != undefined){
		oldObj.getElementsByTagName("div")[0].setAttribute("class", "xian04");
	}
	obj.getElementsByTagName("div")[0].setAttribute("class", "xian02");
	oldObj = obj;
	
	//设置中心点
	$('#lng').val(posX);
	$('#lat').val(posY);
	$('#poiaddress').val(address);
	
	baiduMarkPosition(poiName,posX,posY,address);
}

/**
 * 百度地图显示位置点
 * @param {} poiName
 * @param {} posX
 * @param {} posY
 * @param {} address
 */
function baiduMarkPosition(poiName,posX,posY,address){
	searchmap.clearOverlays();
	
	var point = new BMap.Point(posX, posY);
	var marker = new BMap.Marker(point);
	
//	var label = new BMap.Label(poiName,{offset:new BMap.Size(20,-10)});
//	marker.setLabel(label);
	var opts = {
	  width : 70,     // 信息窗口宽度
	  // height: 150,     // 信息窗口高度
	  //title : infoTitle , // 信息窗口标题
	  enableMessage:false //设置允许信息窗发送短息
	};
	var infoWindow = new BMap.InfoWindow("" +
		"<table style='font-size: 12px;text-align: left;width:220px;'>" +
		"<tr><td width='40px;'><font color = '#0000ff'><b>名称：</b></font></td><td colspan='3' style='text-align: left'><font color='#004a91'>"+poiName+"</font></td></tr>"+  
		"<tr><td width='40px;'><font color = '#0000ff'><b>地址：</b></font></td><td colspan='3' style='text-align: left'><font color='#004a91'>"+address+"</font></td></tr>"+  
		"</table>", opts);  // 创建信息窗口对象道路运输证：
	infoWindow.enableAutoPan();
	infoWindow.disableCloseOnClick();
		
	marker.addEventListener("click", function(){
		this.openInfoWindow(infoWindow);
	});
	
	searchmap.addOverlay(marker);  
	marker.openInfoWindow(infoWindow);
    searchmap.centerAndZoom(point, 18);
    
    poiaddress = poiName;
    poilng = posX;
	poilat = posY;
}

function addPosition(){
	if(poilng == ''){
		alert('请选择地址！');
		return;
	}
	if (flag == 1) { // 出发地
		if (type == 1) {
			$('#t_saddress').val(poiaddress);
			$('#t_slng').val(poilng);
			$('#t_slat').val(poilat);
		} else {
			$('#tr_saddress').val(poiaddress);
			$('#tr_slng').val(poilng);
			$('#tr_slat').val(poilat);
		}
	} else if (flag == 2) { // 目的地
		if (type == 1) { // 最后一次约车信息
			$('#t_eaddress').val(poiaddress);
			$('#t_elng').val(poilng);
			$('#t_elat').val(poilat);
		} else { // 召车信息
			$('#tr_eaddress').val(poiaddress);
			$('#tr_elng').val(poilng);
			$('#tr_elat').val(poilat);
		}
	}
	closeDiv();
}


//选择召车车辆
$("#carChooseButton").click(function() {
			var lng = $("#tr_slng").val();
			var lat = $("#tr_slat").val();
			if (lng == '' || lat == '') {
				alert("召车出发地不能为空!");
				return;
			}
			$("#carNumbers").val("");
			carSelList.length = 0;
			$('#mask').fadeIn();
			$('#carChooseDiv').fadeIn();
			$('#radius').val(1000);
			$("#carSearch").trigger("click");
			$(".xiala").hide();
			x=0;
			return false;
		});
		

function closeDiv() {
	$('#popupMapDiv').fadeOut();
	$('#carChooseDiv').fadeOut();
	$('#mask').fadeOut();
}

var x=0;

$(".aa").click(function () {
	if(x==0){
		$(".xiala").show();
		x=1;
	}else{
   	 $(".xiala").hide();
		x=0;
	}
})

function dj(obj){
	var val = obj.innerHTML;
	var kzstate = obj.className;
	$('#kz').val(val);
	$('#kzstate').val(kzstate);
	$(".xiala").hide();
	x=0;
}

//车辆搜索
$("#carSearch").click(function() {
	var lng = $("#tr_slng").val();
	var lat = $("#tr_slat").val();
	var radius = $("#radius").val();
	var kzstate = $("#kzstate").val();
	if (lng == '' || lat == '') {
		alert("召车出发地不能为空!");
		return;
	}
	carSelList.length = 0;
	var arrays = new Array();
	$("#carList").html("");
	var str = "";
	$.ajax({
		type : "post",
		url : window.BIZCTX_PATH + '/poiinfojson/queryCarInfoList.action',
		data : "lng=" + lng + "&lat=" + lat + "&mileage=" + radius + "&kzstate=" + kzstate,
		dataType : "json",
		async : false,
		success : function(result) {
			if (!result.result.list.length > 0) {
				str = "<div class=\"empty\">周边无车辆!</div>";
			} 
			$.each(result.result.list, function(i, item) {
						if (item.carstatus == null) {
							item.carstatus = "";
						}
						if (item.speed == null) {
							item.speed = "";
						}
						if (item.address == null) {
							item.address = "";
						}
						switch (item.acc) {
							case 0 :
								item.acc = "关";
								break;
							case 1 :
								item.acc = "开";
								break;
							default :
								item.acc = "";
						}
						str += '<div class="biaog biaog02"  onclick="selCar(this,\''+ item.carnumber + '\')">' +
								'<div class="b01"></div><div class="b02">'+ item.carnumber +'</div><div class="b03">' +
								item.carstatus +'</div><div class="b04">'+ item.acc +'</div>' +
								'<div class="b05">'+ item.speed +'</div><div class="b06">' +
								item.address +'</div></div>';
						
					});
		}
	});
	$("#carList").html(str);
})
function selCar(obj,name) {
	var index = carSelList.indexOf(name);
	var classname = obj.getElementsByTagName("div")[0].className;
	if(classname == 'b01'){
		obj.getElementsByTagName("div")[0].setAttribute("class", "rrr");
		if (index < 0) {
			carSelList.push(name);
		}
	}else{
		obj.getElementsByTagName("div")[0].setAttribute("class", "b01");
		if (index > 0) {
			carSelList = carSelList.slice(0, index).concat(carSelList.slice(index+1));
		} else if (index == 0) {
			carSelList.shift();
		}
	}
	
}
function addCarList() {
	var names = "";
	for (var i = 0, len = carSelList.length; i < len; i++) {
		if (i != len - 1) {
			names += carSelList[i] + ",";
		} else {
			names += carSelList[i];
		}
	}
	$("#carNumbers").val(names);
	closeDiv();
}

function loadUsetwoname(input) {
	if(input.value.length > 0){
		$("#usernametwo").val(input.value);
	}
}


//百度地图点击搜索结果显示地图
function addMarker(obj,lng, lat, pioname) {
	if(oldObj != undefined){
		oldObj.getElementsByTagName("div")[0].setAttribute("class", "xian04");
	}
	obj.getElementsByTagName("div")[0].setAttribute("class", "xian02");
	oldObj = obj;
	
	mapObj.clearMap();
	marker = new AMap.Marker({
				map : mapObj,
				title : pioname,
				position : new AMap.LngLat(lng, lat), // 位置
				icon : "http://webapi.amap.com/images/marker_sprite.png"
			});

	// 自定义点标记内容
	var markerContent = document.createElement("div");
	markerContent.className = "markerContentStyle";
	// 点标记中的图标
	var markerImg = document.createElement("img");
	markerImg.className = "markerlnglat";
	markerImg.src = "http://webapi.amap.com/images/marker_sprite.png";
	markerContent.appendChild(markerImg);

	// 点标记中的文本
	var markerSpan = document.createElement("span");
	markerSpan.style.width = 100;
	markerSpan.style.height = 20;
	markerSpan.innerHTML = pioname;

	markerContent.appendChild(markerSpan);
	marker.setContent(markerContent);// 更新点标记内容

	AMap.event.addListener(marker, 'click', function(e) {
				if (flag == 1) { // 出发地
					if (type == 1) {
						$('#t_saddress').val(pioname);
						$('#t_slng').val(lng);
						$('#t_slat').val(lat);
					} else {
						$('#tr_saddress').val(pioname);
						$('#tr_slng').val(lng);
						$('#tr_slat').val(lat);
					}
				} else if (flag == 2) { // 目的地
					if (type == 1) { // 最后一次约车信息
						$('#t_eaddress').val(pioname);
						$('#t_elng').val(lng);
						$('#t_elat').val(lat);
					} else { // 召车信息
						$('#tr_eaddress').val(pioname);
						$('#tr_elng').val(lng);
						$('#tr_elat').val(lat);
					}
				}
				closeDiv();
			});
	mapObj.setFitView();
	
	poiaddress = pioname;
    poilng = lng;
	poilat = lat;
}

