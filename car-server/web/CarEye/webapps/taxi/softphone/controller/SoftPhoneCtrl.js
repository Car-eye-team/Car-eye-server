var mapObj;
var marker;
var markermap = new Map();
var flag=1; //1 出发地  2 目的地
var type = 1; //1代表最后一次约车信息，2代表召车信息
var city;

Ext.define('SoftPhoneApp.controller.SoftPhoneCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['TypeListStore','SexStore','TransactionTypeListStore','ResstatusStore','TraTypeStore',
    		 'PioPlaceStore','GroupStore','CarListStore'],//声明该控制层要用到的store
    models: ['TypeModel','TransactionTypeModel','PioPlaceModel','CarInfoModel'],//声明该控制层要用到的model
    views: ['CustomInfoView','CenterListView','MapView','MapWindowView','SearchMapView'],
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'customInfoView',
            selector: 'customInfoView'
        }
    ],
    init: function() {
		this.control({
			'customInfoView #saveCustomInfo':{
				click:this.saveCustomInfo
			},
			'customInfoView #tiredset':{
				click:this.tiredset
			},
			'customInfoView #addTransactionInfo':{
				click:this.addTransactionInfo
			},
			'customInfoView #tr_isgroup':{
				select:this.showCarCombo
			},
			'customInfoView':{
				afterrender:this.loadCustomer
			},
			'searchMapView': {
                afterrender:function(component, eOpts){
                    //初始化地图
                    render: this.initMap(component.el.dom);
                }
            },
            'customInfoView button[action=sselmap]' : {
				click : this.saddress
			},
			'customInfoView button[action=eselmap]' : {
				click : this.eaddress
			},
            'customInfoView button[action=tr_sselmap]' : {
				click : this.tr_saddress
			},
			'customInfoView button[action=tr_eselmap]' : {
				click : this.tr_eaddress
			},
			'mapView button[action=searchtext]' : {
				//click : this.searchtext
				click : this.searchPOI
			}
		});
	},
	 /*
        初始化地图
    */
    initMap : function(div) {
		// 清空map中marker对象
		markermap.clear();
		mapObj = new AMap.Map("searchMapView", {
					resizeEnable : true,
					rotateEnable : true,
					dragEnable : true,
					zoomEnable : true,
					zooms : [3, 18],
					// 二维地图显示视口
					view : new AMap.View2D({
						center : new AMap.LngLat(116.397428, 39.90923),// 地图中心点
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
	},
	saddress:function(){
    	flag=1;
		var s_lng=Ext.getCmp('slng').getValue();
		var s_lat=Ext.getCmp('slat').getValue();
    	
    	var view = Ext.widget('mapWindowView');
		view.show();
		//定位
    	if(s_lng.length>0){
    	  geocoder(s_lng,s_lat);
    	}
		return false;
    },
    eaddress:function(){
    	flag=2;
    	var e_lng=Ext.getCmp('elng').getValue();
		var e_lat=Ext.getCmp('elat').getValue();
    	var view = Ext.widget('mapWindowView');
		view.show();
		//定位
    	if(e_lng.length>0){
    	  geocoder(e_lng,e_lat);
    	}
		return false;
    },
	tr_saddress:function(){
    	flag=1;
    	type = 2;
		var tr_s_lng=Ext.getCmp('tr_slng').getValue();
		var tr_s_lat=Ext.getCmp('tr_slat').getValue();
    	
    	var view = Ext.widget('mapWindowView');
		view.show();
		//定位
    	if(tr_s_lng.length>0){
    	  geocoder(tr_s_lng,tr_s_lat);
    	}
		return false;
    },
    tr_eaddress:function(){
    	flag=2;
    	type = 2;
    	var tr_e_lng=Ext.getCmp('tr_elng').getValue();
		var tr_e_lat=Ext.getCmp('tr_elat').getValue();
    	var view = Ext.widget('mapWindowView');
		view.show();
		//定位
    	if(tr_e_lng.length>0){
    	  geocoder(tr_e_lng,tr_e_lat);
    	}
		return false;
    },
    searchtext:function(){
    	var keyword = Ext.getCmp('addr_search').getValue();
		if (keyword == null || keyword == ""){
			Ext.Msg.alert('提示',"请输入要搜索的关键字");
			return;
		}
		getlocation(keyword);
//		getwordSearch(keyword);
    },
	searchPOI:function(button){
		var keyword = Ext.getCmp('addr_search').getValue();
		if (keyword == null || keyword == "") {
			Ext.Msg.alert('提示', "请输入要搜索的关键字");
			return;
		}
		var store=Ext.StoreManager.get("PioPlaceStore");
		store.removeAll();
		var arrays=new Array();
		
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
							city = result.city;
						}
					}else{
						city=result.info ;
					}
				});
			});
		}
		showCityInfo();
		AMap.service(["AMap.PlaceSearch"], function() {       
	        MSearch = new AMap.PlaceSearch({ //构造地点查询类
	            pageSize:poipagesize,
	            pageIndex:1,
	            city:city //城市
	        });
	        //关键字查询
	        MSearch.search(keyword, function(status, result){
	        	if(status === 'complete' && result.info === 'OK'){
	        		
	        		var poiList= result.poiList.pois;
	        		for(var i=0;i<poiList.length;i++){
        			   var poi=poiList[i];
        			   var location=poi.location.lng+","+poi.location.lat;
						
					   var obj=new Object();
					   obj.name=poi.name;
					   obj.address=poi.address;
					   obj.lnglat=location;
					   obj.telephone=poi.tel;
					   arrays[i]=obj;
	        		}
	        	}else if(status === 'no_data'){
	        	}
	        	 store.addSorted(arrays);  
	        }); 
	        
	    });
	},
	saveCustomInfo :function(button){
		var form = Ext.getCmp('customerform');
		if (!form.getForm().isValid()) {
			return;
		}
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/customer/customerjson/saveCustomer.action',
			method : 'post',
			urlMsg:'正在保存请稍候...',
			success : function(form, action) {
					var resp = action.result.result;
					var su = resp.su;
					 if (su == -1) {
						Ext.Msg.alert("提示","系统异常,请与管理员联系!");
					}else if (su == -3) {
						Ext.Msg.alert("提示","保存失败，客户名称已被使用!");
					}else {
						Ext.Msg.alert("提示","保存成功!");
						Ext.getCmp('typename').setValue(Ext.getCmp('typeid').getRawValue());
					}
		    },
			failure : function(form, action) {
				Ext.Msg.alert('提示', "操作信息失败!");
			}
		});
		return false;
	},
	tiredset :function(button){
		var form = Ext.getCmp('transaction_form');
		if (!form.getForm().isValid()) {
			return;
		}
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/transaction/transactionjson/saveTransaction.action',
			method : 'post',
			urlMsg:'正在保存请稍候...',
			success : function(form, action) {
					var resp = action.result.result;
					var su = resp.su;
					 if (su == -1) {
						Ext.Msg.alert("提示","系统异常,请与管理员联系!");
					}else {
						Ext.Msg.alert("提示","保存成功!");
					}
		    },
			failure : function(form, action) {
				Ext.Msg.alert('提示', "操作信息失败!");
			}
		});
		return false;
	},
	addTransactionInfo :function(button){
		var form = Ext.getCmp('transaction_add_form');
		if (!form.getForm().isValid()) {
			return;
		}
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/transaction/transactionjson/saveTransaction.action',
			method : 'post',
			urlMsg:'正在保存请稍候...',
			success : function(form, action) {
					var resp = action.result.result;
					var su = resp.su;
					 if (su == -1) {
						Ext.Msg.alert("提示","系统异常,请与管理员联系!");
					}else {
						Ext.Msg.alert("提示","召车信息发送成功!");
					}
		    },
			failure : function(form, action) {
				Ext.Msg.alert('提示', "召车信息发送失败!");
			}
		});
		return false;
	},
	loadCustomer :function(button){
		var phone = document.getElementById('customer_phone').value;
		if(phone.length == 0){
			return;
		}
		
		//加载客户信息信息
		var form = Ext.getCmp('customerform');
		form.getForm().load({
    		  url: window.BIZCTX_PATH + '/customer/customerjson/loadCustomer.action', //请求的服务器地址
		      params:{ 
		      		phone : phone
		      },
		      success : function(form, action) {
		      }
    	 });
    	 
    	//加载最后一次约车信息
		var form = Ext.getCmp('transaction_form');
		form.getForm().load({
    		  url: window.BIZCTX_PATH + '/transaction/transactionjson/loadLastTransaction.action', //请求的服务器地址
		      params:{ 
		      		phone : phone
		      },
		      success : function(form, action) {
		      		var dataphone =  action.result.data.t_phone;
		      		var username =  action.result.data.username;
		      		var t_typeid =  action.result.data.t_typeid;
		      		var usetime =  action.result.data.usetime;
		      		var tr_cid =  action.result.data.cid;
		      		if(dataphone != null && typeof dataphone != "undifined" && dataphone.length > 0){
		      			Ext.getCmp('transaction_form').setVisible(true);
		      			Ext.getCmp('tiredset').setVisible(true);
		      			Ext.getCmp('emptymessage').setVisible(false);
		      		}
		      		
		      		//赋值召车信息
		      		Ext.getCmp('tr_username').setValue(username == null?phone:username);
		      		Ext.getCmp('tr_phone').setValue(dataphone == null?phone:dataphone);
		      		Ext.getCmp('tr_typeid').setValue(t_typeid);
		      		Ext.getCmp('usetime').setValue(usetime);
		      		Ext.getCmp('tr_cid').setValue(tr_cid);
		      		
		      }
    	 });
		return false;
	},
	showCarCombo :function(button){
		var isgroup = Ext.getCmp('tr_isgroup').getValue();	//0否1是
		if(isgroup == 1){
			Ext.getCmp('tr_carnumber').setVisible(true);
		}else{
			Ext.getCmp('tr_carnumber').setVisible(false);
			Ext.getCmp('tr_carnumber').setValue("");
		}
		return false;
	}
	
	
});

function getlocation(keyword){
	   var cityinfo="";
		//加载城市查询插件
		AMap.service(["AMap.CitySearch"], function() {
				//实例化城市查询类
				var citysearch = new AMap.CitySearch();
				//自动获取用户IP，返回当前城市
				citysearch.getLocalCity(function(status, result){
					if(status === 'complete' && result.info === 'OK'){
						if(result && result.city && result.bounds) {
							cityinfo = result.city;
							var citybounds = result.bounds;
							
		var store = Ext.StoreManager.get('PioPlaceStore');
		// 显示
	    var myMask = new Ext.LoadMask(Ext.getBody(), {//也可以是Ext.getCmp('').getEl()窗口名称
            msg    : "正在操作,请稍后...",//你要写成Loading...也可以
            msgCls : 'z-index:10000;'
        }); 
        myMask.show();
        
	     Ext.getCmp('centerlistview').expand();
			store.on('beforeload', function (store, options) {
			            var new_params = { 
				            query: encodeURI(keyword),
				            city: encodeURI(cityinfo)
			            };
			            Ext.apply(store.proxy.extraParams, new_params);
			        });
			     store.load();
       			myMask.hide();
			}
					}
				});
			});
}



function addMarker(lng, lat,pioname) {
	map.clearOverlays();
	marker = new AMap.Marker({                   
	   map:mapObj,  
	   title:pioname,
	   position:new AMap.LngLat(lng,lat), //位置
	   icon:"http://webapi.amap.com/images/marker_sprite.png"
    });
			   
    //自定义点标记内容  
    var markerContent = document.createElement("div");
    markerContent.className = "markerContentStyle";
    //点标记中的图标
    var markerImg= document.createElement("img");
    markerImg.className="markerlnglat";
    markerImg.src="http://webapi.amap.com/images/marker_sprite.png";   
    markerContent.appendChild(markerImg);
      
    //点标记中的文本
    var markerSpan = document.createElement("span");
    markerSpan.style.width=100;
    markerSpan.style.height=20;
    markerSpan.innerHTML = pioname;
    
    markerContent.appendChild(markerSpan);
    marker.setContent(markerContent);//更新点标记内容
    
    AMap.event.addListener(marker, 'click', function(e) {
    	if (flag == 1) {	//出发地
			if(type == 1){	
				Ext.getCmp('saddress').setValue(pioname);
				Ext.getCmp('slng').setValue(lng);
				Ext.getCmp('slat').setValue(lat);
			}else{
				Ext.getCmp('tr_saddress').setValue(pioname);
				Ext.getCmp('tr_slng').setValue(lng);
				Ext.getCmp('tr_slat').setValue(lat);
			}
		} else if (flag == 2) {	//目的地
			if(type == 1){	//最后一次约车信息
				Ext.getCmp('eaddress').setValue(pioname);
				Ext.getCmp('elng').setValue(lng);
				Ext.getCmp('elat').setValue(lat);
			}else{		//召车信息
				Ext.getCmp('tr_eaddress').setValue(pioname);
				Ext.getCmp('tr_elng').setValue(lng);
				Ext.getCmp('tr_elat').setValue(lat);
			}
		}
	Ext.getCmp('mapWindowView').close();
    });
    mapObj.setFitView();
}

function geocoder(lng,lat) {
	 var lnglatXY = new AMap.LngLat(lng,lat);
     marker = new AMap.Marker({
        map:mapObj,
        icon: new AMap.Icon({
            image: "http://api.amap.com/Public/images/js/mark.png",
            size:new AMap.Size(58,30),
            imageOffset: new AMap.Pixel(-32, -0)
        }),
        position: lnglatXY,
        offset: new AMap.Pixel(-5,-30)
    });
    mapObj.setFitView();
}


