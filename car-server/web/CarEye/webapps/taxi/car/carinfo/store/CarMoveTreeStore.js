Ext.define("CarInfoApp.store.CarMoveTreeStore",{
	 extend:"Ext.data.TreeStore",
//	 autoLoad : false,
	 root : {expanded : true,text : '车辆列表' },
	 proxy: {
		 type: 'ajax',
		 url:window.BIZCTX_PATH + '/servlet/CarTree', 
		 reader: {
			 type: 'json'
		 }
	 }
 });