Ext.define('SearchMapApp.view.CenterListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    id : 'centerlistview',
    alias : 'widget.centerListView',
	region: "east",
	width:300,
	autoSrcoll:'auto',
	autoHeight:true,
	title:'POI搜索列表',
            collapsible: true,
			store: "PioPlaceStore",
            collapseMode: "mini",
            split: true,
			frame: true,
			multiSelect: true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
			
			columns: [
			{ header: '名称', width : 180, dataIndex: 'name', sortable: true },
			{ header: '地址', width : 220, dataIndex: 'address', sortable: true },
			{ header: '联系电话', width:100, dataIndex: 'telephone', sortable: true },
			{ header: '经纬度', wdith:60, dataIndex: 'lnglat', hidden: true }
			],
			listeners :{
				itemclick : function(view,record,item,rowIndex, e,opts) {//选中时产生的事件
						var lnglat = record.data.lnglat.split(',');
						var pioname = record.data.name;
						addMarker(lnglat[0], lnglat[1],pioname,-1);
				}
			}
			
});




function getlocation(keyword) {
	var cityinfo = "";
	//加载城市查询插件
	AMap.service(["AMap.CitySearch"], function() {
				//实例化城市查询类
				var citysearch = new AMap.CitySearch();
				//自动获取用户IP，返回当前城市
				citysearch.getLocalCity(function(status, result) {
							if (status === 'complete' && result.info === 'OK') {
								if (result && result.city && result.bounds) {
									cityinfo = result.city;
									var citybounds = result.bounds;

									var store = Ext.StoreManager.get('PioPlaceStore');
									// 显示
									var myMask = new Ext.LoadMask(
											Ext.getBody(), {//也可以是Ext.getCmp('').getEl()窗口名称
												msg : "正在操作,请稍后...",//你要写成Loading...也可以
												msgCls : 'z-index:10000;'
											});
									myMask.show();

									store.on('beforeload', function(store,options) {
										var new_params = {
											query : encodeURI(keyword),
											city : encodeURI(city)
										};
										Ext.apply(store.proxy.extraParams,new_params);
									});
									store.load();
									myMask.hide();
								}
							}
						});
			});
}

