Ext.define('VedioGuojiApp.controller.VedioGuojiCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['FileTypeStore','ChannelStore','PlaybackSearchFileStore'],//声明该控制层要用到的store
    models: ['PlaybackSearchFileModel'],//声明该控制层要用到的model
    views: ['VedioView','VedioRight'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'vedioView',
            selector: 'vedioView'
        }
        ,{
            ref: 'vedioRight',
            selector: 'vedioRight'
        }
    ],
    init: function() {
    	
		this.control({
			'vedioView' : {
				afterrender : this.loadParameter
			},
			'vedioRight button[action=searchbottom]' : {
				click : this.playbackSearchFile
			},
			'vedioRight button[action=download]' : {
				click : this.download
			},
			
			'vedioView':{
                 render : this.initOcx
            }
		});
	},
	//下载
	download : function(button){
	    var lChannelNumber = Ext.getCmp('v_channel').getValue();
		var lFileType = Ext.getCmp('v_fileType').getValue();
		if(lFileType == "" || lFileType == null){
			lFileType = 0;
		}
		var lpStartTime = Ext.util.Format.date(Ext.getCmp('v_stime').getValue(), 'Y-m-d H:i:s');
		var lpStopTime  = Ext.util.Format.date(Ext.getCmp('v_etime').getValue(), 'Y-m-d H:i:s');
		
		if(lChannelNumber ==null || lChannelNumber ==""){
			alert("请选择通道!");
			return;
		}
		if(lpStartTime ==null || lpStartTime ==""){
			alert("请选择开始时间!");
			return;
		}
		if(lpStopTime ==null || lpStopTime ==""){
			alert("请选择结束时间!");
			return;
		}
		
//		vedioPlaybackSearchFile(lChannelNumber,lFileType,lpStartTime,lpStopTime);
		StartPlaybackRec(lChannelNumber,lFileType,lpStartTime,lpStopTime);
	},
	//搜索视频文件
	playbackSearchFile : function(){
		
//		var store = this.getPlaybackSearchFileStoreStore();
//		store.clearFilter(true);
//		store.reload();
		
		var lChannelNumber = Ext.getCmp('v_channel').getValue();
		var lFileType = Ext.getCmp('v_fileType').getValue();
		if(lFileType == "" || lFileType == null){
			lFileType = 0;
		}
		var lpStartTime = Ext.util.Format.date(Ext.getCmp('v_stime').getValue(), 'Y-m-d H:i:s');
		var lpStopTime  = Ext.util.Format.date(Ext.getCmp('v_etime').getValue(), 'Y-m-d H:i:s');
		
		if(lChannelNumber ==null || lChannelNumber ==""){
			alert("请选择通道!");
			return;
		}
		if(lpStartTime ==null || lpStartTime ==""){
			alert("请选择开始时间!");
			return;
		}
		if(lpStopTime ==null || lpStopTime ==""){
			alert("请选择结束时间!");
			return;
		}
		
		vedioPlaybackSearchFile(lChannelNumber,lFileType,lpStartTime,lpStopTime);
	},
	//初始化控件
	initOcx: function() {
		    devid = document.getElementById("terminal").value;
			if(window.ActiveXObject){
					cmsv6_demo = document.getElementById("cmsv6");   
				    cmsv6_demo.ServerIP= ServerIP;
				    cmsv6_demo.ServerPort = ServerPort;
			}else{
				Ext.Msg.alert("提示","视频功能只能在IE浏览器中使用!");
			}
	},
	loadParameter : function() {
//			alert(RegIp);
			return false;
	}
	
});
