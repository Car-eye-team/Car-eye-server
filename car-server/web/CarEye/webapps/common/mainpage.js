var menuidArray = new Array();   //存放三级菜单id

//扩展ie不支持indexOf方法
Array.prototype.indexOf = function(item) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == item)
		return i;
	}
	return -1;
} 


//修改密码
function editpassword(){
   Ext.create('Ext.window.Window', {
		    title: '修改密码',
		    id:'lock_window',
		    width: 350,
		    animCollapse:false,
			constrain : true,
			constrainHeader : true,
			maximizable : false,
			minimizable : false,
			frame : true,
		    modal : true,
		    closable : false,
		    layout: 'fit',
		    items: {  
		        xtype: 'form',
		        id:'open_lock_form',
		        border:false,
		        frame : false,
		        items : [ {
		xtype : 'form',
		frame : false,
		border:false,
		anchor : '100%',
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 80
	    },
		items : [ {
					xtype : 'textfield',
					fieldLabel : '原密码',
					margin:'10 5 0 0',
					name : 'user.password',
					id :'edit_password',
					anchor : '100%',
					cls : 'x-textfield',
					minLength : 6,
					minLengthText : '最小长度不小于6位!',
					maxLength : 20,
					maxLengthText : '最大长度不超过20位!',
					allowBlank : false,
					blankText : '原密码不能为空',
					regex : /[a-zA-Z0-9]{6,20}/,
					regexText : '只能输入6~20个必须含有字母或数字的字符串!',
					inputType : 'password',
					enableKeyEvents : true
				},{
					xtype : 'textfield',
					fieldLabel : '新密码',
					margin:'5 5 0 0',
					name : 'user.newpassword',
					id :'edit_newpassword',
					anchor : '100%',
					cls : 'x-textfield',
					minLength : 6,
					minLengthText : '最小长度不小于6位!',
					maxLength : 20,
					maxLengthText : '最大长度不超过20位!',
					allowBlank : false,
					blankText : '新密码不能为空',
					regex : /[a-zA-Z0-9]{6,20}/,
					regexText : '只能输入6~20个必须含有字母或数字的字符串!',
					inputType : 'password',
					enableKeyEvents : true
				},{
					xtype : 'textfield',
					fieldLabel : '重复新密码',
					name : 'user.renewpassword',
					margin:'5 5 5 0',
					id :'edit_renewpassword',
					anchor : '100%',
					cls : 'x-textfield',
					minLength : 6,
					minLengthText : '最小长度不小于6位!',
					maxLength : 20,
					maxLengthText : '最大长度不超过20位!',
					allowBlank : false,
					blankText : '重复新密码不能为空',
					regex : /[a-zA-Z0-9]{6,20}/,
					regexText : '只能输入6~20个必须含有字母或数字的字符串!',
					inputType : 'password',
					enableKeyEvents : true
				} ]
	} ]
	    },
	    buttons: [{
			text: '修改',
			handler: function(button){
				
			var win = button.up('window');
			var form = win.down('form');
			if (!form.getForm().isValid()) {
				return;
			}
			form.getForm().submit({
				url : window.BIZCTX_PATH + '/userLoginJson/editPassWord.action',
				method : 'post',
				waitMsg:'请稍候...',
				success : function(form, action) {
						var resp = action.result.result;
						var su = resp.su;
						if (su == -1) {
							Ext.Msg.alert("提示","原始密码不正确,请重新输入!");
							Ext.getCmp('edit_password').setValue("");
							Ext.getCmp('edit_password').focus(true, true);
						}else if (su == -2) {
							Ext.Msg.alert("提示","两次密码输入不一致!");
							Ext.getCmp('edit_newpassword').setValue("");
							Ext.getCmp('edit_renewpassword').setValue("");
							Ext.getCmp('edit_newpassword').focus(true, true);
						}else {
							win.close();
							Ext.Msg.alert("提示","修改密码成功,系统需重新登陆!");
							window.location.href = window.BIZCTX_PATH  + '/index.jsp';
						}
			    },
				failure : function(form, action) {
					win.close();
					Ext.Msg.alert('提示', "创建用户组信息失败!");
				}
			});
			}
		},{
			text: '取消',
			handler: function(btn){
				btn.up('window').close();
				}
			}]
		}).show();
}
//增加tab
function addTab(tabid,text,hrefTarget){
		var tabPanel = Ext.getCmp("taxiMapPanel");
		var url = window.BIZCTX_PATH + hrefTarget;
		if (!Ext.getCmp('tabpanel'+ tabid )) {
			 tabPanel.add({
				title:text,
				id:'tabpanel'+tabid,
				//加载修改首页内容的form表单页面
				html:"<iframe width=100% height=100% frameborder='no' style='padding:5px;'  border='0' marginwidth='0' marginheight='0'" +
						"scrolling='auto'  src='" + url + "'></iframe>",
				//可以被关闭
				closable:true,
				//自动销毁
				autoDestroy:true
			});
		}
		tabPanel.setActiveTab('tabpanel'+tabid);
}
	

//高德地图矩形拉框    
var gaodecomplete = function(lnglat){
	    var array = lnglat.split(",")
	    //lat > lat1  lng1>lng
		var lng = array[0];
		var lat = array[1];
		var lng1 = array[2];
		var lat1 = array[5];
		var view = Ext.widget('searchCarView');
        view.show();
		var store =Ext.StoreManager.get('SearchCarListStore');
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
            var new_params = { 
            	lng:lng,
            	lat:lat,
            	lng1:lng1,
            	lat1:lat1
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.loadPage(1); 
};




function getIcon(carstatus){
	var icon = "";
	if(carstatus == 7){
		icon = window.BIZCTX_PATH +"/resource/images/inline_1.png";
	}else if(carstatus == 1){
		icon = window.BIZCTX_PATH +"/resource/images/offline_1.png";
	}else if(carstatus == 2){
		icon = window.BIZCTX_PATH +"/resource/images/offline_1.png";
	}else if(carstatus == 3){
		icon = window.BIZCTX_PATH +"/resource/images/shutdown_1.png";
	}else if(carstatus == 5){
		icon = window.BIZCTX_PATH +"/resource/images/drivering_1.png";
	}else if(carstatus == 4){
		icon = window.BIZCTX_PATH +"/resource/images/stop_1.png";
	}else if(carstatus == 6){
		icon = window.BIZCTX_PATH +"/resource/images/warn_1.png";
	}else{
		icon = window.BIZCTX_PATH +"/resource/images/drivering_1.png";
	}
	return icon;
}

function getCarstatusText(carstatus){
	var text = "";
	if(carstatus == 7){
		text = "在线";
	}else if(carstatus == 1){
		text = "长时间离线";
	}else if(carstatus == 2){
		text = "离线";
	}else if(carstatus == 3){
		text = "熄火";
	}else if(carstatus == 5){
		text = "行驶";
	}else if(carstatus == 4){
		text = "停车";
	}else if(carstatus == 6){
		text = "报警";
	}else if(carstatus == 8){
		text = "未定位";
	}else{
		text = "长时间离线";
	}
	return text;
}
	
//车辆树右键菜单按权限显示
var hidderContent = function(menuid){
	if(parent.menuidArray.indexOf(menuid) == -1){
		return true;
	}else{
		return false;
	}
}


//车辆定时跟踪
function init() {
	//逆序
//	points.reverse();
	
	var pointarr =[];
	pointarr[0] = points[0];
	if(points.length >= 2){
		pointarr[1] = points[1];
	}
	
	map.addOverlay(new BMap.Polyline(pointarr, {strokeColor: "red", strokeWeight:1.5, strokeOpacity: 1}));
	
	centerPoint = new BMap.Point(points[0].lng, points[0].lat);
	map.panTo(centerPoint);
	label = new BMap.Label("", {offset: new BMap.Size(0, -20)});	//设置小车label相对小车的偏移量
	label.setStyle({"border":"1px solid #ccc","padding":"2px"});
	label.setContent("地址: " + points[0].address+ (points[0].datetime ==undefined ? "" :"<br/>时间："+points[0].datetime));
	car = new BMap.Marker(points[0], {icon: new BMap.Icon(window.BIZCTX_PATH+"/resource/images/carTrack.png", new BMap.Size(30, 30), {imageOffset: new BMap.Size(0, 0)})});
	car.setLabel(label);
	map.addOverlay(car);
		
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
	//点标记中的文本
    var markerSpan = document.createElement("span");
	markerContent.className = "gaodemarkerContentStyle";
	//点标记中的图标
	var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	markerImg.src=window.BIZCTX_PATH+"/resource/images/drivering_1.png";   
	markerContent.appendChild(markerImg);
	markerSpan.innerHTML = "地址: " + point.address+ (point.createtime ==undefined ? "" :"<br/>时间："+point.createtime+"<br/>状态："+chageCarStatus(point.carstatus));
    markerContent.appendChild(markerSpan);
    return markerContent;
}