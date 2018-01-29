Ext.define("WindowSetApp.store.RemindTreeStore",{
	extend:"Ext.data.TreeStore",
	autoLoad :false,
	root : {
		expanded : true,
		text : '选择提醒类型'
	},
	 proxy: {
		 type: 'ajax',
		 url: window.BIZCTX_PATH + '/servlet/ShowAllRemindTree',
		 reader: {
			 type: 'json'
		 }
	 }
 });