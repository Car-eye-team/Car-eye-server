Ext.define('RemoteControlApp.view.CenterListView' ,{
    extend: 'Ext.panel.Panel',
    border : false,
    id : 'centerlistview',
    alias : 'widget.centerListView',
	dock: 'right',
	width:300,
	frame : true,
	autoScroll:true,
	title:'POI搜索列表',
	items : [{
		    xtype: 'grid',
			store: "PioPlaceStore",
            split: true,
			height:400,
			multiSelect: true,
			viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			columns: [
			{ header: '名称', width : 150, dataIndex: 'name', sortable: true },
			{ header: '地址', width : 200, dataIndex: 'address', sortable: true },
			{ header: '联系电话', width:100, dataIndex: 'telephone', hidden: true },
			{ header: '经纬度', width:60, dataIndex: 'lnglat', hidden: true }
			],
			listeners :{
				itemclick : function(view,record,item, rowIndex, e,opts) {//选中时产生的事件
						var lnglat = record.data.lnglat.split(',');
						var pioname = record.data.name;
						addMarker(lnglat[0], lnglat[1],pioname);
				}
			}
			}]
			
});
