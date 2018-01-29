Ext.define('FMS.view.CarRealTime',{
	extend:'Ext.grid.Panel',
	alias:'widget.carRealTime',
	title:'车辆实时信息',
    minHeight: 130,
    id:'carRealTime_list',
    aotoHeight:true,
    collapsible: true,
	header : false, // 显示 header 默认 true
	markDirty : false,
	border : false,
    store:'TerminalPositionStore',
    multiSelect: true,
	stripeRows:true, //表格是否隔行换色，默认为false
	loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false  //,mode:'SINGLE'
	selModel: { selType: 'checkboxmodel',listeners :{
			    select : function(rowmodel,select){
					  var carid = rowmodel.getSelection()[0].get('carid');
					  var store = Ext.StoreManager.get('TerminalPositionStore');
					  for(var i=0;i<store.getCount();i++){
							if(store.getAt(i).data.carid == carid){
								if(maptype == 1){
									var blng = $("#gridview-"+realcaridvalue+"-table tr:eq("+i+") td:nth-child(9) div:nth-child(1)").html();
									var blat = $("#gridview-"+realcaridvalue+"-table tr:eq("+i+") td:nth-child(10) div:nth-child(1)").html();
									map.setViewport(new Array(new BMap.Point(blng,blat)));
									break;
								}else{
									var carnumber = store.getAt(i).get('carnumber');
									var marker = markermap.get(carnumber);
									var pointinfo = [];
							 		pointinfo.push(marker);
									mapObj.setFitView(pointinfo);
									break; 
								}
								 
							}
					  }   	  
				}
	}}, //在首列實現checkbox，僅此在首列
	columns: [
	new Ext.grid.RowNumberer({header:"编号",width:35,align:"center"}), 
	{ header: '车牌号', flex: 1, dataIndex: 'carnumber', sortable: true,hideable:false},
	{ header: '空重车状态', flex: 1, dataIndex: 'kzstate', sortable: true ,hideable:false,renderer:function(value){
		return chageKzstate(value);
	}},
	{ header: '在线状态', flex: 1, dataIndex: 'carstatus', sortable: true,hideable:false,renderer:function(value){
		return chageCarStatus(value);
	}},
	{ header: 'ACC状态', flex: 1, dataIndex: 'acc', sortable: true ,hideable:false,renderer:function(value){
		if(value == 0){
			return "关";
		}else if(value == 1){
			return "开";
		}else{
			return "";
		}
	}},
	{ header: '速度(km/h)', flex: 1, dataIndex: 'speed', sortable: true,hideable:false },
	{ header: '方向', flex: 1, dataIndex: 'direction', sortable: true,hideable:false,renderer:function(value){
		if(value == null || value == "null" || value == ""){
			return "";
		}
		if(value ==0){
			return "正北";
		}else if(value >0 && value <90){
			return "东北";
		}else if(value == 90){
			return "正东";
		}else if(value >90 && value <180){
			return "东南";
		}else if(value == 180){
			return "正南";
		}else if(value >180 && value <270){
			return "西南";
		}else if(value == 270){
			return "正西";
		}else if(value >270 && value <360){
			return "西北";
		}else if(value == 360){
			return "正北";
		}else{
			return value;
		}
	}},
	{ header: '经度', flex: 1, dataIndex: 'blng', sortable: false,hideable:false },
	{ header: '纬度', flex: 1, dataIndex: 'blat', sortable: false,hideable:false },
	{ header: '当前位置', flex: 3, dataIndex: 'address', sortable: false,hideable:false },
	{ header: '总里程(千米)', flex: 1, dataIndex: 'mileage', sortable: true,hideable:false},
	{ header: '更新时间', flex: 2, dataIndex: 'createtime', sortable: true ,hideable:false},
	{ header: 'GPS是否有效', flex: 1, dataIndex: 'gpsflagtext', hidden: true,hideable:false },
	{ header: '车辆id', flex: 0.5, dataIndex: 'carid', hidden: true,hideable:false },
	{ header: '高度', flex: 1, dataIndex: 'altitude', hidden: true,hideable:false },
	{ header: '终端设备号', flex: 1, dataIndex: 'terminal', hidden: true,hideable:false },
	{ header: '手机号', flex: 1, dataIndex: 'phone', hidden: true,hideable:false }
	],
	listeners: {
			'collapse': {
	            fn: function(){
	            	Ext.getCmp('taxiMapPanel').setSize(Ext.getCmp('taxiMapPanel').getSize().width,Ext.getCmp('taxiMapPanel').getSize().height+130);
	            }
            },
            'expand':{
	            fn: function(){
	            	Ext.getCmp('taxiMapPanel').setSize(Ext.getCmp('taxiMapPanel').getSize().width,Ext.getCmp('taxiMapPanel').getSize().height-130);
	            }          	
        }
       },
    viewConfig : {
		markDirty : false
	}

});