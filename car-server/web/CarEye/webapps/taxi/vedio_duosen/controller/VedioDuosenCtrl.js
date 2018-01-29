var cmsv6_demo;
var devid;
var ServerIP = "120.31.131.73";
var ServerPort = "6603";

var clearflag = false;//回放查询时是否清空列表状态
var eid = 0;//每次收到回放列表时开始的eid值

Ext.define('VedioDuosenApp.controller.VedioDuosenCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['FileTypeStore','ChannelStore','PlaybackSearchFileStore'],//声明该控制层要用到的store
    views: ['VedioView','VedioView1','VedioRight'],//声明该控制层要用到的view
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
			'vedioView':{
                 render : this.initOcx
            },
            'VedioRight button[action=startRecord]' : {
				click : this.startRecord
			}
		});
	},
	//初始化控件
	initOcx: function() {
	    devid = document.getElementById("terminal").value;
		if(window.ActiveXObject || "ActiveXObject" in window){
			init();
//			config();
		}else{
			Ext.Msg.alert("提示","视频功能只能在IE浏览器中使用!");
		}
	},
	//开始回放视频
	startRecord: function(){
//		var grid = button.up('grid');
		var grid=Ext.getCmp('vedioRight');
		var store = this.getPlaybackSearchFileStoreStore();
		var records = grid.getSelectionModel().getSelection();
		if(records.length != 1){
			Ext.Msg.alert('提示', '请选择一条视频信息');
			return;
		}
	    
	    var id = records[0].get('id');
	    var filename = records[0].get('filename');
	    var splaysec = records[0].get('splaysec');
	    var eplaysec = records[0].get('eplaysec');
	    
	    startRecord(id,filename,splaysec,eplaysec);
		
		return false;
	}
});
