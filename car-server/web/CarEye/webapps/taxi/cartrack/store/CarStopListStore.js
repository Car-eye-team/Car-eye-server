Ext.define("CarTrackApp.store.CarStopListStore",{
	extend:"Ext.data.Store",
	model : 'CarTrackApp.model.CarTrackModel',
	autoLoad: false,
	proxy: {
		type: 'ajax',
		url :window.BIZCTX_PATH +"/carjson/findCarTrackPointList.action", // 请求路径
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });